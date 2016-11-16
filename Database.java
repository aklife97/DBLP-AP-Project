/**
*@author Abhinav Khattar 2015120, Tushar Arora 2015107
*/
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.util.*;
import java.io.*;
class DataRecords{
	private String author;
	private String title;
	private String pages;
	private String year;
	private String volume;
	private String journal;
	private String booktitle;
	private String url;
	public DataRecords(String _author, String _title, String _pages, String _voulme, String _year, String _journal, String _booktitle, String _url){
		author = _author;
		title = _title;
		pages = _pages;
		volume = _voulme;
		year = _year;
		journal = _journal;
		booktitle = _booktitle;
		url = _url;
	}
	public String getAuthor(){
		return author;
	}
	public String getTitle(){
		return title;
	}
	public String getPages(){
		return pages;
	}
	public String getVolume(){
		return volume;
	}
	public String getYear(){
		return year;
	}
	public String getJournal(){
		return journal;
	}
	public String getBookTitle(){
		return booktitle;
	}
	public String getURL(){
		return url;
	}
}
public class Database{
	private SAXParser parser;
	private ArrayList<DataRecords> dataRec = new ArrayList<DataRecords>();
	public Database(String filename){
		try{
			SAXParserFactory factory = SAXParserFactory.newInstance();
			parser = factory.newSAXParser();
			Handler handle = new Handler(this);
			parser.parse(filename, handle);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		this.printData();
	}
	public static void main(String[] args){
		System.setProperty("jdk.xml.entityExpansionLimit", "0");
		Database d = new Database("dblp.xml");
		d.printData();
		System.clearProperty("jdk.xml.entityExpansionLimit");
	}
	public void check(DataRecords d){
		if (d.getAuthor() !=null && d.getAuthor().equalsIgnoreCase("Arnold Schönhage")){
			dataRec.add(d);
		}
	}
	public void printData(){
		for (DataRecords d : dataRec){
			System.out.println(d.getTitle());
			System.out.println(d.getAuthor());
		}
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
	private String title;
	private String pages;
	private String year;
	private String volume;
	private String journal;
	private String booktitle;
	private String url;
	private Database dbase;
	public Handler(Database _dbase){
		dbase = _dbase;
	}
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
		if (qName.equalsIgnoreCase("author")){
			bAuthor = true;
		}
		else if (qName.equalsIgnoreCase("title")){
			bTitle = true;
		}
		else if (qName.equalsIgnoreCase("pages")){
			bPages = true;
		}
		else if (qName.equalsIgnoreCase("year")){
			bYear = true;
		}
		else if (qName.equalsIgnoreCase("volume")){
			bVolume = true;
		}
		else if (qName.equalsIgnoreCase("journal")){
			bJournal = true;
		}
		else if (qName.equalsIgnoreCase("booktitle")){
			bBookTitle = true;
		}
		else if (qName.equalsIgnoreCase("url")){
			bURL = true;
		}
	}
	public void endElement(String uri, String localName, String qName) throws SAXException{
		if (qName.equalsIgnoreCase("article") || qName.equalsIgnoreCase("inproceedings") || qName.equalsIgnoreCase("proceedings") || qName.equalsIgnoreCase("book") || qName.equalsIgnoreCase("incollection") || qName.equalsIgnoreCase("phdthesis") || qName.equalsIgnoreCase("mastersthesis") || qName.equalsIgnoreCase("www")){
			if (author != null)
				author = author.substring(0, author.length()-1);
			if (title == null || !title.equalsIgnoreCase("Home Page")){
				DataRecords d = new DataRecords(author, title, pages, year, volume, journal, booktitle, url);
				// System.out.println(author);
				dbase.check(d);
			}
			// else{
			// 	System.out.println(author);
			// }
			author = title = pages = year = volume = journal = booktitle = url = null;
		}
		else if (qName.equalsIgnoreCase("author")){
			author = author + ",";
			bAuthor = false;
		}
		else if (qName.equalsIgnoreCase("title")){
			bTitle = false;
		}
		else if (qName.equalsIgnoreCase("journal")){
			bJournal = false;
		}
		else if (qName.equalsIgnoreCase("booktitle")){
			bBookTitle = false;
		}
		else if (qName.equalsIgnoreCase("url")){
			bURL = false;
		}
	}
	// public void endDocument() throws SAXException{
	// 	System.out.println("here" + author);
	// }
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
			if (title == null)
				title = new String(ch, start, length);
			else
				title += new String(ch, start, length);
			// bTitle = false;
		}
		else if (bPages){
			pages = new String(ch, start, length);
			bPages = false;
		}
		else if (bYear){
			year = new String(ch, start, length);
			bYear = false;
		}
		else if (bVolume){
			volume = new String(ch, start, length);
			bVolume = false;
		}
		else if (bJournal){
			if (journal == null)
				journal = new String(ch, start, length);
			else
				journal += new String(ch, start, length);
			// bJournal = false;
		}
		else if (bBookTitle){
			if (booktitle == null)
				booktitle = new String(ch, start, length);
			else
				booktitle += new String(ch, start, length);
			// bBookTitle = false;
		}
		else if (bURL){
			if (url == null)
				url = new String(ch, start, length);
			else
				url += new String(ch, start, length);
			// bURL = false;
		}
	}
}