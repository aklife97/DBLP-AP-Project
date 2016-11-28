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

public class GUI
{
	private JFrame mainFrame;
	private JPanel upperPanel;
	private JPanel sidePanel;
	private JPanel displayPanel;
	private JComboBox<String> queries;
	private JButton submit,reset;
	private GuiQuery1 g1;
	private GuiQuery2 g2;
	private GuiQuery3 g3;
	private Query1 q1=null;
	private Query2 q2=null;
	private Query3 q3=null;
	private int flag=0,flag2=0,tableWorking=0,pages=0;

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
		sidePanel.setVisible(true);
		upperPanel.setVisible(true);
		displayPanel.setVisible(true);
		sidePanel.setBounds(0,80,250,380);
		upperPanel.setBounds(0,0,900,80);
		displayPanel.setBounds(250,80,650,380);
		q1=new Query1("dblp.xml");
		q2=new Query2("dblp.xml");
		q3=new Query3("dblp.xml");
		g1=new GuiQuery1(mainFrame,queries,sidePanel,displayPanel,q1);
		g2=new GuiQuery2(mainFrame,queries,sidePanel,displayPanel,q2);
		g3=new GuiQuery3(mainFrame,queries,sidePanel,displayPanel,q3);
		mainFrame.add(sidePanel);
		mainFrame.add(upperPanel);
		mainFrame.add(displayPanel);
		mainFrame.setVisible(true); 
		//---
		queries.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent event) {
		    	// try{
		        	JComboBox<? extends Object> q = (JComboBox<? extends Object>) event.getSource();
		    	// }
		    	// catch(Exception e){ System.out.printl}
		        String selectedQuery = (String) q.getSelectedItem();
		        if (selectedQuery.equals("Query 1")) {
		            g1.setQuery1();
		        }     else if (selectedQuery.equals("Query 2")) {
		            g2.setQuery2();
		        } else if(selectedQuery.equals("Query 3")) {
		        	g3.setQuery3();
		        }
		    }
		});
	}

	public static void main(String[] args)
	{
		System.setProperty("jdk.xml.entityExpansionLimit", "0");
		AuthorManager.addFile("dblp.xml");
		AuthorManager.createMap();
		GUI a = new GUI();
		// System.clearProperty("jdk.xml.entityExpansionLimit");
	}

	public void change(int i)
	{
		flag=i;
	}
	public void changeMode(int i)
	{
		flag2=i;
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