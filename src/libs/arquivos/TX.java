package libs.arquivos;

import libs.arquivos.binario.Arquivador;
import libs.arquivos.binario.Inteiro;
import libs.luan.Lista;

import java.io.File;
import java.io.IOException;

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
                        arquivador.set_u8((byte) indice);
                    } else {

                        int proximo = (indice - 255) + 1;

                        arquivador.set_u8((byte) 255);
                        arquivador.set_u8((byte) proximo);

                    }

                }

                i += 1;
            }

            arquivador.set_u8((byte) 0);

            arquivador.fechar();

            //    System.out.println("Texto TX - Terminado");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Lista<Byte> toListBytes(String eConteudo) {

        TX eTX = new TX();

        int i = 0;
        int o = eConteudo.length();

        Lista<Byte> bytes = new Lista<Byte>();


        //System.out.println("Texto TX - Iniciado");

        while (i < o) {
            String letra = String.valueOf(eConteudo.charAt(i));

            int indice = eTX.getIndice(letra);

            if (indice >= 0) {

                if (indice < 255) {
                    bytes.adicionar((byte) indice);
                } else {

                    int proximo = (indice - 255) + 1;

                    bytes.adicionar((byte) 255);
                    bytes.adicionar((byte) proximo);

                }

            }

            i += 1;
        }

        bytes.adicionar((byte) 0);

        return bytes;

    }

    public static Lista<Byte> toListaBytes(String eConteudo) {

        TX eTX = new TX();

        int i = 0;
        int o = eConteudo.length();

        Lista<Byte> bytes = new Lista<Byte>();


        //System.out.println("Texto TX - Iniciado");

        while (i < o) {
            String letra = String.valueOf(eConteudo.charAt(i));

            int indice = eTX.getIndice(letra);

            if (indice >= 0) {

                if (indice < 255) {
                    bytes.adicionar((byte) indice);
                } else {

                    int proximo = (indice - 255) + 1;

                    bytes.adicionar((byte) 255);
                    bytes.adicionar((byte) proximo);

                }

            }

            i += 1;
        }

        bytes.adicionar((byte) 0);

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

                int valor = arquivador.get_u8();

                if (valor == 0) {
                    lendo = false;
                } else {
                    if (valor == 255) {

                        int valor2 = arquivador.get_u8();

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

            int valor = arquivador.get_u8();

            if (valor == 0) {
                lendo = false;
            } else {
                if (valor == 255) {

                    int valor2 = arquivador.get_u8();

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

            int valor = arquivador.get_u8();

            if (valor == 0) {
                lendo = false;
            } else {
                if (valor == 255) {

                    int valor2 = arquivador.get_u8();

                    int menos = valor2 - 1;
                    texto += getCaracter(menos);

                } else {

                    int menos = valor - 1;
                    texto += getCaracter(menos);

                }
            }

            contando += 1;
            if (contando > limite) {
                break;
            }
        }


        return texto;
    }

    public static String lerDeBytes(Lista<Byte> bytes) {

        TX eTX = new TX();

        String texto = "";

        // System.out.println("Texto TX - Iniciado");

        boolean lendo = true;

        int ii = 0;
        int oo = bytes.getQuantidade();

        while (lendo && (ii < oo)) {

            int valor = Inteiro.byteToInt(bytes.get(ii));
            ii += 1;

            if (valor == 0) {
                lendo = false;
            } else {
                if (valor == 255) {

                    int valor2 = Inteiro.byteToInt(bytes.get(ii));
                    ii += 1;

                    int menos = valor2 - 1;
                    texto += eTX.getCaracter(menos);

                } else {

                    int menos = valor - 1;
                    texto += eTX.getCaracter(menos);

                }
            }

            if (ii > oo) {
                break;
            }

        }


        return texto;
    }

    public void escreverFluxo(String eConteudo, Arquivador arquivador) {

        int i = 0;
        int o = eConteudo.length();


        //System.out.println("Texto TX - Iniciado");

        while (i < o) {
            String letra = String.valueOf(eConteudo.charAt(i));

            int indice = getIndice(letra);

            if (indice >= 0) {

                if (indice < 255) {
                    arquivador.set_u8((byte) indice);
                } else {

                    int proximo = (indice - 255) + 1;

                    arquivador.set_u8((byte) 255);
                    arquivador.set_u8((byte) proximo);

                }

            }

            i += 1;
        }

        arquivador.set_u8((byte) 0);


        //    System.out.println("Texto TX - Terminado");


    }

    public static String ler_vetor(byte[] bytes) {

        TX eTX = new TX();

        String texto = "";

        // System.out.println("Texto TX - Iniciado");

        boolean lendo = true;

        int ii = 0;
        int oo = bytes.length;

        while (lendo && (ii < oo)) {

            int valor = Inteiro.byteToInt(bytes[ii]);
            ii += 1;

            if (valor == 0) {
                lendo = false;
            } else {
                if (valor == 255) {

                    int valor2 = Inteiro.byteToInt(bytes[ii]);
                    ii += 1;

                    int menos = valor2 - 1;
                    texto += eTX.getCaracter(menos);

                } else {

                    int menos = valor - 1;
                    texto += eTX.getCaracter(menos);

                }
            }

            if (ii > oo) {
                break;
            }

        }


        return texto;
    }


    public static int INDICE_DE_CARACTERE(String letra) {
        TX eTX = new TX();
        return eTX.getIndice(letra);
    }

    public static String OBTER_CARACTERE(int indice) {
        TX eTX = new TX();
        return eTX.getCaracter(indice-1);
    }


}
