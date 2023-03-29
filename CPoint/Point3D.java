package CPoint;

import CFrame.CFrame;
import java.awt.*;

public class Point3D{

    public double x, y, z;

    public static double scale = 1;

    public Point3D(double _x, double _y, double _z){
        x = _x;
        y = _y;
        z = _z;
    }

    public static Point ConvertPoint(Point3D _3DPoint){

        double x3d = _3DPoint.y * scale;
        double y3d = _3DPoint.z * scale;
        double depth = _3DPoint.x * scale;

        double[] newValues = scale(x3d,y3d,depth);

        int x2D = (int) (CFrame.WIDTH / 2 + newValues[0]);
        int y2D = (int) (CFrame.HEIGHT / 2 - newValues[1]);

        return new Point(x2D,y2D);
    }

    public static double[] scale(double x3d, double y3d, double depth) {
        double dist = Math.sqrt(Math.pow(x3d,2) + Math.pow(y3d,2));
        double theta = Math.atan2(y3d,x3d);
        double depth2 = 15 - depth;
        double localScale = Math.abs(1400/(depth2 + 1400));
        dist *= localScale;
        double[] outValues = new double[2];
        outValues[0] = dist * Math.cos(theta);
        outValues[1] = dist * Math.sin(theta);
        return outValues;
    }

    public static void rotateAxisX(Point3D _Point, boolean CW, double degree){
        double radius = Math.sqrt(Math.pow(_Point.y,2) + Math.pow(_Point.z,2));
        double theta = Math.atan2(_Point.z,_Point.y);
        if (CW) { theta += 2 * Math.PI / 360 * degree * -1; }
        else { theta += 2 * Math.PI / 360 * degree; }
        _Point.y = radius * Math.cos(theta);
        _Point.z = radius * Math.sin(theta);
    }

    public static void rotateAxisY(Point3D _Point, boolean CW, double degree){
        double radius = Math.sqrt(Math.pow(_Point.x,2) + Math.pow(_Point.z,2));
        double theta = Math.atan2(_Point.x,_Point.z);
        if (CW) { theta += 2 * Math.PI / 360 * degree * -1; }
        else { theta += 2 * Math.PI / 360 * degree; }
        _Point.z = radius * Math.cos(theta);
        _Point.x = radius * Math.sin(theta);
    }

    public static void rotateAxisZ(Point3D _Point, boolean CW, double degree){
        double radius = Math.sqrt(Math.pow(_Point.y,2) + Math.pow(_Point.x,2));
        double theta = Math.atan2(_Point.y,_Point.x);
        if (CW) { theta += 2 * Math.PI / 360 * degree * -1; }
        else { theta += 2 * Math.PI / 360 * degree; }
        _Point.x = radius * Math.cos(theta);
        _Point.y = radius * Math.sin(theta);
    }
}
