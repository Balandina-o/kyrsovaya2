package authorization;

import UtilFiles.CipherText;
import UtilFiles.CryptLine;
import UtilFiles.PairFromFile;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Path;
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
        PairFromFile files = new PairFromFile();
        var readPair = files.readFileAsPair(Path.of(path));
        CipherText line = new CryptLine();
        try {
            for (var entry : readPair.entrySet()) {
                String check = line.decrypt(entry.getValue());
                if (Objects.equals(entry.getKey(), log) & (Objects.equals(check, password))) {
                    return !CORRECT_AUTH;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CORRECT_AUTH;   //& abb;
    }

    /**
     * Метод регистрации в базе
     * / FIXME:  11.04.2022 изменить при добавлении базы данных
     * / TODO Вынести сообщения в отдельный метод / пропускать чтение если false
     **/
    static boolean createNew(String login, String password, String path) {
        boolean ind = findByLogin(login, path);
        if (ind) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(path, true))) {
                CipherText line = new CryptLine();
                //Шифрование пароля при создании.
                String cipherPass = line.encrypt(password);
                //печать в файл с новой строки
                writer.println(String.format("%s;%s", login, cipherPass));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ind;
    }

    /**
     * Метод проверяет логин на уникальность в базе данных
     * / FIXME изменить при добавлении базы данных
     * / TODO  изменить чтение строки на символы. читать до разделителя /поменять тип на String
     */
    private static boolean findByLogin(String login, String path) {
        int count = 0; // count служит переменной уникальностью
        PairFromFile files = new PairFromFile();
        var readPair = files.readFileAsPair(Path.of(path));
        for (String LogInFile : readPair.keySet()) {
            if (Objects.equals(LogInFile, login)) {
                count++;
            }
        }
        return count == 0;
    }
}
