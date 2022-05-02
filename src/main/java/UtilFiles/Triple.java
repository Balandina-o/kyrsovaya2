package UtilFiles;


/**
 * @author Artyom Zlygostev
 * <br> Класс для удобного хранения данных при чтении
 * @param <Log> Тип логина
 * @param <Pass> Тип пароля
 * @param <Role> Тип роли
 */
public class Triple< Log extends String, Pass extends String, Role extends String> {
     private final Log login;
     private final Pass pass;
    private final Role role;

    public Triple(Log login, Pass pass, Role role) {
        this.login = login;
        this.pass = pass;
        this.role = role;
    }

    public Log getLogin() {
        return login;
    }

    public Pass getPass() {
        return pass;
    }


    public Role getRole() {
        return role;
    }
    @Override
    public String toString() {
        return String.format("login: %s, pass: %s, Role: %s",login,pass,role);
    }
}
