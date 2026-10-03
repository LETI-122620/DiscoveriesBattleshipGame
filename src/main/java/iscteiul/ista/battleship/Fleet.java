package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementacao da frota de navios (Fleet) de um jogador no jogo de Batalha Naval[cite: 51].
 * Gere a validacao de limites, regras de afastamento e o estado dos navios[cite: 1, 51].
 *
 * @author Engenharia de Software Team[cite: 1]
 * @version 1.0
 */
public class Fleet implements IFleet {

    /**
     * Imprime no terminal a informacao de cada navio da lista fornecida[cite: 51].
     *
     * @param ships Lista de navios a imprimir[cite: 51]
     */
    static void printShips(List<IShip> ships) {
        for (IShip ship : ships)
            System.out.println(ship);
    }

    /** Colecao de navios pertencentes a esta frota[cite: 51]. */
    private List<IShip> ships;

    /**
     * Cria uma frota vazia[cite: 51].
     */
    public Fleet() {
        ships = new ArrayList<>();
    }

    /**
     * {@inheritDoc}[cite: 51]
     */
    @Override
    public List<IShip> getShips() {
        return ships;
    }

    /**
     * Adiciona um navio caso nao ultrapasse a capacidade maxima da frota,
     * esteja totalmente contido no tabuleiro e nao colida com os restantes[cite: 51].
     *
     * @param s Navio a adicionar[cite: 51]
     * @return true se o navio for inserido, false se violar qualquer validacao[cite: 51]
     */
    @Override
    public boolean addShip(IShip s) {
        boolean result = false;
        if ((ships.size() <= FLEET_SIZE) && (isInsideBoard(s)) && (!colisionRisk(s))) {
            ships.add(s);
            result = true;
        }
        return result;
    }

    /**
     * {@inheritDoc}[cite: 51]
     */
    @Override
    public List<IShip> getShipsLike(String category) {
        List<IShip> shipsLike = new ArrayList<>();
        for (IShip s : ships)
            if (s.getCategory().equals(category))
                shipsLike.add(s);

        return shipsLike;
    }

    /**
     * {@inheritDoc}[cite: 51]
     */
    @Override
    public List<IShip> getFloatingShips() {
        List<IShip> floatingShips = new ArrayList<>();
        for (IShip s : ships)
            if (s.stillFloating())
                floatingShips.add(s);

        return floatingShips;
    }

    /**
     * {@inheritDoc}[cite: 51]
     */
    @Override
    public IShip shipAt(IPosition pos) {
        for (int i = 0; i < ships.size(); i++)
            if (ships.get(i).occupies(pos))
                return ships.get(i);
        return null;
    }

    /**
     * Valida se todas as celulas do navio estao estritamente dentro da grelha de jogo[cite: 51].
     *
     * @param s Navio a validar[cite: 51]
     * @return true se o navio couber integralmente na grelha, false caso contrario[cite: 51]
     */
    private boolean isInsideBoard(IShip s) {
        return (s.getLeftMostPos() >= 0 && s.getRightMostPos() <= BOARD_SIZE - 1 && s.getTopMostPos() >= 0
                && s.getBottomMostPos() <= BOARD_SIZE - 1);
    }

    /**
     * Verifica se o navio esta demasiado proximo ou sobreposto a algum navio ja presente[cite: 51].
     *
     * @param s Navio a validar[cite: 51]
     * @return true se existir colisao ou contacto indeferido, false se a posicao estiver livre[cite: 1, 51]
     */
    private boolean colisionRisk(IShip s) {
        for (int i = 0; i < ships.size(); i++) {
            if (ships.get(i).tooCloseTo(s))
                return true;
        }
        return false;
    }

    /**
     * Mostra na consola o estado detalhado da frota e das suas categorias[cite: 51].
     */
    public void printStatus() {
        printAllShips();
        printFloatingShips();
        printShipsByCategory("Galeao");
        printShipsByCategory("Fragata");
        printShipsByCategory("Nau");
        printShipsByCategory("Caravela");
        printShipsByCategory("Barca");
    }

    /**
     * Imprime todos os navios da frota pertencentes a categoria indicada[cite: 51].
     *
     * @param category Nome da categoria de interesse[cite: 51]
     */
    public void printShipsByCategory(String category) {
        assert category != null;

        printShips(getShipsLike(category));
    }

    /**
     * Imprime os navios da frota que continuam a flutuar[cite: 51].
     */
    public void printFloatingShips() {
        printShips(getFloatingShips());
    }

    /**
     * Imprime todos os navios que compoem a frota[cite: 51].
     */
    void printAllShips() {
        printShips(ships);
    }
}