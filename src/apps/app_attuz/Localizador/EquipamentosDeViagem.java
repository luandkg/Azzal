package apps.app_attuz.Localizador;

import libs.luan.fmt;

import java.util.ArrayList;

public class EquipamentosDeViagem {

    public static int getPercurso(ArrayList<String> pontos) {
        int p = 0;

        int i = 0;
        int o = pontos.size();

        String anterior = "";

        while (i < o) {
            String corrente = pontos.get(i);

            if (!corrente.contentEquals(anterior)) {
                anterior = corrente;
                p += 1;
            }
            i += 1;
        }


        return p * 10;
    }

    public static int getPassos(ArrayList<String> pontos) {

        int passos_1_terco = (getPercurso(pontos) * 100) / 3;
        int passos_2_tercos = passos_1_terco * 2;

        int quantidade_de_passos = (passos_2_tercos * 3) + (passos_1_terco * 2);

        return quantidade_de_passos;
    }

    public static int getPercursoDesde(ArrayList<String> pontos, String eDesde) {
        int p = 0;

        int i = 0;
        int o = pontos.size();

        String anterior = "";
        boolean contar = false;

        while (i < o) {
            String corrente = pontos.get(i);

            if (eDesde.contentEquals(corrente)) {
                contar = true;
            }

            if (contar && !eDesde.contentEquals(corrente)) {
                if (!corrente.contentEquals(anterior)) {
                    anterior = corrente;
                    p += 1;
                }
            }

            i += 1;
        }


        return p * 10;
    }

    public static String getTempo(int valor) {
        String v = "";

        v = valor + " ittas";

        if (valor >= 100) {

            int arcos = 0;

            while (valor >= 100) {
                valor -= 100;
                arcos += 1;
            }

            if (valor == 0) {
                if (arcos == 1) {
                    v = arcos + " arko";
                } else {
                    v = arcos + " arkos";
                }
            } else {

                if (arcos == 1) {
                    v = arcos + " arko";
                } else {
                    v = arcos + " arkos";
                }

                if (valor == 1) {
                    v += " " + valor + " itta";
                } else {
                    v += " " + valor + " ittas";
                }

            }

        }

        return v;
    }

    public static String getVelocidade_StgzIttas(int distancia, int tempo) {
        double velocidade = (double) distancia / (double) tempo;
        return fmt.doubleNumC3(velocidade) + " Stgz/ittas";
    }
    public static String getVelocidade_StgzArko(int distancia, int tempo) {
        double velocidade = (double) distancia / (double) tempo/ 100.0;
        return fmt.doubleNumC3(velocidade) + " Stgz/Arko";
    }
}
