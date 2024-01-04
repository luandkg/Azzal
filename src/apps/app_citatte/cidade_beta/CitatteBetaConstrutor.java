package apps.app_citatte.cidade_beta;

import apps.app_attuz.Ferramentas.GPS;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.geometria.Ponto;
import libs.dkg.DKG;
import libs.dkg.DKGObjeto;
import libs.luan.EmCiclico;
import libs.luan.Embaralhar;
import libs.luan.ItemCiclico;
import libs.luan.Lista;

import java.util.ArrayList;
import java.util.Random;

public class CitatteBetaConstrutor {

    public void construir(Lista<Ponto> eixos, Renderizador mCidade) {

        Cores mCores = new Cores();


        Lista<Ponto> eixos_rota_primaria = new Lista<Ponto>();
        Lista<Ponto> eixos_rota_secundaria = new Lista<Ponto>();
        Lista<Ponto> eixos_moradias_pode_construir = new Lista<Ponto>();


        Lista<Ponto> eixos_marginais = new Lista<Ponto>();
        Lista<Ponto> eixos_moradias = new Lista<Ponto>();
        Lista<Ponto> eixos_rotas = new Lista<Ponto>();


        for (ItemCiclico<Ponto> pt : EmCiclico.GET(eixos)) {

            Ponto pt_a = pt.getObjeto();
            Ponto pt_b = pt.getObjetoProximo();

            ArrayList<Ponto> rota = GPS.criarRota(pt_a.getX(), pt_a.getY(), pt_b.getX(), pt_b.getY());

            for (Ponto ponto_rota : rota) {
                eixos_moradias.adicionar(ponto_rota);
                eixos_rotas.adicionar(ponto_rota);
                eixos_rota_primaria.adicionar(ponto_rota);

                //  mCidade.drawPixel(ponto_rota.getX(), ponto_rota.getY(), mCores.getAmarelo());
            }

            // mCidade.drawLinha(pt_a.getX(), pt_a.getY(), pt_b.getX(), pt_b.getY(), mCores.getAmarelo());

            Random ale = new Random();

            for (int ale_i = 0; ale_i < (ale.nextInt(3) + 5); ale_i += 1) {
                eixos_marginais.adicionar(CRIAR_PONTO_PROXIMO(pt_a));
            }

        }

        for (Ponto pt : eixos) {
            mCidade.drawCirculo_Pintado(pt.getX() - 3, pt.getY() - 3, 3, mCores.getVermelho());
        }

        for (Ponto pt : eixos_marginais) {
            mCidade.drawCirculo_Pintado(pt.getX() - 3, pt.getY() - 3, 3, mCores.getVerde());
        }

        Embaralhar.emabaralhe(eixos_marginais);

        for (ItemCiclico<Ponto> pt : EmCiclico.GET(eixos_marginais)) {

            Ponto pt_a = pt.getObjeto();
            Ponto pt_b = pt.getObjetoProximo();

            ArrayList<Ponto> rota = GPS.criarRota(pt_a.getX(), pt_a.getY(), pt_b.getX(), pt_b.getY());

            for (Ponto ponto_rota : rota) {
                eixos_moradias.adicionar(ponto_rota);
                eixos_rotas.adicionar(ponto_rota);
                eixos_rota_secundaria.adicionar(ponto_rota);
                // mCidade.drawPixel(ponto_rota.getX(), ponto_rota.getY(), mCores.getAzul());
            }


        }

        Embaralhar.emabaralhe(eixos_moradias);

        System.out.println("A = " + eixos_moradias.getQuantidade());
        eixos_moradias = reduzir(eixos_moradias, 20);
        System.out.println("B = " + eixos_moradias.getQuantidade());
        eixos_moradias = ao_redor(eixos_moradias);
        System.out.println("C = " + eixos_moradias.getQuantidade());


        int processando = 0;
        int processando_etapa = eixos_moradias.getQuantidade() / 10;
        int proc = 0;

        for (Ponto pt : eixos_moradias) {

            boolean tem_asfalto = false;

            for (Ponto asfalto : eixos_rotas) {
                if (pt.getX() == asfalto.getX() && pt.getY() == asfalto.getY()) {
                    tem_asfalto = true;
                    break;
                }
            }

            if (!tem_asfalto) {
                eixos_moradias_pode_construir.adicionar(pt);
            }

            if (processando >= processando_etapa) {
                processando = 0;
                System.out.println("Processando :: " + proc);
                proc += 1;
            }
            processando += 1;
            //  System.out.println("Processando :: " + processando + " de "+eixos_moradias.getQuantidade());

        }


        System.out.println("D = " + eixos_moradias_pode_construir.getQuantidade());


        for (Ponto pt : eixos_moradias_pode_construir) {
            mCidade.drawPixel(pt.getX(), pt.getY(), mCores.getBranco());
        }

        for (Ponto ponto_rota : eixos_rota_secundaria) {
            mCidade.drawPixel(ponto_rota.getX(), ponto_rota.getY(), mCores.getAzul());
        }

        for (Ponto ponto_rota : eixos_rota_primaria) {
            mCidade.drawPixel(ponto_rota.getX(), ponto_rota.getY(), mCores.getAmarelo());
        }

    }


    public Lista<Ponto> ao_redor(Lista<Ponto> entrada) {
        Lista<Ponto> ret = new Lista<Ponto>();

        Random ale = new Random();

        for (Ponto pt_a : entrada) {
            for (int ale_i = 0; ale_i < (ale.nextInt(3) + 5); ale_i += 1) {
                ret.adicionar(CRIAR_PONTO_PROXIMO(pt_a));
            }
        }


        return ret;
    }


    public Lista<Ponto> reduzir(Lista<Ponto> entrada, int reducao) {
        Lista<Ponto> ret = new Lista<Ponto>();


        int i = 0;
        for (Ponto pt : entrada) {
            if (i == reducao) {
                ret.adicionar(pt);
                i = 0;
            }
            i += 1;
        }


        return ret;
    }

    public Ponto CRIAR_PONTO_PROXIMO(Ponto pt_a) {

        Random ale = new Random();

        int lado_x = ale.nextInt(50);
        int lado_y = ale.nextInt(50);

        if (ale.nextInt(100) > 50) {
            lado_x = lado_x * (-1);
        }
        if (ale.nextInt(100) > 50) {
            lado_y = lado_y * (-1);
        }

        return new Ponto(pt_a.getX() + lado_x, pt_a.getY() + lado_y);
    }


    public void construir_arquivo(Lista<Ponto> eixos, String arquivo) {

        Cores mCores = new Cores();


        Lista<Ponto> eixos_rota_primaria = new Lista<Ponto>();
        Lista<Ponto> eixos_rota_secundaria = new Lista<Ponto>();
        Lista<Ponto> eixos_moradias_pode_construir = new Lista<Ponto>();


        Lista<Ponto> eixos_marginais = new Lista<Ponto>();
        Lista<Ponto> eixos_moradias = new Lista<Ponto>();
        Lista<Ponto> eixos_rotas = new Lista<Ponto>();


        for (ItemCiclico<Ponto> pt : EmCiclico.GET(eixos)) {

            Ponto pt_a = pt.getObjeto();
            Ponto pt_b = pt.getObjetoProximo();

            ArrayList<Ponto> rota = GPS.criarRota(pt_a.getX(), pt_a.getY(), pt_b.getX(), pt_b.getY());

            for (Ponto ponto_rota : rota) {
                eixos_moradias.adicionar(ponto_rota);
                eixos_rotas.adicionar(ponto_rota);
                eixos_rota_primaria.adicionar(ponto_rota);

                //  mCidade.drawPixel(ponto_rota.getX(), ponto_rota.getY(), mCores.getAmarelo());
            }

            // mCidade.drawLinha(pt_a.getX(), pt_a.getY(), pt_b.getX(), pt_b.getY(), mCores.getAmarelo());

            Random ale = new Random();

            for (int ale_i = 0; ale_i < (ale.nextInt(3) + 5); ale_i += 1) {
                eixos_marginais.adicionar(CRIAR_PONTO_PROXIMO(pt_a));
            }

        }

        for (Ponto pt : eixos) {
            //   mCidade.drawCirculo_Pintado(pt.getX() - 3, pt.getY() - 3, 3, mCores.getVermelho());
        }

        for (Ponto pt : eixos_marginais) {
            //  mCidade.drawCirculo_Pintado(pt.getX() - 3, pt.getY() - 3, 3, mCores.getVerde());
        }

        Embaralhar.emabaralhe(eixos_marginais);

        for (ItemCiclico<Ponto> pt : EmCiclico.GET(eixos_marginais)) {

            Ponto pt_a = pt.getObjeto();
            Ponto pt_b = pt.getObjetoProximo();

            ArrayList<Ponto> rota = GPS.criarRota(pt_a.getX(), pt_a.getY(), pt_b.getX(), pt_b.getY());

            for (Ponto ponto_rota : rota) {
                eixos_moradias.adicionar(ponto_rota);
                eixos_rotas.adicionar(ponto_rota);
                eixos_rota_secundaria.adicionar(ponto_rota);
                // mCidade.drawPixel(ponto_rota.getX(), ponto_rota.getY(), mCores.getAzul());
            }


        }

        Embaralhar.emabaralhe(eixos_moradias);

        System.out.println("A = " + eixos_moradias.getQuantidade());
        eixos_moradias = reduzir(eixos_moradias, 20);
        System.out.println("B = " + eixos_moradias.getQuantidade());
        eixos_moradias = ao_redor(eixos_moradias);
        System.out.println("C = " + eixos_moradias.getQuantidade());


        int processando = 0;
        int processando_etapa = eixos_moradias.getQuantidade() / 10;
        int proc = 0;

        for (Ponto pt : eixos_moradias) {

            boolean tem_asfalto = false;

            for (Ponto asfalto : eixos_rotas) {
                if (pt.getX() == asfalto.getX() && pt.getY() == asfalto.getY()) {
                    tem_asfalto = true;
                    break;
                }
            }

            if (!tem_asfalto) {
                eixos_moradias_pode_construir.adicionar(pt);
            }

            if (processando >= processando_etapa) {
                processando = 0;
                System.out.println("Processando :: " + proc);
                proc += 1;
            }
            processando += 1;
            //  System.out.println("Processando :: " + processando + " de "+eixos_moradias.getQuantidade());

        }


        System.out.println("D = " + eixos_moradias_pode_construir.getQuantidade());


        DKG documento = new DKG();
        DKGObjeto raiz = documento.unicoObjeto("Citatte");

        for (Ponto pt : eixos_moradias_pode_construir) {
            DKGObjeto item = raiz.criarObjeto("Item");
            item.identifique("Tipo", "Habitavel");
            item.identifique("X", pt.getX());
            item.identifique("Y", pt.getY());
        }

        for (Ponto pt : eixos_rota_secundaria) {
            DKGObjeto item = raiz.criarObjeto("Item");
            item.identifique("Tipo", "Rua");
            item.identifique("X", pt.getX());
            item.identifique("Y", pt.getY());
        }

        for (Ponto pt : eixos_rota_primaria) {
            DKGObjeto item = raiz.criarObjeto("Item");
            item.identifique("Tipo", "Avenida");
            item.identifique("X", pt.getX());
            item.identifique("Y", pt.getY());
        }

        documento.salvar(arquivo);
    }

    public void carregar_citatte(String arquivo, Renderizador mCidade) {

        Cores mCores = new Cores();

        DKG documento = DKG.ABRIR_DO_ARQUIVO(arquivo);
        DKGObjeto raiz = documento.unicoObjeto("Citatte");
        for (DKGObjeto item : raiz.getObjetos()) {
            if (item.identifique("Tipo").isValor("Habitavel")) {

                mCidade.drawPixel(item.identifique("X").getInteiro(0), item.identifique("Y").getInteiro(0), mCores.getBranco());

            } else if (item.identifique("Tipo").isValor("Rua")) {

                mCidade.drawPixel(item.identifique("X").getInteiro(0), item.identifique("Y").getInteiro(0), mCores.getAzul());

             //   mCidade.drawPixel(item.identifique("X").getInteiro(0)+1, item.identifique("Y").getInteiro(0), mCores.getAzul());
             //   mCidade.drawPixel(item.identifique("X").getInteiro(0)-1, item.identifique("Y").getInteiro(0), mCores.getAzul());

              //  mCidade.drawPixel(item.identifique("X").getInteiro(0), item.identifique("Y").getInteiro(0)+1, mCores.getAzul());
              //  mCidade.drawPixel(item.identifique("X").getInteiro(0), item.identifique("Y").getInteiro(0)-1, mCores.getAzul());

            } else if (item.identifique("Tipo").isValor("Avenida")) {
                mCidade.drawPixel(item.identifique("X").getInteiro(0), item.identifique("Y").getInteiro(0), mCores.getAmarelo());

              ///  mCidade.drawPixel(item.identifique("X").getInteiro(0)+1, item.identifique("Y").getInteiro(0), mCores.getAmarelo());
               // mCidade.drawPixel(item.identifique("X").getInteiro(0)-1, item.identifique("Y").getInteiro(0), mCores.getAmarelo());

                //mCidade.drawPixel(item.identifique("X").getInteiro(0), item.identifique("Y").getInteiro(0)+1, mCores.getAmarelo());
               // mCidade.drawPixel(item.identifique("X").getInteiro(0), item.identifique("Y").getInteiro(0)-1, mCores.getAmarelo());

            }
        }


    }


    public void carregar_em(String arquivo, Lista<Ponto> habitavel, Lista<Ponto> rua, Lista<Ponto> avenida) {

        DKG documento = DKG.ABRIR_DO_ARQUIVO(arquivo);
        DKGObjeto raiz = documento.unicoObjeto("Citatte");
        for (DKGObjeto item : raiz.getObjetos()) {
            if (item.identifique("Tipo").isValor("Habitavel")) {

                habitavel.adicionar(new Ponto(item.identifique("X").getInteiro(0), item.identifique("Y").getInteiro(0)));

            } else if (item.identifique("Tipo").isValor("Rua")) {
                rua.adicionar(new Ponto(item.identifique("X").getInteiro(0), item.identifique("Y").getInteiro(0)));

            } else if (item.identifique("Tipo").isValor("Avenida")) {
                avenida.adicionar(new Ponto(item.identifique("X").getInteiro(0), item.identifique("Y").getInteiro(0)));
            }
        }


    }

    public void guardar_em(String arquivo, Lista<Ponto> habitavel, Lista<Ponto> rua, Lista<Ponto> avenida) {

        DKG documento = new DKG();
        DKGObjeto raiz = documento.unicoObjeto("Citatte");

        for (Ponto pt : habitavel) {
            DKGObjeto item = raiz.criarObjeto("Item");
            item.identifique("Tipo", "Habitavel");
            item.identifique("X", pt.getX());
            item.identifique("Y", pt.getY());
        }

        for (Ponto pt : rua) {
            DKGObjeto item = raiz.criarObjeto("Item");
            item.identifique("Tipo", "Rua");
            item.identifique("X", pt.getX());
            item.identifique("Y", pt.getY());
        }

        for (Ponto pt : avenida) {
            DKGObjeto item = raiz.criarObjeto("Item");
            item.identifique("Tipo", "Avenida");
            item.identifique("X", pt.getX());
            item.identifique("Y", pt.getY());
        }

        documento.salvar(arquivo);
    }


    public  Lista<Ponto> organizar_habitavel(Lista<Ponto> habitavel, Lista<Ponto> rua, Lista<Ponto> avenida){

        Lista<Ponto> eixos_moradias_pode_construir = new Lista<Ponto>();

        int processando = 0;
        int processando_etapa = habitavel.getQuantidade() / 10;
        int proc = 0;

        for (Ponto pt : habitavel) {

            boolean tem_asfalto = false;

            for (Ponto asfalto : rua) {
                if (pt.getX() == asfalto.getX() && pt.getY() == asfalto.getY()) {
                    tem_asfalto = true;
                    break;
                }
            }

            for (Ponto asfalto : avenida) {
                if (pt.getX() == asfalto.getX() && pt.getY() == asfalto.getY()) {
                    tem_asfalto = true;
                    break;
                }
            }

            if (!tem_asfalto) {
                eixos_moradias_pode_construir.adicionar(pt);
            }

            if (processando >= processando_etapa) {
                processando = 0;
                System.out.println("Processando :: " + proc);
                proc += 1;
            }
            processando += 1;
            //  System.out.println("Processando :: " + processando + " de "+eixos_moradias.getQuantidade());

        }

        return eixos_moradias_pode_construir;

    }


}
