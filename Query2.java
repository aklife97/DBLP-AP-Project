/**
*@author Abhinav Khattar 2015120, Tushar Arora 2015107
*/
import java.util.*;
import java.io.*;
public class Query2{
	private ArrayList<Author> result;
	private String filename;
	public Query2(String _filename){
		filename = _filename;
	}
	public void find(int k){
		result = AuthorManager.getGreaterK(k);
	}
	public void printData(){
		for (Author a : result){
			System.out.println(a.getName() + " " + a.getCount());
		}
	}
}