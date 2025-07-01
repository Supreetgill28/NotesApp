import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class NotesApp {
    private static final String NOTES_FILE="notes.txt";
    private static Scanner scanner=new Scanner(System.in);

    public static void main(String[]args){
        System.out.println("Welcome to Java Notes App!");
        System.out.println("==========================");

        boolean running=true;
        while(running){
            displayMenu();
            String choice=scanner.nextLine();
            switch(choice){
                case"1":
                viewAllNotes();
                break;
                case "2":
                addNewNote();
                break;
                case"3":
                running= false;

                System.out.println("Thank you for using java notes app.Goodbye!");
                break;
                default:
                System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void displayMenu(){
        System.out.println("1. View all notes.");
        System.out.println("2. Add a new note.");
        System.out.println("3. Exit");
        System.out.println("Enter your choice(1-3)");
    }
    private static void viewAllNotes(){
        List<String>notes=readNotesFromFile();
        if(notes.isEmpty()){
            System.out.println("\nYou don't have any notes yet.");
        }else{
            System.out.println("\nYour Notes:");
            for(int i=0;i<notes.size();i++){
                System.out.println((i+1)+"."+notes.get(i));
            }
        }
    }
    private static void addNewNote(){
        System.out.println("Enter your note:");
        String note=scanner.nextLine();
        if(note.trim().isEmpty()){
            System.out.println("Note cannot be empty.Please try again.");
            return;
        }
        try(FileWriter writer=new FileWriter(NOTES_FILE, true)){
            writer.write(note+System.lineSeparator());
            System.out.println("Note added successfully.");
        }catch(IOException e){
            System.out.println("Error saving note:"+e.getMessage());
        }
    }
    private static List<String>readNotesFromFile(){
        List<String>notes=new ArrayList<>();

        try(BufferedReader reader=new BufferedReader(new FileReader(NOTES_FILE))){
            String line;
            while((line=reader.readLine())!=null){
                if(!line.trim().isEmpty()){
                    notes.add(line);
                }
            }
        }catch(IOException e){
            //If file doesn't exist yet,return empty list
            return notes;
        }
        return notes;
    }
}
