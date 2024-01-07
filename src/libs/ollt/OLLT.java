package libs.ollt;

import java.io.*;
import java.util.ArrayList;

public class OLLT {

    // IMPLEMENTACAO : 2019 	  -->> OLLT 1.0
    // IMPLEMENTACAO : 2020 08 26 -->> VETOR e MATRIZ


    private ArrayList<Pacote> mPacotes;

    public OLLT() {

        mPacotes = new ArrayList<Pacote>();

    }

    public ArrayList<Pacote> getPacotes() {
        return mPacotes;
    }

    public Pacote CriarPacote(String eNome) {

        Pacote ret = new Pacote(eNome);
        mPacotes.add(ret);

        return ret;
    }

    public Pacote UnicoPacote(String eNome) {

        boolean enc = false;
        Pacote ret = null;

        for (Pacote mPacote : mPacotes) {
            if (mPacote.getNome().contentEquals(eNome)) {
                enc = true;
                ret = mPacote;
                break;
            }

        }

        if (enc == false) {
            ret = new Pacote(eNome);
            mPacotes.add(ret);
        }

        return ret;
    }

    public void RemoverPacote(Pacote ePacote) {

        for (Pacote mPacote : mPacotes) {

            if (mPacote == ePacote) {
                mPacotes.remove(ePacote);
                break;
            }

        }

    }

    public void RemoverPacotePorNome(String eNome) {

        for (Pacote mPacote : mPacotes) {

            if (mPacote.getNome().contentEquals(eNome)) {
                mPacotes.remove(mPacote);
                break;
            }

        }

    }

    public String toString() {

        TextoDocumento TextoDocumentoC = new TextoDocumento();
        Salvamento SalvamentoC = new Salvamento();

        SalvamentoC.Pacote_Listar(TextoDocumentoC, "", this.getPacotes());

        //System.out.println(ITextoC.toString());

        return TextoDocumentoC.toString();
    }

    public String gerarReduzido() {

        TextoDocumento TextoDocumentoC = new TextoDocumento();
        SalvamentoReduzido SalvamentoC = new SalvamentoReduzido();

        SalvamentoC.Pacote_Listar(TextoDocumentoC, this.getPacotes());

        return TextoDocumentoC.toString();
    }

    // IO

    public void Abrir(String eArquivo) {

        this.getPacotes().clear();

        Parser ParserC = new Parser();

        ParserC.Parse(arquivo_ler(eArquivo), this);

    }


    public void Parser(String eConteudo) {

        Parser ParserC = new Parser();

        ParserC.Parse(eConteudo, this);

    }

    public void Salvar(String eArquivo) {

        arquivo_escrever(eArquivo, this.toString());

    }

    public void SalvarReduzido(String eArquivo) {

        arquivo_escrever(eArquivo, this.gerarReduzido());

    }

    public String GerarString() {
        return this.toString();
    }

    // HIERARQUIZE

    public String Hierarquize() {
        TextoDocumento TextoDocumentoC = new TextoDocumento();
        Hierarquizador HierarquizadorC = new Hierarquizador();

        HierarquizadorC.Hierarquizar(TextoDocumentoC, "   ", this.getPacotes());

        return TextoDocumentoC.toString();

    }

    // EXTRA

    public Pacote PacoteComAtributoUnico(String eNomePacote, String eNomeIdentificador, String eValor) {

        Pacote ret = null;
        boolean enc = false;

        for (Pacote PacoteC : getPacotes()) {

            if (PacoteC.getNome().contentEquals(eNomePacote)) {

                if (PacoteC.IdentificadorExiste(eNomeIdentificador)) {
                    if (PacoteC.Identifique(eNomeIdentificador).getValor().contentEquals(eValor)) {
                        ret = PacoteC;
                        enc = true;
                        break;
                    }
                }

            }

        }

        if (!enc) {

            ret = new Pacote(eNomePacote);

            ret.Identifique(eNomeIdentificador, eValor);

            getPacotes().add(ret);
        }

        return ret;

    }

    public boolean PacoteComAtributo_Existe(String eNomePacote, String eNomeIdentificador, String eValor) {

        boolean enc = false;

        for (Pacote PacoteC : getPacotes()) {

            if (PacoteC.getNome().contentEquals(eNomePacote)) {

                if (PacoteC.IdentificadorExiste(eNomeIdentificador)) {
                    if (PacoteC.Identifique(eNomeIdentificador).getValor().contentEquals(eValor)) {
                        enc = true;
                        break;
                    }
                }

            }

        }

        return enc;

    }

    public void PacoteComAtributo_Remover(String eNomePacote, String eNomeIdentificador, String eValor) {

        for (Pacote PacoteC : getPacotes()) {

            if (PacoteC.getNome().contentEquals(eNomePacote)) {

                if (PacoteC.IdentificadorExiste(eNomeIdentificador)) {
                    if (PacoteC.Identifique(eNomeIdentificador).getValor().contentEquals(eValor)) {
                        getPacotes().remove(PacoteC);
                        break;
                    }
                }

            }

        }

    }


    private static void arquivo_escrever(String eArquivo, String eConteudo) {

        BufferedWriter writer = null;
        try {
            File logFile = new File(eArquivo);

            writer = new BufferedWriter(new FileWriter(logFile));
            writer.write(eConteudo);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
            }
        }

    }

    private static String arquivo_ler(String eArquivo) {

        String ret = "";

        try {
            FileReader arq = new FileReader(eArquivo);
            BufferedReader lerArq = new BufferedReader(arq);

            String linha = lerArq.readLine();

            ret += linha;

            while (linha != null) {

                linha = lerArq.readLine();
                if (linha != null) {
                    ret += "\n" + linha;
                }

            }

            arq.close();
        } catch (IOException e) {

        }

        return ret;
    }

}
