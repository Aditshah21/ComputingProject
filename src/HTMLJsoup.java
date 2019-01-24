
import java.io.*;
import org.jsoup.*;
import org.jsoup.nodes.Document;

public class HTMLJsoup
{
	
	public HTMLJsoup(String url, int count) throws IOException
	{
		org.jsoup.nodes.Document doc = Jsoup.connect(url).timeout(0).ignoreHttpErrors(true).userAgent("Chrome").get();
		
		String text = doc.text();
		PrintWriter out = new PrintWriter("urls_list/text/"+count+".txt"); //also save web url in parsed text file
		out.println(url+" "+text);
		out.close();
		

	}

	public static void main(String[] args) throws IOException
	{
		
	}
}
