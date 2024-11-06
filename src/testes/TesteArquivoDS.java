package testes;

import apps.app_attuz.Sociedade.PessoaNomeadorDeAkkax;
import libs.arquivos.DM;
import libs.arquivos.DSInterno;
import libs.arquivos.IM;
import libs.arquivos.binario.Arquivador;
import libs.arquivos.binario.ByteChunker;
import libs.arquivos.ds.DS;
import libs.arquivos.ds.DSIndexador;
import libs.arquivos.ds.DSItem;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.fs.PastaFS;
import libs.imagem.Imagem;
import libs.luan.*;
import libs.tronarko.Hazde;
import libs.tronarko.Tronarko;

import java.awt.image.BufferedImage;

public class TesteArquivoDS {


    public static void init() {

        PastaFS pasta_de_assets = new PastaFS("/home/luan/assets");
        PastaFS pasta_de_testes = new PastaFS("/home/luan/assets/testes");

        String arquivo_ds = pasta_de_testes.getArquivo("Teste.ds");

        DS.limpar(arquivo_ds);

        DS.adicionar_pre_alocado(arquivo_ds, "@Init.pre", Matematica.KB(500));

        for (int a = 1; a <= 10; a++) {
            DS.adicionar(arquivo_ds, "A" + a + ".txt", "Valor de " + a);
        }

        ByteChunker imagem_bytes = IM.salvar_to_chunks(Imagem.getImagem("/home/luan/Imagens/atzum/build/planeta/atzum_relevo_terra.png"));
        fmt.print("Bytes : {}", imagem_bytes.getTamanho());

        DS.adicionar(arquivo_ds, "atzum_relevo.im", imagem_bytes);

        DS.adicionar_arquivo(arquivo_ds, "luan.entts", pasta_de_assets.getArquivo("luan.entts"));
        DS.adicionar_arquivo(arquivo_ds, "atzum_planeta.qtt", "/home/luan/Imagens/atzum/dados/planeta.qtt");
        DS.adicionar_arquivo(arquivo_ds, "atzum_relevo.qtt", "/home/luan/Imagens/atzum/dados/relevo.qtt");
        DS.adicionar_arquivo(arquivo_ds, "atzum_regioes.qtt", "/home/luan/Imagens/atzum/dados/regioes.qtt");
        DS.adicionar_arquivo(arquivo_ds, "atzum_subregioes.qtt", "/home/luan/Imagens/atzum/dados/subregioes.qtt");
        DS.adicionar_arquivo(arquivo_ds, "atzum_cidades.entts", "/home/luan/Imagens/atzum/parametros/CIDADES_NOMES.entts");


        Lista<Entidade> pessoas = ENTT.CRIAR_LISTA();

        for (int p = 1; p <= 10; p++) {

            Entidade e_pessoa = ENTT.CRIAR_EM(pessoas);
            e_pessoa.at("SEQ", p);
            e_pessoa.at("RGID", Aleatorio.aleatorio_dos_numero(4) + "." + Aleatorio.aleatorio_dos_numero(4) + "." + Aleatorio.aleatorio_dos_numero(4) + "-" + Aleatorio.aleatorio_dos_numero(2));
            e_pessoa.at("Nome", Strings.CAPTALIZAR(PessoaNomeadorDeAkkax.getSimples()));
            e_pessoa.at("DDN", fmt.zerado(Aleatorio.aleatorio_entre(1, 50), 2) + "/" + fmt.zerado(Aleatorio.aleatorio_entre(1, 10), 2) + "/" + Aleatorio.aleatorio_entre(6930, 7004));
            e_pessoa.at("TipoSanguineo", Aleatorio.escolha_um(Lista.CRIAR("A", "B", "AB", "O")) + Aleatorio.aleatorio_escolha("+", "-"));
            e_pessoa.at("Peso", Aleatorio.aleatorio_entre(50, 100) + " Kg");
            e_pessoa.at("Altura", "1." + Aleatorio.aleatorio_entre(10, 99) + " m");
            e_pessoa.at("CorDosOlhos", Aleatorio.escolha_um(Lista.CRIAR("Castanho", "Azul", "Verde")));
            e_pessoa.at("CorDaPele", Aleatorio.escolha_um(Lista.CRIAR("Branco", "Pardo", "Moreno", "Negro")));
            e_pessoa.at("CorDoCabelo", Aleatorio.escolha_um(Lista.CRIAR("Preto", "Castanho Escuro", "Castanho Claro", "Loiro", "Ruivo")));
            e_pessoa.at("Sexo", Aleatorio.escolha_um(Lista.CRIAR("Masculino", "Feminino")));
            e_pessoa.at("TomDeVoz", Aleatorio.escolha_um(Lista.CRIAR("Rouca", "Suave", "Baixa", "Alta", "Grosseira", "Tranquila")));


        }

        DS.adicionar(arquivo_ds, "pessoas.entts", ENTT.TO_DOCUMENTO(pessoas));


        Opcional<DSItem> op_init = DS.buscar_item(arquivo_ds, "@Init.pre");

        if (Opcional.IS_OK(op_init)) {

            DSIndexador.INDEX(arquivo_ds, "@Init.pre");

            Lista<DSItem> indice = DSIndexador.GET_INDEX(arquivo_ds, "@Init.pre");

            DS.DUMP_ITENS(indice);

            fmt.print("Indice Tamanho >> {}", fmt.formatar_tamanho_precisao_dupla(op_init.get().getTamanhoUtilizadoPreAlocado()));

        }


        DS.DUMP_TABELA(arquivo_ds);

    }

    public static void ver() {

        PastaFS pasta_de_testes = new PastaFS("/home/luan/assets/testes");

        String arquivo_ds = pasta_de_testes.getArquivo("Teste.ds");

        Opcional<DSItem> op_init = DS.buscar_item(arquivo_ds, "@Init.pre");

        if (Opcional.IS_OK(op_init)) {


            Lista<DSItem> indice = DSIndexador.GET_INDEX(arquivo_ds, "@Init.pre");

            DS.DUMP_ITENS(indice);

            fmt.print("Indice Tamanho Completo    >> {}", fmt.formatar_tamanho_precisao_dupla(op_init.get().getTamanho()));
            fmt.print("Indice Tamanho Utilizado   >> {}", fmt.formatar_tamanho_precisao_dupla(op_init.get().getTamanhoUtilizadoPreAlocado()));


            Opcional<DSItem> op_a7 = DSIndexador.GET_ITEM(indice, "A7.txt");
            if (Opcional.IS_OK(op_a7)) {
                fmt.print(">> {}", op_a7.get().getNome());
                fmt.print("\t++ Conteudo = {}", op_a7.get().getTexto());
            }

            //  QTT planeta_real = QTT.getTudo("/home/luan/Imagens/atzum/dados/planeta.qtt");
            //   fmt.print("\t ++ Largura        = {}", planeta_real.getLargura());
            //    fmt.print("\t ++ Altura         = {}", planeta_real.getAltura());
            //    fmt.print("\t ++ P(500,500)     = {}", planeta_real.getValor(500, 500));
            //    fmt.print("\t ++ P(1000,800)    = {}", planeta_real.getValor(1000, 800));


            Opcional<DSItem> op_planeta = DSIndexador.GET_ITEM(indice, "atzum_planeta.qtt");
            if (Opcional.IS_OK(op_planeta)) {
                fmt.print(">> {}", op_planeta.get().getNome());
                fmt.print("\t ++ Tamanho        = {}", fmt.formatar_tamanho_precisao_dupla(op_planeta.get().getTamanho()));

                //   QTT planeta = DSInterno.ABRIR_QTT(op_planeta.get());
                fmt.print("\t ++ Largura        = {}", DSInterno.QTT_OBTER_LARGURA(op_planeta.get()));
                fmt.print("\t ++ Altura         = {}", DSInterno.QTT_OBTER_ALTURA(op_planeta.get()));
                fmt.print("\t ++ P(500,500)     = {}", DSInterno.QTT_GET(op_planeta.get(), 500, 500));
                fmt.print("\t ++ P(1000,800)    = {}", DSInterno.QTT_GET(op_planeta.get(), 1000, 800));

            }

            Opcional<DSItem> op_pessoas = DSIndexador.GET_ITEM(indice, "pessoas.entts");
            if (Opcional.IS_OK(op_pessoas)) {
                fmt.print(">> {}", op_pessoas.get().getNome());
                fmt.print("\t ++ Tamanho        = {}", fmt.formatar_tamanho_precisao_dupla(op_pessoas.get().getTamanho()));
                fmt.print("\t ++ Conteudo       ");

                ENTT.EXIBIR_TABELA(DSInterno.PARSER_ENTIDADES(op_pessoas.get()));
            }


            Opcional<DSItem> op_luan = DSIndexador.GET_ITEM(indice, "luan.entts");
            if (Opcional.IS_OK(op_luan)) {
                fmt.print(">> {}", op_luan.get().getNome());
                fmt.print("\t ++ Tamanho        = {}", fmt.formatar_tamanho_precisao_dupla(op_luan.get().getTamanho()));
                fmt.print("\t ++ Conteudo       ");

                //  ENTT.EXIBIR_TABELA(DSInterno.PARSER_ENTIDADES(op_luan.get()));
                //  DSInterno.DS_DUMP_TABELA(op_luan.get());
                ENTT.EXIBIR_TABELA(DSInterno.ENTT_ABRIR(op_luan.get()));

            }

            Opcional<DSItem> op_atzum_cidades = DSIndexador.GET_ITEM(indice, "atzum_cidades.entts");
            if (Opcional.IS_OK(op_atzum_cidades)) {
                fmt.print(">> {}", op_atzum_cidades.get().getNome());
                fmt.print("\t ++ Tamanho        = {}", fmt.formatar_tamanho_precisao_dupla(op_atzum_cidades.get().getTamanho()));
                fmt.print("\t ++ Conteudo       ");

                //  ENTT.EXIBIR_TABELA(DSInterno.PARSER_ENTIDADES(op_atzum_cidades.get()));
                //  DSInterno.DS_DUMP_TABELA(op_atzum_cidades.get());
                ENTT.EXIBIR_TABELA(DSInterno.ENTT_ABRIR(op_atzum_cidades.get()));

            }

            Opcional<DSItem> op_atzum_relevo = DSIndexador.GET_ITEM(indice, "atzum_relevo.im");
            if (Opcional.IS_OK(op_atzum_relevo)) {
                fmt.print(">> {}", op_atzum_relevo.get().getNome());
                fmt.print("\t ++ Tamanho        = {}", fmt.formatar_tamanho_precisao_dupla(op_atzum_relevo.get().getTamanho()));

                BufferedImage img = DSInterno.IM_VISUALIZAR(op_atzum_relevo.get());
                fmt.print("\t ++ Largura        = {}", img.getWidth());
                fmt.print("\t ++ Altura         = {}", img.getHeight());

                Imagem.exportar(img, pasta_de_testes.getArquivo("Oi.png"));

            }

        }


    }

    public static void comparar_im() {


        PastaFS pasta_de_testes = new PastaFS("/home/luan/assets/testes");
        String arquivo_atzum = pasta_de_testes.getArquivo("teste_im.ds");

        String arquivo_imagem = "/home/luan/Imagens/atzum/build/planeta/atzum_relevo_terra.png";

        DS.limpar(arquivo_atzum);

        Hazde t1 = Tronarko.getHazde();
        DS.adicionar(arquivo_atzum, "a.im", IM.salvar_to_bytes(Imagem.getImagem(arquivo_imagem)));
        Hazde t2 = Tronarko.getHazde();

        Hazde t3 = Tronarko.getHazde();
        DS.adicionar(arquivo_atzum, "b.im", IM.salvar_to_chunks(Imagem.getImagem(arquivo_imagem)));
        Hazde t4 = Tronarko.getHazde();

        fmt.print("D1 : {}", Tronarko.HAZDE_DIFERENCA(t2, t1));
        fmt.print("D2 : {}", Tronarko.HAZDE_DIFERENCA(t4, t3));

        DS.DUMP_TABELA(arquivo_atzum);

        for (DSItem item : DS.ler_todos(arquivo_atzum)) {
            Arquivador arq = new Arquivador(item.getArquivo());
            arq.setPonteiro(item.getInicio());
            BufferedImage ima = IM.lerDoFluxo(arq);
            arq.encerrar();

            Imagem.exportar(ima, pasta_de_testes.getArquivo(item.getNome()).replace(".im", ".png"));
        }

    }


    public static void alterar_ultimo() {


        // ARQUIVO DM - Documento Monitorado

        PastaFS pasta_de_testes = new PastaFS("/home/luan/assets/testes");

        Lista<String> adicoes = Lista.CRIAR("Alfa", "Beta", "Gama", "Delta");

        String arquivo_ds = pasta_de_testes.getArquivo("ultimo.dm");

        Opcional<DSItem> op_status = DS.buscar_item(arquivo_ds, "@Status.entt");
        Opcional<DSItem> op_historico = DS.buscar_item(arquivo_ds, "@Historico.entts");

        if (Opcional.IS_OK(op_status)) {
            //  fmt.print("Status ::{}",Strings.LINEARIZAR(op_status.get().getTextoPreAlocado()));

            Entidade e_status = ENTT.PARSER_ENTIDADE(op_status.get().getTextoPreAlocado());

            fmt.print("\t ++ Criado     :: {}", e_status.at("Criado"));
            fmt.print("\t ++ Inserido   :: {}", e_status.at("Inserido"));
            fmt.print("\t ++ Atualizado :: {}", e_status.at("Atualizado"));
            fmt.print("\t ++ Corrente   :: {}", e_status.at("Corrente"));


        } else {
            DS.adicionar_pre_alocado(arquivo_ds, "@Status.entt", Matematica.KB(10));

            Entidade e_status = new Entidade();
            e_status.at("Criado", Tronarko.getTronAgora().getTextoZerado());
            e_status.at("Atualizado", Tronarko.getTronAgora().getTextoZerado());
            e_status.at("Inserido", Tronarko.getTronAgora().getTextoZerado());
            e_status.at("Corrente", "");

            DS.alterar_pre_alocado(arquivo_ds, "@Status.entt", ENTT.TO_DOCUMENTO(e_status));
        }

        if (Opcional.IS_OK(op_historico)) {

        } else {
            DS.adicionar_pre_alocado(arquivo_ds, "@Historico.entts", Matematica.MB(1));
            DS.alterar_pre_alocado(arquivo_ds, "@Historico.entts", ENTT.TO_DOCUMENTO(ENTT.CRIAR_LISTA()));
        }


        DS.DUMP_TABELA(arquivo_ds);


        Opcional<DSItem> ultimo_item = Opcional.CANCEL();
        Lista<DSItem> historico = new Lista<DSItem>();

        for (DSItem item : DS.ler_todos(arquivo_ds)) {
            if (!item.isNome("@Status.entt") && !item.isNome("@Historico.entts")) {
                ultimo_item.set(item);
                historico.adicionar(item);
            }
        }

        if (Opcional.IS_OK(ultimo_item)) {
            fmt.print(">> Ultimo : {}", ultimo_item.get().getNome());

            String ultimo_valor = ultimo_item.get().getTexto();
            int ultimo_i = Integer.parseInt(ultimo_valor);

            fmt.print(">> Ultimo Valor = {}", ultimo_i);

            ultimo_i += 1;
            if (ultimo_i <= 10) {

                DS.substituir_ultimo(arquivo_ds, ultimo_item.get().getNome(), String.valueOf(ultimo_i));

                Entidade e_status = ENTT.PARSER_ENTIDADE(op_status.get().getTextoPreAlocado());
                e_status.at("Atualizado", Tronarko.getTronAgora().getTextoZerado());
                DS.alterar_pre_alocado(arquivo_ds, "@Status.entt", ENTT.TO_DOCUMENTO(e_status));

            } else {

                int proximo = 0;
                Opcional<String> proximo_nome = Opcional.CANCEL();

                for (String item_nome : adicoes) {
                    if (item_nome.contentEquals(ultimo_item.get().getNome())) {

                        if (proximo + 1 < adicoes.getQuantidade()) {
                            proximo_nome.set(adicoes.get(proximo + 1));
                        }

                        break;
                    }
                    proximo += 1;
                }


                if (proximo_nome.isOK()) {
                    DS.adicionar(arquivo_ds, proximo_nome.get(), "0");


                    Entidade e_status = ENTT.PARSER_ENTIDADE(op_status.get().getTextoPreAlocado());
                    e_status.at("Inserido", Tronarko.getTronAgora().getTextoZerado());
                    e_status.at("Corrente", proximo_nome.get());

                    DS.alterar_pre_alocado(arquivo_ds, "@Status.entt", ENTT.TO_DOCUMENTO(e_status));

                }
            }

        } else {
            DS.adicionar(arquivo_ds, adicoes.get(0), "0");

            Entidade e_status = new Entidade();
            e_status.at("Criado", Tronarko.getTronAgora().getTextoZerado());
            e_status.at("Inserido", Tronarko.getTronAgora().getTextoZerado());
            e_status.at("Corrente", adicoes.get(0));

            DS.alterar_pre_alocado(arquivo_ds, "@Status.entt", ENTT.TO_DOCUMENTO(e_status));

        }


        historico.limpar();

        for (DSItem item : DS.ler_todos(arquivo_ds)) {
            if (!item.isNome("@Status.entt") && !item.isNome("@Historico.entts")) {
                historico.adicionar(item);
            }
        }

        DS.DUMP_ITENS_CONTEUDO(historico);

    }


    public static void alterar_ultimo_dm() {

        PastaFS pasta_de_testes = new PastaFS("/home/luan/assets/testes");

        Lista<String> adicoes = Lista.CRIAR("Alfa", "Beta", "Gama", "Delta");

        String arquivo_dm = pasta_de_testes.getArquivo("ultimo.dm");

       // DM.LIMPAR(arquivo_dm);

        Opcional<DSItem> ultimo_item = DM.GET_ULTIMO(arquivo_dm);

        if (Opcional.IS_OK(ultimo_item)) {
            fmt.print(">> Ultimo : {} ->> {}", ultimo_item.get().getNome(), ultimo_item.get().getTexto());

            int ultimo_i = Integer.parseInt(ultimo_item.get().getTexto());

            ultimo_i += 1;
            if (ultimo_i <= 10) {
                fmt.print(">> Atualizar Ultimo : {} ->> {}", ultimo_item.get().getNome(), ultimo_item.get().getTexto());

                DM.ATUALIZAR(arquivo_dm, ultimo_item.get().getNome(), String.valueOf(ultimo_i));
            } else {

                Opcional<String> proximo_nome = Lista.GET_DEPOIS_DE(adicoes, Strings.IGUALDADE(), ultimo_item.get().getNome());

                if (proximo_nome.isOK()) {
                    fmt.print(">> Novo Ultimo : {} ->> {}", proximo_nome.get(), 0);

                    DM.ATUALIZAR(arquivo_dm, proximo_nome.get(), "0");
                }

            }

        } else {
            fmt.print(">> Primeiro : {} ->> {}", Lista.PRIMEIRO(adicoes), 0);

            DM.ATUALIZAR(arquivo_dm, Lista.PRIMEIRO(adicoes), "0");
        }


        DM.DUMP_CONTEUDO(arquivo_dm);
        DM.DUMP_HISTORICO(arquivo_dm);

        DS.DUMP_TABELA(arquivo_dm);

    }

}
