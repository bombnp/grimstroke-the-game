package gui;

import entity.minion.Minion;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class EntityInformationPane extends HBox{
	public Text nameDisplay, resist_MG_text, resist_Rocket_text, resist_Cannon_text,nameText;
	public ProgressBar healthBar, resist_MG_bar, resist_Rocket_bar, resist_Cannon_bar;
	public String unitName;
	public double maxHp,hp, resist_MG, resist_Rocket, resist_Cannon;
	public Minion selectedMinion;
	public EntityInformationPane() {
		createPreset();
	}
	public void createPreset() {
		unitName = "ABCDEFGHIJKLMNOP";
		nameDisplay = new Text("unit");
		resist_MG_text = new Text("resist_MG");
		resist_Rocket_text = new Text("resist_Rocket");
		resist_Cannon_text = new Text("resist_Cannon");
		nameText = new Text(unitName);
		healthBar = new ProgressBar(hp);
		resist_MG_bar = new ProgressBar(resist_MG);
		resist_Rocket_bar = new ProgressBar(resist_Rocket);
		resist_Cannon_bar = new ProgressBar(resist_Cannon);
		setStyle();
		initializeAllInfo();
		this.setVisible(false);
	}

	public Minion getSelectedMinion() {
		return selectedMinion;
	}

	public void sendInfo(Minion selectedMinion) {
		if (this.selectedMinion != null) {
			this.selectedMinion.setDropShadow(false);
		}
		this.selectedMinion = selectedMinion;
		if (selectedMinion != null) {
			this.nameText.setText(selectedMinion.getName());
			this.healthBar.setProgress(selectedMinion.getCurrentHealth()/selectedMinion.getMaxHealth());
			this.resist_MG_bar.setProgress(selectedMinion.getResist_MG());
			this.resist_Rocket_bar.setProgress(selectedMinion.getResist_Rocket());
			this.resist_Cannon_bar.setProgress(selectedMinion.getResist_Cannon());
			this.selectedMinion.setDropShadow(true);
		}
		this.setVisible(selectedMinion != null);
	}

	public void setStyle() {
		this.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setPadding(new Insets(10,10,10,10));
		this.setSpacing(20);		
	}

	public void initializeAllInfo() {
		addContext(groupContext(nameDisplay, nameText));
		initializeResistInfo();
	}

	public void initializeResistInfo() {
		addContext(groupContext(resist_MG_text, resist_MG_bar));
		addContext(groupContext(resist_Rocket_text, resist_Rocket_bar));
		addContext(groupContext(resist_Cannon_text, resist_Cannon_bar));
	}

	public VBox groupContext(Text text1,Text text2) {
		VBox context = new VBox();
		context.getChildren().addAll(text1,text2);
		context.setAlignment(Pos.TOP_CENTER);
		context.setSpacing(5);
		return context;
	}

	public VBox groupContext(Text text,ProgressBar bar) {
		VBox context = new VBox();
		context.getChildren().addAll(text,bar);
		context.setAlignment(Pos.TOP_CENTER);
		context.setSpacing(5);
		return context;
	}

	public void addContext(VBox context) {
		this.getChildren().add(context);
	}
}
