/**
 * QueueAndStackDemo.java — Queue, Deque, PriorityQueue, Stack
 * =============================================================
 * CSE215 - Programming Language II
 *
 * 💡 INTUITION:
 *   Queue = a line at a grocery store: first person in line is served first (FIFO)
 *   Stack = a stack of plates: the last plate placed on top is the first one taken (LIFO)
 *   Deque = a double-ended queue: can add/remove from BOTH ends
 *
 * Topics covered:
 *   1. Queue interface (FIFO — First In, First Out)
 *   2. LinkedList as Queue
 *   3. PriorityQueue (elements ordered by priority)
 *   4. Deque (Double-Ended Queue) — ArrayDeque
 *   5. Stack behavior using Deque
 *   6. Practical examples (BFS sketch, balanced parentheses)
 *
 * 🔗 SEE ALSO: p05_Collections/ArrayListDemo.java, p01_Basics/MethodsAndRecursionDemo.java
 */
package p05_Collections;

import java.util.*;

public class QueueAndStackDemo {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║   QUEUE & STACK DEMO                         ║");
        System.out.println("╚══════════════════════════════════════════════╝\n");

        // =====================================================================
        // SECTION 1: QUEUE (FIFO)
        // =====================================================================
        System.out.println("=== 1. QUEUE (FIFO) ===");

        /**
         * 💡 Queue methods come in two flavors:
         *
         * Operation │ Throws Exception │ Returns null/false
         * ───────────┼──────────────────┼────────────────────
         * Insert │ add(e) │ offer(e)
         * Remove │ remove() │ poll()
         * Examine │ element() │ peek()
         *
         * ✅ BEST PRACTICE: Use offer/poll/peek — they handle empty queues gracefully.
         */

        Queue<String> queue = new LinkedList<>();

        // Enqueue (add to back)
        queue.offer("Alice");
        queue.offer("Bob");
        queue.offer("Charlie");
        queue.offer("Diana");
        System.out.println("Queue: " + queue);
        System.out.println("Front (peek): " + queue.peek()); // Alice — doesn't remove
        System.out.println("Size: " + queue.size());

        // Dequeue (remove from front)
        System.out.println("\nProcessing queue:");
        while (!queue.isEmpty()) {
            System.out.println("  Served: " + queue.poll()); // Removes and returns front
        }
        System.out.println("Queue empty? " + queue.isEmpty());
        System.out.println("peek() on empty queue: " + queue.peek()); // null (not exception!)

        // =====================================================================
        // SECTION 2: PRIORITYQUEUE
        // =====================================================================
        System.out.println("\n=== 2. PRIORITYQUEUE ===");

        /**
         * 💡 INTUITION: Like a hospital ER — patients are served by severity
         * (priority),
         * NOT by arrival order.
         *
         * Internally uses a min-heap: smallest element has highest priority.
         * Elements must be Comparable or you must provide a Comparator.
         *
         * ⚠️ GOTCHA: Iterator does NOT guarantee priority order!
         * Only poll() gives you elements in priority order.
         */

        // Min-heap (natural ordering — smallest first)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.addAll(Arrays.asList(42, 17, 85, 3, 56));
        System.out.println("PriorityQueue (internal): " + minHeap); // NOT necessarily sorted!

        System.out.println("Polling in priority order:");
        while (!minHeap.isEmpty()) {
            System.out.print("  " + minHeap.poll()); // 3, 17, 42, 56, 85
        }
        System.out.println();

        // Max-heap (reverse order)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        maxHeap.addAll(Arrays.asList(42, 17, 85, 3, 56));

        System.out.println("\nMax-heap polling:");
        while (!maxHeap.isEmpty()) {
            System.out.print("  " + maxHeap.poll()); // 85, 56, 42, 17, 3
        }
        System.out.println();

        // PriorityQueue with custom objects
        System.out.println("\n--- PriorityQueue with Custom Comparator ---");
        PriorityQueue<String> byLength = new PriorityQueue<>(
                Comparator.comparingInt(String::length));
        byLength.addAll(Arrays.asList("Elephant", "Cat", "Hippopotamus", "Dog", "Ant"));

        System.out.println("Shortest to longest:");
        while (!byLength.isEmpty()) {
            System.out.println("  " + byLength.poll());
        }

        // =====================================================================
        // SECTION 3: DEQUE (Double-Ended Queue)
        // =====================================================================
        System.out.println("\n=== 3. DEQUE (Double-Ended Queue) ===");

        /**
         * 💡 INTUITION: A Deque is a hybrid — it can work as BOTH a queue AND a stack.
         * You can add/remove from EITHER end.
         *
         * ← removeFirst/pollFirst ── [front ... back] ── addLast/offerLast →
         * → addFirst/offerFirst ──── [front ... back] ── removeLast/pollLast ←
         *
         * ✅ BEST PRACTICE: Use ArrayDeque (not Stack class) for stack/queue behavior.
         * ArrayDeque is faster than LinkedList for both stack and queue operations.
         */

        Deque<String> deque = new ArrayDeque<>();

        // Add to both ends
        deque.addFirst("B"); // [B]
        deque.addFirst("A"); // [A, B]
        deque.addLast("C"); // [A, B, C]
        deque.addLast("D"); // [A, B, C, D]
        System.out.println("Deque: " + deque);

        // Access both ends
        System.out.println("First: " + deque.peekFirst()); // A
        System.out.println("Last:  " + deque.peekLast()); // D

        // Remove from both ends
        System.out.println("removeFirst: " + deque.removeFirst()); // A
        System.out.println("removeLast:  " + deque.removeLast()); // D
        System.out.println("Remaining: " + deque); // [B, C]

        // =====================================================================
        // SECTION 4: STACK BEHAVIOR (using Deque)
        // =====================================================================
        System.out.println("\n=== 4. STACK (LIFO) using Deque ===");

        /**
         * ⚠️ GOTCHA: java.util.Stack exists but is legacy (extends Vector,
         * synchronized).
         * ✅ BEST PRACTICE: Use ArrayDeque instead.
         *
         * Stack operation │ Deque method
         * ────────────────┼─────────────
         * push(e) │ push(e) or addFirst(e)
         * pop() │ pop() or removeFirst()
         * peek() │ peek() or peekFirst()
         */

        Deque<String> stack = new ArrayDeque<>();

        // Push (add to top)
        stack.push("Bottom");
        stack.push("Middle");
        stack.push("Top");
        System.out.println("Stack: " + stack);

        // Peek (look at top without removing)
        System.out.println("Peek: " + stack.peek());

        // Pop (remove from top — LIFO order)
        System.out.println("\nPopping stack:");
        while (!stack.isEmpty()) {
            System.out.println("  Popped: " + stack.pop());
        }

        // =====================================================================
        // SECTION 5: PRACTICAL — Balanced Parentheses Checker
        // =====================================================================
        System.out.println("\n=== 5. PRACTICAL: Balanced Parentheses ===");

        /**
         * 💡 Classic stack problem:
         * For each opening bracket, push to stack.
         * For each closing bracket, pop and check if it matches.
         * If stack is empty at the end, the expression is balanced.
         */

        String[] testExpressions = {
                "(())",
                "{[()]}",
                "(()",
                "{[(])}",
                "([{}])",
                ""
        };

        for (String expr : testExpressions) {
            System.out.printf("  \"%s\" → %s%n", expr,
                    isBalanced(expr) ? "✅ Balanced" : "❌ Not balanced");
        }

        // =====================================================================
        // SECTION 6: PRACTICAL — Simple Task Queue
        // =====================================================================
        System.out.println("\n=== 6. PRACTICAL: Task Queue ===");

        Queue<String> taskQueue = new LinkedList<>();
        taskQueue.offer("Send email");
        taskQueue.offer("Update database");
        taskQueue.offer("Generate report");
        taskQueue.offer("Send notifications");

        System.out.println("Task queue: " + taskQueue);
        System.out.println("\nProcessing tasks:");
        int taskNum = 1;
        while (!taskQueue.isEmpty()) {
            System.out.println("  Task " + taskNum++ + ": " + taskQueue.poll() + " ✓");
        }

        System.out.println("\n✅ All demos completed successfully!");
    }

    // =========================================================================
    // HELPER: Balanced Parentheses
    // =========================================================================

    /** Checks if brackets are balanced using a stack */
    public static boolean isBalanced(String expression) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char ch : expression.toCharArray()) {
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch); // Opening → push
            } else if (ch == ')' || ch == ']' || ch == '}') {
                if (stack.isEmpty())
                    return false; // Closing with nothing to match
                char top = stack.pop();
                if (!matches(top, ch))
                    return false; // Mismatched pair
            }
        }
        return stack.isEmpty(); // All opened brackets must be closed
    }

    private static boolean matches(char open, char close) {
        return (open == '(' && close == ')')
                || (open == '[' && close == ']')
                || (open == '{' && close == '}');
    }
}

/*
 * ╔══════════════════════════════════════════════════════════════════╗
 * ║ QUEUE/STACK CHEAT SHEET ║
 * ╠══════════════════════════════════════════════════════════════════╣
 * ║ Structure │ Order │ Best Implementation ║
 * ╠══════════════════════════════════════════════════════════════════╣
 * ║ Queue (FIFO) │ First→ │ ArrayDeque or LinkedList ║
 * ║ Stack (LIFO) │ Last→ │ ArrayDeque (NOT Stack class!) ║
 * ║ Deque │ Both │ ArrayDeque ║
 * ║ Priority │ By rank │ PriorityQueue ║
 * ╚══════════════════════════════════════════════════════════════════╝
 */
