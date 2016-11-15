import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import javax.swing.border.Border;

public class GUI
{
	private JFrame mainFrame;
	private JPanel upperPanel;
	private JPanel sidePanel;
	private JPanel displayPanel;
	private JComboBox<String> queries;
	private JButton submit,reset;
	private int flag=0;

	public GUI()
	{
		initframe();
	}

	public void initframe()
	{
		mainFrame=new JFrame("DBLP query engine");
		mainFrame.setSize(700,500); 
		mainFrame.setLocation(0,0);
		mainFrame.setResizable(false);
		mainFrame.setLayout(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		submit=new JButton("Submit");
		reset=new JButton("Reset");
		submit.setBackground(Color.BLACK);
		reset.setBackground(Color.RED);
		//---
		sidePanel=new JPanel();
		sidePanel.setPreferredSize(new Dimension(250,380));
		sidePanel.setMinimumSize(new Dimension(250,380));
		sidePanel.setMaximumSize(new Dimension(250,380));
		sidePanel.setLocation(0,120);
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
		displayPanel.setPreferredSize(new Dimension(450,380));
		displayPanel.setMinimumSize(new Dimension(450,380));
		displayPanel.setMaximumSize(new Dimension(450,380));
		displayPanel.setLocation(250,120);
		displayPanel.setLayout(null);
		displayPanel.setBorder(BorderFactory.createLineBorder(Color.black,2));
		displayPanel.setBackground(Color.WHITE);
		JLabel random = new JLabel("Results Here");
		random.setBounds(150,100,150,40);
		displayPanel.add(random);	
		//---
		upperPanel=new JPanel();
		upperPanel.setSize(new Dimension(700,120));
		upperPanel.setMinimumSize(new Dimension(700,120));
		upperPanel.setMaximumSize(new Dimension(700,120));
		upperPanel.setLocation(0,0);
		upperPanel.setLayout(null);
		JLabel header=new JLabel("<html><b>DBLP Query Engine</b></html>",JLabel.CENTER);
		header.setFont(new Font("Calibri", Font.PLAIN, 45));
		header.setBounds(100,20,500,80);
		header.setAlignmentX(Component.CENTER_ALIGNMENT);
		upperPanel.add(header);
		upperPanel.setBorder(BorderFactory.createLineBorder(Color.black,2));
		upperPanel.setBackground(Color.WHITE);
		//---
		sidePanel.setVisible(true);
		upperPanel.setVisible(true);
		displayPanel.setVisible(true);
		sidePanel.setBounds(0,120,250,380);
		upperPanel.setBounds(0,0,700,120);
		displayPanel.setBounds(250,120,450,380);
		mainFrame.add(sidePanel);
		mainFrame.add(upperPanel);
		mainFrame.add(displayPanel);
		mainFrame.setVisible(true); 
		//---
		queries.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent event) {
		        JComboBox<String> q = (JComboBox<String>) event.getSource();
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

    private void setQuery1()
    {
    	String ans="Query 1 - ";
    	queries.removeItem("Queries");
    	sidePanel.removeAll();
    	displayPanel.removeAll();
    	queries.setBounds(50,40,100,20);
    	queries.setSelectedItem("Query 1");
    	JComboBox<String> searchBy = new JComboBox<String>();
    	searchBy.addItem("Search By:");
    	searchBy.addItem("Name/Title Tag");
    	searchBy.addItem("since Year");
    	searchBy.addItem("Custom Year Range");
    	searchBy.setSelectedItem("Search By");
    	searchBy.setBounds(50,80,100,20);
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
    	title1.setBounds(30,120,100,20);
    	title1.setBounds(30,120,100,20);
    	title2.setBounds(30,160,100,20);
    	title3.setBounds(30,200,100,20);
    	dash.setBounds(170,200,10,20);
    	result.setBounds(50,200,350,50);
    	warning.setBounds(30,340,190,20);
    	// warning2.setBounds(30,360,190,20);
    	JTextField title =new JTextField();
    	JTextField year1 =new JTextField("YYYY");
    	JTextField year2 =new JTextField("YYYY");
    	JTextField year3 =new JTextField("YYYY");
    	year1.setHorizontalAlignment(JTextField.CENTER);
    	year2.setHorizontalAlignment(JTextField.CENTER);
    	year3.setHorizontalAlignment(JTextField.CENTER);
    	title.setBounds(140,120,70,20);
    	year1.setBounds(140,160,50,20);
    	year2.setBounds(110,200,50,20);
    	year3.setBounds(190,200,50,20);
    	final JRadioButton sortRel = new JRadioButton("Sort by Relevance");
    	final JRadioButton sortYear = new JRadioButton("Sort by Year",true);
    	ButtonGroup sortButtons = new ButtonGroup();
        sortButtons.add(sortRel);
        sortButtons.add(sortYear);
        sortYear.setBounds(60,240,150,20);
        sortRel.setBounds(60,260,150,20);
        sortYear.setFont(new Font("Calibri", Font.PLAIN, 10));
        sortRel.setFont(new Font("Calibri", Font.PLAIN, 10));
        submit.setBounds(30,300,80,30);
        reset.setBounds(140,300,80,30);
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
    	// sidePanel.add(warning2);
    	displayPanel.add(result);
    	mainFrame.revalidate();
	 	mainFrame.repaint();

		submit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					String res=" ";
					String selectedOption = (String) searchBy.getSelectedItem();
			        if (selectedOption.equals("Name/Title Tag")) {
			            res="title - "+title.getText();
			        } else if (selectedOption.equals("since Year")) {
			            if(isInteger(year1.getText()))
			            {
			            	warning.setText(" ");
			            	res="since year - "+(String)year1.getText();
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
			            	res="year between - "+(String)year2.getText()+" and "+(String)year3.getText();
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