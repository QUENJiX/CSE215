
/**
 * Main.java — Practice Package Entry Point & Index
 * ==================================================
 * CSE215 - Programming Language II
 *
 * This is the root entry point for the Practice package.
 * Run this to see a welcome message and the full index of available demos.
 *
 * To run any specific demo, navigate to its file and run its main() method.
 * For example: javac p01_Basics/HelloWorld.java && java p01_Basics.HelloWorld
 *
 * See Practice/README.md for the full learning guide and compilation instructions.
 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║   CSE215 - JAVA PRACTICE PACKAGE             ║");
        System.out.println("╚══════════════════════════════════════════════╝");

        System.out.print("\nEnter your name: ");
        String name = input.nextLine();
        System.out.println("\nWelcome, " + name + "!\n");

        System.out.println("Available Practice Packages:");
        System.out.println("─────────────────────────────────────────────");
        System.out.println("  p01_Basics/         → Data types, control flow, methods, strings, arrays");
        System.out.println("  p02_OOP/            → Classes, inheritance, polymorphism, enums, static/final");
        System.out.println("  p03_Interfaces/     → Interfaces, abstract classes, functional interfaces");
        System.out.println("  p04_Exceptions/     → Try/catch, custom exceptions");
        System.out.println("  p05_Collections/    → ArrayList, HashMap, Set, Queue, Stack, generics");
        System.out.println("  p06_FileIO/         → File read/write, serialization");
        System.out.println("  p07_Applications/   → Calculator, guessing game, student management system");
        System.out.println("  p08_LambdasAndStreams/ → Lambda expressions, streams API, method references");
        System.out.println("  p09_InnerClasses/   → Static nested, inner, local, anonymous classes");
        System.out.println("  p10_Concurrency/    → Threads, synchronization, ExecutorService");
        System.out.println("  p11_DesignPatterns/ → Singleton, Factory, Observer, Strategy, Builder");
        System.out.println("─────────────────────────────────────────────");
        System.out.println("\nRun each file individually. See README.md for details.");
        System.out.println("Happy coding, " + name + "!");

        input.close();
    }
}