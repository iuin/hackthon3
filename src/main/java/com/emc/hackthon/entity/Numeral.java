package com.emc.hackthon.entity;

import com.emc.hackthon.util.CommonUtils;

import java.util.ArrayList;
import java.util.LinkedList;
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
    private List<Line> lines;

    public Numeral(String content) {
        points = new ArrayList<Point>(7);
        lines = new ArrayList<Line>();
        String[] split = content.split(",");
        number = Integer.parseInt(split[split.length - 1].trim());
        name = CommonUtils.getSha1(content).substring(0, 6)+"-"+number;
        for (int i = 0; i < split.length - 2; i = i + 2) {
            addPoint(new Point(Integer.parseInt(split[i].trim()), Integer.parseInt(split[i + 1].trim())));
        }

        for (int i = 1; i < points.size(); i++) {
            lines.add(new Line(points.get(i - 1), points.get(i)));
        }
    }

    private void addPoint(Point point) {
        points.add(point);
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

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    /**
     * 移除相似的线段
     */
    private void removeSameLine() {
        List<Line> list = new ArrayList<Line>();
        Line lastLine = null;
        for (int i = 0; i < lines.size(); i++) {
            Line currentLine = lines.get(i);
            if(list.size() > 0){
                lastLine = list.get(list.size() -1);
                //方向相同或者两条线的角度小于等于15度的时候合并
                if(CommonUtils.isSameDirection(lastLine, currentLine)
                        || CommonUtils.compareAngle(lastLine,currentLine) <= 15){
                    Line mergeLine = CommonUtils.mergeLine(lastLine, currentLine);
                    list.set(list.size() -1, mergeLine);
                }else{
                    list.add(currentLine);
                }
            }else {
                list.add(currentLine);
            }
        }
        setLines(list);
    }

    /**
     * 获取线段的特征
     * @return
     */
    public String getFeature() {
        removeSameLine();
        String feature = "";
        for (Line line : lines) {
            feature+=line.getDirection()+",";
        }
        return feature+number;
    }

    @Override
    public String toString() {
        return "Numeral{" +
                "points=" + points +
                '}';
    }
}
