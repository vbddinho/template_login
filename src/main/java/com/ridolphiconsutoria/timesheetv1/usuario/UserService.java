package com.ridolphiconsutoria.timesheetv1.usuario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;



@Service
public class UserService  {
    private Logger logger = LoggerFactory.getLogger( UserService.class );


    @Autowired
    private UserRepository userRepository;


    @PostConstruct
    public void init() {
        try {
            addUser( "admin" , "1234", Role.GERENTE, "Administrador","administrador@gmail.com" );
            logger.info("Usuario ADMIN CRIADO: ");
     } catch ( Exception e ) {  	}


    }

    public UserModel addUser( String username, String password, Role role, String nome, String email ) throws Exception {
        UserModel newUser = new UserModel();
        newUser.setUsername( username );
        newUser.setEmail( email );
        newUser.setRole( role );
        newUser.setNome(nome);
        newUser.setPassword( new BCryptPasswordEncoder( 5 ).encode(password) );
        UserModel user = userRepository.save(newUser);
        return user;
    }

}
