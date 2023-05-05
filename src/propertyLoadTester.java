
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class propertyLoadTester {
    public static void main(String[] args) throws Exception {
       // get my stream object
       ObjectInputStream in = new ObjectInputStream(new FileInputStream("files/landlordTest.data"));
       
       ArrayList<Landlord> classes;
       // read the list from the stream object
       classes = (ArrayList<Landlord>) in.readObject();
       // close the stream
       in.close();
       
       // loop to display the classes nicely
       JOptionPane.showMessageDialog(null,classes);
       
    }
}
