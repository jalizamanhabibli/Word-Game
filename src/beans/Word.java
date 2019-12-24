package beans;

public class Word {
    private int id;
    private String wordEn;
    private String wordAz;

    public Word() {
    }

    public Word(String wordEn, String wordAz) {
        this.wordEn = wordEn;
        this.wordAz = wordAz;
    }

    public int getId() {
        return id;
    }

    public Word setId(int id) {
        this.id = id;
        return this;
    }

    public String getWordEn() {
        return wordEn;
    }

    public Word setWordEn(String wordEn) {
        this.wordEn = wordEn;
        return this;
    }

    public String getWordAz() {
        return wordAz;
    }

    public Word setWordAz(String wordAz) {
        this.wordAz = wordAz;
        return this;
    }

    @Override
    public String toString() {
        return "Word{" + "id=" + id + "\n wordEn=" + wordEn + "\n wordAz=" + wordAz + "\n}";
    }
    
    
    
}
