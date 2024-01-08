package libs.documentar;

import java.util.ArrayList;

public class TextoToPartes {

    public ArrayList<ParteTextual> parser(String texto) {
        ArrayList<ParteTextual> objetos = new ArrayList<ParteTextual>();

        AutoInt index = new AutoInt(0);

        int tamanho = texto.length();

        String juntando = "";

        while (index.get() < tamanho) {

            String letra = String.valueOf(texto.charAt(index.get()));

            if (letra.contentEquals("@")) {

                if (juntando.length() > 0) {

                    if (isValido(juntando)) {

                        juntando = juntando.replace("\n", " ");
                        juntando = juntando.replace("\t", " ");

                        if (juntando.startsWith(" ")) {
                            juntando = zerarInicio(juntando);
                        }

                        ParteTextual parte = new ParteTextual("TEXTO");
                        parte.setConteudo(juntando);
                        objetos.add(parte);
                    }


                    juntando = "";
                }

                index.somar(1);

                ParteTextual parte = new ParteTextual("DESCONHECIDO");
                parser_objeto(index, tamanho, texto, parte);
                objetos.add(parte);

            } else {
                juntando += letra;
            }

            index.somar(1);
        }


        return objetos;
    }


    public void parser_objeto(AutoInt index, int tamanho, String texto, ParteTextual parte) {


        String nome = "";
        boolean abriu = false;
        boolean fechou = false;

        while (index.get() < tamanho) {

            String letra = String.valueOf(texto.charAt(index.get()));

            if (letra.contentEquals(" ") || letra.contentEquals("\t") || letra.contentEquals("(")) {
                break;
            } else {
                nome += letra;
            }

            index.somar(1);
        }

        parte.setTipo(nome);

        while (index.get() < tamanho) {

            String letra = String.valueOf(texto.charAt(index.get()));

            if (letra.contentEquals(" ") || letra.contentEquals("\t")) {

            } else if (letra.contentEquals("(")) {
                abriu = true;
                index.somar(1);
                break;
            }

            index.somar(1);
        }

        if (abriu) {

            while (index.get() < tamanho) {

                String letra = String.valueOf(texto.charAt(index.get()));

                if (letra.contentEquals("\"")) {

                    index.somar(1);
                    String at = getAtributo(index, tamanho, texto);
                    parte.atribuir(at);


                } else if (letra.contentEquals(")")) {
                    fechou = true;
                    break;
                }

                index.somar(1);
            }

        }


    }

    public String getAtributo(AutoInt index, int tamanho, String texto) {

        String juntando = "";

        while (index.get() < tamanho) {

            String letra = String.valueOf(texto.charAt(index.get()));

            if (letra.contentEquals("\"")) {
                break;
            } else {
                juntando += letra;
            }

            index.somar(1);
        }

        return juntando;
    }


    public boolean isValido(String texto) {
        boolean ret = false;

        int index = 0;
        int tamanho = texto.length();

        while (index < tamanho) {
            String letra = String.valueOf(texto.charAt(index));

            if (letra.contentEquals(" ")) {
            } else if (letra.contentEquals("\t")) {
            } else if (letra.contentEquals("\n")) {
            } else {
                ret = true;
                break;
            }
            index += 1;
        }


        return ret;
    }

    public String zerarInicio(String texto) {
        String ret = "";

        int index = 0;
        int tamanho = texto.length();

        while (index < tamanho) {
            String letra = String.valueOf(texto.charAt(index));
            if (letra.contentEquals(" ")) {

            } else {
                break;
            }
            index += 1;
        }

        while (index < tamanho) {
            String letra = String.valueOf(texto.charAt(index));
            ret += letra;
            index += 1;
        }

        return ret;
    }
}
