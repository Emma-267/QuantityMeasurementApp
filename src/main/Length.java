package main;

public class Length{
    private final double value;
    private final LengthUnit unit;
    public enum LengthUnit{
        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);
        private final double toInchesFactor;
        LengthUnit(double toInchesFactor){
            this.toInchesFactor=toInchesFactor;
        }
        public double toBaseUnit(double value){
            return value*toInchesFactor;
        }
    }
    public Length(double value, LengthUnit unit){
        if(unit==null){
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.value=value;
        this.unit=unit;
    }
    public double getValue(){
        return value;
    }
    private double toInches(){
        return unit.toBaseUnit(value);
    }
    private boolean compare(Length thatLength){
        if(thatLength==null){
            throw new IllegalArgumentException("Length to compare cannot be null");
        }
        return Double.compare(this.toInches(),thatLength.toInches())==0;
    }
    public Length convertTo(LengthUnit targetUnit){
        if(targetUnit==null){
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double valueInInches=this.toInches();
        double convertedValue=valueInInches/targetUnit.toInchesFactor;
        return new Length(convertedValue,targetUnit);
    }

    @Override
    public String toString(){
        return value+" "+unit.name();
    }

    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null||getClass()!=o.getClass()) return false;
        Length that=(Length) o;
        return Double.compare(this.toInches(),that.toInches())==0;
    }
    @Override
    public int hashCode(){
        return Double.hashCode(toInches());
    }
    public Length add(Length thatLength){
        if(thatLength==null){
            throw new IllegalArgumentException("Length to add cannot be null");
        }
        if(!Double.isFinite(this.value)||!Double.isFinite(thatLength.value)){
            throw new IllegalArgumentException("Invalid numeric value");
        }
        double thisInInches=this.toInches();
        double thatInInches=thatLength.toInches();
        double sumInInches=thisInInches+thatInInches;
        double resultValue = convertFromBaseToTargetUnit(sumInInches, this.unit);
        resultValue = Math.round(resultValue * 1e6) / 1e6;
        return new Length(resultValue,this.unit);
    }
    public double convertFromBaseToTargetUnit(double lengthInInches, LengthUnit targetUnit){
        if(targetUnit==null){
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        return lengthInInches/targetUnit.toInchesFactor;
    }
}