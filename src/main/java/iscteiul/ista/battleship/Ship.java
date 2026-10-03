package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Classe abstrata que implementa o comportamento comum a todas as embarcacoes da frota[cite: 52].
 *
 * @author Engenharia de Software Team[cite: 1]
 * @version 1.0
 */
public abstract class Ship implements IShip {

    /** Constante para identificacao da Barca[cite: 52]. */
    private static final String GALEAO = "galeao";
    /** Constante para identificacao da Fragata[cite: 52]. */
    private static final String FRAGATA = "fragata";
    /** Constante para identificacao da Nau[cite: 52]. */
    private static final String NAU = "nau";
    /** Constante para identificacao da Caravela[cite: 52]. */
    private static final String CARAVELA = "caravela";
    /** Constante para identificacao do Galeao[cite: 52]. */
    private static final String BARCA = "barca";

    /**
     * Fabrica responsavel pela instanciacao do tipo concreto de navio adequado[cite: 52].
     *
     * @param shipKind Identificador do tipo de navio em minusculas[cite: 52]
     * @param bearing Orientacao cardeal[cite: 52]
     * @param pos Posicao de referencia do navio[cite: 52]
     * @return Instancia concreta de Ship ou null se o tipo for desconhecido[cite: 52]
     */
    static Ship buildShip(String shipKind, Compass bearing, Position pos) {
        Ship s;
        switch (shipKind) {
            case BARCA:
                s = new Barge(bearing, pos);
                break;
            case CARAVELA:
                s = new Caravel(bearing, pos);
                break;
            case NAU:
                s = new Carrack(bearing, pos);
                break;
            case FRAGATA:
                s = new Frigate(bearing, pos);
                break;
            case GALEAO:
                s = new Galleon(bearing, pos);
                break;
            default:
                s = null;
        }
        return s;
    }

    /** Categoria do navio[cite: 52]. */
    private String category;

    /** Orientacao cardeal do navio[cite: 52]. */
    private Compass bearing;

    /** Posicao de referencia base[cite: 52]. */
    private IPosition pos;

    /** Lista de celulas ocupadas pelo navio[cite: 52]. */
    protected List<IPosition> positions;

    /**
     * Construtor base para os navios[cite: 52].
     *
     * @param category Nome da categoria da embarcacao[cite: 52]
     * @param bearing Orientacao cardeal atribuida ao navio[cite: 52]
     * @param pos Posicao de referencia inicial[cite: 52]
     */
    public Ship(String category, Compass bearing, IPosition pos) {
        assert bearing != null;
        assert pos != null;

        this.category = category;
        this.bearing = bearing;
        this.pos = pos;
        positions = new ArrayList<>();
    }

    /**
     * {@inheritDoc}[cite: 52]
     */
    @Override
    public String getCategory() {
        return category;
    }

    /**
     * Devolve a lista de posicoes associadas a este navio[cite: 52].
     *
     * @return Lista das posicoes que o navio ocupa[cite: 52]
     */
    public List<IPosition> getPositions() {
        return positions;
    }

    /**
     * {@inheritDoc}[cite: 52]
     */
    @Override
    public IPosition getPosition() {
        return pos;
    }

    /**
     * {@inheritDoc}[cite: 52]
     */
    @Override
    public Compass getBearing() {
        return bearing;
    }

    /**
     * {@inheritDoc}[cite: 52]
     */
    @Override
    public boolean stillFloating() {
        for (int i = 0; i < getSize(); i++)
            if (!getPositions().get(i).isHit())
                return true;
        return false;
    }

    /**
     * {@inheritDoc}[cite: 52]
     */
    @Override
    public int getTopMostPos() {
        int top = getPositions().get(0).getRow();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getRow() < top)
                top = getPositions().get(i).getRow();
        return top;
    }

    /**
     * {@inheritDoc}[cite: 52]
     */
    @Override
    public int getBottomMostPos() {
        int bottom = getPositions().get(0).getRow();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getRow() > bottom)
                bottom = getPositions().get(i).getRow();
        return bottom;
    }

    /**
     * {@inheritDoc}[cite: 52]
     */
    @Override
    public int getLeftMostPos() {
        int left = getPositions().get(0).getColumn();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getColumn() < left)
                left = getPositions().get(i).getColumn();
        return left;
    }

    /**
     * {@inheritDoc}[cite: 52]
     */
    @Override
    public int getRightMostPos() {
        int right = getPositions().get(0).getColumn();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getColumn() > right)
                right = getPositions().get(i).getColumn();
        return right;
    }

    /**
     * {@inheritDoc}[cite: 52]
     */
    @Override
    public boolean occupies(IPosition pos) {
        assert pos != null;

        for (int i = 0; i < getSize(); i++)
            if (getPositions().get(i).equals(pos))
                return true;
        return false;
    }

    /**
     * {@inheritDoc}[cite: 52]
     */
    @Override
    public boolean tooCloseTo(IShip other) {
        assert other != null;

        Iterator<IPosition> otherPos = other.getPositions().iterator();
        while (otherPos.hasNext())
            if (tooCloseTo(otherPos.next()))
                return true;

        return false;
    }

    /**
     * {@inheritDoc}[cite: 52]
     */
    @Override
    public boolean tooCloseTo(IPosition pos) {
        for (int i = 0; i < this.getSize(); i++)
            if (getPositions().get(i).isAdjacentTo(pos))
                return true;
        return false;
    }

    /**
     * {@inheritDoc}[cite: 52]
     */
    @Override
    public void shoot(IPosition pos) {
        assert pos != null;

        for (IPosition position : getPositions()) {
            if (position.equals(pos))
                position.shoot();
        }
    }

    /**
     * Representacao textual do navio com a sua categoria, orientacao e posicao base[cite: 52].
     *
     * @return String formatada contendo dados do navio[cite: 52]
     */
    @Override
    public String toString() {
        return "[" + category + " " + bearing + " " + pos + "]";
    }
}