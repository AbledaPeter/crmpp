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

import crmpp.csvreader.ReadCSV;

@SuppressWarnings("serial")
public class ReadCSV_GUI extends JFrame{

	private JLabel csvLabel;
	private JTextField csvTextField;
	private JButton browseButton;
	private JButton okButton;
	
	private final JPanel groupPanel;
	private final JPanel flowPanel;
	private GroupLayout groupLayout;
	private FlowLayout flowLayout;
	
	public ReadCSV_GUI(String title)
	{
		super(title);
		
		// initialize the components
		csvLabel = new JLabel("CSV Path:");
		csvLabel.setBorder(new EmptyBorder(0,0,0,10));
		
		csvTextField = new JTextField(50);
		
		browseButton = new JButton("Browse...");
		browseButton.setMargin(new Insets(2, 2, 2, 2));
		
		// open the browse dialog on clicking the 'Browse...' button
		browseButton.addActionListener(new CSVFileBrowser());
		
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
		            addComponent(csvLabel));
		hGroup.addGroup(groupLayout.createParallelGroup().
					addComponent(csvTextField).addComponent(flowPanel));
		hGroup.addGroup(groupLayout.createParallelGroup().
					addComponent(browseButton));
	            
		groupLayout.setHorizontalGroup(hGroup);
		
		GroupLayout.SequentialGroup vGroup = groupLayout.createSequentialGroup();
		vGroup.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).
		            addComponent(csvLabel).addComponent(csvTextField).addComponent(browseButton));
		vGroup.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE));
		vGroup.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).
		            addComponent(flowPanel));
		
		groupLayout.setVerticalGroup(vGroup);
		
		// read and process the file on clicking the 'OK' button
		okButton.addActionListener (new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				File f = new File(csvTextField.getText());
				
				if (!f.exists())
				{
					showFileNotFoundError();
				}
				else
				{
					// TODO: ha három CSV típusunk van (és hozzá 3 féle read fv.), akkor ide is fel kellene venni 3 gombot, ami nem szép
					// esetleg meg lehetne adni a CSV-ben egy fejlécet, amibõl kiderül, hogy milyen típusú
					ReadCSV reader = new ReadCSV();
					reader.readCRMUsersCSV(f);
				}
			}
		});
	}
	
	// Displays a popup error message regarding a non-existing file
	private void showFileNotFoundError() {
		JOptionPane.showMessageDialog(this, "The file on the specified path does not exist!", "File not found", JOptionPane.ERROR_MESSAGE);
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
	
	// Entry point for the application
	public static void main(String[] args) {
		ReadCSV_GUI gui = new ReadCSV_GUI("CSV Reader");
		gui.start();
	}

	// File browser that can see only CSV files and has the default directory "testfiles"
    private class CSVFileBrowser implements ActionListener {
        public void actionPerformed(ActionEvent e) {
          JFileChooser chooser = new JFileChooser("testfiles");
          
          FileNameExtensionFilter filter = new FileNameExtensionFilter(
        		    "CSV files", "csv");
        		chooser.setFileFilter(filter);
        		
          int result = chooser.showOpenDialog(ReadCSV_GUI.this);
          
          if (result == JFileChooser.APPROVE_OPTION) {
            csvTextField.setText(chooser.getSelectedFile().getPath());
          }
        }
      }
}
