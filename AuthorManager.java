/**
*@author Abhinav Khattar 2015120, Tushar Arora 2015107
*/
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.util.*;
import java.io.*;
public class AuthorManager{
	private static HashMap<String, Author> authorMap = new HashMap<String, Author>();
	public static void createMap(String filename){
		try{
			SAXParserFactory factory = SAXParserFactory.newInstance();
			parser = factory.newSAXParser();
			DefaultHandler handle = new DefaukltHandler(){
				private boolean bAuthor = false;
				private boolean bTitle = false;
				private boolean bHomePage = false;
				private String author;
				private String title;
				public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
					if (qName.equalsIgnoreCase("author")){
						bAuthor = true;
					}
					else if (qName.equalsIgnoreCase("title")){
						bTitle = true;
					}
				}
				public void endElement(String uri, String localName, String qName) throws SAXException{
					if (qName.equalsIgnoreCase("author")){
						author = author + ",";
						bAuthor = false;
					}
					else if (qName.equalsIgnoreCase("title")){
						bTitle = false;
					}
					if (qName.equalsIgnoreCase("article") || qName.equalsIgnoreCase("inproceedings") || qName.equalsIgnoreCase("proceedings") || qName.equalsIgnoreCase("book") || qName.equalsIgnoreCase("incollection") || qName.equalsIgnoreCase("phdthesis") || qName.equalsIgnoreCase("mastersthesis") || qName.equalsIgnoreCase("www")){
						if (author != null)
							author = author.substring(0, author.length()-1);
						if (title != null && title.equalsIgnoreCase("Home Page")){
							
						}
						author = title = null;
					}
				}
				public void characters(char ch[], int start, int length) throws SAXException{
					if (bAuthor){
						if (author != null){
							author = author + new String(ch, start, length);
						}
						else{
							author = (new String(ch, start, length));
						}
					}
					else if (bTitle){
						title = new String(ch, start, length);
					}
				}
			}
			parser.parse(filename, handle);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}