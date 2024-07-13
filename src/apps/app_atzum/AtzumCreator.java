package apps.app_atzum;

import apps.app_attuz.Ferramentas.Espaco2D;
import apps.app_atzum.escalas.EscalaAQ4;
import apps.app_atzum.servicos.ServicoRelevo;
import apps.app_atzum.utils.AtzumPontosInteiro;
import apps.app_atzum.utils.Rasterizador;
import apps.app_atzum.utils.RegiaoDefinida;
import libs.arquivos.IM;
import libs.arquivos.QTT;
import libs.arquivos.ds.DS;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.geometria.Ponto;
import libs.azzal.utilitarios.Cor;
import libs.azzal.utilitarios.HSV;
import libs.fs.PastaFS;
import libs.imagem.Imagem;
import libs.luan.*;
import libs.meta_functional.Acao;

import java.awt.image.BufferedImage;

public class AtzumCreator {

    private static String LOCAL = "/home/luan/Imagens/atzum/";
    private static String LOCAL_PROCESSANDO = "/home/luan/Imagens/atzum/build/processando/";
    private static String LOCAL_DADOS = "/home/luan/Imagens/atzum/dados/";
    private static String LOCAL_VIDEOS = "/home/luan/Imagens/atzum/videos/";

    public static String LOCAL_GET_ARQUIVO(String nome) {
        return new PastaFS(LOCAL).getArquivo(nome);
    }

    public static String PROCESSANDO_GET_ARQUIVO(String nome) {
        return new PastaFS(LOCAL_PROCESSANDO).getArquivo(nome);
    }

    public static String VIDEO_GET_ARQUIVO(String nome) {
        return new PastaFS(LOCAL_VIDEOS).getArquivo(nome);
    }

    public static String DADOS_GET_ARQUIVO(String nome) {
        return new PastaFS(LOCAL_DADOS).getArquivo(nome);
    }

    public static BufferedImage GET_MAPA() {
        BufferedImage mapa = Imagem.GET_IMAGEM_POR_PIXEL_RGB(AtzumCreator.LOCAL_GET_ARQUIVO("atzum_terra.png"));
        return mapa;
    }

    public static BufferedImage GET_MAPA_TERRA() {
        BufferedImage mapa = Imagem.GET_IMAGEM_POR_PIXEL_RGB(AtzumCreator.LOCAL_GET_ARQUIVO("atzum_terra.png"));
        return mapa;
    }

    public static BufferedImage GET_MAPA_DE_CONTORNO() {
        BufferedImage mapa = Imagem.GET_IMAGEM_POR_PIXEL_RGB(AtzumCreator.LOCAL_GET_ARQUIVO("atzum_contornos.png"));
        return mapa;
    }

    public static BufferedImage GET_MAPA_DE_CONTORNO_REGIOES() {
        BufferedImage mapa = Imagem.GET_IMAGEM_POR_PIXEL_RGB(AtzumCreator.LOCAL_GET_ARQUIVO("build/regioes/regioes_v3.png"));
        return mapa;
    }


    public static BufferedImage GET_MAPA_RELEVO_TERRA() {
        BufferedImage mapa = Imagem.GET_IMAGEM_POR_PIXEL_RGB(AtzumCreator.LOCAL_GET_ARQUIVO("relevo_terra.png"));
        return mapa;
    }

    public static BufferedImage GET_MAPA_DE_RELEVO() {
        BufferedImage mapa = Imagem.GET_IMAGEM_POR_PIXEL_RGB(AtzumCreator.LOCAL_GET_ARQUIVO("atzum_relevo.png"));
        return mapa;
    }


    public static BufferedImage GET_MAPA_DE_REGIOES() {
        BufferedImage mapa = Imagem.GET_IMAGEM_POR_PIXEL_RGB(AtzumCreator.LOCAL_GET_ARQUIVO("atzum_regioes.png"));
        return mapa;
    }

    public static BufferedImage GET_MAPA_DE_OCEANOS() {
        BufferedImage mapa = Imagem.GET_IMAGEM_POR_PIXEL_RGB(AtzumCreator.LOCAL_GET_ARQUIVO("atzum_oceanos.png"));
        return mapa;
    }

    public static BufferedImage GET_MAPA_VEGETACAO() {
        BufferedImage mapa = Imagem.GET_IMAGEM_POR_PIXEL_RGB(AtzumCreator.LOCAL_GET_ARQUIVO("build/clima/clima.png"));
        return mapa;
    }

    public static BufferedImage GET_MAPA_MARGEM_OCEANICA() {
        BufferedImage mapa = Imagem.GET_IMAGEM_POR_PIXEL_RGB(AtzumCreator.LOCAL_GET_ARQUIVO("atzum_organizado.png"));
        return mapa;
    }

    public static void CRIAR_INICIAR() {

        BufferedImage mapa = Imagem.GET_IMAGEM_POR_PIXEL_RGB(LOCAL + "inicial/atzum.png");

        mapa = Imagem.girar(mapa);
        mapa = Imagem.espelhar_verticalmente(mapa);
        mapa = Imagem.espelhar_horizontalmente(mapa);

        Imagem.exportar(mapa, LOCAL_GET_ARQUIVO("atzum_organizado_v2.png"));

        Renderizador render = new Renderizador(mapa);

        Cores mCores = new Cores();

        NORMALIZAR_2_CORES_ABAIXO_DE(render, 100, mCores.getAmarelo(), mCores.getPreto());
        // terras_v1(render);

        // Imagem.exportar(render.toImagemSemAlfa(), LOCAL_GET_ARQUIVO("atzum_terra.png"));


        //QTT.alocar(AtzumCreator.DADOS_GET_ARQUIVO("planeta.qtt"),render.getLargura(),render.getAltura());
        //  QTT.alocar(AtzumCreator.DADOS_GET_ARQUIVO("regioes.qtt"),render.getLargura(),render.getAltura());


        fmt.print("Tudo Pronto !");
    }

    public static void NORMALIZAR_2_CORES_ABAIXO_DE(Renderizador render, int valor_referencia, Cor cor_especial, Cor cor_fundo) {

        int largura = render.getLargura();
        int altura = render.getAltura();

        for (int y = 0; y < altura; y++) {

            for (int x = 0; x < largura; x++) {

                Cor cor = render.getPixel(x, y);

                if (cor.getRed() < valor_referencia && cor.getGreen() < valor_referencia && cor.getBlue() < valor_referencia) {
                    render.setPixel(x, y, cor_especial);
                } else {
                    render.setPixel(x, y, cor_fundo);
                }


            }
        }

    }

    public static void terras_v1(Renderizador render) {

        Cores mCores = new Cores();

        RefInt processante = new RefInt(0);

        Acao durante_mudanca = new Acao() {
            @Override
            public void fazer() {
                Imagem.exportar(render.toImagemSemAlfa(), LOCAL_GET_ARQUIVO("processando.png"));
            }
        };

        Acao a_cada_100 = new Acao() {
            @Override
            public void fazer() {

                Imagem.exportar(render.toImagemSemAlfa(), PROCESSANDO_GET_ARQUIVO("processando_" + fmt.zerado(processante.get(), 4) + ".png"));
                processante.set(processante.get() + 1);

            }
        };


        Rasterizador.RASTERIZAR_COM(render, 700, 500, mCores.getPreto(), mCores.getAmarelo(), durante_mudanca, a_cada_100);

        Rasterizador.RASTERIZAR_COM(render, 470, 520, mCores.getPreto(), mCores.getAmarelo(), durante_mudanca, a_cada_100);

        Rasterizador.RASTERIZAR_COM(render, 404, 1116, mCores.getPreto(), mCores.getAmarelo(), durante_mudanca, a_cada_100);

        Rasterizador.RASTERIZAR_COM(render, 1006, 1204, mCores.getPreto(), mCores.getAmarelo(), durante_mudanca, a_cada_100);
        Rasterizador.RASTERIZAR_COM(render, 1008, 1124, mCores.getPreto(), mCores.getAmarelo(), durante_mudanca, a_cada_100);
        Rasterizador.RASTERIZAR_COM(render, 1058, 1078, mCores.getPreto(), mCores.getAmarelo(), durante_mudanca, a_cada_100);
        Rasterizador.RASTERIZAR_COM(render, 1170, 1182, mCores.getPreto(), mCores.getAmarelo(), durante_mudanca, a_cada_100);


        Rasterizador.RASTERIZAR_COM(render, 1822, 1100, mCores.getPreto(), mCores.getAmarelo(), durante_mudanca, a_cada_100);
        Rasterizador.RASTERIZAR_COM(render, 1804, 1248, mCores.getPreto(), mCores.getAmarelo(), durante_mudanca, a_cada_100);

        Rasterizador.RASTERIZAR_COM(render, 1998, 1212, mCores.getPreto(), mCores.getAmarelo(), durante_mudanca, a_cada_100);
        Rasterizador.RASTERIZAR_COM(render, 1930, 1052, mCores.getPreto(), mCores.getAmarelo(), durante_mudanca, a_cada_100);


        //  Rasterizador.trocar_cores(render,mCores.getVermelho(),mCores.getAmarelo());


        Imagem.exportar(render.toImagemSemAlfa(), PROCESSANDO_GET_ARQUIVO("terra.png"));

    }


    public static void SEPARAR_TERRA_E_AGUA() {

        Cores mCores = new Cores();

        BufferedImage mapa = Imagem.GET_IMAGEM_POR_PIXEL_RGB(LOCAL_GET_ARQUIVO("atzum_terra.png"));
        Renderizador render = new Renderizador(mapa);
        Rasterizador.trocar_cores(render, mCores.getPreto(), mCores.getAzul());

        Imagem.exportar(render.toImagemSemAlfa(), PROCESSANDO_GET_ARQUIVO("atzum_agua.png"));

    }


    public static void ORGANIZAR_DADOS_PLANETA() {


        Cores mCores = new Cores();

        BufferedImage mapa = GET_MAPA();
        Renderizador render = new Renderizador(mapa);

        int largura = render.getLargura();
        int altura = render.getAltura();

        QTT.alocar(AtzumCreator.DADOS_GET_ARQUIVO("planeta.qtt"), render.getLargura(), render.getAltura());

        for (int y = 0; y < altura; y++) {

            for (int x = 0; x < largura; x++) {

                Cor cor = render.getPixel(x, y);

                if (cor.igual(mCores.getAmarelo())) {
                    QTT.alterar(AtzumCreator.DADOS_GET_ARQUIVO("planeta.qtt"), x, y, 1);
                } else if (cor.igual(mCores.getPreto())) {
                    QTT.alterar(AtzumCreator.DADOS_GET_ARQUIVO("planeta.qtt"), x, y, -1);
                }

            }
        }


        fmt.print("OK !");
    }


    public static void ORGANIZAR_OCEANOS() {
        fmt.print("Feature :: Oceanos");


        String ARQUIVO_OCEANO = AtzumCreator.LOCAL_GET_ARQUIVO("OCEANOS.dkg");
        Unico<Par<Ponto, Integer>> pontos_de_relevo = AtzumPontosInteiro.UNICOS(AtzumPontosInteiro.ABRIR_ZERADO(ARQUIVO_OCEANO));

        int valor = 100;

        for (Par<Ponto, Integer> ponto : pontos_de_relevo) {
            ponto.setValor(valor);
            valor += 100;
        }

        BufferedImage mapa = AtzumCreator.GET_MAPA();
        Renderizador render = new Renderizador(mapa);

        QTT.alocar(AtzumCreator.DADOS_GET_ARQUIVO("oceanos.qtt"), render.getLargura(), render.getAltura());

        Cores mCores = new Cores();
        Rasterizador.trocar_cores(render, mCores.getPreto(), mCores.getBranco());

        int largura = render.getLargura();
        int altura = render.getAltura();


        for (int y = 0; y < altura; y++) {

            for (int x = 0; x < largura; x++) {

                Cor cor = render.getPixel(x, y);

                if (cor.igual(mCores.getBranco())) {

                    int valor_proximo = ServicoRelevo.ALTITUDE_MAIS_PROXIMA(pontos_de_relevo, x, y);

                    Cor eCor = EscalaAQ4.GET_COR(valor_proximo);
                    render.setPixel(x, y, eCor);

                    QTT.alterar(AtzumCreator.DADOS_GET_ARQUIVO("oceanos.qtt"), x, y, (valor_proximo / 100));

                } else {
                    QTT.alterar(AtzumCreator.DADOS_GET_ARQUIVO("oceanos.qtt"), x, y, -1);
                }


            }
        }


        Rasterizador.trocar_cores(render, mCores.getAmarelo(), mCores.getPreto());

        Imagem.exportar(render.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("atzum_oceanos.png"));


        fmt.print("OK !");
    }


    public static void EMPACOTAR_ATZUM() {

        DS.limpar(LOCAL_GET_ARQUIVO("Atzum.ds"));

        DS.adicionar(LOCAL_GET_ARQUIVO("Atzum.ds"), "ATZUM.im", IM.salvar_to_bytes(GET_MAPA()));
        DS.adicionar(LOCAL_GET_ARQUIVO("Atzum.ds"), "BUILD.txt", Texto.arquivo_ler(LOCAL_GET_ARQUIVO("AtzumCreator.txt")));
        DS.adicionar(LOCAL_GET_ARQUIVO("Atzum.ds"), "RELEVO_TERRA.dkg", Texto.arquivo_ler(LOCAL_GET_ARQUIVO("RELEVO_TERRA.dkg")));
        DS.adicionar(LOCAL_GET_ARQUIVO("Atzum.ds"), "RELEVO_AGUA.dkg", Texto.arquivo_ler(LOCAL_GET_ARQUIVO("RELEVO_AGUA.dkg")));
        DS.adicionar(LOCAL_GET_ARQUIVO("Atzum.ds"), "OCEANOS.dkg", Texto.arquivo_ler(LOCAL_GET_ARQUIVO("OCEANOS.dkg")));
        DS.adicionar(LOCAL_GET_ARQUIVO("Atzum.ds"), "CIDADES_DEFINIDAS.dkg", Texto.arquivo_ler(LOCAL_GET_ARQUIVO("CIDADES_DEFINIDAS.dkg")));


    }


    public static Lista<RegiaoDefinida> GET_REGIOES() {

        Cores mCores = new Cores();

        Lista<RegiaoDefinida> regioes = new Lista<RegiaoDefinida>();


        // REGIAO 1
        // Rasterizador.RASTERIZAR_COM(render, 548, 1063, mCores.getPreto(), mCores.getAmarelo(), durante_mudanca, a_cada_100);
        //   Rasterizador.RASTERIZAR_COM(render, 402, 1095, mCores.getPreto(), mCores.getAmarelo(), durante_mudanca, a_cada_100);
        regioes.adicionar(new RegiaoDefinida(548, 1063, mCores.getAmarelo(), 1, 10));
        regioes.adicionar(new RegiaoDefinida(402, 1095, mCores.getAmarelo(), 1, 11));


        // REGIAO 2
        // Rasterizador.RASTERIZAR_COM(render, 578, 767, mCores.getPreto(), mCores.getAzul(), durante_mudanca, a_cada_100);
        // Rasterizador.RASTERIZAR_COM(render, 507, 507, mCores.getPreto(), mCores.getAzul(), durante_mudanca, a_cada_100);
        regioes.adicionar(new RegiaoDefinida(578, 767, mCores.getAzul(), 2, 20));
        regioes.adicionar(new RegiaoDefinida(507, 507, mCores.getAzul(), 2, 21));


        // REGIAO 3
        //  Rasterizador.RASTERIZAR_COM(render, 864, 486, mCores.getPreto(), mCores.getLaranja(), durante_mudanca, a_cada_100);
        regioes.adicionar(new RegiaoDefinida(864, 486, mCores.getLaranja(), 3, 30));

        // REGIAO 4
        // Rasterizador.RASTERIZAR_COM(render, 1410, 1209, mCores.getPreto(), mCores.getRosa(), durante_mudanca, a_cada_100);
        // Rasterizador.RASTERIZAR_COM(render, 1173, 1176, mCores.getPreto(), mCores.getRosa(), durante_mudanca, a_cada_100);
        regioes.adicionar(new RegiaoDefinida(1410, 1209, mCores.getRosa(), 4, 40));
        regioes.adicionar(new RegiaoDefinida(1173, 1176, mCores.getRosa(), 4, 41));


        // REGIAO 5
        //   Rasterizador.RASTERIZAR_COM(render, 1801, 1230, mCores.getPreto(), mCores.getBranco(), durante_mudanca, a_cada_100);
        // Rasterizador.RASTERIZAR_COM(render, 1906, 1276, mCores.getPreto(), mCores.getBranco(), durante_mudanca, a_cada_100);
        regioes.adicionar(new RegiaoDefinida(1801, 1230, mCores.getTurjo(), 5, 50));
        regioes.adicionar(new RegiaoDefinida(1906, 1276, mCores.getTurjo(), 5, 51));

        // REGIAO 6
        // Rasterizador.RASTERIZAR_COM(render, 1984, 1191, mCores.getPreto(), mCores.getTurquesa(), durante_mudanca, a_cada_100);
        // Rasterizador.RASTERIZAR_COM(render, 1895, 1125, mCores.getPreto(), mCores.getTurquesa(), durante_mudanca, a_cada_100);
        //   Rasterizador.RASTERIZAR_COM(render, 1929, 1048, mCores.getPreto(), mCores.getTurquesa(), durante_mudanca, a_cada_100);
        regioes.adicionar(new RegiaoDefinida(1984, 1191, mCores.getTurquesa(), 6, 60));
        regioes.adicionar(new RegiaoDefinida(1895, 1125, mCores.getTurquesa(), 6, 61));
        regioes.adicionar(new RegiaoDefinida(1929, 1048, mCores.getTurquesa(), 6, 62));

        // REGIAO 7
        //  Rasterizador.RASTERIZAR_COM(render, 1841, 1097, mCores.getPreto(), mCores.getCinza(), durante_mudanca, a_cada_100);
        regioes.adicionar(new RegiaoDefinida(1841, 1097, mCores.getCinza(), 7, 70));


        // REGIAO 8
        //  Rasterizador.RASTERIZAR_COM(render, 1733, 1066, mCores.getPreto(), mCores.getMarrom(), durante_mudanca, a_cada_100);
        regioes.adicionar(new RegiaoDefinida(1733, 1066, mCores.getMarrom(), 8, 80));

        // REGIAO 9
        // Rasterizador.RASTERIZAR_COM(render, 1798, 1128, mCores.getPreto(), mCores.getCianeto(), durante_mudanca, a_cada_100);
        regioes.adicionar(new RegiaoDefinida(1798, 1128, mCores.getCianeto(), 9, 90));

        // REGIAO 10
        // Rasterizador.RASTERIZAR_COM(render, 900, 900, mCores.getPreto(), mCores.getVerde(), durante_mudanca, a_cada_100);
        // Rasterizador.RASTERIZAR_COM(render, 1003, 1198, mCores.getPreto(), mCores.getVerde(), durante_mudanca, a_cada_100);
        // Rasterizador.RASTERIZAR_COM(render, 1014, 1124, mCores.getPreto(), mCores.getVerde(), durante_mudanca, a_cada_100);
        //  Rasterizador.RASTERIZAR_COM(render, 1060, 1070, mCores.getPreto(), mCores.getVerde(), durante_mudanca, a_cada_100);

        regioes.adicionar(new RegiaoDefinida(900, 900, mCores.getVerde(), 10, 100));
        regioes.adicionar(new RegiaoDefinida(1003, 1198, mCores.getVerde(), 10, 101));
        regioes.adicionar(new RegiaoDefinida(1014, 1124, mCores.getVerde(), 10, 102));
        regioes.adicionar(new RegiaoDefinida(1060, 1070, mCores.getVerde(), 10, 103));

        return regioes;
    }


    public static Renderizador GET_RENDER_PRETO_E_BRANCO() {
        Cores mCores = new Cores();
        Renderizador renderizador = new Renderizador(AtzumCreator.GET_MAPA());
        Rasterizador.trocar_cores(renderizador, mCores.getPreto(), mCores.getBranco());
        Rasterizador.trocar_cores(renderizador, mCores.getAmarelo(), mCores.getPreto());

        return renderizador;
    }

    public static Renderizador GET_RENDER_FUNDO_PRETO() {
        Cores mCores = new Cores();
        Renderizador renderizador = new Renderizador(AtzumCreator.GET_MAPA());
        Rasterizador.trocar_cores(renderizador, mCores.getBranco(), mCores.getPreto());
        Rasterizador.trocar_cores(renderizador, mCores.getAmarelo(), mCores.getBranco());

        return renderizador;
    }

    public static Renderizador GET_RENDER_FUNDO_PRETO_MARGEM_OCEANICA() {
        Cores mCores = new Cores();
        Renderizador renderizador = new Renderizador(AtzumCreator.GET_MAPA_MARGEM_OCEANICA());
        Rasterizador.trocar_cores(renderizador, mCores.getBranco(), mCores.getPreto());
        Rasterizador.trocar_cores(renderizador, mCores.getVermelho(), mCores.getBranco());

        return renderizador;
    }

    public static void TERRA_DRAW_PONTOS(Renderizador render, Lista<Ponto> pontos, HSV eCor) {

        AtzumTerra planeta = new AtzumTerra();

        for (Ponto pt : pontos) {
            if (planeta.isTerra(pt.getX(), pt.getY())) {
                render.setPixel(pt.getX(), pt.getY(), eCor);
            }
        }

    }

    public static void TERRA_DRAW_QUANDO_DISTANCIA_PROXIMA_MENOR(Renderizador render, Lista<Ponto> pontos, int distancia_maxima, HSV eCor) {

        AtzumTerra planeta = new AtzumTerra();

        for (int y = 0; y < planeta.getAltura(); y++) {
            for (int x = 0; x < planeta.getLargura(); x++) {
                if (planeta.isTerra(x, y)) {

                    int proximidade = Espaco2D.GET_DISTANCIA_MAIS_PROXIMA(pontos, x, y);

                    if (proximidade < distancia_maxima) {
                        render.setPixel(x, y, eCor);
                    }

                }
            }
        }

    }


    public static Lista<Ponto> TERRA_GET_PONTOS_DA_AREA_QUANDO_AO_REDOR(Renderizador render, Cor eCorDaArea, Cor eCorProxima) {

        AtzumTerra planeta = new AtzumTerra();

        Lista<Ponto> transicao_1 = new Lista<Ponto>();

        for (int y = 0; y < planeta.getAltura(); y++) {
            for (int x = 0; x < planeta.getLargura(); x++) {
                if (render.getPixel(x, y).igual(eCorDaArea)) {

                    boolean marcar = Rasterizador.TEM_AO_REDOR(render, x, y, eCorProxima);

                    if (marcar) {
                        transicao_1.adicionar(new Ponto(x, y));
                    }

                }
            }
        }

        return transicao_1;
    }


    public static void MARCAR_ZONA(String nome, Cor zona_cor, Renderizador render) {

        Lista<Ponto> pontos_da_zona = AtzumCidades.ABRIR_LOCAIS(AtzumCreator.LOCAL_GET_ARQUIVO(nome));


        boolean tem_anterior = false;
        Ponto pt_anterior = null;

        for (Ponto ponto : pontos_da_zona) {
            render.drawCirculoCentralizado_Pintado(ponto, 5, zona_cor);

            if (tem_anterior) {
                render.drawLinha(ponto.getX(), ponto.getY(), pt_anterior.getX(), pt_anterior.getY(), zona_cor);
            }

            tem_anterior = true;
            pt_anterior = ponto;

        }

    }

    public static void MARCAR_ZONA_DELIMITADA(Lista<Ponto> zona_delimitada, Cor zona_cor, Renderizador render) {


        boolean tem_anterior = false;
        Ponto pt_anterior = null;

        for (Ponto ponto : zona_delimitada) {
            render.drawCirculoCentralizado_Pintado(ponto, 5, zona_cor);

            if (tem_anterior) {
                render.drawLinha(ponto.getX(), ponto.getY(), pt_anterior.getX(), pt_anterior.getY(), zona_cor);
            }

            tem_anterior = true;
            pt_anterior = ponto;

        }

    }

    public static void MARCAR_ZONA_DELIMITADA_SEM_LINHA(Lista<Ponto> zona_delimitada, Cor zona_cor, Renderizador render) {


        boolean tem_anterior = false;
        Ponto pt_anterior = null;

        for (Ponto ponto : zona_delimitada) {
            render.drawCirculoCentralizado_Pintado(ponto, 5, zona_cor);

            if (tem_anterior) {
                // render.drawLinha(ponto.getX(),ponto.getY(),pt_anterior.getX(),pt_anterior.getY(),zona_cor);
            }

            tem_anterior = true;
            pt_anterior = ponto;

        }

    }


    public static void PREENCHER_TERRA(AtzumTerra planeta,Renderizador render,int px,int py,int largura,int altura,Cor eCor) {


        for (int y = py; y < (py+altura); y++) {
            for (int x = (px); x < (px+largura); x++) {
                if (planeta.isTerra(x, y)) {
                    render.setPixel(x,y,eCor);
                }
            }
        }


    }

}
