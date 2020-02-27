package br.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.tree.*;
import java.util.Iterator;
import org.jdom.*;
import org.jdom.input.SAXBuilder;
import java.io.StringReader;


public class XMLTreeViewer extends JFrame {


    private JTree xmlTree;
    private Document xmlDoc;
    DefaultMutableTreeNode tn;

    public XMLTreeViewer(String xml_text) {
        super();
        Document doc=null;
        try {
            doc = new SAXBuilder().build(new StringReader(xml_text));
        } catch (Exception e) {
            System.out.println("ERRO:" + e.getMessage());
        }
        this.xmlDoc = doc;
        setSize(600, 450);
        tn = new DefaultMutableTreeNode("XML");
        initialize();
    }

    private void initialize() {

        xmlTree = new JTree();
        xmlTree.setName("XML Tree");
        this.setTitle(xmlDoc.getRootElement().getName());
 
        getContentPane().add(new JScrollPane(xmlTree), BorderLayout.CENTER);

        processElement(xmlDoc.getRootElement(), tn);

        ((DefaultTreeModel) xmlTree.getModel()).setRoot(tn);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                xmlTree = null;
                tn = null;
            }
        });

        setVisible(true);
    }

    private void processElement(Element el, DefaultMutableTreeNode dmtn) {
        DefaultMutableTreeNode currentNode
                = new DefaultMutableTreeNode(el.getName());
        String text = el.getTextNormalize();
        if ((text != null) && (!text.equals(""))) {
            currentNode.add(new DefaultMutableTreeNode(text));
        }

        processAttributes(el, currentNode);

        Iterator children = el.getChildren().iterator();

        while (children.hasNext()) {
            processElement((Element) children.next(), currentNode);
        }

        dmtn.add(currentNode);
    }

    private void processAttributes(Element el, DefaultMutableTreeNode dmtn) {
        Iterator atts = el.getAttributes().iterator();

        while (atts.hasNext()) {
            Attribute att = (Attribute) atts.next();
            DefaultMutableTreeNode attNode
                    = new DefaultMutableTreeNode("@: " + att.getName());
   
            attNode.add(new DefaultMutableTreeNode(att.getValue()));
            dmtn.add(attNode);
          
        }
    }

   
}
