package model;

import java.util.regex.Pattern;

public class Customer {
    private String firstName,lastName,email;
    private String emailRegEx = "^(.+)@(.+).(.+)$";
    private Pattern emailPattern = Pattern.compile(emailRegEx);

    public Customer(String firstName,String lastName,String email){
        if (!emailPattern.matcher(email).matches()){
            System.out.println("Error, Invalid Email entered!");
            throw new IllegalArgumentException("Error, Invalid Email entered!");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String toString(){
        return "Customer Name: "+ firstName +" "+ lastName+
                "\nEmail: "+ email;
    }
}
