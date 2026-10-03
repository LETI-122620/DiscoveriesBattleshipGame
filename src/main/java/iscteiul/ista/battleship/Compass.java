/**
 *
 */
package iscteiul.ista.battleship;

/**
 * Representa os pontos cardeais para a orientação dos navios no tabuleiro.
 * 
 * @author fba
 */
public enum Compass {
    NORTH('n'), SOUTH('s'), EAST('e'), WEST('o'), UNKNOWN('u');

    private final char c;

    /**
     * Construtor do enum Compass.
     * 
     * @param c o caráter associado à direção
     */
    Compass(char c) {
        this.c = c;
    }

    /**
     * Devolve o caráter representativo da direção.
     * 
     * @return o caráter correspondente à direção
     */
    public char getDirection() {
        return c;
    }

    /**
     * Retorna a representação em String da direção.
     * 
     * @return String com o caráter da direção
     */
    @Override
    public String toString() {
        return "" + c;
    }

    /**
     * Converte um caráter na direção correspondente do enum Compass.
     * 
     * @param ch o caráter que representa a direção ('n', 's', 'e', 'o')
     * @return o objeto Compass correspondente ou UNKNOWN se o caráter for inválido
     */
    static Compass charToCompass(char ch) {
        Compass bearing;
        switch (ch) {
            case 'n':
                bearing = NORTH;
                break;
            case 's':
                bearing = SOUTH;
                break;
            case 'e':
                bearing = EAST;
                break;
            case 'o':
                bearing = WEST;
                break;
            default:
                bearing = UNKNOWN;
        }

        return bearing;
    }
}
