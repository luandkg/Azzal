package libs.aqz;

import libs.aqz.colecao.AQZInternamenteTX;
import libs.aqz.colecao.AQZInternamenteUTF8;
import libs.aqz.colecao.ColecaoTX;
import libs.aqz.colecao.ColecaoUTF8;
import libs.armazenador.Armazenador;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;

public class MigrattorAQZ {



    public static void MIGRAR_TX_BRUTO_PARA_TX_STRING_VIEW(String arquivo_banco,String colecao_nome){


        Lista<Entidade> dados_original = AQZ.COLECAO_ITENS(arquivo_banco, colecao_nome);

        Armazenador mArmazenador = new Armazenador(arquivo_banco);

        AQZInternamenteTX tx = new AQZInternamenteTX(mArmazenador);

        ColecaoTX numeros = tx.colecao_orgarnizar_e_obter(colecao_nome);
        numeros.zerarSequencial();
        numeros.limpar();


        for(Entidade e : dados_original){
            numeros.adicionar(e);
        }

        Lista<Entidade> dados = numeros.getObjetos();

        ENTT.EXIBIR_TABELA(dados);

        mArmazenador.fechar();


    }

    public static void MIGRAR_UTF8(String arquivo_banco,String colecao_nome){


        Lista<Entidade> dados_original = AQZUTF8Antigamente.OBTER_CONTEUDO(arquivo_banco, colecao_nome);

        Armazenador mArmazenador = new Armazenador(arquivo_banco);

        AQZInternamenteUTF8 tx = new AQZInternamenteUTF8(mArmazenador);

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

}
