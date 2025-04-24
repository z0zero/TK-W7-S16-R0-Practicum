/**
 * Main class to demonstrate the TextEditor with undo/redo functionality
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Demo Fitur Undo/Redo dalam Editor Teks (Stack) ===");
        
        TextEditor editor = new TextEditor();
        
        // Menambahkan teks awal
        editor.setText("Selamat");
        System.out.println("Status Stack - Undo: " + editor.getUndoStackSize() + ", Redo: " + editor.getRedoStackSize());
        
        // Menambahkan teks lagi
        editor.addText(" datang");
        System.out.println("Status Stack - Undo: " + editor.getUndoStackSize() + ", Redo: " + editor.getRedoStackSize());
        
        // Demonstrasi undo
        System.out.println("\n--- Demonstrasi Undo ---");
        editor.undo();
        System.out.println("Status Stack - Undo: " + editor.getUndoStackSize() + ", Redo: " + editor.getRedoStackSize());
        
        // Demonstrasi redo
        System.out.println("\n--- Demonstrasi Redo ---");
        editor.redo();
        System.out.println("Status Stack - Undo: " + editor.getUndoStackSize() + ", Redo: " + editor.getRedoStackSize());
        
        // Demonstrasi undo multiple times
        System.out.println("\n--- Demonstrasi Multiple Undo ---");
        editor.undo(); // Kembali ke "Selamat"
        editor.undo(); // Kembali ke ""
        System.out.println("Status Stack - Undo: " + editor.getUndoStackSize() + ", Redo: " + editor.getRedoStackSize());
        
        // Tambahkan teks baru setelah multiple undo
        System.out.println("\n--- Demonstrasi Menambahkan Teks Setelah Undo ---");
        editor.addText("Hello");
        editor.addText(" World!");
        System.out.println("Status Stack - Undo: " + editor.getUndoStackSize() + ", Redo: " + editor.getRedoStackSize());
        
        // Coba redo setelah menambahkan teks baru (seharusnya tidak ada yang bisa di-redo)
        System.out.println("\n--- Demonstrasi Redo Setelah Perubahan Baru ---");
        editor.redo();
        
        // Menambahkan pengukuran waktu eksekusi Stack
        System.out.println("\n=== Pengukuran Waktu Eksekusi Stack ===");
        
        // Parameter untuk jumlah iterasi pengujian
        final int ITERATIONS_SMALL = 1000;
        final int ITERATIONS_MEDIUM = 10000;
        
        System.out.println("\nPengujian dengan " + ITERATIONS_SMALL + " iterasi:");
        editor.displayPerformanceMetrics(ITERATIONS_SMALL);
        
        System.out.println("\nPengujian dengan " + ITERATIONS_MEDIUM + " iterasi:");
        editor.displayPerformanceMetrics(ITERATIONS_MEDIUM);
    }
} 