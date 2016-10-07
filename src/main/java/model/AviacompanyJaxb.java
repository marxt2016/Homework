package model;

import exceptions.FileNotFoundException;
import xmlParser.JaxbParser;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Created by OM on 02.10.2016.
 */

@XmlRootElement(name = "aviacompany")
@XmlType(propOrder ={"name","planes"})
public class AviacompanyJaxb  {
    public static final String PATH_TO_FILE = "./src/main/resources/aviacompany.xml";
    private String name;
    private List <PlaneJaxb> planes;

    public AviacompanyJaxb(String name, List<PlaneJaxb> planes) {
        this.name = name;
        this.planes = planes;
    }
    public AviacompanyJaxb() {    }

    public String getName() {
        return name;
    }
    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public List<PlaneJaxb> getPlanes() {
        return planes;
    }

    @XmlElement(name="plane")
    @XmlElementWrapper
    public void setPlanes(List<PlaneJaxb> planes) {
        this.planes = planes;
    }

    @Override
    public String toString() {
        String result ="AviacompanyJaxb{" +
                "name='" + name + '\'' +
                ", planes {";
        if(planes!=null){
            for (PlaneJaxb p:planes){
                result+=p.toString();
            }
        }
        result+="}}";
        return result;
    }


    public AviacompanyJaxb loadPlanesfromSource() throws Exception{
        JaxbParser parser = new JaxbParser();
        File file = new File(PATH_TO_FILE);
        return (AviacompanyJaxb) parser.getObject(file, AviacompanyJaxb.class);
    }

    public void printAllPlanes(){
        try {
            for (PlaneJaxb plane: this.loadPlanesfromSource().getPlanes()){
                System.out.println(plane.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
