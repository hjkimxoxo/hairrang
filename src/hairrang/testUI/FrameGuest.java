package hairrang.testUI;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import hairrang.dto.Guest;
import hairrang.service.GuestService;

public class FrameGuest extends JFrame {

	private GuestService gService;
	private JPanel contentPane;
	private ArrayList<Guest> guestList;
	private GuestManagementTable table;
	private GuestManagementPanel pGuest;
	private JPanel pBtn;
	private JButton btnAdd;
	private JButton btnCancel;
	private JPanel pTable;
	private JScrollPane scrollPane;
	private int curr;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameGuest frame = new FrameGuest();
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
	public FrameGuest() {
		
		
		gService = new GuestService();
		guestList = (ArrayList<Guest>) gService.getGuestList();
		curr = gService.getGuestCurrVal();
		System.out.println("curr: " + curr);
		initComponent();
	}

	private void initComponent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 739, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		pGuest = new GuestManagementPanel();
		pGuest.setBounds(5, 5, 713, 179);
		contentPane.add(pGuest);
		pGuest.setLayout(null);
		
		
		//////////////////////////////////////////////
		pGuest.setTfJoinDay();
		pGuest.setTfNo(curr);
		//////////////////////////////////////////////

		
		pBtn = new JPanel();
		pBtn.setBounds(5, 184, 713, 33);
		contentPane.add(pBtn);
		pBtn.setLayout(null);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				Guest addGuest;
				try {
					addGuest = pGuest.getGuest();
					System.out.println(addGuest);
					gService.addGuest(addGuest);
					table.addRow(addGuest);
					
					curr++;
					
					JOptionPane.showMessageDialog(null, String.format("%s님이 추가되었습니다.",addGuest.getGuestName()));
					
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				pGuest.clearTf();
				pGuest.setTfNo(curr);
			}
		});
		btnAdd.setBounds(362, 0, 97, 23);
		pBtn.add(btnAdd);
		
		btnCancel = new JButton("취소");
		btnCancel.setBounds(471, 0, 97, 23);
		pBtn.add(btnCancel);
		
		pTable = new JPanel();
		pTable.setBounds(5, 217, 713, 278);
		contentPane.add(pTable);
		pTable.setLayout(new GridLayout(1, 0, 0, 0));
		
		scrollPane = new JScrollPane();
		pTable.add(scrollPane);
		
		table = new GuestManagementTable();
		scrollPane.setViewportView(table);
		
		
		table.setItems(guestList);
	}
	
	
	
	public void actionPerformed(ActionEvent e) throws ParseException {
		if(e.getSource() == btnAdd) {
			if(e.getActionCommand().contentEquals("추가")) 
				btnAddActionPerformed(e);
			if (e.getActionCommand().equals("수정")) 
				btnUpdateActionPerformed();
		}
		
		if(e.getSource() == btnCancel) {
			btnCancelActionPerformed(e);
		}
	}

	//고객리턴
	private Guest getSelectedGuest() {
		int selectedRow = table.getSelectedRow();
		return guestList.get(selectedRow);
	}

	
	
	private void btnAddActionPerformed(ActionEvent e) throws ParseException {
		Guest addGuest = pGuest.getGuest();
		System.out.println(addGuest);
//		guestList.add(addGuest);
//		table.addRow(addGuest);
//		JOptionPane.showMessageDialog(null, String.format("%s님이 추가되었습니다.",addGuest.getGuestName()));
//		pGuest.clearTf();
		
	}

	private void btnUpdateActionPerformed() {
		// TODO Auto-generated method stub
		
	}
	
	private void btnCancelActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
