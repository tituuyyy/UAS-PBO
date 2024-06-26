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

public class PeminjamanRuang {
    private String ruang;
    private String pengguna;
    private String idPmjm;
    private String tanggal;
    private String kegiatan;
    private String status;
    
    public PeminjamanRuang(){
        
    }
    
    public String getRuang(){
        return ruang;
    }
    
    public void setRuang(String ruang){
        this.ruang = ruang;
    }
    
    public String getPengguna(){
        return pengguna;
    }
    
    public void setPengguna(String pengguna){
        this.pengguna = pengguna;
    }
    
    public String getIdPmjm(){
        return idPmjm;
    }
    
    public void setIdPmjm(String id){
        this.idPmjm = id;
    }
    
    public String getTanggal(){
        return tanggal;
    }
    
    public void setTanggal(String tanggal){
        this.tanggal = tanggal;
    }
    
    public String getKegiatan(){
        return kegiatan;
    }
    
    public void setKegiatan(String kegiatan){
        this.kegiatan = kegiatan;
    }
    
    public String getStatus(){
        return status;
    }
    
    public void setStatus(String status){
        this.status = status;
    }
}

