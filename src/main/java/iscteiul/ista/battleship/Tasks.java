/**
 *
 */
package iscteiul.ista.battleship;

import java.util.Scanner;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Classe utilitária que agrupa as tarefas (A, B, C e D) de teste do jogo da
 * batalha naval, executadas através de comandos lidos da entrada padrão.
 * <ul>
 * <li>{@link #taskA()}: construção de navios;</li>
 * <li>{@link #taskB()}: construção de frotas;</li>
 * <li>{@link #taskC()}: construção de frotas com possibilidade de batota;</li>
 * <li>{@link #taskD()}: jogo completo com rajadas de tiros.</li>
 * </ul>
 */
public class Tasks {

    /** Logger utilizado para escrever as mensagens da aplicação. */
    private static final Logger LOGGER = LogManager.getLogger();

    /** Número de tiros de cada rajada. */
    private static final int NUMBER_SHOTS = 3;

    /** Mensagem de despedida apresentada quando o utilizador desiste. */
    private static final String GOODBYE_MESSAGE = "Bons ventos!";

    /**
     * Strings to be used by the user
     */

    /** Comando para criar uma nova frota. */
    private static final String NOVAFROTA = "nova";

    /** Comando para terminar o programa. */
    private static final String DESISTIR = "desisto";

    /** Comando para disparar uma rajada de tiros. */
    private static final String RAJADA = "rajada";

    /** Comando para ver os tiros válidos já efetuados. */
    private static final String VERTIROS = "ver";

    /** Comando para mostrar a frota completa (batota). */
    private static final String BATOTA = "mapa";

    /** Comando para mostrar o estado da frota. */
    private static final String STATUS = "estado";


    /////////////////////////////////////////////////////////////////////////////
    // hereafter one may find some code that can be converted to automatic tests,
    // as long as appropriate changes are made. It also shows that we should
    // develop our code incrementally e.g. first the ships, then the fleet,
    // then some rule checking, then dealing with firing and so on
    /////////////////////////////////////////////////////////////////////////////

    /**
     * This task tests the building up of ships: For each ship, reads positions and
     * indicates whether the ship occupies each one of such positions or not
     */
    public static void taskA() {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            Ship s = readShip(in);
            if (s != null)
                for (int i = 0; i < NUMBER_SHOTS; i++) {
                    Position p = readPosition(in);
                    LOGGER.info("{} {}", p, s.occupies(p));
                }
        }
    }

    /**
     * This task tests the building up of fleets
     * <p>
     * Comandos aceites: {@code nova} (constrói uma frota), {@code estado}
     * (mostra o estado da frota) e {@code desisto} (termina).
     * </p>
     */
    public static void taskB() {
        Scanner in = new Scanner(System.in);
        IFleet fleet = null;
        String command = in.next();
        while (!command.equals(DESISTIR)) {
            switch (command) {
                case NOVAFROTA:
                    fleet = buildFleet(in);
                    break;
                case STATUS:
                    if (fleet != null)
                        fleet.printStatus();
                    break;
                default:
                    LOGGER.info("Que comando é esse??? Repete lá ...");
            }
            // The other commands are unknown in this task
            command = in.next();
        }
        LOGGER.info(GOODBYE_MESSAGE);
    }

    /**
     * This task tests the building up of fleets and takes into consideration the
     * possibility of cheating
     * <p>
     * Além dos comandos da {@link #taskB()}, aceita {@code mapa}, que mostra a
     * frota completa.
     * </p>
     */
    public static void taskC() {
        Scanner in = new Scanner(System.in);
        IFleet fleet = null;
        String command = in.next();
        while (!command.equals(DESISTIR)) {
            switch (command) {
                case NOVAFROTA:
                    fleet = buildFleet(in);
                    break;
                case STATUS:
                    if (fleet != null)
                        fleet.printStatus();
                    break;
                case BATOTA:
                    LOGGER.info(fleet);
                    break;
                default:
                    LOGGER.info("Que comando é esse??? Repete lá ...");
            }
            // The other commands are unknown in this task
            command = in.next();
        }
        LOGGER.info(GOODBYE_MESSAGE);
    }

    /**
     * This task also tests the fighting element of a round of three shots
     * <p>
     * Além dos comandos da {@link #taskC()}, aceita {@code rajada} (dispara três
     * tiros e apresenta as estatísticas do jogo) e {@code ver} (mostra os tiros
     * válidos já efetuados).
     * </p>
     */
    public static void taskD() {

        Scanner in = new Scanner(System.in);
        IFleet fleet = null;
        IGame game = null;
        String command = in.next();
        while (!command.equals(DESISTIR)) {
            switch (command) {
                case NOVAFROTA:
                    fleet = buildFleet(in);
                    game = new Game(fleet);
                    break;
                case STATUS:
                    if (fleet != null)
                        fleet.printStatus();
                    break;
                case BATOTA:
                    if (fleet != null)
                        game.printFleet();
                    break;
                case RAJADA:
                    if (game != null) {
                        firingRound(in, game);

                        LOGGER.info("Hits: {} Inv: {} Rep: {} Restam {} navios.", game.getHits(), game.getInvalidShots(),
                                game.getRepeatedShots(), game.getRemainingShips());
                        if (game.getRemainingShips() == 0)
                            LOGGER.info("Maldito sejas, Java Sparrow, eu voltarei, glub glub glub...");
                    }
                    break;
                case VERTIROS:
                    if (game != null)
                        game.printValidShots();
                    break;
                default:
                    LOGGER.info("Que comando é esse??? Repete ...");
            }
            command = in.next();
        }
        LOGGER.info(GOODBYE_MESSAGE);
    }

    /**
     * This operation allows the build up of a fleet, given user data
     *
     * @param in The scanner to read from
     * @return The fleet that has been built
     */
    static Fleet buildFleet(Scanner in) {
        assert in != null;

        Fleet fleet = new Fleet();
        int i = 0; // i represents the total of successfully created ships

        while (i <= Fleet.FLEET_SIZE) {
            IShip s = readShip(in);
            if (s != null) {
                boolean success = fleet.addShip(s);
                if (success)
                    i++;
                else
                    LOGGER.info("Falha na criacao de {} {} {}", s.getCategory(), s.getBearing(), s.getPosition());
            } else {
                LOGGER.info("Navio desconhecido!");
            }
        }
        LOGGER.info("{} navios adicionados com sucesso!", i);
        return fleet;
    }

    /**
     * This operation reads data about a ship, build it and returns it
     * <p>
     * Lê, por esta ordem: o tipo de navio, a posição (linha e coluna) e o
     * caráter da orientação.
     * </p>
     *
     * @param in The scanner to read from
     * @return The created ship based on the data that has been read
     */
    static Ship readShip(Scanner in) {
        String shipKind = in.next();
        Position pos = readPosition(in);
        char c = in.next().charAt(0);
        Compass bearing = Compass.charToCompass(c);
        return Ship.buildShip(shipKind, bearing, pos);
    }

    /**
     * This operation allows reading a position in the map
     * <p>
     * Lê dois inteiros: a linha e a coluna.
     * </p>
     *
     * @param in The scanner to read from
     * @return The position that has been read
     */
    static Position readPosition(Scanner in) {
        int row = in.nextInt();
        int column = in.nextInt();
        return new Position(row, column);
    }

    /**
     * This operation allows firing a round of shots (three) over a fleet, in the
     * context of a game
     *
     * @param in   The scanner to read from
     * @param game The context game while fleet is being attacked
     */
    static void firingRound(Scanner in, IGame game) {
        for (int i = 0; i < NUMBER_SHOTS; i++) {
            IPosition pos = readPosition(in);
            IShip sh = game.fire(pos);
            if (sh != null)
                LOGGER.info("Mas... mas... {}s nao sao a prova de bala? :-(", sh.getCategory());
        }

    }

}
