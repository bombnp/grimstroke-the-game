package gui;

import entity.Buildspot;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.GameController;

public class TowerCell extends Button{
	int PrefSize = ControlPane.GetControlPaneSize();
	private int SpriteIdx;
	private String Name;
	private String Damage;
	private String Rates;
	private String Range;
	private boolean isTool;
	public TowerCell(int bgSprite,String Name,String Damage,String Rates,String Range,boolean isTool) {
		this.setGraphic(new TowerImage(bgSprite));
		this.setPrefWidth(PrefSize);
		this.setPrefHeight(PrefSize);
		this.setData(bgSprite,Name, Damage, Rates, Range, isTool);
		this.setTooltip();
	}
	public void setData(int SpriteIdx,String Name,String Damage,String Rates,String Range,boolean isTool) {
		this.SpriteIdx = SpriteIdx;
		this.Name = Name;
		this.Damage = Damage;
		this.Rates = Rates;
		this.Range = Range;
		this.isTool = isTool;
	}
	public void setTooltip() {
    	Tooltip tooltip = new Tooltip();
    	tooltip.setFont(new Font(12));
    	this.SetTooltipText(tooltip);
    	this.setOnMouseMoved((MouseEvent e) -> {
    		tooltip.show(this, e.getScreenX() + 5, e.getScreenY() + 10);
    	});
    	this.setOnMouseExited(new EventHandler<MouseEvent>() {
    		@Override
    		public void handle(MouseEvent arg0) {
    			// TODO Auto-generated method stub
    			tooltip.hide();
    		}
		});
		//
		this.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				//On Click
				BoardCell cell = GameController.SelectedCell;
				if(cell != null) {
					if(cell.getbuildable() && !getIsTool()) {
						// Build
						cell.getChildren().remove(getCellSize(cell)-1);
						cell.addImage(getSpriteIdx());
						cell.setbuildable(false);
						System.out.println(cell);
					}else {
						if(getIsTool() && !cell.getbuildable()) {
							//Using Tool
							cell.getChildren().remove(getCellSize(cell)-1);
							cell.setBuilding(new Buildspot(cell));
							cell.setbuildable(true);
						}else {
							//Cant Build
						}
					}
				}
				
			};
		});
	}
	public int getSpriteIdx(){
		return this.SpriteIdx;
	}
	public int getCellSize(BoardCell cell) {
		return cell.getChildren().size();
	}
	public boolean getIsTool() {
		return this.isTool;
	}
	public String getName(boolean addExtra) {
		if(!addExtra)
			return this.Name;
		else
			return "Name : "+this.Name;
	}
	public String getDamage(boolean addExtra) {
		if(!addExtra)
			return this.Damage;
		else
			return "\nDamage : "+this.Damage;
	}
	public String getRates(boolean addExtra) {
		if(!addExtra)
			return this.Rates;
		else
			return "\nRates : "+this.Rates;
	}
	public String getRange(boolean addExtra) {
		if(!addExtra)
			return this.Range;
		else
			return "\nRange : "+this.Range;
	}
	public void SetTooltipText(Tooltip tool) {
		if(this.isTool)
			tool.setText("Remove Tool");
		else
			tool.setText(this.getName(true) + this.getDamage(true) + this.getRates(true) + this.getRange(true)); 
	}
}
