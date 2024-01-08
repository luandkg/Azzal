package apps.app_citatte.transito;

import apps.app_citatte.Citatte;
import apps.app_citatte.engenharia.AvenidaViaria;
import apps.app_citatte.engenharia.EngenhariaRodoviaria;
import libs.azzal.geometria.Ponto;
import libs.azzal.utilitarios.Cor;
import libs.luan.Lista;
import libs.luan.fmt;

import java.util.Random;

public class Transito {


    public static Rota criar_rota(Lista<AvenidaViaria> avenidas, Ponto origem, Ponto destino) {

        Rota rota = new Rota();
        Lista<AvenidaViaria> caminho = Transito.procurar_avenidas_que_ligam_infinitum(avenidas, origem, destino, rota.getCruzamentos(), rota.getAndando());
        rota.setAvenidas(caminho);

        return rota;
    }

    public static Rota criar_rota_melhor_de_3(Lista<AvenidaViaria> avenidas, Ponto origem, Ponto destino) {

        Rota r1 = Transito.criar_rota(avenidas, origem, destino);
        Rota r2 = Transito.criar_rota(avenidas, origem, destino);
        Rota r3 = Transito.criar_rota(avenidas, origem, destino);

        Rota ret = r1;

        if (r2.getCruzamentos().getQuantidade() < ret.getCruzamentos().getQuantidade()) {
            ret = r2;
        }

        if (r3.getCruzamentos().getQuantidade() < ret.getCruzamentos().getQuantidade()) {
            ret = r3;
        }

        return ret;
    }

    public static Rota criar_rota_melhor_de_100(Lista<AvenidaViaria> avenidas, Ponto origem, Ponto destino) {

        Rota ret = Transito.criar_rota(avenidas, origem, destino);

        int melhorando = 0;
        while (melhorando < 100) {

            Rota r2 = Transito.criar_rota(avenidas, origem, destino);

            fmt.print("++ Rota {} -> {}", melhorando, r2.getCruzamentos().getQuantidade());

            if (r2.getCruzamentos().getQuantidade() < ret.getCruzamentos().getQuantidade()) {
                ret = r2;
                fmt.print("-->> Menor Rota -> {}", ret.getCruzamentos().getQuantidade());
            }

            melhorando += 1;
        }

        fmt.print("-->> Menor Rota -> {}", ret.getCruzamentos().getQuantidade());

        return ret;
    }

    public static Rota criar_rota_pior_de_100(Lista<AvenidaViaria> avenidas, Ponto origem, Ponto destino) {

        Rota ret = Transito.criar_rota(avenidas, origem, destino);

        int piorando = 0;
        while (piorando < 100) {

            Rota r2 = Transito.criar_rota(avenidas, origem, destino);

            fmt.print("++ Rota {} -> {}", piorando, r2.getCruzamentos().getQuantidade());

            if (r2.getCruzamentos().getQuantidade() > ret.getCruzamentos().getQuantidade()) {
                ret = r2;
                fmt.print("-->> Maior Rota -> {}", ret.getCruzamentos().getQuantidade());
            }


            piorando += 1;
        }

        fmt.print("-->> Maior Rota -> {}", ret.getCruzamentos().getQuantidade());

        return ret;
    }

    public static void dirigir_ate(Lista<AvenidaViaria> avenidas, Lista<AvenidaViaria> dirigindo, AvenidaViaria av_comecar, Ponto destino, Lista<AvenidaViaria> diferente_de, Lista<Ponto> cruzamentos_realizados, Lista<Ponto> ir_andando) {
        Random sorte = new Random();

        Lista<AvenidaViaria> cruzamentos = new Lista<AvenidaViaria>();
        Lista<Ponto> cruzamentos_pontos = new Lista<Ponto>();

        for (Ponto cruz : av_comecar.getConexoes()) {
            for (AvenidaViaria av3 : EngenhariaRodoviaria.procurar_avenidas_que_passam_em(avenidas, cruz)) {
                if (!cruzamentos.existe(av3) && !diferente_de.existe(av3)) {
                    cruzamentos.adicionar(av3);
                    cruzamentos_pontos.adicionar(cruz);
                }
            }
        }


        if (cruzamentos.getQuantidade() > 0) {

            int virar = sorte.nextInt(cruzamentos.getQuantidade());
            AvenidaViaria av_ir = cruzamentos.get(virar);


            Ponto virar_cruz = cruzamentos_pontos.get(virar);
            ir_andando.adicionar(virar_cruz);


            cruzamentos_realizados.adicionar(EngenhariaRodoviaria.procurar_conexao(av_comecar, av_ir));

            //  fmt.print("VIRANDO :: {} de {}", virar, cruzamentos.getQuantidade());

            dirigindo.adicionar(av_ir);
            diferente_de.adicionar(av_ir);


            if (EngenhariaRodoviaria.avenidas_tem_ponto(dirigindo, destino)) {
                cruzamentos_realizados.adicionar(destino);
                ir_andando.adicionar(destino);
                //  fmt.print("CHEGUEI AO DESTINO :: {}", destino);
            } else {
                dirigir_ate(avenidas, dirigindo, av_ir, destino, diferente_de, cruzamentos_realizados, ir_andando);
            }


        }


    }

    public static Lista<AvenidaViaria> procurar_avenidas_que_ligam(Lista<AvenidaViaria> avenidas, Ponto origem, Ponto destino, Lista<Ponto> cruzamentos_realizados, Lista<Ponto> ir_andando) {

        Random sorte = new Random();

        Lista<AvenidaViaria> dirigindo = new Lista<AvenidaViaria>();

        int tentativa = 5;

        while (tentativa > 0) {


            dirigindo.limpar();

            Lista<AvenidaViaria> diferente_de = new Lista<AvenidaViaria>();

            Lista<AvenidaViaria> dirigindo_iniciar_em = procurar_avenidas_que_passam_em_diferente_de(avenidas, origem, diferente_de);

            if (dirigindo_iniciar_em.getQuantidade() > 0) {

                int virar = sorte.nextInt(dirigindo_iniciar_em.getQuantidade());
                AvenidaViaria av_ir = dirigindo_iniciar_em.get(virar);

                //    fmt.print("VIRANDO :: {} de {}", virar, dirigindo_iniciar_em.getQuantidade());

                dirigindo.adicionar(av_ir);
                diferente_de.adicionar(av_ir);

                if (av_ir.estaEm(destino)) {
                    break;
                } else {
                    dirigir_ate(avenidas, dirigindo, av_ir, destino, diferente_de, cruzamentos_realizados, ir_andando);
                }

            }


            fmt.print("TENTATIVA :: {} -> {}", tentativa, dirigindo.getQuantidade());

            if (EngenhariaRodoviaria.avenidas_tem_ponto(dirigindo, destino)) {
                break;
            } else {
                dirigindo.limpar();
                cruzamentos_realizados.limpar();
            }

            tentativa -= 1;
        }


        return dirigindo;
    }

    public static Lista<AvenidaViaria> procurar_avenidas_que_ligam_infinitum(Lista<AvenidaViaria> avenidas, Ponto origem, Ponto destino, Lista<Ponto> cruzamentos_realizados, Lista<Ponto> ir_andando) {

        Random sorte = new Random();

        Lista<AvenidaViaria> dirigindo = new Lista<AvenidaViaria>();

        boolean encontrou = false;
        int tentativa = 0;

        while (!encontrou) {

            dirigindo.limpar();
            cruzamentos_realizados.limpar();

            cruzamentos_realizados.adicionar(origem);
            ir_andando.adicionar(origem);

            Lista<AvenidaViaria> diferente_de = new Lista<AvenidaViaria>();

            Lista<AvenidaViaria> dirigindo_iniciar_em = procurar_avenidas_que_passam_em_diferente_de(avenidas, origem, diferente_de);

            if (dirigindo_iniciar_em.getQuantidade() > 0) {

                int virar = sorte.nextInt(dirigindo_iniciar_em.getQuantidade());
                AvenidaViaria av_ir = dirigindo_iniciar_em.get(virar);

                //   fmt.print("VIRANDO :: {} de {}", virar, dirigindo_iniciar_em.getQuantidade());


                dirigindo.adicionar(av_ir);
                diferente_de.adicionar(av_ir);

                if (av_ir.estaEm(destino)) {

                    cruzamentos_realizados.adicionar(destino);
                    ir_andando.adicionar(destino);

                    encontrou = true;
                    break;
                } else {
                    dirigir_ate(avenidas, dirigindo, av_ir, destino, diferente_de, cruzamentos_realizados, ir_andando);
                }

            }


            //  fmt.print("TENTATIVA :: {} -> {}", tentativa, dirigindo.getQuantidade());

            if (EngenhariaRodoviaria.avenidas_tem_ponto(dirigindo, destino)) {
                encontrou = true;
                break;
            } else {
                dirigindo.limpar();
                ir_andando.limpar();
                cruzamentos_realizados.limpar();
            }

            tentativa += 1;
        }


        return dirigindo;
    }

    public static Lista<AvenidaViaria> procurar_avenidas_que_passam_em_diferente_de(Lista<AvenidaViaria> avenidas, Ponto ponto_proc, Lista<AvenidaViaria> avenidas_diferente) {

        Lista<AvenidaViaria> ret = new Lista<AvenidaViaria>();

        for (AvenidaViaria av : avenidas) {
            if (av.estaEm(ponto_proc) && !avenidas_diferente.existe(av)) {
                ret.adicionar(av);
            }
        }

        return ret;
    }


    public static void draw_percurso(Citatte mCitatte, Rota eRota, Cor cor_rota, Cor cor_conexao) {

        boolean tem_anterior = false;
        Ponto pt_anterior = null;
        for (Ponto px : eRota.getAndando()) {

            if (tem_anterior) {

                if (px.getX() == pt_anterior.getX()) {
                    mCitatte.get().drawLinha(px.getX(), px.getY(), pt_anterior.getX(), pt_anterior.getY(), cor_rota);
                }

                if (px.getY() == pt_anterior.getY()) {
                    mCitatte.get().drawLinha(px.getX(), px.getY(), pt_anterior.getX(), pt_anterior.getY(), cor_rota);
                }

            }

            mCitatte.get().drawPixel(px.getX(), px.getY(), cor_conexao);

            pt_anterior = px;
            tem_anterior = true;
        }


    }


    public static Rota criar_rota_melhor_de_100_inteligente(Lista<AvenidaViaria> avenidas, Ponto origem, Ponto destino) {

        Rota ret = Transito.criar_rota(avenidas, origem, destino);

        int menor_rota = ret.getCruzamentos().getQuantidade();

        fmt.print("++ Rota {} -> {}", 0, ret.getCruzamentos().getQuantidade());

        int melhorando = 1;
        while (melhorando < 100) {

            Rota r2 = Transito.criar_rota_inteligente(avenidas, origem, destino, menor_rota);

            if (r2.teveSucesso()) {

                fmt.print("++ Rota {} -> {}", melhorando, r2.getCruzamentos().getQuantidade());

                if (r2.getCruzamentos().getQuantidade() < ret.getCruzamentos().getQuantidade()) {
                    ret = r2;
                    menor_rota = ret.getCruzamentos().getQuantidade();
                    fmt.print("-->> Menor Rota -> {}", ret.getCruzamentos().getQuantidade());
                }
            } else {
                fmt.print("-->> --- Rota {} Interrompida !", melhorando);
            }


            melhorando += 1;
        }

        fmt.print("-->> Menor Rota -> {}", ret.getCruzamentos().getQuantidade());

        return ret;
    }

    public static Rota criar_rota_inteligente(Lista<AvenidaViaria> avenidas, Ponto origem, Ponto destino, int inteligente_menor_que) {

        Rota rota = new Rota();
        Lista<AvenidaViaria> caminho = Transito.procurar_avenidas_que_ligam_infinitum_inteligente(avenidas, origem, destino, rota.getCruzamentos(), rota.getAndando(), inteligente_menor_que);
        rota.setAvenidas(caminho);

        if (EngenhariaRodoviaria.avenidas_tem_ponto(caminho, destino)) {
            rota.marcarComSucesso();
        }

        return rota;
    }

    public static Lista<AvenidaViaria> procurar_avenidas_que_ligam_infinitum_inteligente(Lista<AvenidaViaria> avenidas, Ponto origem, Ponto destino, Lista<Ponto> cruzamentos_realizados, Lista<Ponto> ir_andando, int inteligente_menor_que) {

        Random sorte = new Random();

        Lista<AvenidaViaria> dirigindo = new Lista<AvenidaViaria>();

        boolean encontrou = false;
        int tentativa = 0;

        while (!encontrou) {

            dirigindo.limpar();
            cruzamentos_realizados.limpar();

            cruzamentos_realizados.adicionar(origem);
            ir_andando.adicionar(origem);

            Lista<AvenidaViaria> diferente_de = new Lista<AvenidaViaria>();

            Lista<AvenidaViaria> dirigindo_iniciar_em = procurar_avenidas_que_passam_em_diferente_de(avenidas, origem, diferente_de);

            if (dirigindo_iniciar_em.getQuantidade() > 0) {

                int virar = sorte.nextInt(dirigindo_iniciar_em.getQuantidade());
                AvenidaViaria av_ir = dirigindo_iniciar_em.get(virar);

                //   fmt.print("VIRANDO :: {} de {}", virar, dirigindo_iniciar_em.getQuantidade());


                dirigindo.adicionar(av_ir);
                diferente_de.adicionar(av_ir);

                if (av_ir.estaEm(destino)) {

                    cruzamentos_realizados.adicionar(destino);
                    ir_andando.adicionar(destino);

                    if (cruzamentos_realizados.getQuantidade() > inteligente_menor_que) {
                        //  fmt.print("ROTA INTELIGENTE INTERROMPER :: {} pois {}", inteligente_menor_que, cruzamentos_realizados.getQuantidade());
                        dirigindo.limpar();
                        cruzamentos_realizados.limpar();
                        break;
                    }

                    encontrou = true;
                    break;
                } else {
                    boolean continuar = dirigir_ate_inteligente(avenidas, dirigindo, av_ir, destino, diferente_de, cruzamentos_realizados, ir_andando, inteligente_menor_que);
                    if (!continuar) {
                        break;
                    }
                }

            }


            //  fmt.print("TENTATIVA :: {} -> {}", tentativa, dirigindo.getQuantidade());

            if (EngenhariaRodoviaria.avenidas_tem_ponto(dirigindo, destino)) {
                encontrou = true;
                break;
            } else {
                dirigindo.limpar();
                ir_andando.limpar();
                cruzamentos_realizados.limpar();
            }

            tentativa += 1;
        }


        return dirigindo;
    }

    public static boolean dirigir_ate_inteligente(Lista<AvenidaViaria> avenidas, Lista<AvenidaViaria> dirigindo, AvenidaViaria av_comecar, Ponto destino, Lista<AvenidaViaria> diferente_de, Lista<Ponto> cruzamentos_realizados, Lista<Ponto> ir_andando, int inteligente_menor_que) {
        boolean continuar = true;

        Random sorte = new Random();

        Lista<AvenidaViaria> cruzamentos = new Lista<AvenidaViaria>();
        Lista<Ponto> cruzamentos_pontos = new Lista<Ponto>();

        for (Ponto cruz : av_comecar.getConexoes()) {
            for (AvenidaViaria av3 : EngenhariaRodoviaria.procurar_avenidas_que_passam_em(avenidas, cruz)) {
                if (!cruzamentos.existe(av3) && !diferente_de.existe(av3)) {
                    cruzamentos.adicionar(av3);
                    cruzamentos_pontos.adicionar(cruz);
                }
            }
        }


        if (cruzamentos.getQuantidade() > 0) {

            int virar = sorte.nextInt(cruzamentos.getQuantidade());
            AvenidaViaria av_ir = cruzamentos.get(virar);


            Ponto virar_cruz = cruzamentos_pontos.get(virar);
            ir_andando.adicionar(virar_cruz);


            cruzamentos_realizados.adicionar(EngenhariaRodoviaria.procurar_conexao(av_comecar, av_ir));

            if (cruzamentos_realizados.getQuantidade() > inteligente_menor_que) {
                //     fmt.print("ROTA INTELIGENTE INTERROMPER :: {} pois {}", inteligente_menor_que, cruzamentos_realizados.getQuantidade());
                cruzamentos_realizados.limpar();
                ir_andando.limpar();
                return false;
            }
            //  fmt.print("VIRANDO :: {} de {}", virar, cruzamentos.getQuantidade());

            dirigindo.adicionar(av_ir);
            diferente_de.adicionar(av_ir);


            if (EngenhariaRodoviaria.avenidas_tem_ponto(dirigindo, destino)) {
                cruzamentos_realizados.adicionar(destino);
                ir_andando.adicionar(destino);
                //  fmt.print("CHEGUEI AO DESTINO :: {}", destino);
            } else {
                boolean continuar2 = dirigir_ate_inteligente(avenidas, dirigindo, av_ir, destino, diferente_de, cruzamentos_realizados, ir_andando, inteligente_menor_que);
                continuar = continuar2;
            }


        }

        return continuar;
    }

}
