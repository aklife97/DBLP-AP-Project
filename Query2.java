/**
*@author Abhinav Khattar 2015120, Tushar Arora 2015107
*/
import java.util.*;
import java.io.*;
public class Query2{
	// private Database dbase;
	// private int k;
	// public Query2(String filename){
	// 	k = _k;
	// 	dbase = new Database(filename);
	// }
	private String filename;
	public Query1(String _filename){
		filename = _filename;
	}
	public ArrayList<Author> find(int k){
		// if (!AuthorManager.isCountAdded()){
		// 	AuthorManager.addCount();
		// }
		return AuthorManager.getGreaterK(k);
	}
	public static void main(String[] args){
		System.setProperty("jdk.xml.entityExpansionLimit", "0");
		AuthorManager.addFile("dblp.xml");
		Query2 q = new Query2();
		ArrayList<Author> a = q.find(125);
		for (Author au : a){
			System.out.println(au.getName() + " " + au.getCount());
		}
		System.clearProperty("jdk.xml.entityExpansionLimit");
	}
}