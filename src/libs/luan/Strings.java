package libs.luan;

import libs.arquivos.binario.Inteiro;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Strings {

    public static String retirar_espaco_do_comeco(String texto) {

        int i = 0;
        int o = texto.length();

        String ret = "";
        boolean comecou = false;

        while (i < o) {
            String c = String.valueOf(texto.charAt(i));
            if (comecou) {
                ret += c;
            } else {
                if (c.contentEquals(" ") || c.contentEquals("\t") || c.contentEquals("\n")) {

                } else {
                    comecou = true;
                    ret += c;
                }
            }

            i += 1;
        }

        return ret;
    }


    public static ArrayList<String> dividir_linhas(String texto) {
        ArrayList<String> linhas = new ArrayList<String>();

        int i = 0;
        int o = texto.length();

        String linha = "";

        while (i < o) {
            String c = String.valueOf(texto.charAt(i));
            if (c.contentEquals("\n")) {
                linhas.add(linha);
                linha = "";
            } else {
                linha += c;
            }
            i += 1;
        }
        if (linha.length() > 0) {
            linhas.add(linha);
        }
        return linhas;
    }

    public static ArrayList<String> dividir_em_partes(String texto, int fixo) {
        ArrayList<String> linhas = new ArrayList<String>();

        int i = 0;
        int e = 0;

        int o = texto.length();

        String linha = "";

        while (i < o) {
            String c = String.valueOf(texto.charAt(i));
            linha += c;

            i += 1;
            e += 1;

            if (e >= fixo) {
                e = 0;
                linhas.add(linha);
                linha = "";
            }
        }
        if (linha.length() > 0) {
            linhas.add(linha);
        }
        return linhas;
    }


    public static ArrayList<String> ordenar(ArrayList<String> entradas) {

        int n = entradas.size();
        String temp = null;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {

                if (entradas.get(j - 1).compareTo(entradas.get(j)) > 0) {
                    temp = entradas.get(j - 1);
                    entradas.set(j - 1, entradas.get(j));
                    entradas.set(j, temp);

                }

            }
        }
        return entradas;
    }

    public static String exibir_lista_em_linha(ArrayList<String> lista) {
        String ret = "";

        for (String item : lista) {
            ret += item + " ";
        }

        return ret;
    }

    public static String numero_zerado(int v) {
        String s = String.valueOf(v);
        if (s.length() == 1) {
            return "0" + s;
        } else {
            return s;
        }
    }

    public static String numero_zerado(String v) {
        String s = String.valueOf(v);
        if (s.length() == 1) {
            return "0" + s;
        } else {
            return s;
        }
    }


    public static String GET_REVERSO_ATE(String texto, String ate) {

        String ret = "";

        int o = texto.length() - 1;

        while (o >= 0) {
            String letra = String.valueOf(texto.charAt(o));
            if (letra.contentEquals(ate)) {
                break;
            }
            ret = letra + ret;
            o -= 1;
        }

        return ret;

    }


    public static String exibir_justificado(String texto, int tamanho) {

        String justificado = "";

        int i = 0;
        int o = texto.length();

        String palavra = "";
        String linha = "";

        while (i < o) {
            String letra = String.valueOf(texto.charAt(i));
            if (letra.contentEquals(" ")) {
                String linha_futura = "";

                if (linha.length() == 0) {
                    linha_futura = palavra;
                } else {
                    linha_futura = linha + " " + palavra;
                }

                if (linha_futura.length() >= tamanho) {
                    justificado += linha_futura + "\n";
                    linha = "";
                } else {
                    linha = linha_futura;
                }

                palavra = "";
            } else {
                palavra += letra;
            }
            i += 1;
        }

        if (linha.length() > 0 || palavra.length() > 0) {
            String linha_futura = linha + " " + palavra;
            justificado += linha_futura + "\n";
        }

        return justificado;

    }


    public static int indice(String conjunto, String letra) {

        int indice_retornar = 0;

        int o = conjunto.length();
        while (indice_retornar < o) {
            String proc_letra = String.valueOf(conjunto.charAt(indice_retornar));
            if (proc_letra.contentEquals(letra)) {
                break;
            }
            indice_retornar += 1;
        }


        return indice_retornar;

    }


    public static void println(Lista<String> ls){
        for(String item : ls){
            fmt.print("{}",item);
        }
    }

    public static int STRING_HASH_C10(String texto) {

        int limite = 1000;
        int valor = 0;

        int i = 0;
        byte[] bytes = texto.getBytes(StandardCharsets.UTF_8);
        int o = bytes.length;

        while (i < o) {
            int c = Inteiro.byteToInt(bytes[i]);
            valor += c;
            if (valor >= limite) {
                valor -= limite;
            }
            i += 1;
        }

        return valor;

    }


    public static Hasher<String> HASH() {
        return new Hasher<String>() {
            @Override
            public int hash(String eObjeto) {
                return STRING_HASH_C10(eObjeto);
            }
        };
    }

    public static Igualdade<String> IGUALDADE() {
        return new Igualdade<String>() {
            @Override
            public boolean isIgual(String v1, String v2) {
                return v1.contentEquals(v2);
            }
        };
    }

}
