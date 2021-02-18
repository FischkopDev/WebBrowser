/**
 * @author 
 * 	Timo Skrobanek
 * 
 * @date
 * 	27.03.2019
 * 
 * @description
 * 	copied example from 
 * 	https://dzone.com/articles/add-jar-file-java-load-path
 */
package de.home_skrobanek.browser.plugins.loader;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class PluginFileLoader extends URLClassLoader
{
    public PluginFileLoader (URL[] urls)
    {
        super (urls);
    }
    public void addFile (String path) throws MalformedURLException
    {
        String urlPath = "jar:file:\\" + path + "!/";
        addURL (new URL (urlPath));
    }

}
