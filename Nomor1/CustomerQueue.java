package Practicum2;

import Practicum2.Customer;

public class CustomerQueue {
  private Customer front, rear;

  public CustomerQueue() {
      this.front = this.rear = null;
  }

  // Tambah pelanggan ke antrean
  public void enqueue(String name) {
      Customer newCustomer = new Customer(name);
      if (rear == null) {
          front = rear = newCustomer;
      } else {
          rear.next = newCustomer;
          rear = newCustomer;
      }
      System.out.println(name + " ditambahkan ke dalam antrean.");
  }

  // Layani pelanggan
  public void dequeue() {
      if (front == null) {
          System.out.println("\nAntrean kosong. Tidak ada pelanggan yang bisa dilayani.");
          return;
      }
      System.out.println("\nMelayani pelanggan: " + front.name);
      front = front.next;
      if (front == null) {
          rear = null;
      }
      displayQueue();  // Tampilkan antrean setelah melayani
  }

  // Tampilkan daftar pelanggan dalam antrean
  public void displayQueue() {
      if (front == null) {
          System.out.println("\nAntrean kosong.");
          return;
      }

      System.out.println("\nPelanggan dalam antrean:");
      Customer current = front;
      int number = 1;
      while (current != null) {
          System.out.println(number + ". " + current.name);
          current = current.next;
          number++;
      }
  }
}
