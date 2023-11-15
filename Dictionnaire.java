import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
//import java.util.stream.Collectors;

public class Dictionnaire{
    private final HashMap<String, List<String>> dico = new HashMap<>();

    
    public Dictionnaire(String file) throws IOException{
        List<String> lines = Files.readAllLines(Paths.get(file));
        for(String word : lines){
            List<String> trigrammeList= createTrigramme(word);
            List<String> newValuesList = new ArrayList<String>();
            newValuesList.add(word);

            for(String tri : trigrammeList){
                if( ! dico.containsKey(tri)){
                    System.out.println("Je ne possède pas la clé : " + tri);
                    dico.put(tri, newValuesList);
                }
                else{
                    if( ! dico.get(tri).contains(word)){
                        newValuesList.add(word);
                        dico.put(tri, newValuesList);
                    }
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