package gui;

import entity.building.base.Tower;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import logic.GameController;

public class TowerCell extends StackPane {
	int prefSize = ControlPane.getControlPaneWidth();

	private int bgSprite;

	private String name;
	private String damage;
	private String rate;
	private String range;

	private Background currentBG;

	private Tower towerType;

	public TowerCell(int bgSprite, String name, String damage, String rate, String range) {
		this.setPrefWidth(prefSize);
		this.setPrefHeight(prefSize);

		this.getChildren().add(new TowerImage(bgSprite));

		this.currentBG = GUIController.BG.TOWER_UNSELECTED;
		this.setBackground(currentBG);



		setData(bgSprite,name, damage, rate, range);

		setTooltip();

		addListener();
	}

	public void setData(int bgSprite, String name, String damage, String rate, String range) {
		this.bgSprite = bgSprite;
		this.name = name;
		this.damage = damage;
		this.rate = rate;
		this.range = range;
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

	public Background getCurrentBG() {
		return currentBG;
	}

	public void setCurrentBG(Background currentBG) {
		this.currentBG = currentBG;
		this.setBackground(currentBG);
	}

	public int getBgSprite(){
		return this.bgSprite;
	}

	public int getCellSize(BoardCell cell) {
		return cell.getChildren().size();
	}

	public boolean isTool() {
		return this.name.equals("DESTROY");
	}

	public String getName() {
		return name;
	}

	public String getDamage() {
		return damage;
	}

	public String getRate() {
		return rate;
	}

	public String getRange() {
		return range;
	}

	public String getNameWithText() {
		return "Name : "+this.name;
	}

	public String getDamageWithText() {
		return "\nDamage : "+this.damage;
	}

	public String getRatesWithText() {
		return "\nRates : "+this.rate;
	}

	public String getRangeWithText() {
		return "\nRange : "+this.range;
	}

	public void SetTooltipText(Tooltip tool) {
		if(this.isTool())
			tool.setText("Destroy Tool");
		else
			tool.setText(this.getNameWithText() + this.getDamageWithText() + this.getRatesWithText() + this.getRangeWithText());
	}
}
