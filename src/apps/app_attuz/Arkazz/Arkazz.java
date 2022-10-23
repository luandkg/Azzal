package apps.app_attuz.Arkazz;

import apps.app_attuz.Ferramentas.Local;
import apps.app_attuz.Regiao.Regiao;
import azzal.utilitarios.Cor;

import java.util.ArrayList;

public class Arkazz {

    private ArrayList<Regiao> mRegioes;
    private ArrayList<Local> mCidades;
    private ArrayList<Local> mOceanos;

    public Arkazz() {


        mRegioes = new ArrayList<Regiao>();
        mCidades = new ArrayList<Local>();
        mOceanos = new ArrayList<Local>();

        mRegioes.add(new Regiao("Alkammus", new Cor(240, 246, 164)));
        mRegioes.add(new Regiao("Ongaz", new Cor(84, 156, 231)));
        mRegioes.add(new Regiao("Umbus", new Cor(10, 97, 96)));
        mRegioes.add(new Regiao("Gonnaz", new Cor(44, 3, 235)));
        mRegioes.add(new Regiao("Bacco", new Cor(246, 182, 64)));
        mRegioes.add(new Regiao("Ezko", new Cor(221, 34, 34)));
        mRegioes.add(new Regiao("Flum", new Cor(210, 80, 21)));
        mRegioes.add(new Regiao("Ozz", new Cor(148, 64, 31)));
        mRegioes.add(new Regiao("Allum", new Cor(49, 181, 103)));
        mRegioes.add(new Regiao("Skor", new Cor(180, 99, 189)));
        mRegioes.add(new Regiao("Immal", new Cor(49, 34, 103)));

        carregar(mCidades, mOceanos);

    }

    public ArrayList<Regiao> getRegioes() {
        return mRegioes;
    }

    public Regiao getRegiaoDaCor(Cor eCor) {
        Regiao eRegiao = null;

        for (Regiao r : mRegioes) {
            if (r.getCor().igual(eCor)) {
                eRegiao = r;
                break;
            }
        }

        return eRegiao;
    }

    public String getRegiaoNomeDaCor(Cor eCor) {
        String eRegiao = "";

        for (Regiao r : mRegioes) {
            if (r.getCor().igual(eCor)) {
                eRegiao = r.getNome();
                break;
            }
        }

        return eRegiao;
    }

    public ArrayList<Local> getCidades() {
        return mCidades;
    }

    public ArrayList<Local> getOceanos() {
        return mOceanos;
    }

    public void carregar(ArrayList<Local> mLocais, ArrayList<Local> mMares) {

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


        mLocais.add(new Local("Ruinas de Ezzavont", 1515, 470));


        // MARES
        mMares.add(new Local("Mar Otacam", 550, 130));
        mMares.add(new Local("Mar Vermelho", 853, 709));
        mMares.add(new Local("Mar Indigo", 1430, 574));
        mMares.add(new Local("Mar Atzo", 1157, 18));
        mMares.add(new Local("Mar Ekroz", 1491, 200));
        mMares.add(new Local("Mar Hallen Daz", 2314 / 2, 1172 / 2));

    }

}
