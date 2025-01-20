package ram.ewe.englishwithexperts.Repositories;

public class relacaoRepository {
    private int id;
    private String relacao;

    public relacaoRepository()
    {}

    public relacaoRepository(int id, String relacao)
    {
        this.id = id;
        this.relacao = relacao;
    }

    public int getId() { return this.id; }
        public String getRelacao() { return this.relacao; }
        public void setRelacao(String relacao) { this.relacao = relacao; }
    public void setId(int id) { this.id = id; }
}
