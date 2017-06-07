# MailingListCrawler
Description - This project downloads all the mails from given mailing list URL and year.<br>

This project uses jsoup (a  third party library) to collect xml document from any given URL. This is a maven project and all the dependecies are added in pom.xml. <br>

App.java is starting point of this application where it expects following arguments in respective order.<br>
        - Mailing list URL <br>
        - Year <br>
        - output folder to download mails. <br>
        
For example:
http://mail-archives.apache.org/mod_mbox/maven-users/ 2015 mails

This inputs are expected as program arguments. <br>

Few details about design - <br>
Crawler is an abstract base class which crawl a given URL. Concrete implementation of this Crawler class defines filtering criteria. These Crawler implementations follow chain of responsibility design pattern where each crawler knows about next in chain crawler. <br>

First crawler is MailingListCrawler and next for him is MBoxCrawler (crawler for page which contains mail for selected year and month). Last Crawler in chain is MailDownloader which crawl mail page and stores them in file). <br>
