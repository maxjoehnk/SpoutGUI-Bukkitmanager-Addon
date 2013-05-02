package org.efreak.bukkitmanager.addon.spoutgui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.efreak.bukkitmanager.addon.spoutgui.screens.Screen;
import org.efreak.bukkitmanager.addons.BukkitmanagerAddon;
import org.efreak.bukkitmanager.language.Language;
import org.efreak.bukkitmanager.util.FileFilter;
import org.efreak.bukkitmanager.util.FileHelper;

public class SpoutGUIAddon extends BukkitmanagerAddon {

	private static List<Screen> screens;
	private static File spoutDir;
	
	@Override
	public void onLoad() {
		super.onLoad();
		name = "SpoutAddon";
	}
	
	@Override
	public void onEnable() {
		super.onEnable();
		//Setup Config
		config.update("Spout.Screens.Enabled", true);
		config.save();
	
		//Add Language Entries
		Language.addKey("en", "Spout.Screens.Loading", "Loading Custom Spout Screens...");
		Language.addKey("en", "Spout.Screens.Loaded", "%count% Spout Screens loaded!");
		
		screens = new ArrayList<Screen>();
		spoutDir = new File(FileHelper.getPluginDir(), "spout");
		spoutDir.mkdirs();
		File[] files;
		if (config.getBoolean("Spout.Screens.Enabled")) {
			io.sendConsole(io.translate("Spout.Screens.Loading"));
			files = spoutDir.listFiles(new FileFilter(".screen"));
			int i;
			for (i = 0; i < files.length; i++) screens.add(new Screen(files[i]));
			io.sendConsole(io.translate("Spout.Screens.Loaded").replaceAll("%count%", String.valueOf(i)));
		}
	}
}