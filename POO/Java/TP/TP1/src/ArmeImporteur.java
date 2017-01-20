import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Loïc on 20/01/2017.
 */
public class ArmeImporteur implements Map<String,Integer>{

    /**
     *  Chemin d'accès au fichier
     */
    private String path;

    /**
     * Contenu du fichier
     */
    private String fileContent;

    private List<String> blackList;

    private HashMap<String,Integer> wordOccurences;

    private int maxLength;

    public String getPath() {
        return path;
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    public List<String> getBlackList() {
        return blackList;
    }

    public void setBlackList(List<String> blackList) {
        this.blackList = blackList;
    }

    public HashMap<String, Integer> getWordOccurences() {
        return wordOccurences;
    }

    public void setWordOccurences(HashMap<String, Integer> wordOccurences) {
        this.wordOccurences = wordOccurences;
    }
    public int getMaxLength() {
        return maxLength;
    }

    public ArmeImporteur(String path, List<String> blackList, int maxLength) {
        this.path = path;
        this.blackList = blackList;
        this.maxLength = maxLength;
    }

    /**
     * Importe les armes dans l'armurerie
     * @throws IOException
     */
    public void importWeapons () throws IOException {
        BufferedReader br = null;
        String content;

        String currentLine;
        fileContent = "";
        br = new BufferedReader(new BufferedReader(new FileReader(getPath())));

        while ((currentLine = br.readLine()) != null) {
            fileContent += currentLine;
        }

        if (br != null){
            br.close();
        }

        extractWordsOccurences();
        transferToArmurerie();

    }

    /**
     * Applique une fonction a tous les mots du texte
     */
    private void extractWordsOccurences(){
        HashMap<String, Integer> wordOccs = new HashMap<>();
        Pattern p = Pattern.compile("[a-zA-Z0-9_]*");
        String str = getFileContent();
        Matcher m = p.matcher(str);
        while(m.find()){
            String test = m.group(0).trim().toLowerCase();
            if(!test.equals("")){
                validate(test,wordOccs);
            }
        }
        setWordOccurences(wordOccs);
    }

    /**
     * Fonction de validation de l'import du mot
     * @param word
     * @param wordsOccs
     */
    private void validate(String word,HashMap<String,Integer> wordsOccs){
        //Incrémente le compteur si le mot existe
        if(wordsOccs.containsKey(word) && !getBlackList().contains(word) && word.length() != getMaxLength()){
            wordsOccs.put(word,wordsOccs.get(word)+1);
        }else{
            wordsOccs.put(word,1);
        }
    }

    /**
     * Ajoute les armes dans l'armurerie
     */
    private void transferToArmurerie() throws IOException {
        Armurerie a = Armurerie.getInstance();
        for ( Map.Entry<String,Integer> v: getWordOccurences().entrySet()
             ) {
            Random rand = new Random();
            int posEnum = rand.nextInt(Arme.Type.values().length);
            if(v.getKey().length() >= v.getValue()){
                a.getListArmes().add(new Arme(v.getKey(),v.getValue(),v.getKey().length(),Arme.Type.values()[posEnum],2));
            }else{
                a.getListArmes().add(new Arme(v.getKey(),v.getKey().length(),v.getValue(),Arme.Type.values()[posEnum],2));
            }

        };
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public Integer get(Object key) {
        return null;
    }

    @Override
    public Integer put(String key, Integer value) {
        return null;
    }

    @Override
    public Integer remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Integer> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<String> keySet() {
        return null;
    }

    @Override
    public Collection<Integer> values() {
        return null;
    }

    @Override
    public Set<Entry<String, Integer>> entrySet() {
        return null;
    }


}