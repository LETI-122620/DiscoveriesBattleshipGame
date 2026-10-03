package iscteiul.ista.battleship;

import java.util.List;

/**
 * Interface que define o contrato e operacoes para um navio no jogo de Batalha Naval[cite: 53].
 *
 * @author Engenharia de Software Team[cite: 1]
 * @version 1.0
 */
public interface IShip {

    /**
     * Obtem a categoria ou tipo do navio[cite: 53].
     *
     * @return String correspondente a categoria do navio[cite: 53]
     */
    String getCategory();

    /**
     * Obtem a dimensao (numero de celulas) do navio[cite: 53].
     *
     * @return Numero total de posicoes que o navio ocupa[cite: 53]
     */
    Integer getSize();

    /**
     * Devolve a lista de todas as posicoes ocupadas pelo navio na grelha[cite: 53].
     *
     * @return Lista com os objetos IPosition associados ao navio[cite: 53]
     */
    List<IPosition> getPositions();

    /**
     * Devolve a posicao de referencia inicial onde o navio foi ancorado[cite: 53].
     *
     * @return Posicao de referencia do navio[cite: 53]
     */
    IPosition getPosition();

    /**
     * Devolve a orientacao cardeal do navio[cite: 53].
     *
     * @return Orientacao (Compass) atual[cite: 53]
     */
    Compass getBearing();

    /**
     * Indica se o navio ainda se encontra a flutuar (ou seja, se resta pelo menos uma posicao nao atingida)[cite: 52, 53].
     *
     * @return true se o navio ainda flutuar, false caso esteja totalmente afundado[cite: 52, 53]
     */
    boolean stillFloating();

    /**
     * Devolve o indice da linha mais a cima (topo) ocupada pelo navio[cite: 52, 53].
     *
     * @return Inteiro correspondente ao menor valor de linha[cite: 52, 53]
     */
    int getTopMostPos();

    /**
     * Devolve o indice da linha mais a baixo (fundo) ocupada pelo navio[cite: 52, 53].
     *
     * @return Inteiro correspondente ao maior valor de linha[cite: 52, 53]
     */
    int getBottomMostPos();

    /**
     * Devolve o indice da coluna mais a esquerda ocupada pelo navio[cite: 52, 53].
     *
     * @return Inteiro correspondente ao menor valor de coluna[cite: 52, 53]
     */
    int getLeftMostPos();

    /**
     * Devolve o indice da coluna mais a direita ocupada pelo navio[cite: 52, 53].
     *
     * @return Inteiro correspondente ao maior valor de coluna[cite: 52, 53]
     */
    int getRightMostPos();

    /**
     * Verifica se o navio ocupa uma determinada posicao na grelha[cite: 53].
     *
     * @param pos Posicao a avaliar[cite: 53]
     * @return true se a coordenada coincidir com uma das posicoes do navio, false caso contrario[cite: 52, 53]
     */
    boolean occupies(IPosition pos);

    /**
     * Verifica se este navio esta demasiado perto (sobreposto ou adjacente) de outro navio[cite: 52, 53].
     *
     * @param other Outro navio a comparar[cite: 53]
     * @return true se existir risco de colisao ou contacto indeferido, false caso contrario[cite: 51, 52, 53]
     */
    boolean tooCloseTo(IShip other);

    /**
     * Verifica se o navio esta demasiado perto de uma determinada posicao[cite: 53].
     *
     * @param pos Posicao a avaliar[cite: 53]
     * @return true se a posicao for adjacente a qualquer parte do navio, false caso contrario[cite: 52, 53]
     */
    boolean tooCloseTo(IPosition pos);

    /**
     * Efetua um disparo sobre o navio. Se a posicao pertencer ao navio, fica marcada como atingida[cite: 52, 53].
     *
     * @param pos Coordenada do disparo a processar[cite: 53]
     */
    void shoot(IPosition pos);
}