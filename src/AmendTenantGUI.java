
import javax.swing.*;
import java.awt.*;


public class AmendTenantGUI extends JFrame {
    
    private static final int WIDTH = 800;
    private static final int HEIGHT = 500;
    
    private JLabel nameL, addressLine1L, addressLine2L, addressLine3L, countyL, tenantPhoneL, tenantPPSL, tenantStatusL,  tenantIDL, blankL, blank2L;
    private JTextField nameTF, addressLine1TF, addressLine2TF, addressLine3TF, countyTF, tenantPhoneTF, tenantPPSTF, tenantStatusTF, tenantIDTF;
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
    
    public static Tenant tenant_;
    
    public AmendTenantGUI(Tenant tenant){
        
        tenant_ = tenant;
        
        //Setting Font Properties
        Font myFont = new Font("Times New Roman", Font.BOLD, 20);
        
        //Instantiate the labels
        nameL = new JLabel("Tenant Name: " , SwingConstants.RIGHT);
        addressLine1L = new JLabel("Address Line 1: ", SwingConstants.RIGHT);
        addressLine2L = new JLabel("Address Line 2: ", SwingConstants.RIGHT);
        addressLine3L = new JLabel("Address Line 3: ", SwingConstants.RIGHT);
        countyL = new JLabel("County: ", SwingConstants.RIGHT);
        tenantPhoneL = new JLabel("Contact No: ", SwingConstants.RIGHT);
        tenantPPSL = new JLabel("Tenant's PPS Number: ", SwingConstants.RIGHT);
        tenantStatusL = new JLabel("Tenant Status: ", SwingConstants.RIGHT);
        tenantIDL = new JLabel("Tenant's ID Number: ", SwingConstants.RIGHT);
        blankL = new JLabel("", SwingConstants.RIGHT);
        blank2L = new JLabel("", SwingConstants.RIGHT);
        
        //Text Fields:
        nameTF = new JTextField(10);
        addressLine1TF = new JTextField(10);
        addressLine2TF = new JTextField(10);
        addressLine3TF = new JTextField(10);
        countyTF = new JTextField(10);
        tenantPhoneTF = new JTextField(10);
        tenantPPSTF = new JTextField(10);
        tenantStatusTF = new JTextField(10);
        tenantIDTF = new JTextField(10);
        tenantIDTF.setEditable(false);

        //County Drop Down Menu
        countyComboBox = new JComboBox(counties);
        
        nameTF.setText(tenant.getName());
        addressLine1TF.setText(tenant.getAddress());
        tenantPhoneTF.setText(tenant.getPhoneNumber());
        tenantPPSTF.setText(tenant.getPpsNumber());
        tenantStatusTF.setText(tenant.getTenantStatus());
        tenantIDTF.setText(""+tenant.getTenantID());
        
        //Buttons:
        //Add Button
        addB = new JButton("Commit Changes");
        addB.setFont(myFont);
        addB.setToolTipText("Click to submit details to the system.\n NOTE: Make sure details are correct with landlord");
        addB.addActionListener(ae->{
            tenant.setName(nameTF.getText());
            tenant.setAddress(addressLine1TF.getText());
            tenant.setPhoneNumber(tenantPhoneTF.getText());
            tenant.setPpsNumber(tenantPPSTF.getText());
            tenant.setTenantStatus(tenantStatusTF.getText()); 
            
            tenantIDTF.setText(""+tenant.getTenantID());
            JOptionPane.showMessageDialog(null, "Tenant info updated!");
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
        add(nameL);
        add(nameTF);
        add(addressLine1L);
        add(addressLine1TF);
        add(countyL);
        add(countyComboBox);
        add(tenantPhoneL);
        add(tenantPhoneTF);
        add(tenantPPSL);
        add(tenantPPSTF);
        add(tenantStatusL);
        add(tenantStatusTF);
        add(tenantIDL);
        add(tenantIDTF);
        add(blankL);
        add(blank2L);
        add(addB);
        add(cancelB);
        
        setTitle("Amend Tenant Details");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null); //Position application in center of screen
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        toFront();
    }
}
