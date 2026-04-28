package src.test;

import org.junit.jupiter.api.Test;
import src.main.Length;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest{
    @Test
    void testEquality_FeetToYard(){
        Length l1=new Length(3.0,Length.LengthUnit.FEET);
        Length l2=new Length(1.0,Length.LengthUnit.YARDS);
        assertTrue(l1.equals(l2),"3 ft should equal 1 yard");
    }

    @Test
    void testEquality_YardToFeet(){
        Length l1=new Length(1.0,Length.LengthUnit.YARDS);
        Length l2=new Length(3.0,Length.LengthUnit.FEET);
        assertTrue(l1.equals(l2), "1 yard should equal 3 ft");
    }

    @Test
    void testEquality_CmToInches(){
        Length l1=new Length(2.54,Length.LengthUnit.CENTIMETERS);
        Length l2=new Length(1.0,Length.LengthUnit.INCHES);
        assertFalse(l1.equals(l2),"2.54 cm should equal 1 inch");
    }

    @Test
    void testEquality_CmToFeet(){
        Length l1=new Length(30.48,Length.LengthUnit.CENTIMETERS);
        Length l2=new Length(1.0,Length.LengthUnit.FEET);
        assertFalse(l1.equals(l2),"30.48 cm should equal 1 ft");
    }

    @Test
    void testEquality_YardToInches(){
        Length l1=new Length(1.0,Length.LengthUnit.YARDS);
        Length l2=new Length(36.0,Length.LengthUnit.INCHES);
        assertTrue(l1.equals(l2),"1 yard should equal 36 inches");
    }

    @Test
    void testEquality_DifferentValues(){
        Length l1=new Length(1.0,Length.LengthUnit.YARDS);
        Length l2=new Length(2.0,Length.LengthUnit.YARDS);
        assertFalse(l1.equals(l2),"1 yard should not equal 2 yards");
    }

    @Test
    void testEquality_SameReference(){
        Length l=new Length(1.0,Length.LengthUnit.FEET);
        assertTrue(l.equals(l),"Object should equal itself");
    }

    @Test
    void testEquality_NullComparison(){
        Length l=new Length(1.0,Length.LengthUnit.FEET);
        assertFalse(l.equals(null),"Object should not equal null");
    }

    @Test
    void testEquality_InvalidUnit(){
        assertThrows(IllegalArgumentException.class,()->{
            new Length(1.0,null);
        });
    }
}