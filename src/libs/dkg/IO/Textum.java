package libs.dkg.IO;

public class Textum {

    public static String codifica(String e) {

        e = e.replace("_", "_U");
        e = e.replace("@", "_A");
        e = e.replace("'", "_S");
        e = e.replace("\"", "_D");
        e = e.replace("-", "_H");

        return e;
    }

    public static String decodifica(String e) {
        e = e.replace("_H", "-");
        e = e.replace("_D", "\"");
        e = e.replace("_S", "'");
        e = e.replace("_A", "@");
        e = e.replace("_U", "_");

        return e;
    }

}
