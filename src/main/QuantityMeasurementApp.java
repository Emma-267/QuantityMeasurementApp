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
    public static void main(String[] args){
        Length feet=new Length(1.0,Length.LengthUnit.FEET);
        Length inches=new Length(12.0,Length.LengthUnit.INCHES);
        Length yards=new Length(1.0,Length.LengthUnit.YARDS);
        Length cm=new Length(30.48,Length.LengthUnit.CENTIMETERS);
        System.out.println("1 ft == 12 in: "+demonstrateLengthEquality(feet,inches));
        System.out.println("1 yard == 3 ft: "+demonstrateLengthEquality(yards,new Length(3.0,Length.LengthUnit.FEET)));
        System.out.println("30.48 cm == 1 ft: "+demonstrateLengthEquality(cm,feet));
        System.out.println("1 ft to inches: "+demonstrateLengthConversion(1.0,Length.LengthUnit.FEET,Length.LengthUnit.INCHES));
        System.out.println("3 yards to feet: "+demonstrateLengthConversion(3.0,Length.LengthUnit.YARDS,Length.LengthUnit.FEET));
    }
}