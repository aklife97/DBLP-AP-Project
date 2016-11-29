/**
*@file AuthorManager.java
*File related to entity resolution
*@author Abhinav Khattar 2015120
*@author Tushar Arora 2015107
*/
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.util.*;
import java.io.*;
/**AuthorManager provides us a way to resolve Authors i.e. Does entity resolution work*/
public class AuthorManager{
	private static HashMap<String, Author> authorMap = new HashMap<String, Author>();
	private static ArrayList<Author> authorsList = new ArrayList<Author>();
	private static boolean mapCreated = false;
	private static boolean countAdded = false;
	private static String filename;
	public boolean isMapCreated(){
		return mapCreated;
	}
	public boolean isCountAdded(){
		return countAdded;
	}
	public static void printData(){
		for (Map.Entry m : authorMap.entrySet()){
			System.out.println(m.getKey()+" "+((Author)m.getValue()).getName());
		}
	}
	public static void addFile(String _filename){
		filename = _filename;
		mapCreated = false;
		countAdded = false;
	}
	/**Given the name of an author it returns a unique Author object, hence reolving the entity*/
	public static Author resolveAuthor(String name){
		Author value = 	authorMap.get(name);
		if (value == null){
			value = new Author(name);
			authorMap.put(name, value);
		}
		return value;
	}
	public static ArrayList<Author> getGreaterK(int k){
		if (!countAdded){
			AuthorManager.addCount();
		}
		ArrayList<Author> au = new ArrayList<Author>();
		for (Author a : authorsList){
			if (a.getCount() >=k){
				au.add(a);
			}
		}
		return au;
	}
	/**Creates a map of Author name to object*/
	public static void createMap(){
		if (filename == null)
			return;
		try{
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			DefaultHandler handle = new DefaultHandler(){
				private boolean bAuthor = false, bTitle = false, bHomePage = false;
				private String author, title;
				public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
					if (qName.equalsIgnoreCase("author"))
						bAuthor = true;
					else if (qName.equalsIgnoreCase("title"))
						bTitle = true;
				}
				public void endElement(String uri, String localName, String qName) throws SAXException{
					if (qName.equalsIgnoreCase("author")){
						author = author + ",";
						bAuthor = false;
					}
					else if (qName.equalsIgnoreCase("title"))
						bTitle = false;
					if (qName.equalsIgnoreCase("article") || qName.equalsIgnoreCase("inproceedings") || qName.equalsIgnoreCase("proceedings") || qName.equalsIgnoreCase("book") || qName.equalsIgnoreCase("incollection") || qName.equalsIgnoreCase("phdthesis") || qName.equalsIgnoreCase("mastersthesis") || qName.equalsIgnoreCase("www")){
						if (author != null)
							author = author.substring(0, author.length()-1);
						if (author != null && title != null && title.equalsIgnoreCase("Home Page")){
							String[] authors = author.split(",");
							Author au = new Author(authors[0]);
							authorsList.add(au);
							for (String a : authors){
								authorMap.put(a.toLowerCase(), au);
							}
						}
						author = title = null;
					}
				}
				public void characters(char ch[], int start, int length) throws SAXException{
					if (bAuthor){
						if (author != null) author = author + new String(ch, start, length);
						else author = (new String(ch, start, length));
					}
					else if (bTitle) title = new String(ch, start, length);
				}
			};
			parser.parse(filename, handle);
			mapCreated = true;
		}
		catch(Exception e){e.printStackTrace();}
	}
	/**Designates each Author with his respective number of Publications*/
	public static void addCount(){
		if (!mapCreated)
			AuthorManager.createMap();
		try{
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			DefaultHandler handle = new DefaultHandler(){
				private boolean bAuthor = false;
				private boolean bTitle = false;
				private String author;
				private String title;
				public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
					if (qName.equalsIgnoreCase("author"))
						bAuthor = true;
					else if (qName.equalsIgnoreCase("title"))
						bTitle = true;
				}
				public void endElement(String uri, String localName, String qName) throws SAXException{
					if (qName.equalsIgnoreCase("author")){
						author = author + ",";
						bAuthor = false;
					}
					else if (qName.equalsIgnoreCase("title"))
						bTitle = false;
					if (qName.equalsIgnoreCase("article") || qName.equalsIgnoreCase("inproceedings") || qName.equalsIgnoreCase("proceedings") || qName.equalsIgnoreCase("book") || qName.equalsIgnoreCase("incollection") || qName.equalsIgnoreCase("phdthesis") || qName.equalsIgnoreCase("mastersthesis") || qName.equalsIgnoreCase("www")){
						if (author != null)
							author = author.substring(0, author.length()-1);
						if (author != null && title != null && !title.equalsIgnoreCase("Home Page")){
							String[] authors = author.split(",");
							for (String a : authors){
								Author au = authorMap.get(a.toLowerCase());
								au.increaseCount();
							}
						}
						author = title = null;
					}
				}
				public void characters(char ch[], int start, int length) throws SAXException{
					if (bAuthor){
						if (author != null) author = author + new String(ch, start, length);
						else author = (new String(ch, start, length));
					}
					else if (bTitle) title = new String(ch, start, length);
				}
			};
			parser.parse(filename, handle);
			countAdded = true;
		}
		catch(Exception e){e.printStackTrace();}
	}
}