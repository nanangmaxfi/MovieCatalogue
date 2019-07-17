package id.nanangmaxfi.moviecatalogue.helper;

public enum KeyString {
    MOVIE("movie"),
    TV_SHOW("tv_show");

    private String value;

    KeyString(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
