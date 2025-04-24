import java.util.Stack;

/**
 * TextEditor class that implements undo/redo functionality using Stack data structure
 */
public class TextEditor {
    private String currentText;
    private Stack<String> undoStack;
    private Stack<String> redoStack;
    
    // Flag untuk mencetak output atau tidak
    private boolean printOutput = true;

    public TextEditor() {
        this.currentText = "";
        this.undoStack = new Stack<>();
        this.redoStack = new Stack<>();
    }
    
    /**
     * Constructor dengan opsi untuk mencetak output atau tidak
     * @param printOutput true jika ingin mencetak output, false jika tidak
     */
    public TextEditor(boolean printOutput) {
        this();
        this.printOutput = printOutput;
    }

    /**
     * Adds new text to the current text
     * @param text text to be added
     */
    public void addText(String text) {
        // Save current state to undo stack
        undoStack.push(currentText);
        
        // Update the text
        currentText += text;
        
        // Clear redo stack since we have a new change
        redoStack.clear();
        
        if (printOutput) {
            System.out.println("Teks saat ini: \"" + currentText + "\"");
        }
    }
    
    /**
     * Replace the current text with a new text
     * @param text new text to replace current text
     */
    public void setText(String text) {
        // Save current state to undo stack
        undoStack.push(currentText);
        
        // Set the new text
        currentText = text;
        
        // Clear redo stack since we have a new change
        redoStack.clear();
        
        if (printOutput) {
            System.out.println("Teks saat ini: \"" + currentText + "\"");
        }
    }

    /**
     * Undo the last text change
     * @return true if undo was successful, false otherwise
     */
    public boolean undo() {
        if (undoStack.empty()) {
            if (printOutput) {
                System.out.println("Tidak ada yang dapat di-undo");
            }
            return false;
        }
        
        // Save current state to redo stack
        redoStack.push(currentText);
        
        // Restore previous state
        currentText = undoStack.pop();
        
        if (printOutput) {
            System.out.println("Undo: \"" + currentText + "\"");
        }
        return true;
    }

    /**
     * Redo the last undone change
     * @return true if redo was successful, false otherwise
     */
    public boolean redo() {
        if (redoStack.empty()) {
            if (printOutput) {
                System.out.println("Tidak ada yang dapat di-redo");
            }
            return false;
        }
        
        // Save current state to undo stack
        undoStack.push(currentText);
        
        // Restore the state from redo stack
        currentText = redoStack.pop();
        
        if (printOutput) {
            System.out.println("Redo: \"" + currentText + "\"");
        }
        return true;
    }

    /**
     * Get the current text
     * @return current text
     */
    public String getCurrentText() {
        return currentText;
    }
    
    /**
     * Get the number of operations that can be undone
     * @return size of undo stack
     */
    public int getUndoStackSize() {
        return undoStack.size();
    }
    
    /**
     * Get the number of operations that can be redone
     * @return size of redo stack
     */
    public int getRedoStackSize() {
        return redoStack.size();
    }

    /**
     * Menghitung waktu eksekusi operasi push pada stack
     * @param iterations jumlah iterasi untuk pengujian
     * @return waktu eksekusi rata-rata dalam nanodetik
     */
    public long measurePushOperation(int iterations) {
        Stack<String> testStack = new Stack<>();
        String testText = "test";
        
        long startTime = System.nanoTime();
        
        for (int i = 0; i < iterations; i++) {
            testStack.push(testText + i);
        }
        
        long endTime = System.nanoTime();
        
        return (endTime - startTime) / iterations;
    }
    
    /**
     * Menghitung waktu eksekusi operasi pop pada stack
     * @param iterations jumlah iterasi untuk pengujian
     * @return waktu eksekusi rata-rata dalam nanodetik
     */
    public long measurePopOperation(int iterations) {
        Stack<String> testStack = new Stack<>();
        
        // Mengisi stack untuk testing
        for (int i = 0; i < iterations; i++) {
            testStack.push("test" + i);
        }
        
        long startTime = System.nanoTime();
        
        for (int i = 0; i < iterations; i++) {
            if (!testStack.empty()) {
                testStack.pop();
            }
        }
        
        long endTime = System.nanoTime();
        
        return (endTime - startTime) / iterations;
    }
    
    /**
     * Menghitung waktu eksekusi operasi undo/redo
     * @param iterations jumlah iterasi untuk pengujian
     * @return array dengan waktu eksekusi rata-rata [undo, redo] dalam nanodetik
     */
    public long[] measureUndoRedoOperations(int iterations) {
        // Gunakan TextEditor tanpa output ke konsol
        TextEditor testEditor = new TextEditor(false);
        long undoTime = 0;
        long redoTime = 0;
        
        // Setup: melakukan beberapa perubahan teks
        for (int i = 0; i < iterations; i++) {
            testEditor.addText("teks" + i);
        }
        
        // Mengukur waktu undo
        long startUndo = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            testEditor.undo();
        }
        long endUndo = System.nanoTime();
        undoTime = (endUndo - startUndo) / iterations;
        
        // Mengukur waktu redo
        long startRedo = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            testEditor.redo();
        }
        long endRedo = System.nanoTime();
        redoTime = (endRedo - startRedo) / iterations;
        
        return new long[] {undoTime, redoTime};
    }
    
    /**
     * Menampilkan hasil pengukuran waktu eksekusi stack
     * @param iterations jumlah iterasi untuk pengujian
     */
    public void displayPerformanceMetrics(int iterations) {
        System.out.println("Jumlah iterasi: " + iterations);
        
        long pushTime = measurePushOperation(iterations);
        System.out.println("Waktu rata-rata operasi PUSH: " + pushTime + " ns");
        
        long popTime = measurePopOperation(iterations);
        System.out.println("Waktu rata-rata operasi POP: " + popTime + " ns");
        
        long[] undoRedoTimes = measureUndoRedoOperations(iterations);
        System.out.println("Waktu rata-rata operasi UNDO: " + undoRedoTimes[0] + " ns");
        System.out.println("Waktu rata-rata operasi REDO: " + undoRedoTimes[1] + " ns");
    }
} 