import java.util.Scanner;

public class CRC {
    public static void main (String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter data stream: ");
        String datastream = sc.nextLine();

        System.out.print("Enter generator: ");
        String generator = sc.nextLine();

        int l1 = datastream.length();
        int l2 = generator.length();
        int l3 = l1 + l2 - 1;

        int data[] = new int[l3];
        int divisor[] = new int[l2];

        for (int i = 0; i < l1; i++)
            data[i] = Integer.parseInt(datastream.charAt(i) + "");

        for (int i = 0; i < l2; i++)
            divisor[i] = Integer.parseInt(generator.charAt(i) + "");

        for (int i = 0; i < l1; i++)
            if (data[i] == 1)
                for (int j = 0; j < divisor.length; j++)
                    data[i+j] ^= divisor[j];

        System.out.print("The CRC code is: ");
        for (int i = 0; i < l1; i++)
            data[i] = Integer.parseInt(datastream.charAt(i) + "");

        for (int i = 0; i < data.length; i++)
            System.out.print(data[i]);
        System.out.println();

        System.out.print("Enter CRC Code: ");
        datastream = sc.nextLine();

        System.out.print("Enter generator: ");
        generator = sc.nextLine();

        int l4 = datastream.length();
        int l5 = generator.length();
        int l6 = l4 + l5 - 1;

        data = new int[l6];
        divisor = new int[l5];

        for (int i = 0; i < l4; i++)
            data[i] = Integer.parseInt(datastream.charAt(i) + "");

        for (int i = 0; i < l5; i++)
            divisor[i] = Integer.parseInt(generator.charAt(i) + "");

        for (int i = 0; i < l4; i++)
            if (data[i] == 1)
                for (int j = 0; j < divisor.length; j++)
                    data[i+j] ^= divisor[j];

        boolean valid = true;
        for (int i = 0; i < data.length; i ++)
            if (data[i] == 1) {
                valid = false;
                break;
            }

        if (valid) System.out.println("Data stream is valid.");
        else System.out.println("Data stream is invalid. CRC error occured.");
    }
}

/*
 * Output 1:
 * Enter data stream: 11011
 * Enter generator: 100
 * The CRC code is: 1101100
 * Enter CRC Code: 1101100
 * Enter generator: 100
 * Data stream is valid.
 *
 * Output 2:
 * Enter data stream: 11011
 * Enter generator: 100
 * The CRC code is: 1101100
 * Enter CRC Code: 11011001
 * Enter generator: 101
 * Data stream is invalid. CRC error occured.
 */