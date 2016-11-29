/**
*@file GuiQuery1Author.java
*This file contains GUI Query1 w.r.t Search by Author implementation
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
/**Creates the GUI Query1 search by author panel*/
public class GuiQuery1Author extends GuiQuery1
{
    private final JRadioButton sinceYear = new JRadioButton("For Since year");
	private final JRadioButton customYear = new JRadioButton("For custom year range");
	private ButtonGroup yearButtons = new ButtonGroup();

	public GuiQuery1Author(JFrame mainFrame, JComboBox<String> queries,JPanel sidePanel,JPanel displayPanel,QueryFacade q,JComboBox<String> searchBy)
	{
		super(mainFrame,queries,sidePanel,displayPanel,q,searchBy);
		super.start();
		yearButtons.add(sinceYear);
        yearButtons.add(customYear);
        sinceYear.setBounds(60,200,150,15);
        customYear.setBounds(60,215,150,15);
        sinceYear.setFont(new Font("Calibri", Font.PLAIN, 10));
        customYear.setFont(new Font("Calibri", Font.PLAIN, 10));
    	flag=0;
    	flag2=0;
    	searchBy.setSelectedItem("Author Name");
    	try{
    		searchBy.removeItem("Search By");
    	} catch (Exception e){}
    	sidePanel.removeAll();
    	displayPanel.removeAll();
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
    	sidePanel.add(submit);
    	sidePanel.add(reset);
    	sidePanel.add(warning);
    	sidePanel.add(sinceYear);
    	sidePanel.add(customYear); 	
    	initAuthor();
	}
	public void initState()
	{
		System.out.println("Flag2 in reset= "+flag2);
		warning.setText(" ");
		totalResults.setText("");
		tableWorking=0;
		flag2=0;
		flag=0;	
		query1Table.setRowCount(0);
		title.setText("");	
		year1.setText("");	
		year2.setText("");			
		year3.setText("");		
		yearButtons.clearSelection();
		changeMode(0);	
	}
	public void initAuthor()
	{	
		displayPanel.add(totalResults);
    	displayPanel.add(dispTable);
    	displayPanel.add(next);
    	// displayPanel.add(back);
    	mainFrame.revalidate();
	 	mainFrame.repaint();
	 	tableWorking=0;
		pages=0;
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
		reset.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					initState();}
			});
	}

    public void setQueryAuthor()
    {
		submit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				warning.setText(" ");
				totalResults.setText(" ");
				int count=0;
				query1Table.setRowCount(0);
				int y1=0,y2=0;
				if(flag2==1){
					if(isInteger(year1.getText())){
		            	y1=Integer.parseInt(year1.getText());
		            } else {
			            	warning.setText("Year field should be numbers");
			            }
		            y2=9999;
				} else if (flag2==2) {
					if(isInteger(year2.getText())&&isInteger(year3.getText()))  {
		            	y1=Integer.parseInt(year2.getText());
		            	y2=Integer.parseInt(year3.getText());
		            } else {
		            	warning.setText("Year field should be numbers");
		            }
				} else {
					y1=0;
					y2=9999;
				}
				if(warning.getText().equals(" ")){
					if(title.getText().length()==0){
		        		warning.setText("Author/Title field cannot be empty");
		        	}else{
		        		if(flag==0 || flag ==1){
			        			pages=0;
					            q.queryOneFind(1, title.getText(),y1,y2,flag);
					            if(q.queryOneGetCount()==0){
					            	totalResults.setText("No Result Found");
					            } else{
					           		totalResults.setText("Total results = "+q.queryOneGetCount());}
					            tableWorking=1;
					            DataRecords d= q.queryOneGetData();
					            while(d!=null && count<20) {
					            	query1Table.addRow(new Object[]{count+1,d.getAuthor(),d.getTitle(),d.getPages(),d.getYear(),d.getVolume(),d.getJournalTitle(),d.getURL()});
				            		count++;
				            		if(count<20)
				            		{d=q.queryOneGetData();}	
				            	}
				            	pages=1;
			        		}	
		        		}
		        	}else { }	
				}
		});
    }	
}