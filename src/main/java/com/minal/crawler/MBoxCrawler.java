package com.minal.crawler;

import java.net.URLDecoder;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class MBoxCrawler extends Crawler {
	
	public MBoxCrawler(String cssQuery, String value, String attr) {
		super(cssQuery, value, attr);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void process(Elements elements) {
		
		for (Element page : elements) {
			String value = page.attr(this.attr);
			
			if (!value.equals(this.value)) {
				continue;
			}
			
			Node node = page.childNode(0);
			String href = node.attr("abs:href");
			href = URLDecoder.decode(href);
			this.nextInChain.crawl(href);
		}
	}
}