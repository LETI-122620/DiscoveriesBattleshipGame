package iscteiul.ista.battleship;

import java.util.List;

/**
 * Interface que define o contrato para a gestao da frota de navios no jogo[cite: 50].
 *
 * @author Engenharia de Software Team[cite: 1]
 * @version 1.0
 */
public interface IFleet {

    /** Dimensao das linhas e colunas do tabuleiro (10x10)[cite: 1, 50]. */
    Integer BOARD_SIZE = 10;

    /** Numero total de navios admitidos na frota[cite: 1, 50]. */
    Integer FLEET_SIZE = 10;

    /**
     * Devolve a lista de todos os navios que integram a frota[cite: 50].
     *
     * @return Lista com os navios da frota[cite: 50]
     */
    List<IShip> getShips();

    /**
     * Tenta adicionar um novo navio a frota caso respeite as regras de limite, limites do tabuleiro e colisao[cite: 50, 51].
     *
     * @param s Navio a inserir[cite: 50]
     * @return true se for adicionado com sucesso, false caso viole as regras[cite: 50, 51]
     */
    boolean addShip(IShip s);

    /**
     * Filtra e devolve todos os navios pertencentes a uma categoria especifica[cite: 50].
     *
     * @param category Categoria a procurar (ex.: Galeao, Fragata)[cite: 50, 51]
     * @return Lista com os navios encontrados[cite: 50]
     */
    List<IShip> getShipsLike(String category);

    /**
     * Devolve a lista de navios da frota que ainda continuam a flutuar[cite: 50].
     *
     * @return Lista de navios nao afundados[cite: 50]
     */
    List<IShip> getFloatingShips();

    /**
     * Devolve o navio que ocupa a posicao indicada[cite: 50].
     *
     * @param pos Posicao da grelha a inspecionar[cite: 50]
     * @return Objeto IShip presente na celula ou null caso nao haja nenhum navio[cite: 50, 51]
     */
    IShip shipAt(IPosition pos);

    /**
     * Imprime na consola o estado detalhado da frota e respetivas categorias[cite: 50, 51].
     */
    void printStatus();
}