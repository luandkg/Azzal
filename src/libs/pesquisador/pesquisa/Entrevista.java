package libs.pesquisador.pesquisa;

import libs.pesquisador.pesquisa.quantitativo.FormularioDePesquisaQuantitativa;

public interface Entrevista <T>{

     void realizar(FormularioDePesquisaQuantitativa eFormulario, T eOrganismo) ;

}
