import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class Dictionnaire{
    HashMap<String, String> dico = new HashMap<>();

    public Dictionnaire(String file) throws IOException{
        List<String> lines = Files.lines(Path.of(file)).toList();
        for(String word : lines){
            dico.put(word, );
        }
    }

    private List<String> createTrigramme(String string){
        List<String> trigramme = new ArrayList<>();
        String start = "<";
        String end = ">";
        string = start + string + end;
        
        return 
    }
}