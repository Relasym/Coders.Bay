
public class Main {

    public static void main(String[] args) {

        MiniDictionary miniDictionary = new MiniDictionary();
        miniDictionary.addGermanWord("hallo","hello");
        miniDictionary.addEnglishWord("Mountain","Berg");
        miniDictionary.printDictionaries();

        System.out.println("Translating 'berg': ");
        miniDictionary.translate("berg").forEach(System.out::println);
        System.out.println("Removing 'berg'");
        miniDictionary.removeGermanWord("berg");
        System.out.println("Translating 'berg': ");
        miniDictionary.translate("berg").forEach(System.out::println);

    }
}
