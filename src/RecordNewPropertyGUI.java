
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;


public class RecordNewPropertyGUI extends JFrame{
    
    private static final int WIDTH = 900;
    private static final int HEIGHT = 600;
    
    private JLabel addressLine1L, addressLine2L, addressLine3L, countyL, bedsL, propertyTypeL, rentAmountL, propertyStatusL, landlordL, propertyIDL, blankL, blank2L;
    private JTextField addressLine1TF, addressLine2TF, addressLine3TF, countyTF, bedsTF, propertyTypeTF, rentAmountTF, propertyStatusTF, landlordTF, propertyIDTF;
    private String[] counties = {"Afghanistan",
    "Albania",
    "Algeria",
    "Andorra",
    "Angola",
    "Antigua and Barbuda",
    "Argentina",
    "Armenia",
    "Australia",
    "Austria",
    "Azerbaijan",
    "Bahamas",
    "Bahrain",
    "Bangladesh",
    "Barbados",
    "Belarus",
    "Belgium",
    "Belize",
    "Benin",
    "Bhutan",
    "Bolivia",
    "Bosnia and Herzegovina",
    "Botswana",
    "Brazil",
    "Brunei",
    "Bulgaria",
    "Burkina Faso",
    "Burundi",
    "Cabo Verde",
    "Cambodia",
    "Cameroon",
    "Canada",
    "Central African Republic",
    "Chad",
    "Chile",
    "China",
    "Colombia",
    "Comoros",
    "Congo, Democratic Republic of the",
    "Congo, Republic of the",
    "Costa Rica",
    "Cote d'Ivoire",
    "Croatia",
    "Cuba",
    "Cyprus",
    "Czech Republic",
    "Denmark",
    "Djibouti",
    "Dominica",
    "Dominican Republic",
    "Ecuador",
    "Egypt",
    "El Salvador",
    "Equatorial Guinea",
    "Eritrea",
    "Estonia",
    "Eswatini",
    "Ethiopia",
    "Fiji",
    "Finland",
    "France",
    "Gabon",
    "Gambia",
    "Georgia",
    "Germany",
    "Ghana",
    "Greece",
    "Grenada",
    "Guatemala",
    "Guinea",
    "Guinea-Bissau",
    "Guyana",
    "Haiti",
    "Honduras",
    "Hungary",
    "Iceland",
    "India",
    "Indonesia",
    "Iran",
    "Iraq",
    "Ireland",
    "Israel",
    "Italy",
    "Jamaica",
    "Japan",
    "Jordan",
    "Kazakhstan",
    "Kenya",
    "Kiribati",
    "Kosovo",
    "Kuwait",
    "Kyrgyzstan",
    "Laos",
    "Latvia"};
    private JComboBox countyComboBox;
    private JButton addB, cancelB;
    
    public RecordNewPropertyGUI(){
        
        //Setting Font Properties
        Font myFont = new Font("Times New Roman", Font.BOLD, 20);
        
        //Instantiate the labels
        addressLine1L = new JLabel("Address Line 1: ", SwingConstants.RIGHT);
        addressLine2L = new JLabel("Address Line 2: ", SwingConstants.RIGHT);
        addressLine3L = new JLabel("Address Line 3: ", SwingConstants.RIGHT);
        countyL = new JLabel("County: ", SwingConstants.RIGHT);
        bedsL = new JLabel("Number of Beds: ", SwingConstants.RIGHT);
        propertyTypeL = new JLabel("Property Type: ", SwingConstants.RIGHT);
        rentAmountL = new JLabel("Rent Amount: ", SwingConstants.RIGHT);
        propertyStatusL = new JLabel("Property Status: ", SwingConstants.RIGHT);
        //landlordL = new JLabel("Landlord: ", SwingConstants.RIGHT);
        propertyIDL = new JLabel("Property ID: ", SwingConstants.RIGHT);
        blankL = new JLabel("", SwingConstants.RIGHT);
        blank2L = new JLabel("", SwingConstants.RIGHT);
        
        //Text Fields:
        addressLine1TF = new JTextField(10);
        addressLine2TF = new JTextField(10);
        addressLine3TF = new JTextField(10);
        countyTF = new JTextField(10);
        bedsTF = new JTextField(10);
        propertyTypeTF = new JTextField(10);
        rentAmountTF = new JTextField(10);
        propertyStatusTF = new JTextField(10);
        //landlordTF = new JTextField(10);
        propertyIDTF = new JTextField(10);
        propertyIDTF.setEditable(false);
        
         //County Drop Down Menu
        countyComboBox = new JComboBox(counties);
        
        //Buttons:
        //Add Button
        addB = new JButton("Add Property");
        addB.setFont(myFont);
        addB.setToolTipText("Click to submit details to the system.\n NOTE: Make sure details are correct");
        addB.addActionListener(ae->{
            Property p1 = new Property();
            p1.setAddress_(addressLine1TF.getText() + "\n" + addressLine2TF.getText() + "\n" + addressLine3TF.getText() + "\n" + countyComboBox.getSelectedItem());
            p1.setBeds_(bedsTF.getText());
            p1.setPropertyType_(propertyTypeTF.getText());
            p1.setRentAmount_(rentAmountTF.getText());
            p1.setPropertyStatus_(propertyStatusTF.getText());
            HomeScreenGUI.propertyList.add(p1);   //Add to the propertyList arrayList declared in the Home Screen
            HomeScreenGUI.propertyAvailable.add(p1); //Add to the propertyAvailable arrayList declared in the Home Screen
            propertyIDTF.setText(""+p1.getPropertyID_());
            JOptionPane.showMessageDialog(null, "Property added to the property list!");
        });
        
        //Cancel Button
        cancelB = new JButton("Cancel");
        cancelB.setFont(myFont);
        cancelB.addActionListener(ae->{
            setVisible(false);
            dispose();
        });
        
        setLayout(new GridLayout(11, 2));
        
        //Add components to the JFrame
        add(addressLine1L);
        add(addressLine1TF);
        add(addressLine2L);
        add(addressLine2TF);
        add(addressLine3L);
        add(addressLine3TF);
        add(countyL);
        add(countyComboBox);
        add(bedsL);
        add(bedsTF);
        add(propertyTypeL);
        add(propertyTypeTF);
        add(rentAmountL);
        add(rentAmountTF);
        add(propertyStatusL);
        add(propertyStatusTF);
        add(propertyIDL);
        add(propertyIDTF);
        add(blankL);
        add(blank2L);
        add(addB);
        add(cancelB);
        
        setTitle("Register new Landlord Screen");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null); //Position application in center of screen
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        toFront();
    }
}
