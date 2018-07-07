package com.bank.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEParameterSpec;

public class CryptoLibrary
{
        private Cipher encryptCipher;
        private Cipher decryptCipher;
        private sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
        private sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
        
        public static final String key="SEERVI SAMAJ KI JAI HO";
        public CryptoLibrary() throws SecurityException
        {
                java.security.Security.addProvider(new com.sun.crypto.provider.SunJCE());
                char[] pass = key.toCharArray();
                byte[] salt = { (byte) 0xa3, (byte) 0x21, (byte) 0x24, (byte) 0x2c,(byte) 0xf2, (byte) 0xd2, (byte) 0x3e, (byte) 0x19};
                int iterations = 3;
                init(pass, salt, iterations);
        }
        public void init(char[] pass, byte[] salt, int iterations) throws SecurityException
        {
                try
                {
                        PBEParameterSpec ps = new javax.crypto.spec.PBEParameterSpec(salt, 20);
                        SecretKeyFactory kf = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
                        SecretKey k = kf.generateSecret(new javax.crypto.spec.PBEKeySpec(pass));
                        encryptCipher = Cipher.getInstance("PBEWithMD5AndDES/CBC/PKCS5Padding");
                        encryptCipher.init(Cipher.ENCRYPT_MODE, k, ps);
                        decryptCipher = Cipher.getInstance("PBEWithMD5AndDES/CBC/PKCS5Padding");
                        decryptCipher.init(Cipher.DECRYPT_MODE, k, ps);
                }catch (Exception e){
                        throw new SecurityException("Could not initialize CryptoLibrary: " + e.getMessage());
                }
        }
        /**
       
        * convenience method for encrypting a string.
       
        *
       
        * @param str Description of the Parameter
       
        * @return String the encrypted string.
       
        * @exception SecurityException Description of the Exception
       
        */
       
        public synchronized String encrypt(String str) throws SecurityException
        {
                try
                {
                byte[] utf8 = str.getBytes("UTF8");
                byte[] enc = encryptCipher.doFinal(utf8);
                return encoder.encode(enc);
                }
                catch (Exception e)
                {
                throw new SecurityException("Could not encrypt: " + e.getMessage());
                }
        }
        /**
        * convenience method for encrypting a string.
        ** @param str Description of the Parameter
        * @return String the encrypted string.
        * @exception SecurityException Description of the Exception
        */
       
        public synchronized String decrypt(String str) throws SecurityException
        {
                try
                {
                byte[] dec = decoder.decodeBuffer(str);
                byte[] utf8 = decryptCipher.doFinal(dec);
                return new String(utf8, "UTF8");
                }
                catch (Exception e)
                {
                        throw new SecurityException("Could not decrypt: " + e.getMessage());
                }
        }
       
        /**
         * @param args
         */
        public static void main(String[] args)
        {
                try
                {
                CryptoLibrary cl = new CryptoLibrary();
                String user = "mail.jewelbankers.comXj".toUpperCase().trim();
                String pass = "jewelbankers";
                String euser = cl.encrypt(user);
                String epass = cl.encrypt(pass);
                String duser = cl.decrypt("v1NT/KT8Smpamcpp6zzY8Q==");
                String dpass = cl.decrypt("WmpfivQii8ksKIWPRdKn/w==");
                System.out.println("euser: " + " --> " + euser );
                System.out.println("epass: " + " --> " + epass );
                System.out.println("duser" + " --> " + duser );
                System.out.println("dpass: " + " --> " + dpass );
               // System.out.println("Pass: " + pass + " --> " + epass + " --> " + dpass);
                 
                } catch (Exception e){
                        e.printStackTrace();
                }
        }
}
