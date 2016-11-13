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
}