/**
*@file QueryFacade.java
*This file contains a Facade to communicate to Query1,2,3
*@author Abhinav Khattar 2015120
*@author Tushar Arora 2015107
*/
import java.util.*;
import java.io.*;
/**QueryFacade implemnts Facade Design Pattern over the Query classes*/
public class QueryFacade{
	private Query1 q1;
	private Query2 q2;
	private Query3 q3;
	public QueryFacade(){
		q1 = Query1.getInstance();
		q2 = Query2.getInstance();
		q3 = Query3.getInstance();
	}
	public void queryOneFind(int _mode, String _q, int _since, int _to,int _sortMode){
		q1.find(_mode, _q, _since, _to, _sortMode);
	}
	public DataRecords queryOneGetData(){
		return q1.getData();
	}
	public int queryOneGetCount(){
		return q1.getCount();
	}
	public void queryOnePrintData(){
		q1.printData();
	}
	public void queryTwoFind(int k){
		q2.find(k);
	}
	public Author queryTwoGetData(){
		return q2.getData();
	}
	public int queryTwoGetCount(){
		return q2.getCount();
	}
	public void queryTwoPrintData(){
		q2.printData();
	}
	public void queryThreeFind(int _year, String _auth){
		q3.find(_year, _auth);
	}
	public double queryThreeGetData(){
		return q3.getData();
	}
}