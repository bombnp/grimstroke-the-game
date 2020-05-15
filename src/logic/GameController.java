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

public class GameController {
    private static TowerCell selectedTower;

    private static ArrayList<Vector2> minionPath;

    private static final ArrayList<Updatable> updatables = new ArrayList<>();
    private static final ArrayList<Updatable> garbageCollector = new ArrayList<>();
    private static final ArrayList<Updatable> updatablesAddList = new ArrayList<>();

    private static final ArrayList<Minion> minions = new ArrayList<>();

    public static final double minionSpeed = 48;
    public static final double bulletSpeed = 192;

	private static final int maxHp = 20;
	private static int currentHp = 20, gold = 10;
	private static boolean isGameOver = false;
    public static void initialize(String mapName) {
		GamePane.playerStatusPane.createPreset();
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

                    }
                    updatables.remove(garbage);
                    //noinspection SuspiciousMethodCalls
                    GUIController.getGamePane().getChildren().remove(garbage);

                    if (minions.isEmpty()) {
                        if (MinionWaveController.getWaveNumber() == Database.waves.length && MinionWaveController.isSpawning()) {
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

    public static void initializeMap(String[][] mapCSV, String[][] decorCSV) {
        int height = mapCSV.length;
        int width = mapCSV[0].length;

        BoardGrid boardGrid = GUIController.getBoardGrid().initialize(width, height);

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

    public static void removeUpdatable(Updatable updatable) {
        garbageCollector.add(updatable);
    }

    public static void addUpdatable(Updatable updatable) {
        updatablesAddList.add(updatable);
    }

    public static void setSelectedTower(TowerCell tower) {
        if (selectedTower != null)
            selectedTower.setCurrentBG(GUIController.BG.TOWER_UNSELECTED);
        selectedTower = tower;
        selectedTower.setCurrentBG(GUIController.BG.TOWER_SELECTED);
    }

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

    public static TowerCell getSelectedTower() {
        return selectedTower;
    }

    public static ArrayList<Vector2> getMinionPath() {
        return minionPath;
    }

    public static ArrayList<Updatable> getUpdatables() {
        return updatables;
    }

    public static ArrayList<Minion> getMinions() {
        return minions;
    }

    public static int getMaxHp() {
    	return maxHp;
    }

	public static int getCurrentHp() {
		return currentHp;
	}

	public static int getGold() {
		return gold;
	}

	public static boolean canBuy(Tower tower, int level) {
        switch (tower.getClass().getName()) {
            case "entity.tower.MachineGunTower" : return gold >= Database.MG[level-1].cost;
            case "entity.tower.RocketTower" : return gold >= Database.Rocket[level-1].cost;
            case "entity.tower.CannonTower" : return gold >= Database.Cannon[level-1].cost;
            default: return false;
        }
    }

    public static boolean canBuy(TowerCell towerCell) {
        if (towerCell == null)
            return false;
        return gold >= towerCell.getCost();
    }

	public static void addMoney(int value) {
		gold +=value;
		GamePane.playerStatusPane.updateData();
	}

	public static void addHp(int value) {
		if(value > 0)
			currentHp = Math.min(currentHp +value, maxHp);
		else
			currentHp = Math.max(currentHp +value, 0);
		GamePane.playerStatusPane.updateData();
	}

	public static void lose() {
		if(!isGameOver) {
			isGameOver = true;
            new GameOverPane();
		}
	}
}
