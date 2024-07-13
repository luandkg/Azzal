package libs.entt;

import libs.arquivos.binario.Arquivador;
import libs.arquivos.ds.DS;
import libs.arquivos.ds.DSItem;
import libs.luan.Lista;
import libs.luan.fmt;

public class ONTT {


    public static Lista<Entidade> GET_ENTIDADES(String arquivo, String sufixo){
        Lista<Entidade> dados = new Lista<Entidade>();
        for(DSItem item : DS.ler_todos(arquivo)){
            if(item.getNome().endsWith(sufixo)){
                Entidade e = ENTT.PARSER_ENTIDADE(item.getTexto());
                dados.adicionar(e);
            }
        }
        return dados;
    }

    public static void CRIAR_ENTIDADE(String arquivo,String nome,String sufixo,Entidade entidade){
        DS.adicionar(arquivo,nome+sufixo,ENTT.TO_DOCUMENTO(entidade));
    }
    public static void CRIAR_DOCUMENTO(String arquivo,String nome,String sufixo,byte[] eDados){
        DS.adicionar(arquivo,nome+sufixo,eDados);
    }

    public static void CRIAR_DOCUMENTO(String arquivo,String nome,String sufixo,Lista<Byte> eDados){
        DS.adicionar(arquivo,nome+sufixo,eDados);
    }



    public static Lista<Entidade> VISUALIZAR(String arquivo){

            Lista<Entidade> entts = new Lista<Entidade>();

            for(DSItem item : DS.ler_todos(arquivo)){
                Entidade e = new Entidade();
                e.at("Nome",item.getNome());
                e.at("Tamanho", fmt.formatar_tamanho_precisao_dupla( item.getTamanho()));
                entts.adicionar(e);
            }

            return entts;

    }


    public static Lista<DSItem> FILTRAR(String arquivo, String sufixo){
        Lista<DSItem> dados = new Lista<DSItem>();

        for(DSItem item : DS.ler_todos(arquivo)){
            if(item.getNome().endsWith(sufixo)){
               dados.adicionar(item);
            }
        }

        return dados;
    }

}
