package edu.baylor.ecs.si;

public class BikeHolder {
    protected Bicycle bike;

    public BikeHolder(Bicycle bike) {
        this.bike = bike;
    }
}

class MountainBikeHolder extends BikeHolder {
    public MountainBikeHolder(MountainBike mountainBike) {
        super(mountainBike);
    }
}

class RoadBikeHolder extends BikeHolder {
    public RoadBikeHolder(RoadBike roadBike) {
        super(roadBike);
    }
}