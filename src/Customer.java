public class Customer {

    // FIELDS   //////////////////////////////////////////////////////////////////////
    private int customerId;
    private String firstName;
    private String lastName;
    private String city;
    private static int MusterID = 1000;
    ///////////////////////////////////////////////////////////////////////////////////

    public Customer(String firstName, String lastName, String city) {
        setCustomerId(customerId);
        setFirstName(firstName);
        setLastName(lastName);
        setCity(city);
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = MusterID++;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    // TO STRING METHOD //
    @Override
    public String toString() {
        return
                        "\033[1;31m" + "\tAD : " + "\033[0m" + getFirstName() +
                        "\033[1;31m" + "\tSOYAD : " + "\033[0m" + getLastName()+
                        "\033[1;31m" + "\tŞEHİR : " + "\033[0m"+ getCity();
    }
    /////////////////////////////////////////////////////////////////////////////////
}
