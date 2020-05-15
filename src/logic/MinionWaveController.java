package logic;

import database.Database;
import database.MinionData;
import entity.minion.Minion;
import javafx.application.Platform;

public class MinionWaveController{
	private static int waveNumber = 1;
	private static final int TIME_BETWEEN_SPAWN = 500; // ms

	public static void generateNextWave() {
		new Thread(() -> {
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
			waveNumber++;
		}).start();
	}

	public static int getWaveNumber() {
		return waveNumber;
	}
}
