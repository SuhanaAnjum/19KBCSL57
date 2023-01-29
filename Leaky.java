import java.util.Scanner;

public class Leaky {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int no_groups, bucket_size;

        System.out.print("Enter the bucket size: ");
        bucket_size = sc.nextInt();

        System.out.print("Enter the number of groups: ");
        no_groups = sc.nextInt();

        int no_packets[] = new int[no_groups];
        int in_bandwidth[] = new int[no_groups];

        int out_bandwidth;
        int required_bandwidth = 0;
        int total_packets = 0;

        for (int i = 0; i < no_groups; i++) {
            System.out.print("Enter the number of packets for group " + (i + 1) + ": ");
            no_packets[i] = sc.nextInt();

            System.out.print("Enter the input bandwidth for the group " + (i + 1) + ": ");
            in_bandwidth[i] = sc.nextInt();

            if ((total_packets + no_packets[i]) <= bucket_size)
                total_packets += no_packets[i];
            else {
                do {
                    System.out.println("Bucket overflow");
                    System.out.print("Enter value less than " + (bucket_size - total_packets) + ": ");
                    no_packets[i] = sc.nextInt();
                } while ((total_packets + no_packets[i]) > bucket_size);

                total_packets += no_packets[i];
            }
            required_bandwidth += (no_packets[i] * in_bandwidth[i]);
        }

        System.out.println("The total required bandwidth is: " + required_bandwidth);
        System.out.print("Enter the output bandwidth: ");
        out_bandwidth = sc.nextInt();

        int temp = required_bandwidth;
        int remaining_packets = total_packets;

        while (out_bandwidth <= temp && remaining_packets > 0) {
            System.out.println("Data sent\n" + (--remaining_packets) + " packets remaining");
            System.out.println("Remaining bandwidth " + (--temp)); // option 1
            // System.out.println("Remaining bandwidth " + (temp -= out_bandwidth)); // option 2

            if (out_bandwidth > temp && remaining_packets > 0)
                System.out.println(remaining_packets + " packet(s) discarded due to insufficient bandwidth");
        }
    }
}

/*
 * Output:
 *  Enter the bucket size: 3
 *  Enter the number of groups: 2
 *  Enter the number of packets for group 1: 1
 *  Enter the input bandwidth for the group 1: 2
 *  Enter the number of packets for group 2: 3
 *  Enter the input bandwidth for the group 2: 1
 *  Bucket overflow
 *  Enter value less than 2: 1
 *  The total required bandwidth is: 3
 *  Enter the output bandwidth: 2
 *  Data sent 
 *
 *  // option 1
 *  1 packets remaining
 *  Remaining bandwidth 2
 *  Data sent
 *  0 packets remaining
 *  Remaining bandwidth 1
 *
 *  // option 2
 *  1 packets remaining
 *  Remaining bandwidth 1
 *  1 packet(s) discarded due to insufficient bandwidth
 */