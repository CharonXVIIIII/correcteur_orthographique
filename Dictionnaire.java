import java.util.*;
import java.io.IOException;
import java.nio.file.*;
//import java.util.stream.Collectors;


public class Dictionnaire{
    private final HashMap<String, List<String>> dico = new HashMap<>();

    
    public Dictionnaire(String file) throws IOException{
        List<String> lines = Files.readAllLines(Paths.get(file));
        for(String word : lines){
            List<String> trigrammeList = createTrigramme(word);

            for(String tri : trigrammeList){
                if( ! dico.containsKey(tri)){
                    List<String> newValuesList = new ArrayList<String>();
                    newValuesList.add(word);
                    dico.put(tri, newValuesList);
                }
                else{
                    dico.get(tri).add(word);
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

    private Map<String,Integer> wordsWithCommonTrigramByFrequency(String word){
        HashMap<String, Integer> occurenceCountMap = new HashMap<>();
        List<String> trigrammes = createTrigramme(word);
        List<String> wordsWithCommonTrigram = new ArrayList<>();
        for (String tri : trigrammes) {
            wordsWithCommonTrigram.addAll(dico.getOrDefault(tri,List.of()));
        } 

        for (String mot : wordsWithCommonTrigram) {
            int nbOccurences = occurenceCountMap.containsKey(mot)? occurenceCountMap.get(mot) + 1: 1;
            occurenceCountMap.put(mot, nbOccurences);
        }
        return occurenceCountMap;
    }

    private List<String> occurencesFrequencyMap(String word){
        Map<String, Integer> ocurencesFrequency = wordsWithCommonTrigramByFrequency(word);
        List<String> words = new ArrayList<>(ocurencesFrequency.keySet());
        words.sort(Comparator.comparingInt(ocurencesFrequency::get));

        List<String> closeWords = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            if (words.size() <= i) break;
            closeWords.add(words.get(i));
        }
        return closeWords;
    }

    public String closestWord(String word){
        List<String> closeWord = occurencesFrequencyMap(word);
        String closestWord = null;
        int minChange = Integer.MAX_VALUE;
        for (String compareWord : closeWord ){
            levenshtein lev = new levenshtein(word, compareWord);
            if(lev.editlength() < minChange){
                minChange = lev.editlength();
                closestWord = compareWord;
            }
        }
        return closestWord;
    }

}