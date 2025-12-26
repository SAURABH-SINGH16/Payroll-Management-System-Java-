package payroll;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AddEmployeeFrame extends JFrame {

    private JTextField idField, nameField, dobField, emailField, phoneField;
    private JTextField addr1Field, addr2Field, aptField, postalField;
    private JTextField deptField, desigField, statusField, dateHiredField, salaryField, jobTitleField;
    private JRadioButton maleBtn, femaleBtn;
    private JLabel imgLabel;
    private JButton addBtn, clearBtn, uploadBtn;

    private ImageIcon employeePhoto; // to hold the selected photo

    public AddEmployeeFrame() {
        setTitle("Payroll System | ADD EMPLOYEE");
        setSize(1100, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // ===== HEADER =====
        JLabel header = new JLabel("ADD EMPLOYEE");
        header.setFont(new Font("Segoe UI", Font.BOLD, 24));
        header.setForeground(Color.BLUE.darker());
        header.setBounds(40, 15, 400, 35);
        add(header);

        // ===== LEFT SIDE =====
        int lblX = 40, fieldX = 200, y = 70, gap = 40;

        JLabel idLbl = new JLabel("Employee ID:");
        idLbl.setBounds(lblX, y, 150, 25);
        add(idLbl);
        idField = new JTextField();
        idField.setBounds(fieldX, y, 220, 28);
        add(idField);

        y += gap;
        JLabel nameLbl = new JLabel("Employee Name:");
        nameLbl.setBounds(lblX, y, 150, 25);
        add(nameLbl);
        nameField = new JTextField();
        nameField.setBounds(fieldX, y, 220, 28);
        add(nameField);

        y += gap;
        JLabel dobLbl = new JLabel("Date of Birth:");
        dobLbl.setBounds(lblX, y, 150, 25);
        add(dobLbl);
        dobField = new JTextField("DD/MM/YYYY");
        dobField.setBounds(fieldX, y, 220, 28);
        add(dobField);

        y += gap;
        JLabel genderLbl = new JLabel("Gender:");
        genderLbl.setBounds(lblX, y, 150, 25);
        add(genderLbl);
        maleBtn = new JRadioButton("Male");
        femaleBtn = new JRadioButton("Female");
        maleBtn.setBackground(Color.WHITE);
        femaleBtn.setBackground(Color.WHITE);
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleBtn);
        genderGroup.add(femaleBtn);
        maleBtn.setBounds(fieldX, y, 70, 25);
        femaleBtn.setBounds(fieldX + 100, y, 100, 25);
        add(maleBtn);
        add(femaleBtn);

        y += gap;
        JLabel emailLbl = new JLabel("Email:");
        emailLbl.setBounds(lblX, y, 150, 25);
        add(emailLbl);
        emailField = new JTextField();
        emailField.setBounds(fieldX, y, 220, 28);
        add(emailField);

        y += gap;
        JLabel phoneLbl = new JLabel("Contact No:");
        phoneLbl.setBounds(lblX, y, 150, 25);
        add(phoneLbl);
        phoneField = new JTextField();
        phoneField.setBounds(fieldX, y, 220, 28);
        add(phoneField);

        y += gap;
        JLabel addr1Lbl = new JLabel("Address Line 1:");
        addr1Lbl.setBounds(lblX, y, 150, 25);
        add(addr1Lbl);
        addr1Field = new JTextField();
        addr1Field.setBounds(fieldX, y, 220, 28);
        add(addr1Field);

        y += gap;
        JLabel addr2Lbl = new JLabel("Address Line 2:");
        addr2Lbl.setBounds(lblX, y, 150, 25);
        add(addr2Lbl);
        addr2Field = new JTextField();
        addr2Field.setBounds(fieldX, y, 220, 28);
        add(addr2Field);

        y += gap;
        JLabel aptLbl = new JLabel("Apt/House No:");
        aptLbl.setBounds(lblX, y, 150, 25);
        add(aptLbl);
        aptField = new JTextField();
        aptField.setBounds(fieldX, y, 220, 28);
        add(aptField);

        y += gap;
        JLabel postalLbl = new JLabel("Postal Code:");
        postalLbl.setBounds(lblX, y, 150, 25);
        add(postalLbl);
        postalField = new JTextField();
        postalField.setBounds(fieldX, y, 220, 28);
        add(postalField);

        // ===== RIGHT SIDE =====
        int rightX = 480, rightY = 70, gap2 = 40;

        JLabel deptLbl = new JLabel("Department:");
        deptLbl.setBounds(rightX, rightY, 130, 25);
        add(deptLbl);
        deptField = new JTextField();
        deptField.setBounds(rightX + 150, rightY, 220, 28);
        add(deptField);

        rightY += gap2;
        JLabel desigLbl = new JLabel("Designation:");
        desigLbl.setBounds(rightX, rightY, 130, 25);
        add(desigLbl);
        desigField = new JTextField();
        desigField.setBounds(rightX + 150, rightY, 220, 28);
        add(desigField);

        rightY += gap2;
        JLabel statusLbl = new JLabel("Status:");
        statusLbl.setBounds(rightX, rightY, 130, 25);
        add(statusLbl);
        statusField = new JTextField();
        statusField.setBounds(rightX + 150, rightY, 220, 28);
        add(statusField);

        rightY += gap2;
        JLabel hiredLbl = new JLabel("Date Hired:");
        hiredLbl.setBounds(rightX, rightY, 130, 25);
        add(hiredLbl);
        dateHiredField = new JTextField("DD/MM/YYYY");
        dateHiredField.setBounds(rightX + 150, rightY, 220, 28);
        add(dateHiredField);

        rightY += gap2;
        JLabel salaryLbl = new JLabel("Basic Salary:");
        salaryLbl.setBounds(rightX, rightY, 130, 25);
        add(salaryLbl);
        salaryField = new JTextField();
        salaryField.setBounds(rightX + 150, rightY, 220, 28);
        add(salaryField);

        rightY += gap2;
        JLabel jobLbl = new JLabel("Job Title:");
        jobLbl.setBounds(rightX, rightY, 130, 25);
        add(jobLbl);
        jobTitleField = new JTextField();
        jobTitleField.setBounds(rightX + 150, rightY, 220, 28);
        add(jobTitleField);

        // ===== PHOTO SECTION =====
        imgLabel = new JLabel("No Image", SwingConstants.CENTER);
        imgLabel.setBounds(880, 90, 150, 150);
        imgLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(imgLabel);

        uploadBtn = new JButton("Insert Picture...");
        uploadBtn.setBounds(890, 250, 130, 28);
        add(uploadBtn);

        uploadBtn.addActionListener(e -> uploadImage());

        // ===== BUTTONS =====
        addBtn = new JButton("Add Record");
        addBtn.setBounds(880, 320, 150, 35);
        add(addBtn);

        clearBtn = new JButton("Clear");
        clearBtn.setBounds(880, 370, 150, 35);
        add(clearBtn);

        setVisible(true);
    }

    private void uploadImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Employee Photo");
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files (JPG, PNG, JPEG)", "jpg", "jpeg", "png");
        fileChooser.addChoosableFileFilter(filter);

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            ImageIcon originalIcon = new ImageIcon(selectedFile.getAbsolutePath());
            Image scaledImg = originalIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            employeePhoto = new ImageIcon(scaledImg);
            imgLabel.setIcon(employeePhoto);
            imgLabel.setText(null);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AddEmployeeFrame::new);
    }
}
