package src.test;

import org.junit.jupiter.api.Test;
import src.main.QuantityMeasurementApp;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest{
    @Test
    void testEquality_SameValue(){
        QuantityMeasurementApp.Feet feet1=new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet feet2=new QuantityMeasurementApp.Feet(1.0);
        boolean result=feet1.equals(feet2);
        assertTrue(result,"Expected 1.0 ft to be equal to 1.0 ft");
    }

    @Test
    void testEquality_DifferentValue(){
        QuantityMeasurementApp.Feet feet1=new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet feet2=new QuantityMeasurementApp.Feet(2.0);
        boolean result=feet1.equals(feet2);
        assertFalse(result,"Expected 1.0 ft to NOT be equal to 2.0 ft");
    }

    @Test
    void testEquality_NullComparison(){
        QuantityMeasurementApp.Feet feet=new QuantityMeasurementApp.Feet(1.0);
        boolean result=feet.equals(null);
        assertFalse(result,"Expected value to NOT be equal to null");
    }

    @Test
    void testEquality_NonNumericInput(){
        QuantityMeasurementApp.Feet feet=new QuantityMeasurementApp.Feet(1.0);
        Object nonNumeric="Not a number";
        boolean result=feet.equals(nonNumeric);
        assertFalse(result,"Expected value to NOT be equal to non-numeric input");
    }

    @Test
    void testEquality_SameReference() {
        QuantityMeasurementApp.Feet feet = new QuantityMeasurementApp.Feet(1.0);
        boolean result = feet.equals(feet);
        assertTrue(result, "Expected object to be equal to itself");
    }
}