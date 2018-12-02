package src;

import org.junit.jupiter.api.Test;
import src.Coords.MyCoords;
import src.Geom.*;

import static org.junit.jupiter.api.Assertions.*;

class MyCoordsTest
{

    @Test
    void add()
    {
        Point3D P0 = new Point3D(32.103315 , 35.209039 , 670);
        Point3D Vector = new Point3D(337.6989921, -359.2492069, -20);
        MyCoords coords = new MyCoords();
        Point3D P1 = coords.add(P0,Vector);
        assertEquals(P1.x() , 32.106352 , 0.0001);
        assertEquals(P1.y() , 35.205225 , 0.0001);
        assertEquals(P1.z() , 650 , 0.0001);
    }

    @Test
    void distance3d()
    {
        Point3D P0 = new Point3D(32.103315 , 35.209039 , 670);
        Point3D P1 = new Point3D(32.106352 , 35.205225 , 650);
        MyCoords coords = new MyCoords();
        double dist = coords.distance3d(P0,P1);
        assertEquals(dist,493.0523318 , 0.01);
    }

    @Test
    void vector3D()
    {
        Point3D P0 = new Point3D(32.103315 , 35.209039 , 670);
        Point3D P1 = new Point3D(32.106352 , 35.205225 , 650);
        MyCoords coords = new MyCoords();
        Point3D Vector = coords.vector3D(P0,P1);
        assertEquals(Vector.x() ,  -337.6989921, 0.0001);
        assertEquals(Vector.y() , 359.2492069 , 0.0001);
        assertEquals(Vector.z() , 20 , 0.0001);
    }

    @Test
    void azimuth_elevation_dist()
    {

    }

    @Test
    void isValid_GPS_Point()
    {
        assertTrue(new MyCoords().isValid_GPS_Point(new Point3D(180,90,1000)));
        assertFalse(new MyCoords().isValid_GPS_Point(new Point3D(190,90,1000)));
    }
}