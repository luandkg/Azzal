#### AZZAL

> Sistema de Renderização de Interface Gráfica

![AZZAL UI](https://github.com/luandkg/Azzal/blob/master/res/app_ui.png)

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

# libs.tronarko

> Sistema de contagem temporal
>
> O ano é chamado de TRONARKO e possui 500 Superarkos que representam os dias, dividos em 10 faixas de 50 Superarkos
> entitulados de Hiperarkos
>
> Cada Superarko é formado por 10 Arcos [ 0 ... 9 ] que representam as horas

![TRONARKO](https://github.com/luandkg/Azzal/blob/master/res/app_tronarko.png)

# Attuz - Construtor de Mundos

> Aplicação de construção de WorldBuilding

    - Algoritmos para MAPAS
    - Ferramentas de algoritmos IDW
    - Geração de mapas : Relevo, Temperatura, Umidade, Latitude, Longitude ...
    - Aplicativo para visualização de mapa e realizar marcações.

# libs.dkg - Arquivo DKG

> Formato de texto com estilo de marcação para estruturação de dados, possui dois tipos básicos de dados OBJETO e ATRIBUTO.
> A estrutura é uma classe árvore que permite armazenar vários filhos do tipo OBJETO e cada objeto possui uma lista de atributos únicos.

~~~
!Alunos :: {
    !Aluno :: {  @Turma = "A" @Nome = "BELTRANO" @Grupo = "AZUL"     @Status = "PRESENTE" @Visibilidade = "SIM" }
    !Aluno :: {  @Turma = "B" @Nome = "FULANO"   @Grupo = "VERMELHO" @Status = "AUSENTE"  @Visibilidade = "SIM" }
}
~~~

~~~
!Configuracoes :: {
    !Tarefas :: {
        !Status :: {  @Nome = "Acessar" @Nome = "SIM" }
        !Status :: {  @Nome = "Publicar" @Nome = "NAO" }
        !Status :: {  @Nome = "Listar" @Nome = "SIM" }
    }
}
~~~

# libs.dg - Biblioteca de Coleções

> Banco de coleções em arquivo único
> Essa biblioteca é ideal para armazenar objetos com vários campos de forma rápida e organizada, possui sistema de indexamento por páginas, construção de coleção não existente com a necessidade.

# libs.Luan - Estruturas de Dados

> Estruturas de dados implementadas no segundo semestre do curso de ciências das computação na disciplina de Estrutura
de Dados da Universidade de Brasília

1. Vetor
2. Lista
3. Dicionário
4. Par
5. Iterador
6. String

> Algoritmos implementados 

1. Ordenador
2. Opcional
3. Integers
4. Strings

> Classes referenciáveis

1. RefInt
2. RefBool
3. RefLong
4. RefString

> fmt : Essa classe é muito top e foi desenvolvida para facilitar a saida de strings no terminal

> Tempo : Classe responsável por trabalhar com datas e horarios, ela será atualizada com outra versão que desenvolvi no projeto appEscola


# libs.arquivos - Meus formatos de arquivos

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

# libs.zettagraficos

> Pacote ZettaBarras para desenhos de gráficos de barras e pontos.

![ZETTA BARRAS](https://github.com/luandkg/Azzal/blob/master/res/app_zetta.png)

# libs.Imaginador

> Coleção de códigos para facilitar abertura de imagens, além de aplicação de efeitos.


# Partículas

> Aplicação de renderização de partículas AREIA e LÍQUIDO.

# libs.OrganizadorQ2D

> Mapeador de regiões do espaço 2D com algoritmo para algilizar detecção de colisão de corpos.

![Organizador Espacial](https://github.com/luandkg/Azzal/blob/master/res/app_oe.png)


# libs.Movimento

> Algoritmos para realizar movimento de corpos no espaço 2D de forma mais harmônica e real.

    - libs.Movimento Burro
    - libs.Movimento Linear
    - libs.Movimento Vertical
    - libs.Movimento Inteligente
