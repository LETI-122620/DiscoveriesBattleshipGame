/**
 *
 */
package iscteiul.ista.battleship;

/**
 * Representa uma fragata, um navio de tamanho 4 do jogo da batalha naval.
 * <p>
 * As posições ocupadas pela fragata são calculadas no construtor a partir da
 * posição inicial e da orientação (bearing) indicadas.
 * </p>
 *
 * @see Ship
 * @see Compass
 * @see IPosition
 */
public class Frigate extends Ship {

    /** Número de posições ocupadas por uma fragata. */
    private static final Integer SIZE = 4;

    /** Nome da categoria deste navio. */
    private static final String NAME = "Fragata";

    /**
     * Constrói uma fragata com a orientação e posição inicial indicadas.
     * <ul>
     * <li>Para {@code NORTH} e {@code SOUTH}, as posições são geradas
     * incrementando a linha, mantendo a coluna.</li>
     * <li>Para {@code EAST} e {@code WEST}, as posições são geradas incrementando
     * a coluna, mantendo a linha.</li>
     * </ul>
     *
     * @param bearing a orientação do navio no tabuleiro
     * @param pos     a posição inicial do navio
     * @throws IllegalArgumentException se a orientação não for válida (por exemplo,
     *                                  {@code UNKNOWN})
     */
    public Frigate(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Frigate.NAME, bearing, pos);
        switch (bearing) {
            case NORTH:
            case SOUTH:
                for (int r = 0; r < SIZE; r++)
                    getPositions().add(new Position(pos.getRow() + r, pos.getColumn()));
                break;
            case EAST:
            case WEST:
                for (int c = 0; c < SIZE; c++)
                    getPositions().add(new Position(pos.getRow(), pos.getColumn() + c));
                break;
            default:
                throw new IllegalArgumentException("ERROR! invalid bearing for thr frigate");
        }
    }

    /**
     * Devolve o tamanho da fragata.
     *
     * @return o número de posições ocupadas pela fragata (4)
     * @see Ship#getSize()
     */
    @Override
    public Integer getSize() {
        return Frigate.SIZE;
    }

}
