
import java.util.LinkedList;
import java.util.Queue;

class DispatchCenter {
    Queue<Passenger> passengerQueue = new LinkedList<>();
    Queue<Taxi> taxiQueue = new LinkedList<>();

    // Main methods:
    public void AddPassanger(Passenger passenger) {
        passengerQueue.add(passenger);
        tryMatch();
    }

    public Passenger NextPassenger() {
        return passengerQueue.poll(); // Removes and returns the passenger at the front of the queue
    }

    public void AddTaxi(Taxi taxi) {
        taxiQueue.add(taxi); // Adds taxi to the queue
        tryMatch();
    }

    public void reject(Taxi taxi, Passenger passenger) {
        taxiQueue.add(taxi);
        ((LinkedList<Passenger>) passengerQueue).addFirst(passenger);
        tryMatch();
    }

    public Taxi nextTaxi() {
        return taxiQueue.poll(); // Removes and returns the taxi at the front of the queue
    }

    public void registerTaxi(Taxi taxi) {
        taxi.setDispatch(this);
    }

    private void tryMatch() {
        while (!passengerQueue.isEmpty() && !taxiQueue.isEmpty()) {
            Passenger passenger = NextPassenger();
            Taxi taxi = nextTaxi();
            taxi.assignPassanger(passenger);
            System.out.println("Dispatch assigned Taxi " + taxi.getId() + " to passenger " + passenger.getName() + ".");
        }
    }
}

class Passenger {
    private String name;
    private String destination;

    public Passenger(String name) {
        this.name = name;
    }

    public void requestRide(String destination, DispatchCenter dispatchCenter) {
        System.out.println("Passenger " + this.name + " requested a ride to " + destination + ".");
        this.destination = destination;
        dispatchCenter.AddPassanger(this);
    }

    public String getDestination() {
        return this.destination;
    }

    public String getName() {
        return this.name;
    }
}

class Taxi {
    private String id;
    private DispatchCenter dispatchCenter;
    private Passenger passenger;

    public Taxi(String id) {
        this.id = id;
    }

    public void setDispatch(DispatchCenter dispatchCenter) {
        this.dispatchCenter = dispatchCenter;
    }

    public void setAvailable(boolean avail) {
        if (avail) {
            System.out.println("Taxi " + this.id + " is now available.");
            if (dispatchCenter != null) {
                dispatchCenter.AddTaxi(this);
            }
        }
    }

    public void assignPassanger(Passenger passenger) {
        this.passenger = passenger;
    }

    public void respondToRide(boolean accept) {
        if (this.passenger == null) return;

        if (accept) {
            System.out.println("Taxi " + this.id + " accepted the ride to " + this.passenger.getDestination() + ".");
            this.passenger = null;
        } else {
            System.out.println("Taxi " + this.id + " rejected the ride to " + this.passenger.getDestination() + ". Searching for another taxi...");
            Passenger rejected = this.passenger;
            this.passenger = null;

            if (this.dispatchCenter != null) {
                dispatchCenter.reject(this, rejected);
            }
        }
    }

    public String getId() {
        return this.id;
    }
}