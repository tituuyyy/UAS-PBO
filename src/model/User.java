package model;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.util.Random;

/**
 *
 * @author TITHA
 */

public class User {
    private String idUser;
    private String nama;
    private String email;
    private String noTel;
    private String password;
    private String tipe;
    private String role;
    
    public User(){
    
    }
    
    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser =  idUser;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoTel() {
        return noTel;
    }

    public void setNoTel(String noTel) {
        this.noTel = noTel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getTipe(){
    String subId = this.idUser.substring(0,2);
    if(subId.equals("20")){
        this.tipe="admin";
    }
    else{
        this.tipe="user";
    }
    setTipe(subId);
    return this.tipe;
}
    
    public void setTipe(String tipe){
        this.tipe = tipe;
    }
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
