package apps.app_citatte.testes;

import apps.app_citatte.*;
import apps.app_citatte.engenharia.AvenidaViaria;
import apps.app_citatte.engenharia.EngenhariaRodoviaria;
import apps.app_citatte.engenharia.RosaDosVentos;
import apps.app_citatte.urbanizacao.AreaTipada;
import apps.app_citatte.urbanizacao.Urbanizador;
import libs.azzal.Cores;
import libs.azzal.geometria.Ponto;
import libs.entt.CorpoENTT;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.fmt;
import libs.matematica.Matematica;

public class RenderSetoresColoridos {


    public static void init(Citatte mCitatte, String arquivo) {

        Cores mCores = new Cores();

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

        mCitatte.exportar_imagem(arquivo);

    }

    public static void init_all(Citatte mCitatte, String arquivo) {

        String local_assets = "/home/luan/assets/cidades";

        //   ConselhoGeral.enderecar(mCitatte.get(), mCitatte.getAvenidas(), mCitatte);


        Cores mCores = new Cores();

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

            }

        }

        EngenhariaRodoviaria.draw_avenidas_amarelo(mCitatte.get(), mCitatte.getAvenidas());

        mCitatte.exportar_imagem(arquivo);


        CorpoENTT infos = new CorpoENTT();

        for (String setor : RosaDosVentos.GET_SENTIDOS()) {


            int comum = 0;
            int especiais = 0;

            for (AreaAdministravel area : mCitatte.getAreasAdministraveis()) {

                if (area.getNome().startsWith(setor)) {
                    if (area.getNome().contains("ESPECIAL")) {
                        especiais += 1;
                    } else {
                        comum += 1;
                    }
                }


            }


            Entidade info_corrente = infos.adicionar("Setor", setor, "Comum", comum, "Especial", especiais);
            info_corrente.atInt("Total", info_corrente.somar("Comum", "Especial"));

        }


        fmt.print("");
        fmt.print(fmt.espacar_depois("SETOR", 20) + fmt.espacar_depois("COMUM", 20) + fmt.espacar_depois("ESPECIAL", 20) + fmt.espacar_depois("TOTAL", 20));
        fmt.print("");


        int somar_comum = infos.agregar_somatorio("Comum");
        int somar_especiais = infos.agregar_somatorio("Especial");
        int somar_total = infos.agregar_somatorios("Comum", "Especial");


        Entidade e_setor_menor = infos.getMenorInteiro("Total");
        Entidade e_setor_maior = infos.getMaiorInteiro("Total");


        for (String setor : RosaDosVentos.GET_SENTIDOS()) {
            Entidade e_setor = infos.getUnico("Setor", setor);

            int comum = e_setor.atInt("Comum");
            int especial = e_setor.atInt("Especial");
            int total = e_setor.somar("Comum", "Especial");

            String status = fmt.doubleNumC2(Matematica.getPorcentagem(total, somar_total)) + " %";
            String status_2 = "";

            if (total == e_setor_menor.atInt("Total")) {
                status_2 = "#MENOR";
            } else if (total == e_setor_maior.atInt("Total")) {
                status_2 = "#MAIOR";
            }


            fmt.print(fmt.espacar_depois(setor, 20) + fmt.espacar_depois(comum, 20) + fmt.espacar_depois(especial, 20) + fmt.espacar_depois(total, 20) + fmt.espacar_depois(status, 15) + fmt.espacar_depois(status_2, 10));
        }


        fmt.print("");
        fmt.print(fmt.espacar_depois("TOTAL", 20) + fmt.espacar_depois(somar_comum, 20) + fmt.espacar_depois(somar_especiais, 20) + fmt.espacar_depois(somar_total, 20));


        infos.exportar_dkg(local_assets + "/infos.dkg");

    }

    public static void marcar_areas_tipos(Citatte mCitatte, Lista<AreaTipada> areas_tipadas, String arquivo) {


        Cores mCores = new Cores();

        mCitatte.zerar();


        for (AreaAdministravel area : mCitatte.getAreasAdministraveis()) {


            if (area.getNome().contains("ESPECIAL")) {

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

            } else {

                // RESIDENCIAS


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

                if (Urbanizador.isPrivada(areas_tipadas, area.getNome())) {

                    if (area.getNome().startsWith("SUL")) {
                        mCitatte.get().drawRect_Pintado(area.getArea(), mCores.getVerde());
                    } else if (area.getNome().startsWith("NORTE")) {
                        mCitatte.get().drawRect_Pintado(area.getArea(), mCores.getLaranja());
                    } else if (area.getNome().startsWith("LESTE")) {
                        mCitatte.get().drawRect_Pintado(area.getArea(), mCores.getTurquesa());
                    } else if (area.getNome().startsWith("OESTE")) {
                        mCitatte.get().drawRect_Pintado(area.getArea(), mCores.getAzul());
                    } else if (area.getNome().startsWith("CENTRO")) {
                        mCitatte.get().drawRect_Pintado(area.getArea(), mCores.getRosa());
                    } else {
                        mCitatte.get().drawRect_Pintado(area.getArea(), mCores.getBranco());
                    }

                }

            }

        }

        EngenhariaRodoviaria.draw_avenidas_amarelo(mCitatte.get(), mCitatte.getAvenidas());

        mCitatte.exportar_imagem(arquivo);


    }

    public static void marcar_areas_tipo_privadas(Citatte mCitatte, Lista<AreaTipada> residencias, String arquivo) {


        Cores mCores = new Cores();

        mCitatte.zerar();


        for (AreaAdministravel area : mCitatte.getAreasAdministraveis()) {


            if (area.getNome().contains("ESPECIAL")) {

                mCitatte.get().drawRect(area.getArea(), mCores.getBranco());


            } else {

                // RESIDENCIAS


                if (Urbanizador.isPrivada(residencias, area.getNome())) {

                    mCitatte.get().drawRect(area.getArea(), mCores.getVermelho());

                } else {
                    mCitatte.get().drawRect(area.getArea(), mCores.getBranco());
                }

            }

        }

        EngenhariaRodoviaria.draw_avenidas_amarelo(mCitatte.get(), mCitatte.getAvenidas());

        mCitatte.exportar_imagem(arquivo);


    }

    public static void marcar_areas_tipo_privadas_modos(Citatte mCitatte, Lista<AreaTipada> residencias, Lista<AvenidaViaria> av_comerciais, String arquivo) {


        Cores mCores = new Cores();

        mCitatte.zerar();


        for (AreaAdministravel area : mCitatte.getAreasAdministraveis()) {


            if (area.getNome().contains("ESPECIAL")) {

                mCitatte.get().drawRect(area.getArea(), mCores.getBranco());


            } else {

                // PRIVADAS

                if (Urbanizador.isPrivada(residencias, area.getNome())) {

                    mCitatte.get().drawRect(area.getArea(), mCores.getVermelho());

                    if (Urbanizador.isComercial(residencias, area.getNome())) {
                        mCitatte.get().drawRect(area.getArea(), mCores.getLaranja());
                    } else if (Urbanizador.isResidencial(residencias, area.getNome())) {
                        mCitatte.get().drawRect(area.getArea(), mCores.getAzul());
                    }


                } else {
                    mCitatte.get().drawRect(area.getArea(), mCores.getBranco());
                }

            }

        }

        EngenhariaRodoviaria.draw_avenidas_amarelo(mCitatte.get(), mCitatte.getAvenidas());


        for (AvenidaViaria av : av_comerciais) {
            for (Ponto px : av.getPontos()) {
                mCitatte.get().drawPixel(px.getX(), px.getY(), mCores.getVermelho());
            }
        }


        mCitatte.exportar_imagem(arquivo);


    }


    public static void init_apenas_avenidas(Citatte mCitatte, String arquivo) {


        Cores mCores = new Cores();

        mCitatte.zerar();


        for (AvenidaViaria avenida : mCitatte.getAvenidas()) {

            if (avenida.isTerminal() || avenida.isPrimaria()) {
                EngenhariaRodoviaria.draw_avenida_com_cor(mCitatte.get(), avenida, mCores.getVermelho());
            } else {
                EngenhariaRodoviaria.draw_avenida_com_cor(mCitatte.get(), avenida, mCores.getBranco());

            }


        }


        mCitatte.exportar_imagem(arquivo);


    }

}
