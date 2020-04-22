package logic;

import application.Utility;
import gui.BoardCell;
import gui.ControlPane;
import gui.GUIController;

public class GameController {
    private static GameMap gameMap;

    private static String[][] TowerDataCSV;

    public static BoardCell SelectedCell;

    public static void initialize(String mapName) {
        String[][] mapCSV = Utility.readCSV("map/" + mapName + "_Map.csv");
        String[][] decorCSV = Utility.readCSV("map/" + mapName + "_Decor.csv");
        TowerDataCSV = Utility.readCSV("Database/TowerData.csv");
        assert mapCSV != null;
        gameMap = new GameMap(mapCSV, decorCSV);
        AddAllTower();
    }
    public static void AddAllTower() {
        ControlPane pane = GUIController.getControlPane();
	    for(String[] str: TowerDataCSV) {
	    	// ImgIDX,Name,DamageRange,RateOfFire,Range,isTool
	    	pane.addTower(Integer.parseInt(str[0]), str[1], str[2], str[3], str[4],Boolean.parseBoolean(str[5]));
	    }
    }
    public static String[][] GetTowerData(){
    	return TowerDataCSV;
    }
	public static GameMap getGameMap() {
        return gameMap;
    }
}
