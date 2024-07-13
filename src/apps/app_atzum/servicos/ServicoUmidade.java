package apps.app_atzum.servicos;

import apps.app_attuz.Ferramentas.Espaco2D;
import apps.app_atzum.AtzumCreator;
import apps.app_atzum.AtzumTerra;
import apps.app_atzum.utils.AtzumCreatorInfo;
import apps.app_atzum.utils.AtzumPontos;
import apps.app_atzum.utils.Rasterizador;
import libs.arquivos.QTT;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.geometria.Ponto;
import libs.azzal.utilitarios.Cor;
import libs.azzal.utilitarios.HSV;
import libs.imagem.Imagem;
import libs.luan.*;


public class ServicoUmidade {

    private static final String SERVICO_NOME = "ServicoUmidade";

    public static void INIT() {

        AtzumCreatorInfo.iniciar(SERVICO_NOME+".INIT");

        fmt.print("-->> Parte 1");

        AtzumTerra planeta = new AtzumTerra();
        Renderizador render_zonas =AtzumCreator.GET_RENDER_PRETO_E_BRANCO();

        Cores mCores = new Cores();


        Lista<Ponto> zona_seca_1= AtzumPontos.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO("parametros/umidade/AR_SECO_1.dkg") );
        Lista<Ponto> zona_seca_2= AtzumPontos.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO("parametros/umidade/AR_SECO_2.dkg") );
        Lista<Ponto> zona_seca_3= AtzumPontos.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO("parametros/umidade/AR_SECO_3.dkg") );

        Lista<Ponto> zona_umida_1= AtzumPontos.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO("parametros/umidade/AR_UMIDO_1.dkg") );
        Lista<Ponto> zona_umida_2= AtzumPontos.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO("parametros/umidade/AR_UMIDO_2.dkg") );


        MOVER_PONTOS(zona_umida_2,+100,-150);


        MARCAR_ZONA_PONTOS(zona_seca_1,mCores.getVermelho(),render_zonas);
        MARCAR_ZONA_PONTOS(zona_seca_2,mCores.getVermelho(),render_zonas);
        MARCAR_ZONA_PONTOS(zona_seca_3,mCores.getVermelho(),render_zonas);

        MARCAR_ZONA_PONTOS(zona_umida_1,mCores.getAzul(),render_zonas);
        MARCAR_ZONA_PONTOS(zona_umida_2,mCores.getAzul(),render_zonas);

        Imagem.exportar(render_zonas.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/umidade/atzum_ar_v1.png"));



        // PARTE 2
        fmt.print("-->> Parte 2");

        HSV HSV_UMIDADE_ALTA = new HSV(230,HSV.MEDIANO,HSV.NORMAL(100));

        HSV HSV_UMIDADE_Q75 = new HSV(230,HSV.MEDIANO,HSV.NORMAL(75));
        HSV HSV_UMIDADE_Q50 = new HSV(230,HSV.MEDIANO,HSV.NORMAL(50));
        HSV HSV_UMIDADE_Q25 = new HSV(230,HSV.MEDIANO,HSV.NORMAL(25));

        HSV HSV_UMIDADE_BAIXA = new HSV(230,HSV.MEDIANO,HSV.NORMAL(10));


        Lista<Par<Ponto,Integer>> zonas = new Lista<Par<Ponto,Integer>>();

        zonas.adicionar_varios(TRANSFORMAR_PONTOS_EM_PONTOS_INTEIRO(zona_seca_1,0));
        zonas.adicionar_varios(TRANSFORMAR_PONTOS_EM_PONTOS_INTEIRO(zona_seca_2,0));
        zonas.adicionar_varios(TRANSFORMAR_PONTOS_EM_PONTOS_INTEIRO(zona_seca_3,0));

        zonas.adicionar_varios(TRANSFORMAR_PONTOS_EM_PONTOS_INTEIRO(zona_umida_1,100));
        zonas.adicionar_varios(TRANSFORMAR_PONTOS_EM_PONTOS_INTEIRO(zona_umida_2,100));


        Renderizador render_ar =Renderizador.construir(planeta.getLargura(), planeta.getAltura(),mCores.getBranco());

        for (int y = 0; y < planeta.getAltura(); y++) {
            for (int x = 0; x < planeta.getLargura(); x++) {
                if(planeta.isTerra(x,y)){

                    int zona_valor = Espaco2D.GET_VALOR_DA_DISTANCIA_MAIS_PROXIMA(zonas,x,y);

                    if(zona_valor==0) {
                        render_ar.setPixel(x, y,HSV_UMIDADE_BAIXA);
                    }else  if(zona_valor==100){
                        render_ar.setPixel(x, y,HSV_UMIDADE_ALTA);
                    }
                    
                }
            }
        }


        Imagem.exportar(render_ar.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/umidade/atzum_ar_v2.png"));

        fmt.print("-->> Parte 3");

        // PARTE 3

        Renderizador mapa_umidade_transicao_1 =AtzumCreator.GET_RENDER_PRETO_E_BRANCO();

        Lista<Ponto> transicao_1 = AtzumCreator.TERRA_GET_PONTOS_DA_AREA_QUANDO_AO_REDOR(render_ar,HSV.toRGB(HSV_UMIDADE_BAIXA),HSV.toRGB(HSV_UMIDADE_ALTA));

        AtzumCreator.TERRA_DRAW_PONTOS(render_ar,transicao_1,HSV_UMIDADE_Q75);

        AtzumCreator.TERRA_DRAW_PONTOS(mapa_umidade_transicao_1,transicao_1,HSV_UMIDADE_Q75);

        drawLimiteTransicao(transicao_1,mapa_umidade_transicao_1,HSV.toRGB(HSV_UMIDADE_Q75));

        Imagem.exportar(mapa_umidade_transicao_1.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/umidade/atzum_ar_v3.png"));



        fmt.print("-->> Parte 4");

        // PARTE 3
        AtzumCreator.TERRA_DRAW_QUANDO_DISTANCIA_PROXIMA_MENOR(render_ar,transicao_1,60,HSV_UMIDADE_Q75);
        Imagem.exportar(render_ar.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/umidade/atzum_ar_v4.png"));


        fmt.print("-->> Parte 5");

        // PARTE 5
        Renderizador mapa_umidade_transicao_2 =AtzumCreator.GET_RENDER_PRETO_E_BRANCO();

        Lista<Ponto> transicao_2 = new Lista<Ponto>();

        for (int y = 0; y < planeta.getAltura(); y++) {
            for (int x = 0; x < planeta.getLargura(); x++) {
                if(planeta.isTerra(x,y)){

                    if(render_ar.getPixel(x,y).igual(HSV.toRGB(HSV_UMIDADE_Q75))) {

                        boolean marcar = Rasterizador.TEM_AO_REDOR(render_ar,x,y,HSV.toRGB(HSV_UMIDADE_BAIXA));

                        if(marcar){
                            mapa_umidade_transicao_2.setPixel(x, y,HSV_UMIDADE_Q50);
                            transicao_2.adicionar(new Ponto(x,y));
                        }

                    }



                }
            }
        }

        drawLimiteTransicao(transicao_2,mapa_umidade_transicao_2,HSV.toRGB(HSV_UMIDADE_Q50));

        Imagem.exportar(mapa_umidade_transicao_2.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/umidade/atzum_ar_v5.png"));


        fmt.print("-->> Parte 6");
        AtzumCreator.TERRA_DRAW_QUANDO_DISTANCIA_PROXIMA_MENOR(render_ar,transicao_2,50,HSV_UMIDADE_Q50);
        Imagem.exportar(render_ar.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/umidade/atzum_ar_v6.png"));


        fmt.print("-->> Parte 7");

        // PARTE 6
        Renderizador mapa_umidade_transicao_3 =AtzumCreator.GET_RENDER_PRETO_E_BRANCO();

        Lista<Ponto> transicao_3 = new Lista<Ponto>();

        for (int y = 0; y < planeta.getAltura(); y++) {
            for (int x = 0; x < planeta.getLargura(); x++) {
                if(planeta.isTerra(x,y)){

                    if(render_ar.getPixel(x,y).igual(HSV.toRGB(HSV_UMIDADE_Q50))) {

                        boolean marcar = Rasterizador.TEM_AO_REDOR(render_ar,x,y,HSV.toRGB(HSV_UMIDADE_BAIXA));

                        if(marcar){
                            mapa_umidade_transicao_3.setPixel(x, y,HSV_UMIDADE_Q25);
                            transicao_3.adicionar(new Ponto(x,y));
                        }

                    }



                }
            }
        }

        drawLimiteTransicao(transicao_3,mapa_umidade_transicao_3,HSV.toRGB(HSV_UMIDADE_Q25));

        Imagem.exportar(mapa_umidade_transicao_3.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/umidade/atzum_ar_v7.png"));



        fmt.print("-->> Parte 8");
        AtzumCreator.TERRA_DRAW_QUANDO_DISTANCIA_PROXIMA_MENOR(render_ar,transicao_3,30,HSV_UMIDADE_Q25);
        Imagem.exportar(render_ar.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/umidade/atzum_ar_v8.png"));





        fmt.print("-->> Dados");
        // FASE 9
        QTT.alocar(AtzumCreator.DADOS_GET_ARQUIVO("umidade.qtt"),planeta.getLargura(),planeta.getAltura());
        QTT.alterar_todos(AtzumCreator.DADOS_GET_ARQUIVO("umidade.qtt"),planeta.getLargura(),planeta.getAltura(),-1);

        for (int y = 0; y < planeta.getAltura(); y++) {
            for (int x = 0; x < planeta.getLargura(); x++) {
                if(planeta.isTerra(x,y)){

                    Cor umidade_tipo = render_ar.getPixel(x,y);

                    int umidade = 0;

                    if(umidade_tipo.igual(HSV.toRGB(HSV_UMIDADE_BAIXA))) {
                        umidade = 0;
                    }else    if(umidade_tipo.igual(HSV.toRGB(HSV_UMIDADE_Q25))){
                        umidade=25;
                    }else    if(umidade_tipo.igual(HSV.toRGB(HSV_UMIDADE_Q50))){
                        umidade=50;
                    }else    if(umidade_tipo.igual(HSV.toRGB(HSV_UMIDADE_Q75))){
                        umidade=75;
                    }else    if(umidade_tipo.igual(HSV.toRGB(HSV_UMIDADE_ALTA))){
                        umidade=100;
                    }


                    QTT.alterar(AtzumCreator.DADOS_GET_ARQUIVO("umidade.qtt"),x,y,umidade);


                }
            }
        }




        fmt.print("-->> Parte 9");

        AtzumTerra mapa_planeta = new AtzumTerra();
        Lista<Ponto> pontos_terra = new Lista<Ponto>();

        for (int y = 0; y < mapa_planeta.getAltura(); y++) {
            for (int x = 0; x < mapa_planeta.getLargura(); x++) {
                if (mapa_planeta.isTerra(x, y)) {
                    pontos_terra.adicionar(new Ponto(x, y));
                }
            }
        }

        int criar_pontos = (mapa_planeta.getLargura() / 100) * (mapa_planeta.getAltura() / 100);

        Lista<Par<Ponto, Integer>> pontos_escolhidos = new Lista<Par<Ponto, Integer>>();

        int processando = 0;

        while (processando < criar_pontos) {

            int escolhido = Aleatorio.aleatorio(pontos_terra.getQuantidade());
            Ponto ponto_escolhido = pontos_terra.get(escolhido);

            int umidade = QTT.pegar(AtzumCreator.DADOS_GET_ARQUIVO("umidade.qtt"), ponto_escolhido.getX(), ponto_escolhido.getY());

            int umidade_min = 0;
            int umidade_max = 0;

            if(umidade==0) {
                umidade_min=0;
                umidade_max=15;
            }else   if(umidade==25){
                umidade_min=25;
                umidade_max=35;
            }else   if(umidade==50){
                umidade_min=50;
                umidade_max=65;
            }else   if(umidade==75){
                umidade_min=75;
                umidade_max=80;
            }else   if(umidade==100){
                umidade_min=81;
                umidade_max=100;
            }


            int valor_aqui = Aleatorio.alatorio_entre(umidade_min, umidade_max);

            pontos_escolhidos.adicionar(new Par<Ponto, Integer>(ponto_escolhido, valor_aqui));

            processando += 1;
        }

        fmt.print("Terra pontos    : {}", pontos_terra.getQuantidade());
        fmt.print("Criar pontos    : {}", criar_pontos);
        fmt.print("Pontos criados  : {}", pontos_escolhidos.getQuantidade());


        fmt.print(">> Processando");
        for (int y = 0; y < mapa_planeta.getAltura(); y++) {
            for (int x = 0; x < mapa_planeta.getLargura(); x++) {
                if (mapa_planeta.isTerra(x, y)) {

                    int valor_proximo = Espaco2D.GET_VALOR_DA_DISTANCIA_MAIS_PROXIMA(pontos_escolhidos, x, y);

                    QTT.alterar(AtzumCreator.DADOS_GET_ARQUIVO("umidade.qtt"), x, y, valor_proximo);

                }
            }
        }

        fmt.print(">> Renderizando");

        Renderizador render_distancia = Renderizador.construir(mapa_planeta.getLargura(), mapa_planeta.getAltura(), mCores.getBranco());


        for (int y = 0; y < mapa_planeta.getAltura(); y++) {
            for (int x = 0; x < mapa_planeta.getLargura(); x++) {
                if (mapa_planeta.isTerra(x, y)) {

                    int umidade = QTT.pegar(AtzumCreator.DADOS_GET_ARQUIVO("umidade.qtt"), x, y);;

                    HSV hsv = new HSV(230,HSV.MEDIANO,HSV.NORMAL(umidade));

                    render_distancia.setPixel(x, y,hsv);


                }
            }
        }


        Imagem.exportar(render_distancia.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/umidade/atzum_ar_v9.png"));


        fmt.print("Tudo OK !");

        AtzumCreatorInfo.terminar(SERVICO_NOME + ".INIT");
        AtzumCreatorInfo.exibir_item(SERVICO_NOME + ".INIT");

    }


    public static void MARCAR_ZONA(String nome, Cor zona_cor, Renderizador render){

        Lista<Ponto> pontos_da_zona= AtzumPontos.ABRIR(nome );

        boolean tem_anterior = false;
        Ponto pt_anterior = null;

        for(Ponto ponto : pontos_da_zona){
            render.drawCirculoCentralizado_Pintado(ponto,5,zona_cor);

            if(tem_anterior){
                render.drawLinha(ponto.getX(),ponto.getY(),pt_anterior.getX(),pt_anterior.getY(),zona_cor);
            }

            tem_anterior=true;
            pt_anterior=ponto;

        }

    }

    public static Lista<Par<Ponto,Integer>> OBTER_ZONA(String nome, int valor){

        Lista<Ponto> pontos_da_zona= AtzumPontos.ABRIR(nome);

        Lista<Par<Ponto,Integer>> pontos_com_valor = new Lista<Par<Ponto,Integer>>();

        for(Ponto ponto : pontos_da_zona){
            pontos_com_valor.adicionar(new Par<Ponto,Integer>(ponto,valor));
        }

        return pontos_com_valor;
    }

    public static void drawLimiteTransicao(Lista<Ponto> transicao,Renderizador render_transicao,Cor eCor){

        Lista<Ponto> transicao_1_embaralhados =transicao.getCopia();
        Lista<Ponto> transicao_1_marcados =new Lista<Ponto> ();

        int t1_quantidade = transicao.getQuantidade()/100;
        if(t1_quantidade>0){
            Embaralhar.emabaralhe(transicao_1_embaralhados);

            while(transicao_1_marcados.getQuantidade()<100){
                transicao_1_marcados.adicionar(transicao_1_embaralhados.get(0));
                transicao_1_embaralhados.removerIndex(0);
            }

        }

        for(Ponto p : transicao_1_marcados){
            render_transicao.drawCirculoCentralizado_Pintado(p.getX(), p.getY(),5,eCor);
        }

    }

    public static void MARCAR_ZONA(Lista<Par<Ponto,Integer>> pontos_de_zona, Cor zona_cor, Renderizador render){


        boolean tem_anterior = false;
        Ponto pt_anterior = null;

        for(Par<Ponto,Integer> ponto_valor : pontos_de_zona){

            Ponto ponto = ponto_valor.getChave();

            render.drawCirculoCentralizado_Pintado(ponto,5,zona_cor);

            if(tem_anterior){
                render.drawLinha(ponto.getX(),ponto.getY(),pt_anterior.getX(),pt_anterior.getY(),zona_cor);
            }

            tem_anterior=true;
            pt_anterior=ponto;

        }

    }

    public static void MARCAR_ZONA_PONTOS(Lista<Ponto> pontos_de_zona, Cor zona_cor, Renderizador render){


        boolean tem_anterior = false;
        Ponto pt_anterior = null;

        for(Ponto ponto : pontos_de_zona){

            render.drawCirculoCentralizado_Pintado(ponto,5,zona_cor);

            if(tem_anterior){
                render.drawLinha(ponto.getX(),ponto.getY(),pt_anterior.getX(),pt_anterior.getY(),zona_cor);
            }

            tem_anterior=true;
            pt_anterior=ponto;

        }

    }

    public static Lista<Par<Ponto,Integer>> TRANSFORMAR_PONTOS_EM_PONTOS_INTEIRO(Lista<Ponto> pontos, int valor){

        Lista<Par<Ponto,Integer>> pontos_com_valor = new Lista<Par<Ponto,Integer>>();

        for(Ponto ponto : pontos){
            pontos_com_valor.adicionar(new Par<Ponto,Integer>(ponto,valor));
        }

        return pontos_com_valor;
    }

    public static void MOVER_PONTOS(Lista<Ponto> pontos_de_zona, int dx,int dy) {

        for(Ponto ponto : pontos_de_zona) {
            ponto.setPos(ponto.getX()+dx,ponto.getY()+dy);
        }

        }

    }
