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

public class GuiQuery1
{
	private JFrame mainFrame;
	private JPanel upperPanel;
	private JPanel sidePanel;
	private JPanel displayPanel;
	private Query1 q1=null;
	private JComboBox<String> queries;
	private JButton submit,reset;
	private DefaultTableModel query1Table= new DefaultTableModel();
	private JTable displayTable = new JTable(query1Table);
	private JScrollPane dispTable = new JScrollPane(displayTable);
	private JButton next = new JButton("NEXT");
	private JButton back = new JButton("BACK");
	private JComboBox<String> searchBy = new JComboBox<String>();
	private JLabel title1 = new JLabel("Name / Title tags");
	private JLabel title2 = new JLabel("Since Year");
	private JLabel title3 = new JLabel("Custom Range");	
	private JLabel result = new JLabel();
	private JLabel warning = new JLabel(" ");
	private JLabel dash = new JLabel("-");
	private JTextField title =new JTextField();
	private JTextField year1 =new JTextField();
	private JTextField year2 =new JTextField();
	private JTextField year3 =new JTextField();
	private final JRadioButton sortRel = new JRadioButton("Sort by Relevance");
	private final JRadioButton sortYear = new JRadioButton("Sort by Date",true);
	private ButtonGroup sortButtons = new ButtonGroup();
    private final JRadioButton sinceYear = new JRadioButton("For Since year");
	private final JRadioButton customYear = new JRadioButton("For custom year range");
	private ButtonGroup yearButtons = new ButtonGroup();
	private int flag=0,flag2=0,tableWorking=0,pages=0;
	private JLabel totalResults = new JLabel(" ");

	public GuiQuery1(JFrame mainFrame, JComboBox<String> queries,JPanel sidePanel,JPanel displayPanel,Query1 q1)
	{
		this.mainFrame=mainFrame;
		this.queries=queries;
		this.sidePanel=sidePanel;
		this.displayPanel=displayPanel;
		this.q1=q1;
		initQuery1();
	}
	
	public void initQuery1()
	{
		
		displayTable.setDefaultEditor(Object.class, null);
		dispTable.setBounds(20,5,610,330);
		query1Table.addColumn("S.No.");
		query1Table.addColumn("Authors");
		query1Table.addColumn("Title");
		query1Table.addColumn("Pages");
		query1Table.addColumn("Year");
		query1Table.addColumn("Volume");
		query1Table.addColumn("Journal/Booktitle");
		query1Table.addColumn("Url");
		// query1Table.addRow(new Object[]{"v1", "v2" , "v3" , "v4", "v5", "v6", "v7","v8"});
		//---
		
		next.setBounds(540,335,80,40);
		next.setBackground(Color.RED);
		next.setFont(new Font("Calibri", Font.PLAIN, 10));
		
		back.setBounds(30,335,80,40);
		back.setBackground(Color.BLACK);
		back.setFont(new Font("Calibri", Font.PLAIN, 10));
		//--
    	
    	searchBy.addItem("Search By");
    	searchBy.addItem("Author Name");
    	searchBy.addItem("Title Tag");
    	searchBy.setSelectedItem("Search By");
    	searchBy.setBounds(50,50,100,20);
    	searchBy.setFont(new Font("Calibri", Font.PLAIN, 10));
    	
    	
    	// JLabel warning2 = new JLabel();
    	
    	title1.setFont(new Font("Calibri", Font.PLAIN, 10));
    	title2.setFont(new Font("Calibri", Font.PLAIN, 10));
    	title3.setFont(new Font("Calibri", Font.PLAIN, 10));
    	dash.setFont(new Font("Calibri", Font.PLAIN, 10));	
    	result.setFont(new Font("Calibri", Font.PLAIN, 15));	
    	warning.setFont(new Font("Calibri", Font.PLAIN, 12));
    	warning.setForeground(Color.RED);
    	// warning2.setFont(new Font("Calibri", Font.PLAIN, 12));
    	title1.setBounds(30,90,100,20);
    	// title1.setBounds(30,70,100,20);
    	title2.setBounds(30,130,100,20);
    	title3.setBounds(30,155,100,20);
    	dash.setBounds(170,155,10,20);
    	result.setBounds(50,120,350,50);
    	warning.setBounds(30,340,190,20);

    	totalResults.setFont(new Font("Calibri", Font.PLAIN, 10));
    	totalResults.setBounds(265,335,120,30);
    	// warning2.setBounds(30,360,190,20);
    	
    	year1.setHorizontalAlignment(JTextField.CENTER);
    	year2.setHorizontalAlignment(JTextField.CENTER);
    	year3.setHorizontalAlignment(JTextField.CENTER);
    	title.setBounds(140,90,70,20);
    	year1.setBounds(140,130,50,20);
    	year2.setBounds(110,155,50,20);
    	year3.setBounds(190,155,50,20);
    	
    	
        sortButtons.add(sortRel);
        sortButtons.add(sortYear);
        sortYear.setBounds(60,200,150,15);
        sortRel.setBounds(60,215,150,15);
        sortYear.setFont(new Font("Calibri", Font.PLAIN, 10));
        sortRel.setFont(new Font("Calibri", Font.PLAIN, 10));

       
        yearButtons.add(sinceYear);
        yearButtons.add(customYear);
        sinceYear.setBounds(60,245,150,15);
        customYear.setBounds(60,260,150,15);
        sinceYear.setFont(new Font("Calibri", Font.PLAIN, 10));
        customYear.setFont(new Font("Calibri", Font.PLAIN, 10));

        submit=new JButton("Submit");
		reset=new JButton("Reset");
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

    public void setQuery1()
    {
    	flag=0;
    	flag2=0;
    	// q1=new Query1("dblp.xml");
    	queries.removeItem("Queries");
    	sidePanel.removeAll();
    	displayPanel.removeAll();
    	queries.setBounds(50,20,100,20);
    	queries.setSelectedItem("Query 1");
    	//----
    	
    	sidePanel.add(searchBy);
    	sidePanel.add(queries);
    	sidePanel.add(title1);
    	sidePanel.add(title);
    	sidePanel.add(title2);
    	sidePanel.add(year1);
    	sidePanel.add(title3);
    	sidePanel.add(year2);
    	sidePanel.add(dash);
    	sidePanel.add(year3);
    	sidePanel.add(sortYear);
    	sidePanel.add(sortRel);
    	sidePanel.add(submit);
    	sidePanel.add(reset);
    	sidePanel.add(warning);
    	sidePanel.add(sinceYear);
    	sidePanel.add(customYear);
    	displayPanel.add(totalResults);
    	displayPanel.add(dispTable);
    	displayPanel.add(next);
    	displayPanel.add(back);
    	mainFrame.revalidate();
	 	mainFrame.repaint();
	 	tableWorking=0;
		pages=0;
		sortRel.addItemListener(new ItemListener() {
	         public void itemStateChanged(ItemEvent e) {         
	            	    change(1);    
	            }           
	      });
		
		sortYear.addItemListener(new ItemListener() {
	         public void itemStateChanged(ItemEvent e) {         
	            change(0);
	         }           
	      });
		sinceYear.addItemListener(new ItemListener() {
	         public void itemStateChanged(ItemEvent e) {         
	            	    changeMode(1);    
	            }           
	      });
		
		customYear.addItemListener(new ItemListener() {
	         public void itemStateChanged(ItemEvent e) {         
	            changeMode(2);
	         }           
	      });

		next.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(tableWorking==1)
				{
					DataRecords d =q1.getData();
					if(d!=null)
					{	
						int count=0;
						query1Table.setRowCount(0);
		            	while(d!=null && count<20)
		            	{
		            		query1Table.addRow(new Object[]{(pages*20)+(count+1),d.getAuthor(),d.getTitle(),d.getPages(),d.getYear(),d.getVolume(),d.getJournalTitle(),d.getURL()});
		            		count++;
		            		if(count<20)
		            		{d=q1.getData();}	
		            	}
		            	pages+=1;}
            	}
            	else
            	{
            		tableWorking=0;
            	}
			}
		});

		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{

			}
		});
		submit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					warning.setText(" ");
					  totalResults.setText(" ");
					int count=0;
					query1Table.setRowCount(0);
					String res=" ";
					String selectedOption =(String) searchBy.getSelectedItem();
			        if (selectedOption.equals("Author Name")) {
			        	if(title.getText().length()==0){
			        		warning.setText("Author name field cannot be empty");
			        	}
			        	else {
				        	if(flag==0 || flag ==1)
				        	{
				        		if(flag2==1)
				        		{
				        			int y=0,pages=0;
				        			if(isInteger(year1.getText()))
						            {
						            	warning.setText(" ");
						            	y=Integer.parseInt(year1.getText());
						            }
						            else
						            {
						            	warning.setText("Year field should be numbers");
						            }
						            q1.find(1, title.getText(),y, 9999,flag);
						            totalResults.setText("Total results = "+q1.getCount());
						            tableWorking=1;
						            DataRecords d= q1.getData();
						            while(d!=null && count<20)
						            {
						            	query1Table.addRow(new Object[]{count+1,d.getAuthor(),d.getTitle(),d.getPages(),d.getYear(),d.getVolume(),d.getJournalTitle(),d.getURL()});
					            		count++;
					            		if(count<20)
					            		{d=q1.getData();}	
					            	}
					            	pages=1;
				        		}
				        		else if(flag2==2)
				        		{
				        			int y1=0,y2=0,pages=0;
				        			if(isInteger(year2.getText())&&isInteger(year3.getText()))
						            {
						            	warning.setText(" ");
						            	y1=Integer.parseInt(year2.getText());
						            	y2=Integer.parseInt(year3.getText());
						            }
						            else
						            {
						            	warning.setText("Year field should be numbers");
						            }
						            q1.find(1, title.getText(),y1,y2,flag);
						              totalResults.setText("Total results = "+q1.getCount());
						            tableWorking=1;
						            DataRecords d= q1.getData();
						            while(d!=null && count<20)
						            {
						            	query1Table.addRow(new Object[]{count+1,d.getAuthor(),d.getTitle(),d.getPages(),d.getYear(),d.getVolume(),d.getJournalTitle(),d.getURL()});
					            		count++;
					            		if(count<20)
					            		{d=q1.getData();}	
					            	}
					            	pages=1;
				        		}
				        		else
				        		{
				        			q1.find(1, title.getText(),0,9999,flag);
				        			  totalResults.setText("Total results = "+q1.getCount());
						            tableWorking=1;
						            DataRecords d= q1.getData();
						            while(d!=null && count<20)
						            {
						            	query1Table.addRow(new Object[]{count+1,d.getAuthor(),d.getTitle(),d.getPages(),d.getYear(),d.getVolume(),d.getJournalTitle(),d.getURL()});
					            		count++;
					            		if(count<20)
					            		{d=q1.getData();}	
					            	}
					            	pages=1;
				        		}
				        	}
				        }

			        } else if (selectedOption.equals("Title Tag")) {
			        	if(title.getText().length()==0){
			        		warning.setText("Title tag cannot be empty");
			        	}
			        	else
			        	{
				            if(flag==0 || flag ==1)
				        	{
				        		if(flag2==1)
				        		{
				        			int y=0,pages=0;
				        			if(isInteger(year1.getText()))
						            {
						            	warning.setText(" ");
						            	y=Integer.parseInt(year1.getText());
						            }
						            else
						            {
						            	warning.setText("Year field should be numbers");
						            }
						            q1.find(2, title.getText(),y, 9999,flag);
						              totalResults.setText("Total results = "+q1.getCount());
						            tableWorking=1;
						            DataRecords d= q1.getData();
						            while(d!=null && count<20)
						            {
						            	query1Table.addRow(new Object[]{count+1,d.getAuthor(),d.getTitle(),d.getPages(),d.getYear(),d.getVolume(),d.getJournalTitle(),d.getURL()});
					            		count++;
					            		if(count<20)
					            		{d=q1.getData();}	
					            	}
					            	pages=1;
				        		}
				        		else if(flag2==2)
				        		{
				        			int y1=0,y2=0,pages=0;
				        			if(isInteger(year2.getText())&&isInteger(year3.getText()))
						            {
						            	warning.setText(" ");
						            	y1=Integer.parseInt(year2.getText());
						            	y2=Integer.parseInt(year3.getText());
						            }
						            else
						            {
						            	warning.setText("Year field should be numbers");
						            }
						            q1.find(2, title.getText(),y1,y2,flag);
						              totalResults.setText("Total results = "+q1.getCount());
						            tableWorking=1;
						            DataRecords d= q1.getData();
						            while(d!=null && count<20)
						            {
						            	query1Table.addRow(new Object[]{count+1,d.getAuthor(),d.getTitle(),d.getPages(),d.getYear(),d.getVolume(),d.getJournalTitle(),d.getURL()});
					            		count++;
					            		if(count<20)
					            		{d=q1.getData();}	
					            	}
					            	pages=1;
				        		}	
				        		else
				        		{
				        			q1.find(2, title.getText(),0,9999,flag);
				        			  totalResults.setText("Total results = "+q1.getCount());
						            tableWorking=1;
						            DataRecords d= q1.getData();
						            while(d!=null && count<20)
						            {
						            	query1Table.addRow(new Object[]{count+1,d.getAuthor(),d.getTitle(),d.getPages(),d.getYear(),d.getVolume(),d.getJournalTitle(),d.getURL()});
					            		count++;
					            		if(count<20)
					            		{d=q1.getData();}	
					            	}
					            	pages=1;
				        		}
				        	}	
			        	}
			        } 
			        else
			        {
			        	warning.setText("Select option");
			        }
				}
			});
		reset.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					warning.setText(" ");
					tableWorking=0;
					query1Table.setRowCount(0);
					title.setText(" ");	
					year1.setText(" ");	
					year2.setText(" ");			
					year3.setText(" ");		
					searchBy.setSelectedItem("Search By");
					sortButtons.clearSelection();
					yearButtons.clearSelection();
					sortYear.setSelected(true);	
				}
			});
    }	
   
    public static boolean isInteger(String s) {
      boolean isValidInteger = false;
      try
      {
         Integer.parseInt(s);
         isValidInteger = true;
      }
      catch (NumberFormatException ex)
      {}
      return isValidInteger;
   }
}