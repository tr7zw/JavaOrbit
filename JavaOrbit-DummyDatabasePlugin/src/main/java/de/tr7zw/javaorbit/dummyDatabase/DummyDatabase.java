package de.tr7zw.javaorbit.dummyDatabase;

import java.util.logging.Logger;

import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;

import com.google.inject.Inject;

public class DummyDatabase extends Plugin {

	@Inject
	private Logger log;
	
	public DummyDatabase(PluginWrapper wrapper) {
		super(wrapper);
	}

	@Override
	public void start() {
		log.info("Starting...");
	}

	@Override
	public void stop() {
		log.info("Stopped...");
	}

}
