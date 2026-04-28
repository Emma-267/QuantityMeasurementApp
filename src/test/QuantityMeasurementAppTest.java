package src.test;

import org.junit.jupiter.api.Test;
import src.main.Length;
import src.main.QuantityMeasurementApp;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest{
    @Test
    void testEquality_FeetToFeet_SameValue(){
        Length l1=new Length(1.0,Length.LengthUnit.FEET);
        Length l2=new Length(1.0,Length.LengthUnit.FEET);
        assertTrue(l1.equals(l2),"1 ft should equal 1 ft");
    }

    @Test
    void testEquality_InchToInch_SameValue(){
        Length l1=new Length(1.0,Length.LengthUnit.INCHES);
        Length l2=new Length(1.0,Length.LengthUnit.INCHES);
        assertTrue(l1.equals(l2),"1 inch should equal 1 inch");
    }

    @Test
    void testEquality_FeetToInch_EquivalentValue(){
        Length l1=new Length(1.0,Length.LengthUnit.FEET);
        Length l2=new Length(12.0,Length.LengthUnit.INCHES);
        assertTrue(l1.equals(l2),"1 ft should equal 12 inches");
    }

    @Test
    void testEquality_InchToFeet_EquivalentValue(){
        Length l1=new Length(12.0,Length.LengthUnit.INCHES);
        Length l2=new Length(1.0,Length.LengthUnit.FEET);
        assertTrue(l1.equals(l2),"12 inches should equal 1 ft (symmetry)");
    }

    @Test
    void testEquality_FeetToFeet_DifferentValue(){
        Length l1=new Length(1.0,Length.LengthUnit.FEET);
        Length l2=new Length(2.0,Length.LengthUnit.FEET);
        assertFalse(l1.equals(l2),"1 ft should NOT equal 2 ft");
    }

    @Test
    void testEquality_InchToInch_DifferentValue(){
        Length l1=new Length(1.0,Length.LengthUnit.INCHES);
        Length l2=new Length(2.0,Length.LengthUnit.INCHES);
        assertFalse(l1.equals(l2),"1 inch should NOT equal 2 inches");
    }

    @Test
    void testEquality_InvalidUnit(){
        assertThrows(IllegalArgumentException.class,()->{
            new Length(1.0,null);
        },"Null unit should throw exception");
    }

    @Test
    void testEquality_NullUnit(){
        Length l1=new Length(1.0,Length.LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class,()->{
            new Length(2.0,null);
        },"Null unit should not be allowed");
    }

    @Test
    void testEquality_SameReference(){
        Length l1=new Length(1.0,Length.LengthUnit.FEET);
        assertTrue(l1.equals(l1),"Object should equal itself");
    }

    @Test
    void testEquality_NullComparison(){
        Length l1=new Length(1.0,Length.LengthUnit.FEET);
        assertFalse(l1.equals(null),"Object should not equal null");
    }
}