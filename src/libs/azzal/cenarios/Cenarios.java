package libs.azzal.cenarios;


import libs.luan.Lista;

public class Cenarios {

    private Lista<Cenario> mCenarios;
    private int mID;

    public Cenarios() {
        mCenarios = new Lista<Cenario>();
        mID = 0;
    }

    public int CriarCenario(Cena eCena) {

        Cenario eCenario = new Cenario(mID, eCena);
        mCenarios.adicionar(eCenario);
        mID += 1;

        return eCenario.getID();
    }

    public Cenario getCenario(int eID) {

        for (Cenario mCenario : mCenarios) {
            if (mCenario.getID() == eID) {
                return mCenario;
            }
        }

        return null;
    }

    public void RemoverCenario(int eID) {

        for (Cenario mCenario : mCenarios) {
            if (mCenario.getID() == eID) {
                mCenarios.remover(mCenario);
                break;
            }
        }

    }

}
