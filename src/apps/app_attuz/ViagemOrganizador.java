package apps.app_attuz;

import apps.app_attuz.Arkazz.Arkazz;
import apps.app_attuz.Ferramentas.Local;
import apps.app_attuz.Viagem.Viagem;
import apps.app_attuz.Viagem.Viajante;
import libs.luan.Lista;

import java.util.ArrayList;

public class ViagemOrganizador {

    public static void construir() {

        boolean criar_viagem = false;

        if (criar_viagem) {

            Viagem mViagem = new Viagem(100);
            Arkazz eArkazz = new Arkazz();

            Viajante EU = new Viajante();
            Lista<Local> cidades = eArkazz.getCidades();

            while (mViagem.getTempo() < 3000) {
                mViagem.viajar(EU, cidades);

                System.out.println(mViagem.getTempo() + " de 10000");
            }

            mViagem.salvar("/home/luan/Documentos/viagem_ovkom.txt");
            mViagem.marcarCidades("/home/luan/Documentos/viagem_ovkom.txt", cidades);

            mViagem.separar("/home/luan/Documentos/viagem_ovkom.txt", "7002");
            mViagem.separar("/home/luan/Documentos/viagem_ovkom.txt", "7003");
            mViagem.separar("/home/luan/Documentos/viagem_ovkom.txt", "7004");
            // mViagem.separar("/home/luan/Documentos/viagem_ovkom.txt", "7005");
            // mViagem.separar("/home/luan/Documentos/viagem_ovkom.txt", "7006");
            // mViagem.separar("/home/luan/Documentos/viagem_ovkom.txt", "7007");
            //  mViagem.separar("/home/luan/Documentos/viagem_ovkom.txt", "7008");
            // mViagem.separar("/home/luan/Documentos/viagem_ovkom.txt", "7009");


        }


        //  GuiaDeViagem.unir("/home/luan/Documentos/viagem_desorganizada.txt");
        //    GuiaDeViagem.organizar("/home/luan/Documentos/viagem_desorganizada.txt","/home/luan/Documentos/viagem_organizada.txt");
        //GuiaDeViagem.passei("/home/luan/Documentos/viagem_organizada.txt",libs.Tronarko.libs.Tronarko.getTozteDireto(), libs.Tronarko.libs.Tronarko.getHazdeDireto());

        //  BZZ.alocar("/home/luan/Documentos/viagem.bzz", 2000);
        //ViagemIndexar.indexar("/home/luan/Documentos/viagem_organizada.txt", "/home/luan/Documentos/viagem.bzz");

        //  String conteudo = BZZ.procurar("/home/luan/Documentos/viagem.bzz", 140);
        //  System.out.println(conteudo);
        // System.out.println("Tamanho :: " + conteudo.length());


        //  ViagemIndexar.passeiBZZ("/home/luan/Documentos/viagem.bzz", libs.Tronarko.libs.Tronarko.getTozte(), libs.Tronarko.libs.Tronarko.getHazde(), libs.Tronarko.libs.Tronarko.getTozte());

        //System.out.println("HOJE :: " + libs.Tronarko.libs.Tronarko.getTozteDireto().getTexto());

        //ViagemIndexar.passeiBZZ("/home/luan/Documentos/viagem.bzz", new libs.Tronarko.Tozte(1,4,7002), new libs.Tronarko.Hazde(7,0,0),libs.Tronarko.libs.Tronarko.getTozteDireto());

        // System.out.println("BZZ -->> Max " + BZZ.getQuantidadeMaxima("/home/luan/Documentos/viagem.bzz"));
        // System.out.println("BZZ -->> " + BZZ.comValores("/home/luan/Documentos/viagem.bzz"));

        // ViagemIndexar.obterIndexado("/home/luan/Documentos/viagem.bzz","36/05/7002");
        //  String vt =  ViagemIndexar.procurando("/home/luan/Documentos/viagem.bzz", new Viajante(),new libs.Tronarko.Tozte(36,6,7002),new libs.Tronarko.Hazde(4,0,0));

        //  String eArquivoBzz = "/home/luan/Documentos/viagem.bzz";
        //   BZZ.alocar(eArquivoBzz, 2000);

        // ViagemIndexar.indexar(eArquivoBzz, "/home/luan/Documentos/t7002.txt");
        // ViagemIndexar.indexar(eArquivo, "/home/luan/Documentos/t7003.txt");
        // ViagemIndexar.indexar(eArquivo, "/home/luan/Documentos/t7004.txt");

        // BZZ.procurar(eArquivo, 0);

        //  System.out.println(vt);

        // System.out.println("Com Valores :: " + BZZ.comValores("/home/luan/Documentos/viagem.bzz"));
        //   System.out.println("Sem Valores :: " + BZZ.semValores("/home/luan/Documentos/viagem.bzz"));


    }
}
