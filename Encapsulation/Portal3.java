
import java.util.*;
import java.io.*;


// ---------------- Portal Class ----------------
public class Portal3 {
    static Scanner sc = new Scanner(System.in);
    static final String FILE_NAME = "users.ser"; // serialized file

    public static void main(String[] args) throws Exception {
        ArrayList<Adhaar3> users = loadUsersFromFile();

        boolean home = true;
        do {
            System.out.println("\n===== Aadhaar Portal =====");
            System.out.println("1. Get info");
            System.out.println("2. Set info");
            System.out.println("3. Add user");
            System.out.println("4. Remove user");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

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
                case 5 -> {
                    saveUsersToFile(users);
                    home = false;
                }
                default -> System.out.println("Invalid input");
            }
        } while (home);

        System.out.println("Thank you for using Aadhaar Portal!");
    }

    // ---------------- Serialization ----------------
    public static void saveUsersToFile(ArrayList<Adhaar3> users) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(users);
            System.out.println("💾 Users saved successfully (Serialization).");
        } catch (Exception e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Adhaar3> loadUsersFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No previous data found, starting fresh.");
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            ArrayList<Adhaar3> users = (ArrayList<Adhaar3>) ois.readObject();
            System.out.println("📂 Users loaded successfully (Deserialization).");
            return users;
        } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // ---------------- Get Info ----------------
    public static void getInfo(ArrayList<Adhaar3> users) {
        boolean getPage = true;
        while (getPage) {
            displayNames(users);
            int choice = sc.nextInt();


            if (choice >= 1 && choice <= users.size())
                getData(users.get(choice - 1));
            else if (choice == users.size() + 1)
                getPage = false;
            else
                System.out.println("Invalid input");
        }
    }

    public static void getData(Adhaar3 user) {
        boolean getData = true;
        while (getData) {
            System.out.println("\n1. Aadhaar number\n2. Contact\n3. Address\n4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            try {
                switch (choice) {
                    case 1 -> System.out.println("Aadhaar number: " + user.getAdhaarNo());
                    case 2 -> System.out.println("Contact number: " + user.getContact());
                    case 3 -> System.out.println("Address: " + user.getAddress());
                    case 4 -> getData = false;
                    default -> System.out.println("Invalid input");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    // ---------------- Set Info ----------------
    public static void setInfo(ArrayList<Adhaar3> users) {
        boolean setPage = true;
        while (setPage) {
            displayNames(users);
            int choice = sc.nextInt();

            if (choice >= 1 && choice <= users.size())
                setData(users.get(choice - 1));
            else if (choice == users.size() + 1)
                setPage = false;
            else
                System.out.println("Invalid input");
        }
    }

    public static void setData(Adhaar3 user) {
        boolean setData = true;
        while (setData) {
            System.out.println("\n1. Name\n2. Contact\n3. Address\n4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            try {

                switch (choice) {
                    case 1 -> {
                        sc.nextLine();
                        System.out.print("Enter name: ");
                        String name = sc.nextLine();
                        user.setName(name);
                    }
                    case 2 -> {
                        System.out.print("Enter contact: ");
                        long contact = sc.nextLong();
                        user.setContact(contact);
                    }
                    case 3 -> {
                        sc.nextLine();
                        System.out.print("Enter address: ");
                        String address = sc.nextLine();
                        user.setAddress(address);
                    }
                    case 4 -> setData = false;
                    default -> System.out.println("Invalid input");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    // ---------------- Add / Remove User ----------------
    public static void addUser(ArrayList<Adhaar3> users) {
        sc.nextLine(); // clear buffer
        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter Aadhaar number (12 digits): ");
        long adhaarNo = sc.nextLong();

        sc.nextLine(); // clear buffer
        System.out.print("Enter address: ");
        String address = sc.nextLine();

        System.out.print("Enter contact number (10 digits): ");
        long contact = sc.nextLong();

        users.add(new Adhaar3(name, adhaarNo, address, contact));
        System.out.println("✅ User added successfully!");
    }

    public static void removeUser(ArrayList<Adhaar3> users) {
        if (users.isEmpty()) {
            System.out.println("No users to remove!");
            return;
        }
        displayNames(users);
        int choice = sc.nextInt();

        if (choice >= 1 && choice <= users.size()) {
            String removedName = users.get(choice - 1).getName();
            users.remove(choice - 1);
            System.out.println("❌ User " + removedName + " removed successfully.");
        } else {
            System.out.println("Invalid input");
        }
    }

    // ---------------- Utility ----------------
    public static void displayNames(ArrayList<Adhaar3> users) {
        System.out.println("\n--- Select User ---");
        for (int i = 0; i < users.size(); i++) {
            System.out.println((i + 1) + ". " + users.get(i).getName());
        }
        System.out.println((users.size() + 1) + ". Exit");
        System.out.print("Enter your choice: ");
    }
}
