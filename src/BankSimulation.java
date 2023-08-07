import java.util.*;
import java.util.List;
import java.util.regex.Pattern;

public class BankSimulation {
    final Scanner scanner;
    final Map<Integer, Customer> Customers;
    final List<Account> Accounts;

    public BankSimulation() {
        scanner = new Scanner(System.in);
        Customers = new HashMap<>();
        Accounts = new ArrayList<>();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) throws InterruptedException {

        BankSimulation bankSimulation = new BankSimulation();
        bankSimulation.run();
    }

    // public void run() METODU, ÇIKIŞ YAPILANA KADAR ÇALIŞACAK OLAN ANA METOD //
    public void run() throws InterruptedException {
        int choice;
        do {
            System.out.println("\033[1;34m" + "[=== BANKA İŞLEMLERİ ===]" + "\033[0m\t");
            System.out.println("1| Müşteri Listele");
            System.out.println("2| Yeni Müşteri Ekle");
            System.out.println("3| Müşteri İşlemleri");
            System.out.println("0| Çıkış");
            System.out.println();
            System.out.print("Seçiminiz: ");
            while (true) {
                if (scanner.hasNextInt()) { // Integer DIŞINDA GİRİLEN DEĞERLERİ KONTROL EDİP KULLANICIYI UYGUN SEÇİME ZORLAR
                    choice = scanner.nextInt();
                    break;
                } else {
                    System.out.println("\033[1;31m" + "LÜTFEN SADECE SAYI GİRİNİZ!" + "\033[0m");
                    System.out.print("Lütfen tekrar seçim yapınız : ");
                    scanner.next();
                }
            }
            switch (choice) {
                case 1:
                    listCustomers();
                    break;
                case 2:
                    addCustomer();
                    break;
                case 3:
                    customerOperationsMenu();
                    break;
                case 0:
                    System.out.println("\033[1;34m" + "Çıkış Yapılıyor....\033[0m");
                    Thread.sleep(500);
                    System.out.println("\033[1;34m" + "\nÇIKIŞ YAPILDI\033[0m");
                    break;
                default:
                    System.out.println("\033[1;31m" + "Geçersiz Seçim, Tekrar Deneyiniz!\033[0m");
            }
        } while (choice != 0);
    }

    // MÜŞTERİLERİ LİSTELEYECEK OLAN METHOD ///////////////////////////////////////////////////
    public void listCustomers() {
        if (Customers.isEmpty()) {
            System.out.println("\n**----------------------------------------**");
            System.out.println("\033[1;31m" + "\t  BANKADA MÜŞTERİ MEVCUT DEĞİLDİR!\033[0m");
            System.out.println("**----------------------------------------**\n");
        } else {
            System.out.println("\n\t\t\t\t  [====== MÜŞTERİLER ======]\n");
            int i = 0;
            for (Map.Entry<Integer, Customer> customer : Customers.entrySet()) {
                System.out.println("\033[1;31m" + "Müşteri ID : " + "\033[0m" + customer.getKey() + " " + customer.getValue());
                listCustomerAccounts(Customers.get(customer.getKey()));
                System.out.println("=---------------------------------------------------------------[" + i + "]");
                i++;
            }
            System.out.println();
        }
    }

    // MÜŞTERİLER LİSTELENİRKEN HESAP BİLGİSİ DE LİSTELENMESİ İÇİN GEREKLİ METOD.////////////////
    public void listCustomerAccounts(Customer customer) {
        boolean hesapVarMi = false;

        System.out.println("_____ " + customer.getCustomerId() +
                " " + customer.getFirstName() +
                " " + customer.getLastName() +
                " HESAPLARI _____");

        for (Account account : Accounts) {
            if (account.getCustomerId() == customer.getCustomerId()) {
                System.out.println(account.getAccountId() + " " + account.getAccountType()
                        + "\033[1;31m Bakiye: \033[0m" + account.getBalance() + " TL");
                hesapVarMi = true;

            }
        }
        System.out.println();
        if (!hesapVarMi) {
            System.out.println("**========================================**");
            System.out.println("\033[1;31m" + "Müsterinin açılmış bir hesabı mevcut değildir\033[0m");
            System.out.println("**========================================**\n");
        }
    }

    // KONSOLDAN İSİM, SOYİSİM VE ŞEHİR BİLGİSİ ALARAK MÜŞTERİ EKLEME METODU. //
    public void addCustomer() {
        scanner.nextLine();
        System.out.println("\n----- YENİ MÜŞTERİ EKLE -----");

        System.out.print("İsim: ");
        String firstName = scanner.nextLine();


        do {
            String firstName2 = firstName.replaceAll(" ", ""); // İKİ ADI OLAN MÜŞTERİLER İÇİN

            if (!strControl(firstName2)) { // Girilen String'in karakterlerini kontrol eder.
                System.out.print("\033[1;32m" + "İSİM UYGUNSUZ KARAKTER İÇEREMEZ!" + "\033[0m\t" +
                        "\nTekrar isim giriniz : ");
                firstName = scanner.nextLine();
            } else {
                break;
            }
        } while (true);

        System.out.print("Soyisim: ");
        String lastName = scanner.next();
        do {
            if (!strControl(lastName)) { // Girilen String'in karakterlerini kontrol eder.
                System.out.print("\033[1;32m" + "SOYİSİM UYGUNSUZ KARAKTER İÇEREMEZ!" + "\033[0m\t" +
                        "\nTekrar soyisim giriniz : ");
                lastName = scanner.next();
            }
        } while (!strControl(lastName));

        System.out.print("Şehir: ");
        String city = scanner.next();

        do {
            if (!strControl(city)) { // Girilen String'in karakterlerini kontrol eder.
                System.out.print("\033[1;32m" + "ŞEHİR SÖZCÜĞÜ UYGUNSUZ KARAKTER İÇEREMEZ!" + "\033[0m\t" +
                        "\nTekrar şehir giriniz : ");
                city = scanner.next();
            }
        } while (!strControl(city));

        Customer newCustomer = new Customer(firstName.toUpperCase(), lastName.toUpperCase(), city.toUpperCase());
        Customers.put(newCustomer.getCustomerId(), newCustomer);


        System.out.println("\033[1;33m" + "\nMüşteri başarıyla eklendi. " + "\033[0m" +
                "\033[1;31m" + "Müşteri ID: " + "\033[0m" + newCustomer.getCustomerId() + "\n");

    }

    // String OLARAK YAZILAN MÜŞTERİ BİLGİLERİNİ KONTROL EDECEK OLAN METOD //
    /* Girilen String'in karakterlerini kontrol eder ve
     istenmeyen karakterler varsa false olarak geri döner.. */
    public static boolean strControl(String str) {
        String regex = "^[a-zA-ZçÇğĞıİöÖşŞü]+$";
        return Pattern.matches(regex, str);
    }

    // MÜŞTERİ OPERASYON MENÜSÜ [-İLK OLARAK ID KONTROLÜ YAPILMAKTADIR-]//
    public void customerOperationsMenu() {
        int customerId;

        System.out.println("\n----- MÜŞTERİ İŞLEMLERİ -----");
        System.out.println("0. Ana Menüye Dön");

        while (true) {
            System.out.print("Müşteri ID girin (0 çıkış yapar): ");
            if (scanner.hasNextInt()) { // Integer DIŞINDA GİRİLEN DEĞERLERİ KONTROL EDİP KULLANICIYI UYGUN SEÇİME ZORLAR
                customerId = scanner.nextInt();
                break;
            } else {
                System.out.println("\n**----------------------------------------**");
                System.out.println("\033[1;31m" + "        LÜTFEN SADECE SAYI GİRİNİZ!!" + "\033[0m");
                System.out.println("**----------------------------------------**\n");
                scanner.next();
            }
        }
        if (customerId == 0) {
            return;
        }
        if (Customers.get(customerId) == null) {
            System.out.println("\n**----------------------------------------**");
            System.out.println("\033[1;31m" + "Geçersiz Müşteri ID. Tekrar Deneyin." + "\033[0m");
            System.out.println("**----------------------------------------**\n");
        } else {
            System.out.println();
            customerOperations(Customers.get(customerId));
        }

    }

    public void customerOperations(Customer customer) {
        int choice;

        do {
            System.out.println("----- " + customer.getFirstName() + " " + customer.getLastName() + " İŞLEMLERİ -----" + "\n" +
                    "     1| Yeni Hesap Aç\n" +
                    "     2| Hesapları Listele\n" +
                    "     3| Para Yatır\n" +
                    "     4| Para Çek\n" +
                    "     5| Bakiye Sorgula\n" +
                    "     0| Ana Menüye Dön\n");
            System.out.print("Seçiminizi yapın:");
            int secim;

            while (true) {
                if (scanner.hasNextInt()) { // Integer DIŞINDA GİRİLEN DEĞERLERİ KONTROL EDİP KULLANICIYI UYGUN SEÇİME ZORLAR
                    secim = scanner.nextInt();
                    break;
                } else {
                    System.out.println("\033[1;31m" + "LÜTFEN SADECE SAYI GİRİNİZ!" + "\033[0m");
                    System.out.print("Lütfen tekrar seçim yapınız : ");
                    scanner.next();
                }
            }

            switch (secim) {
                case 1:
                    openNewAccount(customer);
                    break;
                case 2:
                    listCustomerAccounts(customer);
                    break;
                case 3:
                    depositToAccount(customer);
                    break;
                case 4:
                    withdrawToAccount(customer);
                    break;
                case 5:
                    checkBalance(customer);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("\033[1;31m" + "\nHatalı Değer Girdiniz! Tekrar Deneyiniz.." + "\033[0m\n");
                    break;
            }
        } while (true);
    }

    // HESAP TÜRÜNÜ SEÇTİKTEN SONRA ID ATAYARAK accounts LIST İNE YENİ HESABI EKLEYEN METOD
    public void openNewAccount(Customer customer) {

        System.out.println("\033[1;34m" + "\n----- YENİ HESAP AÇ -----\033[0m");

        System.out.println("1. Vadesiz Hesap (Checking)");
        System.out.println("2. Tasarruf Hesabı (Savings)");
        System.out.println("3. Kredi Hesabı (Credit)");
        System.out.print("\nHesap Türü Seçin:");
        int secim;
        Account newAccount;
        while (true) {
            if (scanner.hasNextInt()) { // Integer DIŞINDA GİRİLEN DEĞERLERİ KONTROL EDİP KULLANICIYI UYGUN SEÇİME ZORLAR
                secim = scanner.nextInt();
                break;
            } else {
                System.out.println("\033[1;31m" + "LÜTFEN SADECE SAYI GİRİNİZ!" + "\033[0m");
                System.out.print("Lütfen tekrar seçim yapınız : ");
                scanner.next();
            }
        }
        switch (secim) {
            case 1:
                newAccount = new Account(customer.getCustomerId(), AccountType.CHECKING);
                Accounts.add(newAccount);
                System.out.println("\033[1;33m" + "\nHesap başarıyla açıldı. " + "\033[1;0m" + "\033[1;31m" +
                        "Hesap Numarası: " + "\033[1;0m" + newAccount.getAccountId() + "\n");
                break;
            case 2:
                newAccount = new Account(customer.getCustomerId(), AccountType.SAVINGS);
                Accounts.add(newAccount);
                System.out.println("\033[1;33m" + "\nHesap başarıyla açıldı. " + "\033[1;0m" + "\033[1;31m" +
                        "Hesap Numarası: " + "\033[1;0m" + newAccount.getAccountId() + "\n");
                break;
            case 3:
                newAccount = new Account(customer.getCustomerId(), AccountType.CREDIT);
                Accounts.add(newAccount);
                System.out.println("\033[1;33m" + "\nHesap başarıyla açıldı. " + "\033[1;0m" + "\033[1;31m" +
                        "Hesap Numarası: " + "\033[1;0m" + newAccount.getAccountId() + "\n");
                break;
            default:
                System.out.println("\033[1;31m" + "\nGeçersiz seçim! Yeni hesap açma işlemi iptal edildi." + "\033[0m\n");
        }
    }

    // PARA EKLE ( deposit() ) METODU  //
    public void depositToAccount(Customer customer) {
        System.out.println("\033[1;34m" + "\n----- PARA YATIR -----\033[0m");
        System.out.print("Hesap Numarasını Girin: ");
        int accountId;
        while (true) {
            if (scanner.hasNextInt()) { // Integer DIŞINDA GİRİLEN DEĞERLERİ KONTROL EDİP KULLANICIYI UYGUN SEÇİME ZORLAR
                accountId = scanner.nextInt();
                break;
            } else {
                System.out.println("\033[1;31m" + "HATALI DEĞER GİRDİNİZ!\033[0m");
                System.out.print("Hesap Numarasını Girin: ");
                scanner.next();
            }
        }
        scanner.nextLine();

        Account account = getAccountById(accountId);
        if (account != null && account.getCustomerId() == customer.getCustomerId()) {
            System.out.print("Yatırılacak Tutarı Girin: ");
            double amount;
            while (true) {
                if (scanner.hasNextDouble()) { // double DIŞINDA GİRİLEN DEĞERLERİ KONTROL EDİP KULLANICIYI UYGUN SEÇİME ZORLAR
                    amount = scanner.nextDouble();
                    if (amount <= 0) {
                        System.out.println("\033[1;31m" + "Geçersiz tutar! Para yatırma işlemi iptal edildi." + "\033[0m");
                        break;
                    } else {
                        account.depositToAmount(amount);
                        System.out.println(amount + "\033[1;34m" + " TL tutarındaki para hesaba yatırıldı.\033[0m");
                        break;
                    }
                } else {
                    System.out.println("\033[1;31m" + "HATALI DEĞER GİRDİNİZ!\033[0m");
                    System.out.print("Yatırılacak Tutarı Girin: ");
                    scanner.next();
                }
            }
        } else {
            System.out.println("\033[1;31m" + "Geçersiz Hesap Numarası veya hesap sizin değil! İşlem iptal edildi.\033[0m");
        }
    }


    // PARA ÇEK ( withdraw() ) METODU  //
    public void withdrawToAccount(Customer customer) {
        System.out.println("\033[1;34m" + "\n----- PARA ÇEK -----\033[0m");
        System.out.print("Hesap Numarasını Girin: ");
        int accountId;
        while (true) {
            if (scanner.hasNextInt()) { // Integer DIŞINDA GİRİLEN DEĞERLERİ KONTROL EDİP KULLANICIYI UYGUN SEÇİME ZORLAR
                accountId = scanner.nextInt();
                break;
            } else {
                System.out.println("\033[1;31m" + "HATALI DEĞER GİRDİNİZ!\033[0m");
                System.out.print("Hesap Numarasını Girin: ");
                scanner.next();
            }
        }
        scanner.nextLine();

        Account account = getAccountById(accountId);
        if (account != null && account.getCustomerId() == customer.getCustomerId()) {
            System.out.print("Çekilecek Tutarı Girin: ");
            double amount;
            while (true) {
                if (scanner.hasNextDouble()) { // double DIŞINDA GİRİLEN DEĞERLERİ KONTROL EDİP KULLANICIYI UYGUN SEÇİME ZORLAR
                    amount = scanner.nextDouble();
                    if (amount <= 0) {
                        System.out.println("\033[1;31m" + "Geçersiz tutar! Para çekme işlemi iptal edildi.\033[0m");
                        break;
                    } else if (account.getBalance() >= amount) {
                        account.withdrawToAmount(amount);
                        System.out.println(amount + "\033[1;34m" + " TL tutarındaki para hesaptan çekildi.\033[0m");
                        break;
                    } else {
                        System.out.println("\033[1;31m" + "Hesap Bakiyesi yetersiz\033[0m");
                        break;
                    }
                } else {
                    System.out.println("\033[1;31m" + "HATALI DEĞER GİRDİNİZ!\033[0m");
                    System.out.print("Çekilecek Tutarı Girin: ");
                    scanner.next();
                }
            }
        } else {
            System.out.println("\033[1;31m" + "Geçersiz Hesap Numarası veya hesap sizin değil! İşlem iptal edildi.\033[0m");
        }
    }

    //  BAKİYE SORGULAMA İPUCU METODU //
    public void checkBalance(Customer customer) {
        System.out.println("\033[1;34m" + "\n----- BAKİYE SORGULA -----\033[0m");
        System.out.print("Hesap Numarasını Girin: ");
        int accountId;
        while (true) {
            if (scanner.hasNextInt()) { // Integer DIŞINDA GİRİLEN DEĞERLERİ KONTROL EDİP KULLANICIYI UYGUN SEÇİME ZORLAR
                accountId = scanner.nextInt();
                break;
            } else {
                System.out.println("\033[1;31m" + "HATALI DEĞER GİRDİNİZ!\033[0m");
                System.out.print("Hesap Numarasını Girin: ");
                scanner.next();
            }
        }
        scanner.nextLine();


        Account account = getAccountById(accountId);
        if (account != null && account.getCustomerId() == customer.getCustomerId()) {
            System.out.println("\033[1;33m" + "Güncel bakiye:\033[0m" + account.getBalance() + " TL");
        } else {
            System.out.println("\033[1;31m" + "Geçersiz Hesap Numarası veya hesap sizin değil! İşlem iptal edildi.\033[0m");
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////

    // İPUCU METODU ///////////////////////////////////////////////////////////////////////////////////
    // Bu metot, accounts listesinde belirli bir hesabı aramak için kullanılır.
    // Bu sayede, hesap işlemleri sırasında müşteriye ait doğru hesabın bulunması sağlanır.
    // Örneğin, para çekme ve yatırma işlemlerinde, belirli bir müşteriye ait olan hesap bilgilerine
    // doğru bir şekilde ulaşmak için bu metot kullanılır. Girilen accountId (hesap ID) 'sine
    // sahip bir hesap bulunmazsa, metot null değerini döndürür (return null)
    public Account getAccountById(int accountId) {
        for (Account account : Accounts) {
            if (account.getAccountId() == accountId) {
                return account;
            }
        }
        return null;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////
}
