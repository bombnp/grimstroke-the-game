package logic;

import gui.BoardCell;
import gui.BoardGrid;
import gui.GUIController;

public class GameMap {
    private Cell[][] cellMap;
    private final int width, height;

    public GameMap(String[][] mapCSV, String[][] decorCSV, String[][] buildingSpotCSV) {
        height = mapCSV.length;
        width = mapCSV[0].length;

        BoardGrid boardGrid = GUIController.getBoardGrid().initialize(width, height);

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                int spriteIndex = Integer.parseInt(mapCSV[row][col]);
                BoardCell cell = boardGrid.addCell(spriteIndex, row, col);

                spriteIndex = Integer.parseInt(decorCSV[row][col]);
                cell.addImage(spriteIndex);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
