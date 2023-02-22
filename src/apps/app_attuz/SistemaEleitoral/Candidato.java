package apps.app_attuz.SistemaEleitoral;

public class Candidato {

    private int mNumero;
    private String mNome;
    private String mPartido;


    public Candidato(String ePartido, int eNumero, String eNome) {
        mPartido = ePartido;
        mNumero = eNumero;
        mNome = eNome;
    }

    public String getPartido() {
        return mPartido;
    }

    public int getNumero() {
        return mNumero;
    }

    public String getNome() {
        return mNome;
    }

    public void mudarNumero(int e) {
        mNumero = e;
    }
}
