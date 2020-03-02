package br.com.logique.map.plugin01;

import br.com.logique.map.api.MapExtension;
import org.pf4j.Extension;
import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;

public class Plugin1 extends Plugin  {

    /**
     * Constructor to be used by plugin manager for plugin instantiation.
     * Your plugins have to provide constructor with this exact signature to
     * be successfully loaded by manager.
     *
     * @param wrapper
     */
    public Plugin1(PluginWrapper wrapper) {
        super(wrapper);
    }


    @Extension
    public static class Plugin1Extension implements MapExtension {

        @Override
        public String run() {
            return "Hello from plugin v4";
        }
    }

}
