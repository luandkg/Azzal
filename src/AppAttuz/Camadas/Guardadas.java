package AppAttuz.Camadas;

import AppAttuz.Ferramentas.AlgoritmosDeMapa;
import AppAttuz.Ferramentas.Escala;
import AppAttuz.Ferramentas.Espaco2D;
import AppAttuz.Ferramentas.Pintor;
import AppAttuz.IDW.PontoIDW;
import AppAttuz.Mapa.Local;
import AppAttuz.MapaUtilitario;
import AppAttuz.Ferramentas.Normalizador;
import Azzal.Formatos.Ponto;
import Imaginador.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Guardadas {

    public static void criar(String LOCAL) {

        Massas massa = new Massas(LOCAL,0,1);

        System.out.println("Tudo :: " + massa.getContagem());

        System.out.println("Agua  :: " + massa.getContagemAgua() + " -->> " + massa.getProporcaoAgua());
        System.out.println("Terra :: " + massa.getContagemTerra() + " -->> " + massa.getProporcaoTerra());


        BufferedImage mapa = ImageUtils.getImagem("/home/luan/Imagens/Mapas/terra.png");


        Escala mRelevo = new Escala();

        mRelevo.set(0, Color.WHITE.getRGB());
        mRelevo.set(1, Color.BLACK.getRGB());

        mRelevo.set(10, new Color(141, 0, 0).getRGB());
        mRelevo.set(9, new Color(141, 0, 0).getRGB());
        mRelevo.set(8, new Color(141, 0, 0).getRGB());
        mRelevo.set(7, new Color(241, 23, 20).getRGB());
        mRelevo.set(6, new Color(247, 130, 0).getRGB());
        mRelevo.set(5, new Color(251, 167, 92).getRGB());
        mRelevo.set(4, new Color(253, 189, 130).getRGB());
        mRelevo.set(3, new Color(255, 243, 181).getRGB());
        mRelevo.set(2, new Color(111, 173, 114).getRGB());


     //   ImageUtils.exportar(Pintor.colorir(mapa, massa, mRelevo), Relevo.getArquivo(1));

        marcadores(mapa, massa, mRelevo, 7, 7, "relevo.txt");

      //  ImageUtils.exportar(Pintor.colorir(mapa, massa, mRelevo), Relevo.getArquivo(2));

        equalizar(massa);

       // ImageUtils.exportar(Pintor.colorir(mapa, massa, mRelevo), Relevo.getArquivo(3));

        completar(massa, 200);

       // ImageUtils.exportar(Pintor.colorir(mapa, massa, mRelevo), Relevo.getArquivo(3));


        boolean deve = suavizar(massa, 200);

       // String arq = Relevo.getArquivo(4);

       // ImageUtils.exportar(Pintor.colorir(mapa, massa, mRelevo), arq);

        int s = 0;

        while (deve) {
            System.out.println("SUAVIZAR NOVAMENTE :: " + s);
            deve = suavizar(massa, 200);
         //   ImageUtils.exportar(Pintor.colorir(mapa, massa, mRelevo), arq);
            s += 1;
            if (s > 100) {
                break;
            }
        }


        ImageUtils.exportar(Pintor.colorir(mapa, massa, mRelevo), "/home/luan/Imagens/Mapas/relevo/relevo.png");

        // ObterPaletaDeCores.get("/home/luan/Imagens/escala_relevo.png","/home/luan/Imagens/Mapas/relevo/paleta_relevo.png");

    }

    public static void emAreaFixa(BufferedImage mapa, int px, int py, Massas massa, Escala mRelevo, int centro, int ate, int quantos) {

        AlgoritmosDeMapa am = new AlgoritmosDeMapa();


        ArrayList<Ponto> pt_montanhas = new ArrayList<Ponto>();
        pt_montanhas.add(new Ponto(px, py));

        for (int q = 0; q < quantos; q++) {
            pt_montanhas.add(new Ponto(px + am.sorte(20), py + am.sorte(20)));
        }


        int raio = am.sorte(4) + 2;


        ArrayList<Ponto> n2 = am.expandir_em4(pt_montanhas, raio);
        ArrayList<Ponto> n3 = am.expandir_em4(n2, 10);
        ArrayList<Ponto> n4 = am.expandir_em4(n3, 15);
        ArrayList<Ponto> n5 = am.expandir_em4(n4, 20);

        if (centro - 2 >= ate) {
            for (Ponto ePonto : am.getArea1(am.contorno_de_area(n5))) {
                massa.pintar_se_terra(ePonto.getX(), ePonto.getY(), centro - 2);
            }
        }

        if (centro - 2 >= ate) {
            for (Ponto ePonto : am.getArea1(am.contorno_de_area(n3))) {
                massa.pintar_se_terra(ePonto.getX(), ePonto.getY(), centro - 1);
            }
        }


        for (Ponto ePonto : am.getArea1(am.contorno_de_area(n3))) {
            massa.pintar_se_terra(ePonto.getX(), ePonto.getY(), centro);
        }


     //   ImageUtils.exportar(Pintor.colorir(mapa, massa, mRelevo),Relevo. getArquivo(1));

        emGrupo(massa, n5, centro);
        emGrupo(massa, n4, centro);
        emGrupo(massa, n3, centro);
        emGrupo(massa, n2, centro);

    }

    public static void emGrupo(Massas massa, ArrayList<Ponto> pontos, int v) {

        Random sorte = new Random();
        int g = v * 100;

        for (Ponto ePonto : pontos) {
            massa.pintar_se_terra(ePonto.getX(), ePonto.getY(), g + (sorte.nextInt(100)));
        }
    }

    public static void algoritmo_proximidade(Massas tectonica, int TECTONICA_VALOR, Massas massa, Normalizador normalizador, ArrayList<PontoIDW> eixos) {

        System.out.println("TERRA -->> Eixos :: " + eixos.size());

        normalizador.zerar();

        for (int y = 0; y < massa.getAltura(); y++) {
            for (int x = 0; x < massa.getLargura(); x++) {

                if (tectonica.getValor(x, y) == TECTONICA_VALOR) {

                    int ponto_valor = massa.getValor(x, y);

                    if (ponto_valor >= 1) {

                        double soma = 0;
                        boolean conseguiu = false;

                        int menor = 0;
                        int menor_valor = 0;

                        int menor_ant = 0;
                        int menor_valor_ant = 0;

                        int tam = 10;

                        int contagem = 0;
                        int somando = 0;

                        for (int prox_y = y - tam; prox_y < y + tam; prox_y++) {
                            for (int prox_x = x - tam; prox_x < x + tam; prox_x++) {

                                if (tectonica.getValor(prox_x, prox_y) == TECTONICA_VALOR) {

                                    if (x == prox_x && y == prox_y) {

                                    } else {

                                        int ponto_valor_prox = massa.getValor(prox_x, prox_y);

                                        if (ponto_valor_prox > 1) {

                                            int distancia = Espaco2D.distancia_entre_pontos(x, y, prox_x, prox_y);

                                            contagem += 1;
                                            somando += ponto_valor_prox;

                                            if (distancia < 5) {


                                                if (!conseguiu) {
                                                    conseguiu = true;
                                                    menor = distancia;
                                                    menor_valor = ponto_valor_prox;

                                                    menor_ant = distancia;
                                                    menor_valor_ant = ponto_valor_prox;
                                                } else {
                                                    if (distancia > menor) {

                                                        menor_ant = menor;
                                                        menor_valor_ant = menor_valor;

                                                        menor = distancia;
                                                        menor_valor = ponto_valor_prox;
                                                    }
                                                }

                                            }


                                        }


                                    }

                                }

                            }
                        }

                        if (conseguiu) {

                            if (contagem > 0) {
                                menor_valor = somando / contagem;
                            }
                            normalizador.adicionar(menor_valor);
                            massa.setValor(x, y, menor_valor);


                        }


                    }


                }

            }
        }
    }


    public static void marcadores(BufferedImage mapa, Massas massa, Escala mRelevo, int centro, int redor, String arq) {

        arq = "/home/luan/Imagens/Mapas/dados/" + arq;

        ArrayList<Local> todos = MapaUtilitario.toLocalNormalizado(MapaUtilitario.obterLocais(arq));

        marcar(mapa, massa, mRelevo, MapaUtilitario.getPontosComNome(todos, String.valueOf(mRelevo.get(2))), 2);
        marcar(mapa, massa, mRelevo, MapaUtilitario.getPontosComNome(todos, String.valueOf(mRelevo.get(3))), 3);
        marcar(mapa, massa, mRelevo, MapaUtilitario.getPontosComNome(todos, String.valueOf(mRelevo.get(4))), 4);
        marcar(mapa, massa, mRelevo, MapaUtilitario.getPontosComNome(todos, String.valueOf(mRelevo.get(5))), 5);
        marcar(mapa, massa, mRelevo, MapaUtilitario.getPontosComNome(todos, String.valueOf(mRelevo.get(6))), 6);
        marcar(mapa, massa, mRelevo,MapaUtilitario. getPontosComNome(todos, String.valueOf(mRelevo.get(7))), 7);
        marcar(mapa, massa, mRelevo, MapaUtilitario.getPontosComNome(todos, String.valueOf(mRelevo.get(8))), 8);

    }

    public static void marcar(BufferedImage mapa, Massas massa, Escala mRelevo, ArrayList<Ponto> pt_montanhas, int v) {

        AlgoritmosDeMapa am = new AlgoritmosDeMapa();

        System.out.println(v + " -->> " + pt_montanhas.size());

        ArrayList<Ponto> n2 = am.expandir_em4(pt_montanhas, 10);
        ArrayList<Ponto> n3 = am.expandir_em4(n2, 20);
        ArrayList<Ponto> n4 = am.expandir_em4(n3, 20);
        ArrayList<Ponto> n5 = am.expandir_em4(n4, 20);

        ArrayList<Ponto> liga = am.contorno_de_area_de(n3, 50);

        for (Ponto ePonto : n5) {
            massa.pintar_se_terra(ePonto.getX(), ePonto.getY(), v);
        }

        for (Ponto ePonto : liga) {
            massa.pintar_se_terra(ePonto.getX(), ePonto.getY(), v);
        }
    }


    public static void equalizar(Massas massa) {


        Random sorte = new Random();

        ArrayList<Ponto> zerados = new ArrayList<Ponto>();

        for (int y = 0; y < massa.getAltura(); y++) {
            for (int x = 0; x < massa.getLargura(); x++) {
                int acc = massa.getValor(x, y);

                if (acc == 1) {
                    if (massa.isTerra(x, y)) {
                        zerados.add(new Ponto(x, y));
                    }
                }

                if (acc >= 2) {

                    //int base = (acc * 100) + sorte.nextInt(50);
                    int base = (acc * 100);

                    massa.pintar_se_terra(x, y, base);

                }
            }
        }

        int quantos = zerados.size();
        int mais = 0;

        int anterior = 1;
        int quadrado = 5;

        while (anterior > 0) {

            quantos = 0;
            mais += 1;

            int agora = getTerraVazia(massa);

            if (anterior == agora) {
                q20(massa, quadrado);
                quadrado += 1;
            }

            System.out.println("Equalizando :: " + mais + " -->> Quantidade :: " + agora + " em " + quadrado);


            anterior = agora;
            if (mais > 200) {
                break;
            }

            for (Ponto zerado : zerados) {

                int x = zerado.getX();
                int y = zerado.getY();

                int acc = massa.getValor(x, y);

                if (acc == 1) {

                    quantos += 1;

                    int acima = 0;
                    int abaixo = 0;
                    int esquerda = 0;
                    int direita = 0;

                    if (massa.isTerra(x, y - 1)) {
                        acima = massa.getValor(x, y - 1);
                    }
                    if (massa.isTerra(x, y + 1)) {
                        abaixo = massa.getValor(x, y + 1);
                    }
                    if (massa.isTerra(x + 1, y)) {
                        direita = massa.getValor(x + 1, y);
                    }
                    if (massa.isTerra(x - 1, y)) {
                        esquerda = massa.getValor(x - 1, y);
                    }


                    if (acima >= 200 && abaixo >= 200 && esquerda >= 200 && direita >= 200 && acima <= 1000 && abaixo <= 1000 && esquerda <= 1000 && direita <= 1000) {

                        // System.out.println("Acima    :: " + acima + " Abaixo  :: " + abaixo);
                        // System.out.println("Esquerda :: " + esquerda + " Direita :: " + direita);

                        int estacao = acima;
                        if (abaixo > estacao) {
                            estacao = abaixo;
                        }
                        if (direita > estacao) {
                            estacao = direita;
                        }
                        if (esquerda > estacao) {
                            estacao = esquerda;
                        }

                        //  System.out.println("Ref :: " + mais + " -->> Estacao :: " + estacao);

                        if (estacao > 200) {
                            massa.pintar_se_terra(x, y, estacao);
                        }

                    } else if (acima >= 200 && abaixo >= 200 && acima <= 1000 && abaixo <= 1000) {

                        int estacao = acima;
                        if (abaixo > estacao) {
                            estacao = abaixo;
                        }
                        if (estacao > 200) {
                            massa.pintar_se_terra(x, y, estacao);
                        }

                    } else if (esquerda >= 200 && direita >= 200 && esquerda <= 1000 && direita <= 1000) {

                        int estacao = direita;

                        if (esquerda > estacao) {
                            estacao = esquerda;
                        }
                        if (estacao > 200) {
                            massa.pintar_se_terra(x, y, estacao);
                        }
                    }

                    //  massa.pintar_se_terra(x, y , 200);


                }
            }


        }

    }

    public static boolean suavizar(Massas massa, int valor) {

        Random eSorte = new Random();

        boolean suavizou = false;

        for (int y = 0; y < massa.getAltura(); y++) {
            for (int x = 0; x < massa.getLargura(); x++) {

                int v = eSorte.nextInt(100);

                int v2 = eSorte.nextInt(50);

                if (v >= 0 && v < 25) {
                    suave(massa, x, y, -1, 0, valor - v2, 1, 5);
                    suavizou = true;
                } else if (v >= 25 && v < 50) {
                    suave(massa, x, y, 1, 0, valor - v2, 2, 5);
                    suavizou = true;
                } else if (v >= 50 && v < 75) {
                    suave(massa, x, y, 0, -1, valor - v2, 3, 5);
                    suavizou = true;
                } else if (v >= 75 && v < 100) {
                    suave(massa, x, y, 0, 1, valor - v2, 4, 5);
                    suavizou = true;
                }


            }
        }

        boolean ret = false;

        for (int y = 0; y < massa.getAltura(); y++) {
            for (int x = 0; x < massa.getLargura(); x++) {

                ret = deve_suave(massa, x, y, -1, 0, valor, 1);
                if (ret) {
                    break;
                }
                ret = deve_suave(massa, x, y, 1, 0, valor, 2);
                if (ret) {
                    break;
                }
                ret = deve_suave(massa, x, y, 0, -1, valor, 3);
                if (ret) {
                    break;
                }
                ret = deve_suave(massa, x, y, 0, 1, valor, 4);
                if (ret) {
                    break;
                }
            }
            if (ret) {
                break;
            }
        }

        if (!suavizou) {
            ret = false;
        }

        return ret;
    }

    public static void suave(Massas massa, int x, int y, int mais_x, int mais_y, int valor, int t, int v) {


        if (massa.isTerra(x, y) && massa.isTerra(x + mais_x, y + mais_y)) {

            int a1 = massa.getValor(x, y);
            int a2 = massa.getValor(x + mais_x, y + mais_y);


            if (a1 - a2 >= valor) {

                int d = a1 - v;

                if (d < 200) {
                    d = 200;
                }


                massa.pintar_se_terra(x + mais_x, y + mais_y, d);
                //System.out.println("Suavizando T" + t + " (" + x + "," + y + " ) -->> " + a1);

            } else if (a2 - a1 >= valor) {

                int f = a2 + v;

                if (f < 200) {
                    f = 200;
                }


                massa.pintar_se_terra(x + mais_x, y + mais_y, f);
                // System.out.println("Suavizando T" + t + " (" + x + "," + y + " ) -->> " + a1);

            }

        }

    }

    public static boolean deve_suave(Massas massa, int x, int y, int mais_x, int mais_y, int valor, int t) {


        boolean ret = false;

        if (massa.isTerra(x, y) && massa.isTerra(x + mais_x, y + mais_y)) {

            int a1 = massa.getValor(x, y);
            int a2 = massa.getValor(x + mais_x, y + mais_y);


            if (a1 - a2 >= valor) {
                ret = true;
            } else if (a2 - a1 >= valor) {
                ret = true;
            }

        }

        return ret;
    }


    public static void completar(Massas massa, int valor) {

        for (int y = 0; y < massa.getAltura(); y++) {
            for (int x = 0; x < massa.getLargura(); x++) {
                int acc = massa.getValor(x, y);
                if (acc == 1) {
                    if (massa.isTerra(x, y)) {
                        massa.pintar_se_terra(x, y, valor);
                    }
                }

            }
        }
    }

    public static int getTerraPintado(Massas massa) {
        int v = 0;

        for (int y = 0; y < massa.getAltura(); y++) {
            for (int x = 0; x < massa.getLargura(); x++) {
                if (massa.isTerra(x, y)) {
                    int acc = massa.getValor(x, y);

                    if (acc > 200) {
                        v += 1;
                    }
                }
            }
        }

        return v;
    }

    public static int getTerraVazia(Massas massa) {
        int v = 0;

        for (int y = 0; y < massa.getAltura(); y++) {
            for (int x = 0; x < massa.getLargura(); x++) {
                if (massa.isTerra(x, y)) {
                    int acc = massa.getValor(x, y);

                    if (acc == 1) {
                        v += 1;
                    }
                }
            }
        }

        return v;
    }

    public static void q20(Massas massa, int t) {

        AlgoritmosDeMapa am = new AlgoritmosDeMapa();

        int tam = t;

        for (int y = 0; y < massa.getAltura(); y += tam) {
            for (int x = 0; x < massa.getLargura(); x += tam) {

                int q = 0;
                int s = 0;


                ArrayList<Ponto> pontos_zerados = new ArrayList<Ponto>();

                int maior = 0;
                int menor = 0;

                for (int y1 = 0; y1 < tam; y1++) {
                    for (int x1 = 0; x1 < tam; x1++) {

                        int acc = massa.getValor(x + x1, y + y1);
                        if (acc == 1) {
                            pontos_zerados.add(new Ponto(x + x1, y + y1));
                        }
                        if (acc > 1) {
                            q += 1;

                            s += acc;

                            if (acc > maior) {
                                maior = acc;
                            }
                            if (menor == 0) {
                                menor = acc;
                            }
                            if (acc < menor && acc > 200) {
                                menor = acc;
                            }
                        }


                    }
                }

                if (pontos_zerados.size() > 0) {

                    menor = menor - 5;

                    if (menor < 200) {
                        // menor = 200;
                    }
                    for (Ponto ponto : pontos_zerados) {
                        if (menor >= 200) {
                            massa.pintar_se_terra(ponto.getX(), ponto.getY(), menor);
                        }
                    }


                }

            }


        }
    }

}
