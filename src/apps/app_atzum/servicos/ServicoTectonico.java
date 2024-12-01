package apps.app_atzum.servicos;

import apps.app_attuz.Ferramentas.Espaco2D;
import apps.app_attuz.Ferramentas.GPS;
import apps.app_atzum.AtzumCreator;
import apps.app_atzum.AtzumTerra;
import apps.app_atzum.utils.AtzumCreatorInfo;
import apps.app_atzum.utils.AtzumPontosInteiro;
import apps.app_atzum.utils.Rasterizador;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.geometria.Ponto;
import libs.azzal.utilitarios.Cor;
import libs.imagem.Imagem;
import libs.luan.*;
import libs.meta_functional.Acao;

public class ServicoTectonico {

    public static void INIT() {
        AtzumCreatorInfo.iniciar("ServicoTectonico.INIT");

        INICIAR_PLACAS();
        EXTRAIR_PLACAS_TECTONICAS_CONTORNOS();

        CRIAR_PLACAS_EXPANDIDAS();


        AtzumCreatorInfo.terminar("ServicoTectonico.INIT");
        AtzumCreatorInfo.exibir_item("ServicoTectonico.INIT");
    }

    public static void AJUSTAR(){

        Cores mCores = new Cores();

        Renderizador render_tronarko_placas_tectonicas_limites = Renderizador.ABRIR_DE_ARQUIVO_RGB(AtzumCreator.LOCAL_GET_ARQUIVO("build/tectonico/tronarko_tectonico_limites.png"));

        // EXPANDIR LIMITES
        for (int y = 0; y < render_tronarko_placas_tectonicas_limites.getAltura(); y++) {
            for (int x = 0; x < render_tronarko_placas_tectonicas_limites.getLargura(); x++) {
                if (render_tronarko_placas_tectonicas_limites.getPixel(x, y).igual(mCores.getVermelho())||render_tronarko_placas_tectonicas_limites.getPixel(x, y).igual(mCores.getPreto())) {

                    Cor mais_proxima = Rasterizador.GET_COR_DO_QUADRANTE_DIFERENTE(render_tronarko_placas_tectonicas_limites, x, y, 50, mCores.getVermelho(), mCores.getPreto());
                    render_tronarko_placas_tectonicas_limites.setPixel(x, y, mais_proxima);

                }
            }
        }

        Imagem.exportar(render_tronarko_placas_tectonicas_limites.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/tectonico/tronarko_tectonico_limites_v2.png"));


    }


    public static Lista<Par<Ponto, Cor>> GET_PLACAS_TECTONICAS() {

        Cores mCores = new Cores();
        Lista<Par<Ponto, Cor>> placas_tectonicas = new Lista<Par<Ponto, Cor>>();

        placas_tectonicas.adicionar(new Par<Ponto, Cor>(new Ponto(500, 500), Cor.getHexCor("#FDD835")));
        placas_tectonicas.adicionar(new Par<Ponto, Cor>(new Ponto(800, 500), Cor.getHexCor("#43A047")));
        placas_tectonicas.adicionar(new Par<Ponto, Cor>(new Ponto(500, 1200), Cor.getHexCor("#D81B60")));

        placas_tectonicas.adicionar(new Par<Ponto, Cor>(new Ponto(100, 700), Cor.getHexCor("#1976D2")));
        placas_tectonicas.adicionar(new Par<Ponto, Cor>(new Ponto(2200, 700), Cor.getHexCor("#1976D2")));

        placas_tectonicas.adicionar(new Par<Ponto, Cor>(new Ponto(500, 1500), Cor.getHexCor("#512DA8")));
        placas_tectonicas.adicionar(new Par<Ponto, Cor>(new Ponto(2000, 900), Cor.getHexCor("#9E9D24")));
        //   placas_tectonicas.adicionar(new Par<Ponto,Cor>(new Ponto(500,50),mCores.getMarrom()));

        placas_tectonicas.adicionar(new Par<Ponto, Cor>(new Ponto(2080, 600), Cor.getHexCor("#FF8F00")));
        placas_tectonicas.adicionar(new Par<Ponto, Cor>(new Ponto(1900, 250), Cor.getHexCor("#D32F2F")));

        return placas_tectonicas;
    }

    public static void INICIAR_PLACAS() {
        AtzumCreatorInfo.iniciar("ServicoTectonico.INICIAR_PLACAS");

        fmt.print("Tectonismo");

        Cores mCores = new Cores();

        Renderizador render_tronarko = new Renderizador(AtzumCreator.GET_MAPA_PRETO_E_BRANCO());
        Renderizador render_tronarko_placas_tectonicas_limites = Renderizador.CONSTRUIR(render_tronarko.getLargura(), render_tronarko.getAltura(), mCores.getPreto());


        AtzumTerra planeta = new AtzumTerra();

        String LOCAL = "/home/luan/Imagens/atzum/parametros/";
        String ARQUIVO_NOME = LOCAL + "PLACAS_TECTONICAS.dkg";

        Unico<Par<Ponto, Integer>> eixos = new Unico<Par<Ponto, Integer>>(AtzumPontosInteiro.PAR_PONTO_INTEGER_IGUALAVEL());
        for (Par<Ponto, Integer> p : AtzumPontosInteiro.ABRIR(ARQUIVO_NOME)) {
            eixos.item(p);
        }

        Lista<Ponto> pontos = new Lista<Ponto>();

        for (Par<Ponto, Integer> eixo : eixos) {
            pontos.adicionar(eixo.getChave());
        }

        fmt.print("Quantidade : {}", pontos.getQuantidade());


        for (Ponto pt_eixo : pontos) {

            render_tronarko.drawCirculoCentralizado_Pintado(pt_eixo, 10, mCores.getAmarelo());


            Opcional<Ponto> proximo = Espaco2D.GET_MAIS_PROXIMO(pt_eixo, pontos);

            if (proximo.isOK()) {
                double dist = Espaco2D.distancia_entre_pontos(pt_eixo, proximo.get());
                if (dist < 200) {
                    render_tronarko.drawLinha(pt_eixo, proximo.get(), mCores.getVerde());

                    for (Ponto o : GPS.criarRota(pt_eixo, proximo.get())) {
                        render_tronarko_placas_tectonicas_limites.drawRect_Pintado(o.getX(), o.getY(), 20, 20, mCores.getVermelho());
                    }
                }
            }

            Opcional<Ponto> proximo2 = Espaco2D.GET_SEGUNDO_PROXIMO(pt_eixo, pontos);

            if (proximo2.isOK()) {
                double dist = Espaco2D.distancia_entre_pontos(pt_eixo, proximo2.get());
                if (dist < 200) {
                    render_tronarko.drawLinha(pt_eixo, proximo2.get(), mCores.getVermelho());

                    for (Ponto o : GPS.criarRota(pt_eixo, proximo2.get())) {
                        render_tronarko_placas_tectonicas_limites.drawRect_Pintado(o.getX(), o.getY(), 20, 20, mCores.getVermelho());
                    }
                }
            }


        }


        Imagem.exportar(render_tronarko.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/tectonico/tronarko_tectonico.png"));


        render_tronarko_placas_tectonicas_limites.drawRect_Pintado(0, 420, 50, 50, mCores.getVermelho());
        render_tronarko_placas_tectonicas_limites.drawRect_Pintado(0, 1050, 50, 50, mCores.getVermelho());


        Lista<Par<Ponto, Cor>> placas_tectonicas = GET_PLACAS_TECTONICAS();

        for (Par<Ponto, Cor> placa_tectonica : placas_tectonicas) {
            //  render_tronarko_placas_tectonicas_limites.drawRect_Pintado(placa_tectonica.getChave().getX(),placa_tectonica.getChave().getY(),50,50,placa_tectonica.getValor());

            RefInt processante = new RefInt(0);

            Acao durante_mudanca = new Acao() {
                @Override
                public void fazer() {
                }
            };

            Acao a_cada_100 = new Acao() {
                @Override
                public void fazer() {

                    // Imagem.exportar(render.toImagemSemAlfa(), AtzumCreator.PROCESSANDO_GET_ARQUIVO("processando_" + fmt.zerado(processante.get(), 4) + ".png"));
                    processante.set(processante.get() + 1);
                    Imagem.exportar(render_tronarko_placas_tectonicas_limites.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/processando.png"));

                }
            };


            Rasterizador.RASTERIZAR_COM(render_tronarko_placas_tectonicas_limites, placa_tectonica.getChave().getX(), placa_tectonica.getChave().getY(), mCores.getPreto(), placa_tectonica.getValor(), durante_mudanca, a_cada_100);

            Imagem.exportar(render_tronarko_placas_tectonicas_limites.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/tectonico/tronarko_tectonico_limites_v1.png"));

        }


        Rasterizador.trocar_cores(render_tronarko_placas_tectonicas_limites, mCores.getPreto(), mCores.getVermelho());

        Imagem.exportar(render_tronarko_placas_tectonicas_limites.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/tectonico/tronarko_tectonico_limites_v1.png"));


        // EXPANDIR LIMITES
        for (int y = 0; y < render_tronarko_placas_tectonicas_limites.getAltura(); y++) {
            for (int x = 0; x < render_tronarko_placas_tectonicas_limites.getLargura(); x++) {
                if (render_tronarko_placas_tectonicas_limites.getPixel(x, y).igual(mCores.getVermelho())) {

                    Cor mais_proxima = Rasterizador.GET_COR_DO_QUADRANTE_DIFERENTE(render_tronarko_placas_tectonicas_limites, x, y, 50, mCores.getVermelho(), mCores.getPreto());
                    render_tronarko_placas_tectonicas_limites.setPixel(x, y, mais_proxima);

                }
            }
        }

        Imagem.exportar(render_tronarko_placas_tectonicas_limites.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/tectonico/tronarko_tectonico_limites_v2.png"));


        AtzumCreatorInfo.terminar("ServicoTectonico.INICIAR_PLACAS");
        AtzumCreatorInfo.exibir_item("ServicoTectonico.INICIAR_PLACAS");
    }


    public static void EXTRAIR_PLACAS_TECTONICAS_CONTORNOS() {

        AtzumCreatorInfo.iniciar("ServicoTectonico.EXTRAIR_CONTORNOS");

        Renderizador render = Renderizador.ABRIR_DE_ARQUIVO_RGB(AtzumCreator.LOCAL_GET_ARQUIVO("build/tectonico/tronarko_tectonico_limites_v2.png"));

        Cores mCores = new Cores();


        Renderizador render_salvar = Renderizador.CONSTRUIR(render.getLargura(), render.getAltura(), mCores.getPreto());


        for (int y = 0; y < render.getAltura(); y++) {
            for (int x = 0; x < render.getLargura(); x++) {

                Cor ponto_a = render.getPixel(x, y);

                if (render.isPontoValido(x + 1, y)) {

                    Cor ponto_b = render.getPixel(x + 1, y);

                    if (ponto_a.isDiferente(ponto_b)) {
                        render_salvar.setPixel(x, y, mCores.getVermelho());
                    }
                }
                if (render.isPontoValido(x - 1, y)) {

                    Cor ponto_b = render.getPixel(x - 1, y);

                    if (ponto_a.isDiferente(ponto_b)) {
                        render_salvar.setPixel(x, y, mCores.getVermelho());
                    }
                }
                if (render.isPontoValido(x, y + 1)) {

                    Cor ponto_b = render.getPixel(x, y + 1);

                    if (ponto_a.isDiferente(ponto_b)) {
                        render_salvar.setPixel(x, y, mCores.getVermelho());
                    }
                }
                if (render.isPontoValido(x, y - 1)) {

                    Cor ponto_b = render.getPixel(x, y - 1);

                    if (ponto_a.isDiferente(ponto_b)) {
                        render_salvar.setPixel(x, y, mCores.getVermelho());
                    }
                }

                // DIAGONAL
                if (render.isPontoValido(x + 1, y + 1)) {

                    Cor ponto_b = render.getPixel(x + 1, y + 1);

                    if (ponto_a.isDiferente(ponto_b)) {
                        render_salvar.setPixel(x, y, mCores.getVermelho());
                    }
                }

                if (render.isPontoValido(x + 1, y - 1)) {

                    Cor ponto_b = render.getPixel(x + 1, y - 1);

                    if (ponto_a.isDiferente(ponto_b)) {
                        render_salvar.setPixel(x, y, mCores.getVermelho());
                    }
                }

                if (render.isPontoValido(x - 1, y + 1)) {

                    Cor ponto_b = render.getPixel(x - 1, y + 1);

                    if (ponto_a.isDiferente(ponto_b)) {
                        render_salvar.setPixel(x, y, mCores.getVermelho());
                    }
                }

                if (render.isPontoValido(x - 1, -+1)) {

                    Cor ponto_b = render.getPixel(x - 1, y - 1);

                    if (ponto_a.isDiferente(ponto_b)) {
                        render_salvar.setPixel(x, y, mCores.getVermelho());
                    }
                }

            }
        }

        Imagem.exportar(render_salvar.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/tectonico/tronarko_tectonico_limites_definidos.png"));

        AtzumCreatorInfo.terminar("ServicoTectonico.EXTRAIR_CONTORNOS");
        AtzumCreatorInfo.exibir_item("ServicoTectonico.EXTRAIR_CONTORNOS");

    }

    public static void CRIAR_PLACAS_EXPANDIDAS(){

        AtzumCreatorInfo.iniciar("ServicoTectonico.CRIAR_PLACAS_EXPANDIDAS");

        Renderizador render = Renderizador.ABRIR_DE_ARQUIVO_RGB(AtzumCreator.LOCAL_GET_ARQUIVO("build/tectonico/tronarko_tectonico_limites_definidos.png"));

        Cores mCores = new Cores();


        Lista<Par<Ponto, Cor>> placas_tectonicas = GET_PLACAS_TECTONICAS();

        for (Par<Ponto, Cor> placa_tectonica : placas_tectonicas) {
            //  render_tronarko_placas_tectonicas_limites.drawRect_Pintado(placa_tectonica.getChave().getX(),placa_tectonica.getChave().getY(),50,50,placa_tectonica.getValor());

            RefInt processante = new RefInt(0);

            Acao durante_mudanca = new Acao() {
                @Override
                public void fazer() {
                    Imagem.exportar(render.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/processando.png"));
                }
            };

            Acao a_cada_100 = new Acao() {
                @Override
                public void fazer() {

                    // Imagem.exportar(render.toImagemSemAlfa(), AtzumCreator.PROCESSANDO_GET_ARQUIVO("processando_" + fmt.zerado(processante.get(), 4) + ".png"));
                    processante.set(processante.get() + 1);

                }
            };


            Rasterizador.RASTERIZAR_COM(render, placa_tectonica.getChave().getX(), placa_tectonica.getChave().getY(), mCores.getPreto(), placa_tectonica.getValor(), durante_mudanca, a_cada_100);

            Imagem.exportar(render.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/tectonico/tronarko_tectonico_placas.png"));

        }

        Imagem.exportar(render.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/tectonico/tronarko_tectonico_placas.png"));

        AtzumCreatorInfo.terminar("ServicoTectonico.CRIAR_PLACAS_EXPANDIDAS");
        AtzumCreatorInfo.exibir_item("ServicoTectonico.CRIAR_PLACAS_EXPANDIDAS");
    }

}
