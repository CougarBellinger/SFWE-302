package edu.baylor.ecs.si;

import java.util.ArrayList;
import java.util.List;

public class Car implements BicycleInterface {
    private List<BikeHolder> carHolders = new ArrayList<BikeHolder>(4);

    public void accept(Bicycle bicycle) {
        this.carHolders.add(new BikeHolder(bicycle));
        System.out.println("  Added bike holder with bicycle to car");
    }

    public void accept(MountainBike mountainBike) {
        this.carHolders.add(new MountainBikeHolder(mountainBike));
        System.out.println("  Added mountain bike holder with mountain bike to car");
    }

    public void accept(RoadBike roadBike) {
        this.carHolders.add(new RoadBikeHolder(roadBike));
        System.out.println("  Added road bike holder with road bike to car");
    }

}
