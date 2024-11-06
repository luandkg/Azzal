package apps.app_atzum.servicos;

import apps.app_attuz.Ferramentas.Espaco2D;
import apps.app_attuz.Ferramentas.GPS;
import apps.app_atzum.AtzumCreator;
import apps.app_atzum.AtzumTerra;
import apps.app_atzum.VideoRasterizar;
import apps.app_atzum.utils.AtzumCreatorInfo;
import apps.app_atzum.utils.Rasterizador;
import libs.arquivos.QTT;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.geometria.Ponto;
import libs.azzal.utilitarios.Cor;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.imagem.Imagem;
import libs.luan.*;
import libs.meta_functional.Acao;

import java.util.ArrayList;

public class ServicoTectonico {

    public static void INIT() {
        AtzumCreatorInfo.iniciar("ServicoTectonico.INIT");

        fmt.print("Tectonismo");

        Cores mCores = new Cores();

        Renderizador render_tronarko = new Renderizador(AtzumCreator.GET_MAPA_PRETO_E_BRANCO());
        Renderizador render_tronarko_placas_tectonicas_limites = Renderizador.CONSTRUIR(render_tronarko.getLargura(), render_tronarko.getAltura(), mCores.getPreto());


        AtzumTerra planeta = new AtzumTerra();

        QTT dados_relevo = QTT.getTudo(AtzumCreator.DADOS_GET_ARQUIVO("relevo.qtt"));

        Unico<Ponto> pontos_marcados_terra = new Unico<Ponto>(Ponto.IGUAL());
        Unico<Ponto> pontos_marcados_oceano = new Unico<Ponto>(Ponto.IGUAL());

        for (int y = 0; y < planeta.getAltura(); y++) {

            int maior_altitude_terra = 0;
            boolean tem_altitude_terra = false;

            int maior_altitude_oceano = 0;
            boolean tem_altitude_oceano = false;


            for (int x = 0; x < planeta.getLargura(); x++) {
                if (planeta.isTerra(x, y)) {
                    int altitude = dados_relevo.getValor(x, y);
                    if (tem_altitude_terra) {
                        if (altitude > maior_altitude_terra) {
                            maior_altitude_terra = altitude;
                        }
                    } else {
                        maior_altitude_terra = altitude;
                        tem_altitude_terra = true;
                    }
                } else {

                    int altitude = dados_relevo.getValor(x, y);
                    if (tem_altitude_oceano) {
                        if (altitude < maior_altitude_oceano) {
                            maior_altitude_oceano = altitude;
                        }
                    } else {
                        maior_altitude_oceano = altitude;
                        tem_altitude_oceano = true;
                    }

                }

            }

            if (tem_altitude_terra) {
                for (int x = 0; x < planeta.getLargura(); x++) {
                    if (planeta.isTerra(x, y)) {
                        int altitude = dados_relevo.getValor(x, y);
                        if (maior_altitude_terra == altitude) {
                            render_tronarko.setPixel(x, y, mCores.getVermelho());
                            pontos_marcados_terra.item(new Ponto(x, y));
                        }
                    }
                }
            }

            if (tem_altitude_oceano) {
                for (int x = 0; x < planeta.getLargura(); x++) {
                    if (planeta.isOceano(x, y)) {
                        int altitude = dados_relevo.getValor(x, y);
                        if (maior_altitude_oceano == altitude) {
                            render_tronarko.setPixel(x, y, mCores.getAzul());
                            pontos_marcados_oceano.item(new Ponto(x, y));
                        }
                    }
                }

            }


        }

        fmt.print("Pontos marcados : {}", pontos_marcados_terra.getQuantidade());

        for (int x = 0; x < planeta.getLargura(); x++) {

            int maior_altitude_terra = 0;
            boolean tem_altitude_terra = false;

            int maior_altitude_oceano = 0;
            boolean tem_altitude_oceano = false;

            for (int y = 0; y < planeta.getAltura(); y++) {
                if (planeta.isTerra(x, y)) {
                    int altitude = dados_relevo.getValor(x, y);
                    if (tem_altitude_terra) {
                        if (altitude > maior_altitude_terra) {
                            maior_altitude_terra = altitude;
                        }
                    } else {
                        maior_altitude_terra = altitude;
                        tem_altitude_terra = true;
                    }
                } else {
                    int altitude = dados_relevo.getValor(x, y);
                    if (tem_altitude_oceano) {
                        if (altitude < maior_altitude_oceano) {
                            maior_altitude_oceano = altitude;
                        }
                    } else {
                        maior_altitude_oceano = altitude;
                        tem_altitude_oceano = true;
                    }
                }

            }

            if (tem_altitude_terra) {
                for (int y = 0; y < planeta.getAltura(); y++) {
                    if (planeta.isTerra(x, y)) {
                        int altitude = dados_relevo.getValor(x, y);
                        if (maior_altitude_terra == altitude) {
                            render_tronarko.setPixel(x, y, mCores.getVermelho());
                            pontos_marcados_terra.item(new Ponto(x, y));
                        }
                    }
                }
            }

            if (tem_altitude_oceano) {
                for (int y = 0; y < planeta.getAltura(); y++) {
                    if (planeta.isOceano(x, y)) {
                        int altitude = dados_relevo.getValor(x, y);
                        if (maior_altitude_oceano == altitude) {
                            render_tronarko.setPixel(x, y, mCores.getAzul());
                            pontos_marcados_oceano.item(new Ponto(x, y));
                        }
                    }
                }
            }

        }

        fmt.print("Pontos marcados terra  : {}", pontos_marcados_terra.getQuantidade());
        fmt.print("Pontos marcados oceano : {}", pontos_marcados_oceano.getQuantidade());


        Lista<Ponto> pontos_definidos_terra = pontos_marcados_terra.toLista().getCopia();

        Lista<Ponto> pontos_escolhidos = new Lista<Ponto>();

        int terra_escolhidos = Aleatorio.aleatorio_entre(30, 50);
        int oceano_escolhidos = Aleatorio.aleatorio_entre(30, 50);


        for (int marcar = 0; marcar < terra_escolhidos; marcar++) {

            Ponto p1 = Aleatorio.escolha_um(pontos_definidos_terra);
            pontos_definidos_terra.remover(p1);
            render_tronarko.drawCirculoCentralizado_Pintado(p1.getX(), p1.getY(), 10, mCores.getVerde());
            //  render_tronarko.drawCirculoCentralizado(p1.getX(), p1.getY(), 100, mCores.getVerde());
            //  render_tronarko.drawCirculoCentralizado(p1.getX(), p1.getY(), 300, mCores.getVermelho());

            pontos_escolhidos.adicionar(p1);
        }

        Lista<Ponto> pontos_definidos_oceano = pontos_marcados_oceano.toLista().getCopia();

        for (int marcar = 0; marcar < oceano_escolhidos; marcar++) {

            Ponto p1 = Aleatorio.escolha_um(pontos_definidos_oceano);
            pontos_definidos_oceano.remover(p1);
            render_tronarko.drawCirculoCentralizado_Pintado(p1.getX(), p1.getY(), 10, mCores.getCinza());
            //  render_tronarko.drawCirculoCentralizado(p1.getX(), p1.getY(), 100, mCores.getCinza());
            // render_tronarko.drawCirculoCentralizado(p1.getX(), p1.getY(), 300, mCores.getVermelho());

            pontos_escolhidos.adicionar(p1);

        }

        Lista<Ponto> pontos_escolhidos_v2 = new Lista<Ponto>();

        int terra_escolhidos_v2 = Aleatorio.aleatorio_entre(30, 50);
        int oceano_escolhidos_v2 = Aleatorio.aleatorio_entre(30, 50);


        for (int marcar = 0; marcar < terra_escolhidos_v2; marcar++) {

            Ponto p1 = Aleatorio.escolha_um(pontos_definidos_terra);
            pontos_definidos_terra.remover(p1);
            render_tronarko.drawCirculoCentralizado_Pintado(p1.getX(), p1.getY(), 10, mCores.getVerde());
            //  render_tronarko.drawCirculoCentralizado(p1.getX(), p1.getY(), 100, mCores.getVerde());
            //  render_tronarko.drawCirculoCentralizado(p1.getX(), p1.getY(), 300, mCores.getVermelho());

            pontos_escolhidos_v2.adicionar(p1);
        }

        for (int marcar = 0; marcar < oceano_escolhidos_v2; marcar++) {

            Ponto p1 = Aleatorio.escolha_um(pontos_definidos_oceano);
            pontos_definidos_oceano.remover(p1);
            render_tronarko.drawCirculoCentralizado_Pintado(p1.getX(), p1.getY(), 10, mCores.getCinza());
            //  render_tronarko.drawCirculoCentralizado(p1.getX(), p1.getY(), 100, mCores.getCinza());
            // render_tronarko.drawCirculoCentralizado(p1.getX(), p1.getY(), 300, mCores.getVermelho());

            pontos_escolhidos_v2.adicionar(p1);

        }

        // RENDER

        for (Ponto p1 : pontos_escolhidos) {

            render_tronarko.drawCirculoCentralizado_Pintado(p1.getX(), p1.getY(), 10, mCores.getRosa());

            Lista<Ponto> ao_redor = new Lista<Ponto>();

            for (Ponto p2 : pontos_escolhidos) {
                if (Espaco2D.distancia_entre_pontos(p1, p2) >= 300 && Espaco2D.distancia_entre_pontos(p1, p2) < 500) {
                    int diff_x = Matematica.POSITIVO(p1.getX() - p2.getX());
                    int diff_y = Matematica.POSITIVO(p1.getY() - p2.getY());

                    if (diff_x > 100 && diff_y > 100) {
                        ao_redor.adicionar(p2);
                    }
                }
            }

            if (ao_redor.possuiObjetos()) {

                Ponto sorteado = Aleatorio.escolha_um(ao_redor);
                ArrayList<Ponto> rota = GPS.criarRota(p1, sorteado);

                for (Ponto p2 : rota) {
                    //  render_tronarko.drawCirculoCentralizado_Pintado(p2.getX(), p2.getY(), 3, mCores.getTurquesa());
                }

            }


        }


        Ponto iniciar = Lista.PRIMEIRO(pontos_escolhidos);

        while (pontos_escolhidos.possuiObjetos()) {
            pontos_escolhidos.remover(iniciar);


            Opcional<Ponto> mais_proximo = Espaco2D.GET_MAIS_PROXIMO(iniciar, pontos_escolhidos);

            if (mais_proximo.isOK()) {

                ArrayList<Ponto> rota = GPS.criarRota(iniciar, mais_proximo.get());

                for (Ponto p2 : rota) {
                    render_tronarko.drawCirculoCentralizado_Pintado(p2.getX(), p2.getY(), 3, mCores.getTurquesa());
                    render_tronarko_placas_tectonicas_limites.drawRect_Pintado(p2.getX(), p2.getY(), 3, 3, mCores.getTurquesa());
                }

                iniciar = mais_proximo.get();
            } else {
                break;
            }

        }

        iniciar = Lista.PRIMEIRO(pontos_escolhidos_v2);

        while (pontos_escolhidos_v2.possuiObjetos()) {
            pontos_escolhidos_v2.remover(iniciar);


            Opcional<Ponto> mais_proximo = Espaco2D.GET_MAIS_PROXIMO(iniciar, pontos_escolhidos_v2);

            if (mais_proximo.isOK()) {

                ArrayList<Ponto> rota = GPS.criarRota(iniciar, mais_proximo.get());

                for (Ponto p2 : rota) {
                    render_tronarko.drawCirculoCentralizado_Pintado(p2.getX(), p2.getY(), 3, mCores.getVerde());
                    render_tronarko_placas_tectonicas_limites.drawRect_Pintado(p2.getX(), p2.getY(), 3, 3, mCores.getTurquesa());
                }

                iniciar = mais_proximo.get();
            } else {
                break;
            }

        }


        Imagem.exportar(render_tronarko.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/tectonico/tronarko_tectonico.png"));
        Imagem.exportar(render_tronarko_placas_tectonicas_limites.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/tectonico/tronarko_tectonico_limites.png"));


        AtzumCreatorInfo.terminar("ServicoTectonico.INIT");
        AtzumCreatorInfo.exibir_item("ServicoTectonico.INIT");
    }

    public static void RASTERIZAR() {
        AtzumCreatorInfo.iniciar("ServicoTectonico.RASTERIZAR");

        fmt.print("Tectonismo :: Organizar Placas");

        Cores mCores = new Cores();

        Renderizador render_tronarko = new Renderizador(Imagem.GET_IMAGEM_POR_PIXEL_RGB(AtzumCreator.LOCAL_GET_ARQUIVO("build/tectonico/tronarko_tectonico_limites.png")));

        String arquivo_video_tectonismo = AtzumCreator.LOCAL_GET_ARQUIVO("videos/atzum_tectonismo.vi");
        String arquivo_video_tectonismo_processando = AtzumCreator.LOCAL_GET_ARQUIVO("build/processando.png");

        VideoRasterizar video = new VideoRasterizar(arquivo_video_tectonismo, render_tronarko);
        video.debug(arquivo_video_tectonismo_processando);


        boolean tem_preto = true;

        int placas = 0;


        EmLoop<Cor> qual_cor = new EmLoop<Cor>(Lista.CRIAR(Cor.getHexCor("#1565C0"), Cor.getHexCor("#0277BD"), Cor.getHexCor("#00838F"), Cor.getHexCor("#2E7D32"), Cor.getHexCor("#558B2F"), Cor.getHexCor("#F9A825"), Cor.getHexCor("#FF8F00"), Cor.getHexCor("#EF6C00"), Cor.getHexCor("#D84315"), Cor.getHexCor("#C62828")));

        Lista<Ponto> placas_marcadas = new Lista<Ponto>();


        while (tem_preto) {

            Opcional<Ponto> tem_ponto_preto = Rasterizador.GET_PONTO_COM_COR_DIFERENTES_DE(render_tronarko, mCores.getPreto(), placas_marcadas);

            if (tem_ponto_preto.isOK()) {

                video.novoQuadro();

                Ponto ponto_preto = tem_ponto_preto.get();
                placas_marcadas.adicionar(ponto_preto);
                placas += 1;

                Cor placa_cor = qual_cor.get();

                fmt.print("Placa Tectonica :: {} ->> {}:{} com {}", placas, ponto_preto.getX(), ponto_preto.getY(), render_tronarko.getPixel(ponto_preto.getX(), ponto_preto.getY()).getValor());
                Rasterizador.RASTERIZAR_COM(render_tronarko, ponto_preto.getX(), ponto_preto.getY(), mCores.getPreto(), placa_cor, Acao.ACAO_VAZIA(), video.onQuadro());
                render_tronarko.setPixel(ponto_preto.getX(), ponto_preto.getY(), placa_cor);

                video.novoQuadro();

            }

            tem_preto = tem_ponto_preto.isOK();
            Imagem.exportar(render_tronarko.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/tectonico/tronarko_tectonico_limites_v3.png"));

            // break;
        }


        video.fechar();

        Lista<Entidade> e_placas = ENTT.CRIAR_LISTA();

        for (Ponto placa : placas_marcadas) {
            Entidade e_placa = ENTT.CRIAR_EM(e_placas);
            e_placa.atInt("PlacaID", ENTT.CONTAGEM(e_placas));
            e_placa.at("X", placa.getX());
            e_placa.at("Y", placa.getY());
        }

        ENTT.GUARDAR(e_placas, AtzumCreator.LOCAL_GET_ARQUIVO("build/tectonico/placas_tectonicas.entts"));


        AtzumCreatorInfo.terminar("ServicoTectonico.RASTERIZAR");
        AtzumCreatorInfo.exibir_item("ServicoTectonico.RASTERIZAR");

    }


    public static void PLACA_AREA() {
        AtzumCreatorInfo.iniciar("ServicoTectonico.PLACA_AREA");

        Cores mCores = new Cores();


        Lista<Entidade> e_placas = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO("build/tectonico/placas_tectonicas.entts"));

        for (Entidade e_placa : e_placas) {

            ENTT.EXIBIR_TABELA_COM_NOME(e_placas, "Placas Tectônicas :: ANTES");

            Renderizador render_tronarko = new Renderizador(Imagem.GET_IMAGEM_POR_PIXEL_RGB(AtzumCreator.LOCAL_GET_ARQUIVO("build/tectonico/tronarko_tectonico_limites.png")));

            fmt.print("Placa Tectonica :: {}", e_placa.at("PlacaID"));


            Acao durante_mudanca = new Acao() {
                @Override
                public void fazer() {
                    Imagem.exportar(render_tronarko.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/processando.png"));
                }
            };


            Rasterizador.RASTERIZAR_COM(render_tronarko, e_placa.atInt("X"), e_placa.atInt("Y"), mCores.getPreto(), mCores.getAmarelo(), durante_mudanca, Acao.ACAO_VAZIA());

            long tamanho = 0;

            for (int y = 0; y <= render_tronarko.getAltura(); y++) {
                for (int x = 0; x <= render_tronarko.getLargura(); x++) {
                    if (render_tronarko.getPixel(x, y).igual(mCores.getAmarelo())) {
                        tamanho += 1;
                    }
                }
            }

            long area_minima = 300 * 300;

            e_placa.at("Tamanho", tamanho);
            e_placa.at("AreaTipo", Matematica.CONDICIONAL(tamanho > area_minima, "AREA_VALIDA", "PEQUENO"));


            ENTT.GUARDAR(e_placas, AtzumCreator.LOCAL_GET_ARQUIVO("build/tectonico/placas_tectonicas.entts"));
            ENTT.EXIBIR_TABELA_COM_NOME(e_placas, "Placas Tectônicas :: DEPOIS");

        }


        fmt.print("Placas Válidas  : {}", ENTT.CONTAGEM(e_placas, "AreaTipo", "AREA_VALIDA"));
        fmt.print("Placas Pequenas : {}", ENTT.CONTAGEM(e_placas, "AreaTipo", "PEQUENO"));

        AtzumCreatorInfo.terminar("ServicoTectonico.PLACA_AREA");
        AtzumCreatorInfo.exibir_item("ServicoTectonico.PLACA_AREA");
    }


    public static void ORGANIZAR_PLACAS() {

        AtzumCreatorInfo.iniciar("ServicoTectonico.ORGANIZAR_PLACAS");

        Lista<Entidade> e_placas = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO("build/tectonico/placas_tectonicas.entts"));

        Lista<Entidade> placas_validas = ENTT.COLETAR(e_placas, "AreaTipo", "AREA_VALIDA");
        Lista<Entidade> placas_pequenas = ENTT.COLETAR(e_placas, "AreaTipo", "PEQUENO");

        fmt.print("Placas Válidas  : {}", ENTT.CONTAGEM(e_placas, "AreaTipo", "AREA_VALIDA"));
        fmt.print("Placas Pequenas : {}", ENTT.CONTAGEM(e_placas, "AreaTipo", "PEQUENO"));


        ENTT.ATRIBUTO_TODOS(e_placas,"RefPlacaID","");

        for(Entidade e : placas_validas){
            e.at("RefPlacaID",e.at("PlacaID"));
        }

        ENTT.EXIBIR_TABELA(placas_validas);

        for (Entidade placa_pequena : placas_pequenas) {

            int px = placa_pequena.atInt("X");
            int py = placa_pequena.atInt("Y");

            Opcional<Entidade> placa_proxima = Opcional.CANCEL();
            double placa_proximidade = 0;

            for (Entidade placa_valida : placas_validas) {

                int pvx = placa_valida.atInt("X");
                int pvy = placa_valida.atInt("Y");

                if (placa_proxima.isVazio()) {
                    placa_proxima.set(placa_valida);
                    placa_proximidade = Espaco2D.distancia_entre_pontos(px, py, pvx, pvy);
                } else {
                    double distancia = Espaco2D.distancia_entre_pontos(px, py, pvx, pvy);
                    if (distancia < placa_proximidade) {
                        placa_proxima.set(placa_valida);
                        placa_proximidade=distancia;
                    }
                }

            }

            if(placa_proxima.isOK()){
                placa_pequena.at("RefPlacaID",placa_proxima.get().at("PlacaID"));
                placa_pequena.at("PlacaProximaID",placa_proxima.get().at("PlacaID"));
            }


        }


        ENTT.GUARDAR(e_placas, AtzumCreator.LOCAL_GET_ARQUIVO("build/tectonico/placas_tectonicas.entts"));

        AtzumCreatorInfo.terminar("ServicoTectonico.ORGANIZAR_PLACAS");
        AtzumCreatorInfo.exibir_item("ServicoTectonico.ORGANIZAR_PLACAS");

    }

}
