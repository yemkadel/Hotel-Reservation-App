package service;

import model.Customer;
import java.util.*;

public final class CustomerService {
    private static Map<String,Customer> customers = new HashMap<String,Customer>();
    private static CustomerService INSTANCE;

    private CustomerService(){
    }

    public static CustomerService getInstance(){
        if (INSTANCE == null){
            INSTANCE = new CustomerService();
        }
        return INSTANCE;
    }

    public void addCustomer(String email, String firstName, String lastName){
        boolean customerExists = false;
        Customer newCustomer = new Customer(firstName,lastName,email);
        Collection<Customer> existingCustomers = getAllCustomers();
        for(Customer customer: existingCustomers){
            if (customer.equals(newCustomer)){
                System.out.println("Customer already exists");
                customerExists = true;
                break;
            }else if (customers.containsKey(email)){
                System.out.println("Email has been used by an existing customer, PLS use another");
                break;
            }
        }
        if (customerExists == false){
            customers.put(email,newCustomer);
        }
    }
    public Customer getCustomer(String customerEmail){
        return customers.get(customerEmail);
    }
    public Collection<Customer> getAllCustomers(){
        Collection<Customer> allCustomers = new ArrayList<>();
        for(Customer customer : customers.values()){
            allCustomers.add(customer);
        }
        return allCustomers;
    }

}
