package apps.app_atzum.servicos;

import apps.app_attuz.Ferramentas.Espaco2D;
import apps.app_atzum.*;
import apps.app_atzum.escalas.EscalaAQ4;
import apps.app_atzum.escalas.Vegetacao;
import apps.app_atzum.utils.AtzumCreatorInfo;
import apps.app_letrum.Maker.FonteRunTime;
import libs.arquivos.QTT;
import libs.arquivos.binario.Inteiro;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.utilitarios.Cor;
import libs.azzal.utilitarios.HSV;
import libs.imagem.Imagem;
import libs.luan.Extremos;
import libs.luan.Opcional;
import libs.luan.Ordenavel;
import libs.luan.fmt;
import libs.tronarko.Tron;
import libs.tronarko.Tronarko;

public class ServicoClima {


    public static void INIT(){

        AtzumCreatorInfo.iniciar("ServicoClima.INIT");

        Cores mCores = new Cores();

        AtzumTerra terra = new AtzumTerra();
        AtzumVegetacoes vegetacoes =new AtzumVegetacoes();



        Renderizador render_umidade =AtzumCreator.GET_RENDER_FUNDO_PRETO();
        Renderizador render_t1 =AtzumCreator.GET_RENDER_FUNDO_PRETO();
        Renderizador render_t2 =AtzumCreator.GET_RENDER_FUNDO_PRETO();

        Renderizador render_t1_t2 =AtzumCreator.GET_RENDER_FUNDO_PRETO();
        Renderizador render_t1_t2_completo =AtzumCreator.GET_RENDER_FUNDO_PRETO();
        Renderizador render_clima =AtzumCreator.GET_RENDER_FUNDO_PRETO();

        Renderizador render_vegetacao =AtzumCreator.GET_RENDER_FUNDO_PRETO();

        Extremos<Integer> temperatura_m1 = new Extremos<Integer>(Inteiro.GET_ORDENAVEL());
        Extremos<Integer> temperatura_m2 = new Extremos<Integer>(Inteiro.GET_ORDENAVEL());
        Extremos<Integer> umidade = new Extremos<Integer>(Inteiro.GET_ORDENAVEL());



        int TEMPERATURA_NORMAL=22;

        int TEMPERATURA_NORMAL_SUPERIOR = 25;
        int TEMPERATURA_NORMAL_INFERIOR = 20;

        int TEMPERATURA_QUENTE=35;
        int TEMPERATURA_FRIO=15;

        String arquivo_processando_umidade ="umidade.qtt";

        String arquivo_processando_temperatura1 = "build/complexo/temperatura_processando_v1.qtt";
        String arquivo_processando_temperatur2 = "build/complexo/temperatura_processando_v2.qtt";


        int IS_TEMPERATURA_NORMAL = 0;
        int IS_TEMPERATURA_FRIA = -1;
        int IS_TEMPERATURA_QUENTE = +1;

        int UMIDADE_ALTA_EXTREMA = 90;
        int UMIDADE_ALTA = 75;
        int UMIDADE_BAIXA = 25;
        int UMIDADE_BAIXA_EXTREMA = 10;

        int IS_UMIDADE_ALTA_EXTREMA = +2;
        int IS_UMIDADE_ALTA = +1;
        int IS_UMIDADE_NORMAL = 0;
        int IS_UMIDADE_BAIXA = -1;
        int IS_UMIDADE_BAIXA_EXTREMA = -2;


        int IS_TEMPERATURA_SEMPRE_QUENTE = +3;
        int IS_TEMPERATURA_SEMPRE_FRIA = -3;
        int IS_TEMPERATURA_SEMPRE_NORMAL = 0;

        int IS_TEMPERATURA_ESQUENTANTO = +2;
        int IS_TEMPERATURA_ESFRIANDO = -2;
        int IS_TEMPERATURA_VARIACAO_EXTREMA= 4;


        Tron tron1 = Tronarko.getTronAgora();


        QTT QTT_T1 = QTT.getTudo(AtzumCreator.LOCAL_GET_ARQUIVO(arquivo_processando_temperatura1));
        QTT QTT_T2 = QTT.getTudo(AtzumCreator.LOCAL_GET_ARQUIVO(arquivo_processando_temperatur2));
        QTT QTT_UMIDADE = QTT.getTudo(AtzumCreator.DADOS_GET_ARQUIVO(arquivo_processando_umidade));

        Tron tron2 = Tronarko.getTronAgora();

        TRONARKO_DIRETIVA.DIRETIVA(tron1,tron2);

        for (int y = 0; y < terra.getAltura(); y++) {
            for (int x = 0; x < terra.getLargura(); x++) {
                if(terra.isTerra(x,y)){

                    int t1 = QTT_T1.getValor(x,y);
                    int t2 = QTT_T2.getValor(x,y);

                    int valor_umidade = QTT_UMIDADE.getValor(x,y);

                    temperatura_m1.set(t1);
                    temperatura_m2.set(t2);
                    umidade.set(valor_umidade);

                    render_umidade.setPixel(x, y,mCores.getPreto());

                    int UMIDADE = IS_UMIDADE_NORMAL;

                    int TEMP_Q1 = IS_TEMPERATURA_NORMAL;
                    int TEMP_Q2 = IS_TEMPERATURA_NORMAL;



                    if(valor_umidade>=UMIDADE_ALTA) {
                        UMIDADE=IS_UMIDADE_ALTA;
                    }

                    if(valor_umidade>=UMIDADE_ALTA_EXTREMA) {
                        UMIDADE=IS_UMIDADE_ALTA_EXTREMA;
                    }

                    if(valor_umidade<=UMIDADE_BAIXA) {
                        UMIDADE=IS_UMIDADE_BAIXA;
                    }

                    if(valor_umidade<=UMIDADE_BAIXA_EXTREMA) {
                        UMIDADE=IS_UMIDADE_BAIXA_EXTREMA;
                    }

                    if(t1<=TEMPERATURA_FRIO) {
                        TEMP_Q1 = IS_TEMPERATURA_FRIA;
                    }else  if(t1>=TEMPERATURA_QUENTE){
                        TEMP_Q1=IS_TEMPERATURA_QUENTE;
                    }

                    if(t2<=TEMPERATURA_FRIO) {
                        TEMP_Q2 = IS_TEMPERATURA_FRIA;
                    }else  if(t2>=TEMPERATURA_QUENTE){
                        TEMP_Q2=IS_TEMPERATURA_QUENTE;
                    }


                    if(UMIDADE==IS_UMIDADE_ALTA_EXTREMA){
                        render_umidade.setPixel(x, y,mCores.getAzul());
                    }

                    if(UMIDADE==IS_UMIDADE_ALTA){
                        render_umidade.setPixel(x, y,mCores.getCianeto());
                    }

                    if(UMIDADE==IS_UMIDADE_BAIXA_EXTREMA){
                        render_umidade.setPixel(x, y,mCores.getVermelho());
                    }

                    if(UMIDADE==IS_UMIDADE_BAIXA){
                        render_umidade.setPixel(x, y,mCores.getAmarelo());
                    }

                    if(UMIDADE==IS_UMIDADE_NORMAL){
                        render_umidade.setPixel(x, y,mCores.getCinza());
                    }

                    // T1
                    if(TEMP_Q1==IS_TEMPERATURA_FRIA){
                        render_t1.setPixel(x, y,mCores.getAzul());
                    }

                    if(TEMP_Q1==IS_TEMPERATURA_QUENTE){
                        render_t1.setPixel(x, y,mCores.getVermelho());
                    }

                    if(TEMP_Q1==IS_TEMPERATURA_NORMAL){
                        render_t1.setPixel(x, y,mCores.getCinza());
                    }

                    // T2
                    if(TEMP_Q2==IS_TEMPERATURA_FRIA){
                        render_t2.setPixel(x, y,mCores.getAzul());
                    }

                    if(TEMP_Q2==IS_TEMPERATURA_QUENTE){
                        render_t2.setPixel(x, y,mCores.getVermelho());
                    }

                    if(TEMP_Q2==IS_TEMPERATURA_NORMAL){
                        render_t2.setPixel(x, y,mCores.getCinza());
                    }

                    // T1 vs T2

                    int TEMPERATURA_VARIACAO = IS_TEMPERATURA_SEMPRE_NORMAL;

                    if(TEMP_Q1==TEMP_Q2){
                        render_t1_t2.setPixel(x, y,mCores.getPreto());
                        render_t1_t2_completo.setPixel(x, y,mCores.getPreto());


                        int TEMP_SEMPRE = TEMP_Q1;

                        if(TEMP_SEMPRE==IS_TEMPERATURA_FRIA) {
                             TEMPERATURA_VARIACAO = IS_TEMPERATURA_SEMPRE_FRIA;
                            render_t1_t2_completo.setPixel(x, y, HSV.toRGB(new HSV(240,5*11,5*11)));
                        }else   if(TEMP_SEMPRE==IS_TEMPERATURA_NORMAL){
                            TEMPERATURA_VARIACAO = IS_TEMPERATURA_SEMPRE_NORMAL;
                            render_t1_t2_completo.setPixel(x, y, HSV.toRGB(new HSV(100,5*11,5*11)));
                        }else   if(TEMP_SEMPRE==IS_TEMPERATURA_QUENTE){
                            TEMPERATURA_VARIACAO = IS_TEMPERATURA_SEMPRE_QUENTE;
                            render_t1_t2_completo.setPixel(x, y, HSV.toRGB(new HSV(5,5*11,9*11)));
                        }

                    }else{

                        render_t1_t2.setPixel(x, y,mCores.getAmarelo());

                        if((TEMP_Q1==IS_TEMPERATURA_QUENTE && TEMP_Q2==IS_TEMPERATURA_NORMAL)||(TEMP_Q1==IS_TEMPERATURA_NORMAL && TEMP_Q2==IS_TEMPERATURA_QUENTE) ) {
                            TEMPERATURA_VARIACAO = IS_TEMPERATURA_ESQUENTANTO;
                            render_t1_t2.setPixel(x, y, mCores.getVermelho());
                        }else    if((TEMP_Q1==IS_TEMPERATURA_FRIA && TEMP_Q2==IS_TEMPERATURA_NORMAL)||(TEMP_Q1==IS_TEMPERATURA_NORMAL && TEMP_Q2==IS_TEMPERATURA_FRIA) ){
                            TEMPERATURA_VARIACAO = IS_TEMPERATURA_ESFRIANDO;
                            render_t1_t2.setPixel(x, y,mCores.getAzul());
                        }else    if((TEMP_Q1==IS_TEMPERATURA_FRIA && TEMP_Q2==IS_TEMPERATURA_QUENTE)||(TEMP_Q1==IS_TEMPERATURA_QUENTE && TEMP_Q2==IS_TEMPERATURA_FRIA) ){
                            TEMPERATURA_VARIACAO = IS_TEMPERATURA_VARIACAO_EXTREMA;
                            render_t1_t2.setPixel(x, y,mCores.getVerde());
                        }

                        render_t1_t2_completo.setPixel(x, y,mCores.getAmarelo());

                        if((TEMP_Q1==IS_TEMPERATURA_QUENTE && TEMP_Q2==IS_TEMPERATURA_NORMAL)||(TEMP_Q1==IS_TEMPERATURA_NORMAL && TEMP_Q2==IS_TEMPERATURA_QUENTE) ) {
                            render_t1_t2_completo.setPixel(x, y, mCores.getVermelho());
                        }else    if((TEMP_Q1==IS_TEMPERATURA_FRIA && TEMP_Q2==IS_TEMPERATURA_NORMAL)||(TEMP_Q1==IS_TEMPERATURA_NORMAL && TEMP_Q2==IS_TEMPERATURA_FRIA) ){
                            render_t1_t2_completo.setPixel(x, y,mCores.getAzul());
                        }else    if((TEMP_Q1==IS_TEMPERATURA_FRIA && TEMP_Q2==IS_TEMPERATURA_QUENTE)||(TEMP_Q1==IS_TEMPERATURA_QUENTE && TEMP_Q2==IS_TEMPERATURA_FRIA) ){
                            render_t1_t2_completo.setPixel(x, y,mCores.getVerde());
                        }
                    }



                    // RENDER CLIMA

                    if(TEMPERATURA_VARIACAO==IS_TEMPERATURA_SEMPRE_FRIA) {

                        if(UMIDADE==IS_UMIDADE_ALTA_EXTREMA){
                            render_clima.setPixel(x, y, vegetacoes.TUNDRA().getCor());
                        }else if(UMIDADE==IS_UMIDADE_ALTA){
                            render_clima.setPixel(x, y, vegetacoes.TUNDRA().getCor());
                        }else if(UMIDADE==IS_UMIDADE_NORMAL){
                            render_clima.setPixel(x, y, vegetacoes.TUNDRA().getCor());
                        }else  if(UMIDADE==IS_UMIDADE_BAIXA){
                            render_clima.setPixel(x, y, vegetacoes.TUNDRA().getCor());
                        }else if(UMIDADE==IS_UMIDADE_BAIXA_EXTREMA){
                            render_clima.setPixel(x, y, vegetacoes.TUNDRA().getCor());
                        }


                    }else   if(TEMPERATURA_VARIACAO==IS_TEMPERATURA_SEMPRE_NORMAL){

                        if(UMIDADE==IS_UMIDADE_ALTA_EXTREMA){
                            render_clima.setPixel(x, y, vegetacoes.FLORESTA().getCor());
                        }else if(UMIDADE==IS_UMIDADE_ALTA){
                            render_clima.setPixel(x, y, vegetacoes.FLORESTA().getCor());
                        }else if(UMIDADE==IS_UMIDADE_NORMAL){
                            render_clima.setPixel(x, y, vegetacoes.DESERTO().getCor());
                        }else  if(UMIDADE==IS_UMIDADE_BAIXA){
                            render_clima.setPixel(x, y, vegetacoes.DESERTO().getCor());
                        }else if(UMIDADE==IS_UMIDADE_BAIXA_EXTREMA){
                            render_clima.setPixel(x, y, vegetacoes.DESERTO().getCor());
                        }

                    }else   if(TEMPERATURA_VARIACAO==IS_TEMPERATURA_SEMPRE_QUENTE){

                        if(UMIDADE==IS_UMIDADE_ALTA_EXTREMA){
                            render_clima.setPixel(x, y, vegetacoes.SAVANA().getCor());
                        }else if(UMIDADE==IS_UMIDADE_ALTA){
                            render_clima.setPixel(x, y, vegetacoes.SAVANA().getCor());
                        }else if(UMIDADE==IS_UMIDADE_NORMAL){
                            render_clima.setPixel(x, y, vegetacoes.DESERTO().getCor());
                        }else  if(UMIDADE==IS_UMIDADE_BAIXA){
                            render_clima.setPixel(x, y, vegetacoes.DESERTO().getCor());
                        }else if(UMIDADE==IS_UMIDADE_BAIXA_EXTREMA){
                            render_clima.setPixel(x, y, vegetacoes.DESERTO().getCor());
                        }

                    }else   if(TEMPERATURA_VARIACAO==IS_TEMPERATURA_ESQUENTANTO){

                        if(UMIDADE==IS_UMIDADE_ALTA_EXTREMA){
                            render_clima.setPixel(x, y, vegetacoes.FLORESTA().getCor());
                        }else if(UMIDADE==IS_UMIDADE_ALTA){
                            render_clima.setPixel(x, y, vegetacoes.FLORESTA().getCor());
                        }else if(UMIDADE==IS_UMIDADE_NORMAL){
                            render_clima.setPixel(x, y, vegetacoes.ESTEPE().getCor());
                        }else  if(UMIDADE==IS_UMIDADE_BAIXA){
                            render_clima.setPixel(x, y, vegetacoes.ESTEPE().getCor());
                        }else if(UMIDADE==IS_UMIDADE_BAIXA_EXTREMA){
                            render_clima.setPixel(x, y, vegetacoes.ESTEPE().getCor());
                        }

                    }else   if(TEMPERATURA_VARIACAO==IS_TEMPERATURA_ESFRIANDO){

                        if(UMIDADE==IS_UMIDADE_ALTA_EXTREMA){
                            render_clima.setPixel(x, y, vegetacoes.MATA().getCor());
                        }else if(UMIDADE==IS_UMIDADE_ALTA){
                            render_clima.setPixel(x, y, vegetacoes.MATA().getCor());
                        }else if(UMIDADE==IS_UMIDADE_NORMAL){
                            render_clima.setPixel(x, y, vegetacoes.ESTEPE().getCor());
                        }else  if(UMIDADE==IS_UMIDADE_BAIXA){
                            render_clima.setPixel(x, y, vegetacoes.ESTEPE().getCor());
                        }else if(UMIDADE==IS_UMIDADE_BAIXA_EXTREMA){
                            render_clima.setPixel(x, y, vegetacoes.ESTEPE().getCor());
                        }

                    }else   if(TEMPERATURA_VARIACAO==IS_TEMPERATURA_VARIACAO_EXTREMA){

                        if(UMIDADE==IS_UMIDADE_ALTA_EXTREMA){
                            render_clima.setPixel(x, y, vegetacoes.SAZONAL().getCor());
                        }else if(UMIDADE==IS_UMIDADE_ALTA){
                            render_clima.setPixel(x, y, vegetacoes.SAZONAL().getCor());
                        }else if(UMIDADE==IS_UMIDADE_NORMAL){
                            render_clima.setPixel(x, y, vegetacoes.TAIGA().getCor());
                        }else  if(UMIDADE==IS_UMIDADE_BAIXA){
                            render_clima.setPixel(x, y, vegetacoes.TAIGA().getCor());
                        }else if(UMIDADE==IS_UMIDADE_BAIXA_EXTREMA){
                            render_clima.setPixel(x, y, vegetacoes.TAIGA().getCor());
                        }
                    }


                }
            }
        }


        Tron tron3 = Tronarko.getTronAgora();

        TRONARKO_DIRETIVA.DIRETIVA(tron2,tron3);


        fmt.print("Temperatura m1 : {} -- {}",temperatura_m1.getMenor(),temperatura_m1.getMaior());
        fmt.print("Temperatura m2 : {} -- {}",temperatura_m2.getMenor(),temperatura_m2.getMaior());
        fmt.print("Umidade        : {} -- {}",umidade.getMenor(),umidade.getMaior());





        Imagem.exportar(render_umidade.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/clima/atzum_umidade.png"));
        Imagem.exportar(render_vegetacao.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/clima/atzum_vegetacao.png"));


        Imagem.exportar(render_t1.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/clima/t1.png"));
        Imagem.exportar(render_t2.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/clima/t2.png"));
        Imagem.exportar(render_t1_t2.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/clima/tv.png"));
        Imagem.exportar(render_t1_t2_completo.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/clima/tvc.png"));
        Imagem.exportar(render_clima.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/clima/clima.png"));




        // INFO VEGETACAO
        FonteRunTime escritor = new FonteRunTime(mCores.getBranco(),40);
        Renderizador vegetacao_expandida = Renderizador.construir(render_vegetacao.getLargura()+600,render_vegetacao.getAltura(),mCores.getPreto());

        escritor.setRenderizador(vegetacao_expandida);

        vegetacao_expandida.drawImagem(0,0,render_clima.toImagemSemAlfa());
        //vegetacao_expandida.drawRect_Pintado(render_vegetacao.getLargura(),600,500,500,mCores.getBranco());

        int veg_py = 600;
        for(Vegetacao vegetacao : vegetacoes.listar()){

            vegetacao_expandida.drawRect_Pintado(render_vegetacao.getLargura(),veg_py,50,50,vegetacao.getCor());
            escritor.escreva(render_vegetacao.getLargura()+60,veg_py,vegetacao.getNome());

            veg_py+=60;
        }


        Imagem.exportar(vegetacao_expandida.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/clima/atzum_vegetacao_infos.png"));


        AtzumCreatorInfo.terminar("ServicoClima.INIT");

    }

}
