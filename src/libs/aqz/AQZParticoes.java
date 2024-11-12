package libs.aqz;

import libs.aqz.colecao.AZInternamenteUTF8;
import libs.aqz.utils.ItemDoBancoTX;
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
}
