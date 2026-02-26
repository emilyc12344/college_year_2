abstract class Shape {
    protected String color;

    public Shape(String color) {
        this.color = color;
    }

    abstract double getArea();

    public void displayColor() {
        System.out.printf("Shapa color: %s", this.color);
    }

}

class Circle extends Shape {
    private double radius;

    public Circle(String color, double radius){
        super(color);
        if (radius > 0) {
            this.radius = radius;
        } else {
            this.radius = 0;
        }
    }

    @Override
    double getArea() {
        return (3.141592653589793 * (this.radius * this.radius));
    }
}

class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(String color, double width, double height) {
        super(color);
        if (height > 0) {
            this.height = height;
        } else {
            this.height = 0;
        }
        if (width > 0) {
            this.width = width;
        } else {
            this.width = 0;
        }
    }

    @Override
    double getArea(){
        return (this.width * this.height);
    }
}