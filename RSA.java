import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

public class RSA {
    private BigInteger p, q, N, phi, e, d;
    private int bitLength;
    private Random r;

    public RSA () {
        r = new Random();
        bitLength = 1024;

        p = BigInteger.probablePrime(bitLength, r);
        q = BigInteger.probablePrime(bitLength, r);
        e = BigInteger.probablePrime(bitLength / 2, r);

        N = p.multiply(q); // N = p * q
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        // phi = (p - 1) * (q - 1);

        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0  // gcd(phi, e) > 1
                                && e.compareTo(phi) < 0) // e < phi
            e.add(BigInteger.ONE); // e = e + 1

        d = e.modInverse(phi); // d = e^-1 % phi
    }

    public RSA (BigInteger e, BigInteger d, BigInteger N) {
        this.e = e;
        this.d = d;
        this.N = N;
        this.bitLength = 1024;
    }

    public static void main(String[] args) throws IOException {
        RSA rsa = new RSA();
        DataInputStream in = new DataInputStream(System.in);

        System.out.print("Enter the plain text: ");
        String testString = in.readLine();

        System.out.println("Encrypting string: " + testString);
        System.out.println("String in bytes: " + bytesToString(testString.getBytes()));

        byte[] encrypted = rsa.encrypt(testString.getBytes());
        byte[] decrypted = rsa.decrypt(encrypted);

        System.out.println("Decrypting bytes: " + bytesToString(decrypted));
        System.out.println("Decrypted string: " + new String(decrypted));
    }

    private static String bytesToString(byte[] encrypted) {
        String s = "";
        for (byte b: encrypted) s += Byte.toString(b);
              // same as method name ^^^^^^^^^^^^^
        return s;
    }

    public byte[] encrypt(byte[] msg) {
        return (new BigInteger(msg)).modPow(e, N).toByteArray();
                           //  encrypt -> e ^
    }

    public byte[] decrypt(byte[] msg) {
        return (new BigInteger(msg)).modPow(d, N).toByteArray();
                          //  dencrypt -> d ^
    }
}

/* 
 * Output:
 * Enter the plain text: hello world
 * Encrypting string: hello world
 * String in bytes: 10410110810811132119111114108100
 * Decrypting bytes: 10410110810811132119111114108100
 * Decrypted string: hello world
 */