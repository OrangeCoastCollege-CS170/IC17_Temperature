
public class Temperature {
    private double mDegree;
    private TemperatureUnit mUnit;

    public Temperature(double degrees, TemperatureUnit unit) {
        mDegree = degrees;
        mUnit = unit;

    }

    public Temperature() {
        mDegree = 0.0;
        mUnit = TemperatureUnit.CELSIUS;
    }

    public Temperature(Temperature other) {
        mDegree = other.mDegree;
        mUnit = other.mUnit;
    }

    public double getDegrees() {
        return mDegree;
    }

    public TemperatureUnit getUnit() {
        return mUnit;
    }

    public void setDegrees(double degrees) {
        mDegree = degrees;
    }

    public void setUnit(TemperatureUnit unit) {
        mUnit = unit;
    }

    public boolean convertTo(TemperatureUnit unit) {
        mUnit = unit;
        if ((unit.equals(TemperatureUnit.FARENHEIT) && !mUnit.equals(TemperatureUnit.FARENHEIT)) || (unit.equals(TemperatureUnit.CELSIUS) && !mUnit.equals(TemperatureUnit.CELSIUS))) {
            mDegree = inOtherUnit(unit).getDegrees();
            return true;
        }
        return false;

    }

    public Temperature inOtherUnit(TemperatureUnit unit) {
        Temperature ret = this;
        if (unit.equals(TemperatureUnit.FARENHEIT) && !mUnit.equals(TemperatureUnit.FARENHEIT))
            return new Temperature((mDegree * 9 / 5 + 32), TemperatureUnit.FARENHEIT);
        else if (unit.equals(TemperatureUnit.CELSIUS) && !mUnit.equals(TemperatureUnit.CELSIUS))
            return new Temperature((mDegree - 32) * 5 / 9, TemperatureUnit.CELSIUS);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Temperature that = (Temperature) o;

        if (Double.compare(that.inOtherUnit(mUnit).getDegrees(), mDegree) != 0) return false;
        return true;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(mDegree);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (mUnit != null ? mUnit.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Temperature[" + mDegree +
                " degrees " + (TemperatureUnit.CELSIUS.equals(mUnit)? "Celsius":"Fahrenheit") +
                ']';
    }
}
