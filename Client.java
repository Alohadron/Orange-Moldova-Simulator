//In aceasta clasa se creaza clientul
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.time.LocalDate;


//Client
public class Client {
    static Client prime = new Client();
    static Color color = new Color();
    static String pID;//cand creez clientul, informatie sa fie deodata afisata in terminal dupa ID-ul stocat in variabila data

    String nume;
    String prenume;
    final String dataNasterii;
    final int ID;
    static double balanta;
    String abonament;
    String optiuni;
    int dataDeFacturare;

    public Client() {
        this.nume = "Unknown";
        this.prenume = "Unknown";
        this.dataNasterii = "Unknown";
        this.ID = 0;
        this.balanta = 0.0;
        this.abonament = "Unknown";
        this.optiuni = "None";
        this.dataDeFacturare = 1;
    }

    public Client(String nume, String prenume, String dataNasterii, int ID, double balanta, String abonament, int dataDeFacturare) {
        this.nume = nume;
        this.prenume = prenume;
        this.dataNasterii = dataNasterii;
        this.ID = ID;
        this.balanta = balanta;
        this.abonament = abonament;
        this.optiuni = "None";
        this.dataDeFacturare = dataDeFacturare;
    }

    //metoda care creaza clientul
    public static void createClient() {
        Random rand = new Random();

        System.out.println(color.BLUE + "Create client" + color.RESET);
        System.out.print("Enter first name: ");
        String nume = Main.input.nextLine();
        System.out.print("Enter last name: ");
        String prenume = Main.input.nextLine();

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a date of birth (dd/mm/yyyy): ");
        String dateStr = sc.next();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = sdf.parse(dateStr);
            System.out.println("Input date: " + sdf.format(date));
        } catch (ParseException e) {
            System.out.println(color.RED + "\nInvalid date format" + color.RESET);
            System.exit(0);
        }
        Abonament.alegeAb();
        int rand_ID = rand.nextInt(100000000);
        LocalDate myObj = LocalDate.now();
        int modi = myObj.getDayOfMonth();
        if (modi == 29 || modi == 30 || modi == 31) modi = 1;


        prime = new Client(nume, prenume, dateStr, rand_ID, 0.0, Abonament.abAles, modi);

        Main.linie(50);
        Client.displayCreatedClient(prime);
        System.out.print("\nType \"1\" to continue ");
        int contin = Main.input.nextInt();
        pID = String.valueOf(prime.ID);
        FileHelper.writeClient();
        FileHelper.writeMBMIN();
        System.out.println(color.GREEN + "Client successfully created!" + color.RESET);

    }

    //metoda care face display la datele clientului
    public static void displayCreatedClient(Client client) {
        System.out.println(color.GREEN + "\nClient's data: " + color.RESET);
        System.out.println(color.BLUE + "First Name: " + color.RESET + client.nume);
        System.out.println(color.BLUE + "Last Name: " + color.RESET + client.prenume);
        System.out.println(color.BLUE + "Date of birth: " + color.RESET + client.dataNasterii);
        System.out.println(color.BLUE + "ID: " + color.RESET + client.ID);
        System.out.println(color.BLUE + "Account balance: " + color.RESET + client.balanta);
        System.out.println(color.BLUE + "Subscription: " + color.RESET + client.abonament);
        System.out.println(color.BLUE + "Options: " + color.RESET + client.optiuni);
        System.out.println(color.BLUE + "Billing date: " + color.RESET + client.dataDeFacturare);
    }
}



