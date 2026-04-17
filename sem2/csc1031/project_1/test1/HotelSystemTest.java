package test1;
import java.util.ArrayList;
import java.util.List;

public class HotelSystemTest {
    public static void main(String[] args) {
        System.out.println("--- OFFICIAL HOTEL SYSTEM COMPLIANCE TEST ---\n");

        // 1. SETUP COMPONENTS
        ServiceCoordinator coordinator = new ServiceCoordinator();
        HousekeepingDepartment housekeeping = new HousekeepingDepartment();
        MaintenanceDepartment maintenance = new MaintenanceDepartment();

        HotelRoom room404 = new HotelRoom(404);

        // ---------------------------------------------------------
        // TEST 1: Request Ownership (Has-A relationship)
        // ---------------------------------------------------------
        System.out.println("TEST 1: Testing Room-Request Ownership");

        MaintenanceRequest leak = new MaintenanceRequest(room404);
        leak.recordFaultDescription("Bathroom sink is leaking.");

        HousekeepingRequest pillows = new HousekeepingRequest(room404);
        pillows.specifySupplies("2 Feather Pillows");

        room404.recordNewRequest(leak);
        room404.recordNewRequest(pillows);

        System.out.println("Room 404 initialized with 2 separate requests.");
        System.out.println();

        // ---------------------------------------------------------
        // TEST 2: Department Assignment & Polymorphism
        // ---------------------------------------------------------
        System.out.println("TEST 2: Department Assignment");

        // Testing that the coordinator can handle any HotelServiceRequest subtype
        coordinator.openRequest(leak);
        coordinator.assignRequest(leak, maintenance, "John the Fixer");

        System.out.println("Request assigned to: " + leak.staff);
        System.out.println("Current Status: " + leak.getStatus().getStatusLabel());
        System.out.println();

        // ---------------------------------------------------------
        // TEST 3: Full Lifecycle Progression (State Pattern)
        // ---------------------------------------------------------
        System.out.println("TEST 3: Progressing Lifecycle (Logged -> Assigned -> In Progress -> Complete)");

        // Note: Your assignRequest calls progressRequest once, 
        // moving it from Logged to Assigned.

        System.out.println("Initial State (After Assignment): " + leak.getStatus().getStatusLabel());

        coordinator.progressRequest(leak); // Moves to In Progress
        System.out.println("State after starting work: " + leak.getStatus().getStatusLabel());

        coordinator.progressRequest(leak); // Moves to Complete
        System.out.println("State after finishing work: " + leak.getStatus().getStatusLabel());

        // Verify outcome recording
        leak.addNote("Fixed leak by tightening the U-bend pipe.");
        System.out.println("Final Notes Check:\n" + leak.notes);
        System.out.println();

        // ---------------------------------------------------------
        // TEST 4: Safe Cancellation (Edge Case Testing)
        // ---------------------------------------------------------
        System.out.println("TEST 4: Cancellation and Null-Safety");

        MaintenanceRequest brokenWindow = new MaintenanceRequest(room404);
        coordinator.openRequest(brokenWindow);

        // This tests the logic we discussed regarding the NullPointerException fix
        System.out.println("Attempting to cancel an unassigned request...");
        try {
            coordinator.cancelRequest(brokenWindow);
            System.out.println("Cancellation logic executed.");
        } catch (NullPointerException e) {
            System.out.println("CRITICAL ERROR: NullPointerException detected in cancelRequest!");
        }

        System.out.println("\n--- ALL BEHAVIOURAL TESTS COMPLETED ---");
    }
}

// HotelRoom, RequestStatus (CompletedStatus)
abstract class HotelServiceRequest {
    protected HotelRoom room;
    protected RequestStatus status;
    protected String notes;
    protected String staff;
    protected HotelDepartment department;

    public HotelServiceRequest(HotelRoom room) {
        this.room = room;
        this.status = new LoggedStatus();
        this.notes = "";
    }

    protected void recordDetails(String details) {
        addNote(details);
    }

    public void addNote(String newNote) {
        this.notes += newNote + "\n";
    }

    protected void markCompleted() {
        this.status = new CompletedStatus();
    }

    protected void assignTo(String staffName) {
        this.staff = staffName;
    }

    void setStatus(RequestStatus status) {
        this.status = status;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setDepartment(HotelDepartment department) {
        this.department = department;
    }

    public HotelDepartment getDepartment() {
        return this.department;
    }

    public String getNotes() {
        return this.notes;
    }

    public HotelRoom getRoom() {
        return this.room;
    }

    public String getStaff() {
        return this.staff;
    }
}

// HotelServiceRequest
class HotelRoom {
    private int roomNumber;
    private List<HotelServiceRequest> requests;

    public HotelRoom(int room) {
        this.roomNumber = room;
        this.requests = new ArrayList<>();
    }

    public int getRoomNumber() {
        return this.roomNumber;
    }

    public void recordNewRequest(HotelServiceRequest request) {
        requests.add(request);
    }
}

//
class HousekeepingRequest extends HotelServiceRequest {
    public HousekeepingRequest(HotelRoom room) {
        super(room);
    }

    public void specifySupplies(String supplies) {
        addNote("Supplies: " + supplies);
    }

    public void estimatedDeliveryTime(String time) {
        addNote("DeliveryTime: " + time);
    }
}

// HotelServiceRequest, HotelRoom
class MaintenanceRequest extends HotelServiceRequest {
    public MaintenanceRequest(HotelRoom room) {
        super(room);
    }

    public void recordFaultDescription(String fault) {
        recordDetails("Fault: " + fault);
    }

    public void setAccessNeeded(boolean access) {
        addNote("Access Needed: " + access);
    }
}

//
class RoomServiceRequest extends HotelServiceRequest {
    public RoomServiceRequest(HotelRoom room) {
        super(room);
    }

    public void addRequestedItem(String item) {
        addNote("Item: " + item);
    }

    public void confirmDeliverySlot(String time) {
        addNote("Delivery Time: " + time);
    }
}

//
class WakeUpCallRequest extends HotelServiceRequest {
    public WakeUpCallRequest(HotelRoom room) {
        super(room);
    }

    public void setCallTime(String callTime) {
        addNote("Call Time: " + callTime);
    }

    public void confirmCallRecorded() {
        addNote("Call Scheduled");
    }
}

//
class ServiceCoordinator {
    public void openRequest(HotelServiceRequest request) {
        System.out.println("open request");
    }

    public void assignRequest(HotelServiceRequest request, HotelDepartment department, String staff) {
        department.acceptRequest(request);
        request.addNote("Accepted by: " + department.getName());
        department.allocateStaffMember(request, staff);
        request.addNote("Assigned to: " + staff);
        progressRequest(request);
    }

    public void progressRequest(HotelServiceRequest request) {
        request.getStatus().advance(request);
        request.addNote("Request advanced to: " + request.getStatus().getStatusLabel());
    }

    public void cancelRequest(HotelServiceRequest request) {
        if (request.getDepartment() != null) {
            request.getStatus().cancel(request);
        } else {
            request.setStatus(new CancelledStatus());
        }
    }
}

//
interface RequestStatus {
    void advance(HotelServiceRequest request);
    void cancel(HotelServiceRequest request);
    String getStatusLabel();
}

//
class LoggedStatus implements RequestStatus {
    public void advance(HotelServiceRequest request) {
        request.setStatus(new AssignedStatus());
    }

    public void cancel(HotelServiceRequest request) {
        request.getDepartment().cancelRequest(request);
    }

    public String getStatusLabel() {
        return "Logged";
    }
}

//
class AssignedStatus implements RequestStatus {
    public void advance(HotelServiceRequest request) {
        request.setStatus(new InProgressStatus());
    }

    public void cancel(HotelServiceRequest request) {
        request.getDepartment().cancelRequest(request);
    }

    public String getStatusLabel() {
        return "Assigned";
    }
}

//
class InProgressStatus implements RequestStatus {
    public void advance(HotelServiceRequest request) {
        request.markCompleted();
    }

    public void cancel(HotelServiceRequest request) {
        request.getDepartment().cancelRequest(request);
    }

    public String getStatusLabel() {
        return "In Progress";
    }
}

//
class CompletedStatus implements RequestStatus {
    public void advance(HotelServiceRequest request) {
        System.out.println("Request is already Complete");
    }

    public void cancel(HotelServiceRequest request) {
        System.out.println("Cannot cancel completed request");
    }

    public String getStatusLabel() {
        return "Complete";
    }
}

class CancelledStatus implements RequestStatus {
    public void advance(HotelServiceRequest request) {
        System.out.println("Cannot advance a cancelled request");
    }

    public void cancel(HotelServiceRequest request) {
        System.out.println("Cannot cancel a cancelled request");
    }

    public String getStatusLabel() {
        return "Cancelled";
    }
}

// HotelServiceRequest,
abstract class HotelDepartment {
    abstract void acceptRequest(HotelServiceRequest request);
    abstract void allocateStaffMember(HotelServiceRequest request, String staffName);
    abstract void cancelRequest(HotelServiceRequest request);
    abstract String getName();
}

//
class HousekeepingDepartment extends HotelDepartment {
    private List<HotelServiceRequest> housekeepingRequests;

    public HousekeepingDepartment() {
        this.housekeepingRequests = new ArrayList<>();
    }

    public void acceptRequest(HotelServiceRequest request) {
        this.housekeepingRequests.add(request);
        request.setDepartment(this);
    }

    public void allocateStaffMember(HotelServiceRequest request, String staffName) {
        request.assignTo(staffName);
    }

    public void cancelRequest(HotelServiceRequest request) {
        this.housekeepingRequests.remove(request);
        request.setStatus(new CancelledStatus());
    }

    public String getName() {
        return "Housekeeping";
    }
}

//
class MaintenanceDepartment extends HotelDepartment {
    private List<HotelServiceRequest> maintenanceRequests;

    public MaintenanceDepartment() {
        this.maintenanceRequests = new ArrayList<>();
    }

    public void acceptRequest(HotelServiceRequest request) {
        this.maintenanceRequests.add(request);
        request.setDepartment(this);
    }

    public void allocateStaffMember(HotelServiceRequest request, String staffName) {
        request.assignTo(staffName);
    }

    public void cancelRequest(HotelServiceRequest request) {
        this.maintenanceRequests.remove(request);
        request.setStatus(new CancelledStatus());
    }

    public String getName() {
        return "Maintenance";
    }
}