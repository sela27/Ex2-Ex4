package src.File_format;

import src.GIS.GIS_element;
import src.GIS.MyGisElement;
import src.GIS.MyGisLayer;
import src.GIS.MyMetaData;
import src.Geom.Point3D;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.SimpleTimeZone;


public class Csv2kml
{
    public static LinkedList<String[]> CSVReader(String csvFile)
    {
        String line = "";
        String cvsSplitBy = ",";
        String[] info = {};
        LinkedList<String[]> InfoList = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile)))
        {
            while ((line = br.readLine()) != null)
            {
                info = line.split(cvsSplitBy);
                InfoList.add(info);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return InfoList;
    }

    public static MyGisElement CreateGisElementFromCvs(String[] info)
    {

        double x = Double.parseDouble(info[6]);
        double y = Double.parseDouble(info[7]);
        double z = Double.parseDouble(info[8]);
        Point3D geo = new Point3D(x,y,z);

        String time = info[3];
        DateTimeFormatter parseFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(time, parseFormatter);
        long utc = dateTime.atOffset(ZoneOffset.UTC).toInstant().toEpochMilli();


        MyMetaData Mdata = new MyMetaData(utc);

        return new MyGisElement(geo,Mdata);
    }

    public static MyGisLayer CreateGisLayer(LinkedList<String[]> info)
    {
        MyGisLayer Layer = new MyGisLayer();
        Iterator<String[]> I = info.iterator();
        while (I.hasNext())
        {
            Layer.add(CreateGisElementFromCvs(I.next()));
        }
        return Layer;
    }

    public static void CreateKmlFileFromCvs(String path)
    {
        LinkedList<String[]> L = CSVReader(path);
        MyGisLayer Layer = CreateGisLayer(L);
        String kml = "";
        kml+= "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
        kml+= "\n";
        kml+= "<kml xmlns="+"http://www.opengis.net/kml/2.2"+"><Document><Style id="+"red"+"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style><Style id="+"yellow"+"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style><Style id="+"green"+"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style><Folder><name>Wifi Networks</name>";
        kml+= "\n";
        Iterator<GIS_element> Ilayer = Layer.iterator();
        GIS_element gisEle;
        while (Ilayer.hasNext())
        {
            gisEle = Ilayer.next();
            kml+="<Placemark>";
            kml+="\n";
            kml+= "<description>";
            kml+= "<![CDATA[";
            kml+= "Date: <b>";
            kml += gisEle.getData().toString();
            kml += "</b>]]></description>";
            kml+= "\n";
            kml += "<Point>";
            kml+= "\n";
            kml+= "<coordinates>";
            kml += gisEle.getPoint().x() + "," + gisEle.getPoint().y() + "," + gisEle.getPoint().z();
            kml += "</coordinates></Point>" + "\n" +  "</Placemark>";

        }


    }

    /*public static void main(String[] args)
    {
        LinkedList<String[]> arr = CSVReader("WigleWifi_20171201110209.csv");
        Iterator<String[]> I = arr.iterator();
        String[] line;
        while (I.hasNext())
        {
            line = I.next();
            for(int i = 0; i < line.length; i++)
                System.out.print(line[i] + " ");
            System.out.println();
        }

    }*/
}