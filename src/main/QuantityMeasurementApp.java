package src.main;

import main.Length;
import main.Length.LengthUnit;

public class QuantityMeasurementApp {
    public static Length demonstrateLengthAddition(Length l1, Length l2, LengthUnit targetUnit) {
        if (l1 == null || l2 == null) {
            throw new IllegalArgumentException("Lengths cannot be null");
        }
        return l1.add(l2, targetUnit);
    }
    public static void main(String[] args) {
        System.out.println(demonstrateLengthAddition(
                new Length(1.0, LengthUnit.FEET),
                new Length(12.0, LengthUnit.INCHES),
                LengthUnit.FEET));
        System.out.println(demonstrateLengthAddition(
                new Length(1.0, LengthUnit.FEET),
                new Length(12.0, LengthUnit.INCHES),
                LengthUnit.INCHES));
        System.out.println(demonstrateLengthAddition(
                new Length(1.0, LengthUnit.FEET),
                new Length(12.0, LengthUnit.INCHES),
                LengthUnit.YARDS));
        System.out.println(demonstrateLengthAddition(
                new Length(1.0, LengthUnit.YARDS),
                new Length(3.0, LengthUnit.FEET),
                LengthUnit.YARDS));
        System.out.println(demonstrateLengthAddition(
                new Length(36.0, LengthUnit.INCHES),
                new Length(1.0, LengthUnit.YARDS),
                LengthUnit.FEET));
        System.out.println(demonstrateLengthAddition(
                new Length(2.54, LengthUnit.CENTIMETERS),
                new Length(1.0, LengthUnit.INCHES),
                LengthUnit.CENTIMETERS));
        System.out.println(demonstrateLengthAddition(
                new Length(5.0, LengthUnit.FEET),
                new Length(0.0, LengthUnit.INCHES),
                LengthUnit.YARDS));
        System.out.println(demonstrateLengthAddition(
                new Length(5.0, LengthUnit.FEET),
                new Length(-2.0, LengthUnit.FEET),
                LengthUnit.INCHES));
    }
}