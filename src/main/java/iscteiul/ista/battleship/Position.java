/**
 *
 */
package iscteiul.ista.battleship;

import java.util.Objects;

/**
 * Implementação de {@link IPosition}. Representa uma célula do tabuleiro,
 * identificada pela linha e coluna, que pode estar ocupada por um navio e/ou ter
 * sido atingida por um tiro.
 *
 * @see IPosition
 */
public class Position implements IPosition {

    /** Linha da posição. */
    private int row;

    /** Coluna da posição. */
    private int column;

    /** Indica se a posição está ocupada por um navio. */
    private boolean isOccupied;

    /** Indica se a posição já foi atingida por um tiro. */
    private boolean isHit;

    /**
     * Constrói uma posição com a linha e coluna indicadas. Inicialmente a posição
     * não está ocupada nem foi atingida.
     *
     * @param row    a linha da posição
     * @param column a coluna da posição
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
        this.isOccupied = false;
        this.isHit = false;
    }

    /**
     * {@inheritDoc}
     *
     * @return o número da linha
     */
    @Override
    public int getRow() {
        return row;
    }

    /**
     * {@inheritDoc}
     *
     * @return o número da coluna
     */
    @Override
    public int getColumn() {
        return column;
    }

    /**
     * Calcula o código de hash da posição, com base na linha, coluna e nos estados
     * de ocupação e de acerto.
     *
     * @return o código de hash da posição
     */
    @Override
    public int hashCode() {
        return Objects.hash(column, isHit, isOccupied, row);
    }

    /**
     * {@inheritDoc}
     * <p>
     * A comparação considera apenas a linha e a coluna, ignorando os estados de
     * ocupação e de acerto.
     * </p>
     *
     * @param otherPosition o objeto a comparar
     * @return {@code true} se for a mesma instância ou uma {@link IPosition} com a
     *         mesma linha e coluna; {@code false} caso contrário
     */
    @Override
    public boolean equals(Object otherPosition) {
        if (this == otherPosition)
            return true;
        if (otherPosition instanceof IPosition) {
            IPosition other = (IPosition) otherPosition;
            return (this.getRow() == other.getRow() && this.getColumn() == other.getColumn());
        } else {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param other a posição com a qual se pretende comparar
     * @return {@code true} se a diferença de linhas e de colunas for no máximo 1
     */
    @Override
    public boolean isAdjacentTo(IPosition other) {
        return (Math.abs(this.getRow() - other.getRow()) <= 1 && Math.abs(this.getColumn() - other.getColumn()) <= 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void occupy() {
        isOccupied = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void shoot() {
        isHit = true;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code true} se a posição estiver ocupada
     */
    @Override
    public boolean isOccupied() {
        return isOccupied;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code true} se a posição tiver sido atingida
     */
    @Override
    public boolean isHit() {
        return isHit;
    }

    /**
     * Devolve uma representação textual da posição no formato
     * {@code "Linha = x Coluna = y"}.
     *
     * @return a representação textual da posição
     */
    @Override
    public String toString() {
        return ("Linha = " + row + " Coluna = " + column);
    }

}
