package authorization;

import UtilFiles.ChangeFiles;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

interface Authorized {
    //TODO отвечает за авторизацию - подумать куда вынести
    boolean CORRECT_AUTH = false;

    /**
     * Метод проверяет пользователя в базе
     * / FIXME: 11.04.2022 изменить при базе данных
     * / TODO изменить чтение? поменять на protected / default / поменять тип на String
     **/
    static boolean authentication(String log, String password, String path) {
//        boolean abb = isCorrectAuth(log, password); //FIXME при абстрактном
        ChangeFiles files = new ChangeFiles();
        var readPair = files.readFileAsPair(Path.of(path));
        for (var entry : readPair.entrySet()) {
            if (Objects.equals(entry.getKey(), log) & (Objects.equals(entry.getValue(), password))) {
                return !CORRECT_AUTH;
            }
        }
        return CORRECT_AUTH;   //& abb;
    }
    /**
     * Метод регистрации в базе
     * / FIXME:  11.04.2022 изменить при добавлении базы данных
     * / TODO Вынести сообщения в отдельный метод / пропускать чтение если false
     **/
    static boolean createNew(String login, String password, String path) {
        boolean ind = checkLoginInBase(login, path);
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
    private static boolean checkLoginInBase(String login, String path) {
        int count = 0; // count служит переменной уникальностью

        ChangeFiles files = new ChangeFiles();
        var readPair = files.readFileAsPair(Path.of(path));
        for (String LogInFile : readPair.keySet()) {
            if (Objects.equals(LogInFile, login)) {
                count++;
            }
        }
        return count == 0;
    }
}
