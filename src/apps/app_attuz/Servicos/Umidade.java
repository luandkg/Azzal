package apps.app_attuz.Servicos;

import apps.app_attuz.Assessorios.CadaPonto;
import apps.app_attuz.Assessorios.Massas;
import apps.app_attuz.Assessorios.MassasDados;
import apps.app_attuz.Assessorios.DadosQTT;
import apps.app_attuz.Assessorios.EscalasPadroes;
import apps.app_attuz.Assessorios.Escala;
import apps.app_attuz.Ferramentas.MassaToQTT;
import apps.app_attuz.Ferramentas.Normalizador;
import libs.azzal.utilitarios.Cor;
import libs.imagem.Imagem;
import libs.servittor.Servico;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Umidade extends Servico {

    private String LOCAL;

    public Umidade(String eLOCAL) {
        LOCAL = eLOCAL;
    }

    @Override
    public void onInit() {

        marcarInicio();

        Conveccionador eConveccionador = new Conveccionador(LOCAL);
        DadosQTT dadosQTT = new DadosQTT(LOCAL);

        Massas tectonica = MassasDados.getTerraAgua(LOCAL);
        Massas dados = MassasDados.getTerraAgua(LOCAL);

        dados.zerar();

        BufferedImage mapa_colorindo = Imagem.getImagem(LOCAL + "build/planeta.png");


        boolean indo = true;

        int faixa_contador = 0;

        Escala escala_umidade = EscalasPadroes.getEscalaUmidade();

        Normalizador norm = new Normalizador(escala_umidade.getMaximo());

        for (Integer faixa : eConveccionador.getCentrosDeLatitudes()) {


            int pCentral_c = faixa - (eConveccionador.getAlturaFaixa() / 2);
            int pCentral_t = faixa + (eConveccionador.getAlturaFaixa() / 2);

            int comecar = 0;
            int mudanca = 0;

            if (indo) {
                comecar = 0;
                mudanca = +1;
            } else {
                comecar = tectonica.getLargura() - 1;
                mudanca = -1;
            }

            for (int u = pCentral_c; u <= pCentral_t; u++) {
                umidecer(tectonica, dados, dadosQTT, norm, u, comecar, mudanca);
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
            public void onPonto(int x, int y) {
                if (tectonica.isTerra(x, y)) {
                    dados.setValor(x, y, norm.get(dados.getValor(x, y)) + 1);
                }
            }
        });

        tectonica.paraCadaPonto(new CadaPonto() {
            @Override
            public void onPonto(int x, int y) {
                if (tectonica.isTerra(x, y)) {
                    mapa_colorindo.setRGB(x, y, escala_umidade.get(dados.getValor(x, y)));
                }
            }
        });

        // aplicar_grade(tectonica, onCartografia, mapa_colorindo);


        Imagem.exportar(mapa_colorindo, LOCAL + "build/umidade.png");

        System.out.println("Guardar Umidade - QTT");
        MassaToQTT.salvarTerra(tectonica, dados, LOCAL + "dados/umidade.qtt");


        marcarFim();
        mostrarTempo();
    }


    public void aplicar_grade(Massas tectonica, Cartografia onCartografia, BufferedImage mapa_colorindo) {

        tectonica.paraCadaPonto(new CadaPonto() {
            @Override
            public void onPonto(int x, int y) {

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

    public void umidecer(Massas tectonica, Massas dados, DadosQTT dadosQTT, Normalizador norm, int altura_inicio, int xComecar, int mudanca) {


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

                } else {
                    double antigo = dados.getValor(x, altura_inicio);

                    double valor_corrente = antigo + menor;

                    dados.setValor(x, altura_inicio, (int) valor_corrente);
                    norm.adicionar((int) valor_corrente);
                }


                if (dadosQTT.getAltura(x, altura_inicio) > 3000) {

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
