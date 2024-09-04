//In aceasta clasa se afla metodele pentru indeplinirea actiunilor din sectia "Meniul Operatorului"
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.LocalDate;

public class MainMenu {
    static Color color = new Color();
    static String str; //variabila care contine parametru ales pentru schimbare
    static String foundClientLine;
    static String parametru;
    static String ID;

    static ArrayList<String> separatedList = new ArrayList<>();//lista unde se separa key/value atunci cand editezi clietn data
    static ArrayList<String> foundClientData = new ArrayList<>();//lista cu fiecare key:value a clientului aparte

    //Display toti clientii
    public static void allClients() {
        try (BufferedReader br = new BufferedReader(new FileReader("ListaClientilor.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String a = line.replace("Nume", "FirstName");
                String b = a.replace("Prenume", "LastName");
                String c = b.replace("Data Nasterii", "Date of birth");
                String d = c.replace("Balanta", "Balance");
                String e = d.replace("Abonament", "Subscription");
                String f = e.replace("Optiuni", "Options");
                String g = f.replace("Data de Facturare", "Billing date");
                System.out.println(g);
            }
        } catch (IOException e) {System.out.println("No!");}
    }

    //metoda pentru a gasi un client dupa id
    public static void findClient(String ID) {
        try (BufferedReader br = new BufferedReader(new FileReader("ListaClientilor.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(ID)) {
                    foundClientLine = line;
                    String[] list = line.split(", ");
                    foundClientData.addAll(Arrays.asList(list));
                }
            }
        } catch (IOException e) {
            // handle exception...
            System.out.println("No!");
        }
    }

    //metoda pentru a ajuta metodata-mama editClient
    public static void editClientHelper(String input) {
        //parametru ~ "Nume", "Prenume", "Data Nasterii"...
        if (Objects.equals(input, "1")) parametru = "Nume";
        if (Objects.equals(input, "2")) parametru = "Prenume";
        if (Objects.equals(input, "3")) parametru = "Data Nasterii";
        if (Objects.equals(input, "4")) parametru = "Balanta";
        if (Objects.equals(input, "5")) Abonament.schimbDeAbonament(ID);
        if (Objects.equals(input, "6")) parametru = "Data de Facturare";

        for (String i : foundClientData) {
                if (i.contains(parametru + ": ")) {
                    String[] list = i.split(": ");
                    for (String j : list) separatedList.add(j);
                    str = separatedList.get(1);

                    //Editam parametru ales
                    String preStr = parametru + ": " + str;
                    if (Objects.equals(parametru, "Data Nasterii")) System.out.println("Example: dd/mm/yyyy");
                    System.out.print(color.GREEN + "Type the change: " + color.RESET);
                    Scanner banana = new Scanner(System.in);
                    String newNume = banana.nextLine();

                    switch (parametru){
                        case "Balanta":
                            Double num = Double.parseDouble(newNume);
                            break;
                        case "Data de Facturare":
                            int date = Integer.parseInt(newNume);
                            if (1 >= date || date >= 31) {
                                System.out.println(color.RED + "\nThere are 31 days in a month" + color.RESET);
                                System.exit(0);
                            }
                            break;
                        case "Data Nasterii":
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            LocalDate date1 = LocalDate.parse(newNume, formatter);
                    }

                    if (foundClientData.contains(preStr)) {
                        String newStr = parametru + ": " + newNume;
                        String j = foundClientLine.replace(preStr, newStr);
                        FileHelper.replaceLine("ListaClientilor.txt", foundClientLine, j);
                        System.out.println(color.BLUE + "\nParameter successfully changed!" + color.RESET);
                    }
                    separatedList.clear();
                }

        }
    }

    //metodat pentru a edita datele despre clienti
    public static void editClient() {
        System.out.print(color.GREEN + "\nType client's ID: " + color.RESET);
        String inpID = Main.input.nextLine();
        ID = inpID;
        findClient(inpID);
        Main.space(1);
        System.out.println(color.YELLOW + foundClientData.get(0).replace("Nume", "First Name") + ", " + foundClientData.get(1).replace("Prenume", "Last Name") + ", "  + foundClientData.get(2).replace("Data Nasterii", "Date of birth") + ", "  + foundClientData.get(4).replace("Balanta", "Balance") + ", "  + foundClientData.get(5).replace("Abonament", "Subscription") + ", "  + foundClientData.get(7).replace("Data de Facturare", "Billing date") + ", "  + color.RESET);
        System.out.println(color.GREEN + "\nWhat do you want to change:\n \n1 First Name\n2 Last Name\n3 Date of birth\n4 Balance\n5 Subscription\n6 Billing date\n" + color.RESET);
        String alegeParametru = Main.input.nextLine();
        editClientHelper(alegeParametru);
        foundClientData.clear();
    }

    //metoda pentru a sterge un client
    public static void removeClient(String ID) {
        try (BufferedReader br = new BufferedReader(new FileReader("ListaClientilor.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(ID)) {
                    FileHelper.replaceLine("ListaClientilor.txt", line, "");
                    FileHelper.removeEmptyLine("ListaClientilor.txt");
                    FileHelper.replaceLine("StareaContului.txt", line, "");
                    FileHelper.removeEmptyLine("StareaContului.txt");
                    System.out.println(color.BLUE + "Client successfully removed!" + color.RESET);
                }
            }
        } catch (IOException e) {
            // handle exception...
            System.out.println("No!");
        }
        try (BufferedReader br = new BufferedReader(new FileReader("StareaContului.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(ID)) {
                    FileHelper.replaceLine("StareaContului.txt", line, "");
                    FileHelper.removeEmptyLine("StareaContului.txt");
                }
            }
        } catch (IOException e) {
            // handle exception...
            System.out.println("No!");
        }
    }

    //metoda pentru a face display la datele clientului
    public static void displayClientData(String clientID) {
        String Prenume;
        String Nume;
        String DataNasterii;
        int ID;
        double Balanta;
        String Abonament;
        String Optiuni;
        int dataDeFacturare;

        foundClientData.clear();
        findClient(clientID);

        Nume = foundClientData.get(0).replace("Nume: ", "");
        Prenume = foundClientData.get(1).replace("Prenume: ", "");
        DataNasterii = foundClientData.get(2).replace("Data Nasterii: ", "");
        ID = Integer.parseInt(foundClientData.get(3).replace("ID: ", ""));
        Balanta = Double.parseDouble(foundClientData.get(4).replace("Balanta: ", ""));
        Abonament = foundClientData.get(5).replace("Abonament: ", "");
        Optiuni = foundClientData.get(6).replace("Optiuni: ", "");
        dataDeFacturare = Integer.parseInt(foundClientData.get(7).replace("Data de Facturare: ", ""));

        Main.space(2);
        Main.linie(130);

        System.out.print(color.CYAN + "\nClient: " + color.BLUE + Nume + " " + Prenume + color.RESET);
        System.out.print(color.CYAN + " | Date of birth: " + color.BLUE + DataNasterii + color.RESET);
        System.out.print(color.CYAN + " | Id: " + color.BLUE + ID + color.RESET);
        System.out.print(color.CYAN + " | Balance: " + color.BLUE + Balanta + color.RESET);
        System.out.print(color.CYAN + " | Subscription: " + color.BLUE + Abonament + color.RESET);
        System.out.print(color.CYAN + " | Options: " + color.BLUE + Optiuni + color.RESET);
        System.out.print(color.CYAN + " | Billing date: " + color.BLUE + dataDeFacturare + color.RESET);
        Main.space(2);
    }
}
