package src.GIS;

import src.Geom.Geom_element;
import src.Geom.Point3D;

public class MyGisElement implements GIS_element
{
    Point3D geom;
    MyMetaData meta;

    public MyGisElement(Point3D geom , MyMetaData meta)
    {
        this.geom = geom;
        this.meta = meta;
    }

    @Override
    public Geom_element getGeom()
    {
        return this.geom;
    }

    @Override
    public Point3D getPoint()
    {
        return this.geom;
    }

    @Override
    public Meta_data getData()
    {
        return this.meta;
    }

    //TODO: finish translate
    @Override
    public void translate(Point3D vec)
    {
    }

}
