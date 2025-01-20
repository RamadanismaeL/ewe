package ram.ewe.englishwithexperts.Repositories;

public class civilRepository {
    private int id;
    private String civil;

    public civilRepository()
    {}

    public civilRepository(int id, String civil)
    {
        this.id = id;
        this.civil = civil;
    }

    public int getId() { return this.id; }
        public String getCivil() { return this.civil; }
        public void setCivil(String civil) { this.civil = civil; }
    public void setId(int id) { this.id = id; }
}
