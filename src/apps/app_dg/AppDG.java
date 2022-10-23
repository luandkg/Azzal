package apps.app_dg;

import libs.arquivos.binario.Arquivador;
import libs.Luan.Opcional;
import libs.dg.*;
import libs.tronarko.Tronarko;

import java.util.Random;

public class AppDG {

    private static String LOCAL_CONTAINERS = "/home/luan/Containers";
    private static String LOCAL_COLECAO_AZZAL = "/home/luan/Containers/azzal.dg";

    public static void init() {

        boolean mostrar = false;
        if (mostrar) {
            System.out.println("DG 1.0");
            System.out.println("Colecoes :: " + DG.COLECOES);
            System.out.println("Paginas :: " + DG.PAGINAS);
            System.out.println("Itens por Pagina :: " + DG.ITENS_POR_PAGINA);
            System.out.println("Maximo de Itens por Colecao :: " + DG.MAX_ITENS);
        }

        // TESTE_COLECAO();

        // TESTE_PAGINAS();

        // TESTE_MELHOR();

        // CONTAGEM GERAL

        System.out.println("-------------- LISTAR -------------------");

        DG azzal_dg = new DG(LOCAL_COLECAO_AZZAL, true);

        DGObjeto a1 = new DGObjeto(DG.CHAVE_UNICA, "ID");

        a1.identifique("DDC", Tronarko.getAgora());
        a1.identifique("VALOR", new Random().nextInt(100));

        azzal_dg.colecao("TimeStamps").adicionar(a1);

        azzal_dg.colecao("TimeStamps").mostrar_tudo();

        Opcional<DGItem> ultimo_inserido = Features.PROCURAR_ULTIMO(azzal_dg, "TimeStamps", "ID");

        if (ultimo_inserido.isOK()) {
            // System.out.println(ultimo_inserido.get().getValorEmLinha());
        }

        // azzal_dg.mostrar_informacoes();

        azzal_dg.mostrar_colecoes();

        azzal_dg.fechar();

    }

    public static void TESTE_COLECAO() {

        Arquivador.remover(LOCAL_COLECAO_AZZAL);

        System.out.println("DG ->> ABRINDO");

        DG azzal_dg = new DG(LOCAL_COLECAO_AZZAL, true);

        azzal_dg.colecao("itens").adicionar(new DGObjeto());

        azzal_dg.colecao("itens");

        for (DGColecao colecao : azzal_dg.getColecoes()) {
            System.out.println("\t -- Colecao :: " + colecao.getNome() + " -->> " + colecao.getPaginasContagem()
                    + " com " + colecao.getItensContagem());
        }

        azzal_dg.fechar();
        System.out.println("DG ->> FECHADO");

    }

    public static void TESTE_PAGINAS() {

        Arquivador.remover(LOCAL_COLECAO_AZZAL);

        DG azzal_dg = new DG(LOCAL_COLECAO_AZZAL, true);

        for (int i = 0; i < 5000; i++) {

            DGObjeto a1 = new DGObjeto();

            a1.identifique("ID", azzal_dg.indicePara("CHAVES"));
            a1.identifique("DDC", Tronarko.getAgora());
            a1.identifique("VALOR", new Random().nextInt(100));
            a1.identifique("CONTAGEM", i);

            azzal_dg.colecao("CHAVES").adicionar(a1);

            // DGColecao colecao = azzal_dg.colecao("CHAVES");

            // System.out.println("\t -- Colecao :: " + colecao.getNome() + " -->> " +
            // colecao.getPaginasContagem() + " com " + colecao.getItensContagem());

        }

        for (int i = 0; i < 1000; i++) {

            DGObjeto a1 = new DGObjeto();
            a1.identifique("ID", azzal_dg.indicePara("CHAVES"));
            a1.identifique("DDC", Tronarko.getAgora());
            a1.identifique("VALOR", new Random().nextInt(100));
            a1.identifique("CONTAGEM", i);

            azzal_dg.colecao("CHAVES").adicionar(a1);

            // DGColecao colecao = azzal_dg.colecao("CHAVES");

            // System.out.println("\t -- Colecao :: " + colecao.getNome() + " -->> " +
            // colecao.getPaginasContagem() + " com " + colecao.getItensContagem());

        }

        azzal_dg.fechar();

    }

    public static void TESTE_MELHOR() {

        Arquivador.remover(LOCAL_COLECAO_AZZAL);

        DG azzal_dg = new DG(LOCAL_COLECAO_AZZAL, true);

        for (int i = 0; i < 50; i++) {

            DGObjeto a1 = new DGObjeto();
            a1.identifique("ID", i);
            a1.identifique("DDC", Tronarko.getAgora());
            a1.identifique("MNG", Tronarko.getHazde().toString());

            azzal_dg.colecao("itens").adicionar(a1);

            DGColecao colecao = azzal_dg.colecao("itens");

            // System.out.println("\t -- Colecao :: " + colecao.getNome() + " -->> " +
            // colecao.getPaginasContagem() + " com " + colecao.getItensContagem());

        }

        azzal_dg.fechar();

    }

}
