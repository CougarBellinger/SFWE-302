package edu.baylor.ecs.si;

import java.util.ArrayList;
import java.util.List;

public class AnyHolderCar implements BicycleInterface {
    List<AnyHolder> bikeHolders = new ArrayList<AnyHolder>(4);

    public void accept(Bicycle bike) {
        bikeHolders.add(new AnyHolder<Bicycle>(bike));
        System.out.println("  Added bicycle to car with anyholder");
    }

    public void accept(MountainBike mountainBikebike) {
        bikeHolders.add(new AnyHolder<MountainBike>(mountainBikebike));
        System.out.println("  Added mountain bicycle to car with anyholder");
    }

    public void accept(RoadBike roadBike) {
        bikeHolders.add(new AnyHolder<RoadBike>(roadBike));
        System.out.println("  Added road bicycle to car with anyholder");
    }
}
