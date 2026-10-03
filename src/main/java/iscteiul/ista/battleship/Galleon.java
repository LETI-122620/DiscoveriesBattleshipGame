package iscteiul.ista.battleship;

/**
 * Representa um navio do tipo Galeao (Galleon) no jogo Discoveries Battleship[cite: 1, 49].
 * O Galeao possui dimensao 5 e ocupa posicoes especificas na grelha de acordo com a sua orientacao[cite: 1, 49].
 *
 * @author Engenharia de Software Team[cite: 1]
 * @version 1.0
 */
public class Galleon extends Ship {

    /** Dimensao fixa do Galeao na grelha de jogo[cite: 1, 49]. */
    private static final Integer SIZE = 5;

    /** Designacao da categoria do navio[cite: 49]. */
    private static final String NAME = "Galeao";

    /**
     * Constroi um novo Galeao com uma orientacao e posicao de referencia iniciais[cite: 49].
     *
     * @param bearing Orientacao cardeal do navio (Compass)[cite: 49]
     * @param pos Posicao base de referencia do navio na grelha[cite: 49]
     * @throws NullPointerException Se a orientacao for nula[cite: 49]
     * @throws IllegalArgumentException Se a orientacao for invalida[cite: 49]
     */
    public Galleon(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Galleon.NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("ERROR! invalid bearing for the galleon");

        switch (bearing) {
            case NORTH:
                fillNorth(pos);
                break;
            case EAST:
                fillEast(pos);
                break;
            case SOUTH:
                fillSouth(pos);
                break;
            case WEST:
                fillWest(pos);
                break;

            default:
                throw new IllegalArgumentException("ERROR! invalid bearing for the galleon");
        }
    }

    /**
     * Devolve o tamanho ocupado pelo Galeao[cite: 49].
     *
     * @return Numero de celulas que compoem o navio (5)[cite: 1, 49]
     */
    @Override
    public Integer getSize() {
        return Galleon.SIZE;
    }

    /**
     * Preenche as posicoes ocupadas pelo Galeao quando orientado a Norte[cite: 49].
     *
     * @param pos Posicao base inicial[cite: 49]
     */
    private void fillNorth(IPosition pos) {
        for (int i = 0; i < 3; i++) {
            getPositions().add(new Position(pos.getRow(), pos.getColumn() + i));
        }
        getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + 1));
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn() + 1));
    }

    /**
     * Preenche as posicoes ocupadas pelo Galeao quando orientado a Sul[cite: 49].
     *
     * @param pos Posicao base inicial[cite: 49]
     */
    private void fillSouth(IPosition pos) {
        for (int i = 0; i < 2; i++) {
            getPositions().add(new Position(pos.getRow() + i, pos.getColumn()));
        }
        for (int j = 2; j < 5; j++) {
            getPositions().add(new Position(pos.getRow() + 2, pos.getColumn() + j - 3));
        }
    }

    /**
     * Preenche as posicoes ocupadas pelo Galeao quando orientado a Este[cite: 49].
     *
     * @param pos Posicao base inicial[cite: 49]
     */
    private void fillEast(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 1; i < 4; i++) {
            getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i - 3));
        }
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }

    /**
     * Preenche as posicoes ocupadas pelo Galeao quando orientado a Oeste[cite: 49].
     *
     * @param pos Posicao base inicial[cite: 49]
     */
    private void fillWest(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 1; i < 4; i++) {
            getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i - 1));
        }
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }
}