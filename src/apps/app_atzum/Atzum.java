package apps.app_atzum;


import apps.app_atzum.utils.AtzumPontos;
import apps.app_atzum.utils.MassaDeAr;
import libs.arquivos.binario.Inteiro;
import libs.azzal.geometria.Ponto;
import libs.azzal.utilitarios.Cor;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Extremos;
import libs.luan.Lista;
import libs.luan.Strings;
import libs.luan.Vetor;
import libs.meta_functional.Funcao;

public class Atzum {

    private static String LOCAL = "/home/luan/Imagens/atzum/";


    private Vetor<String> mRegioes;
    private Vetor<String> mOceanos;


    private static final Cor mMassaDeArFria = Cor.getHexCor("#4FC3F7");
    private static final Cor mMassaDeArQuente = Cor.getHexCor("#FFEB3B");
    private static final Cor mSuperMassaDeArFria = Cor.getHexCor("#283593");
    private static final Cor mSuperMassaDeArQuente = Cor.getHexCor("#D84315");
    private static final Cor mMassaDeArTempestade = Cor.getHexCor("#8BC34A");
    private static final Cor mMassaDeArHiperTempestade = Cor.getHexCor("#1B5E20");


    public static final Cor COR_CHUVA = Cor.getHexCor("#4FC3F7");
    public static final Cor COR_NEVE = Cor.getHexCor("#CFD8DC");
    public static final Cor COR_TEMPESTADE_CHUVA = Cor.getHexCor("#1A237E");
    public static final Cor COR_TEMPESTADE_NEVE = Cor.getHexCor("#607D8B");

    public static final Cor COR_SECA = Cor.getHexCor("#FFEE58");
    public static final Cor COR_SECA_EXTREMA = Cor.getHexCor("#F4511E");
    public static final Cor COR_TEMPESTADE_VENTO = Cor.getHexCor("#BF360C");


    public static final Cor COR_VENTANIA = Cor.getHexCor("#D81B60");
    public static final Cor COR_ONDA_DE_CALOR = Cor.getHexCor("#FFFFFF");


    public Atzum() {

        mOceanos = new Vetor<String>(6);
        mOceanos.set(0, "");
        mOceanos.set(1, "Eppem");
        mOceanos.set(2, "Ikkoz");
        mOceanos.set(3, "Volgromno");
        mOceanos.set(4, "Uz");
        mOceanos.set(5, "Allamnos");

        mRegioes = new Vetor<String>(11);
        mRegioes.set(0, "");
        mRegioes.set(1, "Mazzo");
        mRegioes.set(2, "Ewkon");
        mRegioes.set(3, "Izmim");
        mRegioes.set(4, "Laccos");
        mRegioes.set(5, "Thargum");
        mRegioes.set(6, "Ox");
        mRegioes.set(7, "Urrem");
        mRegioes.set(8, "Plaos");
        mRegioes.set(9, "Oomgue");
        mRegioes.set(10, "Mecron");

    }

    public static String GET_LOCAL(){return LOCAL;}

    public String GET_REGIAO_NOME(int i) {
        return mRegioes.get(i);
    }

    public String GET_OCEANO(int valor) {
        return mOceanos.get(valor);
    }


    public static Lista<Ponto> GET_CIDADES() {
        return AtzumPontos.ABRIR( LOCAL + "parametros/CIDADES.dkg");
    }

    public static Lista<Entidade> GET_SENSORES() {

        Lista<Entidade> e_sensores = ENTT.ABRIR(LOCAL + "parametros/SENSORES.entts");
        ENTT.SEQUENCIAR(e_sensores, "SensorID", 0);

        return e_sensores;
    }

    public static Lista<Entidade> GET_CIDADES_NOMES() {

        Lista<Entidade> e_cidades = ENTT.ABRIR(LOCAL + "parametros/CIDADES_NOMES.entts");
        ENTT.AT_ALTERAR_NOME(e_cidades, "ID","CidadeID");

        return e_cidades;
    }


    public Vetor<Extremos<Integer>> GET_ZONAS_DE_TEMPERATURAS() {

        Vetor<Extremos<Integer>> zonas_de_temperatura = new Vetor<Extremos<Integer>>(12, new Funcao<Extremos<Integer>>() {
            @Override
            public Extremos<Integer> fazer() {
                return new Extremos<Integer>(Inteiro.GET_ORDENAVEL());
            }
        });

        zonas_de_temperatura.get(0).set(-40, 15);
        zonas_de_temperatura.get(5).set(20, 25);
        zonas_de_temperatura.get(10).set(35, 60);


        return zonas_de_temperatura;
    }


    public Cor getMassaDeArFria() {
        return mMassaDeArFria;
    }

    public Cor getMassaDeArQuente() {
        return mMassaDeArQuente;
    }

    public Cor getSuperMassaDeArFria() {
        return mSuperMassaDeArFria;
    }

    public Cor getSuperMassaDeArQuente() {
        return mSuperMassaDeArQuente;
    }

    public Cor getMassaDeArTempestade() {
        return mMassaDeArTempestade;
    }

    public Cor getMassaDeArHiperTempestade() {
        return mMassaDeArHiperTempestade;
    }


    public String getMassaDeArTipo(Cor eCor) {

        String ret = "";

        if (eCor.igual(mMassaDeArFria)) {
            ret = "FRIO";
        } else if (eCor.igual(mSuperMassaDeArFria)) {
            ret = "SUPERFRIO";
        } else if (eCor.igual(mMassaDeArQuente)) {
            ret = "QUENTE";
        } else if (eCor.igual(mSuperMassaDeArQuente)) {
            ret = "SUPERQUENTE";
        } else if (eCor.igual(mMassaDeArTempestade)) {
            ret = "TEMPESTADE";
        } else if (eCor.igual(mMassaDeArHiperTempestade)) {
            ret = "HIPERTEMPESTADE";
        }

        return ret;
    }

    public String getFatorClimatico(Cor eCor) {
        String ret = "";

        if (eCor.igual(COR_CHUVA)) {
            ret = "CHUVA";
        } else if (eCor.igual(COR_NEVE)) {
            ret = "NEVE";
        } else if (eCor.igual(COR_TEMPESTADE_CHUVA)) {
            ret = "TEMPESTADE_CHUVA";
        } else if (eCor.igual(COR_TEMPESTADE_NEVE)) {
            ret = "TEMPESTADE_NEVE";
        } else if (eCor.igual(COR_SECA)) {
            ret = "SECA";
        } else if (eCor.igual(COR_SECA_EXTREMA)) {
            ret = "SECA_EXTREMA";
        } else if (eCor.igual(COR_TEMPESTADE_VENTO)) {
            ret = "TEMPESTADE_VENTO";
        } else if (eCor.igual(COR_VENTANIA)) {
            ret = "VENTANIA";
        } else if (eCor.igual(COR_ONDA_DE_CALOR)) {
            ret = "ONDA_DE_CALOR";
        }

        return ret;
    }

    public Cor GET_FATOR_CLIMATICO_COR(String fator_climatico) {
        Cor ret = new Cor(255, 255, 255);

        if (Strings.isIgual(fator_climatico, "CHUVA")) {
            ret = COR_CHUVA;
        } else if (Strings.isIgual(fator_climatico, "NEVE")) {
            ret = COR_NEVE;
        } else if (Strings.isIgual(fator_climatico, "TEMPESTADE_CHUVA")) {
            ret = COR_TEMPESTADE_CHUVA;
        } else if (Strings.isIgual(fator_climatico, "TEMPESTADE_NEVE")) {
            ret = COR_TEMPESTADE_NEVE;
        } else if (Strings.isIgual(fator_climatico, "SECA")) {
            ret = COR_SECA;
        } else if (Strings.isIgual(fator_climatico, "SECA_EXTREMA")) {
            ret = COR_SECA_EXTREMA;
        } else if (Strings.isIgual(fator_climatico, "TEMPESTADE_VENTO")) {
            ret = COR_TEMPESTADE_VENTO;
        } else if (Strings.isIgual(fator_climatico, "VENTANIA")) {
            ret = COR_VENTANIA;
        } else if (Strings.isIgual(fator_climatico, "ONDA_DE_CALOR")) {
            ret = COR_ONDA_DE_CALOR;
        } else {
            // fmt.print("ERRO :: {}",fator_climatico);
        }

        return ret;
    }


    public static Lista<String> GET_MODELO_CLIMATICO() {
        Lista<String> modelos = new Lista<String>();

        modelos.adicionar("ESFRIANDO");
        modelos.adicionar("FRIO");
        modelos.adicionar("FRIO_EXTREMO");
        modelos.adicionar("ESFRIANDO_EXTREMO");

        modelos.adicionar("ESQUENTANDO");
        modelos.adicionar("QUENTE");
        modelos.adicionar("QUENTE_EXTREMO");
        modelos.adicionar("ESQUENTANDO_EXTREMO");

        modelos.adicionar("AMBIENTE");

        modelos.adicionar("SAZONAL");
        modelos.adicionar("SAZONAL_QUENTE");
        modelos.adicionar("SAZONAL_QUENTE_EXTREMO");
        modelos.adicionar("SAZONAL_FRIO");
        modelos.adicionar("SAZONAL_FRIO_EXTREMO");
        modelos.adicionar("SAZONAL_EXTREMO");

        return modelos;
    }

    public static Cor GET_MODELO_CLIMATICO_COR(String modelo_climatico) {
        Cor ret = new Cor(0, 0, 0);


        if (Strings.isIgual(modelo_climatico, "ESFRIANDO_EXTREMO")) {
            ret = Cor.getHexCor("#01579B");
        } else if (Strings.isIgual(modelo_climatico, "FRIO_EXTREMO")) {
            ret = Cor.getHexCor("#03A9F4");
        } else if (Strings.isIgual(modelo_climatico, "FRIO")) {
            ret = Cor.getHexCor("#4FC3F7");
        } else if (Strings.isIgual(modelo_climatico, "ESFRIANDO")) {
            ret = Cor.getHexCor("#B2EBF2");


        } else if (Strings.isIgual(modelo_climatico, "ESQUENTANDO_EXTREMO")) {
            ret = Cor.getHexCor("#D50000");
        } else if (Strings.isIgual(modelo_climatico, "QUENTE_EXTREMO")) {
            ret = Cor.getHexCor("#B71C1C");
        } else if (Strings.isIgual(modelo_climatico, "QUENTE")) {
            ret = Cor.getHexCor("#D32F2F");
        } else if (Strings.isIgual(modelo_climatico, "ESQUENTANDO")) {
            ret = Cor.getHexCor("#EF5350");


        } else if (Strings.isIgual(modelo_climatico, "AMBIENTE")) {
            ret = Cor.getHexCor("#F5F5F5");


        } else if (Strings.isIgual(modelo_climatico, "SAZONAL")) {
            ret = Cor.getHexCor("#FFF176");
        } else if (Strings.isIgual(modelo_climatico, "SAZONAL_QUENTE_EXTREMO")) {
            ret = Cor.getHexCor("#BF360C");
        } else if (Strings.isIgual(modelo_climatico, "SAZONAL_FRIO_EXTREMO")) {
            ret = Cor.getHexCor("#2E7D32");

        } else if (Strings.isIgual(modelo_climatico, "SAZONAL_EXTREMO")) {
            ret = Cor.getHexCor("#FF9800");

        } else if (Strings.isIgual(modelo_climatico, "SAZONAL_FRIO")) {
            ret = Cor.getHexCor("#9CCC65");
        } else if (Strings.isIgual(modelo_climatico, "SAZONAL_QUENTE")) {
            ret = Cor.getHexCor("#FF5722");


        }


        return ret;
    }


    public static Cor GET_MODELO_VEGETACAO_COR(String modelo_vegetacao) {
        Cor ret = new Cor(0, 0, 0);

        if (Strings.isIgual(modelo_vegetacao, "TUNDRA")) {
            ret = Cor.getHexCor("#3949AB");
        } else if (Strings.isIgual(modelo_vegetacao, "DESERTO_DE_GELO")) {
            ret = Cor.getHexCor("#B0BEC5");
        } else if (Strings.isIgual(modelo_vegetacao, "SAVANA")) {
            ret = Cor.getHexCor("#FF5722");
        } else if (Strings.isIgual(modelo_vegetacao, "FLORESTA_TEMPERADA")) {
            ret = Cor.getHexCor("#9E9D24");
        } else if (Strings.isIgual(modelo_vegetacao, "FLORESTA_TROPICAL")) {
            ret = Cor.getHexCor("#388E3C");
        } else if (Strings.isIgual(modelo_vegetacao, "TAIGA")) {
            ret = Cor.getHexCor("#00897B");
        } else if (Strings.isIgual(modelo_vegetacao, "DESERTO")) {
            ret = Cor.getHexCor("#FFCA28");
        }

        return ret;
    }

    public static Lista<String> GET_MODELO_VEGETACAO() {
        Lista<String> modelos = new Lista<String>();

        modelos.adicionar("TUNDRA");
        modelos.adicionar("DESERTO_DE_GELO");
        modelos.adicionar("SAVANA");
        modelos.adicionar("FLORESTA_TEMPERADA");
        modelos.adicionar("FLORESTA_TROPICAL");
        modelos.adicionar("TAIGA");
        modelos.adicionar("DESERTO");


        return modelos;
    }

    public static Lista<String> GET_MODELO_TEMPERATURA() {
        Lista<String> modelos = new Lista<String>();

        modelos.adicionar("MUITO_QUENTE");
        modelos.adicionar("QUENTE");
        modelos.adicionar("NORMAL");
        modelos.adicionar("FRIO");
        modelos.adicionar("MUITO_FRIO");

        return modelos;
    }

    public static Cor GET_MODELO_TEMPERATURA_COR(String modelo_temperatura) {
        Cor ret = new Cor(0, 0, 0);

        if (Strings.isIgual(modelo_temperatura, "MUITO_QUENTE")) {
            ret = Cor.getHexCor("#BF360C");
        } else if (Strings.isIgual(modelo_temperatura, "QUENTE")) {
            ret = Cor.getHexCor("#FB8C00");
        } else if (Strings.isIgual(modelo_temperatura, "FRIO")) {
            ret = Cor.getHexCor("#1976D2");
        } else if (Strings.isIgual(modelo_temperatura, "MUITO_FRIO")) {
            ret = Cor.getHexCor("#0D47A1");
        } else if (Strings.isIgual(modelo_temperatura, "NORMAL")) {
            ret = Cor.getHexCor("#8BC34A");
        }

        return ret;
    }


    public static Lista<String> GET_REGIOES() {
        Lista<String> modelos = new Lista<String>();

        Atzum eAtzum = new Atzum();
        for (int i = 1; i <= 10; i++) {
            modelos.adicionar(eAtzum.GET_REGIAO_NOME(i));
        }

        return modelos;
    }


    public static Cor GET_REGIAO_COR(String regiao) {
        Cor ret = new Cor(0, 0, 0);

        Cor COR_ALFA = Cor.getHexCor("#FFC107"); // AMARELO
        Cor COR_BETA = Cor.getHexCor("#1E88E5"); // AZUL
        Cor COR_GAMA = Cor.getHexCor("#E65100"); // LARANJA
        Cor COR_DELTA = Cor.getHexCor("#D81B60"); // ROSA
        Cor COR_EPSILON = Cor.getHexCor("#283593"); // AZUL ESCURO
        Cor COR_OMEGA = Cor.getHexCor("#00796B"); // AZUL ESCURO
        Cor COR_LAMBDA = Cor.getHexCor("#78909C"); // CINZA
        Cor COR_OMICRON = Cor.getHexCor("#8D6E63"); // MARROM
        Cor COR_ZETA = Cor.getHexCor("#D32F2F"); // VERMELHO
        Cor COR_PI = Cor.getHexCor("#689F38"); // VERDE

        Atzum eAtzum = new Atzum();

        if (Strings.isIgual(regiao, eAtzum.GET_REGIAO_NOME(1))) {
            ret = COR_ALFA;
        } else if (Strings.isIgual(regiao, eAtzum.GET_REGIAO_NOME(2))) {
            ret = COR_BETA;
        } else if (Strings.isIgual(regiao, eAtzum.GET_REGIAO_NOME(3))) {
            ret = COR_GAMA;
        } else if (Strings.isIgual(regiao, eAtzum.GET_REGIAO_NOME(4))) {
            ret = COR_DELTA;
        } else if (Strings.isIgual(regiao, eAtzum.GET_REGIAO_NOME(5))) {
            ret = COR_EPSILON;
        } else if (Strings.isIgual(regiao, eAtzum.GET_REGIAO_NOME(6))) {
            ret = COR_OMEGA;
        } else if (Strings.isIgual(regiao, eAtzum.GET_REGIAO_NOME(7))) {
            ret = COR_LAMBDA;
        } else if (Strings.isIgual(regiao, eAtzum.GET_REGIAO_NOME(8))) {
            ret = COR_OMICRON;
        } else if (Strings.isIgual(regiao, eAtzum.GET_REGIAO_NOME(9))) {
            ret = COR_ZETA;
        } else if (Strings.isIgual(regiao, eAtzum.GET_REGIAO_NOME(10))) {
            ret = COR_PI;
        }

        return ret;
    }


    public Lista<MassaDeAr> getMassasDeAr() {

        Lista<MassaDeAr> mMassasDeAr = new Lista<MassaDeAr>();

        mMassasDeAr.adicionar(new MassaDeAr("MIZ_A", "miz", "FRIO", 100, 1));
        mMassasDeAr.adicionar(new MassaDeAr("MOP_A", "mop", "QUENTE", 300, 1));
        mMassasDeAr.adicionar(new MassaDeAr("MUT_A", "mut", "FRIO", 100, 1));
        mMassasDeAr.adicionar(new MassaDeAr("MOX_A", "mox", "FRIO", 100, 1));

        mMassasDeAr.adicionar(new MassaDeAr("RAF_A", "raf", "QUENTE", 50, 1));
        mMassasDeAr.adicionar(new MassaDeAr("REZ_A", "rez", "QUENTE", 50, 1));
        mMassasDeAr.adicionar(new MassaDeAr("RUC_A", "ruc", "QUENTE", 50, 1));

        mMassasDeAr.adicionar(new MassaDeAr("REC_B", "rez", "QUENTE", 300, -1));
        //   mMassasDeAr.adicionar(new MassaDeAr("MUT_B", "mut", "FRIO", 400-300, -1));
        mMassasDeAr.adicionar(new MassaDeAr("RAF_B", "raf", "FRIO", 500, 1));


        mMassasDeAr.adicionar(new MassaDeAr("MUT_C", "mut", "FRIO", 250 + 300, -1));
        mMassasDeAr.adicionar(new MassaDeAr("REZ_C", "rez", "QUENTE", 400, -1));
        mMassasDeAr.adicionar(new MassaDeAr("RAF_C", "raf", "QUENTE", 400, -1));
        mMassasDeAr.adicionar(new MassaDeAr("RUC_C", "ruc", "QUENTE", 450 + 130, 1));


        return mMassasDeAr;
    }


}
