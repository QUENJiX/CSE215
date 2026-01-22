/**
 * FileReadWrite.java - Demonstrates file I/O operations in Java
 * CSE215 - Programming Language II
 */
import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.Scanner;

public class FileReadWrite {
    // Sample file paths for demos
    private static final String TEXT_FILE = "sample.txt";
    private static final String DATA_FILE = "data.txt";
    
    public static void main(String[] args) {
        System.out.println("=== File I/O Demo ===\n");
        
        // === Writing to Files ===
        System.out.println("--- Writing to Files ---");
        
        // 1. FileWriter (basic)
        writeWithFileWriter();
        
        // 2. BufferedWriter (efficient)
        writeWithBufferedWriter();
        
        // 3. PrintWriter (convenient)
        writeWithPrintWriter();
        
        // 4. Files.write (modern, Java 7+)
        writeWithNIO();
        
        // === Reading from Files ===
        System.out.println("\n--- Reading from Files ---");
        
        // 1. FileReader + BufferedReader
        readWithBufferedReader();
        
        // 2. Scanner
        readWithScanner();
        
        // 3. Files.readAllLines (modern, Java 7+)
        readWithNIO();
        
        // === File Operations ===
        System.out.println("\n--- File Operations ---");
        fileOperations();
        
        // === Working with Binary Files ===
        System.out.println("\n--- Binary Files ---");
        binaryFileDemo();
        
        // Cleanup demo files
        cleanup();
    }
    
    // ==========================================
    // WRITING METHODS
    // ==========================================
    
    public static void writeWithFileWriter() {
        System.out.println("\n1. FileWriter:");
        try {
            FileWriter writer = new FileWriter(TEXT_FILE);
            writer.write("Hello, File I/O!\n");
            writer.write("This is line 2.\n");
            writer.write("This is line 3.\n");
            writer.close();
            System.out.println("   Written to " + TEXT_FILE);
        } catch (IOException e) {
            System.out.println("   Error: " + e.getMessage());
        }
    }
    
    public static void writeWithBufferedWriter() {
        System.out.println("\n2. BufferedWriter (more efficient for large files):");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            writer.write("Name,Age,Score");
            writer.newLine();
            writer.write("Alice,25,95");
            writer.newLine();
            writer.write("Bob,30,88");
            writer.newLine();
            writer.write("Charlie,22,92");
            System.out.println("   Written CSV data to " + DATA_FILE);
        } catch (IOException e) {
            System.out.println("   Error: " + e.getMessage());
        }
    }
    
    public static void writeWithPrintWriter() {
        System.out.println("\n3. PrintWriter (convenient methods):");
        try (PrintWriter writer = new PrintWriter(new FileWriter("formatted.txt"))) {
            writer.println("Using println() method");
            writer.printf("Formatted: %s, %d, %.2f%n", "Java", 2024, 3.14);
            writer.print("No newline after this");
            System.out.println("   Written formatted data");
        } catch (IOException e) {
            System.out.println("   Error: " + e.getMessage());
        }
    }
    
    public static void writeWithNIO() {
        System.out.println("\n4. Files.write (Java NIO - modern approach):");
        try {
            String content = "Modern Java NIO!\nSimple and clean.\nRecommended approach.";
            Files.write(Path.of("nio_file.txt"), content.getBytes());
            System.out.println("   Written using NIO");
            
            // Append mode
            Files.write(
                Path.of("nio_file.txt"), 
                "\nAppended line!".getBytes(), 
                StandardOpenOption.APPEND
            );
            System.out.println("   Appended using NIO");
        } catch (IOException e) {
            System.out.println("   Error: " + e.getMessage());
        }
    }
    
    // ==========================================
    // READING METHODS
    // ==========================================
    
    public static void readWithBufferedReader() {
        System.out.println("\n1. BufferedReader:");
        try (BufferedReader reader = new BufferedReader(new FileReader(TEXT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("   " + line);
            }
        } catch (IOException e) {
            System.out.println("   Error: " + e.getMessage());
        }
    }
    
    public static void readWithScanner() {
        System.out.println("\n2. Scanner:");
        try (Scanner scanner = new Scanner(new File(DATA_FILE))) {
            while (scanner.hasNextLine()) {
                System.out.println("   " + scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("   Error: " + e.getMessage());
        }
    }
    
    public static void readWithNIO() {
        System.out.println("\n3. Files.readAllLines (NIO):");
        try {
            List<String> lines = Files.readAllLines(Path.of(TEXT_FILE));
            for (String line : lines) {
                System.out.println("   " + line);
            }
            
            // Or read entire file as string
            System.out.println("\n   Reading entire file as string:");
            String content = Files.readString(Path.of(TEXT_FILE));
            System.out.println("   " + content.replace("\n", "\n   "));
        } catch (IOException e) {
            System.out.println("   Error: " + e.getMessage());
        }
    }
    
    // ==========================================
    // FILE OPERATIONS
    // ==========================================
    
    public static void fileOperations() {
        try {
            Path path = Path.of(TEXT_FILE);
            File file = new File(TEXT_FILE);
            
            // Check if file exists
            System.out.println("File exists: " + Files.exists(path));
            System.out.println("Is regular file: " + Files.isRegularFile(path));
            System.out.println("Is directory: " + Files.isDirectory(path));
            System.out.println("File size: " + Files.size(path) + " bytes");
            
            // Using File class
            System.out.println("\nUsing File class:");
            System.out.println("Absolute path: " + file.getAbsolutePath());
            System.out.println("Can read: " + file.canRead());
            System.out.println("Can write: " + file.canWrite());
            
            // Create directory
            Path newDir = Path.of("temp_directory");
            if (!Files.exists(newDir)) {
                Files.createDirectory(newDir);
                System.out.println("\nCreated directory: " + newDir);
            }
            
            // Copy file
            Path copied = Path.of("temp_directory/copied.txt");
            Files.copy(path, copied, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Copied file to: " + copied);
            
            // Move/rename file
            Path moved = Path.of("temp_directory/moved.txt");
            Files.move(copied, moved, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Moved file to: " + moved);
            
            // Delete
            Files.deleteIfExists(moved);
            Files.deleteIfExists(newDir);
            System.out.println("Cleaned up temp files");
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    // ==========================================
    // BINARY FILE I/O
    // ==========================================
    
    public static void binaryFileDemo() {
        String binaryFile = "data.bin";
        
        // Writing binary data
        try (DataOutputStream dos = new DataOutputStream(
                new FileOutputStream(binaryFile))) {
            dos.writeInt(42);
            dos.writeDouble(3.14159);
            dos.writeUTF("Hello Binary!");
            dos.writeBoolean(true);
            System.out.println("Written binary data");
        } catch (IOException e) {
            System.out.println("Error writing: " + e.getMessage());
        }
        
        // Reading binary data
        try (DataInputStream dis = new DataInputStream(
                new FileInputStream(binaryFile))) {
            System.out.println("Read int: " + dis.readInt());
            System.out.println("Read double: " + dis.readDouble());
            System.out.println("Read string: " + dis.readUTF());
            System.out.println("Read boolean: " + dis.readBoolean());
        } catch (IOException e) {
            System.out.println("Error reading: " + e.getMessage());
        }
        
        // Cleanup
        new File(binaryFile).delete();
    }
    
    // ==========================================
    // CLEANUP
    // ==========================================
    
    public static void cleanup() {
        System.out.println("\n--- Cleanup ---");
        String[] files = {TEXT_FILE, DATA_FILE, "formatted.txt", "nio_file.txt"};
        for (String file : files) {
            if (new File(file).delete()) {
                System.out.println("Deleted: " + file);
            }
        }
    }
}

/*
 * ========================================
 * FILE I/O SUMMARY
 * ========================================
 * 
 * WRITING:
 * - FileWriter: Basic character writing
 * - BufferedWriter: Efficient buffered writing
 * - PrintWriter: Convenient formatted writing
 * - Files.write: Modern NIO approach
 * 
 * READING:
 * - FileReader + BufferedReader: Line-by-line reading
 * - Scanner: Parsing tokens and lines
 * - Files.readAllLines: Read all lines at once
 * - Files.readString: Read entire file as string
 * 
 * FILE OPERATIONS:
 * - Files.exists, Files.isDirectory, Files.size
 * - Files.copy, Files.move, Files.delete
 * - Files.createDirectory, Files.createFile
 * 
 * BEST PRACTICES:
 * 1. Always use try-with-resources for auto-closing
 * 2. Use BufferedReader/Writer for large files
 * 3. Prefer NIO (java.nio.file) for modern code
 * 4. Handle exceptions appropriately
 */
