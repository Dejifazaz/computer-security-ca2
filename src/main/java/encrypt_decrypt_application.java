import java.util.Scanner;

public class encrypt_decrypt_application {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("Choose an option:");
            System.out.println("1. Encrypt a file");
            System.out.println("2. Decrypt a file");
            System.out.println("3. Quit application");
            System.out.print("Enter your choice: ");


            int choice = 0;
            try {
                 choice = scanner.nextInt();
            }catch (Exception e){
                System.out.println("\nInvalid option please try again!!!!!\n");
                // this tells the program to go back to the beginning of the loop
                scanner = new Scanner(System.in);
                continue;
            }

            scanner.nextLine();
            if (choice == 1){
                System.out.println("\nStarting encryption\n");
                encrypt();
            } else if (choice == 2) {
                System.out.println("\nStarting decryption\n");
                decrypt();
            } else if (choice == 3) {
                System.out.println("\nQuiting application\n");
                return;
            }else {
                System.out.println("\nInvalid option please try again!!!!!\n");
            }
        }
    }

    public static void encrypt(){
        Scanner scanner = new Scanner(System.in);
        String filePath;

        try {
            System.out.print("\nEnter the path of the file: ");
            filePath = scanner.nextLine();

            //ToDo: Write code here to check if the file exists

        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
            return; // Exit if there's an error
        }

        try {
            // File exists so continue with encryption

            // Generate AES key

            // Encrypt file

            // Write encrypted data to a text file (cyphertext.txt)

            // Print the encryption key to the user
        }
        catch(Exception e){
            System.out.print("An error occurred when encrypting. " + e.getMessage());
        }

    }

    public static void decrypt(){

    }
}
