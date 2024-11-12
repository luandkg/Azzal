package libs.aqz;

import libs.aqz.colecao.AZInternamenteTX;
import libs.aqz.colecao.AZInternamenteUTF8;
import libs.aqz.utils.ItemDoBancoTX;
import libs.aqz.utils.ProcuradorTX;
import libs.armazenador.Armazenador;
import libs.armazenador.ParticaoMestre;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;

public class AQZParticoes {

    public static void EXIBIR_PARTICOES(String arquivo_banco) {

        AZInternamenteUTF8 aqz = new AZInternamenteUTF8(arquivo_banco);

        Lista<Entidade> objetos = new Lista<Entidade>();

        for (ParticaoMestre particaoPrimaria : aqz.particoes_primarias()) {

            Entidade e = new Entidade();
            e.at("ID",particaoPrimaria.getID());
            e.at("Nome",particaoPrimaria.getNome());
            e.at("Itens",particaoPrimaria.getItensContagem());
            e.at("Alocados",particaoPrimaria.getItensAlocadosContagem());

            objetos.adicionar(e);



        }

        ENTT.EXIBIR_TABELA_COM_TITULO(objetos, "AQZParticoes :: PRIMÁRIAS");

    }

    public static void EXIBIR_ESTRUTURA_INTERNA(String arquivo) {

        Armazenador mArmazenador = new Armazenador(arquivo);

        ParticaoMestre s_inits = mArmazenador.getParticaoMestre(AZInternamenteTX.AQZ_INIT);

        ParticaoMestre s_tabelas = mArmazenador.getParticaoMestre("@Tabelas");

        ParticaoMestre s_tabelas_dados = mArmazenador.getParticaoMestre("@TabelasDados");
        ParticaoMestre s_tabelas_esquemas = mArmazenador.getParticaoMestre("@TabelasEsquemas");

        ParticaoMestre s_sequencias_dados = mArmazenador.getParticaoMestre("@TabelasDados::Sequencias");
        ParticaoMestre s_sequencias_esquemas = mArmazenador.getParticaoMestre("@TabelasEsquemas::Sequencias");


        Lista<Entidade> inits = ProcuradorTX.listar_dados(s_inits);

        Lista<Entidade> tabelas_mestre = ProcuradorTX.listar_dados(s_tabelas);
        Lista<Entidade> tabelas_dados = ProcuradorTX.listar_dados(s_tabelas_dados);
        Lista<Entidade> tabelas_esquemas = ProcuradorTX.listar_dados(s_tabelas_esquemas);
        Lista<Entidade> sequencias_dados = ProcuradorTX.listar_dados(s_sequencias_dados);
        Lista<Entidade> sequencias_esquemas =ProcuradorTX.listar_dados(s_sequencias_esquemas);

        mArmazenador.fechar();

        ENTT.EXIBIR_TABELA_COM_TITULO(inits, "@AQZ :: INIT");

        ENTT.EXIBIR_TABELA_COM_TITULO(tabelas_mestre, "@TABELAS :: MESTRE");

        ENTT.EXIBIR_TABELA_COM_TITULO(tabelas_dados, "@TABELAS");
        ENTT.EXIBIR_TABELA_COM_TITULO(tabelas_esquemas, "@ESQUEMAS");

        ENTT.EXIBIR_TABELA_COM_TITULO(sequencias_dados, "@TABELAS :: SEQUENCIAS");
        ENTT.EXIBIR_TABELA_COM_TITULO(sequencias_esquemas, "@ESQUEMAS :: SEQUENCIAS");

    }

}
