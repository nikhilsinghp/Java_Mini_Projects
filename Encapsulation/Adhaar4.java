import java.io.Serializable;
import java.util.Scanner;

public class Adhaar4 implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private long adhaarNo;
    private String address;
    private long contact;

    public Adhaar4(String name, long adhaarNo, String address, long contact) throws InvalidDataException {
        validateAdhaar(adhaarNo);
        validateContact(contact);
        validateText(name, "Name");
        validateText(address, "Address");
        this.name = name;
        this.adhaarNo = adhaarNo;
        this.address = address;
        this.contact = contact;
    }

    // ---------- Validation ----------
    private void validateAdhaar(long adhaarNo) throws InvalidDataException {
        if (String.valueOf(adhaarNo).length() != 12)
            throw new InvalidDataException("Aadhaar number must be 12 digits!");
    }

    private void validateContact(long contact) throws InvalidDataException {
        if (String.valueOf(contact).length() != 10)
            throw new InvalidDataException("Contact number must be 10 digits!");
    }

    private void validateText(String text, String field) throws InvalidDataException {
        if (text == null || text.trim().isEmpty())
            throw new InvalidDataException(field + " cannot be empty!");
    }

    // ---------- Getters ----------
    public String getName() {
        return name;
    }

    public long getAdhaarNo(Scanner sc) throws Exception {
        if (verify(sc)) return adhaarNo;
        else throw new Exception("Verification failed");
    }

    public String getAddress(Scanner sc) throws Exception {
        if (verify(sc)) return address;
        else throw new Exception("Verification failed");
    }

    public long getContact(Scanner sc) throws Exception {
        if (verify(sc)) return contact;
        else throw new Exception("Verification failed");
    }

    // ---------- Setters ----------
    public void setName(String name) throws InvalidDataException {
        validateText(name, "Name");
        this.name = name;
        System.out.println(Colors.GREEN + "✅ Name updated to " + this.name + Colors.RESET);
    }

    public void setAddress(String address) throws InvalidDataException {
        validateText(address, "Address");
        this.address = address;
        System.out.println(Colors.GREEN + "✅ Address updated to " + this.address + Colors.RESET);
    }

    public void setContact(long contact) throws InvalidDataException {
        validateContact(contact);
        this.contact = contact;
        System.out.println(Colors.GREEN + "✅ Contact updated to " + this.contact + Colors.RESET);
    }

    // ---------- OTP Verification ----------
    public boolean verify(Scanner sc) throws Exception {
        System.out.print("Enter registered contact number: ");
        long entered = sc.nextLong();
        sc.nextLine();
        if (entered != this.contact) {
            System.out.println(Colors.RED + "❌ Contact number mismatch." + Colors.RESET);
            return false;
        }

        int otp = (int) (Math.random() * 9000 + 1000);
        Thread t = new Thread(new OtpSender(otp));
        t.start();
        t.join();

        System.out.print("Enter OTP: ");
        int inputOtp = sc.nextInt();
        sc.nextLine();
        if (inputOtp == otp) {
            System.out.println(Colors.GREEN + "✅ Verification successful." + Colors.RESET);
            return true;
        } else {
            System.out.println(Colors.RED + "❌ Incorrect OTP." + Colors.RESET);
            return false;
        }
    }

    // ---------- Helpers ----------
    public String getMaskedAdhaar() {
        String s = String.valueOf(adhaarNo);
        return (s.length() == 12) ? "********" + s.substring(8) : s;
    }

    @Override
    public String toString() {
        return "Name: " + name + " | Aadhaar: " + getMaskedAdhaar()
                + " | Contact: " + contact + " | Address: " + address;
    }

}