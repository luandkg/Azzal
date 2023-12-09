package apps.app_attuz.Arkazz;

import apps.app_attuz.Ferramentas.Local;
import apps.app_attuz.Regiao.Regiao;
import libs.azzal.utilitarios.Cor;
import libs.luan.Lista;

import java.util.ArrayList;

public class Arkazz {

    private ArrayList<Regiao> mRegioes;
    private Lista<Local> mCidades;
    private Lista<Local> mOceanos;

    public Arkazz() {


        mRegioes = new ArrayList<Regiao>();
        mCidades = new Lista<Local>();
        mOceanos = new Lista<Local>();

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

    public Lista<Local> getCidades() {
        return mCidades;
    }

    public Lista<Local> getOceanos() {
        return mOceanos;
    }

    public void carregar(Lista<Local> mLocais, Lista<Local> mMares) {

        mLocais.adicionar(new Local("Vilarejo de Czattos", 1080, 221));
        mLocais.adicionar(new Local("Pedreira de Okkos", 1351, 142));
        mLocais.adicionar(new Local("Ruinas Imperiais", 767, 406));
        mLocais.adicionar(new Local("Portal do Sol", 650, 279));
        mLocais.adicionar(new Local("Templo do Fogo", 656, 494));
        mLocais.adicionar(new Local("Moinho de Tzar", 1262, 659));
        mLocais.adicionar(new Local("Desfiladeiro de Orkor", 1318, 397));
        mLocais.adicionar(new Local("Pirâmide do Deserto de Ett", 1035, 418));
        mLocais.adicionar(new Local("Encruzilhada de Moac", 1067, 649));
        mLocais.adicionar(new Local("Vilarejo de Escavação Arbz", 1206, 740));
        mLocais.adicionar(new Local("Pirâmide do Deserto de Onn", 1186, 377));
        mLocais.adicionar(new Local("Vulcão de Hilje", 956, 251));
        mLocais.adicionar(new Local("Vilarejo de Accas", 933, 119));
        mLocais.adicionar(new Local("Deserto de Halcabraz", 791, 210));
        mLocais.adicionar(new Local("Pirâmide do Deserto de Aaz", 909, 386));
        mLocais.adicionar(new Local("Vilarejo de Ikkas", 703, 320));
        mLocais.adicionar(new Local("Poncor", 737, 565));
        mLocais.adicionar(new Local("Templo de Luxor", 1008, 494));
        mLocais.adicionar(new Local("Reserva de Dunnatzo", 1066, 602));
        mLocais.adicionar(new Local("Escavação de Auxi", 1322, 156));
        mLocais.adicionar(new Local("Vilarejo de Pratzo", 1134, 132));
        mLocais.adicionar(new Local("Vilarejo de Noff", 967, 145));
        mLocais.adicionar(new Local("Vilarejo do Amanhecer", 1445, 66));
        mLocais.adicionar(new Local("Muralha de Gelo", 1409, 30));
        mLocais.adicionar(new Local("Castelo das Águas", 1336, 34));
        mLocais.adicionar(new Local("Templo do Ar do Leste", 1466, 399));
        mLocais.adicionar(new Local("Cidade do Amanhecer", 1485, 430));
        mLocais.adicionar(new Local("Desfilafeiro de Horgovant", 1345, 511));
        mLocais.adicionar(new Local("Masmorras de Sanonta", 1276, 603));
        mLocais.adicionar(new Local("Pantano de Forn", 1396, 704));
        mLocais.adicionar(new Local("Ilha de Connan", 1303, 783));
        mLocais.adicionar(new Local("Ilha de Vocz", 1338, 780));
        mLocais.adicionar(new Local("Cidade de Ouro", 1179, 726));
        mLocais.adicionar(new Local("Cavernas de Ukkavno", 1068, 718));
        mLocais.adicionar(new Local("Montanhas de Allan", 1189, 455));
        mLocais.adicionar(new Local("Vilarejo de Alcabraz", 849, 221));
        mLocais.adicionar(new Local("Minas de Ferro de Forgê", 766, 611));
        mLocais.adicionar(new Local("Ruela de Penhores", 946, 630));
        mLocais.adicionar(new Local("Grande Mercatto", 938, 643));
        mLocais.adicionar(new Local("Trilhos de Ezzon", 934, 620));
        mLocais.adicionar(new Local("Labirinto do Touro", 958, 621));
        mLocais.adicionar(new Local("Cemiterio de Bojê", 955, 638));
        mLocais.adicionar(new Local("Fontes termais de Flurras", 956, 592));
        mLocais.adicionar(new Local("Ilha da Reserva", 921, 575));
        mLocais.adicionar(new Local("Ilha de Aan", 909, 602));
        mLocais.adicionar(new Local("Pedreira de Alcabraz", 805, 298));
        mLocais.adicionar(new Local("Mortum", 883, 507));
        mLocais.adicionar(new Local("Ollone Lugar Nenhum", 1263, 244));
        mLocais.adicionar(new Local("Vilarejo de Inhero", 1167, 189));
        mLocais.adicionar(new Local("Metrópolis de Cal", 1427, 260));
        mLocais.adicionar(new Local("Castelo do Duque Valgrá", 1430, 137));

        mLocais.adicionar(new Local("Sallinaz", 566, 181));
        mLocais.adicionar(new Local("Gaoz", 529, 222));
        mLocais.adicionar(new Local("Cidadela de Mercho", 562, 254));
        mLocais.adicionar(new Local("Ponta de Ardente", 486, 393));

        mLocais.adicionar(new Local("Templo do Ar do Norte", 943, 32));
        mLocais.adicionar(new Local("Templo do Ar do Sul", 1223, 795));
        mLocais.adicionar(new Local("Templo do Ar do Oeste", 549, 514));

        mLocais.adicionar(new Local("Vilarejo da Folha", 1490, 244));
        mLocais.adicionar(new Local("Vilarejo da Sombra da Folha", 1502, 262));
        mLocais.adicionar(new Local("Escadarias da Folha", 1469, 278));


        mLocais.adicionar(new Local("Ruinas de Ezzavont", 1515, 470));


        // MARES
        mMares.adicionar(new Local("Mar Otacam", 550, 130));
        mMares.adicionar(new Local("Mar Vermelho", 853, 709));
        mMares.adicionar(new Local("Mar Indigo", 1430, 574));
        mMares.adicionar(new Local("Mar Atzo", 1157, 18));
        mMares.adicionar(new Local("Mar Ekroz", 1491, 200));
        mMares.adicionar(new Local("Mar Hallen Daz", 2314 / 2, 1172 / 2));

    }

}
