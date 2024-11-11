package libs.aqz.utils;

import libs.armazenador.Armazenador;
import libs.armazenador.ParticaoMestre;

public class AZSequenciometro {

    // LUAN FREITAS
    // CRIADO EM : 2024 01 07

    private Armazenador mArmazenador;
    private ParticaoMestre mSequencias;
    private String mNomeSequencia;

    public AZSequenciometro(Armazenador eArmazenador, ParticaoMestre eSequencias, String eNomeSequencia) {
        mArmazenador = eArmazenador;
        mSequencias = eSequencias;
        mNomeSequencia = eNomeSequencia;
        AZSequenciador.organizar_sequencial(mSequencias, mNomeSequencia);
    }

    public void organizar() {
        AZSequenciador.organizar_sequencial(mSequencias, mNomeSequencia);
    }

    public int getSequencial() {
        return AZSequenciador.aumentar_sequencial(mSequencias, mNomeSequencia);
    }
}
