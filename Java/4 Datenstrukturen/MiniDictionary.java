import java.util.ArrayList;
import java.util.HashMap;

public class MiniDictionary {
    private final HashMap<String,String> gerEng = new HashMap<>();
    private final HashMap<String,String> engGer = new HashMap<>();

    public void addGermanWord(String firstWord,String secondWord) {
        gerEng.put(firstWord.toLowerCase(),secondWord.toLowerCase());
        engGer.put(secondWord.toLowerCase(),firstWord.toLowerCase());
    }

    public void addEnglishWord(String firstWord,String secondWord) {
        engGer.put(firstWord.toLowerCase(),secondWord.toLowerCase());
        gerEng.put(secondWord.toLowerCase(),firstWord.toLowerCase());
    }

    public void removeGermanWord(String word) {
        engGer.remove(gerEng.get(word.toLowerCase()));
        gerEng.remove(word.toLowerCase());
    }

    public void removeEnglishWord(String word) {
        gerEng.remove(engGer.get(word.toLowerCase()));
        engGer.remove(word.toLowerCase());
    }

    public ArrayList<String> translate(String word) {
        String searchWord = word.toLowerCase();
        ArrayList<String> results= new ArrayList<>();
        if (gerEng.containsKey(searchWord)) {
            results.add(gerEng.get(searchWord));
        }
        if (engGer.containsKey(searchWord)) {
            results.add(engGer.get(searchWord));
        }
        if(results.isEmpty()) {
            results.add("Not found");
        }
        return results;
    }

    public void printDictionaries(){
        System.out.println("German : English");
        gerEng.forEach((key,value)-> System.out.println(key + " : " + value));
        System.out.println();
        System.out.println("English : German");
        engGer.forEach((key,value)-> System.out.println(key + " : " + value));
        System.out.println();
    }
}
