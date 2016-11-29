/**
*@file AuthorManager.java
*File to show the Loading Screen
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
/**Shows Loading*/
public class GuiLoading
{
	private JFrame loadingFrame;
	private JPanel loadingPanel;
	private JLabel loadingLabel;

	public GuiLoading()
	{
		System.out.println("Gui Loading");
		loadingFrame=new JFrame("Loading");
		loadingFrame.setSize(250,40); 
		loadingFrame.setLocation(600,270);
		loadingFrame.setResizable(false);
		loadingFrame.setLayout(new BoxLayout (loadingFrame.getContentPane(), BoxLayout.Y_AXIS));
		loadingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loadingPanel=new JPanel();
		loadingLabel=new JLabel("<html><b>Loading... Please Wait</b></html>",JLabel.CENTER);
		loadingLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
		loadingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		loadingPanel.add(Box.createRigidArea(new Dimension(20,7)));
		loadingPanel.add(loadingLabel);
		loadingPanel.setVisible(true);
		loadingFrame.add(loadingPanel);
	}

	public void start()
	{
		loadingFrame.setVisible(true);
	}	

	public void stop()
	{
		loadingFrame.setVisible(false);
	}

}