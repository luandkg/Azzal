package apps.app_momentum;

import libs.armazenador.Debugador;
import libs.aqz.utils.ItemDoBanco;
import libs.asset.Arquivo;
import libs.asset.AssetContainer;
import libs.asset.AssetCreator;
import libs.aqz.BancoBS;
import libs.aqz.colecao.Colecao;
import libs.dkg.DKG;
import libs.dkg.DKGObjeto;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Opcional;
import libs.luan.fmt;

public class HarrempluzCreator {

    public static void criar() {


        String arquivo_banco = "/home/luan/assets/tron.az";

        BancoBS.checar(arquivo_banco);


        BancoBS colecoes = new BancoBS(arquivo_banco);

        Colecao tronarko = colecoes.getColecao("Tronarko");


        int t1 = 6900;
        int t2 = 7200;

        for (int t = t1; t < t2; t++) {

            for (int m = 1; m <= 50; m++) {


                Entidade objeto_megarko = new Entidade();
                objeto_megarko.at("Tronarko", t);
                objeto_megarko.at("Megarko", m);

                for (int s = 1; s <= 10; s++) {
                    objeto_megarko.getEntidades().adicionar(Harrumpluz.construir("S" + s));
                }

                tronarko.adicionar(objeto_megarko);

                System.out.println(" @@ -->> Adicionando " + fmt.espacar_antes(m, 3) + " / " + fmt.espacar_antes(t, 3) + " :: " + ENTT.TO_DOCUMENTO(objeto_megarko).length());

            }

            Debugador.debug_colecionador_completo(colecoes);

        }


        Debugador.debug_colecionador_completo(colecoes);


        colecoes.fechar();

    }


    public static void visualizar() {

        String arquivo_banco = "/home/luan/assets/tron.az";

        BancoBS.checar(arquivo_banco);


        BancoBS colecoes = new BancoBS(arquivo_banco);


        Debugador.debug_colecionador_completo(colecoes);

        Colecao tronarko = colecoes.getColecao("Tronarko");

        boolean mostrar = false;

        if (mostrar) {

            for (ItemDoBanco item : tronarko.getItens()) {
                DKGObjeto objeto = DKG.PARSER_TO_OBJETO(item.lerTexto());

                if (objeto.identifique("Tronarko").isValor(7002)) {
                    System.out.println("Tronarko :: " + objeto.identifique("Tronarko").getValor() + "_" + objeto.identifique("Megarko").getValor() + " -->> " + objeto.toDocumento().length());
                }

            }
        }


        int pTronarko = 7002;
        int pMegarko = 1;


        int INDEXAMENTO_INICIO = 6900;
        int indice = ((pTronarko - INDEXAMENTO_INICIO) * 50) + (pMegarko - 1);

        Opcional<ItemDoBanco> t7002_1 = tronarko.getIndexado(indice);

        if (t7002_1.isOK()) {

            DKGObjeto objeto = DKG.PARSER_TO_OBJETO(t7002_1.get().lerTexto());

            System.out.println("Tronarko :: " + objeto.identifique("Tronarko").getValor() + "_" + objeto.identifique("Megarko").getValor() + " -->> " + objeto.toDocumento().length());
            System.out.println(objeto.toDocumento());

        }

        colecoes.fechar();


        System.out.println("------------ ASSET CREATOR -----------------");

        AssetCreator.criarCompressed("/home/luan/assets/turmas.acc", "/home/luan/Dropbox/CED_01/cache");


        System.out.println("------------ ASSET ABRIR -----------------");

        AssetContainer ac = new AssetContainer();
        ac.abrir("/home/luan/assets/turmas.acc");


        ac.listarTabelaDeArquivos();

        for (Arquivo arq : ac.getTabelaDeArquivos()) {
            System.out.println(arq.getNome() + " -->> " + arq.getInicio() + " : " + arq.getFim() + " :: " + arq.getTamanho());
        }

    }
}
