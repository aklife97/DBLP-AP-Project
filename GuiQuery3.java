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

public class GuiQuery3 extends GUIQuery
{
	private int flag=0,flag2=0,tableWorking=0,pages=0;
	private JLabel title = new JLabel("Name of Author");
	private JLabel title1 = new JLabel("Prediction after year");
	private JTextField authName =new JTextField();
  	private JTextField predYear =new JTextField();
	private DefaultTableModel query3Table= new DefaultTableModel();
	private JTable displayTable = new JTable(query3Table);
	private JScrollPane dispTable = new JScrollPane(displayTable);
	private JLabel warning = new JLabel(" ");	
	private JButton next = new JButton("NEXT");
	private JButton back = new JButton("BACK");
	// private Query3 q3 = null;

	public GuiQuery3(JFrame mainFrame, JComboBox<String> queries,JPanel sidePanel,JPanel displayPanel,QueryFacade q3)
	{
		this.mainFrame=mainFrame;
		this.queries=queries;
		this.sidePanel=sidePanel;
		this.displayPanel=displayPanel;
		this.q=q3;
	}

	public void initQuery()
	{
		submit=new JButton("Submit");
		reset=new JButton("Reset");
		submit.setBackground(Color.BLACK);
		reset.setBackground(Color.RED);
    	title.setFont(new Font("Calibri", Font.PLAIN, 12));
    	title.setBounds(30,80,130,20);
    	title1.setFont(new Font("Calibri", Font.PLAIN, 12));
    	title1.setBounds(30,105,130,20);
		authName.setBounds(170,80,50,20);
		predYear.setBounds(170,105,30,20);
 		submit.setBounds(30,155,80,30);
        reset.setBounds(140,155,80,30);
        submit.setFont(new Font("Calibri", Font.PLAIN, 12));
        reset.setFont(new Font("Calibri", Font.PLAIN, 12));
		displayTable.setDefaultEditor(Object.class, null);
		dispTable.setBounds(20,5,610,330);
		query3Table.addColumn("S.No.");
		query3Table.addColumn("Authors");
		query3Table.addColumn("Predicted Value");
    	warning.setForeground(Color.RED);
		warning.setBounds(30,340,190,20);
		warning.setFont(new Font("Calibri", Font.PLAIN, 12));
		next.setBounds(540,335,80,40);
		next.setBackground(Color.RED);
		next.setFont(new Font("Calibri", Font.PLAIN, 10));
		back.setBounds(30,335,80,40);
		back.setBackground(Color.BLACK);
		back.setFont(new Font("Calibri", Font.PLAIN, 10));
	}

    public void setQuery()
    {
    	queries.removeItem("Queries");
    	sidePanel.removeAll();
    	queries.setBounds(50,50,100,20);
    	queries.setSelectedItem("Query 3");
        queries.removeItem("Queries");
        displayPanel.removeAll();
    	displayPanel.add(dispTable);
		sidePanel.add(warning);
    	sidePanel.add(title);
    	sidePanel.add(queries);
    	sidePanel.add(title1);
    	sidePanel.add(predYear);
    	sidePanel.add(authName);
    	sidePanel.add(submit);
    	sidePanel.add(reset);
    	mainFrame.revalidate();
	 	mainFrame.repaint();
	 	submit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					query3Table.setRowCount(0);
					tableWorking=0;
		            if(isInteger(predYear.getText()))
		            {
		            	warning.setText(" ");
		            	q.queryThreeFind(Integer.parseInt(predYear.getText()),authName.getText());
		            	tableWorking=1;
		            	double a =q.queryThreeGetData();
		            	query3Table.addRow(new Object[]{1,authName.getText(),a});
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
					authName.setText(" ");
					predYear.setText(" ");
				}
			});
    }
}