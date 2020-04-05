package logic;

import application.Utility;

public class GameController {
    private static GameMap gameMap;

    public static void initialize(String mapName) {
        String[][] mapCSV = Utility.readCSV("map/" + mapName + "_csv_Background.csv");
        String[][] decorCSV = Utility.readCSV("map/" + mapName + "_csv_Decor.csv");
        String[][] buildingSpotCSV = Utility.readCSV("map/" + mapName + "_csv_BuildingSpots.csv");
        assert mapCSV != null;
        gameMap = new GameMap(mapCSV, decorCSV, buildingSpotCSV);
    }

    public static GameMap getGameMap() {
        return gameMap;
    }
}
