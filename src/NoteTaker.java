
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

class NoteTaker {
    NoteTaker() {
    }

    public void writeToFile(File newFile, Scanner scanner) {
        try (
                FileWriter fileWriter = new FileWriter(newFile, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)
        ) {
            while(true) {
                System.out.print("What do you want to write in the file? (Enter DONE to exit): ");
                String userInput = scanner.nextLine();
                if (userInput.equalsIgnoreCase("DONE")) {
                    break;
                }

                bufferedWriter.write(userInput);
                bufferedWriter.newLine();
            }
        } catch (IOException var111) {
            System.out.println("An error occurred.");
        }

    }

    public void outputFile(File newFile) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(newFile))) {
            for(int lineNumber = 1; (line = br.readLine()) != null; ++lineNumber) {
                System.out.println(lineNumber + ") " + line);
            }
        } catch (IOException var8) {
            System.out.println("Something went wrong when writing to the file.");
        }

    }

    public void listTxtFiles() {
        File directory = new File(System.getProperty("user.dir"));
        File[] files = directory.listFiles();
        boolean hasTxtFile = false;
        if (files != null) {
            System.out.println("Your notes");
            System.out.println("---------------------------------------");
            int index = 0;

            for(File file : files) {
                if (file.getName().endsWith(".txt")) {
                    hasTxtFile = true;
                    ++index;
                    System.out.println(index + ") " + file.getName());
                }
            }

            if (!hasTxtFile) {
                System.out.println("You have no notes!");
            }
        }

        System.out.println("---------------------------------------");
    }

    public HashMap listOfTxtFiles() {
        File directory = new File(System.getProperty("user.dir"));
        File[] files = directory.listFiles();
        HashMap<Integer, String> myHashMap = new HashMap<>();
        if (files != null) {
            int index = 0;

            for(File file : files) {
                if (file.getName().endsWith(".txt")) {
                    ++index;
                    myHashMap.put(index, file.getName());
                }
            }
        }

        return myHashMap;
    }

    public void openFile(File newFile) {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();

            try {
                desktop.open(newFile);
            } catch (IOException var4) {
                System.out.println("Something went wrong when trying to open the file.");
            }
        } else {
            System.out.println("Desktop is not supported on this system.");
        }

    }

    public void listOptions() {
        System.out.println("1) View your notes");
        System.out.println("2) Write a note");
        System.out.println("3) Delete a note");
        System.out.println("4) Exit");
    }
}
