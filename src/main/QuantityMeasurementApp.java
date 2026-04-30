package main;

public class QuantityMeasurementApp {
    public static void main(String[] args) {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
        System.out.println("Addition: " + q1.add(q2));
        System.out.println("Subtraction: " + q1.subtract(q2));
        System.out.println("Division: " + q1.divide(q2));
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        System.out.println("Volume Add: " + v1.add(v2));
        System.out.println("Volume Subtract: " + v1.subtract(v2));
        System.out.println("Volume Divide: " + v1.divide(v2));
    }
}