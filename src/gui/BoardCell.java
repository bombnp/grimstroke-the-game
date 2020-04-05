package gui;

import application.Utility;
import exception.SpriteIndexOutOfBoundsException;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class BoardCell extends StackPane {

    public BoardCell(int spriteIndex) {
        this.setPrefWidth(48);
        this.setPrefHeight(48);

        addImage(spriteIndex);
    }

    public void addImage(int spriteIndex) {
        ImageView imageView;
        try {
            imageView = new ImageView(Utility.getSprite(spriteIndex));
        } catch (SpriteIndexOutOfBoundsException e) {
            imageView = new ImageView();
        }
        imageView.setFitWidth(48);
        imageView.setFitHeight(48);
        this.getChildren().add(imageView);
    }
}
