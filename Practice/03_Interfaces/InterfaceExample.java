/**
 * InterfaceExample.java - Demonstrates interfaces and their implementation
 * CSE215 - Programming Language II
 */

public class InterfaceExample {
    public static void main(String[] args) {
        System.out.println("=== Interface Implementation Demo ===\n");
        
        // Creating objects that implement interfaces
        Smartphone phone = new Smartphone("iPhone 15", "Apple");
        Laptop laptop = new Laptop("MacBook Pro", "Apple");
        SmartTV tv = new SmartTV("OLED C3", "LG");
        
        // Using interface methods
        System.out.println("--- Powering ON devices ---");
        phone.powerOn();
        laptop.powerOn();
        tv.powerOn();
        
        System.out.println("\n--- Connecting to WiFi ---");
        phone.connectToWifi("HomeNetwork");
        laptop.connectToWifi("HomeNetwork");
        tv.connectToWifi("HomeNetwork");
        
        System.out.println("\n--- Playing Media ---");
        phone.playMedia("Song.mp3");
        laptop.playMedia("Movie.mkv");
        tv.playMedia("Show.mp4");
        
        // Interface as type (polymorphism)
        System.out.println("\n=== Polymorphism with Interfaces ===");
        Connectable[] connectables = {phone, laptop, tv};
        for (Connectable device : connectables) {
            device.connectToWifi("OfficeNetwork");
        }
        
        // Multiple interfaces
        System.out.println("\n=== Multiple Interface Implementation ===");
        MediaPlayable[] mediaDevices = {phone, laptop, tv};
        for (MediaPlayable device : mediaDevices) {
            device.playMedia("sample.mp4");
            device.pauseMedia();
        }
        
        // Default methods from interface
        System.out.println("\n=== Default Interface Methods ===");
        phone.showBatteryStatus();
        laptop.showBatteryStatus();
        
        // Static interface method
        System.out.println("\n=== Static Interface Method ===");
        Connectable.showNetworkInfo();
    }
}

/**
 * Interface for devices that can connect to networks
 */
interface Connectable {
    // Abstract method (implicitly public and abstract)
    void connectToWifi(String networkName);
    void disconnect();
    boolean isConnected();
    
    // Default method (Java 8+) - provides default implementation
    default void showConnectionStatus() {
        System.out.println("Connection status: " + (isConnected() ? "Connected" : "Disconnected"));
    }
    
    // Static method in interface (Java 8+)
    static void showNetworkInfo() {
        System.out.println("Network protocol: WiFi 6 (802.11ax)");
    }
}

/**
 * Interface for devices that can play media
 */
interface MediaPlayable {
    void playMedia(String filename);
    void pauseMedia();
    void stopMedia();
    
    // Default method
    default void showSupportedFormats() {
        System.out.println("Supported formats: MP3, MP4, WAV, AVI");
    }
}

/**
 * Interface for devices with power capabilities
 */
interface PowerDevice {
    void powerOn();
    void powerOff();
    
    default void showBatteryStatus() {
        System.out.println("Battery status: Not applicable");
    }
}

/**
 * Smartphone implements multiple interfaces
 */
class Smartphone implements Connectable, MediaPlayable, PowerDevice {
    private String model;
    private String brand;
    private boolean connected = false;
    private boolean poweredOn = false;
    
    public Smartphone(String model, String brand) {
        this.model = model;
        this.brand = brand;
    }
    
    // PowerDevice methods
    @Override
    public void powerOn() {
        poweredOn = true;
        System.out.println(brand + " " + model + " powered ON");
    }
    
    @Override
    public void powerOff() {
        poweredOn = false;
        connected = false;
        System.out.println(brand + " " + model + " powered OFF");
    }
    
    // Connectable methods
    @Override
    public void connectToWifi(String networkName) {
        connected = true;
        System.out.println(brand + " " + model + " connected to " + networkName);
    }
    
    @Override
    public void disconnect() {
        connected = false;
        System.out.println(brand + " " + model + " disconnected");
    }
    
    @Override
    public boolean isConnected() {
        return connected;
    }
    
    // MediaPlayable methods
    @Override
    public void playMedia(String filename) {
        System.out.println(brand + " " + model + " playing: " + filename);
    }
    
    @Override
    public void pauseMedia() {
        System.out.println(brand + " " + model + " paused");
    }
    
    @Override
    public void stopMedia() {
        System.out.println(brand + " " + model + " stopped");
    }
    
    // Override default method
    @Override
    public void showBatteryStatus() {
        System.out.println(brand + " " + model + " battery: 85%");
    }
}

/**
 * Laptop also implements multiple interfaces
 */
class Laptop implements Connectable, MediaPlayable, PowerDevice {
    private String model;
    private String brand;
    private boolean connected = false;
    
    public Laptop(String model, String brand) {
        this.model = model;
        this.brand = brand;
    }
    
    @Override
    public void powerOn() {
        System.out.println(brand + " " + model + " booting up...");
    }
    
    @Override
    public void powerOff() {
        System.out.println(brand + " " + model + " shutting down...");
    }
    
    @Override
    public void connectToWifi(String networkName) {
        connected = true;
        System.out.println(brand + " " + model + " connected to " + networkName);
    }
    
    @Override
    public void disconnect() {
        connected = false;
    }
    
    @Override
    public boolean isConnected() {
        return connected;
    }
    
    @Override
    public void playMedia(String filename) {
        System.out.println(brand + " " + model + " playing: " + filename);
    }
    
    @Override
    public void pauseMedia() {
        System.out.println(brand + " " + model + " paused");
    }
    
    @Override
    public void stopMedia() {
        System.out.println(brand + " " + model + " stopped");
    }
    
    @Override
    public void showBatteryStatus() {
        System.out.println(brand + " " + model + " battery: 67%");
    }
}

/**
 * SmartTV implements interfaces
 */
class SmartTV implements Connectable, MediaPlayable, PowerDevice {
    private String model;
    private String brand;
    private boolean connected = false;
    
    public SmartTV(String model, String brand) {
        this.model = model;
        this.brand = brand;
    }
    
    @Override
    public void powerOn() {
        System.out.println(brand + " " + model + " TV turning on...");
    }
    
    @Override
    public void powerOff() {
        System.out.println(brand + " " + model + " TV turning off...");
    }
    
    @Override
    public void connectToWifi(String networkName) {
        connected = true;
        System.out.println(brand + " " + model + " TV connected to " + networkName);
    }
    
    @Override
    public void disconnect() {
        connected = false;
    }
    
    @Override
    public boolean isConnected() {
        return connected;
    }
    
    @Override
    public void playMedia(String filename) {
        System.out.println(brand + " " + model + " TV streaming: " + filename);
    }
    
    @Override
    public void pauseMedia() {
        System.out.println(brand + " " + model + " TV paused");
    }
    
    @Override
    public void stopMedia() {
        System.out.println(brand + " " + model + " TV stopped");
    }
    // Uses default showBatteryStatus() - TV doesn't have battery
}
