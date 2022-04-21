package UtilFiles;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;
import java.util.Base64;

/**
 * @author Artyom Zlygostev
 * @version 1.0
 * Интерфейс шифрование и расшифрования Текста
 */
public interface CipherText {
    /**
     * @see CryptLine#encrypt(String)
     */
    String encrypt(String text) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException;

    /**
     * @see CryptLine#encrypt(String)
     */
    String decrypt(String text) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException;

    /**
     * @param b - зашифрованный массив AES Стандарта.
     * @return - Строка зашифрованного AES в формате Base64
     */
    default String EncBase64(byte[] b) {
        return Base64.getEncoder().encodeToString(b);
    }

    /**
     * @param text - зашифрованный строга в кодировке Base64
     * @return - полностью расшифрованная строка
     */
    default byte[] DecBase64(String text) {
        return Base64.getDecoder().decode(text);
    }
}
