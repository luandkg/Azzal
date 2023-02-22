package apps.app_attuz.Servicos;

import apps.appLetrum.Fonte;
import apps.appLetrum.Maker.FonteRunTime;
import azzal.Renderizador;
import azzal.utilitarios.Cor;
import libs.imagem.Imagem;
import libs.servittor.Servico;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TerraFormar extends Servico {

    private String LOCAL = "";

    public TerraFormar(String eLOCAL) {
        LOCAL = eLOCAL;
    }

    @Override
    public void onInit() {

        String arquivo_terra_formar = LOCAL + "terra_formacao.png";

        String arquivo_planeta = LOCAL + "build/planeta.png";
        String arquivo_politicamente = LOCAL + "build/politicamente.png";

        String arquivo_terraformando_inicio = LOCAL + "build/terraformando_inicio.png";
        String arquivo_terraformando_processo = LOCAL + "build/terraformando_processo.png";

        BufferedImage planeta = Imagem.getImagem(arquivo_terra_formar);


        Imagem.exportar(planeta, arquivo_terraformando_inicio);

        println("Largura = " + planeta.getWidth());
        println("Altura = " + planeta.getHeight());

        ArrayList<Cor> regioes = getListaDeCores(planeta);


        println("Regioes = " + mostrar_lista_de_cores(regioes));

        if (regioes.size() > 0) {

            BufferedImage cores_regioes = construir_faixa_de_cores(regioes);

            Imagem.exportar(cores_regioes, arquivo_terraformando_processo);

        }


        int COR_AGUA = -1;
        int COR_TERRA = -16777216;

        int COR_DIVISAO_TERRESTRE = -16777216;


        // CONSTRUIR MAPA POLITICO

        BufferedImage mapa_politicamente = Imagem.criarEmBranco(planeta.getWidth(), planeta.getHeight());

        for (int x = 0; x < planeta.getWidth(); x++) {
            for (int y = 0; y < planeta.getHeight(); y++) {

                int valor = planeta.getRGB(x, y);

                if (valor == COR_DIVISAO_TERRESTRE) {

                    int eCorProxima = getCorProxima(planeta, x, y);

                    mapa_politicamente.setRGB(x, y, eCorProxima);

                } else {
                    mapa_politicamente.setRGB(x, y, valor);
                }


            }
        }


        Imagem.exportar(mapa_politicamente, arquivo_politicamente);

        println("Mapa Politico = OK");


        // CONSTRUIR MAPA DE AGUA E TERRA

        BufferedImage mapa_planeta = Imagem.criarEmBranco(planeta.getWidth(), planeta.getHeight());

        for (int x = 0; x < planeta.getWidth(); x++) {
            for (int y = 0; y < planeta.getHeight(); y++) {

                int valor = mapa_politicamente.getRGB(x, y);

                if (valor == COR_AGUA) {
                    mapa_politicamente.setRGB(x, y, COR_AGUA);
                } else {
                    mapa_planeta.setRGB(x, y, COR_TERRA);
                }


            }
        }


        Imagem.exportar(mapa_planeta, arquivo_planeta);

        println("Mapa Planeta = OK");


    }

    public static String mostrar_lista_de_cores(ArrayList<Cor> cores) {
        String s = "";

        int vo = cores.size() - 1;
        int vi = 0;

        for (Cor cor : cores) {
            if (vi == vo) {
                s += cor.getValor() + "";
            } else {
                s += cor.getValor() + " , ";
            }
            vi += 1;
        }
        return "{ " + s + " }";
    }

    public static ArrayList<Cor> getListaDeCores(BufferedImage mapa) {

        ArrayList<Cor> cores = new ArrayList<Cor>();


        for (int x = 0; x < mapa.getWidth(); x++) {
            for (int y = 0; y < mapa.getHeight(); y++) {

                int valor = mapa.getRGB(x, y);
                Cor cor_corrente = Cor.getInt(valor);

                boolean ja_existe = false;
                for (Cor cor : cores) {
                    if (cor.igual(cor_corrente)) {
                        ja_existe = true;
                        break;
                    }
                }
                if (!ja_existe) {
                    cores.add(Cor.getInt(valor));
                }

            }
        }

        return cores;
    }

    public static int getCorProxima(BufferedImage politico, int x, int y) {

        int valor = 0;
        int dist = 0;

        int PRETO = Color.BLACK.getRGB();
        int BRANCO = Color.WHITE.getRGB();

        int OBSERVAR = 100;

        for (int xi = 0; xi < OBSERVAR; xi++) {
            int vcor = politico.getRGB(x + xi, y);
            if (vcor != PRETO && vcor != BRANCO) {
                valor = vcor;
                dist = xi;
                break;
            }
        }

        int guardar_valor = valor;
        int guardar_dist = dist;

        for (int xi = 0; xi < OBSERVAR; xi++) {
            int vcor = politico.getRGB(x - xi, y);
            if (vcor != PRETO && vcor != BRANCO) {
                valor = vcor;
                dist = xi;
                break;
            }
        }

        if (guardar_dist < dist && guardar_valor != 0) {
            valor = guardar_valor;
            dist = guardar_dist;
        }

        guardar_valor = valor;
        guardar_dist = dist;

        for (int yi = 0; yi < OBSERVAR; yi++) {

            int px = x;
            int py = y + yi;

            if (px >= 0 && px < politico.getWidth() && py >= 0 && py < politico.getHeight()) {

                int vcor = politico.getRGB(x, y + yi);
                if (vcor != PRETO && vcor != BRANCO) {
                    valor = vcor;
                    dist = yi;
                    break;
                }

            }


        }

        if (guardar_dist < dist && guardar_valor != 0) {
            valor = guardar_valor;
            dist = guardar_dist;
        }

        guardar_valor = valor;
        guardar_dist = dist;


        for (int yi = 0; yi < OBSERVAR; yi++) {
            int px = x;
            int py = y - yi;

            if (px >= 0 && px < politico.getWidth() && py >= 0 && py < politico.getHeight()) {

                int vcor = politico.getRGB(x, y - yi);
                if (vcor != PRETO && vcor != BRANCO) {
                    valor = vcor;
                    dist = yi;
                    break;
                }

            }

        }

        if (guardar_dist < dist && guardar_valor != 0) {
            valor = guardar_valor;
            dist = guardar_dist;
        }

        return valor;

    }


    public static BufferedImage construir_faixa_de_cores(ArrayList<Cor> cores) {

        int por_linha = 5;

        int linhas = ((cores.size()) / 5) + 1;


        BufferedImage faixa_horizontal = Imagem.criarEmBranco(por_linha * 150, linhas * 130);

        Renderizador r = new Renderizador(faixa_horizontal);

        Fonte f = new FonteRunTime(new Cor(0, 0, 0), 10);
        f.setRenderizador(r);

        int lateralmente = 0;
        int alturamente = 0;

        int indo_linha = 0;

        for (Cor v : cores) {

            for (int x = lateralmente; x < (lateralmente + 150); x++) {
                for (int y = alturamente; y < (alturamente + 100); y++) {
                    faixa_horizontal.setRGB(x, y, v.getValor());
                }
            }

            f.escrevaCentralizado(lateralmente + (150 / 2), alturamente + 100, v.toString());

            lateralmente += 150;
            indo_linha += 1;
            if (indo_linha >= por_linha) {
                indo_linha = 0;
                lateralmente = 0;
                alturamente += 130;
            }
        }

        return r.toImagemSemAlfa();
    }


}
