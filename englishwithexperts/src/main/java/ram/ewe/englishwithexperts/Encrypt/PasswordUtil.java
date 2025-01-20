package ram.ewe.englishwithexperts.Encrypt;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil
{
    public static String generateHash(String password)
    {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }

    public static boolean checkPassword(String password, String hashArmazenado)
    {
        return BCrypt.checkpw(password, hashArmazenado);
    }
}
