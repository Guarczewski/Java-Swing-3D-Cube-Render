package Shapes;

import CPoint.Point3D;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Polygon3D{

    private Color Col;
    private Point3D[] Points;

    public Polygon3D(Color _Color, Point3D... _Points){
        Col = _Color;
        Points = new Point3D[_Points.length];
        for (int i = 0; i < _Points.length; i++){
            Point3D tmp = _Points[i];
            Points[i] = new Point3D(tmp.x, tmp.y, tmp.z);
        }
    }

    public void Render(Graphics g) {

        Polygon poly = new Polygon();

        for (Point3D point : Points) {
            Point p = Point3D.ConvertPoint(point);
            poly.addPoint(p.x, p.y);
        }

        g.setColor(Col);
        g.fillPolygon(poly);

    }

    public void Rotate(boolean CW, double _VectorX, double _VectorY, double _VectorZ) {
        for (Point3D p : Points){
            Point3D.rotateAxisX(p,CW,_VectorX);
            Point3D.rotateAxisY(p,CW,_VectorY);
            Point3D.rotateAxisZ(p,CW,_VectorZ);
        }
    }

    public double getAverageX() {
        double sum = 0;
        for (Point3D p : Points){
            sum += p.x;
        }
        return sum / Points.length;
    }

    public static Polygon3D[] sortPolygons(Polygon3D[] _Polygons){
        List<Polygon3D> polygon3DList = new ArrayList<Polygon3D>();

        for (Polygon3D poly : _Polygons){
            polygon3DList.add(poly);
        }

        Collections.sort(polygon3DList, new Comparator<Polygon3D>(){
            @Override
            public int compare(Polygon3D p1, Polygon3D p2){
                return p2.getAverageX() - p1.getAverageX() < 0 ? 1 : -1;
            }
        });

        for (int i = 0; i < _Polygons.length; i++) {
            _Polygons[i] = polygon3DList.get(i);
        }

        return _Polygons;

    }

}
