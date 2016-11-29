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

public class GuiLoading
{
	private JFrame loadingFrame;
	private JPanel loadingPanel;
	private JLabel loadingLabel;

	public GuiLoading()
	{
		System.out.println("Gui Loading");
		loadingFrame=new JFrame("Loading");
		loadingFrame.setSize(250,45); 
		loadingFrame.setLocation(400,400);
		loadingFrame.setResizable(false);
		loadingFrame.setLayout(new BoxLayout (loadingFrame.getContentPane(), BoxLayout.Y_AXIS));
		loadingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loadingPanel=new JPanel();
		loadingLabel=new JLabel("<html><b>LOADING ... Please Wait</b></html>",JLabel.CENTER);
		loadingLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
		loadingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		loadingPanel.add(Box.createRigidArea(new Dimension(15,0)));
		loadingPanel.add(loadingLabel);
		loadingPanel.setVisible(true);
		loadingFrame.add(loadingPanel);
		loadingFrame.setVisible(true);
	}	

	public void stop()
	{
		loadingFrame.setVisible(false);
		loadingFrame.dispose();
	}

}