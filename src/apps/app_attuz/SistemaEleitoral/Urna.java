package apps.app_attuz.SistemaEleitoral;

import java.util.ArrayList;

public class Urna {

    private String mUnicidade;
    private String mCidade;

    private int mGrande;
    private int mPequeno;
    private int mTamanhoDaFila;
    private int mSequenciaDaFila;

    private ArrayList<CargoApuracao> cargos;
    private int mVotos;
    private String mTempoComecou;

    private int mPresenca;
    private int mPresencaObrigatoria;
    private int mAbstensao;

    public Urna(String eCidade, String eZona, String eSessao) {
        mUnicidade = eCidade + "::" + eZona + "::" + eSessao;
        mCidade = eCidade;
        mGrande = 0;
        mPequeno = 0;
        mSequenciaDaFila = 0;
        mTamanhoDaFila = 0;
        mVotos = 0;
        cargos = new ArrayList<CargoApuracao>();
        mTempoComecou = "";
        mPresenca = 0;
        mPresencaObrigatoria = 0;
        mAbstensao = 0;
    }

    public void setFila(int eMaximo) {
        mTamanhoDaFila = eMaximo;
    }

    public void setComecar(int a, int b) {
        mGrande = a;
        mPequeno = b;
        mTempoComecou = getTempo();
    }

    public String getTempoComecou() {
        return mTempoComecou;
    }

    public void votar(int tempo) {
        mPequeno += tempo;

        while (mPequeno >= 100) {
            mPequeno -= 100;
            mGrande += 1;
        }

        mSequenciaDaFila += 1;
        mVotos += 1;

    }

    public void passar_tempo(int tempo) {
        mPequeno += tempo;

        while (mPequeno >= 100) {
            mPequeno -= 100;
            mGrande += 1;
        }

    }


    public boolean nao_tem_ninguem_esperando() {
        return mSequenciaDaFila >= mTamanhoDaFila;
    }


    public String getTempo() {

        String s1 = String.valueOf(mGrande);
        String s2 = String.valueOf(mPequeno);

        if (s1.length() == 1) {
            s1 = "0" + s1;
        }
        if (s2.length() == 1) {
            s2 = "0" + s2;
        }

        return s1 + ":" + s2;
    }

    public String getUnicidade() {
        return mUnicidade;
    }

    public int getContagemDeVotos() {
        return mVotos;
    }

    public void guardar_voto(String nome_cargo, String candidato) {


        boolean existe = false;
        for (CargoApuracao c : cargos) {
            if (c.getNome().contentEquals(nome_cargo)) {
                existe = true;
                c.votar(candidato);
                break;
            }
        }
        if (!existe) {
            CargoApuracao c = new CargoApuracao(nome_cargo);
            c.votar(candidato);
            cargos.add(c);
        }


    }

    public String getCidade() {
        return mCidade;
    }

    public ArrayList<CargoApuracao> getCargos() {
        return cargos;
    }

    public void marcar_presenca() {
        mPresenca += 1;
    }

    public void marcar_presenca_obrigatoria() {
        mPresencaObrigatoria += 1;
    }

    public void marcar_abstensao() {
        mAbstensao += 1;
    }

    public int getAbstensao() {
        return mAbstensao;
    }


    public int getPresenca() {
        return mPresenca;
    }

    public int getPresencaObrigatoria() {
        return mPresencaObrigatoria;
    }

}
