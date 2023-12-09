package apps.app_citatte.cidade_beta;

import apps.app_attuz.Ferramentas.Espaco2D;
import apps.app_attuz.Ferramentas.GPS;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.geometria.Ponto;
import libs.azzal.utilitarios.Cor;
import libs.dkg.DKG;
import libs.dkg.DKGObjeto;
import libs.luan.Lista;
import libs.luan.Opcional;
import libs.luan.Par;

public class AnaliseCitatte {

    Cores mCores;

    public AnaliseCitatte() {
        mCores = new Cores();

    }

    public int GET_HABITAVEL(Renderizador render) {

        int c_habitavel = 0;


        for (int y = 0; y < render.getAltura(); y++) {
            for (int x = 0; x < render.getLargura(); x++) {
                Cor pixel = render.getPixel(x, y);
                if (mCores.getBranco().igual(pixel)) {
                    c_habitavel += 1;
                }
            }

        }

        return c_habitavel;
    }


    public void retirar_brancos_solos(Renderizador render) {


        for (int y = 0; y < render.getAltura(); y++) {
            for (int x = 0; x < render.getLargura(); x++) {
                if (mCores.getBranco().igual(render.getPixel(x, y))) {

                    if (mCores.getPreto().igual(render.getPixel(x - 1, y)) && mCores.getPreto().igual(render.getPixel(x + 1, y)) && mCores.getPreto().igual(render.getPixel(x, y - 1)) && mCores.getPreto().igual(render.getPixel(x, y + 1))) {

                        if (!tem_acima(render, x, y - 1, 50) || !tem_abaixo(render, x, y + 1, 50)) {
                            render.drawPixel(x, y, mCores.getPreto());
                        }
                        if (!tem_esquerda(render, x - 1, y, 50) || !tem_direita(render, x + 1, y, 50)) {
                            render.drawPixel(x, y, mCores.getPreto());
                        }
                    } else {
                        render.drawPixel(x, y, mCores.getPreto());
                    }


                }
            }

        }

    }


    public int GET_HABITAVEL_RODEADO_PRETO(Renderizador render) {

        int c_habitavel = 0;


        for (int y = 0; y < render.getAltura(); y++) {
            for (int x = 0; x < render.getLargura(); x++) {
                if (mCores.getBranco().igual(render.getPixel(x, y))) {

                    if (mCores.getPreto().igual(render.getPixel(x - 1, y)) && mCores.getPreto().igual(render.getPixel(x + 1, y)) && mCores.getPreto().igual(render.getPixel(x, y - 1)) && mCores.getPreto().igual(render.getPixel(x, y + 1))) {

                        if (tem_acima(render, x, y - 1, 50) && tem_abaixo(render, x, y + 1, 50)) {
                            if (tem_esquerda(render, x - 1, y, 50) && tem_direita(render, x + 1, y, 50)) {
                                c_habitavel += 1;
                            }
                        }

                    }


                }
            }

        }

        return c_habitavel;
    }


    public Lista<Ponto> GET_HABITAVEL_RODEADO_PRETO_LISTA(Renderizador render) {

        Lista<Ponto> habitavel = new Lista<Ponto>();


        for (int y = 0; y < render.getAltura(); y++) {
            for (int x = 0; x < render.getLargura(); x++) {
                if (mCores.getBranco().igual(render.getPixel(x, y))) {

                    if (mCores.getPreto().igual(render.getPixel(x - 1, y)) && mCores.getPreto().igual(render.getPixel(x + 1, y)) && mCores.getPreto().igual(render.getPixel(x, y - 1)) && mCores.getPreto().igual(render.getPixel(x, y + 1))) {

                        if (tem_acima(render, x, y - 1, 50) && tem_abaixo(render, x, y + 1, 50)) {
                            if (tem_esquerda(render, x - 1, y, 50) && tem_direita(render, x + 1, y, 50)) {
                                habitavel.adicionar(new Ponto(x, y));
                            }
                        }

                    }


                }
            }

        }

        return habitavel;
    }

    public Lista<Ponto> getBrancos(Renderizador render) {

        Lista<Ponto> habitavel = new Lista<Ponto>();


        for (int y = 0; y < render.getAltura(); y++) {
            for (int x = 0; x < render.getLargura(); x++) {
                if (mCores.getBranco().igual(render.getPixel(x, y))) {
                    habitavel.adicionar(new Ponto(x, y));
                }
            }

        }

        return habitavel;
    }


    public boolean tem_esquerda(Renderizador render, int ox, int oy, int limite) {

        boolean ret = false;

        for (int x = ox; x > (ox - limite); x--) {
            if (mCores.getAmarelo().igual(render.getPixel(x, oy)) || mCores.getAzul().igual(render.getPixel(x, oy))) {
                ret = true;
                break;
            }
        }


        return ret;
    }

    public boolean tem_direita(Renderizador render, int ox, int oy, int limite) {

        boolean ret = false;

        for (int x = ox; x < (ox + limite); x++) {
            if (mCores.getAmarelo().igual(render.getPixel(x, oy)) || mCores.getAzul().igual(render.getPixel(x, oy))) {
                ret = true;
                break;
            }
        }


        return ret;
    }

    public boolean tem_acima(Renderizador render, int ox, int oy, int limite) {

        boolean ret = false;

        for (int y = oy; y > (oy - limite); y--) {
            if (mCores.getAmarelo().igual(render.getPixel(ox, y)) || mCores.getAzul().igual(render.getPixel(ox, y))) {
                ret = true;
                break;
            }
        }


        return ret;
    }

    public boolean tem_abaixo(Renderizador render, int ox, int oy, int limite) {

        boolean ret = false;

        for (int y = oy; y < (oy + limite); y++) {
            if (mCores.getAmarelo().igual(render.getPixel(ox, y)) || mCores.getAzul().igual(render.getPixel(ox, y))) {
                ret = true;
                break;
            }
        }


        return ret;
    }


    public int pintar_regiao(Renderizador render, Ponto px) {

        Cor PRETO = mCores.getPreto();
        Cor BRANCO = mCores.getBranco();

        Lista<Ponto> pinte = new Lista<Ponto>();
        Lista<Ponto> pintados = new Lista<Ponto>();

        if (render.getPixel(px.getX(), px.getY()).igual(PRETO)) {
            pinte.adicionar(new Ponto(px.getX(), px.getY()));
        }

        if (render.getPixel(px.getX() - 1, px.getY()).igual(PRETO)) {
            pinte.adicionar(new Ponto(px.getX() - 1, px.getY()));
        }

        if (render.getPixel(px.getX() + 1, px.getY()).igual(PRETO)) {
            pinte.adicionar(new Ponto(px.getX() + 1, px.getY()));
        }

        if (render.getPixel(px.getX(), px.getY() - 1).igual(PRETO)) {
            pinte.adicionar(new Ponto(px.getX(), px.getY() - 1));
        }

        if (render.getPixel(px.getX(), px.getY() + 1).igual(PRETO)) {
            pinte.adicionar(new Ponto(px.getX(), px.getY() + 1));
        }


        int seq = 0;

        while (pinte.getQuantidade() > 0) {

            int i = 0;
            int o = pinte.getQuantidade();

            Lista<Ponto> adicionar = new Lista<Ponto>();
            Lista<Ponto> remover = new Lista<Ponto>();

            for (int vi = i; vi < o; vi++) {

                Ponto cc = pinte.getValor(vi);

                if (!passou(pintados, cc.getX(), cc.getY())) {
                    pintados.adicionar(cc);

                    System.out.println("PINTE :: " + seq + " :: " + cc.toString() + " :: " + pinte.getQuantidade());

                    if (render.getPixel(cc.getX(), cc.getY()).igual(PRETO)) {
                        render.drawPixel(cc.getX(), cc.getY(), BRANCO);

                        if (render.getPixel(cc.getX() - 1, cc.getY()).igual(PRETO)) {
                            if (!passou(pintados, cc.getX() - 1, cc.getY())) {
                                adicionar.adicionar(new Ponto(cc.getX() - 1, cc.getY()));
                            }
                        }

                        if (render.getPixel(cc.getX() + 1, cc.getY()).igual(PRETO)) {
                            if (!passou(pintados, cc.getX() + 1, cc.getY())) {
                                adicionar.adicionar(new Ponto(cc.getX() + 1, cc.getY()));
                            }
                        }

                        if (render.getPixel(cc.getX(), cc.getY() - 1).igual(PRETO)) {
                            if (!passou(pintados, cc.getX(), cc.getY() - 1)) {
                                adicionar.adicionar(new Ponto(cc.getX(), cc.getY() - 1));
                            }
                        }

                        if (render.getPixel(cc.getX(), cc.getY() + 1).igual(PRETO)) {
                            if (!passou(pintados, cc.getX(), cc.getY() + 1)) {
                                adicionar.adicionar(new Ponto(cc.getX(), cc.getY() + 1));
                            }
                        }
                    }


                }


                remover.adicionar(cc);

            }

            for (Ponto r : remover) {
                pinte.remover(r);
            }

            for (Ponto r : adicionar) {
                if (r.getX() > 0 && r.getY() > 0 && r.getX() < render.getLargura() && r.getY() < render.getAltura()) {

                    boolean ja_adicionado = false;

                    for (Ponto para_pintar : pinte) {
                        if (para_pintar.isIgual(r)) {
                            ja_adicionado = true;
                            break;
                        }
                    }

                    if (!ja_adicionado) {
                        pinte.adicionar(r);
                    }

                    if (pinte.getQuantidade() >= 200) {
                        pinte.limpar();
                        System.out.println("PULAR - " + r.toString());
                        for (Ponto limpe : pintados) {
                            if (render.getPixel(limpe.getX(), limpe.getY()).igual(BRANCO)) {
                                render.drawPixel(limpe.getX(), limpe.getY(), PRETO);
                            }
                        }
                    }

                } else {
                    System.out.println("INTERROMPER - " + r.toString());
                    pinte.limpar();

                    for (Ponto limpe : pintados) {
                        if (render.getPixel(limpe.getX(), limpe.getY()).igual(BRANCO)) {
                            render.drawPixel(limpe.getX(), limpe.getY(), PRETO);
                        }
                    }

                    break;

                }
            }

            System.out.println("++ :: " + adicionar.getQuantidade());
            System.out.println("-- :: " + remover.getQuantidade());
            System.out.println("   :: " + pintados.getQuantidade());


            seq += 1;
            if (seq >= 500) {
                //   break;
            }
        }

        return pintados.getQuantidade();
    }

    public boolean isRegiao(Renderizador render, Ponto px) {

        Cor preto = mCores.getPreto();

        boolean e_regiao = false;

        if (render.getPixel(px.getX() - 1, px.getY()).igual(preto)) {
            e_regiao = true;
        }

        if (render.getPixel(px.getX() + 1, px.getY()).igual(preto)) {
            e_regiao = true;
        }

        if (render.getPixel(px.getX(), px.getY() - 1).igual(preto)) {
            e_regiao = true;
        }

        if (render.getPixel(px.getX(), px.getY() + 1).igual(preto)) {
            e_regiao = true;
        }

        return e_regiao;
    }

    public boolean passou(Lista<Ponto> pontos, int x, int y) {
        boolean enc = false;
        for (Ponto r : pontos) {
            if (r.getX() == x && r.getY() == y) {
                enc = true;
                break;
            }
        }
        return enc;
    }


    public void guardar_regioes(String arquivo, Lista<Par<Ponto, Integer>> regioes) {

        DKG documento = new DKG();
        DKGObjeto raiz = documento.unicoObjeto("Citatte");

        for (Par<Ponto, Integer> pt : regioes) {
            DKGObjeto item = raiz.criarObjeto("Regiao");
            item.identifique("X", pt.getChave().getX());
            item.identifique("Y", pt.getChave().getY());
            item.identifique("Tamanho", pt.getValor());
        }


        documento.salvar(arquivo);

    }


    public void marcar_regiao_laranja(Renderizador render) {

        Lista<Ponto> laranjas = new Lista<Ponto>();

        // ESQUERDA
        for (int y = 0; y < render.getAltura(); y++) {

            int metade_x = render.getLargura() / 2;

            int pos_x = 0;
            boolean primeiro = true;
            boolean encontrou = false;

            for (int x = metade_x; x >= 0; x--) {
                if (mCores.getBranco().igual(render.getPixel(x, y))) {
                    if (primeiro) {
                        pos_x = x;
                        primeiro = false;
                        encontrou = true;
                    } else {
                        pos_x = x;
                    }
                }
            }

            if (encontrou) {
                render.drawPixel(pos_x, y, mCores.getLaranja());
                laranjas.adicionar(new Ponto(pos_x, y));
            }

        }


        // DIREITA
        for (int y = 0; y < render.getAltura(); y++) {

            int metade_x = render.getLargura() / 2;

            int pos_x = 0;
            boolean primeiro = true;
            boolean encontrou = false;

            for (int x = metade_x; x < render.getLargura(); x++) {
                if (mCores.getBranco().igual(render.getPixel(x, y))) {
                    if (primeiro) {
                        pos_x = x;
                        primeiro = false;
                        encontrou = true;
                    } else {
                        pos_x = x;
                    }
                }
            }

            if (encontrou) {
                render.drawPixel(pos_x, y, mCores.getLaranja());
                laranjas.adicionar(new Ponto(pos_x, y));
            }

        }


        // ACIMA
        for (int x = 0; x < render.getLargura(); x++) {

            int metade_y = render.getAltura() / 2;

            int pos_y = 0;
            boolean primeiro = true;
            boolean encontrou = false;

            for (int y = metade_y; y >= 0; y--) {
                if (mCores.getBranco().igual(render.getPixel(x, y))) {
                    if (primeiro) {
                        pos_y = y;
                        primeiro = false;
                        encontrou = true;
                    } else {
                        pos_y = y;
                    }
                }
            }

            if (encontrou) {
                render.drawPixel(x, pos_y, mCores.getLaranja());
                laranjas.adicionar(new Ponto(x, pos_y));
            }

        }

        // ABAIXO
        for (int x = 0; x < render.getLargura(); x++) {

            int metade_y = render.getAltura() / 2;

            int pos_y = 0;
            boolean primeiro = true;
            boolean encontrou = false;

            for (int y = metade_y; y < render.getAltura(); y++) {
                if (mCores.getBranco().igual(render.getPixel(x, y))) {
                    if (primeiro) {
                        pos_y = y;
                        primeiro = false;
                        encontrou = true;
                    } else {
                        pos_y = y;
                    }
                }
            }

            if (encontrou) {
                render.drawPixel(x, pos_y, mCores.getLaranja());
                laranjas.adicionar(new Ponto(x, pos_y));
            }

        }

        if (laranjas.getQuantidade() > 0) {

            Ponto ir_comecar = laranjas.get(0);
            Ponto ir_comecar_primeiro = laranjas.get(0);

            while (laranjas.getQuantidade() > 0) {

                Opcional<Ponto> ponto_mais_proximo = Espaco2D.GET_MAIS_PROXIMO(ir_comecar, laranjas);

                if (ponto_mais_proximo.isOK()) {

                    for (Ponto indo : GPS.criarRota(ir_comecar.getX(), ir_comecar.getY(), ponto_mais_proximo.get().getX(), ponto_mais_proximo.get().getY())) {

                        render.drawPixel(indo.getX(), indo.getY(), mCores.getLaranja());

                        render.drawPixel(indo.getX() + 1, indo.getY(), mCores.getLaranja());
                        render.drawPixel(indo.getX() - 1, indo.getY(), mCores.getLaranja());
                        render.drawPixel(indo.getX(), indo.getY() + 1, mCores.getLaranja());
                        render.drawPixel(indo.getX(), indo.getY() - 1, mCores.getLaranja());

                    }

                    laranjas.remover(ir_comecar);
                    System.out.println(laranjas.getQuantidade() + " -- Indo de " + ir_comecar.toString() + " para " + ponto_mais_proximo.get().toString());

                    if (laranjas.getQuantidade() == 1) {

                        for (Ponto indo : GPS.criarRota(ponto_mais_proximo.get().getX(), ponto_mais_proximo.get().getY(), ir_comecar_primeiro.getX(), ir_comecar_primeiro.getY())) {
                            render.drawPixel(indo.getX(), indo.getY(), mCores.getLaranja());

                            render.drawPixel(indo.getX() + 1, indo.getY(), mCores.getLaranja());
                            render.drawPixel(indo.getX() - 1, indo.getY(), mCores.getLaranja());
                            render.drawPixel(indo.getX(), indo.getY() + 1, mCores.getLaranja());
                            render.drawPixel(indo.getX(), indo.getY() - 1, mCores.getLaranja());

                        }

                        laranjas.limpar();
                    }
                }

                if (ponto_mais_proximo.isOK()) {
                    ir_comecar = ponto_mais_proximo.get();
                }
            }


        }


    }


    public void retirar_branco_se_azul_ou_amarelo(Renderizador render, Ponto px) {

        Cor BRANCO = mCores.getBranco();
        Cor PRETO = mCores.getPreto();
        Cor AZUL = mCores.getAzul();
        Cor AMARELO = mCores.getAmarelo();

        if (render.getPixel(px.getX(), px.getY()).igual(BRANCO)) {

            int ao_redor = 0;

            if (render.getPixel(px.getX() - 1, px.getY()).igual(AZUL) || render.getPixel(px.getX() - 1, px.getY()).igual(AMARELO)) {
                ao_redor += 1;
            }

            if (render.getPixel(px.getX() + 1, px.getY()).igual(AZUL) || render.getPixel(px.getX() + 1, px.getY()).igual(AMARELO)) {
                ao_redor += 1;
            }

            if (render.getPixel(px.getX(), px.getY() - 1).igual(AZUL) || render.getPixel(px.getX(), px.getY() - 1).igual(AMARELO)) {
                ao_redor += 1;
            }

            if (render.getPixel(px.getX(), px.getY() + 1).igual(AZUL) || render.getPixel(px.getX(), px.getY() + 1).igual(AMARELO)) {
                ao_redor += 1;
            }

            if (ao_redor > 2) {
                render.drawPixel(px.getX(), px.getY(), PRETO);
            }

        }


    }

}
