package libs.aqz.tabela;

import libs.aqz.colecao.ColecaoUTF8;
import libs.aqz.utils.ItemDoBancoTX;
import libs.aqz.utils.ProcuradorTX;
import libs.armazenador.Armazenador;
import libs.armazenador.ParticaoMestre;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;

public class AZTabelas {

    private AZTabelasInternamente mAZTabelasInternamente;

    public AZTabelas(String eArquivo) {

        mAZTabelasInternamente = new AZTabelasInternamente(eArquivo);

    }


    public AQZTabela tabela_orgarnizar_e_obter(String nome_tabela) {
        ColecaoUTF8 tabela_dados = mAZTabelasInternamente.tabela_orgarnizar_e_obter(nome_tabela);
        return new AQZTabela(mAZTabelasInternamente.getArmazenador(), nome_tabela, mAZTabelasInternamente.esquemas_obter(nome_tabela), tabela_dados);
    }


    public void fechar() {
        mAZTabelasInternamente.fechar();
    }


    public static void EXIBIR_ESTRUTURA_INTERNA(String arquivo) {

        Armazenador mArmazenador = new Armazenador(arquivo);

        ParticaoMestre s_tabelas = mArmazenador.getParticaoMestre("@Tabelas");

        ParticaoMestre s_tabelas_dados = mArmazenador.getParticaoMestre("@TabelasDados");
        ParticaoMestre s_tabelas_esquemas = mArmazenador.getParticaoMestre("@TabelasEsquemas");

        ParticaoMestre s_sequencias_dados = mArmazenador.getParticaoMestre("@TabelasDados::Sequencias");
        ParticaoMestre s_sequencias_esquemas = mArmazenador.getParticaoMestre("@TabelasEsquemas::Sequencias");

        Lista<Entidade> tabelas_mestre = ProcuradorTX.listar_dados(s_tabelas);
        Lista<Entidade> tabelas_dados = ProcuradorTX.listar_dados(s_tabelas_dados);
        Lista<Entidade> tabelas_esquemas = ProcuradorTX.listar_dados(s_tabelas_esquemas);
        Lista<Entidade> sequencias_dados = ProcuradorTX.listar_dados(s_sequencias_dados);
        Lista<Entidade> sequencias_esquemas =ProcuradorTX.listar_dados(s_sequencias_esquemas);

        mArmazenador.fechar();

        ENTT.EXIBIR_TABELA_COM_TITULO(tabelas_mestre, "@TABELAS :: MESTRE");

        ENTT.EXIBIR_TABELA_COM_TITULO(tabelas_dados, "@TABELAS");
        ENTT.EXIBIR_TABELA_COM_TITULO(tabelas_esquemas, "@ESQUEMAS");

        ENTT.EXIBIR_TABELA_COM_TITULO(sequencias_dados, "@TABELAS :: SEQUENCIAS");
        ENTT.EXIBIR_TABELA_COM_TITULO(sequencias_esquemas, "@ESQUEMAS :: SEQUENCIAS");

    }


}
