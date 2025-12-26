package payroll;
import javax.swing.*;
import java.awt.*;

public class PrintReceiptFrame extends JFrame {
    public PrintReceiptFrame() {
        setTitle("Print Employee Payment Receipt");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lbl = new JLabel("🧾 Print Receipt Section Coming Soon!", SwingConstants.CENTER);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 18));
        add(lbl);
        setVisible(true);
    }
}
