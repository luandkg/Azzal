package libs.aqz.extincao;

import libs.armazenador.Armazenador;
import libs.aqz.extincao.armazenador_antigo.ArmazenadorEmExtincao;
import libs.armazenador.ParticaoEmExtincao;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;

public class BancoBS {

    private ArmazenadorEmExtincao mArmazenador;
    private ParticaoEmExtincao s_sequencias;

    public BancoBS(String eArquivo) {
        mArmazenador = new ArmazenadorEmExtincao(eArquivo);
        s_sequencias = mArmazenador.getParticaoEmExtincao( "@Sequencias");
    }

    public ColecaoAntigamente getColecao(String eNome) {
        return new ColecaoAntigamente(eNome, mArmazenador, s_sequencias, mArmazenador.getParticaoEmExtincao(  eNome));
    }

    public UnicidadeAntigamente getSettum(String eNome) {
        return new UnicidadeAntigamente(eNome, s_sequencias, mArmazenador.getParticaoEmExtincao(  eNome));
    }

    public long getSequenciaID(String eNome) {
        return Sequenciador.get_sequencial_contador(s_sequencias, eNome);
    }


    public void remover_colecao(String eNome) {
        mArmazenador.banco_remover(eNome);
    }

    public ItemDoBancoEmExtincao getItemDireto(long ePointeiro) {

        ItemDoBancoEmExtincao item = mArmazenador.getItemDireto(ePointeiro);
        if (item.existe()) {
            return item;
        } else {
            return null;
        }

    }


    public ArmazenadorEmExtincao getMomentum() {
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


        ColecaoAntigamente colecao_Antigamente_analise = this.getColecao("@Analise");
        colecao_Antigamente_analise.limpar();
        colecao_Antigamente_analise.zerarSequencial();

        Lista<Entidade> objetos_analisados = new Lista<Entidade>();

        for (ParticaoEmExtincao b : getMomentum().getBancos()) {

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
            colecao_Antigamente_analise.adicionar(obj_analise);
        }

        // colecao_analise.primeiro_campo("ID");
    }
}
