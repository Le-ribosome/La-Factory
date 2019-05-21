package fr.projet.lafactory.model.view;

public class JsonViews {

	// --- RH ---

	public static class User {
	}

	public static class Gestionnaire {
	}

	public static class Formateur {
	}

	public static class Stagiaire {
	}

	public static class StagiaireAvecFormation {
	}

	public static class StagiaireAvecOrdinateur {
	}

	public static class Technicien {
	}

	// --- Materiel ---

	public static class Materiel {

	}

	public static class Ordinateur extends Materiel {
	}

	public static class OrdinateurAvecStagiaire extends Materiel {
	}

	public static class Salle extends Materiel {
	}

	public static class SalleAvecVideoprojecteur extends Salle {
	}

	public static class Videoprojecteur extends Materiel {
	}

	public static class VideoprojecteurAvecSalle extends Videoprojecteur{
	}

	// --- Formation ---
	
	
	public static class Enseignement{
	}
	
	public static class EnseignementAvecFormateur extends Enseignement {
	}

	public static class EnseignementAvecMatiere extends Enseignement {
	}

	
	public static class Formation {
	}

	public static class FormationAvecSalle extends Formation {
	}

	public static class FormationAvecGestionnaire extends Formation {
	}
	
	public static class FormationAvecModule extends Formation {
	}
	
	public static class FormationAvecStagiaire extends Formation {
	}
	
	public static class Indisponibilite{
		
	}
	
	public static class IndisponibiliteAvecFormateur extends Indisponibilite {
	}

	public static class Matiere {
	}

	public static class MatiereAvecEnseignement extends Matiere{
	}

	public static class Module {
	}

	public static class ModuleAvecFormation extends Module {
	}

	public static class ModuleAvecFormateur extends Module {
	}

	public static class ModuleAvecMatiere extends Module {
	}

}
