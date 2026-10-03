/**
 *
 */
package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

/**
 * Gestor do estado e fluxo principal do jogo da Batalha Naval.
 * Responsável por processar os disparos, contabilizar métricas (tiros válidos, inválidos,
 * repetidos, acertos e afundamentos) e permitir a visualização do tabuleiro.
 *
 * @author fba
 */
public class Game implements IGame {
    private IFleet fleet;
    private List<IPosition> shots;

    private Integer countInvalidShots;
    private Integer countRepeatedShots;
    private Integer countHits;
    private Integer countSinks;

    /**
     * Constrói uma nova partida associada a uma frota de navios.
     *
     * @param fleet a frota utilizada no jogo
     */
    public Game(IFleet fleet) {
        shots = new ArrayList<>();
        countInvalidShots = 0;
        countRepeatedShots = 0;
        this.fleet = fleet;
    }

    /*
     * (non-Javadoc)
     *
     * @see battleship.IGame#fire(battleship.IPosition)
     */
    /**
     * Executa um disparo sobre a grelha de jogo numa dada posição.
     *
     * @param pos a posição visada pelo disparo
     * @return o navio que foi afundado com este disparo, ou null caso o disparo não tenha resultado num afundamento
     */
    @Override
    public IShip fire(IPosition pos) {
        if (!validShot(pos))
            countInvalidShots++;
        else { // valid shot!
            if (repeatedShot(pos))
                countRepeatedShots++;
            else {
                shots.add(pos);
                IShip s = fleet.shipAt(pos);
                if (s != null) {
                    s.shoot(pos);
                    countHits++;
                    if (!s.stillFloating()) {
                        countSinks++;
                        return s;
                    }
                }
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see battleship.IGame#getShots()
     */
    /**
     * Devolve a lista de posições dos disparos válidos efetuados.
     *
     * @return lista de posições dos tiros efetuados
     */
    @Override
    public List<IPosition> getShots() {
        return shots;
    }

    /*
     * (non-Javadoc)
     *
     * @see battleship.IGame#getRepeatedShots()
     */
    /**
     * Devolve o número total de disparos repetidos.
     *
     * @return contagem de disparos efetuados em coordenadas já atacadas
     */
    @Override
    public int getRepeatedShots() {
        return this.countRepeatedShots;
    }

    /*
     * (non-Javadoc)
     *
     * @see battleship.IGame#getInvalidShots()
     */
    /**
     * Devolve o número total de disparos inválidos (fora dos limites do tabuleiro).
     *
     * @return contagem de tiros inválidos
     */
    @Override
    public int getInvalidShots() {
        return this.countInvalidShots;
    }

    /*
     * (non-Javadoc)
     *
     * @see battleship.IGame#getHits()
     */
    /**
     * Devolve o número total de tiros bem-sucedidos que atingiram um navio.
     *
     * @return contagem de acertos em navios
     */
    @Override
    public int getHits() {
        return this.countHits;
    }

    /*
     * (non-Javadoc)
     *
     * @see battleship.IGame#getSunkShips()
     */
    /**
     * Devolve o número total de navios da frota que foram totalmente afundados.
     *
     * @return contagem de navios afundados
     */
    @Override
    public int getSunkShips() {
        return this.countSinks;
    }

    /*
     * (non-Javadoc)
     *
     * @see battleship.IGame#getRemainingShips()
     */
    /**
     * Devolve o número de navios da frota que ainda se encontram a flutuar.
     *
     * @return número de navios restantes em jogo
     */
    @Override
    public int getRemainingShips() {
        List<IShip> floatingShips = fleet.getFloatingShips();
        return floatingShips.size();
    }

    /**
     * Verifica se a posição de um disparo se encontra dentro dos limites da grelha de jogo.
     *
     * @param pos a posição a validar
     * @return true se o tiro for válido, false caso contrário
     */
    private boolean validShot(IPosition pos) {
        return (pos.getRow() >= 0 && pos.getRow() <= Fleet.BOARD_SIZE && pos.getColumn() >= 0
                && pos.getColumn() <= Fleet.BOARD_SIZE);
    }

    /**
     * Verifica se um disparo já foi efetuado anteriormente na mesma posição.
     *
     * @param pos a posição a verificar
     * @return true se for um disparo repetido, false caso contrário
     */
    private boolean repeatedShot(IPosition pos) {
        for (int i = 0; i < shots.size(); i++)
            if (shots.get(i).equals(pos))
                return true;
        return false;
    }

    /**
     * Imprime no consola uma representação visual do tabuleiro com as posições fornecidas
     * marcadas por um caráter específico.
     *
     * @param positions lista de posições a apresentar na grelha
     * @param marker caráter utilizado para desenhar as posições no mapa
     */
    public void printBoard(List<IPosition> positions, Character marker) {
        char[][] map = new char[Fleet.BOARD_SIZE][Fleet.BOARD_SIZE];

        for (int r = 0; r < Fleet.BOARD_SIZE; r++)
            for (int c = 0; c < Fleet.BOARD_SIZE; c++)
                map[r][c] = '.';

        for (IPosition pos : positions)
            map[pos.getRow()][pos.getColumn()] = marker;

        for (int row = 0; row < Fleet.BOARD_SIZE; row++) {
            for (int col = 0; col < Fleet.BOARD_SIZE; col++)
                System.out.print(map[row][col]);
            System.out.println();
        }

    }

    /**
     * Prints the board showing valid shots that have been fired
     */
    public void printValidShots() {
        printBoard(getShots(), 'X');
    }

    /**
     * Prints the board showing the fleet
     */
    public void printFleet() {
        List<IPosition> shipPositions = new ArrayList<IPosition>();

        for (IShip s : fleet.getShips())
            shipPositions.addAll(s.getPositions());

        printBoard(shipPositions, '#');
    }

}
