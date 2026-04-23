package se.kth.iv1350.electricBike.controller;

import java.lang.String;
import se.kth.iv1350.electricBike.integration.*;

public class Controller {

    private CustomerRegistry customerReg;

    /**
     * The controller constructor
     * @param customerReg CustomerRegistry reference so controller can make calls to CustomerRegistry
     */
    public Controller(CustomerRegistry customerReg) {
        this.customerReg = customerReg;
    }

    /**
     * Tries to find a customer based on a specified phone number
     * @param phoneNumber The phone number used to search for the customer
     * @return Returns the found customer
     */
    public CustomerDTO findCustomer(String phoneNumber) {
        CustomerDTO foundCustomer = customerReg.findCustomer(phoneNumber);
        return foundCustomer;
    }
}
