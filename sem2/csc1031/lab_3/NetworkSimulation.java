import java.util.ArrayList;
import java.util.List;

public class NetworkSimulation {
    private class CellTower {
        private int id;
        private double x, y;
        private double coverageRadius;
        private String operator;

        private CellTower(int tower_id, double x, double y, double coverage_radius) {
            this.id = tower_id;
            this.x = x;
            this.y = y;
            this.coverageRadius = coverage_radius;
            this.operator = null;
        }
    }

    private class Operator {
        private String name;
        private List<Integer> clientsPhone;
        private List<CellTower> towers;

        private Operator(String operator_name) {
            this.name = operator_name;
            this.clientsPhone = new ArrayList<>();
            this.towers = new ArrayList<>();
        }
    }

    private class Client {
        private int phoneNumber;
        private double x, y;
        private String operatorName;
        private CellTower connectedTower;

        private Client(int phoneNumber, String operatorName, double x, double y) {
            this.phoneNumber = phoneNumber;
            this.operatorName = operatorName;
            this.x = x;
            this.y = y;
            this.connectedTower = null;
        }

        private void move(double new_x, double new_y) {
            double disToTower = Math.sqrt(Math.pow((new_x - this.x), 2) + (Math.pow((new_y - this.y), 2)));
            if (this.connectedTower.coverageRadius < disToTower) {
                this.connectedTower = null;
            }
            this.x = new_x;
            this.y = new_y;
            //  If the phone moves out of the current tower’s range, it should automatically switch to the nearest available tower.
        }

        private void changeOperator(String new_operator_name) {
            this.operatorName = new_operator_name;
            // If the new operator does not support the current tower, the phone should switch to the nearest available tower.
        }
    }

    private class Network {
        private List<CellTower> towers;
        private List<Client> clients;
        private List<Operator> operators;

        private void MOVE_CLIENT(int phoneNumber, double new_x, double new_y) {
            for (Client currClient : this.clients) {
                if (currClient.phoneNumber == phoneNumber) {
                    currClient.move(new_x, new_y);
                }
            }
        }

        private void CHANGE_OPERATOR(int phoneNumber, String new_operator_name) {
            for (Client currClient : this.clients) {
                if (currClient.phoneNumber == phoneNumber) {
                    currClient.changeOperator(new_operator_name);
                }
            }
        }

        private int TOWER_CLIENT_COUNT(int tower_id) {
            int total = 0;
            for (Client currClient : this.clients) {
                if (currClient.connectedTower.id == tower_id) {
                    total += 1;
                }
            }
            return total;
        }

        private int OPERATOR_SUBSCRIBER_COUNT(String operator_name) {
            int total = 0;
            for (Client currClient : this.clients) {
                if (currClient.operatorName == operator_name) {
                    total += 1;
                }
            }
            return total;
        }

        private void ADD_CLIENT(int phone_number, String operator_name, double x, double y) {
            Client new_client = new Client(phone_number, operator_name, x, y);
            this.clients.add(new_client);
        }

        private void REMOVE_CLIENT(int phone_number) {
            for (Client currClient : this.clients) {
                if (currClient.phoneNumber == phone_number) {
                    this.clients.remove(currClient);
                }
            }
        }

        private void ADD_TOWER(int tower_id, double x, double y, double coverage_radius) {
            CellTower new_tower = new CellTower(tower_id, x, y, coverage_radius);
            this.towers.add(new_tower);
        }

        private void REGISTER_OPERATOR_TOWER(String operator_name, int tower_id) {
            for (CellTower currTower : this.towers) {
                if (currTower.id == tower_id) {
                    currTower.operator = operator_name;
                    for (Operator currOperator : this.operators) {
                        if (currOperator.name == operator_name) {
                            currOperator.towers.add(currTower);
                        }
                    }
                }
            }
        }

        private void REMOVE_TOWER(int tower_id) {
            for (CellTower currTower : this.towers) {
                if (currTower.id == tower_id) {
                    this.towers.remove(currTower);
                }
            }
        }

        private int NO_SIGNAL_COUNT() {
            int total = 0;
            return total;
            // Returns the number of phones currently out of signal range for each operator.
        }

        private void ADD_OPERATOR(String operator_name) {
            Operator new_operator = new Operator(operator_name);
            this.operators.add(new_operator);
        }
    }
}