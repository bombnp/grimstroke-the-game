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

	/**
	 * The range of the tower, measured in pixels.
	 */
	private double range;

	/**
	 * The cost of the tower.
	 */
	private int cost;

	/**
	 * The current {@link Background} of the tower.
	 */
	private Background currentBG;

	/**
	 * The constructor of the TowerCell class. This variant creates the cells for the tools.
	 * @param toolName The name of the tool.
	 */
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

	/**
	 * The constructor of the TowerCell class. This variant creates the cells for the towers.
	 * @param towerData The data of the tower.
	 */
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

	/**
	 * Sets the tooltip and its listeners.
	 */
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

	/**
	 * Add the listeners to the cell.
	 */
	public void addListener() {
		this.setOnMouseEntered(mouseEvent -> this.setBackground(GUIController.BG_TOWER_HOVER));
		this.setOnMouseClicked(mouseEvent -> GameController.setSelectedTower(this));
	}

	/**
	 * Sets the {@link Background} of the cell.
	 * @param currentBG The {@link Background} of the cell.
	 */
	public void setCurrentBG(Background currentBG) {
		this.currentBG = currentBG;
		this.setBackground(currentBG);
	}

	/**
	 * Checks whether the cell is a tool or not.
	 * @return True if the cell is a tool, false otherwise.
	 */
	public boolean isTool() {
		return this.name.contains("Tool");
	}

	/**
	 * Checks whether the cell is the Upgrade tool or not.
	 * @return True if the cell is the Upgrade tool, false otherwise.
	 */
	public boolean isUpgradeTool() {
		return this.name.equals("Upgrade Tool");
	}

	/**
	 * Checks whether the cell is the Sell tool or not.
	 * @return True if the cell is the Sell tool, false otherwise.
	 */
	public boolean isSellTool() {
		return this.name.equals("Sell Tool");
	}

	/**
	 * Gets the {@link #name}.
	 * @return The {@link #name}.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the {@link #cost}.
	 * @return The {@link #cost}.
	 */
	public int getCost() {
		return this.cost;
	}

	/**
	 * Sets the text of the tooltip.
	 * @param tooltip The tooltip to set the texts.
	 */
	public void setTooltipText(Tooltip tooltip) {
		if(this.isTool())
			tooltip.setText(this.getName());
		else
			tooltip.setText(
					name + "\n" +
					String.format("Damage: %s\n", damage) +
					String.format("Rates: %.1f shots/sec\n", rate) +
					String.format("Range: %d\n", (int)range) +
					String.format("Cost: %d\n", cost)
			);
	}
}
