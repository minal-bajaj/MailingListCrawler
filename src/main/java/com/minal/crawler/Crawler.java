package com.minal.crawler;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public abstract class Crawler {
	
	protected String cssQuery;
	protected String value;
	
	protected String attr;
	
	protected Crawler nextInChain;
	
	public Crawler(String cssQuery, String value, String attr) {
		this.cssQuery = cssQuery;
		this.value = value;
		this.attr = attr;
	}
	
	public void setNextInChain(Crawler nextInChain) {
		this.nextInChain = nextInChain;
	}
	
	public void crawl(String url) {
        Document document;
		try {
			document = Jsoup.connect(url).get();
			
			Elements elements = document.select(this.cssQuery);
	        
	        process(elements);
		} catch (IOException e) {
		}
	}
	
	public abstract void process(Elements elements);
}