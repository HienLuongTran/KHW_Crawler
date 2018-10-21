package WebCrawler.xyz;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Consumer {
	private static int MAX_DEPTH = 7;
	private String domainURL;
	private Domain domain;
	private HashSet<String> links;
	Map<Integer, String> map_Url_Depth;
	private List<List<String>> articles;
	
	public Consumer(String URLDomain) {
		domain = new Domain("",URLDomain);
		links  = new HashSet<String>();
		articles = new ArrayList<>();
		map_Url_Depth = new HashMap<Integer, String>();
	}
	
	public void Start() {
		Crawl(domain.getDomainUrl(),0);
	}

	public void Crawl(String URL, int depth) {
		if(!links.contains(URL) &&  (depth < MAX_DEPTH) && HtmlTools.checkLength(URL)
								&& HtmlTools.shouldVisit(URL)
								&& HtmlTools.isTrueDomain(URL, domainURL)) {
			System.out.println("Depth : "+ depth+ "["+ URL + "]");
			try {
				//URL = HtmlTools.fixUrl(URL,domain);
				links.add(URL);
				map_Url_Depth.put(depth,URL);
				Document doc = Jsoup.connect(URL).get();
				Elements newhrefs = doc.select("a");
				depth++;
				for (Element newhref : newhrefs) {
					Crawl(newhref.attr("abs:href"),depth);
					//String href = newhref.attr("href").trim();
					//href = HtmlTools.fixUrl(href, domain);
					//links.add(href);
				}
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}

		}
	}

	public void getArticles(String keyword) {
        links.forEach(x -> {
            Document document;
            try {
                document = Jsoup.connect(x).get();
                Elements articleLinks = document.select("a");
                for (Element article : articleLinks) {
                    //Only retrieve the titles of the articles that contain Java 8
                    if (article.text().matches("^.*?"+keyword+".*$")) {
                        ArrayList<String> temporary = new ArrayList<>();
                        temporary.add(article.text()); //The title of the article
                        temporary.add(article.attr("abs:href")); //The URL of the article
                        articles.add(temporary);
                    }
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        });
	}
	
	 public void writeToFile(String filename) {
	        FileWriter writer;
	        try {
	            writer = new FileWriter(filename);
	            articles.forEach(a -> {
	                try {
	                    String temp = "- Title: " + a.get(0) + " (link: " + a.get(1) + ")\n";
	                    //display to console
	                    System.out.println(temp);
	                    //save to file
	                    writer.write(temp);
	                } catch (IOException e) {
	                    System.err.println(e.getMessage());
	                }
	            });
	            writer.close();
	        } catch (IOException e) {
	            System.err.println(e.getMessage());
	        }
	    }

	public String getDomainURL() {
		return domainURL;
	}

	public void setDomainURL(String domainURL) {
		this.domainURL = domainURL;
	}
}
