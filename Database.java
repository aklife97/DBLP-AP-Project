/**
*@author Abhinav Khattar 2015120, Tushar Arora 2015107
*/
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.util.*;
import java.io.*;
public class Database{
	private SAXParser parser;
	public Database(String filename){
		try{
			SAXParserFactory factory = SAXParserFactory.newInstance();
			parser = factory.newSAXParser();
			Handler handle = new Handler();
			parser.parse(filename, handle);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		// XMLInputFactory inputFactory = XMLInputFactory.newInstance(); 
		// inputFactory.setProperty(JDK_ENTITY_EXPANSION_LIMIT, "0");
		System.setProperty("jdk.xml.entityExpansionLimit", "0");
		Database d = new Database("dblp.xml");
		System.clearProperty("jdk.xml.entityExpansionLimit");
	}
}
class Handler extends DefaultHandler{
	private boolean bAuthor = false;
	private boolean bTitle = false;
	private boolean bPages = false;
	private boolean bYear = false;
	private boolean bVolume = false;
	private boolean bJournal = false;
	private boolean bBookTitle = false;
	private boolean bURL = false;
	private String author;
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
		if (qName.equalsIgnoreCase("author")){
			bAuthor = true;
		}
		if (qName.equalsIgnoreCase("title")){
			bTitle = true;
		}
		if (qName.equalsIgnoreCase("pages")){
			bPages = true;
		}
		if (qName.equalsIgnoreCase("year")){
			bYear = true;
		}
		if (qName.equalsIgnoreCase("volume")){
			bVolume = true;
		}
		if (qName.equalsIgnoreCase("journal")){
			bJournal = true;
		}
		if (qName.equalsIgnoreCase("booktitle")){
			bBookTitle = true;
		}
		if (qName.equalsIgnoreCase("url")){
			bURL = true;
		}
	}
	public void endDocument() throws SAXException{
		System.out.println(author);
	}
	public void characters(char ch[], int start, int length) throws SAXException{
		if (bAuthor){
			author = new String(ch, start, length);
			bAuthor = false;
		}
		else if (bTitle){
			new String(ch, start, length);
			bTitle = false;
		}
		else if (bPages){
			new String(ch, start, length);
			bPages = false;
		}
		else if (bYear){
			new String(ch, start, length);
			bYear = false;
		}
		else if (bVolume){
			new String(ch, start, length);
			bVolume = false;
		}
		else if (bJournal){
			new String(ch, start, length);
			bJournal = false;
		}
		else if (bBookTitle){
			new String(ch, start, length);
			bBookTitle = false;
		}
		else if (bURL){
			new String(ch, start, length);
			bURL = false;
		}
	}
}