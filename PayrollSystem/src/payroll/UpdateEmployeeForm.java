package payroll;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Map;

public class UpdateEmployeeForm extends JFrame implements ActionListener {

    private JTextField txtEmpId, txtFirstName, txtSurname, txtDOB, txtEmail, txtContact,
            txtAddress1, txtAddress2, txtHouse, txtPostCode, txtDept, txtDesignation,
            txtStatus, txtDateHired, txtSalary, txtJobTitle;

    private JRadioButton maleBtn, femaleBtn;
    private JLabel photoLabel;
    private JButton updateBtn, deleteBtn, clearBtn, uploadPhotoBtn;
    private Map<String, String[]> dummyEmployees;
    private String empId;
    private String selectedImagePath = null;

    public UpdateEmployeeForm(String empId, Map<String, String[]> dummyEmployees) {
        this.empId = empId;
        this.dummyEmployees = dummyEmployees;

        setTitle("Update Employee");
        setSize(850, 750);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel title = new JLabel("Search Employee");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setBounds(280, 20, 300, 40);
        add(title);

        // ===== FORM FIELDS =====
        int y = 80;

        txtEmpId = addLabeledField("Employee ID:", 60, y);
        txtEmpId.setEditable(false);

        txtFirstName = addLabeledField("First Name:", 60, y += 40);
        txtSurname = addLabeledField("Surname:", 60, y += 40);
        txtDOB = addLabeledField("Date of Birth:", 60, y += 40);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(60, y += 40, 120, 30);
        add(genderLabel);
        maleBtn = new JRadioButton("Male");
        femaleBtn = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleBtn);
        genderGroup.add(femaleBtn);
        maleBtn.setBounds(200, y, 80, 30);
        femaleBtn.setBounds(280, y, 100, 30);
        maleBtn.setBackground(Color.WHITE);
        femaleBtn.setBackground(Color.WHITE);
        add(maleBtn);
        add(femaleBtn);

        txtEmail = addLabeledField("Email:", 60, y += 40);
        txtContact = addLabeledField("Contact:", 60, y += 40);
        txtAddress1 = addLabeledField("Address Line 1:", 60, y += 40);
        txtAddress2 = addLabeledField("Address Line 2:", 60, y += 40);
        txtHouse = addLabeledField("Apt/House No:", 60, y += 40);
        txtPostCode = addLabeledField("Post Code:", 60, y += 40);
        txtDept = addLabeledField("Department:", 60, y += 40);
        txtDesignation = addLabeledField("Designation:", 60, y += 40);
        txtStatus = addLabeledField("Status:", 60, y += 40);
        txtDateHired = addLabeledField("Date Hired:", 60, y += 40);
        txtSalary = addLabeledField("Basic Salary:", 60, y += 40);
        txtJobTitle = addLabeledField("Job Title:", 60, y += 40);

        // ===== PHOTO SECTION =====
        photoLabel = new JLabel("No Photo", SwingConstants.CENTER);
        photoLabel.setBounds(580, 80, 200, 220);
        photoLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(photoLabel);

        uploadPhotoBtn = new JButton("Update/Insert Picture");
        uploadPhotoBtn.setBounds(580, 320, 200, 35);
        uploadPhotoBtn.addActionListener(this);
        styleButton(uploadPhotoBtn, new Color(70, 130, 180));
        add(uploadPhotoBtn);

        // ===== BUTTONS UNDER PHOTO =====
        updateBtn = new JButton("Update Record");
        deleteBtn = new JButton("Delete Record");
        clearBtn = new JButton("Clear");

        styleButton(updateBtn, new Color(0, 153, 0));
        styleButton(deleteBtn, new Color(204, 0, 0));
        styleButton(clearBtn, new Color(100, 100, 100));

        updateBtn.setBounds(580, 370, 200, 35);
        deleteBtn.setBounds(580, 415, 200, 35);
        clearBtn.setBounds(580, 460, 200, 35);

        add(updateBtn);
        add(deleteBtn);
        add(clearBtn);

        updateBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
        clearBtn.addActionListener(this);

        loadEmployeeData();
        setVisible(true);
    }

    private void styleButton(JButton btn, Color color) {
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    }

    private JTextField addLabeledField(String label, int x, int y) {
        JLabel lbl = new JLabel(label);
        lbl.setBounds(x, y, 120, 30);
        add(lbl);

        JTextField field = new JTextField();
        field.setBounds(200, y, 300, 30);
        add(field);
        return field;
    }

    private void loadEmployeeData() {
        if (!dummyEmployees.containsKey(empId)) {
            JOptionPane.showMessageDialog(this, "No employee found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] data = dummyEmployees.get(empId);
        txtEmpId.setText(empId);
        txtFirstName.setText(data[0]);
        txtSurname.setText(data[1]);
        txtDOB.setText(data[2]);
        if (data[3].equalsIgnoreCase("male")) maleBtn.setSelected(true);
        else femaleBtn.setSelected(true);
        txtEmail.setText(data[4]);
        txtContact.setText(data[5]);
        txtAddress1.setText(data[6]);
        txtPostCode.setText(data[7]);
        txtHouse.setText(data[8]);
        txtDept.setText(data[9]);
        txtDesignation.setText(data[10]);
        txtStatus.setText(data[11]);
        txtDateHired.setText(data[12]);
        txtSalary.setText(data[13]);
        txtJobTitle.setText(data[14]);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == uploadPhotoBtn) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select Employee Photo");
            int result = fileChooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                selectedImagePath = file.getAbsolutePath();
                ImageIcon icon = new ImageIcon(new ImageIcon(selectedImagePath)
                        .getImage().getScaledInstance(200, 220, Image.SCALE_SMOOTH));
                photoLabel.setIcon(icon);
                photoLabel.setText("");
            }
        }

        else if (src == updateBtn) {
            if (!dummyEmployees.containsKey(empId)) {
                JOptionPane.showMessageDialog(this, "No employee found to update!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String gender = maleBtn.isSelected() ? "Male" : "Female";
            String[] updatedData = {
                    txtFirstName.getText(), txtSurname.getText(), txtDOB.getText(),
                    gender, txtEmail.getText(), txtContact.getText(), txtAddress1.getText(),
                    txtPostCode.getText(), txtHouse.getText(), txtDept.getText(),
                    txtDesignation.getText(), txtStatus.getText(), txtDateHired.getText(),
                    txtSalary.getText(), txtJobTitle.getText()
            };

            dummyEmployees.put(empId, updatedData);
            JOptionPane.showMessageDialog(this, "Employee record updated successfully!");
        }

        else if (src == deleteBtn) {
            dummyEmployees.remove(empId);
            JOptionPane.showMessageDialog(this, "Employee deleted successfully!");
            dispose();
        }

        else if (src == clearBtn) {
            for (Component c : getContentPane().getComponents()) {
                if (c instanceof JTextField) ((JTextField) c).setText("");
            }
            maleBtn.setSelected(false);
            femaleBtn.setSelected(false);
            photoLabel.setIcon(null);
            photoLabel.setText("No Photo");
        }
    }
}
