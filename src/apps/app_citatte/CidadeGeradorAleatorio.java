package apps.app_citatte;

import apps.app_citatte.engenharia.AvenidaViaria;
import apps.app_citatte.engenharia.EngenhariaDeObras;
import apps.app_citatte.engenharia.EngenhariaRodoviaria;
import apps.app_citatte.testes.RenderAvenidasConectadas;
import apps.app_citatte.testes.RenderSetoresColoridos;
import apps.app_citatte.transito.Rota;
import apps.app_citatte.transito.Transito;
import apps.app_citatte.urbanizacao.AreaTipada;
import apps.app_citatte.urbanizacao.Urbanizador;
import libs.azzal.Cores;
import libs.azzal.geometria.Ponto;
import libs.luan.Lista;
import libs.luan.fmt;

import java.util.Random;

public class CidadeGeradorAleatorio {


    public static void init_cidades() {

        CidadeGeradorAleatorio.init("Alfa");
        CidadeGeradorAleatorio.init("Beta");
        CidadeGeradorAleatorio.init("Gama");
        CidadeGeradorAleatorio.init("Omega");
        CidadeGeradorAleatorio.init("Pi");
        CidadeGeradorAleatorio.init("Tau");
        CidadeGeradorAleatorio.init("Zeta");

    }

    public static void init_cidade() {

        String nome = "GAUZZ";

        Citatte mCitatte = new Citatte(1000, 1000);

        Cores mCores = new Cores();

        CitatteConstructor.criar_cidade(mCitatte.get(), mCitatte);
        CitatteConstructor.abrir("/home/luan/assets/cidadela.dkg", mCitatte.get(), mCitatte);

        //CitatteConstructor.abrir( "/home/luan/assets/cidadela_tritton.dkg",mCitatte.get(), mCitatte);
        //  CitatteConstructor.abrir("/home/luan/assets/cidadela_atto.dkg", mCitatte.get(), mCitatte);

        // CitatteConstructor.abrir("/home/luan/assets/cidadela_granttaz/cidadela.dkg", mCitatte.get(), mCitatte);


        Urbanizador.ORGANIZAR_ENDERECOS(mCitatte.get(), mCitatte.getAvenidas(), mCitatte);


        mCitatte.zerar();


        for (AreaAdministravel area : mCitatte.getAreasAdministraveis()) {

            if (area.getNome().startsWith("SUL")) {
                mCitatte.get().drawRect(area.getArea(), mCores.getVerde());
            } else if (area.getNome().startsWith("NORTE")) {
                mCitatte.get().drawRect(area.getArea(), mCores.getLaranja());
            } else if (area.getNome().startsWith("LESTE")) {
                mCitatte.get().drawRect(area.getArea(), mCores.getTurquesa());
            } else if (area.getNome().startsWith("OESTE")) {
                mCitatte.get().drawRect(area.getArea(), mCores.getAzul());
            } else if (area.getNome().startsWith("CENTRO")) {
                mCitatte.get().drawRect(area.getArea(), mCores.getRosa());
            } else {
                mCitatte.get().drawRect(area.getArea(), mCores.getBranco());
            }

            if (area.getNome().contains("ESPECIAL")) {

                if (area.getNome().contains("AMARELO")) {
                    mCitatte.get().drawRect_Pintado(area.getArea(), mCores.getAmarelo());
                } else if (area.getNome().contains("AZUL")) {
                    mCitatte.get().drawRect_Pintado(area.getArea(), mCores.getAzul());
                } else if (area.getNome().contains("ROSA")) {
                    mCitatte.get().drawRect_Pintado(area.getArea(), mCores.getRosa());
                } else if (area.getNome().contains("MARROM")) {
                    mCitatte.get().drawRect_Pintado(area.getArea(), mCores.getMarrom());
                } else if (area.getNome().contains("LARANJA")) {
                    mCitatte.get().drawRect_Pintado(area.getArea(), mCores.getLaranja());
                } else {
                    mCitatte.get().drawRect_Pintado(area.getArea(), mCores.getCinza());
                }

            }

        }

        EngenhariaRodoviaria.draw_avenidas_amarelo(mCitatte.get(), mCitatte.getAvenidas());


        String local_assets = "/home/luan/assets/cidades";
        mCitatte.get().exportarSemAlfa(local_assets + "/CIDADELA_" + nome + ".png");
        CitatteConstructor.to_arquivo(local_assets + "/CIDADELA_" + nome + ".dkg", mCitatte.getAvenidas(), mCitatte.getAreasAdministraveis());


    }


    public static void render_cidade() {

        String nome = "GAUZZ";
        String local_assets = "/home/luan/assets/cidades";

        Citatte mCitatte = new Citatte(1000, 1000);


        CitatteConstructor.abrir(local_assets + "/CIDADELA_" + nome + ".dkg", mCitatte.get(), mCitatte);


        RenderSetoresColoridos.init(mCitatte, local_assets + "/CIDADELA_" + nome + "_setores.png");
        RenderSetoresColoridos.init_all(mCitatte, local_assets + "/CIDADELA_" + nome + "_setores_todos.png");

        RenderSetoresColoridos.init_apenas_avenidas(mCitatte, local_assets + "/CIDADELA_" + nome + "_avenidas.png");


        Lista<AreaTipada> areas_tipadas = Urbanizador.classificar_areas(mCitatte);

        Lista<AvenidaViaria> av_comerciais = Urbanizador.classificar_areas_privadas(mCitatte, areas_tipadas);


        fmt.print("Áreas Privadas    = {}", Urbanizador.contagem_privadas(areas_tipadas));
        fmt.print("\t - Comercial    = {}", Urbanizador.contagem_comercial(areas_tipadas));
        fmt.print("\t - Residencial  = {}", Urbanizador.contagem_residencial(areas_tipadas));

        fmt.print("Áreas Públicas    = {}", Urbanizador.contagem_publicas(areas_tipadas));


        RenderSetoresColoridos.marcar_areas_tipos(mCitatte, areas_tipadas, local_assets + "/CIDADELA_" + nome + "_tipos.png");
        RenderSetoresColoridos.marcar_areas_tipo_privadas(mCitatte, areas_tipadas, local_assets + "/CIDADELA_" + nome + "_tipo_privadas.png");
        RenderSetoresColoridos.marcar_areas_tipo_privadas_modos(mCitatte, areas_tipadas, av_comerciais, local_assets + "/CIDADELA_" + nome + "_tipo_privadas_modos.png");


        Urbanizador.toArquivo(av_comerciais, areas_tipadas, local_assets + "/CIDADELA_" + nome + ".areas");


        if (mCitatte.getAvenidas().getQuantidade() > 0) {
            return;
        }


        RenderAvenidasConectadas.init(mCitatte, local_assets + "/CIDADELA_" + nome + "_conectadas.png");


        Random sorte = new Random();

        Ponto origem = mCitatte.getAvenidas().get(sorte.nextInt(mCitatte.getAvenidas().getQuantidade())).getPontos().get(0);
        Ponto destino = mCitatte.getAvenidas().get(sorte.nextInt(mCitatte.getAvenidas().getQuantidade())).getPontos().get(0);

        int v1 = sorte.nextInt(mCitatte.getAvenidas().getQuantidade());
        origem = mCitatte.getAvenidas().get(v1).getPontos().get(0);
        destino = mCitatte.getAvenidas().get(v1 + 5).getPontos().get(0);


        render_cidade_entre_vias_cache(mCitatte, origem, destino, "aleatorio", "1");
        render_cidade_entre_vias_cache(mCitatte, origem, destino, "aleatorio", "2");
        render_cidade_entre_vias_cache(mCitatte, origem, destino, "aleatorio", "3");
        render_cidade_entre_vias_cache(mCitatte, origem, destino, "melhor_de_3", "melhor_de_3");
        render_cidade_entre_vias_cache(mCitatte, origem, destino, "melhor_de_100", "melhor_de_100");
        render_cidade_entre_vias_cache(mCitatte, origem, destino, "pior_de_100", "pior_de_100");


    }

    public static void render_cidade_entre_vias(String algoritmo_rota) {

        String nome = "GAUZZ";
        String local_assets = "/home/luan/assets/cidades";

        Citatte mCitatte = new Citatte(1000, 1000);

        Cores mCores = new Cores();

        CitatteConstructor.abrir(local_assets + "/CIDADELA_" + nome + ".dkg", mCitatte.get(), mCitatte);

        mCitatte.zerar();


        for (AreaAdministravel area : mCitatte.getAreasAdministraveis()) {

            mCitatte.get().drawRect(area.getArea(), mCores.getBranco());

            if (area.getNome().contains("ESPECIAL")) {
                mCitatte.get().drawRect_Pintado(area.getArea(), mCores.getBranco());
            }

        }

        EngenhariaRodoviaria.draw_avenidas_amarelo(mCitatte.get(), mCitatte.getAvenidas());

        EngenhariaDeObras.organizar_cruzamentos(mCitatte.getAvenidas());


        Ponto origem = mCitatte.getAvenidas().get(15).getPontos().get(0);
        Ponto destino = mCitatte.getAvenidas().get(50).getPontos().get(0);


        Rota eRota = null;

        fmt.print("Algoritmo = {}", algoritmo_rota);

        if (algoritmo_rota.contentEquals("melhor_de_3")) {
            fmt.print("Melhor de 3");
            eRota = Transito.criar_rota_melhor_de_3(mCitatte.getAvenidas(), origem, destino);
        } else if (algoritmo_rota.contentEquals("melhor_de_100")) {
            fmt.print("Melhor de 100");
            eRota = Transito.criar_rota_melhor_de_100(mCitatte.getAvenidas(), origem, destino);
        } else {
            eRota = Transito.criar_rota(mCitatte.getAvenidas(), origem, destino);
        }

        for (AvenidaViaria av_lig : eRota.getAvenidas()) {
            for (Ponto px : av_lig.getPontos()) {
                //    mCitatte.get().drawPixel(px.getX(), px.getY(), mCores.getVermelho());
            }
        }


        boolean tem_anterior = false;
        Ponto pt_anterior = null;
        for (Ponto px : eRota.getAndando()) {

            if (tem_anterior) {

                if (px.getX() == pt_anterior.getX()) {
                    mCitatte.get().drawLinha(px.getX(), px.getY(), pt_anterior.getX(), pt_anterior.getY(), mCores.getAzul());
                }

                if (px.getY() == pt_anterior.getY()) {
                    mCitatte.get().drawLinha(px.getX(), px.getY(), pt_anterior.getX(), pt_anterior.getY(), mCores.getAzul());
                }

            }

            mCitatte.get().drawPixel(px.getX(), px.getY(), mCores.getVermelho());

            pt_anterior = px;
            tem_anterior = true;
        }


        mCitatte.get().drawCirculoCentralizado_Pintado(origem, 5, mCores.getVerde());
        mCitatte.get().drawCirculoCentralizado_Pintado(destino, 5, mCores.getAzul());

        for (Ponto cruz : eRota.getCruzamentos()) {
            mCitatte.get().drawCirculoCentralizado_Pintado(cruz, 3, mCores.getRosa());
        }


        mCitatte.get().exportarSemAlfa(local_assets + "/CIDADELA_" + nome + "_rota_" + algoritmo_rota + ".png");


    }

    public static void render_cidade_entre_vias_testes() {

        String nome = "GAUZZ";
        String local_assets = "/home/luan/assets/cidades";

        Citatte mCitatte = new Citatte(1000, 1000);

        Cores mCores = new Cores();

        CitatteConstructor.abrir(local_assets + "/CIDADELA_" + nome + ".dkg", mCitatte.get(), mCitatte);

        mCitatte.zerar();


        for (AreaAdministravel area : mCitatte.getAreasAdministraveis()) {

            mCitatte.get().drawRect(area.getArea(), mCores.getBranco());

            if (area.getNome().contains("ESPECIAL")) {
                mCitatte.get().drawRect_Pintado(area.getArea(), mCores.getBranco());
            }

        }

        EngenhariaRodoviaria.draw_avenidas_amarelo(mCitatte.get(), mCitatte.getAvenidas());

        EngenhariaDeObras.organizar_cruzamentos(mCitatte.getAvenidas());


        Random sorte = new Random();

        Ponto origem = mCitatte.getAvenidas().get(sorte.nextInt(mCitatte.getAvenidas().getQuantidade())).getPontos().get(0);
        Ponto destino = mCitatte.getAvenidas().get(sorte.nextInt(mCitatte.getAvenidas().getQuantidade())).getPontos().get(0);

        render_cidade_entre_vias_cache(mCitatte, origem, destino, "aleatorio", "1");
        render_cidade_entre_vias_cache(mCitatte, origem, destino, "aleatorio", "2");
        render_cidade_entre_vias_cache(mCitatte, origem, destino, "aleatorio", "3");
        render_cidade_entre_vias_cache(mCitatte, origem, destino, "melhor_de_3", "melhor_de_3");
        render_cidade_entre_vias_cache(mCitatte, origem, destino, "melhor_de_100", "melhor_de_100");

    }

    public static void render_cidade_entre_vias_cache(Citatte mCitatte, Ponto origem, Ponto destino, String algoritmo_rota, String v) {

        mCitatte.zerar();


        Rota eRota = null;

        fmt.print("Algoritmo = {}", algoritmo_rota);

        if (algoritmo_rota.contentEquals("melhor_de_3")) {
            fmt.print("Melhor de 3");
            eRota = Transito.criar_rota_melhor_de_3(mCitatte.getAvenidas(), origem, destino);
        } else if (algoritmo_rota.contentEquals("melhor_de_100")) {
            fmt.print("Melhor de 100");
            eRota = Transito.criar_rota_melhor_de_100(mCitatte.getAvenidas(), origem, destino);
        } else if (algoritmo_rota.contentEquals("pior_de_100")) {
            fmt.print("Pior de 100");
            eRota = Transito.criar_rota_pior_de_100(mCitatte.getAvenidas(), origem, destino);
        } else {
            eRota = Transito.criar_rota(mCitatte.getAvenidas(), origem, destino);
        }

        for (AvenidaViaria av_lig : eRota.getAvenidas()) {
            for (Ponto px : av_lig.getPontos()) {
                //    mCitatte.get().drawPixel(px.getX(), px.getY(), mCores.getVermelho());
            }
        }


        Cores mCores = new Cores();

        for (AreaAdministravel area : mCitatte.getAreasAdministraveis()) {

            // mCitatte.get().drawRect(area.getArea(), mCores.getBranco());

            if (area.getNome().contains("ESPECIAL")) {
                //     mCitatte.get().drawRect_Pintado(area.getArea(), mCores.getBranco());
            }

        }

        EngenhariaRodoviaria.draw_avenidas_amarelo(mCitatte.get(), mCitatte.getAvenidas());


        Transito.draw_percurso(mCitatte, eRota, mCores.getAzul(), mCores.getVermelho());


        mCitatte.get().drawCirculoCentralizado_Pintado(origem, 5, mCores.getVerde());
        mCitatte.get().drawCirculoCentralizado_Pintado(destino, 5, mCores.getVermelho());

        for (Ponto cruz : eRota.getCruzamentos()) {
            mCitatte.get().drawCirculoCentralizado_Pintado(cruz, 3, mCores.getRosa());
        }


        String nome = "GAUZZ";
        String local_assets = "/home/luan/assets/cidades";

        mCitatte.get().exportarSemAlfa(local_assets + "/CIDADELA_" + nome + "_rota_" + v + ".png");


    }

    public static void init(String nome) {

        Citatte mCitatte = new Citatte();


        if (nome.contentEquals("Alfa")) {
            mCitatte = new Citatte(500, 500);
        } else if (nome.contentEquals("Beta")) {
            mCitatte = new Citatte(1000, 1000);
        } else if (nome.contentEquals("Gama")) {
            mCitatte = new Citatte(2000, 2000);
        } else if (nome.contentEquals("Omega")) {
            mCitatte = new Citatte(4000, 4000);
        } else if (nome.contentEquals("Pi")) {
            mCitatte = new Citatte(5000, 5000);
        } else if (nome.contentEquals("Tau")) {
            mCitatte = new Citatte(6000, 6000);
        } else if (nome.contentEquals("Zeta")) {
            mCitatte = new Citatte(7000, 7000);
        }


        Cores mCores = new Cores();

        CitatteConstructor.criar_cidade(mCitatte.get(), mCitatte);
        CitatteConstructor.abrir("/home/luan/assets/cidadela.dkg", mCitatte.get(), mCitatte);

        //CitatteConstructor.abrir( "/home/luan/assets/cidadela_tritton.dkg",mCitatte.get(), mCitatte);
        //  CitatteConstructor.abrir("/home/luan/assets/cidadela_atto.dkg", mCitatte.get(), mCitatte);

        // CitatteConstructor.abrir("/home/luan/assets/cidadela_granttaz/cidadela.dkg", mCitatte.get(), mCitatte);


        Urbanizador.ORGANIZAR_ENDERECOS(mCitatte.get(), mCitatte.getAvenidas(), mCitatte);


        mCitatte.zerar();


        for (AreaAdministravel area : mCitatte.getAreasAdministraveis()) {

            if (area.getNome().startsWith("SUL")) {
                mCitatte.get().drawRect(area.getArea(), mCores.getVerde());
            } else if (area.getNome().startsWith("NORTE")) {
                mCitatte.get().drawRect(area.getArea(), mCores.getLaranja());
            } else if (area.getNome().startsWith("LESTE")) {
                mCitatte.get().drawRect(area.getArea(), mCores.getTurquesa());
            } else if (area.getNome().startsWith("OESTE")) {
                mCitatte.get().drawRect(area.getArea(), mCores.getAzul());
            } else if (area.getNome().startsWith("CENTRO")) {
                mCitatte.get().drawRect(area.getArea(), mCores.getRosa());
            } else {
                mCitatte.get().drawRect(area.getArea(), mCores.getBranco());
            }

            if (area.getNome().contains("ESPECIAL")) {

                if (area.getNome().contains("AMARELO")) {
                    mCitatte.get().drawRect_Pintado(area.getArea(), mCores.getAmarelo());
                } else if (area.getNome().contains("AZUL")) {
                    mCitatte.get().drawRect_Pintado(area.getArea(), mCores.getAzul());
                } else if (area.getNome().contains("ROSA")) {
                    mCitatte.get().drawRect_Pintado(area.getArea(), mCores.getRosa());
                } else if (area.getNome().contains("MARROM")) {
                    mCitatte.get().drawRect_Pintado(area.getArea(), mCores.getMarrom());
                } else if (area.getNome().contains("LARANJA")) {
                    mCitatte.get().drawRect_Pintado(area.getArea(), mCores.getLaranja());
                } else {
                    mCitatte.get().drawRect_Pintado(area.getArea(), mCores.getCinza());
                }

            }

        }

        EngenhariaRodoviaria.draw_avenidas_amarelo(mCitatte.get(), mCitatte.getAvenidas());


        String local_assets = "/home/luan/assets/cidades";
        mCitatte.get().exportarSemAlfa(local_assets + "/CIDADELA_" + nome + ".png");


    }


    public static void render_gama() {

        String nome = "GAMMA";
        String local_assets = "/home/luan/assets/cidade_gamma";

        Citatte mCitatte = new Citatte(500, 500);

        CitatteConstructor.criar_cidade(mCitatte.get(), mCitatte);

        CitatteConstructor.to_arquivo(local_assets + "/gamma.dkg", mCitatte.getAvenidas(), mCitatte.getAreasAdministraveis());

        CitatteConstructor.abrir(local_assets + "/gamma.dkg", mCitatte.get(), mCitatte);


        Urbanizador.ORGANIZAR_ENDERECOS(mCitatte.get(), mCitatte.getAvenidas(), mCitatte);


        CitatteConstructor.to_arquivo(local_assets + "/gamma.dkg", mCitatte.getAvenidas(), mCitatte.getAreasAdministraveis());


        RenderSetoresColoridos.init(mCitatte, local_assets + "/CIDADELA_" + nome + "_setores.png");
        RenderSetoresColoridos.init_all(mCitatte, local_assets + "/CIDADELA_" + nome + "_setores_todos.png");

        RenderSetoresColoridos.init_apenas_avenidas(mCitatte, local_assets + "/CIDADELA_" + nome + "_avenidas.png");


        Lista<AreaTipada> areas_tipadas = Urbanizador.classificar_areas(mCitatte);

        Lista<AvenidaViaria> av_comerciais = Urbanizador.classificar_areas_privadas(mCitatte, areas_tipadas);


        fmt.print("Áreas Privadas    = {}", Urbanizador.contagem_privadas(areas_tipadas));
        fmt.print("\t - Comercial    = {}", Urbanizador.contagem_comercial(areas_tipadas));
        fmt.print("\t - Residencial  = {}", Urbanizador.contagem_residencial(areas_tipadas));

        fmt.print("Áreas Públicas    = {}", Urbanizador.contagem_publicas(areas_tipadas));


        RenderSetoresColoridos.marcar_areas_tipos(mCitatte, areas_tipadas, local_assets + "/CIDADELA_" + nome + "_tipos.png");
        RenderSetoresColoridos.marcar_areas_tipo_privadas(mCitatte, areas_tipadas, local_assets + "/CIDADELA_" + nome + "_tipo_privadas.png");
        RenderSetoresColoridos.marcar_areas_tipo_privadas_modos(mCitatte, areas_tipadas, av_comerciais, local_assets + "/CIDADELA_" + nome + "_tipo_privadas_modos.png");


        RenderAvenidasConectadas.init(mCitatte, local_assets + "/CIDADELA_" + nome + "_conectadas.png");


        Random sorte = new Random();

        Ponto origem = mCitatte.getAvenidas().get(sorte.nextInt(mCitatte.getAvenidas().getQuantidade())).getPontos().get(0);
        Ponto destino = mCitatte.getAvenidas().get(sorte.nextInt(mCitatte.getAvenidas().getQuantidade())).getPontos().get(0);

        int v1 = sorte.nextInt(mCitatte.getAvenidas().getQuantidade());
        origem = mCitatte.getAvenidas().get(v1).getPontos().get(0);
        destino = mCitatte.getAvenidas().get(v1 + 5).getPontos().get(0);


        render_cidade_entre_vias_cache(mCitatte, origem, destino, "aleatorio", "1");
        render_cidade_entre_vias_cache(mCitatte, origem, destino, "aleatorio", "2");
        render_cidade_entre_vias_cache(mCitatte, origem, destino, "aleatorio", "3");
        render_cidade_entre_vias_cache(mCitatte, origem, destino, "melhor_de_3", "melhor_de_3");
        render_cidade_entre_vias_cache(mCitatte, origem, destino, "melhor_de_100", "melhor_de_100");
        render_cidade_entre_vias_cache(mCitatte, origem, destino, "pior_de_100", "pior_de_100");


    }

}
