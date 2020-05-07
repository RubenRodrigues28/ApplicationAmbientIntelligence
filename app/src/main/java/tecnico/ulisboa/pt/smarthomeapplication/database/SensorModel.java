package tecnico.ulisboa.pt.smarthomeapplication.database;

import java.io.Serializable;

public class SensorModel implements Serializable {
    private int DeviceID;
    private int Humidity;
    private int Temperature;
    private int EnergyConsumed;
    private int MaxTemperature;
    private int MinTemperature;

    //for registration
    public SensorModel(int humidity, int temperature, int energyConsumed, int maxTemperature, int minTemperature) {
        Humidity = humidity;
        Temperature = temperature;
        EnergyConsumed = energyConsumed;
        MaxTemperature = maxTemperature;
        MinTemperature = minTemperature;
    }

    //for access to database
    public SensorModel(int deviceID, int humidity, int temperature, int energyConsumed, int maxTemperature, int minTemperature) {
        DeviceID = deviceID;
        Humidity = humidity;
        Temperature = temperature;
        EnergyConsumed = energyConsumed;
        MaxTemperature = maxTemperature;
        MinTemperature = minTemperature;
    }

    public int getHumidity() {
        return Humidity;
    }

    public void setHumidity(int humidity) {
        Humidity = humidity;
    }

    public int getTemperature() {
        return Temperature;
    }

    public int getMaxTemperature() {
        return MaxTemperature;
    }

    public int getMinTemperature() {
        return MinTemperature;
    }

    public void setTemperature(int temperature) {
        Temperature = temperature;
    }

    public void setMaxTemperature(int maxTemperature) {
        MaxTemperature = maxTemperature;
    }

    public void setMinTemperature(int minTemperature) {
        MinTemperature = minTemperature;
    }

    public int getEnergyConsumed() {
        return EnergyConsumed;
    }

    public void setEnergyConsumed(int energyConsumed) {
        EnergyConsumed = energyConsumed;
    }

    public int getDeviceID() {
        return DeviceID;
    }

    public void setDeviceID(int deviceID) {
        DeviceID = deviceID;
    }

    @Override
    public String toString() {
        return "SensorModel{" +
                "DeviceID=" + DeviceID +
                ", Humidity=" + Humidity +
                ", Temperature=" + Temperature +
                ", EnergyConsumed=" + EnergyConsumed +
                ", MaxTemperature=" + MaxTemperature +
                ", MinTemperature=" + MinTemperature +
                '}';
    }
}

