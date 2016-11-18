import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class GUI
{
	private JFrame mainFrame;
	private JPanel upperPanel;
	private JPanel sidePanel;
	private JPanel displayPanel;
	private JComboBox<String> queries;
	private JButton submit,reset;
	// private DefaultTableModel modelTable;
	// private JTable displayTable;
	private int flag=0,flag2=0;

	public GUI()
	{
		initframe();
	}

	public void initframe()
	{
		mainFrame=new JFrame("DBLP query engine");
		mainFrame.setSize(900,460); 
		mainFrame.setLocation(0,0);
		mainFrame.setResizable(false);
		mainFrame.setLayout(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		submit=new JButton("Submit");
		reset=new JButton("Reset");
		submit.setBackground(Color.BLACK);
		reset.setBackground(Color.RED);
		// display=new JTable();
		//---
		sidePanel=new JPanel();
		sidePanel.setPreferredSize(new Dimension(250,380));
		sidePanel.setMinimumSize(new Dimension(250,380));
		sidePanel.setMaximumSize(new Dimension(250,380));
		sidePanel.setLocation(0,80);
		sidePanel.setLayout(null);
		sidePanel.setBorder(BorderFactory.createLineBorder(Color.black,2));
		sidePanel.setBackground(Color.WHITE);	
		queries = new JComboBox<String>();
		queries.addItem("Queries");
		queries.addItem("Query 1");
		queries.addItem("Query 2");
		queries.addItem("Query 3");
		sidePanel.add(queries);
		queries.setBounds(50,100,100,20);
		queries.setAlignmentX(Component.CENTER_ALIGNMENT);
		//---
		displayPanel=new JPanel();
		displayPanel.setPreferredSize(new Dimension(650,380));
		displayPanel.setMinimumSize(new Dimension(450,380));
		displayPanel.setMaximumSize(new Dimension(450,380));
		displayPanel.setLocation(250,80);
		displayPanel.setLayout(null);
		displayPanel.setBorder(BorderFactory.createLineBorder(Color.black,2));
		displayPanel.setBackground(Color.WHITE);
		JLabel random = new JLabel("Results Here");
		random.setBounds(150,100,150,40);
		displayPanel.add(random);	
		//---
		upperPanel=new JPanel();
		upperPanel.setSize(new Dimension(900,80));
		upperPanel.setMinimumSize(new Dimension(900,80));
		upperPanel.setMaximumSize(new Dimension(900,80));
		upperPanel.setLocation(0,0);
		upperPanel.setLayout(null);
		JLabel header=new JLabel("<html><b>DBLP Query Engine</b></html>",JLabel.CENTER);
		header.setFont(new Font("Calibri", Font.PLAIN, 45));
		header.setBounds(200,10,500,60);
		header.setAlignmentX(Component.CENTER_ALIGNMENT);
		upperPanel.add(header);
		upperPanel.setBorder(BorderFactory.createLineBorder(Color.black,2));
		upperPanel.setBackground(Color.WHITE);
		//---
		sidePanel.setVisible(true);
		upperPanel.setVisible(true);
		displayPanel.setVisible(true);
		sidePanel.setBounds(0,80,250,380);
		upperPanel.setBounds(0,0,900,80);
		displayPanel.setBounds(250,80,650,380);
		mainFrame.add(sidePanel);
		mainFrame.add(upperPanel);
		mainFrame.add(displayPanel);
		mainFrame.setVisible(true); 
		//---
		queries.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent event) {
		    	// try{
		        	JComboBox<String> q = (JComboBox<String>) event.getSource();
		    	// }
		    	// catch(Exception e){ System.out.printl}
		        String selectedQuery = (String) q.getSelectedItem();
		        if (selectedQuery.equals("Query 1")) {
		            setQuery1();
		        }     else if (selectedQuery.equals("Query 2")) {
		            setQuery2();
		        } else if(selectedQuery.equals("Query 3")) {
		        	setQuery3();
		        }
		    }
		});
	}

	public static void main(String[] args)
	{
		GUI a = new GUI();
	}

	public void change(int i)
	{
		flag=i;
	}
	public void changeMode(int i)
	{
		flag2=i;
	}

    private void setQuery1()
    {
    	flag=0;
    	flag2=0;
    	String ans="Query 1 - ";
    	queries.removeItem("Queries");
    	sidePanel.removeAll();
    	displayPanel.removeAll();
    	queries.setBounds(50,20,100,20);
    	queries.setSelectedItem("Query 1");
    	//----
    	DefaultTableModel query1Table= new DefaultTableModel();
		JTable displayTable = new JTable(query1Table);
		JScrollPane dispTable = new JScrollPane(displayTable);
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
		query1Table.addRow(new Object[]{"v1", "v2" , "v3" , "v4", "v5", "v6", "v7","v8"});
		//---
    	JComboBox<String> searchBy = new JComboBox<String>();
    	searchBy.addItem("Search By:");
    	searchBy.addItem("Author name");
    	searchBy.addItem("Title Tag");
    	searchBy.setSelectedItem("Search By");
    	searchBy.setBounds(50,50,100,20);
    	searchBy.setFont(new Font("Calibri", Font.PLAIN, 10));
    	JLabel title1 = new JLabel("Name / Title tags");
    	JLabel title2 = new JLabel("Since Year");
    	JLabel title3 = new JLabel("Custom Range");	
    	JLabel result = new JLabel();
    	JLabel warning = new JLabel(" ");
    	// JLabel warning2 = new JLabel();
    	JLabel dash = new JLabel("-");
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
    	// warning2.setBounds(30,360,190,20);
    	JTextField title =new JTextField();
    	JTextField year1 =new JTextField("YYYY");
    	JTextField year2 =new JTextField("YYYY");
    	JTextField year3 =new JTextField("YYYY");
    	year1.setHorizontalAlignment(JTextField.CENTER);
    	year2.setHorizontalAlignment(JTextField.CENTER);
    	year3.setHorizontalAlignment(JTextField.CENTER);
    	title.setBounds(140,90,70,20);
    	year1.setBounds(140,130,50,20);
    	year2.setBounds(110,155,50,20);
    	year3.setBounds(190,155,50,20);
    	final JRadioButton sortRel = new JRadioButton("Sort by Relevance");
    	final JRadioButton sortYear = new JRadioButton("Sort by Date",true);
    	ButtonGroup sortButtons = new ButtonGroup();
        sortButtons.add(sortRel);
        sortButtons.add(sortYear);
        sortYear.setBounds(60,200,150,15);
        sortRel.setBounds(60,215,150,15);
        sortYear.setFont(new Font("Calibri", Font.PLAIN, 10));
        sortRel.setFont(new Font("Calibri", Font.PLAIN, 10));

        final JRadioButton sinceYear = new JRadioButton("For Since year");
    	final JRadioButton customYear = new JRadioButton("For custom year range");
    	ButtonGroup yearButtons = new ButtonGroup();
        yearButtons.add(sinceYear);
        yearButtons.add(customYear);
        sinceYear.setBounds(60,245,150,15);
        customYear.setBounds(60,260,150,15);
        sinceYear.setFont(new Font("Calibri", Font.PLAIN, 10));
        customYear.setFont(new Font("Calibri", Font.PLAIN, 10));

        submit.setBounds(30,290,80,30);
        reset.setBounds(140,290,80,30);
        submit.setFont(new Font("Calibri", Font.PLAIN, 12));
        reset.setFont(new Font("Calibri", Font.PLAIN, 12));
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
    	// sidePanel.add(warning2);
    	displayPanel.add(dispTable);
    	mainFrame.revalidate();
	 	mainFrame.repaint();

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
				public void actionPerformed(ActionEvent e)
				{
					String res=" ";
					String selectedOption =(String) searchBy.getSelectedItem();
			        if (selectedOption.equals("Author Name")) {
			            res="selected - "+title.getText();
			        } else if (selectedOption.equals("Title Tag")) {
			            if(isInteger(year1.getText()))
			            {
			            	warning.setText(" ");
			            	res="since year - "+year1.getText();
			            }
			            else
			            {
			            	res=" ";
			            	warning.setText("Year field should be numbers");
			            }
			        } else if(selectedOption.equals("Custom Year Range")) {
			        	if(isInteger(year2.getText()) && isInteger(year3.getText()))
			            {
			            	warning.setText(" ");
			            	res="year between - "+year2.getText()+" and "+year3.getText();
			            }
			            else
			        	{
			                warning.setText("Year field should be numbers");
			            }
			        }
			        else
			        {
			        	res=" ";
			        	result.setText(" ");
			        	warning.setText("Select option");
			        }
			        String sortop=" ";
			        if(flag==0)
			        {
			        	sortop=" sort by year";
			        }
			        else
			        {
			        	sortop=" sort by relevance";	
			        }
			        if(warning.getText().equals(new String(" ")))
			        {
			        	result.setText(ans+res+sortop);
			        }
			         else
			        {
			        	result.setText(" ");
			        }
				}
			});
		reset.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					setQuery1();
				}
			});
    }	
    private void setQuery2()
    {
    	String ans="Query 2 - ";
    	queries.removeItem("Queries");
    	sidePanel.removeAll();
    	queries.setBounds(50,50,100,20);
    	queries.setSelectedItem("Query 2");
    	JLabel title = new JLabel("No. of Publications");
    	title.setFont(new Font("Calibri", Font.PLAIN, 12));
    	title.setBounds(30,80,130,30);
		JTextField publk =new JTextField();
		publk.setBounds(170,80,50,20);
 		submit.setBounds(30,140,80,30);
        reset.setBounds(140,140,80,30);
        submit.setFont(new Font("Calibri", Font.PLAIN, 12));
        reset.setFont(new Font("Calibri", Font.PLAIN, 12));
        queries.removeItem("Queries");
		displayPanel.removeAll();
		JLabel result = new JLabel();
		result.setFont(new Font("Calibri", Font.PLAIN, 15));
		result.setBounds(50,200,350,50);
		JLabel warning = new JLabel(" ");
		warning.setFont(new Font("Calibri", Font.PLAIN, 12));
    	warning.setForeground(Color.RED);
		warning.setBounds(30,340,190,20);
		sidePanel.add(warning);
		displayPanel.add(result);
    	sidePanel.add(title);
    	sidePanel.add(queries);
    	sidePanel.add(publk);
    	sidePanel.add(submit);
    	sidePanel.add(reset);
    	mainFrame.revalidate();
	 	mainFrame.repaint();
	 	submit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					String res=" ";
			        res="title - "+title.getText();
			            if(isInteger(publk.getText()))
			            {
			            	warning.setText(" ");
			            	res="since year - "+publk.getText();
			            }
			            else
			            {
			            	res=" ";
			            	warning.setText("Year field should be numbers");
			            }
			        
			        if(warning.getText().equals(new String(" ")))
			        {
			        	result.setText(ans+res);
			        }
			        else
			        {
			        	result.setText(" ");
			        }
				}
			});
		reset.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					setQuery2();
				}
			});
    }	
    private void setQuery3()
    {
    	queries.removeItem("Queries");
    	sidePanel.removeAll();
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