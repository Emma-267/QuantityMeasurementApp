package src.main;

import main.Length;
import main.LengthUnit;
import main.Weight;
import main.WeightUnit;

public class QuantityMeasurementApp {
    public static void main(String[] args) {
        System.out.println(new Length(1.0, LengthUnit.FEET).convertTo(LengthUnit.INCHES));
        System.out.println(new Length(1.0, LengthUnit.FEET).add(new Length(12.0, LengthUnit.INCHES), LengthUnit.FEET));
        System.out.println(new Weight(1.0, WeightUnit.KILOGRAM).equals(new Weight(1000.0, WeightUnit.GRAM)));
        System.out.println(new Weight(1.0, WeightUnit.KILOGRAM).equals(new Weight(2.20462, WeightUnit.POUND)));
        System.out.println(new Weight(1.0, WeightUnit.KILOGRAM).convertTo(WeightUnit.GRAM));
        System.out.println(new Weight(2.0, WeightUnit.POUND).convertTo(WeightUnit.KILOGRAM));
        System.out.println(new Weight(1.0, WeightUnit.KILOGRAM).add(new Weight(1000.0, WeightUnit.GRAM)));
        System.out.println(new Weight(1.0, WeightUnit.KILOGRAM).add(new Weight(1000.0, WeightUnit.GRAM), WeightUnit.GRAM));
        System.out.println(new Weight(2.0, WeightUnit.KILOGRAM).add(new Weight(4.0, WeightUnit.POUND), WeightUnit.KILOGRAM));
    }
}