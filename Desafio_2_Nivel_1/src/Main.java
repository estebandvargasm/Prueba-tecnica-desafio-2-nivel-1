import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private JTextField inputField;
    private JTextArea outputArea;

    public Main() {
        setTitle("Desafío 2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Ingrese los montos de las casas separados por comas:");
        inputField = new JTextField();
        JButton calculateButton = new JButton("Calcular");
        outputArea = new JTextArea();

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(label, BorderLayout.NORTH);
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(calculateButton, BorderLayout.SOUTH);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateRobbery();
            }
        });
    }

    private void calculateRobbery() {
        String inputText = inputField.getText();
        String[] numsString = inputText.split(",");
        int[] nums = new int[numsString.length];

        for (int i = 0; i < numsString.length; i++) {
            nums[i] = Integer.parseInt(numsString[i].trim());
        }

        int result = rob(nums);
        outputArea.setText("Monto total que puedes robar: " + result);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main gui = new Main();
                gui.setVisible(true);
            }
        });
    }

    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;

        if (n == 1) {
            return nums[0];
        }

        int[] dineroRobado = new int[n];
        dineroRobado[0] = nums[0];
        dineroRobado[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            dineroRobado[i] = Math.max(dineroRobado[i - 1], dineroRobado[i - 2] + nums[i]);
        }

        return dineroRobado[n - 1];
    }
}
