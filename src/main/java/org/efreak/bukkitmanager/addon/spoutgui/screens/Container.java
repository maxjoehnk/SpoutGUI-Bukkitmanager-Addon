package org.efreak.bukkitmanager.addon.spoutgui.screens;

import org.getspout.spoutapi.gui.GenericContainer;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Container extends GenericContainer {
	
	private Element element;
	private NodeList childs;
	private String id;
	
	public Container(Element arg1Element) {
		element = arg1Element;
		id = element.getAttribute("id");
		childs = element.getChildNodes();
		for (int i = 0; i < childs.getLength(); i++) {
			Node childNode = childs.item(i);
			if (childNode.getNodeType() == Node.ELEMENT_NODE) {
				Element child = (Element) childNode;				
			}
		}
	}
}
