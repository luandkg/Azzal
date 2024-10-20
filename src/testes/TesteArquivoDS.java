package testes;

import apps.app_attuz.Sociedade.PessoaNomeadorDeAkkax;
import libs.arquivos.IM;
import libs.arquivos.ds.DS;
import libs.arquivos.ds.DSIndexador;
import libs.arquivos.ds.DSItem;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.fs.PastaFS;
import libs.imagem.Imagem;
import libs.luan.*;

import java.awt.image.BufferedImage;

public class TesteArquivoDS {


    public static void init() {

        PastaFS pasta_de_assets = new PastaFS("/home/luan/assets");
        PastaFS pasta_de_testes = new PastaFS("/home/luan/assets/testes");

        String arquivo_ds = pasta_de_testes.getArquivo("Teste.ds");

        DS.limpar(arquivo_ds);

        DS.adicionar_pre_alocado(arquivo_ds, "@Init.pre", 500 * 1024);

        for (int a = 1; a <= 10; a++) {
            DS.adicionar(arquivo_ds, "A" + a + ".txt", "Valor de " + a);
        }

        Lista<Byte> imagem_bytes = IM.salvar_to_bytes(Imagem.getImagem("/home/luan/Imagens/atzum/build/planeta/atzum_relevo_terra.png"));
        fmt.print("Bytes : {}", imagem_bytes.getQuantidade());

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
            e_pessoa.at("DDN", fmt.zerado(Aleatorio.alatorio_entre(1, 50),2) + "/" + fmt.zerado(Aleatorio.alatorio_entre(1, 10),2) + "/" + Aleatorio.alatorio_entre(6930, 7004));
            e_pessoa.at("TipoSanguineo",Aleatorio.escolha_um(Lista.CRIAR("A","B","AB","O")) + Aleatorio.aleatorio_escolha("+","-"));
            e_pessoa.at("Peso",Aleatorio.alatorio_entre(50,100) +" Kg");
            e_pessoa.at("Altura","1."+Aleatorio.alatorio_entre(10,99) +" m");
            e_pessoa.at("CorDosOlhos",Aleatorio.escolha_um(Lista.CRIAR("Castanho","Azul","Verde")));
            e_pessoa.at("CorDaPele",Aleatorio.escolha_um(Lista.CRIAR("Branco","Pardo","Moreno","Negro")));
            e_pessoa.at("CorDoCabelo",Aleatorio.escolha_um(Lista.CRIAR("Preto","Castanho Escuro","Castanho Claro","Loiro","Ruivo")));
            e_pessoa.at("Sexo",Aleatorio.escolha_um(Lista.CRIAR("Masculino","Feminino")));
            e_pessoa.at("TomDeVoz",Aleatorio.escolha_um(Lista.CRIAR("Rouca","Suave","Baixa","Alta","Grosseira","Tranquila")));


        }

        DS.adicionar(arquivo_ds, "pessoas.entts", ENTT.TO_DOCUMENTO(pessoas));


        Opcional<DSItem> op_init = DS.buscar_item(arquivo_ds, "@Init.pre");

        if (Opcional.IS_OK(op_init)) {

            DSIndexador.INDEX(arquivo_ds, "@Init.pre");

            Lista<Entidade> indice = DSIndexador.GET_INDEX(arquivo_ds, "@Init.pre");

            ENTT.EXIBIR_TABELA_COM_NOME(indice, "DSIndice");

            fmt.print("Indice Tamanho >> {}", fmt.formatar_tamanho_precisao_dupla(op_init.get().getTamanhoUtilizadoPreAlocado()));

        }


        DS.DUMP_TABELA(arquivo_ds);

    }

    public static void ver() {

        PastaFS pasta_de_testes = new PastaFS("/home/luan/assets/testes");

        String arquivo_ds = pasta_de_testes.getArquivo("Teste.ds");

        Opcional<DSItem> op_init = DS.buscar_item(arquivo_ds, "@Init.pre");

        if (Opcional.IS_OK(op_init)) {


            Lista<Entidade> indice = DSIndexador.GET_INDEX(arquivo_ds, "@Init.pre");

            ENTT.EXIBIR_TABELA_COM_NOME(indice, "DSIndice");

            fmt.print("Indice Tamanho Completo    >> {}", fmt.formatar_tamanho_precisao_dupla(op_init.get().getTamanho()));
            fmt.print("Indice Tamanho Utilizado   >> {}", fmt.formatar_tamanho_precisao_dupla(op_init.get().getTamanhoUtilizadoPreAlocado()));
            fmt.print("Indice Tamanho Utilizado   >> {}", fmt.formatar_tamanho_precisao_dupla(DS.obter_tamanho_usado_pre_alocado(arquivo_ds, op_init.get().getNome())));


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

}
