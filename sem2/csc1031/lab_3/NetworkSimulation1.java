import java.util.ArrayList;
import java.util.List;

public class CellTower {
    private String id;
    private double x, y;
    private double coverageRadius;
    private String operator;
    private List<Client> connectedClients;

    public CellTower(String id, double x, double y, double coverageRadius){
        this.id = id
        this.x = x;
        this.y = y;
        this.coverageRadius = coverageRadius;
        this.operator = null;
        this.connectedClients = new ArrayList<>();
    }

    public double distanceTo(double x, double y){
        return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
    }

    public boolean isWithinCoverage(double x, double y){
        return distanceTo(x, y) <= coverageRadius;
    }

    public void connectClient(Client client){
        if (!connectedClients.contains(client)){
            connectClient.add(client);
        }
    }

    public void disconnectClient(Client client){
        connectedClients.remove(client)
    }
}

public class Operator {
    private String name;
    private List<int> clients;
    private List<int> towers;

    public Operator()
}

public class Client {
    private int phoneNumber;
    private double x, y;
    private String operator;
    private CellTower connectedTower;

    public Client(String phoneNumber, String operator, double x, double y){
        this.phoneNumber = phoneNumber;
        this.operator = operator;
        this.x = x;
        this.y = y;
        this.connectedTower = null; // initlally no connected tower
    }

    public void MOVE_CLIENT(String phoneNumber, double new_x, double new_y){
        if (this.phoneNumber == phoneNumber) {
            this.x = new_x;
            this.y = new_y;

            if (connectedTower != null && !connectedTower.isWithinCoverage(new_x, new_y)) {
                connectedTower.disconnectClient(this);
                connectedTower = null
            }
        }
    }

    public void CHANGE_OPERATOR(String newOperator) {
        this.operator = newOperator;
        // disconnect if operator cant use this tower
    }
}

public class Network {

}