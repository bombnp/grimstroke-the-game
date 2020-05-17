package application;

import entity.minion.Minion;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import logic.Vector2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * The Utility class provides various helpful methods, such as {@link #getSprite(int) getSprite}.
 */
public class Utility {
    /**
     * The tileset image used as the baseline of map graphics.
     */
    private static final Image tileset = new Image("tilesheet/tilesheet.png");

    /**
     * Reads a CSV file and convert it to a 2D array of {@link String Strings}.
     *
     * @param filename The directory of the CSV file to be read.
     * @return The 2D array of {@link String Strings} in the CSV file.
     */
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

    /**
     * Reads the path to be used by {@link Minion Minions}.
     * The directory of the file to be read must be provided.
     *
     * @param filename The directory of the CSV file to be read.
     * @return An {@link ArrayList ArrayList} of {@link Vector2 coordinates} in the minion path.
     * @see Vector2
     */
    public static ArrayList<Vector2> readMinionPath(String filename) {
        String[][] data = readCSV(filename);
        ArrayList<Vector2> minionPath = new ArrayList<>();
        for (String[] row : data) {
            minionPath.add(new Vector2(Integer.parseInt(row[0]), Integer.parseInt(row[1])));
        }
        return minionPath;
    }

    /**
     * Retrieves a sprite from the tileset specified by spriteIndex defined in the {@link gui.Sprite Sprite} class.
     * This method calculates the position of the sprite in the tileset
     * and generates a new Image that consists of only that sprite.
     *
     * @param spriteIndex The index of the sprite to retrieve.
     * @return An {@link Image} of the sprite
     */
    public static Image getSprite(int spriteIndex) {
        int row = spriteIndex/23, col = spriteIndex%23;
        return new WritableImage(tileset.getPixelReader(), col*128, row*128, 128, 128);
    }

    /**
     * Retrieves the set of animation frames specified by animationName. This method generates an
     * {@link java.lang.reflect.Array Array} of frames in the animation
     *
     * @param animationName The name of the animation to retrieve.
     *                      Note that all animations are located in res/animation folder.
     * @param frameCount The number of frames in the animation.
     * @return An {@link java.lang.reflect.Array Array} of {@link Image Images} of frames in the animation.
     */
    public static Image[] getAnimation(String animationName, int frameCount) {
        Image[] animation = new Image[frameCount];
        for (int i = 0; i < frameCount; i++) {
            animation[i] = new Image(String.format("animation/%s/%s_%d.png", animationName, animationName, i));
        }
        return animation;
    }
}