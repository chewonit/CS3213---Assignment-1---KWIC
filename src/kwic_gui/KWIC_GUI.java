package kwic_gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import kwic.KWIC;

public class KWIC_GUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNewLabel;

	private static KWIC kwic;

	/**
	 * Launch the application.
	 */
	public void run(KWIC kwic) {
		this.kwic = kwic;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KWIC_GUI frame = new KWIC_GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void clearTextField(JTextField textField) {
		textField.setText("");
	}

	private void toggleButtonEnabled(JButton btn) {
		if (btn.isEnabled()) {
			btn.setEnabled(false);
		} else {
			btn.setEnabled(true);
		}
	}

	private void updateDisplayList(DefaultListModel<String> listModel) {

		listModel.clear();
		listModel.addElement("asdsad");
		listModel.addElement("helloworld");

	}
	
	private void addNewLine(JButton btn, DefaultListModel<String> listModel) {
		String input = textField.getText();
		clearTextField(textField);
		toggleButtonEnabled(btn);

		kwic.process(input);

		toggleButtonEnabled(btn);
		updateDisplayList(listModel);
		
	}

	/**
	 * Create the frame.
	 */
	public KWIC_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblKwic = new JLabel("KWIC");
		lblKwic.setHorizontalAlignment(SwingConstants.CENTER);
		lblKwic.setFont(new Font("Tahoma", Font.PLAIN, 24));

		textField = new JTextField();
		textField.setColumns(10);

		lblNewLabel = new JLabel("Line:");

		JScrollPane scrollPane = new JScrollPane();
		final JList<String> list = new JList<String>();
		final DefaultListModel<String> listModel = new DefaultListModel<String>();

		final JButton btnAdd = new JButton("Add");
		final JButton btnDeleteSelected = new JButton("Delete selected lines");
		btnAdd.setEnabled(false);
		btnDeleteSelected.setEnabled(false);

		scrollPane.setViewportView(list);
		list.setModel(listModel);
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if (!arg0.getValueIsAdjusting()) {
					if (list.getSelectedValue() != null) {
						System.out.println(list.getSelectedValue().toString());
						btnDeleteSelected.setEnabled(true);
					} else if (list.getSelectedValue() == null) {
						btnDeleteSelected.setEnabled(false);
					}
				}
			}
		});

		textField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				updateAddBtnStatus();
			}

			public void removeUpdate(DocumentEvent e) {
				updateAddBtnStatus();
			}

			public void insertUpdate(DocumentEvent e) {
				updateAddBtnStatus();
			}

			public void updateAddBtnStatus() {
				if ( textField.getText().length() <= 0 ) {
					btnAdd.setEnabled(false);
				} else if ( textField.getText().length() > 0 ) {
					btnAdd.setEnabled(true);
				}
			}
		});
		
		btnDeleteSelected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(list.getSelectedValue());
				System.out.println(list.getSelectedIndex());

				updateDisplayList(listModel);
			}
		});

		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addNewLine(btnAdd, listModel);
			}
		});
		
		textField.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	addNewLine(btnAdd, listModel);
		    }
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																lblKwic,
																GroupLayout.DEFAULT_SIZE,
																738,
																Short.MAX_VALUE)
														.addComponent(
																scrollPane,
																GroupLayout.DEFAULT_SIZE,
																738,
																Short.MAX_VALUE)
														.addGroup(
																Alignment.TRAILING,
																gl_contentPane
																		.createSequentialGroup()
																		.addComponent(
																				lblNewLabel)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				textField,
																				GroupLayout.DEFAULT_SIZE,
																				569,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				btnAdd))
														.addComponent(
																btnDeleteSelected,
																Alignment.TRAILING))
										.addContainerGap()));
		gl_contentPane
				.setVerticalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(lblKwic)
										.addGap(30)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblNewLabel)
														.addComponent(
																textField,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(btnAdd))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(scrollPane,
												GroupLayout.PREFERRED_SIZE,
												322, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(btnDeleteSelected)
										.addContainerGap(61, Short.MAX_VALUE)));

		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		contentPane.setLayout(gl_contentPane);
	}
}
