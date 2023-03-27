package com.logistics.common.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

public class QRGenUtils {
    private static final int black = 0xFF000000;

    private static final int white = 0xFFFFFFFF;

    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage p_w_picpath = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                p_w_picpath.setRGB(x, y, matrix.get(x, y) ? black : white);
            }
        }
        return p_w_picpath;
    }

    public static void writeToFile(BitMatrix matrix, String format, File file) throws IOException {
        BufferedImage p_w_picpath = toBufferedImage(matrix);
        ImageIO.write(p_w_picpath, format, file);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void createQRImage(String content, int width, int height, String path, String fileName)
            throws Exception {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        if (StringUtils.isNotBlank(path)) {
            if (!path.endsWith("/")) {
                path = path + "/";
            }
        } else {
            path = "";
        }
        String suffix = "jpg";
        if (fileName.indexOf(".") <= -1) {
            fileName = fileName + "." + suffix;
        } else {
            suffix = fileName.split("[.]")[1];
        }
        fileName = path + fileName;
        File file = new File(fileName);
        writeToFile(bitMatrix, suffix, file);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static BufferedImage createQRImageBuffer(String content, int width, int height) throws Exception {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        BufferedImage p_w_picpath = toBufferedImage(bitMatrix);
        return p_w_picpath;
    }
}
