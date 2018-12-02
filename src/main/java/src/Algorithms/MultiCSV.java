package src.Algorithms;

import src.GIS.GIS_layer;
import src.GIS.MyGisProject;

import java.util.Iterator;
import java.util.LinkedList;

public class MultiCSV
{
    public static MyGisProject CreateGisProject(LinkedList<GIS_layer> LS)
    {
        MyGisProject GisPro = new MyGisProject();
        Iterator<GIS_layer> Ilayer = LS.iterator();
        GIS_layer Glayer;
        while (Ilayer.hasNext())
        {
            Glayer = Ilayer.next();
            GisPro.add(Glayer);
        }
        return GisPro;
    }

    public static void CreateKmlFileProject(MyGisProject GisPro)
    {
        Iterator<GIS_layer> Ilayer = GisPro.iterator();
    }
}
