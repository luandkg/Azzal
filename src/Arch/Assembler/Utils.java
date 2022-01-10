package Arch.Assembler;

import java.util.ArrayList;

public class Utils {

    private RefString mCodigo;
    private ArrayList<String> mErros;

    private final String ALFABETO = "abcdefghijklmnopqrstuwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_";
    private final String ALFABETONUMERICO = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_0123456789";
    private final String NUMEROS = "0123456789";

    public Utils(RefString eCodigo, ArrayList<String> eErros) {
        mCodigo = eCodigo;
        mErros = eErros;
    }


    public boolean isAlfaNum(String eValor) {

        int i = 0;
        int o = ALFABETONUMERICO.length();
        boolean ret = false;

        while (i < o) {

            String eCorrente = String.valueOf(ALFABETONUMERICO.charAt(i));


            if (eCorrente.contentEquals(eValor)) {
                ret = true;
                break;
            }

            i += 1;
        }

        return ret;
    }

    public String getIdentificador() {

        String mIdentificador = "";

        while (mCodigo.getIndex() < mCodigo.getTamanho()) {

            String eCorrente = mCodigo.getCorrente();

            if (isAlfaNum(eCorrente)) {
                mIdentificador += eCorrente;
            } else {
                break;
            }

            mCodigo.aumentar(1);
        }

        return mIdentificador;
    }
    public String getNumero() {

        String mIdentificador = "";

        while (mCodigo.getIndex() < mCodigo.getTamanho()) {

            String eCorrente = mCodigo.getCorrente();

            if (isNumero(eCorrente)) {
                mIdentificador += eCorrente;
            } else {
                break;
            }

            mCodigo.aumentar(1);
        }

        return mIdentificador;
    }

    public boolean isEspaco(String eValor) {
        return eValor.contentEquals(" ") || eValor.contentEquals("\n") || eValor.contentEquals("\t")|| eValor.contentEquals("\r");
    }




    public boolean isPonto(String eValor) {
        return eValor.contentEquals(".");
    }


    public boolean isExclamacao(String eValor) {
        return eValor.contentEquals("!");
    }

    public boolean isRegistrador(String eValor) {
        return eValor.contentEquals("$");
    }

    public boolean isVirgula(String eValor) {
        return eValor.contentEquals(",");
    }

    public boolean isMenor(String eValor) {
        return eValor.contentEquals("<");
    }
    public boolean isMaior(String eValor) {
        return eValor.contentEquals(">");
    }


    public boolean isArroba(String eValor) {
        return eValor.contentEquals("@");
    }

    public boolean esperarVigula() {

        boolean tem = false;

        while (mCodigo.getIndex() < mCodigo.getTamanho()) {

            String eCorrente = mCodigo.getCorrente();

            if (isEspaco(eCorrente)) {
            } else if (isVirgula(eCorrente)) {
                mCodigo.aumentar(1);
                tem = true;
                break;
            }

            mCodigo.aumentar(1);
        }


        return tem;

    }

    public boolean isAlfa(String eValor) {

        int i = 0;
        int o = ALFABETO.length();
        boolean ret = false;

        while (i < o) {

            String eCorrente = String.valueOf(ALFABETO.charAt(i));

            if (eCorrente.contentEquals(eValor)) {
                ret = true;
                break;
            }

            i += 1;
        }

        return ret;
    }

    public boolean isNumero(String eValor) {

        int i = 0;
        int o = NUMEROS.length();
        boolean ret = false;

        while (i < o) {

            String eCorrente = String.valueOf(NUMEROS.charAt(i));

            if (eCorrente.contentEquals(eValor)) {
                ret = true;
                break;
            }

            i += 1;
        }

        return ret;
    }

    public boolean isDoisPontos(String eValor) {
        return eValor.contentEquals(":");
    }

    public boolean esperaDoisPontos() {

        boolean tem = false;

        while (mCodigo.getIndex() < mCodigo.getTamanho()) {

            String eCorrente = mCodigo.getCorrente();

            if (isEspaco(eCorrente)) {
            } else if (isDoisPontos(eCorrente)) {
                mCodigo.aumentar(1);
                tem = true;
                break;
            }

            mCodigo.aumentar(1);
        }

        return tem;

    }

}
