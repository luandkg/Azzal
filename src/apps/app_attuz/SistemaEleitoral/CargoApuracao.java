package apps.app_attuz.SistemaEleitoral;

import java.util.ArrayList;

public class CargoApuracao {

    private String mNome;
    private ArrayList<CandidatoApuracao> mCandidatos;

    public CargoApuracao(String eNome) {
        mNome = eNome;
        mCandidatos = new ArrayList<CandidatoApuracao>();
    }


    public void criar_candidato(String eNome) {

        boolean existe = false;
        for (CandidatoApuracao c : mCandidatos) {
            if (c.getNome().contentEquals(eNome)) {
                existe = true;
                break;
            }
        }
        if (!existe) {
            CandidatoApuracao c = new CandidatoApuracao(eNome);
            mCandidatos.add(c);
        }

    }

    public String getNome() {
        return mNome;
    }

    public ArrayList<CandidatoApuracao> getCandidatos() {
        return mCandidatos;
    }

    public void votar(String eNome) {
        boolean existe = false;
        for (CandidatoApuracao c : mCandidatos) {
            if (c.getNome().contentEquals(eNome)) {
                existe = true;
                c.aumentar();
                break;
            }
        }
        if (!existe) {
            CandidatoApuracao c = new CandidatoApuracao(eNome);
            c.aumentar();
            mCandidatos.add(c);
        }
    }

    public void votar_quantidade(String eNome, int quantidade) {
        boolean existe = false;
        for (CandidatoApuracao c : mCandidatos) {
            if (c.getNome().contentEquals(eNome)) {
                existe = true;
                c.aumentar(quantidade);
                break;
            }
        }
        if (!existe) {
            CandidatoApuracao c = new CandidatoApuracao(eNome);
            c.aumentar(quantidade);
            mCandidatos.add(c);
        }
    }
}
