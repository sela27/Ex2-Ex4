package src.GIS;

import src.Geom.Point3D;

public class MyMetaData implements Meta_data
{
    private long UTC;


    public MyMetaData(long UTC)
    {
        this.UTC = UTC;

    }

    /**
     * returns the Universal Time Clock associated with this data;
     */
    @Override
    public long getUTC()
    {
        return this.UTC;
    }


    /**
     * @return the orientation: yaw, pitch and roll associated with this data;
     */
    //TODO: finish Orientation
    @Override
    public Point3D get_Orientation()
    {
        return null;
    }

    @Override
    public String toString()
    {
        return this.UTC + "";
    }
}