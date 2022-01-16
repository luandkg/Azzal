package AppAttuz.Mapa;

import AppAttuz.*;
import AppAttuz.Camadas.Massas;
import AppAttuz.Ferramentas.Espaco2D;
import AppAttuz.Ferramentas.Normalizador;
import AppAttuz.Ferramentas.Progressante;
import AppAttuz.IDW.PontoIDW;
import Luan.fmt;

import java.util.ArrayList;

public class Mapetzo {

    public static void aplicar(Massas tectonica, int TECTONICA_VALOR, Massas massa, Normalizador normalizador, ArrayList<PontoIDW> eixos, int AFASTADOR) {


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

                        int p1 = ajustar(direita, AFASTADOR);
                        int p2 = ajustar(esquerda, AFASTADOR);
                        int p3 = ajustar(acima, AFASTADOR);
                        int p4 = ajustar(abaixo, AFASTADOR);

                        int somatorio = (p1 + p2 + p3 + p4) / 4;

                        normalizador.adicionar(somatorio);
                        massa.setValor(x, y, somatorio);

                        String v = fmt.print(" VALORES ( {} , {} ) :: {} ", direita.getValor() + " dist " + direita.getDistancia(), esquerda.getValor() + " dist " + esquerda.getDistancia(), somatorio);

                        progresso.emitir((y * tectonica.getAltura()) + x, fmt.print(" -->> TOTAL ( {} , {} ) :: {} ", x, y, v));
                    } else if (direita.existe() && esquerda.existe()) {

                        int p1 = ajustar(direita, AFASTADOR);
                        int p2 = ajustar(esquerda, AFASTADOR);

                        int somatorio = (p1 + p2) / 2;

                        normalizador.adicionar(somatorio);
                        massa.setValor(x, y, somatorio);

                        String v = fmt.print(" VALORES ( {} , {} ) :: {} ", direita.getValor() + " dist " + direita.getDistancia(), esquerda.getValor() + " dist " + esquerda.getDistancia(), somatorio);

                        progresso.emitir((y * tectonica.getAltura()) + x, fmt.print(" -->> HORIZONTAL ( {} , {} ) :: {} ", x, y, v));
                    } else if (acima.existe() && abaixo.existe()) {


                        int p3 = ajustar(acima, AFASTADOR);
                        int p4 = ajustar(abaixo, AFASTADOR);

                        int somatorio = (p3 + p4) / 2;

                        normalizador.adicionar(somatorio);
                        massa.setValor(x, y, somatorio);

                        String v = fmt.print(" VALORES ( {} , {} ) :: {} ", acima.getValor() + " dist " + acima.getDistancia(), abaixo.getValor() + " dist " + abaixo.getDistancia(), somatorio);

                        progresso.emitir((y * tectonica.getAltura()) + x, fmt.print(" -->> VERTICAL ( {} , {} ) :: {} ", x, y, v));
                    } else {
                        System.out.println("FALTOU !!");
                    }


                }

            }
        });


    }

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

    public static int ajustar(PontoProximo ponto, int AFASTADOR) {

        int valor = ponto.getValor();

        if (ponto.getDistancia() / AFASTADOR > 0) {
            valor = (ponto.getValor() / (ponto.getDistancia() / AFASTADOR));
        }

        return valor;
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

                        int p1 = direita.getDistancia();
                        int p2 = esquerda.getDistancia();
                        int p3 = acima.getDistancia();
                        int p4 = abaixo.getDistancia();

                        int distancia = p1;
                        int valor = direita.getValor();

                        String direcao = "DIREITA";

                        if (p2 < distancia) {
                            distancia = p2;
                            valor = esquerda.getValor();
                            direcao = "ESQUERDA";
                        }
                        if (p3 < distancia) {
                            distancia = p3;
                            valor = acima.getValor();
                            direcao = "ACIMA";
                        }
                        if (p4 < distancia) {
                            distancia = p4;
                            valor = abaixo.getValor();
                            direcao = "ABAIXO";
                        }

                        normalizador.adicionar(valor);
                        massa.setValor(x, y, valor);

                        String v = fmt.print(" PONTO ( {esq5} , {esq5} ) :: {esq9} com {esq5} em distancia {esq5}", x, y, direcao, valor  , distancia);

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

                        String v = fmt.print(" PONTO ( {esq5} , {esq5} ) :: {esq9} com {esq5} em distancia {esq5}", x, y, direcao, valor  , distancia);

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

                        String v = fmt.print(" PONTO ( {esq5} , {esq5} ) :: {esq9} com {esq5} em distancia {esq5}", x, y, direcao, valor  , distancia);

                        progresso.emitir((y * tectonica.getAltura()) + x, v);

                    } else {
                        System.out.println("FALTOU !!");
                    }


                }

            }
        });


    }

    public static void reduzir(Massas tectonica, int TECTONICA_VALOR, Massas massa, Normalizador normalizador, ArrayList<PontoIDW> eixos, int AFASTADOR) {


        Progressante progresso = new Progressante(massa.getAltura() * massa.getLargura());

        tectonica.paraCadaPonto(new CadaPonto() {
            @Override
            public void onPonto(int x, int y) {

                if (tectonica.getValor(x, y) == TECTONICA_VALOR) {

                    int lado_x = x + 1;
                    int lado_y = y;

                    int valor = massa.getValor(x, y);

                    if (tectonica.isTerra(lado_x, lado_y)) {

                        int lado = massa.getValor(lado_x, lado_y);

                        normalizador.adicionar(valor);
                        massa.setValor(x, y, valor);

                        int dif = valor - lado;

                        if (dif>100){
                            String v = fmt.print(" PONTO ( {} , {} ) :: {} com {} ", x, y, valor, dif);
                            progresso.emitir((y * tectonica.getAltura()) + x, v);

                            massa.setValor(x, y, valor-50);

                        }

                    }

                }

            }
        });


    }

}
