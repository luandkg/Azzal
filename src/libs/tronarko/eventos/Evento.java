package libs.tronarko.eventos;

import libs.tronarko.Tozte;
import libs.tronarko.Tron;

public class Evento {

    public static final int PEQUENO = 1;
    public static final int GRANDE = 2;

    private String mNome;
    private Tozte mTozte;

    private Tron mTronInicio;
    private int mTipo;
    private PequenoEvento mPequeno;
    private GrandeEvento mGrande;



    public Evento(PequenoEvento pequeno, String eNome, Tozte eTozte) {

        mTipo = PEQUENO;

        this.mNome = eNome;
        this.mTozte = eTozte;

        mTronInicio = new Tron(1, 0, 0, mTozte.getSuperarko(), mTozte.getHiperarko(), mTozte.getTronarko());

        mPequeno = pequeno;
        mGrande = null;

    }

    public Evento(GrandeEvento grande, String eNome, Tozte eTozte) {

        mTipo = GRANDE;

        this.mNome = eNome;
        this.mTozte = eTozte;

        mTronInicio = new Tron(1, 0, 0, mTozte.getSuperarko(), mTozte.getHiperarko(), mTozte.getTronarko());

        mPequeno = null;
        mGrande = grande;

    }

    public final String getNome() {
        return this.mNome;
    }

    public final Tozte getTozte() {
        return this.mTozte;
    }

    public final Tron getTronInicio() {
        return this.mTronInicio;
    }


    public boolean isPequeno() {
        return mTipo == PEQUENO;
    }

    public boolean isGrande() {
        return mTipo == GRANDE;
    }

    public PequenoEvento getPequeno(){return mPequeno;}
    public GrandeEvento getGrande(){return mGrande;}

}
