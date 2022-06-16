package com.codecool.dungeoncrawl.util;


import com.codecool.dungeoncrawl.display.Tiles;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Creates Images out of the Tiles with the given coordinate
 */
public class ImageCreator {
    public static final Image image = Tiles.getTileset();

    public static final String filePath = "src/main/resources/subImages/";

    public static final File imageFile = new File("src/main/resources/tiles.png");

    public Image getImageWithCoordinates(int x, int y, int width, int height, String imageName) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(imageFile);
        BufferedImage subImage = bufferedImage.getSubimage(x, y, width, height);
        File fileToSave = new File(filePath + imageName + ".png");
        ImageIO.write(subImage, "png", fileToSave);
        return new Image("www.placeholder.com");
    }

    public Image getImage(String imagePath) throws IOException {
        File imageFile = new File(imagePath);
        BufferedImage bufferedImage = ImageIO.read(imageFile);
//        Image image = SwingFXUtils.toFXImage(bufferedImage, null);
        return image;
    }
}
