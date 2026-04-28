package src.main;

public class QuantityMeasurementApp{
    public static boolean checkEquality(Length l1, Length l2){
        return l1.equals(l2);
    }
    public static void main(String[] args){
        Length feet=new Length(1.0,Length.LengthUnit.FEET);
        Length inches=new Length(12.0,Length.LengthUnit.INCHES);
        Length yards=new Length(1.0,Length.LengthUnit.YARDS);
        Length cm=new Length(30.48,Length.LengthUnit.CENTIMETERS);
        System.out.println("1 ft == 12 in: "+checkEquality(feet,inches));
        System.out.println("1 yard == 3 ft: "+checkEquality(yards,new Length(3.0,Length.LengthUnit.FEET)));
        System.out.println("30.48 cm == 1 ft: "+checkEquality(cm,feet));
    }
}