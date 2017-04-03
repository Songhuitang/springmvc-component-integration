package win.songhuitang.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 * Created by simon.song on 2017/4/3.
 */
public class EncryptUtil {

    private static final Logger logger = LoggerFactory.getLogger(EncryptUtil.class);

    private static final String PASSWORD_ALGORITHM = "AES";
    private static final String PASSWORD_KEY = "1Hbfh667adfDEJ73";

    public static String encrypt(String value){
        Key key = generateKey();
        Cipher cipher;
        String encryptedValue64 = "";
        try {
            cipher = Cipher.getInstance(PASSWORD_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedByteValue = cipher.doFinal(value.getBytes("utf-8"));
            encryptedValue64 = new BASE64Encoder().encode(encryptedByteValue);
        } catch (Exception e) {
            logger.error("!!!!!!!!!!!!!!!!!Error occured when encrypt Password.");
            e.printStackTrace();
        }
        return encryptedValue64;
    }

    public static String decrypt(String value){
        Key key = generateKey();
        String decryptedValue = "";
        try{
            Cipher cipher = Cipher.getInstance(PASSWORD_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decryptedValue64 = new BASE64Decoder().decodeBuffer(value);
            byte[] decryptedByteValue = cipher.doFinal(decryptedValue64);
            decryptedValue = new String(decryptedByteValue, "utf-8");
        }catch(Exception e){
            logger.error("!!!!!!!!!!!!!!!!!Error occured when decrypt Password.");
            e.printStackTrace();
        }
        return decryptedValue;
    }

    private static Key generateKey(){
        Key key = new SecretKeySpec(PASSWORD_KEY.getBytes(), PASSWORD_ALGORITHM);
        return key;
    }

}
