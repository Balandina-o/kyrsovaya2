package UtilFiles;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;

/**
 * @author Artyom
 * @version 1.0
 */
public class CryptLine implements CipherText {
    private final byte[] pass = {73, 76, 48, 55, 113, 65, 103, 72, 113, 54, 50, 90, 81, 99, 112, 78};
    SecretKeySpec key;
    Cipher cipher;

    public CryptLine() {
        init();
    }

    /**
     * Инициализация шифрования для ключа и шифра.
     */
    private void init() {
        try {
            this.key = new SecretKeySpec(pass, "AES");
            this.cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param text - текст который нужно зашифровать UTF-8 кодировка
     * @return зашифрованный текст в кодировке Base64 для файла
     * @throws InvalidKeyException недопустимых ключей (неверная кодировка, неправильная длина).
     * @throws IllegalBlockSizeException, а длина данных не соответствует размеру блока шифра.
     * @throws BadPaddingException данные не заполняются должным образом.
     */
    @Override
    public String encrypt(String text) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] CipherText = text.getBytes(StandardCharsets.UTF_8);
        return EncBase64(cipher.doFinal(CipherText));

    }

    /**
     * @param text - текст который нужно расшифровать UTF-8 кодировка
     * @return полностью расшифрованный текст в кодировке UTF-8
     * @throws InvalidKeyException недопустимых ключей (неверная кодировка, неправильная длина).
     * @throws IllegalBlockSizeException длина данных не соответствует размеру блока шифра.
     * @throws BadPaddingException данные не заполняются должным образом.
     */
    @Override
    public String decrypt(String text) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] decBase64 = DecBase64(text);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] dec = cipher.doFinal(decBase64);
        return new String(dec, StandardCharsets.UTF_8);
    }
}
