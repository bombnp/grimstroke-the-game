package logic;

import application.Utility;
import entity.Updatable;
import entity.building.*;
import entity.building.base.Tower;
import entity.minion.base.Minion;
import exception.InvalidTowerException;
import gui.BoardCell;
import gui.GUIController;
import gui.TowerCell;
import javafx.animation.AnimationTimer;

import java.util.ArrayList;

public class GameController {
    private static GameMap gameMap;

    private static TowerCell selectedTower;

    private static ArrayList<Vector2> minionPath;
    
    private static final MinionWaveController WaveController = new MinionWaveController();

    private static final ArrayList<Updatable> updatables = new ArrayList<>();

    private static final ArrayList<Minion> minions = new ArrayList<>();
    private static final ArrayList<Minion> garbageCollector = new ArrayList<>();

    public static final double minionSpeed = 48;

    public static void initialize(String mapName) {
        String[][] mapCSV = Utility.readCSV("map/" + mapName + "_Map.csv");
        String[][] decorCSV = Utility.readCSV("map/" + mapName + "_Decor.csv");
        gameMap = new GameMap(mapCSV, decorCSV);

        minionPath = Utility.readMinionPath("map/" + mapName + "_MinionPath.csv");
        
        new AnimationTimer() {
            private long lastFrameNanoTime = System.nanoTime();

            @Override
            public void handle(long currentNanoTime) {
                double deltaTime = (currentNanoTime - lastFrameNanoTime) / 1000000000.0;
                for (Updatable updatable : updatables) {
                    updatable.update(deltaTime);
                }
                for (Minion garbage : garbageCollector) {
                    minions.remove(garbage);
                    updatables.remove(garbage);
                    GUIController.getGamePane().getChildren().remove(garbage);

                    if (minions.isEmpty()) {
                        GUIController.getGamePane().getNextWaveButton().setDisable(false);
                    }
                }
                garbageCollector.clear();
                lastFrameNanoTime = currentNanoTime;
            }
        }.start();
        
    }

    public static void removeMinion(Minion minion) {
        garbageCollector.add(minion);
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
            case "Machine Gun Tower" : return new MachineGunTower(targetCell);
            case "Rocket Tower" : return new RocketTower1(targetCell);
            case "Rocket Tower Lv. 2" : return new RocketTower2(targetCell);
            case "Cannon Tower" : return new CannonTower1(targetCell);
            case "Cannon Tower Lv. 2" : return new CannonTower2(targetCell);
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
}
