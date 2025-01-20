package ram.ewe.englishwithexperts.Controllers.Receipt;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import ram.ewe.englishwithexperts.Controllers.Alerts.Sucesso.AlertRegisterPaymentsController;
import ram.ewe.englishwithexperts.Controllers.Registrations.RegistrationsController;
import ram.ewe.englishwithexperts.Models.ModelDataDAO;
import ram.ewe.englishwithexperts.Repositories.RegistrationsRepository;
import ram.ewe.englishwithexperts.Repositories.valorPorExtenso;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * @author Ramadan ismaeL
 */
public class PaymentsController implements Initializable {
    private static FXMLLoader _loader;
    private static Stage _stages;
    public AnchorPane formReceipt;
    public Label txtNrReceipt;
    public Label txtMoney;
    public Label txtNrStudent;
    public Label txtNameExmo;
    public Label txtExtenso;
    public Label txtDia;
    public Label txtMes;
    public Label txtAno;
    public Label txtHora;
    public Label txtAtentidoPor;
    public CheckBox txtNumerario;
    public CheckBox txtEmola;
    public CheckBox txtMpesa;

    private JFileChooser fileChooser = new JFileChooser();

    public static void setLoader(FXMLLoader loader) { _loader = loader; }
    public static FXMLLoader getLoader() { return _loader; }
    public static void setStage(Stage stages) { _stages = stages; }
    public static Stage getStage() { return _stages; }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        config();
    }

    public void generatePDF() {
        configureFileChooser();
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String filePath = ensurePdfExtension(fileToSave.getAbsolutePath());

            try {
                Platform.runLater(() -> {
                    int width = 845, height = 593;
                    WritableImage writableImage = new WritableImage(width, height);
                    formReceipt.snapshot(null, writableImage);
                    BufferedImage bufferedImage = SwingFXUtils.fromFXImage(writableImage, null);
                    try {
                        saveAsPNG(bufferedImage, filePath);
                        getStage().setAlwaysOnTop(true);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                JOptionPane.showMessageDialog(null, ("Recibo salvo com sucesso em: " + filePath));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar o PDF: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        getStage().toFront();
    }

    private void configureFileChooser() {
        fileChooser.setDialogTitle("Salvar como");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("imagem do recibo-EWE (.png)", "png"));
    }

    private void saveAsPNG(BufferedImage bufferedImage, String filePath) throws IOException {
        File outputFile = new File(filePath);
        ImageIO.write(bufferedImage, "PNG", outputFile);
        //System.out.println("Imagem salva em PNG em: " + outputFile.getAbsolutePath());
    }

    private String ensurePdfExtension(String filePath) {
        if (!filePath.toLowerCase().endsWith(".png")) {
            filePath += ".png";
        }
        return filePath;
    }

    private void config()
    {
        RegistrationsController controller = getLoader().getController();

        txtNrReceipt.setText(controller.txtReciboPgmt.getText());
        txtMoney.setText(""+controller.getValorTotal());
        txtNrStudent.setText(controller.txtNumeroDeEstudantePgmt.getText());
        txtNameExmo.setText(controller.txtExmoPgmt.getText());
        txtExtenso.setText(valorPorExtenso.setValor(controller.getValorTotal()));
        if(controller.txtMetodoPgmt.getValue().equals("E-Mola"))
        {
            txtEmola.setSelected(true);
            txtMpesa.setSelected(false);
            txtNumerario.setSelected(false);
        }
        else if(controller.txtMetodoPgmt.getValue().equals("M-Pesa"))
        {
            txtEmola.setSelected(false);
            txtMpesa.setSelected(true);
            txtNumerario.setSelected(false);
        }
        else if(controller.txtMetodoPgmt.getValue().equals("Numerário"))
        {
            txtEmola.setSelected(false);
            txtMpesa.setSelected(false);
            txtNumerario.setSelected(true);
        }
        txtDia.setText(controller.getDia());
        txtMes.setText(controller.getMes());
        txtAno.setText(""+controller.getAno());
        txtHora.setText(controller.getHoras());
        txtAtentidoPor.setText("PROCESSADO POR COMPUTADOR - Atendido por : "+ModelDataDAO.getInstance().getUserDataModel().getUserName());
    }
}
