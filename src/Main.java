
import java.io.File;
import java.util.Scanner;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        NoteTaker noteTaker = new NoteTaker();

        while(true) {
            System.out.println("Welcome to the note taker app!");
            System.out.println("------------------------------");
            noteTaker.listOptions();
            System.out.print("Which option would you like to pick? ");
            int userChoice = scanner.nextInt();
            scanner.nextLine();
            if (userChoice == 1) {
                noteTaker.listTxtFiles();

                while(true) {
                    System.out.print("Which file would you like to open? ");
                    int userFileChoice = scanner.nextInt();
                    int hashMapSize = noteTaker.listOfTxtFiles().size();
                    if (userFileChoice <= hashMapSize && userFileChoice > 0) {
                        File newFile = new File(String.valueOf(noteTaker.listOfTxtFiles().get(userFileChoice)));
                        noteTaker.openFile(newFile);
                        break;
                    }

                    System.out.println("That's not a valid file. Try again.");
                }
            } else if (userChoice == 2) {
                System.out.print("Enter the directory you would like to have your file in: ");
                String directory = scanner.nextLine();
                System.out.print("Enter the name of your file: ");
                String fileName = scanner.nextLine();
                File newFile = new File(directory + "\\" + fileName);
                noteTaker.writeToFile(newFile, scanner);
            } else if (userChoice != 3) {
                if (userChoice == 4) {
                    System.out.println("Thank you for using the app!");
                    return;
                }
            } else {
                noteTaker.listTxtFiles();

                while(true) {
                    System.out.print("Which file would you like to delete? ");
                    int userDeleteChoice = scanner.nextInt();
                    int hashMapSize = noteTaker.listOfTxtFiles().size();
                    if (userDeleteChoice <= hashMapSize && userDeleteChoice > 0) {
                        File newFile = new File(String.valueOf(noteTaker.listOfTxtFiles().get(userDeleteChoice)));
                        if (newFile.delete()) {
                            break;
                        }
                    } else {
                        System.out.println("That's not a valid file. Try again.");
                    }
                }
            }
        }
    }
}
