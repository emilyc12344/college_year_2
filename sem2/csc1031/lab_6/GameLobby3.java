import java.util.ArrayList;
import java.util.List;

interface Player {
    void joinGame();
    void leaveGame();
    void sendMessage(String message);
    void receiveMessage(String message);
    String getPlayerType();
    String getName();
}

abstract class AbstractPlayer implements Player {
    protected String name;
    protected GameLobby lobby;

    public AbstractPlayer(String name, GameLobby lobby) {
        this.name = name;
        this.lobby = lobby;
    }

    @Override
    public void sendMessage(String message) {
        this.lobby.sendMessage(message, this);

    }

    @Override
    public void receiveMessage(String message) {
        System.out.printf("[%s] received: \"%s\"\n", this.name, message);
    }

    public abstract String getPlayerType();

    public String getName() {
        return this.name;
    }
}

class HumanPlayer extends AbstractPlayer {
    public HumanPlayer(String name, GameLobby lobby) {
        super(name, lobby);
    }
    
    @Override
    public void joinGame() {
        this.lobby.registerPlayer(this);
    }

    @Override
    public void leaveGame() {
        this.lobby.removePlayer(this);
    }

    @Override
    public String getPlayerType() {
        return "HumanPlayer";
    }
}

class AIPlayer extends AbstractPlayer {
    public AIPlayer(String name, GameLobby lobby) {
        super(name, lobby);
    }
    
    @Override
    public void joinGame() {
        this.lobby.registerPlayer(this);
    }

    @Override
    public void leaveGame() {
        this.lobby.removePlayer(this);
    }

    @Override
    public String getPlayerType() {
        return "AIPlayer";
    }
}

class Spectator extends AbstractPlayer {
    public Spectator(String name, GameLobby lobby) {
        super(name, lobby);
    }
    
    @Override
    public void joinGame() {
        this.lobby.registerPlayer(this);
    }

    @Override
    public void leaveGame() {
        this.lobby.removePlayer(this);
    }

    @Override
    public String getPlayerType() {
        return "Spectator";
    }
}

class AdminPlayer extends AbstractPlayer {
    public AdminPlayer(String name, GameLobby lobby) {
        super(name, lobby);
    }

    @Override
    public String getPlayerType() {
        return "AdminPlayer";
    }

     @Override
    public void joinGame() {
        this.lobby.registerPlayer(this);
    }

    @Override
    public void leaveGame() {
        this.lobby.removePlayer(this);
    }

    public void kickPlayer(String name) {
        lobby.kickPlayer(name, this);
    }
}

class GameLobby {
    List<Player> playerList;
    int playerCount;

    public GameLobby() {
        this.playerList = new ArrayList<>();
    }

    void registerPlayer(Player player) {
        if (player.getPlayerType() != "Spectator") {
            playerCount += 1;
        }
        this.playerList.add(player);
        System.out.printf("[GameLobby] %s %s has joined the lobby.\n", player.getPlayerType(), player.getName());
    }

    void removePlayer(Player player) {
        if (player.getPlayerType() != "Spectator") {
            playerCount -= 1;
        }
        this.playerList.remove(player);
        System.out.printf("[GameLobby] %s %s has left the lobby.\n", player.getPlayerType(), player.getName());
    }

    void sendMessage(String message, Player sender) {
        if (sender.getPlayerType() != "Spectator") {
            System.out.printf("[%s] sends: \"%s\"\n", sender.getName(), message);
            System.out.printf("[GameLobby] Message from %s: \"%s\"\n", sender.getName(), message);
            for (Player curr : playerList) {
                if (curr.getName() != sender.getName()) {
                    curr.receiveMessage(message);
                }
            }
        } else {
            System.out.println("[GameLobby] Spectators cannot send messages.");
        }
    }

    void startMatch() {
        int validPlayers = 0;
        for (Player curr : playerList) {
            if (curr.getPlayerType() != "Spectator" && curr.getPlayerType() != "AdminPlayer") {
                validPlayers += 1;
            } 
        }
        if (validPlayers < 2) {
            System.out.println("[GameLobby] Not enough players to start a match.");
        } else {
            String playerString = "";
            for (Player curr : this.playerList) {
                if (curr.getPlayerType() != "Spectator" && curr.getPlayerType() != "AdminPlayer") {
                    playerString = playerString.concat(curr.getName()).concat(", ");
                }
            }
            String currNames = playerString.substring(0, playerString.length() - 2);
            System.out.printf("[GameLobby] Starting game with players: %s\n", currNames);
        }
    }

    void kickPlayer(String name, AdminPlayer admin) {
        Boolean done = false;
        for (int i = playerList.size() - 1; i >= 0; i--) {
            Player curr = playerList.get(i);
            if (curr.getName().equals(name) && !curr.getName().equals(admin.getName())) {
                playerList.remove(i);
                System.out.printf("[GameLobby] Admin %s kicked %s %s from the lobby.\n", admin.getName(), curr.getPlayerType(), curr.getName());
                System.out.printf("[GameLobby] %s %s has left the lobby.\n", curr.getPlayerType(), curr.getName());
                done = true;
            }
        }
        if (!done) {
            System.out.printf("[GameLobby] Player %s not found.\n", name);
        }
    }
}

class PlayerFactory {
    public static Player createPlayer(String type, String name, GameLobby lobby) {
        switch (type.toLowerCase()) {
            case "human":
                return new HumanPlayer(name, lobby);
            case "ai":
                return new AIPlayer(name, lobby);
            case "spectator":
                return new Spectator(name, lobby);
            default:
                return new AdminPlayer(name, lobby);
        }
    }
}
