package com.ridolphiconsutoria.timesheetv1.usuario;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class UserModel implements UserDetails, Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id", nullable = false, updatable = false)
    private String id;

    @Column(name = "username", length = 100, nullable = false, unique = true)	
    private String username;

    @Column(name = "password", length = 100, nullable = false)	
	private String password;

    @Column(name = "email", length = 100, nullable = false, unique = true )	
    private String email;

    @Column(name = "nome", length = 250, nullable = false)	
    private String nome;

    @Column(name = "role", length = 100, nullable = false)	
    private Role role;

    public UserModel() {
    
    }

   

    public UserModel(String id, String username, String password, String email, String nome, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.nome = nome;
        this.role = role;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    
    public String getPassword() {
        return password;
    }



    public void setPassword(String password) {
        this.password = password;
    }



    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", nome="
                + nome + ", role=" + role + "]";
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       
        if(this.role == Role.GERENTE) return List.of(new SimpleGrantedAuthority("ROLE_GERENTE"), new SimpleGrantedAuthority("ROLE_COORDENADOR"), new SimpleGrantedAuthority("ROLE_CONSULTOR"));
        if(this.role == Role.COORDENADOR) return List.of(new SimpleGrantedAuthority("ROLE_COORDENADOR"), new SimpleGrantedAuthority("ROLE_CONSULTOR"));
        else return List.of(new SimpleGrantedAuthority("ROLE_CONSULTOR"));
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
        return true;
    }
    



}
