class Vehicle {
    public String brand;
    public Engine engine;

    public Vehicle(String brand, Engine engine) {
        this.brand = brand;
        this.engine = engine;

    }

    public void startEngine() {
    }
}

class Car extends Vehicle{
    public int numDoors;

    public Car(String brand, int numDoors, Engine engine) {
        super(brand, engine);
        this.numDoors = numDoors;
    }

    public void startEngine() {
        System.out.println("Starting car with " + this.engine.horsePower + " horsepowers");
    }
}

class ElectricCar extends Car {
    public int batterCapacity;

    public ElectricCar(String brand, int numDoors, int batterCapacity, Engine engine) {
        super(brand, numDoors, engine);
        this.batterCapacity = batterCapacity;
    }

    public void startEngine() {
        System.out.println("Starting electric car silently with " + this.engine.horsePower + " horsepowers");
    }
}

class Bike extends Vehicle{
    public boolean hasCarrier;

    public Bike(String brand, boolean hasCarrier, Engine engine) {
        super(brand, engine);
        this.hasCarrier = hasCarrier;
    }

    public void startEngine() {
        System.out.println("Starting bike with " + this.engine.horsePower + " horsepowers");
    }
}

class Engine {
    public int horsePower;

    public Engine(int horsePower) {
        this.horsePower = horsePower;
    }
}