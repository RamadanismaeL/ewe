package ram.ewe.englishwithexperts.Repositories;

public class hmRepository {
    private int id;
    private String horario;
    private String modalidade;

    public hmRepository()
    {}

    public hmRepository(int id, String horario, String modalidade)
    {
        this.id = id;
        this.horario = horario;
        this.modalidade = modalidade;
    }

    public int getId() { return this.id; }
        public String getHorario() { return this.horario; }
            public String getModalidade() { return this.modalidade; }
            public void setModalidade(String modalidade) { this.modalidade = modalidade; }
        public void setHorario(String horario) { this.horario = horario; }
    public void setId(int id) { this.id = id; }
}
