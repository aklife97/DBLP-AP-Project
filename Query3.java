/**
*@file Query3.java
*This file contains backend Query3 implementation
*@author Abhinav Khattar 2015120
*@author Tushar Arora 2015107
*/
import java.util.*;
import java.io.*;
/**This class uses Database to extract data as needed for Query3, the class is Singleton*/
public class Query3 implements Query13{
	private static Query3 instance = new Query3("dblp.xml");
	private Database dbase;
	private int year;
	private String filename;
	private String auth;
	private ArrayList<DataRecords> dataRec;
	private double[] yearRegTemp=null;
	private double[] pubCountTemp=null;
	private int[] yearReg=null;
	private int[] pubCount=null;
	private Query3(String _filename){
		filename = _filename;
	}
	/**This method starts the finding of relevant data*/
	public void find(int _year, String _auth){
		dataRec = new ArrayList<DataRecords>();
		year = _year;
		auth=_auth;
		yearReg=new int[year-1900+1];
		pubCount = new int[year-1900+1];
		for(int i=0;i<=year-1900;i++)
		{
			yearReg[i]=i+1900;
			pubCount[i]=0;
		}
		dbase = new Database(filename, this);

	}
	public void check(DataRecords d){
		if(d.getAuthor() != null)
		{	
			String[] authors = d.getAuthor().split(",");
					for (String a : authors){
						if (AuthorManager.resolveAuthor(a.toLowerCase()) == AuthorManager.resolveAuthor(auth) && d.getYear() !=0 && d.getYear() <= year){
							pubCount[d.getYear()-1900]++;
						}
					}
		}		
	}

	private void initArrays()
	{
		yearRegTemp=new double[yearReg.length-this.startIndex()];
		pubCountTemp = new double[yearReg.length-this.startIndex()];
		int j=0;
		for(int i=this.startIndex();i<yearReg.length;i++)
		{
			yearRegTemp[j]=(double)yearReg[i];
			pubCountTemp[j]=(double)pubCount[i];
			j++;
		}
	}

	public void printData(){
	}

	/**This method gives back data one element at a time returning null when finished*/
	public double getData()
	{
		initArrays();
		if(pubCountTemp.length==0){
			return -1.0;
		}
		return linearRegression(year+1);
	}

	public int getDegree()
	{
		int upCount=0,downCount=0;
		double upLimit=1.333,downLimit=0.666;
		for(int i=1;i<pubCountTemp.length;i++){
			if(pubCountTemp[i]<(int)downLimit*pubCountTemp[i-1]){
				downCount++;
			}
			else if(pubCountTemp[i]>(int)upLimit*pubCountTemp[i-1]){
				upCount++;
			}
		}
		return upCount+downCount;
	}

	private int startIndex()
	{
		for(int i=0;i<=year-1900;i++)
		{
			if(pubCount[i]==0)
			{
				continue;
			}
			else
			{
				return i;
			}
		}
		return 0;
	}

	private double mean(int[] arr)
	{
		int sum=0;
		for(int i=startIndex();i<arr.length;i++)
		{
			sum+=pubCount[i];
		}
		try
		{
			return sum/(arr.length-this.startIndex());	
		}
		catch(NullPointerException n)
		{
			n.printStackTrace();
		}
		return 0;
	}
	private long calcSum(int[] a, int i)
	{
		long sum=0;
		for(int j=i;j<a.length;j++)
		{
			sum+=a[j];
		}
		return sum;
	}
	private long calcMulSum(int[] a,int[] b,int i)
	{
		long mulSum=0;
		for(int j=i;j<a.length;j++)
		{
			mulSum+=a[i]*b[i];
		}
		return mulSum;
	}
	private double findSlope()
	{
		long numerator=0;
		long denomenator=0;
		int n=yearReg.length-this.startIndex();
		int idx=this.startIndex();
		long num1=n*calcMulSum(yearReg,pubCount,idx);
		long num2=calcSum(yearReg,idx)*calcSum(pubCount,idx);
		long denum1=n*calcMulSum(yearReg,yearReg,idx);
		long denum2=calcSum(yearReg,idx)*calcSum(yearReg,idx);
		numerator=num1-num2;
		denomenator=denum1-denum2;
		try
		{
			return numerator/denomenator;
		}
		catch(NullPointerException e)
		{
			e.printStackTrace();
		}
		return 0;
	}

	private double yIntercept(double xMean,double yMean,double slope)
	{
		return (yMean-(slope*xMean));
	}

	private double linearRegression(int xGiven)
	{
		double xMean= mean(yearReg);
		double yMean= mean(pubCount);
		double slope= findSlope();
		double yIntercept= yIntercept(xMean,yMean,slope); 
		double yToFind=yIntercept + slope*xGiven;

		return yToFind;
	}
	public static Query3 getInstance(){
		return instance;
	}
}