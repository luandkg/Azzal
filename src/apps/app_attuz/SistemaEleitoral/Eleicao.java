package apps.app_attuz.SistemaEleitoral;

import libs.luan.Aleatorio;

import java.util.ArrayList;

public class Eleicao {

    private ArrayList<Candidato> mPresidencia;
    private ArrayList<Candidato> mSenado;
    private ArrayList<Candidato> mSecretaria;

    public Eleicao() {
        mPresidencia = new ArrayList<Candidato>();
        mSenado = new ArrayList<Candidato>();
        mSecretaria = new ArrayList<Candidato>();
    }

    public void presidencia(String ePartido, int eNumero, String eNome) {
        mPresidencia.add(new Candidato(ePartido, eNumero, eNome));
    }

    public void senado(String ePartido, int eNumero, String eNome) {
        mSenado.add(new Candidato(ePartido, eNumero, eNome));
    }

    public void secretaria(String ePartido, int eNumero, String eNome) {
        mSecretaria.add(new Candidato(ePartido, eNumero, eNome));
    }

    public ArrayList<Candidato> getPresidencia() {
        return mPresidencia;
    }


    public ArrayList<Candidato> getSenado() {
        return mSenado;
    }

    public ArrayList<Candidato> getSecretaria() {
        return mSecretaria;
    }

    public void embaralhar() {


        embaralhar_candidatos(mPresidencia);

        embaralhar_candidatos(mSenado);

        embaralhar_candidatos(mSecretaria);


    }

    public static void embaralhar_candidatos(ArrayList<Candidato> candidatos) {

        for (int i = 0; i < candidatos.size(); i++) {

            int p1 = Aleatorio.aleatorio(candidatos.size());
            int p2 = Aleatorio.aleatorio(candidatos.size());

            Candidato c1 = candidatos.get(p1);
            Candidato c2 = candidatos.get(p2);

            candidatos.set(p1, c2);
            candidatos.set(p2, c1);

        }

    }

    public static ArrayList<String> fazer_lista_de_nomes(ArrayList<Candidato> candidatos) {

        embaralhar_candidatos(candidatos);

        ArrayList<String> ls = new ArrayList<String>();
        for (Candidato c : candidatos) {
            ls.add(c.getNome());
        }
        return ls;
    }

}
