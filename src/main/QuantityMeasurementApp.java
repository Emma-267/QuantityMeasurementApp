package src.main;

import main.Length;

public class QuantityMeasurementApp{
    public static boolean demonstrateLengthEquality(Length l1, Length l2){
        return l1.equals(l2);
    }
    public static boolean demonstrateLengthComparison(Length l1, Length l2){
        return l1.equals(l2);
    }
    public static Length demonstrateLengthConversion(double value, Length.LengthUnit fromUnit, Length.LengthUnit toUnit){
        if(!Double.isFinite(value)){
            throw new IllegalArgumentException("Invalid numeric value");
        }
        Length length=new Length(value,fromUnit);
        return length.convertTo(toUnit);
    }
    public static Length demonstrateLengthConversion(Length length, Length.LengthUnit toUnit){
        if(length==null){
            throw new IllegalArgumentException("Length cannot be null");
        }
        return length.convertTo(toUnit);
    }
    public static Length demonstrateLengthAddition(Length l1, Length l2){
        if(l1==null||l2==null){
            throw new IllegalArgumentException("Lengths cannot be null");
        }
        return l1.add(l2);
    }
    public static void main(String[] args){
        System.out.println("1 ft + 2 ft: "+demonstrateLengthAddition(
                new Length(1.0,Length.LengthUnit.FEET),
                new Length(2.0,Length.LengthUnit.FEET)));
        System.out.println("1 ft + 12 in: "+demonstrateLengthAddition(
                new Length(1.0,Length.LengthUnit.FEET),
                new Length(12.0,Length.LengthUnit.INCHES)));
        System.out.println("12 in + 1 ft: "+demonstrateLengthAddition(
                new Length(12.0,Length.LengthUnit.INCHES),
                new Length(1.0,Length.LengthUnit.FEET)));
    }
}