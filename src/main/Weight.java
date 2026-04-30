package main;

public class Weight {
    private final double value;
    private final WeightUnit unit;
    private static final double EPSILON = 1e-6;
    public Weight(double value, WeightUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }
        this.value = value;
        this.unit = unit;
    }
    public double getValue() {
        return value;
    }
    public WeightUnit getUnit() {
        return unit;
    }
    private double toBaseUnit() {
        return unit.convertToBaseUnit(value);
    }
    public Weight convertTo(WeightUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double baseValue = this.toBaseUnit();
        double converted = targetUnit.convertFromBaseUnit(baseValue);
        converted = Math.round(converted * 1e6) / 1e6;
        return new Weight(converted, targetUnit);
    }
    public Weight add(Weight that) {
        return add(that, this.unit);
    }
    public Weight add(Weight that, WeightUnit targetUnit) {
        if (that == null) {
            throw new IllegalArgumentException("Weight cannot be null");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double sumBase = this.toBaseUnit() + that.toBaseUnit();
        double result = targetUnit.convertFromBaseUnit(sumBase);
        result = Math.round(result * 1e6) / 1e6;
        return new Weight(result, targetUnit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weight that = (Weight) o;
        return Math.abs(this.toBaseUnit() - that.toBaseUnit()) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(toBaseUnit());
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}
