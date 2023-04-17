package libs.arquivos.stacker;

import libs.arquivos.TX;
import libs.arquivos.binario.Arquivador;
import libs.arquivos.binario.Inteiro;
import libs.luan.Opcional;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Stacker {

    // AUTOR : LUAN FREITAS
    // DATA  : 2023 04 17

    public static void limpar(String eArquivo) {
        Arquivador.remover(eArquivo);
    }

    public static void adicionar(String eArquivo, String eNome, byte[] eDados) {


        Arquivador arquivar = new Arquivador(eArquivo);

        long t = arquivar.getLength();

        if (t == 0) {
            arquivar.setPonteiro(0);
            arquivar.set_u8((byte) 100);
            arquivar.set_u8((byte) 120);
        }

        arquivar.setPonteiro(arquivar.getLength());


        // adicionar novo item
        arquivar.set_u8((byte) 255);
        arquivar.set_u64((long) eDados.length);

        long antes = arquivar.getPonteiro();

        for (int z = 0; z < 1024; z++) {
            arquivar.set_u8((byte) 0);
        }

        long depois = arquivar.getPonteiro();

        arquivar.setPonteiro(antes);

        ArrayList<Byte> bytes_nome = TX.toListBytes(eNome);

        if (bytes_nome.size() < 1024) {

            for (byte b : bytes_nome) {
                arquivar.set_u8(b);
            }

        }


        arquivar.setPonteiro(depois);

        int i = 0;
        int o = eDados.length;

        while (i < o) {
            arquivar.set_u8(eDados[i]);
            i += 1;
        }


        arquivar.encerrar();

    }

    public static void adicionar(String eArquivo, String eNome, String dados) {
        adicionar(eArquivo, eNome, dados.getBytes(StandardCharsets.UTF_8));
    }

    public static void adicionar_pre_alocado(String eArquivo, String eNome, int pre_alocado) {


        Arquivador arquivar = new Arquivador(eArquivo);

        long t = arquivar.getLength();

        if (t == 0) {
            arquivar.setPonteiro(0);
            arquivar.set_u8((byte) 100);
            arquivar.set_u8((byte) 120);
        }

        arquivar.setPonteiro(arquivar.getLength());


        // adicionar novo item
        arquivar.set_u8((byte) 255);
        arquivar.set_u64((long) pre_alocado);

        long antes = arquivar.getPonteiro();

        for (int z = 0; z < 1024; z++) {
            arquivar.set_u8((byte) 0);
        }

        long depois = arquivar.getPonteiro();

        arquivar.setPonteiro(antes);

        ArrayList<Byte> bytes_nome = TX.toListBytes(eNome);

        if (bytes_nome.size() < 1024) {

            for (byte b : bytes_nome) {
                arquivar.set_u8(b);
            }

        }


        arquivar.setPonteiro(depois);

        int i = 0;
        int o = pre_alocado;

        while (i < o) {
            arquivar.set_u8((byte) 0);
            i += 1;
        }


        arquivar.encerrar();

    }


    public static ArrayList<StackItem> ler_todos(String eArquivo) {

        ArrayList<StackItem> itens = new ArrayList<StackItem>();

        Arquivador arquivar = new Arquivador(eArquivo);

        //  System.out.println("Tamanho :: " + arquivar.getLength());

        arquivar.setPonteiro(0);

        int b1 = Inteiro.byteToInt(arquivar.get());
        int b2 = Inteiro.byteToInt(arquivar.get());

        //  System.out.println("B1 :: " + b1);
        //   System.out.println("B2 :: " + b2);

        int status = Inteiro.byteToInt(arquivar.get());

        long t = arquivar.getLength();

        int index = 0;

        while (status == 255 && arquivar.getPonteiro() < t) {

            long item_tamanho = arquivar.get_u64();


            long comecar_nome = arquivar.getPonteiro();

            TX eTX = new TX();
            String nome = eTX.lerFluxoLimitado(arquivar, 1024);

            arquivar.setPonteiro(comecar_nome + 1024);

            long antes = arquivar.getPonteiro();

            itens.add(new StackItem(eArquivo, index, status, nome, item_tamanho, antes));

            arquivar.setPonteiro(arquivar.getPonteiro() + item_tamanho);
            status = Inteiro.byteToInt(arquivar.get());

            index += 1;

        }

        arquivar.encerrar();

        return itens;
    }




    public static Opcional<StackItem> buscar_item(String eArquivo, String eNome) {

        Opcional<StackItem> ret = new Opcional<StackItem>();


        Arquivador arquivar = new Arquivador(eArquivo);

        //  System.out.println("Tamanho :: " + arquivar.getLength());

        arquivar.setPonteiro(0);

        int b1 = Inteiro.byteToInt(arquivar.get());
        int b2 = Inteiro.byteToInt(arquivar.get());

        //  System.out.println("B1 :: " + b1);
        //   System.out.println("B2 :: " + b2);

        int status = Inteiro.byteToInt(arquivar.get());

        long t = arquivar.getLength();

        int index = 0;

        while (status == 255 && arquivar.getPonteiro() < t) {

            long item_tamanho = arquivar.get_u64();


            long comecar_nome = arquivar.getPonteiro();

            TX eTX = new TX();
            String nome = eTX.lerFluxoLimitado(arquivar, 1024);

            arquivar.setPonteiro(comecar_nome + 1024);

            long antes = arquivar.getPonteiro();


            if (nome.contentEquals(eNome)) {
                ret.set(new StackItem(eArquivo, index, status, nome, item_tamanho, antes));
                break;
            }

            arquivar.setPonteiro(arquivar.getPonteiro() + item_tamanho);
            status = Inteiro.byteToInt(arquivar.get());

            index += 1;

        }

        arquivar.encerrar();

        return ret;
    }


    public static int contar(String eArquivo) {

        int contando = 0;


        Arquivador arquivar = new Arquivador(eArquivo);

        //  System.out.println("Tamanho :: " + arquivar.getLength());

        arquivar.setPonteiro(0);

        int b1 = Inteiro.byteToInt(arquivar.get());
        int b2 = Inteiro.byteToInt(arquivar.get());

        //  System.out.println("B1 :: " + b1);
        //   System.out.println("B2 :: " + b2);

        int status = Inteiro.byteToInt(arquivar.get());

        long t = arquivar.getLength();

        int index = 0;

        while (status == 255 && arquivar.getPonteiro() < t) {

            long item_tamanho = arquivar.get_u64();


            long comecar_nome = arquivar.getPonteiro();

            TX eTX = new TX();
            String nome = eTX.lerFluxoLimitado(arquivar, 1024);

            arquivar.setPonteiro(comecar_nome + 1024);

            long antes = arquivar.getPonteiro();

            contando += 1;

            arquivar.setPonteiro(arquivar.getPonteiro() + item_tamanho);
            status = Inteiro.byteToInt(arquivar.get());

            index += 1;

        }

        arquivar.encerrar();

        return contando;
    }


    public static Opcional<StackItem> ler_item(String eArquivo, int chave) {

        Opcional<StackItem> ret = new Opcional<StackItem>();

        ArrayList<StackItem> itens = Stacker.ler_todos(eArquivo);
        for (StackItem item : itens) {
            if (item.getIndex() == chave) {
                ret.set(item);
                break;
            }
        }

        return ret;
    }


    public static ArrayList<StackItem> separar(String prefixo, ArrayList<StackItem> itens) {

        ArrayList<StackItem> guardando = new ArrayList<StackItem>();

        for (StackItem item : itens) {
            if (item.getNome().startsWith(prefixo)) {
                guardando.add(item);
            }
        }

        return guardando;

    }


    public static void indexar(String eArquivoDados, String ePrefixo, String eArquivoIndex) {

        Arquivador.remover(eArquivoIndex);

        ArrayList<StackItem> guardando = new ArrayList<StackItem>();

        for (StackItem item : ler_todos(eArquivoDados)) {
            if (item.getNome().startsWith(ePrefixo)) {
                guardando.add(item);
            }
        }


        int maior = 0;

        for (StackItem item : guardando) {

            String sID = item.getNome().replace(ePrefixo, "");
            if (sID.length() > 0) {
                int iID = Integer.parseInt(sID);

                if (iID > maior) {
                    maior = iID;
                }

            }
        }


        Arquivador arquivar = new Arquivador(eArquivoIndex);

        arquivar.setPonteiro(0);
        arquivar.set_u8((byte) 52);
        arquivar.set_u8((byte) 53);

        long antes = arquivar.getPonteiro();


        for (int i = 0; i < maior + 1; i++) {
            arquivar.set_u64(0);
            arquivar.set_u64(0);
        }


        for (StackItem item : guardando) {

            String sID = item.getNome().replace(ePrefixo, "");

            if (sID.length() > 0) {
                int iID = Integer.parseInt(sID);

                arquivar.setPonteiro(antes + (long) (iID * (16L)));
                arquivar.set_u64(item.getInicio());
                arquivar.set_u64(item.getTamanho());

            }
        }


        arquivar.encerrar();


    }

}
