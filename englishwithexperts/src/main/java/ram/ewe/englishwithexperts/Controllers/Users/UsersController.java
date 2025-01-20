package ram.ewe.englishwithexperts.Controllers.Users;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import ram.ewe.englishwithexperts.Controllers.Alerts.Erro.AlertErroCriticoController;
import ram.ewe.englishwithexperts.Data.ConnectionDAO;
import ram.ewe.englishwithexperts.Data.ConnectionLITE;
import ram.ewe.englishwithexperts.Encrypt.PasswordUtil;
import ram.ewe.englishwithexperts.Models.Model;
import ram.ewe.englishwithexperts.Models.ModelDataDAO;
import ram.ewe.englishwithexperts.Models.ModelViewsDAO;
import ram.ewe.englishwithexperts.Repositories.LanguagesRepository;
import ram.ewe.englishwithexperts.Repositories.UsersLogsTableDataRepository;
import ram.ewe.englishwithexperts.Repositories.UsersTableDataRepository;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Ramadan IsmaeL
 */
public class UsersController implements Initializable {
    private static String senhaAtual;
    private Connection _connect = null;
    private Connection _connectLITE = null;
    private PreparedStatement _prepare = null;
    private Statement _statement;
    private ResultSet _rs = null;

    public StackPane formUserMain;
    public Label breadCrumb;
    public TextField search_userTable;
    public TableView<UsersTableDataRepository> user_TableView;
    public TableColumn<UsersTableDataRepository, Integer> user_col_Id;
    public TableColumn<UsersTableDataRepository, String> user_col_fullName;
    public TableColumn<UsersTableDataRepository, String> user_col_username;
    public TableColumn<UsersTableDataRepository, String> user_col_profile;
    public TableColumn<UsersTableDataRepository, String> user_col_state;
    public TableColumn<UsersTableDataRepository, Date> user_col_dateRegister;

    public Label ID;
    public FontAwesomeIconView eyePrimitive;
    public Label lblNomeCompleto;
    public Label lblUsuario;
    public Label lblSenha;
    public Label lblConfirmarSenha;
    public Label lblPerfil;
    public Label lblEstado;
    public TextField txtNomeCompleto;
    public TextField txtUsuario;
    public TextField txtSenha;
    public FontAwesomeIconView eyeSenha;
    public TextField fldSenha;
    public TextField txtConfirmarSenha;
    public FontAwesomeIconView eyeConfirmarSenha;
    public TextField fldConfirmarSenha;
    public ComboBox<String> txtPerfil;
    public RadioButton ativo;
    public RadioButton desativo;
    public Label lblAtivo;
    public Label lblDesativo;
    public Text mudarSenha;

    public Button btnAdd;
    public Button btnEdit;
    public Button btnRemove;
    public Button btnClean;

    public Label breadCrumbLog;
    public TextField search_userLogTable;
    public Text updateTable_userLog;
    public TableView<UsersLogsTableDataRepository> userLog_TableView;
    public TableColumn<UsersLogsTableDataRepository, String> userLog_col_username;
    public TableColumn<UsersLogsTableDataRepository, String> userLog_col_profile;
    public TableColumn<UsersLogsTableDataRepository, String> userLog_col_description;
    public TableColumn<UsersLogsTableDataRepository, Date> userLog_col_date;

    public UsersController() throws SQLException {
        _connect = ConnectionDAO.connectDAO();
        _connectLITE = ConnectionLITE.connectLite();
    }

    public static String getSenhaAtual() { return senhaAtual; }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(LanguagesRepository.getPort())
        {
            breadCrumb.setText("GERENCIAMENTO DE USUÁRIOS");
            search_userTable.setPromptText("Pesquisar");
            user_col_Id.setText("Ordem");
            user_col_fullName.setText("Nome Completo");
            user_col_username.setText("Usuário");
            user_col_profile.setText("Perfil");
            user_col_state.setText("Estado");
            user_col_dateRegister.setText("Data de Registro");

            lblNomeCompleto.setText("Nome Completo");
            lblUsuario.setText("Usuário");
            lblSenha.setText("Senha");
            lblConfirmarSenha.setText("Confirmar Senha");
            lblPerfil.setText("Perfil");
            lblEstado.setText("Estado");
            txtNomeCompleto.setPromptText("Nome Completo");
            txtUsuario.setPromptText("Usuário");
            txtSenha.setPromptText("Nova Senha");
            fldSenha.setPromptText("Nova Senha");
            txtConfirmarSenha.setPromptText("Confirmar Nova Senha");
            fldConfirmarSenha.setPromptText("Confirmar Nova Senha");
            lblAtivo.setText("  Ativo");
            lblDesativo.setText("  Desativo");
            mudarSenha.setText("Mudar Senha");

            btnAdd.setText("Registrar");
            btnEdit.setText("Atualizar");
            btnRemove.setText("Excluir");
            btnClean.setText("Limpar");

            breadCrumbLog.setText("Histórico de Ações do Usuário");
            search_userLogTable.setPromptText("Pesquisar");
            updateTable_userLog.setText("Atualizar Tabela");
            userLog_col_username.setText("Usuário");
            userLog_col_profile.setText("Perfil");
            userLog_col_description.setText("Descrição");
            userLog_col_date.setText("Data");

            /*
            breadCrumb.setTooltip(new Tooltip("Gerencie os usuários do sistema."));
            search_userTable.setTooltip(new Tooltip("Encontre informações rapidamente por NOME COMPLETO, USUÁRIO E PERFIL."));
            txtNomeCompleto.setTooltip(new Tooltip("Insira o nome completo do usuário."));
            txtUsuario.setTooltip(new Tooltip("Nome do usuário para acessar o sistema."));
            txtSenha.setTooltip(new Tooltip("Defina uma senha segura."));
            fldSenha.setTooltip(new Tooltip("Defina uma senha segura."));
            txtConfirmarSenha.setTooltip(new Tooltip("Confirme a senha inserida."));
            fldConfirmarSenha.setTooltip(new Tooltip("Confirme a senha inserida."));
            txtPerfil.setTooltip(new Tooltip("Escolha um perfil para o usuário"));
            ativo.setTooltip(new Tooltip("Indica que o usuário está ativo."));
            desativo.setTooltip(new Tooltip("Indica que o usuário está inativo."));
            btnAdd.setTooltip(new Tooltip("Registre um novo usuário."));
            btnEdit.setTooltip(new Tooltip("Atualize as informações do usuário."));
            btnRemove.setTooltip(new Tooltip("Remova um usuário do sistema."));
            btnClean.setTooltip(new Tooltip("Apague os campos de entrada."));
            breadCrumbLog.setTooltip(new Tooltip("Visualize as ações realizadas pelo usuário."));
            search_userLogTable.setTooltip(new Tooltip("Econtre informações rapidamente por USUÁRIO, PERFIL, DESCRIÇÃO E DATA."));
            */
        }
        else
        {
            breadCrumb.setText("USER MANAGEMENT");
            search_userTable.setPromptText("Search");
            user_col_Id.setText("Order");
            user_col_fullName.setText("Full Name");
            user_col_username.setText("Username");
            user_col_profile.setText("Profile");
            user_col_state.setText("Status");
            user_col_dateRegister.setText("Registration Date");

            lblNomeCompleto.setText("Full Name");
            lblUsuario.setText("Username");
            lblSenha.setText("Password");
            lblConfirmarSenha.setText("Confirm Password");
            lblPerfil.setText("Profile");
            lblEstado.setText("Status");
            txtNomeCompleto.setPromptText("Full Name");
            txtUsuario.setPromptText("Username");
            txtSenha.setPromptText("New Password");
            fldSenha.setPromptText("New Password");
            txtConfirmarSenha.setPromptText("Confirm New Password");
            fldConfirmarSenha.setPromptText("Confirm New Password");
            lblAtivo.setText("  Active");
            lblDesativo.setText("  Inactive");
            mudarSenha.setText("Change Password");

            btnAdd.setText("Register");
            btnEdit.setText("Update");
            btnRemove.setText("Delete");
            btnClean.setText("Clear");

            breadCrumbLog.setText("User Action History");
            search_userLogTable.setPromptText("Search");
            updateTable_userLog.setText("Update Table");
            userLog_col_username.setText("Username");
            userLog_col_profile.setText("Profile");
            userLog_col_description.setText("Description");
            userLog_col_date.setText("Date");
        }

        UsersTable();
        search_userTable.setOnKeyTyped(evt -> searchUsersTable());
        user_TableView.setOnMouseClicked(evt -> UsersSelect());
        /*SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String today = sdf.format(new java.util.Date());
        txtdate.setText(today);*/
        ObservableList<String> listData = FXCollections.observableArrayList(null, "admin", "user");
        txtPerfil.setItems(listData);
        txtPerfil.setOnAction(evt -> {
            txtPerfil.getItems();
        });
        mudarSenha.setOnMouseClicked(evt -> mudarSenha());
        estado();
        eyesPassword();
        campos();
        btnAdd.setOnAction(evt -> addUser());
        btnEdit.setOnAction(evt -> updateUser());
        btnRemove.setOnAction(evt -> RemoveUser());
        btnClean.setOnAction(evt -> resetBTN());

        UsersLogsTable();
        updateTable_userLog.setOnMouseClicked(evt -> {
            Model.getInstance().getAlertsFactory().getOnTableUpdate();
            UsersLogsTable();
        });
        search_userLogTable.setOnKeyTyped(evt -> searchUsersLogsTable());
    }

    public ObservableList<UsersTableDataRepository> addUserListData()
    {
        ObservableList<UsersTableDataRepository> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM tbUser";
        try {
            _prepare = _connect.prepareStatement(sql);
            _rs = _prepare.executeQuery();
            UsersTableDataRepository usersTableDataRepository;

            while(_rs.next())
            {
                usersTableDataRepository = new UsersTableDataRepository(
                        _rs.getInt("ID"),
                        _rs.getString("NOME"),
                        _rs.getString("USUÁRIO"),
                        _rs.getString("PERFIL"),
                        _rs.getBoolean("ESTADO"),
                        _rs.getDate("DATA_CRIAÇÃO"));
                listData.add(usersTableDataRepository);
                //System.out.println("ID : "+rs.getInt("ID")+"\nNome: "+rs.getString("NOME")+"\nUsuário :"+rs.getString("USUÁRIO")+"\nPerfil : "+rs.getString("PERFIL")+"\nEstado: "+rs.getString("ESTADO")+"\nData "+rs.getString("DATA_CRIAÇÃO"));
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return listData;
    }

    public void UsersTable()
    {
        ObservableList<UsersTableDataRepository> addUsersInTable = addUserListData();

        user_col_Id.setCellValueFactory(new PropertyValueFactory<>("id"));
        user_col_fullName.setCellValueFactory(new PropertyValueFactory<>("nome"));
        user_col_username.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        user_col_profile.setCellValueFactory(new PropertyValueFactory<>("perfil"));
        user_col_state.setCellValueFactory(new PropertyValueFactory<>("estado"));
        user_col_dateRegister.setCellValueFactory(new PropertyValueFactory<>("data"));

        user_col_fullName.setStyle("-fx-alignment: CENTER_LEFT");
        user_col_username.setStyle("-fx-alignment: CENTER_LEFT");

        user_TableView.setItems(addUsersInTable);

        user_TableView.getColumns().forEach(column -> column.setResizable(false));
        user_TableView.getColumns().forEach(column -> column.setReorderable(false));
        //user_TableView.getSortOrder().add(user_col_profile);
    }

    public void UsersSelect()
    {
        UsersTableDataRepository userData = user_TableView.getSelectionModel().getSelectedItem();
        int num = user_TableView.getSelectionModel().getSelectedIndex();
        if((num - 1) < -1) { return; }
        ID.setText(String.valueOf(userData.getId()));
        String id = ID.getText();
        txtNomeCompleto.setText(userData.getNome());
        txtUsuario.setText(userData.getUsuario());
        try
        {
            String checkExist = "SELECT SENHA FROM tbUser WHERE ID = '"+id+"'";
            _statement = _connect.createStatement();
            _rs = _statement.executeQuery(checkExist);
            if(_rs.next())
            {
                txtSenha.setText(_rs.getString(1));
            }
            txtConfirmarSenha.setText(txtSenha.getText());
            senhaAtual = txtConfirmarSenha.getText();
            //System.out.println("UsersController SENHA = "+senhaAtual);
        }
        catch (SQLException e)
        {
            AlertErroCriticoController.setErroCritico(e.getMessage());
            try {
                Model.getInstance().getAlertsFactory().getERROCRITICO();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        txtPerfil.setValue(userData.getPerfil());
        if(userData.getEstado())
        {
            ativo.setSelected(true);
            desativo.setSelected(false);
        }
        else
        {
            ativo.setSelected(false);
            desativo.setSelected(true);
        }
        if(!ID.getText().isEmpty())
        {
            txtSenha.setDisable(true);
            fldSenha.setDisable(true);
            eyeSenha.setFill(Color.RED);
            txtConfirmarSenha.setDisable(true);
            fldConfirmarSenha.setDisable(true);
            eyeConfirmarSenha.setFill(Color.RED);
            mudarSenha.setVisible(true);
        }
    }

    private void mudarSenha()
    {
        if(mudarSenha.isVisible())
        {
            try {
                ModelViewsDAO.getInstance().getViewsDAOFactory().getUsersChangePassword();
                ModelDataDAO.getInstance().getUserDataModel().setUserLogs(ModelDataDAO.getInstance().getUserDataModel().getUserName(), ModelDataDAO.getInstance().getUserDataModel().getProfile(), "Tela : [Definições] > [Usuários] > [Mudar Senha] -- aberto.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<UsersTableDataRepository> searchUsersDAO(String searchKey)
    {
        List<UsersTableDataRepository> usersList = new ArrayList<>();
        String sql = "SELECT * FROM tbUser WHERE NOME LIKE ? OR USUÁRIO LIKE ? OR PERFIL LIKE ?";

        try
        {
            _prepare = _connect.prepareStatement(sql);
            String likeKey = "%" + searchKey + "%";
            for(int i = 1; i <= 3; i++) { _prepare.setString(i, likeKey); }

            _rs = _prepare.executeQuery();
            while(_rs.next())
            {
                UsersTableDataRepository usersData = new UsersTableDataRepository();
                usersData.setId(_rs.getInt("ID"));
                usersData.setNome(_rs.getString("NOME"));
                usersData.setUsuario(_rs.getString("USUÁRIO"));
                usersData.setPerfil(_rs.getString("PERFIL"));
                usersData.setEstado(_rs.getBoolean("ESTADO"));
                usersData.setData(_rs.getDate("DATA_CRIAÇÃO"));
                usersList.add(usersData);
            }
        }
        catch(SQLException e) {
            AlertErroCriticoController.setErroCritico(e.getMessage());
            try {
                Model.getInstance().getAlertsFactory().getERROCRITICO();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        return usersList;
    }

    public void searchUsersTable()
    {
        search_userTable.textProperty().addListener((observable, oldValue, newValue) ->{
            List<UsersTableDataRepository> searching = searchUsersDAO(newValue.toLowerCase());
            user_TableView.setItems(FXCollections.observableArrayList(searching));
        });
    }

    public void eyesPassword()
    {
        //System.out.println("isDisable = "+txtSenha.isDisabled()+" e !isDisable = "+!txtSenha.isDisabled());
        fldSenha.setVisible(false);
        txtSenha.setVisible(true);
        txtSenha.textProperty().bindBidirectional(fldSenha.textProperty());

        eyeSenha.setOnMouseClicked(MouseEvent ->
        {
            if(txtSenha.isDisabled() && fldSenha.isDisabled() && txtConfirmarSenha.isDisabled() && fldConfirmarSenha.isDisabled())
            {
                eyeSenha.setFill(Color.RED);
                eyeConfirmarSenha.setFill(Color.RED);
            }
            else
            {
                if(!txtSenha.isVisible() && fldSenha.isVisible())
                {
                    eyeSenha.setIcon(FontAwesomeIcon.EYE);
                    txtSenha.setVisible(true);
                    fldSenha.setVisible(false);
                }
                else {
                    eyeSenha.setIcon(FontAwesomeIcon.EYE_SLASH);
                    txtSenha.setVisible(false);
                    fldSenha.setVisible(true);
                }
            }
        });

        fldConfirmarSenha.setVisible(false);
        txtConfirmarSenha.setVisible(true);
        txtConfirmarSenha.textProperty().bindBidirectional(fldConfirmarSenha.textProperty());
        eyeConfirmarSenha.setOnMouseClicked(MouseEvent ->
        {
            if(txtSenha.isDisabled() && fldSenha.isDisabled() && txtConfirmarSenha.isDisabled() && fldConfirmarSenha.isDisabled())
            {
                eyeSenha.setFill(Color.RED);
                eyeConfirmarSenha.setFill(Color.RED);
            }
            else
            {
                if(!txtConfirmarSenha.isVisible() && fldConfirmarSenha.isVisible())
                {
                    eyeConfirmarSenha.setIcon(FontAwesomeIcon.EYE);
                    txtConfirmarSenha.setVisible(true);
                    fldConfirmarSenha.setVisible(false);
                }
                else {
                    eyeConfirmarSenha.setIcon(FontAwesomeIcon.EYE_SLASH);
                    txtConfirmarSenha.setVisible(false);
                    fldConfirmarSenha.setVisible(true);
                }
            }
        });
    }

    private void estado()
    {
        ativo.setSelected(true);
        desativo.setSelected(false);

        ativo.setOnAction(evt -> {
            if(ativo.isSelected())
            {
                ativo.setSelected(true);
                desativo.setSelected(false);
            }
            else
            {
                ativo.setSelected(true);
                desativo.setSelected(false);
            }
        });

        desativo.setOnAction(evt -> {
            if(desativo.isSelected())
            {
                ativo.setSelected(false);
                desativo.setSelected(true);
            }
            else
            {
                ativo.setSelected(false);
                desativo.setSelected(true);
            }
        });
    }

    public void addUser()
    {
        if(_connect == null)
        {
            Model.getInstance().getAlertsFactory().getOnErroDataBase();
        }
        else
        {
            String sql = "INSERT INTO tbUser(NOME, USUÁRIO, SENHA, PERFIL, ESTADO) VALUES(?, ?, ?, ?, ?)";
            try
            {
                String checkExist = "SELECT NOME, USUÁRIO FROM tbUser WHERE NOME = '"+txtNomeCompleto.getText()+"' OR USUÁRIO = '"+txtUsuario.getText()+"'";
                _statement = _connect.createStatement();
                _rs = _statement.executeQuery(checkExist);
                if(_rs.next())
                {
                    validarCampos();
                    Model.getInstance().getAlertsFactory().getOnUserExistView();
                }
                else
                {
                    validarCampos();
                    if(!txtNomeCompleto.getText().isEmpty() && !txtUsuario.getText().isEmpty() && !txtSenha.getText().isEmpty() && !fldSenha.getText().isEmpty() && !txtConfirmarSenha.getText().isEmpty() && !fldConfirmarSenha.getText().isEmpty() && txtPerfil.getSelectionModel().getSelectedItem() != null && txtSenha.getText().length() >= 6 && fldSenha.getText().length() >= 6 && txtConfirmarSenha.getText().length() >= 6 && fldConfirmarSenha.getText().length() >= 6)
                    {
                        if(fldSenha.getText().equals(fldConfirmarSenha.getText()))
                        {
                            //System.out.println("Senha Igual");
                            _prepare = _connect.prepareStatement(sql);

                            _prepare.setString(1, txtNomeCompleto.getText());
                            _prepare.setString(2, txtUsuario.getText());
                            String passwordHash = PasswordUtil.generateHash(fldSenha.getText());
                            _prepare.setString(3, passwordHash);
                            _prepare.setString(4, (String) txtPerfil.getSelectionModel().getSelectedItem());
                            _prepare.setBoolean(5, ativo.isSelected());

                            _prepare.executeUpdate();
                            ModelDataDAO.getInstance().getUserDataModel().setUserLogs(ModelDataDAO.getInstance().getUserDataModel().getUserName(), ModelDataDAO.getInstance().getUserDataModel().getProfile(), "Tela : [Definições] > [Usuários] -- Novo usuário registrado. Usuário registrado { "+txtUsuario.getText()+" }");
                            UsersTable();
                            reset();
                            Model.getInstance().getAlertsFactory().getOnRegister();
                        }
                        else
                        {
                            //System.out.println("Senhas Diferentes");
                            txtSenha.getStyleClass().removeAll("textGray");
                            fldSenha.getStyleClass().removeAll("textGray");
                            lblSenha.getStyleClass().add("lblEmptyRed");
                            txtSenha.getStyleClass().add("textEmptyRed");
                            fldSenha.getStyleClass().add("textEmptyRed");

                            txtConfirmarSenha.getStyleClass().removeAll("textGray");
                            fldConfirmarSenha.getStyleClass().removeAll("textGray");
                            lblConfirmarSenha.getStyleClass().add("lblEmptyRed");
                            txtConfirmarSenha.getStyleClass().add("textEmptyRed");
                            fldConfirmarSenha.getStyleClass().add("textEmptyRed");
                            Model.getInstance().getAlertsFactory().getOnPasswordEqualView();
                        }
                    }
                }
            }
            catch (Exception e)
            {
                AlertErroCriticoController.setErroCritico(e.getMessage());
                try {
                    Model.getInstance().getAlertsFactory().getERROCRITICO();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    public void updateUser()
    {
        if(_connect == null)
        {
            Model.getInstance().getAlertsFactory().getOnErroDataBase();
        }
        else
        {
            String sql = "UPDATE tbUser SET NOME = ?, USUÁRIO = ?, SENHA = ?, PERFIL = ?, ESTADO = ? WHERE ID = ?";
            try
            {
                String checkExist = "SELECT NOME, USUÁRIO FROM tbUser WHERE NOME = '"+txtNomeCompleto.getText()+"' OR USUÁRIO = '"+txtUsuario.getText()+"'";
                Statement statement = _connect.createStatement();
                ResultSet rs = statement.executeQuery(checkExist);
                if(rs.next())
                {
                    validarCampos();
                    if(!txtNomeCompleto.getText().isEmpty() && !txtUsuario.getText().isEmpty() && !txtSenha.getText().isEmpty() && !fldSenha.getText().isEmpty() && !txtConfirmarSenha.getText().isEmpty() && !fldConfirmarSenha.getText().isEmpty() && txtPerfil.getSelectionModel().getSelectedItem() != null && txtSenha.getText().length() >= 6 && fldSenha.getText().length() >= 6 && txtConfirmarSenha.getText().length() >= 6 && fldConfirmarSenha.getText().length() >= 6)
                    {
                        if(fldSenha.getText().equals(fldConfirmarSenha.getText()))
                        {
                            String nome = null, usuario = null, senha = null, confSenha = null, perfil = null;
                            boolean estado = false;
                            String checkEquals = "SELECT NOME, USUÁRIO, SENHA, PERFIL, ESTADO FROM tbUser WHERE ID = '"+ID.getText()+"'";
                            try
                            {
                                Statement stmt = _connect.createStatement();
                                ResultSet rslt = stmt.executeQuery(checkEquals);
                                while(rslt.next())
                                {
                                    nome = rslt.getString(1);
                                    usuario = rslt.getString(2);
                                    senha = rslt.getString(3);
                                    confSenha = senha;
                                    perfil = rslt.getString(4);
                                    estado = rslt.getBoolean(5);
                                }
                            }
                            catch(Exception e) {
                                AlertErroCriticoController.setErroCritico(e.getMessage());
                            }

                            //System.out.println("Senha Igual");
                            if(txtNomeCompleto.getText().equals(nome) && txtUsuario.getText().equals(usuario) && fldSenha.getText().equals(senha) && fldConfirmarSenha.getText().equals(confSenha) && txtPerfil.getSelectionModel().getSelectedItem().equals(perfil) && ativo.isSelected() == estado)
                            {
                                //System.out.println("Nenhuma alteração detectada.");
                                Model.getInstance().getAlertsFactory().getOnNoChanges();
                            }
                            else
                            {
                                _prepare = _connect.prepareStatement(sql);
                                _prepare.setString(1, txtNomeCompleto.getText());
                                _prepare.setString(2, txtUsuario.getText());
                                if(fldSenha.getText().equals(_rs.getString(1)))
                                {
                                    _prepare.setString(3, fldSenha.getText());
                                }
                                else
                                {
                                    String passwordHash = PasswordUtil.generateHash(fldSenha.getText());
                                    _prepare.setString(3, passwordHash);
                                }
                                _prepare.setString(4, (String) txtPerfil.getSelectionModel().getSelectedItem());
                                _prepare.setBoolean(5, ativo.isSelected());
                                _prepare.setString(6, ID.getText());

                                _prepare.executeUpdate();
                                ModelDataDAO.getInstance().getUserDataModel().setUserLogs(ModelDataDAO.getInstance().getUserDataModel().getUserName(), ModelDataDAO.getInstance().getUserDataModel().getProfile(), "Tela : [Definições] > [Usuários] -- Atualizou as informações.");
                                UsersTable();
                                reset();
                                Model.getInstance().getAlertsFactory().getOnUpdate();
                            }
                        }
                        else
                        {
                            //System.out.println("Senhas Diferentes");
                            txtSenha.getStyleClass().removeAll("textGray");
                            fldSenha.getStyleClass().removeAll("textGray");
                            lblSenha.getStyleClass().add("lblEmptyRed");
                            txtSenha.getStyleClass().add("textEmptyRed");
                            fldSenha.getStyleClass().add("textEmptyRed");

                            txtConfirmarSenha.getStyleClass().removeAll("textGray");
                            fldConfirmarSenha.getStyleClass().removeAll("textGray");
                            lblConfirmarSenha.getStyleClass().add("lblEmptyRed");
                            txtConfirmarSenha.getStyleClass().add("textEmptyRed");
                            fldConfirmarSenha.getStyleClass().add("textEmptyRed");
                            Model.getInstance().getAlertsFactory().getOnPasswordEqualView();
                        }
                    }
                }
                else
                {
                    //System.out.println("User does not exist.!");
                    Model.getInstance().getAlertsFactory().getOnUserNotExistView();
                }

            }
            catch (Exception e)
            {
                AlertErroCriticoController.setErroCritico(e.getMessage());
                try {
                    Model.getInstance().getAlertsFactory().getERROCRITICO();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    public void RemoveUser()
    {
        if(_connect == null)
        {
            Model.getInstance().getAlertsFactory().getOnErroDataBase();
        }
        else
        {
            try
            {
                String checkExist = "SELECT NOME, USUÁRIO FROM tbUser WHERE NOME = '"+txtNomeCompleto.getText()+"' OR USUÁRIO = '"+txtUsuario.getText()+"'";
                Statement statement = _connect.createStatement();
                ResultSet rs = statement.executeQuery(checkExist);
                if(rs.next())
                {
                    validarCampos();
                    if(!txtNomeCompleto.getText().isEmpty() && !txtUsuario.getText().isEmpty() && !txtSenha.getText().isEmpty() && !fldSenha.getText().isEmpty() && !txtConfirmarSenha.getText().isEmpty() && !fldConfirmarSenha.getText().isEmpty() && txtPerfil.getSelectionModel().getSelectedItem() != null && txtSenha.getText().length() >= 6 && fldSenha.getText().length() >= 6 && txtConfirmarSenha.getText().length() >= 6 && fldConfirmarSenha.getText().length() >= 6)
                    {
                        if(fldSenha.getText().equals(fldConfirmarSenha.getText()))
                        {
                            String nome = null, usuario = null, senha = null, confSenha = null, perfil = null;
                            boolean estado = false;
                            String checkEquals = "SELECT NOME, USUÁRIO, SENHA, PERFIL, ESTADO FROM tbUser WHERE ID = '"+ID.getText()+"'";
                            try
                            {
                                Statement stmt = _connect.createStatement();
                                ResultSet rslt = stmt.executeQuery(checkEquals);
                                while(rslt.next())
                                {
                                    nome = rslt.getString(1);
                                    usuario = rslt.getString(2);
                                    senha = rslt.getString(3);
                                    confSenha = senha;
                                    perfil = rslt.getString(4);
                                    estado = rslt.getBoolean(5);
                                }
                            }
                            catch(Exception e) {
                                AlertErroCriticoController.setErroCritico(e.getMessage());
                                try {
                                    Model.getInstance().getAlertsFactory().getERROCRITICO();
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }

                            //System.out.println("Senha Igual");
                            if(txtNomeCompleto.getText().equals(nome) && txtUsuario.getText().equals(usuario) && fldSenha.getText().equals(senha) && fldConfirmarSenha.getText().equals(confSenha) && txtPerfil.getSelectionModel().getSelectedItem().equals(perfil) && ativo.isSelected() == estado)
                            {
                                Model.getInstance().getAlertsFactory().getOnQ_RemoveUser();
                            }
                            else
                            {
                                Model.getInstance().getAlertsFactory().getOnQQ_RemoveUser();
                            }
                        }
                        else
                        {
                            //System.out.println("Senhas Diferentes");
                            txtSenha.getStyleClass().removeAll("textGray");
                            fldSenha.getStyleClass().removeAll("textGray");
                            lblSenha.getStyleClass().add("lblEmptyRed");
                            txtSenha.getStyleClass().add("textEmptyRed");
                            fldSenha.getStyleClass().add("textEmptyRed");

                            txtConfirmarSenha.getStyleClass().removeAll("textGray");
                            fldConfirmarSenha.getStyleClass().removeAll("textGray");
                            lblConfirmarSenha.getStyleClass().add("lblEmptyRed");
                            txtConfirmarSenha.getStyleClass().add("textEmptyRed");
                            fldConfirmarSenha.getStyleClass().add("textEmptyRed");
                            Model.getInstance().getAlertsFactory().getOnPasswordEqualView();
                        }
                    }
                }
                else
                {
                    //System.out.println("User does not exist.!");
                    Model.getInstance().getAlertsFactory().getOnUserNotExistView();
                }

            }
            catch (Exception e)
            {
                AlertErroCriticoController.setErroCritico(e.getMessage());
                try {
                    Model.getInstance().getAlertsFactory().getERROCRITICO();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    public void deleteUser()
    {
        String sql = "DELETE FROM tbUser WHERE ID = ?";
        try {
            _prepare = _connect.prepareStatement(sql);
            _prepare.setString(1, ID.getText());
            _prepare.executeUpdate();
            ModelDataDAO.getInstance().getUserDataModel().setUserLogs(ModelDataDAO.getInstance().getUserDataModel().getUserName(), ModelDataDAO.getInstance().getUserDataModel().getProfile(), "Tela : [Definições] > [Usuários] -- Excluíu 1 usuário. Usuário excluído { "+txtUsuario.getText()+" } e Nome Completo { "+txtNomeCompleto.getText()+" }.");
            UsersTable();
            reset();
            Model.getInstance().getAlertsFactory().getOnRemove();
        }
        catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        //System.out.println("Excluido com sucesso");
    }

    private void resetBTN()
    {
        if((txtNomeCompleto.getText().isEmpty() && txtUsuario.getText().isEmpty() && (txtSenha.getText().isEmpty() && fldSenha.getText().isEmpty()) && (txtConfirmarSenha.getText().isEmpty() && fldConfirmarSenha.getText().isEmpty()) && txtPerfil.getSelectionModel().getSelectedItem() == null && ativo.isSelected() && eyeSenha.getText().equals(eyePrimitive.getText()) && eyeConfirmarSenha.getText().equals(eyePrimitive.getText())) && (!txtSenha.isDisabled() && !fldSenha.isDisabled() && !txtConfirmarSenha.isDisabled() && !fldConfirmarSenha.isDisabled()))
        {
                lblNomeCompleto.getStyleClass().removeAll("lblEmptyRed");
                txtNomeCompleto.getStyleClass().removeAll("textEmptyRed", "textGray");
                lblNomeCompleto.getStyleClass().add("labelRegisterView");
                txtNomeCompleto.getStyleClass().add("textFieldRegisterView");

                lblUsuario.getStyleClass().removeAll("lblEmptyRed");
                txtUsuario.getStyleClass().removeAll("textEmptyRed", "textGray");
                lblUsuario.getStyleClass().add("labelRegisterView");
                txtUsuario.getStyleClass().add("textFieldRegisterView");

                lblSenha.getStyleClass().removeAll("lblEmptyRed");
                txtSenha.getStyleClass().removeAll("textEmptyRed", "textGray");
                fldSenha.getStyleClass().removeAll("textEmptyRed", "textGray");
                lblSenha.getStyleClass().add("labelRegisterView");
                txtSenha.getStyleClass().add("textFieldRegisterView");
                fldSenha.getStyleClass().add("textFieldRegisterView");

                lblConfirmarSenha.getStyleClass().removeAll("lblEmptyRed");
                txtConfirmarSenha.getStyleClass().removeAll("textEmptyRed", "textGray");
                fldConfirmarSenha.getStyleClass().removeAll("textEmptyRed", "textGray");
                lblConfirmarSenha.getStyleClass().add("labelRegisterView");
                txtConfirmarSenha.getStyleClass().add("textFieldRegisterView");
                fldConfirmarSenha.getStyleClass().add("textFieldRegisterView");

                lblPerfil.getStyleClass().removeAll("lblEmptyRed");
                txtPerfil.getStyleClass().removeAll("textEmptyRed", "textGray");
                lblPerfil.getStyleClass().add("labelRegisterView");
                txtPerfil.getStyleClass().add("textFieldRegisterView");

            Model.getInstance().getAlertsFactory().getOnEmptyResetFieldsView();
        }
        else
        {
            if(txtSenha.getText().isEmpty() && fldSenha.getText().isEmpty())
            {
                txtSenha.setVisible(true);
                fldSenha.setVisible(false);
            }
            if(txtConfirmarSenha.getText().isEmpty() && fldConfirmarSenha.getText().isEmpty())
            {
                txtConfirmarSenha.setVisible(true);
                fldConfirmarSenha.setVisible(false);
            }
            reset();
            Model.getInstance().getAlertsFactory().getOnResetFields();
        }
    }

    private void validarCampos()
    {
        if(txtNomeCompleto.getText().isEmpty())
        {
            txtNomeCompleto.getStyleClass().removeAll("textGray");

            lblNomeCompleto.getStyleClass().add("lblEmptyRed");
            txtNomeCompleto.getStyleClass().add("textEmptyRed");
        }
        if(txtUsuario.getText().isEmpty())
        {
            txtUsuario.getStyleClass().removeAll("textGray");

            lblUsuario.getStyleClass().add("lblEmptyRed");
            txtUsuario.getStyleClass().add("textEmptyRed");
        }
        if(txtSenha.getText().isEmpty() && fldSenha.getText().isEmpty())
        {
            txtSenha.getStyleClass().removeAll("textGray");
            fldSenha.getStyleClass().removeAll("textGray");

            lblSenha.getStyleClass().add("lblEmptyRed");
            txtSenha.getStyleClass().add("textEmptyRed");
            fldSenha.getStyleClass().add("textEmptyRed");
        }
        if(txtConfirmarSenha.getText().isEmpty() && fldConfirmarSenha.getText().isEmpty())
        {
            txtConfirmarSenha.getStyleClass().removeAll("textGray");
            fldConfirmarSenha.getStyleClass().removeAll("textGray");

            lblConfirmarSenha.getStyleClass().add("lblEmptyRed");
            txtConfirmarSenha.getStyleClass().add("textEmptyRed");
            fldConfirmarSenha.getStyleClass().add("textEmptyRed");
        }
        if(txtPerfil.getSelectionModel().getSelectedItem() == null)
        {
            txtPerfil.getStyleClass().removeAll("textGray");

            lblPerfil.getStyleClass().add("lblEmptyRed");
            txtPerfil.getStyleClass().add("textEmptyRed");
        }
        if(txtNomeCompleto.getText().isEmpty() || txtUsuario.getText().isEmpty() || txtSenha.getText().isEmpty() || fldSenha.getText().isEmpty() || txtConfirmarSenha.getText().isEmpty() || fldConfirmarSenha.getText().isEmpty() || txtPerfil.getSelectionModel().getSelectedItem() == null)
        {
            Model.getInstance().getAlertsFactory().getOnEmptyView();
        }

        blackValidation();
    }

    private void blackValidation()
    {
        if(!txtNomeCompleto.getText().isEmpty())
        {
            lblNomeCompleto.getStyleClass().removeAll("lblEmptyRed");
            txtNomeCompleto.getStyleClass().removeAll("textEmptyRed");

            lblNomeCompleto.getStyleClass().add("labelRegisterView");
            txtNomeCompleto.getStyleClass().add("textGray");
        }
        if(!txtUsuario.getText().isEmpty())
        {
            lblUsuario.getStyleClass().removeAll("lblEmptyRed");
            txtUsuario.getStyleClass().removeAll("textEmptyRed");

            lblUsuario.getStyleClass().add("labelRegisterView");
            txtUsuario.getStyleClass().add("textGray");
        }
        if(!txtSenha.getText().isEmpty() && !fldSenha.getText().isEmpty())
        {
            if(txtSenha.getText().length() >= 6)
            {
                lblSenha.getStyleClass().removeAll("lblEmptyRed");
                txtSenha.getStyleClass().removeAll("textEmptyRed");
                fldSenha.getStyleClass().removeAll("textEmptyRed");

                lblSenha.getStyleClass().add("labelRegisterView");
                txtSenha.getStyleClass().add("textGray");
                fldSenha.getStyleClass().add("textGray");
            }
            else
            {
                txtSenha.getStyleClass().removeAll("textGray");
                fldSenha.getStyleClass().removeAll("textGray");

                lblSenha.getStyleClass().add("lblEmptyRed");
                txtSenha.getStyleClass().add("textEmptyRed");
                fldSenha.getStyleClass().add("textEmptyRed");
                Model.getInstance().getAlertsFactory().getOnPassword6View();
            }
        }
        if(!txtConfirmarSenha.getText().isEmpty() && !fldConfirmarSenha.getText().isEmpty())
        {
            if(txtConfirmarSenha.getText().length() >= 6)
            {
                lblConfirmarSenha.getStyleClass().removeAll("lblEmptyRed");
                txtConfirmarSenha.getStyleClass().removeAll("textEmptyRed");
                fldConfirmarSenha.getStyleClass().removeAll("textEmptyRed");

                lblConfirmarSenha.getStyleClass().add("labelRegisterView");
                txtConfirmarSenha.getStyleClass().add("textGray");
                fldConfirmarSenha.getStyleClass().add("textGray");
            }
            else
            {
                txtConfirmarSenha.getStyleClass().removeAll("textGray");
                fldConfirmarSenha.getStyleClass().removeAll("textGray");

                lblConfirmarSenha.getStyleClass().add("lblEmptyRed");
                txtConfirmarSenha.getStyleClass().add("textEmptyRed");
                fldConfirmarSenha.getStyleClass().add("textEmptyRed");
                Model.getInstance().getAlertsFactory().getOnPassword6View();
            }
        }
        if(txtPerfil.getSelectionModel().getSelectedItem() != null)
        {
            lblPerfil.getStyleClass().removeAll("lblEmptyRed");
            txtPerfil.getStyleClass().removeAll("textEmptyRed");

            lblPerfil.getStyleClass().add("labelRegisterView");
            txtPerfil.getStyleClass().add("textGray");
        }
    }

    private void reset()
    {
        if(txtNomeCompleto.getText().isEmpty() || !txtNomeCompleto.getText().isEmpty())
        {
            if(!txtNomeCompleto.getText().isEmpty()) { txtNomeCompleto.clear(); }
            lblNomeCompleto.getStyleClass().removeAll("lblEmptyRed");
            txtNomeCompleto.getStyleClass().removeAll("textEmptyRed", "textGray");

            lblNomeCompleto.getStyleClass().add("labelRegisterView");
            txtNomeCompleto.getStyleClass().add("textFieldRegisterView");
        }
        if(txtUsuario.getText().isEmpty() || !txtUsuario.getText().isEmpty())
        {
            if(!txtUsuario.getText().isEmpty()) { txtUsuario.clear(); }
            lblUsuario.getStyleClass().removeAll("lblEmptyRed");
            txtUsuario.getStyleClass().removeAll("textEmptyRed", "textGray");

            lblUsuario.getStyleClass().add("labelRegisterView");
            txtUsuario.getStyleClass().add("textFieldRegisterView");
        }
        if((txtSenha.getText().isEmpty() && fldSenha.getText().isEmpty()) || (!txtSenha.getText().isEmpty() && !fldSenha.getText().isEmpty()))
        {
            if(!txtSenha.getText().isEmpty() && !fldSenha.getText().isEmpty())
            {
                txtSenha.clear();
                fldSenha.clear();
                fldSenha.setVisible(false);
                txtSenha.setVisible(true);
            }
            lblSenha.getStyleClass().removeAll("lblEmptyRed");
            txtSenha.getStyleClass().removeAll("textEmptyRed", "textGray");
            fldSenha.getStyleClass().removeAll("textEmptyRed", "textGray");

            lblSenha.getStyleClass().add("labelRegisterView");
            txtSenha.getStyleClass().add("textFieldRegisterView");
            fldSenha.getStyleClass().add("textFieldRegisterView");
        }
        if((txtConfirmarSenha.getText().isEmpty() && fldConfirmarSenha.getText().isEmpty()) || (!txtConfirmarSenha.getText().isEmpty() && !fldConfirmarSenha.getText().isEmpty()))
        {
            if(!txtConfirmarSenha.getText().isEmpty() && !fldConfirmarSenha.getText().isEmpty())
            {
                txtConfirmarSenha.clear();
                fldConfirmarSenha.clear();
                fldConfirmarSenha.setVisible(false);
                txtConfirmarSenha.setVisible(true);
            }
            lblConfirmarSenha.getStyleClass().removeAll("lblEmptyRed");
            txtConfirmarSenha.getStyleClass().removeAll("textEmptyRed", "textGray");
            fldConfirmarSenha.getStyleClass().removeAll("textEmptyRed", "textGray");

            lblConfirmarSenha.getStyleClass().add("labelRegisterView");
            txtConfirmarSenha.getStyleClass().add("textFieldRegisterView");
            fldConfirmarSenha.getStyleClass().add("textFieldRegisterView");
        }
        if((txtPerfil.getSelectionModel().getSelectedItem() == null) || (txtPerfil.getSelectionModel().getSelectedItem() != null))
        {
            if(txtPerfil.getSelectionModel().getSelectedItem() != null) { txtPerfil.getSelectionModel().clearSelection(); }
            lblPerfil.getStyleClass().removeAll("lblEmptyRed");
            txtPerfil.getStyleClass().removeAll("textEmptyRed", "textGray");

            lblPerfil.getStyleClass().add("labelRegisterView");
            txtPerfil.getStyleClass().add("textFieldRegisterView");
        }

        if(!eyeSenha.getText().equals(eyePrimitive.getText())) { eyeSenha.setIcon(FontAwesomeIcon.EYE); }
        if(!eyeConfirmarSenha.getText().equals(eyePrimitive.getText())){ eyeConfirmarSenha.setIcon(FontAwesomeIcon.EYE); }

        ativo.setSelected(true);
        desativo.setSelected(false);

        if(txtSenha.isDisabled() && fldSenha.isDisabled() && txtConfirmarSenha.isDisabled() && fldConfirmarSenha.isDisabled())
        {
            txtSenha.setDisable(false);
            fldSenha.setDisable(false);
            txtConfirmarSenha.setDisable(false);
            fldConfirmarSenha.setDisable(false);
            eyeSenha.setFill(Color.rgb(67, 69, 67));
            eyeConfirmarSenha.setFill(Color.rgb(67, 69, 67));
        }
        mudarSenha.setVisible(false);
    }

    private void campos()
    {
        txtNomeCompleto.setOnKeyTyped(evt -> {
            if(!txtNomeCompleto.getText().isEmpty())
            {
                lblNomeCompleto.getStyleClass().removeAll("lblEmptyRed");
                txtNomeCompleto.getStyleClass().removeAll("textEmptyRed");

                lblNomeCompleto.getStyleClass().add("labelRegisterView");
                txtNomeCompleto.getStyleClass().add("textGray");
            }
        });
        txtUsuario.setOnKeyTyped(evt -> {
            if(!txtUsuario.getText().isEmpty())
            {
                lblUsuario.getStyleClass().removeAll("lblEmptyRed");
                txtUsuario.getStyleClass().removeAll("textEmptyRed");

                lblUsuario.getStyleClass().add("labelRegisterView");
                txtUsuario.getStyleClass().add("textGray");
            }
        });
        txtSenha.setOnKeyTyped(evt -> {
            if(!txtSenha.getText().isEmpty() && !fldSenha.getText().isEmpty())
            {
                lblSenha.getStyleClass().removeAll("lblEmptyRed");
                txtSenha.getStyleClass().removeAll("textEmptyRed");
                fldSenha.getStyleClass().removeAll("textEmptyRed");

                lblSenha.getStyleClass().add("labelRegisterView");
                txtSenha.getStyleClass().add("textGray");
                fldSenha.getStyleClass().add("textGray");
            }
        });
        fldSenha.setOnKeyTyped(evt -> {
            if(!txtSenha.getText().isEmpty() && !fldSenha.getText().isEmpty())
            {
                lblSenha.getStyleClass().removeAll("lblEmptyRed");
                txtSenha.getStyleClass().removeAll("textEmptyRed");
                fldSenha.getStyleClass().removeAll("textEmptyRed");

                lblSenha.getStyleClass().add("labelRegisterView");
                txtSenha.getStyleClass().add("textGray");
                fldSenha.getStyleClass().add("textGray");
            }
        });
        txtConfirmarSenha.setOnKeyTyped(evt -> {
            if(!txtConfirmarSenha.getText().isEmpty() && !fldConfirmarSenha.getText().isEmpty())
            {
                lblConfirmarSenha.getStyleClass().removeAll("lblEmptyRed");
                txtConfirmarSenha.getStyleClass().removeAll("textEmptyRed");
                fldConfirmarSenha.getStyleClass().removeAll("textEmptyRed");

                lblConfirmarSenha.getStyleClass().add("labelRegisterView");
                txtConfirmarSenha.getStyleClass().add("textGray");
                fldConfirmarSenha.getStyleClass().add("textGray");
            }
        });
        fldConfirmarSenha.setOnKeyTyped(evt -> {
            if(!txtConfirmarSenha.getText().isEmpty() && !fldConfirmarSenha.getText().isEmpty())
            {
                lblConfirmarSenha.getStyleClass().removeAll("lblEmptyRed");
                txtConfirmarSenha.getStyleClass().removeAll("textEmptyRed");
                fldConfirmarSenha.getStyleClass().removeAll("textEmptyRed");

                lblConfirmarSenha.getStyleClass().add("labelRegisterView");
                txtConfirmarSenha.getStyleClass().add("textGray");
                fldConfirmarSenha.getStyleClass().add("textGray");
            }
        });
        txtPerfil.setOnAction(evt -> {
            if(txtPerfil.getSelectionModel().getSelectedItem() != null)
            {
                lblPerfil.getStyleClass().removeAll("lblEmptyRed");
                txtPerfil.getStyleClass().removeAll("textEmptyRed");

                lblPerfil.getStyleClass().add("labelRegisterView");
                txtPerfil.getStyleClass().add("textGray");
            }
        });
    }

    // USERS LOGS
    public ObservableList<UsersLogsTableDataRepository> addUsersLogsListData()
    {
        ObservableList<UsersLogsTableDataRepository> listData = FXCollections.observableArrayList();
        String sql = "SELECT USUARIO, PERFIL, DESCRICAO, ULTIMO_LOGIN FROM tbUserLogs ORDER BY ULTIMO_LOGIN DESC";
        try {
            _prepare = _connectLITE.prepareStatement(sql);
            _rs = _prepare.executeQuery();
            UsersLogsTableDataRepository usersLogsTableDataRepository;

            while(_rs.next())
            {
                usersLogsTableDataRepository = new UsersLogsTableDataRepository(
                        _rs.getString("USUARIO"),
                        _rs.getString("PERFIL"),
                        _rs.getString("DESCRICAO"),
                        _rs.getString("ULTIMO_LOGIN"));
                listData.add(usersLogsTableDataRepository);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return listData;
    }

    public void UsersLogsTable()
    {
        ObservableList<UsersLogsTableDataRepository> addUsersLogsInTable = addUsersLogsListData();

        userLog_col_username.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        userLog_col_profile.setCellValueFactory(new PropertyValueFactory<>("perfil"));
        userLog_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        userLog_col_date.setCellValueFactory(new PropertyValueFactory<>("data"));

        userLog_col_username.setStyle("-fx-alignment: CENTER_LEFT");
        userLog_col_description.setStyle("-fx-alignment: CENTER_LEFT");

        userLog_TableView.setItems(addUsersLogsInTable);

        userLog_TableView.getColumns().forEach(column -> column.setResizable(true));
        userLog_TableView.getColumns().forEach(column -> column.setReorderable(false));
    }

    public List<UsersLogsTableDataRepository> searchUsersLogsDAO(String searchKey)
    {
        List<UsersLogsTableDataRepository> usersList = new ArrayList<>();
        String sql = "SELECT USUARIO, PERFIL, DESCRICAO, ULTIMO_LOGIN FROM tbUserLogs WHERE USUARIO LIKE ? OR PERFIL LIKE ? OR DESCRICAO LIKE ? OR ULTIMO_LOGIN LIKE ? ORDER BY ULTIMO_LOGIN DESC";

        try
        {
            _prepare = _connectLITE.prepareStatement(sql);
            String likeKey = "%" + searchKey + "%";
            for(int i = 1; i <= 4; i++) { _prepare.setString(i, likeKey); }

            _rs = _prepare.executeQuery();
            while(_rs.next())
            {
                UsersLogsTableDataRepository usersData = new UsersLogsTableDataRepository();
                usersData.setUsuario(_rs.getString("USUARIO"));
                usersData.setPerfil(_rs.getString("PERFIL"));
                usersData.setDescription(_rs.getString("DESCRICAO"));
                usersData.setData(_rs.getString("ULTIMO_LOGIN"));
                usersList.add(usersData);
            }
        }
        catch(Exception e) {
            AlertErroCriticoController.setErroCritico(e.getMessage());
            try {
                Model.getInstance().getAlertsFactory().getERROCRITICO();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        return usersList;
    }

    public void searchUsersLogsTable()
    {
        search_userLogTable.textProperty().addListener((observable, oldValue, newValue) ->{
            List<UsersLogsTableDataRepository> searching = searchUsersLogsDAO(newValue.toLowerCase());
            userLog_TableView.setItems(FXCollections.observableArrayList(searching));
        });
    }
}
