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
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.imagem.Imagem;
import libs.luan.*;
import libs.meta_functional.Acao;

public class ServicoTectonico {

    public static void INIT() {
        AtzumCreatorInfo.iniciar("ServicoTectonico.INIT");

        //  INICIAR_PLACAS();
        // EXTRAIR_PLACAS_TECTONICAS_CONTORNOS();

        // CRIAR_PLACAS_COM_LIMITES();

        DEFINIR_AREAS_DE_ATIVIDADE_SISMICA();
        VULCANIZAR();
        VULCANIZAR_TERRA_OU_AGUA();

        AtzumCreatorInfo.terminar("ServicoTectonico.INIT");
        AtzumCreatorInfo.exibir_item("ServicoTectonico.INIT");
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


        Imagem.exportar(render_tronarko.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/tectonico/atzum_tectonismo_eixos.png"));


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

            Imagem.exportar(render_tronarko_placas_tectonicas_limites.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/tectonico/atzum_tectonismo_placas_v0.png"));

        }


        Rasterizador.trocar_cores(render_tronarko_placas_tectonicas_limites, mCores.getPreto(), mCores.getVermelho());

        Imagem.exportar(render_tronarko_placas_tectonicas_limites.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/tectonico/atzum_tectonismo_placas_v0.png"));


        // EXPANDIR LIMITES
        for (int y = 0; y < render_tronarko_placas_tectonicas_limites.getAltura(); y++) {
            for (int x = 0; x < render_tronarko_placas_tectonicas_limites.getLargura(); x++) {
                if (render_tronarko_placas_tectonicas_limites.getPixel(x, y).igual(mCores.getVermelho())) {

                    Cor mais_proxima = Rasterizador.GET_COR_DO_QUADRANTE_DIFERENTE(render_tronarko_placas_tectonicas_limites, x, y, 50, mCores.getVermelho(), mCores.getPreto());
                    render_tronarko_placas_tectonicas_limites.setPixel(x, y, mais_proxima);

                }
            }
        }

        Imagem.exportar(render_tronarko_placas_tectonicas_limites.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/tectonico/atzum_tectonismo_placas.png"));


        AtzumCreatorInfo.terminar("ServicoTectonico.INICIAR_PLACAS");
        AtzumCreatorInfo.exibir_item("ServicoTectonico.INICIAR_PLACAS");
    }


    public static void EXTRAIR_PLACAS_TECTONICAS_CONTORNOS() {

        AtzumCreatorInfo.iniciar("ServicoTectonico.EXTRAIR_CONTORNOS");

        Renderizador render = Renderizador.ABRIR_DE_ARQUIVO_RGB(AtzumCreator.LOCAL_GET_ARQUIVO("build/tectonico/atzum_tectonismo_placas.png"));

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

        Imagem.exportar(render_salvar.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/tectonico/atzum_tectonismo_limites.png"));

        AtzumCreatorInfo.terminar("ServicoTectonico.EXTRAIR_CONTORNOS");
        AtzumCreatorInfo.exibir_item("ServicoTectonico.EXTRAIR_CONTORNOS");

    }

    public static void CRIAR_PLACAS_COM_LIMITES() {

        AtzumCreatorInfo.iniciar("ServicoTectonico.CRIAR_PLACAS_COM_LIMITES");

        Renderizador render = Renderizador.ABRIR_DE_ARQUIVO_RGB(AtzumCreator.LOCAL_GET_ARQUIVO("build/tectonico/atzum_tectonismo_limites.png"));

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

            Imagem.exportar(render.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/tectonico/atzum_tectonismo_placas_com_limites.png"));

        }

        Imagem.exportar(render.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/tectonico/atzum_tectonismo_placas_com_limites.png"));

        AtzumCreatorInfo.terminar("ServicoTectonico.CRIAR_PLACAS_COM_LIMITES");
        AtzumCreatorInfo.exibir_item("ServicoTectonico.CRIAR_PLACAS_COM_LIMITES");
    }


    public static void DEFINIR_AREAS_DE_ATIVIDADE_SISMICA() {

        AtzumCreatorInfo.iniciar("ServicoTectonico.DEFINIR_AREAS_DE_ATIVIDADE_SISMICA");

        Renderizador render = Renderizador.ABRIR_DE_ARQUIVO_RGB(AtzumCreator.LOCAL_GET_ARQUIVO("build/tectonico/atzum_tectonismo_limites.png"));
        Renderizador render_atividade_sismica = Renderizador.ABRIR_DE_ARQUIVO_RGB(AtzumCreator.LOCAL_GET_ARQUIVO("build/tectonico/atzum_tectonismo_limites.png"));

        Cores mCores = new Cores();

        int x_mudanca = Aleatorio.aleatorio_entre(50, 200);
        int x_contagem = 0;
        int x_aumentar = Aleatorio.aleatorio_entre(5, 20);

        for (int y = 0; y < render.getAltura(); y++) {
            x_contagem += 1;
            if (x_contagem == x_mudanca) {
                x_contagem = 0;
                x_mudanca = Aleatorio.aleatorio_entre(50, 200);
                x_aumentar = Aleatorio.aleatorio_entre(5, 20);
            }
            for (int x = 0; x < render.getLargura(); x++) {
                if (render.getPixel(x, y).igual(mCores.getVermelho())) {

                    for (int a = (x - 30 - x_aumentar); a < (x + 30 + x_aumentar); a++) {
                        render_atividade_sismica.setPixel(a, y, mCores.getAmarelo());
                    }

                }

            }
        }

        int y_mudanca = Aleatorio.aleatorio_entre(50, 200);
        int y_contagem = 0;
        int y_aumentar = Aleatorio.aleatorio_entre(5, 20);


        for (int y = 0; y < render.getAltura(); y++) {
            for (int x = 0; x < render.getLargura(); x++) {

                y_contagem += 1;
                if (y_contagem == y_mudanca) {
                    y_contagem = 0;
                    y_mudanca = Aleatorio.aleatorio_entre(50, 200);
                    y_aumentar = Aleatorio.aleatorio_entre(5, 20);
                }
                if (render.getPixel(x, y).igual(mCores.getVermelho())) {

                    for (int a = (y - 30 - y_aumentar); a < (y + 30 + y_aumentar); a++) {
                        render_atividade_sismica.setPixel(x, a, mCores.getAmarelo());
                    }

                }

            }
        }

        for (int y = 0; y < render.getAltura(); y++) {
            for (int x = 0; x < render.getLargura(); x++) {
                if (render.getPixel(x, y).igual(mCores.getVermelho())) {
                    render_atividade_sismica.setPixel(x, y, mCores.getAzul());
                }
            }
        }

        Imagem.exportar(render_atividade_sismica.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/tectonico/atzum_tectonismo_atividade_sismica.png"));

        AtzumCreatorInfo.terminar("ServicoTectonico.DEFINIR_AREAS_DE_ATIVIDADE_SISMICA");
        AtzumCreatorInfo.exibir_item("ServicoTectonico.DEFINIR_AREAS_DE_ATIVIDADE_SISMICA");
    }


    public static void VULCANIZAR() {

        AtzumCreatorInfo.iniciar("ServicoTectonico.VULCANIZAR");

        Renderizador render = Renderizador.ABRIR_DE_ARQUIVO_RGB(AtzumCreator.LOCAL_GET_ARQUIVO("build/tectonico/atzum_tectonismo_atividade_sismica.png"));
        Renderizador render_vulcanismo = Renderizador.ABRIR_DE_ARQUIVO_RGB(AtzumCreator.LOCAL_GET_ARQUIVO("build/tectonico/atzum_tectonismo_atividade_sismica.png"));

        Cores mCores = new Cores();

        Lista<Entidade> vulcoes = ENTT.CRIAR_LISTA();


        int vulcanizando_contagem = 0;
        int vulcanizando_mudanca = Aleatorio.aleatorio_entre(100, 300);


        for (int y = 0; y < render.getAltura(); y++) {
            for (int x = 0; x < render.getLargura(); x++) {
                if (render.getPixel(x, y).igual(mCores.getAzul())) {

                    vulcanizando_contagem += 1;
                    if (vulcanizando_contagem == vulcanizando_mudanca) {
                        vulcanizando_contagem = 0;
                        vulcanizando_mudanca = Aleatorio.aleatorio_entre(100, 300);

                        int vulcao_x = x - Aleatorio.aleatorio_entre_positivo_ou_negativo(10, 30);
                        int vulcao_y = y + Aleatorio.aleatorio_entre_positivo_ou_negativo(10, 30);

                        if (render.getPixel(vulcao_x, vulcao_y).igual(mCores.getAmarelo()) || render.getPixel(vulcao_x, vulcao_y).igual(mCores.getAzul())) {
                            render_vulcanismo.drawCirculoCentralizado_Pintado(vulcao_x, vulcao_y, 10, mCores.getVermelho());

                            Entidade e_vulcao = ENTT.CRIAR_EM_SEQUENCIALMENTE(vulcoes, "VID");
                            e_vulcao.at("X", vulcao_x);
                            e_vulcao.at("Y", vulcao_y);
                            e_vulcao.at("Sismicidade", Aleatorio.aleatorio_entre(500, 6000));
                            e_vulcao.at("Dormencia", e_vulcao.atInt("Sismicidade") + Aleatorio.aleatorio_entre(50, 100));
                            e_vulcao.at("Nivel", Aleatorio.aleatorio_entre(1, 12));


                        }


                    }

                }
            }
        }


        Imagem.exportar(render_vulcanismo.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/tectonico/atzum_vulcanismo.png"));

        ENTT.GUARDAR(vulcoes, AtzumCreator.LOCAL_GET_ARQUIVO("dados/vulcanismo.entts"));


        ENTT.EXIBIR_TABELA_COM_TITULO(vulcoes, "VULCÕES");

        AtzumCreatorInfo.terminar("ServicoTectonico.VULCANIZAR");
        AtzumCreatorInfo.exibir_item("ServicoTectonico.VULCANIZAR");
    }

    public static void VULCANIZAR_TERRA_OU_AGUA() {

        AtzumCreatorInfo.iniciar("ServicoTectonico.VULCANIZAR_TERRA_OU_AGUA");

        Renderizador render_vulcoes = new Renderizador(AtzumCreator.GET_MAPA_PRETO_E_BRANCO());

        Cores mCores = new Cores();

        Lista<Entidade> vulcoes = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO("dados/vulcanismo.entts"));

        for (Entidade vulcao : vulcoes) {

            int vulcao_x = vulcao.atInt("X");
            int vulcao_y = vulcao.atInt("Y");

            if (render_vulcoes.getPixel(vulcao_x, vulcao_y).igual(mCores.getPreto())) {

                vulcao.at("Tipo", "Aquatico");
                render_vulcoes.drawCirculoCentralizado_Pintado(vulcao_x, vulcao_y, 10, mCores.getAzul());

            }else{

                vulcao.at("Tipo", "Terreste");
                render_vulcoes.drawCirculoCentralizado_Pintado(vulcao_x, vulcao_y, 10, mCores.getVermelho());

            }

        }



        Imagem.exportar(render_vulcoes.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/tectonico/atzum_vulcoes.png"));

        ENTT.GUARDAR(vulcoes,AtzumCreator.LOCAL_GET_ARQUIVO("dados/vulcanismo.entts"));

        ENTT.EXIBIR_TABELA_COM_TITULO(vulcoes, "VULCÕES");

        AtzumCreatorInfo.terminar("ServicoTectonico.VULCANIZAR_TERRA_OU_AGUA");
        AtzumCreatorInfo.exibir_item("ServicoTectonico.VULCANIZAR_TERRA_OU_AGUA");
    }

}
