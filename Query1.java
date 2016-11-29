/**
*@file Query1.java
*This file contains backend Query1 implementation
*@author Abhinav Khattar 2015120
*@author Tushar Arora 2015107
*/
import java.util.*;
import java.io.*;
/**This class uses Database to extract data as needed for Query1, the class is Singleton*/
public class Query1 implements Query13{
	private static Query1 instance = new Query1("dblp.xml");
	private Database dbase;
	private String filename;
	private int mode, since, to,sortMode;
	private String q;
	private PriorityQueue<DataRecords> dataRec;
	private PriorityQueue<DataRecords> dataRec2;
	private int count=0;
	public Query1(String _filename){
		filename = _filename;
	}
	/**This method starts the finding of relevant data*/
	public void find(int _mode, String _q, int _since, int _to,int _sortMode){
		dataRec = new PriorityQueue<DataRecords>();
		dataRec2 = new PriorityQueue<DataRecords>(new Comparator<DataRecords>() {
       			public int compare(DataRecords d1, DataRecords d2) {
            		if(d1.getStringMatch()<d2.getStringMatch()){
            			return 1;
            		}	         
            		else if(d1.getStringMatch()==d2.getStringMatch()){
            			return 0;
            		} 
            		return -1;                                               
		        }
		    });
		mode = _mode;
		since = _since;
		to = _to;
		sortMode=_sortMode;
		q = _q.toLowerCase();
		count=0;
		dbase = new Database(filename, this);
	}
	public void check(DataRecords d){
		int t;
		if (mode == 1 && d.getAuthor() != null){
			String[] authors = d.getAuthor().split(",");
			for (String a : authors){
				if (AuthorManager.resolveAuthor(a.toLowerCase()) == AuthorManager.resolveAuthor(q) && d.getYear() >= since && d.getYear() <= to){
					dataRec.add(d);
					count++;
				}
			}
		}		
		else if (sortMode==0 && mode == 2 && d.getTitle()!=null && (t=this.getDistance(d.getTitle(),q.toLowerCase()))>0 && d.getYear() >= since && d.getYear() <= to){
			dataRec.add(d);
			count++;
		} 
		else if (sortMode==1 && mode == 2 && d.getTitle()!=null && (t=this.getDistance(d.getTitle(),q.toLowerCase()))>0 && d.getYear() >= since && d.getYear() <= to){
			if (t>d.getStringMatch())
				d.setStringMatch(t);
			dataRec2.add(d);
			count++;
		} 
	}
	/**This method gives back data one element at a time returning null when finished*/
	public DataRecords getData(){
		if(mode== 1 && (sortMode==0 || sortMode ==1))
			return dataRec.poll();
		else if(mode==2 && (sortMode==0))
			return dataRec.poll();
		else
			return dataRec2.poll();		
	}
	/**Gets the total number of results*/
	public int getCount(){
		return count;
	}
	public void printData(){
		DataRecords d;
		System.out.println("size- "+dataRec.size());
		while ((d = dataRec.poll())!=null){
			System.out.println(d.getTitle());
			String[] authors = d.getAuthor().split(",");
				for (String a : authors){
					System.out.print(a+" ");
				}
				System.out.println(" ");
		}
	}
	public int getDistance(String s, String t){
	    String[] arr = s.split(" ");  
	    String[] arr2 = t.split(" ");
	    int count=0;
	    for(String a:arr){
	    	for(String b:arr2){
	    		if(a.equalsIgnoreCase(b) && b.length()>=4){
	    			count++;
	    			break;
	    		}
	    	}
	    }  
	    return count;
	}
	public static Query1 getInstance(){
		return instance;
	}
}