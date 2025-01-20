package ram.ewe.englishwithexperts.Controllers.Registrations;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import ram.ewe.englishwithexperts.Controllers.Alerts.Erro.AlertErroCriticoController;
import ram.ewe.englishwithexperts.Controllers.Receipt.PaymentsController;
import ram.ewe.englishwithexperts.Data.ConnectionDAO;
import ram.ewe.englishwithexperts.Data.ConnectionLITERegistrations;
import ram.ewe.englishwithexperts.Models.Model;
import ram.ewe.englishwithexperts.Models.ModelDataDAO;
import ram.ewe.englishwithexperts.Repositories.RegistrationsRepository;
import ram.ewe.englishwithexperts.Repositories.paymentRepository;
import ram.ewe.englishwithexperts.Repositories.valorPorExtenso;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Date;

/**
 * @author Ramadan Ismael
 */
public class RegistrationsController implements Initializable {
    private String _dia, _mes, _horas;
    private int _ano;
    private boolean exist;
    private int _valorTotal;
    public ScrollPane viewRegistration;
    public AnchorPane viewPayment;

    public Label lblFormularioDeInscricao;

    /// INFORMAÇÕES ACADÊMICAS
    public Label lblInformacoesAcademicas;
    public Label lblNumeroDeEstudante;
    public Label lblNivel;
    public Label lblModalidade;
    public Label lblHorario;
    public Label lblDataDeIngresso;
    public Label lblDuracao;
    public Label lblTaxaDeInscricao;
    public Label lblTaxaDeMensalidade;
    public Label txtNumeroDeEstudante;
    public ComboBox<String> txtNivel;
    public RadioButton online;
    public Label lblOnline;
    public RadioButton presencial;
    public Label lblPresencial;
    public ComboBox<String> txtHorario;
    public Label txtDataDeIngresso;
    public Label txtDuracao;
    public Label txtTaxaDeInscricao;
    public Label txtTaxaDeMensalidade;

    /// DOCUMENTOS PESSOAIS
    public Label lblDocumentosPessoais;
    public Label lblTipo;
    public Label lblNumeroDeIdentidade;
    public Label lblLocalDeEmissao;
    public Label lblDataDeValidade;
    public ComboBox<String> txtTipo;
    public TextField txtNumeroDeIdentidade;
    public TextField txtLocalDeEmissao;
    public DatePicker txtDataDeValidade;

    /// INFORMAÇÕES DO ESTUDANTE
    public Label lblInformacoesDoEstudante;
    public Label lblNomeCompleto;
    public Label lblDataDeNascimento;
    public Label lblGenero;
    public Label lblEstadoCivil;
    public Label lblNacionalidade;
    public Label lblNatural;
    public Label lblEnderecoResidencial;
    public Label lblNumeroDeTelefone;
    public Label lblEmail;
    public Label lblObservacoes;
    public TextField txtNomeCompleto;
    public DatePicker txtDataDeNascimento;
    public ComboBox<String> txtGenero;
    public ComboBox<String> txtEstadoCivil;
    public TextField txtNacionalidade;
    public TextField txtNatural;
    public TextField txtEnderecoResidencial;
    public TextField txtTelefone1;
    public TextField txtTelefone2;
    public TextField txtEmail;
    public TextArea txtObservacoes;

    /// CONTACTO DE EMERGÊNCIA/RESPONSÁVEL
    public Label lblContactoDeResponsavel;
    public Label lblRespNomeCompleto;
    public Label lblRespRelacao;
    public Label lblRespEnderecoResidencial;
    public Label lblRespNumeroDeTelefone;
    public TextField txtRespNomeCompleto;
    public ComboBox<String> txtRespRelacao;
    public TextField txtRespEnderecoResidencial;
    public TextField txtRespTelefone1;
    public TextField txtRespTelefone2;

    /// BTN
    public Button btnLimpar;
    public Button btnSubmeter;

    /// PAGAMENTO
    public Label lblPagamento;
    public Label lblReciboPgmt;
    public Label lblNumeroDeEstudantePgmt;
    public Label lblReferentePgmt;
    public Label lblExmoPgmt;
    public Label lblMetodoPgmt;
    public Label lblDescontoPgmt;
    public Label txtReciboPgmt;
    public Label txtNumeroDeEstudantePgmt;
    public Label txtReferentePgmt;
    public TextField txtExmoPgmt;
    public ComboBox<String> txtMetodoPgmt;
    public Spinner<Integer> txtDescontoPgmt;
    public Button btnCancelarPgmt;
    public Button btnPagarPgmt;

    public String getDia() { return this._dia; }
        public String getMes() { return this._mes; }
            public int getAno() { return this._ano; }
                public String getHoras() { return this._horas; }
                    public int getValorTotal() { return this._valorTotal; }
                    public void setValorTotal(int valorTotal) { this._valorTotal = valorTotal; }
                public void setHoras(String horas) { this._horas = horas; }
            public void setAno(int ano) { this._ano = ano; }
        public void setMes(String mes) { this._mes = mes; }
    public void setDia(String dia) { this._dia = dia; }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        int dia = LocalDate.now().getDayOfMonth();
        //String mes = LocalDate.now().getMonth().toString();
        LocalDate monthNow = LocalDate.now();
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMMM", new Locale("pt", "PT"));
        String mesPT = monthNow.format(monthFormatter);
        String mes = mesPT.substring(0, 1).toUpperCase()+mesPT.substring(1);
        int ano = LocalDate.now().getYear();
        //LocalTime horas = LocalTime.now();
        LocalTime timeNow = LocalTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String horas = timeNow.format(timeFormatter);

        setDia(""+dia);
        setMes(mes);
        setAno(ano);
        setHoras(horas);
        //System.out.println("Dia = "+dia+"\nMes = "+mes+"\nAno = "+ano+"\nHoras = "+horas);

        studentID();
        TextFormatter<String> formatter = new TextFormatter<>(change -> {
           if(change.getControlNewText().matches("\\d{0,2}\\d{0,3}\\d{0,4}"))
           {
               return change;
           }
           return null;
        });
        txtTelefone1.setTextFormatter(formatter);
        txtTaxaDeInscricao.setText(""+RegistrationsRepository.getTaxaDeInscricao());
        txtNivel();
        ndm();
        modalidade();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String today = sdf.format(new Date());
        txtDataDeIngresso.setText(today);
        txtHorario.setOnAction(evt -> txtHorario.getItems());
        dp_txtTipo();
        genero_txtTipo();
        estadoCivil_txtTipo();
        relacaoEmerg();
        typeAnyWord();
        btnLimpar.setOnAction(evt -> limpar());
        btnSubmeter.setOnAction(evt -> submeter());

        /// PAYMENT
        SpinnerValueFactory<Integer> DiscountValue = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5000, 0);
        txtDescontoPgmt.setValueFactory(DiscountValue);
        txtMetodo();
        txtExmoPgmt.setOnKeyTyped(evt -> {
            if(!txtExmoPgmt.getText().isEmpty())
            {
                lblExmoPgmt.getStyleClass().add("lblBlack");
                lblExmoPgmt.getStyleClass().removeAll("lblEmptyRed");
                txtExmoPgmt.getStyleClass().add("textGray");
                txtExmoPgmt.getStyleClass().removeAll("textEmptyRed");
            }
        });
        txtMetodoPgmt.setOnAction(evt -> {
            if(txtMetodoPgmt.getValue() != null)
            {
                lblMetodoPgmt.getStyleClass().add("lblBlack");
                lblMetodoPgmt.getStyleClass().removeAll("lblEmptyRed");
                txtMetodoPgmt.getStyleClass().add("textGray");
                txtMetodoPgmt.getStyleClass().removeAll("comboBoxEmptyRed");
            }
        });

        btnPagarPgmt.setOnAction(evt -> payment());
        btnCancelarPgmt.setOnAction(evt -> Model.getInstance().getAlertsFactory().getOnExitPaymentRegistrationView());
    }

    public void hidePayment() { viewPayment.setVisible(false); }

    private void submeter()
    {
        validarCampos();
        if(txtNivel.getValue() != null && txtHorario.getValue() != null && txtTipo.getValue() != null && !txtNumeroDeIdentidade.getText().isEmpty() && !txtLocalDeEmissao.getText().isEmpty() && txtDataDeValidade.getValue() != null && !txtNomeCompleto.getText().isEmpty() && txtDataDeNascimento.getValue() != null && txtGenero.getValue() != null && txtEstadoCivil.getValue() != null && !txtNacionalidade.getText().isEmpty() && !txtNatural.getText().isEmpty() && !txtEnderecoResidencial.getText().isEmpty() && !txtRespNomeCompleto.getText().isEmpty() && txtRespRelacao.getValue() != null && !txtRespEnderecoResidencial.getText().isEmpty() && !txtRespTelefone1.getText().isEmpty())
        {
            txtNumeroDeEstudantePgmt.setText(txtNumeroDeEstudante.getText());
            int numReceipt = select_receipt();
            txtReciboPgmt.setText(reciboNumber(numReceipt+1));
            viewPayment.setVisible(true);
            if(viewPayment.isVisible())
            {
                txtExmoPgmt.clear();
                txtMetodoPgmt.setValue(null);
                txtDescontoPgmt.getValueFactory().setValue(0);
            }
        }
    }

    private void payment()
    {
        validarPayment();
        if(!txtExmoPgmt.getText().isEmpty() && txtMetodoPgmt.getValue() != null)
        {
            String reciboNr = txtReciboPgmt.getText();
            String tipo = "Inscrição";
            String estudanteID = txtNumeroDeEstudantePgmt.getText();
            String exmo = txtExmoPgmt.getText();
            int valor = RegistrationsRepository.getTaxaDeInscricao();
            int desconto = txtDescontoPgmt.getValue();
            int valorPago = valor-desconto;
            setValorTotal(valorPago);
            String quantia = valorPorExtenso.setValor(valorPago);
            String metodo = txtMetodoPgmt.getValue();

            String checkExist = "SELECT reciboNr FROM tbReceiptDetail WHERE reciboNr = '"+txtReciboPgmt.getText()+"'";
            try(Connection _connect = ConnectionDAO.connectDAO(); Statement _statement = _connect.createStatement(); ResultSet _rs = _statement.executeQuery(checkExist))
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
                paymentRepository.set_receipt_Add(reciboNr, tipo, estudanteID, exmo, valor, desconto, valorPago, quantia, metodo, getDia(), getMes(), getAno(), getHoras());
                add_receipt(txtReciboPgmt.getText());
                add_studentID(txtNumeroDeEstudante.getText());
                Model.getInstance().getAlertsFactory().getOnRegisterPaymentSuccess();
                clearCampos();
            }
        }
    }

    private String reciboNumber(int cont) { return String.format("%07d", cont); }

    private int select_receipt()
    {
        String sql = "SELECT MAX(id) AS last_id FROM receipt";
        try(Connection _connect = ConnectionLITERegistrations.connectLite(); Statement _statement = _connect.createStatement(); ResultSet _rs = _statement.executeQuery(sql))
        {
            if (_rs.next())
            {
                int lastId = _rs.getInt("last_id");
                return lastId;
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
        return 0;
    }

    public static void add_receipt(String num)
    {
        String sql = "INSERT INTO receipt(detail) VALUES(?)";
        try(Connection connect = ConnectionLITERegistrations.connectLite(); PreparedStatement prepare = connect.prepareStatement(sql))
        {
            prepare.setString(1, num);
            prepare.executeUpdate();
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
    }

    /// S T U D E N T   ID
    private void studentID()
    {
        int PresentYear = LocalDate.now().getYear();
        int PresentMonth = LocalDate.now().getMonthValue();
        int id = select_studentID();
        String yearMonth = PresentYear+String.format("%02d", PresentMonth);;
        String ewe = "EWE-"+studentID_convert(yearMonth, id+1);
        //System.out.println("Student ID = "+ewe+"\nYear = "+PresentYear+"\nMonth = "+PresentMonth+"\nID = "+id);
        txtNumeroDeEstudante.setText(ewe);
    }

    private String studentID_convert(String yearMonth, int cont)
    {
        String num = String.format("%02d", cont);
        return yearMonth+num;
    }

    private int select_studentID()
    {
        String sql = "SELECT MAX(id) AS last_id FROM studentID";
        try(Connection _connect = ConnectionLITERegistrations.connectLite(); Statement _statement = _connect.createStatement(); ResultSet _rs = _statement.executeQuery(sql))
        {
            if (_rs.next())
            {
                int lastId = _rs.getInt("last_id");
                return lastId;
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
        return 0;
    }

    public static void add_studentID(String num)
    {
        String sql = "INSERT INTO studentID(detail) VALUES(?)";
        try(Connection connect = ConnectionLITERegistrations.connectLite(); PreparedStatement prepare = connect.prepareStatement(sql))
        {
            prepare.setString(1, num);
            prepare.executeUpdate();
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
    }

    private void txtMetodo()
    {
        String checkExist = "SELECT * FROM method";
        try(Connection _connect = ConnectionLITERegistrations.connectLite(); Statement _statement = _connect.createStatement(); ResultSet _rs = _statement.executeQuery(checkExist))
        {
            List<String> list = new ArrayList<>();
            while (_rs.next())
            {
                list.add(_rs.getString("metodo"));
            }
            ObservableList<String> listTipo = FXCollections.observableArrayList(list);
            txtMetodoPgmt.setItems(listTipo);
            txtMetodoPgmt.setOnAction(evt -> txtMetodoPgmt.getItems());
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

    private void validarPayment()
    {
        if(txtExmoPgmt.getText().isEmpty())
        {
            lblExmoPgmt.getStyleClass().removeAll("lblBlack");
            lblExmoPgmt.getStyleClass().add("lblEmptyRed");
            txtExmoPgmt.getStyleClass().removeAll("textGray");
            txtExmoPgmt.getStyleClass().add("textEmptyRed");
        }
        if(txtMetodoPgmt.getValue() == null)
        {
            lblMetodoPgmt.getStyleClass().removeAll("lblBlack");
            lblMetodoPgmt.getStyleClass().add("lblEmptyRed");
            txtMetodoPgmt.getStyleClass().removeAll("textGray");
            txtMetodoPgmt.getStyleClass().add("comboBoxEmptyRed");
        }

        if(txtExmoPgmt.getText().isEmpty() || txtMetodoPgmt.getValue() == null) { Model.getInstance().getAlertsFactory().getOnEmptyView(); }

        if(!txtExmoPgmt.getText().isEmpty())
        {
            lblExmoPgmt.getStyleClass().add("lblBlack");
            lblExmoPgmt.getStyleClass().removeAll("lblEmptyRed");
            txtExmoPgmt.getStyleClass().add("textGray");
            txtExmoPgmt.getStyleClass().removeAll("textEmptyRed");
        }
        if(txtMetodoPgmt.getValue() != null)
        {
            lblMetodoPgmt.getStyleClass().add("lblBlack");
            lblMetodoPgmt.getStyleClass().removeAll("lblEmptyRed");
            txtMetodoPgmt.getStyleClass().add("textGray");
            txtMetodoPgmt.getStyleClass().removeAll("comboBoxEmptyRed");
        }
    }

    private void validarCampos()
    {
        if(txtNivel.getValue() == null)
        {
            lblNivel.getStyleClass().removeAll("lblBlack");
            lblNivel.getStyleClass().add("lblEmptyRed");
            txtNivel.getStyleClass().removeAll("textGray");
            txtNivel.getStyleClass().add("comboBoxEmptyRed");
        }
        if(txtHorario.getValue() == null)
        {
            lblHorario.getStyleClass().removeAll("lblBlack");
            lblHorario.getStyleClass().add("lblEmptyRed");
            txtHorario.getStyleClass().removeAll("textGray");
            txtHorario.getStyleClass().add("comboBoxEmptyRed");
        }
        if(txtTipo.getValue() == null)
        {
            lblTipo.getStyleClass().removeAll("lblBlack");
            lblTipo.getStyleClass().add("lblEmptyRed");
            txtTipo.getStyleClass().removeAll("textGray");
            txtTipo.getStyleClass().add("comboBoxEmptyRed");
        }
        if(txtNumeroDeIdentidade.getText().isEmpty())
        {
            lblNumeroDeIdentidade.getStyleClass().removeAll("lblBlack");
            lblNumeroDeIdentidade.getStyleClass().add("lblEmptyRed");
            txtNumeroDeIdentidade.getStyleClass().removeAll("textGray");
            txtNumeroDeIdentidade.getStyleClass().add("textEmptyRed");
        }
        if(txtLocalDeEmissao.getText().isEmpty())
        {
            lblLocalDeEmissao.getStyleClass().removeAll("lblBlack");
            lblLocalDeEmissao.getStyleClass().add("lblEmptyRed");
            txtLocalDeEmissao.getStyleClass().removeAll("textGray");
            txtLocalDeEmissao.getStyleClass().add("textEmptyRed");
        }
        if(txtDataDeValidade.getValue() == null)
        {
            lblDataDeValidade.getStyleClass().removeAll("lblBlack");
            lblDataDeValidade.getStyleClass().add("lblEmptyRed");
            txtDataDeValidade.getStyleClass().removeAll("textGray");
            txtDataDeValidade.getStyleClass().add("datePickerEmptyRed");
        }
        if(txtNomeCompleto.getText().isEmpty())
        {
            lblNomeCompleto.getStyleClass().removeAll("lblBlack");
            lblNomeCompleto.getStyleClass().add("lblEmptyRed");
            txtNomeCompleto.getStyleClass().removeAll("textGray");
            txtNomeCompleto.getStyleClass().add("textEmptyRed");
        }
        if(txtDataDeNascimento.getValue() == null)
        {
            lblDataDeNascimento.getStyleClass().removeAll("lblBlack");
            lblDataDeNascimento.getStyleClass().add("lblEmptyRed");
            txtDataDeNascimento.getStyleClass().removeAll("textGray");
            txtDataDeNascimento.getStyleClass().add("datePickerEmptyRed");
        }
        if(txtGenero.getValue() == null)
        {
            lblGenero.getStyleClass().removeAll("lblBlack");
            lblGenero.getStyleClass().add("lblEmptyRed");
            txtGenero.getStyleClass().removeAll("textGray");
            txtGenero.getStyleClass().add("comboBoxEmptyRed");
        }
        if(txtEstadoCivil.getValue() == null)
        {
            lblEstadoCivil.getStyleClass().removeAll("lblBlack");
            lblEstadoCivil.getStyleClass().add("lblEmptyRed");
            txtEstadoCivil.getStyleClass().removeAll("textGray");
            txtEstadoCivil.getStyleClass().add("comboBoxEmptyRed");
        }
        if(txtNacionalidade.getText().isEmpty())
        {
            lblNacionalidade.getStyleClass().removeAll("lblBlack");
            lblNacionalidade.getStyleClass().add("lblEmptyRed");
            txtNacionalidade.getStyleClass().removeAll("textGray");
            txtNacionalidade.getStyleClass().add("textEmptyRed");
        }
        if(txtNatural.getText().isEmpty())
        {
            lblNatural.getStyleClass().removeAll("lblBlack");
            lblNatural.getStyleClass().add("lblEmptyRed");
            txtNatural.getStyleClass().removeAll("textGray");
            txtNatural.getStyleClass().add("textEmptyRed");
        }
        if(txtEnderecoResidencial.getText().isEmpty())
        {
            lblEnderecoResidencial.getStyleClass().removeAll("lblBlack");
            lblEnderecoResidencial.getStyleClass().add("lblEmptyRed");
            txtEnderecoResidencial.getStyleClass().removeAll("textGray");
            txtEnderecoResidencial.getStyleClass().add("textEmptyRed");
        }
        if(txtRespNomeCompleto.getText().isEmpty())
        {
            lblRespNomeCompleto.getStyleClass().removeAll("lblBlack");
            lblRespNomeCompleto.getStyleClass().add("lblEmptyRed");
            txtRespNomeCompleto.getStyleClass().removeAll("textGray");
            txtRespNomeCompleto.getStyleClass().add("textEmptyRed");
        }
        if(txtRespRelacao.getValue() == null)
        {
            lblRespRelacao.getStyleClass().removeAll("lblBlack");
            lblRespRelacao.getStyleClass().add("lblEmptyRed");
            txtRespRelacao.getStyleClass().removeAll("textGray");
            txtRespRelacao.getStyleClass().add("comboBoxEmptyRed");
        }
        if(txtRespEnderecoResidencial.getText().isEmpty())
        {
            lblRespEnderecoResidencial.getStyleClass().removeAll("lblBlack");
            lblRespEnderecoResidencial.getStyleClass().add("lblEmptyRed");
            txtRespEnderecoResidencial.getStyleClass().removeAll("textGray");
            txtRespEnderecoResidencial.getStyleClass().add("textEmptyRed");
        }
        if(txtRespTelefone1.getText().isEmpty())
        {
            lblRespNumeroDeTelefone.getStyleClass().removeAll("lblBlack");
            lblRespNumeroDeTelefone.getStyleClass().add("lblEmptyRed");
            txtRespTelefone1.getStyleClass().removeAll("textGray");
            txtRespTelefone1.getStyleClass().add("textEmptyRed");
        }

        if(txtNivel.getValue() == null || txtHorario.getValue() == null || txtTipo.getValue() == null || txtNumeroDeIdentidade.getText().isEmpty() || txtLocalDeEmissao.getText().isEmpty() || txtDataDeValidade.getValue() == null || txtNomeCompleto.getText().isEmpty() || txtDataDeNascimento.getValue() == null || txtGenero.getValue() == null || txtEstadoCivil.getValue() == null || txtNacionalidade.getText().isEmpty() || txtNatural.getText().isEmpty() || txtEnderecoResidencial.getText().isEmpty() || txtRespNomeCompleto.getText().isEmpty() || txtRespRelacao.getValue() == null || txtRespEnderecoResidencial.getText().isEmpty() || txtRespTelefone1.getText().isEmpty()) { Model.getInstance().getAlertsFactory().getOnEmptyView(); }

        blackCampos();
    }

    private void blackCampos()
    {
        if(txtNivel.getValue() != null)
        {
            lblNivel.getStyleClass().add("lblBlack");
            lblNivel.getStyleClass().removeAll("lblEmptyRed");
            txtNivel.getStyleClass().add("textGray");
            txtNivel.getStyleClass().removeAll("comboBoxEmptyRed");
        }
        if(txtHorario.getValue() != null)
        {
            lblHorario.getStyleClass().add("lblBlack");
            lblHorario.getStyleClass().removeAll("lblEmptyRed");
            txtHorario.getStyleClass().add("textGray");
            txtHorario.getStyleClass().removeAll("comboBoxEmptyRed");
        }
        if(txtTipo.getValue() != null)
        {
            lblTipo.getStyleClass().add("lblBlack");
            lblTipo.getStyleClass().removeAll("lblEmptyRed");
            txtTipo.getStyleClass().add("textGray");
            txtTipo.getStyleClass().removeAll("comboBoxEmptyRed");
        }
        if(!txtNumeroDeIdentidade.getText().isEmpty())
        {
            lblNumeroDeIdentidade.getStyleClass().add("lblBlack");
            lblNumeroDeIdentidade.getStyleClass().removeAll("lblEmptyRed");
            txtNumeroDeIdentidade.getStyleClass().add("textGray");
            txtNumeroDeIdentidade.getStyleClass().removeAll("textEmptyRed");
        }
        if(!txtLocalDeEmissao.getText().isEmpty())
        {
            lblLocalDeEmissao.getStyleClass().add("lblBlack");
            lblLocalDeEmissao.getStyleClass().removeAll("lblEmptyRed");
            txtLocalDeEmissao.getStyleClass().add("textGray");
            txtLocalDeEmissao.getStyleClass().removeAll("textEmptyRed");
        }
        if(txtDataDeValidade.getValue() != null)
        {
            lblDataDeValidade.getStyleClass().add("lblBlack");
            lblDataDeValidade.getStyleClass().removeAll("lblEmptyRed");
            txtDataDeValidade.getStyleClass().add("textGray");
            txtDataDeValidade.getStyleClass().removeAll("datePickerEmptyRed");
        }
        if(!txtNomeCompleto.getText().isEmpty())
        {
            lblNomeCompleto.getStyleClass().add("lblBlack");
            lblNomeCompleto.getStyleClass().removeAll("lblEmptyRed");
            txtNomeCompleto.getStyleClass().add("textGray");
            txtNomeCompleto.getStyleClass().removeAll("textEmptyRed");
        }
        if(txtDataDeNascimento.getValue() != null)
        {
            lblDataDeNascimento.getStyleClass().add("lblBlack");
            lblDataDeNascimento.getStyleClass().removeAll("lblEmptyRed");
            txtDataDeNascimento.getStyleClass().add("textGray");
            txtDataDeNascimento.getStyleClass().removeAll("datePickerEmptyRed");
        }
        if(txtGenero.getValue() != null)
        {
            lblGenero.getStyleClass().add("lblBlack");
            lblGenero.getStyleClass().removeAll("lblEmptyRed");
            txtGenero.getStyleClass().add("textGray");
            txtGenero.getStyleClass().removeAll("comboBoxEmptyRed");
        }
        if(txtEstadoCivil.getValue() != null)
        {
            lblEstadoCivil.getStyleClass().add("lblBlack");
            lblEstadoCivil.getStyleClass().removeAll("lblEmptyRed");
            txtEstadoCivil.getStyleClass().add("textGray");
            txtEstadoCivil.getStyleClass().removeAll("comboBoxEmptyRed");
        }
        if(!txtNacionalidade.getText().isEmpty())
        {
            lblNacionalidade.getStyleClass().add("lblBlack");
            lblNacionalidade.getStyleClass().removeAll("lblEmptyRed");
            txtNacionalidade.getStyleClass().add("textGray");
            txtNacionalidade.getStyleClass().removeAll("textEmptyRed");
        }
        if(!txtNatural.getText().isEmpty())
        {
            lblNatural.getStyleClass().add("lblBlack");
            lblNatural.getStyleClass().removeAll("lblEmptyRed");
            txtNatural.getStyleClass().add("textGray");
            txtNatural.getStyleClass().removeAll("textEmptyRed");
        }
        if(!txtEnderecoResidencial.getText().isEmpty())
        {
            lblEnderecoResidencial.getStyleClass().add("lblBlack");
            lblEnderecoResidencial.getStyleClass().removeAll("lblEmptyRed");
            txtEnderecoResidencial.getStyleClass().add("textGray");
            txtEnderecoResidencial.getStyleClass().removeAll("textEmptyRed");
        }
        if(!txtRespNomeCompleto.getText().isEmpty())
        {
            lblRespNomeCompleto.getStyleClass().add("lblBlack");
            lblRespNomeCompleto.getStyleClass().removeAll("lblEmptyRed");
            txtRespNomeCompleto.getStyleClass().add("textGray");
            txtRespNomeCompleto.getStyleClass().removeAll("textEmptyRed");
        }
        if(txtRespRelacao.getValue() != null)
        {
            lblRespRelacao.getStyleClass().add("lblBlack");
            lblRespRelacao.getStyleClass().removeAll("lblEmptyRed");
            txtRespRelacao.getStyleClass().add("textGray");
            txtRespRelacao.getStyleClass().removeAll("comboBoxEmptyRed");
        }
        if(!txtRespEnderecoResidencial.getText().isEmpty())
        {
            lblRespEnderecoResidencial.getStyleClass().add("lblBlack");
            lblRespEnderecoResidencial.getStyleClass().removeAll("lblEmptyRed");
            txtRespEnderecoResidencial.getStyleClass().add("textGray");
            txtRespEnderecoResidencial.getStyleClass().removeAll("textEmptyRed");
        }
        if(!txtRespTelefone1.getText().isEmpty())
        {
            lblRespNumeroDeTelefone.getStyleClass().add("lblBlack");
            lblRespNumeroDeTelefone.getStyleClass().removeAll("lblEmptyRed");
            txtRespTelefone1.getStyleClass().add("textGray");
            txtRespTelefone1.getStyleClass().removeAll("textEmptyRed");
        }
    }

    private void typeAnyWord()
    {
        txtNivel.setOnKeyTyped(evt -> blackCampos());
        txtHorario.setOnKeyTyped(evt -> blackCampos());
        txtHorario.setOnAction(evt -> blackCampos());
        txtTipo.setOnKeyTyped(evt -> blackCampos());
        txtTipo.setOnAction(evt -> blackCampos());
        txtNumeroDeIdentidade.setOnKeyTyped(evt -> blackCampos());
        txtLocalDeEmissao.setOnKeyTyped(evt -> blackCampos());
        txtDataDeValidade.setOnKeyTyped(evt -> blackCampos());
        txtDataDeValidade.setOnAction(evt -> blackCampos());
        txtNomeCompleto.setOnKeyTyped(evt -> blackCampos());
        txtDataDeNascimento.setOnKeyTyped(evt -> blackCampos());
        txtDataDeNascimento.setOnAction(evt -> blackCampos());
        txtGenero.setOnKeyTyped(evt -> blackCampos());
        txtGenero.setOnAction(evt -> blackCampos());
        txtEstadoCivil.setOnKeyTyped(evt -> blackCampos());
        txtEstadoCivil.setOnAction(evt -> blackCampos());
        txtNacionalidade.setOnKeyTyped(evt -> blackCampos());
        txtNatural.setOnKeyTyped(evt -> blackCampos());
        txtEnderecoResidencial.setOnKeyTyped(evt -> blackCampos());
        txtRespNomeCompleto.setOnKeyTyped(evt -> blackCampos());
        txtRespRelacao.setOnKeyTyped(evt -> blackCampos());
        txtRespRelacao.setOnAction(evt -> blackCampos());
        txtRespEnderecoResidencial.setOnKeyTyped(evt -> blackCampos());
        txtRespTelefone1.setOnKeyTyped(evt -> blackCampos());
    }

    private void clearCampos()
    {
        justBlack();

        txtNivel.setValue(null);
        txtDuracao.setText("- -");
        txtTaxaDeMensalidade.setText("0.0");
        online.setSelected(false);
        presencial.setSelected(true);
        txtHorario.setValue(null);

        txtTipo.setValue(null);
        txtNumeroDeIdentidade.clear();
        txtLocalDeEmissao.clear();
        txtDataDeValidade.setValue(null);

        txtNomeCompleto.clear();
        txtDataDeNascimento.setValue(null);
        txtGenero.setValue(null);
        txtEstadoCivil.setValue(null);
        txtNacionalidade.clear();
        txtNatural.clear();
        txtEnderecoResidencial.clear();
        txtTelefone1.clear();
        txtTelefone2.clear();
        txtEmail.clear();
        txtObservacoes.clear();

        txtRespNomeCompleto.clear();
        txtRespRelacao.setValue(null);
        txtRespEnderecoResidencial.clear();
        txtRespTelefone1.clear();
        txtRespTelefone2.clear();
    }

    private void justBlack()
    {
        lblNivel.getStyleClass().add("lblBlack");
        lblNivel.getStyleClass().removeAll("lblEmptyRed");
        txtNivel.getStyleClass().add("textGray");
        txtNivel.getStyleClass().removeAll("comboBoxEmptyRed");

        lblHorario.getStyleClass().add("lblBlack");
        lblHorario.getStyleClass().removeAll("lblEmptyRed");
        txtHorario.getStyleClass().add("textGray");
        txtHorario.getStyleClass().removeAll("comboBoxEmptyRed");

        lblTipo.getStyleClass().add("lblBlack");
        lblTipo.getStyleClass().removeAll("lblEmptyRed");
        txtTipo.getStyleClass().add("textGray");
        txtTipo.getStyleClass().removeAll("comboBoxEmptyRed");

        lblNumeroDeIdentidade.getStyleClass().removeAll("lblEmptyRed");
        txtNumeroDeIdentidade.getStyleClass().removeAll("textEmptyRed");
        lblNumeroDeIdentidade.getStyleClass().add("lblBlack");
        txtNumeroDeIdentidade.getStyleClass().add("textGray");

        lblLocalDeEmissao.getStyleClass().add("lblBlack");
        lblLocalDeEmissao.getStyleClass().removeAll("lblEmptyRed");
        txtLocalDeEmissao.getStyleClass().add("textGray");
        txtLocalDeEmissao.getStyleClass().removeAll("textEmptyRed");

        lblDataDeValidade.getStyleClass().add("lblBlack");
        lblDataDeValidade.getStyleClass().removeAll("lblEmptyRed");
        txtDataDeValidade.getStyleClass().add("textGray");
        txtDataDeValidade.getStyleClass().removeAll("datePickerEmptyRed");

        lblNomeCompleto.getStyleClass().add("lblBlack");
        lblNomeCompleto.getStyleClass().removeAll("lblEmptyRed");
        txtNomeCompleto.getStyleClass().add("textGray");
        txtNomeCompleto.getStyleClass().removeAll("textEmptyRed");

        lblDataDeNascimento.getStyleClass().add("lblBlack");
        lblDataDeNascimento.getStyleClass().removeAll("lblEmptyRed");
        txtDataDeNascimento.getStyleClass().add("textGray");
        txtDataDeNascimento.getStyleClass().removeAll("datePickerEmptyRed");

        lblGenero.getStyleClass().add("lblBlack");
        lblGenero.getStyleClass().removeAll("lblEmptyRed");
        txtGenero.getStyleClass().add("textGray");
        txtGenero.getStyleClass().removeAll("comboBoxEmptyRed");

        lblEstadoCivil.getStyleClass().add("lblBlack");
        lblEstadoCivil.getStyleClass().removeAll("lblEmptyRed");
        txtEstadoCivil.getStyleClass().add("textGray");
        txtEstadoCivil.getStyleClass().removeAll("comboBoxEmptyRed");

        lblNacionalidade.getStyleClass().add("lblBlack");
        lblNacionalidade.getStyleClass().removeAll("lblEmptyRed");
        txtNacionalidade.getStyleClass().add("textGray");
        txtNacionalidade.getStyleClass().removeAll("textEmptyRed");

        lblNatural.getStyleClass().add("lblBlack");
        lblNatural.getStyleClass().removeAll("lblEmptyRed");
        txtNatural.getStyleClass().add("textGray");
        txtNatural.getStyleClass().removeAll("textEmptyRed");

        lblEnderecoResidencial.getStyleClass().add("lblBlack");
        lblEnderecoResidencial.getStyleClass().removeAll("lblEmptyRed");
        txtEnderecoResidencial.getStyleClass().add("textGray");
        txtEnderecoResidencial.getStyleClass().removeAll("textEmptyRed");

        lblRespNomeCompleto.getStyleClass().add("lblBlack");
        lblRespNomeCompleto.getStyleClass().removeAll("lblEmptyRed");
        txtRespNomeCompleto.getStyleClass().add("textGray");
        txtRespNomeCompleto.getStyleClass().removeAll("textEmptyRed");

        lblRespRelacao.getStyleClass().add("lblBlack");
        lblRespRelacao.getStyleClass().removeAll("lblEmptyRed");
        txtRespRelacao.getStyleClass().add("textGray");
        txtRespRelacao.getStyleClass().removeAll("comboBoxEmptyRed");

        lblRespEnderecoResidencial.getStyleClass().add("lblBlack");
        lblRespEnderecoResidencial.getStyleClass().removeAll("lblEmptyRed");
        txtRespEnderecoResidencial.getStyleClass().add("textGray");
        txtRespEnderecoResidencial.getStyleClass().removeAll("textEmptyRed");

        lblRespNumeroDeTelefone.getStyleClass().add("lblBlack");
        lblRespNumeroDeTelefone.getStyleClass().removeAll("lblEmptyRed");
        txtRespTelefone1.getStyleClass().add("textGray");
        txtRespTelefone1.getStyleClass().removeAll("textEmptyRed");
    }

    private void limpar()
    {
        if(txtNivel.getValue() == null && txtHorario.getValue() == null && txtTipo.getValue() == null && txtNumeroDeIdentidade.getText().isEmpty() && txtLocalDeEmissao.getText().isEmpty() && txtDataDeValidade.getValue() == null && txtNomeCompleto.getText().isEmpty() && txtDataDeNascimento.getValue() == null && txtGenero.getValue() == null && txtEstadoCivil.getValue() == null && txtNacionalidade.getText().isEmpty() && txtNatural.getText().isEmpty() && txtEnderecoResidencial.getText().isEmpty() && txtRespNomeCompleto.getText().isEmpty() && txtRespRelacao.getValue() == null && txtRespEnderecoResidencial.getText().isEmpty() && txtRespTelefone1.getText().isEmpty() && txtTelefone1.getText().isEmpty() && txtTelefone2.getText().isEmpty() && txtEmail.getText().isEmpty() && txtObservacoes.getText().isEmpty() && txtRespTelefone2.getText().isEmpty() && !online.isSelected() && presencial.isSelected())
        {
            justBlack();
            Model.getInstance().getAlertsFactory().getOnEmptyResetFieldsView();
        }
        else
        {
            clearCampos();
            Model.getInstance().getAlertsFactory().getOnResetFields();
        }
    }

    private void relacaoEmerg()
    {
        String checkExist = "SELECT * FROM relacaoEmerg";
        try(Connection _connect = ConnectionLITERegistrations.connectLite(); Statement _statement = _connect.createStatement(); ResultSet _rs = _statement.executeQuery(checkExist))
        {
            List<String> list = new ArrayList<>();
            while (_rs.next())
            {
                list.add(_rs.getString("relacao"));
            }
            ObservableList<String> listTipo = FXCollections.observableArrayList(list);
            txtRespRelacao.setItems(listTipo);
            txtRespRelacao.setOnAction(evt -> txtRespRelacao.getItems());
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

    private void estadoCivil_txtTipo()
    {
        String checkExist = "SELECT * FROM ieCivil";
        try(Connection _connect = ConnectionLITERegistrations.connectLite(); Statement _statement = _connect.createStatement(); ResultSet _rs = _statement.executeQuery(checkExist))
        {
            List<String> list = new ArrayList<>();
            while (_rs.next())
            {
                list.add(_rs.getString("civil"));
            }
            ObservableList<String> listTipo = FXCollections.observableArrayList(list);
            txtEstadoCivil.setItems(listTipo);
            txtEstadoCivil.setOnAction(evt -> txtEstadoCivil.getItems());
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

    private void genero_txtTipo()
    {
        String checkExist = "SELECT * FROM ieSexo";
        try(Connection _connect = ConnectionLITERegistrations.connectLite(); Statement _statement = _connect.createStatement(); ResultSet _rs = _statement.executeQuery(checkExist))
        {
            List<String> list = new ArrayList<>();
            while (_rs.next())
            {
                list.add(_rs.getString("genero"));
            }
            ObservableList<String> listTipo = FXCollections.observableArrayList(list);
            txtGenero.setItems(listTipo);
            txtGenero.setOnAction(evt -> txtGenero.getItems());
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

    private void dp_txtTipo()
    {
        String checkExist = "SELECT * FROM dp";
        try(Connection _connect = ConnectionLITERegistrations.connectLite(); Statement _statement = _connect.createStatement(); ResultSet _rs = _statement.executeQuery(checkExist))
        {
            List<String> list = new ArrayList<>();
            while (_rs.next())
            {
                list.add(_rs.getString("tipo"));
            }
            ObservableList<String> listTipo = FXCollections.observableArrayList(list);
            txtTipo.setItems(listTipo);
            txtTipo.setOnAction(evt -> txtTipo.getItems());
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


    private void ndm()
    {
        txtNivel.setOnAction(evt -> {
            txtNivel.getItems();

            txtDuracao.setText("");
            txtTaxaDeMensalidade.setText("");
            String checkExist = "SELECT duracao, mensalidade FROM ndm WHERE nivel = '"+txtNivel.getValue()+"'";
            try(Connection _connect = ConnectionLITERegistrations.connectLite(); Statement _statement = _connect.createStatement(); ResultSet _rs = _statement.executeQuery(checkExist))
            {
                if(_rs.next())
                {
                    System.out.println("ENTROU COM NÍVEL = "+txtNivel.getValue());
                    txtDuracao.setText(_rs.getString("duracao"));
                    txtTaxaDeMensalidade.setText(_rs.getString("mensalidade"));
                    //System.out.println("Duração = "+_rs.getString("duracao")+"\n Mensalidade = "+_rs.getString("mensalidade"));
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
            blackCampos();
        });
    }

    private void txtNivel()
    {
        String checkExist = "SELECT nivel FROM ndm";
        try(Connection _connect = ConnectionLITERegistrations.connectLite(); Statement _statement = _connect.createStatement(); ResultSet _rs = _statement.executeQuery(checkExist))
        {
            List<String> list = new ArrayList<>();
            //list.add(null);
            while (_rs.next())
            {
                list.add(_rs.getString("nivel"));
            }
            ObservableList<String> listNivel = FXCollections.observableArrayList(list);
            txtNivel.setItems(listNivel);
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

    private void modalidade()
    {
        presencial.setSelected(true);
        online.setSelected(false);

        presencial.setOnAction(evt -> {
            if(presencial.isSelected())
            {
                presencial.setSelected(true);
                online.setSelected(false);
            }
            else
            {
                presencial.setSelected(true);
                online.setSelected(false);
            }
            txtHorario.getItems().clear();
            String p = "Presencial";
            String checkExist = "SELECT Horario FROM hm WHERE Modalidade = 'Presencial' ORDER BY CAST(SUBSTR(Horario, 1, INSTR(Horario, 'h') - 1) AS INTEGER) ASC";
            try(Connection _connect = ConnectionLITERegistrations.connectLite(); Statement _statement = _connect.createStatement(); ResultSet _rs = _statement.executeQuery(checkExist))
            {
                List<String> list = new ArrayList<>();
                while (_rs.next())
                {
                    list.add(_rs.getString("Horario"));
                }
                ObservableList<String> listHorario = FXCollections.observableArrayList(list);
                txtHorario.setItems(listHorario);
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
        });

        online.setOnAction(evt -> {
            if(online.isSelected())
            {
                presencial.setSelected(false);
                online.setSelected(true);
            }
            else
            {
                presencial.setSelected(false);
                online.setSelected(true);
            }
            txtHorario.getItems().clear();
            String checkExist = "SELECT Horario FROM hm WHERE Modalidade = 'Online' ORDER BY CAST(SUBSTR(Horario, 1, INSTR(Horario, 'h') - 1) AS INTEGER) ASC";
            try(Connection _connect = ConnectionLITERegistrations.connectLite(); Statement _statement = _connect.createStatement(); ResultSet _rs = _statement.executeQuery(checkExist))
            {
                List<String> list = new ArrayList<>();
                while (_rs.next())
                {
                    list.add(_rs.getString("Horario"));
                }
                ObservableList<String> listHorario = FXCollections.observableArrayList(list);
                txtHorario.setItems(listHorario);
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
        });

        if(presencial.isSelected())
        {
            String p = "Presencial";
            String checkExist = "SELECT Horario FROM hm WHERE Modalidade = 'Presencial' ORDER BY CAST(SUBSTR(Horario, 1, INSTR(Horario, 'h') - 1) AS INTEGER) ASC";
            try(Connection _connect = ConnectionLITERegistrations.connectLite(); Statement _statement = _connect.createStatement(); ResultSet _rs = _statement.executeQuery(checkExist))
            {
                List<String> list = new ArrayList<>();
                while (_rs.next())
                {
                    list.add(_rs.getString("Horario"));
                }
                ObservableList<String> listHorario = FXCollections.observableArrayList(list);
                txtHorario.setItems(listHorario);
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
}
