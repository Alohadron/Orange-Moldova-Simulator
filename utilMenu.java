//In aceasta clasa se afla metodele pentru sectiuni optiuni in meniul utilizatorului
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class utilMenu {
    static Color color = new Color();
    static int Min;
    static int Mb;
    static int MINMB;
    static String csl = MainMenu.foundClientLine;//csl = client's string line

    //metodata care afiseaza starea de cont a clientului
    public static void stareaContului(String ID) {
        Main.space(2);

        FileHelper.readMBMIN(ID, "MIN");
        Min = FileHelper.MbMin;
        FileHelper.readMBMIN(ID, "GB");
        Mb = FileHelper.MbMin * 1000;

        System.out.println(color.GREEN + "You have in account:\n" + color.RESET);
        System.out.println(Mb + " MB");
        System.out.println(Min + " Min");

        MainMenu.findClient(ID);
        System.out.println("Balance " + MainMenu.foundClientData.get(4));
        System.out.println(MainMenu.foundClientData.get(5));
        MainMenu.foundClientData.clear();

        System.out.println("\n0 - Exit program");
        System.out.println("1 - Back\n");

        String action = Main.input.nextLine();
        if(Objects.equals(action, "0")) System.exit(0);
        if (Objects.equals(action, "1")) Main.utilizatorMenu(ID);

    }

    //metoda care ajuta metoda-mama optiuni
    public static void modificareOptiune(String ID, int pret, String Optiunea, String MINGB, int cantitatea) {
        int x = 2;
        if (Objects.equals(MINGB, "GB")) x = 1;

        String preOptiune = MainMenu.foundClientData.get(6);
        String preBalance = MainMenu.foundClientData.get(4).replace("Balanta: ", "");
        String newBalance = String.valueOf(Double.parseDouble(preBalance) - pret);
        String op = csl.replace(preOptiune, Optiunea);// "Optiuni: 1 GB lunar"
        String op1 = op.replace(preBalance, newBalance);
        FileHelper.replaceLine("ListaClientilor.txt", csl, op1);
        FileHelper.readMBMIN(ID, MINGB);
        MINMB = FileHelper.MbMin + cantitatea;

        try (BufferedReader br = new BufferedReader(new FileReader("StareaContului.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(ID)) {
                    String[] list = line.split(", ");
                    String preGBMIN = list[x];
                    String newLine = line.replace(preGBMIN, MINGB + ": " + MINMB);
                    FileHelper.replaceLine("StareaContului.txt", line, newLine);
                    System.out.println(color.GREEN + "Option successfully added" + color.RESET);
                }
            }
        } catch (IOException e) {
            // handle exception...
            System.out.println("No!");
        }

    }

    //Metodata care afiseaza si modifica sectia cu optiuni
    public static void optiuni(String ID,String optiunea) {
        MainMenu.findClient(ID);

        //optiuni internet mobil
        if (Objects.equals(optiunea, "Internet")) {
            String alegeGB = """
             Choose:
             1 - 1 GB monthly
             2 - 5 GB monthly
             3 - 10 GB monthly\n
             4 - Back""";
            System.out.println(alegeGB);
            String str = Main.input.nextLine();

            switch (str) {
                case "1" -> modificareOptiune(ID, 30, "Optiuni: 1 GB lunar", "GB", 1);
                case "2" -> modificareOptiune(ID, 60, "Optiuni: 5 GB lunar", "GB", 5);
                case "3" -> modificareOptiune(ID, 100, "Optiuni: 10 GB lunar", "GB", 10);
                case "4" -> Main.utilizatorMenu(ID);
                default -> System.out.println(color.YELLOW + "\nIncorrect option!" + color.RESET);
            }
        }

        //optiuni voce
        if (Objects.equals(optiunea, "Voce")) {
            String alegeMin = """
                    Choose
                    1 - 50 min monthly
                    2 - 100 min monthly
                    3 - 200 min monthly
                    4 - Back""";
            System.out.println(alegeMin);
            String str = Main.input.nextLine();

            switch (str) {
                case "1" -> modificareOptiune(ID, 30, "Optiuni: 50 Min lunar", "MIN", 50);
                case "2" -> modificareOptiune(ID, 60, "Optiuni: 100 Min lunar", "MIN", 100);
                case "3" -> modificareOptiune(ID, 100, "Optiuni: 200 Min lunar", "MIN", 200);
                case "4" -> Main.utilizatorMenu(ID);
                default -> System.out.println(color.YELLOW + "\nIncorrect option!" + color.RESET);
            }
        }
        MainMenu.foundClientData.clear();
    }
}
