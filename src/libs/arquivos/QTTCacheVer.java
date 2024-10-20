package libs.arquivos;

import libs.arquivos.binario.Arquivador;
import libs.azzal.geometria.Ponto;
import libs.azzal.geometria.Retangulo;
import libs.luan.Lista;
import libs.luan.Par;
import libs.luan.fmt;

public class QTTCacheVer {


    private String mArquivo="";
    private int mZonaObsersavel;

    private Lista<Par<Ponto,Integer>> mCache;

    private int mZonaPY_Inicio = -1;
    private int mZonaPY_Fim = -1;
    private int mZonaPX_Inicio = -1;
    private int mZonaPX_Fim = -1;


    public QTTCacheVer(String eArquivo,int eZonaObservavel){

        mArquivo=eArquivo;
        mZonaObsersavel=eZonaObservavel;
        mCache = new Lista<Par<Ponto,Integer> >();

    }


    public int get(int px,int py){

        int valor_retornar = -1;


        if (px>=mZonaPX_Inicio && px<=mZonaPX_Fim && py>=mZonaPY_Inicio && py<=mZonaPY_Fim){

            for(Par<Ponto,Integer> item : mCache){
                if(item.getChave().getX()==px && item.getChave().getY()==py){
                    valor_retornar=item.getValor();
                    break;
                }
            }

        }else{


          //  fmt.print("Montar cache :: {} - {}",px,py);

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



          int  px_inicio = px-mZonaObsersavel;
          int  px_fim = px+mZonaObsersavel;

           int py_inicio = py-mZonaObsersavel;
          int  py_fim = py+mZonaObsersavel;

          if(px_inicio<0){
              px_inicio=0;
          }

            if(py_inicio<0){
                py_inicio=0;
            }

            if(px_fim>=largura){
                px_fim=largura;
            }

            if(py_fim>=altura){
                py_fim=largura;
            }

            mZonaPX_Inicio=px_inicio;
            mZonaPX_Fim=px_fim;
            mZonaPY_Inicio=py_inicio;
            mZonaPY_Fim=py_fim;


            mCache.limpar();


            for(int y=py_inicio;y<=py_fim;y++){
                for(int x=px_inicio;x<=px_fim;x++){


                    if (x >= 0 && y >= 0 && x < largura && y < altura) {

                        int apontar = ((y * largura) + x) * 4;

                        arquivador.setPonteiro(ePonteiroInicio + apontar);
                       int valor =  arquivador.get_u32();

                        mCache.adicionar(new Par<Ponto,Integer>(new Ponto(x,y),valor));


                        if(px==x && py==y){
                            valor_retornar=valor;
                        }

                    }

                }
            }




            arquivador.encerrar();

        }

        return valor_retornar;
    }


    public Lista<Retangulo> getZonas(){

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

        Lista<Retangulo> zonas = new Lista<Retangulo>();

        int px = 0;

        while(px<largura){

            int py = 0;
            while(py<altura) {
                zonas.adicionar(new Retangulo(px,py,px+mZonaObsersavel,py+mZonaObsersavel));
                py+=mZonaObsersavel;
            }

            px+=mZonaObsersavel;
        }


        arquivador.encerrar();

        return zonas;
    }


    public Lista<Par<Ponto,Integer>> getZonaDados(Retangulo zona){

        Lista<Par<Ponto,Integer>> zona_dados = new Lista<Par<Ponto,Integer>> ();

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

        for(int y=zona.getY();y<=zona.getY2();y++){
            for(int x=zona.getX();x<=zona.getX2();x++){


                if (x >= 0 && y >= 0 && x < largura && y < altura) {

                    int apontar = ((y * largura) + x) * 4;

                    arquivador.setPonteiro(ePonteiroInicio + apontar);
                    int valor =  arquivador.get_u32();

                    zona_dados.adicionar(new Par<Ponto,Integer>(new Ponto(x,y),valor));


                }

            }
        }

        arquivador.encerrar();
        return zona_dados;
    }

}
