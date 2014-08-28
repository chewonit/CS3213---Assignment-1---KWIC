package kwic_gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.peer.TextFieldPeer;
import java.util.Vector;

import javafx.util.Pair;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
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
import kwic.Line;

import java.awt.Component;
import java.io.IOException;

import javax.swing.Box;

public class KWIC_GUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNewLabel;

	private static KWIC kwic;
	private JTextField textFieldFilter;
	private JTextField textFieldFile;

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

	private void updateDisplayList(DefaultListModel<Line> listModel,
			Vector<Line> lines) {

		listModel.clear();
		for (int i = 0; i < lines.size(); i++) {
			listModel.addElement(lines.elementAt(i));
		}
	}
	
	private void updateFilterList(DefaultListModel<String> listModel,
			Vector<String> filters) {

		listModel.clear();
		for (int i = 0; i < filters.size(); i++) {
			listModel.addElement(filters.elementAt(i));
		}
	}

	private void addNewLine(JButton btn, DefaultListModel<Line> listModel) {
		String input = textField.getText();
		clearTextField(textField);
		toggleButtonEnabled(btn);

		Vector<Line> lines = kwic.process(input);

		toggleButtonEnabled(btn);
		updateDisplayList(listModel, lines);
	}
	
	private void deleteLine(JButton btn, DefaultListModel<Line> listModel, JList<Line> list) {
		Line line = list.getSelectedValue();
		toggleButtonEnabled(btn);

		Vector<Line> lines = kwic.removeLine(line.getId());

		toggleButtonEnabled(btn);
		updateDisplayList(listModel, lines);
	}
	
	private void addNewFilter(JButton btn, DefaultListModel<String> listModelFilter, DefaultListModel<Line> listModel) {
		String filter = textFieldFilter.getText();
		clearTextField(textFieldFilter);
		toggleButtonEnabled(btn);

		Pair<Vector<String>, Vector<Line>> filtersAndOutput = kwic.addFilter(filter);
		
		toggleButtonEnabled(btn);
		updateFilterList(listModelFilter, filtersAndOutput.getKey());
		updateDisplayList(listModel, filtersAndOutput.getValue());
	}
	
	private void deleteFilter(JButton btn, DefaultListModel<String> listModelFilter, JList<String> list, DefaultListModel<Line> listModel) {
		String filter = list.getSelectedValue();
		toggleButtonEnabled(btn);

		Pair<Vector<String>, Vector<Line>> filtersAndOutput = kwic.removeFilter(filter);

		toggleButtonEnabled(btn);
		updateFilterList(listModelFilter, filtersAndOutput.getKey());
		updateDisplayList(listModel, filtersAndOutput.getValue());
	}
	
	private void processFile(JButton btn, DefaultListModel<Line> listModel) {
		String path = textFieldFile.getText();
		clearTextField(textFieldFile);
		toggleButtonEnabled(btn);

		Vector<Line> lines = new Vector<Line>();
		try {
			lines = kwic.processFile(path);
		} catch (IOException e) {
			textFieldFile.setText("Error encountered when opening file.");
			return;
		}

		toggleButtonEnabled(btn);
		updateDisplayList(listModel, lines);
	}

	/**
	 * Create the frame.
	 */
	public KWIC_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblKwic = new JLabel("KWIC");
		lblKwic.setHorizontalAlignment(SwingConstants.CENTER);
		lblKwic.setFont(new Font("Tahoma", Font.PLAIN, 24));

		JLabel lblFilters = new JLabel("Filters");
		lblFilters.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblInputFileOf = new JLabel("Input File of Lines:");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textFieldFilter = new JTextField();
		textFieldFilter.setColumns(10);
		
		textFieldFile = new JTextField();
		textFieldFile.setColumns(10);
		
		lblNewLabel = new JLabel("Lines");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		final JList<Line> list = new JList<Line>();
		final DefaultListModel<Line> listModel = new DefaultListModel<Line>();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPane_1 = new JScrollPane();
		final JList<String> listFilter = new JList<String>();
		final DefaultListModel<String> listModelFilter = new DefaultListModel<String>();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listFilter.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		final JButton btnAddLine = new JButton("Add");
		final JButton btnAddFilter = new JButton("Add");
		final JButton btnDeleteLines = new JButton("Delete selected lines");
		final JButton btnDeleteFilters = new JButton("Delete selected filters");
		final JButton btnProcessFile = new JButton("Process File");
		
		btnAddLine.setEnabled(false);
		btnAddFilter.setEnabled(false);
		btnDeleteLines.setEnabled(false);
		btnDeleteFilters.setEnabled(false);
		btnProcessFile.setEnabled(false);
		
		scrollPane.setViewportView(list);
		list.setModel(listModel);
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if (!arg0.getValueIsAdjusting()) {
					if (list.getSelectedValue() != null) {
						btnDeleteLines.setEnabled(true);
					} else if (list.getSelectedValue() == null) {
						btnDeleteLines.setEnabled(false);
					}
				}
			}
		});
		
		scrollPane_1.setViewportView(listFilter);
		listFilter.setModel(listModelFilter);
		listFilter.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if (!arg0.getValueIsAdjusting()) {
					if (listFilter.getSelectedValue() != null) {
						btnDeleteFilters.setEnabled(true);
					} else if (listFilter.getSelectedValue() == null) {
						btnDeleteFilters.setEnabled(false);
					}
				}
			}
		});

		textField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				updateBtnStatus();
			}

			public void removeUpdate(DocumentEvent e) {
				updateBtnStatus();
			}

			public void insertUpdate(DocumentEvent e) {
				updateBtnStatus();
			}

			public void updateBtnStatus() {
				if (textField.getText().length() <= 0) {
					btnAddLine.setEnabled(false);
				} else if (textField.getText().length() > 0) {
					btnAddLine.setEnabled(true);
				}
			}
		});
		
		textFieldFilter.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				updateBtnStatus();
			}

			public void removeUpdate(DocumentEvent e) {
				updateBtnStatus();
			}

			public void insertUpdate(DocumentEvent e) {
				updateBtnStatus();
			}

			public void updateBtnStatus() {
				if (textFieldFilter.getText().length() <= 0) {
					btnAddFilter.setEnabled(false);
				} else if (textFieldFilter.getText().length() > 0) {
					btnAddFilter.setEnabled(true);
				}
			}
		});
		
		textFieldFile.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				updateBtnStatus();
			}

			public void removeUpdate(DocumentEvent e) {
				updateBtnStatus();
			}

			public void insertUpdate(DocumentEvent e) {
				updateBtnStatus();
			}

			public void updateBtnStatus() {
				if (textFieldFile.getText().length() <= 0) {
					btnProcessFile.setEnabled(false);
				} else if (textFieldFile.getText().length() > 0) {
					btnProcessFile.setEnabled(true);
				}
			}
		});

		btnDeleteLines.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteLine(btnDeleteLines, listModel, list);
			}
		});

		btnAddLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addNewLine(btnAddLine, listModel);
			}
		});

		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNewLine(btnAddLine, listModel);
			}
		});
		
		btnDeleteFilters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteFilter(btnDeleteFilters, listModelFilter, listFilter, listModel);
			}
		});
		
		btnAddFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNewFilter(btnAddFilter, listModelFilter, listModel);
			}
		});
		
		textFieldFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNewFilter(btnAddFilter, listModelFilter, listModel);
			}
		});
		
		btnProcessFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				processFile(btnProcessFile, listModel);
			}
		});
		
		textFieldFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processFile(btnProcessFile, listModel);
			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblKwic, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(textField)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnAddLine))
								.addComponent(scrollPane, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 421, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnDeleteLines, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnDeleteFilters, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
									.addGap(94)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(scrollPane_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
										.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
											.addComponent(textFieldFilter, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnAddFilter, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
										.addComponent(lblFilters, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblInputFileOf)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(textFieldFile, GroupLayout.PREFERRED_SIZE, 437, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnProcessFile, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblKwic)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblFilters))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAddLine)
						.addComponent(textFieldFilter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAddFilter))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(scrollPane_1)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnDeleteLines)
						.addComponent(btnDeleteFilters))
					.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldFile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblInputFileOf)
						.addComponent(btnProcessFile))
					.addGap(25))
		);
		
		contentPane.setLayout(gl_contentPane);

	}
}
