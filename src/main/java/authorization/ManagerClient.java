package authorization;


import servlets.AccessResourcePath;

import java.util.List;

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
    public static List<String> apiAuthZ(String log, String pass) {
        return checkAuthZ(log, pass);
    }

    public static String apiReg(String log, String pass) {
        return checkReg(log, pass);
    }

    //В этом методе возвращать массив - строка + bool?
    private static List<String> checkAuthZ(String log, String pass) {
        String mess = "", role = "";
        var absolutePathRes = AccessResourcePath.PATH_resources.getPath();
        var readPair = Authorized.authentication(log, pass, absolutePathRes + PATH_BASE);
        for (var map : readPair.entrySet()) {
            role = map.getKey();
            mess = map.getValue() ? messTrueAuthZ : messFalseAuthZ;
        }
        return List.of(mess, role);
    }

    private static String checkReg(String log, String pass) {
        var absolutePathRes = AccessResourcePath.PATH_resources.getPath();
        if (Authorized.createNew(log, pass, absolutePathRes + PATH_BASE)) {
            return messTrueReg;
        }
        return messFalseReg;
    }
}
