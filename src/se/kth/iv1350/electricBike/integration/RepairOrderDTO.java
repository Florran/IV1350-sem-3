package se.kth.iv1350.electricBike.integration;

public class RepairOrderDTO {
    private String id;
    private String state;
    private String problemDescr;

    public RepairOrderDTO(String id, String state, String problemDescr) {
        this.id = id;
        this.state = state;
        this.problemDescr = problemDescr;
    }

    public String getId() { return id; }
    public String getState() { return state; }
    public String getProblemDescr() { return problemDescr; }
}
