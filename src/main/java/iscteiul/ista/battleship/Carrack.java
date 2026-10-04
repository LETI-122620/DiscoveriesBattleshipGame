/**
 *
 */
package iscteiul.ista.battleship;

/**
 * Representa uma nau, um navio de tamanho 3 do jogo da batalha naval.
 * <p>
 * As posições ocupadas pela nau são calculadas no construtor a partir da
 * posição inicial e da orientação (bearing) indicadas.
 * </p>
 *
 * @see Ship
 * @see Compass
 * @see IPosition
 */
public class Carrack extends Ship {

    /** Número de posições ocupadas por uma nau. */
    private static final Integer SIZE = 3;

    /** Nome da categoria deste navio. */
    private static final String NAME = "Nau";

    /**
     * Constrói uma nau com a orientação e posição inicial indicadas.
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
    public Carrack(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Carrack.NAME, bearing, pos);
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
                throw new IllegalArgumentException("ERROR! invalid bearing for the carrack");
        }
    }

    /**
     * Devolve o tamanho da nau.
     *
     * @return o número de posições ocupadas pela nau (3)
     * @see Ship#getSize()
     */
    @Override
    public Integer getSize() {
        return Carrack.SIZE;
    }

}
