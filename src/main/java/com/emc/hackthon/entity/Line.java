package com.emc.hackthon.entity;

/**
 * User: Alex
 * Date: 2017/11/12
 * Time: 11:37
 * Description:
 */
public class Line {

    private Point p1; //start point
    private Point p2; //end point
    private String direction; // direction

    public Line(Point start, Point end) {
        this.p1 = start;
        this.p2 = end;
    }

    public String getDirection() {
        if(p1.x-p2.x <= 0){
            if(p1.y-p2.y <= 0) {
                //UR
                return "UR";
            }else{
                //DR
                return "DR";
            }
        }else {
            if(p1.y-p2.y <= 0) {
                //UL
                return "UL";
            }else{
                //DL
                return "DL";
            }
        }
    }

    public float length()
    {
        return (float)java.lang.Math.sqrt((p1.x-p2.x)*(p1.x-p2.x)+(p1.y-p2.y)*(p1.y-p2.y));
    }


    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }
}
