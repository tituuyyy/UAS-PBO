package model;

/**
 *
 * @author TITHA
 */
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.LoggerFactory;


public class Database implements Serializable{
    public static Database instance;
    private Connection connection;
    public static synchronized Database getInstance(){
        if(instance == null){
            instance = new Database();
        }
        return instance;
     }
    // Lokasi file SQLite Anda
    private static final String URL = "jdbc:sqlite:D:\\SEMESTER 4\\Belajar Java\\UASPBO_Titha\\peminjamanA.db";

    // Fungsi untuk mendapatkan koneksi ke database
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC"); // Memastikan driver JDBC SQLite di-load
            return DriverManager.getConnection(URL);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("SQLite JDBC driver tidak ditemukan", e);
        }
    }

    public void insertPeminjaman(PeminjamanRuang pmjm, String idUser) throws SQLException{
        Connection conn = getConnection();
        try{
            String sql="INSERT INTO peminjaman (id_pmjm,pengguna,kegiatan,ruang,tanggal,status,id_user) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, pmjm.getIdPmjm());
            pstmt.setString(2, pmjm.getPengguna());
            pstmt.setString(3, pmjm.getKegiatan());
            pstmt.setString(4, pmjm.getRuang());
            pstmt.setString(5, pmjm.getTanggal());
            pstmt.setString(6, pmjm.getStatus());
            pstmt.setString(7, idUser);
            pstmt.executeUpdate();
        }catch(SQLException ex){
            throw ex;
        } finally{
            if (conn!=null){
                conn.close();
            }
        }
    }
    
    public boolean pengecekanRuang(String tanggal, String ruang)throws SQLException{
        Connection conn = getConnection();
        try{
            String sql = "SELECT * FROM peminjaman";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                if (tanggal.equals(rs.getString("tanggal"))&&ruang.equals(rs.getString("ruang")))
                    return true;
            }
        }catch(SQLException ex){
            throw ex;
        } finally{
            if (conn!=null){
                conn.close();
            }
        }
        return false;
    }
    
    public List<PeminjamanRuang> getListPeminjamanRuang() throws SQLException{
        List<PeminjamanRuang> peminjamanRuangList = new ArrayList<>();
        Connection conn = getConnection();
        try{
            String sql = "SELECT * FROM peminjaman";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                PeminjamanRuang peminjamanRuang = new PeminjamanRuang();
                peminjamanRuang.setRuang(rs.getString("ruang"));
                peminjamanRuang.setPengguna(rs.getString("pengguna"));
                peminjamanRuang.setIdPmjm(rs.getString("id_pmjm"));
                peminjamanRuang.setTanggal(rs.getString("tanggal"));
                peminjamanRuang.setKegiatan(rs.getString("kegiatan"));
                peminjamanRuang.setStatus(rs.getString("status"));
                peminjamanRuangList.add(peminjamanRuang);
            }
        }catch(SQLException ex){
            throw ex;
        } finally{
            if (conn!=null){
                conn.close();
            }
        }
        return peminjamanRuangList;
    }
    
    public List<PeminjamanRuang> getListPeminjamanRuangById(String idUser) throws SQLException{
        List<PeminjamanRuang> peminjamanRuangList = new ArrayList<>();
        Connection conn = getConnection();
        try{
            String sql = "SELECT * FROM peminjaman WHERE id_user = '"+idUser+"' ";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                PeminjamanRuang peminjamanRuang = new PeminjamanRuang();
                peminjamanRuang.setRuang(rs.getString("ruang"));
                peminjamanRuang.setPengguna(rs.getString("pengguna"));
                peminjamanRuang.setIdPmjm(rs.getString("id_pmjm"));
                peminjamanRuang.setTanggal(rs.getString("tanggal"));
                peminjamanRuang.setKegiatan(rs.getString("kegiatan"));
                peminjamanRuang.setStatus(rs.getString("status"));
                peminjamanRuangList.add(peminjamanRuang);
            }
        }catch(SQLException ex){
            throw ex;
        } finally{
            if (conn!=null){
                conn.close();
            }
        }
        return peminjamanRuangList;
    }
    
    public List<PeminjamanRuang> getListPencarian(String text) throws SQLException{
        List<PeminjamanRuang> peminjamanRuangList = new ArrayList<>();
        Connection conn = getConnection();
        try{
            String sql = "SELECT * FROM peminjaman WHERE ruang LIKE '%"+text+"%' or "
                    + "status like '%"+text+"%' or "
                    + "pengguna like '%"+text+"%' or "
                    + "kegiatan like '%"+text+"%' or "
                    + "tanggal like '%"+text+"%'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                PeminjamanRuang peminjamanRuang = new PeminjamanRuang();
                peminjamanRuang.setRuang(rs.getString("ruang"));
                peminjamanRuang.setPengguna(rs.getString("pengguna"));
                peminjamanRuang.setIdPmjm(rs.getString("id_pmjm"));
                peminjamanRuang.setTanggal(rs.getString("tanggal"));
                peminjamanRuang.setKegiatan(rs.getString("kegiatan"));
                peminjamanRuang.setStatus(rs.getString("status"));
                peminjamanRuangList.add(peminjamanRuang);
            }
        }catch(SQLException ex){
            throw ex;
        } finally{
            if (conn!=null){
                conn.close();
            }
        }
        return peminjamanRuangList;
    }
    
    public List<PeminjamanRuang> getListPencarianById(String text, String idUser) throws SQLException{
        List<PeminjamanRuang> peminjamanRuangList = new ArrayList<>();
        Connection conn = getConnection();
        try{
            String sql = "SELECT * FROM peminjaman WHERE id_user LIKE '"+idUser+"' AND ("
                    + "ruang LIKE '%"+text+"%' or "
                    + "status like '%"+text+"%' or "
                    + "pengguna like '%"+text+"%' or "
                    + "kegiatan like '%"+text+"%' or "
                    + "tanggal like '%"+text+"%')";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                PeminjamanRuang peminjamanRuang = new PeminjamanRuang();
                peminjamanRuang.setRuang(rs.getString("ruang"));
                peminjamanRuang.setPengguna(rs.getString("pengguna"));
                peminjamanRuang.setIdPmjm(rs.getString("id_pmjm"));
                peminjamanRuang.setTanggal(rs.getString("tanggal"));
                peminjamanRuang.setKegiatan(rs.getString("kegiatan"));
                peminjamanRuang.setStatus(rs.getString("status"));
                peminjamanRuangList.add(peminjamanRuang);
            }
        }catch(SQLException ex){
            throw ex;
        } finally{
            if (conn!=null){
                conn.close();
            }
        }
        return peminjamanRuangList;
    }
    
    public void ubahStatus(String id_pmjm, String statusBaru)throws SQLException{
        Connection conn = getConnection();
        try{
            String sql2 = "UPDATE peminjaman SET status=? WHERE id_pmjm=?";
            PreparedStatement pstmt = conn.prepareStatement(sql2);
            pstmt.setString(1,statusBaru);
            pstmt.setString(2, id_pmjm);
            pstmt.executeUpdate();
        }catch(SQLException ex){
            throw ex;
        } finally{
            if (conn!=null){
                conn.close();
            }
        }
    }
    
    public void editPeminjaman(String id_pmjm, String tgl, String ruang, String status)throws SQLException{
        Connection conn = getConnection();
        try{
            String sql2 = "UPDATE peminjaman SET ruang=?, tanggal=?, status=? WHERE id_pmjm=?";
            PreparedStatement pstmt = conn.prepareStatement(sql2);
            pstmt.setString(1,ruang);
            pstmt.setString(2, tgl);
            pstmt.setString(3, status);
            pstmt.setString(4, id_pmjm);
            pstmt.executeUpdate();
        }catch(SQLException ex){
            throw ex;
        } finally{
            if (conn!=null){
                conn.close();
            }
        }
    }
    
    public void hapusPeminjaman(String id_pmjm)throws SQLException{
        Connection conn = getConnection();
        try{
            String sql = "DELETE FROM peminjaman WHERE id_pmjm=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id_pmjm);
            pstmt.executeUpdate();
        }catch(SQLException ex){
            throw ex;
        } finally{
            if (conn!=null){
                conn.close();
            }
        }
    }
    
    public List<String> getListRuang() throws SQLException{
        List<String> ruangList = new ArrayList<>();
        Connection conn = getConnection();
        try{
            String sql = "SELECT * FROM ruang";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String id = rs.getString("id");
                String namaRuang = rs.getString("nama");
                ruangList.add(namaRuang);
            }
        }catch(SQLException ex){
            throw ex;
        } finally{
            if (conn!=null){
                conn.close();
            }
        }
        return ruangList;
    }
    
    public void insertUser(User user) throws SQLException{
        Connection conn = getConnection();
        try{
            String sql;
            sql="INSERT INTO user (id_user,user_name,email,no_tel,password,tipe) VALUES (?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getIdUser());
            pstmt.setString(2, user.getNama());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getNoTel());
            pstmt.setString(5, user.getPassword());
            pstmt.setString(6, user.getTipe());
            pstmt.executeUpdate();
        }catch(SQLException ex){
            throw ex;
        }finally{
            if(conn!=null){
                conn.close();
            }
        }
    }
    
    public ResultSet loginUser(String user, String password)throws SQLException{
        Connection conn = getConnection();
        ResultSet rs=null;
        try{
            Statement stt=conn.createStatement();
            String sql="SELECT * FROM user WHERE user_name='"+user+"' AND password='"+password+"'";
            rs = stt.executeQuery(sql);
        }catch(SQLException ex){
            throw ex;
        }
        return rs;
    }
    
    public ResultSet getUser(String user)throws SQLException{
    Connection conn = getConnection();
    ResultSet rs = null;
    try{
        Statement stt=conn.createStatement();
        String sql="SELECT * FROM user WHERE id_user='"+user+"'";
        rs = stt.executeQuery(sql);
    }catch(SQLException ex){
        throw ex;
    }
    return rs;
}
}

