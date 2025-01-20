package ram.ewe.englishwithexperts.Controllers.Ferramentas;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ram.ewe.englishwithexperts.Controllers.Alerts.Erro.AlertErroCriticoController;
import ram.ewe.englishwithexperts.Data.ConnectionLITERegistrations;
import ram.ewe.englishwithexperts.Models.Model;
import ram.ewe.englishwithexperts.Repositories.*;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

/**
 * @author Ramadan Ismael
 */
public class FerramentasController implements Initializable
{
    private int id_genero, id_civil, id_relacao, id_ndm, id_hm, id_dp;
    private boolean exist;

    public VBox formFerramentas;
    public Button btnClose;

    public Label lblTitle;
    public Label inscricaoTitle;
    public Label inscricao_lbl;
    public Spinner<Double> inscricao_txt;
    public Label inscricao_valorAtual_lbl;
    public Text inscricao_valorAtual_txt;
    public MenuButton inscricao_btn;
    public FontAwesomeIconView inscricao_icon_btn;
    public MenuItem inscricao_atualizar_btn;
    public MenuItem inscricao_limpar_btn;

    public Label ndm_id;
    public Label ndm_nivel_lbl;
    public Label ndm_duracao_lbl;
    public Label ndm_mensalidade_lbl;
    public TextField ndm_nivel_txt;
    public TextField ndm_duracao_txt;
    public Spinner<Double> ndm_mensalidade_txt;
    public MenuButton ndm_btn;
    public FontAwesomeIconView ndm_icon_btn;
    public MenuItem ndm_registrar_btn;
    public MenuItem ndm_atualizar_btn;
    public MenuItem ndm_excluir_btn;
    public MenuItem ndm_limpar_btn;
    public TableView<ndmRepository> ndm_tableView;
    public TableColumn<ndmRepository, String> ndm_col_nivel;
    public TableColumn<ndmRepository, String> ndm_col_duracao;
    public TableColumn<ndmRepository, Double> ndm_col_mensalidade;

    public Label hm_id;
    public Label hm_horario_lbl;
    public Label hm_modalidade_lbl;
    public TextField hm_horario_txt;
    public ComboBox<String> hm_modalidade_txt;
    public MenuButton hm_btn;
    public FontAwesomeIconView hm_icon_btn;
    public MenuItem hm_registrar_btn;
    public MenuItem hm_atualizar_btn;
    public MenuItem hm_excluir_btn;
    public MenuItem hm_limpar_btn;
    public TableView<hmRepository> hm_tableView;
    public TableColumn<hmRepository, String> hm_col_horario;
    public TableColumn<hmRepository, String> hm_col_modalidade;

    public Label dp_id;
    public Label dp_tipo_lbl;
    public TextField dp_tipo_txt;
    public MenuButton dp_btn;
    public FontAwesomeIconView dp_icon_btn;
    public MenuItem dp_registrar_btn;
    public MenuItem dp_atualizar_btn;
    public MenuItem dp_excluir_btn;
    public MenuItem dp_limpar_btn;
    public TableView<dpRepository> dp_tableView;
    public TableColumn<dpRepository, String> dp_col_tipo;

    public Label genero_id;
    public Label genero_genero_lbl;
    public TextField genero_genero_txt;
    public MenuButton genero_btn;
    public FontAwesomeIconView genero_icon_btn;
    public MenuItem genero_registrar_btn;
    public MenuItem genero_atualizar_btn;
    public MenuItem genero_excluir_btn;
    public MenuItem genero_limpar_btn;
    public TableView<generoRepository> genero_tableView;
    public TableColumn<generoRepository, String> genero_col_genero;

    public Label civil_id;
    public Label civil_lbl;
    public TextField civil_txt;
    public MenuButton civil_btn;
    public FontAwesomeIconView civil_icon_btn;
    public MenuItem civil_registrar_btn;
    public MenuItem civil_atualizar_btn;
    public MenuItem civil_excluir_btn;
    public MenuItem civil_limpar_btn;
    public TableView<civilRepository> civil_tableView;
    public TableColumn<civilRepository, String> civil_col_civil;

    public Label relacao_id;
    public Label relacao_lbl;
    public TextField relacao_txt;
    public MenuButton relacao_btn;
    public FontAwesomeIconView relacao_icon_btn;
    public MenuItem relacao_registrar_btn;
    public MenuItem relacao_atualizar_btn;
    public MenuItem relacao_excluir_btn;
    public MenuItem relacao_limpar_btn;
    public TableView<relacaoRepository> relacao_tableView;
    public TableColumn<relacaoRepository, String> relacao_col;

    public Label metodo_id;
    public Label lblMetodoPgmt;
    public TextField txtMetodoPgmt;
    public FontAwesomeIconView metodo_icon_btn;
    public MenuButton metodo_btn;
    public MenuItem metodo_registrar_btn;
    public MenuItem metodo_atualizar_btn;
    public MenuItem metodo_excluir_btn;
    public MenuItem metodo_limpar_btn;
    public TableView<metodoRepository> metodo_tableView;
    public TableColumn<metodoRepository, String> metodo_col;

    public int getId_ndm() { return this.id_ndm; }
        public int getId_hm() { return this.id_hm; }
            public int getId_dp() { return this.id_dp; }
                public int getId_genero() { return this.id_genero; }
                    public int getId_civil() { return this.id_civil; }
                        public int getId_relacao() { return this.id_relacao; }
                            public int getId_metodo() { return this.id_relacao; }
                            public void setId_metodo(int id) { this.id_relacao = id; }
                        public void setId_relacao(int id) { this.id_relacao = id; }
                    public void setId_civil(int id) { this.id_civil = id;}
                public void setId_genero(int id) { this.id_genero = id; }
            public void setId_dp(int id) { this.id_dp = id; }
        public void setId_hm(int id) { this.id_hm = id; }
    public void setId_ndm(int id) { this.id_ndm = id; }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        if(LanguagesRepository.getEng())
        {
            lblTitle.setText("SETTINGS");

            inscricaoTitle.setText("ENROLLMENTS");
            inscricao_atualizar_btn.setText("Update");
            inscricao_limpar_btn.setText("Clear");
            inscricao_lbl.setText("Enrollment");
            inscricao_valorAtual_lbl.setText("CURRENT VALUE");

            ndm_registrar_btn.setText("Register");
            ndm_atualizar_btn.setText("Update");
            ndm_excluir_btn.setText("Delete");
            ndm_limpar_btn.setText("Clear");
            ndm_nivel_lbl.setText("Level");
            ndm_nivel_txt.setPromptText("Level");
            ndm_duracao_lbl.setText("Duration");
            ndm_duracao_txt.setText("Duration");
            ndm_mensalidade_lbl.setText("Monthly Fee");
            ndm_col_nivel.setText("Level");
            ndm_col_duracao.setText("Duration");
            ndm_col_mensalidade.setText("Monthly Fee");

            hm_registrar_btn.setText("Register");
            hm_atualizar_btn.setText("Update");
            hm_excluir_btn.setText("Delete");
            hm_limpar_btn.setText("Clear");
            hm_horario_lbl.setText("Schedule");
            hm_horario_txt.setPromptText("Schedule");
            hm_modalidade_lbl.setText("Modality");
            hm_col_horario.setText("Chedule");
            hm_col_modalidade.setText("Modality");

            dp_registrar_btn.setText("Register");
            dp_atualizar_btn.setText("Update");
            dp_excluir_btn.setText("Delete");
            dp_limpar_btn.setText("Clear");
            dp_tipo_lbl.setText("Type");
            dp_tipo_txt.setPromptText("Document Type");
            dp_col_tipo.setText("Document Type");

            genero_registrar_btn.setText("Register");
            genero_atualizar_btn.setText("Update");
            genero_excluir_btn.setText("Delete");
            genero_limpar_btn.setText("Clear");
            genero_genero_lbl.setText("Gender");
            genero_genero_txt.setPromptText("Gender");
            genero_col_genero.setText("Gender List");

            civil_registrar_btn.setText("Register");
            civil_atualizar_btn.setText("Update");
            genero_excluir_btn.setText("Delete");
            genero_limpar_btn.setText("Clear");
        }
        btnClose.setOnAction(evt -> close());
        SpinnerValueFactory<Double> inscricaoValue = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 500, 0);
        SpinnerValueFactory<Double> mensalidadeValue = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 5000, 0);

        /// I N S C R I Ç Ã O
        inscricao_txt.setValueFactory(inscricaoValue);
        inscricao_txt.setEditable(true);
        iconAnimation();
        inscricao_valorAtual_txt();
        inscricao_atualizar_btn.setOnAction(evt -> inscricao_updateTaxaDeInscricao());
        inscricao_limpar_btn.setOnAction(evt -> inscricao_Clean());

        /// N D M
        ndm_mensalidade_txt.setValueFactory(mensalidadeValue);
        ndm_mensalidade_txt.setEditable(true);
        NDMTable();
        ndm_tableView.setOnMouseClicked(evt -> NDMSelect());
        ndm_registrar_btn.setOnAction(evt -> ndm_Add());
        ndm_atualizar_btn.setOnAction(evt -> ndm_Update());
        ndm_excluir_btn.setOnAction(evt -> ndm_Delete());
        ndm_limpar_btn.setOnAction(evt -> ndm_Clean());

        /// H M
        HMTable();
        hm_horario_txt.setOnKeyTyped(evt -> {
            if(!hm_horario_txt.getText().isEmpty())
            {
                hm_horario_txt.getStyleClass().removeAll("textEmptyRed");
                hm_horario_txt.getStyleClass().add("textGray");
            }
        });
        ObservableList<String> listModalidade = FXCollections.observableArrayList("Online", "Presencial");
        hm_modalidade_txt.setItems(listModalidade);
        hm_modalidade_txt.setOnAction(evt -> {
            hm_modalidade_txt.getItems();
            if(hm_modalidade_txt.getValue() != null)
            {
                hm_modalidade_txt.getStyleClass().removeAll("comboBoxEmptyRed");
                hm_modalidade_txt.getStyleClass().add("textGray");
            }
        });
        hm_modalidade_txt.setOnKeyTyped(evt -> {
            if(hm_modalidade_txt.getValue() != null)
            {
                hm_modalidade_txt.getStyleClass().removeAll("comboBoxEmptyRed");
                hm_modalidade_txt.getStyleClass().add("textGray");
            }
        });
        hm_tableView.setOnMouseClicked(evt -> HMSelect());
        hm_registrar_btn.setOnAction(evt -> hm_Add());
        hm_atualizar_btn.setOnAction(evt -> hm_Update());
        hm_excluir_btn.setOnAction(evt -> hm_Delete());
        hm_limpar_btn.setOnAction(evt -> hm_Clean());

        /// DP
        DPTable();
        dp_tipo_txt.setOnKeyTyped(evt -> {
            if(!dp_tipo_txt.getText().isEmpty())
            {
                dp_tipo_txt.getStyleClass().removeAll("textEmptyRed");
                dp_tipo_txt.getStyleClass().add("textGray");
            }
        });
        dp_tableView.setOnMouseClicked(evt -> DPSelect());
        dp_registrar_btn.setOnAction(evt -> dp_Add());
        dp_atualizar_btn.setOnAction(evt -> dp_Update());
        dp_excluir_btn.setOnAction(evt -> dp_Delete());
        dp_limpar_btn.setOnAction(evt -> dp_Clean());

        /// Gênero
        genero_Table();
        genero_genero_txt.setOnKeyTyped(evt -> {
            if(!genero_genero_txt.getText().isEmpty())
            {
                genero_genero_txt.getStyleClass().removeAll("textEmptyRed");
                genero_genero_txt.getStyleClass().add("textGray");
            }
        });
        genero_tableView.setOnMouseClicked(evt -> genero_Select());
        genero_registrar_btn.setOnAction(evt -> genero_Add());
        genero_atualizar_btn.setOnAction(evt -> genero_Update());
        genero_excluir_btn.setOnAction(evt -> genero_Delete());
        genero_limpar_btn.setOnAction(evt -> genero_Clean());

        /// Estado Civil
        civil_Table();
        civil_txt.setOnKeyTyped(evt -> {
            if(!civil_txt.getText().isEmpty())
            {
                civil_txt.getStyleClass().removeAll("textEmptyRed");
                civil_txt.getStyleClass().add("textGray");
            }
        });
        civil_tableView.setOnMouseClicked(evt -> civil_Select());
        civil_registrar_btn.setOnAction(evt -> civil_Add());
        civil_atualizar_btn.setOnAction(evt -> civil_Update());
        civil_excluir_btn.setOnAction(evt -> civil_Delete());
        civil_limpar_btn.setOnAction(evt -> civil_Clean());

        /// Relacao Emerg
        relacao_Table();
        relacao_txt.setOnKeyTyped(evt -> {
            if(!relacao_txt.getText().isEmpty())
            {
                relacao_txt.getStyleClass().removeAll("textEmptyRed");
                relacao_txt.getStyleClass().add("textGray");
            }
        });
        relacao_tableView.setOnMouseClicked(evt -> relacao_Select());
        relacao_registrar_btn.setOnAction(evt -> relacao_Add());
        relacao_atualizar_btn.setOnAction(evt -> relacao_Update());
        relacao_excluir_btn.setOnAction(evt -> relacao_Delete());
        relacao_limpar_btn.setOnAction(evt -> relacao_Clean());

        /// Payment Method
        metodo_Table();
        txtMetodoPgmt.setOnKeyTyped(evt -> {
            if(!txtMetodoPgmt.getText().isEmpty())
            {
                txtMetodoPgmt.getStyleClass().removeAll("textEmptyRed");
                txtMetodoPgmt.getStyleClass().add("textGray");
            }
        });
        metodo_tableView.setOnMouseClicked(evt -> metodo_Select());
        metodo_registrar_btn.setOnAction(evt -> metodo_Add());
        metodo_atualizar_btn.setOnAction(evt -> metodo_Update());
        metodo_excluir_btn.setOnAction(evt -> metodo_Delete());
        metodo_limpar_btn.setOnAction(evt -> metodo_Clean());
    }

    private void close()
    {
        Stage _stage = (Stage) btnClose.getScene().getWindow();
        _stage.close();
    }

    private void iconAnimation()
    {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(inscricao_btn.isShowing()) { inscricao_icon_btn.setIcon(FontAwesomeIcon.ANGLE_UP); }
                else { inscricao_icon_btn.setIcon(FontAwesomeIcon.ANGLE_DOWN); }
                if(ndm_btn.isShowing()) { ndm_icon_btn.setIcon(FontAwesomeIcon.ANGLE_UP); }
                else { ndm_icon_btn.setIcon(FontAwesomeIcon.ANGLE_DOWN); }
                if(hm_btn.isShowing()) { hm_icon_btn.setIcon(FontAwesomeIcon.ANGLE_UP); }
                else { hm_icon_btn.setIcon(FontAwesomeIcon.ANGLE_DOWN); }
                if(dp_btn.isShowing()) { dp_icon_btn.setIcon(FontAwesomeIcon.ANGLE_UP); }
                else { dp_icon_btn.setIcon(FontAwesomeIcon.ANGLE_DOWN); }
                if(genero_btn.isShowing()) { genero_icon_btn.setIcon(FontAwesomeIcon.ANGLE_UP); }
                else { genero_icon_btn.setIcon(FontAwesomeIcon.ANGLE_DOWN); }
                if(civil_btn.isShowing()) { civil_icon_btn.setIcon(FontAwesomeIcon.ANGLE_UP); }
                else { civil_icon_btn.setIcon(FontAwesomeIcon.ANGLE_DOWN); }
                if(relacao_btn.isShowing()) { relacao_icon_btn.setIcon(FontAwesomeIcon.ANGLE_UP); }
                else { relacao_icon_btn.setIcon(FontAwesomeIcon.ANGLE_DOWN); }
                if(metodo_btn.isShowing()) { metodo_icon_btn.setIcon(FontAwesomeIcon.ANGLE_UP); }
                else { metodo_icon_btn.setIcon(FontAwesomeIcon.ANGLE_DOWN); }
            }
        };
        timer.start();
    }

    /// M e t h o d
    public ObservableList<metodoRepository> addmetodoListData()
    {
        ObservableList<metodoRepository> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM method ORDER BY metodo asc";
        try (Connection _connect = ConnectionLITERegistrations.connectLite(); PreparedStatement _prepare = _connect.prepareStatement(sql))
        {
            ResultSet _rs = _prepare.executeQuery();
            metodoRepository metodo;

            while(_rs.next())
            {
                metodo = new metodoRepository(
                        _rs.getInt("id"),
                        _rs.getString("metodo"));
                listData.add(metodo);
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
        return listData;
    }

    public void metodo_Table()
    {
        ObservableList<metodoRepository> addmetodoInTable = addmetodoListData();

        metodo_col.setCellValueFactory(new PropertyValueFactory<>("metodo"));

        metodo_tableView.setItems(addmetodoInTable);

        metodo_tableView.getColumns().forEach(column -> column.setResizable(false));
        metodo_tableView.getColumns().forEach(column -> column.setReorderable(false));
    }

    public void metodo_Select()
    {
        metodoRepository metodoData = metodo_tableView.getSelectionModel().getSelectedItem();
        int num = metodo_tableView.getSelectionModel().getSelectedIndex();
        if((num - 1) < -1) { return; }
        metodo_id.setText(String.valueOf(metodoData.getId()));
        txtMetodoPgmt.setText(metodoData.getMetodo());
    }

    public void metodo_Add()
    {
        metodo_ValidarCampos();
        if(!txtMetodoPgmt.getText().isEmpty())
        {
            String checkExist = "SELECT * FROM method WHERE metodo = '"+txtMetodoPgmt.getText()+"'";
            try(Connection _connect = ConnectionLITERegistrations.connectLite(); Statement _statement = _connect.createStatement(); ResultSet _rs = _statement.executeQuery(checkExist))
            {
                exist = _rs.next();
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

            if(exist)
            {
                Model.getInstance().getAlertsFactory().getOnDataExistView();
            }
            else
            {
                RegistrationsRepository.set_metodo_Add(txtMetodoPgmt.getText());
                metodo_Table();
                metodo_Reset();
                Model.getInstance().getAlertsFactory().getOnRegister();
            }
        }
    }

    public void metodo_Update()
    {
        int id = 0;
        String metodo = null;
        String checkExist = "SELECT * FROM method WHERE id = '"+metodo_id.getText()+"'";
        try(Connection _connect = ConnectionLITERegistrations.connectLite(); Statement _statement = _connect.createStatement(); ResultSet _rs = _statement.executeQuery(checkExist))
        {
            exist = _rs.next();

            id = _rs.getInt("id");
            metodo = _rs.getString("metodo");
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

        if(exist)
        {
            if(txtMetodoPgmt.getText().equals(metodo)) { Model.getInstance().getAlertsFactory().getOnNoChanges(); }
            else
            {
                RegistrationsRepository.set_metodo_Update(id, txtMetodoPgmt.getText());
                metodo_Table();
                metodo_Reset();
                Model.getInstance().getAlertsFactory().getOnUpdate();
            }
        }
        else
        {
            Model.getInstance().getAlertsFactory().getOnDataNotExistView();
        }
    }

    private void metodo_Delete()
    {
        String metodo = null;
        String checkExist = "SELECT * FROM method WHERE id = '"+metodo_id.getText()+"'";
        try(Connection _connect = ConnectionLITERegistrations.connectLite(); Statement _statement = _connect.createStatement(); ResultSet _rs = _statement.executeQuery(checkExist))
        {
            exist = _rs.next();

            setId_metodo(_rs.getInt("id"));
            metodo = _rs.getString("metodo");
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

        if(exist)
        {
            if(txtMetodoPgmt.getText().equals(metodo)) { Model.getInstance().getAlertsFactory().getOnQ_RemoveMetodo(); }
            else { Model.getInstance().getAlertsFactory().getOnQQ_RemoveMetodo(); }
        }
        else
        {
            Model.getInstance().getAlertsFactory().getOnDataNotExistView();
        }
    }

    public void metodo_Excluir()
    {
        RegistrationsRepository.set_metodo_Delete(getId_relacao());
        metodo_Table();
        metodo_Reset();
        try {
            Model.getInstance().getAlertsFactory().getOnRemove();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void metodo_ValidarCampos()
    {
        if(txtMetodoPgmt.getText().isEmpty())
        {
            txtMetodoPgmt.getStyleClass().removeAll("textGray");
            txtMetodoPgmt.getStyleClass().add("textEmptyRed");
            Model.getInstance().getAlertsFactory().getOnEmptyView();
        }

        if(!txtMetodoPgmt.getText().isEmpty())
        {
            txtMetodoPgmt.getStyleClass().removeAll("textEmptyRed");
            txtMetodoPgmt.getStyleClass().add("textGray");
        }
    }

    private void metodo_Reset()
    {
        metodo_id.setText(null);
        txtMetodoPgmt.getStyleClass().removeAll("textEmptyRed");
        txtMetodoPgmt.clear();
        txtMetodoPgmt.getStyleClass().add("textGray");
    }

    private void metodo_Clean()
    {
        if(txtMetodoPgmt.getText().isEmpty())
        {
            txtMetodoPgmt.getStyleClass().removeAll("textEmptyRed");
            txtMetodoPgmt.getStyleClass().add("textGray");
            Model.getInstance().getAlertsFactory().getOnEmptyResetFieldsView();
        }
        else
        {
            metodo_Reset();
            Model.getInstance().getAlertsFactory().getOnResetFields();
        }
    }


    /// R e l a ç ã o
    public ObservableList<relacaoRepository> addrelacaoListData()
    {
        ObservableList<relacaoRepository> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM relacaoEmerg ORDER BY relacao ASC";
        try (Connection _connect = ConnectionLITERegistrations.connectLite(); PreparedStatement _prepare = _connect.prepareStatement(sql))
        {
            ResultSet _rs = _prepare.executeQuery();
            relacaoRepository relacao;

            while(_rs.next())
            {
                relacao = new relacaoRepository(
                        _rs.getInt("id"),
                        _rs.getString("relacao"));
                listData.add(relacao);
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
        return listData;
    }

    public void relacao_Table()
    {
        ObservableList<relacaoRepository> addrelacaoInTable = addrelacaoListData();

        relacao_col.setCellValueFactory(new PropertyValueFactory<>("relacao"));

        relacao_tableView.setItems(addrelacaoInTable);

        relacao_tableView.getColumns().forEach(column -> column.setResizable(false));
        relacao_tableView.getColumns().forEach(column -> column.setReorderable(false));
    }

    public void relacao_Select()
    {
        relacaoRepository relacaoData = relacao_tableView.getSelectionModel().getSelectedItem();
        int num = relacao_tableView.getSelectionModel().getSelectedIndex();
        if((num - 1) < -1) { return; }
        relacao_id.setText(String.valueOf(relacaoData.getId()));
        relacao_txt.setText(relacaoData.getRelacao());
    }

    public void relacao_Add()
    {
        relacao_ValidarCampos();
        if(!relacao_txt.getText().isEmpty())
        {
            String checkExist = "SELECT * FROM relacaoEmerg WHERE relacao = '"+relacao_txt.getText()+"'";
            try(Connection _connect = ConnectionLITERegistrations.connectLite(); Statement _statement = _connect.createStatement(); ResultSet _rs = _statement.executeQuery(checkExist))
            {
                exist = _rs.next();
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

            if(exist)
            {
                Model.getInstance().getAlertsFactory().getOnDataExistView();
            }
            else
            {
                RegistrationsRepository.set_relacao_Add(relacao_txt.getText());
                relacao_Table();
                relacao_Reset();
                Model.getInstance().getAlertsFactory().getOnRegister();
            }
        }
    }

    public void relacao_Update()
    {
        int id = 0;
        String relacao = null;
        String checkExist = "SELECT * FROM relacaoEmerg WHERE id = '"+relacao_id.getText()+"'";
        try(Connection _connect = ConnectionLITERegistrations.connectLite(); Statement _statement = _connect.createStatement(); ResultSet _rs = _statement.executeQuery(checkExist))
        {
            exist = _rs.next();

            id = _rs.getInt("id");
            relacao = _rs.getString("relacao");
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

        if(exist)
        {
            if(relacao_txt.getText().equals(relacao)) { Model.getInstance().getAlertsFactory().getOnNoChanges(); }
            else
            {
                RegistrationsRepository.set_relacao_Update(id, relacao_txt.getText());
                relacao_Table();
                relacao_Reset();
                Model.getInstance().getAlertsFactory().getOnUpdate();
            }
        }
        else
        {
            Model.getInstance().getAlertsFactory().getOnDataNotExistView();
        }
    }

    private void relacao_Delete()
    {
        String relacao = null;
        String checkExist = "SELECT * FROM relacaoEmerg WHERE id = '"+relacao_id.getText()+"'";
        try(Connection _connect = ConnectionLITERegistrations.connectLite(); Statement _statement = _connect.createStatement(); ResultSet _rs = _statement.executeQuery(checkExist))
        {
            exist = _rs.next();

            setId_relacao(_rs.getInt("id"));
            relacao = _rs.getString("relacao");
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

        if(exist)
        {
            if(relacao_txt.getText().equals(relacao)) { Model.getInstance().getAlertsFactory().getOnQ_RemoveRelacao(); }
            else { Model.getInstance().getAlertsFactory().getOnQQ_RemoveRelacao(); }
        }
        else
        {
            Model.getInstance().getAlertsFactory().getOnDataNotExistView();
        }
    }

    public void relacao_Excluir()
    {
        RegistrationsRepository.set_relacao_Delete(getId_relacao());
        relacao_Table();
        relacao_Reset();
        try {
            Model.getInstance().getAlertsFactory().getOnRemove();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void relacao_ValidarCampos()
    {
        if(relacao_txt.getText().isEmpty())
        {
            relacao_txt.getStyleClass().removeAll("textGray");
            relacao_txt.getStyleClass().add("textEmptyRed");
            Model.getInstance().getAlertsFactory().getOnEmptyView();
        }

        if(!relacao_txt.getText().isEmpty())
        {
            relacao_txt.getStyleClass().removeAll("textEmptyRed");
            relacao_txt.getStyleClass().add("textGray");
        }
    }

    private void relacao_Reset()
    {
        relacao_id.setText(null);
        if(relacao_txt.getText().isEmpty() || !relacao_txt.getText().isEmpty())
        {
            relacao_txt.getStyleClass().removeAll("textEmptyRed");
            relacao_txt.clear();
            relacao_txt.getStyleClass().add("textGray");

        }
    }

    private void relacao_Clean()
    {
        if(relacao_txt.getText().isEmpty())
        {
            relacao_txt.getStyleClass().removeAll("textEmptyRed");
            relacao_txt.getStyleClass().add("textGray");
            Model.getInstance().getAlertsFactory().getOnEmptyResetFieldsView();
        }
        else
        {
            relacao_Reset();
            Model.getInstance().getAlertsFactory().getOnResetFields();
        }
    }



    /// C i v i l
    public ObservableList<civilRepository> addcivilListData()
    {
        ObservableList<civilRepository> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM ieCivil ORDER BY civil ASC";
        try (Connection _connect = ConnectionLITERegistrations.connectLite(); PreparedStatement _prepare = _connect.prepareStatement(sql))
        {
            ResultSet _rs = _prepare.executeQuery();
            civilRepository civil;

            while(_rs.next())
            {
                civil = new civilRepository(
                        _rs.getInt("id"),
                        _rs.getString("civil"));
                listData.add(civil);
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
        return listData;
    }

    public void civil_Table()
    {
        ObservableList<civilRepository> addcivilInTable = addcivilListData();

        civil_col_civil.setCellValueFactory(new PropertyValueFactory<>("civil"));

        civil_tableView.setItems(addcivilInTable);

        civil_tableView.getColumns().forEach(column -> column.setResizable(false));
        civil_tableView.getColumns().forEach(column -> column.setReorderable(false));
    }

    public void civil_Select()
    {
        civilRepository civilData = civil_tableView.getSelectionModel().getSelectedItem();
        int num = civil_tableView.getSelectionModel().getSelectedIndex();
        if((num - 1) < -1) { return; }
        civil_id.setText(String.valueOf(civilData.getId()));
        civil_txt.setText(civilData.getCivil());
    }

    public void civil_Add()
    {
        civil_ValidarCampos();
        if(!civil_txt.getText().isEmpty())
        {
            String checkExist = "SELECT * FROM ieCivil WHERE civil = '"+civil_txt.getText()+"'";
            try(Connection _connect = ConnectionLITERegistrations.connectLite(); Statement _statement = _connect.createStatement(); ResultSet _rs = _statement.executeQuery(checkExist))
            {
                exist = _rs.next();
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

            if(exist)
            {
                Model.getInstance().getAlertsFactory().getOnDataExistView();
            }
            else
            {
                RegistrationsRepository.set_civil_Add(civil_txt.getText());
                civil_Table();
                civil_Reset();
                Model.getInstance().getAlertsFactory().getOnRegister();
            }
        }
    }

    public void civil_Update()
    {
        int id = 0;
        String civil = null;
        String checkExist = "SELECT * FROM ieCivil WHERE id = '"+civil_id.getText()+"'";
        try(Connection _connect = ConnectionLITERegistrations.connectLite(); Statement _statement = _connect.createStatement(); ResultSet _rs = _statement.executeQuery(checkExist))
        {
            exist = _rs.next();

            id = _rs.getInt("id");
            civil = _rs.getString("civil");
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

        if(exist)
        {
            if(civil_txt.getText().equals(civil)) { Model.getInstance().getAlertsFactory().getOnNoChanges(); }
            else
            {
                RegistrationsRepository.set_civil_Update(id, civil_txt.getText());
                civil_Table();
                civil_Reset();
                Model.getInstance().getAlertsFactory().getOnUpdate();
            }
        }
        else
        {
            Model.getInstance().getAlertsFactory().getOnDataNotExistView();
        }
    }

    private void civil_Delete()
    {
        String civil = null;
        String checkExist = "SELECT * FROM ieCivil WHERE id = '"+civil_id.getText()+"'";
        try(Connection _connect = ConnectionLITERegistrations.connectLite(); Statement _statement = _connect.createStatement(); ResultSet _rs = _statement.executeQuery(checkExist))
        {
            exist = _rs.next();

            setId_civil(_rs.getInt("id"));
            civil = _rs.getString("civil");
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

        if(exist)
        {
            if(civil_txt.getText().equals(civil)) { Model.getInstance().getAlertsFactory().getOnQ_RemoveCivil(); }
            else { Model.getInstance().getAlertsFactory().getOnQQ_RemoveCivil(); }
        }
        else
        {
            Model.getInstance().getAlertsFactory().getOnDataNotExistView();
        }
    }

    public void civil_Excluir()
    {
        RegistrationsRepository.set_civil_Delete(getId_civil());
        civil_Table();
        civil_Reset();
        try {
            Model.getInstance().getAlertsFactory().getOnRemove();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void civil_ValidarCampos()
    {
        if(civil_txt.getText().isEmpty())
        {
            civil_txt.getStyleClass().removeAll("textGray");
            civil_txt.getStyleClass().add("textEmptyRed");
            Model.getInstance().getAlertsFactory().getOnEmptyView();
        }

        if(!civil_txt.getText().isEmpty())
        {
            civil_txt.getStyleClass().removeAll("textEmptyRed");
            civil_txt.getStyleClass().add("textGray");
        }
    }

    private void civil_Reset()
    {
        civil_id.setText(null);
        if(civil_txt.getText().isEmpty() || !civil_txt.getText().isEmpty())
        {
            civil_txt.getStyleClass().removeAll("textEmptyRed");
            civil_txt.clear();
            civil_txt.getStyleClass().add("textGray");

        }
    }

    private void civil_Clean()
    {
        if(civil_txt.getText().isEmpty())
        {
            civil_txt.getStyleClass().removeAll("textEmptyRed");
            civil_txt.getStyleClass().add("textGray");
            Model.getInstance().getAlertsFactory().getOnEmptyResetFieldsView();
        }
        else
        {
            civil_Reset();
            Model.getInstance().getAlertsFactory().getOnResetFields();
        }
    }



    /// G ê n e r o
    public ObservableList<generoRepository> addgeneroListData()
    {
        ObservableList<generoRepository> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM ieSexo ORDER BY genero ASC";
        try (Connection _connect = ConnectionLITERegistrations.connectLite(); PreparedStatement _prepare = _connect.prepareStatement(sql))
        {
            ResultSet _rs = _prepare.executeQuery();
            generoRepository genero;

            while(_rs.next())
            {
                genero = new generoRepository(
                        _rs.getInt("id"),
                        _rs.getString("genero"));
                listData.add(genero);
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
        return listData;
    }

    public void genero_Table()
    {
        ObservableList<generoRepository> addgeneroInTable = addgeneroListData();

        genero_col_genero.setCellValueFactory(new PropertyValueFactory<>("genero"));

        genero_tableView.setItems(addgeneroInTable);

        genero_tableView.getColumns().forEach(column -> column.setResizable(false));
        genero_tableView.getColumns().forEach(column -> column.setReorderable(false));
    }

    public void genero_Select()
    {
        generoRepository dpData = genero_tableView.getSelectionModel().getSelectedItem();
        int num = genero_tableView.getSelectionModel().getSelectedIndex();
        if((num - 1) < -1) { return; }
        genero_id.setText(String.valueOf(dpData.getId()));
        genero_genero_txt.setText(dpData.getGenero());
    }

    public void genero_Add()
    {
        genero_ValidarCampos();
        if(!genero_genero_txt.getText().isEmpty())
        {
            String checkExist = "SELECT * FROM ieSexo WHERE genero = '"+genero_genero_txt.getText()+"'";
            try(Connection _connect = ConnectionLITERegistrations.connectLite(); Statement _statement = _connect.createStatement(); ResultSet _rs = _statement.executeQuery(checkExist))
            {
                exist = _rs.next();
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

            if(exist)
            {
                Model.getInstance().getAlertsFactory().getOnDataExistView();
            }
            else
            {
                RegistrationsRepository.set_genero_Add(genero_genero_txt.getText());
                genero_Table();
                genero_Reset();
                Model.getInstance().getAlertsFactory().getOnRegister();
            }
        }
    }

    public void genero_Update()
    {
        int id = 0;
        String genero = null;
        String checkExist = "SELECT * FROM ieSexo WHERE id = '"+genero_id.getText()+"'";
        try(Connection _connect = ConnectionLITERegistrations.connectLite(); Statement _statement = _connect.createStatement(); ResultSet _rs = _statement.executeQuery(checkExist))
        {
            exist = _rs.next();

            id = _rs.getInt("id");
            genero = _rs.getString("genero");
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

        if(exist)
        {
            if(genero_genero_txt.getText().equals(genero)) { Model.getInstance().getAlertsFactory().getOnNoChanges(); }
            else
            {
                RegistrationsRepository.set_genero_Update(id, genero_genero_txt.getText());
                genero_Table();
                genero_Reset();
                Model.getInstance().getAlertsFactory().getOnUpdate();
            }
        }
        else
        {
            Model.getInstance().getAlertsFactory().getOnDataNotExistView();
        }
    }

    private void genero_Delete()
    {
        String genero = null;
        String checkExist = "SELECT * FROM ieSexo WHERE id = '"+genero_id.getText()+"'";
        try(Connection _connect = ConnectionLITERegistrations.connectLite(); Statement _statement = _connect.createStatement(); ResultSet _rs = _statement.executeQuery(checkExist))
        {
            exist = _rs.next();

            setId_genero(_rs.getInt("id"));
            genero = _rs.getString("genero");
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

        if(exist)
        {
            if(genero_genero_txt.getText().equals(genero)) { Model.getInstance().getAlertsFactory().getOnQ_RemoveGenero(); }
            else { Model.getInstance().getAlertsFactory().getOnQQ_RemoveGenero(); }
        }
        else
        {
            Model.getInstance().getAlertsFactory().getOnDataNotExistView();
        }
    }

    public void genero_Excluir()
    {
        RegistrationsRepository.set_genero_Delete(getId_genero());
        genero_Table();
        genero_Reset();
        try {
            Model.getInstance().getAlertsFactory().getOnRemove();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void genero_ValidarCampos()
    {
        if(genero_genero_txt.getText().isEmpty())
        {
            genero_genero_txt.getStyleClass().removeAll("textGray");
            genero_genero_txt.getStyleClass().add("textEmptyRed");
            Model.getInstance().getAlertsFactory().getOnEmptyView();
        }

        if(!genero_genero_txt.getText().isEmpty())
        {
            genero_genero_txt.getStyleClass().removeAll("textEmptyRed");
            genero_genero_txt.getStyleClass().add("textGray");
        }
    }

    private void genero_Reset()
    {
        genero_id.setText(null);
        if(genero_genero_txt.getText().isEmpty() || !genero_genero_txt.getText().isEmpty())
        {
            genero_genero_txt.getStyleClass().removeAll("textEmptyRed");
            genero_genero_txt.clear();
            genero_genero_txt.getStyleClass().add("textGray");

        }
    }

    private void genero_Clean()
    {
        if(genero_genero_txt.getText().isEmpty())
        {
            genero_genero_txt.getStyleClass().removeAll("textEmptyRed");
            genero_genero_txt.getStyleClass().add("textGray");
            Model.getInstance().getAlertsFactory().getOnEmptyResetFieldsView();
        }
        else
        {
            genero_Reset();
            Model.getInstance().getAlertsFactory().getOnResetFields();
        }
    }



    /// H M
    public ObservableList<hmRepository> addHMListData()
    {
        ObservableList<hmRepository> listData = FXCollections.observableArrayList();
        //mysql sql = "SELECT * FROM hm ORDER BY STR_TO_DATE(CONCAT(SUBSTRING_INDEX(Horario, 'h', 1), ':00'), '%H:%i') ASC"
        String sql = "SELECT * FROM hm ORDER BY CAST(SUBSTR(Horario, 1, INSTR(Horario, 'h') - 1) AS INTEGER) ASC";
        try (Connection _connect = ConnectionLITERegistrations.connectLite(); PreparedStatement _prepare = _connect.prepareStatement(sql))
        {
            ResultSet _rs = _prepare.executeQuery();
            hmRepository hm;

            while(_rs.next())
            {
                hm = new hmRepository(
                        _rs.getInt("id"),
                        _rs.getString("Horario"),
                        _rs.getString("Modalidade"));
                listData.add(hm);
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
        return listData;
    }

    public void HMTable()
    {
        ObservableList<hmRepository> addHMInTable = addHMListData();

        hm_col_horario.setCellValueFactory(new PropertyValueFactory<>("horario"));
        hm_col_modalidade.setCellValueFactory(new PropertyValueFactory<>("modalidade"));

        hm_tableView.setItems(addHMInTable);

        hm_tableView.getColumns().forEach(column -> column.setResizable(false));
        hm_tableView.getColumns().forEach(column -> column.setReorderable(false));
    }

    public void HMSelect()
    {
        hmRepository hmData = hm_tableView.getSelectionModel().getSelectedItem();
        int num = hm_tableView.getSelectionModel().getSelectedIndex();
        if((num - 1) < -1) { return; }
        hm_id.setText(String.valueOf(hmData.getId()));
        hm_horario_txt.setText(hmData.getHorario());
        hm_modalidade_txt.setValue(hmData.getModalidade());
    }

    public void hm_Add()
    {
        hm_ValidarCampos();
        if(!hm_horario_txt.getText().isEmpty() && hm_modalidade_txt.getValue() != null)
        {
            String checkExist = "SELECT Horario FROM hm WHERE Horario = '"+hm_horario_txt.getText()+"'";
            try(Connection _connect = ConnectionLITERegistrations.connectLite(); Statement _statement = _connect.createStatement(); ResultSet _rs = _statement.executeQuery(checkExist))
            {
                exist = _rs.next();
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

            if(exist)
            {
                Model.getInstance().getAlertsFactory().getOnDataExistView();
            }
            else
            {
                RegistrationsRepository.set_hm_Add(hm_horario_txt.getText(), hm_modalidade_txt.getValue());
                HMTable();
                hm_Reset();
                Model.getInstance().getAlertsFactory().getOnRegister();
            }
        }
    }

    public void hm_Update()
    {
        int id = 0;
        String horario = null, modalidade = null;
        String checkExist = "SELECT * FROM hm WHERE id = '"+hm_id.getText()+"'";
        try(Connection _connect = ConnectionLITERegistrations.connectLite(); Statement _statement = _connect.createStatement(); ResultSet _rs = _statement.executeQuery(checkExist))
        {
            exist = _rs.next();

            id = _rs.getInt("id");
            horario = _rs.getString("Horario");
            modalidade = _rs.getString("Modalidade");
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

        if(exist)
        {
            if(hm_horario_txt.getText().equals(horario) && hm_modalidade_txt.getValue().equals(modalidade)) { Model.getInstance().getAlertsFactory().getOnNoChanges(); }
            else
            {
                RegistrationsRepository.set_hm_Update(id, hm_horario_txt.getText(), hm_modalidade_txt.getValue());
                HMTable();
                hm_Reset();
                Model.getInstance().getAlertsFactory().getOnUpdate();
            }
        }
        else
        {
            Model.getInstance().getAlertsFactory().getOnDataNotExistView();
        }
    }

    private void hm_Delete()
    {
        String horario = null, modalidade = null;
        String checkExist = "SELECT * FROM hm WHERE id = '"+hm_id.getText()+"'";
        try(Connection _connect = ConnectionLITERegistrations.connectLite(); Statement _statement = _connect.createStatement(); ResultSet _rs = _statement.executeQuery(checkExist))
        {
            exist = _rs.next();

            setId_hm(_rs.getInt("id"));
            horario = _rs.getString("horario");
            modalidade = _rs.getString("modalidade");
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

        if(exist)
        {
            if(hm_horario_txt.getText().equals(horario) && hm_modalidade_txt.getValue().equals(modalidade)) { Model.getInstance().getAlertsFactory().getOnQ_RemoveHM(); }
            else { Model.getInstance().getAlertsFactory().getOnQQ_RemoveHM(); }
        }
        else
        {
            Model.getInstance().getAlertsFactory().getOnDataNotExistView();
        }
    }

    public void hm_Excluir()
    {
        RegistrationsRepository.set_hm_Delete(getId_hm());
        HMTable();
        hm_Reset();
        try {
            Model.getInstance().getAlertsFactory().getOnRemove();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void hm_ValidarCampos()
    {
        if(hm_horario_txt.getText().isEmpty())
        {
            hm_horario_txt.getStyleClass().removeAll("textGray");
            hm_horario_txt.getStyleClass().add("textEmptyRed");
        }
        if(hm_modalidade_txt.getValue() == null)
        {
            hm_modalidade_txt.getStyleClass().removeAll("textGray");
            hm_modalidade_txt.getStyleClass().add("comboBoxEmptyRed");
        }
        if(hm_horario_txt.getText().isEmpty() || hm_modalidade_txt.getValue() == null) { Model.getInstance().getAlertsFactory().getOnEmptyView(); }

        if(!hm_horario_txt.getText().isEmpty())
        {
            hm_horario_txt.getStyleClass().removeAll("textEmptyRed");
            hm_horario_txt.getStyleClass().add("textGray");
        }
        if(hm_modalidade_txt.getValue() != null)
        {
            hm_modalidade_txt.getStyleClass().removeAll("comboBoxEmptyRed");
            hm_modalidade_txt.getStyleClass().add("textGray");
        }
    }

    private void hm_Reset()
    {
        hm_id.setText(null);
        if(hm_horario_txt.getText().isEmpty() || !hm_horario_txt.getText().isEmpty())
        {
            hm_horario_txt.getStyleClass().removeAll("textEmptyRed");
            hm_horario_txt.clear();
            hm_horario_txt.getStyleClass().add("textGray");

        }
        if(hm_modalidade_txt.getValue() == null || hm_modalidade_txt.getValue() != null)
        {
            hm_modalidade_txt.getStyleClass().removeAll("comboBoxEmptyRed");
            hm_modalidade_txt.setValue(null);
            hm_modalidade_txt.getStyleClass().add("textGray");
        }
    }

    private void hm_Clean()
    {
        if(hm_horario_txt.getText().isEmpty() && hm_modalidade_txt.getValue() == null)
        {
            hm_horario_txt.getStyleClass().removeAll("textEmptyRed");
            hm_modalidade_txt.getStyleClass().removeAll("comboBoxEmptyRed");

            hm_horario_txt.getStyleClass().add("textGray");
            hm_modalidade_txt.getStyleClass().add("textGray");
            Model.getInstance().getAlertsFactory().getOnEmptyResetFieldsView();
        }
        else
        {
            hm_Reset();
            Model.getInstance().getAlertsFactory().getOnResetFields();
        }
    }



    /// D P
    public ObservableList<dpRepository> addDPListData()
    {
        ObservableList<dpRepository> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM dp ORDER BY tipo ASC";
        try (Connection _connect = ConnectionLITERegistrations.connectLite(); PreparedStatement _prepare = _connect.prepareStatement(sql))
        {
            ResultSet _rs = _prepare.executeQuery();
            dpRepository dp;

            while(_rs.next())
            {
                dp = new dpRepository(
                        _rs.getInt("id"),
                        _rs.getString("tipo"));
                listData.add(dp);
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
        return listData;
    }

    public void DPTable()
    {
        ObservableList<dpRepository> addDPInTable = addDPListData();

        dp_col_tipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        dp_tableView.setItems(addDPInTable);

        dp_tableView.getColumns().forEach(column -> column.setResizable(false));
        dp_tableView.getColumns().forEach(column -> column.setReorderable(false));
    }

    public void DPSelect()
    {
        dpRepository dpData = dp_tableView.getSelectionModel().getSelectedItem();
        int num = dp_tableView.getSelectionModel().getSelectedIndex();
        if((num - 1) < -1) { return; }
        dp_id.setText(String.valueOf(dpData.getId()));
        dp_tipo_txt.setText(dpData.getTipo());
    }

    public void dp_Add()
    {
        dp_ValidarCampos();
        if(!dp_tipo_txt.getText().isEmpty())
        {
            String checkExist = "SELECT * FROM dp WHERE tipo = '"+dp_tipo_txt.getText()+"'";
            try(Connection _connect = ConnectionLITERegistrations.connectLite(); Statement _statement = _connect.createStatement(); ResultSet _rs = _statement.executeQuery(checkExist))
            {
                exist = _rs.next();
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

            if(exist)
            {
                Model.getInstance().getAlertsFactory().getOnDataExistView();
            }
            else
            {
                RegistrationsRepository.set_dp_Add(dp_tipo_txt.getText());
                DPTable();
                dp_Reset();
                Model.getInstance().getAlertsFactory().getOnRegister();
            }
        }
    }

    public void dp_Update()
    {
        int id = 0;
        String tipo = null;
        String checkExist = "SELECT * FROM dp WHERE id = '"+dp_id.getText()+"'";
        try(Connection _connect = ConnectionLITERegistrations.connectLite(); Statement _statement = _connect.createStatement(); ResultSet _rs = _statement.executeQuery(checkExist))
        {
            exist = _rs.next();

            id = _rs.getInt("id");
            tipo= _rs.getString("tipo");
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

        if(exist)
        {
            if(dp_tipo_txt.getText().equals(tipo)) { Model.getInstance().getAlertsFactory().getOnNoChanges(); }
            else
            {
                RegistrationsRepository.set_dp_Update(id, dp_tipo_txt.getText());
                DPTable();
                dp_Reset();
                Model.getInstance().getAlertsFactory().getOnUpdate();
            }
        }
        else
        {
            Model.getInstance().getAlertsFactory().getOnDataNotExistView();
        }
    }

    private void dp_Delete()
    {
        String tipo = null;
        String checkExist = "SELECT * FROM dp WHERE id = '"+dp_id.getText()+"'";
        try(Connection _connect = ConnectionLITERegistrations.connectLite(); Statement _statement = _connect.createStatement(); ResultSet _rs = _statement.executeQuery(checkExist))
        {
            exist = _rs.next();

            setId_dp(_rs.getInt("id"));
            tipo = _rs.getString("tipo");
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

        if(exist)
        {
            if(dp_tipo_txt.getText().equals(tipo)) { Model.getInstance().getAlertsFactory().getOnQ_RemoveDP(); }
            else { Model.getInstance().getAlertsFactory().getOnQQ_RemoveDP(); }
        }
        else
        {
            Model.getInstance().getAlertsFactory().getOnDataNotExistView();
        }
    }

    public void dp_Excluir()
    {
        RegistrationsRepository.set_dp_Delete(getId_dp());
        DPTable();
        dp_Reset();
        try {
            Model.getInstance().getAlertsFactory().getOnRemove();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void dp_ValidarCampos()
    {
        if(dp_tipo_txt.getText().isEmpty())
        {
            dp_tipo_txt.getStyleClass().removeAll("textGray");
            dp_tipo_txt.getStyleClass().add("textEmptyRed");
            Model.getInstance().getAlertsFactory().getOnEmptyView();
        }

        if(!dp_tipo_txt.getText().isEmpty())
        {
            dp_tipo_txt.getStyleClass().removeAll("textEmptyRed");
            dp_tipo_txt.getStyleClass().add("textGray");
        }
    }

    private void dp_Reset()
    {
        dp_id.setText(null);
        if(dp_tipo_txt.getText().isEmpty() || !dp_tipo_txt.getText().isEmpty())
        {
            dp_tipo_txt.getStyleClass().removeAll("textEmptyRed");
            dp_tipo_txt.clear();
            dp_tipo_txt.getStyleClass().add("textGray");

        }
    }

    private void dp_Clean()
    {
        if(dp_tipo_txt.getText().isEmpty())
        {
            dp_tipo_txt.getStyleClass().removeAll("textEmptyRed");
            dp_tipo_txt.getStyleClass().add("textGray");
            Model.getInstance().getAlertsFactory().getOnEmptyResetFieldsView();
        }
        else
        {
            dp_Reset();
            Model.getInstance().getAlertsFactory().getOnResetFields();
        }
    }



    /// N D M
    public ObservableList<ndmRepository> addNDMListData()
    {
        ObservableList<ndmRepository> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM ndm ORDER BY nivel";
        try (Connection _connect = ConnectionLITERegistrations.connectLite(); PreparedStatement _prepare = _connect.prepareStatement(sql))
        {
            ResultSet _rs = _prepare.executeQuery();
            ndmRepository ndm;

            while(_rs.next())
            {
                ndm = new ndmRepository(
                        _rs.getInt("id"),
                        _rs.getString("nivel"),
                        _rs.getString("duracao"),
                        _rs.getDouble("mensalidade"));
                listData.add(ndm);
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
        return listData;
    }

    public void NDMTable()
    {
        ObservableList<ndmRepository> addNDMInTable = addNDMListData();

        ndm_col_nivel.setCellValueFactory(new PropertyValueFactory<>("nivel"));
        ndm_col_duracao.setCellValueFactory(new PropertyValueFactory<>("duracao"));
        ndm_col_mensalidade.setCellValueFactory(new PropertyValueFactory<>("mensalidade"));

        ndm_tableView.setItems(addNDMInTable);

        ndm_tableView.getColumns().forEach(column -> column.setResizable(false));
        ndm_tableView.getColumns().forEach(column -> column.setReorderable(false));
    }

    public void NDMSelect()
    {
        ndmRepository ndmData = ndm_tableView.getSelectionModel().getSelectedItem();
        int num = ndm_tableView.getSelectionModel().getSelectedIndex();
        if((num - 1) < -1) { return; }
        ndm_id.setText(String.valueOf(ndmData.getId()));
        ndm_nivel_txt.setText(ndmData.getNivel());
        ndm_duracao_txt.setText(ndmData.getDuracao());
        ndm_mensalidade_txt.getValueFactory().setValue(ndmData.getMensalidade());
    }

    public void ndm_Add()
    {
        ndm_ValidarCampos();
        if(!ndm_nivel_txt.getText().isEmpty() && !ndm_duracao_txt.getText().isEmpty())
        {
            String checkExist = "SELECT nivel, mensalidade FROM ndm WHERE nivel = '"+ndm_nivel_txt.getText()+"' OR mensalidade = '"+ndm_mensalidade_txt.getValue()+"'";
            try(Connection _connect = ConnectionLITERegistrations.connectLite(); Statement _statement = _connect.createStatement(); ResultSet _rs = _statement.executeQuery(checkExist))
            {
                exist = _rs.next();
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

            if(exist)
            {
                Model.getInstance().getAlertsFactory().getOnDataExistView();
            }
            else
            {
                RegistrationsRepository.set_ndm_Add(ndm_nivel_txt.getText(), ndm_duracao_txt.getText(), ndm_mensalidade_txt.getValue());
                NDMTable();
                ndm_Reset();
                Model.getInstance().getAlertsFactory().getOnRegister();
            }
        }
    }

    public void ndm_Update()
    {
        int id = 0;
        String nivel = null, duracao = null;
        double mensalidade = 0.;
        String checkExist = "SELECT * FROM ndm WHERE id = '"+ndm_id.getText()+"'";
        try(Connection _connect = ConnectionLITERegistrations.connectLite(); Statement _statement = _connect.createStatement(); ResultSet _rs = _statement.executeQuery(checkExist))
        {
            exist = _rs.next();

            id = _rs.getInt("id");
            nivel = _rs.getString("nivel");
            duracao = _rs.getString("duracao");
            mensalidade = _rs.getDouble("mensalidade");
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

        if(exist)
        {
            if(ndm_nivel_txt.getText().equals(nivel) && ndm_duracao_txt.getText().equals(duracao) && ndm_mensalidade_txt.getValue() == mensalidade) { Model.getInstance().getAlertsFactory().getOnNoChanges(); }
            else
            {
                RegistrationsRepository.set_ndm_Update(id, ndm_nivel_txt.getText(), ndm_duracao_txt.getText(), ndm_mensalidade_txt.getValue());
                NDMTable();
                ndm_Reset();
                Model.getInstance().getAlertsFactory().getOnUpdate();
            }
        }
        else
        {
            Model.getInstance().getAlertsFactory().getOnDataNotExistView();
        }
    }

    private void ndm_Delete()
    {
        String nivel = null, duracao = null;
        double mensalidade = 0.;
        String checkExist = "SELECT * FROM ndm WHERE id = '"+ndm_id.getText()+"'";
        try(Connection _connect = ConnectionLITERegistrations.connectLite(); Statement _statement = _connect.createStatement(); ResultSet _rs = _statement.executeQuery(checkExist))
        {
            exist = _rs.next();

            setId_ndm(_rs.getInt("id"));
            nivel = _rs.getString("nivel");
            duracao = _rs.getString("duracao");
            mensalidade = _rs.getDouble("mensalidade");
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

        if(exist)
        {
            if(ndm_nivel_txt.getText().equals(nivel) && ndm_duracao_txt.getText().equals(duracao) && ndm_mensalidade_txt.getValue() == mensalidade) { Model.getInstance().getAlertsFactory().getOnQ_RemoveNDM(); }
            else { Model.getInstance().getAlertsFactory().getOnQQ_RemoveNDM(); }
        }
        else
        {
            Model.getInstance().getAlertsFactory().getOnDataNotExistView();
        }
    }

    public void ndm_Excluir()
    {
        RegistrationsRepository.set_ndm_Delete(getId_ndm());
        NDMTable();
        ndm_Reset();
        try {
            Model.getInstance().getAlertsFactory().getOnRemove();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void ndm_ValidarCampos()
    {
        if(ndm_nivel_txt.getText().isEmpty())
        {
            ndm_nivel_txt.getStyleClass().removeAll("textGray");
            ndm_nivel_txt.getStyleClass().add("textEmptyRed");
        }
        if(ndm_duracao_txt.getText().isEmpty())
        {
            ndm_duracao_txt.getStyleClass().removeAll("textGray");
            ndm_duracao_txt.getStyleClass().add("textEmptyRed");
        }
        if(ndm_nivel_txt.getText().isEmpty() || ndm_duracao_txt.getText().isEmpty() || ndm_mensalidade_txt.getValue() == null) { Model.getInstance().getAlertsFactory().getOnEmptyView(); }

        if(!ndm_nivel_txt.getText().isEmpty())
        {
            ndm_nivel_txt.getStyleClass().removeAll("textEmptyRed");
            ndm_nivel_txt.getStyleClass().add("textGray");
        }
        if(!ndm_duracao_txt.getText().isEmpty())
        {
            ndm_duracao_txt.getStyleClass().removeAll("textEmptyRed");
            ndm_duracao_txt.getStyleClass().add("textGray");
        }
    }

    private void ndm_Reset()
    {
        ndm_id.setText(null);
        if(ndm_nivel_txt.getText().isEmpty() || !ndm_nivel_txt.getText().isEmpty())
        {
            ndm_nivel_txt.getStyleClass().removeAll("textEmptyRed");
            ndm_nivel_txt.clear();
            ndm_nivel_txt.getStyleClass().add("textGray");

        }
        if(ndm_duracao_txt.getText().isEmpty() || !ndm_duracao_txt.getText().isEmpty())
        {
            ndm_duracao_txt.getStyleClass().removeAll("textEmptyRed");
            ndm_duracao_txt.clear();
            ndm_duracao_txt.getStyleClass().add("textGray");
        }
        if(ndm_mensalidade_txt.getValue() == 0. || ndm_mensalidade_txt.getValue() != 0.)
        {
            ndm_mensalidade_txt.getValueFactory().setValue(0.);
        }
    }

    private void ndm_Clean()
    {
        if(ndm_nivel_txt.getText().isEmpty() && ndm_duracao_txt.getText().isEmpty() && ndm_mensalidade_txt.getValue() == 0.)
        {
            ndm_nivel_txt.getStyleClass().removeAll("textEmptyRed");
            ndm_duracao_txt.getStyleClass().removeAll("textEmptyRed");

            ndm_nivel_txt.getStyleClass().add("textGray");
            ndm_duracao_txt.getStyleClass().add("textGray");
            Model.getInstance().getAlertsFactory().getOnEmptyResetFieldsView();
        }
        else
        {
            ndm_Reset();
            Model.getInstance().getAlertsFactory().getOnResetFields();
        }
    }


    /// I N S C R I Ç Ã O
    private void inscricao_valorAtual_txt() { inscricao_valorAtual_txt.setText(""+RegistrationsRepository.getTaxaDeInscricao()); }

    private void inscricao_updateTaxaDeInscricao()
    {
        String checkExist = "SELECT Valor FROM taxaDeInscricao WHERE Valor = '"+inscricao_txt.getValue()+"'";
        try(Connection _connect = ConnectionLITERegistrations.connectLite(); Statement _statement = _connect.createStatement(); ResultSet _rs = _statement.executeQuery(checkExist))
        {
            exist = _rs.next();
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
        if(exist)
        {
            Model.getInstance().getAlertsFactory().getOnDataExistView();
        }
        else
        {
            RegistrationsRepository.setUpdateTaxaDeInscricao(inscricao_txt.getValue());
            Model.getInstance().getAlertsFactory().getOnUpdate();
            inscricao_valorAtual_txt();
            inscricao_Reset();
        }
    }

    private void inscricao_Reset()
    {
        if(inscricao_txt.getValue() != 0.) { inscricao_txt.getValueFactory().setValue(0.); }
    }

    private void inscricao_Clean()
    {
        if(inscricao_txt.getValue() == 0.) { Model.getInstance().getAlertsFactory().getOnEmptyResetFieldsView(); }
        else
        {
            inscricao_Reset();
            Model.getInstance().getAlertsFactory().getOnResetFields();
        }
    }
}
