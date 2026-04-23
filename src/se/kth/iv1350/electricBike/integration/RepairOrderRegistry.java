package se.kth.iv1350.electricBike.integration;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.electricBike.model.RepairOrder;

public class RepairOrderRegistry {
    private List<RepairOrder> repairOrders;

    public RepairOrderRegistry() {
        this.repairOrders = new ArrayList<>();
    }

    public void saveRepairOrder(RepairOrder order) {
        repairOrders.add(order);
        System.out.println("SystemLogg: Order " + order.getId() + " har nu sparats i databasen.");
    }

    public RepairOrder findRepairOrderById(String id) {
        for (RepairOrder order : repairOrders) {
            if (order.getId().equals(id)) {
                return order;
            }
        }
        return null;
    }
}
