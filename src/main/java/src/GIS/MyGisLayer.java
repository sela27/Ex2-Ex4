package src.GIS;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class MyGisLayer extends LinkedList<GIS_element> implements GIS_layer
{
    //private Meta_data Mdata;
    public MyGisLayer()
    {

        //this.Mdata = new MyMetaData(System.currentTimeMillis(),);
    }

    @Override
    public Meta_data get_Meta_data()
    {
        return null;
    }
}