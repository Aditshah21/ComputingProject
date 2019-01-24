import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HTMLJsoup1 {
	
	public HTMLJsoup1(String url, int count) throws IOException{

	File myfile=new File("C:\\Users\\Adit Shah\\eclipse-workspace\\Advanced Computing Project\\W3C Web Pages");
	File[] folder=myfile.listFiles();
	
	for(int i=0;i<folder.length;i++)
		{
			Document doc=Jsoup.parse(folder[i],"UTF-8");
			String text = doc.text();
			int name=folder[i].getName().lastIndexOf(".");
			String txtName=folder[i].getName().substring(0,name);
			PrintWriter out = new PrintWriter("C:\\Users\\Adit Shah\\eclipse-workspace\\\\Advanced Computing Project\\Texts\\"+txtName+".txt");
			out.println(text);
			out.close();
		}

	}

	public static void main(String[] args) throws IOException
	{
		
	}

}
