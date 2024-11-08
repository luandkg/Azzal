package libs.aqz;

import libs.aqz.colecao.Colecao;
import libs.aqz.colecao.Unicidade;
import libs.aqz.utils.OrquestradorBancario;
import libs.aqz.utils.Sequenciador;
import libs.armazenador.Armazenador;
import libs.armazenador.Banco;
import libs.aqz.utils.ItemDoBanco;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;

public class BancoBS {

    private Armazenador mArmazenador;
    private Banco s_sequencias;

    public BancoBS(String eArquivo) {
        mArmazenador = new Armazenador(eArquivo);
        s_sequencias = OrquestradorBancario.organizar_banco(mArmazenador, "@Sequencias");
    }

    public Colecao getColecao(String eNome) {
        return new Colecao(eNome, mArmazenador, s_sequencias, OrquestradorBancario.organizar_banco(mArmazenador, eNome));
    }

    public Unicidade getSettum(String eNome) {
        return new Unicidade(eNome, s_sequencias, OrquestradorBancario.organizar_banco(mArmazenador, eNome));
    }

    public long getSequenciaID(String eNome) {
        return Sequenciador.get_sequencial_contador(s_sequencias, eNome);
    }


    public void remover_colecao(String eNome) {
        mArmazenador.banco_remover(eNome);
    }

    public ItemDoBanco getItemDireto(long ePointeiro) {

        ItemDoBanco item = mArmazenador.getItemDireto(ePointeiro);
        if (item.existe()) {
            return item;
        } else {
            return null;
        }

    }


    public Armazenador getMomentum() {
        return mArmazenador;
    }

    public void fechar() {
        mArmazenador.fechar();
    }


    public static void checar(String eArquivo) {

        if (!Armazenador.existe(eArquivo)) {
            Armazenador.criar(eArquivo);
        }

    }


    public void auto_analisar() {


        Colecao colecao_analise = this.getColecao("@Analise");
        colecao_analise.limpar();
        colecao_analise.zerarSequencial();

        Lista<Entidade> objetos_analisados = new Lista<Entidade>();

        for (Banco b : getMomentum().getBancos()) {

            Entidade obj_analise = new Entidade();

            obj_analise.at("BID",b.getID());
            obj_analise.at("Nome",b.getNome());

            long itens_alocados = b.getItensAlocadosContagem();
            long itens_utilizados = b.getItensContagem();

            obj_analise.at("Itens_Disponiveis",itens_alocados);
            obj_analise.at("Itens_Utilizados",itens_utilizados);

            obj_analise.at("Disponibilidade",b.getDisponibilidade());
            obj_analise.at("Usabilidadade",b.getUsabilidade());

            obj_analise.at("Capitulos_Contagem",b.getCapitulosContagem());
            obj_analise.at("Paginas_Contagem",b.getPaginasContagem());

            obj_analise.at("Indice_Disponibilidade",b.getIndiceDisponibilidade());
            obj_analise.at("Indice_Usabilidade",b.getIndiceUsabilidade());
            obj_analise.at("Indice_Paginas",b.getIndicePaginasContagem());
            obj_analise.at("Indice_Contagem",b.getIndiceItensTotalContagem());
            obj_analise.at("Indice_Utilizados",b.getIndiceItensUtilizadoContagem());

            objetos_analisados.adicionar(obj_analise);

        }

        for (Entidade obj_analise : ENTT.ORDENAR_TEXTO(objetos_analisados, "Nome")) {
            colecao_analise.adicionar(obj_analise);
        }

        // colecao_analise.primeiro_campo("ID");
    }
}
