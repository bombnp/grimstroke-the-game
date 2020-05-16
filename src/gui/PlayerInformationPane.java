package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.GameController;
import logic.MinionWaveController;

public class PlayerInformationPane extends HBox{
	public ProgressBar hpBar;

	public Text hpText,goldText,goldValue,waveText,waveNumberText;

	public void createPreset() {
		hpText = new Text("Health Points");
		goldText = new Text("Golds");
		waveText = new Text("Wave");
		waveNumberText = new Text(Integer.toString(MinionWaveController.getWaveNumber()));
		goldValue = new Text(Integer.toString(GameController.getGold()));
		hpBar = new ProgressBar((double)GameController.getCurrentHp()/GameController.getMaxHp());
		setStyle();
		initializeAllInfo();
	}

	public void updateData() {
		goldValue.setText(Integer.toString(GameController.getGold()));
		hpBar.setProgress((float)GameController.getCurrentHp()/(float)GameController.getMaxHp());
		waveNumberText.setText(Integer.toString(MinionWaveController.getWaveNumber()));
	}

	public void setStyle() {
		this.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setPadding(new Insets(10,10,10,10));
		this.setSpacing(20);		
	}

	public void initializeAllInfo() {
		addContext(groupContext(hpText, hpBar));	
		addContext(groupContext(goldText, goldValue));
		addContext(groupContext(waveText, waveNumberText));
	}

	public VBox groupContext(Text text1,Text text2) {
		VBox Context = new VBox();
		Context.getChildren().addAll(text1,text2);
		Context.setAlignment(Pos.TOP_CENTER);
		Context.setSpacing(5);
		return Context;
	}

	public VBox groupContext(Text text,ProgressBar bar) {
		VBox Context = new VBox();
		Context.getChildren().addAll(text,bar);
		Context.setAlignment(Pos.TOP_CENTER);
		Context.setSpacing(5);
		return Context;
	}

	public void addContext(VBox context) {
		this.getChildren().add(context);
	}
}
