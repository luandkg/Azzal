package libs.az;

import libs.armazenador.*;

public class AZColecionador {

    private Armazenador mArmazenador;
    private Banco s_sequencias;

    public AZColecionador(String eArquivo) {
        mArmazenador = new Armazenador(eArquivo);
        s_sequencias = AZSequenciador.organizar_banco(mArmazenador, "@Sequencias");
    }

    public Colecao getColecao(String eNome) {
        return new Colecao(eNome, mArmazenador, s_sequencias, AZSequenciador.organizar_banco(mArmazenador, eNome));
    }

    public Unicidade getSettum(String eNome) {
        return new Unicidade(eNome, mArmazenador, s_sequencias, AZSequenciador.organizar_banco(mArmazenador, eNome));
    }

    public long getSequenciaID(String eNome) {
        return AZSequenciador.get_sequencial_contador(s_sequencias, eNome);
    }


    public void remover_colecao(String eNome) {
         mArmazenador.banco_remover(eNome);
    }

    public ItemDoBanco getItemDireto(long ePointeiro) {

        ItemDoBanco item = mArmazenador.getItemDireto(ePointeiro);
        if (item.existe()) {
            return item;
        } else {
            return null;
        }

    }


    public Armazenador getMomentum() {
        return mArmazenador;
    }

    public void fechar() {
        mArmazenador.fechar();
    }


    public static void checar(String eArquivo) {

        if (!Armazenador.existe(eArquivo)) {
            Armazenador.criar(eArquivo);
        }

    }
}
