import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/*
 * Creer par Iftene Zineb Nada 01/05/2018
 */

//les etats de chargement de chaque processus : est afficher dans la console
public class DataVisualizationPanel extends JFrame {

	private JPanel contentPane;
	JTable processingTable;
    JTable waitingTable;
	
	JLabel lblTempsDattente;
	JLabel lblLeTurnAround;
	//declaration
	public int choiceOfAlgo;
    public int quantum;
    public static ArrayList<ClassInputData> file;
    boolean preemptif = false;

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
	
	 void makeStuffWork() {
	        Algorithm algorithmes = new Algorithm(file);
	        switch (choiceOfAlgo) {
	            
	            case 1:
	                
	                    System.out.println("shortest job non Preemtif");
	                    algorithmes.shortJobFirstNoPreemptif(algorithmes.file, this, quantum);
	                
	                break;
	            case 2:
	                System.out.println("round robin");
	                algorithmes.roundRobinNonPreemptif(algorithmes.file, quantum, this);
	                break;
	            
	            default:
	                System.out.println("default");
	                algorithmes.shortJobFirstNoPreemptif(algorithmes.file, this, quantum);
	                break;
	        }

	        /*float turnAround = 0;
	        float waitingTime = 0;

	        DefaultTableModel model = (DefaultTableModel) processingTable.getModel();

	        for (int i = 0; i < model.getRowCount(); i++) {
	            waitingTime += Integer.valueOf(String.valueOf(model.getValueAt(i, 2))) - Integer.valueOf(String.valueOf(model.getValueAt(i, 1)));
	        }
	        waitingTime /= model.getRowCount();
	        //****************************
	        lblTempsDattente.setText(lblTempsDattente.getText() + " " + waitingTime);

	        for (int i = 0; i < file.size(); i++) {
	            turnAround += file.get(i).getExitTime() - file.get(i).getTempArriver();
	        }
	        turnAround /= file.size();
	        lblLeTurnAround.setText(lblLeTurnAround.getText() + ": " + turnAround);

*/
	    }

	public DataVisualizationPanel(ArrayList<ClassInputData> file, int choiceOfAlgo, int quantum) {
		this.file = file;
		 
	        this.quantum = quantum;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 599, 507);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JLabel label = new JLabel("Data Visualisation");
		label.setFont(new Font("Tahoma", Font.BOLD, 15));
		label.setBounds(10, 11, 184, 29);
		contentPane.add(label);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 64, 563, 114);
		contentPane.add(scrollPane_2);
		
		waitingTable = new JTable();
		waitingTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Processus", "Arrival", "Burst"
			}
		));
		
		scrollPane_2.setViewportView(waitingTable);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 198, 563, 141);
		contentPane.add(scrollPane);
		
		processingTable = new JTable();
		processingTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Processus", "Temps Debut", "Qt/Fin"
			}
		));
		
		 
		scrollPane.setViewportView(processingTable);
		
		
		//**********Commencer****************
		JButton btnCommencer = new JButton("Commencer");
		btnCommencer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
                DefaultTableModel model = (DefaultTableModel) processingTable.getModel();
                for (int i = 0; i < file.size(); i++) {
                    String[] a = {"P " + String.valueOf(file.get(i).getName())};
                    model.addRow(a);
                }
//***********************************!????????????????
                makeStuffWork();
            }
		});
		
		btnCommencer.setBounds(343, 420, 125, 23);
		contentPane.add(btnCommencer);
		
		//************************retour a la frame principal
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InputPanel pt = new InputPanel();
				pt.setVisible(true);
				setVisible(false);
			}
		});
		btnRetour.setBounds(97, 420, 119, 23);
		contentPane.add(btnRetour);
		
		//**********************************************remplir
		DefaultTableModel model = (DefaultTableModel) waitingTable.getModel();

        for (int i = 0; i < file.size(); i++) {
            String[] a = {"P " + String.valueOf(file.get(i).getName()), String.valueOf(file.get(i).getTempArriver()), String.valueOf(file.get(i).getTempCpu())};
            model.addRow(a);
        }
	}
}
