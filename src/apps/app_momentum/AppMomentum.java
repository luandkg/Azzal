package apps.app_momentum;

import libs.armazenador.Banco;
import libs.armazenador.Debugador;
import libs.armazenador.ItemDoBanco;
import libs.az.AZColecionador;
import libs.az.Colecao;
import libs.az.Unicidade;
import libs.dkg.DKG;
import libs.dkg.DKGObjeto;
import libs.luan.Indexado;
import libs.luan.Indexamento;
import libs.luan.Opcional;
import libs.tempo.Calendario;

import java.util.Random;

public class AppMomentum {


    public static void init() {

        String arquivo_banco = "/home/luan/assets/luan.mt";

        AZColecionador.checar(arquivo_banco);


        AZColecionador m = new AZColecionador(arquivo_banco);

        m.getColecao("Lixeira");

        Debugador.debug_colecionador_completo(m);


        m.remover_colecao("Lixeira");

        Debugador.debug_colecionador(m);
        for (Banco banco : m.getMomentum().getBancos()) {
            Debugador.debug_banco(banco);
        }


        System.out.println("## Objetos (ID) = " + m.getSequenciaID("Objetos"));

        Debugador.debug_colecionador_completo(m);


        Unicidade valores = m.getSettum("Valores");

        Random sorte = new Random();
        int s = sorte.nextInt(20);
        String ss = String.valueOf(s);

        String ss_status = "";

        if (!valores.contem_valor(ss)) {
            valores.adicionar(ss);
            ss_status = "Adicionado";
        } else {
            ss_status = "Ja existente";
        }

        System.out.println("Settum - " + ss + " :: " + ss_status);

        for (ItemDoBanco item : valores.getItens()) {
            System.out.println("@@@ " + item.lerTextoLinearizado());
        }


        Debugador.debug_colecionador_completo(m);


        Colecao objetos = m.getColecao("Objetos");

        int remover_quantos = sorte.nextInt(30) + 5;
        int removendo = 0;

        for (Indexado<ItemDoBanco> item_indexado : Indexamento.embaralhar(objetos.getItens())) {
            if (removendo < remover_quantos) {

                ItemDoBanco item = item_indexado.get();

                System.out.println(" @@ -->> Removendo.... - " + removendo + " :: " + item.getPonteiro() + " -->> " + item.lerTextoLinearizado());
                // objetos.remover(item);
                System.out.println(" @@ -->> Removido");

                removendo += 1;
            }

        }

        Debugador.debug_colecionador_completo(m);


        int mais_quantos = sorte.nextInt(200) + 50;

        for (int v = 0; v < mais_quantos; v++) {

            System.out.println(" @@ -->> Adicionando....." + v + " de " + mais_quantos);

            DKGObjeto novo = new DKGObjeto("Objeto");
            novo.identifique("DATA", Calendario.getData());
            objetos.adicionar(novo);


        }


        Debugador.debug_colecionador_completo(m);


        Opcional<ItemDoBanco> proc2 = objetos.getIndexado(150);
        System.out.println("T150 :: " + proc2.temValor());

        if (proc2.temValor()) {

            ItemDoBanco item = proc2.get();

            System.out.println("V150 :: " + item.lerTextoLinearizado());

            //   m.remover(item);

        }

        objetos.remover_por_chave(300);

        Opcional<ItemDoBanco> proc300 = objetos.getIndexado(300);
        System.out.println("T300 :: " + proc300.temValor());

        if (proc300.temValor()) {

            ItemDoBanco item = proc300.get();

            System.out.println("V300 :: " + item.lerTextoLinearizado());

            //   m.remover(item);

        }

        Opcional<ItemDoBanco> proc2t = objetos.getIndexado(2000);
        System.out.println("T2000 :: " + proc2t.temValor());

        if (proc2t.temValor()) {

            ItemDoBanco item = proc2t.get();

            System.out.println("V2000 :: " + item.lerTextoLinearizado());


        }


        Debugador.debug_colecionador_completo(m);


        for (ItemDoBanco item : objetos.getItens()) {
            //System.out.println(" ## -->> " + item.lerTextoLinearizado());
        }

        for (ItemDoBanco item : m.getColecao("@Sequencias").getItens()) {
            System.out.println(" ## -->> " + item.lerTextoLinearizado());
        }

        comparar_velocidade_de_busca(objetos, 500);


        m.fechar();

    }

    public static void comparar_velocidade_de_busca(Colecao eColecao, int chave_id) {

        long at1 = System.nanoTime();

        boolean enc = false;

        for (ItemDoBanco item : eColecao.getItens()) {
            DKGObjeto obj = DKG.PARSER_TO_OBJETO(item.lerTexto());
            if (obj.identifique("ID").isValor(chave_id)) {

                System.out.println("T" + chave_id + " :: true");
                System.out.println("V" + chave_id + " :: " + item.lerTextoLinearizado());

                enc = true;
                break;
            }
        }

        long at2 = System.nanoTime();


        long bt1 = System.nanoTime();

        Opcional<ItemDoBanco> proc500 = eColecao.getIndexado(chave_id);
        System.out.println("T" + chave_id + " :: " + proc500.temValor());

        if (proc500.temValor()) {
            ItemDoBanco item = proc500.get();
            System.out.println("V" + chave_id + " :: " + item.lerTextoLinearizado());
        }

        long bt2 = System.nanoTime();

        System.out.println("Delta A = " + (at2 - at1));
        System.out.println("Delta B = " + (bt2 - bt1));


    }


}
