package View;

/**
 *
 * @author TITHA
 */

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Database;
import model.PeminjamanRuang;
import model.User;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import view.Login;

public class DashbordAdmin extends javax.swing.JFrame {

    /**
     * Creates new form DashbordAdmin
     */
    public DashbordAdmin() {
        initComponents();
        loadTableData();
        
        tabelStatus.getColumnModel().getColumn(0).setPreferredWidth(70);
        tabelStatus.getColumnModel().getColumn(1).setPreferredWidth(60);
        tabelStatus.getColumnModel().getColumn(2).setPreferredWidth(115);
        tabelStatus.getColumnModel().getColumn(3).setPreferredWidth(50);
        tabelStatus.getColumnModel().getColumn(4).setPreferredWidth(75);
        tabelStatus.getColumnModel().getColumn(5).setPreferredWidth(80);
        
        tabelHapus.getColumnModel().getColumn(0).setPreferredWidth(70);
        tabelHapus.getColumnModel().getColumn(1).setPreferredWidth(60);
        tabelHapus.getColumnModel().getColumn(2).setPreferredWidth(115);
        tabelHapus.getColumnModel().getColumn(3).setPreferredWidth(50);
        tabelHapus.getColumnModel().getColumn(4).setPreferredWidth(75);
        tabelHapus.getColumnModel().getColumn(5).setPreferredWidth(80);
    }
    
    public void exportToCSV(JTable table, JFileChooser jFileChooser) {
        jFileChooser.setDialogTitle("Save as");

        // Set filter untuk file CSV
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
        jFileChooser.setFileFilter(filter);

        int userSelection = jFileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = jFileChooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();

            // Tambahkan ekstensi .csv jika tidak ada
            if (!filePath.toLowerCase().endsWith(".csv")) {
                filePath += ".csv";
            }

            try (FileWriter csvWriter = new FileWriter(filePath)) {
                // Write header
                for (int col = 0; col < table.getColumnCount(); col++) {
                    csvWriter.append(table.getColumnName(col));
                    csvWriter.append(col == table.getColumnCount() - 1 ? "\n" : ",");
                }

                // Write data rows
                for (int row = 0; row < table.getRowCount(); row++) {
                    for (int col = 0; col < table.getColumnCount(); col++) {
                        csvWriter.append(table.getValueAt(row, col).toString());
                        csvWriter.append(col == table.getColumnCount() - 1 ? "\n" : ",");
                    }
                }

                csvWriter.flush();
                JOptionPane.showMessageDialog(null, "Data berhasil diekspor ke " + filePath);

            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + e.getMessage());
            }
        }
    }
    
    public void exportToExcel(JTable table, JFileChooser jFileChooser) {
        jFileChooser.setDialogTitle("Save as");

        // Set filter untuk file Excel
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files", "xlsx");
        jFileChooser.setFileFilter(filter);

        int userSelection = jFileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = jFileChooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();

            // Tambahkan ekstensi .xlsx jika tidak ada
            if (!filePath.toLowerCase().endsWith(".xlsx")) {
                filePath += ".xlsx";
            }

            try (Workbook workbook = new XSSFWorkbook(); FileOutputStream fileOut = new FileOutputStream(filePath)) {
                Sheet sheet = workbook.createSheet("Sheet1");

                // Buat header
                Row headerRow = sheet.createRow(0);
                for (int col = 0; col < table.getColumnCount(); col++) {
                    Cell cell = headerRow.createCell(col);
                    cell.setCellValue(table.getColumnName(col));
                }

                // Buat data rows
                for (int row = 0; row < table.getRowCount(); row++) {
                    Row dataRow = sheet.createRow(row + 1);
                    for (int col = 0; col < table.getColumnCount(); col++) {
                        Cell cell = dataRow.createCell(col);
                        Object value = table.getValueAt(row, col);
                        cell.setCellValue(value != null ? value.toString() : "");
                    }
                }

                workbook.write(fileOut);
                JOptionPane.showMessageDialog(null, "Data berhasil diekspor ke " + filePath);

            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + e.getMessage());
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        menu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        menuHome = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        menuEdit = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        menuHapus = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        menuAbout = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        menuLogout = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        layarUtama = new javax.swing.JPanel();
        home = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        editStatus = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelStatus = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        idPmjmUbah = new javax.swing.JTextField();
        diterimaButton = new javax.swing.JButton();
        ditolakButton = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        cariTextField2 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        exportButton = new javax.swing.JButton();
        exportToExcelButton = new javax.swing.JButton();
        hapusPeminjaman = new javax.swing.JPanel();
        editStatus1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelHapus = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        idPmjmHapus = new javax.swing.JTextField();
        hapusButton = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        cariTextField3 = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        about = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(244, 211, 94));

        menu.setBackground(new java.awt.Color(13, 59, 102));
        menu.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(51, 0, 0), new java.awt.Color(51, 0, 0), new java.awt.Color(51, 0, 0), new java.awt.Color(51, 0, 0)));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(250, 240, 202));
        jLabel2.setText("MENU");

        menuHome.setBackground(new java.awt.Color(13, 59, 102));
        menuHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuHomeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuHomeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuHomeMouseExited(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(250, 240, 202));
        jLabel3.setText("Home");

        javax.swing.GroupLayout menuHomeLayout = new javax.swing.GroupLayout(menuHome);
        menuHome.setLayout(menuHomeLayout);
        menuHomeLayout.setHorizontalGroup(
            menuHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuHomeLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menuHomeLayout.setVerticalGroup(
            menuHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuHomeLayout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );

        menuEdit.setBackground(new java.awt.Color(13, 59, 102));
        menuEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuEditMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuEditMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuEditMouseExited(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(250, 240, 202));
        jLabel4.setText("Ubah Status");

        javax.swing.GroupLayout menuEditLayout = new javax.swing.GroupLayout(menuEdit);
        menuEdit.setLayout(menuEditLayout);
        menuEditLayout.setHorizontalGroup(
            menuEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuEditLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menuEditLayout.setVerticalGroup(
            menuEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuEditLayout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap())
        );

        menuHapus.setBackground(new java.awt.Color(13, 59, 102));
        menuHapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuHapusMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuHapusMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuHapusMouseExited(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(250, 240, 202));
        jLabel5.setText("Hapus Peminjaman");

        javax.swing.GroupLayout menuHapusLayout = new javax.swing.GroupLayout(menuHapus);
        menuHapus.setLayout(menuHapusLayout);
        menuHapusLayout.setHorizontalGroup(
            menuHapusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuHapusLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel5)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        menuHapusLayout.setVerticalGroup(
            menuHapusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuHapusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        menuAbout.setBackground(new java.awt.Color(13, 59, 102));
        menuAbout.setForeground(new java.awt.Color(250, 240, 202));
        menuAbout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuAboutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuAboutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuAboutMouseExited(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(250, 240, 202));
        jLabel6.setText("About");

        javax.swing.GroupLayout menuAboutLayout = new javax.swing.GroupLayout(menuAbout);
        menuAbout.setLayout(menuAboutLayout);
        menuAboutLayout.setHorizontalGroup(
            menuAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuAboutLayout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menuAboutLayout.setVerticalGroup(
            menuAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuAboutLayout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addContainerGap())
        );

        menuLogout.setBackground(new java.awt.Color(13, 59, 102));
        menuLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuLogoutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuLogoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuLogoutMouseExited(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(250, 240, 202));
        jLabel7.setText("Logout");

        javax.swing.GroupLayout menuLogoutLayout = new javax.swing.GroupLayout(menuLogout);
        menuLogout.setLayout(menuLogoutLayout);
        menuLogoutLayout.setHorizontalGroup(
            menuLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLogoutLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menuLogoutLayout.setVerticalGroup(
            menuLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLogoutLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menuEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menuHapus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menuAbout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menuLogout, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(menuLayout.createSequentialGroup()
                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(menuLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel1))
                    .addGroup(menuLayout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(menuHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(menuEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(menuHapus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(menuAbout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(menuLogout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layarUtama.setBackground(new java.awt.Color(153, 193, 255));
        layarUtama.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(51, 0, 0), new java.awt.Color(51, 0, 0), new java.awt.Color(51, 0, 0), new java.awt.Color(51, 0, 0)));
        layarUtama.setLayout(new java.awt.CardLayout());

        home.setBackground(new java.awt.Color(13, 59, 102));

        jLabel8.setBackground(new java.awt.Color(250, 240, 202));
        jLabel8.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 30)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(250, 240, 202));
        jLabel8.setText("Dashbord Admin");

        jLabel9.setBackground(new java.awt.Color(250, 240, 202));
        jLabel9.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 30)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(250, 240, 202));
        jLabel9.setText("Selamat Datang di");

        jPanel2.setBackground(new java.awt.Color(250, 240, 202));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 204, 51), new java.awt.Color(204, 102, 0)));

        jLabel10.setBackground(java.awt.Color.orange);
        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(13, 59, 102));
        jLabel10.setText("Tata cara penggunaan:");

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(13, 59, 102));
        jLabel11.setText("1) Silahkan tekan menu edit status untuk menerima atau menolak pengajuan ");

        jLabel13.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(13, 59, 102));
        jLabel13.setText("peminjaman ruang.");

        jLabel12.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(13, 59, 102));
        jLabel12.setText("2) Silahkan klik menu hapus peminjaman jika pengajuan peminjaman");

        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(13, 59, 102));
        jLabel14.setText("sudah melewati tanggal penggunaan atau pengajuan peminjaman");

        jLabel15.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(13, 59, 102));
        jLabel15.setText("yang statusnya ditolak namun tidak melakukan pembaruan selama");

        jLabel16.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(13, 59, 102));
        jLabel16.setText("3 hari setelah status penolakan diberikan.");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel13)))
                .addContainerGap(60, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel10)
                .addGap(26, 26, 26)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addGap(22, 22, 22)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addGap(12, 12, 12)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout homeLayout = new javax.swing.GroupLayout(home);
        home.setLayout(homeLayout);
        homeLayout.setHorizontalGroup(
            homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homeLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
            .addGroup(homeLayout.createSequentialGroup()
                .addGap(188, 188, 188)
                .addGroup(homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(homeLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel8)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        homeLayout.setVerticalGroup(
            homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homeLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        layarUtama.add(home, "card2");

        editStatus.setBackground(new java.awt.Color(13, 59, 102));

        tabelStatus.setAutoCreateRowSorter(true);
        tabelStatus.setBackground(new java.awt.Color(250, 240, 202));
        tabelStatus.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 14)); // NOI18N
        tabelStatus.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id Peminjaman", "Pengguna", "Kegiatan", "Ruang", "Tanggal", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelStatus.setRowHeight(30);
        tabelStatus.getTableHeader().setReorderingAllowed(false);
        tabelStatus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelStatusMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelStatus);

        jLabel17.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(250, 240, 202));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Id Peminjaman");

        idPmjmUbah.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        idPmjmUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idPmjmUbahActionPerformed(evt);
            }
        });

        diterimaButton.setBackground(new java.awt.Color(250, 240, 202));
        diterimaButton.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        diterimaButton.setForeground(new java.awt.Color(13, 59, 102));
        diterimaButton.setText("Diterima");
        diterimaButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 51, 0), new java.awt.Color(153, 51, 0)));
        diterimaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diterimaButtonActionPerformed(evt);
            }
        });

        ditolakButton.setBackground(new java.awt.Color(250, 240, 202));
        ditolakButton.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        ditolakButton.setForeground(new java.awt.Color(13, 59, 102));
        ditolakButton.setText("Ditolak");
        ditolakButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 51, 0), new java.awt.Color(153, 51, 0)));
        ditolakButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ditolakButtonActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 30)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(250, 240, 202));
        jLabel18.setText("Edit Status Peminjaman");

        cariTextField2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cariTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cariTextField2KeyReleased(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(250, 240, 202));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 0, 0), new java.awt.Color(102, 0, 0)));
        jPanel7.setAlignmentX(0.0F);
        jPanel7.setAlignmentY(0.0F);

        jLabel37.setBackground(new java.awt.Color(255, 0, 153));
        jLabel37.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel37))
        );

        jLabel33.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(250, 240, 202));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("Download File");

        exportButton.setBackground(new java.awt.Color(250, 240, 202));
        exportButton.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        exportButton.setForeground(new java.awt.Color(13, 59, 102));
        exportButton.setText("Export .csv");
        exportButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 51, 0), new java.awt.Color(153, 51, 0)));
        exportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportButtonActionPerformed(evt);
            }
        });

        exportToExcelButton.setBackground(new java.awt.Color(250, 240, 202));
        exportToExcelButton.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        exportToExcelButton.setForeground(new java.awt.Color(13, 59, 102));
        exportToExcelButton.setText("Export .xlsx");
        exportToExcelButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 51, 0), new java.awt.Color(153, 51, 0)));
        exportToExcelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportToExcelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout editStatusLayout = new javax.swing.GroupLayout(editStatus);
        editStatus.setLayout(editStatusLayout);
        editStatusLayout.setHorizontalGroup(
            editStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editStatusLayout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(editStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editStatusLayout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cariTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
            .addGroup(editStatusLayout.createSequentialGroup()
                .addGap(177, 177, 177)
                .addComponent(jLabel18)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(editStatusLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel17)
                .addGap(56, 56, 56)
                .addGroup(editStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(editStatusLayout.createSequentialGroup()
                        .addComponent(diterimaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ditolakButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(idPmjmUbah, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(editStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editStatusLayout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addGap(83, 83, 83))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editStatusLayout.createSequentialGroup()
                        .addComponent(exportToExcelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(exportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))))
        );
        editStatusLayout.setVerticalGroup(
            editStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editStatusLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel18)
                .addGap(26, 26, 26)
                .addGroup(editStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cariTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(editStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(idPmjmUbah, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(editStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ditolakButton)
                    .addComponent(diterimaButton)
                    .addComponent(exportButton)
                    .addComponent(exportToExcelButton))
                .addGap(73, 73, 73))
        );

        layarUtama.add(editStatus, "card3");

        hapusPeminjaman.setBackground(new java.awt.Color(255, 234, 167));

        editStatus1.setBackground(new java.awt.Color(13, 59, 102));

        tabelHapus.setAutoCreateRowSorter(true);
        tabelHapus.setBackground(new java.awt.Color(250, 240, 202));
        tabelHapus.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 14)); // NOI18N
        tabelHapus.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id Peminjaman", "Pengguna", "Kegiatan", "Ruang", "Tanggal", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelHapus.setRowHeight(30);
        tabelHapus.getTableHeader().setReorderingAllowed(false);
        tabelHapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelHapusMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelHapus);

        jLabel19.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(250, 240, 202));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Id Peminjaman");

        idPmjmHapus.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        idPmjmHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idPmjmHapusActionPerformed(evt);
            }
        });

        hapusButton.setBackground(new java.awt.Color(250, 240, 202));
        hapusButton.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        hapusButton.setForeground(new java.awt.Color(13, 59, 102));
        hapusButton.setText("Hapus");
        hapusButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 51, 0), new java.awt.Color(153, 51, 0)));
        hapusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusButtonActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 30)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(250, 240, 202));
        jLabel20.setText("Hapus Peminjaman");

        cariTextField3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cariTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cariTextField3KeyReleased(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(250, 240, 202));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 0, 0), new java.awt.Color(102, 0, 0)));
        jPanel8.setAlignmentX(0.0F);
        jPanel8.setAlignmentY(0.0F);

        jLabel38.setBackground(new java.awt.Color(255, 0, 153));
        jLabel38.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel38))
        );

        javax.swing.GroupLayout editStatus1Layout = new javax.swing.GroupLayout(editStatus1);
        editStatus1.setLayout(editStatus1Layout);
        editStatus1Layout.setHorizontalGroup(
            editStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editStatus1Layout.createSequentialGroup()
                .addGap(204, 204, 204)
                .addComponent(jLabel20)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editStatus1Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(editStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editStatus1Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cariTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(editStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editStatus1Layout.createSequentialGroup()
                            .addComponent(jLabel19)
                            .addGap(56, 56, 56)
                            .addGroup(editStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(idPmjmHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(hapusButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(234, 234, 234))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editStatus1Layout.createSequentialGroup()
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(15, 15, 15)))))
        );
        editStatus1Layout.setVerticalGroup(
            editStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editStatus1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel20)
                .addGap(33, 33, 33)
                .addGroup(editStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cariTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(editStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idPmjmHapus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(hapusButton)
                .addGap(73, 73, 73))
        );

        javax.swing.GroupLayout hapusPeminjamanLayout = new javax.swing.GroupLayout(hapusPeminjaman);
        hapusPeminjaman.setLayout(hapusPeminjamanLayout);
        hapusPeminjamanLayout.setHorizontalGroup(
            hapusPeminjamanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 679, Short.MAX_VALUE)
            .addGroup(hapusPeminjamanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(hapusPeminjamanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(editStatus1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        hapusPeminjamanLayout.setVerticalGroup(
            hapusPeminjamanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 485, Short.MAX_VALUE)
            .addGroup(hapusPeminjamanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(hapusPeminjamanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(editStatus1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        layarUtama.add(hapusPeminjaman, "card4");

        about.setBackground(new java.awt.Color(13, 59, 102));

        jPanel3.setBackground(new java.awt.Color(250, 240, 202));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 255, 153), new java.awt.Color(51, 0, 0)));

        jLabel21.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 30)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(13, 59, 102));
        jLabel21.setText("ABOUT");

        jLabel22.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(13, 59, 102));
        jLabel22.setText("Keterangan:");

        jPanel4.setBackground(new java.awt.Color(250, 240, 202));

        jLabel25.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel25.setText("Aplikasi ini berfungsi untuk melakukan peminjaman ruang di kampus Politeknik");

        jLabel26.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel26.setText("Statistika STIS terkhusus untuk seluruh organisasi kampus serta kepanitiaan.");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel26)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(250, 240, 202));

        jLabel27.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel27.setText("kampus Politeknik Statistika STIS dengan mempersingkat alur pengajuan yang");

        jLabel28.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel28.setText("Aplikasi ini dibuat untuk mempermudah alur pengajuan izin peminjaman ruang di");

        jLabel29.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel29.setText("bisa langsung diajukan ke BAAK sebagai admin aplikasi ini tanpa melalui BEM.");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel29)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(250, 240, 202));

        jLabel30.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel30.setText("Titha Nur Izzah ");

        jLabel31.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel31.setText("2KS2");

        jLabel32.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel32.setText("222212898@stis.ac.id");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
                        .addGap(32, 32, 32))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel32)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jLabel23.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(13, 59, 102));
        jLabel23.setText("Disusun oleh:");

        jLabel24.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(13, 59, 102));
        jLabel24.setText("Tujuan:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel24)
                    .addComponent(jLabel23))
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel21)
                .addGap(249, 249, 249))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout aboutLayout = new javax.swing.GroupLayout(about);
        about.setLayout(aboutLayout);
        aboutLayout.setHorizontalGroup(
            aboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, aboutLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        aboutLayout.setVerticalGroup(
            aboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aboutLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        layarUtama.add(about, "card5");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(layarUtama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(layarUtama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuHomeMouseClicked
        // TODO add your handling code here:
        layarUtama.removeAll();
        layarUtama.repaint();
        layarUtama.revalidate();

        layarUtama.add(home);
        layarUtama.repaint();
        layarUtama.revalidate();
    }//GEN-LAST:event_menuHomeMouseClicked

    private void menuHomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuHomeMouseEntered
        // TODO add your handling code here:
        menuHome.setBackground(new Color(13,79,120));
    }//GEN-LAST:event_menuHomeMouseEntered

    private void menuHomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuHomeMouseExited
        // TODO add your handling code here:
        menuHome.setBackground(new Color(13,59,102));
    }//GEN-LAST:event_menuHomeMouseExited

    private void menuEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuEditMouseClicked
        // TODO add your handling code here:
        layarUtama.removeAll();
        layarUtama.repaint();
        layarUtama.revalidate();

        layarUtama.add(editStatus);
        layarUtama.repaint();
        layarUtama.revalidate();
    }//GEN-LAST:event_menuEditMouseClicked

    private void menuEditMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuEditMouseEntered
        // TODO add your handling code here:
        menuEdit.setBackground(new Color(13,79,120));
    }//GEN-LAST:event_menuEditMouseEntered

    private void menuEditMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuEditMouseExited
        // TODO add your handling code here:
        menuEdit.setBackground(new Color(13,59,102));
    }//GEN-LAST:event_menuEditMouseExited

    private void menuHapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuHapusMouseClicked
        // TODO add your handling code here:
        layarUtama.removeAll();
        layarUtama.repaint();
        layarUtama.revalidate();

        layarUtama.add(hapusPeminjaman);
        layarUtama.repaint();
        layarUtama.revalidate();
    }//GEN-LAST:event_menuHapusMouseClicked

    private void menuHapusMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuHapusMouseEntered
        // TODO add your handling code here:
        menuHapus.setBackground(new Color(13,79,120));
    }//GEN-LAST:event_menuHapusMouseEntered

    private void menuHapusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuHapusMouseExited
        // TODO add your handling code here:
        menuHapus.setBackground(new Color(13,59,102));
    }//GEN-LAST:event_menuHapusMouseExited

    private void menuAboutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuAboutMouseClicked
        // TODO add your handling code here:
        layarUtama.removeAll();
        layarUtama.repaint();
        layarUtama.revalidate();

        layarUtama.add(about);
        layarUtama.repaint();
        layarUtama.revalidate();
    }//GEN-LAST:event_menuAboutMouseClicked

    private void menuAboutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuAboutMouseEntered
        // TODO add your handling code here:
        menuAbout.setBackground(new Color(13,79,120));
    }//GEN-LAST:event_menuAboutMouseEntered

    private void menuAboutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuAboutMouseExited
        // TODO add your handling code here:
        menuAbout.setBackground(new Color(13,59,102));
    }//GEN-LAST:event_menuAboutMouseExited

    private void menuLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuLogoutMouseClicked
        // TODO add your handling code here:
        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menuLogoutMouseClicked

    private void menuLogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuLogoutMouseEntered
        // TODO add your handling code here:
        menuLogout.setBackground(new Color(13,79,120));
    }//GEN-LAST:event_menuLogoutMouseEntered

    private void menuLogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuLogoutMouseExited
        // TODO add your handling code here:
        menuLogout.setBackground(new Color(13,59,102));
    }//GEN-LAST:event_menuLogoutMouseExited

    private void tabelStatusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelStatusMouseClicked
        // TODO add your handling code here:
        DefaultTableModel dtm = (DefaultTableModel) tabelStatus.getModel();
        String idPmjm = dtm.getValueAt(tabelStatus.getSelectedRow(), 0).toString();
        idPmjmUbah.setText(idPmjm);
    }//GEN-LAST:event_tabelStatusMouseClicked

    private void idPmjmUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idPmjmUbahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idPmjmUbahActionPerformed

    private void diterimaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diterimaButtonActionPerformed
        // TODO add your handling code here:
        if(idPmjmUbah.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Id peminjaman tidak boleh kosong");
        }else{
            try{
                Database.getInstance().ubahStatus(idPmjmUbah.getText(), "diterima");
                JOptionPane.showMessageDialog(this, "Berhasil Mengubah status");
                loadTableData();
                idPmjmUbah.setText("");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Gagal Mengubah status");
            }
        }
    }//GEN-LAST:event_diterimaButtonActionPerformed

    private void ditolakButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ditolakButtonActionPerformed
        // TODO add your handling code here:
        if(idPmjmUbah.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Id peminjaman tidak boleh kosong");
        }else{
            try{
                Database.getInstance().ubahStatus(idPmjmUbah.getText(), "ditolak");
                JOptionPane.showMessageDialog(this, "Berhasil Mengubah status");
                loadTableData();
                idPmjmUbah.setText("");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Gagal Mengubah status");
            }
        }
    }//GEN-LAST:event_ditolakButtonActionPerformed

    private void cariTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cariTextField2KeyReleased
        // TODO add your handling code here:
        String text=cariTextField2.getText();
        DefaultTableModel dtm = (DefaultTableModel) tabelStatus.getModel();
        if (text.length()==0){
            while(dtm.getRowCount()>0){
                dtm.removeRow(0);
            }
            try {
                //isi tabel
                for(PeminjamanRuang pmjm: Database.getInstance().getListPeminjamanRuang()){
                    dtm.addRow(new Object[]{pmjm.getIdPmjm(),pmjm.getPengguna(),pmjm.getKegiatan(),pmjm.getRuang(),pmjm.getTanggal(),pmjm.getStatus()});
                }
            } catch (SQLException ex) {
                System.err.println(ex);
                JOptionPane.showMessageDialog(this, "Gagal mengambil data",
                    "Gagal", JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            //refresh tabel
            while(dtm.getRowCount()>0){
                dtm.removeRow(0);
            }
            try {
                //isi tabel
                for(PeminjamanRuang pmjm: Database.getInstance().getListPencarian(text)){
                    dtm.addRow(new Object[]{pmjm.getIdPmjm(),pmjm.getPengguna(),pmjm.getKegiatan(),pmjm.getRuang(),pmjm.getTanggal(),pmjm.getStatus()});
                }
            } catch (SQLException ex) {
                System.err.println(ex);
                JOptionPane.showMessageDialog(this, "Gagal mengambil data",
                    "Gagal", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_cariTextField2KeyReleased

    private void tabelHapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelHapusMouseClicked
        // TODO add your handling code here:
        DefaultTableModel dtm = (DefaultTableModel) tabelHapus.getModel();
        String idPmjm = dtm.getValueAt(tabelHapus.getSelectedRow(), 0).toString();
        idPmjmHapus.setText(idPmjm);
    }//GEN-LAST:event_tabelHapusMouseClicked

    private void hapusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusButtonActionPerformed
        // TODO add your handling code here:
        if(idPmjmHapus.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Id peminjaman tidak boleh kosong");
        }else{
            try{
                Database.getInstance().hapusPeminjaman(idPmjmHapus.getText());
                JOptionPane.showMessageDialog(this, "Berhasil Menghapus Peminjaman");
                loadTableData();
                idPmjmHapus.setText("");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Gagal Menghapus Peminjaman");
            }
        }
    }//GEN-LAST:event_hapusButtonActionPerformed

    private void cariTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cariTextField3KeyReleased
        // TODO add your handling code here:
        String text=cariTextField3.getText();
        DefaultTableModel dtm = (DefaultTableModel) tabelHapus.getModel();
        if (text.length()==0){
            while(dtm.getRowCount()>0){
                dtm.removeRow(0);
            }
            try {
                //isi tabel
                for(PeminjamanRuang pmjm: Database.getInstance().getListPeminjamanRuang()){
                    dtm.addRow(new Object[]{pmjm.getIdPmjm(),pmjm.getPengguna(),pmjm.getKegiatan(),pmjm.getRuang(),pmjm.getTanggal(),pmjm.getStatus()});
                }
            } catch (SQLException ex) {
                System.err.println(ex);
                JOptionPane.showMessageDialog(this, "Gagal mengambil data",
                    "Gagal", JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            //refresh tabel
            while(dtm.getRowCount()>0){
                dtm.removeRow(0);
            }
            try {
                //isi tabel
                for(PeminjamanRuang pmjm: Database.getInstance().getListPencarian(text)){
                    dtm.addRow(new Object[]{pmjm.getIdPmjm(),pmjm.getPengguna(),pmjm.getKegiatan(),pmjm.getRuang(),pmjm.getTanggal(),pmjm.getStatus()});
                }
            } catch (SQLException ex) {
                System.err.println(ex);
                JOptionPane.showMessageDialog(this, "Gagal mengambil data",
                    "Gagal", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_cariTextField3KeyReleased

    private void exportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportButtonActionPerformed

        // TODO add your handling code here:
        // Ambil model tabel atau data yang ingin diekspor
        TableModel model = tabelStatus.getModel();

        // Buat file CSV
        File file = new File("DataExport.csv");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            // Export header
            for (int i = 0; i < model.getColumnCount(); i++) {
                writer.write(model.getColumnName(i));
                if (i < model.getColumnCount() - 1) {
                    writer.write(",");
                }
            }
            writer.newLine();

            // Export data
            for (int row = 0; row < model.getRowCount(); row++) {
                for (int col = 0; col < model.getColumnCount(); col++) {
                    writer.write(model.getValueAt(row, col).toString());
                    if (col < model.getColumnCount() - 1) {
                        writer.write(",");
                    }
                }
                writer.newLine();
            }

            JOptionPane.showMessageDialog(this, "Data berhasil diekspor ke CSV.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan file CSV: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_exportButtonActionPerformed

    private void exportToExcelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportToExcelButtonActionPerformed
        // TODO add your handling code here:
        // Buat workbook baru untuk file Excel (format XLSX)
        Workbook workbook = new XSSFWorkbook();
        // Buat sheet baru di workbook
        Sheet sheet = workbook.createSheet("Data Export");

        // Ambil model tabel atau data yang ingin diekspor
        TableModel model = tabelStatus.getModel();

        // Export header
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < model.getColumnCount(); i++) {
            headerRow.createCell(i).setCellValue(model.getColumnName(i));
        }

        // Export data
        for (int row = 0; row < model.getRowCount(); row++) {
            Row sheetRow = sheet.createRow(row + 1);
            for (int col = 0; col < model.getColumnCount(); col++) {
                sheetRow.createCell(col).setCellValue(model.getValueAt(row, col).toString());
            }
        }

        // Simpan workbook ke file Excel
        try (FileOutputStream outputStream = new FileOutputStream("DataExport.xlsx")) {
            workbook.write(outputStream);
            JOptionPane.showMessageDialog(this, "Data berhasil diekspor ke Excel.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan file Excel: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
}
    }//GEN-LAST:event_exportToExcelButtonActionPerformed

    private void idPmjmHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idPmjmHapusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idPmjmHapusActionPerformed

    private void loadTableData(){
        DefaultTableModel dtm1 = (DefaultTableModel) tabelStatus.getModel();
        DefaultTableModel dtm2 = (DefaultTableModel) tabelHapus.getModel();
        //refresh tabel
        while(dtm1.getRowCount()>0){
            dtm1.removeRow(0);
            dtm2.removeRow(0);
        }
        try {
        //isi tabel
            for(PeminjamanRuang pmjm: Database.getInstance().getListPeminjamanRuang()){
                dtm1.addRow(new Object[]{pmjm.getIdPmjm(),pmjm.getPengguna(),pmjm.getKegiatan(),pmjm.getRuang(),
                    pmjm.getTanggal(),pmjm.getStatus()});
                dtm2.addRow(new Object[]{pmjm.getIdPmjm(),pmjm.getPengguna(),pmjm.getKegiatan(),pmjm.getRuang(),
                    pmjm.getTanggal(),pmjm.getStatus()});
            }
        } catch (SQLException ex) {
            System.err.println(ex);
            JOptionPane.showMessageDialog(this, "Gagal mengambil data", 
                    "Gagal", JOptionPane.ERROR_MESSAGE);
        }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel about;
    private javax.swing.JTextField cariTextField2;
    private javax.swing.JTextField cariTextField3;
    private javax.swing.JButton diterimaButton;
    private javax.swing.JButton ditolakButton;
    private javax.swing.JPanel editStatus;
    private javax.swing.JPanel editStatus1;
    private javax.swing.JButton exportButton;
    private javax.swing.JButton exportToExcelButton;
    private javax.swing.JButton hapusButton;
    private javax.swing.JPanel hapusPeminjaman;
    private javax.swing.JPanel home;
    private javax.swing.JTextField idPmjmHapus;
    private javax.swing.JTextField idPmjmUbah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel layarUtama;
    private javax.swing.JPanel menu;
    private javax.swing.JPanel menuAbout;
    private javax.swing.JPanel menuEdit;
    private javax.swing.JPanel menuHapus;
    private javax.swing.JPanel menuHome;
    private javax.swing.JPanel menuLogout;
    private javax.swing.JTable tabelHapus;
    private javax.swing.JTable tabelStatus;
    // End of variables declaration//GEN-END:variables
}