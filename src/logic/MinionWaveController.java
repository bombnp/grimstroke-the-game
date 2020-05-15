package logic;

import database.Database;
import database.MinionData;
import entity.minion.Minion;
import gui.GamePane;
import javafx.application.Platform;

public class MinionWaveController{
	private static int waveNumber = 1;
	private static final int TIME_BETWEEN_SPAWN = 500; // ms
	private static boolean isSpawning = false;
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

	public static int getWaveNumber() {
		return waveNumber;
	}

	public static void increaseWaveNumber() {
		waveNumber++;
		GamePane.playerStatusPane.updateData();
	}

	public static boolean isSpawning() {
		return isSpawning;
	}

}
