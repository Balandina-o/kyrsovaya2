package authorization;

public final class User extends Client {
    private static final String ROLE ="USER";
    public User(String log, String password) {
        super(log, password, ROLE);
    }
}
