package enums;

import UtilFiles.PairFromFile;

import java.nio.file.Path;
import java.util.LinkedHashMap;

/**
 * Класс для чтения пары - сумма из ComboBox и номера константы
 * /TODO Не для Админа
 */
public class ReaderCoff {
    private static final String PATH = "./src/main/resources/enumSumFromComboBox";
    private static LinkedHashMap<Integer, Integer> coff = null;

    private ReaderCoff() {
    }
    private static void readCoff() {
        PairFromFile files = new PairFromFile();
        var readPair = files.readFileAsPair(Path.of(PATH));
            for (var entry : readPair.entrySet()) {
                coff.put(Integer.valueOf(entry.getKey()), Integer.valueOf(entry.getValue()));
            }
    }

    /**
     * @return - Возвращает ссылку на массив суммой
     */
    protected static LinkedHashMap<Integer, Integer> getMassCoff() {
        if (coff == null) {
            coff = new LinkedHashMap<>();
            readCoff();
        }
        return coff;
    }
}
