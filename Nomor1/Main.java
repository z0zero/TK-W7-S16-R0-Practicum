package Practicum2;

import java.util.Scanner;
import Practicum2.CustomerQueue;

public class Main {
    public static void main(String[] args) {
        CustomerQueue queue = new CustomerQueue();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Sistem Manajemen Antrean ===");
            System.out.println("1. Tambah pelanggan ke antrean");
            System.out.println("2. Layani pelanggan");
            System.out.println("3. Tampilkan antrean");
            System.out.println("0. Keluar");
            System.out.print("Pilih opsi: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Bersihkan newline

            switch (choice) {
                case 1:
                    System.out.print("Masukkan nama pelanggan: ");
                    String name = scanner.nextLine();
                    queue.enqueue(name);
                    break;
                case 2:
                    queue.dequeue();
                    break;
                case 3:
                    queue.displayQueue();
                    break;
                case 0:
                    System.out.println("Terima kasih telah menggunakan sistem.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (choice != 0);
    }
}

