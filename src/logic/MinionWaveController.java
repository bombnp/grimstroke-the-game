package logic;

import database.Database;
import database.MinionData;
import entity.minion.Minion;
import gui.GamePane;
import javafx.application.Platform;

/**
 * The MinionWaveController class controls the spawning of {@link Minion Minions} in each wave. The wave number
 * starts at one and continues to increase each time the player hits the next wave button. When the last wave
 * finishes, the player wins.
 */
public class MinionWaveController{
	/**
	 * The current wave number.
	 */
	private static int waveNumber = 1;

	/**
	 * The time between spawning each minion. ({@value} ms)
	 */
	private static final int TIME_BETWEEN_SPAWN = 500;

	/**
	 * Indicates whether or not the minions are spawning.
	 */
	private static boolean isSpawning = false;

	/**
	 * Generates the current wave, one by one.
	 */
	public static void generateNextWave() {
		new Thread(() -> {
			isSpawning = true;
			for(int i = 0 ; i < 8 ; i++) {
				for(int j = 0; j < Database.waves[waveNumber].minionsCount[i] ; j++) {
					MinionData minionData = Database.minions[i];
					Platform.runLater(() -> new Minion(minionData));
					try {
						//noinspection BusyWait
						Thread.sleep(TIME_BETWEEN_SPAWN);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			isSpawning = false;
		}).start();
	}

	/**
	 * Gets the {@link #waveNumber}.
	 * @return The {@link #waveNumber}.
	 */
	public static int getWaveNumber() {
		return waveNumber;
	}

	/**
	 * Sets the {@link #waveNumber}.
	 * @param waveNumber The wave number to be set.
	 */
	public static void setWaveNumber(int waveNumber) {
		MinionWaveController.waveNumber = waveNumber;
	}

	/**
	 * Increase the wave number by 1.
	 */
	public static void increaseWaveNumber() {
		waveNumber++;
		GamePane.playerInformationPane.updateData();
	}

	/**
	 * Checks if the minions are spawning.
	 * @return True if the minions are spawning, false otherwise.
	 */
	public static boolean isSpawning() {
		return isSpawning;
	}

}
