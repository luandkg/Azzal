package libs.luan;

import libs.dkg.DKG;
import libs.dkg.DKGObjeto;

public class JSONDemanda {

    private static String texto = "";
    private static int i = 0;
    private static int o = 0;


    private static final int JSON_STRING = 1;
    private static final int JSON_NUMERO = 2;
    private static final int JSON_ABRIR_COLCHETES = 3;
    private static final int JSON_FECHAR_COLCHETES = 4;
    private static final int JSON_ABRIR_CHAVES = 5;
    private static final int JSON_FECHAR_CHAVES = 6;
    private static final int JSON_DOIS_PONTOS = 7;
    private static final int JSON_INVALIDO = 8;
    private static final int JSON_CONCLUIDO = 9;

    private static  Par<Integer, String> TOKEN_RECENTE = null;

    public static DKG JSON_TO_DKG(String atexto) {

        texto = atexto;
        i = 0;
        o = texto.length();

       // fmt.print(">> JSON Iniciando {} com {}",i,o);

        DKG documento = new DKG();

        to_json( documento);

        return documento;
    }


    private static Par<Integer, String> obter_token(){

        Par<Integer, String> token_corrente = new Par<Integer, String>(JSON_CONCLUIDO,"");

        while (i < o) {
            String letra = String.valueOf(texto.charAt(i));
            if (letra.contentEquals("\"")) {
                token_corrente=new Par<>(JSON_STRING, obter_texto());
                break;
            } else if (isDigito(letra)) {
                token_corrente= new Par<>(JSON_NUMERO, obter_numero());
                break;
            } else if (letra.contentEquals("\n")) {
            } else if (letra.contentEquals("\t")) {
            } else if (letra.contentEquals("\r")) {
            } else if (letra.contentEquals(" ")) {
            } else if (letra.contentEquals(":")) {
                token_corrente=(new Par<>(JSON_DOIS_PONTOS, letra));
                break;
            } else if (letra.contentEquals("{")) {
                token_corrente=(new Par<>(JSON_ABRIR_CHAVES, letra));
                break;
            } else if (letra.contentEquals("}")) {
                token_corrente=(new Par<>(JSON_FECHAR_CHAVES, letra));
                break;
            } else if (letra.contentEquals("[")) {
                token_corrente=(new Par<>(JSON_ABRIR_COLCHETES, letra));
                break;
            } else if (letra.contentEquals("]")) {
                token_corrente=(new Par<>(JSON_FECHAR_COLCHETES, letra));
                break;
            } else {
                token_corrente=(new Par<>(JSON_INVALIDO, letra));
                break;
            }
            i += 1;
        }

        i+=1;

        TOKEN_RECENTE=token_corrente;
        return token_corrente;
    }



    private static Par<Integer, String> obter_token_futuro(){

        int i_antes = i;

        Par<Integer, String> token_corrente = new Par<Integer, String>(JSON_CONCLUIDO,"");

        while (i < o) {
            String letra = String.valueOf(texto.charAt(i));
            if (letra.contentEquals("\"")) {
                token_corrente=new Par<>(JSON_STRING, obter_texto());
                break;
            } else if (isDigito(letra)) {
                token_corrente= new Par<>(JSON_NUMERO, obter_numero());
                break;
            } else if (letra.contentEquals("\n")) {
            } else if (letra.contentEquals("\t")) {
            } else if (letra.contentEquals("\r")) {
            } else if (letra.contentEquals(" ")) {
            } else if (letra.contentEquals(":")) {
                token_corrente=(new Par<>(JSON_DOIS_PONTOS, letra));
                break;
            } else if (letra.contentEquals("{")) {
                token_corrente=(new Par<>(JSON_ABRIR_CHAVES, letra));
                break;
            } else if (letra.contentEquals("}")) {
                token_corrente=(new Par<>(JSON_FECHAR_CHAVES, letra));
                break;
            } else if (letra.contentEquals("[")) {
                token_corrente=(new Par<>(JSON_ABRIR_COLCHETES, letra));
                break;
            } else if (letra.contentEquals("]")) {
                token_corrente=(new Par<>(JSON_FECHAR_COLCHETES, letra));
                break;
            } else {
                token_corrente=(new Par<>(JSON_INVALIDO, letra));
                break;
            }
            i += 1;
        }

        i=i_antes;

        return token_corrente;
    }

    private static Par<Integer, String> obter_token_futuro_duplo(){

        int i_antes = i;

        Par<Integer, String> token_corrente = new Par<Integer, String>(JSON_CONCLUIDO,"");

        while (i < o) {
            String letra = String.valueOf(texto.charAt(i));
            if (letra.contentEquals("\"")) {
                token_corrente=new Par<>(JSON_STRING, obter_texto());
                i+=1;
                break;
            } else if (isDigito(letra)) {
                token_corrente= new Par<>(JSON_NUMERO, obter_numero());
                i+=1;
                break;
            } else if (letra.contentEquals("\n")) {
            } else if (letra.contentEquals("\t")) {
            } else if (letra.contentEquals("\r")) {
            } else if (letra.contentEquals(" ")) {
            } else if (letra.contentEquals(":")) {
                token_corrente=(new Par<>(JSON_DOIS_PONTOS, letra));
                i+=1;
                break;
            } else if (letra.contentEquals("{")) {
                token_corrente=(new Par<>(JSON_ABRIR_CHAVES, letra));
                i+=1;
                break;
            } else if (letra.contentEquals("}")) {
                token_corrente=(new Par<>(JSON_FECHAR_CHAVES, letra));
                i+=1;
                break;
            } else if (letra.contentEquals("[")) {
                token_corrente=(new Par<>(JSON_ABRIR_COLCHETES, letra));
                i+=1;
                break;
            } else if (letra.contentEquals("]")) {
                token_corrente=(new Par<>(JSON_FECHAR_COLCHETES, letra));
                i+=1;
                break;
            } else {
                token_corrente=(new Par<>(JSON_INVALIDO, letra));
                break;
            }
            i += 1;
        }



        while (i < o) {
            String letra = String.valueOf(texto.charAt(i));
            if (letra.contentEquals("\"")) {
                token_corrente=new Par<>(JSON_STRING, obter_texto());
                break;
            } else if (isDigito(letra)) {
                token_corrente= new Par<>(JSON_NUMERO, obter_numero());
                break;
            } else if (letra.contentEquals("\n")) {
            } else if (letra.contentEquals("\t")) {
            } else if (letra.contentEquals("\r")) {
            } else if (letra.contentEquals(" ")) {
            } else if (letra.contentEquals(":")) {
                token_corrente=(new Par<>(JSON_DOIS_PONTOS, letra));
                break;
            } else if (letra.contentEquals("{")) {
                token_corrente=(new Par<>(JSON_ABRIR_CHAVES, letra));
                break;
            } else if (letra.contentEquals("}")) {
                token_corrente=(new Par<>(JSON_FECHAR_CHAVES, letra));
                break;
            } else if (letra.contentEquals("[")) {
                token_corrente=(new Par<>(JSON_ABRIR_COLCHETES, letra));
                break;
            } else if (letra.contentEquals("]")) {
                token_corrente=(new Par<>(JSON_FECHAR_COLCHETES, letra));
                break;
            } else {
                token_corrente=(new Par<>(JSON_INVALIDO, letra));
                break;
            }
            i += 1;
        }

        i=i_antes;

        return token_corrente;
    }


    public static void to_json( DKG documento) {


        while (i < o) {
            Par<Integer, String> token =obter_token();
            if (token.getChave() == JSON_ABRIR_CHAVES) {
                DKGObjeto o_json = documento.criarObjeto("JSON");

               //  fmt.println("ENTRANDO RAIZ ->> " + token.getValor());

             //   voltar_token();
                entrar_objeto( o_json);

                if (i >= o) {
                    break;
                }

                // fmt.println("SAINDO RAIZ ->> " + tokens.get(i_tokens).getValor());

            } else if (token.getChave() == JSON_STRING) {

                boolean foi = false;

                boolean tem_proximo = true;
                if (tem_proximo) {
                    Par<Integer, String> proximo_token = obter_token_futuro();
                    if (proximo_token.getChave() == JSON_DOIS_PONTOS) {

                        boolean tem_proximo_2 = true;
                        if (tem_proximo_2) {
                            Par<Integer, String> proximo_token2 = obter_token_futuro_duplo();
                            if (proximo_token2.getChave() == JSON_STRING) {

                                DKGObjeto ob = documento.criarObjeto("JSON");
                                ob.identifique(token.getValor(), proximo_token2.getValor());

                                foi = true;
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
        }

    }

    private static void entrar_objeto( DKGObjeto pai) {

         // fmt.println("OBJETO ESCOPO INICIO ->> " + pai.getNome());

         // fmt.print("ENTRAR OBJETO :: ESTAMOS EM {} de {}",i,o);

        while (i < o) {
            Par<Integer, String> token =obter_token();

          //  fmt.print("ESTAMOS EM {} de {} -- {}",i,o,token.getValor());

            if (token.getChave() == JSON_ABRIR_CHAVES) {

              //   fmt.println("ENTRAR OBJETO :: COMECAR OBJETO  ->> " + token.getValor());

                entrar_objeto( pai);

                if (i >= o) {
                    return;
                }

              //   fmt.println("TERMINAR OBJETO  ->> " + token.getValor());

                if (TOKEN_RECENTE.getChave() == JSON_FECHAR_CHAVES) {
                 //       fmt.println("SAINDO TERMINAR OBJETO  ->> " + token.getValor());
                    break;
                }

            } else if (token.getChave() == JSON_STRING) {

                boolean foi = false;

               // fmt.println("ENTRAR STRING  ->> " + token.getValor());

                boolean tem_proximo = true;
                if (tem_proximo) {
                    Par<Integer, String> proximo_token = obter_token_futuro();

                  //  fmt.println("\t FUTURO 1  ->> " + proximo_token.getValor());

                    if (proximo_token.getChave() == JSON_DOIS_PONTOS) {

                        boolean tem_proximo_2 = true;
                        if (tem_proximo_2) {
                            Par<Integer, String> proximo_token2 = obter_token_futuro_duplo();

                          //  fmt.println("\t FUTURO 2  ->> " + proximo_token2.getValor());

                            if (proximo_token2.getChave() == JSON_STRING) {
                                pai.identifique(token.getValor(), proximo_token2.getValor());
                                foi = true;
                                obter_token();
                                obter_token();
                            } else if (proximo_token2.getChave() == JSON_NUMERO) {
                                pai.identifique(token.getValor(), proximo_token2.getValor());
                                foi = true;
                                obter_token();
                                obter_token();
                            } else if (proximo_token2.getChave() == JSON_ABRIR_CHAVES) {
                                obter_token();
                                obter_token();

                                //  fmt.println("ENTRANO NO OBJETO :: " + token.getValor());

                                entrar_objeto( pai.criarObjeto(token.getValor()));

                                     //  fmt.println("SAINDO NO OBJETO :: " + token.getValor());

                                if (i >= o) {
                                    break;
                                }

                                  // fmt.println("CORRENTE :: " + token.getValor());


                                foi = true;
                            } else if (proximo_token2.getChave() == JSON_ABRIR_COLCHETES) {

                                 //  fmt.println("ENTRANO COLCHETE []");

                                entrar_objeto_colchete( pai.criarObjeto(token.getValor()));

                            }
                        }


                    }
                }

                if (i >= o) {
                    break;
                }
                //fmt.println("ENTAO :::>>> " + tokens.get(i_tokens).getValor());


            } else if (token.getChave() == JSON_FECHAR_CHAVES) {
                //   fmt.println("BORA FECHAR :::>>> " + tokens.get(i_tokens).getValor());
                break;
            } else {
                //   System.out.println("ITEM -->> " + token.getValor());
            }
        }

        if (i >= o) {
            return;
        }
        //   fmt.println("OBJETO ESCOPO TERMINAR ->> " + tokens.get(i_tokens).getValor());


    }

    private static void entrar_objeto_colchete( DKGObjeto pai) {

        while (i < o) {
            Par<Integer, String> token =obter_token();
            if (token.getChave() == JSON_STRING) {
                pai.criarObjeto(token.getValor());
                //  entrar_objeto(tokens, pai);
            } else if (token.getChave() == JSON_NUMERO) {
                pai.criarObjeto(token.getValor());
                //entrar_objeto(tokens, pai);

            } else if (token.getChave() == JSON_ABRIR_CHAVES) {

                DKGObjeto o_json = pai.criarObjeto("ITEM");
                // fmt.println("ENTRANDO RAIZ ->> " + tokens.get(i_tokens).getValor());
             // obter_token();
                entrar_objeto( o_json);


            } else if (token.getChave() == JSON_FECHAR_COLCHETES) {
                break;
            }

        }
    }

    private static String obter_texto() {
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

    private static String obter_numero() {
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


    private static boolean isDigito(String c) {
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
