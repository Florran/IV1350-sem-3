package se.kth.iv1350.electricBike.integration;

/**
 * Simulates a printer that prints repair order receipts for the customer.
 */
public class Printer {

    /**
     * Prints all data of a repair order to standard output.
     * @param repairOrderToPrint The repair order to print.
     */
    public void printRepairOrder(RepairOrderDTO repairOrderToPrint) {
        System.out.println("\n--- Repair order receipt ---");
        System.out.println(repairOrderToPrint);
        System.out.println("--- End of receipt ---\n");
    }
}
