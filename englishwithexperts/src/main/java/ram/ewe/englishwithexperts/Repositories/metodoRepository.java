package ram.ewe.englishwithexperts.Repositories;

public class metodoRepository {
    private int id;
    private String metodo;

    public metodoRepository()
    {}

    public metodoRepository(int id, String metodo)
    {
        this.id = id;
        this.metodo = metodo;
    }

    public int getId() { return this.id; }
        public String getMetodo() { return this.metodo; }
        public void setMetodo(String metodo) { this.metodo = metodo; }
    public void setId(int id) { this.id = id; }
}
