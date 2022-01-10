package Azzal;

public class RefString {

    private String mString;

    public RefString() {
        mString = "";
    }

    public RefString(String eString) {
        mString = eString;
    }

    public void set(String eString) {
        mString = eString;
    }

    public String get() {
        return mString;
    }
}
