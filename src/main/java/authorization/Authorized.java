package authorization;

import UtilFiles.CipherText;
import UtilFiles.CryptLine;
import UtilFiles.PairFromFile;
import UtilFiles.ReadFile;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

interface Authorized {
    //TODO отвечает за авторизацию - подумать куда вынести
    Map<String, Boolean> INCORRECT = Map.of("EMPTY", false);

    /**
     * Метод проверяет пользователя в базе
     * / FIXME: 11.04.2022 изменить при базе данных
     * / TODO изменить чтение? поменять на protected / default / поменять тип на String
     **/
    static HashMap<String, Boolean> authentication(String log, String password, String path) {

        HashMap<String, Boolean> clientData = new HashMap<>(INCORRECT);

        ReadFile files = new PairFromFile();
        var readTriple = files.readFileAsTriple(path);
        CipherText line = new CryptLine();
        try {
            for (var list : readTriple) {
                String check = line.decrypt(list.getPass());
                if (Objects.equals(list.getLogin(), log) & (Objects.equals(check, password))) {
                    clientData.clear();
                    clientData.put(list.getRole(), true);
                    return clientData;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientData;   //& abb;
    }

    /**
     * Метод регистрации в базе
     * / FIXME:  11.04.2022 изменить при добавлении базы данных
     * / TODO Вынести сообщения в отдельный метод / пропускать чтение если false
     **/
    static boolean createNew(String login, String password, String path) {
        String def_role = "USER";
        boolean ind = findByLogin(login, path);
        if (ind) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(String.valueOf(Path.of(path)), true))) {
                CipherText line = new CryptLine();
                //Шифрование пароля при создании.
                String cipherPass = line.encrypt(password);
                //печать в файл с новой строки
                writer.println(String.format("%s;%s;%s", login, cipherPass, def_role));
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
        var readPair = files.readFileAsTriple(path);
        for (var LogInFile : readPair) {
            if (Objects.equals(LogInFile.getLogin(), login)) {
                count++;
            }
        }
        return count == 0;
    }
}
