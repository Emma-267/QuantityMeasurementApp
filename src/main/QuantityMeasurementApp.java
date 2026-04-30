package main;

import main.LengthUnit;
import main.WeightUnit;

public class QuantityMeasurementApp {
    public static void main(String[] args) {
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
        System.out.println(length.convertTo(LengthUnit.INCHES));
        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        System.out.println(weight.convertTo(WeightUnit.GRAM));
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        System.out.println(v1.equals(v2)); // true
        System.out.println(v1.add(v2));     // 2 L
    }
}