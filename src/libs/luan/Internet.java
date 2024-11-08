package libs.luan;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import libs.arquivos.binario.ArenaChunk;
import libs.arquivos.binario.ByteChunk;
import libs.dkg.DKG;
import libs.dkg.DKGObjeto;
import libs.oo.JSON;

public class Internet {

    public static Opcional<String> GET_PAGINA_HTML_TIMEOUT(String link_pagina) {

        String ss = "";

        try {
            URL url  = new URL(link_pagina);

            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            HttpURLConnection.setFollowRedirects(false);
            huc.setConnectTimeout(15 * 1000);
            huc.setRequestMethod("GET");
            huc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");
            huc.connect();
            InputStream input = huc.getInputStream();

            ss = new String(input.readAllBytes());

        } catch (IOException e) {
            return  Opcional.CANCEL();
        }

       return Opcional.OK(ss);

    }

    public static  Opcional<ByteChunk> DOWNLOAD(String link_pagina ) {

        ByteChunk ss = null;

        try {
            URL url  = new URL(link_pagina);

            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            HttpURLConnection.setFollowRedirects(false);
            huc.setConnectTimeout(15 * 1000);
            huc.setRequestMethod("GET");
            huc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");
            huc.connect();
            InputStream input = huc.getInputStream();

            byte[] bytes = input.readAllBytes();

            ss = new ByteChunk(bytes,bytes.length);

        } catch (IOException e) {
            return  Opcional.CANCEL();
        }

        return Opcional.OK(ss);

    }

    public static Resultado<String, String> http(String webpage) {

        URL url = null;

        try {
            url = new URL(webpage);
        } catch (MalformedURLException e) {
            return Resultado.FALHAR("PROBLEMA_CONEXAO");
        }

        try {
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            HttpURLConnection.setFollowRedirects(false);
            huc.setConnectTimeout(15 * 1000);
            huc.setRequestMethod("GET");
            huc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");
            huc.connect();
            InputStream input = huc.getInputStream();

            String json = new String(input.readAllBytes(), StandardCharsets.UTF_8);

            byte[] bytes = json.getBytes("UTF-8");
            return Resultado.OK(new String(bytes, StandardCharsets.UTF_8));

        } catch (IOException e) {
            return Resultado.FALHAR("PROBLEMA_CONEXAO");
        }


    }


    public static Resultado<DKGObjeto, String> http_dkg_objeto(String link_url) {

        Resultado<String, String> dados = Internet.http(link_url);
        if (dados.isOK()) {
            DKG documento_dkg = JSON.JSON_TO_DKG(dados.getOK());
            return Resultado.OK(documento_dkg.unicoObjeto("JSON"));
        }

        return Resultado.FALHAR(dados.getErro());
    }

}
