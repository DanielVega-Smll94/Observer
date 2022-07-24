/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oscarblancarte.ipd.templetemethod.impl;

/**
 *
 * @author dvega
 */
import java.io.File;
import java.io.FileOutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
public class PaymentNew extends AbstractFileProcessTemplete {
    private String log = "";
    //constructor
    public PaymentNew(File file, String logPath, String movePath) {
        super(file, logPath, movePath);
    }

    @Override
    protected void validateName() throws Exception {
        String name = file.getName();
        if (!name.endsWith(".xml")) {
            throw new Exception("Archivo invalido"+ " debe terminar en extensión .gry");
        }
        if (name.length() != 12) {
            throw new Exception(" Formato de documento incorrecto");
        }
    }
        
    @Override
    protected void processFile() throws Exception {
try{
            File proceso = new File(file.getPath());
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(proceso);
            document.getDocumentElement().normalize();
            NodeList nList = document.getElementsByTagName("pagos");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;                    
                    String Nombre = eElement.getElementsByTagName("nombrebanco").item(0).getTextContent();
                    String Tarjeta = eElement.getElementsByTagName("tarjeta").item(0).getTextContent();
                    String Monto = eElement.getElementsByTagName("monto").item(0).getTextContent();
                    String Cliente = eElement.getElementsByTagName("cliente").item(0).getTextContent();
                    String Estado = eElement.getElementsByTagName("estado").item(0).getTextContent();
                    if(Nombre.equals("")){
                        log += "";
                    }
                    else{
                        log += Nombre.trim().toLowerCase();
                    }                    
                    if(Tarjeta.equals("Credito")){
                        log += "00100";
                    }
                    else{
                        log += "00200";
                    }                    
                    log += Monto.replace(".", "");
                    log += Cliente.trim().toLowerCase();
                    if(Estado.equals("S")){
                        log += "00100";
                    }
                    else{
                        log += "00200";
                    }
                }
            }
        }
        finally{
            try {
                
            } catch (Exception e) {
                
            }
        }
    }

    @Override
    protected void createLog() throws Exception {
        FileOutputStream output = null;
        try {
            File finalFile = new File(logPath + "/" + file.getName());
            if (!finalFile.exists()) {
                finalFile.createNewFile();
            }
            output = new FileOutputStream(finalFile, false);
            output.write(log.getBytes());
            output.flush();
        } finally {
            output.close();
        }

    }

    
}
