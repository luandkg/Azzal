package libs.arquivos.ds;

import libs.arquivos.TX;
import libs.arquivos.binario.Arquivador;
import libs.arquivos.binario.Inteiro;
import libs.luan.Lista;
import libs.luan.fmt;

public class DSGigantesco {


    private String mArquivo;
    private long mPonteiroLendo;


    public DSGigantesco(String eArquivo){

        mArquivo=eArquivo;
        mPonteiroLendo=0;

    }

    public boolean tem(){

        Arquivador arquivar = new Arquivador(mArquivo);
        arquivar.setPonteiro(mPonteiroLendo);

        boolean ret = arquivar.getPonteiro() < arquivar.getLength();
        arquivar.encerrar();

        return ret;

    }

    public  Lista<DSItem> getItens(int eQuantidadeBuscar ) {

        Lista<DSItem> itens = new Lista<DSItem>();

        Arquivador arquivar = new Arquivador(mArquivo);

        // System.out.println("Tamanho :: " + arquivar.getLength());

        arquivar.setPonteiro(mPonteiroLendo);

        if(mPonteiroLendo==0){
            int b1 = Inteiro.byteToInt(arquivar.get());
            int b2 = Inteiro.byteToInt(arquivar.get());
        }


        //  System.out.println("B1 :: " + b1);
        // System.out.println("B2 :: " + b2);

        int status = Inteiro.byteToInt(arquivar.get());

        fmt.print("Onde :: {} -->> {}",mPonteiroLendo,status);

        long t = arquivar.getLength();

        int index = 0;

        while ((status == 255 || status == 111) && arquivar.getPonteiro() < t) {

            long item_tamanho = arquivar.get_u64();


            long comecar_nome = arquivar.getPonteiro();

            TX eTX = new TX();
            String nome = eTX.lerFluxoLimitado(arquivar, 1024);

            arquivar.setPonteiro(comecar_nome + 1024);

            long ponteiro_dados = arquivar.getPonteiro();

            itens.adicionar(new DSItem(mArquivo, index, status, nome, item_tamanho, ponteiro_dados));

            arquivar.setPonteiro(arquivar.getPonteiro() + item_tamanho);
            mPonteiroLendo=arquivar.getPonteiro() + item_tamanho-1;

            fmt.print("\t Ah :: {}",mPonteiroLendo);

            if(itens.getQuantidade()>=eQuantidadeBuscar){
                break;
            }

            status = Inteiro.byteToInt(arquivar.get());

            index += 1;

        }

        arquivar.encerrar();

        return itens;
    }


}
