package apps.AppKhronos;

import apps.AppKhronos.Sintaxes.SintaxerDKG;
import apps.AppKhronos.Sintaxes.SintaxerSigmaz;
import azzal.Cenarios.Cena;
import azzal.Cores;
import azzal.Formatos.Retangulo;
import azzal.Renderizador;
import azzal.Utils.Cor;
import azzal.Windows;
import apps.appLetrum.Fonte;
import apps.appLetrum.Maker.FonteRunTime;
import libs.OLLT.Texto;
import azzal_ui.Interface.Clicavel;


public class AppKrhonos extends Cena {


    private Cores mCores;

    private Clicavel mClicavel;

    private String mArquivo = "/home/luan/IdeaProjects/Sigmaz/res/99 - modelagem.sigmaz";
    private Fonte escuro;
    private Fonte branco_acinzentado;


    private EditorDeDocumento mEditorDeTexto;
    private EditorDeTexto mComandante;
    private Comandos mComandos;

    private boolean isEditando;


    @Override
    public void iniciar(Windows eWindows) {

        mCores = new Cores();


        branco_acinzentado = new FonteRunTime(Cores.hexToCor("#ebdbb2"), "Ubuntu",FonteRunTime.getTamanhoPequeno());
        escuro = new FonteRunTime(new Cor(0, 0, 0), "Ubuntu", FonteRunTime.getTamanhoPequeno());

        mClicavel = new Clicavel();

        mEditorDeTexto = new EditorDeDocumento(50, 50);
        mEditorDeTexto.setTexto("");
        mEditorDeTexto.editar();
        mEditorDeTexto.update();
        mEditorDeTexto.setCursorFim();


        mComandante = new EditorDeTexto(10, 800, escuro);
        mComandante.setTexto("");
        mComandante.editar();
        mComandante.update();
        mComandante.setCursorFim();
        mComandante.setMostrarCursor(false);
        mComandante.setMostrarBarraInferior(false);

        isEditando = false;
        mComandos = new Comandos();

        // txt_abrir("/home/luan/IdeaProjects/Escola/res/coisas.dkg");
        //  txt_abrir("/home/luan/IdeaProjects/Azzal/src/apps.AppAzzal/C1.java");
       // txt_abrir("/home/luan/IdeaProjects/Sigmaz/res/99 - modelagem.sigmaz");
      //  txt_abrir("/home/luan/Imagens/Simples/mares.dkg");

        txt_abrir("/home/luan/Imagens/Simples/teste.dkg");

    }


    public void txt_abrir(String eArquivo) {

        mArquivo = eArquivo;

        String doc = Texto.Ler(eArquivo);
        mEditorDeTexto.setTexto(doc);
        mEditorDeTexto.update();
        mEditorDeTexto.organizarTexto();
        mEditorDeTexto.visualizar_primeira_linha();

        mEditorDeTexto.semSintaxer();

        if (eArquivo.endsWith(".sigmaz")) {
            mEditorDeTexto.setSintaxer(new SintaxerSigmaz(mEditorDeTexto.getFonteNome(),mEditorDeTexto.getFonteTamanho()));
        } else if (eArquivo.endsWith(".dkg")) {
            mEditorDeTexto.setSintaxer(new SintaxerDKG(mEditorDeTexto.getFonteNome(),mEditorDeTexto.getFonteTamanho()));
        }

    }

    @Override
    public void update(double dt) {


        mClicavel.update(dt, getMx(), getMy(), isPressionsado());

        if (isEditando) {
            mEditorDeTexto.clicar(dt, getMx(), getMy(), isClicado());
        }

        mEditorDeTexto.mostrarCursor(isEditando);

        mEditorDeTexto.update();
        mComandante.update();


        if (isEditando) {
            mEditorDeTexto.receberTeclado(getWindows().getTeclado());

            if (mEditorDeTexto.deveSair()) {
                isEditando = false;
                mEditorDeTexto.voltar();
                getWindows().getTeclado().limpar();
            }


        } else {
            mComandante.receberTeclado(getWindows().getTeclado());

            if (mComandante.recebeuEnter()) {
                String comando = mComandante.getTexto();
                System.out.println("Comando :: " + comando);
                mComandante.setEnterizar(false);
                mComandante.limpar();


                if (comando.contentEquals("s")) {
                    mEditorDeTexto.visualizar_subir();
                } else if (comando.contentEquals("d")) {
                    mEditorDeTexto.visualizar_descer();
                } else if (comando.contentEquals("vl0")) {
                    mEditorDeTexto.visualizar_primeira_linha();
                } else if (comando.contentEquals("vln")) {
                    mEditorDeTexto.visualizar_ultima_linha();


                } else if (comando.contentEquals("sv")) {

                    Texto.Escrever(mArquivo,mEditorDeTexto.getTexto());


                } else if (comando.contentEquals("editar")) {
                    isEditando = true;
                    getWindows().getTeclado().limpar();
                }

            }

        }


        getWindows().getMouse().liberar();


    }


    @Override
    public void draw(Renderizador eRender) {

        eRender.limpar(Cores.hexToColor("#282828"));
        // eRender.limpar(mCores.getBranco());

        escuro.setRenderizador(eRender);
        branco_acinzentado.setRenderizador(eRender);

        mEditorDeTexto.render(eRender);

        mClicavel.onDraw(eRender);

        if (isEditando) {
            eRender.drawRect_Pintado(new Retangulo(10, 800, 950, 20), Cores.hexToCor("#cc241d"));
        } else {
            eRender.drawRect_Pintado(new Retangulo(10, 800, 950, 20), Cores.hexToCor("#d79921"));

            //eRender.drawRect_Pintado(new Retangulo(0, 970, 500, 20), Cores.hexToCor("#cc241d"));
            mComandante.render(eRender);

            int px = 10;
            int py = 830;

            for (Comando comando : mComandos.getSugestoes(mComandante.getTexto())) {
                branco_acinzentado.escreva(px, py, comando.getComando() + " :: " + comando.getDescricao());
                py += 30;
            }


        }

    }


}
