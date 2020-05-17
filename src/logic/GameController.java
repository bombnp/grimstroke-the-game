package logic;

import application.Utility;
import database.Database;
import entity.Updatable;
import entity.minion.Minion;
import entity.tower.CannonTower;
import entity.tower.MachineGunTower;
import entity.tower.RocketTower;
import entity.tower.base.Tower;
import exception.InvalidTowerException;
import gui.*;
import javafx.animation.AnimationTimer;

import java.util.ArrayList;

/**
 * The GameController class controls all the data related to gameplay. It stores the references to
 * all {@link Tower Towers}, {@link Minion Minions}, and particles. It also controls the player's
 * health and gold. It contains various methods that can initialize or restart the game. <br>
 * <br>
 * This class also implements the game loop, calling the {@link Updatable#update(double) update} method
 * of all {@link Updatable} objects.
 */
public class GameController {
    /**
     * The currently selected {@link TowerCell}.
     */
    private static TowerCell selectedTower;

    /**
     * The path of the minions.
     */
    private static ArrayList<Vector2> minionPath;

    /**
     * The currently active {@link Updatable Updatables}.
     */
    private static final ArrayList<Updatable> updatables = new ArrayList<>();

    /**
     * The {@link Updatable Updatables} marked for removal.
     */
    private static final ArrayList<Updatable> garbageCollector = new ArrayList<>();

    /**
     * The {@link Updatable Updatables} waiting to be added to the {@link #updatables} list.
     */
    private static final ArrayList<Updatable> updatablesAddList = new ArrayList<>();

    /**
     * The currently active {@link Minion Minions}.
     */
    private static final ArrayList<Minion> minions = new ArrayList<>();

    /**
     * The speed of {@link Minion Minions}.
     */
    public static final double minionSpeed = 48;

    /**
     * The speed of {@link entity.particle.base.Bullet Bullets}.
     */
    public static final double bulletSpeed = 192;

    /**
     * The maximum amount of health the player has. ({@value})
     */
	private static final int maxHp = 20;

    /**
     * The current amount of health the player has.
     */
	private static int currentHp = 20;

    /**
     * The current amount of gold the player has.
     */
    private static int gold = 10;

    /**
     * Indicates whether the game is over or not.
     */
    private static boolean isGameOver = false;

    /**
     * Initializes the game. Loads the map from the file and sets up the board, then
     * creates the game loop.
     * @param mapName The name of the map to be loaded.
     */
    public static void initialize(String mapName) {
        String[][] mapCSV = Utility.readCSV("map/" + mapName + "_Map.csv");
        String[][] decorCSV = Utility.readCSV("map/" + mapName + "_Decor.csv");
        initializeMap(mapCSV, decorCSV);

        minionPath = Utility.readMinionPath("map/" + mapName + "_MinionPath.csv");
        
        new AnimationTimer() {
            private long lastFrameNanoTime = System.nanoTime();

            @Override
            public void handle(long currentNanoTime) {
                double deltaTime = (currentNanoTime - lastFrameNanoTime) / 1000000000.0;

                if (!isGameOver) {
                    for (Updatable updatable : updatables) {
                        updatable.update(deltaTime);
                    }
                }

                for (Updatable garbage : garbageCollector) {
                    if (garbage instanceof Minion){
                        minions.remove(garbage);
                        for (Updatable updatable : updatables) {
                            if (updatable instanceof Tower && ((Tower) updatable).getCurrentTarget() == garbage) {
                                ((Tower) updatable).setCurrentTarget(null);
                            }
                        }
                        if (garbage.equals(GamePane.entityInformationPane.getSelectedMinion())) {
                            GamePane.entityInformationPane.setVisible(false);
                        }
                    }
                    updatables.remove(garbage);
                    //noinspection SuspiciousMethodCalls
                    GUIController.getGamePane().getChildren().remove(garbage);

                    if (garbage instanceof Minion && minions.isEmpty() && !MinionWaveController.isSpawning()) {
                        if (MinionWaveController.getWaveNumber() == Database.waves.length-1) {
                            new WinGamePane();
                        } else {
                            GUIController.getGamePane().getNextWaveButton().setDisable(false);
                            MinionWaveController.increaseWaveNumber();
                        }
                    }
                }
                garbageCollector.clear();

                updatables.addAll(updatablesAddList);
                updatablesAddList.clear();

                lastFrameNanoTime = currentNanoTime;
            }
        }.start();
        
    }

    /**
     * Initializes the game board with the given data.
     * @param mapCSV The data of the map itself.
     * @param decorCSV The data of decorations on the map. Also marks what cells are buildable.
     */
    public static void initializeMap(String[][] mapCSV, String[][] decorCSV) {
        int height = mapCSV.length;
        int width = mapCSV[0].length;

        BoardGrid boardGrid = GUIController.getBoardGrid();

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                int mapSprite = Integer.parseInt(mapCSV[row][col]);
                int decorSprite = Integer.parseInt(decorCSV[row][col]);

                BoardCell cell = boardGrid.addCell(mapSprite, row, col, decorSprite == Sprite.BUILD_SPOT);
                if (decorSprite != -1 && decorSprite != Sprite.BUILD_SPOT) {
                    cell.addImage(decorSprite);
                }
            }
        }
    }

    /**
     * Restarts the game without restarting the application.
     */
    public static void restart() {
        currentHp = 20;
        gold = 10;
        isGameOver = false;

        for (Updatable updatable : updatables) {
            if (updatable instanceof Tower) {
                ((Tower) updatable).getCell().removeTower();
            } else {
                //noinspection SuspiciousMethodCalls
                GUIController.getGamePane().getChildren().remove(updatable);
            }
        }

        updatables.clear();
        minions.clear();
        updatablesAddList.clear();
        MinionWaveController.setWaveNumber(1);
        GamePane.playerInformationPane.updateData();
        GamePane.entityInformationPane.setVisible(false);

        setSelectedTower(null);
        GUIController.getGamePane().getNextWaveButton().setDisable(false);
    }

    /**
     * Removes the given {@link Updatable} from the list. The given updatable is added to the
     * {@link #garbageCollector} so it can be removed after the update process.
     * @param updatable The {@link Updatable} to be removed.
     */
    public static void removeUpdatable(Updatable updatable) {
        garbageCollector.add(updatable);
    }

    /**
     * Adds the given {@link Updatable} to the list. The given updatable is added to the
     * {@link #updatablesAddList} so it can be added after the update process.
     * @param updatable The {@link Updatable} to be added.
     */
    public static void addUpdatable(Updatable updatable) {
        updatablesAddList.add(updatable);
    }

    /**
     * Sets the {@link #selectedTower}. Also changes the {@link TowerCell TowerCells'}
     * {@link javafx.scene.layout.Background Backgrounds} accordingly.
     * @param towerCell The newly selected {@link TowerCell}.
     */
    public static void setSelectedTower(TowerCell towerCell) {
        if (selectedTower != null)
            selectedTower.setCurrentBG(GUIController.BG_TOWER_UNSELECTED);
        selectedTower = towerCell;
        if (selectedTower != null)
            selectedTower.setCurrentBG(GUIController.BG_TOWER_SELECTED);
    }

    /**
     * Generates a new {@link Tower} instance based on the {@link #selectedTower}.
     * @param targetCell The cell for the new {@link Tower} to be bound to.
     * @return A new {@link Tower} instance based on the {@link #selectedTower}.
     * @throws InvalidTowerException Thrown with the {@link #selectedTower} is incorrect.
     */
    public static Tower generateSelectedTower(BoardCell targetCell) throws InvalidTowerException {
        if (selectedTower == null)
            throw new InvalidTowerException("No tower is selected");
        switch (selectedTower.getName()) {
            case "Machine Gun Tower" : return new MachineGunTower(targetCell, 1);
            case "Rocket Tower" : return new RocketTower(targetCell, 1);
            case "Cannon Tower" : return new CannonTower(targetCell, 1);
            default: throw new InvalidTowerException("Tower name is incorrect.");
        }
    }

    /**
     * Checks if the player can buy the given tower with the given level.
     * @param tower The tower to check.
     * @param level The level of the tower to check.
     * @return True if the player can buy it, false otherwise.
     */
    public static boolean canBuy(Tower tower, int level) {
        switch (tower.getClass().getName()) {
            case "entity.tower.MachineGunTower" : return gold >= Database.MG[level-1].cost;
            case "entity.tower.RocketTower" : return gold >= Database.Rocket[level-1].cost;
            case "entity.tower.CannonTower" : return gold >= Database.Cannon[level-1].cost;
            default: return false;
        }
    }

    /**
     * Checks if the player can buy the given tower.
     * @param towerCell The tower to check.
     * @return True if the player can buy it, false otherwise.
     */
    public static boolean canBuy(TowerCell towerCell) {
        if (towerCell == null)
            return false;
        return gold >= towerCell.getCost();
    }

    /**
     * Adds the given amount to the player's gold.
     * @param amount The amount of gold to be added.
     */
    public static void addGold(int amount) {
        gold += amount;
        GamePane.playerInformationPane.updateData();
    }

    /**
     * Adds the given amount of health to the player's health.
     * @param amount The amount of health to be added.
     */
    public static void addHp(int amount) {
        if(amount > 0)
            currentHp = Math.min(currentHp +amount, maxHp);
        else
            currentHp = Math.max(currentHp +amount, 0);
        GamePane.playerInformationPane.updateData();
    }

    /**
     * Creates the {@link GameOverPane}. Called when the player's health reaches zero.
     */
    public static void lose() {
        if(!isGameOver) {
            isGameOver = true;
            new GameOverPane();
        }
    }

    /**
     * Gets the {@link #selectedTower}.
     * @return The {@link #selectedTower}.
     */
    public static TowerCell getSelectedTower() {
        return selectedTower;
    }

    /**
     * Gets the {@link #minionPath}.
     * @return The {@link #minionPath}.
     */
    public static ArrayList<Vector2> getMinionPath() {
        return minionPath;
    }

    /**
     * Gets the {@link #updatables}.
     * @return The {@link #updatables}.
     */
    public static ArrayList<Updatable> getUpdatables() {
        return updatables;
    }

    /**
     * Gets the {@link #minions}.
     * @return The {@link #minions}.
     */
    public static ArrayList<Minion> getMinions() {
        return minions;
    }

    /**
     * Gets the {@link #maxHp}.
     * @return The {@link #maxHp}.
     */
    public static int getMaxHp() {
    	return maxHp;
    }

    /**
     * Gets the {@link #currentHp}.
     * @return The {@link #currentHp}.
     */
	public static int getCurrentHp() {
		return currentHp;
	}

    /**
     * Gets the {@link #gold}.
     * @return The {@link #gold}.
     */
	public static int getGold() {
		return gold;
	}
}
