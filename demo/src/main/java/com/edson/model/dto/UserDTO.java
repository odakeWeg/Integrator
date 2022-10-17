package com.edson.model.dto;

import com.edson.persistence.model.User;
import com.edson.persistence.repository.UserRepository;
import com.edson.util.ApplicationSetup;

public class UserDTO {

    private UserRepository userRepository;

    private String cadastro;
    private String password;
    private String perfil;

    public UserDTO(String cadastro, String password, String perfil) {
        userRepository = ApplicationSetup.getApplicationContext().getBean(UserRepository.class);

        this.cadastro = cadastro;
        this.password = password;
        this.perfil = perfil;
    }

    public UserDTO(String cadastro, String password) {
        userRepository = ApplicationSetup.getApplicationContext().getBean(UserRepository.class);

        this.cadastro = cadastro;
        this.password = password;
    }

    public UserDTO(User user) {
        userRepository = ApplicationSetup.getApplicationContext().getBean(UserRepository.class);

        this.cadastro = user.getCadastro();
        this.password = user.getPassword();
        this.perfil = user.getPerfil();
    }

    public boolean isUserAuthenticated(User user) {
        if (user != null && user.getPassword().equals(this.password)) {
            this.perfil = user.getPerfil();
            return true;
        } else {
            return false;
        }
    }

    public void save() {
        User user = new User(cadastro, password, perfil);
        userRepository.save(user);
    }

    public String getCadastro() {
        return this.cadastro;
    }

    public void setCadastro(String cadastro) {
        this.cadastro = cadastro;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPerfil() {
        return this.perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public UserRepository getUserRepository() {
        return this.userRepository;
    }

}
