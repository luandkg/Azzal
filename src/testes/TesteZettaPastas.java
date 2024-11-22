package testes;

import libs.arquivos.binario.Arquivador;
import libs.luan.FS;
import libs.luan.Opcional;
import libs.luan.Strings;
import libs.luan.fmt;
import libs.tronarko.Tronarko;
import libs.zettaquorum.ZettaArquivo;
import libs.zettaquorum.ZettaPasta;
import libs.zettaquorum.ZettaPastas;

public class TesteZettaPastas {

    public static void init_pastas() {

        String arquivo_zeta = "/home/luan/assets/teste_fazendas/zeta.az";

        //   Arquivador.remover(arquivo_zeta);

        ZettaPastas zeta = new ZettaPastas(arquivo_zeta);


        ZettaPasta togz = zeta.getPastaSempre("@Togz");

        String arquivo_nome = Tronarko.getTozte().getTextoInversoZerado_UnderLine() + ".txt";
        Opcional<ZettaArquivo> op_arquivo_togz = togz.procurar_arquivo(arquivo_nome);

        if (Opcional.IS_VAZIO(op_arquivo_togz)) {

            String logs_criando = "---------------------- LOGZ :: " + Tronarko.getTozte().getTextoZerado() + " ----------------------";
            logs_criando += "\n" + "++ INICIADO :: " + Tronarko.getTronAgora().getTextoZerado();

            togz.adicionar_arquivo_se_nao_existir(arquivo_nome, Strings.GET_STRING_VIEW_BYTES(logs_criando));

        } else {

            ZettaArquivo arquivo_togz=Opcional.SOME(op_arquivo_togz);
            arquivo_togz.expandir( Strings.GET_STRING_VIEW_BYTES("\n ++ STATUS :: "+Tronarko.getTronAgora().getTextoZerado()));

        }


        ZettaPasta textos_objetos = zeta.getPastaSempre("@TextoObjetos");

        for (int i = 100; i < 105; i++) {
            //  fmt.print("TextoObjeto :: {}",i);
            //  textos_objetos.adicionar_arquivo_se_nao_existir("@TextoObjetos/ID_"+ fmt.zerado(i,10)+ ".txt", Strings.GET_STRING_VIEW_BYTES("----------- OBJETO ZERADO ( "+ i + " )  :: " + Tronarko.getTronAgora().getTextoZerado() +" ----------"));
        }


        ZettaPasta tronarkum = zeta.getPastaSempre("@Tronarkum");


        // tronarkum.adicionar_arquivo_se_nao_existir("@Textos/TronarkoLogs.txt", Strings.GET_STRING_VIEW_BYTES("----------- Tronarko :: LOGS ----------"));
        //tronarkum.adicionar_arquivo_se_nao_existir("@Textos/Tronarko.txt", Strings.GET_STRING_VIEW_BYTES(Tronarko.getTronAgora().getTextoZerado()));

        //  fmt.print("OP : Adicionando imagem...");
        //  String imagem_grande = "/home/luan/Imagens/Homem-olhando-para-sua-primeira-arvore-Worldbuilding-1024x574.png";
        //  silos.adicionar_ou_atualizar("@Imagem/FundoAmarelo.png", Arquivador.GET_BYTES(imagem_grande));


        tronarkum.dump();
        tronarkum.dump_arquivos();

        for (ZettaArquivo arquivo : tronarkum.getArquivosAtualizaveis()) {

            if (arquivo.isNome("@Textos/TronarkoLogs.txt")) {

                // fmt.print(">> Editar :: {}", arquivo.getNome());

                for (int a = 0; a < 500; a++) {
                    //       arquivo.expandir(Strings.GET_STRING_VIEW_BYTES("\n++ Edição nova :: " + Tronarko.getTronAgora().getTextoZerado()));
                    //     fmt.print(">> Arquivo :: {}", arquivo.getNome());
                    //    silos.exibir_arquivo(arquivo);
                }

                break;
            }
        }

        fmt.print("OP : Editar arquivos...");

        for (ZettaArquivo arquivo : tronarkum.getArquivosAtualizaveis()) {

            //   fmt.print(">> Arquivo :: {}", arquivo.getNome());

            if (arquivo.isNome("@Textos/TronarkoLogs.txt")) {

                //  byte[] todos_bytes = arquivo.getBytes();

                fmt.print("---------------------------------------------");
                //      fmt.print("{}", Strings.GET_STRING_VIEW(todos_bytes));
                fmt.print("---------------------------------------------");

            }


        }

        //   tronarkum.dump_arquivos();


        Opcional<ZettaArquivo> op_fa = tronarkum.procurar_arquivo("1200px-Protein_structure_examples.png");

        if (op_fa.isOK()) {

            fmt.print(">> Arquivo :: {}", op_fa.get().getNome());

            // silos.exibir_arquivo(arquivo);

            Arquivador.CONSTRUIR_ARQUIVO("/home/luan/assets/tronarkum_arquivo_dentro.png", op_fa.get().getBytes());
        }


        fmt.print("OP :: Remover todos os arquivos !");
        // silos.limpar();

        for (String arquivo : FS.lista_de_arquivos_total("/home/luan/Imagens/Hummm")) {
            //  fmt.print("OP Adicionando : {}",arquivo);
            // tronarkum.adicionar_ou_atualizar(FS.GET_NOME_DO_ARQUIVO(arquivo), Arquivador.GET_BYTES(arquivo));
        }


        //   tronarkum.dump_arquivos();
        textos_objetos.dump_arquivos();

        Opcional<ZettaArquivo> existe_esse = textos_objetos.procurar_arquivo("p16k.txt");
        if (existe_esse.isOK()) {
            //   existe_esse.get().remover();
        }

        textos_objetos.adicionar_arquivo_se_nao_existir("p16k.txt", new byte[16 * 1024]);

        for (int v = 0; v <= 10; v++) {
            fmt.print("-------------------- PARTE {} ----------------------", v);
            Opcional<ZettaArquivo> existe_esse2 = textos_objetos.procurar_arquivo("p16k.txt");
            if (existe_esse2.isOK()) {
                existe_esse2.get().expandir(new byte[16 * 1024]);
            }

            Opcional<ZettaArquivo> existe_esse3 = textos_objetos.procurar_arquivo("p16k.txt");
            if (existe_esse3.isOK()) {
                fmt.print("ARQUIVO TAMANHO : {}", existe_esse3.get().getTamanhoFormatado());
            }
        }


        textos_objetos.dump_arquivos();
        togz.dump_arquivos();

        // zeta.dump();

         zeta.dump_pastas();

        // contagem = tronarkum.contagem();
        zeta.fechar();

        //   fmt.print("++ Contagem : {}", contagem);


    }

}
