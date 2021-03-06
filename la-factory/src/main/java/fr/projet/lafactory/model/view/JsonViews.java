package fr.projet.lafactory.model.view;

public class JsonViews {

	// --- RH ---

	public static interface User {
	}

	public static interface Gestionnaire extends User, Formation{
	}

	public static interface Formateur extends User{
	}

	public static interface Stagiaire extends User, Materiel, Formation {
	}

	public static interface Technicien extends User{
	}
	
	public static interface Administrateur extends User{
	}

	
	
	// --- Materiel ---
	
	public static interface Materiel {

	}
	
	public static interface Ordinateur extends Materiel {
	}

	public static interface OrdinateurAvecStagiaire extends Ordinateur {
	}

	public static interface Salle extends Materiel {
	}

	public static interface SalleAvecVideoprojecteur extends Salle {
	}
	
	public static interface SalleAvecFormations extends Salle {
	}

	public static interface Videoprojecteur extends Materiel {
	}

	public static interface VideoprojecteurAvecSalle extends Videoprojecteur{
	}

	// --- Formation ---
	
	
	public static interface Enseignement{
	}
	
	public static interface EnseignementAvecFormateur extends Enseignement {
	}

	public static interface EnseignementAvecMatiere extends Enseignement {
	}

	
	public static interface Formation {
	}

	public static interface FormationAvecSalle extends Formation, Salle {
	}

	public static interface FormationAvecGestionnaire extends Formation, User {
	}
	
	public static interface FormationAvecModule extends Formation, Module {
	}
	
	public static interface FormationAvecModuleEtSalle extends FormationAvecSalle, FormationAvecModule {
	}
	
	public static interface FormationAvecStagiaire extends Formation, Stagiaire {
	}
	
	public static interface Indisponibilite{
		
	}
	
	public static interface IndisponibiliteAvecFormateur extends Indisponibilite {
	}

	public static interface Matiere {
	}

	public static interface MatiereAvecEnseignement extends Matiere{
	}

	public static interface Module {
	}

	public static interface ModuleAvecFormation extends Module, Formation {
	}

	public static interface ModuleAvecFormateur extends Module, Formateur {
	}

	public static interface ModuleAvecMatiere extends Module, Matiere {
	}
	
	public static interface ModuleAvecMatiereEtFormateur extends ModuleAvecFormateur, ModuleAvecMatiere {
	}
	
	public static interface ModuleAvecMatiereEtFormationEtFormateur extends ModuleAvecFormation, ModuleAvecFormateur, ModuleAvecMatiere {
	}

}
