package ram.ewe.englishwithexperts.Repositories;

import java.sql.Date;

public class UsersTableDataRepository {
    private int id;
    private String nome;
    private String usuario;
    private String perfil;
    private boolean estado;
    private Date data;

    public UsersTableDataRepository()
    {}

    public UsersTableDataRepository(int userID, String fullName, String username, String profile, boolean state, Date date)
    {
        this.id = userID;
        this.nome = fullName;
        this.usuario = username;
        this.perfil = profile;
        this.estado = state;
        this.data = date;
    }

    public int getId() { return this.id; }
        public String getNome() { return this.nome; }
            public String getUsuario() { return this.usuario; }
                public String getPerfil() { return this.perfil; }
                    public boolean getEstado() { return  this.estado; }
                        public Date getData() { return this.data; }
                        public void setData(Date data) { this.data = data; }
                    public void setEstado(boolean estado) { this.estado = estado; }
                public void setPerfil(String perfil) { this.perfil = perfil; }
            public void setUsuario(String usuario) { this.usuario = usuario; }
        public void setNome(String nome) { this.nome = nome; }
    public void setId(int id) { this.id = id; }
}
