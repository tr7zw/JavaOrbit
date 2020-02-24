package de.tr7zw.javaorbit.server.plugins;

import java.nio.file.Path;

import org.pf4j.ExtensionFactory;
import org.pf4j.JarPluginManager;
import org.pf4j.PluginDescriptorFinder;
import org.pf4j.PluginFactory;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;


@Singleton
public class InjectingPluginManager extends JarPluginManager{

	private PluginFactory pluginFactory;
	private ExtensionFactory extensionFactory;
	private PluginDescriptorFinder pluginDescriptorFinder = new YmlPluginDescriptorFinder();
	
	@Inject
	public InjectingPluginManager(@Named(value = "plugins") Path pluginsRoot, PluginFactory pluginFactory, ExtensionFactory extensionFactory) {
		super(pluginsRoot);
		this.pluginFactory = pluginFactory;
		this.extensionFactory = extensionFactory;
		initialize();
	}


	@Override
	protected PluginFactory createPluginFactory() {
		return pluginFactory;
	}

	@Override
	protected ExtensionFactory createExtensionFactory() {
		return extensionFactory;
	}

	@Override
	protected PluginDescriptorFinder createPluginDescriptorFinder() {
		return pluginDescriptorFinder;
	}

}




