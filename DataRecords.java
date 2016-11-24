/**
*@author Abhinav Khattar 2015120, Tushar Arora 2015107
*/
import java.util.*;
import java.io.*;
public class DataRecords implements Comparable<DataRecords>{
	private String author;
	private String title;
	private String pages;
	private int year;
	private String volume;
	private String journal;
	private String booktitle;
	private String url;
	private int stringMatch;
	public DataRecords(String _author, String _title, String _pages, String _volume, String _year, String _journal, String _booktitle, String _url){
		author = _author;
		title = _title;
		pages = _pages;
		volume = _volume;
		try{	
			year = Integer.parseInt(_year);
		}
		catch (Exception e){
			year = 0;
		}
		journal = _journal;
		booktitle = _booktitle;
		url = _url;
		stringMatch=0;
	}
	public int compareTo(DataRecords d2){
		if (d2.getYear() > year){
			return 1;
		}
		else if (d2.getYear() == year){
			return 0;
		}
		return -1;
	}
	public boolean equals(DataRecords d2){
		return d2.getYear() == year;
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
	public int getYear(){
		return year;
	}
	public String getJournal(){
		return journal;
	}
	public String getBookTitle(){
		return booktitle;
	}
	public String getJournalTitle(){
		if (journal == null){
			return booktitle;
		}
		return journal;
	}
	public String getURL(){
		return url;
	}
	public int getStringMatch()
	{
		return stringMatch;
	}
	public void setStringMatch(int m)
	{
		stringMatch=m;
	}
}