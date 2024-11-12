package libs.aqz.colecao;

import libs.aqz.utils.ItemDoBancoTX;
import libs.aqz.utils.ItemDoBancoUTF8;
import libs.armazenador.Armazenador;
import libs.armazenador.ParticaoMestre;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.tempo.Calendario;

public class AZInternamenteUTF8Visualizadores {

    private Armazenador mArmazenador;

    public final String AQZ_INIT = "@Init";

    public final String COLECOES_DADOS = "@ColecoesUTF8::Dados";
    public final String COLECOES_SEQUENCIAS = "@ColecoesUTF8::Sequencias";
    public final String COLECOES_VISUALIZADORES = "@ColecoesUTF8::Visualizadores";

    public AZInternamenteUTF8Visualizadores(String arquivo_banco) {

        Armazenador.CHECAR(arquivo_banco);
        mArmazenador = new Armazenador(arquivo_banco);


    }

    public AZInternamenteUTF8Visualizadores(Armazenador eArmazenador) {
        mArmazenador = eArmazenador;
    }

    public void fechar() {
        mArmazenador.fechar();
    }


    public void views_criar(String view_nome, String colecao_nome, Lista<String> colunas) {

        ParticaoMestre s_inits = mArmazenador.getParticaoMestre(  AQZ_INIT);
        ParticaoMestre s_views =mArmazenador.getParticaoMestre(  COLECOES_VISUALIZADORES);

        if (!views_existe(view_nome)) {


            Entidade init_views = new Entidade();
            ItemDoBancoUTF8 ref_init_views = null;
            boolean init_views_existe = false;

            for (ItemDoBancoUTF8 item : s_inits.getItensUTF8()) {
                Entidade item_dkg = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());
                if (item_dkg.at("Nome").toUpperCase().contentEquals("VIEWS")) {
                    ref_init_views = item;
                    init_views = item_dkg;
                    init_views_existe = true;
                    break;
                }
            }


            if (!init_views_existe) {
                Entidade nova_init = new Entidade();
                nova_init.at("Nome", "VIEWS");
                nova_init.at("Corrente", 0);
                nova_init.at("Sequencia", 1);
                nova_init.at("DDC", Calendario.getTempoCompleto());
                nova_init.at("DDA", Calendario.getTempoCompleto());
                s_inits.adicionarTX(nova_init);

                for (ItemDoBancoUTF8 item : s_inits.getItensUTF8()) {
                    Entidade item_dkg = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());
                    if (item_dkg.at("Nome").toUpperCase().contentEquals("VIEWS")) {
                        ref_init_views = item;
                        init_views = item_dkg;
                        init_views_existe = true;
                        break;
                    }
                }

                if (!init_views_existe) {
                    mArmazenador.fechar();
                    throw new RuntimeException("AQZ ERRO - Init nao encontrada : VIEWS");
                }

            }

            int view_id = init_views.atInt("Corrente", 0);

            init_views.at("Corrente", init_views.atInt("Corrente", 0) + init_views.at("Sequencia", 0));
            ref_init_views.atualizarUTF8(init_views);

            Entidade obj_view = new Entidade();
            obj_view.at("ID", view_id);
            obj_view.at("Nome", view_nome.toUpperCase());
            obj_view.at("NomeOriginal", view_nome);
            obj_view.at("Banco", colecao_nome.toUpperCase());

            for (String coluna : colunas) {
                Entidade obj_view_coluna = ENTT.CRIAR_EM(obj_view.getEntidades());
                obj_view_coluna.at("Nome", coluna);
            }


            s_views.adicionarTX(obj_view);
        }

    }

    public Lista<String> views_listar() {

        Lista<String> lista = new Lista<String>();

        ParticaoMestre s_inits = mArmazenador.getParticaoMestre( AQZ_INIT);
        ParticaoMestre s_views =mArmazenador.getParticaoMestre( COLECOES_VISUALIZADORES);

        for (ItemDoBancoUTF8 item : s_views.getItensUTF8()) {
            Entidade obj = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());
            lista.adicionar(obj.at("Nome").toUpperCase());
        }

        return lista;
    }

    public boolean views_existe(String view_nome) {
        view_nome = view_nome.toUpperCase();

        ParticaoMestre s_inits = mArmazenador.getParticaoMestre( AQZ_INIT);
        ParticaoMestre s_views =mArmazenador.getParticaoMestre( COLECOES_VISUALIZADORES);

        for (ItemDoBancoUTF8 item : s_views.getItensUTF8()) {
            Entidade obj = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());
            if (obj.at("Nome").toUpperCase().contentEquals(view_nome)) {
                return true;
            }
        }

        return false;
    }

    public Entidade views_obter(String view_nome) {
        view_nome = view_nome.toUpperCase();

        ParticaoMestre s_inits = mArmazenador.getParticaoMestre( AQZ_INIT);
        ParticaoMestre s_views =mArmazenador.getParticaoMestre( COLECOES_VISUALIZADORES);

        for (ItemDoBancoUTF8 item : s_views.getItensUTF8()) {
            Entidade obj = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());
            if (obj.at("Nome").toUpperCase().contentEquals(view_nome)) {
                return obj;
            }
        }

        return null;
    }

    public void views_remover(String view_nome) {
        view_nome = view_nome.toUpperCase();

        ParticaoMestre s_inits = mArmazenador.getParticaoMestre( AQZ_INIT);
        ParticaoMestre s_views =mArmazenador.getParticaoMestre( COLECOES_VISUALIZADORES);

        for (ItemDoBancoUTF8 item : s_views.getItensUTF8()) {
            Entidade obj = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());
            if (obj.at("Nome").toUpperCase().contentEquals(view_nome)) {
                s_views.remover(item);
                break;
            }
        }

    }


    public Lista<ColecaoUTF8> colecoes_listar() {


        Lista<ColecaoUTF8> colecoes = new Lista<ColecaoUTF8>();


        ParticaoMestre s_inits = mArmazenador.getParticaoMestre(AQZ_INIT);
        ParticaoMestre s_bancos = mArmazenador.getParticaoMestre(COLECOES_DADOS);
        ParticaoMestre s_sequencias = mArmazenador.getParticaoMestre(COLECOES_SEQUENCIAS);


        for (ItemDoBancoTX item : s_bancos.getItensTX()) {
            Entidade obj_colecao = ENTT.PARSER_ENTIDADE(item.lerTextoTX());

            //  fmt.print("AQZ STATUS :: {}",ENTT.TO_DOCUMENTO(obj_colecao));

            if (obj_colecao.is("Status", "OK")) {

                String nome = obj_colecao.at("Nome");

                int banco_id = obj_colecao.atInt("PID");

                long ponteiro_inicial_do_banco = obj_colecao.atLong("Ponteiro");

                long local_itens = obj_colecao.atLong("Capitulos");
                long local_cache = obj_colecao.atLong("Cache");
                long local_corrente = obj_colecao.atLong("PPPC");
                long local_indice = obj_colecao.atLong("Indice");

                ParticaoMestre particaoPrimaria = new ParticaoMestre(nome, mArmazenador, mArmazenador.getArquivador(), banco_id, ponteiro_inicial_do_banco, local_itens, local_cache, local_corrente, local_indice);
                ColecaoUTF8 colecao = new ColecaoUTF8(particaoPrimaria.getNome(), mArmazenador, s_sequencias, particaoPrimaria);
                colecoes.adicionar(colecao);


            }


            //    fmt.print("{}", obj_colecao.toDocumento());
        }


        return colecoes;

    }


    public ColecaoUTF8 colecoes_obter(String colecao_nome) {

        colecao_nome = colecao_nome.toUpperCase();

        for (ColecaoUTF8 b : colecoes_listar()) {
            if (b.getNome().contentEquals(colecao_nome)) {

                // fmt.print("AQZ STATUS BANCO - {}",colecao_nome);
                return b;
            }
        }

        return null;
    }

}
