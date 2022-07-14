#### AZZAL

> Sistema de Renderização de Interface Gráfica

![AZZAL](https://github.com/luandkg/Azzal/blob/master/res/azzal_01.png)

    Interface
        - Janela
        - Cena
        - AppGlobal com varias cenas e transição

    Entrada de dados
        - Mouse
        - Teclado
        
    Formas Geométricas
        - Ponto
        - Linha
        - Circulo
        - Triângulo
        - Quadrado
        - Retangulo
        
    Utilitários
        - Tempo
        - Cronometro
        - Cor
        - Luz
        - Paleta
        - Posicionador
        - Transformador de Cor


# libs.Tronarko

> Sistema de contagem temporal
> 
> O ano é chamado de TRONARKO e possui 500 Superarkos que representam os dias, dividos em 10 faixas de 50 Superarkos entitulados de Hiperarkos
>
> Cada Superarko é formado por 10 Arcos [ 0 ... 9 ] que representam as horas

![TRONARKO](https://github.com/luandkg/Azzal/blob/master/res/tronarko.png)


# Attuz

> Aplicação de construção de WorldBuilding

    - Algoritmos para MAPAS
    - Ferramentas de algoritmos IDW
    - Geração de mapas : Relevo, Temperatura, Umidade, Latitude, Longitude ...
    - Aplicativo para visualização de mapa e realizar marcações.


# libs.DKG

> Formato de texto com estilo de marcação para estruturação de dados

~~~
!Alunos :: {
    !Aluno :: {  @Turma = "A" @Nome = "BELTRANO" @Grupo = "AZUL" @Status = "PRESENTE" @Visibilidade = "SIM" }
    !Aluno :: {  @Turma = "B" @Nome = "FULANO"   @Grupo = "AZUL" @Status = "AUSENTE"  @Visibilidade = "SIM" }
}
~~~

~~~
!Configuracoes :: {
    !Objeto :: {  @Tipo = "Cadeira" @Nome = "BELTRANO" }
    !Status :: {
        !Status :: {  @Nome = "SETOR_A" @Nome = "PRONTO" }
        !Status :: {  @Nome = "SETOR_B" @Nome = "EXECUTANDO" }
    }
}
~~~

# libs.Arquivos

> Pacote com formatos de arquivados criados por mim mesmo ( hehehe )

    - IM : Formato de Imagem
    - AI : Formato de Álbum de Imagens
    - TX : Formato de Texto
    - BZ : Formato de Blocos de Textos Indexados
    - HZ : Formato de Áudio : @ESTOU_ESTUDANDO
    - VI : Formato de Vídeo : @NAO_TEM_AUDIO

    - Arquivador : Classe para abrir/escrever arquivos

    - Int8 : Mapeador de 8 bits
    - Int6 : Mapeador de 6 bits

# libs.Graficos

> Pacote ZettaBarras para desenhos de gráficos de barras e pontos.

# libs.Imaginador

> Coleção de códigos para facilitar abertura de imagens, além de aplicação de efeitos.

# libs.Luan

> Pacote com ESTRUTURAS DE DADOS

    - Vetor
    - Lista
    - Dicionário
    - Iterador
    - Par

> Algoritmos interessantes

    - fmt 
    - TTY
    - Tarefa

# Partículas

> Aplicação de renderização de partículas AREIA e LÍQUIDO.

# libs.OrganizadorQ2D

> Mapeador de regiões do espaço 2D com algoritmo para algilizar detecção de colisão de corpos.

# libs.Movimento

> Algoritmos para realizar movimento de corpos no espaço 2D de forma mais harmônica e real.

    - libs.Movimento Burro
    - libs.Movimento Linear
    - libs.Movimento Vertical
    - libs.Movimento Inteligente
