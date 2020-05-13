package logic;

import application.Utility;
import entity.Updatable;
import entity.minion.base.Minion;
import entity.tower.CannonTower;
import entity.tower.MachineGunTower;
import entity.tower.RocketTower;
import entity.tower.base.Tower;
import exception.InvalidTowerException;
import gui.BoardCell;
import gui.GUIController;
import gui.GameOverPane;
import gui.GamePane;
import gui.TowerCell;
import gui.*;
import javafx.animation.AnimationTimer;

import java.util.ArrayList;

public class GameController {
    private static TowerCell selectedTower;

    private static ArrayList<Vector2> minionPath;
    
    private static final MinionWaveController WaveController = new MinionWaveController();

    private static final ArrayList<Updatable> updatables = new ArrayList<>();
    private static final ArrayList<Updatable> garbageCollector = new ArrayList<>();
    private static final ArrayList<Updatable> updatablesAddList = new ArrayList<>();

    private static final ArrayList<Minion> minions = new ArrayList<>();

    public static final double minionSpeed = 48;
    public static final double bulletSpeed = 192;

	private static int maxHp,curHp,money;
	private static boolean isGameOver;
    public static void initialize(String mapName) {
		curHp = maxHp = 20;
		money = 10;
		isGameOver = false;
		GamePane.playerStatusPane.CreatePreset();
        String[][] mapCSV = Utility.readCSV("map/" + mapName + "_Map.csv");
        String[][] decorCSV = Utility.readCSV("map/" + mapName + "_Decor.csv");
        initializeMap(mapCSV, decorCSV);

        minionPath = Utility.readMinionPath("map/" + mapName + "_MinionPath.csv");
        
        new AnimationTimer() {
            private long lastFrameNanoTime = System.nanoTime();

            @Override
            public void handle(long currentNanoTime) {
                double deltaTime = (currentNanoTime - lastFrameNanoTime) / 1000000000.0;

                for (Updatable updatable : updatables) {
                    updatable.update(deltaTime);
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
                        GUIController.getGamePane().getNextWaveButton().setDisable(false);
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
    public static MinionWaveController getWaveController() {
    	return WaveController;
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
		return curHp;
	}
	public static int getCurrentMoney() {
		return money;
	}
	public static void ModifyMoney(int value) {
		money+=value;
		GamePane.playerStatusPane.UpdateData();
	}
	public static void ModifyHp(int value) {
		if(value > 0)
			curHp = Math.min(curHp+value, maxHp);
		else
			curHp = Math.max(curHp+value, 0);
		GamePane.playerStatusPane.UpdateData();
	}
	public static void GameOver() {
		if(!isGameOver) {
			isGameOver = true;
			GameOverPane GameOver = new GameOverPane();
		}
	}
}
