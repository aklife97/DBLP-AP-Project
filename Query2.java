/**
*@author Abhinav Khattar 2015120, Tushar Arora 2015107
*/
import java.util.*;
import java.io.*;
public class Query2{
	private static Query2 instance = new Query2("dblp.xml");
	private ArrayList<Author> result;
	private String filename;
	private Query2(String _filename){
		filename = _filename;
	}
	public void find(int k){
		result = AuthorManager.getGreaterK(k);
	}
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
	public int getCount(){
		return result.size();
	}
	public static Query2 getInstance(){
		return instance;
	}
}