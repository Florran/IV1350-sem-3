package se.kth.iv1350.electricBike.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class RepairOrder {
    private String id;
    private String problemDescr;
    private String customerPhone;
    private String bikeSerialNo;
    private LocalDateTime date;
    private String state;
    private String diagnosticReport;

    public RepairOrder(String problemDescr, String customerPhone, String bikeSerialNo) {
        this.id = UUID.randomUUID().toString().substring(0, 5);
        this.problemDescr = problemDescr;
        this.customerPhone = customerPhone;
        this.bikeSerialNo = bikeSerialNo;
        this.date = LocalDateTime.now();
        this.state = "Newly created";
    }

    public String getId() {
        return id;
    }

    public String getProblemDescr() {
        return problemDescr;
    }

    public String getState() {
        return state;
    }
}
