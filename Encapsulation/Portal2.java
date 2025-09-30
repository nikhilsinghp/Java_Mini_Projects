/*
import java.util.*;

class Portal1 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        // Using ArrayList instead of Array
        ArrayList<Adhaar> users = new ArrayList<>();

        // Preloaded users
        users.add(new Adhaar("Nikhil", 123412341234L, "Satna", 9876543210L));
        users.add(new Adhaar("Aman", 234523452345L, "Bhopal", 9876501234L));
        users.add(new Adhaar("Rohit", 345634563456L, "Mumbai", 8765432109L));
        users.add(new Adhaar("Virat", 456745674567L, "Delhi", 7654321098L));

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
                case 3 -> addUser(users);
                case 4 -> removeUser(users);
                case 5 -> home = false;
                default -> System.out.println("Invalid input");
            }
        } while (home);

        System.out.println("Thank you for using Aadhaar Portal!");
    }

    // ---------------- Get Info ----------------
    public static void getInfo(ArrayList<Adhaar> users) throws Exception {
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

    public static void getData(Adhaar user) throws Exception {
        boolean getData = true;
        while (getData) {
            System.out.println("\n1. Aadhaar number\n2. Contact\n3. Address\n4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> System.out.println("Aadhaar number: " + user.getAdhaarNo());
                case 2 -> System.out.println("Contact number: " + user.getContact());
                case 3 -> System.out.println("Address: " + user.getAddress());
                case 4 -> getData = false;
                default -> System.out.println("Invalid input");
            }
        }
    }

    // ---------------- Set Info ----------------
    public static void setInfo(ArrayList<Adhaar> users) throws Exception {
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

    public static void setData(Adhaar user) throws Exception {
        boolean setData = true;
        while (setData) {
            System.out.println("\n1. Name\n2. Contact\n3. Address\n4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

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
        }
    }

    // ---------------- Add / Remove User ----------------
    public static void addUser(ArrayList<Adhaar> users) {
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

        users.add(new Adhaar(name, adhaarNo, address, contact));
        System.out.println("✅ User added successfully!");
    }

    public static void removeUser(ArrayList<Adhaar> users) {
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
    public static void displayNames(ArrayList<Adhaar> users) {
        System.out.println("\n--- Select User ---");
        for (int i = 0; i < users.size(); i++) {
            System.out.println((i + 1) + ". " + users.get(i).getName());
        }
        System.out.println((users.size() + 1) + ". Exit");
        System.out.print("Enter your choice: ");
    }
}
*/