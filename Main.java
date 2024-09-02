
import java.util.Objects;
import java.util.Scanner;


class Color{
    String RESET = "\u001B[0m";
    String RED = "\u001B[31m";
    String GREEN = "\u001B[32m";
    String YELLOW = "\u001B[33m";
    String CYAN = "\u001B[36m";
    String BLUE = "\u001B[34m";
}

//Main
public class Main {
    static Color color = new Color();
    static Scanner input = new Scanner(System.in);

    //spatiu in terminal
    public static void space(int x) {
        for (int i = 0; i < x; ++i) {
            System.out.println();
        }
    }
    //linie in terminal
    public static void linie(int x) {
        for (int i = 0; i < x; ++i) {
            System.out.print(color.CYAN + "-" + color.RESET);
        }
    }
    //meniul operatorului
    public static void operatorMenu() {
            space(15);
            System.out.println(color.GREEN + "Welcome to Operator's Main Menu" + color.RESET);
            System.out.println(color.GREEN + "Here you will be able to freely manipulate your client's data." + color.RESET);
            System.out.println(color.GREEN + "\nBelow are the possibilities you have: " + color.RESET);
            System.out.println(color.BLUE + "1 \"Create\" a client" + color.RESET);
            System.out.println(color.BLUE + "2 \"Remove\" a client" + color.RESET);
            System.out.println(color.BLUE + "3 \"Edit\" client's data" + color.RESET);
            System.out.println(color.BLUE + "4 \"Choose\" a client" + color.RESET);
            System.out.println(color.BLUE + "5 \"Show the list\" of clients" + color.RESET);
            System.out.println("\n0 - Exit program");
            System.out.print(color.GREEN + "\nChoose an option: " + color.RESET);
            String inp = input.nextLine();

            if (Objects.equals(inp, "1")) {
                space(2);
                Client.createClient();
            }
            if (Objects.equals(inp, "2")) {
                space(2);
                System.out.print(color.BLUE + "Type client's ID to remove them: " + color.RESET);
                String removeID = input.nextLine();
                MainMenu.removeClient(removeID);
            }
            if (Objects.equals(inp, "3")) {
                space(2);
                MainMenu. editClient();
            }
            if (Objects.equals(inp, "4")) {
                space(2);
                System.out.print(color.BLUE + "Type client's ID: " + color.RESET);
                String findID = input.nextLine();

                MainMenu.findClient(findID);
                utilizatorMenu(findID);

                MainMenu.foundClientData.clear();
            }
            if (Objects.equals(inp, "5")) {
                space(2);
                MainMenu.allClients();
            }
        }
   //meniul utilizatorului
   public static void utilizatorMenu(String ID) {
       MainMenu.displayClientData(ID);

       System.out.println("Choose an action \n");
       System.out.println("133 - Account status");
       System.out.println("100 - Orange options");
       System.out.println("### - OperatorMenu\n");

       Scanner inp = new Scanner(System.in);
       String str = inp.nextLine();
       if (Objects.equals(str, "133")) {
           utilMenu.stareaContului(ID);
       }
       if (Objects.equals(str, "100")) {
           Main.space(2);
           System.out.println("Choose:");
           System.out.println("1 Mobile data options");
           System.out.println("2 \"Voce\" options");
           System.out.println("3 Change the subscription\n");

           System.out.println("0 - Exit program");
           System.out.println("4 - Back\n");

           String opt = input.nextLine();

           if (Objects.equals(opt, "1")) utilMenu.optiuni(ID, "Internet");
           if (Objects.equals(opt, "2")) utilMenu.optiuni(ID, "Voce");
           if (Objects.equals(opt, "3")) Abonament.schimbDeAbonament(ID);

           if(Objects.equals(opt, "0")) System.exit(0);
           if (Objects.equals(opt, "4")) Main.utilizatorMenu(ID);

       }
       if (Objects.equals(str, "###")) operatorMenu();
       if (!Objects.equals(str, "133") && !Objects.equals(str, "100") && !Objects.equals(str, "###")) {
           space(2);
           System.out.println(color.YELLOW + "Incorrect Action!" + color.RESET);
           System.out.println();
           utilizatorMenu(ID);
       }
   }


    public static void main(String[] args) {
        String zero = color.BLUE + "\"0\"" + color.GREEN;
        String one = color.BLUE + "\"1\"" + color.GREEN;
        String Menu = color.BLUE + "\"Main Menu\"" + color.RESET;
        Scanner input = new Scanner(System.in);

        System.out.println(color.GREEN + "Hello!\n" +
                "Welcome to Orange Services.\n" +
                "First of all try to create a client, yourself for example.\n" +
                "If you want, you can skip to MainMenu\n" +
                "\nType " + one + " to continue\n" +
                "Type " + zero +  " to access " + Menu + "\n"
                + color.RESET + "\n");

        String alOpt = input.nextLine();
        if (Objects.equals(alOpt, "1")) {

            Client.createClient();
            utilizatorMenu(Client.pID);
        }
        if (Objects.equals(alOpt, "0")) {
            operatorMenu();
        }
        System.out.println("Banzai");
        System.out.println("Banzai");
        System.out.println("Banzai");
        System.out.println("Banzai");
        System.out.println("Banzai");
        System.out.println("Banzai");

    }
}

