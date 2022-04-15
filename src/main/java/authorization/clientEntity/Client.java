package authorization.clientEntity;

public abstract class Client {
    private final String log;
    private final String Role;

    public Client(String log, String role) {
        this.log = log;
        Role = role;
    }
    public String getLog() {
        return log;
    }

    public String getRole() {
        return Role;
    }

}
