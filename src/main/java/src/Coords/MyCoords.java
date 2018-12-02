package src.Coords;

import src.Geom.Point3D;

public class MyCoords implements coords_converter
{
    /**
     * computes a new point which is the gps point transformed by a 3D vector (in meters)
     * @param gps a gps point
     * @param local_vector_in_meter vector in meter
     * @return the new point after we add gps with local_vector_in_meter
     */
    @Override
    public Point3D add(Point3D gps, Point3D local_vector_in_meter)
    {
        Point3D added = new Point3D(0,0,0);
        int EarthRatio = 6371000;
        double RadX = local_vector_in_meter.x() / EarthRatio;
        double RadY = local_vector_in_meter.y() / (EarthRatio * Math.cos((Math.PI * gps.x()) / 180));
        Double x = gps.x() + (RadX * (180/Math.PI));
        Double y = gps.y() + (RadY * (180/Math.PI));
        Double z = gps.z() + local_vector_in_meter.z();
        added.add(x,y,z);
        return added;
    }

    /**
     * computes the 3D distance (in meters) between the two gps like points
     * @param gps0 first point
     * @param gps1 second point
     * @return the distance between the 2 point
     */
    @Override
    public double distance3d(Point3D gps0, Point3D gps1)
    {
        int EarthRatio = 6371000;
        double lon_norm = Math.cos(gps0.x() * (Math.PI / 180));
        double diffX = gps0.x() - gps1.x();
        double diffY = gps0.y() - gps1.y();
        double diffX_Rad = diffX * (Math.PI / 180);
        double diffY_Rad = diffY * (Math.PI / 180);
        double Xmeter = Math.sin(diffX_Rad) * EarthRatio;
        double Ymeter = Math.sin(diffY_Rad) * EarthRatio * lon_norm;
        double distance = Math.sqrt(Math.pow(Xmeter,2) + Math.pow(Ymeter , 2));
        return distance;
    }

    /**
     * computes the 3D vector (in meters) between two gps like points
     * @param gps0 first point
     * @param gps1 second point
     * @return the 3D vector between the two points
     */
    @Override
    public Point3D vector3D(Point3D gps0, Point3D gps1)
    {
        int EarthRatio = 6371000;
        double lon_norm = Math.cos(gps0.x() * (Math.PI / 180));
        double diffX = gps0.x() - gps1.x();
        double diffY = gps0.y() - gps1.y();
        double diffZ = gps0.z() - gps1.z();
        double diffX_Rad = diffX * (Math.PI / 180);
        double diffY_Rad = diffY * (Math.PI / 180);
        double Xmeter = Math.sin(diffX_Rad) * EarthRatio;
        double Ymeter = Math.sin(diffY_Rad) * EarthRatio * lon_norm;
        return new Point3D(Xmeter,Ymeter,diffZ);
    }

    /**
     * computes the polar representation of the 3D vector be gps0-->gps1
     *  Note: this method should return an azimuth (aka yaw), elevation (pitch), and distance
     * @param gps0 first point
     * @param gps1 second point
     * @return an azimuth (aka yaw), elevation (pitch), and distance
     */
    @Override
    public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1)
    {
        double[] azim = new double[3];
        azim[2] = this.distance3d(gps0,gps1);
        Point3D V = vector3D(gps0,gps1);
        Point3D VP = new Point3D(V.x(),V.y(), 0 );
        double yaw = Math.acos(dot_product(V,VP) / (Vector_Length(V) *Vector_Length(VP)));
        azim[0] = yaw;
        Point3D VPP = new Point3D(0, V.y(), 0);
        double pitch = Math.acos(dot_product(VP,VPP) / (Vector_Length(VP) * Vector_Length(VPP)));
        azim[1] = pitch;
        return azim;

    }

    /**
     * return true iff this point is a valid lat, lon , alt coordinate: [-180,+180],[-90,+90],[-450, +inf]
     * @param p a point to check if its a valid gps point
     * @return true if its a gps point false otherwise
     */
    @Override
    public boolean isValid_GPS_Point(Point3D p)
    {
        boolean lat = p.x() <= 180 && p.x() >= -180;
        boolean lon = p.y() <= 90 && p.y() >= -90;
        boolean alt = p.z() >= -450;
        return (lat && lon && alt);
    }

    /**
     * calculate the size of the vector
     * @param vector_in_meter the vector to check
     * @return the size of vector_in_meter
     */
    private double Vector_Length(Point3D vector_in_meter)
    {
        return Math.sqrt(Math.pow(vector_in_meter.x(),2) + Math.pow(vector_in_meter.y(),2) + Math.pow(vector_in_meter.z(),2));
    }

    /**
     * Calculate the dot product between V1 and V2
     * @param V1 first point
     * @param V2 second point
     * @return the dot product
     */
    private double dot_product(Point3D V1, Point3D V2)
    {
        return ((V1.x()*V2.x()) + (V1.y()*V2.y()) +(V1.z()*V2.y()));
    }
}
