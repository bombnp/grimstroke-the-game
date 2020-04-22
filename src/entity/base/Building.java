package entity.base;

import application.Utility;
import exception.SpriteIndexOutOfBoundsException;
import gui.BoardCell;
import gui.CellImage;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import logic.GameController;

public abstract class Building extends StackPane {
    private BoardCell cell;
    private CellImage baseImage;
    Background BlankBG = Background.EMPTY;
	Background SelectedBG = new Background(new BackgroundFill(Color.YELLOWGREEN, CornerRadii.EMPTY, Insets.EMPTY));
    public Building(BoardCell cell, int baseSprite) {
        this.cell = cell;
        baseImage = new CellImage(baseSprite);
        this.getChildren().add(baseImage);
        cell.setOnMouseClicked(mouseEvent -> {
            BoardCell PreviousCell = GameController.SelectedCell;
            if(PreviousCell != null) {
                PreviousCell.SetCellBG(BlankBG);
            }
            setSelectedCell(cell);
            cell.SetCellBG(SelectedBG);
        });
    }
    public void setSelectedCell(BoardCell cell) {
    	GameController.SelectedCell = cell;
    }
}
