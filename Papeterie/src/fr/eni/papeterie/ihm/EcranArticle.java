package fr.eni.papeterie.ihm;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;

public class EcranArticle extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int TEXT_FIELD_SIZE = 25;
	
	private JLabel lblReference, lblDesignation, lblMarque, lblStock, lblPrix, lblType, lblGrammage, lblCouleur;
	private JTextField txtReference, txtDesignation, txtMarque, txtStock, txtPrix;
	private JRadioButton radioRamette, radioStylo;
	private JCheckBox check80, check100;
	private JComboBox<String> cboCouleur;
	private JPanel panelType, panelGrammage, panelBouton;
	private JButton btnPrecedent, btnNouveau, btnSauvegarder, btnSupprimer, btnSuivant;
	
	private ImageIcon imgPrecedent = new ImageIcon(this.getClass().getResource("resources/Back24.gif"));
	private ImageIcon imgNouveau = new ImageIcon(this.getClass().getResource("resources/New24.gif"));
	private ImageIcon imgSauvegarder = new ImageIcon(this.getClass().getResource("resources/Save24.gif"));
	private ImageIcon imgSupprimer = new ImageIcon(this.getClass().getResource("resources/Delete24.gif"));
	private ImageIcon imgSuivant = new ImageIcon(this.getClass().getResource("resources/Forward24.gif"));
	
	private Integer idCourant;

	public EcranArticle() {
		this.setLocationRelativeTo(null);
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
		
		gbc.gridy = 5;
		gbc.gridx = 0;
		panel.add(getLblType(), gbc);
		gbc.gridx = 1;
		panel.add(getPanelType(), gbc);
		
		gbc.gridy = 6;
		gbc.gridx = 0;
		panel.add(getLblGrammage(), gbc);
		gbc.gridx = 1;
		panel.add(getPanelGrammage(), gbc);
		
		gbc.gridy = 7;
		gbc.gridx = 0;
		panel.add(getLblCouleur(), gbc);
		gbc.gridx = 1;
		panel.add(getCboCouleur(), gbc);
		
		gbc.gridy = 8;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		panel.add(getPanelBouton(), gbc);
		
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
	
	public JRadioButton getRadioRamette() {
		if(radioRamette == null)
			radioRamette = new JRadioButton("Ramette");
		return radioRamette;
	}

	public JRadioButton getRadioStylo() {
		if(radioStylo == null)
			radioStylo = new JRadioButton("Stylo");
		return radioStylo;
	}

	public JCheckBox getCheck80() {
		if(check80 == null)
			check80 = new JCheckBox("80 grammes");
		return check80;
	}

	public JCheckBox getCheck100() {
		if(check100 == null)
			check100 = new JCheckBox("100 grammes");
		return check100;
	}

	public JComboBox<String> getCboCouleur() {
		if(cboCouleur == null) {
			String[] couleurs = {"noir", "bleu", "rouge", "vert"};
			cboCouleur = new JComboBox<String>(couleurs);
		}
		return cboCouleur;
	}

	public JPanel getPanelType() {
		if(panelType == null) {
			panelType = new JPanel();
			panelType.setLayout(new BoxLayout(panelType, BoxLayout.Y_AXIS));
			panelType.add(getRadioRamette());
			panelType.add(getRadioStylo());
			ButtonGroup bg = new ButtonGroup();
			bg.add(getRadioRamette());
			bg.add(getRadioStylo());
		}
		return panelType;
	}

	public JPanel getPanelGrammage() {
		if(panelGrammage == null) {
			panelGrammage = new JPanel();
			panelGrammage.setLayout(new BoxLayout(panelGrammage, BoxLayout.Y_AXIS));
			panelGrammage.add(getCheck80());
			panelGrammage.add(getCheck100());
			ButtonGroup bg = new ButtonGroup();
			bg.add(getCheck80());
			bg.add(getCheck100());
		}
		return panelGrammage;
	}
	
	public JPanel getPanelBouton() {
		if(panelBouton == null) {
			panelBouton = new JPanel();
			panelBouton.setLayout(new FlowLayout());
			panelBouton.add(getBtnPrecedent());
			panelBouton.add(getBtnNouveau());
			panelBouton.add(getBtnSauvegarder());
			panelBouton.add(getBtnSupprimer());
			panelBouton.add(getBtnSuivant());
		}
		return panelBouton;
	}

	public JButton getBtnPrecedent() {
		if(btnPrecedent == null) {
			btnPrecedent = new JButton();
			btnPrecedent.setIcon(imgPrecedent);
			btnPrecedent.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					ArticleController.get().precedent();
				}
			});
		}
		return btnPrecedent;
	}

	public JButton getBtnNouveau() {
		if(btnNouveau == null) {
			btnNouveau = new JButton();
			btnNouveau.setIcon(imgNouveau);
			btnNouveau.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					ArticleController.get().nouveau();
				}
			});
		}
		return btnNouveau;
	}

	public JButton getBtnSauvegarder() {
		if(btnSauvegarder == null) {
			btnSauvegarder = new JButton();
			btnSauvegarder.setIcon(imgSauvegarder);
			btnSauvegarder.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					ArticleController.get().sauvegarder();
				}
			});
		}
		return btnSauvegarder;
	}

	public JButton getBtnSupprimer() {
		if(btnSupprimer == null) {
			btnSupprimer = new JButton();
			btnSupprimer.setIcon(imgSupprimer);
			btnSupprimer.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					ArticleController.get().supprimer();
				}
			});
		}
		return btnSupprimer;
	}

	public JButton getBtnSuivant() {
		if(btnSuivant == null) {
			btnSuivant = new JButton();
			btnSuivant.setIcon(imgSuivant);
			btnSuivant.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					ArticleController.get().suivant();
				}
			});
		}
		return btnSuivant;
	}
	
	public void afficherNouveau() {
		// Par défaut un article est une rammette
		Ramette a = new Ramette(null, "", "", "", 0.0f, 0, 0);

		afficherArticle(a); 

	}
	
	public void afficherArticle(Article a) {

		idCourant = a.getIdArticle();
		// Autres caractéristiques de l'article
		getTxtReference().setText(a.getReference() + "");
		getTxtMarque().setText(a.getMarque() + "");
		getTxtDesignation().setText(a.getDesignation() + "");
		getTxtPrix().setText(String.valueOf(a.getPrixUnitaire()) + "");
		getTxtStock().setText(a.getQteStock() + "");

		if (a.getClass().equals(Stylo.class)) {
			// Cas du stylo
			// sélectionner le bouton radio correspondant
			getRadioStylo().setSelected(true);
			// activer le choix des couleurs
			getCboCouleur().setEnabled(true);
			// Sélectionner la bonne couleur
			getCboCouleur().setSelectedItem(((Stylo) a).getCouleur());
			// Désactiver les cases à cocher
			getCheck80().setEnabled(false);
			getCheck100().setEnabled(false);
		} else {
			// Cas de la ramette
			// activer le bouton radio
			getRadioRamette().setSelected(true);
			// activer les cases à cocher
			getCheck80().setEnabled(true);
			getCheck100().setEnabled(true);
			// Papier de 80g par défaut
			getCheck80().setSelected(((Ramette) a).getGrammage() == 80);
			getCheck100().setSelected(((Ramette) a).getGrammage() == 100);
			// Désactiver les champs inutiles
			getCboCouleur().setSelectedItem(null);
			getCboCouleur().setEnabled(false);
		}
		getRadioStylo().setEnabled(a.getIdArticle() == null);
		getRadioRamette().setEnabled(a.getIdArticle() == null);

	}

	public void precedent() {
		ArticleController.get().precedent();

	}

	public void suivant() {
		ArticleController.get().suivant();

	}

	public void nouveau() {
		ArticleController.get().nouveau();

	}

	public void enregistrer() {
		ArticleController.get().sauvegarder();

	}

	public void supprimer() {
		ArticleController.get().supprimer();

	}
	
	public Article getArticle() {
		Article article=null;
		if(getRadioStylo().isSelected()){
			article = new Stylo();
		}else{
			article = new Ramette();
		}
		try {
			article.setIdArticle(idCourant);
			article.setReference( getTxtReference().getText());
			article.setMarque(getTxtMarque().getText());
			article.setDesignation( getTxtDesignation().getText());
			article.setPrixUnitaire(Float.parseFloat(getTxtPrix().getText()));
			article.setQteStock(Integer.parseInt(getTxtStock().getText()));
			if (getCboCouleur().isEnabled()) {
				((Stylo)article).setCouleur( (String) getCboCouleur().getSelectedItem());
			} else {
				((Ramette) article).setGrammage(getCheck80().isSelected()?80:100);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return article;
	}
	
	public void infoErreur(String msg) {
		JOptionPane.showMessageDialog(EcranArticle.this, msg, "", JOptionPane.ERROR_MESSAGE);
	}
}
