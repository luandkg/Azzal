package algoritmos;

import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.*;

public class LimonTorrents {


    public static void INIT(){


        Opcional<String> limon_torrents = Internet.GET_PAGINA_HTML_TIMEOUT("https://nickfilmes.net");

        if(limon_torrents.isOK()){
            String pagina = limon_torrents.get();
          //  fmt.print("{}",pagina);

            Lista<String> tokens =new Lista<String>();

            for(String linha : Strings.DIVIDIR_LINHAS(pagina)){
                tokens.adicionar_varios( Strings.DIVIDIR_POR(linha,"<"));
            }

            Lista<Entidade> e_tokens=ENTT.VALORES_SEQUENCIADOS("ID","Token",tokens);


            for(Entidade e : e_tokens){
                e.at("Token",e.at("Token").replace("\n",""));
                e.at("Token",e.at("Token").trim());
            }

           // ENTT.REMOVER_SE(e_tokens,"Token","");

           // ENTT.EXIBIR_TABELA(e_tokens);

            for(Entidade e : e_tokens) {
                if(e.at("Token").contains("http")){
                    fmt.print(">> {}",e.at("ID"));
                    ENTT.EXIBIR_TABELA(ENTT.SLICE(e_tokens,e.atInt("ID")-10,e.atInt("ID")+10));
                }
            }

            ENTT.EXIBIR_TABELA(ENTT.SLICE(e_tokens,200,300));

        }




    }

}
