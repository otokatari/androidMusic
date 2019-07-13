package otokatari.com.otokatari.Utils;

import android.util.Base64;
import android.util.Log;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;


public class RSAUtils
{
    private static final String TAG = "RSAUtils";
    private static final String KeyPadding = "RSA/None/PKCS1Padding";
    private static RSAPublicKey PublicKeyInstance;
    private static PrivateKey PrivateKeyInstance;
    
    public RSAUtils(String PublicKey, String PrivateKey) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        InitRSAKeys(PublicKey, PrivateKey);
    }

    public RSAUtils(byte[] PublicKeyBytes, byte[] PrivateKeyBytes) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        this(new String(PublicKeyBytes, StandardCharsets.UTF_8),
                PrivateKeyBytes != null ? new String(PrivateKeyBytes, StandardCharsets.UTF_8) : null);
    }

    public String Encrypt(String message) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException
    {
        if (PublicKeyInstance == null)
        {
            throw new IllegalArgumentException("Public key has not been initialized!");
        }
        Cipher cipher = Cipher.getInstance(KeyPadding, new BouncyCastleProvider());// Specify key padding format and encrypt implementation provider.
        cipher.init(Cipher.ENCRYPT_MODE, PublicKeyInstance);
        byte[] encrypted = cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
        return Base64.encodeToString(encrypted, Base64.DEFAULT);
    }

    public String Decrypt(String EncryptedMessage) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException
    {
        if (PrivateKeyInstance == null)
        {
            throw new IllegalArgumentException("Private key has not been initialized!");
        }

        byte[] encrypted = Base64.decode(EncryptedMessage, Base64.DEFAULT);
        Cipher cipher = Cipher.getInstance(KeyPadding, new BouncyCastleProvider());
        cipher.init(Cipher.DECRYPT_MODE, PrivateKeyInstance);

        return new String(cipher.doFinal(encrypted), StandardCharsets.UTF_8);
    }

    public void InitRSAKeys(String PublicKey, String PrivateKey) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        PublicKey = PublicKey.replaceAll("\\n", "").replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", "");
        KeyFactory kf = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec PublicKeyX509 = new X509EncodedKeySpec(Base64.decode(PublicKey, Base64.DEFAULT));
        PublicKeyInstance = (RSAPublicKey) kf.generatePublic(PublicKeyX509);

        Log.d(TAG, "Loading public key finished!");

        if (PrivateKey != null)
        {
            PKCS8EncodedKeySpec PrivateKeySpec = new PKCS8EncodedKeySpec(Base64.decode(PrivateKey, Base64.DEFAULT));
            PrivateKeyInstance = kf.generatePrivate(PrivateKeySpec);
            Log.d(TAG, "Loading private key finished!");
        }
    }
}
