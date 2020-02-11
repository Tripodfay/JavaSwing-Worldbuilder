package storybuilder;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.border.*;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
//audio stuff maybe?
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.Clip; 
import java.io.File; 
import java.io.IOException; 
/* Goals of my project include:
-Making an application that will store, retrieve and allow for edits of Assets involved in world building for novels and games. 
-Feature a aesthetically pleasing viewing experience for the user, Potentially include night mode.
-Create a sub menu for the night mode. 
-Automatically create directories if none already exist for the assets.
-Make use of constructors for the assets, include the three basic properties, then change the application window to specifically fit the properties of the assets the users select. 
-Maybe make a sound clip or a funny mode that is more of a joke than a legitimate "feature" 
-Have a random search feature (mainly for events/locations) where clicking "random" will open a random file from the type of asset selected. 
-Either display the files by opening a notepad, or find a way to make them appear in the application window. 

Possible other features.. 
-SQL integration? 
-Animated logo?
-Colors on the panels/buttons? 
-Drop down menu instead of button-click menu? 

*/


public class Main extends JFrame {
    
    //Need text fields for Name, Location,description age, conflict, alliance, features, damage, legacy, type, rewards, outcome, trigger, backstory, relatives, abilities, allies, mastery, 
    //need panels for each of the assets, location, characters, items, events, and a panel to hold the buttons. 
    //need buttons for save... Update and Edit if I have time. 
  private JTextField txtName;
  private JTextField txtLocation;
  private JTextField txtAge;
  private JTextField txtConflict;
  private JPanel mainPanel, pnlCharacters, pnlLocations, pnlItems, pnlEvents, pnlButtons;
  private JTextField txtAlliance, txtFeatures, txtDamage, txtLegacy, txtType, txtRewards,
      txtTrigger, txtRelatives, txtAbilities, txtAllies;
  private JTextField txtMastery;
  private JButton btnSave, btnUpdate, btnEdit, btnReset, btnPlay, btnNull;
  
  public static final String CHARACTERS = "Characters";
  public static final String LOCATIONS = "Locations";
  public static final String ITEMS = "Items";
  public static final String EVENTS = "Events";
  
  public boolean isPlaying = false;
  public boolean isColorMode = false;

  JComboBox cmbKeyFactor;
  
   //Create a border for the boxes to have. 
 Border border = BorderFactory.createLineBorder(Color.BLACK);
  
  private JTextArea txtDescription, txtBackstory, txtOutcome;
  
  public static void main(String[] args) {
    Main m = new Main();
    m.setSize(800,725);
    m.setVisible(true);
  }
  
  public Main() {
   /* ALL ASSETS will have a name, a location, and a Description. Characters, Items, Locations, Events share these properties. */
    
    
    //Implement a nightmode potentially.
    
    
    //Create the menu bar with the singular File Category which only contains Exit.
    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    JMenu modeMenu = new JMenu("Modes");
    fileMenu.setMnemonic(WIDTH);
    JMenuItem exitMenuItem = new JMenuItem("Exit");
    JMenuItem nightMode = new JMenuItem("Night Mode");
    JMenuItem dayMode = new JMenuItem("Day Mode");
    JMenuItem colorMode = new JMenuItem("Party Mode");
   
   exitMenuItem.addActionListener((event)-> System.exit(0));
    modeMenu.add(nightMode);
    modeMenu.add(dayMode);
    modeMenu.add(colorMode);
    fileMenu.add(modeMenu);
    fileMenu.add(exitMenuItem);
   menuBar.add(fileMenu);
    setJMenuBar(menuBar);
      
      
    getContentPane().setLayout(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    JPanel panel = new JPanel();
    panel.setBackground(Color.GRAY);
    panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, Color.cyan, Color.GRAY ));
    panel.setBounds(0, 0, 780, 50);
    getContentPane().add(panel);
    panel.setLayout(null);

    //Set title to worldbuilders, Make it bold, big, and centered, add it to the panel
    JLabel lblWorldBuilders = new JLabel("World Builders");
    JLabel ByMe = new JLabel("by Cody Simants");
    ByMe.setForeground(Color.cyan);
    ByMe.setFont(new Font("Serif", Font.BOLD, 12));
    ByMe.setBounds(335, 29, 160, 14);
    lblWorldBuilders.setForeground(Color.cyan);
    lblWorldBuilders.setFont(new Font("Serif", Font.BOLD, 18));
    lblWorldBuilders.setBounds(320, 13, 160, 14);
    panel.add(ByMe);
    panel.add(lblWorldBuilders);
    //Add a search button to the top bar as well. (I couldnt quite figure out how to do a search bar) 
    JButton btnSearch = new JButton("Search");
    
    //upon clicking the button,use an action listener event handler to open the search window, search for user input, searching for the name/type of the asset. 
btnSearch.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Search search = new Search();
        search.setSize(550, 350);
        search.setVisible(true);
        search.addWindowListener(new WindowAdapter() {
          @Override
          public void windowClosed(WindowEvent e) {
            if (search.isSearchClicked()) {
              setFieldData(search.getFileName(), search.getT());
              setDisable();
              btnSave.setEnabled(false);
              btnUpdate.setEnabled(false);
              btnEdit.setEnabled(true);
            }
            super.windowClosed(e);
          }
        });

      }
    });
    
    
    
    //size the button and add it to the panel
    btnSearch.setBounds(600, 15, 89, 23);
    panel.add(btnSearch);

    mainPanel = new JPanel();
    mainPanel.setBounds(0, 50, 780, 522);
    mainPanel.setBackground(Color.GRAY.darker());
    mainPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED, Color.cyan, Color.GRAY ));
    getContentPane().add(mainPanel);
    
    getContentPane().add(mainPanel);
    GridBagLayout gbl_mainPanel = new GridBagLayout();
    gbl_mainPanel.columnWidths = new int[] {600, 0};
    gbl_mainPanel.rowHeights = new int[] {286, 215, 0, 0};
    gbl_mainPanel.columnWeights = new double[] {1.0, Double.MIN_VALUE};
    gbl_mainPanel.rowWeights = new double[] {0.0, 0.0, 0.0, Double.MIN_VALUE};
    mainPanel.setLayout(gbl_mainPanel);

    JPanel panelCommon = new JPanel();
    panelCommon.setBounds(0, 30, 300, 400);
    panelCommon.setBorder(new EtchedBorder(EtchedBorder.RAISED, Color.cyan, Color.GRAY ));
    panelCommon.setBackground(Color.GRAY);

    
    //add in input textfield and size it, add to panel
    txtName = new JTextField();
    txtName.setToolTipText("Enter name (this will also be the name of the file)");
    txtName.setBounds(250, 50, 275, 20);
    panelCommon.add(txtName);
    txtName.setBorder(border);
    txtName.setColumns(10);

   
    //Set the label for the name, add it to the panel 
    JLabel lblNewLabel = new JLabel("Name");
    lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
    lblNewLabel.setBounds(10, 53, 216, 14);
    lblNewLabel.setForeground(Color.CYAN);
    panelCommon.add(lblNewLabel);
    
    //Add label for Location add label to panel
    JLabel lblNewLabel_1 = new JLabel("Location");
    lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
    lblNewLabel_1.setBounds(10, 88, 216, 14);
    lblNewLabel_1.setForeground(Color.CYAN);
    panelCommon.add(lblNewLabel_1);

    //add input text field for location add to panel
    txtLocation = new JTextField();
    txtLocation.setToolTipText("Enter Location (City? region? Plane of Existence?)");
    txtLocation.setBounds(250, 85, 275, 20);
    txtLocation.setBorder(border);
    panelCommon.add(txtLocation);
    txtLocation.setColumns(10);

    //Create a text area for the description. Largest field = largest input area. Add it to the panel. Add border for looks. 
    txtDescription = new JTextArea();
    txtDescription.setToolTipText("Enter Description(hair color? Scars? Beard? etc)");
    txtDescription.setBounds(250, 125, 275, 100);
    txtDescription.setBorder(border);
    txtDescription.setLineWrap(true);
    txtDescription.setWrapStyleWord(true);
    panelCommon.add(txtDescription);

    JLabel lblDescription = new JLabel("Description");
    lblDescription.setHorizontalAlignment(SwingConstants.TRAILING);
    lblDescription.setBounds(0, 126, 226, 14);
    lblDescription.setForeground(Color.CYAN);
    panelCommon.add(lblDescription);

    //Create a dropdown menu combo box with all the assets (Character, Location, Items, Events, then add it to panel
    cmbKeyFactor = new JComboBox();
    cmbKeyFactor.addItem(CHARACTERS);
    cmbKeyFactor.addItem(LOCATIONS);
    cmbKeyFactor.addItem(ITEMS);
    cmbKeyFactor.addItem(EVENTS);
    panelCommon.add(cmbKeyFactor);

    //actionListener event handler for changing to the selected item from the dropdown menu.
    cmbKeyFactor.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        setPaneltoMainPanel((String) cmbKeyFactor.getSelectedItem());
      }
    });
    cmbKeyFactor.setBounds(303, 19, 179, 20);
    cmbKeyFactor.setMaximumRowCount(200);


//Label for dropdown menu. 
    JLabel lblSelectAKey = new JLabel("Select a story asset");
    lblSelectAKey.setHorizontalAlignment(SwingConstants.TRAILING);
    lblSelectAKey.setBounds(0, 22, 226, 14);
    lblSelectAKey.setForeground(Color.CYAN);
    panelCommon.add(lblSelectAKey);
    
//Utilizing grid bag constraints (https://www.cis.upenn.edu/~bcpierce/courses/629/jdkdocs/api/java.awt.GridBagConstraints.html)     
    GridBagConstraints gbc_panelCommon = new GridBagConstraints();
    gbc_panelCommon.fill = GridBagConstraints.BOTH;
    gbc_panelCommon.insets = new Insets(5, 5, 5, 5);
    gbc_panelCommon.gridx = 0;
    gbc_panelCommon.gridy = 0;
    mainPanel.add(panelCommon, gbc_panelCommon);
    panelCommon.setLayout(null);


//Create panels for each of the asset choices
    pnlCharacters = new JPanel();
    pnlCharacters.setLayout(null);
    pnlCharacters.setBorder(new EtchedBorder(EtchedBorder.RAISED, Color.cyan, Color.GRAY ));
    pnlCharacters.setBackground(Color.GRAY);
    
    pnlLocations = new JPanel();
    pnlLocations.setLayout(null);
    pnlLocations.setBorder(new EtchedBorder(EtchedBorder.RAISED, Color.cyan, Color.GRAY ));
    pnlLocations.setBackground(Color.GRAY);
    
    pnlEvents = new JPanel();
    pnlEvents.setLayout(null);
    pnlEvents.setBorder(new EtchedBorder(EtchedBorder.RAISED, Color.cyan, Color.GRAY ));
   pnlEvents.setBackground(Color.GRAY);
    
    pnlItems = new JPanel();
    pnlItems.setLayout(null);
    pnlItems.setBorder(new EtchedBorder(EtchedBorder.RAISED, Color.cyan, Color.GRAY ));
    pnlItems.setBackground(Color.GRAY);
    
      //Nightmode
    nightMode.addActionListener((event-> {

      panelCommon.setBackground(Color.GRAY);
      mainPanel.setBackground(Color.GRAY.darker());
      panel.setBackground(Color.GRAY);
      pnlItems.setBackground(Color.GRAY);
      pnlEvents.setBackground(Color.GRAY);
      pnlLocations.setBackground(Color.GRAY);
      pnlCharacters.setBackground(Color.GRAY);
      btnPlay.setVisible(false);
      btnPlay.setEnabled(false);
      
        }));
    
    //Daymode 
    dayMode.addActionListener((event-> {

      panelCommon.setBackground(Color.white);
      mainPanel.setBackground(Color.white);
      panel.setBackground(Color.white);
      pnlItems.setBackground(Color.white);
      pnlEvents.setBackground(Color.white);
      pnlLocations.setBackground(Color.white);
      pnlCharacters.setBackground(Color.white);
      btnPlay.setVisible(false);
      btnPlay.setEnabled(false);
        }));
    
    //Colormode 
    colorMode.addActionListener((event-> {
      isColorMode = true;
      isPlaying = true; 
      panelCommon.setBackground(Color.pink);
      mainPanel.setBackground(Color.cyan);
      panel.setBackground(Color.yellow);
      pnlItems.setBackground(Color.blue);
      pnlEvents.setBackground(Color.white);
      pnlLocations.setBackground(Color.pink);
      pnlCharacters.setBackground(Color.orange);
      btnPlay.setVisible(true);
      btnPlay.setEnabled(true);

        }));
    
    
    //Set the panel to whichever option the user selects. Run the methods to generate the new panels. 
    setPaneltoMainPanel(CHARACTERS);
    generateCharactersPanel();
    generateLocationPanel();
    generateItemPanel();
    generateEventsPanel();
    generateButtons();
  }
  
  public void Play() throws IOException{
    try{
       String filePath = "C:\\Users\\Artmu\\OneDrive\\Desktop\\StoryBuilder\\StoryBuilder\\src\\storybuilder\\meow.wav";  
       Clip music;

      AudioInputStream audio = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
      music = AudioSystem.getClip();
      music.open(audio);
      music.start();   

    } catch (Exception e){
        e.printStackTrace();
    }
  };

public void generateCharactersPanel() {
      
      /* Characters are the biggest assets, They require Age, Backstory, Abilities, Allies, Relatives as well as 
      the three basic properties from the main function. Name, Description, Location*/
      
    JLabel lblAge = new JLabel("Age");
    lblAge.setHorizontalAlignment(SwingConstants.TRAILING);
    lblAge.setBounds(0, 10, 225, 14);
    lblAge.setForeground(Color.CYAN);
    pnlCharacters.add(lblAge);

    txtAge = new JTextField();
    txtAge.setToolTipText("Enter age");
    txtAge.setBounds(250, 7, 281, 20);
    txtAge.setBorder(border);
    pnlCharacters.add(txtAge);
    txtAge.setColumns(10);

    JLabel lblBackstory = new JLabel("Backstory");
    lblBackstory.setHorizontalAlignment(SwingConstants.TRAILING);
    lblBackstory.setBounds(10, 35, 216, 14);
    lblBackstory.setForeground(Color.CYAN);
    pnlCharacters.add(lblBackstory);
    

    txtBackstory = new JTextArea();
    txtBackstory.setToolTipText("Enter Backstory");
    txtBackstory.setColumns(10);
    txtBackstory.setBounds(251, 32, 281, 50);
    txtBackstory.setLineWrap(true);
    txtBackstory.setBorder(border);
    txtBackstory.setWrapStyleWord(true);
    pnlCharacters.add(txtBackstory);

    JLabel lblRelatives = new JLabel("Relatives");
    lblRelatives.setHorizontalAlignment(SwingConstants.TRAILING);
    lblRelatives.setBounds(10, 91, 216, 14);
    lblRelatives.setForeground(Color.CYAN);
    pnlCharacters.add(lblRelatives);

    txtRelatives = new JTextField();
    txtRelatives.setToolTipText("Enter Relatives");
    txtRelatives.setColumns(10);
    txtRelatives.setBorder(border);
    txtRelatives.setBounds(250, 89, 281, 20);
    pnlCharacters.add(txtRelatives);

    txtAbilities = new JTextField();
    txtAbilities.setToolTipText("Enter Abilities(Strong, Fast, Flight etc)");
    txtAbilities.setColumns(10);
    txtAbilities.setBounds(250, 120, 281, 20);
    txtAbilities.setBorder(border);
    pnlCharacters.add(txtAbilities);

    JLabel lblAbilities = new JLabel("Abilities");
    lblAbilities.setHorizontalAlignment(SwingConstants.TRAILING);
    lblAbilities.setBounds(10, 123, 216, 14);
    lblAbilities.setForeground(Color.CYAN);
    pnlCharacters.add(lblAbilities);

    JLabel lblAllies = new JLabel("Allies");
    lblAllies.setHorizontalAlignment(SwingConstants.TRAILING);
    lblAllies.setBounds(10, 155, 216, 14);
    lblAllies.setForeground(Color.CYAN);
    pnlCharacters.add(lblAllies);

    txtAllies = new JTextField();
    txtAllies.setToolTipText("Enter Allies(Friends, guardians, underlings)");
    txtAllies.setColumns(10);
    txtAllies.setBorder(border);
    txtAllies.setBounds(250, 151, 281, 20);
    pnlCharacters.add(txtAllies);

  }


  public void generateLocationPanel() {
    JLabel lblLocations = new JLabel("Conflict");
    lblLocations.setHorizontalAlignment(SwingConstants.TRAILING);
    lblLocations.setBounds(10, 30, 216, 14);
    lblLocations.setForeground(Color.CYAN);
    pnlLocations.add(lblLocations);

    txtConflict = new JTextField();
    txtConflict.setToolTipText("Enter Conflict(Rabid bear attacks, Starvation)");
    txtConflict.setBounds(250, 27, 281, 20);
    txtConflict.setBorder(border);
    pnlLocations.add(txtConflict);
    txtConflict.setColumns(10);

    JLabel label = new JLabel("Alliance");
    label.setHorizontalAlignment(SwingConstants.TRAILING);
    label.setBounds(10, 61, 216, 14);
    label.setForeground(Color.CYAN);
    pnlLocations.add(label);

    txtAlliance = new JTextField();
    txtAlliance.setToolTipText("Enter Alliance (loyal to: allies with:)");
    txtAlliance.setColumns(10);
    txtAlliance.setBorder(border);
    txtAlliance.setBounds(250, 58, 281, 20);
    pnlLocations.add(txtAlliance);

    JLabel lblFeatures = new JLabel("Features");
    lblFeatures.setHorizontalAlignment(SwingConstants.TRAILING);
    lblFeatures.setBounds(10, 91, 216, 14);
    lblFeatures.setForeground(Color.CYAN);
    pnlLocations.add(lblFeatures);

    txtFeatures = new JTextField();
    txtFeatures.setToolTipText("Enter Features(Mountains, rivers, snow, etc)");
    txtFeatures.setColumns(10);
    txtFeatures.setBorder(border);
    txtFeatures.setBounds(250, 89, 281, 20);
    pnlLocations.add(txtFeatures);


  }

  public void generateItemPanel() {
    JLabel lblDamage = new JLabel("Damage");
    lblDamage.setHorizontalAlignment(SwingConstants.TRAILING);
    lblDamage.setBounds(10, 30, 216, 14);
    lblDamage.setForeground(Color.CYAN);
    pnlItems.add(lblDamage);

    txtDamage = new JTextField();
    txtDamage.setToolTipText("Enter damage(Numbers, type of damage, how often)");
    txtDamage.setBounds(250, 27, 281, 20);
    txtDamage.setColumns(10);
    txtDamage.setBorder(border);
    pnlItems.add(txtDamage);

    JLabel lblLegacy = new JLabel("Legacy");
    lblLegacy.setHorizontalAlignment(SwingConstants.TRAILING);
    lblLegacy.setBounds(10, 61, 216, 14);
    lblLegacy.setForeground(Color.CYAN);
    pnlItems.add(lblLegacy);

    txtLegacy = new JTextField();
    txtLegacy.setToolTipText("Enter legacy (item history? times used? notable events)");
    txtLegacy.setColumns(10);
    txtLegacy.setBorder(border);
    txtLegacy.setBounds(250, 58, 281, 20);
    pnlItems.add(txtLegacy);

    JLabel lblType = new JLabel("Type");
    lblType.setHorizontalAlignment(SwingConstants.TRAILING);
    lblType.setBounds(10, 91, 216, 14);
    lblType.setForeground(Color.CYAN);
    pnlItems.add(lblType);

    txtType = new JTextField();
    txtType.setToolTipText("Enter type of object (Weapon, tool, currency)");
    txtType.setColumns(10);
    txtType.setBorder(border);
    txtType.setBounds(250, 89, 281, 20);
    pnlItems.add(txtType);

    txtMastery = new JTextField();
    txtMastery.setToolTipText("Enter mastery (requirements to use? difficulty to use?)");
    txtMastery.setColumns(10);
    txtMastery.setBorder(border);
    txtMastery.setBounds(250, 120, 281, 20);
    pnlItems.add(txtMastery);

    JLabel lblMastery = new JLabel("Mastery");
    lblMastery.setHorizontalAlignment(SwingConstants.TRAILING);
    lblMastery.setBounds(10, 123, 216, 14);
    lblMastery.setForeground(Color.CYAN);
    pnlItems.add(lblMastery);


  }


  public void generateEventsPanel() {
    JLabel lblTrigger = new JLabel("Trigger");
    lblTrigger.setHorizontalAlignment(SwingConstants.TRAILING);
    lblTrigger.setBounds(10, 30, 216, 14);
    lblTrigger.setForeground(Color.CYAN);
    pnlEvents.add(lblTrigger);

    txtTrigger = new JTextField();
    txtTrigger.setToolTipText("Enter trigger(What causes the event)");
    txtTrigger.setBounds(250, 27, 281, 20);
    txtTrigger.setBorder(border);
    pnlEvents.add(txtTrigger);
    txtTrigger.setColumns(10);

    JLabel lblRewards = new JLabel("Rewards");
    lblRewards.setHorizontalAlignment(SwingConstants.TRAILING);
    lblRewards.setBounds(10, 61, 216, 14);
    lblRewards.setForeground(Color.CYAN);
    pnlEvents.add(lblRewards);

    txtRewards = new JTextField();
    txtRewards.setToolTipText("Enter rewards gained from completing the event");
    txtRewards.setColumns(10);
    txtRewards.setBorder(border);
    txtRewards.setBounds(250, 58, 281, 20);
    pnlEvents.add(txtRewards);

    JLabel lblOutcome = new JLabel("Outcome");
    lblOutcome.setHorizontalAlignment(SwingConstants.TRAILING);
    lblOutcome.setBounds(10, 91, 216, 14);
    lblOutcome.setForeground(Color.CYAN);
    pnlEvents.add(lblOutcome);
    
    txtOutcome = new JTextArea();
    txtOutcome.setToolTipText("Enter outcome (Aftereffects of encountering this event for the character or the world");
    txtOutcome.setColumns(10);
    txtOutcome.setBorder(border);
    txtOutcome.setBounds(250, 89, 281, 100);
    txtOutcome.setLineWrap(true);
    txtOutcome.setWrapStyleWord(true);
    pnlEvents.add(txtOutcome);

  }

  public void setPaneltoMainPanel(String selectedItem) {

    GridBagConstraints gbc_pnlCharacters = new GridBagConstraints();
    gbc_pnlCharacters.insets = new Insets(3, 5, 5, 5);
    gbc_pnlCharacters.fill = GridBagConstraints.BOTH;
    gbc_pnlCharacters.gridx = 0;
    gbc_pnlCharacters.gridy = 1;

    //ensures none of the panels are already up
    mainPanel.remove(pnlCharacters);
    mainPanel.remove(pnlLocations);
    mainPanel.remove(pnlItems);
    mainPanel.remove(pnlEvents);
    
    //If characters is selected, display charcter panel, else display selected panel
    if (CHARACTERS.equals(selectedItem)) {
      
      mainPanel.add(pnlCharacters, gbc_pnlCharacters);
    } else if (LOCATIONS.equals(selectedItem)) {
      
      mainPanel.add(pnlLocations, gbc_pnlCharacters);
    } else if (ITEMS.equals(selectedItem)) {
      
      mainPanel.add(pnlItems, gbc_pnlCharacters);
    } else if (EVENTS.equals(selectedItem)) {
      
      mainPanel.add(pnlEvents, gbc_pnlCharacters);
    }
    
    //If using any containers in Swing that continually changes, use revalidate/repaint to refresh the gui and keep it updated
    mainPanel.revalidate();
    mainPanel.repaint();
  }

  public void generateButtons() {
    //Create a new panel for the button bar
    pnlButtons = new JPanel();
    pnlButtons.setBounds(0, 600, 0, 0);
    getContentPane().add(pnlButtons);
    pnlButtons.setSize(650, 25);
    pnlButtons.setBackground(Color.GRAY);
    //gridlayout (rows, Columns) 1 row of 3 columns
    pnlButtons.setLayout(new GridLayout(1, 3));
    pnlButtons.add(new JPanel());
    pnlButtons.add(new JPanel());
    
        //This null button is just because it looked wierd with 4 buttons, and only the FAR right button invisible. but having 5 buttons with the outer 2 invisible looks good. 
        // Aesthetics.
        
    btnNull = new JButton("a");
    pnlButtons.add(btnNull);
    //save button calles save/update method
    btnSave = new JButton("Save");
    btnSave.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        saveOrUpdate();
      }
    });
    pnlButtons.add(btnSave);
    
    //edit button enables the chat boxes to be editted on a searched file. 
    btnEdit = new JButton("Edit");
    btnEdit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        setEnable();
        btnUpdate.setEnabled(true);
      }
    });
    pnlButtons.add(btnEdit);
    


    //update and save essentailly do the same thing, update is used for a file that already exists with the name.
    btnUpdate = new JButton("Update");
    btnUpdate.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        saveOrUpdate();
      }
    });
    pnlButtons.add(btnUpdate);
    
    btnReset = new JButton("Reset");
    btnReset.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent e){
           reset();        
       } 
    });
    pnlButtons.add(btnReset);    
  
    
    btnPlay = new JButton("Play");
    btnPlay.addActionListener(new ActionListener() {
     public void actionPerformed(ActionEvent e){
         try{
          Play();

          } catch (Exception e3){
         
       } 
    }});

    
    pnlButtons.add(btnPlay);
    
    
    //Edit and Update should be greyed out if there is not a searched file. 
    btnPlay.setEnabled(false);
    btnPlay.setVisible(false);
    btnEdit.setEnabled(false);
    btnUpdate.setEnabled(false);
    btnReset.setEnabled(true);
    btnNull.setVisible(false);
    btnNull.setEnabled(false);
  }

  
  public void saveOrUpdate() {
    String type = (String) cmbKeyFactor.getSelectedItem();

    //Validation check to ensure Name is not null, EVERYTHING MUST BE NAMED.
    if(txtName.getText() == null || txtName.getText().isEmpty()) {
      JOptionPane.showMessageDialog(this, "Name cannot be null", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }
    //Try catch statement for the exception thrown. 
    try {
        //If characters is selected from the drop down menu set/get all the information
    if (CHARACTERS.equals(type)) {
      Characters characters = new Characters();
      characters.setName(txtName.getText());
      characters.setLocation(txtLocation.getText());
      characters.setDescription(txtDescription.getText());
      characters.setAbilities(txtAbilities.getText());
      characters.setAge(txtAge.getText());
      characters.setAllies(txtAllies.getText());
      characters.setBackstory(txtBackstory.getText());
      characters.setRelatives(txtRelatives.getText());

       //Using my file Utility package, storybuilder.util This "savefiles" the character. 
        FileUtil.saveFile(characters);
     
    }

        //If the type of asset in the dropdown menu is a location.....
    if (LOCATIONS.equals(type)) {
      Locations locations = new Locations();

     
      locations.setName(txtName.getText());
      locations.setLocation(txtLocation.getText());
      locations.setDescription(txtDescription.getText());
      locations.setFeatures(txtFeatures.getText());
      locations.setAlliance(txtAlliance.getText());
      locations.setConflict(txtConflict.getText());
      
        FileUtil.saveFile(locations);
     
      //if the type of asset is an Event........
    } else if (EVENTS.equals(type)) {
      Events events = new Events();

      events.setName(txtName.getText());
      events.setLocation(txtLocation.getText());
      events.setDescription(txtDescription.getText());
      events.setOutcome(txtOutcome.getText());
      events.setRewards(txtRewards.getText());
      events.setTrigger(txtTrigger.getText());
     
      FileUtil.saveFile(events);
     
      //if the type of asset is an Item.......
    } else if (ITEMS.equals(type)) {
      Items items = new Items();


      items.setName(txtName.getText());
      items.setLocation(txtLocation.getText());
      items.setDescription(txtDescription.getText());
      items.setDamage(txtDamage.getText());
      items.setLegacy(txtLegacy.getText());
      items.setMastery(txtMastery.getText());
      items.setType(txtType.getText());
     
        FileUtil.saveFile(items);
      
    }//catch the exception, display an error. 
    }catch (IOException e) {
      JOptionPane.showMessageDialog(this, "Error Save or update "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
      e.printStackTrace();
    }
    //if the file is saved correctly, add success message
    JOptionPane.showMessageDialog(this, "Successfully save or updated ", "Success", JOptionPane.INFORMATION_MESSAGE);
    //reset
    reset();
  }

  //PASS THIS the file name being searched. 
  public void setFieldData(String fileName, String type) {
    try {
      if (CHARACTERS.equals(type)) {

          //FileUtil method getObjData reads the file for Assets, this sets the fields to the information retrieved.. 
        Characters characters = FileUtil.getObjData(type, fileName);
        cmbKeyFactor.setSelectedItem(CHARACTERS);
        txtName.setText(characters.getName());
        txtLocation.setText(characters.getLocation());
        txtDescription.setText(characters.getDescription());
        txtAbilities.setText(characters.getAbilities());
        txtAge.setText(characters.getAge());
        txtAllies.setText(characters.getAllies());
        txtBackstory.setText(characters.getBackstory());


      } else if (EVENTS.equals(type)) {

        Events events = FileUtil.getObjData(type, fileName);
        cmbKeyFactor.setSelectedItem(EVENTS);
        txtName.setText(events.getName());
        txtLocation.setText(events.getLocation());
        txtDescription.setText(events.getDescription());
        txtOutcome.setText(events.getOutcome());
        txtTrigger.setText(events.getTrigger());
        txtRewards.setText(events.getRewards());

      } else if (ITEMS.equals(type)) {

        Items items = FileUtil.getObjData(type, fileName);
        cmbKeyFactor.setSelectedItem(ITEMS);
        txtName.setText(items.getName());
        txtLocation.setText(items.getLocation());
        txtDescription.setText(items.getDescription());
        txtDamage.setText(items.getDamage());
        txtType.setText(items.getType());
        txtLegacy.setText(items.getLegacy());
        txtMastery.setText(items.getMastery());

      } else if (LOCATIONS.equals(type)) {

        Locations locations = FileUtil.getObjData(type, fileName);
        cmbKeyFactor.setSelectedItem(LOCATIONS);
        txtName.setText(locations.getName());
        txtLocation.setText(locations.getLocation());
        txtDescription.setText(locations.getDescription());
        txtFeatures.setText(locations.getFeatures());
        txtConflict.setText(locations.getConflict());
        txtAlliance.setText(locations.getAlliance());

      }
    } catch (Exception e) {
      
      JOptionPane.showMessageDialog(this, "Error while Searching "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
      reset();
    }
  }
  
  public void setDisable() {
      
    //If search window is open, disable the underlying window. 
    cmbKeyFactor.setEnabled(false);
    txtAbilities.setEnabled(false);
    txtAlliance.setEnabled(false); 
    txtFeatures.setEnabled(false);
    txtDamage.setEnabled(false);
    txtLegacy.setEnabled(false); 
    txtType.setEnabled(false); 
    txtRewards.setEnabled(false);
    txtOutcome.setEnabled(false); 
    txtTrigger.setEnabled(false); 
    txtBackstory.setEnabled(false);
    txtRelatives.setEnabled(false); 
    txtAbilities.setEnabled(false); 
    txtAllies.setEnabled(false);
    txtMastery.setEnabled(false);
    txtDescription.setEnabled(false);
    txtName.setEnabled(false);
    txtLocation.setEnabled(false);
    txtAge.setEnabled(false);
    txtConflict.setEnabled(false);
  }
  
  public void setEnable() {
      
 
    cmbKeyFactor.setEnabled(false);
    txtAbilities.setEnabled(true);
    txtAlliance.setEnabled(true); 
    txtFeatures.setEnabled(true);
    txtDamage.setEnabled(true);
    txtLegacy.setEnabled(true); 
    txtType.setEnabled(true); 
    txtRewards.setEnabled(true);
    txtOutcome.setEnabled(true); 
    txtTrigger.setEnabled(true); 
    txtBackstory.setEnabled(true);
    txtRelatives.setEnabled(true); 
    txtAbilities.setEnabled(true); 
    txtAllies.setEnabled(true);
    txtMastery.setEnabled(true);
    txtDescription.setEnabled(true);
    txtName.setEnabled(true);
    txtLocation.setEnabled(true);
    txtAge.setEnabled(true);
    txtConflict.setEnabled(true);
  }
  
  //All boxes should be empty, drop down menu should start on characters, Save should be clickable, other buttons should be greyed out. 
  //reset should be called at the end of saving an asset. 
  public void reset() {
    setEnable();
    cmbKeyFactor.setEnabled(true);
    cmbKeyFactor.setSelectedItem(CHARACTERS);
    txtAbilities.setText("");
    txtAlliance.setText("");
    txtFeatures.setText("");
    txtDamage.setText("");
    txtLegacy.setText("");
    txtType.setText("");
    txtRewards.setText("");
    txtOutcome.setText("");
    txtTrigger.setText("");
    txtBackstory.setText("");
    txtRelatives.setText("");
    txtAbilities.setText("");
    txtAllies.setText("");
    txtMastery.setText("");
    txtDescription.setText("");
    txtName.setText("");
    txtLocation.setText("");
    txtAge.setText("");
    txtConflict.setText("");
    btnSave.setEnabled(true);
    btnEdit.setEnabled(false);
    btnUpdate.setEnabled(false);
    btnPlay.setEnabled(false);
    btnPlay.setVisible(false);
    isPlaying = false;
    
  }
}