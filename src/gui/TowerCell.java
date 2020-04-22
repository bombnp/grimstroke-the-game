package gui;

import entity.Buildspot;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import logic.GameController;

public class TowerCell extends Button{
	int PrefSize = ControlPane.getControlPaneSize();
	private int bgSprite;
	private String name;
	private String damage;
	private String rates;
	private String range;
	private boolean isTool;
	public TowerCell(int bgSprite, String name, String damage, String rates, String range, boolean isTool) {
		this.setGraphic(new TowerImage(bgSprite));
		this.setPrefWidth(PrefSize);
		this.setPrefHeight(PrefSize);
		this.setData(bgSprite,name, damage, rates, range, isTool);
		this.setTooltip();
	}
	public void setData(int bgSprite, String name, String damage, String rates, String range, boolean isTool) {
		this.bgSprite = bgSprite;
		this.name = name;
		this.damage = damage;
		this.rates = rates;
		this.range = range;
		this.isTool = isTool;
	}
	public void setTooltip() {
    	Tooltip tooltip = new Tooltip();
    	tooltip.setFont(new Font(12));
    	this.SetTooltipText(tooltip);
    	this.setOnMouseMoved(mouseEvent -> {
    		tooltip.show(this, mouseEvent.getScreenX() + 5, mouseEvent.getScreenY() + 10);
    	});
    	this.setOnMouseExited(mouseEvent -> tooltip.hide());
		//
		this.setOnAction(actionEvent -> {
			//On Click
			BoardCell cell = GameController.SelectedCell;
			if(cell != null) {
				if(cell.getBuildable() && !getIsTool()) {
					// Build
					cell.getChildren().remove(getCellSize(cell)-1);
					cell.addImage(getBgSprite());
					cell.setBuildable(false);
					System.out.println(cell);
				}else {
					if(getIsTool() && !cell.getBuildable()) {
						//Using Tool
						cell.getChildren().remove(getCellSize(cell)-1);
						cell.setBuilding(new Buildspot(cell));
						cell.setBuildable(true);
					}
				}
			}

		});
	}
	public int getBgSprite(){
		return this.bgSprite;
	}

	public int getCellSize(BoardCell cell) {
		return cell.getChildren().size();
	}

	public boolean getIsTool() {
		return this.isTool;
	}

	public String getName() {
		return name;
	}

	public String getDamage() {
		return damage;
	}

	public String getRates() {
		return rates;
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
		return "\nRates : "+this.rates;
	}

	public String getRangeWithText() {
		return "\nRange : "+this.range;
	}

	public void SetTooltipText(Tooltip tool) {
		if(this.isTool)
			tool.setText("Remove Tool");
		else
			tool.setText(this.getNameWithText() + this.getDamageWithText() + this.getRatesWithText() + this.getRangeWithText());
	}
}
