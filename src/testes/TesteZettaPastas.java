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


        ZettaPastas zeta = new ZettaPastas(arquivo_zeta);
        ZettaPasta tronarkum = zeta.getPastaSempre("@Tronarkum");


        tronarkum.adicionar_arquivo_se_nao_existir("@Textos/TronarkoLogs.txt", Strings.GET_STRING_VIEW_BYTES("----------- Tronarko :: LOGS ----------"));
        tronarkum.adicionar_arquivo_se_nao_existir("@Textos/Tronarko.txt", Strings.GET_STRING_VIEW_BYTES(Tronarko.getTronAgora().getTextoZerado()));

        //  fmt.print("OP : Adicionando imagem...");
        //  String imagem_grande = "/home/luan/Imagens/Homem-olhando-para-sua-primeira-arvore-Worldbuilding-1024x574.png";
        //  silos.adicionar_ou_atualizar("@Imagem/FundoAmarelo.png", Arquivador.GET_BYTES(imagem_grande));


        tronarkum.dump();
        tronarkum.dump_arquivos();

        for (ZettaArquivo arquivo : tronarkum.getArquivosAtualizaveis()) {

            if (arquivo.isNome("@Textos/TronarkoLogs.txt")) {

                fmt.print(">> Editar :: {}", arquivo.getNome());

                for (int a = 0; a < 500; a++) {
                    arquivo.expandir(Strings.GET_STRING_VIEW_BYTES("\n++ Edição nova :: " + Tronarko.getTronAgora().getTextoZerado()));
                    fmt.print(">> Arquivo :: {}", arquivo.getNome());
                    //    silos.exibir_arquivo(arquivo);
                }

                break;
            }
        }

        fmt.print("OP : Editar arquivos...");

        for (ZettaArquivo arquivo : tronarkum.getArquivosAtualizaveis()) {

            fmt.print(">> Arquivo :: {}", arquivo.getNome());

            //   silos.exibir_arquivo(arquivo);

            if (arquivo.isNome("@Textos/TronarkoLogs.txt")) {

                byte[] todos_bytes = arquivo.getBytes();

                fmt.print("---------------------------------------------");
                fmt.print("{}", Strings.GET_STRING_VIEW(todos_bytes));
                fmt.print("---------------------------------------------");

            }


        }

        tronarkum.dump_arquivos();


        Opcional<ZettaArquivo> op_fa = tronarkum.procurar_arquivo("1200px-Protein_structure_examples.png");

        if (op_fa.isOK()) {

            fmt.print(">> Arquivo :: {}", op_fa.get().getNome());

            //    silos.exibir_arquivo(arquivo);

            Arquivador.CONSTRUIR_ARQUIVO("/home/luan/assets/tronarkum_arquivo_dentro.png", op_fa.get().getBytes());
        }


        fmt.print("OP :: Remover todos os arquivos !");
        // silos.limpar();

        for (String arquivo : FS.lista_de_arquivos_total("/home/luan/Imagens/Hummm")) {
           //  fmt.print("OP Adicionando : {}",arquivo);
           // tronarkum.adicionar_ou_atualizar(FS.GET_NOME_DO_ARQUIVO(arquivo), Arquivador.GET_BYTES(arquivo));
        }


        tronarkum.dump_arquivos();


        zeta.dump();
        // contagem = tronarkum.contagem();
        zeta.fechar();

        //   fmt.print("++ Contagem : {}", contagem);


    }

}
