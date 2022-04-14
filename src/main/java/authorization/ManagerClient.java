package authorization;

/**
 * @author Artyom
 *  / Класс через который нужно обращаться к функциям входа, регистрации,аутентификации.
 */
public class ManagerClient  {
    //Путь до "базы данных"
    private static final String PATH_BASE = "./src/main/resources/BaseLogPass";

    /**
     * @param log - логин
     * @param pass - пароль
     * @return - true - вошел / false - нет
     */
    public static boolean apiAuthZ(String log, String pass) {
        return Authorized.authentication(log, pass, PATH_BASE);
    }
    public static boolean apiReg(String log,String pass){
        return "Зарегистрирован".equals(Authorized.createNew(log, pass, PATH_BASE));
    }
}
