package fr.projet.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import fr.example.formation.model.UserRole;
import fr.example.formation.model.Utilisateur;

public class CustomUserDetails implements UserDetails{

	private Personne personne;
	
	public CustomUserDetails(Personne u) {
		this.personne=u;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (PersonneDroit role : personne.getDroits()) {
			authorities.add(new SimpleGrantedAuthority(role.getRole().toString()));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return personne.getPassword();
	}

	@Override
	public String getUsername() {
		return personne.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return personne.isEnable();
	}

}
