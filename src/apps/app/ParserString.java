package apps.app;

import java.nio.charset.StandardCharsets;

public class ParserString {

    private String mString;
    private int mTamanho;
    private int indice;

    public ParserString(String eString) {
        mString = eString;
        mTamanho = mString.length();

        indice = 0;
    }

    public long getQuantidadeDeBytes() {
        return mString.getBytes(StandardCharsets.UTF_8).length;
    }


    public int getTamanho() {
        return mTamanho;
    }

    public boolean continuar() {
        return indice < mTamanho;
    }

    public void avancar() {
        indice += 1;
    }

    public String get() {
        return String.valueOf(mString.charAt(indice));
    }

    public String get_linha() {

        String linha = "";

        while (continuar()) {
            String letra = get();

            if (letra.contentEquals("\n")) {
                break;
            } else {
                linha += letra;
            }

            avancar();
        }

        return linha;
    }
}
