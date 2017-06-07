package com.minal.crawler;

import java.util.regex.Pattern;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MailingListCrawler extends Crawler {
	
	String pattern = ".*%s[0-1][0-9].mbox/date";

	private Pattern valuePattern = null;
	
	public MailingListCrawler(String cssQuery, String value, String attr) {
		super(cssQuery, value, attr);
		valuePattern = Pattern.compile(String.format(pattern, value));
	}
	
	@Override
	public void process(Elements elements) {
		
		for (Element element : elements) {
        	
        	String url = element.attr(this.attr);
        	
        	if (!valuePattern.matcher(url).matches()) {
        		continue;
        	}
        	
        	this.nextInChain.crawl(url);
        }
	}
}