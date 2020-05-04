package tecnico.ulisboa.pt.smarthomeapplication.database;

import java.io.Serializable;

public class SensorModel implements Serializable {
    private int DeviceID;
    private int Humidity;
    private int Temperature;
    private int EnergyConsumed;

    //for registration
    public SensorModel(int humidity, int temperature, int energyConsumed) {
        Humidity = humidity;
        Temperature = temperature;
        EnergyConsumed = energyConsumed;
    }

    //for access to database
    public SensorModel(int deviceID, int humidity, int temperature, int energyConsumed) {
        DeviceID = deviceID;
        Humidity = humidity;
        Temperature = temperature;
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

    public void setTemperature(int temperature) {
        Temperature = temperature;
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
                '}';
    }
}

