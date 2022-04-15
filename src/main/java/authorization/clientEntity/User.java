package authorization.clientEntity;

public final class User extends Client {
    private static final String ROLE ="USER";
    public User(String log) {
        super(log, ROLE);
    }
}
