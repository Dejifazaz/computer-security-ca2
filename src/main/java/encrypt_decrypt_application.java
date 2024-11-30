import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.SQLOutput;
import java.util.Base64;
import java.util.Scanner;
import java.io.File;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class encrypt_decrypt_application {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("\nChoose an option:");
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

        try {
            Boolean fileExists = false;
            File file = GetFile();

            // Generate AES key
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128);
            SecretKey secretKey = keyGen.generateKey();

            byte[] fileData = null;
            try (FileInputStream input = new FileInputStream(file)) {
                fileData = input.readAllBytes();
            }
            catch (Exception e) {
                System.out.println("Error reading file input: " + e.getMessage());
                return; // Exit if there's an error
            }

            // encrypt data
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedData = cipher.doFinal(fileData);

            // Write encrypted data to a text file (cyphertext.txt)
            String encrypteFileName = "ciphertext.txt";
            try (FileOutputStream fos = new FileOutputStream("./src/" + encrypteFileName)) {
                fos.write(encryptedData);
            }
            catch (Exception e) {
                System.out.println("Error writing the encrypted file: " + e.getMessage());
                return; // Exit if there's an error
            }
            // Print the encryption key to the user
            String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
            System.out.println("\nFile data encrypted and saved to ciphertext.txt. Here is the secretkey: " + encodedKey);
            System.out.println("\n");

        } catch (Exception e) {
            System.out.print("An error occurred when encrypting. " + e.getMessage());
            return; // Exit if there's an error
        }

    }

    public static void decrypt(){
        try {
            // Ask user for file to decrypt
            Scanner scanner = new Scanner(System.in);

            File file = GetFile();

            // get the secret key as string (using scanner)
            System.out.println("\nEnter key: ");
            String key = scanner.nextLine();

            // change the secret from a text string back to a SecretKey
            byte[] decodedKey = Base64.getDecoder().decode(key);
            SecretKey secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

            byte[] fileData = null;
            try (FileInputStream input = new FileInputStream(file)) {
                fileData = input.readAllBytes();
            }
            catch (Exception e) {
                System.out.println("Error reading file input: " + e.getMessage());
                return; // Exit if there's an error
            }


            // Decrypt the file data
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decrptedData = cipher.doFinal(fileData);


            // write decrypted file to new txt file
            String decryptFileName = "decrypttext.txt";
            try (FileOutputStream fos = new FileOutputStream("./src/" + decryptFileName)) {
                fos.write(decrptedData);
            }
            catch (Exception e) {
                System.out.println("Error writing the encrypted file: " + e.getMessage());
                return; // Exit if there's an error
            }

            System.out.println("\nFile decrypted and data written in: " + decryptFileName);
            System.out.println("\n");

        } catch (Exception e) {
            System.out.print("An error occurred when decrypting. " + e.getMessage());
            return; // Exit if there's an error
        }
    }

    private static File GetFile(){
        // Ask user for file to decrypt
        Scanner scanner = new Scanner(System.in);
        String fileName;

        // check if file exists if not try again
        Boolean fileExists = false;

        File file = null;
        while(fileExists == false){
            System.out.print("\nEnter the file name: ");
            fileName = scanner.nextLine();

            file = new File("./src/" + fileName);
            fileExists = file.exists();

            if(fileExists == false) {
                System.out.println("\nThat file doesn't exist. Try again.");
            }
        }

        return file;
    }
}
