package enums;

import UtilFiles.ClearRes;
import UtilFiles.ReadFromFile;
import servlets.AccessResourcePath;

import java.util.LinkedHashMap;

/**
 * Класс для чтения пары - сумма из ComboBox и номера константы
 * /TODO Не для Админа
 */
public class ReaderCoff implements ClearRes {
    private static final String PATH = "/enumSumFromComboBox";
    private static LinkedHashMap<Integer, Integer> coff = null;
    private static ReaderCoff Instance = null;

    private ReaderCoff() {
    }

    private static void readCoff() {
        ReadFromFile files = new ReadFromFile();
        var readPair = files.readFileAsPair((AccessResourcePath.PATH_resources.getPath() + PATH));
        for (var entry : readPair.entrySet()) {
            coff.put(Integer.valueOf(entry.getKey()), Integer.valueOf(entry.getValue()));
        }
    }

    /**
     * @return - Возвращает ссылку на массив суммой
     */
    public LinkedHashMap<Integer, Integer> getMassCoff() {
        if (coff == null) {
            coff = new LinkedHashMap<>();
            readCoff();
        }
        return coff;
    }

    public static ReaderCoff Instance() {
        if (Instance == null) {
            Instance = new ReaderCoff();
        }
        return Instance;
    }

    @Override
    public void clearEntity() {
        coff=null;
        Instance = null;
    }
}
