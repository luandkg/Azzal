package AppAttuz.Ferramentas;

public class ArquivoSequencial {

    public static String getArquivo(String eArquivo,String extensao,int mapa_contagem) {
        String s_mapa = String.valueOf(mapa_contagem);
        if (s_mapa.length() == 1) {
            s_mapa = "0" + s_mapa;
        }
        return eArquivo + s_mapa + extensao;
    }

}
