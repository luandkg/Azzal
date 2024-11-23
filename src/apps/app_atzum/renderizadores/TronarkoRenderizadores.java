package apps.app_atzum.renderizadores;

import apps.app_atzum.AtzumTerra;
import apps.app_atzum.escalas.EscalaAQ4;
import apps.app_atzum.escalas.EscalaRT3;
import apps.app_atzum.escalas.EscalaVT2;
import apps.app_atzum.utils.IntervaloDeValorColorido;
import libs.arquivos.QTT;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.utilitarios.Cor;
import libs.azzal.utilitarios.HSV;
import libs.luan.AreaDouble;
import libs.luan.Lista;

public class TronarkoRenderizadores {


    public static Renderizador renderizar_variacao_de_temperatura(AtzumTerra mapa_planeta, AreaDouble processando, double temp_taxa) {

        Cores mCores = new Cores();
        Renderizador render_distancia = Renderizador.construir(mapa_planeta.getLargura(), mapa_planeta.getAltura(), mCores.getPreto());


        for (int y = 0; y < mapa_planeta.getAltura(); y++) {
            for (int x = 0; x < mapa_planeta.getLargura(); x++) {
                if (mapa_planeta.isTerra(x, y)) {

                    int temperatura = (int) processando.get(x, y);

                    render_distancia.setPixel(x, y, new HSV(350, HSV.MAXIMO, HSV.INVERSO((int) (temperatura * temp_taxa))));

                }
            }
        }

        return render_distancia;
    }


    public static Renderizador renderizar_variacao_de_temperatura_zona(AtzumTerra mapa_planeta, AreaDouble processando, double temp_taxa) {

        Cores mCores = new Cores();
        Renderizador render_temperatura = Renderizador.construir(mapa_planeta.getLargura(), mapa_planeta.getAltura(), mCores.getPreto());

        Cor COR_MUITO_QUENTE = Cor.getHexCor("#BF360C");
        Cor COR_QUENTE = Cor.getHexCor("#FB8C00");
        Cor COR_FRIO = Cor.getHexCor("#1976D2");
        Cor COR_MUITO_FRIO = Cor.getHexCor("#0D47A1");
        Cor COR_NORMAL = Cor.getHexCor("#8BC34A");

        Lista<IntervaloDeValorColorido> FAIXAS_DE_TEMPERATURA = new Lista<IntervaloDeValorColorido>();

        FAIXAS_DE_TEMPERATURA.adicionar(new IntervaloDeValorColorido(36, 45, COR_MUITO_QUENTE));
        FAIXAS_DE_TEMPERATURA.adicionar(new IntervaloDeValorColorido(30, 35, COR_QUENTE));
        FAIXAS_DE_TEMPERATURA.adicionar(new IntervaloDeValorColorido(10, 30, COR_NORMAL));
        FAIXAS_DE_TEMPERATURA.adicionar(new IntervaloDeValorColorido(-15, 10, COR_FRIO));
        FAIXAS_DE_TEMPERATURA.adicionar(new IntervaloDeValorColorido(-30, -15, COR_MUITO_FRIO));


        for (int y = 0; y < mapa_planeta.getAltura(); y++) {
            for (int x = 0; x < mapa_planeta.getLargura(); x++) {
                if (mapa_planeta.isTerra(x, y)) {

                    int temperatura = (int) processando.get(x, y);


                    Cor cor_temp = mCores.getBranco();

                    for (IntervaloDeValorColorido temp_zona : FAIXAS_DE_TEMPERATURA) {
                        if (temperatura >= temp_zona.getMinimo() && temperatura <= temp_zona.getMaximo()) {
                            cor_temp = temp_zona.getCor();
                            break;
                        }
                    }

                    if (temperatura > FAIXAS_DE_TEMPERATURA.get(0).getMaximo()) {
                        cor_temp = FAIXAS_DE_TEMPERATURA.get(0).getCor();
                    }

                    if (temperatura < FAIXAS_DE_TEMPERATURA.get(FAIXAS_DE_TEMPERATURA.getQuantidade() - 1).getMinimo()) {
                        cor_temp = FAIXAS_DE_TEMPERATURA.get(FAIXAS_DE_TEMPERATURA.getQuantidade() - 1).getCor();
                    }

                    render_temperatura.setPixel(x, y, cor_temp);

                }
            }
        }

        return render_temperatura;
    }


    public static Renderizador renderizar_temperatura_umidade(AtzumTerra mapa_planeta, Renderizador render_mapa_pronto, AreaDouble tronarko_temperatura, QTT umidade_dados) {


        int TEMPERATURA_NORMAL_SUPERIOR = 25;
        int TEMPERATURA_NORMAL_INFERIOR = 20;


        Renderizador render_preciptacao = render_mapa_pronto.getCopia();


        for (int y = 0; y < mapa_planeta.getAltura(); y++) {
            for (int x = 0; x < mapa_planeta.getLargura(); x++) {
                if (mapa_planeta.isTerra(x, y)) {

                    int temperatura = (int) tronarko_temperatura.get(x, y);
                    int umidade = umidade_dados.getValor(x, y);


                    // CHUVA FRIA
                    if (umidade >= 25 && (temperatura <= TEMPERATURA_NORMAL_INFERIOR)) {
                        render_preciptacao.setPixel(x, y, EscalaAQ4.T5);
                    }
                    if (umidade >= 50 && temperatura <= TEMPERATURA_NORMAL_INFERIOR) {
                        render_preciptacao.setPixel(x, y, EscalaAQ4.T10);
                    }
                    if (umidade >= 75 && temperatura <= TEMPERATURA_NORMAL_INFERIOR) {
                        render_preciptacao.setPixel(x, y, EscalaAQ4.T12);
                    }

                    // CHUVA NORMAL
                    if (umidade >= 25 && (temperatura > TEMPERATURA_NORMAL_INFERIOR) && (temperatura < TEMPERATURA_NORMAL_SUPERIOR)) {
                        render_preciptacao.setPixel(x, y, EscalaVT2.T5);
                    }
                    if (umidade >= 50 && temperatura > TEMPERATURA_NORMAL_INFERIOR && (temperatura < TEMPERATURA_NORMAL_SUPERIOR)) {
                        render_preciptacao.setPixel(x, y, EscalaVT2.T10);
                    }
                    if (umidade >= 75 && temperatura > TEMPERATURA_NORMAL_INFERIOR && (temperatura < TEMPERATURA_NORMAL_SUPERIOR)) {
                        render_preciptacao.setPixel(x, y, EscalaVT2.T12);
                    }

                    // CHUVA QUENTE
                    if (umidade >= 25 && (temperatura >= TEMPERATURA_NORMAL_SUPERIOR)) {
                        render_preciptacao.setPixel(x, y, EscalaRT3.T5);
                    }
                    if (umidade >= 50 && temperatura >= TEMPERATURA_NORMAL_SUPERIOR) {
                        render_preciptacao.setPixel(x, y, EscalaRT3.T10);
                    }
                    if (umidade >= 75 && temperatura >= TEMPERATURA_NORMAL_SUPERIOR) {
                        render_preciptacao.setPixel(x, y, EscalaRT3.T12);
                    }


                }
            }
        }

        return render_preciptacao;
    }


}
