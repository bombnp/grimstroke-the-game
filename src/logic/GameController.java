package logic;

import application.Utility;
import gui.BoardCell;
import gui.ControlPane;
import gui.GUIController;

public class GameController {
    private static GameMap gameMap;

    public static BoardCell SelectedCell;

    public static void initialize(String mapName) {
        String[][] mapCSV = Utility.readCSV("map/" + mapName + "_Map.csv");
        String[][] decorCSV = Utility.readCSV("map/" + mapName + "_Decor.csv");
        gameMap = new GameMap(mapCSV, decorCSV);
    }

	public static GameMap getGameMap() {
        return gameMap;
    }
}
