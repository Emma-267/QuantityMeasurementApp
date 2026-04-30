package test;

import main.Length;
import main.Length.LengthUnit;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest{
    private static final double EPSILON = 1e-6;

    @Test
    void testAddition_SameUnit_FeetPlusFeet(){
        Length l1=new Length(1.0,LengthUnit.FEET);
        Length l2=new Length(2.0,LengthUnit.FEET);
        Length result=l1.add(l2);
        assertEquals(new Length(3.0,LengthUnit.FEET),result);
    }

    @Test
    void testAddition_SameUnit_InchPlusInch(){
        Length l1=new Length(6.0,LengthUnit.INCHES);
        Length l2=new Length(6.0,LengthUnit.INCHES);
        Length result=l1.add(l2);
        assertEquals(new Length(12.0,LengthUnit.INCHES),result);
    }

    @Test
    void testAddition_CrossUnit_InchPlusFeet(){
        Length l1=new Length(12.0,LengthUnit.INCHES);
        Length l2=new Length(1.0,LengthUnit.FEET);
        Length result=l1.add(l2);
        assertEquals(24.0,result.getValue(),EPSILON);
    }

    @Test
    void testAddition_CrossUnit_YardPlusFeet(){
        Length l1=new Length(1.0,LengthUnit.YARDS);
        Length l2=new Length(3.0,LengthUnit.FEET);
        Length result=l1.add(l2);
        assertEquals(2.0,result.getValue(),EPSILON);
    }

    @Test
    void testAddition_Commutativity(){
        Length a=new Length(1.0,LengthUnit.FEET);
        Length b=new Length(12.0,LengthUnit.INCHES);
        Length result1=a.add(b);
        Length result2=b.add(a);
        assertEquals(result1.convertTo(LengthUnit.INCHES).getValue(),result2.convertTo(LengthUnit.INCHES).getValue(),EPSILON);
    }

    @Test
    void testAddition_WithZero(){
        Length l1=new Length(5.0,LengthUnit.FEET);
        Length l2=new Length(0.0,LengthUnit.INCHES);
        Length result=l1.add(l2);
        assertEquals(5.0,result.getValue(),EPSILON);
    }

    @Test
    void testAddition_NegativeValues(){
        Length l1=new Length(5.0,LengthUnit.FEET);
        Length l2=new Length(-2.0,LengthUnit.FEET);
        Length result=l1.add(l2);
        assertEquals(3.0,result.getValue(),EPSILON);
    }

    @Test
    void testAddition_NullSecondOperand() {
        Length l1=new Length(1.0,LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class,()->l1.add(null));
    }

    @Test
    void testAddition_LargeValues(){
        Length l1=new Length(1e6,LengthUnit.FEET);
        Length l2=new Length(1e6,LengthUnit.FEET);
        Length result=l1.add(l2);
        assertEquals(2e6,result.getValue(),EPSILON);
    }

    @Test
    void testAddition_SmallValues(){
        Length l1=new Length(0.001,LengthUnit.FEET);
        Length l2=new Length(0.002,LengthUnit.FEET);
        Length result=l1.add(l2);
        assertEquals(0.003,result.getValue(),EPSILON);
    }
}