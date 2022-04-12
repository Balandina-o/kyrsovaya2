package authorization;

public final class  Admin extends Client {
    private static final String ROLE ="ADMIN";
    public Admin(String log, String password) {
        super(log, password, ROLE);
    }
}
