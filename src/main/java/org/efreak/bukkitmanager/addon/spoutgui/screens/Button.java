package org.efreak.bukkitmanager.addon.spoutgui.screens;

import org.getspout.spoutapi.gui.GenericButton;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Button extends GenericButton {

	private Element element;
	private NodeList childs;
	private String id;
	
	public Button(Element arg1Element) {
		element = arg1Element;
		id = element.getAttribute("id");
		childs = element.getChildNodes();
		for (int i = 0; i < childs.getLength(); i++) {
			Node childNode = childs.item(i);
			if (childNode.getNodeType() == Node.ELEMENT_NODE) {
				Element child = (Element) childNode;
				String childName = child.getNodeName();
				String childValue = child.getTextContent();
				if (childName.equalsIgnoreCase("x-pos")) setX(Integer.valueOf(childValue));
				else if (childName.equalsIgnoreCase("y-pos")) setY(Integer.valueOf(childValue));
				else if (childName.equalsIgnoreCase("height")) setHeight(Integer.valueOf(childValue));
				else if (childName.equalsIgnoreCase("width")) setWidth(Integer.valueOf(childValue));
				else if (childName.equalsIgnoreCase("max-height")) setMaxHeight(Integer.valueOf(childValue));
				else if (childName.equalsIgnoreCase("max-width")) setMaxWidth(Integer.valueOf(childValue));
				else if (childName.equalsIgnoreCase("min-height")) setMinHeight(Integer.valueOf(childValue));
				else if (childName.equalsIgnoreCase("min-width")) setMinWidth(Integer.valueOf(childValue));
				
			}
		}
	}	
}
