package authorization;


import servlets.AccessResourcePath;

/**
 * @author Artyom
 * / Класс через который нужно обращаться к функциям входа, регистрации,аутентификации.
 */
public class ManagerClient {
    //Путь до "базы данных"
    private static final String PATH_BASE = "/BaseLogPass";

    private static final String messTrueAuthZ = "Вы вошли";
    private static final String messFalseAuthZ = "Неверные данные для входа";
    private static final String messTrueReg = "Зарегистрирован";
    private static final String messFalseReg = "Логин занят";
//    private static final String messTrueAuthZ="";

    /**
     * @param log  - логин
     * @param pass - пароль
     * @return - true - вошел / false - нет
     */
    public static String apiAuthZ(String log, String pass) {
        return checkAuthZ(log, pass);
    }

    public static String apiReg(String log, String pass) {
        return checkReg(log, pass);
    }

    //В этом методе возвращать массив - строка + bool?
    private static String checkAuthZ(String log, String pass) {
       var fullPath = AccessResourcePath.PATH_resources.getPath();
        return Authorized.authentication(log, pass, fullPath+PATH_BASE) ? messTrueAuthZ : messFalseAuthZ;
    }

    private static String checkReg(String log, String pass) {
        var x = AccessResourcePath.PATH_resources.getPath();
        if (Authorized.createNew(log, pass, x+PATH_BASE)) {
            return messTrueReg;
        }
        return messFalseReg;
    }
}
