package xyz.valeev.trafiklab.model;

public enum RepositoryCodes {
    SUCCESS(0),
    FAIL(1);

    private int code;

    public int getCode(){
        return code;
    }

    RepositoryCodes(int code) {
        this.code = code;
    }
}
