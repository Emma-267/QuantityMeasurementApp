package test;

import main.Length;
import src.main.QuantityMeasurementApp;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest{
    private static final double EPSILON=1e-6;
    @Test
    void testConversion_FeetToInches(){
        Length result= QuantityMeasurementApp.demonstrateLengthConversion(1.0,Length.LengthUnit.FEET,Length.LengthUnit.INCHES);
        assertEquals(12.0,resultValue(result),EPSILON);
    }

    @Test
    void testConversion_InchesToFeet(){
        Length result=QuantityMeasurementApp.demonstrateLengthConversion(24.0,Length.LengthUnit.INCHES,Length.LengthUnit.FEET);
        assertEquals(2.0,resultValue(result),EPSILON);
    }

    @Test
    void testConversion_YardsToInches(){
        Length result=QuantityMeasurementApp.demonstrateLengthConversion(1.0,Length.LengthUnit.YARDS,Length.LengthUnit.INCHES);
        assertEquals(36.0, resultValue(result),EPSILON);
    }

    @Test
    void testConversion_InchesToYards(){
        Length result=QuantityMeasurementApp.demonstrateLengthConversion(72.0,Length.LengthUnit.INCHES,Length.LengthUnit.YARDS);
        assertEquals(2.0,resultValue(result),EPSILON);
    }

    @Test
    void testConversion_CentimetersToInches(){
        Length result=QuantityMeasurementApp.demonstrateLengthConversion(2.54,Length.LengthUnit.CENTIMETERS,Length.LengthUnit.INCHES);
        assertEquals(1.0,resultValue(result),EPSILON);
    }

    @Test
    void testConversion_FeetToYard(){
        Length result=QuantityMeasurementApp.demonstrateLengthConversion(6.0,Length.LengthUnit.FEET,Length.LengthUnit.YARDS);
        assertEquals(2.0,resultValue(result),EPSILON);
    }

    @Test
    void testConversion_RoundTrip_PreservesValue(){
        double original=5.75;
        Length first=QuantityMeasurementApp.demonstrateLengthConversion(original,Length.LengthUnit.FEET,Length.LengthUnit.INCHES);
        Length second=QuantityMeasurementApp.demonstrateLengthConversion(resultValue(first),Length.LengthUnit.INCHES,Length.LengthUnit.FEET);
        assertEquals(original,resultValue(second),EPSILON);
    }

    @Test
    void testConversion_ZeroValue(){
        Length result=QuantityMeasurementApp.demonstrateLengthConversion(0.0,Length.LengthUnit.FEET,Length.LengthUnit.INCHES);
        assertEquals(0.0,resultValue(result),EPSILON);
    }

    @Test
    void testConversion_NegativeValue(){
        Length result=QuantityMeasurementApp.demonstrateLengthConversion(-1.0,Length.LengthUnit.FEET,Length.LengthUnit.INCHES);
        assertEquals(-12.0,resultValue(result),EPSILON);
    }

    @Test
    void testConversion_InvalidUnit_Throws(){
        assertThrows(IllegalArgumentException.class,()->
                QuantityMeasurementApp.demonstrateLengthConversion(1.0,null,Length.LengthUnit.INCHES)
        );
        assertThrows(IllegalArgumentException.class,()->
                QuantityMeasurementApp.demonstrateLengthConversion(1.0,Length.LengthUnit.FEET,null)
        );
    }

    @Test
    void testConversion_NaNOrInfinite_Throws(){
        assertThrows(IllegalArgumentException.class,()->
                QuantityMeasurementApp.demonstrateLengthConversion(Double.NaN,Length.LengthUnit.FEET,Length.LengthUnit.INCHES)
        );
        assertThrows(IllegalArgumentException.class,()->
                QuantityMeasurementApp.demonstrateLengthConversion(Double.POSITIVE_INFINITY,Length.LengthUnit.FEET,Length.LengthUnit.INCHES)
        );
        assertThrows(IllegalArgumentException.class,()->
                QuantityMeasurementApp.demonstrateLengthConversion(Double.NEGATIVE_INFINITY,Length.LengthUnit.FEET,Length.LengthUnit.INCHES)
        );
    }

    @Test
    void testConversion_PrecisionTolerance(){
        Length result=QuantityMeasurementApp
                .demonstrateLengthConversion(1.0,Length.LengthUnit.CENTIMETERS,Length.LengthUnit.INCHES);
        assertEquals(0.393701,resultValue(result),EPSILON);
    }
    private double resultValue(Length length){
        return length.getValue();
    }
}