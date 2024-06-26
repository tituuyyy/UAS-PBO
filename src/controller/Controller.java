/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author TITHA
 */

import model.Database;
import model.PeminjamanRuang;
import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Controller {

    private Database database;

    public Controller() {
        this.database = Database.getInstance();
    }

    public void createUser(User user) throws SQLException {
        database.insertUser(user);
    }

    public User loginUser(String username, String password) throws SQLException {
        ResultSet rs = database.loginUser(username, password);
        if (rs != null && rs.next()) {
            User user = new User();
            user.setIdUser(rs.getString("id_user"));
            user.setNama(rs.getString("user_name"));
            user.setEmail(rs.getString("email"));
            user.setNoTel(rs.getString("no_tel"));
            user.setPassword(rs.getString("password"));
            user.setTipe(rs.getString("tipe"));
            return user;
        }
        return null;
        
    }

    public void createPeminjaman(PeminjamanRuang peminjaman, String userId) throws SQLException {
        database.insertPeminjaman(peminjaman, userId);
    }

    public List<PeminjamanRuang> getPeminjamanRuangList() throws SQLException {
        return database.getListPeminjamanRuang();
    }

    public List<PeminjamanRuang> getPeminjamanRuangListById(String userId) throws SQLException {
        return database.getListPeminjamanRuangById(userId);
    }

    public void updatePeminjamanStatus(String idPmjm, String statusBaru) throws SQLException {
        database.ubahStatus(idPmjm, statusBaru);
    }

    public void editPeminjaman(String idPmjm, String tgl, String ruang, String status) throws SQLException {
        database.editPeminjaman(idPmjm, tgl, ruang, status);
    }

    public void deletePeminjaman(String idPmjm) throws SQLException {
        database.hapusPeminjaman(idPmjm);
    }

    public List<String> getRuangList() throws SQLException {
        return database.getListRuang();
    }

    public boolean isRuangAvailable(String tanggal, String ruang) throws SQLException {
        return !database.pengecekanRuang(tanggal, ruang);
    }

    public List<PeminjamanRuang> searchPeminjamanRuang(String text) throws SQLException {
        return database.getListPencarian(text);
    }

    public List<PeminjamanRuang> searchPeminjamanRuangById(String text, String userId) throws SQLException {
        return database.getListPencarianById(text, userId);
    }
}
