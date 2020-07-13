
import javafx.event.ActionEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class TipCalculatorController {

    private static final NumberFormat currency
            = NumberFormat.getCurrencyInstance();

    private static final NumberFormat percent
            = NumberFormat.getPercentInstance();

    private BigDecimal tipPercentage = new BigDecimal(0.15);

    @FXML
    private Label tipPercentageLabel;

    @FXML
    private TextField amountTextField;

    @FXML
    private TextField tipTextField;

    @FXML
    private TextField totalTextField;

    @FXML
    private Slider tipPercentageSlider;

    @FXML
    void EnterKey(ActionEvent event) {
        try {
            BigDecimal amount = new BigDecimal(amountTextField.getText());
            BigDecimal tip = amount.multiply(tipPercentage);
            BigDecimal total = amount.add(tip);

            tipTextField.setText(currency.format(tip));
            totalTextField.setText(currency.format(total));
        } catch (NumberFormatException ex) {
            amountTextField.setText("Enter Amount");
            amountTextField.selectAll();
            amountTextField.requestFocus();
        }

    }

    public void initialize() {

        currency.setRoundingMode(RoundingMode.HALF_UP);

        tipPercentageSlider.valueProperty().addListener(
                new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                tipPercentage = BigDecimal.valueOf(t1.intValue() / 100.0);
                tipPercentageLabel.setText(percent.format(tipPercentage));
                BigDecimal amount = new BigDecimal(amountTextField.getText());
                BigDecimal tip = amount.multiply(tipPercentage);
                BigDecimal total = amount.add(tip);

                tipTextField.setText(currency.format(tip));
                totalTextField.setText(currency.format(total));
            }
        });

//        totalTextField.get
    }

}
