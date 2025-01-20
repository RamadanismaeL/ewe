package ram.ewe.englishwithexperts.Repositories;

public class dpRepository {
    private int id;
    private String tipo;

    public dpRepository()
    {}

    public dpRepository(int id, String tipo)
    {
        this.id = id;
        this.tipo = tipo;
    }

    public int getId() { return this.id; }
        public String getTipo() { return this.tipo; }
        public void setTipo(String tipo) { this.tipo = tipo; }
    public void setId(int id) { this.id = id; }
}
