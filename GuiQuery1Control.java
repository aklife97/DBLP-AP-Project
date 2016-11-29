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

public class GuiQuery1Control extends GUIQuery
{
	private JComboBox<String> searchBy = new JComboBox<String>();

	public GuiQuery1Control(JFrame mainFrame, JComboBox<String> queries,JPanel sidePanel,JPanel displayPanel,QueryFacade q1)
	{
		this.mainFrame=mainFrame;
		this.queries=queries;
		this.sidePanel=sidePanel;
		this.displayPanel=displayPanel;
		this.q=q1;
		searchBy.addItem("Search By");
    	searchBy.addItem("Author Name");
    	searchBy.addItem("Title Tag");
    	searchBy.setSelectedItem("Search By");
    	searchBy.setBounds(50,50,100,20);
    	searchBy.setFont(new Font("Calibri", Font.PLAIN, 10));
	}
	
	public void initQuery()
	{
		
	}

    public void setQuery()
    {
    	queries.removeItem("Queries");
    	sidePanel.removeAll();
    	displayPanel.removeAll();
    	queries.setBounds(50,20,100,20);
    	queries.setSelectedItem("Query 1");
    	//----	
    	sidePanel.add(searchBy);
    	sidePanel.add(queries);
    	mainFrame.revalidate();
	 	mainFrame.repaint();

		searchBy.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent event) {
		        	JComboBox<? extends Object> t= (JComboBox<? extends Object>) event.getSource();
		        String selectedQuery = (String) t.getSelectedItem();
		        if (selectedQuery.equals("Author Name")) {
		           GuiQuery1Author ga= new GuiQuery1Author(mainFrame,queries,sidePanel,displayPanel,q,searchBy);
		           ga.setQueryAuthor();
		        }     else if (selectedQuery.equals("Title Tag")) {
		            GuiQuery1Title gt= new GuiQuery1Title(mainFrame,queries,sidePanel,displayPanel,q,searchBy);
		           gt.setQueryTitle();
		        }
		    }
		});
    }	
}