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
	private PriorityQueue<DataRecords> dataRec;
	public Query1(String _filename){
		filename = _filename;
	}
	public void find(int _mode, String _q, int _since, int _to){
		dataRec = new PriorityQueue<DataRecords>();
		mode = _mode;
		since = _since;
		to = _to;
		q = _q.toLowerCase();
		dbase = new Database(filename, this);
	}
	public void check(DataRecords d){
		if (mode == 1 && d.getAuthor() != null){
			String[] authors = d.getAuthor().split(",");
			for (String a : authors){
				if (AuthorManager.resolveAuthor(a.toLowerCase()) == AuthorManager.resolveAuthor(q) && d.getYear() >= since && d.getYear() <= to){
					dataRec.add(d);
				}
			}
		}
		
		else if (mode == 2 && d.getTitle()!=null && d.getTitle().equalsIgnoreCase(q) && d.getYear() >= since && d.getYear() <= to){
			dataRec.add(d);
		}

	}
	public DataRecords getData(){
		return dataRec.poll();
	}
	public void printData(){
		DataRecords d;
		while ((d = dataRec.poll())!=null){
			System.out.println(d.getTitle());
			System.out.println(d.getAuthor());
		}
	}
}