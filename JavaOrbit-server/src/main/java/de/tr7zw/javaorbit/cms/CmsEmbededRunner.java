/*package de.tr7zw.javaorbit.cms;

import java.io.File;
import java.io.IOException;
import java.net.BindException;
import java.nio.charset.StandardCharsets;

import com.caucho.resin.HttpEmbed;
import com.caucho.resin.ResinEmbed;
import com.caucho.resin.WebAppEmbed;
import com.google.common.io.Files;

public class CmsEmbededRunner {

	ResinEmbed resin = new ResinEmbed();
	
	
	public void startServer() throws BindException{
	    HttpEmbed http = new HttpEmbed(8181);
	    resin.addPort(http);

	    File htdocs = new File("htdocs");
	    if(!htdocs.exists()) {
	    	htdocs.mkdirs();
	    	File index = new File(htdocs, "index.php");
	    	try {
				Files.write("<?php echo('Hello World!'); ?>".getBytes(StandardCharsets.UTF_8), index);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    WebAppEmbed webApp = new WebAppEmbed("/", "./htdocs");
	    resin.addWebApp(webApp);

	    resin.start();
	}
	
}
*/