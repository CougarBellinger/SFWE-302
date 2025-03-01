package edu.baylor.ecs.si;

public class BasicService implements BicycleInterface {
    public void accept(Bicycle bicycle) {
        System.out.println("  fixing Bicycle");
    }

    public void accept(RoadBike roadBike) {
        System.out.println("  fixing RoadBike");
    }

    public void accept(MountainBike mountainBike) {
        System.out.println("  fixing MountainBike");
    }
}

class MountainBikeService extends BasicService {
    public void accept(MountainBike mountainBike) {
        System.out.println("  fixing MountainBike");
    }
}

class RoadBikeService extends BasicService {
    public void accept(RoadBike roadBike) {
        System.out.println("  fixing RoadBike");
    }
}
