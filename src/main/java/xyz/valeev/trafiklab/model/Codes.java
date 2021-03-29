package xyz.valeev.trafiklab.model;

public enum Codes {
    BUS("BUS", "BUSTERM"),
    SUBWAY("METRO", "METROSTN"),
    TRAMWAY("TRAM", "TRAMSTN"),
    TRAIN("TRAIN", "RAILWSTN"),
    SHIP("SHIP", "SHIPBER"),
    FERRY("FERRY", "FERRYBER");

    private String defaultTransportModeCode;
    private String stopAreaTypeCode;

    public String getDefaultTransportModeCode(){
        return defaultTransportModeCode;
    }

    Codes(String defaultTransportModeCode, String stopAreaTypeCode) {
        this.defaultTransportModeCode = defaultTransportModeCode;
        this.stopAreaTypeCode = stopAreaTypeCode;
    }
}
