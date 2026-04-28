package src.main;

public class Length{
    private double value;
    private LengthUnit unit;
    public enum LengthUnit{
        FEET(12.0),
        INCHES(1.0);
        private final double conversionFactor;
        LengthUnit(double conversionFactor){
            this.conversionFactor=conversionFactor;
        }
        public double toBaseUnit(double value){
            return value*conversionFactor;
        }
        public double getConversionFactor(){
            return conversionFactor;
        }
    }
    public Length(double value, LengthUnit unit){
        if (unit==null){
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.value=value;
        this.unit=unit;
    }
    private double convertToBaseUnit(){
        return unit.toBaseUnit(value);
    }
    public boolean compare(Length thatLength){
        if(thatLength==null) return false;
        return Double.compare(this.convertToBaseUnit(),thatLength.convertToBaseUnit())==0;
    }
    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null||getClass()!=o.getClass()) return false;
        Length that=(Length) o;
        return Double.compare(this.convertToBaseUnit(),that.convertToBaseUnit())==0;
    }
    public static void main(String[] args){
        Length length1=new Length(1.0,LengthUnit.FEET);
        Length length2=new Length(12.0,LengthUnit.INCHES);
        System.out.println("Are lengths equal? "+length1.equals(length2));
    }
}