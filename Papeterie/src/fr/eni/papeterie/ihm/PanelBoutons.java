package fr.eni.papeterie.ihm;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelBoutons extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<IPanelBoutonsObserver> observateurs;
	
	private ImageIcon imgPrecedent = new ImageIcon(this.getClass().getResource("resources/Back24.gif"));
	private ImageIcon imgNouveau = new ImageIcon(this.getClass().getResource("resources/New24.gif"));
	private ImageIcon imgSauvegarder = new ImageIcon(this.getClass().getResource("resources/Save24.gif"));
	private ImageIcon imgSupprimer = new ImageIcon(this.getClass().getResource("resources/Delete24.gif"));
	private ImageIcon imgSuivant = new ImageIcon(this.getClass().getResource("resources/Forward24.gif"));

	private JButton btnPrecedent;
	private JButton btnNouveau;
	private JButton btnEnregistrer;
	private JButton btnSupprimer;
	private JButton btnSuivant;

	public PanelBoutons() {
		setLayout(new FlowLayout());
		
		add(getBtnPrecedent());
		add(getBtnNouveau());
		add(getBtnEnregistrer());
		add(getBtnSupprimer());
		add(getBtnSuivant());
		
		observateurs = new ArrayList<IPanelBoutonsObserver>();
	}
	
	
	public void addPanelBoutonObserver(IPanelBoutonsObserver observer){
		observateurs.add(observer);
	}
	
	public JButton getBtnPrecedent() {
		if(btnPrecedent ==null){
			btnPrecedent = new JButton();
			btnPrecedent.setIcon(imgPrecedent);
			btnPrecedent.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					
					for(IPanelBoutonsObserver observateur: observateurs){
						observateur.precedent();
					}
				}

			});
		}
		return btnPrecedent;
	}

	public JButton getBtnNouveau() {
		if(btnNouveau ==null){
			btnNouveau = new JButton();
			btnNouveau.setIcon(imgNouveau);
			btnNouveau.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					for(IPanelBoutonsObserver p: observateurs){
						p.nouveau();
					}
				}

			});
		}

		return btnNouveau;
	}

	public JButton getBtnEnregistrer() {
		if(btnEnregistrer ==null){
			btnEnregistrer = new JButton();
			btnEnregistrer.setIcon(imgSauvegarder);
			btnEnregistrer.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {

					for(IPanelBoutonsObserver p: observateurs){
						p.enregistrer();
					}
					
				}

			});
		}
		return btnEnregistrer;
	}

	public JButton getBtnSupprimer() {
		if(btnSupprimer ==null){
			btnSupprimer = new JButton();
			btnSupprimer.setIcon(imgSupprimer);
			btnSupprimer.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					for(IPanelBoutonsObserver p: observateurs){
						p.supprimer();
					}

					
				}

			});
		}
		return btnSupprimer;
	}

	public JButton getBtnSuivant() {
		if(btnSuivant ==null){
			btnSuivant = new JButton();
			btnSuivant.setIcon(imgSuivant);
			btnSuivant.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					for(IPanelBoutonsObserver p: observateurs){
						p.suivant();
					}
				}

			});
		}
		return btnSuivant;
	}
}
