package BotTelegram;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author prof
 */
public class CoordintateXml {

    private Document document;
    private String fileName = "csv/coordinate.csv";
    private String xmlFile = "xml/location.xml";

    public Document getDocument() {
        return document;
    }

    public boolean getXMLToCSV(String ricerca, String idChat, String nomeUtente) throws IOException {

        getXML(ricerca);
        boolean exists = true;
         Coordinate coordinate = new Coordinate();

        try {
            coordinate = getCoordinate();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(CoordintateXml.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(CoordintateXml.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (coordinate.getLatitudine()!=0 || coordinate.getLongitudine()!=0) {
            String testo = coordinate.getLatitudine() + ";" + coordinate.getLongitudine() + ";" + idChat + ";" + nomeUtente;
            AddToCSV(testo);
        } else {
            exists = false;
        }
        return exists;
    }

    public void getXML(String ricerca) throws FileNotFoundException, MalformedURLException, IOException {
        BufferedReader in = null;
        PrintWriter out;
        out = new PrintWriter(xmlFile);
        URL url;
        String search = "https://nominatim.openstreetmap.org/search?q=" + URLEncoder.encode(ricerca, StandardCharsets.UTF_8) + "&format=xml&polygon_geojson=1&addressdetails=1";
        url = new URL(search);
        Scanner scanner = new Scanner(url.openStream());//per leggere tutto file
        scanner.useDelimiter("\u001a");//indica la fine del file
        String file = scanner.next();
        out.write(file);
        out.close();
    }

    public Coordinate getCoordinate() throws ParserConfigurationException, SAXException, IOException {

        DocumentBuilderFactory factory;
        DocumentBuilder builder;
        Element root, element;
        NodeList nodelist;
        Coordinate cor=null;
        // creazione dell’albero DOM dal documento XML
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();

        document = builder.parse(xmlFile);
        root = document.getDocumentElement();
        nodelist = root.getElementsByTagName("place");
        if (nodelist != null && nodelist.getLength() > 0) {
            int numNode = nodelist.getLength();
            element = (Element) nodelist.item(0);
            String lat = element.getAttribute("lat");
            String lon = element.getAttribute("lon");
            cor=new Coordinate(Float.parseFloat(lat), Float.parseFloat(lon));
        }

        return cor;
    }

    public void AddToCSV(String testo) throws IOException {
        FileWriter fw = new FileWriter(fileName, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(testo);
        bw.newLine();
        bw.close();
    }

    
    
    public List<User> getListaCoordinate() throws IOException{
        List<User> lista = new ArrayList();
        
        File f;
        f = new File("coordinate.csv");
        Scanner reader = new Scanner(System.in);  // Reading from System.in

        BufferedReader csvReader=null;
        csvReader = new BufferedReader(new FileReader(fileName));
        
        String row;
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(";");
            Coordinate co = new Coordinate(Float.parseFloat(data[0]), Float.parseFloat(data[1]));
            User ut = new User(co, data[2], data[3]);
            lista.add(ut);
        }
        csvReader.close();
        
        return lista;
    }
}
