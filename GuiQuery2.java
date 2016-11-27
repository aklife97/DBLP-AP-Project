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

public class GuiQuery2
{
	private JFrame mainFrame;
	private JPanel upperPanel;
	private JPanel sidePanel;
	private JPanel displayPanel;
	private JComboBox<String> queries;
	private JButton submit,reset;
	private Query2 q2;
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

	public GuiQuery2(JFrame mainFrame, JComboBox<String> queries,JPanel sidePanel,JPanel displayPanel,Query2 q2)
	{
		this.mainFrame=mainFrame;
		this.queries=queries;
		this.sidePanel=sidePanel;
		this.displayPanel=displayPanel;
		this.q2=q2;
		initQuery2();
	}

	public void initQuery2()
	{
		publk.setBounds(170,80,50,20);
		displayTable.setDefaultEditor(Object.class, null);
		dispTable.setBounds(20,5,610,330);
		query2Table.addColumn("S.No.");
		query2Table.addColumn("Authors");
		warning.setFont(new Font("Calibri", Font.PLAIN, 12));
    	warning.setForeground(Color.RED);
		warning.setBounds(30,340,190,20);
		next.setBounds(540,335,80,40);
		next.setBackground(Color.RED);
		next.setFont(new Font("Calibri", Font.PLAIN, 10));
		back.setBounds(30,335,80,40);
		back.setBackground(Color.BLACK);
		back.setFont(new Font("Calibri", Font.PLAIN, 10));
		submit=new JButton("Submit");
		reset=new JButton("Reset");
		submit.setBackground(Color.BLACK);
		reset.setBackground(Color.RED);
		submit.setBounds(30,140,80,30);
 		reset.setBounds(140,140,80,30);
        submit.setFont(new Font("Calibri", Font.PLAIN, 12));
        reset.setFont(new Font("Calibri", Font.PLAIN, 12));
        totalResults.setFont(new Font("Calibri", Font.PLAIN, 10));
    	totalResults.setBounds(265,335,120,30);
	}

	public void change(int i)
	{
		flag=i;
	}
	public void changeMode(int i)
	{
		flag2=i;
	}

    public void setQuery2()
    {
    	// q2 = new Query2("dblp.xml");
    	queries.removeItem("Queries");
    	sidePanel.removeAll();
    	queries.setBounds(50,50,100,20);
    	queries.setSelectedItem("Query 2");
    	title.setFont(new Font("Calibri", Font.PLAIN, 12));
    	title.setBounds(30,80,130,30);
        queries.removeItem("Queries");
		displayPanel.removeAll();
		displayPanel.add(next);
    	displayPanel.add(back);
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
	 	
	 	submit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					totalResults.setText(" ");
					query2Table.setRowCount(0);
					int count=0;
					pages=0;
					tableWorking=0;
		            if(isInteger(publk.getText()))
		            {
		            	warning.setText(" ");
		            	System.out.println("here - "+Integer.parseInt(publk.getText()));
		            	q2.find(Integer.parseInt(publk.getText()));
		            	totalResults.setText("Total results = "+q2.getCount());
		            	tableWorking=1;
		            	// System.out.println("tableWorking - "+tableWorking);
		            	Author a =q2.getData();
		            	while(a!=null && count<20)
		            	{
		            		query2Table.addRow(new Object[]{(count+1),a.getName()});
		            		a=q2.getData();
		            		count++;
		            	}
		            	pages=1;
		            }
		            else
		            {
		            	warning.setText("Year field should be numbers");
		            }
				}
			});
		reset.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					tableWorking=0;
					query2Table.setRowCount(0);
					publk.setText(" ");
				}
			});
		next.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				// System.out.println(tableWorking);
				if(tableWorking==1)
				{
					Author a =q2.getData();
					if(a!=null)
					{	
						int count=0;
						query2Table.setRowCount(0);
						
		            	while(a!=null && count<20)
		            	{
		            		query2Table.addRow(new Object[]{(pages*20)+(count+1),a.getName()});
		            		count++;
		            		if(count<20)
		            		{a=q2.getData();}	
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