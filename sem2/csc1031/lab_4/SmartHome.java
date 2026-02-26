import java.util.ArrayList;
import java.util.List;

class Appliance {
    private final int id;
    private String brand;
    private double powerConsumption;
    private boolean isOn;
    private static int nextId = 1;

    public Appliance(String brand, double powerConsumption){
        this.id = nextId;
        this.nextId += 1;
        this.brand = brand;
        this.powerConsumption = powerConsumption;
        this.isOn = false;
    }

    public int getId(){
        return this.id;
    }

    public void turnOn(){
        if (!this.isOn){
            this.isOn = true;
            System.out.println("Turning on " + this.brand + " appliance (ID: " + this.id + ")");
        } else {
            System.out.println(this.brand + " appliance (ID: " + this.id + ") is already ON");
        }
    }

    public void turnOff(){
        if(this.isOn){
            this.isOn = false;
            System.out.println("Turning off " + this.brand + " appliance (ID: " + this.id + ")");
        } else {
            System.out.println(this.brand + " appliance (ID: " + this.id + ") is already OFF");
        }
    }

    public boolean isOn(){
        return this.isOn;
    }

    public String getBrand(){
        return this.brand;
    }

    public void setBrand(String newBrand){
        this.brand = newBrand;
    }

    public double getPowerConsumption(){
        return this.powerConsumption;
    }

    public void setPowerConsumption(double newPower){
        if(newPower > 0) {
            this.powerConsumption = newPower;
        } else {
            System.out.println("Invalid value. Must be positive.");
        }
    }
}

class WashingMachine extends Appliance {
    private int drumSize;

    public WashingMachine(String brand, double powerConsumption, int drumSize) {
        super(brand, powerConsumption);
        this.drumSize = drumSize;
    }

    public void setDrumSize(int drumSize) {
        if (drumSize > 0) {
            this.drumSize = drumSize;
        } else {
            System.out.println("Invalid value. Must be positive.");
        }
    }

    public void washClothes() {
        if (!this.isOn()) {
            System.out.println("Cannot wash clothes. The washing machine is OFF.");
        } else {
            System.out.printf("Washing clothes in a %s washing machine\n", this.getBrand());
        }
    }

    public int getDrumSize() {
        return this.drumSize;
    }
}

class Refrigerator extends Appliance {
    private double temperature;

    public Refrigerator(String brand, double powerConsumption, double temperature) {
        super(brand, powerConsumption);
        this.temperature = temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void coolItems() {
        if (this.isOn()) {
            System.out.printf("Cooling items in %s refrigerator at %.1f°C (ID: %d)\n", this.getBrand(), this.temperature, this.getId());
        } else {
            System.out.println("Cannot cool items. The refrigerator is OFF.");
        }
    }

    public double getTemperature() {
        return this.temperature;
    }
}

class SmartWashingMachine extends WashingMachine {
    private boolean hasWiFi;

    public SmartWashingMachine(String brand, double powerConsumption, int drumSize, boolean hasWiFi) {
        super(brand, powerConsumption, drumSize);
        this.hasWiFi = hasWiFi;
    }

    public void connectToWiFi(){
        if (this.isOn()) {
            System.out.printf("Smart Washing Machine (ID: %d) connected to WiFi.\n", this.getId());
        } else {
            System.out.println("Cannot connect to WiFi. The machine is OFF.");
        }
    }

    public boolean hasWiFi() {
        return this.hasWiFi;
    }
}

class SmartHome {
    private String ownerName;
    private List<Appliance> appliances;

    public SmartHome(String ownerName) {
        this.ownerName = ownerName;
        this.appliances = new ArrayList<>();
    }

    public void addAppliance(Appliance appliance) {
        this.appliances.add(appliance);
    }

    public void removeAppliance(Appliance appliance) {
        this.appliances.remove(appliance);
    }

    public int getTotalAppliancesInHome() {
        int total = 0;

        for (Appliance curr : appliances) {
            total += 1;
        }
        return total;
    }

    public void turnOnAllAppliances() {
        for (Appliance curr : appliances) {
            curr.turnOn();
        }
    }

    public void turnOffAllAppliances() {
        for (Appliance curr : appliances) {
            curr.turnOff();
        }
    }

    public String getOwnerName() {
        return this.ownerName;
    }
}