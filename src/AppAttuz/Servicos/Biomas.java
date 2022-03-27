package AppAttuz.Servicos;


import AppAttuz.Assessorios.Progressante;
import AppAttuz.Camadas.CadaPonto;
import AppAttuz.Camadas.DadosQTT;
import AppAttuz.Camadas.MapaFolha;
import AppAttuz.Camadas.Massas;
import AppAttuz.Camadas.MassasDados;
import AppAttuz.Camadas.EscalasPadroes;
import AppAttuz.Assessorios.Escala;
import AppAttuz.Ferramentas.*;
import AppAttuz.Legendas.Legendar;
import AppAttuz.Legendas.Legendas;
import AppAttuz.MapaProximidade;
import Imaginador.ImageUtils;
import Luan.fmt;
import Servittor.Servico;

import java.awt.image.BufferedImage;

public class Biomas extends Servico {

    private String LOCAL;

    public Biomas(String eLOCAL) {
        LOCAL = eLOCAL;
    }

    @Override
    public void onInit() {

        marcarInicio();

        Escala mEscala = EscalasPadroes.getEscalaBiomas();

        Massas tectonica = MassasDados.getTerraAgua(LOCAL);

        MassaComNormal bioma = new MassaComNormal(MassasDados.getTerraAgua(LOCAL), new Normalizador(mEscala.getMaximo()));

        DadosQTT dadosQTT = new DadosQTT(LOCAL);

        Progressante progresso = new Progressante(tectonica.getAltura() * tectonica.getLargura());

        bioma.getDados().paraCadaPonto(new CadaPonto() {
            @Override
            public void onPonto(int x, int y) {

                if (tectonica.isTerra(x, y)) {

                    int umidade = dadosQTT.getUmidade(x, y);
                    int preciptacao = dadosQTT.getPreciptacao(x, y);
                    int temperatura = dadosQTT.getTemperatura_vi(x, y);


                    boolean isQuente = false;
                    boolean isFrio = false;

                    boolean isUmido = false;
                    boolean isSeco = false;

                    boolean temAgua = false;

                    if (preciptacao > 1) {
                        temAgua = true;
                    }

                    if (temperatura >= 5) {
                        isQuente = true;
                        isFrio = false;
                    } else {
                        isQuente = false;
                        isFrio = true;
                    }

                    if (umidade >= 7) {
                        isUmido = true;
                        isSeco = false;
                    } else {
                        isUmido = false;
                        isSeco = true;
                    }

                    int valor = 0;

                    if (isQuente && isUmido) {
                        if (temAgua) {
                            valor = 5;
                        } else {
                            valor = 4;
                        }
                    } else if (isQuente && isSeco) {
                        valor = 2;
                    } else if (isFrio && isSeco) {

                        if (temAgua) {
                            valor = 3;
                        } else {
                            valor = 3;
                        }

                    } else if (isFrio && isUmido) {
                        valor = 6;
                    } else {
                        valor = 1;
                    }

                    System.out.println("valor :: " + y + "::" + x);

                    bioma.setValor(x, y, valor);

                    String v = fmt.format(" PONTO ( {esq5} , {esq5} ) :: U={esq9} P ={esq5}  T={esq5} -->> {}", x, y, umidade, preciptacao, temperatura, valor);

                    progresso.emitir((y * tectonica.getLargura()) + x, v);

                } else {
                    progresso.vazio((y * tectonica.getLargura()) + x);
                }


            }
        });


        bioma.getDados().paraCadaPonto(new CadaPonto() {
            @Override
            public void onPonto(int x, int y) {

                if (MapaProximidade.isValido4Lados(x, y, tectonica.getLargura(), tectonica.getAltura())) {

                    int c = bioma.getDados().getValor(x, y);

                    int v1 = bioma.getDados().getValor(x + 1, y);
                    int v2 = bioma.getDados().getValor(x - 1, y);
                    int v3 = bioma.getDados().getValor(x, y + 1);
                    int v4 = bioma.getDados().getValor(x, y - 1);

                    if (v1 == v2 && v2 == v3 && v3 == v4) {
                        if (c != v1) {
                            bioma.getDados().setValor(x, y, v1);
                        }
                    }

                }

            }
        });

        BufferedImage mRenderizado = Pintor.colorir(MapaFolha.getMapa(LOCAL), bioma.getDados(), mEscala);


        ImageUtils.exportar(mRenderizado, LOCAL + "build/biomas.png");


        marcarFim();
        mostrarTempo();
    }


}
