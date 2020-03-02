package br.com.logique.map.pooling;

import br.com.logique.map.api.MapExtension;
import org.pf4j.DefaultPluginManager;
import org.pf4j.PluginManager;

import java.util.List;

public class MapPluginManager {

    private PluginManager pluginManager;

    public void loadPlugins(){
        System.setProperty("pf4j.pluginsDir", "map-pooling-manager/plugins");
        pluginManager = new DefaultPluginManager();
        pluginManager.loadPlugins();
        pluginManager.startPlugins();
    }

    public void unloadPlugins(){
        pluginManager.getPlugins().forEach(p -> {
            pluginManager.stopPlugin(p.getPluginId());
            pluginManager.unloadPlugin(p.getPluginId());
        });
    }

    public void executeAll(){
        List<MapExtension> plugins = pluginManager.getExtensions(MapExtension.class);
        plugins.forEach(c -> System.out.println(c.run()));
    }

    public static void main(String[] args) throws InterruptedException {

        while (true){
            MapPluginManager mapPluginManager = new MapPluginManager();
            mapPluginManager.loadPlugins();
            mapPluginManager.executeAll();
            mapPluginManager.unloadPlugins();
            Thread.sleep(5000);
        }

    }

}
