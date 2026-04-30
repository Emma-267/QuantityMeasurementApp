package main;

public class QuantityMeasurementApp {
    public static void main(String[] args) {
        Quantity<LengthUnit> length1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> length2 = new Quantity<>(6.0, LengthUnit.INCHES);
        System.out.println(length1.subtract(length2));
        System.out.println(length1.divide(length2));
        Quantity<WeightUnit> weight1 = new Quantity<>(10.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> weight2 = new Quantity<>(5000.0, WeightUnit.GRAM);
        System.out.println(weight1.subtract(weight2));
        System.out.println(weight1.divide(weight2));
        Quantity<VolumeUnit> vol1 = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> vol2 = new Quantity<>(500.0, VolumeUnit.MILLILITRE);
        System.out.println(vol1.subtract(vol2));
        System.out.println(vol1.divide(vol2));
    }
}