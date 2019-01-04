package com.jungle.common.utils;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class ImageUtilsTest {

    @Test
    public void loadImage() throws IOException {

        BufferedImage bufferedImage = ImageUtils.loadImage("E:\\dsp\\backup\\lena.jpg");
        assertNotNull(bufferedImage);

    }

    @Test
    public void areaMask() throws IOException {
        BufferedImage bufferedImageIn = ImageUtils.loadImage("E:\\dsp\\backup\\lena.jpg");
        BufferedImage bufferedImageOut = ImageUtils.areaMask(bufferedImageIn, 100, 100, 50, 200, 10);
        ImageIO.write(bufferedImageOut,"jpg",new File("E:\\dsp\\output\\lena.jpg"));
    }
}