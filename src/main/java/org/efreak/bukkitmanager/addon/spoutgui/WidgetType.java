package org.efreak.bukkitmanager.addon.spoutgui;

public enum WidgetType {

	/**
	 * The Button represents a Minecraft button with a label placed on it
	 */
	BUTTON,
	
	/**
	 * This defines a simple checkbox widget
	 */
	CHECKBOX,
	
	/**
	 * This is used to display any in-game entity on the screen
	 */
	ENTITYWIDGET,
	
	/**
	 * The ItemWidget allows you to display a block or item as it would be in the player's inventory
	 */
	ITEMWIDGET,
	
	/**
	 * the Label represents texts on the users screen
	 */
	LABEL,
	
	/**
	 * This defines a list of ListWidgetItems which can be scrolled into visibilty
	 */
	LISTWIDGET,
	
	/**
	 * This is a simple radio button, which does not know anything about any other radio buttons on the screen
	 */
	RADIOBUTTON,
	
	/**
	 * The Slider is a bar with which a user can set a value
	 */
	SLIDER,
	
	/**
	 * Represents one item slot where items can be put in and out
	 */
	SLOT,
	
	/**
	 * This is a box where the user can input a string
	 */
	TEXTFIELD,
	
	/**
	 * This allows an image to be downloaded and shown to the user
	 */
	TEXTURE
}
