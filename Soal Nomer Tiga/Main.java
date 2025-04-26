import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;
import java.util.Scanner;

class Mahasiswa {
    private String nimMahasiswa;
    private String namaMahasiswa;
    private int nilaiMahasiswa;

    public Mahasiswa(String nim, String nama, int nilai) {
        this.nimMahasiswa = nim;
        this.namaMahasiswa = nama;
        this.nilaiMahasiswa = nilai;
    }

    public String getNim() { return nimMahasiswa; }
    public String getNama() { return namaMahasiswa; }
    public int getNilai() { return nilaiMahasiswa; }
    public void setNilai(int nilai) { this.nilaiMahasiswa = nilai; }

    @Override
    public String toString() {
        return "NIM: " + nimMahasiswa + ", Nama: " + namaMahasiswa + ", Nilai: " + nilaiMahasiswa;
    }
}

class Node {
    Mahasiswa data;
    Node next;

    public Node(Mahasiswa data) {
        this.data = data;
        this.next = null;
    }
}

class MahasiswaLinkedList {
    private Node head;
    private Stack<String> undoStack = new Stack<>();
    private Queue<Mahasiswa> printQueue = new LinkedList<>();

    // 1. Menambahkan mahasiswa baru
    public void tambahMahasiswa(Mahasiswa m) {
        long startTime = System.nanoTime();
        
        Node newNode = new Node(m);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        undoStack.push("TAMBAH:" + m.getNim());
        printQueue.add(m);
        
        long endTime = System.nanoTime();
        System.out.println("Mahasiswa berhasil ditambahkan! (Waktu eksekusi: " + (endTime - startTime) + " ns)");
    }

    // 2. Menghapus mahasiswa berdasarkan NIM
    public void hapusMahasiswa(String nim) {
        long startTime = System.nanoTime();
        
        if (head == null) {
            System.out.println("Daftar mahasiswa kosong!");
            return;
        }

        if (head.data.getNim().equals(nim)) {
            undoStack.push("HAPUS:" + head.data.getNim() + ":" + head.data.getNama() + ":" + head.data.getNilai());
            head = head.next;
            System.out.println("Mahasiswa berhasil dihapus! (Waktu eksekusi: " + (System.nanoTime() - startTime) + " ns)");
            return;
        }

        Node current = head;
        while (current.next != null && !current.next.data.getNim().equals(nim)) {
            current = current.next;
        }

        if (current.next != null) {
            undoStack.push("HAPUS:" + current.next.data.getNim() + ":" + current.next.data.getNama() + ":" + current.next.data.getNilai());
            current.next = current.next.next;
            System.out.println("Mahasiswa berhasil dihapus! (Waktu eksekusi: " + (System.nanoTime() - startTime) + " ns)");
        } else {
            System.out.println("Mahasiswa dengan NIM " + nim + " tidak ditemukan!");
        }
    }

    // 3. Mengupdate nilai mahasiswa
    public void updateNilai(String nim, int nilaiBaru) {
        long startTime = System.nanoTime();
        
        Node current = head;
        while (current != null) {
            if (current.data.getNim().equals(nim)) {
                undoStack.push("UPDATE:" + current.data.getNim() + ":" + current.data.getNilai());
                current.data.setNilai(nilaiBaru);
                System.out.println("Nilai berhasil diupdate! (Waktu eksekusi: " + (System.nanoTime() - startTime) + " ns)");
                return;
            }
            current = current.next;
        }
        System.out.println("Mahasiswa dengan NIM " + nim + " tidak ditemukan!");
    }

    // 4. Menampilkan daftar mahasiswa
    public void tampilkanDaftar() {
        long startTime = System.nanoTime();
        
        System.out.println("\nDaftar Mahasiswa:");
        if (head == null) {
            System.out.println("Daftar kosong!");
            return;
        }

        Node current = head;
        int counter = 1;
        while (current != null) {
            System.out.println(counter + ". " + current.data);
            current = current.next;
            counter++;
        }
        
        System.out.println("(Waktu eksekusi: " + (System.nanoTime() - startTime) + " ns)");
    }

    // 5. Undo operasi terakhir
    public void undo() {
        long startTime = System.nanoTime();
        
        if (undoStack.isEmpty()) {
            System.out.println("Tidak ada operasi yang dapat diundo!");
            return;
        }

        String[] action = undoStack.pop().split(":");
        String operation = action[0];
        
        switch (operation) {
            case "TAMBAH":
                hapusMahasiswa(action[1]);
                undoStack.pop(); // Remove the generated HAPUS action
                System.out.println("Undo penambahan mahasiswa dengan NIM " + action[1]);
                break;
            case "HAPUS":
                Mahasiswa m = new Mahasiswa(action[1], action[2], Integer.parseInt(action[3]));
                tambahMahasiswa(m);
                undoStack.pop(); // Remove the generated TAMBAH action
                System.out.println("Undo penghapusan mahasiswa " + action[2]);
                break;
            case "UPDATE":
                updateNilai(action[1], Integer.parseInt(action[2]));
                undoStack.pop(); // Remove the generated UPDATE action
                System.out.println("Undo update nilai mahasiswa dengan NIM " + action[1]);
                break;
        }
        
        System.out.println("(Waktu eksekusi: " + (System.nanoTime() - startTime) + " ns)");
    }

    // 6. Cetak menggunakan Queue
    public void cetakMenggunakanQueue() {
        long startTime = System.nanoTime();
        
        System.out.println("\nMencetak menggunakan Queue:");
        Queue<Mahasiswa> tempQueue = new LinkedList<>(printQueue);
        int counter = 1;
        while (!tempQueue.isEmpty()) {
            System.out.println(counter + ". " + tempQueue.poll());
            counter++;
        }
        
        System.out.println("(Waktu eksekusi: " + (System.nanoTime() - startTime) + " ns)");
    }
}

public class Main {
    public static void main(String[] args) {
        MahasiswaLinkedList list = new MahasiswaLinkedList();
        Scanner scanner = new Scanner(System.in);
        int pilihan;

        // Data contoh untuk demonstrasi
        list.tambahMahasiswa(new Mahasiswa("12345", "Agus", 88));
        list.tambahMahasiswa(new Mahasiswa("67890", "Mulyadi4", 72));

        do {
            System.out.println("\n=== SISTEM AKADEMIK MAHASISWA ===");
            System.out.println("1. Tambah Mahasiswa");
            System.out.println("2. Hapus Mahasiswa");
            System.out.println("3. Update Nilai Mahasiswa");
            System.out.println("4. Tampilkan Daftar Mahasiswa");
            System.out.println("5. Undo Operasi Terakhir");
            System.out.println("6. Cetak Menggunakan Queue");
            System.out.println("0. Keluar");
            System.out.print("Pilihan: ");
            pilihan = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan NIM: ");
                    String nim = scanner.nextLine();
                    System.out.print("Masukkan Nama: ");
                    String nama = scanner.nextLine();
                    System.out.print("Masukkan Nilai: ");
                    int nilai = scanner.nextInt();
                    list.tambahMahasiswa(new Mahasiswa(nim, nama, nilai));
                    break;
                case 2:
                    System.out.print("Masukkan NIM mahasiswa yang akan dihapus: ");
                    String nimHapus = scanner.nextLine();
                    list.hapusMahasiswa(nimHapus);
                    break;
                case 3:
                    System.out.print("Masukkan NIM mahasiswa yang akan diupdate: ");
                    String nimUpdate = scanner.nextLine();
                    System.out.print("Masukkan Nilai Baru: ");
                    int nilaiBaru = scanner.nextInt();
                    list.updateNilai(nimUpdate, nilaiBaru);
                    break;
                case 4:
                    list.tampilkanDaftar();
                    break;
                case 5:
                    list.undo();
                    break;
                case 6:
                    list.cetakMenggunakanQueue();
                    break;
                case 0:
                    System.out.println("Keluar dari program...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 0);

        scanner.close();
    }
}