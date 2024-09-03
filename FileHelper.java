//In aceasta clasa sunt metodele care ajuta la operatiunele cu File-urile
import org.apache.commons.io.FileUtils;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileHelper {
    static int MbMin;

    //Metoda care sterge linia goala
    public static void removeEmptyLine(String file) {
        try {
            String name = file;
            List<String> lines = FileUtils.readLines(new File(name));

            Iterator<String> i = lines.iterator();
            while (i.hasNext()) {
                String line = i.next();
                if (line.trim().isEmpty())
                    i.remove();
            }
            FileUtils.writeLines(new File(name), lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //metoda care face schimb de linie cand avem nevoie sa modificam ceva despre client
    public static void replaceLine(String file, String originalLineText, String newLineText) {
        Path path = Paths.get(file);
        // Get all the lines
        try (Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8)) {
            // Do the line replace
            List<String> list = stream.map(line -> line.equals(originalLineText) ? newLineText : line)
                    .collect(Collectors.toList());
            // Write the content back
            Files.write(path, list, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("No!");
        }
    }

    //metoda care scrie datele clientului in file dupa ce acesta a fost creat
    public static void writeClient() {
        try {
            BufferedWriter myWriter = new BufferedWriter(new FileWriter("ListaClientilor.txt", true));
            myWriter.append("Nume: " + Client.prime.nume + ", ");
            myWriter.append("Prenume: " + Client.prime.prenume + ", ");
            myWriter.append("Data Nasterii: " + Client.prime.dataNasterii + ", ");
            myWriter.append("ID: ").append(String.valueOf(Client.prime.ID)).append(", ");
            myWriter.append("Balanta: ").append(String.valueOf(Client.prime.balanta)).append(", ");
            myWriter.append("Abonament: " + Client.prime.abonament + ", ");
            myWriter.append("Optiuni: " + Client.prime.optiuni + ", ");
            myWriter.append("Data de Facturare: " + Client.prime.dataDeFacturare + "\n");

            myWriter.close();
        } catch (IOException e) {
            System.out.println("No!");
            e.printStackTrace();
        }
    }
    //Metoda ce scriie continutul abonamentelor dupa ce clientul a fost crea si abonamentul ales
    public static void writeMBMIN() {
        try {
            BufferedWriter myWriter = new BufferedWriter(new FileWriter("StareaContului.txt", true));

            myWriter.append("ID: ").append(Client.pID).append(", ");
            MainMenu.findClient(Client.pID);

            switch (MainMenu.foundClientData.get(5)){
                case "Abonament: Start 100":
                    myWriter.append("GB: ").append(String.valueOf(Abonament.Start100.gb) + ", ");
                    myWriter.append("MIN: " + String.valueOf(Abonament.Start100.min) + ("\n"));
                    break;
                case "Abonament: Max 140":
                    myWriter.append("GB: ").append(String.valueOf(Abonament.Max140.gb) + ", ");
                    myWriter.append("MIN: " + String.valueOf(Abonament.Max140.min) + ("\n"));
                    break;
                case "Abonament: Max 175":
                    myWriter.append("GB: ").append(String.valueOf(Abonament.Max175.gb) + ", ");
                    myWriter.append("MIN: " + String.valueOf(Abonament.Max175.min) + ("\n"));
                    break;
                case "Abonament: Max 200":
                    myWriter.append("GB: ").append(String.valueOf(Abonament.Max200.gb) + ", ");
                    myWriter.append("MIN: " + String.valueOf(Abonament.Max200.min) + ("\n"));
                    break;
                case "Abonament: Max 290":
                    myWriter.append("GB: ").append(String.valueOf(Abonament.Max290.gb) + ", ");
                    myWriter.append("MIN: " + String.valueOf(Abonament.Max290.min) + ("\n"));
                    break;
            }

//            if (Objects.equals(MainMenu.foundClientData.get(5), "Abonament: Start 100")) {
//                myWriter.append("GB: ").append(String.valueOf(Abonament.Start100.gb) + ", ");
//                myWriter.append("MIN: " + String.valueOf(Abonament.Start100.min) + ("\n"));
//            }
//            if (Objects.equals(MainMenu.foundClientData.get(5), "Abonament: Max 140")) {
//                myWriter.append("GB: ").append(String.valueOf(Abonament.Max140.gb) + ", ");
//                myWriter.append("MIN: " + String.valueOf(Abonament.Max140.min) + ("\n"));
//            }
//            if (Objects.equals(MainMenu.foundClientData.get(5), "Abonament: Max 175")) {
//                myWriter.append("GB: ").append(String.valueOf(Abonament.Max175.gb) + ", ");
//                myWriter.append("MIN: " + String.valueOf(Abonament.Max175.min) + ("\n"));
//            }
//            if (Objects.equals(MainMenu.foundClientData.get(5), "Abonament: Max 200")) {
//                myWriter.append("GB: ").append(String.valueOf(Abonament.Max200.gb) + ", ");
//                myWriter.append("MIN: " + String.valueOf(Abonament.Max200.min) + ("\n"));
//            }
//            if (Objects.equals(MainMenu.foundClientData.get(5), "Abonament: Max 290")) {
//                myWriter.append("GB: ").append(String.valueOf(Abonament.Max290.gb) + ", ");
//                myWriter.append("MIN: " + String.valueOf(Abonament.Max290.min) + ("\n"));
//            }


            myWriter.close();
        } catch (IOException e) {
            System.out.println("No!");
            e.printStackTrace();
        }
    }

    //Metoda ce citeste cate minute si gb are clientul pentru a fi afisate in "Starea contului"
    public static void readMBMIN(String ID, String GBMIN) {
        try (BufferedReader br = new BufferedReader(new FileReader("StareaContului.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(ID)) {
                    String[] list = line.split(", ");
                    for (String i : list) {
                        if (i.contains(GBMIN)) {
                            String[] elem = i.split(": ");
                            MbMin = Integer.parseInt(elem[1]);
                        }
                    }
                }
            }
        } catch (IOException e) {
            // handle exception...
            System.out.println("No!");

        }
    }
}


