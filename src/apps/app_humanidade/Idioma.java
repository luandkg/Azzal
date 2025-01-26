package apps.app_humanidade;

import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.*;

public class Idioma {

    private String mNome;

    private Lista<String> mLexemasIniciais;
    private Lista<String> mLexemasInternos;
    private Lista<String> mLexemasSoInternos;
    private Lista<String> mLexemasTerminais;

    private int mProbabilidadeInicial = 100;
    private int mProbilidadeInterna = 100;
    private int mProbabilidadeTerminacao = 100;

    private int mQuantidadeInterna = 1;
    private int mTamanhoMinimo = 1;

    public Idioma(String eNome) {

        mNome = eNome;
        mLexemasIniciais = new Lista<String>();
        mLexemasInternos = new Lista<String>();
        mLexemasSoInternos = new Lista<String>();
        mLexemasTerminais = new Lista<String>();

    }

    public String getNome() {
        return mNome;
    }


    protected void adicionar_lexema_inicial(String lexema) {
        mLexemasIniciais.adicionar(lexema);
    }

    protected void adicionar_lexema_interno(String lexema) {
        mLexemasInternos.adicionar(lexema);
    }

    protected void adicionar_lexema_so_interno(String lexema) {
        mLexemasSoInternos.adicionar(lexema);
    }

    protected void adicionar_lexema_terminal(String lexema) {
        mLexemasTerminais.adicionar(lexema);
    }

    protected void setProbabilidadeInicial(int eProbabilidade) {
        mProbabilidadeInicial = eProbabilidade;
    }

    protected void setProbabilidadeInterna(int eProbabilidade) {
        mProbilidadeInterna = eProbabilidade;
    }

    protected void setProbabilidadeTerminacao(int eProbabilidade) {
        mProbabilidadeTerminacao = eProbabilidade;
    }

    protected void setQuantidadeInterna(int eQuantidade) {
        mQuantidadeInterna = eQuantidade;
    }

    protected void setTamanhoMinimo(int eTamanho) {
        mTamanhoMinimo = eTamanho;
    }

    public String get() {

        String s = getInterno();

        while (s.length() < mTamanhoMinimo) {
            s = getInterno();
        }

        return s;
    }

    public String getPequeno() {

        String s = getInternoPequeno();

        s = getInternoPequeno();


        return s;
    }

    protected String getInterno() {

        String a = "";
        String b = "";
        String c = "";

        int p_inicial = Aleatorio.aleatorio(100);
        if (p_inicial <= mProbabilidadeInicial) {
            a = Aleatorio.escolha_um(mLexemasIniciais);
        }

        if (mQuantidadeInterna == 1) {

            int p_interna = Aleatorio.aleatorio(100);
            if (p_interna <= mProbilidadeInterna) {

                if (!a.isEmpty() && mLexemasSoInternos.possuiObjetos() && Aleatorio.aleatorio(100) >= 70) {
                    b = Aleatorio.escolha_um(mLexemasSoInternos);
                } else {
                    b = Aleatorio.escolha_um(mLexemasInternos);
                }

            }

        } else {

            int quantas = Aleatorio.aleatorio(mQuantidadeInterna);
            for (int v = 0; v < quantas; v++) {
                int p_interna = Aleatorio.aleatorio(100);
                if (p_interna <= mProbilidadeInterna) {

                    if (!a.isEmpty() && mLexemasSoInternos.possuiObjetos() && Aleatorio.aleatorio(100) >= 70) {
                        b += Aleatorio.escolha_um(mLexemasSoInternos);
                    } else {
                        b += Aleatorio.escolha_um(mLexemasInternos);
                    }

                }
            }

        }


        int p_terminacao = Aleatorio.aleatorio(100);
        if (p_terminacao <= mProbabilidadeTerminacao) {
            c = Aleatorio.escolha_um(mLexemasTerminais);
        }


        return a + b + c;
    }

    public String getInternoPequeno() {

        String a = "";
        String b = "";
        String c = "";

        int p_inicial = Aleatorio.aleatorio(100);
        if (p_inicial <= mProbabilidadeInicial) {
            a = Aleatorio.escolha_um(mLexemasIniciais);
        }

        if (mQuantidadeInterna == 1) {

            int p_interna = Aleatorio.aleatorio(100);
            if (p_interna <= mProbilidadeInterna) {

                if (!a.isEmpty() && mLexemasSoInternos.possuiObjetos() && Aleatorio.aleatorio(100) >= 70) {
                    b = Aleatorio.escolha_um(mLexemasSoInternos);
                } else {
                    b = Aleatorio.escolha_um(mLexemasInternos);
                }

            }

        } else {

            int quantas = Aleatorio.aleatorio(mQuantidadeInterna);
            for (int v = 0; v < quantas; v++) {
                int p_interna = Aleatorio.aleatorio(100);
                if (p_interna <= mProbilidadeInterna) {

                    if (!a.isEmpty() && mLexemasSoInternos.possuiObjetos() && Aleatorio.aleatorio(100) >= 70) {
                        b += Aleatorio.escolha_um(mLexemasSoInternos);
                    } else {
                        b += Aleatorio.escolha_um(mLexemasInternos);
                    }

                }
            }

        }


        int p_terminacao = Aleatorio.aleatorio(100);
        if (p_terminacao <= mProbabilidadeTerminacao) {
            c = Aleatorio.escolha_um(mLexemasTerminais);
        }

        if (Aleatorio.aleatorio(100) > 50) {
            return a + b;
        } else {
            return a + c;
        }

    }


    public String getUnico(Unico<String> unicas) {
        String palavra = get();
        while (unicas.existe(palavra)) {
            palavra = get();
        }
        unicas.item(palavra);
        return palavra;
    }

    public String getUnicoPequeno(Unico<String> unicas) {
        String palavra = getPequeno();
        while (unicas.existe(palavra) || palavra.length() > 4 || palavra.length() == 0) {
            palavra = getPequeno();
        }
        unicas.item(palavra);
        return palavra;
    }

    public String getUnicoTamanhoMaximo(Unico<String> unicas, int eTamanho) {
        String palavra = get();

        while (unicas.existe(palavra) || palavra.length() > eTamanho) {
            palavra = get();
        }

        unicas.item(palavra);
        return palavra;
    }

    public void exibir_amostra() {

        fmt.print("");
        fmt.print("------------------- {} -----------------------", getNome());


        Lista<Entidade> palavras = new Lista<Entidade>();

        Unico<String> palavras_unicas = new Unico<String>(Strings.IGUALAVEL());

        for (int i = 1; i <= 10; i++) {
            Entidade e_palavras = ENTT.CRIAR_EM(palavras);
            for (int c = 1; c <= 5; c++) {
                e_palavras.at("Palavra_" + c, Strings.CAPTALIZAR_FRASE(getUnico(palavras_unicas)));
            }
        }

        fmt.print("Lexemas Iniciais  : {}", mLexemasIniciais.getQuantidade());
        fmt.print("Lexemas Internos  : {}", mLexemasInternos.getQuantidade());
        fmt.print("Lexemas Terminais : {}", mLexemasTerminais.getQuantidade());

        ENTT.EXIBIR_TABELA_COM_TITULO(palavras, getNome());
        fmt.print("");

    }

    public Lista<String> getAmostra(int quantidade) {

        Lista<String> palavras = new Lista<String>();

        Unico<String> palavras_unicas = new Unico<String>(Strings.IGUALAVEL());

        for (int i = 1; i <= quantidade; i++) {
            palavras.adicionar(Strings.CAPTALIZAR_FRASE(getUnico(palavras_unicas)));

        }

        return palavras;
    }

    public Lista<String> getAmostraPequeno(int quantidade) {

        Lista<String> palavras = new Lista<String>();

        Unico<String> palavras_unicas = new Unico<String>(Strings.IGUALAVEL());

        for (int i = 1; i <= quantidade; i++) {
            palavras.adicionar(Strings.CAPTALIZAR_FRASE(getUnicoPequeno(palavras_unicas)));

        }

        return palavras;
    }


    public Lista<String> getAmostraTamanhoMaximo(int quantidade, int eTamanho) {

        Lista<String> palavras = new Lista<String>();

        Unico<String> palavras_unicas = new Unico<String>(Strings.IGUALAVEL());

        for (int i = 1; i <= quantidade; i++) {
            palavras.adicionar(Strings.CAPTALIZAR_FRASE(getUnicoTamanhoMaximo(palavras_unicas, eTamanho)));

        }

        return palavras;
    }


    public boolean isPalavraFinalizada(Lista<Entidade> lexemas_analisados) {
        for (Entidade lexema_analisando : lexemas_analisados) {
            if (lexema_analisando.at("Palavra").isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public void marque_lexemas_finalizantes(Lista<Entidade> lexemas_analisados) {
        for (Entidade terminal : lexemas_analisados) {
            if (terminal.at("Palavra").isEmpty()) {
                terminal.at("Status", "FIM");
            }
        }
    }

    public boolean pertence_debug(String palavra) {
        return pertence_analisando(palavra, true);
    }

    public boolean pertence(String palavra) {
        return pertence_analisando(palavra, false);
    }

    public boolean pertence_analisando(String palavra, boolean analisando) {

        palavra = palavra.toLowerCase();

        if (analisando) {
            fmt.print("Lexemas Iniciais  : {}", Strings.LISTA_TO_TEXTO_LINHA(mLexemasIniciais));
            fmt.print("Lexemas Internos  : {}", Strings.LISTA_TO_TEXTO_LINHA(mLexemasInternos));
            fmt.print("Lexemas Internos  : {}", Strings.LISTA_TO_TEXTO_LINHA(mLexemasSoInternos));
            fmt.print("Lexemas Terminais : {}", Strings.LISTA_TO_TEXTO_LINHA(mLexemasTerminais));
        }


        // Começar com segundo
        boolean resultado = pertence_internamente_primeiro(palavra, analisando);

        if (!resultado) {
            resultado = pertence_internamente_segundo(palavra, analisando);
        }


        return resultado;
    }

    public boolean pertence_internamente_primeiro(String palavra, boolean analisando) {

        Lista<Entidade> inicio_encontrado = procurar_lexema_iniciando(palavra, mLexemasIniciais);

        if (analisando) {
            ENTT.EXIBIR_TABELA_COM_TITULO(inicio_encontrado, "INICIANDO - PARTE 1");
        }

        if (ENTT.TEM(inicio_encontrado)) {

            if (isPalavraFinalizada(inicio_encontrado)) {
                return true;
            }

            for (Entidade iniciando : inicio_encontrado) {

                Lista<String> internos = new Lista<String>();
                internos.adicionar_varios(mLexemasInternos);
                internos.adicionar_varios(mLexemasSoInternos);

                Lista<Entidade> interno_encontrado = procurar_lexema_iniciando(iniciando.at("Palavra"), internos);


                if (ENTT.TEM(interno_encontrado)) {

                    marque_lexemas_finalizantes(interno_encontrado);

                    if (analisando) {
                        ENTT.EXIBIR_TABELA_COM_TITULO(interno_encontrado, "INTERNO - PARTE 2");
                    }

                    for (Entidade interno : interno_encontrado) {

                        if (interno.at("Palavra").isEmpty()) {
                            return true;
                        } else {
                            if (pertence_internamente_procurando(3, interno, internos, analisando)) {
                                return true;
                            }
                        }

                    }


                    Lista<Entidade> terminal_encontrado = procurar_lexema_iniciando(iniciando.at("Palavra"), mLexemasTerminais);
                    if (ENTT.TEM(terminal_encontrado)) {

                        marque_lexemas_finalizantes(terminal_encontrado);

                        if (analisando) {
                            ENTT.EXIBIR_TABELA_COM_TITULO(terminal_encontrado, "TERMINAIS DIRETAMENTE - PARTE 3");
                        }

                        if (isPalavraFinalizada(terminal_encontrado)) {
                            return true;
                        }

                    }

                } else {

                    Lista<Entidade> terminal_encontrado = procurar_lexema_iniciando(iniciando.at("Palavra"), mLexemasTerminais);


                    if (ENTT.TEM(terminal_encontrado)) {

                        marque_lexemas_finalizantes(terminal_encontrado);

                        if (analisando) {
                            ENTT.EXIBIR_TABELA_COM_TITULO(terminal_encontrado, "INTERNO - PARTE 3");
                        }

                        if (isPalavraFinalizada(terminal_encontrado)) {
                            return true;
                        }

                    }

                }

            }

        }

        return false;
    }

    public boolean pertence_internamente_segundo(String palavra, boolean analisando) {

        palavra = palavra.toLowerCase();

        Lista<String> internos = new Lista<String>();
        internos.adicionar_varios(mLexemasInternos);
        internos.adicionar_varios(mLexemasSoInternos);

        Lista<Entidade> interno_encontrado = procurar_lexema_iniciando(palavra, internos);


        if (ENTT.TEM(interno_encontrado)) {

            marque_lexemas_finalizantes(interno_encontrado);

            if (analisando) {
                ENTT.EXIBIR_TABELA_COM_TITULO(interno_encontrado, "INTERNO - PARTE 2");
            }

            for (Entidade interno : interno_encontrado) {

                if (interno.at("Palavra").isEmpty()) {
                    return true;
                } else {
                    if (pertence_internamente_procurando(3, interno, internos, analisando)) {
                        return true;
                    }
                }

            }


            Lista<Entidade> terminal_encontrado = procurar_lexema_iniciando(palavra, mLexemasTerminais);
            if (ENTT.TEM(terminal_encontrado)) {

                marque_lexemas_finalizantes(terminal_encontrado);

                if (analisando) {
                    ENTT.EXIBIR_TABELA_COM_TITULO(terminal_encontrado, "TERMINAIS DIRETAMENTE - PARTE 3");
                }

                if (isPalavraFinalizada(terminal_encontrado)) {
                    return true;
                }

            }

        } else {

            Lista<Entidade> terminal_encontrado = procurar_lexema_iniciando(palavra, mLexemasTerminais);


            if (ENTT.TEM(terminal_encontrado)) {

                marque_lexemas_finalizantes(terminal_encontrado);

                if (analisando) {
                    ENTT.EXIBIR_TABELA_COM_TITULO(terminal_encontrado, "INTERNO - PARTE 3");
                }

                if (isPalavraFinalizada(terminal_encontrado)) {
                    return true;
                }

            }

        }


        return false;
    }


    public boolean pertence_internamente_procurando(int repetindo, Entidade interno, Lista<String> internos, boolean analisando) {

        Lista<Entidade> interno_repetidamente_encontrado = procurar_lexema_iniciando(interno.at("Palavra"), internos);

        marque_lexemas_finalizantes(interno_repetidamente_encontrado);

        if (analisando) {
            ENTT.EXIBIR_TABELA_COM_TITULO(interno_repetidamente_encontrado, "INTERNO - PARTE " + repetindo + " - REP");
        }

        if (isPalavraFinalizada(interno_repetidamente_encontrado)) {
            return true;
        }

        if (ENTT.TEM(interno_repetidamente_encontrado)) {
            for (Entidade rep : interno_repetidamente_encontrado) {


                Lista<Entidade> terminal_encontrado = procurar_lexema_iniciando(rep.at("Palavra"), mLexemasTerminais);

                marque_lexemas_finalizantes(terminal_encontrado);

                if (analisando) {
                    ENTT.EXIBIR_TABELA_COM_TITULO(terminal_encontrado, "TERMINAL - PARTE 3");
                }

                if (isPalavraFinalizada(terminal_encontrado)) {
                    return true;
                }

                if (pertence_internamente_procurando(repetindo + 1, rep, internos, analisando)) {
                    return true;
                }


            }
        }

        Lista<Entidade> terminal_encontrado = procurar_lexema_iniciando(interno.at("Palavra"), mLexemasTerminais);

        marque_lexemas_finalizantes(terminal_encontrado);

        if (analisando) {
            ENTT.EXIBIR_TABELA_COM_TITULO(terminal_encontrado, "TERMINAL - PARTE 3");
        }

        if (isPalavraFinalizada(terminal_encontrado)) {
            return true;
        }

        return false;
    }

    public boolean pertence_frase(String eFrase) {

        int percente_i = 0;
        int quantidade = 0;

        for (String parte : Strings.DIVIDIR_ESPACOS(eFrase)) {
            if (pertence(parte)) {
                percente_i += 1;
            }
            quantidade += 1;
        }


        return percente_i == quantidade;


    }

    public boolean pertence_palavras(Lista<String> palavras) {

        int percente_i = 0;
        int quantidade = 0;

        for (String parte : palavras) {
            if (pertence(parte)) {
                percente_i += 1;
            }
            quantidade += 1;
        }


        return percente_i == quantidade;


    }

    public Lista<Entidade> procurar_lexema_iniciando(String palavra, Lista<String> lexemas) {

        Lista<Entidade> lexemas_encontrados = new Lista<Entidade>();

        for (String lexema : lexemas) {
            if (palavra.startsWith(lexema)) {


                Entidade e = ENTT.CRIAR_EM(lexemas_encontrados);
                e.at("Lexema", lexema);
                e.at("Palavra", palavra.substring(lexema.length()));


            }
        }

        return lexemas_encontrados;
    }


    public static IgualavelAtributo<Idioma, String> PROCURAVEL_COM_NOME() {
        return new IgualavelAtributo<Idioma, String>() {
            @Override
            public boolean is(Idioma a, String b) {
                return Strings.isIgual(a.getNome(), b);
            }
        };
    }
}
