package ram.ewe.englishwithexperts.Repositories;

public class ndmRepository {
    private int id;
    private String nivel;
    private String duracao;
    private double mensalidade;

    public ndmRepository()
    {}

    public ndmRepository(int id, String nivel, String duracao, double mensalidade)
    {
        this.id = id;
        this.nivel = nivel;
        this.duracao = duracao;
        this.mensalidade = mensalidade;
    }

    public int getId() { return this.id; }
        public String getNivel() { return this.nivel; }
            public String getDuracao() { return this.duracao; }
                public double getMensalidade() { return this.mensalidade; }
                public void setMensalidade(double mensalidade) { this.mensalidade = mensalidade; }
            public void setDuracao(String duracao) { this.duracao = duracao; }
        public void setNivel(String nivel) { this.nivel = nivel; }
    public void setId(int id) { this.id = id; }
}
