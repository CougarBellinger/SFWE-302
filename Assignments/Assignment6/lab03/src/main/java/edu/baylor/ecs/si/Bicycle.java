package edu.baylor.ecs.si;

public class Bicycle {
    protected int cadence;
    protected int gear;
    protected int speed;

    protected BikeColor color;

    public Bicycle(int startCadence, int startSpeed, int startGear, String startColor) {
        gear = startGear;
        cadence = startCadence;
        speed = startSpeed;

        try {
            color = BikeColor.valueOf(startColor.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.printf("Failed to instantiate bike color. Invalid color type\n");
        }
    }

    public void setCadence(int newValue) {
        cadence = newValue;
    }

    public void setGear(int newValue) {
        gear = newValue;
    }

    public void applyBrake(int decrement) {
        speed -= decrement;
    }

    public void speedUp(int increment) {
        speed += increment;
    }

    public void printDescription() {
        System.out.println("  "
                + color.getColorName() + " Bike is " + "in gear " + this.gear
                + " with a cadence of " + this.cadence +
                " and travelling at a speed of " + this.speed + ". ");
    }

    public void visit(BicycleInterface bs) {
        bs.accept(this);
    }
}

class MountainBike extends Bicycle {
    private String suspension;

    public MountainBike(int startCadence, int startSpeed, int startGear, String startColor, String suspension) {
        super(startCadence, startSpeed, startGear, startColor);
        this.suspension = suspension;
    }

    public String getSuspension() {
        return this.suspension;
    }

    public void setSuspension(String suspensionType) {
        this.suspension = suspensionType;
    }

    @Override
    public void printDescription() {
        System.out.print("  "
                + color.getColorName() + " Mountain Bike is " + "in gear " + this.gear
                + " with a cadence of " + this.cadence +
                " and travelling at a speed of " + this.speed);
        System.out.print(" with " + this.suspension + " suspension.\n");
    }

    @Override
    public void visit(BicycleInterface bs) {
        bs.accept(this);
    }
}

class RoadBike extends Bicycle {
    private int tireWidth;

    public RoadBike(int startCadence, int startSpeed, int startGear, String startColor, int tireWidth) {
        super(startCadence, startSpeed, startGear, startColor);
        this.tireWidth = tireWidth;
    }

    public int getTireWidth() {
        return tireWidth;
    }

    public void setTireWidth(int tireWidth) {
        this.tireWidth = tireWidth;
    }

    @Override
    public void printDescription() {
        System.out.print("  "
                + color.getColorName() + " Road Bike is " + "in gear " + this.gear
                + " with a cadence of " + this.cadence +
                " and travelling at a speed of " + this.speed);
        System.out.print(" with tire width of " + this.tireWidth + "\n");
    }

    @Override
    public void visit(BicycleInterface bs) {
        bs.accept(this);
    }
}
