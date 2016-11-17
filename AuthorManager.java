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
				public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
					if (qName.equalsIgnoreCase("author")){
						bAuthor = true;
					}
				}
				public void endElement(String uri, String localName, String qName) throws SAXException{
					if (qName.equalsIgnoreCase("author")){
						author = author + ",";
						bAuthor = false;
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