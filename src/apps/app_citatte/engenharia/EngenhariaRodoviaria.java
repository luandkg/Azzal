package apps.app_citatte.engenharia;

import apps.app_attuz.Ferramentas.GPS;
import apps.app_citatte.ListaDePontos;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.geometria.Ponto;
import libs.azzal.utilitarios.Cor;
import libs.luan.Lista;
import libs.luan.fmt;

import java.util.Random;

public class EngenhariaRodoviaria {

    public static final int DIRECAO_SUBIR = 1;
    public static final int DIRECAO_DESCER = 2;
    public static final int DIRECAO_ESQUERDA = 3;
    public static final int DIRECAO_DIREITA = 4;


    public static Lista<AvenidaViaria> criar_cidade(Renderizador mCidade) {

        Cores mCores = new Cores();

        Lista<AvenidaViaria> avenidas = EngenhariaRodoviaria.construir(mCidade);

        EngenhariaRodoviaria.draw_avenidas(mCidade, avenidas);

        fmt.print("AVENIDAS 1  -->> {}", avenidas.getQuantidade());

        avenidas = EngenhariaRodoviaria.procurar_avenidas(mCidade);
        if (avenidas.getQuantidade() > 0) {
            //   return;
        }

        fmt.print("AVENIDAS 2  -->> {}", avenidas.getQuantidade());

        boolean FEATURE_AUMENTAR_AVENIDAS_VERTICAIS = true;
        boolean FEATURE_AUMENTAR_AVENIDAS_HORIZONTAIS = true;

        if (FEATURE_AUMENTAR_AVENIDAS_VERTICAIS) {
            avenidas = EngenhariaRodoviaria.expandir_avenidas_verticalmente(avenidas, mCidade);
        }

        if (FEATURE_AUMENTAR_AVENIDAS_HORIZONTAIS) {
            avenidas = EngenhariaRodoviaria.expandir_avenidas_horizontalmente(avenidas, mCidade);
        }


        mCidade.limpar(mCores.getPreto());
        EngenhariaRodoviaria.draw_avenidas(mCidade, avenidas);

        if (avenidas.getQuantidade() > 0) {
            //return;
        }

        for (AvenidaViaria av1 : avenidas) {
            fmt.print("\tAVENIDA {}  -->> {}", av1.getID(), av1.getComprimento());
        }

        String local_assets = "/home/luan/assets";

        mCidade.exportarSemAlfa(local_assets + "/cidade_alpha.png");

        // PROCESSAR AVENIDAS SOLITARIAS

        fmt.print("PROCESSAR AVENIDAS SOLITARIAS");
        EngenhariaDeObras.organizar_cruzamentos(avenidas);

        Lista<AvenidaViaria> mais_avenidas = new Lista<AvenidaViaria>();

        int naz = 0;
        Random sorte = new Random();

        for (AvenidaViaria avenida : avenidas) {
            naz += 1;

            if (avenida.getCruzamentos() == 0) {

                mCidade.drawCirculo_Pintado(avenida.getX(), avenida.getY(), 10, mCores.getRosa());

                int ale_a = sorte.nextInt(avenida.getPontos().getQuantidade());
                int ale_b = sorte.nextInt(avenida.getPontos().getQuantidade());

                EngenhariaRodoviaria.anexar_proximo(mCidade, avenidas, 5 + sorte.nextInt(20), avenida.getPontos().get(ale_a).getCopia());
                EngenhariaRodoviaria.anexar_proximo(mCidade, avenidas, 5 + sorte.nextInt(20), avenida.getPontos().get(ale_b).getCopia());

            } else if (avenida.getCruzamentos() == 2) {

                int na = EngenhariaDeObras.contar_avenidas(avenidas, avenida.getX(), avenida.getY(), 60);
                fmt.print("{} Avenida -->> {} Redondenzas ", naz, na);

                if (na > 3) {
                    mCidade.drawCirculo_Pintado(avenida.getX(), avenida.getY(), 10, mCores.getAmarelo());
                } else {
                    mCidade.drawCirculo_Pintado(avenida.getX(), avenida.getY(), 10, mCores.getBranco());

                    if (avenida.isVertical()) {

                        int metade = (avenida.getComprimento()) / 2;
                        int met_y = avenida.getY() + metade;

                        AvenidaViaria nova_avenida = new AvenidaViaria(0);

                        int comp = 20 + sorte.nextInt(60);

                        if (sorte.nextInt(100) > 50) {
                            for (int ax = 0; ax < comp; ax++) {
                                nova_avenida.adicionar(new Ponto(avenida.getX() + ax, met_y));
                            }
                        } else {
                            for (int ax = 0; ax < comp; ax++) {
                                nova_avenida.adicionar(new Ponto(avenida.getX() - ax, met_y));
                            }
                        }


                        mais_avenidas.adicionar(nova_avenida);

                        fmt.print("Expandindo avenida vertical em {}:{} com {} ", avenida.getX(), met_y, comp);
                    } else if (avenida.isHorizontal()) {

                        int metade = (avenida.getComprimento()) / 2;
                        int met_x = avenida.getX() + metade;

                        AvenidaViaria nova_avenida = new AvenidaViaria(0);

                        int comp = 20 + sorte.nextInt(60);

                        if (sorte.nextInt(100) > 50) {
                            for (int ay = 0; ay < comp; ay++) {
                                nova_avenida.adicionar(new Ponto(met_x, avenida.getY() + ay));
                            }
                        } else {
                            for (int ay = 0; ay < comp; ay++) {
                                nova_avenida.adicionar(new Ponto(met_x, avenida.getY() - ay));
                            }
                        }


                        mais_avenidas.adicionar(nova_avenida);

                        fmt.print("Expandindo avenida horizontal em {}:{} com {} ", met_x, avenida.getY(), comp);

                    }

                }


            }
        }


        avenidas.adicionar_varios(mais_avenidas);

        int expansao = 0;

        for (AvenidaViaria av : mais_avenidas) {
            expansao += 1;
            fmt.print("Organizando expansao {} de {} ", expansao, mais_avenidas.getQuantidade());

            if (av.isHorizontal()) {
                EngenhariaRodoviaria.anexar_partes(mCidade, avenidas, 5 + sorte.nextInt(20), EngenhariaRodoviaria.DIRECAO_DESCER, av.getPontos().get(av.getPontos().getQuantidade() - 1));
            } else if (av.isVertical()) {
                EngenhariaRodoviaria.anexar_partes(mCidade, avenidas, 5 + sorte.nextInt(20), EngenhariaRodoviaria.DIRECAO_DIREITA, av.getPontos().get(av.getPontos().getQuantidade() - 1));
            }

            int ale = sorte.nextInt(av.getPontos().getQuantidade());

            EngenhariaRodoviaria.anexar_proximo(mCidade, avenidas, 5 + sorte.nextInt(20), av.getPontos().get(ale).getCopia());
            EngenhariaRodoviaria.anexar_proximo(mCidade, avenidas, 5 + sorte.nextInt(20), av.getPontos().get(ale).getCopia());


        }

        mCidade.exportarSemAlfa(local_assets + "/cidade_beta.png");

        mCidade.limpar(mCores.getPreto());
        EngenhariaRodoviaria.draw_avenidas(mCidade, avenidas);

        mCidade.exportarSemAlfa(local_assets + "/cidade_cota.png");

        fmt.print("Procurando avenidas - FORCA BRUTA !");

        avenidas = EngenhariaRodoviaria.procurar_avenidas(mCidade);


        return avenidas;
    }


    public static Lista<AvenidaViaria> construir(Renderizador mCidade) {

        Random sorte = new Random();

        CidadePlanejamento planta_da_cidade = new CidadePlanejamento();

        int executar_ciclos = (mCidade.getLargura() / 4) + (mCidade.getAltura() / 4);

        fmt.print("Ciclos = {}", executar_ciclos);

        int reducao = sorte.nextInt(executar_ciclos / 4);

        executar_ciclos = executar_ciclos - reducao;

        fmt.print("Ciclos = {}", executar_ciclos);


        // executar_ciclos = 50;

        planta_da_cidade.avenidas = new Lista<AvenidaViaria>();
        planta_da_cidade.direcao = GET_DIRECAO();
        planta_da_cidade.direcao_anterior = -1;
        planta_da_cidade.avenida_anterior = null;
        planta_da_cidade.inicio = new Ponto((sorte.nextInt(mCidade.getLargura() - (mCidade.getLargura() / 6))), (sorte.nextInt(mCidade.getAltura() - (mCidade.getAltura() / 6))));

        planta_da_cidade.limite_acima = (mCidade.getAltura() / 10);
        planta_da_cidade.limite_esquerda = (mCidade.getLargura() / 10);
        planta_da_cidade.maximo_direita = (mCidade.getLargura() - (3 * (mCidade.getLargura() / 10)));
        planta_da_cidade.maximo_descendo = (mCidade.getAltura() - (3 * (mCidade.getAltura() / 10)));


        while (executar_ciclos > 0) {

            construir_parte(planta_da_cidade);

            executar_ciclos -= 1;
        }

        return planta_da_cidade.avenidas;

    }


    public static int GET_DIRECAO() {

        Random sorte = new Random();

        int direcao = EngenhariaRodoviaria.DIRECAO_DIREITA;

        int v = sorte.nextInt(100);

        if (v >= 0 && v < 25) {
            direcao = EngenhariaRodoviaria.DIRECAO_SUBIR;
        } else if (v >= 25 && v < 50) {
            direcao = EngenhariaRodoviaria.DIRECAO_DESCER;
        } else if (v >= 50 && v < 75) {
            direcao = EngenhariaRodoviaria.DIRECAO_ESQUERDA;
        } else if (v >= 75) {
            direcao = EngenhariaRodoviaria.DIRECAO_DIREITA;
        }

        return direcao;

    }


    public static void anexar_partes(Renderizador mCidade, Lista<AvenidaViaria> avenidas, int executar_ciclos, int direcao_inicial, Ponto pt_inicio) {

        CidadePlanejamento planta_da_cidade = new CidadePlanejamento();

        planta_da_cidade.avenidas = avenidas;
        planta_da_cidade.direcao = direcao_inicial;
        planta_da_cidade.direcao_anterior = -1;
        planta_da_cidade.avenida_anterior = null;
        planta_da_cidade.inicio = pt_inicio;

        planta_da_cidade.limite_acima = (mCidade.getAltura() / 10);
        planta_da_cidade.limite_esquerda = (mCidade.getLargura() / 10);
        planta_da_cidade.maximo_direita = (mCidade.getLargura() - (3 * (mCidade.getLargura() / 10)));
        planta_da_cidade.maximo_descendo = (mCidade.getAltura() - (3 * (mCidade.getAltura() / 10)));


        while (executar_ciclos > 0) {

            construir_parte(planta_da_cidade);

            executar_ciclos -= 1;
        }


    }

    public static void anexar_proximo(Renderizador mCidade, Lista<AvenidaViaria> avenidas, int executar_ciclos, Ponto pt_inicio) {

        CidadePlanejamento planta_da_cidade = new CidadePlanejamento();

        Random sorte = new Random();

        planta_da_cidade.avenidas = avenidas;
        planta_da_cidade.direcao = 1 + sorte.nextInt(3);
        planta_da_cidade.direcao_anterior = -1;
        planta_da_cidade.avenida_anterior = null;
        planta_da_cidade.inicio = pt_inicio;


        planta_da_cidade.limite_acima = (mCidade.getAltura() / 10);
        planta_da_cidade.limite_esquerda = (mCidade.getLargura() / 10);
        planta_da_cidade.maximo_direita = (mCidade.getLargura() - (3 * (mCidade.getLargura() / 10)));
        planta_da_cidade.maximo_descendo = (mCidade.getAltura() - (3 * (mCidade.getAltura() / 10)));


        while (executar_ciclos > 0) {

            construir_parte(planta_da_cidade);

            executar_ciclos -= 1;
        }


    }

    public static void construir_parte(CidadePlanejamento planta_da_cidade) {

        Random sorte = new Random();

        if (planta_da_cidade.direcao == DIRECAO_DIREITA) {

            int comprimento = 30 + sorte.nextInt(60);
            // mCidade.drawLinha(inicio.getX(), inicio.getY(), inicio.getX() + comprimento, inicio.getY(), mCores.getAmarelo());

            AvenidaViaria avenida_corrente = null;
            if ((planta_da_cidade.direcao_anterior == DIRECAO_ESQUERDA || planta_da_cidade.direcao_anterior == DIRECAO_DIREITA) && planta_da_cidade.inicio.getY() == planta_da_cidade.avenida_anterior.getY()) {
                avenida_corrente = planta_da_cidade.avenida_anterior;
                //    fmt.print("CONTINUAR -->> {}",inicio.toString());
            } else {
                avenida_corrente = new AvenidaViaria(planta_da_cidade.avenidas.getQuantidade());
                planta_da_cidade.avenida_anterior = avenida_corrente;
                //   fmt.print("DIREITA  -->> {}",inicio.toString());
            }


            for (Ponto px : GPS.criarRotaReta(planta_da_cidade.inicio.getX(), planta_da_cidade.inicio.getY(), planta_da_cidade.inicio.getX() + comprimento, planta_da_cidade.inicio.getY())) {
                avenida_corrente.adicionar(px);
            }
            planta_da_cidade.avenidas.adicionar(avenida_corrente);

            planta_da_cidade.inicio = new Ponto(planta_da_cidade.inicio.getX() + (comprimento - 1), planta_da_cidade.inicio.getY());

            planta_da_cidade.direcao_anterior = planta_da_cidade.direcao;

            int mudar = sorte.nextInt(100);

            if (mudar >= 50) {
                planta_da_cidade.direcao = DIRECAO_SUBIR;
            } else if (mudar >= 25) {
                planta_da_cidade.direcao = DIRECAO_DESCER;
            } else {
                planta_da_cidade.direcao = DIRECAO_DIREITA;
            }

        } else if (planta_da_cidade.direcao == DIRECAO_ESQUERDA) {

            int comprimento = 30 + sorte.nextInt(60);

            if (planta_da_cidade.inicio.getX() - comprimento < planta_da_cidade.limite_esquerda) {
                planta_da_cidade.inicio.setX(planta_da_cidade.maximo_direita + sorte.nextInt(200));
            }

            // mCidade.drawLinha(inicio.getX(), inicio.getY(), inicio.getX() - comprimento, inicio.getY(), mCores.getAmarelo());


            AvenidaViaria avenida_corrente = null;
            if ((planta_da_cidade.direcao_anterior == DIRECAO_ESQUERDA || planta_da_cidade.direcao_anterior == DIRECAO_DIREITA) && planta_da_cidade.inicio.getY() == planta_da_cidade.avenida_anterior.getY()) {
                avenida_corrente = planta_da_cidade.avenida_anterior;
            } else {
                avenida_corrente = new AvenidaViaria(planta_da_cidade.avenidas.getQuantidade());
                planta_da_cidade.avenida_anterior = avenida_corrente;
            }


            for (Ponto px : GPS.criarRotaReta(planta_da_cidade.inicio.getX(), planta_da_cidade.inicio.getY(), planta_da_cidade.inicio.getX() - comprimento, planta_da_cidade.inicio.getY())) {
                avenida_corrente.adicionar(px);
            }
            planta_da_cidade.avenidas.adicionar(avenida_corrente);


            planta_da_cidade.inicio = new Ponto(planta_da_cidade.inicio.getX() - comprimento, planta_da_cidade.inicio.getY());
            planta_da_cidade.direcao_anterior = planta_da_cidade.direcao;

            int mudar = sorte.nextInt(100);

            if (mudar >= 50) {
                planta_da_cidade.direcao = DIRECAO_SUBIR;
            } else if (mudar >= 25) {
                planta_da_cidade.direcao = DIRECAO_DESCER;
            } else {
                planta_da_cidade.direcao = DIRECAO_ESQUERDA;
            }

        } else if (planta_da_cidade.direcao == DIRECAO_DESCER) {

            int comprimento = 30 + sorte.nextInt(60);
            // mCidade.drawLinha(inicio.getX(), inicio.getY(), inicio.getX(), inicio.getY() + comprimento, mCores.getAmarelo());


            AvenidaViaria avenida_corrente = null;
            if ((planta_da_cidade.direcao_anterior == DIRECAO_DESCER || planta_da_cidade.direcao_anterior == DIRECAO_SUBIR) && planta_da_cidade.inicio.getY() == planta_da_cidade.avenida_anterior.getY()) {
                avenida_corrente = planta_da_cidade.avenida_anterior;
            } else {
                avenida_corrente = new AvenidaViaria(planta_da_cidade.avenidas.getQuantidade());
                planta_da_cidade.avenida_anterior = avenida_corrente;
            }


            for (Ponto px : GPS.criarRotaReta(planta_da_cidade.inicio.getX(), planta_da_cidade.inicio.getY(), planta_da_cidade.inicio.getX(), planta_da_cidade.inicio.getY() + comprimento)) {
                avenida_corrente.adicionar(px);
            }
            planta_da_cidade.avenidas.adicionar(avenida_corrente);


            planta_da_cidade.inicio = new Ponto(planta_da_cidade.inicio.getX(), planta_da_cidade.inicio.getY() + comprimento);
            planta_da_cidade.direcao_anterior = planta_da_cidade.direcao;

            int mudar = sorte.nextInt(100);

            if (mudar >= 50) {
                planta_da_cidade.direcao = DIRECAO_SUBIR;
            } else if (mudar >= 25) {
                planta_da_cidade.direcao = DIRECAO_DIREITA;
            } else {
                planta_da_cidade.direcao = DIRECAO_DESCER;
            }

        } else if (planta_da_cidade.direcao == DIRECAO_SUBIR) {

            int comprimento = 30 + sorte.nextInt(60);

            if (planta_da_cidade.inicio.getY() - comprimento < planta_da_cidade.limite_acima) {
                planta_da_cidade.inicio.setY(200 + sorte.nextInt(planta_da_cidade.maximo_descendo));
            }

            //mCidade.drawLinha(inicio.getX(), inicio.getY(), inicio.getX(), inicio.getY() - comprimento, mCores.getAmarelo());

            AvenidaViaria avenida_corrente = null;
            if ((planta_da_cidade.direcao_anterior == DIRECAO_DESCER || planta_da_cidade.direcao_anterior == DIRECAO_SUBIR) && planta_da_cidade.inicio.getY() == planta_da_cidade.avenida_anterior.getY()) {
                avenida_corrente = planta_da_cidade.avenida_anterior;
            } else {
                avenida_corrente = new AvenidaViaria(planta_da_cidade.avenidas.getQuantidade());
                planta_da_cidade.avenida_anterior = avenida_corrente;
            }

            for (Ponto px : GPS.criarRotaReta(planta_da_cidade.inicio.getX(), planta_da_cidade.inicio.getY(), planta_da_cidade.inicio.getX(), planta_da_cidade.inicio.getY() - comprimento)) {
                avenida_corrente.adicionar(px);
            }
            planta_da_cidade.avenidas.adicionar(avenida_corrente);


            planta_da_cidade.inicio = new Ponto(planta_da_cidade.inicio.getX(), planta_da_cidade.inicio.getY() - comprimento);
            planta_da_cidade.direcao_anterior = planta_da_cidade.direcao;

            int mudar = sorte.nextInt(100);


            if (mudar >= 50) {
                planta_da_cidade.direcao = DIRECAO_ESQUERDA;
            } else if (mudar >= 25) {
                planta_da_cidade.direcao = DIRECAO_DIREITA;
            } else {
                planta_da_cidade.direcao = DIRECAO_SUBIR;
            }

        }

        if (planta_da_cidade.inicio.getX() < 0) {
            planta_da_cidade.direcao = DIRECAO_DIREITA;
        }

        if (planta_da_cidade.inicio.getY() < 0) {
            planta_da_cidade.direcao = DIRECAO_DESCER;
        }

        if (planta_da_cidade.inicio.getX() > planta_da_cidade.maximo_direita) {
            planta_da_cidade.direcao = DIRECAO_ESQUERDA;
        }

        if (planta_da_cidade.inicio.getY() > planta_da_cidade.maximo_descendo) {
            planta_da_cidade.direcao = DIRECAO_SUBIR;
        }


    }


    public static Lista<AvenidaViaria> expandir_avenidas_verticalmente(Lista<AvenidaViaria> avenidas, Renderizador mCidade) {

        Cores mCores = new Cores();

        // A1  --->>> INICIO
        EngenhariaDeObras.organizar_cruzamentos(avenidas);

        Lista<AvenidaViaria> mais_avenidas = EngenhariaDeObras.aumentar_avenida_vertical(avenidas, mCidade, false);
        fmt.print("ENGENHARIA ++ AVENIDAS  -->> {}", mais_avenidas.getQuantidade());

        avenidas.adicionar_varios(mais_avenidas);

        fmt.print("AVENIDAS 3  -->> {}", avenidas.getQuantidade());

        mCidade.limpar(mCores.getPreto());
        draw_avenidas(mCidade, avenidas);

        avenidas = procurar_avenidas(mCidade);
        fmt.print("AVENIDAS 4  -->> {}", avenidas.getQuantidade());


        // A1 --->> FIM

        return avenidas;
    }

    public static Lista<AvenidaViaria> expandir_avenidas_horizontalmente(Lista<AvenidaViaria> avenidas, Renderizador mCidade) {

        Cores mCores = new Cores();

        // A1  --->>> INICIO
        EngenhariaDeObras.organizar_cruzamentos(avenidas);

        Lista<AvenidaViaria> mais_avenidas = EngenhariaDeObras.aumentar_avenida_horizontal(avenidas, mCidade, false);
        fmt.print("ENGENHARIA ++ AVENIDAS  -->> {}", mais_avenidas.getQuantidade());

        avenidas.adicionar_varios(mais_avenidas);

        fmt.print("AVENIDAS 3  -->> {}", avenidas.getQuantidade());

        mCidade.limpar(mCores.getPreto());
        draw_avenidas(mCidade, avenidas);

        avenidas = procurar_avenidas(mCidade);
        fmt.print("AVENIDAS 4  -->> {}", avenidas.getQuantidade());


        // A1 --->> FIM
        return avenidas;
    }

    public static Lista<AvenidaViaria> procurar_avenidas(Renderizador mCidade) {
        Lista<AvenidaViaria> avenidas = new Lista<AvenidaViaria>();

        Cores mCores = new Cores();
        Cor BRANCO = mCores.getBranco();
        Cor PRETO = mCores.getPreto();

        int f_a_25 = mCidade.getAltura() / 4;
        int f_a_50 = f_a_25 + f_a_25;
        int f_a_75 = f_a_50 + f_a_25;

        int f_l_25 = mCidade.getLargura() / 4;
        int f_l_50 = f_l_25 + f_l_25;
        int f_l_75 = f_l_50 + f_l_25;

        for (int y = 0; y < mCidade.getAltura(); y++) {

            boolean enc = false;

            AvenidaViaria avenida_corrente = new AvenidaViaria(avenidas.getQuantidade());

            for (int x = 0; x < mCidade.getLargura(); x++) {

                Cor pixel = mCidade.getPixel(x, y);
                if (!enc) {
                    if (pixel.isDiferente(PRETO)) {
                        avenida_corrente.adicionar(new Ponto(x, y));
                        mCidade.drawPixel(x, y, mCores.getAmarelo());
                        enc = true;
                    }
                } else {
                    if (pixel.isDiferente(PRETO)) {
                        avenida_corrente.adicionar(new Ponto(x, y));
                        mCidade.drawPixel(x, y, mCores.getTurquesa());

                    } else {
                        enc = false;
                        if (avenida_corrente.getPontos().getQuantidade() > 2) {
                            avenidas.adicionar(avenida_corrente);
                        }
                        avenida_corrente = new AvenidaViaria(avenidas.getQuantidade());
                    }
                }


            }
            if (enc) {
                avenidas.adicionar(avenida_corrente);
            }

            if (y == f_a_25) {
                fmt.print("FORÇA BRUTA : ALTURA 25.0 %");
            } else if (y == f_a_50) {
                fmt.print("FORÇA BRUTA : ALTURA 50.0 %");
            } else if (y == f_a_75) {
                fmt.print("FORÇA BRUTA : ALTURA 75.0 %");
            }
        }

        fmt.print("FORÇA BRUTA : 50.0 %");


        for (int x = 0; x < mCidade.getLargura(); x++) {

            boolean enc = false;

            AvenidaViaria avenida_corrente = new AvenidaViaria(avenidas.getQuantidade());

            for (int y = 0; y < mCidade.getAltura(); y++) {

                Cor pixel = mCidade.getPixel(x, y);
                if (!enc) {
                    if (pixel.isDiferente(PRETO)) {
                        avenida_corrente.adicionar(new Ponto(x, y));
                        enc = true;
                    }
                } else {
                    if (pixel.isDiferente(PRETO)) {
                        avenida_corrente.adicionar(new Ponto(x, y));
                    } else {
                        enc = false;
                        if (avenida_corrente.getPontos().getQuantidade() > 2) {
                            avenidas.adicionar(avenida_corrente);
                        }
                        avenida_corrente = new AvenidaViaria(avenidas.getQuantidade());
                    }
                }


            }
            if (enc) {
                avenidas.adicionar(avenida_corrente);
            }

            if (x == f_l_25) {
                fmt.print("FORÇA BRUTA : LARGURA 25.0 %");
            } else if (x == f_l_50) {
                fmt.print("FORÇA BRUTA : LARGURA 50.0 %");
            } else if (x == f_l_75) {
                fmt.print("FORÇA BRUTA : LARGURA 75.0 %");
            }
        }

        fmt.print("FORÇA BRUTA : 100.0 %");

        return avenidas;
    }

    public static void draw_avenidas(Renderizador mCidade, Lista<AvenidaViaria> avenidas) {

        Cores mCores = new Cores();

        for (AvenidaViaria avenida : avenidas) {
            for (Ponto px : avenida.getPontos()) {
                mCidade.drawPixel(px.getX(), px.getY(), mCores.getBranco());
            }
        }

    }

    public static void draw_avenidas_amarelo(Renderizador mCidade, Lista<AvenidaViaria> avenidas) {

        Cores mCores = new Cores();

        draw_avenidas_com_cor(mCidade, avenidas, mCores.getAmarelo());
    }

    public static void draw_avenidas_com_cor(Renderizador mCidade, Lista<AvenidaViaria> avenidas, Cor eCor) {

        for (AvenidaViaria avenida : avenidas) {
            for (Ponto px : avenida.getPontos()) {
                mCidade.drawPixel(px.getX(), px.getY(), eCor);
            }
        }

    }


    public static void draw_avenida_com_cor(Renderizador mCidade, AvenidaViaria avenida, Cor eCor) {

        for (Ponto px : avenida.getPontos()) {
            mCidade.drawPixel(px.getX(), px.getY(), eCor);
        }


    }

    public static void draw_avenidas_analise_cruzamentos(Lista<AvenidaViaria> avenidas, Renderizador mCidade) {

        Cores mCores = new Cores();

        int naz = 0;

        for (AvenidaViaria avenida : avenidas) {
            naz += 1;
            for (Ponto px : avenida.getPontos()) {
                //    mCidade.drawPixel(px.getX(), px.getY(), mCores.getAmarelo());
            }

            if (avenida.getCruzamentos() == 2) {

                int na = EngenhariaDeObras.contar_avenidas(avenidas, avenida.getX(), avenida.getY(), 60);

                fmt.print("Avenida {} = {} ", naz, na);

                if (na > 3) {
                    //   mCidade.drawCirculo_Pintado(avenida.getX(), avenida.getY(), 10, mCores.getVermelho());
                } else {
                    //    mCidade.drawCirculo_Pintado(avenida.getX(), avenida.getY(), 10, mCores.getBranco());
                }

            }

            if (avenida.isHorizontal()) {

                int cruzamentos = avenida.getCruzamentos();

                for (Ponto px : avenida.getPontos()) {
                    if (cruzamentos == 0) {
                        mCidade.drawPixel(px.getX(), px.getY(), mCores.getBranco());
                    } else if (cruzamentos == 1) {
                        mCidade.drawPixel(px.getX(), px.getY(), mCores.getAzul());
                    } else if (cruzamentos == 2) {
                        mCidade.drawPixel(px.getX(), px.getY(), mCores.getAmarelo());
                    } else {
                        mCidade.drawPixel(px.getX(), px.getY(), mCores.getVerde());
                    }
                }

                // adicionar_casas_horizontal(avenida, mCidade);

            } else if (avenida.isVertical()) {

                int cruzamentos = avenida.getCruzamentos();

                for (Ponto px : avenida.getPontos()) {
                    if (cruzamentos == 0) {
                        mCidade.drawPixel(px.getX(), px.getY(), mCores.getBranco());
                    } else if (cruzamentos == 1) {
                        mCidade.drawPixel(px.getX(), px.getY(), mCores.getAzul());
                    } else if (cruzamentos == 2) {
                        mCidade.drawPixel(px.getX(), px.getY(), mCores.getAmarelo());
                    } else {
                        mCidade.drawPixel(px.getX(), px.getY(), mCores.getVerde());
                    }
                }

                //  adicionar_casas_vertical(avenida, mCidade);

            } else {

                for (Ponto px : avenida.getPontos()) {
                    mCidade.drawPixel(px.getX(), px.getY(), mCores.getTurquesa());
                }

            }

        }


    }


    public static void analisar_cruzamentos(Lista<AvenidaViaria> avenidas) {

        int cruzamentos = 0;

        Lista<Ponto> cruzamentos_unicos = new Lista<Ponto>();

        for (AvenidaViaria avenida : avenidas) {
            for (Ponto cruzamento : avenida.getConexoes()) {
                // mCidade.drawRect_Pintado(cruzamento.getX(), cruzamento.getY(), 5, 5, mCores.getBranco());
                cruzamentos += 1;

                ListaDePontos.adicionar_unicamente(cruzamento, cruzamentos_unicos);

            }
        }

        fmt.print("Cruzamentos = {}", cruzamentos);
        fmt.print("Cruzamentos Unicos = {}", cruzamentos_unicos.getQuantidade());


    }


    public static Lista<AvenidaViaria> procurar_avenidas_que_passam_em(Lista<AvenidaViaria> avenidas, Ponto ponto_proc) {

        Lista<AvenidaViaria> ret = new Lista<AvenidaViaria>();

        for (AvenidaViaria av : avenidas) {
            if (av.estaEm(ponto_proc)) {
                ret.adicionar(av);
            }
        }

        return ret;
    }


    public static boolean avenidas_tem_ponto(Lista<AvenidaViaria> avenidas, Ponto ponto) {

        boolean ret = false;

        for (AvenidaViaria av : avenidas) {
            if (av.estaEm(ponto)) {
                ret = true;
                break;
            }
        }

        return ret;
    }

    public static Ponto procurar_conexao(AvenidaViaria av1, AvenidaViaria av2) {

        boolean ret = false;

        for (Ponto p1 : av1.getConexoes()) {
            for (Ponto p2 : av2.getConexoes()) {
                if (p1.isIgual(p2)) {
                    return p1;
                }
            }
        }

        return null;
    }


    public static int contar_pontos_semelhantes(AvenidaViaria av, Lista<Ponto> pontos) {

        int contagem = 0;

        for (Ponto pt : pontos) {
            for (Ponto pta : av.getPontos()) {
                if (pt.isIgual(pta)) {
                    contagem += 1;
                    break;
                }
            }
        }


        return contagem;
    }
}
