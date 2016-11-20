/**
*@author Abhinav Khattar 2015120, Tushar Arora 2015107
*/
import java.util.*;
import java.io.*;
public class Query1{
	private Database dbase;
	private String filename;
	private int mode, since, to;
	private String q;
	private ArrayList<DataRecords> dataRec;
	// public Query1(String filename, int _mode, String _q){ // try make mode enum
	// 	dbase = new Database(filename, this);
	// 	mode = _mode;
	// 	q = _q.toLowerCase();
	// }
	public Query1(String _filename){
		filename = _filename;
	}
	public void find(int _mode, String _q, int _since, int _to){ // try make mode enum, see if make return type array
		dataRec = new ArrayList<DataRecords>();
		mode = _mode;
		since = _since;
		to = _to;
		q = _q.toLowerCase();
		dbase = new Database(filename, this);
	}
	public void check(DataRecords d){
		// System.out.println(d.getAuthor());
		if (mode == 1 && d.getAuthor() != null){
			String[] authors = d.getAuthor().split(",");
			for (String a : authors){
				if (AuthorManager.resolveAuthor(a.toLowerCase()) == AuthorManager.resolveAuthor(q) && d.getYear() >= since && d.getYear() <= to){
					dataRec.add(d);
					// System.out.println("here");
				}
			}
		}
		
		else if (mode == 2 && d.getTitle()!=null && d.getTitle().equalsIgnoreCase(q) && d.getYear() >= since && d.getYear() <= to){
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