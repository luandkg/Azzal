package libs.meta_functional;

public class AcaoStringDefinida {

    private AcaoAlfa<String> mAcaoString;
    private String mArgumento;

    public AcaoStringDefinida(AcaoAlfa<String> eAcaoString, String eArgumento) {
        mAcaoString = eAcaoString;
        mArgumento = eArgumento;
    }

    public void fazer() {
        toAcaoDefinida().fazer();
    }

    public Acao toAcaoDefinida() {
        return new Acao() {
            @Override
            public void fazer() {
                mAcaoString.fazer(mArgumento);
            }
        };
    }

}
