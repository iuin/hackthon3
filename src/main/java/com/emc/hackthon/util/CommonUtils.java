package com.emc.hackthon.util;

import com.emc.hackthon.entity.Line;
import com.emc.hackthon.entity.Numeral;
import com.emc.hackthon.entity.Point;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * User: Alex
 * Date: 2017/11/11
 * Time: 22:10
 * Description:
 */
public class CommonUtils {

    //direction
    private static String IMAGE_FOLDER = "C://hackthon//tarin//image//";
    static {
        File folder = new File(IMAGE_FOLDER);
        if(!folder.exists()){
            folder.mkdirs();
        }
    }

    /**
     * 数据训练读取
     * @param filepath
     * @return
     * @throws IOException
     */
    public static List<Numeral> readTrainData(String filepath) throws IOException {
        List<Numeral> numerals = new ArrayList<Numeral>();
        File file = new File(filepath);
        BufferedReader bufReader = null;
        BufferedWriter bufWriter = null;
        try {
            bufReader = new BufferedReader(new FileReader(file));
            String temp = null;
            while ((temp = bufReader.readLine()) != null) {
                numerals.add(new Numeral(temp));
            }
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (bufReader != null && bufWriter != null) {
                try {
                    bufReader.close();
                    bufWriter.close();
                } catch (IOException e) {
                    e.getStackTrace();
                }
            }
        }
        return numerals;
    }


    /**
     * 生成线的图片，根据的点来生成
     * @param numeral
     */
    public static void createImageByPoint(Numeral numeral){
        int width = 100;
        int height = 100;

        File file = new File(IMAGE_FOLDER + numeral.getName() + ".jpg");
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D)bi.getGraphics();
        g2.setBackground(Color.WHITE);
        g2.clearRect(0, 0, width, height);
        g2.setPaint(Color.RED);

        List<com.emc.hackthon.entity.Point> points = numeral.getPoints();

        for (int i = 0; i < points.size() - 1; i++) {
            com.emc.hackthon.entity.Point p1 = points.get(i);
            com.emc.hackthon.entity.Point p2 = points.get(i+1);
            g2.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
        }

        try {
            ImageIO.write(bi, "jpg", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据线段生成图片
     * @param numeral
     */
    public static void createImageByLine(Numeral numeral){
        int width = 100;
        int height = 100;
        List<Line> lines = numeral.getLines();

        File file = new File(IMAGE_FOLDER + numeral.getName()+"-"+ lines.size() + ".jpg");
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D)bi.getGraphics();
        g2.setBackground(Color.WHITE);
        g2.clearRect(0, 0, width, height);
        g2.setPaint(Color.RED);


        for (int i = 0; i < lines.size(); i++) {
            Line line = lines.get(i);
            g2.drawLine(line.getP1().x,line.getP1().y,line.getP2().x,line.getP2().y);
        }

        try {
            ImageIO.write(bi, "jpg", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成点的图片
     * @param numeral
     */
    public static void createPointImage(Numeral numeral){
        int width = 100;
        int height = 100;
        File file = new File(numeral.getName() + ".jpg");
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D)bi.getGraphics();
        g2.setBackground(Color.WHITE);
        g2.clearRect(0, 0, width, height);
        g2.setPaint(Color.RED);

        List<com.emc.hackthon.entity.Point> points = numeral.getPoints();
        for (Point point : points) {
            g2.drawLine(point.getX(),point.getY(),point.getX(),point.getY());
        }

        try {
            ImageIO.write(bi, "jpg", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getSha1(String str) {
        if (null == str || 0 == str.length()) {
            return null;
        }
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = null;
            mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));
            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] buf = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 比较角度
     * @param l1
     * @param l2
     * @return
     */
    public static double compareAngle(Line l1, Line l2){
        double x1 = l1.getP2().x - l1.getP1().x;
        double y1 = l1.getP2().y - l1.getP1().y;

        double x2 = l2.getP2().x - l2.getP1().x;
        double y2 = l2.getP2().y - l2.getP1().y;

        double angle1 = Math.toDegrees(Math.atan(y1/x1));
        double angle2 = Math.toDegrees(Math.atan(y2/x2));

        return Math.abs(angle1 - angle2);
    }

    /**
     * 方向是否一致
     * @param l1
     * @param l2
     * @return
     */
    public static boolean isSameDirection(Line l1, Line l2) {
        return l1.getDirection().equals(l2.getDirection());
    }


    public static Line mergeLine(Line l1, Line l2) {
        return new Line(l1.getP1(), l2.getP2());
    }

}
