package test2;
import java.util.ArrayList;
import java.util.List;

// ==========================================
// 1. Interfaces & States (Lifecycle Handling)
// ==========================================
interface RequestStatus {
    void advance(HotelServiceRequest request);
    void cancel(HotelServiceRequest request);
    String getStatusLabel();
}

class LoggedStatus implements RequestStatus {
    public void advance(HotelServiceRequest request) {
        request.setStatus(new AssignedStatus());
    }
    public void cancel(HotelServiceRequest request) {
        System.out.println("Request cancelled from Logged state.");
    }
    public String getStatusLabel() {
        return "Logged";
    }
}

class AssignedStatus implements RequestStatus {
    public void advance(HotelServiceRequest request) {
        request.setStatus(new InProgressStatus());
    }
    public void cancel(HotelServiceRequest request) {
        System.out.println("Request cancelled from Assigned state.");
    }
    public String getStatusLabel() {
        return "Assigned";
    }
}

class InProgressStatus implements RequestStatus {
    public void advance(HotelServiceRequest request) {
        request.setStatus(new CompletedStatus());
        request.markCompleted();
    }
    public void cancel(HotelServiceRequest request) {
        System.out.println("Request cancelled while In Progress.");
    }
    public String getStatusLabel() {
        return "In Progress";
    }
}

class CompletedStatus implements RequestStatus {
    public void advance(HotelServiceRequest request) {
        System.out.println("Request is already completed.");
    }
    public void cancel(HotelServiceRequest request) {
        System.out.println("Cannot cancel a completed request.");
    }
    public String getStatusLabel() {
        return "Completed";
    }
}

// ==========================================
// 2. Main Entities & Request Types
// ==========================================
class HotelRoom {
    private String roomNumber;
    private List<HotelServiceRequest> requests; // Has-a relationship (Ownership)

    public HotelRoom(String roomNumber) {
        this.roomNumber = roomNumber;
        this.requests = new ArrayList<>();
    }

    public String getRoomNumber() {
        return this.roomNumber;
    }

    public void recordNewRequest(HotelServiceRequest request) {
        requests.add(request);
    }
}

abstract class HotelServiceRequest {
    protected HotelRoom room;
    protected RequestStatus status; // Has-a relationship (Lifecycle)
    protected String notes;

    public HotelServiceRequest(HotelRoom room) {
        this.room = room;
        this.status = new LoggedStatus(); // Default initial state
        this.notes = "";
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void recordDetails(String details) {
        this.notes += "Details: " + details + "\n";
    }

    public void addNote(String note) {
        this.notes += "Note: " + note + "\n";
    }

    public void markCompleted() {
        this.addNote("Task successfully completed.");
    }
}

class HousekeepingRequest extends HotelServiceRequest {
    public HousekeepingRequest(HotelRoom room) { super(room); }
    public void specifySupplies(String supplies) { addNote("Supplies needed: " + supplies); }
    public void estimateDeliveryTime(String time) { addNote("Estimated delivery: " + time); }
}

class MaintenanceRequest extends HotelServiceRequest {
    public MaintenanceRequest(HotelRoom room) { super(room); }
    public void recordFaultDescription(String fault) { addNote("Fault: " + fault); }
    public void setAccessNeeded(boolean access) { addNote("Room access needed: " + access); }
}

class RoomServiceRequest extends HotelServiceRequest {
    public RoomServiceRequest(HotelRoom room) { super(room); }
    public void addRequestedItem(String item) { addNote("Item ordered: " + item); }
    public void confirmDeliverySlot(String slot) { addNote("Delivery slot: " + slot); }
}

class WakeUpCallRequest extends HotelServiceRequest {
    public WakeUpCallRequest(HotelRoom room) { super(room); }
    public void setCallTime(String time) { addNote("Wake up time: " + time); }
    public void confirmCallRecorded() { addNote("Wake up call scheduled in system."); }
}

// ==========================================
// 3. Departments
// ==========================================
abstract class HotelDepartment {
    public abstract void acceptRequest(HotelServiceRequest request);
    public abstract void allocateStaffMember(String staffName, HotelServiceRequest request);
}

class HousekeepingDepartment extends HotelDepartment {
    public void acceptRequest(HotelServiceRequest request) {
        System.out.println("Housekeeping accepted request for room " + request.room.getRoomNumber());
    }
    public void allocateStaffMember(String staffName, HotelServiceRequest request) {
        request.addNote("Assigned to housekeeping staff: " + staffName);
    }
}

class MaintenanceDepartment extends HotelDepartment {
    public void acceptRequest(HotelServiceRequest request) {
        System.out.println("Maintenance accepted request for room " + request.room.getRoomNumber());
    }
    public void allocateStaffMember(String staffName, HotelServiceRequest request) {
        request.addNote("Assigned to maintenance technician: " + staffName);
    }
}

// ==========================================
// 4. Coordinator
// ==========================================
class ServiceCoordinator {

    public void openRequest(HotelServiceRequest request) {
        System.out.println("Opening new request. Current status: " + request.getStatus().getStatusLabel());
    }

    public void assignRequest(HotelServiceRequest request, HotelDepartment department, String staffName) {
        department.acceptRequest(request);
        department.allocateStaffMember(staffName, request);
        progressRequest(request); // Moves from Logged -> Assigned
    }

    public void progressRequest(HotelServiceRequest request) {
        request.getStatus().advance(request);
        System.out.println("Request advanced to: " + request.getStatus().getStatusLabel());
    }

    public void cancelRequest(HotelServiceRequest request) {
        request.getStatus().cancel(request);
    }
}

public class HotelSystemTest {
    public static void main(String[] args) {
        // 1. Initialize the core system components
        ServiceCoordinator coordinator = new ServiceCoordinator();
        HousekeepingDepartment housekeeping = new HousekeepingDepartment();
        //MaintenanceDepartment maintenance = new MaintenanceDepartment();

        // 2. Create a hotel room
        HotelRoom room204 = new HotelRoom("204");

        System.out.println("=== SCENARIO 1: Housekeeping Request (Full Lifecycle) ===");
        
        // Guest requests extra towels
        HousekeepingRequest towelRequest = new HousekeepingRequest(room204);
        towelRequest.specifySupplies("3 Extra Bath Towels");
        room204.recordNewRequest(towelRequest);

        // Coordinator logs the request
        coordinator.openRequest(towelRequest);

        // Coordinator assigns the request to housekeeping
        coordinator.assignRequest(towelRequest, housekeeping, "Sarah");

        // Staff member starts working on it
        coordinator.progressRequest(towelRequest);

        // Staff member finishes the job
        coordinator.progressRequest(towelRequest);

        // Print final notes to verify everything was recorded
        System.out.println("\nFinal Log for Towel Request:");
        System.out.println(towelRequest.notes);


        System.out.println("\n=== SCENARIO 2: Maintenance Request (Cancellation) ===");
        // Guest reports a broken air conditioner
        MaintenanceRequest acRequest = new MaintenanceRequest(room204);
        acRequest.recordFaultDescription("AC is blowing warm air.");
        acRequest.setAccessNeeded(true);
        room204.recordNewRequest(acRequest);

        // Coordinator logs the request
        coordinator.openRequest(acRequest);

        // Guest calls back and says they figured out the thermostat, no repair needed
        coordinator.cancelRequest(acRequest);
        // Status remains unchanged from Logged, but cancellation action is triggered
        System.out.println("Current status after cancellation attempt: " + acRequest.getStatus().getStatusLabel());
    }
}