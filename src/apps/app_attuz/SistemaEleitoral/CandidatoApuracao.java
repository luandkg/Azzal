package apps.app_attuz.SistemaEleitoral;

public class CandidatoApuracao {

    private String mNome;
    private int mVotos;

    public CandidatoApuracao(String eNome) {
        mNome = eNome;
        mVotos = 0;
    }

    public String getNome() {
        return mNome;
    }

    public int getVotos() {
        return mVotos;
    }

    public void aumentar() {
        mVotos += 1;
    }

    public void aumentar(int quantidade) {
        mVotos += quantidade;
    }
}
