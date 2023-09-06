package view;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import helper.*;
import model.Bashekim;
import model.Doctor;
import model.Hasta;

public class LoginGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_hastaTC;
	private JTextField fld_doctorTc;
	private JPasswordField fld_doctorPass;
	private JPasswordField fld_hastaPass;
	private DBConnection conn = new DBConnection ();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		setResizable(false);
		setTitle("Hastane Otomasyonu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 450);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lbl_logo = new JLabel(new ImageIcon(getClass().getResource("hospital1.png")));
		lbl_logo.setBounds(183, 22, 100, 100);
		w_pane.add(lbl_logo);
		
		JLabel lblNewLabel = new JLabel("Hastane Yönetimi Sistemine Hoş Geldiniz");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(32, 133, 433, 25);
		w_pane.add(lblNewLabel);
		
		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(22, 169, 443, 233);
		w_pane.add(w_tabpane);
		
		JPanel w_hastaLogin = new JPanel();
		w_hastaLogin.setBackground(Color.WHITE);
		w_tabpane.addTab("Hasta Giriş", null, w_hastaLogin, null);
		w_hastaLogin.setLayout(null);
		
		JLabel lblTcKimlikNo = new JLabel("TC Kimlik No :");
		lblTcKimlikNo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTcKimlikNo.setBounds(20, 23, 149, 25);
		w_hastaLogin.add(lblTcKimlikNo);
		
		JLabel lblifre = new JLabel("Şifre :");
		lblifre.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblifre.setBounds(20, 70, 71, 25);
		w_hastaLogin.add(lblifre);
		
		fld_hastaTC = new JTextField();
		fld_hastaTC.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 20));
		fld_hastaTC.setBounds(172, 22, 230, 25);
		w_hastaLogin.add(fld_hastaTC);
		fld_hastaTC.setColumns(10);
		
		JButton btn_register = new JButton("Kayıt Ol");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI rGUI = new RegisterGUI();
				rGUI.setVisible(true);
				dispose();
			}
		});
		btn_register.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_register.setBounds(20, 126, 183, 55);
		w_hastaLogin.add(btn_register);
		
		JButton btn_hastaLogin = new JButton("Giriş Yap");
		btn_hastaLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(fld_hastaTC.getText().length() == 0 || fld_hastaPass.getText().length() == 0) {
					Helper.showMsg("fill");
				}else {
					boolean key = true;
					try {
						Connection con = conn.connDb(); 
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user");
						while(rs.next()) {
							if(fld_hastaTC.getText().equals(rs.getString("tcno")) && fld_hastaPass.getText().equals(rs.getString("password"))) {
								if(rs.getString("type").equals("hasta")) {
									Hasta hasta = new Hasta();
									hasta.setId(rs.getInt("id"));
									hasta.setPassword("password");
									hasta.setTcno(rs.getString("tcno"));
									hasta.setName(rs.getString("name"));
									hasta.setType(rs.getString("type"));
									System.out.println(hasta.getName());
									HastaGUI hGUI = new HastaGUI(hasta);
									hGUI.setVisible(true);
									dispose();
									key = false;
								}
								
							}
						}
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
					
					if(key) {
						Helper.showMsg("Böyle bir kullanıcı bulunamadı ! ");
					}
				}
			}
		});
		btn_hastaLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_hastaLogin.setBounds(219, 126, 183, 55);
		w_hastaLogin.add(btn_hastaLogin);
		
		fld_hastaPass = new JPasswordField();
		fld_hastaPass.setBounds(172, 70, 230, 25);
		w_hastaLogin.add(fld_hastaPass);
		
		JPanel w_doctorLogin = new JPanel();
		w_doctorLogin.setBackground(Color.WHITE);
		w_tabpane.addTab("Doktor Giriş", null, w_doctorLogin, null);
		w_doctorLogin.setLayout(null);
		
		JLabel lblTcKimlikNo_1 = new JLabel("TC Kimlik No :");
		lblTcKimlikNo_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTcKimlikNo_1.setBounds(20, 22, 149, 25);
		w_doctorLogin.add(lblTcKimlikNo_1);
		
		fld_doctorTc = new JTextField();
		fld_doctorTc.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 20));
		fld_doctorTc.setColumns(10);
		fld_doctorTc.setBounds(172, 21, 230, 25);
		w_doctorLogin.add(fld_doctorTc);
		
		JLabel lblifre_1 = new JLabel("Şifre :");
		lblifre_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblifre_1.setBounds(20, 70, 71, 25);
		w_doctorLogin.add(lblifre_1);
		
		JButton btn_doctorLogin = new JButton("Giriş Yap");
		btn_doctorLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_doctorTc.getText().length() == 0 || fld_doctorPass.getText().length() == 0) {
					helper.Helper.showMsg("fill");
				}else {
					try {
						Connection con = conn.connDb(); 
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user");
						while(rs.next()) {
							if(fld_doctorTc.getText().equals(rs.getString("tcno")) && fld_doctorPass.getText().equals(rs.getString("password"))) {
								if(rs.getString("type").equals("bashekim")) {
									Bashekim bhekim = new Bashekim();
									bhekim.setId(rs.getInt("id"));
									bhekim.setPassword("password");
									bhekim.setTcno(rs.getString("tcno"));
									bhekim.setName(rs.getString("name"));
									bhekim.setType(rs.getString("type"));
									System.out.println(bhekim.getName());
									BashekimGUI bGUI = new BashekimGUI(bhekim);
									bGUI.setVisible(true);
									dispose();
								}
								if(rs.getString("type").equals("doktor")) {
									Doctor doctor = new Doctor();
									doctor.setId(rs.getInt("id"));
									doctor.setPassword("password");
									doctor.setTcno(rs.getString("tcno"));
									doctor.setName(rs.getString("name"));
									doctor.setType(rs.getString("type"));
									DoctorGUI dGUI = new DoctorGUI(doctor);
									dGUI.setVisible(true);
									dispose();
								}
							}
						}
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				}
			}
		});
		btn_doctorLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_doctorLogin.setBounds(20, 124, 382, 55);
		w_doctorLogin.add(btn_doctorLogin);
		
		fld_doctorPass = new JPasswordField();
		fld_doctorPass.setBounds(172, 70, 230, 25);
		w_doctorLogin.add(fld_doctorPass);
	}
}
