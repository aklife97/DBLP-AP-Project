/**
*@file Database.java
*This file Contains all the Parsing necessities
*@author Abhinav Khattar 2015120
*@author Tushar Arora 2015107
*/
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.util.*;
import java.io.*;
/**This Class helps extracting relevant data*/
public class Database{
	private SAXParser parser;
	private Query13 query;
	public Database(String filename, Query13 _query){
		try{
			query = _query;
			SAXParserFactory factory = SAXParserFactory.newInstance();
			parser = factory.newSAXParser();
			Handler handle = new Handler(this);
			parser.parse(filename, handle);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void check(DataRecords d){
		query.check(d);
	}
}
/**This class in the custom handler required for Parsing */
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
		if (qName.equalsIgnoreCase("author") || qName.equalsIgnoreCase("editor")){
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
				DataRecords d = new DataRecords(author, title, pages, volume, year, journal, booktitle, url);
				dbase.check(d);
			}
			author = title = pages = year = volume = journal = booktitle = url = null;
		}
		else if (qName.equalsIgnoreCase("author") || qName.equalsIgnoreCase("editor")){
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
		}
		else if (bBookTitle){
			if (booktitle == null)
				booktitle = new String(ch, start, length);
			else
				booktitle += new String(ch, start, length);
		}
		else if (bURL){
			if (url == null)
				url = new String(ch, start, length);
			else
				url += new String(ch, start, length);
		}
	}
}