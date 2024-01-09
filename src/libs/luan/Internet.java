package libs.luan;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Internet {

    public static String GET_PAGINA_HTML(String link_pagina) {

        String ss = "";

        URL url = null;

        try {
            url = new URL(link_pagina);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        try {
            byte bytes[] = url.openStream().readAllBytes();
            ss = new String(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return ss;
    }

}
