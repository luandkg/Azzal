package libs.aqz;

import libs.aqz.colecao.AZInternamenteTX;
import libs.aqz.colecao.AZInternamenteUTF8;
import libs.aqz.colecao.ColecaoTX;
import libs.aqz.colecao.ColecaoUTF8;
import libs.aqz.extincao.AQZExtincao;
import libs.aqz.extincao.AQZUTF8Antigamente;
import libs.armazenador.Armazenador;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;

public class MigrattorAQZ {



    public static void MIGRAR_TX_BRUTO_PARA_TX_STRING_VIEW(String arquivo_banco_origem,String arquivo_banco_destino,String colecao_nome){


        Lista<Entidade> dados_original = AQZExtincao.COLECAO_ITENS(arquivo_banco_origem, colecao_nome);

        Armazenador armazenador_destino = new Armazenador(arquivo_banco_destino);

        AZInternamenteTX tx_destino = new AZInternamenteTX(armazenador_destino);

        ColecaoTX colecao_destino = tx_destino.colecao_orgarnizar_e_obter(colecao_nome);
        colecao_destino.zerarSequencial();
        colecao_destino.limpar();


        for(Entidade e : dados_original){
            colecao_destino.adicionar(e);
        }

        Lista<Entidade> dados = colecao_destino.getObjetos();

        ENTT.EXIBIR_TABELA(dados);

        armazenador_destino.fechar();


    }

    public static void MIGRAR_UTF8(String arquivo_banco_origem,String arquivo_banco_destino,String colecao_nome){


        Lista<Entidade> dados_original = AQZUTF8Antigamente.OBTER_CONTEUDO(arquivo_banco_origem, colecao_nome);

        Armazenador mArmazenador = new Armazenador(arquivo_banco_destino);

        AZInternamenteUTF8 tx = new AZInternamenteUTF8(mArmazenador);

        ColecaoUTF8 numeros = tx.colecao_orgarnizar_e_obter(colecao_nome);
        numeros.zerarSequencial();
        numeros.limpar();


        for(Entidade e : dados_original){
            numeros.adicionar(e);
        }

        Lista<Entidade> dados = numeros.getObjetos();

        ENTT.EXIBIR_TABELA(dados);

        mArmazenador.fechar();


    }

    public static void MIGRAR_TX_BRUTO_PARA_UTF8(String arquivo_banco_origem,String arquivo_banco_destino,String colecao_nome){


        Lista<Entidade> dados_original = AQZExtincao.COLECAO_ITENS(arquivo_banco_origem, colecao_nome);

        Armazenador armazenador_destino = new Armazenador(arquivo_banco_destino);

        AZInternamenteUTF8 tx_destino = new AZInternamenteUTF8(armazenador_destino);

        ColecaoUTF8 colecao_destino = tx_destino.colecao_orgarnizar_e_obter(colecao_nome);
        colecao_destino.zerarSequencial();
        colecao_destino.limpar();


        for(Entidade e : dados_original){
            colecao_destino.adicionar(e);
        }

        Lista<Entidade> dados = colecao_destino.getObjetos();

        ENTT.EXIBIR_TABELA(dados);

        armazenador_destino.fechar();


    }

}
