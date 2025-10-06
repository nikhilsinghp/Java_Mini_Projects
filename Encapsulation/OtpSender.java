public  class OtpSender implements Runnable {
    private final int otp;

    public OtpSender(int otp) {
        this.otp = otp;
    }

    @Override
    public void run() {
        try {
            System.out.println(Colors.YELLOW + "📨 Sending OTP..." + Colors.RESET);
            Thread.sleep(1500); // simulate delay
            System.out.println(Colors.BLUE + "📥 OTP delivered (for demo): " + otp + Colors.RESET);
        } catch (InterruptedException e) {
            System.out.println(Colors.RED + "Error sending OTP." + Colors.RESET);
        }
    }
}
