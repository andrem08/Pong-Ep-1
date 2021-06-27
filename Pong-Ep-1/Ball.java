import java.awt.*;
import java.util.Random;

/**
 Esta classe representa a bola usada no jogo. A classe princial do jogo (Pong)
 instancia um objeto deste tipo quando a execução é iniciada.
 */

public class Ball {
    Random random = new Random();

    /**
     Construtor da classe Ball. Observe que quem invoca o construtor desta classe define a velocidade da bola
     (em pixels por millisegundo), mas não define a direção deste movimento. A direção do movimento é determinada
     aleatóriamente pelo construtor.

     @param cx coordenada x da posição inicial da bola (centro do retangulo que a representa).
     @param cy coordenada y da posição inicial da bola (centro do retangulo que a representa).
     @param width largura do retangulo que representa a bola.
     @param height altura do retangulo que representa a bola.
     @param color cor da bola.
     @param speed velocidade da bola (em pixels por millisegundo).
     */
    double cx;
    double cy;
    double width;
    double height;
    Color color;
    double speed;

    double vector_x;
    double vector_y;

    public Ball(double cx, double cy, double width, double height, Color color, double speed){
        //Construindo a bola
        this.cx = cx;
        this.cy = cy;
        this.width = width;
        this.height = height;
        this.color = color;
        this.speed = speed;
     
        //Criando aleatoriamente os valores dos vetores velocidades.
        //Caso forem menores do que 0.3 refaz.
        //Vale ressaltar que os vetores velocidade não seguem a igualdade triangular
        // vector_x^2 + vector_y^2 nem sempre é igual a 1
        while (this.vector_x < 0.3 && this.vector_x > -0.3)
            this.vector_x = ((random.nextDouble() * 2) - 1)*this.speed;
        while (this.vector_y < 0.3 && this.vector_y > -0.3)
            this.vector_y = ((random.nextDouble() * 2) - 1)*this.speed;
    }

    /**
     Método chamado sempre que a bola precisa ser (re)desenhada.
     */

    public void draw(){

        GameLib.setColor(this.color);
        GameLib.fillRect(this.cx, this.cy, this.width, this.height);
    }

    /**
     Método chamado quando o estado (posição) da bola precisa ser atualizado.

     @param delta quantidade de millisegundos que se passou entre o ciclo anterior de atualização do jogo e o atual.
     */

    public void update(long delta){
        for (long i = 0; i < delta; i++) {
            //Tanto cx quanto cy se imcrementam em delta (tempo)
            cx = cx + vector_x;
            cy = cy + vector_y;
        }
    }

    /**
     Método chamado quando detecta-se uma colisão da bola com um jogador.

     @param playerId uma string cujo conteúdo identifica um dos jogadores.
     */

    public void onPlayerCollision(String playerId){
        //Se encontrar com um jogador, o vetor vector_x inverte
        vector_x = -vector_x;
    }

    /**
     Método chamado quando detecta-se uma colisão da bola com uma parede.

     @param wallId uma string cujo conteúdo identifica uma das paredes da quadra.
     */

    public void onWallCollision(String wallId) {
        //Se encontrar com uma parede, dependendo da parede alterna os valores de vector_x ou vertor_y
        switch (wallId) {
            case "Left":
                vector_x = Math.abs(vector_x);
                break;
            case "Right":
                vector_x = -Math.abs(vector_x);
                break;
            case "Top":
                vector_y = Math.abs(vector_y);
                break;
            case "Bottom":
                vector_y = -Math.abs(vector_y);
                break;
        }
    }
    /**
     Método que verifica se houve colisão da bola com uma parede.

     @param wall referência para uma instância de Wall contra a qual será verificada a ocorrência de colisão da bola.
     @return um valor booleano que indica a ocorrência (true) ou não (false) de colisão.
     */

    public boolean checkCollision(Wall wall) {
        //Identifica qual parede é, e verifica se passou do hitbox da parede
        //Se sim, então retorna true, caso contrario false
        switch (wall.getId()){
            case "Left":
                if (cx - width/2 < wall.getCx() + wall.getWidth()/2)
                    return true;
                break;

            case "Right":
                if (cx + width/2 > wall.getCx() - wall.getWidth()/2)
                    return true;
                break;
            case "Top":
                if (cy - height/2 < wall.getCy() + wall.getHeight()/2)
                    return true;
                break;
            case "Bottom":
                if (cy + height/2 > wall.getCy() - wall.getHeight()/2)
                    return true;
                break;
        }
        return false;
    }

    /**
     Método que verifica se houve colisão da bola com um jogador.

     @param player referência para uma instância de Player contra o qual será verificada a ocorrência de colisão da bola.
     @return um valor booleano que indica a ocorrência (true) ou não (false) de colisão.
     */

    public boolean checkCollision(Player player){
        //Identifica qual jogador é, da esquerda ou da direita, e verifica se passou do hitbox do jogador
        //Se sim, então retorna true, caso contrario false
        if (!(cy - height/2 < player.cy + player.height/2 && cy + height/2 > player.cy - player.height/2))
            return false;
        switch (player.getId()) {
            case "Player 1":
                if (cx - width/2 < player.cx + player.width/2 && player.cx - player.width/2 < cx + width/2)
                    return true;
            case "Player 2":
                if (cx + width/2 > player.cx - player.width/2 && player.cx + player.width/2 > cx - width/2)
                    return true;
        }
        return false;
    }

    /**
     Método que devolve a coordenada x do centro do retângulo que representa a bola.
     @return o valor double da coordenada x.
     */

    public double getCx(){

        return this.cx;
    }

    /**
     Método que devolve a coordenada y do centro do retângulo que representa a bola.
     @return o valor double da coordenada y.
     */

    public double getCy(){

        return this.cy;
    }

    /**
     Método que devolve a velocidade da bola.
     @return o valor double da velocidade.

     */

    public double getSpeed(){

        return this.speed;
    }

}
