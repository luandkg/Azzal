package Extenum.Colecionador;

import Extenum.Arquivador.Bloco;

public class Registro {

    private long mChave;
    private long mConteudo;

    private Bloco mBloco;

    public Registro(Bloco eBloco, long eChave, long eConteudo) {

        mBloco = eBloco;

        mChave = eChave;
        mConteudo = eConteudo;

    }

    public long getChave() {
        return mChave;
    }

    public long getConteudoID() {
        return mConteudo;
    }

    public String getConteudo() {
        return mBloco.getArquivador().getBloco(mConteudo).lerObjeto(0);
    }

    public Bloco getConteudoBloco() {
        return mBloco.getArquivador().getBloco(mConteudo);
    }
}
