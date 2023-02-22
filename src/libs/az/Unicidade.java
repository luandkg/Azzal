package libs.az;

import libs.dkg.DKG;
import libs.dkg.DKGObjeto;
import libs.armazenador.Banco;
import libs.armazenador.ItemDoBanco;
import libs.armazenador.Armazenador;

import java.util.ArrayList;

public class Unicidade {

    private String mNome;
    private Armazenador mArmazenador;
    private Banco mSequencias;
    private Banco mColecao;

    public Unicidade(String eNome, Armazenador eArmazenador, Banco eSequencias, Banco eColecao) {
        mNome = eNome;
        mArmazenador = eArmazenador;
        mSequencias = eSequencias;
        mColecao = eColecao;

        AZSequenciador.organizar_sequencial(mSequencias, mNome);

    }

    public boolean contem_valor(String valor) {

        for (ItemDoBanco item : mColecao.getItens()) {
            DKGObjeto objeto = DKG.PARSER_TO_OBJETO(item.lerTexto());
            if (objeto.identifique("Valor").isValor(valor)) {
                return true;
            }
        }

        return false;
    }

    public boolean adicionar(String valor) {

        if (!contem_valor(valor)) {

            int chave = AZSequenciador.aumentar_sequencial(mSequencias, mNome);

            DKGObjeto objeto = new DKGObjeto(mNome);
            objeto.identifique("ID").setInteiro(chave);
            objeto.identifique("Valor").setValor(valor);
            mColecao.adicionar(objeto.toString());
        }

        return false;
    }

    public void remover_valor(String valor) {

        for (ItemDoBanco item : mColecao.getItens()) {
            DKGObjeto objeto = DKG.PARSER_TO_OBJETO(item.lerTexto());
            if (objeto.identifique("Valor").isValor(valor)) {
                mColecao.remover(item);
                break;
            }
        }

    }

    public ArrayList<ItemDoBanco> getItens() {
        return mColecao.getItens();
    }
}
