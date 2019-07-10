package otokatari.com.otokatari;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import otokatari.com.otokatari.Utils.RSAUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open("otokatari-public.txt")));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();

            RSAUtils rsa = new RSAUtils(sb.toString(),null);
            String encrypted = rsa.Encrypt("郑泽屁明");

            Log.d("MainActivity",encrypted);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e)
        {
            e.printStackTrace();
        } catch (BadPaddingException e)
        {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e)
        {
            e.printStackTrace();
        } catch (NoSuchPaddingException e)
        {
            e.printStackTrace();
        } catch (InvalidKeyException e)
        {
            e.printStackTrace();
        }
    }

}
