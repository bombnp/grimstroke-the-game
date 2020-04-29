package logic;

import application.Utility;
import entity.building.*;
import entity.building.base.Tower;
import exception.InvalidTowerException;
import gui.BoardCell;
import gui.GUIController;
import gui.TowerCell;

import java.util.ArrayList;

public class GameController {
    private static GameMap gameMap;

    private static TowerCell selectedTower;

    private static ArrayList<Coordinate> minionPath;

    public static void initialize(String mapName) {
        String[][] mapCSV = Utility.readCSV("map/" + mapName + "_Map.csv");
        String[][] decorCSV = Utility.readCSV("map/" + mapName + "_Decor.csv");
        gameMap = new GameMap(mapCSV, decorCSV);

        minionPath = Utility.readMinionPath("data/" + mapName + "_MinionPath.csv");
    }

    public static void setSelectedTower(TowerCell tower) {
        if (selectedTower != null)
            selectedTower.setCurrentBG(GUIController.BG.TOWER_UNSELECTED);
        selectedTower = tower;
        selectedTower.setCurrentBG(GUIController.BG.TOWER_SELECTED);
    }

    public static Tower generateSelectedTowerEntity(BoardCell targetCell) throws InvalidTowerException {
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

    public static ArrayList<Coordinate> getMinionPath() {
        return minionPath;
    }
}
