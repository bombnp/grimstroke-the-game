package logic;

import application.Utility;

public class GameController {
    private static GameMap gameMap;

    public static void initialize(String mapName) {
        String[][] mapCSV = Utility.readCSV("map/" + mapName + "_Map.csv");
        String[][] decorCSV = Utility.readCSV("map/" + mapName + "_Decor.csv");
        assert mapCSV != null;
        gameMap = new GameMap(mapCSV, decorCSV);
    }

    public static GameMap getGameMap() {
        return gameMap;
    }
}
