package apps.AppAttuz.Mapa;

import apps.AppAttuz.Camadas.CadaPonto;
import apps.AppAttuz.Camadas.Massas;
import apps.AppAttuz.Ferramentas.Espaco2D;
import apps.AppAttuz.Ferramentas.Normalizador;
import apps.AppAttuz.Assessorios.Progressante;
import apps.AppAttuz.IDW.PontoIDW;
import libs.Luan.fmt;

import java.util.ArrayList;

public class Proximattor {

    public static PontoProximo getProximoDireita(ArrayList<PontoIDW> eixos, int x, int y) {

        PontoProximo pp = new PontoProximo();

        boolean primeiro = true;

        for (PontoIDW eixo : eixos) {

            if (eixo.getX() > x) {
                int distancia = Espaco2D.distancia_entre_pontos(x, y, eixo.getX(), eixo.getY());

                if (primeiro) {
                    primeiro = false;
                    pp.set(eixo.getValor(), distancia);
                } else {
                    if (distancia < pp.getDistancia()) {
                        pp.set(eixo.getValor(), distancia);
                    }
                }

            }
        }

        return pp;

    }

    public static PontoProximo getProximoEsquerda(ArrayList<PontoIDW> eixos, int x, int y) {

        PontoProximo pp = new PontoProximo();

        boolean primeiro = true;

        for (PontoIDW eixo : eixos) {

            if (eixo.getX() < x) {
                int distancia = Espaco2D.distancia_entre_pontos(x, y, eixo.getX(), eixo.getY());

                if (primeiro) {
                    primeiro = false;
                    pp.set(eixo.getValor(), distancia);
                } else {
                    if (distancia < pp.getDistancia()) {
                        pp.set(eixo.getValor(), distancia);
                    }
                }

            }
        }

        return pp;

    }

    public static PontoProximo getProximoAcima(ArrayList<PontoIDW> eixos, int x, int y) {

        PontoProximo pp = new PontoProximo();

        boolean primeiro = true;

        for (PontoIDW eixo : eixos) {

            if (eixo.getY() < y) {
                int distancia = Espaco2D.distancia_entre_pontos(x, y, eixo.getX(), eixo.getY());

                if (primeiro) {
                    primeiro = false;
                    pp.set(eixo.getValor(), distancia);
                } else {
                    if (distancia < pp.getDistancia()) {
                        pp.set(eixo.getValor(), distancia);
                    }
                }

            }
        }

        return pp;

    }

    public static PontoProximo getProximoAbaixo(ArrayList<PontoIDW> eixos, int x, int y) {

        PontoProximo pp = new PontoProximo();

        boolean primeiro = true;

        for (PontoIDW eixo : eixos) {

            if (eixo.getY() > y) {
                int distancia = Espaco2D.distancia_entre_pontos(x, y, eixo.getX(), eixo.getY());

                if (primeiro) {
                    primeiro = false;
                    pp.set(eixo.getValor(), distancia);
                } else {
                    if (distancia < pp.getDistancia()) {
                        pp.set(eixo.getValor(), distancia);
                    }
                }

            }
        }

        return pp;

    }

    public static void porProximidade(Massas tectonica, int TECTONICA_VALOR, Massas massa, Normalizador normalizador, ArrayList<PontoIDW> eixos, int AFASTADOR) {


        Progressante progresso = new Progressante(massa.getAltura() * massa.getLargura());

        tectonica.paraCadaPonto(new CadaPonto() {
            @Override
            public void onPonto(int x, int y) {

                if (tectonica.getValor(x, y) == TECTONICA_VALOR) {


                    PontoProximo direita = getProximoDireita(eixos, x, y);
                    PontoProximo esquerda = getProximoEsquerda(eixos, x, y);
                    PontoProximo acima = getProximoAcima(eixos, x, y);
                    PontoProximo abaixo = getProximoAbaixo(eixos, x, y);

                    if (direita.existe() && esquerda.existe() && acima.existe() && abaixo.existe()) {

                        PontoDirecionado ePonto = new PontoDirecionado(direita.getDistancia(), direita.getValor(), "DIREITA");

                        escolherMenor(ePonto, esquerda.getDistancia(), esquerda.getValor(), "ESQUERDA");
                        escolherMenor(ePonto, acima.getDistancia(), acima.getValor(), "ACIMA");
                        escolherMenor(ePonto, abaixo.getDistancia(), abaixo.getValor(), "ABAIXO");

                        normalizador.adicionar(ePonto.getValor());
                        massa.setValor(x, y, ePonto.getValor());

                        String v = fmt.format(" PONTO ( {esq5} , {esq5} ) :: {esq9} com {esq5} em distancia {esq5}", x, y, ePonto.getDirecao(), ePonto.getValor(), ePonto.getDistancia());

                        progresso.emitir((y * tectonica.getAltura()) + x, v);

                    } else if (direita.existe() && esquerda.existe()) {

                        int p1 = direita.getDistancia();
                        int p2 = esquerda.getDistancia();

                        int distancia = p1;
                        int valor = direita.getValor();

                        String direcao = "DIREITA";

                        if (p2 < distancia) {
                            distancia = p2;
                            valor = esquerda.getValor();
                            direcao = "ESQUERDA";
                        }


                        normalizador.adicionar(valor);
                        massa.setValor(x, y, valor);

                        String v = fmt.format(" PONTO ( {esq5} , {esq5} ) :: {esq9} com {esq5} em distancia {esq5}", x, y, direcao, valor, distancia);

                        progresso.emitir((y * tectonica.getAltura()) + x, v);


                    } else if (acima.existe() && abaixo.existe()) {


                        int p3 = acima.getDistancia();
                        int p4 = abaixo.getDistancia();

                        int distancia = p3;
                        int valor = acima.getValor();

                        String direcao = "ACIMA";


                        if (p4 < distancia) {
                            distancia = p4;
                            valor = abaixo.getValor();
                            direcao = "ABAIXO";
                        }


                        normalizador.adicionar(valor);
                        massa.setValor(x, y, valor);

                        String v = fmt.format(" PONTO ( {esq5} , {esq5} ) :: {esq9} com {esq5} em distancia {esq5}", x, y, direcao, valor, distancia);

                        progresso.emitir((y * tectonica.getAltura()) + x, v);

                    } else {
                        System.out.println("FALTOU !!");
                    }


                }

            }
        });


    }

    public static void escolherMenor(PontoDirecionado ePontoDirecionado, int eDistancia, int eValor, String eDirecao) {

        if (eDistancia < ePontoDirecionado.getDistancia()) {
            ePontoDirecionado.mudar(eDistancia, eValor, eDirecao);
        }

    }

}
