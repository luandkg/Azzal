package hipertestes;

import java.util.ArrayList;

public class Teste {

    private String mNome;

    private int CONTADOR_ASSERTIVAS;
    private int CONTADOR_ASSERTIVAS_SUCESSO;
    private int CONTADOR_ASSERTIVAS_FALHAS;

    private ArrayList<String> mFalhas;

    public Teste(String eNome) {
        mNome = eNome;
        mFalhas = new ArrayList<String>();
    }

    public String getNome() {
        return mNome;
    }

    public void executar() {

    }

    public void iniciarContagem() {
        CONTADOR_ASSERTIVAS = 0;
        CONTADOR_ASSERTIVAS_SUCESSO = 0;
        CONTADOR_ASSERTIVAS_FALHAS = 0;
    }

    public void assertiva_validar(boolean resposta) {
        if (resposta) {
            CONTADOR_ASSERTIVAS_SUCESSO += 1;
        } else {
            CONTADOR_ASSERTIVAS_FALHAS += 1;
        }

    }

    public String getRealizadosTexto() {
        String s = "";
        s = String.valueOf(CONTADOR_ASSERTIVAS) + " assertivas";
        return s;
    }

    public String getStatusTexto() {
        String s = "";

        if (CONTADOR_ASSERTIVAS_SUCESSO == CONTADOR_ASSERTIVAS) {
            s = "OK";
        } else {
            if (CONTADOR_ASSERTIVAS_FALHAS == 1) {
                s = String.valueOf(CONTADOR_ASSERTIVAS_FALHAS) + " falhou";
            } else {
                s = String.valueOf(CONTADOR_ASSERTIVAS_FALHAS) + " falharam";
            }
        }

        return s;
    }


    public boolean getStatus() {
        if (CONTADOR_ASSERTIVAS_SUCESSO == CONTADOR_ASSERTIVAS) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<String> getFalhas() {
        return mFalhas;
    }


    // CHECKS

    public boolean CHECK_EQ(String esperado, String obteve) {
        CONTADOR_ASSERTIVAS += 1;

        boolean resposta = esperado.contentEquals(obteve);

        if (!resposta) {
            mFalhas.add("");
            mFalhas.add("\t " + CONTADOR_ASSERTIVAS + " - CHECK EQ :: FALHOU -> Era esperado (" + esperado + ") e obteve " + obteve);
        }

        assertiva_validar(resposta);

        return resposta;
    }

    public boolean CHECK_NOT(String esperado, String obteve) {
        CONTADOR_ASSERTIVAS += 1;

        boolean resposta = !esperado.contentEquals(obteve);

        if (!resposta) {
            mFalhas.add("");
            mFalhas.add("\t " + CONTADOR_ASSERTIVAS + " - CHECK NOT :: FALHOU -> Era esperado um valor diferente de (" + esperado + ") mas obteve " + obteve);
        }

        assertiva_validar(resposta);

        return resposta;
    }

    public boolean CHECK_EQ(int esperado, int obteve) {
        CONTADOR_ASSERTIVAS += 1;

        boolean resposta = esperado == obteve;

        if (!resposta) {
            mFalhas.add("");
            mFalhas.add("\t " + CONTADOR_ASSERTIVAS + " - CHECK EQ :: FALHOU -> Era esperado (" + esperado + ") e obteve " + obteve);
        }

        assertiva_validar(resposta);

        return resposta;
    }

    public boolean CHECK_EQ(boolean esperado, boolean obteve) {
        CONTADOR_ASSERTIVAS += 1;

        boolean resposta = esperado == obteve;

        if (!resposta) {
            mFalhas.add("");
            mFalhas.add("\t " + CONTADOR_ASSERTIVAS + " - CHECK EQ :: FALHOU -> Era esperado (" + esperado + ") e obteve " + obteve);
        }

        assertiva_validar(resposta);

        return resposta;
    }

    public boolean CHECK_GT(int esperado, int obteve) {
        CONTADOR_ASSERTIVAS += 1;

        boolean resposta = obteve > esperado;

        if (!resposta) {
            mFalhas.add("");
            mFalhas.add("\t " + CONTADOR_ASSERTIVAS + " - CHECK GT :: FALHOU -> Era esperado (" + obteve + ") ser maior que " + esperado);
        }

        assertiva_validar(resposta);

        return resposta;
    }

    public boolean CHECK_LS(int esperado, int obteve) {
        CONTADOR_ASSERTIVAS += 1;

        boolean resposta = obteve < esperado;

        if (!resposta) {
            mFalhas.add("");
            mFalhas.add("\t " + CONTADOR_ASSERTIVAS + " - CHECK GT :: FALHOU -> Era esperado (" + obteve + ") ser menor que " + esperado);
        }

        assertiva_validar(resposta);

        return resposta;
    }

    public boolean CHECK_GE(int esperado, int obteve) {
        CONTADOR_ASSERTIVAS += 1;

        boolean resposta = obteve > esperado;

        if (!resposta) {
            mFalhas.add("");
            mFalhas.add("\t " + CONTADOR_ASSERTIVAS + " - CHECK GT :: FALHOU -> Era esperado (" + obteve + ") ser maior ou igual a que " + esperado);
        }

        assertiva_validar(resposta);

        return resposta;
    }

    public boolean CHECK_LE(int esperado, int obteve) {
        CONTADOR_ASSERTIVAS += 1;

        boolean resposta = obteve < esperado;

        if (!resposta) {
            mFalhas.add("");
            mFalhas.add("\t " + CONTADOR_ASSERTIVAS + " - CHECK GT :: FALHOU -> Era esperado (" + obteve + ") ser menor ou igual a " + esperado);
        }

        assertiva_validar(resposta);

        return resposta;
    }

    public boolean CHECK_NULL(Object obteve) {
        CONTADOR_ASSERTIVAS += 1;

        boolean resposta = obteve == null;

        if (!resposta) {
            mFalhas.add("");
            mFalhas.add("\t " + CONTADOR_ASSERTIVAS + " - CHECK NULL :: FALHOU -> Era esperado ( OBJETO NULO ) mas obteve " + obteve.toString());
        }

        assertiva_validar(resposta);

        return resposta;
    }

    public boolean CHECK_VALUE(Object obteve) {
        CONTADOR_ASSERTIVAS += 1;

        boolean resposta = obteve == null;

        if (!resposta) {
            mFalhas.add("");
            mFalhas.add("\t " + CONTADOR_ASSERTIVAS + " - CHECK VALUE :: FALHOU -> Era esperado ( OBJETO COM VALOR ) mas obteve OBJETO NULO");
        }

        assertiva_validar(resposta);

        return resposta;
    }

    public boolean CHECK_TRUE(boolean obteve) {
        CONTADOR_ASSERTIVAS += 1;

        boolean resposta = obteve == true;

        if (!resposta) {
            mFalhas.add("");
            mFalhas.add("\t " + CONTADOR_ASSERTIVAS + " - CHECK TRUE :: FALHOU -> Era esperado ( VERDADEIRO ) mas obteve FALSO");
        }

        assertiva_validar(resposta);

        return resposta;
    }

    public boolean CHECK_FALSE(boolean obteve) {
        CONTADOR_ASSERTIVAS += 1;

        boolean resposta = obteve == false;

        if (!resposta) {
            mFalhas.add("");
            mFalhas.add("\t " + CONTADOR_ASSERTIVAS + " - CHECK FALSE :: FALHOU -> Era esperado ( FALSO ) mas obteve VERDADEIRO");
        }

        assertiva_validar(resposta);

        return resposta;
    }
}
