/**
 * 
 */
package iscteiul.ista.battleship;

/**
 * Representa uma Caravela na versão do jogo Batalha Naval dos Descobrimentos.
 * Ocupa um tamanho fixo de 2 quadrados no tabuleiro.
 */
public class Caravel extends Ship {
    private static final Integer SIZE = 2;
    private static final String NAME = "Caravela";

    /**
     * Constrói uma nova caravela com a orientação e posição inicial especificadas.
     * 
     * @param bearing a direção para onde a caravela aponta
     * @param pos a posição inicial de colocação na grelha
     * @throws NullPointerException se a orientação fornecida for nula
     * @throws IllegalArgumentException se a orientação fornecida for inválida
     */
    public Caravel(Compass bearing, IPosition pos) throws NullPointerException, IllegalArgumentException {
        super(Caravel.NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("ERROR! invalid bearing for the caravel");

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
                throw new IllegalArgumentException("ERROR! invalid bearing for the caravel");
        }

    }

    /*
     * (non-Javadoc)
     *
     * @see battleship.Ship#getSize()
     */
    /**
     * Devolve o tamanho da caravela.
     * 
     * @return o tamanho correspondente a 2 quadrados
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }

}
