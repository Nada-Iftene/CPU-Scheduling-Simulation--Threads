import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Panel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.ScrollPane;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBoxMenuItem;
/*
 * Creer par Iftene Zineb Nada 01/05/2018
 * 
 */
public class InputPanel extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField arrivalField;
	private JTextField burstField;
	
	int i;
	 int choiceOfAlgo;
	 private JTextField quantumField;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InputPanel frame = new InputPanel();
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
	public InputPanel() {
		setTitle("CPU Scheduling Simulation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 770, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInputData = new JLabel("Input Data");
		lblInputData.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblInputData.setBounds(30, 31, 119, 29);
		contentPane.add(lblInputData);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(308, 61, 376, 263);
		contentPane.add(scrollPane);
		
		
		
		
		JLabel lblNewLabel = new JLabel("Arrival Time");
		lblNewLabel.setBounds(42, 88, 74, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Burset Time");
		lblNewLabel_1.setBounds(42, 134, 74, 14);
		contentPane.add(lblNewLabel_1);
		
		arrivalField = new JTextField();
		arrivalField.setBounds(138, 85, 86, 20);
		contentPane.add(arrivalField);
		arrivalField.setColumns(10);
		
		burstField = new JTextField();
		burstField.setBounds(138, 131, 86, 20);
		contentPane.add(burstField);
		burstField.setColumns(10);
		

		
		
		
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 754, 23);
		contentPane.add(menuBar);
		
		JMenu mnParametres = new JMenu("Parameters");
		mnParametres.setFont(new Font("Segoe UI", Font.BOLD, 13));
		menuBar.add(mnParametres);
		
		JMenu mnNewMenu = new JMenu("Algorithm");
		mnParametres.add(mnNewMenu);
		
		JMenuItem mntmRoundRobin = new JMenuItem("Round Robin(RR)");
		mnNewMenu.add(mntmRoundRobin);
		
		JMenuItem mntmShortestJobFirst = new JMenuItem("Shortes Elapsed Time");
		mnNewMenu.add(mntmShortestJobFirst);
		
		JMenuItem mntmQuitter = new JMenuItem("Quitter");
		mntmQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 
		                System.exit(0);
		            
		       
			}});
		
		mnParametres.add(mntmQuitter);
		
		JButton btnNewButton = new JButton("Add Proce ssus To The System");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				DefaultTableModel model = (DefaultTableModel) table.getModel();
               
                ++i;
                String[] a = {String.valueOf(i), arrivalField.getText(), burstField.getText()};

                model.addRow(a);

                
				
				arrivalField.setText("");
				burstField.setText("");
				
			}
		});
		btnNewButton.setBounds(37, 181, 213, 23);
		contentPane.add(btnNewButton);
		
		JLabel quantumlbl = new JLabel("Quantum");
		quantumlbl.setBounds(42, 227, 74, 14);
		contentPane.add(quantumlbl);
		
		quantumField = new JTextField();
		quantumField.setBounds(138, 224, 86, 20);
		contentPane.add(quantumField);
		quantumField.setColumns(10);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Process", "Arrival", "Burst"
			}
		));
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();    

		
		JButton btnCommencer = new JButton("Commencer");
		btnCommencer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	
                ArrayList<ClassInputData> file = new ArrayList<>();

                for (int i = 0; i < table.getRowCount(); i++) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();

                    ClassInputData p = new ClassInputData(i + 1,
                    		Integer.valueOf((String) model.getValueAt(i, 1)),
                    		Integer.valueOf((String) model.getValueAt(i, 2)),
                    	 false);


                    System.out.println("Processus " + p.getName() + " arrival time " + p.getTempArriver() + " cpu " + p.getTempCpu());
                    file.add(p);
                }
                System.out.println();

                for (int i = 0; i < file.size(); i++) {
                    System.out.println(file.get(i).getName());
                }
                
            

                DataVisualizationPanel execution = new DataVisualizationPanel(file, choiceOfAlgo, Integer.parseInt(quantumField.getText()));
                setVisible(false);
                execution.setVisible(true);
            }
            
        });

		btnCommencer.setBounds(37, 269, 213, 23);
		contentPane.add(btnCommencer);
		
	}
}
