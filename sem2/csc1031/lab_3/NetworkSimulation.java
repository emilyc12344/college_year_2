import java.util.*;

class CellTower {
    private String id;
    private double x, y;
    private double radius;
    private int connectionCount;

    public CellTower(String id, double x, double y, double radius) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.connectionCount = 0;
    }

    public String getId() { return id; }
    public double getX() { return x; }
    public double getY() { return y; }
    public double getRadius() { return radius; }
    public int getConnectionCount() { return connectionCount; }

    public void incrementConnections() { connectionCount++; }
    public void decrementConnections() { connectionCount--; }
}

class Operator {
    private String name;
    private Set<String> towerIds = new HashSet<>();
    private List<Client> subscribers = new ArrayList<>();

    public Operator(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public Set<String> getTowerIds() { return towerIds; }
    public List<Client> getSubscribers() { return subscribers; }

    public void addTower(String towerId) { towerIds.add(towerId); }
    public void addSubscriber(Client client) { subscribers.add(client); }
    public void removeSubscriber(Client client) { subscribers.remove(client); }
}

class Client {
    private String phoneNumber;
    private double x, y;
    private Operator operator;
    private CellTower connectedTower;

    public Client(String phoneNumber, Operator operator, double x, double y) {
        this.phoneNumber = phoneNumber;
        this.operator = operator;
        this.x = x;
        this.y = y;
    }

    public String getPhoneNumber() { return phoneNumber; }
    public double getX() { return x; }
    public double getY() { return y; }
    public Operator getOperator() { return operator; }
    public CellTower getConnectedTower() { return connectedTower; }

    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public void setConnectedTower(CellTower tower) {
        if (this.connectedTower != null) {
            this.connectedTower.decrementConnections();
        }
        this.connectedTower = tower;
        if (this.connectedTower != null) {
            this.connectedTower.incrementConnections();
        }
    }
}

class Network {
    private Map<String, CellTower> towers = new HashMap<>();
    private Map<String, Operator> operators = new TreeMap<>();
    private Map<String, Client> clients = new HashMap<>();

    public void addOperator(String name) {
        operators.putIfAbsent(name, new Operator(name));
    }

    public void addTower(String id, double x, double y, double radius) {
        towers.put(id, new CellTower(id, x, y, radius));
    }

    public void registerOperatorTower(String opName, String towerId) {
        Operator op = operators.get(opName);
        if (op != null && towers.containsKey(towerId)) {
            op.addTower(towerId);
            updateAllClients();
        }
    }

    public void addClient(String number, String opName, double x, double y) {
        Operator op = operators.get(opName);
        if (op != null) {
            Client client = new Client(number, op, x, y);
            clients.put(number, client);
            op.addSubscriber(client);
            updateClientConnectivity(client);
        }
    }

    public void moveClient(String number, double x, double y) {
        Client client = clients.get(number);
        if (client != null) {
            client.setLocation(x, y);
            updateClientConnectivity(client);
        }
    }

    public void changeOperator(String number, String newOpName) {
        Client client = clients.get(number);
        Operator newOp = operators.get(newOpName);
        if (client != null && newOp != null) {
            client.getOperator().removeSubscriber(client);
            client.setOperator(newOp);
            newOp.addSubscriber(client);
            updateClientConnectivity(client);
        }
    }

    public void removeClient(String number) {
        Client client = clients.remove(number);
        if (client != null) {
            client.setConnectedTower(null);
            client.getOperator().removeSubscriber(client);
        }
    }

    public void removeTower(String id) {
        towers.remove(id);
        for (Operator op : operators.values()) {
            op.getTowerIds().remove(id);
        }
        updateAllClients();
    }

    private void updateAllClients() {
        for (Client client : clients.values()) {
            updateClientConnectivity(client);
        }
    }

    private void updateClientConnectivity(Client client) {
        CellTower bestTower = null;
        double minDistance = Double.MAX_VALUE;

        Set<String> allowedTowers = client.getOperator().getTowerIds();

        for (String towerId : allowedTowers) {
            CellTower tower = towers.get(towerId);
            if (tower == null) continue;

            double dist = Math.sqrt(Math.pow(client.getX() - tower.getX(), 2) + 
                                    Math.pow(client.getY() - tower.getY(), 2));

            if (dist <= tower.getRadius()) {
                if (dist < minDistance) {
                    minDistance = dist;
                    bestTower = tower;
                } else if (Math.abs(dist - minDistance) < 1e-9) {
                    if (bestTower == null || tower.getConnectionCount() < bestTower.getConnectionCount()) {
                        bestTower = tower;
                    }
                }
            }
        }
        client.setConnectedTower(bestTower);
    }

    public int getTowerClientCount(String id) {
        CellTower tower = towers.get(id);
        return (tower != null) ? tower.getConnectionCount() : 0;
    }

    public int getOperatorSubscriberCount(String name) {
        Operator op = operators.get(name);
        return (op != null) ? op.getSubscribers().size() : 0;
    }

    public void printNoSignalCount() {
        for (Operator op : operators.values()) {
            int count = 0;
            for (Client c : op.getSubscribers()) {
                if (c.getConnectedTower() == null) {
                    count++;
                }
            }
            System.out.println(op.getName() + ": " + count + " phones without signal.");
        }
    }
}

public class NetworkSimulation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Network network = new Network();

        while (sc.hasNext()) {
            String command = sc.next();
            switch (command) {
                case "ADD_OPERATOR":
                    network.addOperator(sc.next());
                    break;
                case "ADD_TOWER":
                    network.addTower(sc.next(), sc.nextDouble(), sc.nextDouble(), sc.nextDouble());
                    break;
                case "REGISTER_OPERATOR_TOWER":
                    network.registerOperatorTower(sc.next(), sc.next());
                    break;
                case "ADD_CLIENT":
                    network.addClient(sc.next(), sc.next(), sc.nextDouble(), sc.nextDouble());
                    break;
                case "MOVE_CLIENT":
                    network.moveClient(sc.next(), sc.nextDouble(), sc.nextDouble());
                    break;
                case "CHANGE_OPERATOR":
                    network.changeOperator(sc.next(), sc.next());
                    break;
                case "REMOVE_CLIENT":
                    network.removeClient(sc.next());
                    break;
                case "REMOVE_TOWER":
                    network.removeTower(sc.next());
                    break;
                case "TOWER_CLIENT_COUNT":
                    System.out.println(network.getTowerClientCount(sc.next()));
                    break;
                case "OPERATOR_SUBSCRIBER_COUNT":
                    System.out.println(network.getOperatorSubscriberCount(sc.next()));
                    break;
                case "NO_SIGNAL_COUNT":
                    network.printNoSignalCount();
                    break;
            }
        }
        sc.close();
    }
}