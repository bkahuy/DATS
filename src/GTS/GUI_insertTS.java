
package GTS;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.border.EmptyBorder;
import java.sql.*;
import GTS.XLTS;
/**
 *
 * @author buikh
 */
public class GUI_insertTS extends JFrame implements MouseListener, ActionListener{
    private JTextField tfsobd, tfhoten, tftongdiem;
    private JComboBox<String> cbnganh;
    private JRadioButton rbnam, rbnu;
    private JButton btthem;
    private JTable tb;
    private DefaultTableModel dfmodel;
    
    public GUI_insertTS(){
        setTitle("DATS");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000,600);
        
        buildGui();
        loaddata(dfmodel);
    }
    
    private void buildGui(){
        
        JPanel pntrai = new JPanel(new GridBagLayout());
        pntrai.setBorder(new EmptyBorder(10,10,10,10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        //sobd
        gbc.gridx =0;
        gbc.gridy = 0;
        JLabel lbsobd = new JLabel("so bao danh");
        pntrai.add(lbsobd,gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        tfsobd = new JTextField();
        tfsobd.setSize(new Dimension(300,30));
        pntrai.add(tfsobd,gbc);
        
        //ho ten
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel lbhoten = new JLabel("ho ten");
        pntrai.add(lbhoten, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        tfhoten = new JTextField();
        tfhoten.setSize(new Dimension(300, 30));
        pntrai.add(tfhoten, gbc);
        
        //gioi tinh
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel lbgiotinh = new JLabel("gioi tinh");
        pntrai.add(lbgiotinh,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        
        ButtonGroup btg = new ButtonGroup();
        rbnam = new JRadioButton("nam");
        rbnu = new JRadioButton("nu");
        btg.add(rbnu);
        btg.add(rbnam);
        JPanel pngioitinh = new JPanel();
        pngioitinh.add(rbnam);
        pngioitinh.add(rbnu);
        
        pntrai.add(pngioitinh, gbc);
        
        //nganh hoc
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel lbnganh = new JLabel("nganh hoc");
        pntrai.add(lbnganh,gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        cbnganh = new JComboBox<>(new String[] {"tri tue nhan tao", "co khi", "cong trinh thuy"});
        pntrai.add(cbnganh,gbc);
        
        //tong diem
        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel lbtongdiem = new JLabel("tong diem");
        pntrai.add(lbtongdiem, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        tftongdiem = new JTextField();
        tftongdiem.setSize(new Dimension(300,30));
        pntrai.add(tftongdiem,gbc);
        
        //btn
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        btthem = new JButton("them thi sinh");
        btthem.addActionListener((e) -> {
            int sobd = Integer.parseInt(tfsobd.getText().trim());
            String hoten = tfhoten.getText().trim();
            String gioitinh = "";
            if(rbnam.isSelected()){
                gioitinh = rbnam.getText();
            }
            else if (rbnu.isSelected()) {
                gioitinh = rbnu.getText();
            }
            String nganhhoc = cbnganh.getSelectedItem().toString();
            int tongdiem = Integer.parseInt(tftongdiem.getText().toString());
            
            XLTS xl = new XLTS();
            boolean res = xl.insertTS(new Thisinh(sobd, tongdiem, hoten, gioitinh, nganhhoc));
            if(res){
                loaddata(dfmodel);
                JOptionPane.showMessageDialog(null, "them thanh cong");
            }
            else{
                JOptionPane.showMessageDialog(null, "them khong thanh cong");
            }
        });
        pntrai.add(btthem,gbc);
        
        //table
        JPanel pnphai = new JPanel(new GridLayout(1, 1));
        String[] header = {"so bao danh", "ho ten", "gioi tinh", "nganh hoc", "tong diem"};
        dfmodel = new DefaultTableModel(header,0);
        tb = new JTable(dfmodel);
        pnphai.add(new JScrollPane(tb));
        
//        loaddata(dfmodel);

        // Thêm cả hai bảng vào JFrame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(pntrai, BorderLayout.WEST);
        getContentPane().add(pnphai, BorderLayout.CENTER);
        
    }
    public static void main(String[] args) {
        new GUI_insertTS().setVisible(true);
        XLTS xl = new XLTS();
        xl.getcon();
    }
    
    private void loaddata(DefaultTableModel dfmode){
        try {
            XLTS xl = new XLTS();
            ResultSet res = xl.getTS();
            dfmode.setRowCount(0);
            if(res != null){
                while(res.next()){
                    Thisinh ts = new Thisinh(
                            res.getInt("sobd"),
                            res.getInt("tongdiem"),
                            res.getString("hoten"),
                            res.getString("gioitinh"),
                            res.getString("nganh")
                    );
                    
                    String hocbong = ts.Hochong();
                    
                    dfmode.addRow(new Object[]{
                        ts.getSobd(),
                        ts.getHoten(),
                        ts.getGioitinh(),
                        ts.getNganh(),
                        ts.getTongdiem(),
                        hocbong                        
                    });
                }
            }
            dfmodel.fireTableDataChanged();
        } catch (SQLException e) {
            System.out.println("failed load" + e.getMessage());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
