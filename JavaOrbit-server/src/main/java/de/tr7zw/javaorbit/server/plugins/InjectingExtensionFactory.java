package de.tr7zw.javaorbit.server.plugins;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pf4j.DefaultExtensionFactory;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

@Singleton
public class InjectingExtensionFactory  extends DefaultExtensionFactory {

    private final List<String> extensionClassNames;

    private Map<String, Object> cache;
    
	@Inject 
	private Injector injector;

    public InjectingExtensionFactory() {
        this.extensionClassNames = new ArrayList<String>();

        cache = new HashMap<>(); // simple cache implementation
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T create(Class<T> extensionClass) {
        String extensionClassName = extensionClass.getName();
        if (cache.containsKey(extensionClassName)) {
            return (T) cache.get(extensionClassName);
        }

        T extension = super.create(extensionClass);
        if (extensionClassNames.isEmpty() || extensionClassNames.contains(extensionClassName)) {
        	injector.injectMembers(extension);
            cache.put(extensionClassName, extension);
        }

        return extension;
    }

}
