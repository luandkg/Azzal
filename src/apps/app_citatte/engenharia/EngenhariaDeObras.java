package apps.app_citatte.engenharia;

import apps.app_attuz.Ferramentas.Espaco2D;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.geometria.Ponto;
import libs.azzal.utilitarios.Cor;
import libs.luan.Lista;
import libs.luan.fmt;

public class EngenhariaDeObras {


    public static void organizar_cruzamentos(Lista<AvenidaViaria> avenidas) {

        fmt.print("ORGANIZANDO CRUZAMENTOS : LIMPANDO...");

        for (AvenidaViaria av1 : avenidas) {
            av1.getConexoes().limpar();
        }



        fmt.print("ORGANIZANDO CRUZAMENTOS : PROCESSAR");

        int p_25 = avenidas.getQuantidade() / 4;
        int p_50 = p_25 + p_25;
        int p_75 = p_50 + p_25;

        int i = 0;

        for (AvenidaViaria av1 : avenidas) {
            i += 1;
            for (AvenidaViaria av2 : avenidas) {
                if (av1.getID() != av2.getID()) {
                    av1.buscar_cruzamentos(av2);
                }
            }

            if (i == p_25) {
                fmt.print("ORGANIZANDO CRUZAMENTOS : 25.0 %");
            } else if (i == p_50) {
                fmt.print("ORGANIZANDO CRUZAMENTOS : 50.0 %");
            } else if (i == p_75) {
                fmt.print("ORGANIZANDO CRUZAMENTOS : 75.0 %");
            }
        }

        fmt.print("ORGANIZANDO CRUZAMENTOS : 100.0 %");

    }


    public static boolean is_tudo_preto(Renderizador mCidade, int px, int py, int largura, int altura) {
        boolean ret = true;

        Cores mCores = new Cores();

        for (int y = py; y <= py + altura; y++) {
            for (int x = px; x <= px + largura; x++) {

                if (!mCidade.getPixel(x, y).igual(mCores.getPreto())) {
                    ret = false;
                    break;
                }


            }
        }

        return ret;
    }


    public static Lista<AvenidaViaria> aumentar_avenida_vertical(Lista<AvenidaViaria> avenidas, Renderizador mCidade, boolean debug) {

        Cores mCores = new Cores();

        Lista<AvenidaViaria> mais_avenidas = new Lista<AvenidaViaria>();

        for (AvenidaViaria avenida : avenidas) {

            if (avenida.isVertical() && avenida.getCruzamentos() > 1) {
                if (avenida.isTerminal()) {


                    int pos_x = avenida.getX();
                    int pos_y = avenida.getPontos().get(avenida.getPontos().getQuantidade() - 1).getY();

                    boolean enc = false;
                    int menor_distancia = 0;

                    for (int proc_y = pos_y; proc_y < (pos_y + 100); proc_y++) {
                        boolean enc_nessa = false;
                        for (AvenidaViaria av : avenidas) {
                            if (av.getID() != avenida.getID() && av.estaEm(pos_x, proc_y)) {
                                menor_distancia = (proc_y - pos_y);
                                enc = true;
                                enc_nessa = true;
                                break;
                            }
                        }
                        if (enc_nessa) {
                            break;
                        }
                    }

                    if (enc) {
                        if (debug) {
                            mCidade.drawRect_Pintado(pos_x + 5, pos_y, 5, 5, mCores.getBranco());
                            mCidade.drawRect_Pintado(pos_x, pos_y, 1, menor_distancia, mCores.getBranco());
                        }

                        if (menor_distancia > 0) {
                            AvenidaViaria nova_avenida = new AvenidaViaria(0);

                            for (int mais_y = pos_y; mais_y < (pos_y + menor_distancia); mais_y++) {
                                nova_avenida.adicionar(new Ponto(pos_x, mais_y));
                            }

                            mais_avenidas.adicionar(nova_avenida);
                        }
                    } else {
                        //     mCidade.drawRect_Pintado(avenida.getPontos().get(avenida.getPontos().getQuantidade() - 1).getX(), avenida.getPontos().get(avenida.getPontos().getQuantidade() - 1).getY(), 5, 5, mCores.getBranco());
                    }


                    // System.out.println(pos_y + " - DESCEU :: " + menor_distancia);

                }
            }

        }

        return mais_avenidas;
    }

    public static Lista<AvenidaViaria> aumentar_avenida_horizontal(Lista<AvenidaViaria> avenidas, Renderizador mCidade, boolean debug) {

        Cores mCores = new Cores();

        Lista<AvenidaViaria> mais_avenidas = new Lista<AvenidaViaria>();

        for (AvenidaViaria avenida : avenidas) {

            if (avenida.isHorizontal() && avenida.getCruzamentos() > 1) {
                if (avenida.isTerminal()) {


                    int pos_x = avenida.getPontos().get(avenida.getPontos().getQuantidade() - 1).getX();
                    int pos_y = avenida.getY();

                    mCidade.drawRect_Pintado(pos_x + 5, pos_y, 5, 5, mCores.getBranco());

                    boolean enc = false;
                    int menor_distancia = 0;

                    for (int proc_y = pos_y; proc_y < (pos_y + 100); proc_y++) {
                        boolean enc_nessa = false;
                        for (AvenidaViaria av : avenidas) {
                            if (av.getID() != avenida.getID() && av.estaEm(pos_x, proc_y)) {
                                menor_distancia = (proc_y - pos_y);
                                enc = true;
                                enc_nessa = true;
                                break;
                            }
                        }
                        if (enc_nessa) {
                            break;
                        }
                    }

                    if (enc) {
                        if (debug) {
                            mCidade.drawRect_Pintado(pos_x, pos_y, 1, menor_distancia, mCores.getBranco());
                        }

                        if (menor_distancia > 0) {
                            AvenidaViaria nova_avenida = new AvenidaViaria(0);

                            for (int mais_y = pos_y; mais_y < (pos_y + menor_distancia); mais_y++) {
                                nova_avenida.adicionar(new Ponto(pos_x, mais_y));
                            }

                            mais_avenidas.adicionar(nova_avenida);
                        }
                    } else {
                        //     mCidade.drawRect_Pintado(avenida.getPontos().get(avenida.getPontos().getQuantidade() - 1).getX(), avenida.getPontos().get(avenida.getPontos().getQuantidade() - 1).getY(), 5, 5, mCores.getBranco());
                    }


                    // System.out.println(pos_y + " - DESCEU :: " + menor_distancia);

                } else if (avenida.isPrimaria()) {


                    int pos_x = avenida.getPontos().get(0).getX();
                    int pos_y = avenida.getY();

                    mCidade.drawRect_Pintado(pos_x - 5, pos_y + 5, 5, 5, mCores.getBranco());


                    boolean enc = false;
                    int menor_distancia = 0;

                    for (int proc_y = pos_y; proc_y > (pos_y - 100); proc_y--) {
                        boolean enc_nessa = false;
                        for (AvenidaViaria av : avenidas) {
                            if (av.getID() != avenida.getID() && av.estaEm(pos_x, proc_y)) {
                                menor_distancia = (pos_y - proc_y);
                                //  fmt.print("AH ->> DIREITA :: {}",menor_distancia);
                                enc = true;
                                enc_nessa = true;
                                break;
                            }
                        }
                        if (enc_nessa) {
                            break;
                        }
                    }

                    if (enc) {

                        if (debug) {
                            mCidade.drawRect_Pintado(pos_x, pos_y - menor_distancia, 1, menor_distancia, mCores.getBranco());
                        }

                        if (menor_distancia > 0) {
                            AvenidaViaria nova_avenida = new AvenidaViaria(0);

                            for (int menos_y = 0; menos_y < menor_distancia; menos_y++) {
                                nova_avenida.adicionar(new Ponto(pos_x, pos_y - menos_y));
                            }

                            mais_avenidas.adicionar(nova_avenida);


                        }
                    } else {
                        //     mCidade.drawRect_Pintado(avenida.getPontos().get(avenida.getPontos().getQuantidade() - 1).getX(), avenida.getPontos().get(avenida.getPontos().getQuantidade() - 1).getY(), 5, 5, mCores.getBranco());
                    }

                }
            }

        }

        return mais_avenidas;
    }


    public static boolean verLinhaFrente(Renderizador render, int px, int py, int ate, Cor eCor) {

        boolean enc = false;

        for (int indo_x = px; indo_x < (px + ate); indo_x++) {
            if (render.getPixel(indo_x, py).igual(eCor)) {
                enc = true;
                break;
            }
        }

        return enc;

    }


    public static boolean verLinhaAtras(Renderizador render, int px, int py, int ate, Cor eCor) {

        boolean enc = false;

        for (int indo_x = (px - ate); indo_x < (px); indo_x++) {
            if (render.getPixel(indo_x, py).igual(eCor)) {
                enc = true;
                break;
            }
        }

        return enc;

    }


    public static boolean verColunaFrente(Renderizador render, int px, int py, int ate, Cor eCor) {

        boolean enc = false;

        for (int indo_y = py; indo_y < (py + ate); indo_y++) {
            if (render.getPixel(px, indo_y).igual(eCor)) {
                enc = true;
                break;
            }
        }

        return enc;

    }


    public static boolean verColunaAtras(Renderizador render, int px, int py, int ate, Cor eCor) {

        boolean enc = false;

        for (int indo_y = (py - ate); indo_y < (py); indo_y++) {
            if (render.getPixel(px, indo_y).igual(eCor)) {
                enc = true;
                break;
            }
        }

        return enc;

    }


    public static int contarLinhaFrente(Renderizador render, int px, int py, int ate, Cor eCor) {

        int quantidade = 0;

        for (int indo_x = px; indo_x < (px + ate); indo_x++) {
            if (render.getPixel(indo_x, py).igual(eCor)) {
                break;
            }
            quantidade += 1;
        }

        return quantidade;

    }

    public static int contarLinhaAtras(Renderizador render, int px, int py, int ate, Cor eCor) {

        int quantidade = 0;

        for (int indo_x = (px - ate); indo_x < (px); indo_x++) {
            if (render.getPixel(indo_x, py).igual(eCor)) {
                break;
            }
            quantidade += 1;
        }

        return quantidade;

    }


    public static int contarColunaFrente(Renderizador render, int px, int py, int ate, Cor eCor) {

        int quantidade = 0;

        for (int indo_y = py; indo_y < (py + ate); indo_y++) {
            if (render.getPixel(px, indo_y).igual(eCor)) {
                break;
            }
            quantidade += 1;
        }

        return quantidade;

    }


    public static int contarColunaAtras(Renderizador render, int px, int py, int ate, Cor eCor) {

        int quantidade = 0;

        for (int indo_y = (py - ate); indo_y < (py); indo_y++) {
            if (render.getPixel(px, indo_y).igual(eCor)) {
                break;
            }
            quantidade += 1;
        }

        return quantidade;

    }


    public static int contar_avenidas(Lista<AvenidaViaria> avenidas, int px, int py, int raio) {

        int contagem = 0;

        for (AvenidaViaria av1 : avenidas) {

            for (Ponto p1 : av1.getPontos()) {
                int aproximando = Espaco2D.distancia_entre_pontos(p1, new Ponto(px, py));
                if (aproximando <= raio) {
                    contagem += 1;
                    break;
                }
            }

        }


        return contagem;

    }
}
