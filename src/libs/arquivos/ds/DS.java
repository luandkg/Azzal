package libs.arquivos.ds;

import libs.arquivos.TX;
import libs.arquivos.binario.Arquivador;
import libs.arquivos.binario.Inteiro;
import libs.entt.ENTT;
import libs.luan.Lista;
import libs.luan.Opcional;
import libs.luan.fmt;

import java.nio.charset.StandardCharsets;

public class DS {

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

        Lista<Byte> bytes_nome = TX.toListBytes(eNome);

        if (bytes_nome.getQuantidade() < 1024) {

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

    public static void adicionar(String eArquivo, String eNome, Lista<Byte> eDados) {


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
        arquivar.set_u64((long) eDados.getQuantidade());

        long antes = arquivar.getPonteiro();

        for (int z = 0; z < 1024; z++) {
            arquivar.set_u8((byte) 0);
        }

        long depois = arquivar.getPonteiro();

        arquivar.setPonteiro(antes);

        Lista<Byte> bytes_nome = TX.toListBytes(eNome);

        if (bytes_nome.getQuantidade() < 1024) {

            for (byte b : bytes_nome) {
                arquivar.set_u8(b);
            }

        }


        arquivar.setPonteiro(depois);

        int i = 0;
        int o = eDados.getQuantidade();

        while (i < o) {
            arquivar.set_u8(eDados.get(i));
            i += 1;
        }


        arquivar.encerrar();

    }

    public static void adicionar(String eArquivo, String eNome, String dados) {
        adicionar(eArquivo, eNome, dados.getBytes(StandardCharsets.UTF_8));
    }

    public static void adicionar_pre_alocado(String eArquivo, String eNome, int pre_alocado) {

        pre_alocado=pre_alocado+9;

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

        Lista<Byte> bytes_nome = TX.toListBytes(eNome);

        if (bytes_nome.getQuantidade() < 1024) {

            for (byte b : bytes_nome) {
                arquivar.set_u8(b);
            }

        }


        arquivar.setPonteiro(depois);
        arquivar.set_u8((byte) 200);

        int i = 0;
        int o = pre_alocado-1;

        while (i < o) {
            arquivar.set_u8((byte) 0);
            i += 1;
        }


        arquivar.encerrar();

    }


    public static Lista<DSItem> ler_todos(String eArquivo) {

        Lista<DSItem> itens = new Lista<DSItem>();

        Arquivador arquivar = new Arquivador(eArquivo);

       // System.out.println("Tamanho :: " + arquivar.getLength());

        arquivar.setPonteiro(0);

        int b1 = Inteiro.byteToInt(arquivar.get());
        int b2 = Inteiro.byteToInt(arquivar.get());

      //  System.out.println("B1 :: " + b1);
       // System.out.println("B2 :: " + b2);

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

            itens.adicionar(new DSItem(eArquivo, index, status, nome, item_tamanho, antes));

            arquivar.setPonteiro(arquivar.getPonteiro() + item_tamanho);
            status = Inteiro.byteToInt(arquivar.get());

            index += 1;

        }

        arquivar.encerrar();

        return itens;
    }


    public static Opcional<DSItem> buscar_item(String eArquivo, String eNome) {

        Opcional<DSItem> ret = new Opcional<DSItem>();


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
                ret.set(new DSItem(eArquivo, index, status, nome, item_tamanho, antes));
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


    public static Opcional<DSItem> ler_item(String eArquivo, int chave) {

        Opcional<DSItem> ret = new Opcional<DSItem>();

        Lista<DSItem> itens = DS.ler_todos(eArquivo);
        for (DSItem item : itens) {
            if (item.getIndex() == chave) {
                ret.set(item);
                break;
            }
        }

        return ret;
    }


    public static Lista<DSItem> separar(String prefixo, Lista<DSItem> itens) {

        Lista<DSItem> guardando = new Lista<DSItem>();

        for (DSItem item : itens) {
            if (item.getNome().startsWith(prefixo)) {
                guardando.adicionar(item);
            }
        }

        return guardando;

    }


    public static void indexar(String eArquivoDados, String ePrefixo, String eArquivoIndex) {

        Arquivador.remover(eArquivoIndex);

        Lista<DSItem> guardando = new Lista<DSItem>();

        for (DSItem item : ler_todos(eArquivoDados)) {
            if (item.getNome().startsWith(ePrefixo)) {
                guardando.adicionar(item);
            }
        }


        int maior = 0;

        for (DSItem item : guardando) {

            String sID = item.getNome().replace(ePrefixo, "");
            if (!sID.isEmpty()) {
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


        for (DSItem item : guardando) {

            String sID = item.getNome().replace(ePrefixo, "");

            if (!sID.isEmpty()) {
                int iID = Integer.parseInt(sID);

                arquivar.setPonteiro(antes + (long) (iID * (16L)));
                arquivar.set_u64(item.getInicio());
                arquivar.set_u64(item.getTamanho());

            }
        }


        arquivar.encerrar();


    }


    public static void atualizar_pre_alocado(DSItem item_pre_alocado,Lista<Byte> bytes){

    //    fmt.print("Iniciar Atualizacao Pre Alloc :: "+bytes.getQuantidade());

        long tamanho =item_pre_alocado.getTamanho()-9;

        if(bytes.getQuantidade()<tamanho){
            long pt_inicio_dados = item_pre_alocado.getInicio();

            Arquivador aquivador = new Arquivador(item_pre_alocado.getArquivo());
            aquivador.setPonteiro(pt_inicio_dados);

            int valor = aquivador.get_u8();

//fmt.print("Atualizar com 200");

            if(valor==200){
                aquivador.set_u64(bytes.getQuantidade());

              //  fmt.print("Atualizar com sv :: "+bytes.getQuantidade());

                for(Byte b : bytes){
                    aquivador.set_u8(b);
                }
            }

            aquivador.encerrar();

            if(valor!=200) {
                throw new RuntimeException("Esse item não pôde ser atualizado porque não foi pré-alocado !");
            }

            }else{
                throw new RuntimeException("Tamanho superior ao item pre alocado !");
            }

    }

}
