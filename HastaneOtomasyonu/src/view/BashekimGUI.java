package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import model.Bashekim;
import model.Clinic;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import helper.*;
import javax.swing.JComboBox;

public class BashekimGUI extends JFrame {

	static Bashekim bashekim = new Bashekim ();
	Clinic clinic = new Clinic();
	private JPanel w_pane;
	private JTextField fld_dName;
	private JTextField fld_dTcno;
	private JTextField fld_doctorID;
	private JTable table_doctor;
	private DefaultTableModel doctorModel = null;
	private Object[] doctorData = null;
	private JTextField fld_dPass;
	private JTable table_clinic;
	private JTextField fld_clinicName;
	private DefaultTableModel clinicModel = null;
	private Object[] clinicData = null;
	private JPopupMenu clinicMenu ;
	private JTable table_worker;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BashekimGUI frame = new BashekimGUI(bashekim);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public BashekimGUI(Bashekim bashekim) throws SQLException {
		
		//doktor model
		doctorModel = new DefaultTableModel();
		Object[] colDoctorName = new Object[4];
		colDoctorName[0]="ID";
		colDoctorName[1]="Ad Soyad";
		colDoctorName[2]="TC No";
		colDoctorName[3]="Şifre";
		doctorModel.setColumnIdentifiers(colDoctorName);
		doctorData = new Object[4];
		for(int i=0; i<bashekim.getDoctorList().size();i++) {
			doctorData[0] = bashekim.getDoctorList().get(i).getId();
			doctorData[1] = bashekim.getDoctorList().get(i).getName();
			doctorData[2] = bashekim.getDoctorList().get(i).getTcno();
			doctorData[3] = bashekim.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
			
		}
		
		//clinic model
		
		clinicModel = new DefaultTableModel();
		Object[] colClinic= new Object[2];
		colClinic[0]="ID";
		colClinic[1]="Polinklinik Adı";
		
		clinicModel.setColumnIdentifiers(colClinic);
		clinicData = new Object[2];
		for(int i=0; i<clinic.getList().size();i++) {
			clinicData[0] = clinic.getList().get(i).getId();
			clinicData[1] = clinic.getList().get(i).getName();
			clinicModel.addRow(clinicData);
			
		}
		
		//Worker Model
		
		DefaultTableModel workerModel = new DefaultTableModel();
		Object [] colWorker = new Object [2];
		colWorker [0] = "ID";
		colWorker [1] = "AD Soyad ";
		workerModel.setColumnIdentifiers(colWorker);
		Object[] workerData = new Object[2];
		
		
		
		setTitle("Hastane Yönetim Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hoşgeldiniz Sayın " + bashekim.getName());
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 11, 238, 39);
		w_pane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Çıkış");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBackground(Color.RED);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(626, 14, 100, 32);
		w_pane.add(btnNewButton);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 15));
		tabbedPane.setBounds(10, 61, 716, 391);
		w_pane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Doktor Yönetimi", null, panel, null);
		panel.setLayout(null);
		
		JLabel label = new JLabel("Ad Soyad");
		label.setFont(new Font("Tahoma", Font.BOLD, 15));
		label.setBounds(495, 10, 206, 27);
		panel.add(label);
		
		fld_dName = new JTextField();
		fld_dName.setBounds(495, 35, 206, 27);
		panel.add(fld_dName);
		fld_dName.setColumns(10);
		
		JLabel label_1 = new JLabel("TC Kimlik No ");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_1.setBounds(495, 70, 206, 27);
		panel.add(label_1);
		
		fld_dTcno = new JTextField();
		fld_dTcno.setColumns(10);
		fld_dTcno.setBounds(495, 95, 206, 27);
		panel.add(fld_dTcno);
		
		JLabel label_2 = new JLabel("Şifre");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_2.setBounds(495, 130, 206, 27);
		panel.add(label_2);
		
		fld_dPass = new JTextField();
		fld_dPass.setColumns(10);
		fld_dPass.setBounds(495, 155, 206, 27);
		panel.add(fld_dPass);
		
		JButton btn_addDoctor = new JButton("Ekle");
		btn_addDoctor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(fld_dName.getText().length() == 0 || fld_dPass.getText().length() == 0 || fld_dTcno.getText().length() == 0) {
					Helper.showMsg("fill");
				}else {
					try {
						boolean control = bashekim.addDoctor(fld_dTcno.getText(),fld_dPass.getText(),fld_dName.getText());
						if(control) {
							Helper.showMsg("success");
							fld_dName.setText(null);
							fld_dTcno.setText(null);
							fld_dPass.setText(null);
							updateDoctorModel();
						}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_addDoctor.setFont(new Font("Tahoma", Font.BOLD, 15));
		btn_addDoctor.setBounds(495, 190, 206, 35);
		panel.add(btn_addDoctor);
		
		JLabel label_3 = new JLabel("Kullanıcı ID");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_3.setBounds(495, 250, 206, 27);
		panel.add(label_3);
		
		fld_doctorID = new JTextField();
		fld_doctorID.setEditable(false);
		fld_doctorID.setBounds(495, 275, 206, 27);
		panel.add(fld_doctorID);
		fld_doctorID.setColumns(10);
		
		JButton btn_delDoctor = new JButton("Sil");
		btn_delDoctor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(fld_doctorID.getText().length() == 0) {
					Helper.showMsg("Lütfen Geçerli Bir Doktor Seçiniz ! ");
					
				}else {
					if(Helper.confirm("sure")) {
						int selectID = Integer.parseInt(fld_doctorID.getText());
						try {
							boolean control = bashekim.deleteDoctor(selectID);
							if(control) {
								Helper.showMsg("success");
								fld_doctorID.setText(null);
								updateDoctorModel();
							}
						} catch (SQLException e1) {
							
							e1.printStackTrace();
						}
					}
				}
				
			}
		});
		btn_delDoctor.setFont(new Font("Tahoma", Font.BOLD, 15));
		btn_delDoctor.setBounds(495, 310, 206, 35);
		panel.add(btn_delDoctor);
		
		JScrollPane w_scrollDoctor = new JScrollPane();
		w_scrollDoctor.setBounds(10, 11, 476, 336);
		panel.add(w_scrollDoctor);
		
		table_doctor = new JTable(doctorModel);
		w_scrollDoctor.setViewportView(table_doctor);
		
		
		table_doctor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					fld_doctorID.setText(table_doctor.getValueAt(table_doctor.getSelectedRow(),0).toString());

				} catch (Exception e2) {
					
				}
				
			}
		});
		
		table_doctor.getModel().addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				if(e.getType() == TableModelEvent.UPDATE) {
					int selectID = Integer.parseInt(table_doctor.getValueAt(table_doctor.getSelectedRow(),0).toString());
					String selectName = table_doctor.getValueAt(table_doctor.getSelectedRow(),1).toString();
					String selectTcno = table_doctor.getValueAt(table_doctor.getSelectedRow(),2).toString();
					String selectPass = table_doctor.getValueAt(table_doctor.getSelectedRow(),3).toString();
					
					try {
						boolean control = bashekim.updateDoctor(selectID, selectTcno, selectPass, selectName);
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				}
				
				
			}
		});
		
		JPanel w_clinic = new JPanel();
		w_clinic.setBackground(Color.WHITE);
		tabbedPane.addTab("Poliklinikler", null, w_clinic, null);
		w_clinic.setLayout(null);
		
		JScrollPane w_scrollClinic = new JScrollPane();
		w_scrollClinic.setBounds(10, 11, 240, 336);
		w_clinic.add(w_scrollClinic);
		
		clinicMenu = new JPopupMenu();
		JMenuItem updateMenu = new JMenuItem("Güncelle");
		JMenuItem deleteMenu = new JMenuItem("Sil");
		clinicMenu.add(updateMenu);
		clinicMenu.add(deleteMenu);
		
		updateMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selID = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(),0).toString());
				Clinic selectClinic = clinic.getFetch(selID);
				UpdateClinicGUI updateGUI = new UpdateClinicGUI(selectClinic);
				updateGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				updateGUI.setVisible(true);
				updateGUI.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						try {
							updateClinicModel();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						super.windowClosed(e);
					}
				});
				
				
			}
		});
		
		deleteMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Helper.confirm("sure")){
					int selID = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(),0).toString());
					try {
						if(clinic.deleteClinic(selID)) {
							Helper.showMsg("success");
							updateClinicModel();
						}else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				

				
			}
		});
		
		table_clinic = new JTable(clinicModel);
		table_clinic.setComponentPopupMenu(clinicMenu);
		table_clinic.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectedRow = table_clinic.rowAtPoint(point);
				table_clinic.setRowSelectionInterval(selectedRow, selectedRow);
				

				
			}
		});
		w_scrollClinic.setViewportView(table_clinic);
		
		JLabel lblPoliklinikAd = new JLabel("Poliklinik Adı");
		lblPoliklinikAd.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPoliklinikAd.setBounds(260, 4, 206, 27);
		w_clinic.add(lblPoliklinikAd);
		
		fld_clinicName = new JTextField();
		fld_clinicName.setColumns(10);
		fld_clinicName.setBounds(260, 31, 193, 27);
		w_clinic.add(fld_clinicName);
		
		JButton btn_addClinic = new JButton("Ekle");
		btn_addClinic.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(fld_clinicName.getText().length() ==  0) {
					Helper.showMsg("fill");
				}else {
					try {
						if(clinic.addClinic(fld_clinicName.getText())) {
							Helper.showMsg("success");
							fld_clinicName.setText(null);
							updateClinicModel();
						}
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				}
				
			}
		});
		btn_addClinic.setFont(new Font("Tahoma", Font.BOLD, 15));
		btn_addClinic.setBounds(260, 69, 193, 42);
		w_clinic.add(btn_addClinic);
		
		JScrollPane w_scrollWorker = new JScrollPane();
		w_scrollWorker.setBounds(463, 11, 238, 336);
		w_clinic.add(w_scrollWorker);
		
		table_worker = new JTable();
		w_scrollWorker.setViewportView(table_worker);
		
		JComboBox select_doctor = new JComboBox();
		select_doctor.setBounds(260, 252, 193, 42);
		for(int i =0 ; i<bashekim.getDoctorList().size() ; i++) {
			select_doctor.addItem(new Item(bashekim.getDoctorList().get(i).getId(),bashekim.getDoctorList().get(i).getName()));
		}
		select_doctor.addActionListener(e -> {
			JComboBox c = (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
			System.out.println(item.getKey() + " : " + item.getValue());
		});
		w_clinic.add(select_doctor);
		
		JButton btn_addWorker = new JButton("Ekle");
		btn_addWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow= table_clinic.getSelectedRow();
				if(selRow >=0) {
					String selClinic= table_clinic.getModel().getValueAt(selRow, 0).toString();
					int selClinicID = Integer.parseInt(selClinic);
					Item doctorItem = (Item) select_doctor.getSelectedItem();		
					try {
						boolean control = bashekim.addWorker(doctorItem.getKey(), selClinicID);
						if(control) {
							Helper.showMsg("success");
							DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
							clearModel.setRowCount(0);
							for(int i= 0; i<bashekim.getClinicDoctorList(selClinicID).size(); i++) {
								workerData [0] = bashekim.getClinicDoctorList(selClinicID).get(i).getId();
								workerData [1] = bashekim.getClinicDoctorList(selClinicID).get(i).getName();
								workerModel.addRow(workerData);
								
							}
							table_worker.setModel(workerModel);

						}else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					Helper.showMsg("Lütfen bir poliklinik seçiniz !");
				}
			}
		});
		btn_addWorker.setFont(new Font("Tahoma", Font.BOLD, 15));
		btn_addWorker.setBounds(260, 305, 193, 42);
		w_clinic.add(btn_addWorker);
		
		JLabel lblPoliklinikAd_1 = new JLabel("Poliklinik Adı");
		lblPoliklinikAd_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPoliklinikAd_1.setBounds(260, 133, 193, 27);
		w_clinic.add(lblPoliklinikAd_1);
		
		JButton btn_workerSelect = new JButton("Seç");
		btn_workerSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_clinic.getSelectedRow();
				if(selRow >= 0 ) {
					String selClinic= table_clinic.getModel().getValueAt(selRow, 0).toString();
					int selClinicID = Integer.parseInt(selClinic);
					DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
					clearModel.setRowCount(0);
					
					try {
						for(int i= 0; i<bashekim.getClinicDoctorList(selClinicID).size(); i++) {
							workerData [0] = bashekim.getClinicDoctorList(selClinicID).get(i).getId();
							workerData [1] = bashekim.getClinicDoctorList(selClinicID).get(i).getName();
							workerModel.addRow(workerData);
							
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					table_worker.setModel(workerModel);
				}else {
					Helper.showMsg("Lütfen bir pliklinik seçiniz !");
				}
			}
		});
		btn_workerSelect.setFont(new Font("Tahoma", Font.BOLD, 15));
		btn_workerSelect.setBounds(260, 158, 193, 42);
		w_clinic.add(btn_workerSelect);
		
		
	}
	
	public void updateDoctorModel () throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
		clearModel.setRowCount(0);
		for(int i=0; i<bashekim.getDoctorList().size();i++) {
			doctorData[0] = bashekim.getDoctorList().get(i).getId();
			doctorData[1] = bashekim.getDoctorList().get(i).getName();
			doctorData[2] = bashekim.getDoctorList().get(i).getTcno();
			doctorData[3] = bashekim.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
			
		}
	 
	}
	
	public void updateClinicModel () throws SQLException {
		
		DefaultTableModel clearModel = (DefaultTableModel) table_clinic.getModel();
		clearModel.setRowCount(0);
		for(int i=0; i<clinic.getList().size();i++) {
			clinicData[0] = clinic.getList().get(i).getId();
			clinicData[1] = clinic.getList().get(i).getName();
			clinicModel.addRow(clinicData);
			
		}
		
	}
}
