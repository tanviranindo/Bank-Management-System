public class Customer extends Bank{
    String name;
    String address;
    String DOB;
    String cardNumber;
    char pin;
    boolean verifyPassword;

    public boolean isVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(boolean verifyPassword) {
        this.verifyPassword = verifyPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public char getPin() {
        return pin;
    }

    public void setPin(char pin) {
        this.pin = pin;
    }
}
