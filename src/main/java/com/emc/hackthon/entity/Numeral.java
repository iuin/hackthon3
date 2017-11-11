package com.emc.hackthon.entity;

import com.emc.hackthon.util.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Alex
 * Date: 2017/11/11
 * Time: 21:36
 * Description:
 */
public class Numeral {

    private String name;
    private List<Point> points;
    private Integer number;

    public Numeral(String line) {
        points = new ArrayList<Point>(7);
        String[] split = line.split(",");
        number = Integer.parseInt(split[split.length - 1].trim());
        name = CommonUtils.getSha1(line).substring(0, 3)+"-"+number;
        for (int i = 0; i < split.length - 2; i = i + 2) {
            addPoint(new Point(Integer.parseInt(split[i].trim()), Integer.parseInt(split[i + 1].trim())));
        }
    }

    private void addPoint(Point point) {
        points.add(point);
    }

    @Override
    public String toString() {
        return "Numeral{" +
                "points=" + points +
                '}';
    }

    public List<Point> getPoints() {
        return points;
    }

    public Integer getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

}
