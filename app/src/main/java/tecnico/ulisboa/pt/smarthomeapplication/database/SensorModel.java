package tecnico.ulisboa.pt.smarthomeapplication.database;

import java.io.Serializable;

public class SensorModel implements Serializable {
    private int DeviceID;
    private int Humidity;
    private int MinHumidity;
    private int MaxHumidity;
    private int Temperature;
    private int MinTemperature;
    private int MaxTemperature;
    private int EnergyConsumed;


    //for registration
    public SensorModel(int humidity, int minHumidity, int maxHumidity, int temperature, int minTemperature, int maxTemperature, int energyConsumed) {
        Humidity = humidity;
        MinHumidity = minHumidity;
        MaxHumidity = maxHumidity;
        Temperature = temperature;
        MinTemperature = minTemperature;
        MaxTemperature = maxTemperature;
        EnergyConsumed = energyConsumed;
    }

    //for access to database
    public SensorModel(int deviceID, int humidity, int minHumidity, int maxHumidity, int temperature, int minTemperature, int maxTemperature, int energyConsumed) {
        DeviceID = deviceID;
        Humidity = humidity;
        MinHumidity = minHumidity;
        MaxHumidity = maxHumidity;
        Temperature = temperature;
        MinTemperature = minTemperature;
        MaxTemperature = maxTemperature;
        EnergyConsumed = energyConsumed;
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

    public int getMinHumidity() {
        return MinHumidity;
    }

    public void setMinHumidity(int minHumidity) {
        MinHumidity = minHumidity;
    }

    public int getMaxHumidity() {
        return MaxHumidity;
    }

    public void setMaxHumidity(int maxHumidity) {
        MaxHumidity = maxHumidity;
    }

    @Override
    public String toString() {
        return "SensorModel{" +
                "DeviceID=" + DeviceID +
                ", Humidity=" + Humidity +
                ", MinHumidity=" + MinHumidity +
                ", MaxHumidity=" + MaxHumidity +
                ", Temperature=" + Temperature +
                ", MinTemperature=" + MinTemperature +
                ", MaxTemperature=" + MaxTemperature +
                ", EnergyConsumed=" + EnergyConsumed +
                '}';
    }
}

