# Implementasi Fitur Undo/Redo dengan Stack

## Deskripsi Implementasi

Program ini mengimplementasikan fitur Undo/Redo yang biasa ditemukan pada editor teks, menggunakan struktur data Stack. Implementasi ini menggunakan dua stack:

1. **undoStack**: Menyimpan histori kondisi teks sebelum perubahan, digunakan untuk operasi Undo.
2. **redoStack**: Menyimpan histori kondisi teks yang telah di-undo, digunakan untuk operasi Redo.

## Struktur Kode

Program terdiri dari dua kelas utama:

1. **TextEditor.java**: Kelas yang mengimplementasikan logika editor teks dengan fitur undo/redo.
2. **Main.java**: Kelas yang menjalankan demonstrasi fitur-fitur TextEditor.

## Penerapan Struktur Data Stack

Stack merupakan struktur data linear yang mengikuti prinsip LIFO (Last-In-First-Out). Dalam implementasi ini:

### Operasi Menambah Teks (addText/setText)
1. Kondisi teks saat ini disimpan ke dalam `undoStack`
2. Teks diperbarui
3. `redoStack` dikosongkan (karena ada perubahan baru)

### Operasi Undo
1. Jika `undoStack` kosong, operasi undo tidak dapat dilakukan
2. Kondisi teks saat ini disimpan ke dalam `redoStack`
3. Kondisi teks terakhir dari `undoStack` dipop dan dijadikan teks saat ini

### Operasi Redo
1. Jika `redoStack` kosong, operasi redo tidak dapat dilakukan
2. Kondisi teks saat ini disimpan ke dalam `undoStack`
3. Kondisi teks terakhir dari `redoStack` dipop dan dijadikan teks saat ini

## Analisis Perbandingan dan Efisiensi

### Kompleksitas Waktu
- **addText/setText**: O(1) - Operasi push pada stack memiliki kompleksitas waktu konstan
- **undo/redo**: O(1) - Operasi pop dan push pada stack memiliki kompleksitas waktu konstan

### Kompleksitas Ruang
- O(n) di mana n adalah jumlah perubahan yang dilakukan pada teks

### Keunggulan Implementasi Stack untuk Undo/Redo
1. **Kesesuaian Konseptual**: Operasi undo/redo secara alami mengikuti prinsip LIFO
2. **Efisiensi**: Operasi push dan pop memiliki kompleksitas O(1)
3. **Mudah Diimplementasikan**: Implementasi relatif sederhana dan intuitif
4. **Performa**: Sangat baik untuk operasi undo/redo sekuensial

### Keterbatasan
1. **Konsumsi Memori**: Menyimpan seluruh teks untuk setiap perubahan bisa mengkonsumsi memori yang signifikan
2. **Tidak Mendukung Undo Selektif**: Implementasi ini hanya mendukung undo/redo sekuensial, tidak bisa melompati operasi tertentu

## Pengukuran Waktu Eksekusi Stack

Program ini juga mencakup implementasi untuk mengukur waktu eksekusi operasi-operasi pada Stack:

1. **measurePushOperation**: Mengukur waktu eksekusi rata-rata untuk operasi push pada Stack
2. **measurePopOperation**: Mengukur waktu eksekusi rata-rata untuk operasi pop pada Stack
3. **measureUndoRedoOperations**: Mengukur waktu eksekusi rata-rata untuk operasi undo dan redo
4. **displayPerformanceMetrics**: Menampilkan hasil pengukuran waktu eksekusi semua operasi

Pengukuran dilakukan dengan cara:
1. Melakukan operasi tersebut sejumlah iterasi (1000, 10000)
2. Mencatat waktu eksekusi menggunakan System.nanoTime()
3. Menghitung waktu rata-rata per operasi

Hasil pengukuran menunjukkan bahwa operasi push dan pop pada Stack memiliki waktu eksekusi yang sangat kecil (sekitar beberapa nanodetik) yang konsisten dengan kompleksitas waktu O(1). Operasi undo dan redo membutuhkan waktu lebih lama karena juga melibatkan manipulasi string.

## Optimasi Potensial
1. Menyimpan operasi/perubahan saja, bukan seluruh state teks
2. Implementasi Command Pattern untuk mendukung operasi yang lebih kompleks
3. Pengelompokan operasi untuk menghemat memori

## Contoh Output Program

```
=== Demo Fitur Undo/Redo dalam Editor Teks (Stack) ===
Teks saat ini: "Selamat"
Status Stack - Undo: 1, Redo: 0
Teks saat ini: "Selamat datang"
Status Stack - Undo: 2, Redo: 0

--- Demonstrasi Undo ---
Undo: "Selamat"
Status Stack - Undo: 1, Redo: 1

--- Demonstrasi Redo ---
Redo: "Selamat datang"
Status Stack - Undo: 2, Redo: 0

... dan seterusnya 

--- Pengukuran Waktu Eksekusi Stack ---
Jumlah iterasi: 1000
Waktu rata-rata operasi PUSH: 42 ns
Waktu rata-rata operasi POP: 25 ns
Waktu rata-rata operasi UNDO: 1250 ns
Waktu rata-rata operasi REDO: 980 ns
``` 