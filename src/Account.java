public class Account {

    // FIELDS   /////////////////////////////////////////////////////////////
    private int accountId;
    private int customerId;
    private double balance;
    private AccountType accountType;
    /////////////////////////////////////////////////////////////////////////
    private static int hesapID = 9000;

    public Account() {
    }

    public Account(int customerId, AccountType accountType) {
        setAccountId(accountId);
        this.customerId = customerId;
        this.accountType = accountType;

    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = hesapID++;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }



    // PARA EKLE (DEPOSIT) METODU   //
    public void depositToAmount(double amount) {

        if (amount<0){
            System.out.println("\033[1;31mYatırılmak istenen tutar NEGATİF olamaz\033[0m");
        } else {
            balance+=amount;
            System.out.println("Hesabınıza " +amount +" miktar yatırıldı");
        }
    }


    // PARA ÇEK (WITHDRAW) METODU   //
    public void withdrawToAmount(double amount) {

        if (amount < 0){
            System.out.println("\033[1;31mNegatif tutar cekemezsiniz\033[0m");
        }else if (amount > balance){
            System.out.println("Bakiye Yetersiz");
        }else System.out.println("Çekilen tutar = "+amount);
        System.out.println("Hesap Bakiyesi = "+ (balance -= amount));

    }

    @Override
    public String toString() {
        return
                "HESAP NO    = " + accountId +
                "MÜŞETERİ NO = " + customerId +
                "BAKİYE      = " + balance +
                "HESAP TİPİ  = " + accountType;
    }
    ////////////////////////////////////////////////////////////////////////////////////////
}
