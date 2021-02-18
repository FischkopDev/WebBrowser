package de.home_skrobanek.browser.plugins.loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

public class PluginLoader {
	
	private final File plugin = new File("/plugin/plugin.yml");
	private final URL urls[] = {};
	private final PluginFileLoader loader = new PluginFileLoader(urls);

	public PluginLoader(String packagePath) throws IOException{
		plugin.mkdir();
		plugin.createNewFile();
		
		loadPlugins(packagePath);
	}

	private void loadPlugins(String packagePath) throws IOException{
		FileReader fr = new FileReader(plugin);
		BufferedReader br = new BufferedReader(fr);
		
		String line = "";

		while ((line = br.readLine()) != null) {
			String[] split = line.split(",");
			
			for(int i = 0; i < split.length; i++) {
				try {
					loader.addFile(split[i]);//absoluter Pfad
					
					Class<?> c = loader.loadClass(packagePath);
					Constructor<?> con = c.getConstructor();
					Object obj = con.newInstance();
					c.getMethod("start").invoke(obj);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		br.close();
	}
}
