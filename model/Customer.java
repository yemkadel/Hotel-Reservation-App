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
    public boolean equals(Object o){
        if (o == this)
            return true;
        if (!(o instanceof Customer))
            return false;
        Customer other = (Customer) o;
        boolean firstNameEquals = (this.firstName == null && other.firstName == null) || (this.firstName != null && this.firstName.equals(other.firstName));
        boolean lastNameEquals = (this.lastName == null && other.lastName == null) || (this.lastName != null && this.lastName.equals(other.lastName));
        boolean emailEquals = (this.email == null && other.email == null) || (this.email != null && this.email.equals(other.email));
        return (firstNameEquals && lastNameEquals && emailEquals);
    }
    public final int hashCode() {
        int result = 17;
        if (firstName != null) {
            result = 31 * result + firstName.hashCode();
        }
        if (lastName != null) {
            result = 31 * result + lastName.hashCode();
        }
        if (email != null) {
            result = 31 * result + email.hashCode();
        }
        return result;
    }

}
