/**
*@file Query2.java
*This file contains backend Query2 implementation
*@author Abhinav Khattar 2015120
*@author Tushar Arora 2015107
*/
import java.util.*;
import java.io.*;
/**This class uses Database to extract data as needed for Query2, the class is Singleton*/
public class Query2{
	private static Query2 instance = new Query2("dblp.xml");
	private ArrayList<Author> result;
	private String filename;
	private Query2(String _filename){
		filename = _filename;
	}
	/**This method starts the finding of relevant data*/
	public void find(int k){
		result = AuthorManager.getGreaterK(k);
	}
	/**This method gives back data one element at a time returning null when finished*/
	public Author getData(){
		if (result.size() == 0)
			return null;
		else{
			Author a = result.get(0);
			result.remove(0);
			return a;
		}
	}
	public void printData(){
		for (Author a : result){
			System.out.println(a.getName() + " " + a.getCount());
		}
	}
	/**Gets the total number of results*/
	public int getCount(){
		return result.size();
	}
	public static Query2 getInstance(){
		return instance;
	}
}