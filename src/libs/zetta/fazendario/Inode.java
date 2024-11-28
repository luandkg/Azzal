package libs.zetta.fazendario;

public class Inode {

    public int bloco_status_aqui ;
    public long ponteiro_eu_mesmo ;
    public long ponteiro_dados_aqui ;

    public Inode(int eStatus,long eEuMesmo,long ePonteiroDados) {
        bloco_status_aqui=eStatus;
        ponteiro_eu_mesmo=eEuMesmo;
        ponteiro_dados_aqui=ePonteiroDados;
    }
}
