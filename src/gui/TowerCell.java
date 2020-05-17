package gui;

import database.TowerData;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import logic.GameController;

/**
 * The TowerCell class represents the buttons in the {@link TowerConstructionPane}. Each towerCells represents
 * each individual {@link entity.tower.base.Tower Towers}.
 */
public class TowerCell extends StackPane {
	/**
	 * The preferred width and height of the cell. ({@value})
	 */
	private final int prefSize = TowerConstructionPane.TOWER_PANE_HEIGHT;

	/**
	 * The name of the tower.
	 */
	private String name;

	/**
	 * The damage of the tower.
	 */
	private String damage;

	/**
	 * The rate in which the tower fires, measured in shots per second.
	 */
	private double rate;
	private double range;
	private int cost;

	protected Background currentBG;

	public TowerCell(String toolName) {
		switch (toolName) {
			case "Sell Tool":
				this.name = "Sell Tool";
				this.getChildren().add(new TowerImage("images/sellIcon.png"));
				break;
			case "Upgrade Tool":
				this.name = "Upgrade Tool";
				this.getChildren().add(new TowerImage("images/upgradeIcon.png"));
				break;
		}


		this.setPrefWidth(prefSize);
		this.setPrefHeight(prefSize);

		this.currentBG = GUIController.BG_TOWER_UNSELECTED;
		this.setBackground(currentBG);

		setTooltip();

		addListener();
	}

	public TowerCell(TowerData towerData) {
		this.name = towerData.name;
		this.damage = (int)towerData.minDamage + "-" + (int)towerData.maxDamage;
		this.rate = towerData.rate;
		this.range = towerData.range;
		this.cost = towerData.cost;

		this.setPrefWidth(prefSize);
		this.setPrefHeight(prefSize);

		this.currentBG = GUIController.BG_TOWER_UNSELECTED;
		this.setBackground(null);

		this.getChildren().add(new TowerImage(towerData.spriteIndex));

		setTooltip();

		addListener();
	}

	public void setTooltip() {
    	Tooltip tooltip = new Tooltip();
    	tooltip.setFont(new Font(12));
    	this.setTooltipText(tooltip);

		this.setOnMouseMoved(mouseEvent -> tooltip.show(this, mouseEvent.getScreenX() + 10, mouseEvent.getScreenY() + 10));
		this.setOnMouseExited(mouseEvent -> {
			tooltip.hide();
			this.setBackground(currentBG);
		});
	}

	public void addListener() {
		this.setOnMouseEntered(mouseEvent -> this.setBackground(GUIController.BG_TOWER_HOVER));
		this.setOnMouseClicked(mouseEvent -> GameController.setSelectedTower(this));
	}

	public void setCurrentBG(Background currentBG) {
		this.currentBG = currentBG;
		this.setBackground(currentBG);
	}

	public boolean isTool() {
		return this.name.contains("Tool");
	}

	public boolean isUpgradeTool() {
		return this.name.equals("Upgrade Tool");
	}

	public boolean isSellTool() {
		return this.name.equals("Sell Tool");
	}

	public String getName() {
		return name;
	}

	public int getCost() {
		return this.cost;
	}

	public void setTooltipText(Tooltip tool) {
		if(this.isTool())
			tool.setText(this.getName());
		else
			tool.setText(
					name +
					String.format("Damage: %s\n", damage) +
					String.format("Rates: %.1f shots/sec\n", rate) +
					String.format("Range: %d\n", (int)range) +
					String.format("Cost: %d\n", cost)
			);

	}
}
