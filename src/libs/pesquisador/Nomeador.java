package libs.pesquisador;

import java.util.ArrayList;
import java.util.Random;

public class Nomeador {

    private String ALFABETO;
    private Random eSorteador;


    public Nomeador() {
        ALFABETO = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        eSorteador = new Random();
    }


    public String nomearCromossomo(ArrayList<String> mNomesRegistrados) {

        boolean mUnico = false;
        String mRegistrado = "";

        int letras = 2;

        while (!mUnico) {

            String eMontandoNome = "";
            for (int i = 0; i < letras; i++) {

                int letra = eSorteador.nextInt(ALFABETO.length());
                eMontandoNome += ALFABETO.charAt(letra);

            }

            if (!mNomesRegistrados.contains(eMontandoNome)) {
                mUnico = true;
                mRegistrado = eMontandoNome;
            }

        }

        return mRegistrado;
    }


    public String nomearCromossomoBemDiferente(ArrayList<String> mNomesRegistrados) {

        boolean mUnico = false;
        String mRegistrado = "";

        int letras = 2;

        while (!mUnico) {

            String eMontandoNome = "";
            for (int i = 0; i < letras; i++) {

                int letra = eSorteador.nextInt(ALFABETO.length());
                eMontandoNome += ALFABETO.charAt(letra);

            }

            if (!mNomesRegistrados.contains(eMontandoNome)) {
                boolean jaIniciado = false;
                for (String eOutroNome : mNomesRegistrados) {
                    if (eOutroNome.contains(String.valueOf(eMontandoNome.charAt(0)))) {
                        jaIniciado = true;
                    } else if (eOutroNome.contains(String.valueOf(eMontandoNome.charAt(1)))) {
                        jaIniciado = true;
                    }
                }


                if (!jaIniciado) {
                    mUnico = true;
                    mRegistrado = eMontandoNome;
                }

            }

        }

        return mRegistrado;
    }


    public String nomearGene(ArrayList<String> mNomesRegistrados) {

        boolean mUnico = false;
        String mRegistrado = "";

        int letras = 3;

        while (!mUnico) {

            String eMontandoNome = "";
            for (int i = 0; i < letras; i++) {

                int letra = eSorteador.nextInt(ALFABETO.length());
                eMontandoNome += ALFABETO.charAt(letra);

            }

            if (!mNomesRegistrados.contains(eMontandoNome)) {
                mUnico = true;
                mRegistrado = eMontandoNome;
            }

        }

        return mRegistrado;
    }


}
