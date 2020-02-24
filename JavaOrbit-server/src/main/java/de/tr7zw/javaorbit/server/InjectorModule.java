package de.tr7zw.javaorbit.server;

import java.io.File;
import java.nio.file.Path;

import org.pf4j.ExtensionFactory;
import org.pf4j.PluginFactory;
import org.pf4j.PluginManager;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import de.tr7zw.javaorbit.server.plugins.InjectingExtensionFactory;
import de.tr7zw.javaorbit.server.plugins.InjectingPluginFactory;
import de.tr7zw.javaorbit.server.plugins.InjectingPluginManager;

public class InjectorModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(PluginManager.class).to(InjectingPluginManager.class);
		bind(PluginFactory.class).to(InjectingPluginFactory.class);
		bind(ExtensionFactory.class).to(InjectingExtensionFactory.class);
		bind(Path.class).annotatedWith(Names.named("plugins")).toInstance(new File("plugins").toPath());
	}

}
