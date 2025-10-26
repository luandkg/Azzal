package apps.app_tozterum.stravamentos;

import libs.entt.Entidade;
import libs.luan.Lista;

public class StravaAtividadesClassificadas {

    private Lista<Entidade> mAcademia;
    private Lista<Entidade> mCorrida;
    private Lista<Entidade> mAquaticos;

    public StravaAtividadesClassificadas() {
        mAcademia=new  Lista<Entidade>();
        mCorrida=new  Lista<Entidade>();
        mAquaticos=new  Lista<Entidade>();
    }

    public Lista<Entidade> getAcademia(){return mAcademia;}
    public Lista<Entidade> getCorrida(){return mCorrida;}
    public Lista<Entidade> getAquaticos(){return mAquaticos;}

    public void adicionar_academia(Entidade atividade){
        mAcademia.adicionar(atividade);
    }

    public void adicionar_corrida(Entidade atividade){
        mCorrida.adicionar(atividade);
    }

    public void adicionar_aquatico(Entidade atividade){
        mAquaticos.adicionar(atividade);
    }
}
