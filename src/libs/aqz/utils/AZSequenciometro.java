package libs.aqz.utils;

import libs.armazenador.Armazenador;
import libs.armazenador.Banco;

public class AZSequenciometro {

    // LUAN FREITAS
    // CRIADO EM : 2024 01 07

    private Armazenador mArmazenador;
    private Banco mSequencias;
    private String mNomeSequencia;

    public AZSequenciometro(Armazenador eArmazenador,String eNomeSequencia) {
        mArmazenador =eArmazenador;
        mNomeSequencia=eNomeSequencia;
        mSequencias = OrquestradorBancario.organizar_banco(mArmazenador, "@Sequencias");
        Sequenciador.organizar_sequencial(mSequencias, mNomeSequencia);
    }

    public void organizar(){
        Sequenciador.organizar_sequencial(mSequencias, mNomeSequencia);
    }

    public int getSequencial(){
       return Sequenciador.aumentar_sequencial(mSequencias, mNomeSequencia);
    }
}
