package logic;

import database.Database;
import database.MinionData;
import entity.minion.Minion;
import gui.GamePane;
import javafx.application.Platform;

public class MinionWaveController{
	private static int waveNumber = 1;
	private static final int TIME_BETWEEN_SPAWN = 500; // ms
	private static boolean spawnStatus;
	public static void generateNextWave() {
		spawnStatus = false;
		waveNumber++;
		GamePane.playerStatusPane.updateData();
		new Thread(() -> {
			for(int i = 0 ; i < 8 ; i++) {
				for(int j = 0; j < Database.waves[waveNumber-1].minionsCount[i] ; j++) {
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
			spawnStatus = true;
		}).start();
	}

	public static int getWaveNumber() {
		return waveNumber;
	}
	public static boolean getspawnStatus() {
		return spawnStatus;
	}
}
