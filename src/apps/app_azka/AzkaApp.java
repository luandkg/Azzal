package apps.app_azka;

import apps.app_letrum.Fonte;
import apps.app_letrum.Maker.FonteRunTime;
import libs.arquivos.IM;
import libs.arquivos.binario.Arquivador;
import libs.arquivos.ds.DS;
import libs.arquivos.ds.DSItem;
import libs.asset.Pasta;
import libs.azzal.AzzalUnico;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.Windows;
import libs.azzal.cenarios.Cena;
import libs.azzal.geometria.Retangulo;
import libs.azzal.utilitarios.Cor;
import libs.fs.PastaFS;
import libs.imagem.Imagem;
import libs.luan.Lista;
import libs.luan.fmt;

import java.awt.image.BufferedImage;

public class AzkaApp extends Cena {

    public static void INICIAR() {

        AzzalUnico.unico("Azka - Leitor de Mangás :: luandkg@gmail.com", 1900, 900, new AzkaApp());

    }


    private final String PASTA = "/home/luan/Imagens/jujutsu_kaizen/";
    private Lista<Manga> mMangas;
    private Cores mCores;
    private Fonte mEscritor;
    private Fonte mEscritorVerde;

    private int mContador = 0;
    private int a = 0;
    private Cor mCorFundo;

    private boolean mArquivoAberto=false;
    private String mArquivoCorrente="";

    private Lista<String> mPaginas = new Lista<String>();
    private int mPaginaCorrenteID = 0;
   private BufferedImage mPaginaCorrente = null;
   private boolean mPaginaCarregada=false;
    private Lista<Retangulo> mMangasAreas;

    @Override
    public void iniciar(Windows eWindows) {

        mCorFundo = Cor.getHexCor("#37474F");


        mMangasAreas= new Lista<Retangulo>();


        mCores = new Cores();
        mEscritor = new FonteRunTime(Cor.getHexCor("#EEEEEE"), 15);
        mEscritorVerde = new FonteRunTime(Cor.getHexCor("#4CAF50"), 15);
        mMangas = new Lista<Manga>();


        for (PastaFS arquivo : new PastaFS(PASTA).getArquivosComExtensao(".hq")) {
            mMangas.adicionar(new Manga(arquivo.getLocal()));
            fmt.print("{}", arquivo.getLocal());
        }


        Manga.ORDENAR_POR_NUMERO(mMangas);

    }

    @Override
    public void update(double dt) {

        if (mContador == 0) {

            for (Manga manga : mMangas) {
                if (!manga.isAberto()) {
                    manga.abrir();
                    fmt.print(">> Abrindo :: {}", manga.getArquivo());
                    fmt.print("A = {}", a);
                    break;
                }
            }

        }

        mContador += 1;
        if (mContador == 80) {
            mContador = 0;
            a += 1;
        }



        if (getWindows().getMouse().isClicked()){

            int i =0;
            int mx = getWindows().getMouse().getX();
            int my = getWindows().getMouse().getY();

            String arquivo_novo_selecionado = "";

            for(Retangulo rect : mMangasAreas){
                if(rect.isDentro(mx,my)){
                    arquivo_novo_selecionado=mMangas.get(i).getArquivo();
                    break;
                }
                i+=1;
            }

            if(!arquivo_novo_selecionado.contentEquals(mArquivoCorrente)){
                mArquivoCorrente =arquivo_novo_selecionado;
                mPaginas.limpar();

                mPaginaCorrenteID=0;
                mPaginaCarregada=false;

                for(DSItem item : DS.ler_todos(mArquivoCorrente)){

                    if(item.getNome().endsWith(".im")){
                        mPaginas.adicionar(item.getNome());
                    }

                }

                if(mPaginas.getQuantidade()>0){
                    mPaginaCorrente = Imagem.GET_IMAGEM(DS.buscar_item(mArquivoCorrente,mPaginas.get(mPaginaCorrenteID)).get().getBytes());
                    mPaginaCarregada=true;
                }


            }


            getWindows().getMouse().liberar();
        }

    }

    @Override
    public void draw(Renderizador g) {

        g.limpar(mCorFundo);

        mEscritor.setRenderizador(g);
        mEscritorVerde.setRenderizador(g);

        int px = 50;
        int py = 100;

        mMangasAreas.limpar();

        int contador = 0;
        for (Manga manga : mMangas) {

            mMangasAreas.adicionar(new Retangulo(px,py,150,200));

            g.drawRect_Pintado(px, py, 150, 200, mCores.getPreto());


            if (manga.getPaginas() > 0) {
                mEscritorVerde.escreva(px, py + 210, manga.getArquivoNome());
                mEscritorVerde.escreva(px + 150 - mEscritor.getLarguraDe(String.valueOf(manga.getPaginas())), py + 210, manga.getPaginas());

                if (manga.temCapa()){

               g.drawImagem(px,py, Imagem.redimensionador(manga.getCapa(),150,200) );

                }

            } else {
                mEscritor.escreva(px, py + 210, manga.getArquivoNome());
            }

            px += 180;
            contador += 1;
            if (contador == 5) {
                contador = 0;
                px = 50;
                py += 270;
            }
        }



        mEscritorVerde.escreva(1000, 50,mArquivoCorrente);
        mEscritorVerde.escreva(1800, 50,(mPaginaCorrenteID+1) + " de " + mPaginas.getQuantidade());

        if(mPaginaCarregada){
            g.drawImagem(1000,100,mPaginaCorrente);
        }

    }
}
