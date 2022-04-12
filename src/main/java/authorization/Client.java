package authorization;

public abstract class Client {
    private final String log;
    private final String password;
    private final String Role;

    public Client(String log, String password, String role) {
        this.log = log;
        this.password = password;
        Role = role;
    }
    public String getLog() {
        return log;
    }

    public String getRole() {
        return Role;
    }

}
