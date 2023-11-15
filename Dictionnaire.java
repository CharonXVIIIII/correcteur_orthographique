import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class Dictionnaire{
    HashMap<String, String> dico = new HashMap<>();

    public Dictionnaire(String file) throws IOException{
        List<String> lines = Files.readAllLines(Paths.get(file));
        for(String line : lines){
            String[] words = line.split(" ");
            dico.put(words[0], words[0]);
        }
    } 

    private List<String> createTrigramme(String string){ // exemple : <patate> -> <pa, pat, ata, tat, ate, te>
        List<String> trigramme = new ArrayList<>();
        String start = "<";
        String end = ">";
        string = start + string + end;
        for(int i = 0; i < string.length() - 2; i++){
            trigramme.add(string.substring(i, i+3));
        }
        return trigramme;
    }


    public static void main(String[] args) throws IOException{
        Dictionnaire dico = new Dictionnaire("minidico.txt");
        System.out.println(dico.dico);
        System.out.println(dico.createTrigramme("patate"));
    }


}