package Classes;

public class Sensor {
    private String Description;
    private String DataType;
    private int Factor;
    private int LowerLimit;
    private int UpperLimit;
    private String UnitOfMeasurement;
    private double CurrentValue = 0.0;

    public Sensor() {}

    public Sensor(String description, String dataType, int factor, int lowerLimit, int upperLimit, String unitOfMeasurement) {
        Description = description;
        DataType = dataType;
        Factor = factor;
        LowerLimit = lowerLimit;
        UpperLimit = upperLimit;
        UnitOfMeasurement = unitOfMeasurement;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setDataType(String dataType) {
        DataType = dataType;
    }

    public void setFactor(int factor) {
        Factor = factor;
    }

    public void setLowerLimit(int lowerLimit) {
        LowerLimit = lowerLimit;
    }

    public void setUpperLimit(int upperLimit) {
        UpperLimit = upperLimit;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        UnitOfMeasurement = unitOfMeasurement;
    }

    public void RandomizeCurrentValue() {
        CurrentValue = (Math.random() * ((UpperLimit / Factor) - (LowerLimit / Factor)) + 1);
    }
}