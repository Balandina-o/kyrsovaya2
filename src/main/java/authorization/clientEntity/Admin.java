package authorization.clientEntity;

public final class  Admin extends Client {
    private static final String ROLE ="ADMIN";
    public Admin(String log) {
        super(log, ROLE);
    }
}
