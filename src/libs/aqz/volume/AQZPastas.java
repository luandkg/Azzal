package libs.aqz.volume;

import libs.aqz.colecao.ColecaoTX;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;

public class AQZPastas {


    public static void EXIBIR_PASTAS(String arquivo_banco) {

        AZInternamentePastas aqz = new AZInternamentePastas(arquivo_banco);

        Lista<Entidade> objetos = new Lista<Entidade>();
        for (ColecaoTX item : aqz.colecoes_listar()) {
            Entidade e = new Entidade();
            e.at("ID", item.getID());
            e.at("Nome", item.getNome());
            e.at("Arquivos", item.getItensContagem());
            objetos.adicionar(e);
        }

        ENTT.EXIBIR_TABELA(objetos);

        aqz.fechar();

    }

}
