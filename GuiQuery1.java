/**
*@file GuiQuery1.java
*This file contains GUI Query1 implementation
*@author Abhinav Khattar 2015120
*@author Tushar Arora 2015107
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
/**Creates the GUI needed for supporting Query1*/
public class GuiQuery1 extends GUIQuery
{
	protected DefaultTableModel query1Table= new DefaultTableModel();
	protected JTable displayTable = new JTable(query1Table);
	protected JScrollPane dispTable = new JScrollPane(displayTable);
	protected JButton next = new JButton("NEXT");
	protected JButton back = new JButton("BACK");
	protected JComboBox<String> searchBy = new JComboBox<String>();
	protected JLabel title1 = new JLabel("Name / Title tags");
	protected JLabel title2 = new JLabel("Since Year");
	protected JLabel title3 = new JLabel("Custom Range");	
	protected JLabel result = new JLabel();
	protected JLabel warning = new JLabel(" ");
	protected JLabel dash = new JLabel("-");
	protected JTextField title =new JTextField();
	protected JTextField year1 =new JTextField();
	protected JTextField year2 =new JTextField();
	protected JTextField year3 =new JTextField();
	protected int flag=0,flag2=0,tableWorking=0,pages=0;
	protected JLabel totalResults = new JLabel(" ");

	public GuiQuery1(JFrame mainFrame, JComboBox<String> queries,JPanel sidePanel,JPanel displayPanel,QueryFacade q1,JComboBox<String> searchBy)
	{
		this.mainFrame=mainFrame;
		this.queries=queries;
		this.sidePanel=sidePanel;
		this.displayPanel=displayPanel;
		this.searchBy=searchBy;
		this.q=q1;
	}
	
	public void initQuery()
	{
		displayTable.setDefaultEditor(Object.class, null);
		dispTable.setBounds(20,5,910,390);
		query1Table.addColumn("S.No.");
		query1Table.addColumn("Authors");
		query1Table.addColumn("Title");
		query1Table.addColumn("Pages");
		query1Table.addColumn("Year");
		query1Table.addColumn("Volume");
		query1Table.addColumn("Journal/Booktitle");
		query1Table.addColumn("Url");
		next.setBounds(840,395,80,40);
		next.setBackground(Color.RED);
		next.setFont(new Font("Calibri", Font.PLAIN, 10));
		back.setBounds(30,335,80,40);
		back.setBackground(Color.BLACK);
		back.setFont(new Font("Calibri", Font.PLAIN, 10));
    	title1.setFont(new Font("Calibri", Font.PLAIN, 10));
    	title2.setFont(new Font("Calibri", Font.PLAIN, 10));
    	title3.setFont(new Font("Calibri", Font.PLAIN, 10));
    	dash.setFont(new Font("Calibri", Font.PLAIN, 10));	
    	result.setFont(new Font("Calibri", Font.PLAIN, 15));
    	warning.setFont(new Font("Calibri", Font.PLAIN, 12));
    	warning.setForeground(Color.RED);
    	title1.setBounds(30,90,100,20);
    	title2.setBounds(30,130,100,20);
    	title3.setBounds(30,155,100,20);
    	dash.setBounds(170,155,10,20);
    	result.setBounds(50,120,350,50);
    	warning.setBounds(30,340,190,20);
    	totalResults.setFont(new Font("Calibri", Font.PLAIN, 10));
    	totalResults.setBounds(515,395,120,30);
    	year1.setHorizontalAlignment(JTextField.CENTER);
    	year2.setHorizontalAlignment(JTextField.CENTER);
    	year3.setHorizontalAlignment(JTextField.CENTER);
    	title.setBounds(140,90,70,20);
    	year1.setBounds(140,130,50,20);
    	year2.setBounds(110,155,50,20);
    	year3.setBounds(190,155,50,20);
    	submit=new JButton("Submit");
		reset=new JButton("Reset");
		submit.setForeground(Color.WHITE);
		submit.setBackground(Color.BLACK);
		reset.setBackground(Color.RED);
        submit.setBounds(30,290,80,30);
        reset.setBounds(140,290,80,30);
        submit.setFont(new Font("Calibri", Font.PLAIN, 12));
        reset.setFont(new Font("Calibri", Font.PLAIN, 12));
	}

	public void change(int i)
	{
		flag=i;
	}
	public void changeMode(int i)
	{
		flag2=i;
	}

    public void setQuery()
    {
    	flag=0;
    	flag2=0;
    	searchBy.setBounds(50,50,100,20);
    	searchBy.setFont(new Font("Calibri", Font.PLAIN, 10));
	 	tableWorking=0;
		pages=0;

		next.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(tableWorking==1){
					DataRecords d =q.queryOneGetData();
					if(d!=null){	
						int count=0;
						query1Table.setRowCount(0);
		            	while(d!=null && count<20){
		            		query1Table.addRow(new Object[]{(pages*20)+(count+1),d.getAuthor(),d.getTitle(),d.getPages(),d.getYear(),d.getVolume(),d.getJournalTitle(),d.getURL()});
		            		count++;
		            		if(count<20)
		            		{d=q.queryOneGetData();}	
		            	}
		            	pages+=1;}
            	}
            	else{
            		tableWorking=0;
            	}
			}
		});

		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{

			}
		});
    }	
}