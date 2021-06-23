import java.awt.*;

/**
 Esta classe representa um placar no jogo. A classe princial do jogo (Pong)
 instancia dois objeto deste tipo, cada um responsável por gerenciar a pontuação
 de um player, quando a execução é iniciada.
 */

public class Score {

    /**
     Construtor da classe Score.

     @param playerId uma string que identifica o player ao qual este placar está associado.
     */
    String playerId;
    int pontuacao = 0;
    int aligh;

    public Score(String playerId){
        this.playerId = playerId;
        if (playerId.equals("Player 1"))
            aligh = 0;
        else  aligh = 1;
    }

    /**
     Método de desenho do placar.
     */

    public void draw(){
        if(playerId == "Player 1")
            GameLib.setColor(Color.GREEN);
        else GameLib.setColor(Color.BLUE);
        GameLib.drawText(this.playerId+": "+this.pontuacao,70, this.aligh);
    }

    /**
     Método que incrementa em 1 unidade a contagem de pontos.
     */

    public void inc(){
        this.pontuacao++;
    }

    /**
     Método que devolve a contagem de pontos mantida pelo placar.

     @return o valor inteiro referente ao total de pontos.
     */

    public int getScore(){

        return this.pontuacao;
    }
}
