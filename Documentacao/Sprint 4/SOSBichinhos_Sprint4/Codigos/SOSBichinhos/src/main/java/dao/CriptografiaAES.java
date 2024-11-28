package dao;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class CriptografiaAES {
    private static final String ALGORITMO = "AES";
    private static final String CHAVE_SECRETA = "1234567890123456"; // 16 caracteres

    // Criptografa a senha
    public static String criptografar(String senha) throws Exception {
        SecretKeySpec chave = new SecretKeySpec(CHAVE_SECRETA.getBytes(), ALGORITMO);
        Cipher cipher = Cipher.getInstance(ALGORITMO);
        cipher.init(Cipher.ENCRYPT_MODE, chave);
        byte[] senhaCriptografada = cipher.doFinal(senha.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(senhaCriptografada);
    }

    // Descriptografa a senha
    public static String descriptografar(String senhaCriptografada) throws Exception {
        SecretKeySpec chave = new SecretKeySpec(CHAVE_SECRETA.getBytes(), ALGORITMO);
        Cipher cipher = Cipher.getInstance(ALGORITMO);
        cipher.init(Cipher.DECRYPT_MODE, chave);
        byte[] senhaDecodificada = Base64.getDecoder().decode(senhaCriptografada);
        return new String(cipher.doFinal(senhaDecodificada), "UTF-8");
    }
}
