//Importing of Packages/Classes
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Main {

  //Defining the array list
  ArrayList<recipesavedlist> RSL;

  //Main start of the program
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      new Main().MakeAndStartGUI();
    });
  }

  //Defining the array list and calling the GUI for creation
  private void MakeAndStartGUI(){
    RecipeEnter RC = new RecipeEnter();
    RSL = new ArrayList<>();
    RC.RecipeEnterFrame();
  }

  public class RecipeEnter extends JFrame{

    //Defining Text field and Text Areas for user input
    JTextField recipenamefield;
    JTextArea recipeingredientsfield;
    JTextArea recipeinstructionsfield;

    //Defining labels used for GUI and Visual instructions for text inputs
    JLabel recipename;
    JLabel recipeingredients;
    JLabel recipeinstructions;

    //Defining buttons used in the main GUI for user selection
    JButton submitrecipe;
    JButton viewrecipes;

    //Creation of the GUI and its constraints
    public void RecipeEnterFrame(){

      //Defining the Panels used with GUI for Text, Labels, and Button layouts
      JPanel mainpanel;
      JPanel recipepanel;

      //Defining GUI constraints
      this.setTitle("Recipe Viewer and Saver");
      this.setDefaultCloseOperation(RecipeEnter.EXIT_ON_CLOSE);
      this.setSize(700,500);
      this.setResizable(false);
      this.setVisible(true);
      this.setLocationRelativeTo(null);

      //Creation of the main panel and defining layout used
      mainpanel = new JPanel();
      mainpanel.setLayout(new BorderLayout());

      //Creation of the recipe panel, defining layout used, and background color
      recipepanel = new JPanel();
      recipepanel.setLayout(new GridLayout(3,2,10,40));
      recipepanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
      recipepanel.setBackground(Color.LIGHT_GRAY);

      //Creation of the recipe name field, and setting of font
      recipenamefield = new JTextField();
      recipenamefield.setFont(new Font("Arial", Font.ITALIC,15));

      //Creation of recipe ingredients text area, and setting of font
      recipeingredientsfield = new JTextArea();
      recipeingredientsfield.setFont(new Font("Arial", Font.ITALIC,15));
      recipeingredientsfield.setBackground(Color.PINK);

      //Adding a scroll function the the recipe ingredients text area
      JScrollPane ingredientsScrollPane = new JScrollPane(recipeingredientsfield);
      ingredientsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

      //Creation of recipe instructions text area, and setting of font
      recipeinstructionsfield = new JTextArea();
      recipeinstructionsfield.setFont(new Font("Arial", Font.ITALIC,15));
      recipeinstructionsfield.setBackground(Color.PINK);

      //Adding a scroll function the the recipe instructions text area
      JScrollPane instructionsScrollPane= new JScrollPane(recipeinstructionsfield);
      instructionsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

      //Creation of recipe label and the font
      recipename = new JLabel("Name of Recipe?");
      recipename.setFont(new Font("Arial",Font.BOLD,15));

      //Creation of recipe ingredients label and the font
      recipeingredients = new JLabel("What are the Ingredients?");
      recipeingredients.setFont(new Font("Arial",Font.BOLD,15));

      //Creation of recipe instructions label and the font
      recipeinstructions = new JLabel("How do you make the Recipe?");
      recipeinstructions.setFont(new Font("Arial",Font.BOLD,15));

      //Creation of the Submit button, Font, and action performed when the button is clicked
      submitrecipe = new JButton("Save Recipe");
      submitrecipe.setFocusable(false);
      submitrecipe.setFont(new Font("Arial",Font.BOLD,15));

      // ====== Submit button action performed when clicked ======
      submitrecipe.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
          submitnew();
        }
      });

      //Creation of the View button, Font, and action performed when the button is clicked
      viewrecipes = new JButton("View Saved Recipes");
      viewrecipes.setFocusable(false);
      viewrecipes.setFont(new Font("Arial",Font.BOLD,15));

      // ====== View button action performed when clicked ======
      viewrecipes.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
          view();
        }
      });

      // Adding of components to the Panels

      // ====== Adding recipe lable and recipe text field to Recipe panel =====
      recipepanel.add(recipename);
      recipepanel.add(recipenamefield);

      // ====== Adding recipe ingredients label and recipe ingredients text area (Scroll Pane) to Recipe panel =====
      recipepanel.add(recipeingredients);
      recipepanel.add(ingredientsScrollPane);

      // ====== Adding recipe instructions label and recipe instructions text area (Scroll Pane) to Recipe panel =====
      recipepanel.add(recipeinstructions);
      recipepanel.add(instructionsScrollPane);

      // ====== Adding recipe panel to the main panel =====
      mainpanel.add(recipepanel,BorderLayout.CENTER);

      // ====== Adding the Submit button to the Main panel =====
      mainpanel.add(submitrecipe, BorderLayout.SOUTH);

      // ====== Adding the View button to the Main panel =====
      mainpanel.add(viewrecipes, BorderLayout.NORTH);

      // Adding the panels to the GUI frame and showing the GUI
      this.add(mainpanel);
      this.setVisible(true);
    
      
    }

    //Submit Button click event
    private void submitnew(){

      // Defining the strings used

      // ===== Getting text entered in the recipe name field =====
      String RecipeName = recipenamefield.getText();

      // ===== Getting text entered in the recipe ingredients text area =====
      String RecipeIngredients = recipeingredientsfield.getText();

      // ===== Getting text entered in the recipe instructions text area =====
      String RecipeInstructions = recipeinstructionsfield.getText();

      if (RecipeName.isEmpty() || RecipeIngredients.isEmpty() || RecipeInstructions.isEmpty()){
        JOptionPane.showMessageDialog(this,"Please fill out all the fields before you save.","ERROR",JOptionPane.ERROR_MESSAGE);
        return;
      }
      
      // Saving the information entered in the Text feilds and text areas to an array list
      recipesavedlist savelist = new recipesavedlist(RecipeName.toUpperCase(),RecipeIngredients,RecipeInstructions);
      RSL.add(savelist);

      // Clearing the text in the text fields and text areas for user, ready for next input of text
      recipenamefield.setText("");
      recipenamefield.requestFocus();
      recipeingredientsfield.setText("");
      recipeinstructionsfield.setText("");

      // Message out to user for confirmation of saved recipe
      JOptionPane.showMessageDialog(this,"Recipe has been saved.","SUCCESS!",JOptionPane.INFORMATION_MESSAGE);
    }

    //View Button click event
    private void view(){

      // Creating a new window to open up
      JDialog viewdialog = new JDialog(this, "Viewing Saved Recipes", true);

      // ===== Setting the constraints for the Dialog =====
      viewdialog.setSize(500,500);
      viewdialog.setLocationRelativeTo(this);

      // Creating a new text area to populate save recipes
      JTextArea viewarea = new JTextArea();
      viewarea.setEditable(false);

      // Filling the View text area with saved array list information
      for (recipesavedlist savelist : RSL){
        viewarea.append("\n");
        viewarea.append("Recipe Name >>>>> " + savelist.getRname() + " <<<<<" +"\n");
        viewarea.append("<<<<< Ingredient List >>>>> \n" + savelist.getIngredientsname() +"\n");
        viewarea.append("<<<<< How to make it >>>>> \n" + savelist.getInstructionsname() +"\n");
      }

      // Adding a scroll funtion to the View text area
      JScrollPane viewareascroll = new JScrollPane(viewarea);
      viewareascroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

      //Creation of recipe look field and the font for user input
      JTextField lookfield = new JTextField();
      lookfield.setFont(new Font("Arial",Font.BOLD,15));
      lookfield.setPreferredSize(new Dimension(200, 30));

      //Creation of recipe look label and the font
      JLabel looklabel = new JLabel("Search for Recipes");
      looklabel.setFont(new Font("Arial",Font.BOLD,15));

      //Creation of Look button(Search), the font, and action performed when the button is clicked
      JButton lookbutton = new JButton("Search");
      lookbutton.setFocusable(false);
      lookbutton.setFont(new Font("Arial",Font.BOLD,15));

      // ====== Look button(Search) action performed when clicked ====== 
      lookbutton.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
          String search = lookfield.getText();
          if (!search.isEmpty()){
            viewsearchresults(search, viewarea);
          }
        }
      });

      //Creation of lookfor panel used in the Dialog and setting of color
      JPanel lookfor = new JPanel();
      lookfor.setBackground(Color.ORANGE);

      // ===== Adding the look label to the lookfor panel =====
      lookfor.add(looklabel);

      // ===== Adding the look text feild to the lookfor panel =====
      lookfor.add(lookfield);

      // ===== Adding the look button to the lookfor panel =====
      lookfor.add(lookbutton);

      //Creation of mainview panel and layout used for the Dialog frame
      JPanel mainview = new JPanel();
      mainview.setLayout(new BorderLayout());

      // Adding the lookfor panel to the mainview panel
      mainview.add(lookfor, BorderLayout.SOUTH);

      // Adding the View text area (scroll) to the mainview panel
      mainview.add(viewareascroll, BorderLayout.CENTER);

      // Adding the mainview panel to the dialog frame
      viewdialog.add(mainview);

      // Making the dialog frame visible
      viewdialog.setVisible(true);
    }

    //Look Button(Search) click event
    private void viewsearchresults (String search, JTextArea viewarea){
      viewarea.setText("");

      //Searching the saved array list for text entered in the lookfield by the Rname(recipenamefield)
      for (recipesavedlist savelist : RSL){
        if (savelist.getRname().toLowerCase().contains(search.toLowerCase())){
          viewarea.append("\n");
          viewarea.append("Recipe Name >>>>> " + savelist.getRname() + " <<<<<" +"\n");
          viewarea.append("<<<<< Ingredient List >>>>> \n" + savelist.getIngredientsname() +"\n");
          viewarea.append("<<<<< How to make it >>>>> \n" + savelist.getInstructionsname() +"\n");
        }
      }
      
    }
  }
}

// Creating the class used for the Array List to save recipe information to
class recipesavedlist{
  
  private String rname;
  private String ingredientsname;
  private String instructionsname;

  public recipesavedlist(String rname,String ingredientsname,String instructionsname){
    this.rname = rname;
    this.ingredientsname = ingredientsname;
    this.instructionsname = instructionsname;
  }

  public String getRname(){
    return rname;
  }

  public String getIngredientsname(){
    return ingredientsname;
  }

  public String getInstructionsname(){
    return instructionsname;
  }
}