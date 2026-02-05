/**
 * CustomExceptionDemo.java - Creating and using custom exceptions
 * CSE215 - Programming Language II
 */
package p04_Exceptions;

public class CustomExceptionDemo {
    public static void main(String[] args) {
        System.out.println("=== Custom Exception Demo ===\n");

        BankAccount account = new BankAccount("John Doe", 1000.0);

        // === Testing valid operations ===
        System.out.println("--- Valid Operations ---");
        try {
            account.deposit(500);
            System.out.println("Balance after deposit: $" + account.getBalance());

            account.withdraw(200);
            System.out.println("Balance after withdrawal: $" + account.getBalance());
        } catch (BankingException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // === Testing InsufficientFundsException ===
        System.out.println("\n--- Testing Insufficient Funds ---");
        try {
            account.withdraw(5000); // More than balance
        } catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Attempted: $" + e.getAttemptedAmount());
            System.out.println("Available: $" + e.getAvailableBalance());
            System.out.println("Shortage: $" + e.getShortage());
        } catch (BankingException e) {
            System.out.println("Banking error: " + e.getMessage());
        }

        // === Testing InvalidAmountException ===
        System.out.println("\n--- Testing Invalid Amount ---");
        try {
            account.deposit(-100); // Negative amount
        } catch (InvalidAmountException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (BankingException e) {
            System.out.println("Banking error: " + e.getMessage());
        }

        // === Testing account validation ===
        System.out.println("\n--- Testing Account Validation ---");
        try {
            validateAccountNumber("12345"); // Too short
        } catch (InvalidAccountException e) {
            System.out.println("Validation error: " + e.getMessage());
            System.out.println("Error code: " + e.getErrorCode());
        }

        // === Using throws keyword ===
        System.out.println("\n--- Method That Throws Exception ---");
        try {
            processTransaction(account, "WITHDRAW", 10000);
        } catch (BankingException e) {
            System.out.println("Transaction failed: " + e.getMessage());
        }

        System.out.println("\nFinal balance: $" + account.getBalance());
    }

    /**
     * Method that declares it throws an exception
     */
    public static void processTransaction(BankAccount account, String type, double amount)
            throws BankingException {
        if (type.equals("DEPOSIT")) {
            account.deposit(amount);
        } else if (type.equals("WITHDRAW")) {
            account.withdraw(amount);
        } else {
            throw new BankingException("Unknown transaction type: " + type);
        }
    }

    /**
     * Validates account number format
     */
    public static void validateAccountNumber(String accountNumber)
            throws InvalidAccountException {
        if (accountNumber == null || accountNumber.length() < 10) {
            throw new InvalidAccountException(
                    "Account number must be at least 10 characters",
                    "ERR_INVALID_ACC_FORMAT");
        }
    }
}

// ==========================================
// CUSTOM EXCEPTION CLASSES
// ==========================================

/**
 * Base exception for all banking-related errors
 * Extends Exception (checked exception)
 */
class BankingException extends Exception {
    public BankingException(String message) {
        super(message);
    }

    public BankingException(String message, Throwable cause) {
        super(message, cause);
    }
}

/**
 * Exception for insufficient funds
 */
class InsufficientFundsException extends BankingException {
    private double attemptedAmount;
    private double availableBalance;

    public InsufficientFundsException(String message, double attempted, double available) {
        super(message);
        this.attemptedAmount = attempted;
        this.availableBalance = available;
    }

    public double getAttemptedAmount() {
        return attemptedAmount;
    }

    public double getAvailableBalance() {
        return availableBalance;
    }

    public double getShortage() {
        return attemptedAmount - availableBalance;
    }
}

/**
 * Exception for invalid amount (negative, zero, etc.)
 */
class InvalidAmountException extends BankingException {
    public InvalidAmountException(String message) {
        super(message);
    }
}

/**
 * Exception for invalid account
 */
class InvalidAccountException extends BankingException {
    private String errorCode;

    public InvalidAccountException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}

// ==========================================
// BANK ACCOUNT CLASS
// ==========================================

/**
 * Bank account class that uses custom exceptions
 */
class BankAccount {
    private String ownerName;
    private double balance;

    public BankAccount(String ownerName, double initialBalance) {
        this.ownerName = ownerName;
        this.balance = initialBalance;
    }

    /**
     * Deposits money into the account
     * 
     * @throws InvalidAmountException if amount is invalid
     */
    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive: " + amount);
        }
        balance += amount;
        System.out.println("Deposited: $" + amount);
    }

    /**
     * Withdraws money from the account
     * 
     * @throws InsufficientFundsException if not enough balance
     * @throws InvalidAmountException     if amount is invalid
     */
    public void withdraw(double amount) throws InsufficientFundsException, InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be positive: " + amount);
        }
        if (amount > balance) {
            throw new InsufficientFundsException(
                    "Insufficient funds for withdrawal of $" + amount,
                    amount,
                    balance);
        }
        balance -= amount;
        System.out.println("Withdrawn: $" + amount);
    }

    public double getBalance() {
        return balance;
    }

    public String getOwnerName() {
        return ownerName;
    }
}

/*
 * ==================================
 * CUSTOM EXCEPTION BEST PRACTICES
 * ==================================
 * 
 * 1. Extend Exception for checked exceptions
 * Extend RuntimeException for unchecked exceptions
 * 
 * 2. Provide useful constructors:
 * - Message only
 * - Message + cause (for chaining)
 * - Additional context data
 * 
 * 3. Add relevant fields for debugging:
 * - Error codes
 * - Problem values
 * - Suggested solutions
 * 
 * 4. Use meaningful exception names that describe the problem
 * 
 * 5. Document when methods throw exceptions using @throws Javadoc
 */
