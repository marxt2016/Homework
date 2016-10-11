package xmlParser;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
/**
 * Created by OM on 02.10.2016.
 */
public class Parser {
    //creates DOM object from XML file
    private Document parseXMLfile(String filepath) {
        File file = new File(filepath);
        //Factory to create document builder
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        Document dom = null;
        if (file.exists()) {
            try {
                DocumentBuilder builder = builderFactory.newDocumentBuilder();
                dom = builder.parse(file);
            } catch (SAXException e){
                e.printStackTrace();
            }catch (IOException ioe){
                ioe.printStackTrace();
            }catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
        }
        return dom;
    }

    public Map<String,List<String>> parseDocument (String file, String type){
        //get ROOT element
        Element docElement = null;
        docElement = parseXMLfile(file).getDocumentElement();

        Map<String,List<String>> hMap=new HashMap<String, List<String>>();

        NodeList nodesList = docElement.getElementsByTagName(type);
        if(nodesList!=null && nodesList.getLength()>0){
            for (int i=0; i< nodesList.getLength(); i++){
                Node node =nodesList.item(i);
                if(node.getNodeType()==Node.ELEMENT_NODE){
                    Element e = (Element) node;
                    ArrayList<String> valuesList = new ArrayList<String>();
                    NodeList nodeList = e.getElementsByTagName("planetype");
                    String planetype = nodeList.item(0).getChildNodes().item(0).getNodeValue();
                    nodeList = e.getElementsByTagName("model");
                    String model = nodeList.item(0).getChildNodes().item(0).getNodeValue();
                    valuesList.add(model);
                    nodeList = e.getElementsByTagName("numberOfSeats");
                    String numberOfSeats = nodeList.item(0).getChildNodes().item(0).getNodeValue();
                    valuesList.add(numberOfSeats);
                    nodeList = e.getElementsByTagName("cargoVolume");
                    String cargoVolume = nodeList.item(0).getChildNodes().item(0).getNodeValue();
                    valuesList.add(cargoVolume);
                    nodeList = e.getElementsByTagName("averageFlyingSpeed");
                    String averageFlyingSpeed = nodeList.item(0).getChildNodes().item(0).getNodeValue();
                    valuesList.add(averageFlyingSpeed);
                    nodeList = e.getElementsByTagName("averageRidingSpeed");
                    String averageRidingSpeed = nodeList.item(0).getChildNodes().item(0).getNodeValue();
                    valuesList.add(averageRidingSpeed);
                    nodeList = e.getElementsByTagName("averageFlyingDistance");
                    String averageFlyingDistance = nodeList.item(0).getChildNodes().item(0).getNodeValue();
                    valuesList.add(averageFlyingDistance);
                    hMap.put(planetype+i,valuesList);
                }
            }
        }return hMap;
    }

    public static void main (String args[]){
        Parser parser = new Parser();
        Map<String,List<String>> values1 = parser.parseDocument("D:\\workspace\\Homework\\resources\\planes.xml", "p");
        Map<String,List<String>> values = parser.parseDocument("D:\\workspace\\Homework\\resources\\planes.xml", "c");
       // System.out.println(values.values());
        for (Object item: values.keySet()){
            for (int i=0; i<values.get(item).size();i++ )
            System.out.println(values.get(item).get(i));
        }
        for (Object item: values1.keySet()){
            for (int i=0; i<values1.get(item).size();i++ )
            System.out.println(values1.get(item).get(i));
        }

    }

}
