package WebCrawler.xyz;

import java.util.regex.Pattern;

public class HtmlTools {
	private final static Pattern FILTERS = Pattern.compile(
			".*(\\.(css|js|gif|jpg" + "|png|mp3|mp3|zip|gz))$");
	
	public static boolean shouldVisit(String url) {
		return !FILTERS.matcher(url).matches();
	}
	
	public static boolean isTrueDomain(String inUrl,String domainUrl){
		if(inUrl.startsWith(domainUrl))
			return true;
		return false;
	}
	public static String fixUrl(String inUrl, Domain domain) {
		String url ="";
		
		if(!inUrl.startsWith(domain.getDomainUrl())) {
			if(!inUrl.startsWith("http")) {
				if(inUrl.startsWith("/")) {
					url = domain.getDomainUrl().concat(inUrl);
				} else
					url = domain.getDomainUrl().concat("/"+inUrl);
			} else 
				url = inUrl;
		}
		
		// remove trailing
		if(inUrl.endsWith("/")) {
			url = url.substring(0, url.length()-1);
		}
		// remove bookmarks
		if(inUrl.contains("#")) {
			url = url.substring(0, url.indexOf("#")-1);	
		}
		return url;
	}
	
	public static boolean checkLength(String url) {
		if(url.length() >= 100) {
			return false;
		}
		return true;
	}
}
