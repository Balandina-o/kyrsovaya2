package enums;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

/**
 * Класс для чтения пары - сумма из ComboBox и номера константы
 * /TODO Не для Админа
 */
public class ReaderCoff {
    private static final String PATH = "./src/main/resources/enumSumFromComboBox";
    private static HashMap<Integer, Integer> coff = null;

    private ReaderCoff() {
    }
    private static void readCoff() {
        try (BufferedReader br = Files.newBufferedReader(Path.of(PATH))) {
            String strLine;
            while ((strLine = br.readLine()) != null) {
                String[] tempArr = strLine.split(";");
                coff.put(Integer.valueOf(tempArr[0]), Integer.valueOf(tempArr[1]));
            }
        } catch (IOException e) {
            System.out.println("Нет файла для чтения ReaderCoff");
        }
    }

    /**
     * @return - Возвращает ссылку на массив суммой
     */
    protected static HashMap<Integer, Integer> getMassCoff() {
        if (coff == null) {
            coff = new HashMap<>();
            readCoff();
        }
        return coff;
    }
}
