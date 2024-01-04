package apps.app;

import libs.luan.Texto;
import libs.luan.fmt;

import java.util.ArrayList;

public class GGDNA {

    public static void init() {


        String ARQUIVO_DADOS = "/home/luan/Dropbox/CED_01/GGDNA/267145.csv";

        ParserString dna = new ParserString(Texto.arquivo_ler(ARQUIVO_DADOS));


        ArrayList<Cromossomo> cromossomos = new ArrayList<Cromossomo>();

        while (dna.continuar()) {
            String linha = dna.get_linha();

            if (linha.startsWith("rs")) {

                Gene gene = Gene.parse(linha);

                Cromossomo cromo = getCromossomo(cromossomos, gene.getCromossomo());
                cromo.adicionar_gene(gene);

            }

            dna.avancar();
        }


        fmt.print("--------------------- ANALISAR DNA ---------------------");
        fmt.print("");
        fmt.print("\t - DNA - Tamanho     = {}", fmt.formatar_tamanho_precisao_dupla(dna.getQuantidadeDeBytes()));
        fmt.print("\t - DNA - Genes       = {}", genes_contagem(cromossomos));
        fmt.print("\t - DNA - Cromossomos = {}", cromossomos.size());
        fmt.print("");

        for (Cromossomo c : cromossomos) {
            fmt.print("\t\t\t - Cromossomo {} -->> {} genes", fmt.espacar_antes(c.getNome(), 3), fmt.espacar_antes(c.getQuantidade(), 5));
        }

        fmt.print("");
        fmt.print("");
        fmt.print("");

        String s = "";

        for (Cromossomo c : cromossomos) {

            if (c.getNome().contentEquals("MT")) {

                for (Gene g : c.getGenes()) {
                    System.out.println("cromo.adicionar_gene(\"" + g.getRSID() + "\",\"" + g.getPosicao() + "\"," + "\"" + g.getAlelos() + "\");");
                    s += ("cromo.adicionar_gene(\"" + g.getRSID() + "\",\"" + g.getPosicao() + "\"," + "\"" + g.getAlelos() + "\");") + "\n";
                }

            }

        }

        Texto.arquivo_escrever("/home/luan/Dropbox/CED_01/GGDNA/cromo.txt", s);
    }


    public static int genes_contagem(ArrayList<Cromossomo> cromossomos) {
        int genes = 0;

        for (Cromossomo c : cromossomos) {
            genes += c.getQuantidade();
        }
        return genes;
    }

    public static Cromossomo getCromossomo(ArrayList<Cromossomo> cromossomos, String nome) {

        boolean enc = false;
        Cromossomo ret = null;

        for (Cromossomo c : cromossomos) {
            if (c.getNome().contentEquals(nome)) {
                enc = true;
                ret = c;
                break;
            }
        }

        if (!enc) {
            Cromossomo c = new Cromossomo(nome);
            cromossomos.add(c);
            ret = c;
        }

        return ret;

    }
}
