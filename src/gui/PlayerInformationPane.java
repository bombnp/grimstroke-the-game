package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.GameController;
import logic.MinionWaveController;

/**
 * The PlayerInformationPane represents the information pane about the player. It shows
 * the health of the player, the amount of gold the player has, and the current wave number.
 */
public class PlayerInformationPane extends HBox{
	/**
	 * The {@link ProgressBar} that shows the player's health.
	 */
	public ProgressBar hpBar;

	/**
	 * The {@link Text Texts} in the pane.
	 */
	public Text hpText,goldText,goldValue,waveText,waveNumberText;

	/**
	 * The constructor of the PlayerInformationPane class. It initializes all the fields.
	 */
	public PlayerInformationPane() {
		hpText = new Text("Health");
		goldText = new Text("Gold");
		waveText = new Text("Wave");
		waveNumberText = new Text(Integer.toString(MinionWaveController.getWaveNumber()));
		goldValue = new Text(Integer.toString(GameController.getGold()));
		hpBar = new ProgressBar((double)GameController.getCurrentHp()/GameController.getMaxHp());
		setStyle();
		initializeAllInfo();
	}

	/**
	 * Updates the data in the pane to match the data in the {@link GameController}.
	 */
	public void updateData() {
		goldValue.setText(Integer.toString(GameController.getGold()));
		hpBar.setProgress((float)GameController.getCurrentHp()/(float)GameController.getMaxHp());
		waveNumberText.setText(Integer.toString(MinionWaveController.getWaveNumber()));
	}

	/**
	 * Sets the style of the pane.
	 */
	public void setStyle() {
		this.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setPadding(new Insets(10,10,10,10));
		this.setSpacing(20);		
	}

	/**
	 * Initializes the {@link Text Texts} and {@link ProgressBar ProgressBars} in the pane.
	 */
	public void initializeAllInfo() {
		addContext(groupContext(hpText, hpBar));	
		addContext(groupContext(goldText, goldValue));
		addContext(groupContext(waveText, waveNumberText));
	}

	/**
	 * Groups the given {@link Text Texts} into {@link VBox}.
	 * @param text1 The {@link Text} to be grouped.
	 * @param text2 The {@link Text} to be grouped.
	 * @return The {@link VBox} of the given {@link Text Texts}.
	 */
	public VBox groupContext(Text text1,Text text2) {
		VBox Context = new VBox();
		Context.getChildren().addAll(text1,text2);
		Context.setAlignment(Pos.TOP_CENTER);
		Context.setSpacing(5);
		return Context;
	}

	/**
	 * Groups the given {@link Text} and {@link ProgressBar} into {@link VBox}.
	 * @param text The {@link Text} to be grouped.
	 * @param bar The {@link ProgressBar} to be grouped.
	 * @return The {@link VBox} of the given {@link Text} and {@link ProgressBar}.
	 */
	public VBox groupContext(Text text,ProgressBar bar) {
		VBox Context = new VBox();
		Context.getChildren().addAll(text,bar);
		Context.setAlignment(Pos.TOP_CENTER);
		Context.setSpacing(5);
		return Context;
	}

	/**
	 * Adds the context to the children list.
	 * @param context The context to be added.
	 */
	public void addContext(VBox context) {
		this.getChildren().add(context);
	}
}
