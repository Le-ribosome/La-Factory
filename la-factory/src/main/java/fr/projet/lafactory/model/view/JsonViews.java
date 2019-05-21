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
	
	public static class Ordinateur extends Materiel{
	}
	
	public static class OrdinateurAvecStagiaire extends Materiel{
	}

	public static class Salle extends Materiel{
	}

	public static class SalleAvecVideoprojecteur extends Materiel{
	}
	
	
	public static class Videoprojecteur extends Materiel{
	}
	
	public static class VideoprojecteurAvecSalle {
	}
	
	
	// --- Formation ---
	
	public static class Matiere {
	}
	
	public static class MatiereAvecEnseignement {
	}
	
	public static class Module {
	}
	
	public static class ModuleAvecFormation {
	}
	
	public static class ModuleAvecFormateur {
	}
	
	public static class ModuleAvecMatiere {
	}

	
}
