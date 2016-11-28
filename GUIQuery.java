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

public abstract class GUIQuery
{
	protected JFrame mainFrame;
	protected JPanel upperPanel;
	protected JPanel sidePanel;
	protected JPanel displayPanel;
	protected JComboBox<String> queries;
	protected JButton submit,reset;
	protected QueryFacade q=null;
	public abstract void initQuery();
	public abstract void setQuery();

	public final void start()
	{
		initQuery();
		setQuery();
	}

	public boolean isInteger(String s) {
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