import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
//import java.util.stream.Collectors;

public class Dictionnaire{
    HashMap<String, List<String>> dico = new HashMap<>();

    
    public Dictionnaire(String file) throws IOException{
       // List<String> lines = Files.lines(Path.of(file)).collect(Collectors.toList());
        List<String> lines = Files.readAllLines(Paths.get(file));
        for(String line : lines){
            String[] words = line.split(" ");
            List<String> trigrammeList= createTrigramme(line);
            for(String tri : trigrammeList){
                if(dico.get(tri)){

                }
            }
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
    }


}