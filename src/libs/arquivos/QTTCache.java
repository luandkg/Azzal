package libs.arquivos;

import libs.arquivos.binario.Arquivador;
import libs.azzal.geometria.Ponto;
import libs.luan.Lista;
import libs.luan.Par;

public class QTTCache {

    private String mArquivo="";
    private int mCacheLimite;

    private Lista<Par<Ponto,Integer> >mCache;

    public QTTCache(String eArquivo,int eCacheLimite){

        mArquivo=eArquivo;
        mCacheLimite=eCacheLimite;
        mCache = new Lista<Par<Ponto,Integer> >();


    }

    public void cache(int x,int y,int valor){
        mCache.adicionar(new Par<Ponto,Integer>(new Ponto(x,y),valor));

        if(mCache.getQuantidade()>=mCacheLimite){
            guardar();
        }

    }

    public void guardar(){


        Arquivador arquivador = new Arquivador(mArquivo, "rw");
        arquivador.setPonteiro(0);

        byte p1 = arquivador.get();
        byte p2 = arquivador.get();
        byte p3 = arquivador.get();
        byte p4 = arquivador.get();
        byte p5 = arquivador.get();

        byte z1 = arquivador.get();

        int largura = arquivador.get_u32();
        int altura = arquivador.get_u32();

        byte z2 = arquivador.get();

        long ePonteiroInicio = arquivador.getPonteiro();

        for(Par<Ponto,Integer> item : mCache){

            int x = item.getChave().getX();
            int y = item.getChave().getY();


            if (x >= 0 && y >= 0 && x < largura && y < altura) {

                int apontar = ((y * largura) + x) * 4;

                arquivador.setPonteiro(ePonteiroInicio + apontar);
                arquivador.set_u32(item.getValor());

            }

        }

        arquivador.encerrar();

        mCache.limpar();
    }


}
