package org.efreak.bukkitmanager.addon.spoutgui.screens;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.getspout.spoutapi.gui.Widget;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Screen {

	private File screenFile;
	private Document doc = null;
	private String id;
	private List<Widget> widgets;
	
	public Screen(File file) {
		screenFile = file;
		widgets = new ArrayList<Widget>();
		readFile();
	}
	
	public void readFile() {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(screenFile);
			doc.getDocumentElement().normalize();
			id = doc.getDocumentElement().getAttribute("id");
			NodeList containerNodes = doc.getElementsByTagName("container");
			for (int i = 0; i < containerNodes.getLength(); i++) {
				if (containerNodes.item(i).getNodeType() == Node.ELEMENT_NODE) widgets.add(new Container((Element) containerNodes.item(i)));
			}
			NodeList btnNodes = doc.getElementsByTagName("button");
			for (int i = 0; i < btnNodes.getLength(); i++) {
				if (btnNodes.item(i).getNodeType() == Node.ELEMENT_NODE) widgets.add(new Button((Element) btnNodes.item(i)));
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
