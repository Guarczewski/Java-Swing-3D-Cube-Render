package Shapes;

import java.awt.*;

public class Tetrahedron {

    private Polygon3D[] Polygons;

    public Tetrahedron(Polygon3D... _Polygons){
        Polygons = _Polygons;
    }

    public void Render(Graphics g) {
        for (Polygon3D poly : this.Polygons) {
            poly.Render(g);
        }
    }

    public void Rotate(boolean CW, double _VectorX, double _VectorY, double _VectorZ) {
        for (Polygon3D p : this.Polygons){
            p.Rotate(CW,_VectorX,_VectorY,_VectorZ);
        }
        sortPolygons();
    }

    private void sortPolygons(){
        Polygon3D.sortPolygons(this.Polygons);
    }

}
