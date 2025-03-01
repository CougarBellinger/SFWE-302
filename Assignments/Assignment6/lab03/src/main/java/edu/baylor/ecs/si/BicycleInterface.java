package edu.baylor.ecs.si;

public interface BicycleInterface {
    public void accept(Bicycle b);

    public void accept(MountainBike mb);

    public void accept(RoadBike rb);
}
