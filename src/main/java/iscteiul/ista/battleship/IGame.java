/**
 *
 */
package iscteiul.ista.battleship;

import java.util.List;

/**
 * Interface que define os comportamentos e operações fundamentais do jogo Batalha Naval.
 * Permite a gestão de disparos, consulta de estatísticas da partida e visualização dos tabuleiros.
 */
public interface IGame {

    /**
     * Executa um disparo sobre a grelha numa determinada posição.
     * 
     * @param pos a posição (coordenadas) onde o disparo é efetuado
     * @return o navio afundado se o tiro resultar no afundamento de uma embarcação, ou null caso contrário
     */
    IShip fire(IPosition pos);

    /**
     * Obtém a lista de todas as posições onde foram efetuados disparos válidos.
     * 
     * @return lista de posições dos disparos efetuados
     */
    List<IPosition> getShots();

    /**
     * Obtém o número total de disparos repetidos efetuados durante a partida.
     * 
     * @return contagem de disparos repetidos
     */
    int getRepeatedShots();

    /**
     * Obtém o número total de disparos inválidos (fora dos limites da grelha).
     * 
     * @return contagem de disparos inválidos
     */
    int getInvalidShots();

    /**
     * Obtém o número total de disparos que acertaram em navios.
     * 
     * @return contagem de acertos
     */
    int getHits();

    /**
     * Obtém o número total de navios da frota que já foram totalmente afundados.
     * 
     * @return contagem de navios afundados
     */
    int getSunkShips();

    /**
     * Obtém o número de navios da frota que ainda se encontram a flutuar.
     * 
     * @return número de navios restantes em jogo
     */
    int getRemainingShips();

    /**
     * Imprime no consola o tabuleiro de jogo contendo o registo dos disparos válidos efetuados.
     */
    void printValidShots();

    /**
     * Imprime no consola o tabuleiro de jogo com a localização de toda a frota de navios.
     */
    void printFleet();
}
