package logic;

import application.Utility;
import gui.GUIController;
import gui.TowerCell;

public class GameController {
    private static GameMap gameMap;

    private static TowerCell selectedTower;

    public static void initialize(String mapName) {
        String[][] mapCSV = Utility.readCSV("map/" + mapName + "_Map.csv");
        String[][] decorCSV = Utility.readCSV("map/" + mapName + "_Decor.csv");
        gameMap = new GameMap(mapCSV, decorCSV);
    }

    public static void setSelectedTower(TowerCell tower) {
        if (selectedTower != null)
            selectedTower.setCurrentBG(GUIController.BG.TOWER_UNSELECTED);
        selectedTower = tower;
        selectedTower.setCurrentBG(GUIController.BG.TOWER_SELECTED);
    }

    public static TowerCell getSelectedTower() {
        return selectedTower;
    }

    public static GameMap getGameMap() {
        return gameMap;
    }
}
