package otokatari.com.otokatari.Utils;
import android.util.Base64;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class HMACSHA256Utils {

    public static String sha256_HMAC(String message) throws NoSuchAlgorithmException,
            InvalidKeyException
    {
        String secret = "FINDLOSTTHINGS-HMAC-ENCODER";
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        byte[] rawHmac = sha256_HMAC.doFinal(message.getBytes());
        return Base64.encodeToString(rawHmac, Base64.NO_WRAP);
    }
}

