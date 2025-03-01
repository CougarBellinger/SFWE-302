package edu.baylor.ecs.si;

public class Bicycle {
    public int cadence;
    public int gear;
    public int speed;

    public Bicycle(int startCadence, int startSpeed, int startGear) {
        gear = startGear;
        cadence = startCadence;
        speed = startSpeed;
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
        System.out.println("\nBike is " + "in gear " + this.gear
                + " with a cadence of " + this.cadence +
                " and travelling at a speed of " + this.speed + ". ");
    }

    public void visit(BasicService bs) {
        bs.accept(this);
    }
}

class MountainBike extends Bicycle {
    private String suspension;

    public MountainBike(int startCadence, int startSpeed, int startGear, String suspensionType) {
        super(startCadence, startSpeed, startGear);
        this.setSuspension(suspensionType);
    }

    public String getSuspension() {
        return this.suspension;
    }

    public void setSuspension(String suspensionType) {
        this.suspension = suspensionType;
    }

    @Override
    public void printDescription() {
        System.out.print("\nBike is " + "in gear " + this.gear
                + " with a cadence of " + this.cadence +
                " and travelling at a speed of " + this.speed);
        System.out.print(" with " + this.suspension + " suspension.\n");
    }

    @Override
    public void visit(BasicService bs) {
        bs.accept(this);
    }
}

class RoadBike extends Bicycle {
    private int tireWidth;

    public RoadBike(int startCadence, int startSpeed, int startGear, int tireWidth) {
        super(startCadence, startSpeed, startGear);
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
        System.out.print("\nBike is " + "in gear " + this.gear
                + " with a cadence of " + this.cadence +
                " and travelling at a speed of " + this.speed);
        System.out.print(" with tire width of " + this.tireWidth + "\n");
    }

    @Override
    public void visit(BasicService bs) {
        bs.accept(this);
    }
}
