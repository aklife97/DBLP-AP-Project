/**
*@author Abhinav Khattar 2015120, Tushar Arora 2015107
*/
import java.util.*;
import java.io.*;
public class DataRecords{
	private String author;
	private String title;
	private String pages;
	private int year;
	private String volume;
	private String journal;
	private String booktitle;
	private String url;
	public DataRecords(String _author, String _title, String _pages, String _voulme, String _year, String _journal, String _booktitle, String _url){
		author = _author;
		title = _title;
		pages = _pages;
		volume = _voulme;
		// if (_year == null){
		// 	System.out.println("hi");
		// 	year = -1;
		// }
		// else
		try{	
			year = Integer.parseInt(_year);
		}
		catch (Exception e){
			year = 0;
		}
		journal = _journal;
		booktitle = _booktitle;
		url = _url;
	}
	public int compareTo(DataRecords d2){
		if (d2.getYear() > year){
			return -1;
		}
		else if (d2.getYear() == year){
			return 0;
		}
		return 1;
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
	public String getURL(){
		return url;
	}
}