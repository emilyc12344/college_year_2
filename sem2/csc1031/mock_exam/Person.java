package mock_exam;

public class Person {
    String name;
    int age;
    String address;

    public Person(String name, int age, String address) {
        System.out.println("Person constructor");
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Address: " + address);
    }
}

interface SpecialFunctionality {
    void fire();
}

class Worker extends Person implements SpecialFunctionality{
    private String workerId;

    public Worker(String name, int age, String address, String ID) {
        super(name, age, address);
        this.workerId = ID;
    }

    public void updateWorkerInfo(String address) {
        this.address = address;
    }

    public void updateWorkerInfo(int age) {
        this.age = age;
    }

    public void fire() {
        this.address = "Fired";
        System.out.println("Worker " + this.workerId + " has been fired!");
    }
}