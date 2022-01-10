package AppAttuz.Servicos;

import AppAttuz.CadaPonto;
import AppAttuz.Camadas.Massas;
import AppAttuz.Camadas.OnData;
import AppAttuz.EscalasPadroes;
import AppAttuz.Ferramentas.Escala;
import AppAttuz.Normalizador;
import Azzal.Cores;
import Azzal.Utils.Cor;
import Imaginador.ImageUtils;
import Servittor.Servico;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Umidade extends Servico {

    private String LOCAL;

    public Umidade(String eLOCAL) {
        LOCAL = eLOCAL;
    }

    @Override
    public void onInit() {


        Cartografia onCartografia = new Cartografia(LOCAL);
        OnData onData = new OnData(LOCAL);

        Massas tectonica = new Massas(LOCAL);
        Massas dados = new Massas(LOCAL);
        dados.zerar();

        BufferedImage mapa_colorindo = ImageUtils.getImagem(LOCAL + "terra.png");

        Cores paleta = new Cores();


        boolean indo = true;

        int faixa_contador = 0;

        Escala umidade = EscalasPadroes.getEscalaUmidade();

        Normalizador norm = new Normalizador(umidade.getMaximo());

        for (Integer faixa : onCartografia.getCentrosDeLatitudes()) {

            // Cor eCor = (indo == true) ? paleta.getVermelho() : paleta.getAzul();
            // drawRect_Pintado(mapa_colorindo, pos, faixa, 30, 30, eCor);

            int pCentral = faixa + 15;

            int pCentral_c = pCentral - 50;
            int pCentral_t = pCentral + 50;

            int comecar = 0;
            int mudanca = 0;

            if (indo) {
                comecar = 0;
                mudanca = +1;
            } else {
                comecar = tectonica.getLargura() - 1;
                mudanca = -1;
            }

            for (int u = pCentral_c; u < pCentral_t; u++) {
                umidecer(tectonica, dados, onData, norm, u, comecar, mudanca);
            }

            indo = !indo;

            faixa_contador += 1;
            if (faixa_contador == 9) {
                indo = !indo;
            }
        }

        norm.equilibrar();
        //  norm.stock();

        tectonica.paraCadaPonto(new CadaPonto() {
            @Override
            public void ontPonto(int x, int y) {
                if (tectonica.isTerra(x, y)) {
                    mapa_colorindo.setRGB(x, y, umidade.get(norm.get(dados.getValor(x, y)) + 1));
                }
            }
        });


        // aplicar_grade(tectonica, onCartografia, mapa_colorindo);


        ImageUtils.exportar(mapa_colorindo, LOCAL + "build/umidade.png");


    }


    public void aplicar_grade(Massas tectonica, Cartografia onCartografia, BufferedImage mapa_colorindo) {

        tectonica.paraCadaPonto(new CadaPonto() {
            @Override
            public void ontPonto(int x, int y) {

                if (onCartografia.isLinhaLatitude(x, y)) {

                    int v = Color.GREEN.getRGB();

                    if (onCartografia.getLatitude(y) == 0) {
                        v = Color.YELLOW.getRGB();
                    }

                    mapa_colorindo.setRGB(x, y, v);

                    mapa_colorindo.setRGB(x, y - 1, v);
                    mapa_colorindo.setRGB(x, y - 2, v);
                    mapa_colorindo.setRGB(x, y + 1, v);
                    mapa_colorindo.setRGB(x, y + 2, v);
                }

            }
        });

    }

    public void drawRect_Pintado(BufferedImage imagem, int px, int py, int largura, int altura, Cor eCor) {

        int mX2 = px + largura;

        int iCor = eCor.getValor();

        for (int mX = px; mX < mX2; mX++) {
            int mY2 = py + altura;
            for (int mY = py; mY < mY2; mY++) {
                imagem.setRGB(mX, mY, iCor);
            }
        }

    }

    public void umidecer(Massas tectonica, Massas dados, OnData onData, Normalizador norm, int altura_inicio, int xComecar, int mudanca) {


        double maior = 300.0;
        double menor = -300.0;

        double valor = maior;

        boolean passou_por_terra = false;

        for (int x = xComecar; x >= 0 && x < tectonica.getLargura(); x += mudanca) {

            if (tectonica.isTerra(x, altura_inicio)) {
                passou_por_terra = true;

                if (valor > menor) {

                    double antigo = dados.getValor(x, altura_inicio);

                    double valor_corrente = antigo + valor;

                    dados.setValor(x, altura_inicio, (int) valor_corrente);
                    norm.adicionar((int) valor_corrente);
                }


                if (onData.getAltura(x, altura_inicio) > 3000) {

                    int co = dados.getValor(x, altura_inicio) + (int) valor;
                    int te = co;

                    for (int x_voltando = x; x_voltando < tectonica.getLargura() && x_voltando > 0; x_voltando += (mudanca * (-1))) {
                        if (valor > 0) {

                            double antigo = dados.getValor(x_voltando, altura_inicio);

                            double valor_corrente = antigo + valor;

                            dados.setValor(x_voltando, altura_inicio, (int) valor_corrente);
                            norm.adicionar((int) valor_corrente);
                            te = (int) valor_corrente;

                        }

                        if (valor > menor) {
                            valor -= 0.5;
                        }
                    }


                    // System.out.println("Altura :: " + onData.getAltura(x, altura_inicio) + " comecou " + (co) + "! terminou " + (te));

                    //break;
                    valor = 0;

                }

                if (valor > menor) {
                    valor -= 0.5;
                }

            } else {
                if (passou_por_terra) {
                    if (valor < maior) {
                        valor += 1;
                    }
                }

            }

        }

    }

}
