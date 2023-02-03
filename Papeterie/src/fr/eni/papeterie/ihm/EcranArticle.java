package fr.eni.papeterie.ihm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EcranArticle extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int TEXT_FIELD_SIZE = 25;
	
	private JLabel lblReference, lblDesignation, lblMarque, lblStock, lblPrix, lblType, lblGrammage, lblCouleur;
	private JTextField txtReference, txtDesignation, txtMarque, txtStock, txtPrix;

	public EcranArticle() {
		this.setTitle("Article");
		this.setSize(400, 400);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initIHM();
	}

	private void initIHM() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(5, 5, 5, 5);
		
		gbc.gridy = 0;
		gbc.gridx = 0;
		panel.add(getLblReference(), gbc);
		gbc.gridx = 1;
		panel.add(getTxtReference(), gbc);
		
		gbc.gridy = 1;
		gbc.gridx = 0;
		panel.add(getLblDesignation(), gbc);
		gbc.gridx = 1;
		panel.add(getTxtDesignation(), gbc);
		
		gbc.gridy = 2;
		gbc.gridx = 0;
		panel.add(getLblMarque(), gbc);
		gbc.gridx = 1;
		panel.add(getTxtMarque(), gbc);
		
		gbc.gridy = 3;
		gbc.gridx = 0;
		panel.add(getLblStock(), gbc);
		gbc.gridx = 1;
		panel.add(getTxtStock(), gbc);
		
		gbc.gridy = 4;
		gbc.gridx = 0;
		panel.add(getLblPrix(), gbc);
		gbc.gridx = 1;
		panel.add(getTxtPrix(), gbc);
		
		this.setContentPane(panel);
	}

	public JLabel getLblReference() {
		if(lblReference == null)
			lblReference = new JLabel("Référence");
		return lblReference;
	}

	public JLabel getLblDesignation() {
		if(lblDesignation == null)
			lblDesignation = new JLabel("Désignation");
		return lblDesignation;
	}

	public JLabel getLblMarque() {
		if(lblMarque == null)
			lblMarque = new JLabel("Marque");
		return lblMarque;
	}

	public JLabel getLblStock() {
		if(lblStock == null)
			lblStock = new JLabel("Designation");
		return lblStock;
	}

	public JLabel getLblPrix() {
		if(lblPrix == null)
			lblPrix = new JLabel("Prix");
		return lblPrix;
	}

	public JLabel getLblType() {
		if(lblType == null)
			lblType = new JLabel("Type");
		return lblType;
	}

	public JLabel getLblGrammage() {
		if(lblGrammage == null)
			lblGrammage = new JLabel("Grammage");
		return lblGrammage;
	}

	public JLabel getLblCouleur() {
		if(lblCouleur == null)
			lblCouleur = new JLabel("Couleur");
		return lblCouleur;
	}

	public JTextField getTxtReference() {
		if(txtReference == null)
			txtReference = new JTextField(TEXT_FIELD_SIZE);
		return txtReference;
	}

	public JTextField getTxtDesignation() {
		if(txtDesignation == null)
			txtDesignation = new JTextField(TEXT_FIELD_SIZE);
		return txtDesignation;
	}

	public JTextField getTxtMarque() {
		if(txtMarque == null)
			txtMarque = new JTextField(TEXT_FIELD_SIZE);
		return txtMarque;
	}
	
	public JTextField getTxtStock() {
		if(txtStock == null)
			txtStock = new JTextField(TEXT_FIELD_SIZE);
		return txtStock;
	}

	public JTextField getTxtPrix() {
		if(txtPrix == null)
			txtPrix = new JTextField(TEXT_FIELD_SIZE);
		return txtPrix;
	}
}
