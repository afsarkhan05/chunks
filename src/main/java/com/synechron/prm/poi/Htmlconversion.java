package com.synechron.prm.poi;


import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.StringWriter;


//import javax.swing.text.Document;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.w3c.dom.Document;


/**
 *
 * @author sandy
 */
public class Htmlconversion {
    
    public static void main(String args[])
    {
    try{
        File docFile=new File("D:\\Workspace\\MyChunks\\uploaded\\Abrar_Khan_Resume_-_Production.doc");   // file object was created
FileInputStream finStream=new FileInputStream(docFile.getAbsolutePath()); // file input stream with docFile
HWPFDocument doc=new HWPFDocument(finStream);// throws IOException and need to import org.apache.poi.hwpf.HWPFDocument;
WordExtractor wordExtract=new WordExtractor(doc);
         Document newDocument = DocumentBuilderFactory.newInstance() .newDocumentBuilder().newDocument();
    WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(newDocument) ;


    wordToHtmlConverter.processDocument(doc);


    StringWriter stringWriter = new StringWriter();
    Transformer transformer = TransformerFactory.newInstance()
            .newTransformer();
    transformer.setOutputProperty( OutputKeys.INDENT, "yes" );
    transformer.setOutputProperty( OutputKeys.ENCODING, "utf-8" );
    transformer.setOutputProperty( OutputKeys.METHOD, "html" );
    transformer.transform(
            new DOMSource( wordToHtmlConverter.getDocument() ),
            new StreamResult( stringWriter ) );


    String html = stringWriter.toString();
  
  System.out.println(html);
    
   FileOutputStream fos; 
    DataOutputStream dos;

   File file= new File("D:\\Workspace\\MyChunks\\uploaded\\my.html");
   
   FileWriter fw= new FileWriter(file);
    
      fw.write(html);
     JEditorPane editorPane = new JEditorPane();
    editorPane.setContentType("text/html");
editorPane.setEditable(false);


editorPane.setPage(file.toURI().toURL());

       JScrollPane scrollPane = new JScrollPane(editorPane);     
     JFrame f = new JFrame("Display Html File");
     // Next line requires Java 1.3
     f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     f.getContentPane().add(scrollPane);
     f.setSize(512, 342);
     f.setVisible(true);
    }catch(Exception e)
    {
    
    }
    }
    
}
