package Arquivos;

import Arquivos.Binario.Arquivador;
import Arquivos.Binario.Inteiro;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TX {

    private String CARACTERERES;
    private int CARACTERERES_TAMANHO;

    public TX() {

        CARACTERERES = " abcçdefghijklmnopqrstuvwxyzABCÇDEFGHIJKLMNOPQRSTUVWXYZ";
        CARACTERERES += "0123456789+-*/\\|(){}[]<>,._:;='\"°?!@#$%*°º\t\n";
        CARACTERERES += "áéíóúàèìòùâêîôûãẽĩõũäëïöü";
        CARACTERERES += "ÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÃẼĨÕŨÄËÏÖÜ";
        CARACTERERES += "ćḉǵj́ḱĺḿńṕŕśẃýźĉĝĥĵŝŷŵ";

        CARACTERERES_TAMANHO = CARACTERERES.length();

    }

    public String getCaracteres() {
        return CARACTERERES;
    }

    public int getIndice(String letra_procurada) {
        int i = 0;

        while (i < CARACTERERES_TAMANHO) {
            String letra = String.valueOf(CARACTERERES.charAt(i));
            if (letra.contentEquals(letra_procurada)) {
                return i + 1;
            }
            i += 1;
        }

        return -1;
    }

    public String getCaracter(int indice) {

        if (indice >= 0 && indice < CARACTERERES_TAMANHO) {
            return String.valueOf(CARACTERERES.charAt(indice));
        }

        return "";
    }

    public void toTX(String eConteudo, String eArquivo) {

        int i = 0;
        int o = eConteudo.length();

        File Arq = new File(eArquivo);
        if (Arq.exists()) {
            Arq.delete();
        }

        try {

            //System.out.println("Texto TX - Iniciado");

            Arquivador arquivador = new Arquivador(eArquivo);

            while (i < o) {
                String letra = String.valueOf(eConteudo.charAt(i));

                int indice = getIndice(letra);

                if (indice >= 0) {

                    if (indice < 255) {
                        arquivador.writeByte((byte) indice);
                    } else {

                        int proximo = (indice - 255) + 1;

                        arquivador.writeByte((byte) 255);
                        arquivador.writeByte((byte) proximo);

                    }

                }

                i += 1;
            }

            arquivador.writeByte((byte) 0);

            arquivador.fechar();

            //    System.out.println("Texto TX - Terminado");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Byte> toListBytes(String eConteudo) {

        int i = 0;
        int o = eConteudo.length();

        ArrayList<Byte> bytes = new ArrayList<Byte>();


        //System.out.println("Texto TX - Iniciado");

        while (i < o) {
            String letra = String.valueOf(eConteudo.charAt(i));

            int indice = getIndice(letra);

            if (indice >= 0) {

                if (indice < 255) {
                    bytes.add((byte) indice);
                } else {

                    int proximo = (indice - 255) + 1;

                    bytes.add((byte) 255);
                    bytes.add((byte) proximo);

                }

            }

            i += 1;
        }

        bytes.add((byte) 0);

        return bytes;

    }


    public static void escreverTX(String eConteudo, String eArquivo) {
        TX eTX = new TX();
        eTX.toTX(eConteudo, eArquivo);
    }

    public static String lerTX(String eArquivo) {
        TX eTX = new TX();
        return eTX.deTX(eArquivo);
    }

    public String deTX(String eArquivo) {

        String texto = "";

        try {

            // System.out.println("Texto TX - Iniciado");

            Arquivador arquivador = new Arquivador(eArquivo);

            boolean lendo = true;

            while (lendo) {

                int valor = Inteiro.byteToInt(arquivador.readByte());

                if (valor == 0) {
                    lendo = false;
                } else {
                    if (valor == 255) {

                        int valor2 = Inteiro.byteToInt(arquivador.readByte());

                        int menos = valor2 - 1;
                        texto += getCaracter(menos);

                    } else {

                        int menos = valor - 1;
                        texto += getCaracter(menos);

                    }
                }


            }

            arquivador.fechar();

            // System.out.println("Texto TX - Terminado");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return texto;
    }


    public String lerFluxo(Arquivador arquivador) {

        String texto = "";

        // System.out.println("Texto TX - Iniciado");

        boolean lendo = true;

        while (lendo) {

            int valor = Inteiro.byteToInt(arquivador.readByte());

            if (valor == 0) {
                lendo = false;
            } else {
                if (valor == 255) {

                    int valor2 = Inteiro.byteToInt(arquivador.readByte());

                    int menos = valor2 - 1;
                    texto += getCaracter(menos);

                } else {

                    int menos = valor - 1;
                    texto += getCaracter(menos);

                }
            }


        }


        return texto;
    }

    public String lerFluxoLimitado(Arquivador arquivador, int limite) {

        String texto = "";

        // System.out.println("Texto TX - Iniciado");

        boolean lendo = true;

        int contando = 0;

        while (lendo) {

            int valor = Inteiro.byteToInt(arquivador.readByte());

            if (valor == 0) {
                lendo = false;
            } else {
                if (valor == 255) {

                    int valor2 = Inteiro.byteToInt(arquivador.readByte());

                    int menos = valor2 - 1;
                    texto += getCaracter(menos);

                } else {

                    int menos = valor - 1;
                    texto += getCaracter(menos);

                }
            }

            contando+=1;
            if (contando>limite){
                break;
            }
        }


        return texto;
    }

}
