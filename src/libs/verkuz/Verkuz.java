package libs.verkuz;

import java.util.ArrayList;

public class Verkuz {

    private String mAutor;
    private int mModo;
    private int mEstagio;
    private String mFaseCorrente;
    private String mFasePrimaria;

    private ArrayList<Commit> mBuilds;

    public static final int SIMPLES = 1;
    public static final int COMPLEXO = 2;


    public static final int RELEASE = 1;
    public static final int TESTE = -1;


    public Verkuz() {
        mAutor = "";
        mFaseCorrente = "";
        mFasePrimaria = "";
        mModo = Verkuz.SIMPLES;
        mEstagio = Verkuz.TESTE;
        mBuilds = new ArrayList<Commit>();
    }


    public void setModo(int eModo) {
        mModo = eModo;
    }

    public void setAutor(String eAutor) {
        mAutor = eAutor;
    }

    public String getAutor() {
        return mAutor;
    }

    public int getEstagio() {
        return mEstagio;
    }

    public void setEstagio(int eEstagio) {
        mEstagio = eEstagio;
    }

    public void FASE_NOVA(String eFase) {
        mFaseCorrente = eFase;
        if (mFasePrimaria.length() == 0) {
            mFasePrimaria = eFase;
        }
    }

    public String FASE_CORRENTE() {
        return mFaseCorrente;
    }

    public String FASE_PRIMARIA() {
        return mFasePrimaria;
    }

    public void DEV(String eData, String eCommit) {
        mBuilds.add(new Commit(mBuilds.size(), eData, eCommit));
    }

    public String getVersaoApenas() {

        String versao = "";

        if (mModo == COMPLEXO) {

            int maior = 0;
            int menor = 0;

            int quantidade = getBuilds();

            while (quantidade >= 10) {
                quantidade -= 10;
                menor += 1;
            }

            while (menor >= 10) {
                menor -= 10;
                maior += 1;
            }

            versao = maior + "." + menor;

        } else if (mModo == SIMPLES) {

            int maior = 0;

            int menor = getBuilds();

            while (menor >= 10) {
                menor -= 10;
                maior += 1;
            }

            versao = maior + "." + menor;

        }

        return versao;
    }

    public String getVersao() {

        String versao = "";

        if (mModo == COMPLEXO) {

            int maior = 0;
            int menor = 0;

            int quantidade = getBuilds();

            while (quantidade >= 10) {
                quantidade -= 10;
                menor += 1;
            }

            while (menor >= 10) {
                menor -= 10;
                maior += 1;
            }

            versao = maior + "." + menor + " #" + quantidade;

        } else if (mModo == SIMPLES) {

            int maior = 0;

            int menor = getBuilds();

            while (menor >= 10) {
                menor -= 10;
                maior += 1;
            }

            versao = maior + "." + menor;

        }

        return versao;
    }

    public String getVersaoCompleta() {

        String versao = "";

        if (mModo == COMPLEXO) {
            int maior = 0;
            int menor = 0;

            int quantidade = getBuilds();

            while (quantidade >= 10) {
                quantidade -= 10;
                menor += 1;
            }

            while (menor >= 10) {
                menor -= 10;
                maior += 1;
            }

            if (quantidade == 0) {
                versao = maior + "." + menor;
            } else {
                versao = maior + "." + menor + " #PATCH " + quantidade;
            }
        } else if (mModo == SIMPLES) {

            int maior = 0;

            int menor = getBuilds();

            while (menor >= 10) {
                menor -= 10;
                maior += 1;
            }

            versao = maior + "." + menor;


        }

        return versao;
    }

    public String getPatchCompleto() {

        String versao = "";

        if (mModo == COMPLEXO) {
            int maior = 0;
            int menor = 0;

            int quantidade = getBuilds();

            while (quantidade >= 10) {
                quantidade -= 10;
                menor += 1;
            }

            while (menor >= 10) {
                menor -= 10;
                maior += 1;
            }

            if (quantidade == 0) {
                versao = "";
            } else {
                versao = "#PATCH " + quantidade;
            }
        } else if (mModo == SIMPLES) {


            versao = "";


        }

        return versao;
    }


    public int getBuilds() {
        return mBuilds.size();
    }

    public ArrayList<Commit> getCommits() {
        return mBuilds;
    }

    public String getUltima() {
        String ret = "";

        if (mBuilds.size() > 0) {
            ret = mBuilds.get(0).getData();
        }

        return ret;
    }

    public String getIniciado() {
        String ret = "";

        if (mBuilds.size() > 0) {
            ret = mBuilds.get(mBuilds.size() - 1).getData();
        }

        return ret;
    }


    public String getVersaoCompletaComAutor() {
        return getVersaoCompleta() + " - " + getAutor();
    }


}
