package apps.app_tozterum.treinamento;

import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.RefString;

public class TreinamentoRetornoQ4 {


    private static void EXERCICIO_GRUPO_DEFINIR(String eGrupoMuscular, RefString refGrupoMuscular) {
        refGrupoMuscular.set(eGrupoMuscular);
    }

    private static Exercicio EXERCICIO_CRIAR(Lista<Entidade> exercicios, int sequencial, RefString refGrupoMuscular) {
        Entidade exercicio_dados = ENTT.GET_SEMPRE(exercicios, "Exercicio", sequencial);
        exercicio_dados.at("GrupoMuscular", refGrupoMuscular.get());
        return new Exercicio(exercicio_dados);
    }

    public static Lista<Entidade> treino_1(Lista<Entidade> cargas_de_treinos) {

        // SEGUNDA e QUINTA


        Entidade carga_de_treino = PlanejamentoDeCargaDeTreino.GET_CARGA_DE_TREINO_CORRENTE(cargas_de_treinos);

        int treino_series = PlanejamentoDeCargaDeTreino.TREINO_SERIES(carga_de_treino);
        int treino_repeticoes = PlanejamentoDeCargaDeTreino.TREINO_REPETICOES(carga_de_treino);


        int abdominal_series = PlanejamentoDeCargaDeTreino.ABDOMINAL_SERIES(carga_de_treino);
        int abdominal_repeticoes = PlanejamentoDeCargaDeTreino.ADBOMINAL_REPETICOES(carga_de_treino);

        RefString GrupoMuscularCorrente = new RefString();


        Lista<Entidade> treino = ENTT.CRIAR_LISTA();

        // AQUECIMENTO
        EXERCICIO_CRIAR(treino, 1, GrupoMuscularCorrente).nome("ESTEIRA").tempo(Fisica.MIN(20)).tipo("AQUECIMENTO");

        EXERCICIO_GRUPO_DEFINIR("PEITO", GrupoMuscularCorrente);
        EXERCICIO_CRIAR(treino, 2, GrupoMuscularCorrente).nome("SUPINO RETO COM BARRA").repeticao(treino_series, treino_repeticoes).carga(Fisica.KG(30)).obs("Última até a FALHA !").tipo("PEITO");
        EXERCICIO_CRIAR(treino, 3, GrupoMuscularCorrente).nome("SUPINO INCLINADO COM BARRA").repeticao(treino_series, treino_repeticoes).carga(Fisica.KG(20)).tipo("COSTAS").tipo("PEITO");
        EXERCICIO_CRIAR(treino, 4, GrupoMuscularCorrente).nome("SUPINO DECLINADO COM BARRA").repeticao(treino_series, treino_repeticoes).carga(Fisica.KG(20)).tipo("COSTAS").tipo("PEITO");
        EXERCICIO_CRIAR(treino, 5, GrupoMuscularCorrente).nome("DESENVOLVIMENTO COM HALTERES").repeticao(treino_series, treino_repeticoes).carga(Fisica.KG(10)).tipo("PEITO");
        EXERCICIO_CRIAR(treino, 6, GrupoMuscularCorrente).nome("CRUCIFIXO DEITADO RETO COM HALTERES").repeticao(treino_series, treino_repeticoes).carga(Fisica.KG(8)).tipo("PEITO");

        // Ombros
        EXERCICIO_CRIAR(treino, 7, GrupoMuscularCorrente).nome("ELEVAÇÃO LATERAL COM HALTER").repeticao(treino_series, treino_repeticoes).carga(Fisica.KG(6)).tipo("OMBROS");
        EXERCICIO_CRIAR(treino, 8, GrupoMuscularCorrente).nome("ELEVAÇÃO FRONTAL COM HALTER").repeticao(treino_series, treino_repeticoes).carga(Fisica.KG(6)).tipo("OMBROS");

        // ABDOMINAL
        EXERCICIO_CRIAR(treino, 9, GrupoMuscularCorrente).nome("ABDOMINAL DEITADO").repeticao(abdominal_series, abdominal_repeticoes).tipo("ABDOMINAL");
        EXERCICIO_CRIAR(treino, 10, GrupoMuscularCorrente).nome("ABDOMINAL TOTAL").repeticao(abdominal_series, abdominal_repeticoes).tipo("ABDOMINAL");

        // FLEXAO
        EXERCICIO_CRIAR(treino, 11, GrupoMuscularCorrente).nome("FLEXÃO NO CHÃO").repeticao(abdominal_series, abdominal_repeticoes).tipo("FLEXAO");

        return treino;
    }

    public static Lista<Entidade> treino_2(Lista<Entidade> cargas_de_treinos) {

        // TERÇA e SEXTA
        RefString GrupoMuscularCorrente = new RefString();

        Lista<Entidade> treino = ENTT.CRIAR_LISTA();

        // AQUECIMENTO
        EXERCICIO_GRUPO_DEFINIR("CORPO", GrupoMuscularCorrente);
        EXERCICIO_CRIAR(treino, 1, GrupoMuscularCorrente).nome("ESTEIRA").tempo(Fisica.MIN(20)).tipo("AQUECIMENTO");

        // COSTAS
        EXERCICIO_GRUPO_DEFINIR("COSTAS", GrupoMuscularCorrente);
        EXERCICIO_CRIAR(treino, 2, GrupoMuscularCorrente).nome("PUXADA ABERTA BARRA FRONTAL").repeticao(3, 12).carga(Fisica.KG(10)).obs("Última até a FALHA !").tipo("COSTAS");
        EXERCICIO_CRIAR(treino, 3, GrupoMuscularCorrente).nome("PUXADA TRIÂNGULO FRONTAL").repeticao(3, 12).carga(Fisica.KG(10)).obs("Última até a FALHA !").tipo("COSTAS");
        EXERCICIO_CRIAR(treino, 4, GrupoMuscularCorrente).nome("PUXADA FECHADA BARRA").repeticao(3, 12).carga(Fisica.KG(10)).tipo("COSTAS");
        EXERCICIO_CRIAR(treino, 5, GrupoMuscularCorrente).nome("REMADA NA MÁQUINA").repeticao(3, 12).carga(Fisica.KG(20)).tipo("COSTAS");

        // BICEPS
        EXERCICIO_GRUPO_DEFINIR("BICEPS", GrupoMuscularCorrente);
        EXERCICIO_CRIAR(treino, 6, GrupoMuscularCorrente).nome("ROSCA SCOTT").repeticao(3, 12).carga(Fisica.KG(10)).tipo("BICEPS");
        EXERCICIO_CRIAR(treino, 7, GrupoMuscularCorrente).nome("ROSCA CONCENTRADA").repeticao(3, 12).carga(Fisica.KG(10)).tipo("BICEPS");
        EXERCICIO_CRIAR(treino, 8, GrupoMuscularCorrente).nome("ROSCA INVERSA").repeticao(3, 12).carga(Fisica.KG(10)).tipo("BICEPS");

        // TRICEPS
        EXERCICIO_GRUPO_DEFINIR("TRICEPS", GrupoMuscularCorrente);
        EXERCICIO_CRIAR(treino, 9, GrupoMuscularCorrente).nome("TRÍCEPS NA POLIA").repeticao(3, 12).carga(Fisica.KG(15)).tipo("TRICEPS");
        EXERCICIO_CRIAR(treino, 10, GrupoMuscularCorrente).nome("TRÍCEPS NA POLIA INVERSO").repeticao(3, 12).carga(Fisica.KG(10)).tipo("TRICEPS");
        EXERCICIO_CRIAR(treino, 11, GrupoMuscularCorrente).nome("TRÍCEPS CORDA").repeticao(3, 12).carga(Fisica.KG(10)).tipo("TRICEPS");


        // ABDOMINAL
        EXERCICIO_GRUPO_DEFINIR("ADBOMEM", GrupoMuscularCorrente);
        EXERCICIO_CRIAR(treino, 12, GrupoMuscularCorrente).nome("ABDOMINAL SPRINTER").repeticao(3, 15).tipo("ABDOMINAL");
        EXERCICIO_CRIAR(treino, 13, GrupoMuscularCorrente).nome("ABDOMINAL TOTAL").repeticao(3, 15).tipo("ABDOMINAL");

        // FLEXAO
        EXERCICIO_GRUPO_DEFINIR("PEITORAL / TRICEPS", GrupoMuscularCorrente);
        EXERCICIO_CRIAR(treino, 14, GrupoMuscularCorrente).nome("FLEXÃO NO CHÃO").repeticao(3, 15).tipo("FLEXAO");

        return treino;
    }

    public static Lista<Entidade> treino_3(Lista<Entidade> cargas_de_treinos) {

        // QUARTA
        RefString GrupoMuscularCorrente = new RefString();

        Lista<Entidade> treino = ENTT.CRIAR_LISTA();

        // AQUECIMENTO
        EXERCICIO_GRUPO_DEFINIR("CORPO", GrupoMuscularCorrente);
        EXERCICIO_CRIAR(treino, 1, GrupoMuscularCorrente).nome("ESTEIRA").tempo(Fisica.MIN(20)).tipo("AQUECIMENTO");

        // PERNA
        EXERCICIO_GRUPO_DEFINIR("PERNA", GrupoMuscularCorrente);
        EXERCICIO_CRIAR(treino, 2, GrupoMuscularCorrente).nome("AGACHAMENTO COM HALTER UNILATERAL").repeticao(3, 12).carga(Fisica.KG(10)).tipo("PERNA");
        EXERCICIO_CRIAR(treino, 3, GrupoMuscularCorrente).nome("HACK 45").repeticao(3, 12).carga(Fisica.KG(10)).tipo("PERNA");
        EXERCICIO_CRIAR(treino, 4, GrupoMuscularCorrente).nome("ADUTORA").repeticao(3, 12).carga(Fisica.KG(10)).tipo("PERNA");
        EXERCICIO_CRIAR(treino, 5, GrupoMuscularCorrente).nome("ABDUTORA").repeticao(3, 12).carga(Fisica.KG(10)).tipo("PERNA");
        EXERCICIO_CRIAR(treino, 6, GrupoMuscularCorrente).nome("EXTENSORA").repeticao(3, 12).carga(Fisica.KG(10)).tipo("PERNA");
        EXERCICIO_CRIAR(treino, 7, GrupoMuscularCorrente).nome("MEXA FLEXORA").repeticao(3, 12).carga(Fisica.KG(10)).tipo("PERNA");
        EXERCICIO_CRIAR(treino, 8, GrupoMuscularCorrente).nome("PANTURRILHA EM PÉ").repeticao(3, 12).carga(Fisica.KG(10)).tipo("PERNA");

        // ABDOMINAL
        EXERCICIO_GRUPO_DEFINIR("ADBOMEM", GrupoMuscularCorrente);
        EXERCICIO_CRIAR(treino, 9, GrupoMuscularCorrente).nome("ABDOMINAL SPRINTER").repeticao(3, 15).tipo("ABDOMINAL");
        EXERCICIO_CRIAR(treino, 10, GrupoMuscularCorrente).nome("ABDOMINAL TOTAL").repeticao(3, 15).tipo("ABDOMINAL");

        // FLEXAO
        EXERCICIO_GRUPO_DEFINIR("PEITORAL / TRICEPS", GrupoMuscularCorrente);
        EXERCICIO_CRIAR(treino, 11, GrupoMuscularCorrente).nome("FLEXÃO NO CHÃO").repeticao(3, 15).tipo("FLEXAO");

        return treino;
    }

}
