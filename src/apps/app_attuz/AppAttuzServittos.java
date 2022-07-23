package apps.app_attuz;

public class AppAttuzServittos {

    public static void init() {


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

        String LOCAL = "/home/luan/Imagens/Simples/";
        LOCAL = "/home/luan/Imagens/Arkazz/";

        boolean criar = false;
        boolean renderizar = false;


        if (criar) {
            WorldBuilding.criar(LOCAL);
        }

        if (renderizar) {
            WorldBuilding.renderQTT(LOCAL);
        }

        //WorldBuilding.biomas(LOCAL);

        //RenderQTT.render(eLocal + "dados/relevo.qtt",eLocal + "dados/relevo.png");

        //ViagemCompleta.motrarCidades();
        //ViagemCompleta.remontar_GuiaDeViagem();
    }
}
