package application;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import logic.Vector2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Utility {
    private static final Image tileset = new Image("tilesheet/tilesheet.png");

    public static String[][] readCSV(String filename) {

        try {
            InputStream inputStream = ClassLoader.
                    getSystemResourceAsStream(filename);
            assert inputStream != null;
            InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader in = new BufferedReader(streamReader);


            ArrayList<String[]> result = new ArrayList<>();

            for (String line; (line = in.readLine()) != null;) {
                String[] parsedData = line.split(",");
                result.add(parsedData);
            }

            return result.toArray(new String[result.size()][]);
        } catch (IOException e) {
            e.printStackTrace();
            return new String[0][0];
        }
    }

    public static ArrayList<Vector2> readMinionPath(String filename) {
        String[][] data = readCSV(filename);
        ArrayList<Vector2> minionPath = new ArrayList<>();
        for (String[] row : data) {
            minionPath.add(new Vector2(Integer.parseInt(row[0]), Integer.parseInt(row[1])));
        }
        return minionPath;
    }

    public static Image getSprite(int spriteIndex) {
        int row = spriteIndex/23, col = spriteIndex%23;
        return new WritableImage(tileset.getPixelReader(), col*128, row*128, 128, 128);
    }
}