/**
*@file GUI.java
*This file contains class to initiate GUI making
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
/**Contains the base GUI class that loads the frame, also contains the projects main*/
public class GUI
{
	private JFrame mainFrame;
	private JPanel upperPanel;
	private JPanel sidePanel;
	private JPanel displayPanel;
	private JComboBox<String> queries;
	private JButton submit,reset;
	private GUIQuery g;
	private GUIFactory gFactory; 
	private QueryFacade q0= new QueryFacade();
	private int flag=0,flag2=0,tableWorking=0,pages=0;

	public GUI()
	{
		runFrame();
		initframe();
	}

	public void runFrame()
	{
		mainFrame=new JFrame("DBLP query engine");
		submit=new JButton("Submit");
		reset=new JButton("Reset");
		sidePanel=new JPanel();
		queries = new JComboBox<String>();
		displayPanel=new JPanel();
		queries.addItem("Queries");
		queries.addItem("Query 1");
		queries.addItem("Query 2");
		queries.addItem("Query 3");
		mainFrame.setSize(900,460); 
		mainFrame.setLocation(0,0);
		mainFrame.setResizable(false);
		mainFrame.setLayout(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		submit.setBackground(Color.BLACK);
		reset.setBackground(Color.RED);
		sidePanel.setPreferredSize(new Dimension(250,380));
		sidePanel.setMinimumSize(new Dimension(250,380));
		sidePanel.setMaximumSize(new Dimension(250,380));
		sidePanel.setLocation(0,80);
		sidePanel.setLayout(null);
		sidePanel.setBorder(BorderFactory.createLineBorder(Color.black,2));
		sidePanel.setBackground(Color.WHITE);
		displayPanel.setPreferredSize(new Dimension(650,380));
		displayPanel.setMinimumSize(new Dimension(450,380));
		displayPanel.setMaximumSize(new Dimension(450,380));
		displayPanel.setLocation(250,80);
		displayPanel.setLayout(null);
		displayPanel.setBorder(BorderFactory.createLineBorder(Color.black,2));
		displayPanel.setBackground(Color.WHITE);
		upperPanel=new JPanel();
		upperPanel.setSize(new Dimension(900,80));
		upperPanel.setMinimumSize(new Dimension(900,80));
		upperPanel.setMaximumSize(new Dimension(900,80));
		upperPanel.setLocation(0,0);
		upperPanel.setLayout(null);
	}

	public void initframe()
	{
		sidePanel.add(queries);
		queries.setBounds(50,100,100,20);
		queries.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel random = new JLabel("Results Here");
		random.setBounds(150,100,150,40);
		displayPanel.add(random);
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
		mainFrame.add(sidePanel);
		mainFrame.add(upperPanel);
		mainFrame.add(displayPanel);
		mainFrame.setVisible(true); 
		gFactory = new GUIFactory(mainFrame,queries,sidePanel,displayPanel,q0);
		queries.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent event) {
		        	JComboBox<? extends Object> q = (JComboBox<? extends Object>) event.getSource();
		        String selectedQuery = (String) q.getSelectedItem();
		        if (selectedQuery.equals("Query 1")) {
		           g=gFactory.getQuery("Query1");
		           g.start();
		        }     else if (selectedQuery.equals("Query 2")) {
		            g=gFactory.getQuery("Query2");
		            g.start();
		        } else if(selectedQuery.equals("Query 3")) {
		        	g=gFactory.getQuery("Query3");
		        	g.start();
		        }
		    }
		});
	}

	public static void main(String[] args)
	{
		System.setProperty("jdk.xml.entityExpansionLimit", "0");
		AuthorManager.addFile("dblp.xml");
		GuiLoading gl = new GuiLoading();
		AuthorManager.createMap();
		gl.stop();
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
