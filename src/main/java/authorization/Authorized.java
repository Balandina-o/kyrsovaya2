package authorization;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

 interface Authorized {
    //TODO отвечает за авторизацию - подумать куда вынести
    boolean isEmpty = false;

    /**
     * Метод проверяет пользователя в базе
     * / FIXME: 11.04.2022 изменить при базе данных
     * / TODO изменить чтение? поменять на protected / default / поменять тип на String
     **/
      static  boolean authentication(String log, String password,String path) {
//        boolean abb = isCorrectAuth(log, password); //FIXME при абстрактном
        try (BufferedReader br = Files.newBufferedReader(Path.of(path))) {
            String strLine;
            while ((strLine = br.readLine()) != null) {
                String[] tempArr = strLine.split(";");
                if ((Objects.equals(log, tempArr[0])) & (Objects.equals(password, tempArr[1])))
                    return !isEmpty;
            }
        } catch (IOException e) {
            System.out.println("Нет файла для чтения");
        }
        return isEmpty;   //& abb;
    }

    /**
     * Метод проверяет корректность данных чтобы не было пробелов
     * /FIXME: 11.04.2022 изменить/удалить при абстрактных
     **/
//     static boolean isCorrectAuth(String login, String password) {
//        String log = login.trim(); // вынести в Абстрактный
//        String pass = password.trim(); // вынести в Абстрактный
//        return (!log.contains(" ") & !pass.contains(" "));
//    }

    /**
     * Метод регистрации в базе
     * / FIXME:  11.04.2022 изменить при добавлении базы данных
     * / TODO Вынести сообщения в отдельный метод / пропускать чтение если false
     **/
    static boolean createNew(String login, String password,String path) {
        boolean ind = checkLoginInBase(login,path);
        if (ind) { // запись в базу если не занят логин
            try (var br = Files.newBufferedWriter(Path.of(path), StandardOpenOption.APPEND)) {
                br.write("\n" + login + ";" + password);
            } catch (Exception e) {
                System.out.println("нет файла для записи");
            }
        }
        return ind;
    }

    /**
     * Метод проверяет логин на уникальность в базе данных
     * / FIXME изменить при добавлении базы данных
     * / TODO  изменить чтение строки на символы. читать до разделителя /поменять тип на String
     */
    private static boolean checkLoginInBase(String login,String path) {
        int count = 0; // count служит переменной уникальностью
        try (BufferedReader br = Files.newBufferedReader(Path.of(path))) {
            String strLine;
            //чтение строки из базы
            while ((strLine = br.readLine()) != null) {
                // разделение строки на log и pass
                String[] tempArr = strLine.split(";");
                if (Objects.equals(login, tempArr[0])) {
                    count++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count == 0;
    }
}
