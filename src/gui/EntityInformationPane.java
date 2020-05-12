package gui;

import entity.minion.base.Minion;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class EntityInformationPane extends HBox{
	public Text nameDisplay,resist_MG_Text,resist_Rocket_Text,resist_Canon_Text,nameText;
	public ProgressBar hpDisplay,resist_MG,resist_Rocket,resist_Canon;
	public String unitName;
	public double maxhp,hp,re_MG,re_Ro,re_Ca;
	public Minion SelectedMinion;
	public EntityInformationPane() {
		CreatePreset();
	}
	public void CreatePreset() {
		unitName = "ABCDEFGHIJKLMNOP";
		nameDisplay = new Text("unit");
		resist_MG_Text = new Text("resist_MG");
		resist_Rocket_Text = new Text("resist_Rocket");
		resist_Canon_Text = new Text("resist_Canon");
		nameText = new Text(unitName);
		hpDisplay = new ProgressBar(hp);
		resist_MG = new ProgressBar(re_MG);	
		resist_Rocket = new ProgressBar(re_Ro);
		resist_Canon = new ProgressBar(re_Ca);
		setStyle();
		initailzeAllInfo();
		this.setVisible(false);
	}
	public Minion getSelectedMinion() {
		return SelectedMinion;
	}
	public void SendInfo(Minion SelectedMinion,String unitName,double maxhp,double hp,double re_MG,double re_Ro,double re_ca) {
		this.SelectedMinion = SelectedMinion;
		this.nameText.setText(unitName);
		this.hpDisplay.setProgress(hp/maxhp);
		this.resist_MG.setProgress(re_MG);
		this.resist_Rocket.setProgress(re_Ro);
		this.resist_Canon.setProgress(re_ca);
		if(SelectedMinion != null)
			this.setVisible(true);
		else
			this.setVisible(false);
	}
	public void setStyle() {
		this.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setPadding(new Insets(10,10,10,10));
		this.setSpacing(20);		
	}
	public void initailzeAllInfo() {
		addContext(groupContext(nameDisplay, nameText));
		initailzeResistInfo();
	}
	public void initailzeResistInfo() {
		addContext(groupContext(resist_MG_Text, resist_MG));
		addContext(groupContext(resist_Rocket_Text, resist_Rocket));
		addContext(groupContext(resist_Canon_Text, resist_Canon));		
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
