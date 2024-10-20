package apps.app;

import libs.luan.Lista;
import libs.luan.Trio;
import libs.meta_functional.Acao;

public class AgendadorDeTarefas {

    public String mTarefaCorrente;

    private Lista<Trio<String,String, Acao>> mTarefas;


    public AgendadorDeTarefas(){
        mTarefaCorrente="";
        mTarefas=new Lista<Trio<String,String, Acao>>();
    }

    public void executeUma(){

        for(Trio<String,String, Acao> tarefa : mTarefas){
            if(tarefa.getChave().contentEquals(mTarefaCorrente)){

                tarefa.getValor2().fazer();
                mTarefaCorrente=tarefa.getValor1();

                break;
            }
        }


    }

    public void setTarefaCorrente(String eCorrente){
        mTarefaCorrente=eCorrente;
    }

    public String getTarefaCorrente(){
        return mTarefaCorrente;
    }


    public void criarSequencia(String eCorrente,String eProxima,Acao eAcao){
        mTarefas.adicionar(new Trio<String,String, Acao>(eCorrente,eProxima,eAcao));
    }

    public void criarSequenciaDupla(String eCorrenteA,String eCorrenteB,String eProxima,Acao eAcao){
        mTarefas.adicionar(new Trio<String,String, Acao>(eCorrenteA,eProxima,eAcao));
        mTarefas.adicionar(new Trio<String,String, Acao>(eCorrenteB,eProxima,eAcao));
    }

}
