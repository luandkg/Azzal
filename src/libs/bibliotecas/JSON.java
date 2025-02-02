package libs.bibliotecas;

import java.util.ArrayList;

import libs.dkg.DKG;
import libs.dkg.DKGObjeto;
import libs.luan.Par;

public class JSON {

    private static String texto = "";
    private static int i = 0;
    private static int o = 0;

    private static int i_tokens = 0;
    private static int o_tokens = 0;

    private static final int JSON_STRING = 1;
    private static final int JSON_NUMERO = 2;
    private static final int JSON_ABRIR_COLCHETES = 3;
    private static final int JSON_FECHAR_COLCHETES = 4;
    private static final int JSON_ABRIR_CHAVES = 5;
    private static final int JSON_FECHAR_CHAVES = 6;
    private static final int JSON_DOIS_PONTOS = 7;
    private static final int JSON_INVALIDO = 8;

    public static void EXIBIR_TOKENS(ArrayList<Par<Integer, String>> tokens) {
        for (Par<Integer, String> token : tokens) {
            // fmt.println(token.getChave() + " :: " + token.getValor());
        }
    }

    public static DKG JSON_TO_DKG(String atexto) {

        ArrayList<Par<Integer, String>> tokens = new ArrayList<Par<Integer, String>>();

        texto = atexto;
        // parse(texto);
        i = 0;
        o = texto.length();

        DKG documento = new DKG();

        while (i < o) {
            String letra = String.valueOf(texto.charAt(i));
            if (letra.contentEquals("\"")) {
                tokens.add(new Par<>(JSON_STRING, obter_texto()));
            } else if (isDigito(letra)) {
                tokens.add(new Par<>(JSON_NUMERO, obter_numero()));
            } else if (letra.contentEquals("\n")) {
            } else if (letra.contentEquals("\t")) {
            } else if (letra.contentEquals("\r")) {
            } else if (letra.contentEquals(" ")) {
            } else if (letra.contentEquals(":")) {
                tokens.add(new Par<>(JSON_DOIS_PONTOS, letra));
            } else if (letra.contentEquals("{")) {
                tokens.add(new Par<>(JSON_ABRIR_CHAVES, letra));
            } else if (letra.contentEquals("}")) {
                tokens.add(new Par<>(JSON_FECHAR_CHAVES, letra));
            } else if (letra.contentEquals("[")) {
                tokens.add(new Par<>(JSON_ABRIR_COLCHETES, letra));
            } else if (letra.contentEquals("]")) {
                tokens.add(new Par<>(JSON_FECHAR_COLCHETES, letra));
            } else {
                tokens.add(new Par<>(JSON_INVALIDO, letra));
            }
            i += 1;
        }

        to_json(tokens, documento);

        return documento;
    }

    public static void to_json(ArrayList<Par<Integer, String>> tokens, DKG documento) {

        i_tokens = 0;
        o_tokens = tokens.size();

        while (i_tokens < o_tokens) {
            Par<Integer, String> token = tokens.get(i_tokens);
            if (token.getChave() == JSON_ABRIR_CHAVES) {
                DKGObjeto o_json = documento.criarObjeto("JSON");

                // fmt.println("ENTRANDO RAIZ ->> " + tokens.get(i_tokens).getValor());

                i_tokens += 1;

                entrar_objeto(tokens, o_json);

                if (i_tokens >= o_tokens) {
                    break;
                }

                // fmt.println("SAINDO RAIZ ->> " + tokens.get(i_tokens).getValor());

            } else if (token.getChave() == JSON_STRING) {

                boolean foi = false;

                boolean tem_proximo = (i_tokens + 1) < o_tokens;
                if (tem_proximo) {
                    Par<Integer, String> proximo_token = tokens.get(i_tokens + 1);
                    if (proximo_token.getChave() == JSON_DOIS_PONTOS) {

                        boolean tem_proximo_2 = (i_tokens + 2) < o_tokens;
                        if (tem_proximo_2) {
                            Par<Integer, String> proximo_token2 = tokens.get(i_tokens + 2);
                            if (proximo_token2.getChave() == JSON_STRING) {

                                DKGObjeto ob = documento.criarObjeto("JSON");
                                ob.identifique(token.getValor(), proximo_token2.getValor());

                                foi = true;
                                i_tokens += 2;
                            }
                        }

                    }
                }

                if (!foi) {
                    DKGObjeto ob = documento.criarObjeto(token.getValor());
                }

            } else if (token.getChave() == JSON_FECHAR_CHAVES) {
                break;
            }
            i_tokens += 1;
        }

    }

    public static void entrar_objeto(ArrayList<Par<Integer, String>> tokens, DKGObjeto pai) {

        // fmt.println("OBJETO ESCOPO INICIO ->> ");

        while (i_tokens < o_tokens) {
            Par<Integer, String> token = tokens.get(i_tokens);
            if (token.getChave() == JSON_ABRIR_CHAVES) {
                i_tokens += 1;

                // fmt.println("COMECAR OBJETO ->> " + tokens.get(i_tokens).getValor());

                entrar_objeto(tokens, pai);

                if (i_tokens >= o_tokens) {
                    return;
                }
                // fmt.println("TERMINAR OBJETO ->> " + tokens.get(i_tokens).getValor());

                if (tokens.get(i_tokens).getChave() == JSON_FECHAR_CHAVES) {
                    // fmt.println("SAINDO TERMINAR OBJETO ->> " + tokens.get(i_tokens).getValor());
                    break;
                }

            } else if (token.getChave() == JSON_STRING) {

                boolean foi = false;

                boolean tem_proximo = (i_tokens + 1) < o_tokens;
                if (tem_proximo) {
                    Par<Integer, String> proximo_token = tokens.get(i_tokens + 1);
                    if (proximo_token.getChave() == JSON_DOIS_PONTOS) {

                        boolean tem_proximo_2 = (i_tokens + 2) < o_tokens;
                        if (tem_proximo_2) {
                            Par<Integer, String> proximo_token2 = tokens.get(i_tokens + 2);
                            if (proximo_token2.getChave() == JSON_STRING) {
                                pai.identifique(token.getValor(), proximo_token2.getValor());
                                foi = true;
                                i_tokens += 2;
                            } else if (proximo_token2.getChave() == JSON_NUMERO) {
                                pai.identifique(token.getValor(), proximo_token2.getValor());
                                foi = true;
                                i_tokens += 2;
                            } else if (proximo_token2.getChave() == JSON_ABRIR_CHAVES) {
                                i_tokens += 2;

                                // fmt.println("ENTRANO NO OBJETO :: " + token.getValor());

                                entrar_objeto(tokens, pai.criarObjeto(token.getValor()));

                                // fmt.println("SAINDO NO OBJETO :: " + token.getValor());

                                if (i_tokens >= o_tokens) {
                                    break;
                                }

                                // fmt.println("CORRENTE :: " + tokens.get(i_tokens).getValor());

                                foi = true;
                            } else if (proximo_token2.getChave() == JSON_ABRIR_COLCHETES) {
                                i_tokens += 2;
                                // fmt.println("ENTRANO COLCHETE []");

                                entrar_objeto_colchete(tokens, pai.criarObjeto(token.getValor()));

                            }
                        }

                    }
                }

                if (i_tokens >= o_tokens) {
                    break;
                }
                // fmt.println("ENTAO :::>>> " + tokens.get(i_tokens).getValor());

            } else if (token.getChave() == JSON_FECHAR_CHAVES) {
                // fmt.println("BORA FECHAR :::>>> " + tokens.get(i_tokens).getValor());
                break;
            } else {
                // System.out.println("ITEM -->> " + token.getValor());
            }
            i_tokens += 1;
        }

        if (i_tokens >= o_tokens) {
            return;
        }
        // fmt.println("OBJETO ESCOPO TERMINAR ->> " + tokens.get(i_tokens).getValor());

    }

    public static void entrar_objeto_colchete(ArrayList<Par<Integer, String>> tokens, DKGObjeto pai) {

        while (i_tokens < o_tokens) {
            Par<Integer, String> token = tokens.get(i_tokens);
            if (token.getChave() == JSON_STRING) {
                pai.criarObjeto(token.getValor());
                // entrar_objeto(tokens, pai);
            } else if (token.getChave() == JSON_NUMERO) {
                pai.criarObjeto(token.getValor());
                // entrar_objeto(tokens, pai);

            } else if (token.getChave() == JSON_FECHAR_COLCHETES) {
                break;
            }
            i_tokens += 1;
        }
    }

    public static String obter_texto() {
        i += 1;
        String ret = "";
        while (i < o) {
            String letra = String.valueOf(texto.charAt(i));
            if (letra.contentEquals("\"")) {
                break;
            } else {
                ret += letra;
            }
            i += 1;
        }
        return ret;
    }

    public static String obter_numero() {
        String ret = "";
        while (i < o) {
            String letra = String.valueOf(texto.charAt(i));
            if (isDigito(letra)) {
                ret += letra;
            } else {
                i -= 1;
                break;
            }
            i += 1;
        }
        return ret;
    }

    public static boolean isDigito(String c) {
        boolean ret = false;
        String conjunto = "0123456789.-";

        int ci = 0;
        int co = conjunto.length();

        while (ci < co) {
            String c_letra = String.valueOf(conjunto.charAt(ci));
            if (c_letra.contentEquals(c)) {
                ret = true;
                break;
            }
            ci += 1;
        }

        return ret;
    }

}
