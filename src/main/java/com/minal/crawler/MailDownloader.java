package com.minal.crawler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MailDownloader extends Crawler {
	
	private String outputFolder;
	private int lastId;

	public MailDownloader(String cssQuery, String value, String attr, String outputFolder) {
		super(cssQuery, value, attr);
		this.outputFolder = outputFolder;
		
		File outputDir = new File(outputFolder);
		outputDir.mkdirs();
		
		this.lastId = 0;
	}
	
	@Override
	public void process(Elements elements) {
		
		for (Element element : elements) {
			
			String value = element.attr(this.attr);
			
			if (value.equals(this.value)) {
				Element parent = element.parent();
				save(parent);
			}
		}
	}
	
	private void save(Element element) {
		
		File mailFile = new File(outputFolder + File.separator + lastId + ".mail");
		
		BufferedWriter writer = null;
		
		try {
			writer = new BufferedWriter(new FileWriter(mailFile));
			writer.write(element.toString());
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) { }
			}
		}
		
		this.lastId++;
	}
}