import java.io.Serializable;
import java.io.Serializable;
import java.util.Scanner;

public class Adhaar3 implements Serializable {
    private static final long serialVersionUID = 1L; // version control
    static transient Scanner sc = new Scanner(System.in); // transient (not serialized)

    private String name;
    private long adhaarNo;
    private String address;
    private long contact;

    // Constructor
    Adhaar3(String name, long adhaarNo, String address, long contact) {
        this.name = name;
        this.adhaarNo = adhaarNo;
        this.address = address;
        this.contact = contact;
    }

    // Getters
    public String getName() {
        return name;
    }

    public long getAdhaarNo() throws Exception {
        if (verify())
            return adhaarNo;
        else {
            System.out.println("Verification failed");
            return -1;
        }
    }

    public String getAddress() throws Exception {
        if (verify())
            return address;
        else {
            System.out.println("Verification failed");
            return "";
        }
    }

    public long getContact() throws Exception {
        if (verify())
            return contact;
        else {
            System.out.println("Verification failed");
            return -1;
        }
    }

    // Setters
    public void setName(String name) throws Exception {
        if (verify()) {
            this.name = name;
            System.out.println("Name updated to " + this.name);
        } else
            System.out.println("Verification failed");
    }

    public void setAddress(String address) throws Exception {
        if (verify()) {
            this.address = address;
            System.out.println("Address updated to " + this.address);
        } else
            System.out.println("Verification failed");
    }

    public void setContact(long contact) throws Exception {
        if (verify()) {
            this.contact = contact;
            System.out.println("Contact updated to " + this.contact);
        } else
            System.out.println("Verification failed");
    }

    // Verification method
    public boolean verify() throws Exception {
        System.out.print("Enter contact number : ");
        long no = sc.nextLong();
        if (no == contact) {
            System.out.println("Generating otp...");
            Thread.sleep(2000);
            int otp = (int) (Math.random() * 9000 + 1000);
            System.out.println("Otp is " + otp);
            System.out.print("Enter otp : ");
            int input = sc.nextInt();
            if (otp == input)
                return true;
            return false;
        }
        return false;
    }
}

