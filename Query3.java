/**
*@author Abhinav Khattar 2015120, Tushar Arora 2015107
*/
import java.util.*;
import java.io.*;
public class Query3 implements Query13{
	private Database dbase;
	private int year;
	private String filename;
	private PriorityQueue<DataRecords> dataRec;
	public Query3(String _filename){
		filename = _filename;
	}
	public void find(int _year){
		dataRec = new PriorityQueue<DataRecords>();
		year = _year;
		dbase = new Database(filename, this);
		// call to a function that predicts, data till the year specified is present in the dataRec Queue, also create a getData method
	}
	public void check(DataRecords d){
		if (d.getYear() !=0 && d.getYear() <= year){
			dataRec.add(d);
		}
	}
	public void printData(){
		DataRecords d;
		while ((d = dataRec.poll())!=null){
			System.out.println(d.getTitle());
			System.out.println(d.getAuthor());
		}
	}
}