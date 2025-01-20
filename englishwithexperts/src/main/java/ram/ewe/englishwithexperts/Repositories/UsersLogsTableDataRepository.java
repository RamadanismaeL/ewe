package ram.ewe.englishwithexperts.Repositories;

import java.sql.Date;

public class UsersLogsTableDataRepository {
    private String usuario;
    private String perfil;
    private String description;
    private String date;

    public UsersLogsTableDataRepository()
    {}

    public UsersLogsTableDataRepository(String username, String profile, String description, String date)
    {
        this.usuario = username;
        this.perfil = profile;
        this.description = description;
        this.date = date;
    }

    public String getUsuario() { return this.usuario; }
        public String getPerfil() { return this.perfil; }
            public String getDescription() { return  this.description; }
                public String getData() { return this.date; }
                public void setData(String data) { this.date = data; }
            public void setDescription(String description) { this.description = description; }
        public void setPerfil(String perfil) { this.perfil = perfil; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
}
