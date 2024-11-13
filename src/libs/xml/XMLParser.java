package libs.xml;

import libs.luan.Lista;


public class XMLParser {

    private String mDocumento;
    private int mIndex;
    private int mTamanho;

    public XMLParser() {

        mDocumento = "";
        mIndex = 0;
        mTamanho = 0;

    }


    public void parser(Lista<XMLObjeto> mObjetos, String texto) {

        mObjetos.limpar();

        mDocumento = texto;
        mIndex = 0;
        mTamanho = mDocumento.length();

        while (mIndex < mTamanho) {
            String letra = String.valueOf(mDocumento.charAt(mIndex));

            if (letra.contentEquals(" ") || letra.contentEquals("\t") || letra.contentEquals("\n")) {
            } else if (letra.contentEquals("<")) {
                mIndex += 1;
                parser_objeto(null,mObjetos);
            }

            mIndex += 1;
        }

    }

    public boolean parser_objeto(XMLObjeto ePai,Lista<XMLObjeto> objetos_raiz) {

        boolean deve_sair_do_objeto_pai = false;

        String letra_primeira = String.valueOf(mDocumento.charAt(mIndex));

        if (letra_primeira.contentEquals("!")) {
            mIndex += 1;
            String comentario = getComentario();

            XMLObjeto obj_com = new XMLObjeto("", XML.XML_COMENTARIO);
            obj_com.setConteudo(comentario);

            objetos_raiz.adicionar(obj_com);
            return false;

        } else if (letra_primeira.contentEquals("/")) {
            mIndex += 1;
            String nome = getNome();
            esperar_ate_maior();
            deve_sair_do_objeto_pai = true;


        } else if (letra_primeira.contentEquals("?")) {
            mIndex += 1;
            String nome = getNome();
            XMLObjeto proc = new XMLObjeto("?" + nome, XML.XML_PROCESSADOR);
            objetos_raiz.adicionar(proc);

            boolean deve_fechar = false;

            while (mIndex < mTamanho) {
                String letra = String.valueOf(mDocumento.charAt(mIndex));

                if (letra.contentEquals(" ") || letra.contentEquals("\t") || letra.contentEquals("\n")) {
                } else if (letra.contentEquals("?")) {
                    deve_fechar = true;
                    mIndex += 1;
                    break;
                } else {
                    String at_nome = getNome();
                    esperar_ate_igual();
                    mIndex += 1;
                    String at_valor = getTexto();

                    proc.atributo(at_nome, at_valor);
                }

                mIndex += 1;
            }

            if (deve_fechar) {
                esperar_ate_maior();
            }

        } else {

            String nome = getNome();
            XMLObjeto proc = new XMLObjeto(nome);

            if(ePai!=null){
                proc.setPai(ePai);
            }

            objetos_raiz.adicionar(proc);

            boolean deve_fechar = false;
            boolean deve_entrar = false;

            while (mIndex < mTamanho) {
                String letra = String.valueOf(mDocumento.charAt(mIndex));

                if (letra.contentEquals(" ") || letra.contentEquals("\t") || letra.contentEquals("\n")) {
                } else if (letra.contentEquals("/")) {
                    deve_fechar = true;
                    mIndex += 1;
                    break;
                } else if (letra.contentEquals(">")) {
                    deve_fechar = false;
                    deve_entrar = true;
                    mIndex += 1;
                    break;
                } else {
                    String at_nome = getNome();
                    esperar_ate_igual();
                    mIndex += 1;
                    String at_valor = getTexto();


                    proc.atributo(at_nome, at_valor);
                }

                mIndex += 1;
            }

            if (deve_fechar) {
                esperar_ate_maior();
            }

            if (deve_entrar) {

                String conteudo = "";

                while (mIndex < mTamanho) {
                    String letra = String.valueOf(mDocumento.charAt(mIndex));

                    if (letra.contentEquals("<")) {
                        mIndex += 1;

                        if (parser_objeto(proc,proc.getObjetos())) {
                            break;
                        }
                    } else {
                        conteudo += letra;
                    }

                    mIndex += 1;
                }

                proc.setConteudo(retirar_quebra(conteudo));

            }
        }


        return deve_sair_do_objeto_pai;
    }

    public String getNome() {
        String ret = "";


        while (mIndex < mTamanho) {
            String letra = String.valueOf(mDocumento.charAt(mIndex));

            if (letra.contentEquals(" ") || letra.contentEquals("\t") || letra.contentEquals("\n") || letra.contentEquals(">") || letra.contentEquals("=") || letra.contentEquals("/")) {
                break;
            } else {
                ret += letra;
            }

            mIndex += 1;
        }


        return ret;
    }

    public String getTexto() {
        String ret = "";

        String abriu_com = "";
        boolean aberto = false;

        while (mIndex < mTamanho) {
            String letra = String.valueOf(mDocumento.charAt(mIndex));

            if (letra.contentEquals(" ") || letra.contentEquals("\t") || letra.contentEquals("\n")) {

            } else if (letra.contentEquals("\"")) {
                aberto = true;
                abriu_com = "\"";
                mIndex += 1;
                break;
            }

            mIndex += 1;
        }

        if (aberto) {

            while (mIndex < mTamanho) {
                String letra = String.valueOf(mDocumento.charAt(mIndex));

                if (letra.contentEquals(" ") || letra.contentEquals("\t") || letra.contentEquals("\n")) {

                } else if (letra.contentEquals(abriu_com)) {
                    break;
                } else {
                    ret += letra;
                }

                mIndex += 1;
            }

        }

        return ret;
    }

    public boolean esperar_ate_igual() {
        boolean ret = false;


        while (mIndex < mTamanho) {
            String letra = String.valueOf(mDocumento.charAt(mIndex));

            if (letra.contentEquals("=")) {
                ret = true;
                break;
            }

            mIndex += 1;
        }


        return ret;
    }

    public boolean esperar_ate_maior() {
        boolean ret = false;


        while (mIndex < mTamanho) {
            String letra = String.valueOf(mDocumento.charAt(mIndex));

            if (letra.contentEquals(">")) {
                ret = true;
                break;
            }

            mIndex += 1;
        }


        return ret;
    }

    public String getComentario() {
        String ret = "";

        int duplo = 0;


        while (mIndex < mTamanho) {
            String letra = String.valueOf(mDocumento.charAt(mIndex));

            if (letra.contentEquals("-")) {
                duplo += 1;
                if (duplo == 2) {
                    mIndex += 1;
                    break;
                }
            }

            mIndex += 1;
        }

        if (duplo == 2) {

            boolean deve_sair = false;

            while (mIndex < mTamanho) {
                String letra = String.valueOf(mDocumento.charAt(mIndex));

                if (letra.contentEquals("-")) {
                    int proxima = mIndex + 1;
                    if (proxima < mTamanho) {
                        String proxima_letra = String.valueOf(mDocumento.charAt(mIndex));
                        if (proxima_letra.contentEquals("-")) {
                            deve_sair = true;
                            mIndex += 2;
                            break;

                        }
                        if (!deve_sair) {
                            ret += letra;
                        }

                    }
                } else {
                    ret += letra;
                }

                mIndex += 1;

            }

            if (deve_sair) {
                esperar_ate_maior();
            }
        }


        return ret;
    }

    public String retirar_quebra(String s) {

        while (s.endsWith(" ") || s.endsWith("\t") || s.endsWith("\n")) {
            s = s.substring(0, s.length() - 1);
        }

        return s;
    }

}
