package Tronarko;

import AssetContainer.AssetContainer;
import AssetContainer.AssetCreator;
import AssetContainer.Arquivo;
import Tronarko.Harrempluz.Colecao;

import LuanDKG.LuanDKG;
import LuanDKG.Pacote;
import AssetContainer.Chronos_Intervalo;

import java.util.ArrayList;

public class HarrempluzCreator {

    private ArrayList<Colecao> mColecoes;

    public HarrempluzCreator() {

        mColecoes = new ArrayList<Colecao>();

        mColecoes.add(new Colecao("COR", "AMARELO VERDE VERMELHO AZUL CINZA PRETO ROSA ROXO BRANCO MARRON LARANJA "));
        mColecoes.add(new Colecao("ELEMENTO", "AGUA TERRA VENTO FOGO "));

        mColecoes.add(new Colecao("METAL", "LATaO COBRE ESTANHO ZINCO AaO FERRO BRONZE PRATA OURO "));
        mColecoes.add(new Colecao("QUALIDADE",
                "adoravel afavel afetivo agradavel ajuizado alegre altruasta amavel amigavel amoroso aplicado assertivo atencioso atento autantico aventureiro bacana benavolo bondoso brioso calmo carinhoso carismatico caritativo cavalheiro cavico civilizado companheiro compreensivo comunicativo confiante confiavel consciencioso corajoso cordial cortas credavel criativo criterioso cuidadoso curioso decente decoroso dedicado descontraado desenvolto determinado digno diligente disciplinado disponavel divertido doce educado eficiente eloquente empatico empenhado empreendedor encantador engraaado entusiasta escrupuloso esforaado esmerado esperanaoso esplandido excelente extraordinario extrovertido feliz fiel fofo forte franco generoso gentil genuano habilidoso honesto honrado honroso humanitario humilde idaneo imparcial independente inovador antegro inteligente inventivo justo leal legal livre maduro maravilhoso meigo modesto natural nobre observador organizado otimista ousado pacato paciente perfeccionista perseverante persistente perspicaz ponderado pontual preocupado preparado prestativo prestavel proativo produtivo prudente racional respeitador responsavel sabio sagaz sensato sensavel simpatico sincero solacito solidario sossegado ternurento tolerante tranquilo transparente valente valoroso verdadeiro zeloso "));
        mColecoes.add(new Colecao("DEFEITO",
                "agressivo ansioso antipatico antissocial apatico apressado arrogante atrevido autoritario avarento birrento bisbilhoteiro bruto calculista casmurro chato canico ciumento colarico comodista covarde cratico cruel debochado depressivo desafiador desbocado descarado descomedido desconfiado descortas desequilibrado desleal desleixado desmazelado desmotivado desobediente desonesto desordeiro despatico desumano discriminador dissimulado distraado egoasta estourado estressado exigente falso fingido fraco frio fravolo fatil ganancioso grosseiro grosso hipacrita ignorante impaciente impertinente impetuoso impiedoso imponderado impostor imprudente impulsivo incompetente inconstante inconveniente incorreto indeciso indecoroso indelicado indiferente infiel inflexavel injusto inseguro insensato insincero instavel insuportavel interesseiro intolerante intransigente irracional irascavel irrequieto irresponsavel irritadiao malandro maldoso malicioso malvado mandão manhoso maquiavalico medroso mentiroso mesquinho narcisista negligente nervoso neuratico obcecado odioso oportunista orgulhoso pedante pessimista pa-frio possessivo precipitado preconceituoso preguiaoso prepotente presunaoso problematico quezilento rancoroso relapso rigoroso rabugento rude sarcastico sedentario teimoso tamido tirano traiaoeiro traidor trapaceiro tendencioso trocista vagabundo vaidoso vulneravel vigarista xenafobo "));
        mColecoes.add(new Colecao("NUMERO_10", "0 1 2 3 4 5 6 7 8 9 "));
        mColecoes.add(new Colecao("NUMERO_100",
                "0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59 60 61 62 63 64 65 66 67 68 69 70 71 72 73 74 75 76 77 78 79 80 81 82 83 84 85 86 87 88 89 90 91 92 93 94 95 96 97 98 99 100 "));
        mColecoes.add(new Colecao("VOGAL", "A E I O U "));
        mColecoes.add(new Colecao("CONSOANTE", "B C D F G H J K L M N P Q R S T V W X Y Z "));
        mColecoes.add(new Colecao("LETRA", "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z "));
        mColecoes.add(new Colecao("CRISTAL",
                "Alexandrita Ametista Aquamarine Citrino Diamante Esmeralda Granada Jade lazali Opala Turmalina Parolas Peridoto Rubi Safira Espinela Tanzanite Topazio Turmalina Turquesa Zircania Morganite agata Amazonita ambar Cornalina Fluorita Hematita Jaspe Malaquita Nacar Sodalita "));

        mColecoes.add(new Colecao("DIRECAO", "NORTE SUL LESTE OESTE NORDESTE SUDESTE NOROESTE SUDOESTE "));


    }

    public String getGrupo(int t) {

        int g = 0;

        while (t > 999) {
            t -= 1000;
            g += 1;
        }

        return "GT" + g;
    }

    public String getSubGrupo(int t) {
        String ret = "";

        int g = 0;
        int e = 0;

        while (t > 999) {
            t -= 1000;
            g += 1;
        }

        while (t > 100) {
            t -= 100;
            e += 1;
        }

        return gerarTT(e);
    }

    public String getNome(int t) {
        return "Tronarko_" + gerarTronarko(t) + ".harrempluz";
    }


    public void criar(String eLocal) {


        ArrayList<String> mPastasFalsas = new ArrayList<String>();
        mPastasFalsas.add("Harrempluz");


        ArrayList<String> mArquivosFalsos = new ArrayList<String>();


        AssetCreator.criarCompressedMontado(eLocal, mArquivosFalsos, mPastasFalsas);

        // 0 ate 10.000
        // Dividido Grupo de 1000 -> 10
        // Cada grupo divido por 100 -> 1000 / 100 -> 10

        mArquivosFalsos.clear();
        mPastasFalsas.clear();

        for (int i = 0; i < 10; i++) {

            String eGrupo = "GT" + i;
            mPastasFalsas.add(eGrupo);

            System.out.println(" --->> GRUPO " + eGrupo);

        }


        AssetCreator.adicionarCompressedMontado(eLocal, "Harrempluz", mArquivosFalsos, mPastasFalsas);

        for (int i = 0; i < 10; i++) {
            String eGrupo = "GT" + i;
            mArquivosFalsos.clear();
            mPastasFalsas.clear();


            for (int a = 0; a < 100; a++) {

                String eSubGrupo = gerarTT(a);

                mPastasFalsas.add(eSubGrupo);

                System.out.println(" --->> SUBGRUPO " + eGrupo + " :: " + eSubGrupo);
            }

            AssetCreator.adicionarCompressedMontado(eLocal, "Harrempluz/" + eGrupo, mArquivosFalsos, mPastasFalsas);


        }

        int eTronarko = 0;

        for (int i = 0; i < 10; i++) {


            String eGrupo = "GT" + i;


            for (int a = 0; a < 100; a++) {

                mArquivosFalsos.clear();
                mPastasFalsas.clear();

                String eSubGrupo = gerarTT(a);

                for (int e = 0; e < 10; e++) {


                    String eTronarkoString = "Tronarko_" + gerarTronarko(eTronarko) + ".harrempluz";

                    mArquivosFalsos.add(eTronarkoString);

                    System.out.println(eTronarko + " --->> ARQUIVANDO " + eGrupo + " - " + eSubGrupo + " :: " + eTronarkoString);

                    eTronarko += 1;
                }

                AssetCreator.adicionarCompressedMontado(eLocal, "Harrempluz/" + eGrupo + "/" + eSubGrupo, mArquivosFalsos, mPastasFalsas);


            }


        }


    }

    public String gerarTT(int t) {
        String e = String.valueOf(t);
        if (t < 10) {
            e = "0" + e;
        }
        return "TT" + e;
    }

    public String gerarTronarko(int t) {
        String e = String.valueOf(t);

        if (t < 10) {
            e = "000" + e;
        } else if (t >= 10 && t < 100) {
            e = "00" + e;
        } else if (t >= 100 && t < 1000) {
            e = "0" + e;
        }

        return e;
    }


    public boolean colocarConteudo(String eLocalArquivo) {

        AssetContainer mAC = new AssetContainer();
        mAC.abrir(eLocalArquivo);

        int eLimite = 50;

        int mQuadro = 0;
        int mi = 0;

        int paginando = 0;
        int pi = 0;

        ArrayList<Arquivo> mColocar = new ArrayList<Arquivo>();

        for (Arquivo eArquivo : mAC.getTabelaDeArquivos()) {
            if (eArquivo.getTamanho() == 0) {
                mColocar.add(eArquivo);
            }

            if (mColocar.size() >= eLimite) {
                if (mi >= mQuadro) {
                    break;
                } else {
                    mColocar.clear();
                }
                mi += 1;
            }

            if (pi >= eLimite) {
                paginando += 1;
                pi = 0;
            }

            pi += 1;

        }


        System.out.println(" -------------- ESCREVER NESSES : PAGINA " + paginando + " --------------- ");


        if (mColocar.size() > 0) {


            Chronos_Intervalo mTempoConteudo = new Chronos_Intervalo();
            mTempoConteudo.marqueInicio();

            for (Arquivo eArquivo : mColocar) {

                System.out.println(" ESCREVER -->> " + " [ " + longMapeado(eArquivo.getInicio(), 7) + " " + longMapeado(eArquivo.getFim(), 7) + " :: " + longMapeado(eArquivo.getFim() - eArquivo.getInicio(), 7) + " ] :: " + eArquivo.getNomeCompleto());

                //   AssetCreator.anexarConteudoCompressed(eLocalArquivo, eArquivo, criarConteudo());

            }

            mTempoConteudo.marqueFim();

            System.out.println("Tempo Escritura Pagina : " + mTempoConteudo.getIntervalo());

        }

        return mColocar.size() > 0;
    }

    public boolean temVazio(String eLocalArquivo) {

        AssetContainer mAC = new AssetContainer();
        mAC.abrir(eLocalArquivo);

        int eLimite = 50;


        ArrayList<Arquivo> mColocar = new ArrayList<Arquivo>();

        for (Arquivo eArquivo : mAC.getTabelaDeArquivos()) {
            if (eArquivo.getTamanho() == 0) {
                mColocar.add(eArquivo);
            }

            if (mColocar.size() >= eLimite) {
                break;

            }

        }

        return mColocar.size() > 0;
    }

    public String longMapeado(long e, int casas) {

        String ret = String.valueOf(e);
        while (ret.length() < casas) {
            ret = "0" + ret;
        }

        return ret;
    }

    public String criarConteudo(int eTronarko) {

        LuanDKG eDocumento = new LuanDKG();


        Pacote eHarrempluz = eDocumento.CriarPacote("Harrempluz");
        eHarrempluz.Identifique("Tronarko", eTronarko);

        for (int h = 1; h <= 10; h++) {

            Pacote eHiperarko = eHarrempluz.CriarPacote("Hiperarko");
            eHiperarko.Identifique("HiperarkoID", h);

            for (int m = 1; m <= 5; m++) {

                Pacote eMegarko = eHiperarko.CriarPacote("Megarko");
                eMegarko.Identifique("MegarkoID", m);

                for (int s = 1; s <= 10; s++) {

                    Pacote eSigno = eMegarko.CriarPacote("Signo");
                    eSigno.Identifique("SignoID", s);


                    for (Colecao eColecao : mColecoes) {

                        eSigno.Identifique(eColecao.getNome(), eColecao.SorteieQualquer());

                    }


                }


            }

        }


        return eDocumento.toString();

    }


    public boolean colocarConteudo(String eLocalArquivo, int ePagina, ArrayList<Arquivo> mColocar) {

        AssetContainer mAC = new AssetContainer();
        mAC.abrir(eLocalArquivo);


        System.out.println(" -------------- ESCREVER : PAGINA " + ePagina + " --------------- ");

        AssetCreator mAssetCreator = new AssetCreator();

        if (mColocar.size() > 0) {


            Chronos_Intervalo mTempoConteudo = new Chronos_Intervalo();
            mTempoConteudo.marqueInicio();

            for (Arquivo eArquivo : mColocar) {

                System.out.println(" ESCREVER -->> "  + eArquivo.getNomeCompleto());

                AssetCreator.anexarConteudoCompressed(eLocalArquivo, eArquivo, criarConteudo(0));

            }

            mTempoConteudo.marqueFim();

            System.out.println("Tempo Escritura Pagina : " + mTempoConteudo.getIntervalo());

        }

        return mColocar.size() > 0;
    }


    public  ArrayList<Arquivo> harremplar(String eLocalArquivo) {

     AssetContainer mAC = new AssetContainer();
        mAC.abrir(eLocalArquivo);



        return mAC.getTabelaDeArquivos();
    }

}
