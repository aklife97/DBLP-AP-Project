/**
*@author Abhinav Khattar 2015120, Tushar Arora 2015107
*/
import java.util.*;
import java.io.*;
public class Author{
	private String name;
	private int count = 0;
	public Author(String _name){
		name = _name;
	}
	public String getName(){
		return name;
	}
	public int getCount(){
		return count;
	}
	public void increaseCount(){
		count++;
	}
}