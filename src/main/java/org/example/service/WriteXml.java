package org.example.service;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.OutputStream;

public class WriteXml {
    public void setXmlAttributeAndElements(String path, String customer, Long id, Integer newInitialBalance) throws ParserConfigurationException, TransformerException {
        File myFile = new File(path);
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        // root elements
        Document doc = docBuilder.newDocument();
        Element deposits = doc.createElement("deposits");
        doc.appendChild(deposits);

        //deposit Node
        Element deposit = doc.createElement("deposit");
        deposits.appendChild(deposit);

        //Set attribute for deposit
        Element customerName = doc.createElement("customer");
        customerName.appendChild(doc.createTextNode(customer.toString()));
        deposit.appendChild(customerName);

        // id Node
        Element customerId = doc.createElement("id");
        customerId.appendChild(doc.createTextNode(id.toString()));
        deposit.appendChild(customerId);
        //newInitialBalance Node
        Element newBalance = doc.createElement("newInitialBalance");
        newBalance.appendChild(doc.createTextNode(newInitialBalance.toString()));
        deposit.appendChild(newBalance);
        writeToXmlFile(doc,System.out);
    }

    public void writeToXmlFile(Document doc, OutputStream output) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);
    }
}
