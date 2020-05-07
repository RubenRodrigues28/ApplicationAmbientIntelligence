package tecnico.ulisboa.pt.smarthomeapplication.database;

public class SQLConstants {

    //DEVICES TABLE
    protected static final String DEVICES_TABLE = "DEVICES";
    protected static final String COLUMN_DEVICE_ID = "deviceID";
    protected static final String COLUMN_DEVICE_NAME = "deviceName";
    protected static final String COLUMN_DEVICE_TYPE = "deviceType";
    protected static final String COLUMN_DEVICE_STATUS = "deviceStatus";

    protected static final String SQL_CREATE_DEVICES_TABLE = "CREATE TABLE IF NOT EXISTS " + DEVICES_TABLE + "("
            + COLUMN_DEVICE_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_DEVICE_NAME + " TEXT NOT NULL,"
            + COLUMN_DEVICE_TYPE + " TEXT NOT NULL,"
            + COLUMN_DEVICE_STATUS + " TEXT NOT NULL)";

    protected static final String SQL_SELECT_ALL_DEVICES = "SELECT * FROM " + DEVICES_TABLE;
    protected static final String SQL_SELECT_STATUS_DEVICE = "SELECT " + COLUMN_DEVICE_STATUS + " FROM " + DEVICES_TABLE + " WHERE " + COLUMN_DEVICE_ID + " = ";
    protected static final String SQL_DELETE_ENTRIES_DEVICES_TABLE = "DROP TABLE IF EXISTS " + DEVICES_TABLE;
    protected static final String SQL_DELETE_DEVICE = "DELETE FROM " + DEVICES_TABLE + " WHERE " + COLUMN_DEVICE_ID + " = ";
    protected static final String SQL_LAST_INSERTED_ROWID = "SELECT MAX(" + COLUMN_DEVICE_ID + ") AS id FROM " + DEVICES_TABLE;

    //LIGHTS TABLE
    protected static final String LIGHTS_TABLE = "LIGHTS";
    protected static final String COLUMN_LIGHT_BRIGHTNESS = "brightness";
    protected static final String COLUMN_LIGHT_COLORSETTING = "colorSetting";
    protected static final String COLUMN_LIGHT_TEMPERATURE = "temperature";
    protected static final String COLUMN_LIGHT_ENERGYCONSUMED = "energyConsumed";

    protected static final String SQL_CREATE_LIGHTS_TABLE = "CREATE TABLE IF NOT EXISTS " + LIGHTS_TABLE + "("
            + COLUMN_DEVICE_ID + " INTEGER  PRIMARY KEY,"
            + COLUMN_LIGHT_BRIGHTNESS + " INTEGER NOT NULL,"
            + COLUMN_LIGHT_COLORSETTING + " INTEGER NOT NULL,"
            + COLUMN_LIGHT_TEMPERATURE + " INTEGER NOT NULL,"
            + COLUMN_LIGHT_ENERGYCONSUMED + " INTEGER NOT NULL)";

    protected static final String SQL_SELECT_BRIGHTNESS_LIGHT = "SELECT " + COLUMN_LIGHT_BRIGHTNESS + " FROM " + LIGHTS_TABLE + " WHERE " + COLUMN_DEVICE_ID + " = ";
    protected static final String SQL_SELECT_COLORSETTING_LIGHT = "SELECT " + COLUMN_LIGHT_COLORSETTING + " FROM " + LIGHTS_TABLE + " WHERE " + COLUMN_DEVICE_ID + " = ";
    protected static final String SQL_SELECT_TEMPERATURE_LIGHT = "SELECT " + COLUMN_LIGHT_TEMPERATURE + " FROM " + LIGHTS_TABLE + " WHERE " + COLUMN_DEVICE_ID + " = ";
    protected static final String SQL_SELECT_ENERGY_CONSUMED_LIGHT = "SELECT " + COLUMN_LIGHT_ENERGYCONSUMED + " FROM " + LIGHTS_TABLE + " WHERE " + COLUMN_DEVICE_ID + " = ";
    protected static final String SQL_DELETE_DEVICE_LIGHT = "DELETE FROM " + LIGHTS_TABLE + " WHERE " + COLUMN_DEVICE_ID + " = ";

    //SENSORS TABLE
    protected static final String SENSORS_TABLE = "SENSORS";
    protected static final String COLUMN_SENSOR_HUMIDITY = "humidity";
    protected static final String COLUMN_SENSOR_TEMPERATURE = "temperature";
    protected static final String COLUMN_SENSOR_ENERGYCONSUMED = "energyConsumed";

    protected static final String SQL_CREATE_SENSORS_TABLE = "CREATE TABLE IF NOT EXISTS " + SENSORS_TABLE + "("
            + COLUMN_DEVICE_ID + " INTEGER  PRIMARY KEY,"
            + COLUMN_SENSOR_HUMIDITY + " INTEGER NOT NULL,"
            + COLUMN_SENSOR_TEMPERATURE + " INTEGER NOT NULL,"
            + COLUMN_SENSOR_ENERGYCONSUMED + " INTEGER NOT NULL)";

    protected static final String SQL_SELECT_HUMIDITY_SENSOR = "SELECT " + COLUMN_SENSOR_HUMIDITY + " FROM " + SENSORS_TABLE + " WHERE " + COLUMN_DEVICE_ID + " = ";
    protected static final String SQL_SELECT_TEMPERATURE_SENSOR = "SELECT " + COLUMN_SENSOR_TEMPERATURE + " FROM " + SENSORS_TABLE + " WHERE " + COLUMN_DEVICE_ID + " = ";
    protected static final String SQL_SELECT_ENERGY_CONSUMED_SENSOR = "SELECT " + COLUMN_SENSOR_ENERGYCONSUMED + " FROM " + SENSORS_TABLE + " WHERE " + COLUMN_DEVICE_ID + " = ";
    protected static final String SQL_DELETE_DEVICE_SENSOR = "DELETE FROM " + SENSORS_TABLE + " WHERE " + COLUMN_DEVICE_ID + " = ";

    //POWERPLUG TABLE
    protected static final String POWERPLUGS_TABLE = "POWER_PLUGS";
    protected static final String COLUMN_POWERPLUG_ENERGYCONSUMED = "energyConsumed";

    protected static final String SQL_CREATE_POWER_PLUGS_TABLE = "CREATE TABLE IF NOT EXISTS " + POWERPLUGS_TABLE + "("
            + COLUMN_DEVICE_ID + " INTEGER  PRIMARY KEY,"
            + COLUMN_POWERPLUG_ENERGYCONSUMED + " INTEGER NOT NULL)";

    protected static final String SQL_SELECT_ENERGY_CONSUMED_POWER_PLUG = "SELECT " + COLUMN_POWERPLUG_ENERGYCONSUMED + " FROM " + POWERPLUGS_TABLE + " WHERE " + COLUMN_DEVICE_ID + " = ";
    protected static final String SQL_DELETE_DEVICE_POWERPLUG = "DELETE FROM " + POWERPLUGS_TABLE + " WHERE " + COLUMN_DEVICE_ID + " = ";
}

