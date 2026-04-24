package se.kth.iv1350.electricBike.view;

import se.kth.iv1350.electricBike.controller.Controller;
import se.kth.iv1350.electricBike.integration.*;

public class View {

    private Controller contr;

    /**
     * The view constructor
     * @param contr Controller reference so view can make calls to controller
     */
    public View(Controller contr) {
        this.contr = contr;
    }

    /**
     * Tries to find a customer based on a specified phone number
     * @param phoneNumber The phone number used to search for the customer
     * @return Returns the found customer
     */
    public CustomerDTO findCustomer(String phoneNumber) {
        return contr.findCustomer(phoneNumber);
    }

    public void fakeExecution() {
        CustomerDTO foundCustomer = this.findCustomer("0705556767");
        System.out.print(foundCustomer);

    String bikeSerial = foundCustomer.getBikeSerialNo();
        String customerPhone = foundCustomer.getPhoneNumber();
        String problemDescription = "Motor stängs av i uppförsbacke";

    System.out.println("\nReceptionist skriver in felet för cykel " + bikeSerial + ": " + problemDescription);

    contr.createRepairOrder(problemDescription, customerPhone, bikeSerial);

    System.out.println("Systemet har skapat reparationsorder");

    System.out.println("\nTekniker söker fram ordern via telefonnummer...");
        java.util.List<RepairOrderDTO> history = contr.findRepairOrderHistory(customerPhone);

        String generatedOrderId = history.get(0).getId();
        RepairOrderDTO foundOrder = contr.findRepairOrderById(generatedOrderId);

        System.out.println("Systemet visar orderdetaljer från DTO:");
        System.out.println(" - Order-ID: " + foundOrder.getId());
        System.out.println(" - Status: " + foundOrder.getState());
        System.out.println(" - Beskrivning: " + foundOrder.getProblemDescr());


    }
}
