package com.jungle.common.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author jungle
 * @version V1.0
 * @date 2019/1/4 14:33
 * @Title: ImageUtils.java
 * @Package com.jungle.common.utils
 * @Description: 图片处理工具类
 * copyright © 2019- github.com
 */
public final class ImageUtils {

    /**
     * no-args constructor.
     */
    private ImageUtils() {/**/}

    public static BufferedImage loadImage(String filePath) throws IOException {
        return ImageIO.read(new File(filePath));
    }

    public static BufferedImage areaMask(BufferedImage bufferedImageIn, int x, int y, int width, int height, int maskSize) {

        int imageInHeight = bufferedImageIn.getHeight();
        int imageInWidth = bufferedImageIn.getWidth();

        if (x < 0 || y < 0 || width < 0 || height < 0) {
            throw new IllegalArgumentException("x,y,width,height should be positive!");
        }

        if (imageInHeight < (y + height) || imageInWidth < (x + width)) {
            throw new IllegalArgumentException("area exceeds max height or max width!");
        }

        double[] buf = new double[width * height * 3];
        double[] pixels = bufferedImageIn.getRaster().getPixels(x, y, width, height, buf);
        double[][][] rgbMatrix = pixelsToRgbMatrix(pixels, width, height);
        double[][][] maskRgbMatrix = new double[width][height][3];
        for (int n = 0; n < 3; n++) {
            for (int j = 0; j < height; j++) {
                for (int i = 0; i < width; i++) {
                    maskRgbMatrix[i][j][n] = rgbMatrix[(i / maskSize) * maskSize][(j / maskSize) * maskSize][n];    //除余舍弃，故只有整数部分
                }
            }
        }
        bufferedImageIn.getRaster().setPixels(x, y, width, height, rgbMatrixToPixels(maskRgbMatrix, width, height));
        return bufferedImageIn;
    }


    public static double[][][] pixelsToRgbMatrix(double[] pixels, int width, int height) {
        double[][][] rgbMatrix = new double[width][height][3];
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                rgbMatrix[i][j][0] = pixels[(j * width + i) * 3];          //  R?
                rgbMatrix[i][j][1] = pixels[(j * width + i) * 3 + 1];      //  G?
                rgbMatrix[i][j][2] = pixels[(j * width + i) * 3 + 2];      //  B?
            }
        }
        return rgbMatrix;
    }

    public static double[] rgbMatrixToPixels(double[][][] rgbMatrix, int width, int height) {
        double[] pixels = new double[width * height * 3];
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                pixels[(j * width + i) * 3] = rgbMatrix[i][j][0];
                pixels[(j * width + i) * 3 + 1] = rgbMatrix[i][j][1];
                pixels[(j * width + i) * 3 + 2] = rgbMatrix[i][j][2];
            }
        }
        return pixels;
    }


}
