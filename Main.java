
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
            String text = color.GREEN + """
             Welcome to Operator's Main Menu
             Here you will be able to freely manipulate your client's data.
             Below are the possibilities you have: """ + color.RESET;
            System.out.println(text);
            String text1 = color.BLUE + """
             1 \"Create\" a client
             2 \"Remove\" a client
             3 \"Edit\" client's data
             4 \"Choose\" a client
             5 \"Show the list\" of clients""" + color.RESET;
            System.out.println(text1);
            System.out.println("\n0 - Exit program");
            System.out.print(color.GREEN + "\nChoose an option: " + color.RESET);
            String inp = input.nextLine();
            space(2);

            switch (inp) {
                case "1":
                    Client.createClient();
                    break;
                case "2":
                    System.out.print(color.BLUE + "Type client's ID to remove them: " + color.RESET);
                    String removeID = input.nextLine();
                    MainMenu.removeClient(removeID);
                    break;
                case "3":
                    MainMenu. editClient();
                    break;
                case "4":
                    System.out.print(color.BLUE + "Type client's ID: " + color.RESET);
                    String findID = input.nextLine();
                    MainMenu.findClient(findID);
                    utilizatorMenu(findID);
                    MainMenu.foundClientData.clear();
                    break;
                case "5":
                    MainMenu.allClients();
                    break;
            }
        }

   //meniul utilizatorului
   public static void utilizatorMenu(String ID) {
       MainMenu.displayClientData(ID);

       String text = """
       Choose an action \n
       133 - Account status
       100 - Orange options
       ### - OperatorMenu\n""";
       System.out.println(text);

       String str = input.nextLine();
       switch (str) {
           case "133":
               utilMenu.stareaContului(ID);
               break;
           case "100":
               Main.space(2);
               String text1 = """
                       Choose:
                       1 Mobile data options
                       2 \"Voce\" options
                       3 Change the subscription

                       0 - Exit program
                       4 - Back\n""";
               System.out.println(text1);

               String opt = input.nextLine();

               switch (opt) {
                   case "1" -> utilMenu.optiuni(ID, "Internet");
                   case "2" -> utilMenu.optiuni(ID, "Voce");
                   case "3" -> Abonament.schimbDeAbonament(ID);
                   case "0" -> System.exit(0);
                   case "4" -> Main.utilizatorMenu(ID);
               }
           case "###":
               operatorMenu();
               break;
           default:
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

        String text = color.GREEN + """
                Hello!
                Welcome to Orange Services.
                First of all try to create a client, yourself for example.
                If you want, you can skip to MainMenu\n""" + color.RESET;
        System.out.println(text);

        System.out.println(color.GREEN + "\nType " + one + " to continue\n" +
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

    }
}

