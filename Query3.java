/**
*@author Abhinav Khattar 2015120, Tushar Arora 2015107
*/
import java.util.*;
import java.io.*;
public class Query3 implements Query13{
	private Database dbase;
	private int year;
	private String filename;
	private String auth;
	private ArrayList<DataRecords> dataRec;
	private double[] yearRegTemp=null;
	private double[] pubCountTemp=null;
	private int[] yearReg=null;
	private int[] pubCount=null;
	public Query3(String _filename){
		filename = _filename;
	}
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
							// System.out.println("d.getYear()== "+d.get.getYear());
							// dataRec.add(d);
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
		// DataRecords d;
		// while ((d = dataRec.poll())!=null){
		// 	System.out.println(d.getTitle());
		// 	System.out.println(d.getAuthor());
		// }
	}
	public double getData()
	{
		return linearRegression(year+1);
		// TrendLine t = new PolyTrendLine(2);
		// t.setValues(pubCountTemp,yearRegTemp);
  //  		return t.predict(year+1);
	}

	// public static void main(String[] args){
	// 	System.setProperty("jdk.xml.entityExpansionLimit", "0");
	// 	AuthorManager.addFile("dblp.xml");
	// 	System.out.println("1.");
	// 	AuthorManager.createMap();
	// 	System.out.println("2.");
	// 	Query3 q3 = new Query3("dblp.xml");
	// 	q3.find(2015,"chin-chen chang");
	// 	double data=q3.getData();
	// 	System.out.println("Prediction is "+data);
	// 	System.clearProperty("jdk.xml.entityExpansionLimit");
	// }

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
		System.out.println("n* ="+n);
		int idx=this.startIndex();
		long num1=n*calcMulSum(yearReg,pubCount,idx);
		long num2=calcSum(yearReg,idx)*calcSum(pubCount,idx);
		long denum1=n*calcMulSum(yearReg,yearReg,idx);
		long denum2=calcSum(yearReg,idx)*calcSum(yearReg,idx);
		numerator=num1-num2;
		denomenator=denum1-denum2;
		System.out.println("Numerator ="+numerator);
		System.out.println("denomenator ="+denomenator);
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
		System.out.println("startIndex= "+this.startIndex());
		for(int i=this.startIndex();i<pubCount.length;i++)
		{
			System.out.print(yearReg[i]+"=="+pubCount[i]+"#");
		}
		double xMean= mean(yearReg);
		double yMean= mean(pubCount);
		double slope= findSlope();
		double yIntercept= yIntercept(xMean,yMean,slope); 
		double yToFind=yIntercept + slope*xGiven;

		return yToFind;
	}
}