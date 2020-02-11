package storybuilder;


import javax.swing.JTextField;


import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.awt.event.ActionEvent;



public class Search extends JFrame {
  private JTextField txtFileName;
  private final ButtonGroup buttonGroup = new ButtonGroup();
  private boolean isSearchClicked = false;
  private String fileName;
  public  static final String CHARACTERS = "Characters";
  public  static final String LOCATIONS = "Locations";
  public  static final String ITEMS = "Items";
  public  static final String EVENTS = "Events";
  private String type;
  
  //dropdown menu for which type of asset you are searching for. 
  JComboBox cmbFileType;
  
  //circular buttons, one for searching specifics, another for getting a random asset of the type 
  //you have selected in the dropdown menu
  JRadioButton SpecificSearch, NewRadioButton;
  
  public Search() {
    
      //get a content pane to hold objects/layers. 
    getContentPane().setLayout(null);
    
    txtFileName = new JTextField();
    
    //(I thought this was super cool. 
    //If you hover over the search box, this tool tip shows saying "Enter Name." 
    //Simple, but awesome. 
    
    txtFileName.setToolTipText("Enter name");
    txtFileName.setBounds(96, 124, 202, 20);
    getContentPane().add(txtFileName);
    txtFileName.setColumns(10);
    
    //create button for search, 
    JButton btnSearch = new JButton("Search");
    
    //actionlistener/event handler for when search is pressed. 
    btnSearch.addActionListener((ActionEvent e) -> {
        //if radio button for specific file is selected, get the text input.
        //Otherwise, try finding another asset of the type selected, and display a random one. 
        if(SpecificSearch.isSelected()) {
            
            //If its a specific search, the filename is the same as the txt file name
            fileName = txtFileName.getText();
            //regardless of what kind of search it is, the type is the selected item in the drop down menu.
            type = (String)cmbFileType.getSelectedItem();
        }else {
            type = (String)cmbFileType.getSelectedItem();
            try {
                //if its NOT A SPRECIFIC SEARCH, generate a random number, list the files, and select the value for the random file selected.
                List<File> fileList = FileUtil.readFileNamesinFolder(type);
                if(fileList.size() > 0) {
                    //create random object for random asset.
                    Random random = new Random();
                    int value = random.nextInt(fileList.size());
                    fileName = (fileList.get(value).getName()).split("\\.")[0];
                }
                 
           } catch (IOException e1) {
                
            }
        }
        isSearchClicked = true;
       
        //this closes the search window. ORIGINALLY I had System.exit(0),
        //but this would close the program completely. After doing some research
        //I learned about dispose.
        dispose();
    });
    btnSearch.setBounds(96, 189, 89, 23);
    getContentPane().add(btnSearch);
    
    //Random radial button creation. 
   NewRadioButton = new JRadioButton("Random Search");
    buttonGroup.add(NewRadioButton);
   NewRadioButton.setBounds(225, 80, 131, 23);
   NewRadioButton.setBackground(Color.GRAY);
   NewRadioButton.setForeground(Color.CYAN);
    getContentPane().add(NewRadioButton);
    
    //Event Handler actionlistener. 
    //When the random radial button is selected, dont allow user input into the filename box. 
    NewRadioButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e) {
       txtFileName.setEnabled(false);
      }
  });
  
    
    SpecificSearch = new JRadioButton("Specific Search");
    buttonGroup.add(SpecificSearch);
    SpecificSearch.setBounds(89, 80, 134, 23);
    SpecificSearch.setSelected(true);
    SpecificSearch.setBackground(Color.GRAY);
    SpecificSearch.setForeground(Color.CYAN);
    getContentPane().add(SpecificSearch);
    
    //when the specific search is selected,
    //this event handler sets the box to editable for user input for filename. 
    SpecificSearch.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e) {
       txtFileName.setEnabled(true);
      }
  });
    
    //drop down menu for selection of asset types to search for
    cmbFileType = new JComboBox();
    cmbFileType.setBounds(326, 124, 98, 20);
    cmbFileType.addItem(CHARACTERS);
    cmbFileType.addItem(LOCATIONS);
    cmbFileType.addItem(ITEMS);
    cmbFileType.addItem(EVENTS);
    getContentPane().add(cmbFileType);
    
    //Cancel button just closes the search window on click.. 
    JButton btnCancel = new JButton("Cancel");
    //event handler actionlistener.
    btnCancel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        dispose();
      }
    });
    
    btnCancel.setBounds(209, 189, 89, 23);
    getContentPane().add(btnCancel);
    
    JLabel lblName = new JLabel("Name");
    lblName.setBounds(40, 127, 46, 14);
    lblName.setForeground(Color.CYAN);
    getContentPane().setBackground(Color.GRAY);
    getContentPane().add(lblName);
    
    setVisible(true);
    
    
    
  }
  public String getFileName() {
    return fileName;
  }

  public boolean isSearchClicked() {
    return isSearchClicked;
  }
  public void setSearchClicked(boolean isSearchClicked) {
    this.isSearchClicked = isSearchClicked;
  }
  public String getT() {
    return type;
  }
  

}
