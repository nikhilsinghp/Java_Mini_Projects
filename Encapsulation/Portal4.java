import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Portal4 {
    private static final Scanner sc = new Scanner(System.in);
    private static final String DATA_FILE = "users.ser";
    private static final String LOG_FILE = "portal.log";


    public static void main(String[] args) {

            System.out.println(Colors.BLUE + "===== Aadhaar Portal (v4.0) =====" + Colors.RESET);

            if (!adminLogin()) {
                System.out.println(Colors.RED + "Access denied. Exiting..." + Colors.RESET);
                return;
            }


                ArrayList<Adhaar4> users = loadUsersFromFile();


            if (users.isEmpty()) {
                try {
                    users.add(new Adhaar4("Nikhil", 123412341234L, "Satna", 9876543210L));
                    users.add(new Adhaar4("Aman", 234523452345L, "Rewa", 9876501234L));
                    users.add(new Adhaar4("Rohit", 345634563456L, "Mumbai", 8765432109L));
                    logAction("Initialized demo users");
                    saveUsersToFile(users);
                } catch (InvalidDataException e) {
                    System.out.println("Error creating demo users: " + e.getMessage());
                }
            }

            boolean running = true;
            while (running) {
                printMenu();
                int choice = readIntSafely();
                try {
                    switch (choice) {
                        case 1 -> getInfo(users);
                        case 2 -> setInfo(users);
                        case 3 -> {
                            addUser(users);
                            saveUsersToFile(users);
                        }
                        case 4 -> {
                            removeUser(users);
                            saveUsersToFile(users);
                        }
                        case 5 -> searchUser(users);
                        case 6 -> {
                            saveUsersToFile(users);
                            running = false;
                        }
                        default -> System.out.println(Colors.RED + "Invalid choice." + Colors.RESET);
                    }
                } catch (ClassCastException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Colors.GREEN + "Thank you for using the Aadhaar Portal. Goodbye!" + Colors.RESET);
        }

        // ---------------- Admin ----------------
        private static boolean adminLogin () {
            System.out.print("Enter admin username: ");
            String user = sc.nextLine();
            System.out.print("Enter admin password: ");
            String pass = sc.nextLine();
            boolean ok = user.equals("admin") && pass.equals("password123");
            System.out.println(ok ? Colors.GREEN + "✅ Login successful!" + Colors.RESET
                    : Colors.RED + "❌ Invalid credentials!" + Colors.RESET);
            if (ok) logAction("Admin logged in");
            return ok;
        }

        // ---------------- Menu ----------------
        private static void printMenu () {
            System.out.println("\n" + Colors.YELLOW + "Main Menu" + Colors.RESET);
            System.out.println("1. Get info");
            System.out.println("2. Set info");
            System.out.println("3. Add user");
            System.out.println("4. Remove user");
            System.out.println("5. Search user");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
        }

        private static int readIntSafely () {
            try {
                int v = sc.nextInt();
                sc.nextLine();
                return v;
            } catch (InputMismatchException e) {
                sc.nextLine();
                return -1;
            }
        }

        // ---------------- File Handling ----------------
        @SuppressWarnings("unchecked")
        private static ArrayList<Adhaar4> loadUsersFromFile () {
            File f = new File(DATA_FILE);
            if (!f.exists()) {
                System.out.println("No previous data found, starting fresh.");
                return new ArrayList<>();
            }
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
                ArrayList<Adhaar4> users = (ArrayList<Adhaar4>) ois.readObject();
                System.out.println(Colors.BLUE + "📂 Users loaded (" + users.size() + ")" + Colors.RESET);
                logAction("Loaded users from file");
                return users;
            } catch (Exception e) {
                System.out.println(Colors.RED + "Error loading users: " + e.getMessage() + Colors.RESET);
                return new ArrayList<>();
            }
        }

        private static void saveUsersToFile (ArrayList < Adhaar4 > users) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
                oos.writeObject(users);
                System.out.println(Colors.GREEN + "💾 Users saved." + Colors.RESET);
                logAction("Saved users to file (count=" + users.size() + ")");
            } catch (Exception e) {
                System.out.println(Colors.RED + "Error saving users: " + e.getMessage() + Colors.RESET);
            }
        }

        // ---------------- Logging ----------------
        private static void logAction (String action){
            try (FileWriter fw = new FileWriter(LOG_FILE, true)) {
                fw.write(new Date() + " - " + action + System.lineSeparator());
            } catch (IOException e) {
                System.out.println(Colors.RED + "Logging failed: " + e.getMessage() + Colors.RESET);
            }
        }

        // ---------------- CRUD ----------------
        private static void addUser (ArrayList < Adhaar4 > users) {
            try {
                System.out.print("Enter name: ");
                String name = sc.nextLine();
                System.out.print("Enter Aadhaar number (12 digits): ");
                long ad = Long.parseLong(sc.nextLine());
                System.out.print("Enter address: ");
                String addr = sc.nextLine();
                System.out.print("Enter contact number (10 digits): ");
                long contact = Long.parseLong(sc.nextLine());

                Adhaar4 a = new Adhaar4(name, ad, addr, contact);
                users.add(a);
                System.out.println(Colors.GREEN + "✅ User added: " + name + Colors.RESET);
                logAction("Added user: " + name);
            } catch (InvalidDataException | NumberFormatException e) {
                System.out.println(Colors.RED + "❌ " + e.getMessage() + Colors.RESET);
            }
        }

        private static void removeUser (ArrayList < Adhaar4 > users) {
            if (users.isEmpty()) {
                System.out.println("No users to remove.");
                return;
            }
            listUsers(users);
            System.out.print("Enter index to remove: ");
            int idx = readIntSafely();
            if (idx >= 1 && idx <= users.size()) {
                String name = users.get(idx - 1).getName();
                users.remove(idx - 1);
                System.out.println(Colors.GREEN + "❌ Removed user: " + name + Colors.RESET);
                logAction("Removed user: " + name);
            } else {
                System.out.println(Colors.RED + "Invalid index." + Colors.RESET);
            }
        }

        private static void getInfo (ArrayList < Adhaar4 > users) {
            if (users.isEmpty()) {
                System.out.println("No users available.");
                return;
            }
            try {
                listUsers(users);
            } catch (ClassCastException e) {
                e.printStackTrace();
            }
            System.out.print("Select user index: ");
            int idx = readIntSafely();
            if (idx >= 1 && idx <= users.size()) {
                Adhaar4 u = users.get(idx - 1);
                boolean viewing = true;
                while (viewing) {
                    System.out.println("\n1. Show Aadhaar number (OTP)");
                    System.out.println("2. Show contact (OTP)");
                    System.out.println("3. Show address (OTP)");
                    System.out.println("4. Back");
                    System.out.print("Choice: ");
                    int c = readIntSafely();

                    try {
                        switch (c) {
                            case 1 -> System.out.println("Aadhaar: " + u.getAdhaarNo(sc));
                            case 2 -> System.out.println("Contact: " + u.getContact(sc));
                            case 3 -> System.out.println("Address: " + u.getAddress(sc));
                            case 4 -> viewing = false;
                            default -> System.out.println("Invalid choice.");
                        }
                    } catch (Exception e) {
                        System.out.println(Colors.RED + "❌ " + e.getMessage() + Colors.RESET);
                    }
                }
            }
        }

        private static void setInfo (ArrayList < Adhaar4 > users) {
            if (users.isEmpty()) {
                System.out.println("No users available.");
                return;
            }
            listUsers(users);
            System.out.print("Select user index: ");
            int idx = readIntSafely();
            if (idx >= 1 && idx <= users.size()) {
                Adhaar4 u = users.get(idx - 1);
                boolean editing = true;
                while (editing) {
                    System.out.println("\n1. Update name");
                    System.out.println("2. Update contact");
                    System.out.println("3. Update address");
                    System.out.println("4. Back");
                    System.out.print("Choice: ");
                    int c = readIntSafely();
                    try {
                        switch (c) {
                            case 1 -> {
                                System.out.print("Enter new name: ");
                                u.setName(sc.nextLine());
                            }
                            case 2 -> {
                                System.out.print("Enter new contact: ");
                                u.setContact(Long.parseLong(sc.nextLine()));
                            }
                            case 3 -> {
                                System.out.print("Enter new address: ");
                                u.setAddress(sc.nextLine());
                            }
                            case 4 -> editing = false;
                            default -> System.out.println("Invalid choice.");
                        }
                        saveUsersToFile(users);
                    } catch (InvalidDataException | NumberFormatException e) {
                        System.out.println(Colors.RED + "❌ " + e.getMessage() + Colors.RESET);
                    }
                }
            }
        }

        // ---------------- Search (Streams) ----------------
        private static void searchUser (ArrayList < Adhaar4 > users) {
            System.out.print("Enter name or Aadhaar number to search: ");
            String input = sc.nextLine().trim().toLowerCase();

            List<Adhaar4> results = users.stream()
                    .filter(u -> u.getName().toLowerCase().contains(input)
                            || u.getMaskedAdhaar().contains(input))
                    .collect(Collectors.toList());

            if (results.isEmpty()) {
                System.out.println(Colors.RED + "No users found." + Colors.RESET);
            } else {
                System.out.println(Colors.GREEN + "Found " + results.size() + " user(s):" + Colors.RESET);
                results.forEach(u ->
                        System.out.println(" - " + u.getName() + " | Aadhaar: " + u.getMaskedAdhaar()));
            }
            logAction("Searched users for: " + input);
        }

        // ---------------- Helpers ----------------
        private static void listUsers (ArrayList < Adhaar4 > users) {
            System.out.println("\nUsers:");
            try {
                for (int i = 0; i < users.size(); i++) {
                    System.out.println((i + 1) + ". " + users.get(i).getName() +
                            " | " + users.get(i).getMaskedAdhaar());
                }
            } catch (ClassCastException e) {
                e.printStackTrace();
            }
        }
    }


