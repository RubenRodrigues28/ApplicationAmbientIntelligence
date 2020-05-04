package tecnico.ulisboa.pt.smarthomeapplication.database;

import java.io.Serializable;

public class PowerPlugModel implements Serializable {
    private int DeviceID;
    private int EnergyConsumed;

    //for registration
    public PowerPlugModel(int energyConsumed) {
        EnergyConsumed = energyConsumed;
    }

    //for access to database
    public PowerPlugModel(int deviceID, int energyConsumed) {
        DeviceID = deviceID;
        EnergyConsumed = energyConsumed;
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
        return "PowerPlugModel{" +
                "DeviceID=" + DeviceID +
                ", EnergyConsumed=" + EnergyConsumed +
                '}';
    }
}

