package apps.AppAttuz.Politicamente;


import apps.AppAttuz.Ferramentas.Espaco2D;
import apps.AppAttuz.Mapa.Caminho;
import apps.AppAttuz.Mapa.Local;
import azzal.Formatos.Ponto;
import libs.dkg.DKG;

import java.util.ArrayList;

import libs.dkg.DKGObjeto;

public class Cidades {


    public static void marcar(ArrayList<Local> mLocais, ArrayList<Local> mMares) {

        mLocais.add(new Local("Vilarejo de Czattos", 1080, 221));
        mLocais.add(new Local("Pedreira de Okkos", 1351, 142));
        mLocais.add(new Local("Ruinas Imperiais", 767, 406));
        mLocais.add(new Local("Portal do Sol", 650, 279));
        mLocais.add(new Local("Templo do Fogo", 656, 494));
        mLocais.add(new Local("Moinho de Tzar", 1262, 659));
        mLocais.add(new Local("Desfiladeiro de Orkor", 1318, 397));
        mLocais.add(new Local("Pirâmide do Deserto de Ett", 1035, 418));
        mLocais.add(new Local("Encruzilhada de Moac", 1067, 649));
        mLocais.add(new Local("Vilarejo de Escavação Arbz", 1206, 740));
        mLocais.add(new Local("Pirâmide do Deserto de Onn", 1186, 377));
        mLocais.add(new Local("Vulcão de Hilje", 956, 251));
        mLocais.add(new Local("Vilarejo de Accas", 933, 119));
        mLocais.add(new Local("Deserto de Halcabraz", 791, 210));
        mLocais.add(new Local("Pirâmide do Deserto de Aaz", 909, 386));
        mLocais.add(new Local("Vilarejo de Ikkas", 703, 320));
        mLocais.add(new Local("Poncor", 737, 565));
        mLocais.add(new Local("Templo de Luxor", 1008, 494));
        mLocais.add(new Local("Reserva de Dunnatzo", 1066, 602));
        mLocais.add(new Local("Escavação de Auxi", 1322, 156));
        mLocais.add(new Local("Vilarejo de Pratzo", 1134, 132));
        mLocais.add(new Local("Vilarejo de Noff", 967, 145));
        mLocais.add(new Local("Vilarejo do Amanhecer", 1445, 66));
        mLocais.add(new Local("Muralha de Gelo", 1409, 30));
        mLocais.add(new Local("Castelo das Águas", 1336, 34));
        mLocais.add(new Local("Templo do Ar do Leste", 1466, 399));
        mLocais.add(new Local("Cidade do Amanhecer", 1485, 430));
        mLocais.add(new Local("Desfilafeiro de Horgovant", 1345, 511));
        mLocais.add(new Local("Masmorras de Sanonta", 1276, 603));
        mLocais.add(new Local("Pantano de Forn", 1396, 704));
        mLocais.add(new Local("Ilha de Connan", 1303, 783));
        mLocais.add(new Local("Ilha de Vocz", 1338, 780));
        mLocais.add(new Local("Cidade de Ouro", 1179, 726));
        mLocais.add(new Local("Cavernas de Ukkavno", 1068, 718));
        mLocais.add(new Local("Montanhas de Allan", 1189, 455));
        mLocais.add(new Local("Vilarejo de Alcabraz", 849, 221));
        mLocais.add(new Local("Minas de Ferro de Forgê", 766, 611));
        mLocais.add(new Local("Ruela de Penhores", 946, 630));
        mLocais.add(new Local("Grande Mercatto", 938, 643));
        mLocais.add(new Local("Trilhos de Ezzon", 934, 620));
        mLocais.add(new Local("Labirinto do Touro", 958, 621));
        mLocais.add(new Local("Cemiterio de Bojê", 955, 638));
        mLocais.add(new Local("Fontes termais de Flurras", 956, 592));
        mLocais.add(new Local("Ilha da Reserva", 921, 575));
        mLocais.add(new Local("Ilha de Aan", 909, 602));
        mLocais.add(new Local("Pedreira de Alcabraz", 805, 298));
        mLocais.add(new Local("Mortum", 883, 507));
        mLocais.add(new Local("Ollone Lugar Nenhum", 1263, 244));
        mLocais.add(new Local("Vilarejo de Inhero", 1167, 189));
        mLocais.add(new Local("Metrópolis de Cal", 1427, 260));
        mLocais.add(new Local("Castelo do Duque Valgrá", 1430, 137));

        mLocais.add(new Local("Sallinaz", 566, 181));
        mLocais.add(new Local("Gaoz", 529, 222));
        mLocais.add(new Local("Cidadela de Mercho", 562, 254));
        mLocais.add(new Local("Ponta de Ardente", 486, 393));

        mLocais.add(new Local("Templo do Ar do Norte", 943, 32));
        mLocais.add(new Local("Templo do Ar do Sul", 1223, 795));
        mLocais.add(new Local("Templo do Ar do Oeste", 549, 514));

        mLocais.add(new Local("Vilarejo da Folha", 1490, 244));
        mLocais.add(new Local("Vilarejo da Sombra da Folha", 1502, 262));
        mLocais.add(new Local("Escadarias da Folha", 1469, 278));


        mLocais.add(new Local("-------- DESCONHECIDO -----------", 1515, 470));



        // MARES
        mMares.add(new Local("Mar Otacam", 550, 130));
        mMares.add(new Local("Mar Vermelho", 853, 709));
        mMares.add(new Local("Mar Indigo", 1430, 574));
        mMares.add(new Local("Mar Atzo", 1157, 18));
        mMares.add(new Local("Mar Ekroz", 1491, 200));

    }

    public static void salvar(ArrayList<Local> mLocais, String eArquivo) {

        DKG arquivo = new DKG();
        DKGObjeto eLocais = arquivo.unicoObjeto("Locais");

        for (Local eLocal : mLocais) {
            DKGObjeto objeto_local = eLocais.criarObjeto("Local");

            objeto_local.identifique("Nome", eLocal.getNome());
            objeto_local.identifique("X", eLocal.getX());
            objeto_local.identifique("Y", eLocal.getY());

        }

        arquivo.salvar(eArquivo);
    }

    public static void ligar(ArrayList<Local> mLocais, ArrayList<Caminho> mCaminhos) {

        // ligarCidades(mLocais, mCaminhos, 0, 48);
        //  ligarCidades(mLocais, mCaminhos, 0, 20);
        // ligarCidades(mLocais, mCaminhos, 0, 21);
        //  ligarCidades(mLocais, mCaminhos, 0, 11);
        // ligarCidades(mLocais, mCaminhos, 11, 21);
        //  ligarCidades(mLocais, mCaminhos, 0, 10);
        //  ligarCidades(mLocais, mCaminhos, 10, 7);

    }

    public static void ligarCidades(ArrayList<Local> mLocais, ArrayList<Caminho> mCaminhos, int i, int f) {

        Local li = getLocal(mLocais, i);
        Local lf = getLocal(mLocais, f);

        mCaminhos.add(new Caminho(new Ponto(li.getX(), li.getY()), new Ponto(lf.getX(), lf.getY())));

    }


    public static Local getLocal(ArrayList<Local> mLocais, int i) {

        int p = 0;
        Local ret = null;

        for (Local ePonto : mLocais) {
            if (p == i) {
                ret = ePonto;
                break;
            }
            p += 1;
        }

        return ret;
    }


    public static void zerar(ArrayList<Local> mLocais) {
        int i = 0;

        for (Local ePonto : mLocais) {
            if (ePonto.getNome().length() < 3) {
                ePonto.setNome(String.valueOf(i));
            }
            System.out.println("mLocais.add(new Local(\"" + ePonto.getNome() + "\"," + ePonto.getX() + "," + ePonto.getY() + "));");
            i += 1;
        }

    }

    public static String getNomeDe(ArrayList<Local> mLocais, int px, int py) {
        String ret = "";
        for (Local ePonto : mLocais) {
            if (ePonto.getX() == px && ePonto.getY() == py) {
                ret = ePonto.getNome();
                break;
            }
        }

        return ret;
    }


    public static String getNomeMaisProximo(ArrayList<Local> mLocais, int px, int py, int ate) {

        String ret = "";
        int menor = ate;

        for (Local ePonto : mLocais) {
            int dist = Espaco2D.distancia_entre_pontos(px, py, ePonto.getX() * 2, ePonto.getY() * 2);

            if (dist < menor) {
                menor = dist;
                ret = ePonto.getNome();
            }


        }

        return ret;
    }


    public static ArrayList<Local> getCidades() {

        ArrayList<Local> cidades_locais = new ArrayList<Local>();

        Cidades.marcar(cidades_locais, new ArrayList<Local>());

        return cidades_locais;
    }

    public static Local getLocalizacao(ArrayList<Local> cidades,String eNome){

        for(Local l : cidades){
            if (l.getNome().contentEquals(eNome)){
                return l;
            }
        }

        return null;
    }
}
