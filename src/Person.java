
import java.io.Serializable;


public class Person implements Serializable{
    
    private String name_;
    private String address_;
    private String phoneNumber_;
    private String ppsNumber_;
    
    public Person() {
    	name_ = "";
    	address_ = "";
    	phoneNumber_ = "";
        ppsNumber_ = "";
    }
       
    public Person (String name, String address, String phone, String pps) {
    	setName(name);
        setAddress(address);
    	setPhoneNumber(phone);
        setPpsNumber(pps);
    }
    
    public void setName(String name){
    	name_ = name;
    }

    public String getName(){
    	return name_;
    }
    
    public void setAddress(String address){
        address_ = address;
    }
    
    public String getAddress(){
        return address_;
    }
    
    public void setPhoneNumber(String phone){
        phoneNumber_ = phone;
    }
    
    public String getPhoneNumber(){
        return phoneNumber_;
    }
    
    public void setPpsNumber(String pps){
        ppsNumber_ = pps;
    }
    
    public String getPpsNumber(){
        return ppsNumber_;
    }
    
    public String toString(){
    	return "Name: "+ this.getName() +"\nAddress: "+ this.getAddress()+"\nPhone Number: "+ this.getPhoneNumber()
                +"\nPPS Number: "+ this.getPpsNumber();
    }
    
}
