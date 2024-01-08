package libs.oo;

import libs.arquivos.DocumentoTexto;
import libs.arquivos.Zipper;
import libs.luan.*;
import libs.xlsx.Planilha;
import libs.xlsx.PlanilhaLinha;
import libs.xml.XML;
import libs.xml.XMLObjeto;



public class ODS {

    private Lista<Planilha> mPlanilhas;

    public ODS(String arquivo) {

        mPlanilhas = new Lista<Planilha>();

        Lista<DocumentoTexto> documentos = new Lista<DocumentoTexto>();

        documentos = Zipper.EXTRAIR_DOCUMENTOS_COM_EXTENSAO(arquivo, ".xml");

        Procurador<DocumentoTexto> documentos_procurador = new Procurador<DocumentoTexto>(new IgualdadeTiposDiferentes<String, DocumentoTexto>() {
            @Override
            public boolean isIgual(String chave, DocumentoTexto valor) {
                return valor.getNome().contentEquals(chave);
            }
        });

        Opcional<DocumentoTexto> doc_content = documentos_procurador.procure("content.xml", documentos);
        if (doc_content.isOK()) {

            XML documento = XML.PARSER_XML(doc_content.get().getConteudo());

            XMLObjeto office_document_content = documento.getObjeto("office:document-content");
            XMLObjeto office_body = office_document_content.getObjeto("office:body");
            XMLObjeto office_spreadsheet = office_body.getObjeto("office:spreadsheet");

            for (XMLObjeto xml_planilha : office_spreadsheet.getObjetos()) {
                if (xml_planilha.isNome("table:table")) {

                    Planilha planilha_nova = new Planilha();
                    planilha_nova.setTitulo(xml_planilha.atributo("table:name").getValor());

                    Lista<PlanilhaLinha> linhas = parser_planilha(xml_planilha);
                    planilha_nova.getLinhas().adicionar_varios(linhas);
                    mPlanilhas.adicionar(planilha_nova);

                }
            }


        }


    }

    public Lista<Planilha> getPlanilhas() {
        return mPlanilhas;
    }

    public static Lista<PlanilhaLinha> parser_planilha(XMLObjeto documento) {

        Lista<PlanilhaLinha> planilha = new Lista<PlanilhaLinha>();

        int ix_linha = 0;

        for (XMLObjeto obj : documento.getObjetos()) {

            if (obj.isNome("table:table-row")) {

                PlanilhaLinha pl = new PlanilhaLinha();

                for (XMLObjeto int_col : obj.getObjetos()) {
                    //  if(int_col.isNome("table:table-cell")){
                    String valor = int_col.getObjeto("text:p").getConteudo();
                    valor = valor.replace("\n", "");

                    pl.adicionar(valor);
                    //  }
                }

                planilha.adicionar(pl);

                ix_linha += 1;

            }


        }

        return planilha;
    }




}