package apps.app_atzum.utils;

import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.geometria.Ponto;
import libs.azzal.utilitarios.Cor;
import libs.luan.*;
import libs.meta_functional.Acao;

public class Rasterizador {

    public static void rasterizar(Renderizador render, int px, int py, Cor eCor) {
        Cores mCores = new Cores();


        Cor FUNDO = mCores.getPreto();


        Lista<Ponto> proximos = new Lista<Ponto>();
        proximos.adicionar(new Ponto(px, py));


        Lista<Ponto> todos = new Lista<Ponto>();

        int processando = 0;

        int processando_salvou = 0;
        int processando_salvou_cem = 0;
        int processando_salvou_cem_indice = 0;

        // String LOCAL = "/home/luan/Imagens/atzum/";

        while (proximos.getQuantidade() > 0) {

            Lista<Ponto> pt_novos = new Lista<Ponto>();

            boolean modificado = false;

            for (Ponto ponto : proximos) {

                todos.adicionar(ponto);

                if (render.getPixel(ponto.getX(), ponto.getY()).igual(FUNDO)) {
                    modificado = true;
                    //   fmt.print("{} - Passando ->> {}",proximos.getQuantidade(),ponto.toString());

                    render.drawRect_Pintado(ponto.getX(), ponto.getY(), 1, 1, eCor);

                    Ponto pt_esquerda = new Ponto(ponto.getX() - 1, ponto.getY());
                    Ponto pt_direita = new Ponto(ponto.getX() + 1, ponto.getY());
                    Ponto pt_subir = new Ponto(ponto.getX(), ponto.getY() - 1);
                    Ponto pt_descer = new Ponto(ponto.getX(), ponto.getY() + 1);

                    processar(render, FUNDO, todos, pt_novos, pt_esquerda);
                    processar(render, FUNDO, todos, pt_novos, pt_direita);
                    processar(render, FUNDO, todos, pt_novos, pt_subir);
                    processar(render, FUNDO, todos, pt_novos, pt_descer);


                } else {
                    //   fmt.print("PULAR PT -->> {}",ponto.toString());
                }

                if (processando == 0 && processando_salvou == 0 && processando_salvou_cem == 0) {
                    //  Imagem.exportar(render.toImagemSemAlfa(),LOCAL + "processando/processando_"+fmt.zerado(processando_salvou_cem_indice,4)+".png");
                    processando_salvou_cem_indice += 1;
                }

                if (processando == 500 && processando_salvou == 0 && processando_salvou_cem == 0) {
                    // Imagem.exportar(render.toImagemSemAlfa(),LOCAL + "processando/processando_"+fmt.zerado(processando_salvou_cem_indice,4)+".png");
                    processando_salvou_cem_indice += 1;
                }

                if (modificado) {
                    processando += 1;
                    if (processando > 1000) {
                        processando = 0;

                        //   Imagem.exportar(render.toImagemSemAlfa(),LOCAL + "processando.png");


                        processando_salvou += 1;
                        processando_salvou_cem += 1;

                        if (processando_salvou_cem == 100) {
                            //  Imagem.exportar(render.toImagemSemAlfa(),LOCAL + "processando/processando_"+fmt.zerado(processando_salvou_cem_indice,4)+".png");
                            processando_salvou_cem_indice += 1;
                            processando_salvou_cem = 0;
                        }

                        //  Texto.arquivo_escrever("/home/luan/Imagens/atzum/processou.txt",String.valueOf(processando_salvou));
                    }
                }


            }

            proximos.limpar();
            proximos.adicionar_varios(pt_novos);

            fmt.print("EXISTEM NOVOS  -->> {}", proximos.getQuantidade());

        }


        // Imagem.exportar(render.toImagemSemAlfa(),LOCAL + "processando2.png");

        processando_salvou += 1;
        // Texto.arquivo_escrever("/home/luan/Imagens/atzum/processou.txt",String.valueOf(processando_salvou));


    }

    public static void processar(Renderizador render, Cor FUNDO, Lista<Ponto> todos, Lista<Ponto> pt_novos, Ponto pt_processar) {

        int FUNDO_VALOR = FUNDO.getValor();

        if (pt_processar.getX() >= 0 && pt_processar.getY() >= 0 && pt_processar.getX() < render.getLargura() && pt_processar.getY() < render.getAltura()) {

            if (render.getPixelBruto(pt_processar.getX(), pt_processar.getY()) == FUNDO_VALOR) {

                boolean deve_adicionar = true;

                if (PONTO_EXISTE(pt_novos, pt_processar)) {
                    deve_adicionar = false;
                }

                if (deve_adicionar) {
                    if (PONTO_EXISTE(todos, pt_processar)) {
                        deve_adicionar = false;
                    }
                }


                if (deve_adicionar) {
                    pt_novos.adicionar(pt_processar);
                    //  fmt.print("NOVO PT -->> {}",pt_processar.toString());
                }

            }
        }


    }


    public static boolean PONTO_EXISTE(Lista<Ponto> todos, Ponto pt_proc) {

        boolean existe = false;

        for (Ponto pt : todos) {
            if (pt.getX() == pt_proc.getX() && pt.getY() == pt_proc.getY()) {
                existe = true;
                break;
            }
        }

        return existe;
    }


    public static void trocar_cores(Renderizador render, Cor procurada, Cor substituir) {

        for (int dy = 0; dy < render.getAltura() + 200; dy++) {
            for (int dx = 0; dx < render.getLargura() + 200; dx++) {
                if (render.getPixel(dx, dy).igual(procurada)) {
                    render.setPixel(dx, dy, substituir);
                }
            }
        }


    }


    public static void RASTERIZAR_COM(Renderizador render, int px, int py, Cor cor_entrada, Cor cor_saida, Acao durante_processo) {


        int cor_entrada_valor = cor_entrada.getValor();

        Lista<Ponto> proximos = new Lista<Ponto>();
        proximos.adicionar(new Ponto(px, py));


        Lista<Ponto> todos = new Lista<Ponto>();

        int processando = 0;

        int processando_salvou = 0;
        int processando_salvou_cem = 0;


        while (proximos.getQuantidade() > 0) {

            Lista<Ponto> pt_novos = new Lista<Ponto>();

            boolean modificado = false;

            for (Ponto ponto : proximos) {

                todos.adicionar(ponto);

                if (render.getPixelBruto(ponto.getX(), ponto.getY()) == cor_entrada_valor) {
                    modificado = true;
                    //   fmt.print("{} - Passando ->> {}",proximos.getQuantidade(),ponto.toString());

                    render.drawRect_Pintado(ponto.getX(), ponto.getY(), 1, 1, cor_saida);

                    Ponto pt_esquerda = new Ponto(ponto.getX() - 1, ponto.getY());
                    Ponto pt_direita = new Ponto(ponto.getX() + 1, ponto.getY());
                    Ponto pt_subir = new Ponto(ponto.getX(), ponto.getY() - 1);
                    Ponto pt_descer = new Ponto(ponto.getX(), ponto.getY() + 1);

                    RASTERIZAR_PROCESSAR(render, cor_entrada, todos, pt_novos, pt_esquerda);
                    RASTERIZAR_PROCESSAR(render, cor_entrada, todos, pt_novos, pt_direita);
                    RASTERIZAR_PROCESSAR(render, cor_entrada, todos, pt_novos, pt_subir);
                    RASTERIZAR_PROCESSAR(render, cor_entrada, todos, pt_novos, pt_descer);


                } else {
                    //   fmt.print("PULAR PT -->> {}",ponto.toString());
                }

                if (processando == 0 && processando_salvou == 0 && processando_salvou_cem == 0) {
                    durante_processo.fazer();
                }

                if (processando == 500 && processando_salvou == 0 && processando_salvou_cem == 0) {
                    durante_processo.fazer();
                }

                if (modificado) {
                    processando += 1;
                    if (processando > 1000) {
                        processando = 0;

                        processando_salvou += 1;
                        processando_salvou_cem += 1;

                        if (processando_salvou_cem == 100) {
                            durante_processo.fazer();
                            processando_salvou_cem = 0;
                        }
                    }
                }


            }

            proximos.limpar();
            proximos.adicionar_varios(pt_novos);

            fmt.print("EXISTEM NOVOS  -->> {}", proximos.getQuantidade());

        }


        durante_processo.fazer();
    }

    public static void RASTERIZAR_COM(Renderizador render, int px, int py, Cor cor_entrada, Cor cor_saida, Acao durante_toda_mudanca, Acao a_cada_100) {


        int cor_entrada_valor = cor_entrada.getValor();

        Lista<Ponto> proximos = new Lista<Ponto>();
        proximos.adicionar(new Ponto(px, py));


        Lista<Ponto> todos = new Lista<Ponto>();

        int processando = 0;

        int processando_salvou = 0;
        int processando_salvou_cem = 0;


        while (proximos.getQuantidade() > 0) {

            Lista<Ponto> pt_novos = new Lista<Ponto>();

            boolean modificado = false;

            for (Ponto ponto : proximos) {

                todos.adicionar(ponto);

                // fmt.print("COMPARADOR {} :: {}",render.getPixelBruto(ponto.getX(), ponto.getY()),cor_entrada_valor);

                if (render.getPixel(ponto.getX(), ponto.getY()).getValor() == cor_entrada_valor) {
                    modificado = true;
                    //   fmt.print("{} - Passando ->> {}",proximos.getQuantidade(),ponto.toString());

                    render.drawRect_Pintado(ponto.getX(), ponto.getY(), 1, 1, cor_saida);

                    Ponto pt_esquerda = new Ponto(ponto.getX() - 1, ponto.getY());
                    Ponto pt_direita = new Ponto(ponto.getX() + 1, ponto.getY());
                    Ponto pt_subir = new Ponto(ponto.getX(), ponto.getY() - 1);
                    Ponto pt_descer = new Ponto(ponto.getX(), ponto.getY() + 1);

                    RASTERIZAR_PROCESSAR(render, cor_entrada, todos, pt_novos, pt_esquerda);
                    RASTERIZAR_PROCESSAR(render, cor_entrada, todos, pt_novos, pt_direita);
                    RASTERIZAR_PROCESSAR(render, cor_entrada, todos, pt_novos, pt_subir);
                    RASTERIZAR_PROCESSAR(render, cor_entrada, todos, pt_novos, pt_descer);


                } else {
                    //   fmt.print("PULAR PT -->> {}",ponto.toString());
                }

                if (processando == 0 && processando_salvou == 0 && processando_salvou_cem == 0) {
                    a_cada_100.fazer();
                }

                if (processando == 500 && processando_salvou == 0 && processando_salvou_cem == 0) {
                    a_cada_100.fazer();
                }

                if (modificado) {
                    processando += 1;
                    if (processando > 500) {
                        processando = 0;

                        durante_toda_mudanca.fazer();

                        processando_salvou += 1;
                        processando_salvou_cem += 1;

                        if (processando_salvou_cem == 100) {
                            a_cada_100.fazer();
                            processando_salvou_cem = 0;
                        }
                    }
                }


            }

            proximos.limpar();
            proximos.adicionar_varios(pt_novos);

            //   fmt.print("EXISTEM NOVOS  -->> {}", proximos.getQuantidade());

        }


        a_cada_100.fazer();
    }

    public static void RASTERIZAR_PROCESSAR(Renderizador render, Cor cor_entrada, Lista<Ponto> todos, Lista<Ponto> pt_novos, Ponto pt_processar) {

        int cor_entrada_valor = cor_entrada.getValor();

        if (pt_processar.getX() >= 0 && pt_processar.getY() >= 0 && pt_processar.getX() < render.getLargura() && pt_processar.getY() < render.getAltura()) {

            if (render.getPixel(pt_processar.getX(), pt_processar.getY()).getValor() == cor_entrada_valor) {

                boolean deve_adicionar = true;

                if (GeometriaEspacial2D.PONTO_EXISTE(pt_novos, pt_processar)) {
                    deve_adicionar = false;
                }

                if (deve_adicionar) {
                    if (GeometriaEspacial2D.PONTO_EXISTE(todos, pt_processar)) {
                        deve_adicionar = false;
                    }
                }


                if (deve_adicionar) {
                    pt_novos.adicionar(pt_processar);
                    //  fmt.print("NOVO PT -->> {}",pt_processar.toString());
                }

            }
        }


    }


    public static boolean TEM_AO_REDOR(Renderizador render, int x, int y, Cor procurada) {

        boolean marcar = false;

        if (render.getPixel(x - 1, y).igual(procurada)) {
            marcar = true;
        } else if (render.getPixel(x + 1, y).igual(procurada)) {
            marcar = true;
        } else if (render.getPixel(x, y - 1).igual(procurada)) {
            marcar = true;
        } else if (render.getPixel(x, y + 1).igual(procurada)) {
            marcar = true;
        }

        return marcar;
    }

    public static boolean isDaRegiao(Renderizador render, Cor cor_entrada, Ponto pt_processar) {

        boolean ret = false;

        int cor_entrada_valor = cor_entrada.getValor();

        if (pt_processar.getX() >= 0 && pt_processar.getY() >= 0 && pt_processar.getX() < render.getLargura() && pt_processar.getY() < render.getAltura()) {
            if (render.getPixel(pt_processar.getX(), pt_processar.getY()).getValor() == cor_entrada_valor) {
                ret = true;
            }
        }

        return ret;
    }


    public static Cor GET_COR_MAIS_PROXIMA_DIFERENTE_DE(Renderizador render, int prox_x, int prox_y, int limite, Cor diferente_de1, Cor diferente_de2) {

        Cor cor_ret = diferente_de1;

        int mais_proxima = Integer.MAX_VALUE;
        boolean encontrada = false;

        fmt.print("------------");

        for (int x = prox_x; x < prox_x + limite; x++) {
            if (x >= 0 && x < render.getLargura()) {
                if (render.getPixel(x, prox_y).isDiferente(diferente_de1) && render.getPixel(x, prox_y).isDiferente(diferente_de2)) {
                    if (x - prox_x < mais_proxima) {
                        mais_proxima = x - prox_x;
                        //    fmt.print("DIR - Mais Proxima :: " + mais_proxima);

                        cor_ret = render.getPixel(x, prox_y);
                        encontrada = true;
                    }
                }
            }
        }

        for (int x = prox_x - limite; x < (prox_x); x++) {
            if (x >= 0 && x < render.getLargura()) {
                if (render.getPixel(x, prox_y).isDiferente(diferente_de1) && render.getPixel(x, prox_y).isDiferente(diferente_de2)) {
                    if (prox_x - x < mais_proxima) {

                        mais_proxima = prox_x - x;
                        // fmt.print("ESQ - Mais Proxima :: " + mais_proxima);

                        cor_ret = render.getPixel(x, prox_y);
                        encontrada = true;
                    }
                }
            }
        }

        for (int y = prox_y; y < prox_y + limite; y++) {
            if (y >= 0 && y < render.getAltura()) {
                if (render.getPixel(prox_x, y).isDiferente(diferente_de1) && render.getPixel(prox_x, y).isDiferente(diferente_de2)) {
                    if (y - prox_y < mais_proxima) {
                        mais_proxima = y - prox_y;
                        //  fmt.print("DES - Mais Proxima :: " + mais_proxima);

                        cor_ret = render.getPixel(prox_x, y);
                        encontrada = true;
                    }
                }
            }
        }

        for (int y = prox_y - limite; y < (prox_y); y++) {
            if (y >= 0 && y < render.getAltura()) {
                if (render.getPixel(prox_x, y).isDiferente(diferente_de1) && render.getPixel(prox_x, y).isDiferente(diferente_de2)) {
                    if (prox_y - y < mais_proxima) {

                        mais_proxima = prox_y - y;
                        //   fmt.print("SUB - Mais Proxima :: " + mais_proxima);

                        cor_ret = render.getPixel(prox_x, y);
                        encontrada = true;
                    }
                }
            }
        }

        return cor_ret;
    }

    public static Cor GET_COR_DO_QUADRANTE_DIFERENTE(Renderizador render, int prox_x, int prox_y, int limite, Cor diferente_de1, Cor diferente_de2) {

        Cor cor_ret = diferente_de1;

        Unico<Par<Cor, Integer>> quadrante = new Unico<Par<Cor, Integer>>(new Igualavel<Par<Cor, Integer>>() {
            @Override
            public boolean is(Par<Cor, Integer> a, Par<Cor, Integer> b) {
                return a.getChave().igual(b.getChave());
            }
        });

        //   fmt.print("------------");

        for (int x = prox_x; x < prox_x + limite; x++) {
            if (x >= 0 && x < render.getLargura()) {
                if (render.getPixel(x, prox_y).isDiferente(diferente_de1) && render.getPixel(x, prox_y).isDiferente(diferente_de2)) {

                    Par<Cor, Integer> item = quadrante.item_get(new Par<Cor, Integer>(render.getPixel(x, prox_y), 0));
                    item.setValor(item.getValor() + 1);

                }
            }
        }

        for (int x = prox_x - limite; x < (prox_x); x++) {
            if (x >= 0 && x < render.getLargura()) {
                if (render.getPixel(x, prox_y).isDiferente(diferente_de1) && render.getPixel(x, prox_y).isDiferente(diferente_de2)) {
                    Par<Cor, Integer> item = quadrante.item_get(new Par<Cor, Integer>(render.getPixel(x, prox_y), 0));
                    item.setValor(item.getValor() + 1);

                }
            }
        }

        for (int y = prox_y; y < prox_y + limite; y++) {
            if (y >= 0 && y < render.getAltura()) {
                if (render.getPixel(prox_x, y).isDiferente(diferente_de1) && render.getPixel(prox_x, y).isDiferente(diferente_de2)) {
                    Par<Cor, Integer> item = quadrante.item_get(new Par<Cor, Integer>(render.getPixel(prox_x, y), 0));
                    item.setValor(item.getValor() + 1);
                }
            }
        }

        for (int y = prox_y - limite; y < (prox_y); y++) {
            if (y >= 0 && y < render.getAltura()) {
                if (render.getPixel(prox_x, y).isDiferente(diferente_de1) && render.getPixel(prox_x, y).isDiferente(diferente_de2)) {
                    Par<Cor, Integer> item = quadrante.item_get(new Par<Cor, Integer>(render.getPixel(prox_x, y), 0));
                    item.setValor(item.getValor() + 1);
                }
            }
        }

        int maior = 0;

        for (Par<Cor, Integer> item : quadrante) {
            if (item.getValor() >= maior) {
                maior = item.getValor();
                cor_ret = item.getChave();
            }
        }

        if (quadrante.getQuantidade() >= 3) {
            fmt.print("------------");
            for (Par<Cor, Integer> item : quadrante) {
                fmt.print("{} -->> {}", item.getChave().toString(), item.getValor());
            }
        }


        return cor_ret;
    }

    public static Lista<Ponto> GET_PONTOS_DE_COR(Renderizador render, Cor cor) {

        Lista<Ponto> pontos = new Lista<Ponto>();

        for (int y = 0; y < render.getAltura(); y++) {
            for (int x = 0; x < render.getLargura(); x++) {
                if (render.getPixel(x, y).igual(cor)) {
                    pontos.adicionar(new Ponto(x, y));
                }
            }
        }

        return pontos;
    }


    public static Opcional<Ponto> GET_PONTO_COM_COR(Renderizador render, Cor proc_cor) {
        for (int y = 0; y <= render.getAltura(); y++) {
            for (int x = 0; x <= render.getLargura(); x++) {
                if (render.getPixel(x, y).igual(proc_cor)) {
                    return Opcional.OK(new Ponto(x, y));
                }
            }
        }
        return Opcional.CANCEL();
    }

    public static Opcional<Ponto> GET_PONTO_COM_COR_DIFERENTES_DE(Renderizador render, Cor proc_cor, Lista<Ponto> pontos) {
        for (int y = 0; y <= render.getAltura(); y++) {
            for (int x = 0; x <= render.getLargura(); x++) {
                if (render.getPixel(x, y).igual(proc_cor)) {
                    Ponto ret = new Ponto(x, y);
                    boolean proximo = false;
                    for (Ponto e : pontos) {
                        if (e.isIgual(ret)) {
                            proximo = true;
                            break;
                        }
                    }
                    if (!proximo) {
                        return Opcional.OK(ret);
                    }
                }
            }
        }
        return Opcional.CANCEL();
    }


}
