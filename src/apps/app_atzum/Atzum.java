package apps.app_atzum;


import apps.app_atzum.escalas.Vegetacao;
import apps.app_atzum.utils.AtzumPontos;
import libs.arquivos.binario.Inteiro;
import libs.azzal.geometria.Ponto;
import libs.azzal.utilitarios.Cor;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Extremos;
import libs.luan.Lista;
import libs.luan.Vetor;
import libs.meta_functional.Funcao;

public class Atzum {

    private String LOCAL = "/home/luan/Imagens/atzum/";

    private Vetor<String> mOceanos;
    private Lista<Vegetacao> mVegetacoes;

    private Cor mMassaDeArFria;
    private Cor mMassaDeArQuente;
    private Cor mSuperMassaDeArFria;
    private Cor mSuperMassaDeArQuente;
    private Cor mMassaDeArTempestade;
    private Cor mMassaDeArHiperTempestade;

   public static final Cor COR_CHUVA = Cor.getHexCor("#4FC3F7");
    public static final   Cor COR_NEVE = Cor.getHexCor("#CFD8DC");
    public static final  Cor COR_TEMPESTADE_CHUVA = Cor.getHexCor("#1A237E");
    public static final Cor COR_TEMPESTADE_NEVE = Cor.getHexCor("#607D8B");

    public static final  Cor COR_SECA = Cor.getHexCor("#FFEE58");
    public static final  Cor COR_SECA_EXTREMA = Cor.getHexCor("#F4511E");
    public static final  Cor COR_TEMPESTADE_VENTO = Cor.getHexCor("#BF360C");


    public static final  Cor COR_VENTANIA = Cor.getHexCor("#000000");
    public static final  Cor COR_ONDA_DE_CALOR = Cor.getHexCor("#FFFFFF");



    public Atzum() {

        mOceanos = new Vetor<String>(6);
        mOceanos.set(0, "");
        mOceanos.set(1, "Eppem");
        mOceanos.set(2, "Ikkoz");
        mOceanos.set(3, "Volgromno");
        mOceanos.set(4, "Uz");
        mOceanos.set(5, "Allamnos");

        mVegetacoes = new Lista<Vegetacao>();

        mVegetacoes.adicionar(new Vegetacao("Taiga", "#7CB342"));
        mVegetacoes.adicionar(new Vegetacao("Deserto", "#FFEB3B"));
        mVegetacoes.adicionar(new Vegetacao("Estepe", "#FBC02D"));
        mVegetacoes.adicionar(new Vegetacao("Savana", "#FF5722"));
        mVegetacoes.adicionar(new Vegetacao("Tundra", "#B0BEC5"));
        mVegetacoes.adicionar(new Vegetacao("Floresta", "#00695C"));
        mVegetacoes.adicionar(new Vegetacao("Mata", "#00ACC1"));
        mVegetacoes.adicionar(new Vegetacao("Sazonal", "#7B1FA2"));

        mMassaDeArFria = Cor.getHexCor("#4FC3F7");
        mMassaDeArQuente = Cor.getHexCor("#FFEB3B");
        mSuperMassaDeArFria = Cor.getHexCor("#283593");
        mSuperMassaDeArQuente = Cor.getHexCor("#D84315");
        mMassaDeArTempestade = Cor.getHexCor("#8BC34A");
        mMassaDeArHiperTempestade = Cor.getHexCor("#1B5E20");

    }


    public String GET_OCEANO(int valor) {
        return mOceanos.get(valor);
    }


    public Lista<Ponto> GET_CIDADES() {
        String ARQUIVO_CIDADES = LOCAL + "parametros/CIDADES.dkg";
        return AtzumPontos.ABRIR(ARQUIVO_CIDADES);
    }

    public Lista<Entidade> GET_SENSORES() {
        String ARQUIVO_CIDADES = LOCAL + "parametros/SENSORES.entts";
        return ENTT.ABRIR(ARQUIVO_CIDADES);
    }


    public Lista<Vegetacao> GET_VEGETACOES() {
        return mVegetacoes;
    }

    public Vegetacao GET_VEGETACAO(String eNome) {
        Vegetacao ret = null;
        for (Vegetacao veg : mVegetacoes) {
            if (veg.getNome().contentEquals(eNome)) {
                ret = veg;
                break;
            }
        }
        return ret;
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


    public String getMassaDeArTipo(Cor eCor){

        String ret = "";

        if(eCor.igual(mMassaDeArFria)) {
            ret = "FRIO";
        }else   if(eCor.igual(mSuperMassaDeArFria)){
            ret="SUPERFRIO";
        }else   if(eCor.igual(mMassaDeArQuente)){
            ret="QUENTE";
        }else   if(eCor.igual(mSuperMassaDeArQuente)){
            ret="SUPERQUENTE";
        }else   if(eCor.igual(mMassaDeArTempestade)){
            ret="TEMPESTADE";
        }else   if(eCor.igual(mMassaDeArHiperTempestade)){
            ret="HIPERTEMPESTADE";
        }

        return ret;
    }

    public String getFatorClimatico(Cor eCor){
        String ret = "";

        if(eCor.igual(COR_CHUVA)) {
            ret = "CHUVA";
        }else  if(eCor.igual(COR_NEVE)){
            ret="NEVE";
        }else  if(eCor.igual(COR_TEMPESTADE_CHUVA)){
            ret="TEMPESTADE_CHUVA";
        }else  if(eCor.igual(COR_TEMPESTADE_NEVE)){
            ret="TEMPESTADE_NEVE";
        }else  if(eCor.igual(COR_SECA)){
            ret="SECA";
        }else  if(eCor.igual(COR_SECA_EXTREMA)){
            ret="SECA_EXTREMA";
        }else  if(eCor.igual(COR_TEMPESTADE_VENTO)){
            ret="TEMPESTADE_VENTO";
        }else  if(eCor.igual(COR_VENTANIA)){
            ret="VENTANIA";
        }else  if(eCor.igual(COR_ONDA_DE_CALOR)){
            ret="ONDA_DE_CALOR";
        }

        return ret;
    }

}
