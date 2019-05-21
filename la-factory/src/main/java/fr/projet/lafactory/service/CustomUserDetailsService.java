package fr.projet.lafactory.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.projet.lafactory.dao.IDAOPersonne;
import fr.projet.lafactory.model.Personne;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	IDAOPersonne daoPersonne;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Personne> opt=daoPersonne.findByIdWithDroits(email);
		if(opt.isPresent()) {
			return new CustomUserDetails(opt.get());
		}
		throw new UsernameNotFoundException("utilisateur inconnu");
	}

}
