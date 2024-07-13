package apps.app_citatte.engenharia;

import apps.app_citatte.Citatte;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.geometria.Retangulo;
import libs.luan.Lista;
import libs.luan.fmt;

import java.util.Random;

public class Habitacional {


    public static void init(Renderizador mCidade, Lista<AvenidaViaria> avenidas, Citatte eCitatte) {


        int p_25 = avenidas.getQuantidade() / 4;
        int p_50 = p_25 + p_25;
        int p_75 = p_50 + p_25;

        int i = 0;


        for (AvenidaViaria avenida : avenidas) {
            i += 1;

            if (avenida.isHorizontal()) {

                int pos_x = avenida.getX();

                criar_casas_horizontal_acima(mCidade, eCitatte, avenida, pos_x, avenida.getY() - 3);
                criar_casas_horizontal_abaixo(mCidade, eCitatte, avenida, pos_x, avenida.getY() + 3);

            } else if (avenida.isVertical()) {

                int pos_y = avenida.getY();

                criar_casas_vertical_esquerda(mCidade, eCitatte, avenida, avenida.getX() - 3, pos_y);
                criar_casas_vertical_direita(mCidade, eCitatte, avenida, avenida.getX() + 3, pos_y);

            }

            if (i == p_25) {
                fmt.print("HABITACIONANDO : 25.0 %");
            } else if (i == p_50) {
                fmt.print("HABITACIONANDO : 50.0 %");
            } else if (i == p_75) {
                fmt.print("HABITACIONANDO : 75.0 %");
            }
        }

        fmt.print("HABITACIONANDO : 100.0 %");

    }


    public static void criar_casas_horizontal_acima(Renderizador mCidade, Citatte eCitatte, AvenidaViaria avenida, int pos_x, int pos_y) {

        Random sorte = new Random();
        Cores mCores = new Cores();

        int tamanho_maximo = pos_x + avenida.getComprimento();

        while (pos_x < tamanho_maximo) {

            int tipo = sorte.nextInt(100);

            if (tipo >= 75) {

                if (EngenhariaDeObras.is_tudo_preto(mCidade, pos_x, pos_y - 10, 20, 10)) {

                    if (!eCitatte.tem_construcao(pos_x, pos_y - 10, 20, 10)) {
                        mCidade.drawRect(pos_x, pos_y - 10, 20, 10, mCores.getTurquesa());
                        eCitatte.area_criar(new Retangulo(pos_x, pos_y - 10, 20, 10), "AV(" + avenida.getID() + ")");
                    }

                }

                pos_x += 22;

            } else if (tipo >= 50 && tipo < 75) {

                if (EngenhariaDeObras.is_tudo_preto(mCidade, pos_x, pos_y - 10, 10, 10)) {
                    if (!eCitatte.tem_construcao(pos_x, pos_y - 10, 10, 10)) {
                        mCidade.drawRect(pos_x, pos_y - 10, 10, 10, mCores.getTurquesa());
                        eCitatte.area_criar(new Retangulo(pos_x, pos_y - 10, 10, 10), "AV(" + avenida.getID() + ")");
                    }
                }

                pos_x += 12;

            } else if (tipo >= 25 && tipo < 50) {
                if (EngenhariaDeObras.is_tudo_preto(mCidade, pos_x, pos_y - 10, 5, 10)) {
                    if (!eCitatte.tem_construcao(pos_x, pos_y - 10, 5, 10)) {
                        mCidade.drawRect(pos_x, pos_y - 10, 5, 10, mCores.getTurquesa());
                        eCitatte.area_criar(new Retangulo(pos_x, pos_y - 10, 5, 10), "AV(" + avenida.getID() + ")");
                    }
                }

                pos_x += 7;

            } else {
                if (EngenhariaDeObras.is_tudo_preto(mCidade, pos_x, pos_y - 5, 5, 5)) {
                    if (!eCitatte.tem_construcao(pos_x, pos_y - 5, 5, 5)) {
                        mCidade.drawRect(pos_x, pos_y - 5, 5, 5, mCores.getTurquesa());
                        eCitatte.area_criar(new Retangulo(pos_x, pos_y - 5, 5, 5), "AV(" + avenida.getID() + ")");
                    }
                }


                pos_x += 7;

            }

        }

    }

    public static void criar_casas_horizontal_abaixo(Renderizador mCidade, Citatte eCitatte, AvenidaViaria avenida, int pos_x, int pos_y) {

        Random sorte = new Random();
        Cores mCores = new Cores();

        int tamanho_maximo = pos_x + avenida.getComprimento();

        while (pos_x < tamanho_maximo) {

            int tipo = sorte.nextInt(100);

            if (tipo >= 75) {

                if (EngenhariaDeObras.is_tudo_preto(mCidade, pos_x, pos_y, 20, 10)) {
                    if (!eCitatte.tem_construcao(pos_x, pos_y, 20, 10)) {
                        mCidade.drawRect(pos_x, pos_y, 20, 10, mCores.getTurquesa());
                        eCitatte.area_criar(new Retangulo(pos_x, pos_y, 20, 10), "AV(" + avenida.getID() + ")");
                    }
                }


                pos_x += 22;

            } else if (tipo >= 50 && tipo < 75) {

                if (EngenhariaDeObras.is_tudo_preto(mCidade, pos_x, pos_y, 10, 10)) {
                    if (!eCitatte.tem_construcao(pos_x, pos_y, 10, 10)) {
                        mCidade.drawRect(pos_x, pos_y, 10, 10, mCores.getTurquesa());
                        eCitatte.area_criar(new Retangulo(pos_x, pos_y, 10, 10), "AV(" + avenida.getID() + ")");
                    }
                }


                pos_x += 12;

            } else if (tipo >= 25 && tipo < 50) {
                if (EngenhariaDeObras.is_tudo_preto(mCidade, pos_x, pos_y, 5, 10)) {
                    if (!eCitatte.tem_construcao(pos_x, pos_y, 5, 10)) {
                        mCidade.drawRect(pos_x, pos_y, 5, 10, mCores.getTurquesa());
                        eCitatte.area_criar(new Retangulo(pos_x, pos_y, 5, 10), "AV(" + avenida.getID() + ")");
                    }
                }


                pos_x += 7;

            } else {
                if (EngenhariaDeObras.is_tudo_preto(mCidade, pos_x, pos_y, 5, 5)) {
                    if (!eCitatte.tem_construcao(pos_x, pos_y, 5, 5)) {
                        mCidade.drawRect(pos_x, pos_y, 5, 5, mCores.getTurquesa());
                        eCitatte.area_criar(new Retangulo(pos_x, pos_y, 5, 5), "AV(" + avenida.getID() + ")");
                    }
                }


                pos_x += 7;

            }

        }

    }

    public static void criar_casas_vertical_esquerda(Renderizador mCidade, Citatte eCitatte, AvenidaViaria avenida, int pos_x, int pos_y) {

        Random sorte = new Random();
        Cores mCores = new Cores();

        int tamanho_maximo = pos_y + avenida.getComprimento();

        while (pos_y < tamanho_maximo) {

            int tipo = sorte.nextInt(100);

            if (tipo >= 75) {

                if (EngenhariaDeObras.is_tudo_preto(mCidade, pos_x - 20, pos_y, 20, 10)) {
                    if (!eCitatte.tem_construcao(pos_x - 20, pos_y, 20, 10)) {
                        mCidade.drawRect(pos_x - 20, pos_y, 20, 10, mCores.getTurquesa());
                        eCitatte.area_criar(new Retangulo(pos_x - 20, pos_y, 20, 10), "AV(" + avenida.getID() + ")");
                    }
                }


                pos_y += 22;

            } else if (tipo >= 50 && tipo < 75) {
                if (EngenhariaDeObras.is_tudo_preto(mCidade, pos_x - 10, pos_y, 10, 10)) {
                    if (!eCitatte.tem_construcao(pos_x - 10, pos_y, 10, 10)) {
                        mCidade.drawRect(pos_x - 10, pos_y, 10, 10, mCores.getTurquesa());
                        eCitatte.area_criar(new Retangulo(pos_x - 10, pos_y, 10, 10), "AV(" + avenida.getID() + ")");
                    }
                }


                pos_y += 12;
            } else if (tipo >= 25 && tipo < 50) {
                if (EngenhariaDeObras.is_tudo_preto(mCidade, pos_x - 5, pos_y, 5, 10)) {
                    if (!eCitatte.tem_construcao(pos_x - 5, pos_y, 5, 10)) {
                        mCidade.drawRect(pos_x - 5, pos_y, 5, 10, mCores.getTurquesa());
                        eCitatte.area_criar(new Retangulo(pos_x - 5, pos_y, 5, 10), "AV(" + avenida.getID() + ")");
                    }
                }


                pos_y += 7;

            } else {
                if (EngenhariaDeObras.is_tudo_preto(mCidade, pos_x - 5, pos_y, 5, 5)) {
                    if (!eCitatte.tem_construcao(pos_x - 5, pos_y, 5, 5)) {
                        mCidade.drawRect(pos_x - 5, pos_y, 5, 5, mCores.getTurquesa());
                        eCitatte.area_criar(new Retangulo(pos_x - 5, pos_y, 5, 5), "AV(" + avenida.getID() + ")");
                    }
                }


                pos_y += 7;

            }

        }

    }

    public static void criar_casas_vertical_direita(Renderizador mCidade, Citatte eCitatte, AvenidaViaria avenida, int pos_x, int pos_y) {

        Random sorte = new Random();
        Cores mCores = new Cores();

        int tamanho_maximo = pos_y + avenida.getComprimento();

        while (pos_y < tamanho_maximo) {

            int tipo = sorte.nextInt(100);

            if (tipo >= 75) {
                if (EngenhariaDeObras.is_tudo_preto(mCidade, pos_x, pos_y, 20, 10)) {
                    if (!eCitatte.tem_construcao(pos_x, pos_y, 20, 10)) {
                        mCidade.drawRect(pos_x, pos_y, 20, 10, mCores.getTurquesa());
                        eCitatte.area_criar(new Retangulo(pos_x, pos_y, 20, 10), "AV(" + avenida.getID() + ")");
                    }
                }


                pos_y += 22;
            } else if (tipo >= 50 && tipo < 75) {
                if (EngenhariaDeObras.is_tudo_preto(mCidade, pos_x, pos_y, 10, 10)) {
                    if (!eCitatte.tem_construcao(pos_x, pos_y, 10, 10)) {
                        mCidade.drawRect(pos_x, pos_y, 10, 10, mCores.getTurquesa());
                        eCitatte.area_criar(new Retangulo(pos_x, pos_y, 10, 10), "AV(" + avenida.getID() + ")");
                    }
                }


                pos_y += 12;
            } else if (tipo >= 25 && tipo < 50) {
                if (EngenhariaDeObras.is_tudo_preto(mCidade, pos_x, pos_y, 5, 10)) {
                    if (!eCitatte.tem_construcao(pos_x, pos_y, 5, 10)) {
                        mCidade.drawRect(pos_x, pos_y, 5, 10, mCores.getTurquesa());
                        eCitatte.area_criar(new Retangulo(pos_x, pos_y, 5, 10), "AV(" + avenida.getID() + ")");
                    }
                }


                pos_y += 7;
            } else {
                if (EngenhariaDeObras.is_tudo_preto(mCidade, pos_x, pos_y, 5, 5)) {
                    if (!eCitatte.tem_construcao(pos_x, pos_y, 5, 5)) {
                        mCidade.drawRect(pos_x, pos_y, 5, 5, mCores.getTurquesa());
                        eCitatte.area_criar(new Retangulo(pos_x, pos_y, 5, 5), "AV(" + avenida.getID() + ")");
                    }
                }


                pos_y += 7;

            }

        }

    }

}
