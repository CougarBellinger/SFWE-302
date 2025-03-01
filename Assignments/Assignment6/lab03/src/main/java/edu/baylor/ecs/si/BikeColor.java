package edu.baylor.ecs.si;

public enum BikeColor {
    BLACK("Black", 0),
    WHITE("White", 1),
    RED("Red", 2),
    GREEN("Green", 3);

    private final String colorName;
    private final int colorId;

    BikeColor(String colorName, int colorId) {
        this.colorName = colorName;
        this.colorId = colorId;
    }

    public String getColorName() {
        return colorName;
    }

    public int getColorId() {
        return colorId;
    }
}
