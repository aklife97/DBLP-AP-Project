/**
*@author Abhinav Khattar 2015120, Tushar Arora 2015107
*/
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.util.*;
import java.io.*;
public class Database{
	private SAXParser parser;
	public Database(String filename){
		try{
			SAXParserFactory factory = SAXParserFactory.newInstance();
			parser = factory.newSAXParser();
			Handler handle = new Handler();
			parser.parse(filename, handle);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
class Handler extends DefaultHandler{
	
}