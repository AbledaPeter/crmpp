package crmpp.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import crmpp.Application;

@SuppressWarnings("serial")
public class ReadCSV_GUI extends JFrame{
	private Application app;			// the application logic
	
	// user labels
	private JLabel usersLabel;
	private JLabel interestsLabel;
	private JLabel availabilitiesLabel;
	
	// user text fields
	private JTextField usersTextField;
	private JTextField interestsTextField;
	private JTextField availabilitiesTextField;
	
	// user buttons
	private JButton usersButton;
	private JButton interestsButton;
	private JButton availabilitiesButton;
	private JButton okButton;
	
	// user panels and layouts
	private JPanel groupPanel;
	private JPanel flowPanel;
	private GroupLayout groupLayout;
	private FlowLayout flowLayout;
	
	public ReadCSV_GUI(String title, Application _app)
	{
		super(title);
		app = _app;
		
		initUserTab();

	}
	
	// Initalizes the user-adding tab of the GUI
	public void initUserTab() {

		usersLabel = new JLabel("Users CSV:");
		usersLabel.setBorder(new EmptyBorder(0,0,0,10));
		interestsLabel = new JLabel("Interests CSV:");
		interestsLabel.setBorder(new EmptyBorder(0,0,0,10));
		availabilitiesLabel = new JLabel("Availabilities CSV:");
		availabilitiesLabel.setBorder(new EmptyBorder(0,0,0,10));
		
		usersTextField = new JTextField(50);
		interestsTextField = new JTextField(50);
		availabilitiesTextField = new JTextField(50);
		
		usersButton = new JButton("Browse...");
		interestsButton = new JButton("Browse...");
		availabilitiesButton = new JButton("Browse...");
		
		usersButton.addActionListener(new CSVFileBrowser(usersTextField));
		interestsButton.addActionListener(new CSVFileBrowser(interestsTextField));
		availabilitiesButton.addActionListener(new CSVFileBrowser(availabilitiesTextField));
		
		okButton = new JButton("OK");
		okButton.setMargin(new Insets(10, 10, 10, 10));
		
		groupPanel = new JPanel();
		groupLayout = new GroupLayout(groupPanel);
		groupPanel.setLayout(groupLayout);
		groupLayout.setAutoCreateContainerGaps(true);
		
		flowPanel = new JPanel();
		flowLayout = new FlowLayout(FlowLayout.CENTER);
		flowPanel.setLayout(flowLayout);
		flowPanel.add(okButton);
		
		groupPanel.add(flowPanel);
		
		// configure the layouts
		GroupLayout.SequentialGroup hGroup = groupLayout.createSequentialGroup();
		hGroup.addGroup(groupLayout.createParallelGroup().
		            addComponent(usersLabel).addComponent(interestsLabel).addComponent(availabilitiesLabel));
		hGroup.addGroup(groupLayout.createParallelGroup().
					addComponent(flowPanel).addComponent(usersTextField).addComponent(interestsTextField).addComponent(availabilitiesTextField));
		hGroup.addGroup(groupLayout.createParallelGroup().
					addComponent(usersButton).addComponent(interestsButton).addComponent(availabilitiesButton));
	            
		groupLayout.setHorizontalGroup(hGroup);
		
		GroupLayout.SequentialGroup vGroup = groupLayout.createSequentialGroup();
		vGroup.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).
		            addComponent(usersLabel).addComponent(usersTextField).addComponent(usersButton));
		vGroup.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).
	            addComponent(interestsLabel).addComponent(interestsTextField).addComponent(interestsButton));
		vGroup.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).
	            addComponent(availabilitiesLabel).addComponent(availabilitiesTextField).addComponent(availabilitiesButton));
		vGroup.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).
		            addComponent(flowPanel));
		
		groupLayout.setVerticalGroup(vGroup);
		
		// read and process the files when clicking the 'OK' button
		okButton.addActionListener (new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				File usersFile = new File(usersTextField.getText());
				
				// Check if every file is valid
				if (!usersFile.exists())
				{
					showFileNotFoundError("\"" + usersTextField.getText() + "\"");
					return;
				}
				
				File interestsFile = new File(interestsTextField.getText());
				
				if (!interestsFile.exists())
				{
					showFileNotFoundError("\"" + interestsTextField.getText() + "\"");
					return;
				}
				
				File availabilitiesFile = new File(availabilitiesTextField.getText());
				
				if (!availabilitiesFile.exists())
				{
					showFileNotFoundError("\"" + availabilitiesTextField.getText() + "\"");
					return;
				}
				
				try 
				{
					app.persistUserData(usersFile, interestsFile, availabilitiesFile);
				} 
				catch (Exception e1) 
				{
					e1.printStackTrace();
				}
				usersTextField.setText("");
				interestsTextField.setText("");
				availabilitiesTextField.setText("");
			}
		});
	}
	
	// Displays a popup error message regarding a non-existing file
	private void showFileNotFoundError(String filepath) {
		JOptionPane.showMessageDialog(this, "The file at path " + filepath +" does not exist!", "File not found", JOptionPane.ERROR_MESSAGE);
	}
	
	// Packs the frame and sets it visible
	public void start() {

		add(groupPanel);
		
    	setResizable(false);
    	
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-250, dim.height/2-100);
		
    	pack();
    	setVisible(true);
	}

	// File browser that can see only CSV files and has the default directory "testfiles"
    private class CSVFileBrowser implements ActionListener {
    	
    	// the text field to save the browsed file path to
    	private JTextField field;
    	
    	public CSVFileBrowser(JTextField _field) {
    		super();
    		field = _field;
    	}
    	
        public void actionPerformed(ActionEvent e) {
          JFileChooser chooser = new JFileChooser("testfiles");
          
          FileNameExtensionFilter filter = new FileNameExtensionFilter(
        		    "CSV files", "csv");
        		chooser.setFileFilter(filter);
        		
          int result = chooser.showOpenDialog(ReadCSV_GUI.this);
          
          if (result == JFileChooser.APPROVE_OPTION) {
        	  field.setText(chooser.getSelectedFile().getPath());
          }
        }
      }
}
