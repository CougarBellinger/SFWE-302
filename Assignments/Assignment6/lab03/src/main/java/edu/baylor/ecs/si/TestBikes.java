package edu.baylor.ecs.si;

public class TestBikes {
    public static void main(String[] args) {
        Bicycle bike01 = new Bicycle(20, 10, 1, "white");
        Bicycle bike02 = new MountainBike(20, 10, 5, "black", "Dual");
        Bicycle bike03 = new RoadBike(40, 20, 8, "red", 23);
        Bicycle bike04 = new Bicycle(20, 10, 1, "green");

        BasicService service1 = new BasicService();
        BasicService service2 = new MountainBikeService();
        BasicService service3 = new RoadBikeService();

        Car car1 = new Car();
        AnyHolderCar car2 = new AnyHolderCar();

        System.out.println("\nBike descriptions:");
        bike01.printDescription();
        bike02.printDescription();
        bike03.printDescription();
        bike04.printDescription();

        System.out.println("\nSingle dispatch:");
        service1.accept(bike01);
        service1.accept(bike04);
        service2.accept(bike02);
        service3.accept(bike03);

        System.out.println("\nDouble dispatch:");
        bike01.visit(service1);
        bike02.visit(service2);
        bike03.visit(service3);
        bike04.visit(service1);

        System.out.println("\nAdding bikes to basic Car:");
        bike01.visit(car1);
        bike02.visit(car1);
        bike03.visit(car1);
        bike04.visit(car1);

        System.out.println("\nAdding bikes to Car using generics:");
        bike01.visit(car2);
        bike02.visit(car2);
        bike03.visit(car2);
        bike04.visit(car2);

    }
}
