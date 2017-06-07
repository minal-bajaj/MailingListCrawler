package com.minal.web_crawler;

import com.minal.crawler.MBoxCrawler;
import com.minal.crawler.MailDownloader;
import com.minal.crawler.MailingListCrawler;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
    	
    	if (args.length != 3) {
    		System.err.println("Mailing list URL, year and output folder are required as arguments");
    		System.exit(-1);
    	}
    	
    	String mailingListURL = args[0];
    	String year = args[1];
    	String outputFolder = args[2];
    	
        MailingListCrawler mailingListCrawler = new MailingListCrawler("a[href]", year, "abs:href");
        MBoxCrawler mBoxCrawler = new MBoxCrawler("td[class]", "subject", "class");
        MailDownloader mailDownloader = new MailDownloader("tr[class]", "from", "class", outputFolder);
        
        mailingListCrawler.setNextInChain(mBoxCrawler);
        mBoxCrawler.setNextInChain(mailDownloader);
        
        mailingListCrawler.crawl(mailingListURL);
    }
}
