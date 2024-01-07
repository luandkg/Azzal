package libs.tronarko;

import libs.luan.Lista;
import libs.luan.fmt;

public class Tronarkofmt {

    // CRIADO 2024 01 02

    public static void EXIBIR_HIPERARKO_COMPACTADO(Tozte tozte) {

        int tracos = (4 * 10) + 2;
        int superarko = 1;


        fmt.print("{}", fmt.repetir("-", tracos));

        String nome = tozte.getHiperarko() + " - " + tozte.getHiperarko_Status().toString();
        String nome_centralizado = fmt.centralizar(nome, tracos - 2);

        fmt.print("|{}|", nome_centralizado);

        fmt.print("{}", fmt.repetir("-", tracos));

        for (int m = 0; m < 5; m++) {
            String linha = "|";
            for (int si = 0; si < 10; si++) {
                linha += " " + fmt.espacar_antes(superarko, 2) + " ";
                superarko += 1;
            }
            linha += "|";
            fmt.print("{}", linha);
        }
        fmt.print("{}", fmt.repetir("-", tracos));


    }

    public static void EXIBIR_HIPERARKO(Tozte tozte) {

        int tracos = (5 * 10) + 3;
        int superarko = 1;

        fmt.print("{}", fmt.repetir("-", tracos));

        String nome = tozte.getHiperarko() + " - " + tozte.getHiperarko_Status().toString();
        String nome_centralizado = fmt.centralizar(nome, tracos - 2);

        fmt.print("|{}|", nome_centralizado);

        fmt.print("{}", fmt.repetir("-", tracos));

        for (int m = 0; m < 5; m++) {
            String linha = "| ";
            for (int si = 0; si < 10; si++) {
                linha += " " + fmt.espacar_antes(superarko, 2) + "  ";
                superarko += 1;
            }
            linha += "|";
            fmt.print("{}", linha);
        }
        fmt.print("{}", fmt.repetir("-", tracos));


    }

    public static void EXIBIR_HIPERARKO_E_MARQUE_TOZTE(Tozte tozte) {

        int tracos = (5 * 10) + 3;
        int superarko = 1;

        fmt.print("{}", fmt.repetir("-", tracos));

        String nome = tozte.getHiperarko() + " - " + tozte.getHiperarko_Status().toString();

        fmt.print("|{}|", fmt.centralizar(nome, tracos - 2));

        fmt.print("{}", fmt.repetir("-", tracos));

        for (int m = 0; m < 5; m++) {
            String linha = "| ";
            for (int si = 0; si < 10; si++) {
                if (superarko == tozte.getSuperarko()) {
                    linha += "<" + fmt.espacar_antes(superarko, 2) + "> ";
                } else {
                    linha += " " + fmt.espacar_antes(superarko, 2) + "  ";
                }
                superarko += 1;
            }
            linha += "|";
            fmt.print("{}", linha);
        }
        fmt.print("{}", fmt.repetir("-", tracos));


    }


    public static Lista<String> EXIBIR_HIPERARKO_COMPACTADO_FRASES(int hiperarko) {

        Lista<String> ret = new Lista<String>();

        int tracos = (4 * 10) + 2;
        int superarko = 1;


        ret.adicionar(fmt.format("{}", fmt.repetir("-", tracos)));

        String nome = hiperarko + " - " + Hiperarkos.get(hiperarko).toString();
        String nome_centralizado = fmt.centralizar(nome, tracos - 2);

        ret.adicionar(fmt.format("|{}|", nome_centralizado));

        ret.adicionar(fmt.format("{}", fmt.repetir("-", tracos)));

        for (int m = 0; m < 5; m++) {
            String linha = "|";
            for (int si = 0; si < 10; si++) {
                linha += " " + fmt.espacar_antes(superarko, 2) + " ";
                superarko += 1;
            }
            linha += "|";
            ret.adicionar(fmt.format("{}", linha));
        }
        ret.adicionar(fmt.format("{}", fmt.repetir("-", tracos)));

        return ret;
    }

    public static Lista<String> EXIBIR_HIPERARKO_E_MARQUE_TOZTE_FRASES(Tozte tozte, int hiperarko) {

        Lista<String> ret = new Lista<String>();

        int tracos = (5 * 10) + 3;
        int superarko = 1;

        ret.adicionar(fmt.format("{}", fmt.repetir("-", tracos)));

        String nome = hiperarko + " - " + Hiperarkos.get(hiperarko).toString();

        ret.adicionar(fmt.format("|{}|", fmt.centralizar(nome, tracos - 2)));

        ret.adicionar(fmt.format("{}", fmt.repetir("-", tracos)));

        for (int m = 0; m < 5; m++) {
            String linha = "| ";
            for (int si = 0; si < 10; si++) {
                if (superarko == tozte.getSuperarko() && hiperarko == tozte.getHiperarko()) {
                    linha += "<" + fmt.espacar_antes(superarko, 2) + "> ";
                } else {
                    linha += " " + fmt.espacar_antes(superarko, 2) + "  ";
                }
                superarko += 1;
            }
            linha += "|";
            ret.adicionar(fmt.format("{}", linha));
        }
        ret.adicionar(fmt.format("{}", fmt.repetir("-", tracos)));

        return ret;
    }


    public static void EXIBIR_TRONARKO_COMPACTADO() {

        int indo = 1;
        for (int dupla = 1; dupla <= 5; dupla++) {

            Lista<String> lado_a = EXIBIR_HIPERARKO_COMPACTADO_FRASES(indo);
            Lista<String> lado_b = EXIBIR_HIPERARKO_COMPACTADO_FRASES(indo + 1);

            fmt.exibir_lado_a_lado(lado_a, "      ", lado_b);

            indo += 2;
        }

    }

    public static void EXIBIR_TRONARKO(Tozte tozte) {

        int indo = 1;

        for (int dupla = 1; dupla <= 5; dupla++) {

            Lista<String> lado_a = EXIBIR_HIPERARKO_E_MARQUE_TOZTE_FRASES(tozte, indo);
            Lista<String> lado_b = EXIBIR_HIPERARKO_E_MARQUE_TOZTE_FRASES(tozte, indo + 1);

            fmt.exibir_lado_a_lado(lado_a, "      ", lado_b);

            indo += 2;
        }

    }

    public static Lista<String> EXIBIR_HIPERARKO_DO_TRONARKO_E_MARQUE_TOZTE_FRASES(Tozte tozte, int eHiperarko, int eTronarko) {

        Lista<String> ret = new Lista<String>();

        int tracos = (5 * 10) + 3;
        int superarko = 1;

        ret.adicionar(fmt.format("{}", fmt.repetir("-", tracos)));

        String nome = eHiperarko + " - " + Hiperarkos.get(eHiperarko).toString();

        ret.adicionar(fmt.format("|{}|", fmt.centralizar(eTronarko, tracos - 2)));
        ret.adicionar(fmt.format("{}", fmt.repetir("-", tracos)));
        ret.adicionar(fmt.format("|{}|", fmt.centralizar(nome, tracos - 2)));
        ret.adicionar(fmt.format("{}", fmt.repetir("-", tracos)));

        for (int m = 0; m < 5; m++) {
            String linha = "| ";
            for (int si = 0; si < 10; si++) {
                if (tozte.isIgual(new Tozte(superarko, eHiperarko, eTronarko))) {
                    linha += "<" + fmt.espacar_antes(superarko, 2) + "> ";
                } else {
                    linha += " " + fmt.espacar_antes(superarko, 2) + "  ";
                }
                superarko += 1;
            }
            linha += "|";
            ret.adicionar(fmt.format("{}", linha));
        }

        ret.adicionar(fmt.format("{}", fmt.repetir("-", tracos)));

        return ret;
    }

}
