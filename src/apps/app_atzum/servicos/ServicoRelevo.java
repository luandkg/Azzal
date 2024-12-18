package apps.app_atzum.servicos;

import apps.app_attuz.Ferramentas.Espaco2D;
import apps.app_atzum.AtzumCreator;
import apps.app_atzum.AtzumTerra;
import apps.app_atzum.escalas.EscalaAQ4;
import apps.app_atzum.escalas.EscalaRT3;
import apps.app_atzum.utils.AtzumCriativoLog;
import apps.app_atzum.utils.AtzumPontosInteiro;
import apps.app_atzum.utils.Rasterizador;
import libs.arquivos.QTT;
import libs.arquivos.binario.Inteiro;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.geometria.Ponto;
import libs.azzal.utilitarios.Cor;
import libs.imagem.Imagem;
import libs.luan.*;

import java.awt.image.BufferedImage;

public class ServicoRelevo {

    private static final String SERVICO_NOME = "ServicoRelevo";

    public static void INIT() {

        AtzumCriativoLog.iniciar(SERVICO_NOME + ".INIT");


        relevo_terra();
        relevo_agua();
        relevo();

        AtzumTerra terra = new AtzumTerra();

        QTT.alocar(AtzumCreator.DADOS_GET_ARQUIVO("relevo.qtt"),terra.getLargura(),terra.getAltura());
        QTT.alterar_todos(AtzumCreator.DADOS_GET_ARQUIVO("relevo.qtt"),terra.getLargura(),terra.getAltura(),0);

        RELEVO_AGUA_PARCELAS();
        RELEVO_TERRA_PARCELAS();

        AtzumCriativoLog.terminar(SERVICO_NOME + ".INIT");
        AtzumCriativoLog.exibir_item(SERVICO_NOME + ".INIT");

    }

    public static void relevo_terra() {

        String ARQUIVO_RELEVO = AtzumCreator.LOCAL_GET_ARQUIVO("parametros/relevo/RELEVO_TERRA.dkg");
        Unico<Par<Ponto, Integer>> pontos_de_relevo = AtzumPontosInteiro.UNICOS(AtzumPontosInteiro.ABRIR(ARQUIVO_RELEVO));

        BufferedImage mapa = AtzumCreator.GET_MAPA();
        Renderizador render = new Renderizador(mapa);


        Cores mCores = new Cores();
        Rasterizador.trocar_cores(render, mCores.getAmarelo(), mCores.getBranco());

        int largura = render.getLargura();
        int altura = render.getAltura();


        for (int y = 0; y < altura; y++) {

            for (int x = 0; x < largura; x++) {

                Cor cor = render.getPixel(x, y);

                if (cor.igual(mCores.getBranco())) {

                    int valor_proximo = ALTITUDE_MAIS_PROXIMA(pontos_de_relevo, x, y);

                    Cor eCor = EscalaRT3.GET_COR(valor_proximo);
                    render.setPixel(x, y, eCor);

                }


            }
        }


        Imagem.exportar(render.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/planeta/atzum_relevo_terra.png"));


    }

    public static void relevo_agua() {

        String ARQUIVO_RELEVO = AtzumCreator.LOCAL_GET_ARQUIVO("parametros/relevo/RELEVO_AGUA.dkg");
        Unico<Par<Ponto, Integer>> pontos_de_relevo = AtzumPontosInteiro.UNICOS(AtzumPontosInteiro.ABRIR(ARQUIVO_RELEVO));

        BufferedImage mapa = AtzumCreator.GET_MAPA();
        Renderizador render = new Renderizador(mapa);


        Cores mCores = new Cores();
        Rasterizador.trocar_cores(render, mCores.getPreto(), mCores.getBranco());

        int largura = render.getLargura();
        int altura = render.getAltura();


        for (int y = 0; y < altura; y++) {

            for (int x = 0; x < largura; x++) {

                Cor cor = render.getPixel(x, y);

                if (cor.igual(mCores.getBranco())) {

                    int valor_proximo = ALTITUDE_MAIS_PROXIMA(pontos_de_relevo, x, y);

                    Cor eCor = EscalaAQ4.GET_COR(valor_proximo);
                    render.setPixel(x, y, eCor);

                }


            }
        }


        Rasterizador.trocar_cores(render, mCores.getAmarelo(), mCores.getPreto());

        Imagem.exportar(render.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/planeta/atzum_relevo_agua.png"));


    }

    public static void relevo() {

        String ARQUIVO_RELEVO_TERRA = AtzumCreator.LOCAL_GET_ARQUIVO("parametros/relevo/RELEVO_TERRA.dkg");
        String ARQUIVO_RELEVO_AGUA = AtzumCreator.LOCAL_GET_ARQUIVO("parametros/relevo/RELEVO_AGUA.dkg");

        Unico<Par<Ponto, Integer>> pontos_de_relevo_terra = AtzumPontosInteiro.UNICOS(AtzumPontosInteiro.ABRIR(ARQUIVO_RELEVO_TERRA));
        Unico<Par<Ponto, Integer>> pontos_de_relevo_agua = AtzumPontosInteiro.UNICOS(AtzumPontosInteiro.ABRIR(ARQUIVO_RELEVO_AGUA));

        Renderizador render = new Renderizador(AtzumCreator.GET_MAPA());
        AtzumTerra terra = new AtzumTerra();


        Cores mCores = new Cores();


        for (int y = 0; y < terra.getAltura(); y++) {
            for (int x = 0; x < terra.getLargura(); x++) {

                Cor cor = render.getPixel(x, y);

                if (cor.igual(mCores.getAmarelo())) {

                    int valor_proximo = ALTITUDE_MAIS_PROXIMA(pontos_de_relevo_terra, x, y);

                    Cor eCor = EscalaRT3.GET_COR(valor_proximo);
                    render.setPixel(x, y, eCor);

                } else if (cor.igual(mCores.getPreto())) {

                    int valor_proximo = ALTITUDE_MAIS_PROXIMA(pontos_de_relevo_agua, x, y);

                    Cor eCor = EscalaAQ4.GET_COR(valor_proximo);
                    render.setPixel(x, y, eCor);

                }


            }
        }


        Imagem.exportar(render.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/planeta/atzum_relevo.png"));


    }

    public static int ALTITUDE_MAIS_PROXIMA(Unico<Par<Ponto, Integer>> pontos_de_relevo, int px, int py) {

        int altitude = 0;
        double menor_distancia = Double.MAX_VALUE;

        for (Par<Ponto, Integer> relevo : pontos_de_relevo) {

            double distancia = Espaco2D.distancia_entre_pontos_double(relevo.getChave().getX(), relevo.getChave().getY(), px, py);

            if (distancia < menor_distancia) {
                menor_distancia = distancia;
                altitude = relevo.getValor();
            }

        }

        return altitude;
    }

    public static int ALTITUDE_MAIS_PROXIMA_LISTA(Lista<Par<Ponto, Integer>> pontos_de_relevo, int px, int py) {

        int altitude = 0;
        double menor_distancia = Double.MAX_VALUE;

        for (Par<Ponto, Integer> relevo : pontos_de_relevo) {

            double distancia = Espaco2D.distancia_entre_pontos_double(relevo.getChave().getX(), relevo.getChave().getY(), px, py);

            if (distancia < menor_distancia) {
                menor_distancia = distancia;
                altitude = relevo.getValor();
            }

        }

        return altitude;
    }

    public static void RELEVO_TERRA_PARCELAS() {

        for (int altitude : EscalaRT3.GET_VALORES()) {
            RELEVO_TERRA_PARCELA(altitude);
        }


    }

    public static void RELEVO_AGUA_PARCELAS() {
        for (int altitude : EscalaAQ4.GET_VALORES()) {
            RELEVO_AGUA_PARCELA(altitude);
        }
    }


    public static void RELEVO_TERRA_PARCELA(int faixa_valor) {


        int faixa_valor_maximo = faixa_valor + 100;


        String ARQUIVO_RELEVO = AtzumCreator.LOCAL_GET_ARQUIVO("parametros/relevo/RELEVO_TERRA.dkg");
        Unico<Par<Ponto, Integer>> pontos_de_relevo = AtzumPontosInteiro.UNICOS(AtzumPontosInteiro.ABRIR(ARQUIVO_RELEVO));

        BufferedImage mapa = AtzumCreator.GET_MAPA();
        Renderizador render = new Renderizador(mapa);


        Cores mCores = new Cores();
        Rasterizador.trocar_cores(render, mCores.getAmarelo(), mCores.getBranco());

        int largura = render.getLargura();
        int altura = render.getAltura();

        Lista<Integer> valores = EscalaRT3.GET_VALORES();

        int chave_contador = 0;
        int chave_maximo = 100 + Aleatorio.aleatorio(200);
        int valor_corrente = Aleatorio.escolha_um(valores);

        Lista<Ponto> eixos = new Lista<Ponto>();
        Lista<Par<Ponto, Integer>> pontos_de_relevo_aqui = new Lista<Par<Ponto, Integer>>();

        for (int y = 0; y < altura; y++) {

            for (int x = 0; x < largura; x++) {

                Cor cor = render.getPixel(x, y);

                if (cor.igual(mCores.getBranco())) {

                    int valor_proximo = ALTITUDE_MAIS_PROXIMA(pontos_de_relevo, x, y);

                    if (valor_proximo == faixa_valor) {
                        Cor eCor = EscalaRT3.GET_COR(valor_corrente);
                        //  render.setPixel(x, y,eCor);

                        chave_contador += 1;
                        if (chave_contador == chave_maximo) {
                            chave_contador = 0;
                            chave_maximo = 100 + Aleatorio.aleatorio(200);
                            valor_corrente = Aleatorio.escolha_um(valores);

                            eixos.adicionar(new Ponto(x, y));
                            pontos_de_relevo_aqui.adicionar(new Par<Ponto, Integer>(new Ponto(x, y), Aleatorio.escolha_um(valores)));
                        }

                    }


                }


            }
        }

        fmt.print("TERRA - {} >> Eixos :: {}", fmt.espacar_antes(faixa_valor,4), eixos.getQuantidade());


        for (int y = 0; y < altura; y++) {

            for (int x = 0; x < largura; x++) {

                Cor cor = render.getPixel(x, y);

                if (cor.igual(mCores.getBranco())) {

                    int valor_proximo = ALTITUDE_MAIS_PROXIMA(pontos_de_relevo, x, y);

                    if (valor_proximo == faixa_valor) {
                        valor_proximo = ALTITUDE_MAIS_PROXIMA_LISTA(pontos_de_relevo_aqui, x, y);

                        Cor eCor = EscalaRT3.GET_COR(valor_proximo);
                        render.setPixel(x, y, eCor);

                        int indice = EscalaRT3.GET_INDICE(valor_proximo);
                        int altitude = faixa_valor + (indice * 5);

                        if (altitude >= faixa_valor_maximo) {
                            altitude = faixa_valor_maximo - 10;
                        }

                        QTT.alterar(AtzumCreator.DADOS_GET_ARQUIVO("relevo.qtt"), x, y, altitude);

                    }


                }


            }
        }

        Imagem.exportar(render.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/planeta/relevo_terra_" + faixa_valor + ".png"));


    }


    public static void RELEVO_AGUA_PARCELA(int faixa_valor) {


        int faixa_valor_minimo = faixa_valor + 300;
        int faixa_valor_maximo = faixa_valor_minimo + 100;


        String ARQUIVO_RELEVO = AtzumCreator.LOCAL_GET_ARQUIVO("parametros/relevo/RELEVO_AGUA.dkg");
        Unico<Par<Ponto, Integer>> pontos_de_relevo = AtzumPontosInteiro.UNICOS(AtzumPontosInteiro.ABRIR(ARQUIVO_RELEVO));

        BufferedImage mapa = AtzumCreator.GET_MAPA();
        Renderizador render = new Renderizador(mapa);


        Cores mCores = new Cores();
        Rasterizador.trocar_cores(render, mCores.getPreto(), mCores.getBranco());
        Rasterizador.trocar_cores(render, mCores.getAmarelo(), mCores.getPreto());

        int largura = render.getLargura();
        int altura = render.getAltura();

        Lista<Integer> valores = EscalaRT3.GET_VALORES();

        int chave_contador = 0;
        int chave_maximo = 100 + Aleatorio.aleatorio(200);
        int valor_corrente = Aleatorio.escolha_um(valores);

        Lista<Ponto> eixos = new Lista<Ponto>();
        Lista<Par<Ponto, Integer>> pontos_de_relevo_aqui = new Lista<Par<Ponto, Integer>>();

        for (int y = 0; y < altura; y++) {

            for (int x = 0; x < largura; x++) {

                Cor cor = render.getPixel(x, y);

                if (cor.igual(mCores.getBranco())) {

                    int valor_proximo = ALTITUDE_MAIS_PROXIMA(pontos_de_relevo, x, y);

                    if (valor_proximo == faixa_valor) {
                        Cor eCor = EscalaRT3.GET_COR(valor_corrente);
                        //  render.setPixel(x, y,eCor);

                        chave_contador += 1;
                        if (chave_contador == chave_maximo) {
                            chave_contador = 0;
                            chave_maximo = 100 + Aleatorio.aleatorio(200);
                            valor_corrente = Aleatorio.escolha_um(valores);

                            eixos.adicionar(new Ponto(x, y));
                            pontos_de_relevo_aqui.adicionar(new Par<Ponto, Integer>(new Ponto(x, y), Aleatorio.escolha_um(valores)));
                        }

                    }


                }


            }
        }

        fmt.print("AGUA - {} >> Eixos :: {}", fmt.espacar_antes(faixa_valor,4), eixos.getQuantidade());


        for (int y = 0; y < altura; y++) {

            for (int x = 0; x < largura; x++) {

                Cor cor = render.getPixel(x, y);

                if (cor.igual(mCores.getBranco())) {

                    int valor_proximo = ALTITUDE_MAIS_PROXIMA(pontos_de_relevo, x, y);

                    if (valor_proximo == faixa_valor) {
                        valor_proximo = ALTITUDE_MAIS_PROXIMA_LISTA(pontos_de_relevo_aqui, x, y);

                        Cor eCor = EscalaAQ4.GET_COR(valor_proximo);
                        render.setPixel(x, y, eCor);

                        int indice = EscalaAQ4.GET_INDICE(valor_proximo);
                        int altitude = faixa_valor_minimo + (indice * 5);

                        if (altitude >= faixa_valor_maximo) {
                            altitude = faixa_valor_maximo - 10;
                        }

                        altitude = altitude * (-1);

                        QTT.alterar(AtzumCreator.DADOS_GET_ARQUIVO("relevo.qtt"), x, y, altitude);

                    }


                }


            }
        }

        Imagem.exportar(render.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/planeta/relevo_agua_" + faixa_valor + ".png"));


    }


    public static void RELEVO_ANALISAR() {


        AtzumTerra terra = new AtzumTerra();

        QTT relevo = QTT.getTudo(AtzumCreator.DADOS_GET_ARQUIVO("relevo.qtt"));

        Extremos<Integer> terrestre = new Extremos<>(Inteiro.GET_ORDENAVEL());
        Extremos<Integer> oceanico = new Extremos<>(Inteiro.GET_ORDENAVEL());

        int superficie_terrestre = 0;
        int superficie_oceanica = 0;

        for (int y = 0; y < terra.getAltura(); y++) {
            for (int x = 0; x < terra.getLargura(); x++) {

                int relevo_aqui = relevo.getValor(x,y);

                if(terra.isTerra(x,y)){
                    terrestre.set(relevo_aqui);
                    superficie_terrestre+=1;
                }else{
                    oceanico.set(relevo_aqui);
                    superficie_oceanica+=1;
                }

            }
        }

        int superficie_total = superficie_terrestre+superficie_oceanica;


        fmt.print("Superficie");
        fmt.print("\t ++ Terreste : {} :: {}",superficie_terrestre,fmt.f2Porcentagem(superficie_terrestre,superficie_total) );
        fmt.print("\t ++ Oceanico : {} :: {}",superficie_oceanica,fmt.f2Porcentagem(superficie_oceanica,superficie_total) );

        fmt.print("Relevo Terreste : {} -- {}",terrestre.getMenor(),terrestre.getMaior());
        fmt.print("Relevo Oceanico : {} -- {}",oceanico.getMenor(),oceanico.getMaior());



    }


}
