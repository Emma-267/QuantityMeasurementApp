package src.main;

import main.Length;
import main.LengthUnit;

public class QuantityMeasurementApp {
    public static void main(String[] args) {
        System.out.println(new Length(1.0, LengthUnit.FEET).convertTo(LengthUnit.INCHES));
        System.out.println(new Length(1.0, LengthUnit.FEET).add(new Length(12.0, LengthUnit.INCHES), LengthUnit.FEET));
        System.out.println(new Length(36.0, LengthUnit.INCHES).equals(new Length(1.0, LengthUnit.YARDS)));
        System.out.println(new Length(1.0, LengthUnit.YARDS).add(new Length(3.0, LengthUnit.FEET), LengthUnit.YARDS));
        System.out.println(new Length(2.54, LengthUnit.CENTIMETERS).convertTo(LengthUnit.INCHES));
        System.out.println(new Length(5.0, LengthUnit.FEET).add(new Length(0.0, LengthUnit.INCHES), LengthUnit.FEET));
        System.out.println(LengthUnit.FEET.convertToBaseUnit(12.0));
        System.out.println(LengthUnit.INCHES.convertToBaseUnit(12.0));
    }
}