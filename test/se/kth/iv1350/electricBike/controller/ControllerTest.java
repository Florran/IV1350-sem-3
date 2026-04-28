package se.kth.iv1350.electricBike.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.electricBike.integration.*;
import se.kth.iv1350.electricBike.model.RepairOrder;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {
    private Controller contr;
    private String savedOrderId;

    @BeforeEach
    public void setUp() {
        CustomerRegistry customerReg = new CustomerRegistry();
        RepairOrderRegistry repairReg = new RepairOrderRegistry();

        this.contr = new Controller(customerReg, repairReg);

        String phone = "0701112233";
        contr.createRepairOrder("Motor error", phone, "SN999");

        RepairOrderDTO foundOrder = contr.findRepairOrder(phone);
        savedOrderId = foundOrder.getId();
    }

    @AfterEach
    public void tearDown() {
        this.contr = null;
        this.savedOrderId = null;
    }


    @Test
    public void testFindExistingCustomer() {
        String phone = "0705556767";
        CustomerDTO result = this.contr.findCustomer(phone);
        assertNotNull(result, "Existing customer was not found");
        assertEquals(phone, result.getPhoneNumber(), "Wrong phone number");
        assertEquals("Customer1", result.getName(), "Wrong name.");
    }

    @Test
    public void testFindUnknownCustomerReturnsNull() {
        String phone = "0700000000";
        CustomerDTO result = this.contr.findCustomer(phone);
        assertNull(result, "invalid phone number should return null");
    }

    @Test
    public void testFindCustomerEmptyPhoneNrReturnsNull() {
        String phone = "";
        CustomerDTO result = this.contr.findCustomer(phone);
        assertNull(result, "no phone number should return null");
    }

    @Test
    void testAddDiagnosticResultViaController() {
        contr.addDiagnosticResult(savedOrderId, "Sensor trasig");
        RepairOrderDTO updatedOrder = contr.findRepairOrderById(savedOrderId);
        assertNotNull(updatedOrder, "Ordern borde existera och gå att hämta ut.");
    }

    @Test
    void testAcceptRepairOrderChangesStateViaController() {
        contr.acceptRepairOrder(savedOrderId);
        RepairOrderDTO acceptedOrder = contr.findRepairOrderById(savedOrderId);
        assertEquals("Accepted", acceptedOrder.getState(),
                "Orderstatus borde vara 'Accepted' efter att controllern anropats.");
    }

    @Test
    public void testFindAllRepairOrdersReturnsEmptyListWhenNoOrdersExist() {
        Controller emptyContr = new Controller(new CustomerRegistry(), new RepairOrderRegistry());
        List<RepairOrderDTO> result = emptyContr.findAllRepairOrders();
        assertTrue(result.isEmpty(), "Repair order list should be empty before any order has been created");
    }

    @Test
    public void testCreateRepairOrderAddsRepairOrder() {
        String problemDescr = "Motor stangs av i uppforsbacke";
        String customerPhone = "0705556767";
        String bikeSerialNo = "0001";

        this.contr.createRepairOrder(problemDescr, customerPhone, bikeSerialNo);
        List<RepairOrderDTO> result = this.contr.findAllRepairOrders();

        assertEquals(2, result.size(), "Two repair orders should exist in total");
    }

    @Test
    public void testCreatedRepairOrderHasCorrectInformation() {
        String problemDescr = "Batteriet laddar inte";
        String customerPhone = "0705556767";
        String bikeSerialNo = "0001";

        this.contr.createRepairOrder(problemDescr, customerPhone, bikeSerialNo);

        List<RepairOrderDTO> allOrders = this.contr.findAllRepairOrders();
        RepairOrderDTO result = null;
        for (RepairOrderDTO o : allOrders) {
            if (o.getProblemDescr().equals(problemDescr)) {
                result = o;
            }
        }

        assertNotNull(result, "Created repair order should be found in the registry");
        assertEquals("Newly created", result.getState(), "Created repair order should have initial state");
        assertEquals(problemDescr, result.getProblemDescr(),
                "Created repair order should keep the problem description");
    }
}