/**
*@file GuiQuery2.java
*This file contains GUI Query2 implementation
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
/**Creates the GUI needed for supporting Query2*/
public class GuiQuery2 extends GUIQuery
{
	private int flag=0,flag2=0,tableWorking=0,pages=0;
	private JLabel title = new JLabel("No. of Publications");
	private JTextField publk =new JTextField();
	private DefaultTableModel query2Table= new DefaultTableModel();
	private JTable displayTable = new JTable(query2Table);
	private JScrollPane dispTable = new JScrollPane(displayTable);
	private JLabel warning = new JLabel(" ");
	private JButton next = new JButton("NEXT");
	private JButton back = new JButton("BACK");
	private ArrayList<Author> res = new ArrayList<Author>();
	private JLabel totalResults = new JLabel(" ");

	public GuiQuery2(JFrame mainFrame, JComboBox<String> queries,JPanel sidePanel,JPanel displayPanel,QueryFacade q2)
	{
		this.mainFrame=mainFrame;
		this.queries=queries;
		this.sidePanel=sidePanel;
		this.displayPanel=displayPanel;
		this.q=q2;
	}

	public void initQuery(){
		publk.setBounds(170,80,50,20);
		displayTable.setDefaultEditor(Object.class, null);
		dispTable.setBounds(20,5,910,390);
		query2Table.addColumn("S.No.");
		query2Table.addColumn("Authors");
		warning.setFont(new Font("Calibri", Font.PLAIN, 12));
    	warning.setForeground(Color.RED);
		warning.setBounds(30,340,190,20);
		next.setBounds(840,395,80,40);
		next.setBackground(Color.RED);
		next.setFont(new Font("Calibri", Font.PLAIN, 10));
		back.setBounds(30,395,80,40);
		back.setBackground(Color.BLACK);
		back.setFont(new Font("Calibri", Font.PLAIN, 10));
		submit=new JButton("Submit");
		reset=new JButton("Reset");
		submit.setForeground(Color.WHITE);
		submit.setBackground(Color.BLACK);
		reset.setBackground(Color.RED);
		submit.setBounds(30,140,80,30);
 		reset.setBounds(140,140,80,30);
        submit.setFont(new Font("Calibri", Font.PLAIN, 12));
        reset.setFont(new Font("Calibri", Font.PLAIN, 12));
        totalResults.setFont(new Font("Calibri", Font.PLAIN, 10));
    	totalResults.setBounds(515,395,120,30);
    	queries.removeItem("Queries");
    	sidePanel.removeAll();
    	queries.setBounds(50,50,100,20);
    	queries.setSelectedItem("Query 2");
    	title.setFont(new Font("Calibri", Font.PLAIN, 12));
    	title.setBounds(30,80,130,30);
        queries.removeItem("Queries");
		displayPanel.removeAll();
		displayPanel.add(next);
    	displayPanel.add(dispTable);
		sidePanel.add(warning);
    	sidePanel.add(title);
    	sidePanel.add(queries);
    	sidePanel.add(publk);
    	sidePanel.add(submit);
    	sidePanel.add(reset);
    	mainFrame.revalidate();
	 	mainFrame.repaint();
	 	displayPanel.add(totalResults);
	 	reset.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					tableWorking=0;
					query2Table.setRowCount(0);
					publk.setText("");
					totalResults.setText("");
				}
			});
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
	 	submit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					totalResults.setText(" ");
					query2Table.setRowCount(0);
					int count=0;
					pages=0;
					tableWorking=0;
		            if(isInteger(publk.getText())){
		            	warning.setText(" ");
		            	q.queryTwoFind(Integer.parseInt(publk.getText()));
		            	if(q.queryTwoGetCount()==0){
			            	totalResults.setText("No Result Found");
			            } else{
			            	totalResults.setText("Total results = "+q.queryTwoGetCount());}
		            	tableWorking=1;
		            	Author a =q.queryTwoGetData();
		            	while(a!=null && count<20){
		            		query2Table.addRow(new Object[]{(count+1),a.getName()});
		            		a=q.queryTwoGetData();
		            		count++;}
		            	pages=1;
		            } else{
		            	warning.setText("Publications should be numbers"); }
				}
			});	
		next.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(tableWorking==1){
					Author a =q.queryTwoGetData();
					if(a!=null){	
						int count=0;
						query2Table.setRowCount(0);
		            	while(a!=null && count<20){
		            		query2Table.addRow(new Object[]{(pages*20)+(count+1),a.getName()});
		            		count++;
		            		if(count<20){
		            		a=q.queryTwoGetData();}	
		            	}
		            	pages+=1;}
            	} else{
            		tableWorking=0;}
			}
		});

		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

			}
		});
    }
}	