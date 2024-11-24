package libs.arquivos;

import libs.arquivos.binario.Arquivador;
import libs.arquivos.binario.Inteiro;
import libs.arquivos.ds.DS;
import libs.arquivos.ds.DSItem;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Strings;
import libs.luan.fmt;

import java.awt.image.BufferedImage;
import java.nio.charset.StandardCharsets;

public class DSInterno {

    // AUTOR    : LUAN FREITAS
    // CRIACAO  : 2024 10 20
    // OBJETIVO : ACESSAR ITENS RAPIDAMENTE DENTRO DO ARQUIVO DS

    public static QTT ABRIR_QTT(DSItem item) {


        QTT eQTT = new QTT();

        //System.out.println("QTT - ObterTudo");

        Arquivador arquivador = new Arquivador(item.getArquivo(), "r");
        arquivador.setPonteiro(item.getInicio());

        byte p1 = arquivador.get();
        byte p2 = arquivador.get();
        byte p3 = arquivador.get();
        byte p4 = arquivador.get();
        byte p5 = arquivador.get();

        byte z1 = arquivador.get();

        int largura = arquivador.get_u32();
        int altura = arquivador.get_u32();

        byte z2 = arquivador.get();


        eQTT.setLargura(largura);
        eQTT.setAltura(altura);

        eQTT.criarBuffer();

        // System.out.println("\t - Largura :: " + largura);
        // System.out.println("\t - Altura  :: " + altura);

        long ePonteiroInicio = arquivador.getPonteiro();

        // System.out.println("\t - Inicio  :: " + ePonteiroInicio);

        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {

                int apontar = ((y * largura) + x) * 4;

                arquivador.setPonteiro(ePonteiroInicio + apontar);
                int valor = arquivador.get_u32();


                eQTT.setValor(x, y, valor);

                //  System.out.println("\t - " + (ePonteiroInicio + apontar) + " :: (" + x + "," + y + ") -->> " + valor);

            }
        }


        arquivador.encerrar();

        //  System.out.println("QTT - Obtido");

        return eQTT;

    }

    public static int QTT_OBTER_LARGURA(DSItem item) {

        Arquivador arquivador = new Arquivador(item.getArquivo(), "r");
        arquivador.setPonteiro(item.getInicio());

        byte p1 = arquivador.get();
        byte p2 = arquivador.get();
        byte p3 = arquivador.get();
        byte p4 = arquivador.get();
        byte p5 = arquivador.get();

        byte z1 = arquivador.get();

        int largura = arquivador.get_u32();
        int altura = arquivador.get_u32();

        byte z2 = arquivador.get();

        arquivador.encerrar();

        return largura;
    }

    public static int QTT_OBTER_ALTURA(DSItem item) {

        Arquivador arquivador = new Arquivador(item.getArquivo(), "r");
        arquivador.setPonteiro(item.getInicio());

        byte p1 = arquivador.get();
        byte p2 = arquivador.get();
        byte p3 = arquivador.get();
        byte p4 = arquivador.get();
        byte p5 = arquivador.get();

        byte z1 = arquivador.get();

        int largura = arquivador.get_u32();
        int altura = arquivador.get_u32();

        byte z2 = arquivador.get();

        arquivador.encerrar();

        return altura;
    }

    public static int QTT_GET(DSItem item, int x, int y) {

        int valor = 0;

        Arquivador arquivador = new Arquivador(item.getArquivo(), "r");
        arquivador.setPonteiro(item.getInicio());

        byte p1 = arquivador.get();
        byte p2 = arquivador.get();
        byte p3 = arquivador.get();
        byte p4 = arquivador.get();
        byte p5 = arquivador.get();

        byte z1 = arquivador.get();

        int largura = arquivador.get_u32();
        int altura = arquivador.get_u32();

        byte z2 = arquivador.get();

        long ePonteiroInicio = arquivador.getPonteiro();

        if (x >= 0 && y >= 0 && x < largura && y < altura) {

            int apontar = ((y * largura) + x) * 4;

            arquivador.setPonteiro(ePonteiroInicio + apontar);
            valor = arquivador.get_u32();

            //  System.out.println("x : " + x + " y : " + y + " -->> pt(" + arquivador.getPonteiro() + ") = " + valor);
        }

        arquivador.encerrar();

        return valor;
    }

    public static Entidade PARSER_ENTIDADE(DSItem item) {
        return ENTT.PARSER_ENTIDADE(item.getTexto());
    }

    public static Lista<Entidade> PARSER_ENTIDADES(DSItem item) {
        return ENTT.PARSER(item.getTexto());
    }

    public static Lista<Entidade> PARSER_ENTIDADES_PRE_ALOCADO(DSItem item) {
        return ENTT.PARSER(item.getTextoPreAlocado());
    }

    private static Lista<DSItem> DS_OBTER_ITENS_ARQUIVO_ABERTO(Arquivador arquivar ,DSItem item) {

        Lista<DSItem> itens = new Lista<DSItem>();


        // System.out.println("Tamanho :: " + arquivar.getLength());

        arquivar.setPonteiro(item.getInicio());

        int b1 = Inteiro.byteToInt(arquivar.get());
        int b2 = Inteiro.byteToInt(arquivar.get());

        //  System.out.println("B1 :: " + b1);
        // System.out.println("B2 :: " + b2);

        int status = Inteiro.byteToInt(arquivar.get());

        long t = item.getFim();

        //   fmt.print(">> Iniciando em ptr : {}",arquivar.getPonteiro());
        //   fmt.print(">> Finalizar em ptr : {}",item.getFim());
        //   fmt.print(">> Tamanho em ptr : {}",arquivar.getLength());

        TX eTX = new TX();

        int index = 0;

        while ((status == 255 || status == 111) && arquivar.getPonteiro() < t) {

            long item_tamanho = arquivar.get_u64();


            long comecar_nome = arquivar.getPonteiro();

            String nome = eTX.lerFluxoLimitado(arquivar, 1024);

            arquivar.setPonteiro(comecar_nome + 1024);

            long antes = arquivar.getPonteiro();

            itens.adicionar(new DSItem(item.getArquivo(), index, status, nome, item_tamanho, antes));

            arquivar.setPonteiro(arquivar.getPonteiro() + item_tamanho);
            status = Inteiro.byteToInt(arquivar.get());

            index += 1;

        }

        //    fmt.print(">> Parando em ptr : {}",arquivar.getPonteiro());


        return itens;
    }

    public static Lista<DSItem> DS_OBTER_ITENS(DSItem item) {

        Lista<DSItem> itens = new Lista<DSItem>();

        Arquivador arquivar = new Arquivador(item.getArquivo());

        // System.out.println("Tamanho :: " + arquivar.getLength());

        arquivar.setPonteiro(item.getInicio());

        int b1 = Inteiro.byteToInt(arquivar.get());
        int b2 = Inteiro.byteToInt(arquivar.get());

        //  System.out.println("B1 :: " + b1);
        // System.out.println("B2 :: " + b2);

        int status = Inteiro.byteToInt(arquivar.get());

        long t = item.getFim();

        int index = 0;

        while ((status == 255 || status == 111) && arquivar.getPonteiro() < t) {

            long item_tamanho = arquivar.get_u64();


            long comecar_nome = arquivar.getPonteiro();

            TX eTX = new TX();
            String nome = eTX.lerFluxoLimitado(arquivar, 1024);

            arquivar.setPonteiro(comecar_nome + 1024);

            long antes = arquivar.getPonteiro();

            itens.adicionar(new DSItem(item.getArquivo(), index, status, nome, item_tamanho, antes));

            arquivar.setPonteiro(arquivar.getPonteiro() + item_tamanho);
            status = Inteiro.byteToInt(arquivar.get());

            index += 1;

        }

        arquivar.encerrar();

        return itens;
    }

    public static void DS_DUMP_TABELA(DSItem item_ds) {


        Lista<DSItem> itens = DS_OBTER_ITENS(item_ds);

        int tamanho_nome = 0;

        for (DSItem item : itens) {
            if (item.getNome().length() > tamanho_nome) {
                tamanho_nome = item.getNome().length();
            }
        }

        tamanho_nome+=15;

        String cabecalho_fmt = "| {dir" + tamanho_nome + "} | {dir10} | {dir10} | {dir18} |";
        String linha_fmt = "| {dir" + tamanho_nome + "} | {dir10} | {dir10} | {esq18} |";

        fmt.print("{}",fmt.repetir("-",tamanho_nome+35+16));
        fmt.print(cabecalho_fmt,"Nome","Início","Fim","Tamanho");
        fmt.print("{}",fmt.repetir("-",tamanho_nome+35+16));

        for (DSItem item : itens) {
            fmt.print(linha_fmt, item.getNome(), item.getInicio(), item.getInicio() + item.getTamanho(), fmt.formatar_tamanho_precisao_dupla(item.getTamanho()));
        }

        fmt.print("{}",fmt.repetir("-",tamanho_nome+35+16));


    }

    public static Lista<Entidade> ENTT_ABRIR(DSItem item_ds) {

        Lista<Entidade> lista = new Lista<Entidade>();

        Arquivador arquivar = new Arquivador(item_ds.getArquivo());

        for (DSItem item : DS_OBTER_ITENS_ARQUIVO_ABERTO(arquivar,item_ds)) {
            lista.adicionar(ENTT.PARSER_ENTIDADE(DS_ITEM_INTERNO_GET_TEXTO(arquivar,item)));
        }

        arquivar.encerrar();

        return lista;
    }

    private static String DS_ITEM_INTERNO_GET_TEXTO( Arquivador arquivar,DSItem item) {
        return new String(DS_ITEM_INTERNO_GET_BYTES(arquivar,item), StandardCharsets.UTF_8);
    }

    private static byte[] DS_ITEM_INTERNO_GET_BYTES(Arquivador arquivar,DSItem item) {

        arquivar.setPonteiro(item.getInicio());

        byte[] dados = new byte[(int) item.getTamanho()];

        for (int b = 0; b < item.getTamanho(); b++) {
            dados[b] = arquivar.get();
        }


        return dados;
    }

    public static BufferedImage IM_VISUALIZAR(DSItem item_ds){

        Arquivador arq = new Arquivador(item_ds.getArquivo());
        arq.setPonteiro(item_ds.getInicio());
        BufferedImage img = IM.lerDoFluxo(arq);
        arq.encerrar();

        return img;
    }

    public static Lista<Entidade> CRIAR_INDICE_ULTRA_RAPIDO(String arquivo,String att_nome){

        Lista<Entidade> indice_ultra_rapido = new Lista<Entidade>();

        for(DSItem item : DS.ler_todos(arquivo)){
            Entidade e_item = ENTT.CRIAR_EM(indice_ultra_rapido);

            e_item.at(att_nome,"");
            e_item.at("PTR_INICIO",item.getInicio());
            e_item.at("PTR_FIM",item.getFim());
            e_item.at("PTR_TAMANHO",item.getTamanho());

            Entidade ents = ENTT.PARSER_ENTIDADE(item.getTexto());

            e_item.at(att_nome,ents.at(att_nome));


        }

        return indice_ultra_rapido;
    }

    public static Entidade GET_ENTIDADE_INDEXADA_ULTRA_RAPIDO(DSItem todos_os_dados,Entidade idx_item ){

        long ponteiro_inicio = todos_os_dados.getInicio() + idx_item.atLong("PTR_INICIO");
        long ponteiro_fim = todos_os_dados.getInicio() + idx_item.atLong("PTR_FIM");
        long tamanho = idx_item.atLong("PTR_TAMANHO");

        Arquivador arquivar = new Arquivador(todos_os_dados.getArquivo());
        arquivar.setPonteiro(ponteiro_inicio);

        byte[] dados = new byte[(int)tamanho];

        for (int b = 0; b < dados.length; b++) {
            dados[b] = arquivar.get();
        }

        arquivar.encerrar();


        // fmt.print("{}", );
        return ENTT.PARSER_ENTIDADE(Strings.GET_STRING_VIEW(dados));
    }

}
