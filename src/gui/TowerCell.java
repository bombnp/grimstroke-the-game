package gui;

import database.TowerData;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import logic.GameController;

public class TowerCell extends StackPane {
	int prefSize = ControlPane.getControlPaneWidth();

	protected int bgSprite;

	private final String name;
	private String damage;
	private double rateOfFire;
	private double range;

	protected Background currentBG;

	public TowerCell(boolean isTool) {
		this.bgSprite = Sprite.DESTROY_TOOL;
		this.name = "Destroy Tool";

		this.setPrefWidth(prefSize);
		this.setPrefHeight(prefSize);

		this.currentBG = GUIController.BG.TOWER_UNSELECTED;
		this.setBackground(currentBG);

		this.getChildren().add(new TowerImage(bgSprite));

		setTooltip();

		addListener();
	}

	public TowerCell(TowerData towerData) {
		this.bgSprite = towerData.spriteIndex;
		this.name = towerData.name;
		this.damage = towerData.minDamage + "-" + towerData.maxDamage;
		this.rateOfFire = towerData.rateOfFire;
		this.range = towerData.range;

		this.setPrefWidth(prefSize);
		this.setPrefHeight(prefSize);

		this.currentBG = GUIController.BG.TOWER_UNSELECTED;
		this.setBackground(currentBG);

		this.getChildren().add(new TowerImage(bgSprite));

		setTooltip();

		addListener();
	}

	public void setTooltip() {
    	Tooltip tooltip = new Tooltip();
    	tooltip.setFont(new Font(12));
    	this.SetTooltipText(tooltip);

		this.setOnMouseMoved(mouseEvent -> tooltip.show(this, mouseEvent.getScreenX() + 5, mouseEvent.getScreenY() + 10));
		this.setOnMouseExited(mouseEvent -> {
			tooltip.hide();
			this.setBackground(currentBG);
		});
	}

	public void addListener() {
		this.setOnMouseEntered(mouseEvent -> this.setBackground(GUIController.BG.TOWER_HOVER));
		this.setOnMouseClicked(mouseEvent -> GameController.setSelectedTower(this));
	}

	public void setCurrentBG(Background currentBG) {
		this.currentBG = currentBG;
		this.setBackground(currentBG);
	}

	public boolean isTool() {
		return this.name.equals("DESTROY");
	}

	public String getName() {
		return name;
	}

	public String getNameWithText() {
		return "Name : "+this.name;
	}

	public String getDamageWithText() {
		return "\nDamage : "+this.damage;
	}

	public String getRatesWithText() {
		return "\nRates : "+this.rateOfFire;
	}

	public String getRangeWithText() {
		return "\nRange : "+(int)this.range;
	}

	public void SetTooltipText(Tooltip tool) {
		if(this.isTool())
			tool.setText("Destroy Tool");
		else
			tool.setText(this.getNameWithText() + this.getDamageWithText() + this.getRatesWithText() + this.getRangeWithText());
	}
}
