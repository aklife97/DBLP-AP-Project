/**
*@author Abhinav Khattar 2015120, Tushar Arora 2015107
*/
import java.util.*;
import java.io.*;
// import StringUtils.*;
public class Query1 implements Query13{
	private Database dbase;
	private String filename;
	private int mode, since, to,sortMode;
	private String q;
	private PriorityQueue<DataRecords> dataRec;
	private PriorityQueue<DataRecords> dataRec2;

	public Query1(String _filename){
		filename = _filename;
	}
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
		dbase = new Database(filename, this);
	}
	public void check(DataRecords d){
		if(sortMode==0){
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
		} else {
			int t=0;
			if (mode == 1 && d.getAuthor() != null){
				String[] authors = d.getAuthor().split(",");

				for (String a : authors){
					if ((t=this.getDistance(a.toLowerCase(),q.toLowerCase()))>0 && d.getYear() >= since && d.getYear() <= to){
						if (t>d.getStringMatch()) {
							d.setStringMatch(t);
						}	
						dataRec2.add(d);
					}
				}
			}
			else if (mode == 2 && d.getTitle()!=null && (t=this.getDistance(d.getTitle(),q.toLowerCase()))>0 && d.getYear() >= since && d.getYear() <= to){
				if (t>d.getStringMatch()) {
							d.setStringMatch(t);
						}
				dataRec2.add(d);
			}
		}	

	}
	public DataRecords getData(){
		if(sortMode==0){
			return dataRec.poll();
		} else{
			return dataRec2.poll();
		}
		
	}
	public void printData(){
		DataRecords d;
		System.out.println("size- "+dataRec2.size());
		while ((d = dataRec2.poll())!=null){
			System.out.println(d.getTitle());
			// System.out.println(d.getYear());
			String[] authors = d.getAuthor().split(",");
				for (String a : authors){
					System.out.print(a+" ");
				}
				System.out.println(" ");
		}
	}
	
	public static int getDistance(String s, String t) {

	    String[] arr = s.split(" ");  
	    String[] arr2 = t.split(" ");
	    int count=0;
	    for(String a:arr)
	    {
	    	for(String b:arr2)
	    	{
	    		if(a.equalsIgnoreCase(b) && b.length()>=4)
	    		{
	    			count++;
	    			break;
	    		}
	    	}
	    }  
	    return count;
	}
}