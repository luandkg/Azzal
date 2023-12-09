package apps.app_coisas;

import libs.arquivos.BZ3;
import libs.arquivos.Sumario;
import libs.dkg.DKGObjeto;
import libs.dkg.DKGUtils;
import libs.luan.RefInt;
import libs.ranking.RankeadoInteiro;
import libs.ranking.Rankeador;

import java.util.ArrayList;

public class AppClassificador {

    public static void init(){

        String arquivo_bz3 = "/home/luan/assets/letras.bz3";

        Sumario eSumario = new Sumario("/home/luan/assets/letras.bz3");


        ArrayList<DKGObjeto> alunos = new ArrayList<DKGObjeto>();

        alunos.add(DKGUtils.criar("Aluno", "Nome", "Alice"));
        alunos.add(DKGUtils.criar("Aluno", "Nome", "Carla"));
        alunos.add(DKGUtils.criar("Aluno", "Nome", "Bruna"));
        alunos.add(DKGUtils.criar("Aluno", "Nome", "Amanda"));
        alunos.add(DKGUtils.criar("Aluno", "Nome", "Beatriz"));
        alunos.add(DKGUtils.criar("Aluno", "Nome", "Ana"));

        eSumario.organizar_completo(alunos, "Nome");

        System.out.println("A :: " + BZ3.procurar(arquivo_bz3, 0).length());
        System.out.println("B :: " + BZ3.procurar(arquivo_bz3, 1).length());
        System.out.println("C :: " + BZ3.procurar(arquivo_bz3, 2).length());

        for (int l = 0; l < 5; l++) {

            String conjunto = BZ3.procurar(arquivo_bz3, l);

            System.out.println("------------- " + l + " ---------------------");
            System.out.println(conjunto);

        }


        RankeadoInteiro<RefInt> r1 = new RankeadoInteiro<RefInt>("A", 12, null);
        RankeadoInteiro<RefInt> r2 = new RankeadoInteiro<RefInt>("B", 25, null);
        RankeadoInteiro<RefInt> r3 = new RankeadoInteiro<RefInt>("C", 5, null);

        ArrayList<RankeadoInteiro> lista = new ArrayList<RankeadoInteiro>();
        lista.add(r1);
        lista.add(r2);
        lista.add(r3);

        Rankeador.ordenar(lista);

        for(RankeadoInteiro item : lista){
            System.out.println("Item - " + item.getRanking() + " -->> " + item.getNome());
        }

    }
}
