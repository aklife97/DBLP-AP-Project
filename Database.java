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
}
class Handler extends DefaultHandler{
	private boolean bArticle = false;
	private boolean bInproceedings = false;
	private boolean bProceedings = false;
	private boolean bBook = false;
	private boolean bIncollection = false;
	private boolean bPHDThesis = false;
	private boolean bMastersThesis = false;
	private boolean bWWW = false;
	private HashMap<String, boolean> pubs = new HashMap<String, boolean>();
	public Handler(){
		pubs.put("article",bArticle);
		pubs.put("inproceedings", bInproceedings);
		pubs.put("proceedings", bProceedings);
		pubs.put("book", bBook);
		pubs.put("incollection", bIncollection);
		pubs.put("phdthesis", bPHDThesis);
		pubs.put("mastersthesis", bMastersThesis);
		pubs.put("www", bWWW);
	}
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
		// if (qName.equalsIgnoreCase("article")){
		for (String key : pubs.keySet()){
			if (qName.equalsIgnoreCase(key)){
				pubs.get(key) = true;
			}
		}
	}
	public void endElement(String uri, String localName, String qName) throws SAXException{
		for (String key : pubs.keySet()){
			if (qName.equalsIgnoreCase(key)){
				pubs.get(key) = true;
			}
		}
	}
	public void characters(char c[], int start, int length) throws SAXException{
		
	}
}