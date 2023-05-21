package libs.xlsx;

import libs.xml.XML;
import libs.xml.XMLObjeto;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class XLSX {

    private ArrayList<String> mSharedStrings;
    private ArrayList<Planilha> mPlanilhas;


    public XLSX(String eArquivo) {

        mSharedStrings = new ArrayList<String>();
        mPlanilhas = new ArrayList<Planilha>();

        ArrayList<Documento> documentos = new ArrayList<Documento>();
        ArrayList<Documento> planilhas_titulos = new ArrayList<Documento>();

        documentos = extrair_xmls(eArquivo);

        for (Documento doc : documentos) {
//            System.out.println(doc.getNome() + " :: " + doc.getConteudo().length());
       }

        for (Documento doc : documentos) {
            if (doc.getNome().contentEquals("xl/sharedStrings.xml")) {

                //  System.out.println("SHARED STRINGS");
                // System.out.println(doc.getConteudo());

                XML xml = new XML();
                xml.parser(doc.getConteudo());
                // xml.exibir();

                for (XMLObjeto obj : xml.getObjeto("sst").getObjetos()) {
                    mSharedStrings.add(obj.getObjeto("t").getConteudo());
                }

                break;
            }
        }

        for (Documento doc : documentos) {
            if (doc.getNome().contentEquals("xl/workbook.xml")) {

             //   System.out.println("WORK BOOK");
              //  System.out.println(doc.getConteudo());

                XML xml = new XML();
                xml.parser(doc.getConteudo());
                //      xml.exibir();

                int id = 0;

                for (XMLObjeto obj : xml.getObjeto("workbook").getObjeto("sheets").getObjetos()) {
                    planilhas_titulos.add(new Documento(String.valueOf(id), obj.atributo("name").getValor()));
                    id += 1;
                }


                break;
            }
        }


        int planilha_id = 0;


        for (Documento doc : documentos) {
            if (doc.getNome().startsWith("xl/worksheets/sheet")) {

                XML xml = new XML();
                xml.parser(doc.getConteudo());
                //  xml.exibir();

                Planilha planilha_corrente = new Planilha();

                String sID = String.valueOf(planilha_id);

                for (Documento pt : planilhas_titulos) {
                    if (pt.getNome().contentEquals(sID)) {
                        planilha_corrente.setTitulo(pt.getConteudo());
                        break;
                    }
                }


                planilha_id += 1;

                int linha_corrente = 1;
                for (XMLObjeto obj : xml.getObjeto("worksheet").getObjeto("sheetData").getObjetos()) {

                    mSharedStrings.add(obj.getObjeto("t").getConteudo());

                    PlanilhaLinha linha = new PlanilhaLinha();

                    for (XMLObjeto coluna : obj.getObjetos()) {

                        String tipo = coluna.atributo("t").getValor();
                        String valor = coluna.getObjeto("v").getConteudo();

                        if (tipo.contentEquals("s")) {
                            linha.adicionar(mSharedStrings.get(Integer.parseInt(valor)));
                        } else {
                            linha.adicionar(valor);
                        }

                    }

                    planilha_corrente.adicionar(linha);

                    linha_corrente += 1;
                }

                mPlanilhas.add(planilha_corrente);
            }
        }


    }

    public void exibir_ss() {
        int si = 0;
        for (String ss : mSharedStrings) {
            System.out.println("\t -->> SS(" + si + ") = " + ss);
            si += 1;
        }
    }

    public void exibir() {

        for (Planilha planilha : mPlanilhas) {

            System.out.println("$$ Planilha :: " + planilha.getTitulo() + " ( " + planilha.maxLinhas() + " :: " + planilha.maxColunas() + " )");

            for (PlanilhaLinha linha : planilha.getLinhas()) {

                System.out.println(linha.getString());

            }

        }

    }


    public ArrayList<Documento> extrair_xmls(String eArquivo) {
        ArrayList<Documento> documentos = new ArrayList<Documento>();

        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(eArquivo))) {
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {


                if (zipEntry.getName().endsWith(".xml")) {

                    ArenaChunk chunks = new ArenaChunk();

                    byte [] buffer = new byte[1024];
                    int len = 0;

                    while ((len = zis.read(buffer)) > 0) {
                        chunks.adicionar(buffer,len);
                    }


                    documentos.add(new Documento(zipEntry.getName(), new String(chunks.toBytes())));


                }
                zipEntry = zis.getNextEntry();
            }


            zis.closeEntry();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return documentos;
    }
}
