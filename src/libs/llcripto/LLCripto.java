package libs.llcripto;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class LLCripto {

    public static byte[] intToByte(int value) {
        return new byte[]{(byte) (value >>> 24), (byte) (value >>> 16), (byte) (value >>> 8), (byte) value};
    }

    public static int bytestoInt(byte[] bytes) {
        return ((bytes[0] & 0xFF) << 24) | ((bytes[1] & 0xFF) << 16) | ((bytes[2] & 0xFF) << 8) | ((bytes[3] & 0xFF) << 0);
    }

    public static int normalizar_byte(int valor) {

        while (valor >= 256) {
            valor -= 256;
        }
        while (valor < 0) {
            valor += 256;
        }

        return valor;
    }

    public static String bytes_to_sequencia(byte[] bytes) {

        int indice_indo = 0;
        String sequencia = "";
        while (indice_indo < bytes.length) {
            sequencia += (Byte.toUnsignedInt(bytes[indice_indo])) + " ";
            indice_indo += 1;
        }

        return sequencia;
    }

    public static String ints_to_sequencia(int[] ints) {

        int indice_indo = 0;
        String sequencia = "";
        while (indice_indo < ints.length) {
            sequencia += ints[indice_indo] + " ";
            indice_indo += 1;
        }

        return sequencia;
    }

    public static void demonstracao() {


        System.out.println("LLCripto 1.0");

        String senha = "SENHA_FORTE";
        String mensagem = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";


        int LLCRIPT_TAMANHO_BLOCO = 16;

        Quadrante LLCRIPT_ORIGNUM = new Quadrante();
        LLCRIPT_ORIGNUM.definirLinha(0, 1, 2, 3, 4);
        LLCRIPT_ORIGNUM.definirLinha(1, 5, 6, 7, 8);
        LLCRIPT_ORIGNUM.definirLinha(2, 9, 10, 11, 12);
        LLCRIPT_ORIGNUM.definirLinha(3, 13, 14, 15, 16);

        System.out.println("Matrix Originum");
        System.out.println(LLCRIPT_ORIGNUM.getView());


        Quadrante LLCRIPT_RE_ORIGNUM = new Quadrante();
        LLCRIPT_RE_ORIGNUM.copiarDe(LLCRIPT_ORIGNUM);

        // ORGANIZAR SENHA
        byte[] senha_bytes = senha.getBytes(StandardCharsets.UTF_8);

        int s_index = 0;
        int s_tamanho = senha_bytes.length;
        int s_adicionar = 0;
        int s_limite = LLCRIPT_TAMANHO_BLOCO;

        while (s_index < s_tamanho) {

            int novo_valor = LLCRIPT_RE_ORIGNUM.getDireto(s_adicionar) + Byte.toUnsignedInt(senha_bytes[s_index]);

            novo_valor = normalizar_byte(novo_valor);

            LLCRIPT_RE_ORIGNUM.setDireto(s_adicionar, novo_valor);

            s_adicionar += 1;
            if (s_adicionar >= s_limite) {
                s_adicionar = 0;
            }
            s_index += 1;
        }

        // INICIAR CRIPTOGRAFIA
        System.out.println("Matrix Re-Originum");
        System.out.println(LLCRIPT_RE_ORIGNUM.getView());


        byte[] bytes = mensagem.getBytes(StandardCharsets.UTF_8);


        System.out.println("Dados   : " + mensagem);
        System.out.println("Tamanho : " + bytes.length);


        System.out.println("INDO :: " + bytes_to_sequencia(bytes));

        ArrayList<Quadrante> quadrantes = bytes_to_quadrantes(bytes);

        listar_quadrantes(quadrantes);

        System.out.println("");

        System.out.println("-->> APLICAR METODO");
        ArrayList<Quadrante> criptados = new ArrayList<Quadrante>();

        Quadrante quadro_anterior = LLCRIPT_RE_ORIGNUM;

        int qi = 0;
        for (Quadrante quadro : quadrantes) {

            Quadrante montagem = Quadrante.somar(quadro, LLCRIPT_RE_ORIGNUM);


            int ponto_central_normalizado = normalizar_byte(quadro_anterior.calcularPontoCentral());
            int torque_normalizado = normalizar_byte(quadro_anterior.calcularTorque());

            for (int pi = 0; pi < LLCRIPT_TAMANHO_BLOCO; pi++) {
                montagem.setDireto(pi, normalizar_byte(montagem.getDireto(pi) + quadro_anterior.getDireto(pi) + ponto_central_normalizado - torque_normalizado));
            }

            System.out.println("QUADRO : " + qi + " -->> PONTO CENTRAL = " + ponto_central_normalizado + " TORQUE = " + torque_normalizado);
            System.out.println(quadrante_trinca(quadro, LLCRIPT_RE_ORIGNUM, montagem));

            criptados.add(montagem);

            quadro_anterior = montagem;

            qi += 1;
        }


        // DESCRIPTOGRAFAR

        System.out.println("-->> VOLTAR");

        int tamanho_voltar = criptados.size() * LLCRIPT_TAMANHO_BLOCO;

        System.out.println("Tamanho : " + tamanho_voltar);

        int valores[] = new int[tamanho_voltar + 4];

        int indice_total = 0;

        byte numero[] = intToByte(bytes.length);

        valores[0] = numero[0];
        valores[1] = numero[1];
        valores[2] = numero[2];
        valores[3] = numero[3];


        indice_total = 4;
        for (Quadrante quadro : criptados) {

            for (int qv = 0; qv < LLCRIPT_TAMANHO_BLOCO; qv++) {
                int valor = quadro.getDireto(qv);

                valores[indice_total] = valor;

                indice_total += 1;
            }


        }


        byte obter_tamanho[] = new byte[4];

        obter_tamanho[0] = (byte) valores[0];
        obter_tamanho[1] = (byte) valores[1];
        obter_tamanho[2] = (byte) valores[2];
        obter_tamanho[3] = (byte) valores[3];

        int tamanho_obtido = bytestoInt(obter_tamanho);

        byte valores_bytes[] = new byte[tamanho_obtido];

        indice_total = 4;
        int montando_texto = 0;

        while (montando_texto < tamanho_obtido) {
            valores_bytes[montando_texto] = (byte) valores[indice_total];
            montando_texto += 1;
            indice_total += 1;
        }

        System.out.println("");

        ArrayList<Quadrante> voltar_quadrantes = bytes_to_quadrantes(valores_bytes);

        listar_quadrantes(voltar_quadrantes);

        System.out.println("");

        System.out.println("-->> DESAPLICAR METODO");
        ArrayList<Quadrante> descriptados = new ArrayList<Quadrante>();

        qi = 0;

        Quadrante descriptografar_quadro_anterior = LLCRIPT_RE_ORIGNUM;

        for (Quadrante quadro : voltar_quadrantes) {


            int ponto_central_normalizado = normalizar_byte(descriptografar_quadro_anterior.calcularPontoCentral());
            int torque_normalizado = normalizar_byte(descriptografar_quadro_anterior.calcularTorque());


            Quadrante montagem = Quadrante.subtrair(quadro, LLCRIPT_RE_ORIGNUM);

            for (int pi = 0; pi < LLCRIPT_TAMANHO_BLOCO; pi++) {
                montagem.setDireto(pi, normalizar_byte(montagem.getDireto(pi) - descriptografar_quadro_anterior.getDireto(pi) - ponto_central_normalizado + torque_normalizado));
            }

            // System.out.println("QUADRO : " + qi);


            System.out.println("QUADRO : " + qi + " -->> PONTO CENTRAL = " + ponto_central_normalizado + " TORQUE = " + torque_normalizado);
            System.out.println(quadrante_trinca(quadro, LLCRIPT_RE_ORIGNUM, montagem));


            descriptados.add(montagem);

            descriptografar_quadro_anterior = quadro;

            qi += 1;
        }


        byte[] bytes_do_texto = quadrantes_to_bytes(descriptados, tamanho_obtido);

        System.out.println("VOLTOU Tamanho :: " + tamanho_obtido);
        System.out.println("VOLTOU Tamanho buf :: " + bytes_do_texto.length);

        System.out.println("VOLTOU :: " + ints_to_sequencia(valores));

        System.out.println("TRADUÇÃO :: " + new String(bytes_do_texto));
    }

    public static void listar_quadrantes(ArrayList<Quadrante> quadrantes) {

        int qi = 0;
        for (Quadrante quadro : quadrantes) {

            System.out.println("QUADRANTE : " + qi);
            System.out.println(quadro.getView());

            qi += 1;
        }


    }

    public static ArrayList<Quadrante> bytes_to_quadrantes(byte bytes[]) {

        ArrayList<Quadrante> quadrantes = new ArrayList<Quadrante>();

        int i = 0;
        int o = bytes.length;

        Quadrante quadranteCorrente = new Quadrante();
        quadrantes.add(quadranteCorrente);

        int e = 0;


        while (i < o) {

            int valor = Byte.toUnsignedInt(bytes[i]);

            if (e >= quadranteCorrente.getTamanho()) {
                quadranteCorrente = new Quadrante();
                quadrantes.add(quadranteCorrente);
                e = 0;
            }

            quadranteCorrente.setDireto(e, valor);

            e += 1;
            i += 1;
        }

        return quadrantes;
    }

    public static String quadrante_trinca(Quadrante q1, Quadrante q2, Quadrante q3) {

        int LADO = 4;

        String ret = "";
        for (int linha = 0; linha < LADO; linha++) {

            // MATRIZ 1
            for (int coluna = 0; coluna < LADO; coluna++) {

                String valorcorrente = String.valueOf(q1.getDireto((coluna * LADO) + linha));
                if (valorcorrente.length() == 1) {
                    valorcorrente = "00" + valorcorrente;
                } else if (valorcorrente.length() == 2) {
                    valorcorrente = "0" + valorcorrente;
                }
                ret += valorcorrente + " ";
            }

            ret += "\t\t";

            // MATRIZ 2
            for (int coluna = 0; coluna < LADO; coluna++) {

                String valorcorrente = String.valueOf(q2.getDireto((coluna * LADO) + linha));
                if (valorcorrente.length() == 1) {
                    valorcorrente = "00" + valorcorrente;
                } else if (valorcorrente.length() == 2) {
                    valorcorrente = "0" + valorcorrente;
                }
                ret += valorcorrente + " ";
            }

            ret += "\t\t ->> \t";

            // MATRIZ 3
            for (int coluna = 0; coluna < LADO; coluna++) {

                String valorcorrente = String.valueOf(q3.getDireto((coluna * LADO) + linha));
                if (valorcorrente.length() == 1) {
                    valorcorrente = "00" + valorcorrente;
                } else if (valorcorrente.length() == 2) {
                    valorcorrente = "0" + valorcorrente;
                }
                ret += valorcorrente + " ";
            }

            ret += "\n";
        }

        return ret;
    }


    public static byte[] quadrantes_to_bytes(ArrayList<Quadrante> quadrantes, int limite) {


        byte valores[] = new byte[limite];

        int indice_total = 0;

        for (Quadrante quadro : quadrantes) {

            for (int qv = 0; qv < 16; qv++) {
                int valor = quadro.getDireto(qv);

                valores[indice_total] = (byte) valor;
                indice_total += 1;

                if (indice_total >= limite) {
                    break;
                }
            }


        }

        return valores;
    }


    public static byte[] criptografar(String mensagem, String senha) {

        int LLCRIPT_TAMANHO_BLOCO = 16;

        Quadrante LLCRIPT_ORIGNUM = new Quadrante();
        LLCRIPT_ORIGNUM.definirLinha(0, 1, 2, 3, 4);
        LLCRIPT_ORIGNUM.definirLinha(1, 5, 6, 7, 8);
        LLCRIPT_ORIGNUM.definirLinha(2, 9, 10, 11, 12);
        LLCRIPT_ORIGNUM.definirLinha(3, 13, 14, 15, 16);

        // System.out.println("Matrix Originum");
        //System.out.println(LLCRIPT_ORIGNUM.getView());


        Quadrante LLCRIPT_RE_ORIGNUM = new Quadrante();
        LLCRIPT_RE_ORIGNUM.copiarDe(LLCRIPT_ORIGNUM);

        // ORGANIZAR SENHA
        byte[] senha_bytes = senha.getBytes(StandardCharsets.UTF_8);

        int s_index = 0;
        int s_tamanho = senha_bytes.length;
        int s_adicionar = 0;
        int s_limite = LLCRIPT_TAMANHO_BLOCO;

        while (s_index < s_tamanho) {

            int novo_valor = LLCRIPT_RE_ORIGNUM.getDireto(s_adicionar) + Byte.toUnsignedInt(senha_bytes[s_index]);

            novo_valor = normalizar_byte(novo_valor);

            LLCRIPT_RE_ORIGNUM.setDireto(s_adicionar, novo_valor);

            s_adicionar += 1;
            if (s_adicionar >= s_limite) {
                s_adicionar = 0;
            }
            s_index += 1;
        }

        // INICIAR CRIPTOGRAFIA
        // System.out.println("Matrix Re-Originum");
        // System.out.println(LLCRIPT_RE_ORIGNUM.getView());


        byte[] bytes = mensagem.getBytes(StandardCharsets.UTF_8);


        //System.out.println("Dados   : " + mensagem);
        // System.out.println("Tamanho : " + bytes.length);
        // System.out.println("INDO :: " + bytes_to_sequencia(bytes));

        ArrayList<Quadrante> quadrantes = bytes_to_quadrantes(bytes);

        //   listar_quadrantes(quadrantes);

        //System.out.println("");

        //System.out.println("-->> APLICAR METODO");
        ArrayList<Quadrante> criptados = new ArrayList<Quadrante>();

        Quadrante quadro_anterior = LLCRIPT_RE_ORIGNUM;

        int qi = 0;
        for (Quadrante quadro : quadrantes) {

            Quadrante montagem = Quadrante.somar(quadro, LLCRIPT_RE_ORIGNUM);


            int ponto_central_normalizado = quadro_anterior.calcularPontoCentral(); //normalizar_byte(quadro_anterior.calcularPontoCentral());
            int torque_normalizado = quadro_anterior.calcularTorque(); //normalizar_byte(quadro_anterior.calcularTorque());

            for (int pi = 0; pi < LLCRIPT_TAMANHO_BLOCO; pi++) {
                montagem.setDireto(pi, normalizar_byte(montagem.getDireto(pi) + quadro_anterior.getDireto(pi) + ponto_central_normalizado - torque_normalizado));
            }

            //System.out.println("QUADRO : " + qi + " -->> PONTO CENTRAL = " + ponto_central_normalizado + " TORQUE = " + torque_normalizado);
            // System.out.println(quadrante_trinca(quadro, LLCRIPT_RE_ORIGNUM, montagem));

            criptados.add(montagem);

            quadro_anterior = montagem;

            qi += 1;
        }

        // System.out.println("-->> VOLTAR");

        int tamanho_voltar = criptados.size() * LLCRIPT_TAMANHO_BLOCO;

        //System.out.println("Tamanho : " + tamanho_voltar);

        byte valores[] = new byte[tamanho_voltar + 4];

        int indice_total = 0;

        byte numero[] = intToByte(bytes.length);

        valores[0] = numero[0];
        valores[1] = numero[1];
        valores[2] = numero[2];
        valores[3] = numero[3];


        indice_total = 4;
        for (Quadrante quadro : criptados) {

            for (int qv = 0; qv < LLCRIPT_TAMANHO_BLOCO; qv++) {
                int valor = quadro.getDireto(qv);

                valores[indice_total] = (byte) valor;

                indice_total += 1;
            }


        }

        return valores;
    }

    public static String descriptografar(byte[] cifra, String senha) {

        int LLCRIPT_TAMANHO_BLOCO = 16;

        Quadrante LLCRIPT_ORIGNUM = new Quadrante();
        LLCRIPT_ORIGNUM.definirLinha(0, 1, 2, 3, 4);
        LLCRIPT_ORIGNUM.definirLinha(1, 5, 6, 7, 8);
        LLCRIPT_ORIGNUM.definirLinha(2, 9, 10, 11, 12);
        LLCRIPT_ORIGNUM.definirLinha(3, 13, 14, 15, 16);

        // System.out.println("Matrix Originum");
        //System.out.println(LLCRIPT_ORIGNUM.getView());


        Quadrante LLCRIPT_RE_ORIGNUM = new Quadrante();
        LLCRIPT_RE_ORIGNUM.copiarDe(LLCRIPT_ORIGNUM);

        // ORGANIZAR SENHA
        byte[] senha_bytes = senha.getBytes(StandardCharsets.UTF_8);

        int s_index = 0;
        int s_tamanho = senha_bytes.length;
        int s_adicionar = 0;
        int s_limite = LLCRIPT_TAMANHO_BLOCO;

        while (s_index < s_tamanho) {

            int novo_valor = LLCRIPT_RE_ORIGNUM.getDireto(s_adicionar) + Byte.toUnsignedInt(senha_bytes[s_index]);

            novo_valor = normalizar_byte(novo_valor);

            LLCRIPT_RE_ORIGNUM.setDireto(s_adicionar, novo_valor);

            s_adicionar += 1;
            if (s_adicionar >= s_limite) {
                s_adicionar = 0;
            }
            s_index += 1;
        }

        // INICIAR CRIPTOGRAFIA
        // System.out.println("Matrix Re-Originum");
        // System.out.println(LLCRIPT_RE_ORIGNUM.getView());


        byte obter_tamanho[] = new byte[4];

        obter_tamanho[0] = (byte) cifra[0];
        obter_tamanho[1] = (byte) cifra[1];
        obter_tamanho[2] = (byte) cifra[2];
        obter_tamanho[3] = (byte) cifra[3];

        int tamanho_obtido = bytestoInt(obter_tamanho);

        byte valores_bytes[] = new byte[tamanho_obtido];

        int indice_total = 4;
        int montando_texto = 0;

        while (montando_texto < tamanho_obtido) {
            valores_bytes[montando_texto] = (byte) cifra[indice_total];
            montando_texto += 1;
            indice_total += 1;
        }

        //System.out.println("");

        ArrayList<Quadrante> voltar_quadrantes = bytes_to_quadrantes(valores_bytes);

        //listar_quadrantes(voltar_quadrantes);

        // System.out.println("");

        //System.out.println("-->> DESAPLICAR METODO");
        ArrayList<Quadrante> descriptados = new ArrayList<Quadrante>();

        int qi = 0;

        Quadrante descriptografar_quadro_anterior = LLCRIPT_RE_ORIGNUM;

        for (Quadrante quadro : voltar_quadrantes) {


            int ponto_central_normalizado = normalizar_byte(descriptografar_quadro_anterior.calcularPontoCentral());
            int torque_normalizado = normalizar_byte(descriptografar_quadro_anterior.calcularTorque());


            Quadrante montagem = Quadrante.subtrair(quadro, LLCRIPT_RE_ORIGNUM);

            for (int pi = 0; pi < LLCRIPT_TAMANHO_BLOCO; pi++) {
                montagem.setDireto(pi, normalizar_byte(montagem.getDireto(pi) - descriptografar_quadro_anterior.getDireto(pi) - ponto_central_normalizado + torque_normalizado));
            }

            // System.out.println("QUADRO : " + qi);


            //System.out.println("QUADRO : " + qi + " -->> PONTO CENTRAL = " + ponto_central_normalizado + " TORQUE = " + torque_normalizado);
            //System.out.println(quadrante_trinca(quadro, LLCRIPT_RE_ORIGNUM, montagem));


            descriptados.add(montagem);

            descriptografar_quadro_anterior = quadro;

            qi += 1;
        }


        byte[] bytes_do_texto = quadrantes_to_bytes(descriptados, tamanho_obtido);

        // System.out.println("VOLTOU Tamanho :: " + tamanho_obtido);
        // System.out.println("VOLTOU Tamanho buf :: " + bytes_do_texto.length);
        //  System.out.println("VOLTOU :: " + bytes_to_sequencia(cifra));
        //  System.out.println("TRADUÇÃO :: " + new String(bytes_do_texto));

        return new String(bytes_do_texto);
    }

}
