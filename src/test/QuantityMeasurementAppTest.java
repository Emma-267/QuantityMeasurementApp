package src.test;

import org.junit.jupiter.api.Test;
import src.main.QuantityMeasurementApp;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest{
    @Test
    void testFeetEquality_SameValue(){
        QuantityMeasurementApp.Feet feet1=new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet feet2=new QuantityMeasurementApp.Feet(1.0);
        boolean result=feet1.equals(feet2);
        assertTrue(result,"Expected 1.0 ft to be equal to 1.0 ft");
    }

    @Test
    void testFeetEquality_DifferentValue(){
        QuantityMeasurementApp.Feet feet1=new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet feet2=new QuantityMeasurementApp.Feet(2.0);
        boolean result=feet1.equals(feet2);
        assertFalse(result,"Expected 1.0 ft to NOT be equal to 2.0 ft");
    }

    @Test
    void testFeetEquality_NullComparison(){
        QuantityMeasurementApp.Feet feet=new QuantityMeasurementApp.Feet(1.0);
        boolean result=feet.equals(null);
        assertFalse(result,"Expected value to NOT be equal to null");
    }

    @Test
    void testFeetEquality_NonNumericInput(){
        QuantityMeasurementApp.Feet feet=new QuantityMeasurementApp.Feet(1.0);
        Object nonNumeric="Not a number";
        boolean result=feet.equals(nonNumeric);
        assertFalse(result,"Expected value to NOT be equal to non-numeric input");
    }

    @Test
    void testFeetEquality_SameReference() {
        QuantityMeasurementApp.Feet feet=new QuantityMeasurementApp.Feet(1.0);
        boolean result=feet.equals(feet);
        assertTrue(result,"Expected object to be equal to itself");
    }

    @Test
    void testInchesEquality_SameValue(){
        QuantityMeasurementApp.Inches inch1=new QuantityMeasurementApp.Inches(1.0);
        QuantityMeasurementApp.Inches inch2=new QuantityMeasurementApp.Inches(1.0);
        boolean result=inch1.equals(inch2);
        assertTrue(result,"Expected 1.0 inch to be equal to 1.0 inch");
    }

    @Test
    void testInchesEquality_DifferentValue(){
        QuantityMeasurementApp.Inches inch1=new QuantityMeasurementApp.Inches(1.0);
        QuantityMeasurementApp.Inches inch2=new QuantityMeasurementApp.Inches(2.0);
        boolean result=inch1.equals(inch2);
        assertFalse(result,"Expected 1.0 inch to NOT be equal to 2.0 inch");
    }

    @Test
    void testInchesEquality_NullComparison(){
        QuantityMeasurementApp.Inches inch=new QuantityMeasurementApp.Inches(1.0);
        boolean result=inch.equals(null);
        assertFalse(result,"Expected value to NOT be equal to null");
    }

    @Test
    void testInchesEquality_NonNumericInput(){
        QuantityMeasurementApp.Inches inch=new QuantityMeasurementApp.Inches(1.0);
        Object nonNumeric="Not a number";
        boolean result=inch.equals(nonNumeric);
        assertFalse(result,"Expected value to NOT be equal to non-numeric input");
    }

    @Test
    void testInchesEquality_SameReference() {
        QuantityMeasurementApp.Inches inch= new QuantityMeasurementApp.Inches(1.0);
        boolean result=inch.equals(inch);
        assertTrue(result,"Expected object to be equal to itself");
    }
}