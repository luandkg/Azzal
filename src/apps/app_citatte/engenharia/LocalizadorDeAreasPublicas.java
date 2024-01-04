package apps.app_citatte.engenharia;

import apps.app_citatte.Citatte;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.geometria.Retangulo;
import libs.azzal.utilitarios.Cor;
import libs.luan.Lista;
import libs.luan.fmt;

public class LocalizadorDeAreasPublicas {


    public static void localizar(Renderizador mCidade, Lista<AvenidaViaria> avenidas, Citatte eCitatte) {

        Cores mCores = new Cores();

        int menor_x = avenidas.get(0).getX();
        int maior_x = avenidas.get(0).getX();
        int menor_y = avenidas.get(0).getY();
        int maior_y = avenidas.get(0).getY();

        for (AvenidaViaria av : avenidas) {
            if (av.getX() < menor_x) {
                menor_x = av.getX();
            }
            if (av.getX() > maior_x) {
                maior_x = av.getX();

                if (av.isHorizontal()) {
                    maior_y = av.getY() + av.getComprimento();
                }
            }

            if (av.getY() < menor_y) {
                menor_y = av.getY();
            }
            if (av.getY() > maior_y) {
                maior_y = av.getY();
                if (av.isVertical()) {
                    maior_y = av.getY() + av.getComprimento();
                }
            }
        }

        fmt.print("Cidade Espaco ->> [{} - {}] -- [{} - {}]", menor_x, maior_x, menor_y, maior_y);

        for (int indo_y = menor_y; indo_y < maior_y; indo_y++) {
            for (int indo_x = menor_x; indo_x < maior_x; indo_x++) {
                if (mCidade.getPixel(indo_x, indo_y).igual(mCores.getPreto())) {

                    int max_procurar = 100;

                    if (EngenhariaDeObras.verLinhaFrente(mCidade, indo_x, indo_y, max_procurar, mCores.getTurquesa())) {
                        if (EngenhariaDeObras.verLinhaAtras(mCidade, indo_x, indo_y, max_procurar, mCores.getTurquesa())) {
                            if (EngenhariaDeObras.verColunaFrente(mCidade, indo_x, indo_y, max_procurar, mCores.getTurquesa())) {
                                if (EngenhariaDeObras.verColunaAtras(mCidade, indo_x, indo_y, max_procurar, mCores.getTurquesa())) {


                                    int cla = EngenhariaDeObras.contarLinhaFrente(mCidade, indo_x, indo_y, max_procurar, mCores.getTurquesa());
                                    int clb = EngenhariaDeObras.contarLinhaAtras(mCidade, indo_x, indo_y, max_procurar, mCores.getTurquesa());

                                    int cca = EngenhariaDeObras.contarColunaFrente(mCidade, indo_x, indo_y, max_procurar, mCores.getTurquesa());
                                    int ccb = EngenhariaDeObras.contarColunaAtras(mCidade, indo_x, indo_y, max_procurar, mCores.getTurquesa());

                                    if (cla >= 30 && clb >= 30 && cca >= 30 && ccb >= 30) {

                                        criar_area(mCidade, eCitatte, indo_x, indo_y, 30, 30, mCores.getAmarelo(), "ESPECIAL AMARELO");
                                        criar_area(mCidade, eCitatte, indo_x, indo_y, 20, 20, mCores.getAzul(), "ESPECIAL AZUL");
                                        criar_area(mCidade, eCitatte, indo_x, indo_y, 10, 10, mCores.getLaranja(), "ESPECIAL LARANJA");

                                    } else if (cla >= 20 && clb >= 20 && cca >= 20 && ccb >= 20) {

                                        criar_area(mCidade, eCitatte, indo_x, indo_y, 20, 20, mCores.getCinza(), "ESPECIAL CINZA");
                                        criar_area(mCidade, eCitatte, indo_x, indo_y, 10, 10, mCores.getMarrom(), "ESPECIAL MARROM");

                                    } else if (cla >= 15 && clb >= 15 && cca >= 15 && ccb >= 15) {

                                        criar_area(mCidade, eCitatte, indo_x, indo_y, 10, 10, mCores.getRosa(), "ESPECIAL ROSA");

                                    }

                                }
                            } else {

                                int cla = EngenhariaDeObras.contarLinhaFrente(mCidade, indo_x, indo_y, max_procurar, mCores.getTurquesa());
                                int clb = EngenhariaDeObras.contarLinhaAtras(mCidade, indo_x, indo_y, max_procurar, mCores.getTurquesa());

                                int cca = EngenhariaDeObras.contarColunaFrente(mCidade, indo_x, indo_y, max_procurar, mCores.getTurquesa());

                                if (cla >= 30 && clb >= 30 && cca >= 30) {

                                    criar_area(mCidade, eCitatte, indo_x, indo_y, 30, 30, mCores.getAmarelo(), "ESPECIAL AMARELO");
                                    criar_area(mCidade, eCitatte, indo_x, indo_y, 20, 20, mCores.getAzul(), "ESPECIAL AZUL");
                                    criar_area(mCidade, eCitatte, indo_x, indo_y, 10, 10, mCores.getLaranja(), "ESPECIAL LARANJA");


                                }

                            }
                        }
                    }

                }
            }
        }

    }


    public static void criar_area(Renderizador mCidade, Citatte eCitatte, int indo_x, int indo_y, int largura, int altura, Cor eCor, String eNome) {

        if (EngenhariaDeObras.is_tudo_preto(mCidade, indo_x, indo_y, largura, altura)) {
            if (!eCitatte.tem_construcao(indo_x, indo_y, largura, altura)) {
                mCidade.drawRect_Pintado(indo_x, indo_y, largura, altura, eCor);
                //    mCidade.drawPixel(indo_x, indo_y, mCores.getBranco());
                eCitatte.area_criar(new Retangulo(indo_x, indo_y, largura, altura), eNome);
            }
        }

    }
}
