package libs.utils;

import libs.arquivos.TX;
import libs.arquivos.binario.Arquivador;
import libs.arquivos.binario.Inteiro;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;

public class DS_ENTT {

    public static void DUMP(String eArquivo) {

        Lista<Entidade> itens = new Lista<Entidade>();

        Arquivador arquivar = new Arquivador(eArquivo);

        //  System.out.println("Tamanho :: " + arquivar.getLength());

        arquivar.setPonteiro(0);

        int b1 = Inteiro.byteToInt(arquivar.get());
        int b2 = Inteiro.byteToInt(arquivar.get());

        //  System.out.println("B1 :: " + b1);
        //   System.out.println("B2 :: " + b2);

        int status = Inteiro.byteToInt(arquivar.get());

        long t = arquivar.getLength();

        int index = 0;

        while ((status == 255 || status == 111) && arquivar.getPonteiro() < t) {

            long item_tamanho = arquivar.get_u64();


            long comecar_nome = arquivar.getPonteiro();

            TX eTX = new TX();
            String nome = eTX.lerFluxoLimitado(arquivar, 1024);

            arquivar.setPonteiro(comecar_nome + 1024);

            long antes = arquivar.getPonteiro();

            //   itens.add(new DSItem(eArquivo, index, status, nome, item_tamanho, antes));
            long dados_tamanho=0;

            if(status==111){
                dados_tamanho = arquivar.get_u64();
            }

            Entidade e = ENTT.CRIAR_EM(itens);
            e.at("Status", status);
            e.at("Index", index);
            e.at("Nome", nome);
            e.at("Inicio", antes);
            e.at("Tamanho", item_tamanho);

            if(status==111){
                e.at("PreAlocado", item_tamanho);
                e.at("PreTamanho", dados_tamanho);
            }

            arquivar.setPonteiro(antes + item_tamanho);
            status = Inteiro.byteToInt(arquivar.get());

            index += 1;

        }

        arquivar.encerrar();

        ENTT.EXIBIR_TABELA(itens);
    }

}
