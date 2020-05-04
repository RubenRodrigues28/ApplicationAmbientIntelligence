package tecnico.ulisboa.pt.smarthomeapplication.database;

import java.io.Serializable;

public class LigthsDeviceModel implements Serializable {
    private int DeviceID;
    private int Brightness;
    private int ColorSetting;
    private int Temperature;
    private int EnergyConsumed;

    //for registration
    public LigthsDeviceModel(int brightness, int colorSetting, int temperature, int energyConsumed) {
        Brightness = brightness;
        ColorSetting = colorSetting;
        Temperature = temperature;
        EnergyConsumed = energyConsumed;
    }

    //for access to database
    public LigthsDeviceModel(int deviceID, int brightness, int colorSetting, int temperature, int energyConsumed) {
        DeviceID = deviceID;
        Brightness = brightness;
        ColorSetting = colorSetting;
        Temperature = temperature;
        EnergyConsumed = energyConsumed;
    }

    public int getDeviceID() {
        return DeviceID;
    }

    public void setDeviceID(int deviceID) {
        DeviceID = deviceID;
    }

    public int getColorSetting() {
        return ColorSetting;
    }

    public void setColorSetting(int colorSetting) {
        ColorSetting = colorSetting;
    }

    public int getBrightness() {
        return Brightness;
    }

    public void setBrightness(int brightness) {
        Brightness = brightness;
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

    @Override
    public String toString() {
        return "LigthsDeviceModel{" +
                "DeviceID=" + DeviceID +
                ", Brightness=" + Brightness +
                ", ColorSetting='" + ColorSetting + '\'' +
                ", Temperature=" + Temperature +
                ", EnergyConsumed=" + EnergyConsumed +
                '}';
    }
}
