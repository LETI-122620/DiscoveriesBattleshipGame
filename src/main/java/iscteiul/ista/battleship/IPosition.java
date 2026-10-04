/**
 *
 */
package iscteiul.ista.battleship;

/**
 * Interface que define uma posição (célula) do tabuleiro do jogo da batalha
 * naval.
 * <p>
 * Uma posição é identificada pela sua linha e coluna, e sabe se está ocupada
 * por um navio e se já foi alvo de um tiro.
 * </p>
 *
 * @author fba
 */
public interface IPosition {

    /**
     * Devolve a linha da posição.
     *
     * @return o número da linha
     */
    int getRow();

    /**
     * Devolve a coluna da posição.
     *
     * @return o número da coluna
     */
    int getColumn();

    /**
     * Compara esta posição com outro objeto. Duas posições são consideradas iguais
     * se tiverem a mesma linha e a mesma coluna.
     *
     * @param other o objeto a comparar
     * @return {@code true} se o objeto for uma posição com a mesma linha e coluna;
     *         {@code false} caso contrário
     */
    boolean equals(Object other);

    /**
     * Verifica se esta posição é adjacente a outra, isto é, se a distância entre
     * ambas, em linhas e em colunas, é no máximo 1 (incluindo diagonais e a própria
     * posição).
     *
     * @param other a posição com a qual se pretende comparar
     * @return {@code true} se as posições forem adjacentes; {@code false} caso
     *         contrário
     */
    boolean isAdjacentTo(IPosition other);

    /**
     * Marca a posição como ocupada por um navio.
     */
    void occupy();

    /**
     * Regista um tiro nesta posição, marcando-a como atingida.
     */
    void shoot();

    /**
     * Indica se a posição está ocupada por um navio.
     *
     * @return {@code true} se a posição estiver ocupada; {@code false} caso
     *         contrário
     */
    boolean isOccupied();

    /**
     * Indica se a posição já foi atingida por um tiro.
     *
     * @return {@code true} se a posição tiver sido atingida; {@code false} caso
     *         contrário
     */
    boolean isHit();
}
