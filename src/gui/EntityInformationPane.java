package gui;

import entity.minion.Minion;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * The EntityInformationPane represents the information pane about {@link Minion Minions}. It shows
 * information of the selected {@link Minion}.
 */
public class EntityInformationPane extends HBox{
	/**
	 * The {@link Text} in the pane.
	 */
	public Text nameDisplay, resist_MG_text, resist_Rocket_text, resist_Cannon_text,nameText;

	/**
	 * The {@link ProgressBar} in the pane.
	 */
	public ProgressBar resist_MG_bar, resist_Rocket_bar, resist_Cannon_bar;

	/**
	 * The currently selected {@link Minion}.
	 */
	public Minion selectedMinion;

	/**
	 * The constructor of the EntityInformationPane class. It initializes the fields.
	 */
	public EntityInformationPane() {
		nameDisplay = new Text("Unit Name");
		resist_MG_text = new Text("MachineGun Resistance");
		resist_Rocket_text = new Text("Rocket Resistance");
		resist_Cannon_text = new Text("Cannon Resistance");
		nameText = new Text();
		resist_MG_bar = new ProgressBar();
		resist_Rocket_bar = new ProgressBar();
		resist_Cannon_bar = new ProgressBar();
		setStyle();
		initializeAllInfo();
		this.setVisible(false);
	}

	/**
	 * Gets the {@link #selectedMinion}.
	 * @return The {@link #selectedMinion}.
	 */
	public Minion getSelectedMinion() {
		return selectedMinion;
	}

	/**
	 * Sets the {@link #selectedMinion}. This also updates the related nodes to match the selected {@link Minion}.
	 * @param selectedMinion The {@link Minion} to be selected.
	 */
	public void setSelectedMinion(Minion selectedMinion) {
		if (this.selectedMinion != null) {
			this.selectedMinion.setDropShadow(false);
		}
		this.selectedMinion = selectedMinion;
		if (selectedMinion != null) {
			this.nameText.setText(selectedMinion.getName());
			this.resist_MG_bar.setProgress(selectedMinion.getResist_MG());
			this.resist_Rocket_bar.setProgress(selectedMinion.getResist_Rocket());
			this.resist_Cannon_bar.setProgress(selectedMinion.getResist_Cannon());
			this.selectedMinion.setDropShadow(true);
		}
		this.setVisible(selectedMinion != null);
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
	 * Initializes the {@link Text Texts} and the {@link ProgressBar ProgressBars} of the pane.
	 */
	public void initializeAllInfo() {
		addContext(groupContext(nameDisplay, nameText));
		addContext(groupContext(resist_MG_text, resist_MG_bar));
		addContext(groupContext(resist_Rocket_text, resist_Rocket_bar));
		addContext(groupContext(resist_Cannon_text, resist_Cannon_bar));
	}

	/**
	 * Groups the given {@link Text Texts} into {@link VBox}.
	 * @param text1 The {@link Text} to be grouped.
	 * @param text2 The {@link Text} to be grouped.
	 * @return The {@link VBox} of the given {@link Text Texts}.
	 */
	public VBox groupContext(Text text1,Text text2) {
		VBox context = new VBox();
		context.getChildren().addAll(text1,text2);
		context.setAlignment(Pos.TOP_CENTER);
		context.setSpacing(5);
		return context;
	}

	/**
	 * Groups the given {@link Text} and {@link ProgressBar} into {@link VBox}.
	 * @param text The {@link Text} to be grouped.
	 * @param bar The {@link ProgressBar} to be grouped.
	 * @return The {@link VBox} of the given {@link Text} and {@link ProgressBar}.
	 */
	public VBox groupContext(Text text,ProgressBar bar) {
		VBox context = new VBox();
		context.getChildren().addAll(text,bar);
		context.setAlignment(Pos.TOP_CENTER);
		context.setSpacing(5);
		return context;
	}

	/**
	 * Adds the context to the children list.
	 * @param context The context to be added.
	 */
	public void addContext(VBox context) {
		this.getChildren().add(context);
	}
}
