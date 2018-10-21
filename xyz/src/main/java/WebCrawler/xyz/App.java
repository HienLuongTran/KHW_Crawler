package WebCrawler.xyz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
    	BufferedReader dataIn = new BufferedReader(new
                InputStreamReader( System.in) );
    	
    	String url = "";
    	String keyword ="Free Java";
    	System.out.println("Input URL: ");
    	url = dataIn.readLine();
    	Consumer consumer = new Consumer(url);
    	//consumer.setCrawlUrl(url);
    	consumer.Start();
    	//consumer.getArticles(keyword);
    	//consumer.writeToFile("luong.html");
    	//System.out.println("HEllo");
    }
    
}
