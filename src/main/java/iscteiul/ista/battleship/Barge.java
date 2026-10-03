/**
 *
 */
package iscteiul.ista.battleship;

/**
 * Representa uma Barca na versão do jogo Batalha Naval dos Descobrimentos[cite: 3].
 * A Barca é a menor embarcação da frota, ocupando um tamanho fixo de 1 quadrado no tabuleiro[cite: 3].
 */
public class Barge extends Ship {
    private static final Integer SIZE = 1;
    private static final String NAME = "Barca";

    /**
     * Constrói uma nova instância de Barca com a orientação e posição inicial especificadas.
     * 
     * @param bearing - barge bearing
     * @param pos     - upper left position of the barge
     */
    public Barge(Compass bearing, IPosition pos) {
        super(Barge.NAME, bearing, pos);
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
    }

    /**
     * Devolve o tamanho da Barca.
     * 
    * @return o tamanho fixo da Barca, que é 1 quadrado[cite: 3]
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }

}
