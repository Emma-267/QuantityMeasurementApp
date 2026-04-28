package src.main;

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
    private double toInches(){
        return unit.toBaseUnit(value);
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
}