package enums;

import UtilFiles.PairFromFile;
import servlets.AccessResourcePath;
import java.util.LinkedHashMap;

/**
 * Класс для чтения пары - сумма из ComboBox и номера константы
 * /TODO Не для Админа
 */
public class ReaderCoff {
    private static final String PATH = "/enumSumFromComboBox";
    private static LinkedHashMap<Integer, Integer> coff = null;

    private ReaderCoff() {
    }

    private static void readCoff() {
        PairFromFile files = new PairFromFile();
        var readPair = files.readFileAsPair((AccessResourcePath.PATH_resources.getPath()+PATH));
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
