import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        int numberOfCorrectWord = 0;
        int numberOfWord = Files.readAllLines(Paths.get("fautes.txt")).size();
        Dictionnaire dico;
        try {
            List<String> lines = Files.readAllLines(Paths.get("fautes.txt"));
            List<String> correctWord = Files.readAllLines(Paths.get("minidico.txt"));
            dico = new Dictionnaire("dico.txt");
            for (int i = 0; i < lines.size(); i++) {
                String correctedWord = dico.closestWord(lines.get(i));
                String expectedWord = correctWord.get(i);
                if(correctedWord.equals(expectedWord)){
                    numberOfCorrectWord++;
                }
                else{
                    System.out.println("Correction obtenue : " + correctedWord +  " vs mot attendu : " + expectedWord);
                }
            }

        } catch (IOException e) {
            System.out.println("Cannot read file");
        }
        System.out.println(numberOfCorrectWord + " vs " + numberOfWord);
    }
}
