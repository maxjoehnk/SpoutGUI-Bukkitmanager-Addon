package org.efreak.bukkitmanager.addon.spoutgui;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import org.efreak.bukkitmanager.Bukkitmanager;
import org.efreak.bukkitmanager.Configuration;

import org.getspout.spoutapi.gui.*;

public class Screen extends GenericPopup {

	private static SAXParserFactory saxFactory;
	private static Configuration config;
	
	private SAXParser saxParser;
	private File screenFile;
	private String id;
	private HashMap<String, Widget> widgets;
	
	static {
		saxFactory = SAXParserFactory.newInstance();
		config = Bukkitmanager.getConfiguration();
	}
	
	public Screen(File file) {
		screenFile = file;
		widgets = new HashMap<String, Widget>();
		try {
			saxParser = saxFactory.newSAXParser();
			saxParser.parse(screenFile, setupHandler());
		} catch (SAXException e) {
			if (config.getDebug()) e.printStackTrace();
		} catch (IOException e) {
			if (config.getDebug()) e.printStackTrace();
		} catch (ParserConfigurationException e) {
			if (config.getDebug()) e.printStackTrace();
		}
	}
	
	private DefaultHandler setupHandler() {
		return new DefaultHandler() {
			@Override
			public void startElement(String uri, String localName, String qName, Attributes attributes) {
				System.out.println(uri);
				if (qName.equalsIgnoreCase("screen")) {
					id = "Screen-" + SpoutGUIAddon.getScreens().size();
					int index = attributes.getIndex("id");
					if (index != -1) id = attributes.getValue(index);
				}
				if (qName.equalsIgnoreCase("widget")) {
					WidgetType type = null;
					Widget widget = null;
					String id = "Element-" + widgets.size();
					int index = attributes.getIndex("id");
					if (index != -1) id = attributes.getValue(index);
					index = attributes.getIndex("type");
					if (index != -1) type = WidgetType.valueOf(attributes.getValue(index).toUpperCase());
					switch(type) {
					case BUTTON:
						widget = new GenericButton();
						index = attributes.getIndex("text");
						if (index != -1) ((GenericButton) widget).setText(attributes.getValue(index));
						index = attributes.getIndex("disabled-text");
						if (index != -1) ((GenericButton) widget).setDisabledText(attributes.getValue(index));
						index = attributes.getIndex("text-color");
						if (index != -1) ((GenericButton) widget).setTextColor(new Color(attributes.getValue(index)));
						index = attributes.getIndex("hover-color");
						if (index != -1) ((GenericButton) widget).setHoverColor(new Color(attributes.getValue(index)));
						index = attributes.getIndex("shadow");
						if (index != -1) ((GenericButton) widget).setShadow(attributes.getValue(index).equalsIgnoreCase("true") ? true : false);
						index = attributes.getIndex("resize");
						if (index != -1) ((GenericButton) widget).setResize(attributes.getValue(index).equalsIgnoreCase("true") ? true : false);
						index = attributes.getIndex("auto");
						if (index != -1) ((GenericButton) widget).setAuto(attributes.getValue(index).equalsIgnoreCase("true") ? true : false);
						index = attributes.getIndex("scale");
						if (index != -1) ((GenericButton) widget).setScale(Float.valueOf(attributes.getValue(index)));
						break;
					case CHECKBOX:
						widget = new GenericCheckBox();
						index = attributes.getIndex("checked");
						if (index != -1) ((GenericCheckBox) widget).setChecked(attributes.getValue(index).equalsIgnoreCase("true") ? true : false);
						break;
					case ENTITYWIDGET:
						widget = new GenericEntityWidget();
						index = attributes.getIndex("entity-id");
						if (index != -1) ((GenericEntityWidget) widget).setEntityId(Integer.valueOf(attributes.getValue(index)));
						break;
					case ITEMWIDGET:
						widget = new GenericItemWidget();
						break;
					case LABEL:
						widget = new GenericLabel();
						index = attributes.getIndex("text");
						if (index != -1) ((GenericLabel) widget).setText(attributes.getValue(index));
						index = attributes.getIndex("text-color");
						if (index != -1) ((GenericLabel) widget).setTextColor(new Color(attributes.getValue(index)));
						index = attributes.getIndex("shadow");
						if (index != -1) ((GenericLabel) widget).setShadow(attributes.getValue(index).equalsIgnoreCase("true") ? true : false);
						index = attributes.getIndex("resize");
						if (index != -1) ((GenericLabel) widget).setResize(attributes.getValue(index).equalsIgnoreCase("true") ? true : false);
						index = attributes.getIndex("auto");
						if (index != -1) ((GenericLabel) widget).setAuto(attributes.getValue(index).equalsIgnoreCase("true") ? true : false);
						index = attributes.getIndex("scale");
						if (index != -1) ((GenericLabel) widget).setScale(Float.valueOf(attributes.getValue(index)));
						break;
					case LISTWIDGET:
						widget = new GenericListWidget();
						break;
					case RADIOBUTTON:
						widget = new GenericRadioButton();
						index = attributes.getIndex("selected");
						if (index != -1) ((GenericRadioButton) widget).setSelected(attributes.getValue(index).equalsIgnoreCase("true") ? true : false);
						index = attributes.getIndex("group");
						if (index != -1) ((GenericRadioButton) widget).setGroup(Integer.valueOf(attributes.getValue(index)));
						break;
					case SLIDER:
						widget = new GenericSlider();
						index = attributes.getIndex("shadow");
						if (index != -1) ((GenericSlider) widget).setShadow(attributes.getValue(index).equalsIgnoreCase("true") ? true : false);
						index = attributes.getIndex("resize");
						if (index != -1) ((GenericSlider) widget).setResize(attributes.getValue(index).equalsIgnoreCase("true") ? true : false);
						index = attributes.getIndex("auto");
						if (index != -1) ((GenericSlider) widget).setAuto(attributes.getValue(index).equalsIgnoreCase("true") ? true : false);
						index = attributes.getIndex("scale");
						if (index != -1) ((GenericSlider) widget).setScale(Float.valueOf(attributes.getValue(index)));
						index = attributes.getIndex("text");
						if (index != -1) ((GenericSlider) widget).setText(attributes.getValue(index));
						index = attributes.getIndex("text-color");
						if (index != -1) ((GenericSlider) widget).setTextColor(new Color(attributes.getValue(index)));
						index = attributes.getIndex("slider-position");
						if (index != -1) ((GenericSlider) widget).setSliderPosition(Float.valueOf(attributes.getValue(index)));
						break;
					case SLOT:
						widget = new GenericSlot();
						
						break;
					case TEXTFIELD:
						widget = new GenericTextField();
						break;
					case TEXTURE:
						widget = new GenericTexture();
						index = attributes.getIndex("draw-alpha-channel");
						if (index != -1) ((GenericTexture) widget).setDrawAlphaChannel(attributes.getValue(index).equalsIgnoreCase("true") ? true : false);
						index = attributes.getIndex("offset-left");
						if (index != -1) ((GenericTexture) widget).setLeft(Integer.valueOf(attributes.getValue(index)));
						index = attributes.getIndex("offset-top");
						if (index != -1) ((GenericTexture) widget).setTop(Integer.valueOf(attributes.getValue(index)));
						index = attributes.getIndex("url");
						if (index != -1) ((GenericTexture) widget).setUrl(attributes.getValue(index));
						break;
					}
					index = attributes.getIndex("height");
					if (index != -1) return;
					else widget.setHeight(Integer.getInteger(attributes.getValue(index)));
					index = attributes.getIndex("width");
					if (index != -1) widget.setWidth(Integer.getInteger(attributes.getValue(index)));
					index = attributes.getIndex("xPos");
					if (index != -1) widget.setX(Integer.getInteger(attributes.getValue(index)));
					index = attributes.getIndex("yPos");
					if (index != -1) widget.setY(Integer.getInteger(attributes.getValue(index)));
					index = attributes.getIndex("anchor");
					if (index != -1) widget.setAnchor(WidgetAnchor.valueOf(attributes.getValue(index).toUpperCase()));
					index = attributes.getIndex("tooltip");
					if (index != -1) widget.setTooltip(attributes.getValue(index));
					widgets.put(id, widget);
				}
				if (qName.equalsIgnoreCase("container")) {
					GenericContainer container = new GenericContainer();
					String id = "Element-" + widgets.size();
					int index = attributes.getIndex("id");
					if (index != -1) id = attributes.getValue(index);	
					index = attributes.getIndex("height");
					if (index != -1) return;
					else container.setHeight(Integer.getInteger(attributes.getValue(index)));
					index = attributes.getIndex("width");
					if (index != -1) container.setWidth(Integer.getInteger(attributes.getValue(index)));
					index = attributes.getIndex("xPos");
					if (index != -1) container.setX(Integer.getInteger(attributes.getValue(index)));
					index = attributes.getIndex("yPos");
					if (index != -1) container.setY(Integer.getInteger(attributes.getValue(index)));
					index = attributes.getIndex("anchor");
					if (index != -1) container.setAnchor(WidgetAnchor.valueOf(attributes.getValue(index).toUpperCase()));
					index = attributes.getIndex("align");
					if (index != -1) container.setAlign(WidgetAnchor.valueOf(attributes.getValue(index).toUpperCase()));
					index = attributes.getIndex("layout");
					if (index != -1) container.setLayout(ContainerType.valueOf(attributes.getValue(index).toUpperCase()));
					index = attributes.getIndex("tooltip");
					if (index != -1) container.setTooltip(attributes.getValue(index));
					widgets.put(id, container);
				}
			}
		};
	}
	
	public String getScreenId() {
		return id;
	}
	
	public Widget getElementById(String id) {
		return widgets.get(id);
	}
	
	public String getParent(String uri) {
		return "";
	}
}
