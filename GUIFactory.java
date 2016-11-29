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
import java.util.*;
import java.io.*;

public class GUIFactory
{
	private JFrame mainFrame;
	private JPanel sidePanel;
	private JPanel displayPanel;
	private JComboBox<String> queries;
	private QueryFacade q0=null;

	public GUIFactory(JFrame mainFrame, JComboBox<String> queries,JPanel sidePanel,JPanel displayPanel,QueryFacade q)
	{
		this.mainFrame=mainFrame;
		this.queries=queries;
		this.sidePanel=sidePanel;
		this.displayPanel=displayPanel;
		this.q0=q;
	}

	public GUIQuery getQuery(String queryName)
	{
		if(queryName==null){
			return null;
		}
		else if(queryName.equalsIgnoreCase("Query1")){
			return new GuiQuery1(mainFrame,queries,sidePanel,displayPanel,q0);
		} else if(queryName.equalsIgnoreCase("Query2")){
			return new GuiQuery2(mainFrame,queries,sidePanel,displayPanel,q0);
		} else if(queryName.equalsIgnoreCase("Query3")){
			return new GuiQuery3(mainFrame,queries,sidePanel,displayPanel,q0);
		}
		return null;
	}
}