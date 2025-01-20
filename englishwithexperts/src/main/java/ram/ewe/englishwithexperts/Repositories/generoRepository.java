package ram.ewe.englishwithexperts.Repositories;

public class generoRepository {
    private int id;
    private String genero;

    public generoRepository()
    {}

    public generoRepository(int id, String genero)
    {
        this.id = id;
        this.genero = genero;
    }

    public int getId() { return this.id; }
        public String getGenero() { return this.genero; }
        public void setGenero(String genero) { this.genero = genero; }
    public void setId(int id) { this.id = id; }
}
