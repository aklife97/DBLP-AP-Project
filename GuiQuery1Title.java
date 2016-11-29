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

public class GuiQuery1Title extends GuiQuery1
{
	private final JRadioButton sortRel = new JRadioButton("Sort by Relevance");
	private final JRadioButton sortYear = new JRadioButton("Sort by Date",true);
	private ButtonGroup sortButtons = new ButtonGroup();
	 private final JRadioButton sinceYear = new JRadioButton("For Since year");
	private final JRadioButton customYear = new JRadioButton("For custom year range");
	private ButtonGroup yearButtons = new ButtonGroup();

    public void setQueryTitle()
    {
    	flag=0;
    	flag2=0;
    	// q1=new Query1("dblp.xml");
    	sidePanel.removeAll();
    	displayPanel.removeAll();
    	searchBy.setSelectedItem("Title Tag");
    	try{
    		searchBy.removeItem("Search By");
    	} catch (Exception e){}
    	
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
    	// displayPanel.add(back);
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
		submit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					warning.setText(" ");
					totalResults.setText(" ");
					int count=0;
					query1Table.setRowCount(0);
					int y1=0,y2=0;
					if(flag2==1){
						if(isInteger(year1.getText())){
			            	warning.setText(" ");
			            	y1=Integer.parseInt(year1.getText());
			            } else {
				            	warning.setText("Year field should be numbers");
				            }
			            y2=9999;
					} else if (flag2==2) {
						if(isInteger(year2.getText())&&isInteger(year3.getText()))  {
			            	warning.setText(" ");
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
						            q.queryOneFind(2, title.getText(),y1,y2,flag);
						            totalResults.setText("Total results = "+q.queryOneGetCount());
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
			        	}else {}	
					}
			});
		reset.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					warning.setText(" ");
					tableWorking=0;
					query1Table.setRowCount(0);
					title.setText(" ");	
					year1.setText(" ");	
					year2.setText(" ");			
					year3.setText(" ");		
					sortButtons.clearSelection();
					yearButtons.clearSelection();
					sortYear.setSelected(true);	
				}
			});
    }	
}