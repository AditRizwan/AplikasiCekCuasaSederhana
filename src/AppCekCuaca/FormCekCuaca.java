package AppCekCuaca;

import java.awt.Image;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.json.*;

public class FormCekCuaca extends javax.swing.JFrame {
     // ====================== VARIABEL ======================
    DefaultTableModel model;
    String apiKey = "dad238815f73b64830fbdd9d1784fc10"; // ganti dengan API Key OpenWeatherMap

    // ====================== CONSTRUCTOR ======================
    public FormCekCuaca() {
        initComponents();
        setTitle("Aplikasi Cek Cuaca");
        setLocationRelativeTo(null);

        // Setup table model
        model = new DefaultTableModel(new String[]{"Kota", "Suhu (°C)", "Kondisi", "Waktu"}, 0);
        tblDataCuaca.setModel(model);

        // Setup combobox favorit
         // Setup combobox favorit
        cmbKota.removeAllItems();
        cmbKota.addItem("Pilih kota favorit...");
        cmbKota.addItem("Jakarta");
        cmbKota.addItem("Bandung");
        cmbKota.addItem("Surabaya");
        cmbKota.addItem("Yogyakarta");
        cmbKota.addItem("Medan");
        cmbKota.addItem("Bali");
        cmbKota.addItem("Makassar");
        cmbKota.addItem("Banjarmasin"); 
        cmbKota.addItem("Banjarbaru");
    }
        // ====================== CEK CUACA ======================
    private void cekCuaca(String kota) {
        try {
            String urlStr = "https://api.openweathermap.org/data/2.5/weather?q=" +
                    URLEncoder.encode(kota, "UTF-8") +
                    "&appid=" + apiKey + "&units=metric&lang=id";
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject json = new JSONObject(response.toString());
            String kondisi = json.getJSONArray("weather").getJSONObject(0).getString("description");
            double suhu = json.getJSONObject("main").getDouble("temp");

            lblHasil.setText("Kondisi: " + kondisi + ", Suhu: " + suhu + "°C");
            setIconCuaca(kondisi);

            String waktu = new java.text.SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new java.util.Date());
            model.addRow(new Object[]{kota, suhu, kondisi, waktu});

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal mengambil data cuaca!\n" + e.getMessage());
        }
    }
     // ====================== TAMPILKAN ICON CUACA ======================
    private void setIconCuaca(String kondisi) {
        String path = "/resources/cerah.png"; // default
    if (kondisi.contains("awan")) path = "/resources/berawan.png";
    else if (kondisi.contains("hujan")) path = "/resources/hujan.png";
    else if (kondisi.contains("kabut")) path = "/resources/kabut.png";

    ImageIcon icon = new ImageIcon(getClass().getResource(path));
    Image img = icon.getImage().getScaledInstance(128, 128, Image.SCALE_SMOOTH);

    // Tetap pakai ukuran JLabel 128x128
    lblIconCuaca.setIcon(new ImageIcon(img));
    lblIconCuaca.setPreferredSize(new java.awt.Dimension(100, 100));
    lblIconCuaca.setMinimumSize(new java.awt.Dimension(100, 100));
    lblIconCuaca.setMaximumSize(new java.awt.Dimension(100, 100));

    }
// ====================== SIMPAN DATA KE CSV ======================
    private void simpanKeCSV() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("data_cuaca.csv"))) {
            for (int i = 0; i < model.getRowCount(); i++) {
                pw.println(model.getValueAt(i, 0) + "," +
                        model.getValueAt(i, 1) + "," +
                        model.getValueAt(i, 2) + "," +
                        model.getValueAt(i, 3));
            }
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan ke CSV!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan file CSV.");
        }
    }
     // ====================== MUAT DATA DARI CSV ======================
    private void muatDataCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader("data_cuaca.csv"))) {
            model.setRowCount(0);
            String line;
            while ((line = br.readLine()) != null) {
                model.addRow(line.split(","));
            }
            JOptionPane.showMessageDialog(this, "Data cuaca berhasil dimuat!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat file CSV.");
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
        lblJudul = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblPilihKota = new javax.swing.JLabel();
        cmbKota = new javax.swing.JComboBox<>();
        txtKota = new javax.swing.JTextField();
        btnTambahFavorit = new javax.swing.JButton();
        btnCek = new javax.swing.JButton();
        btnSimpanCSV = new javax.swing.JButton();
        btnMuatData = new javax.swing.JButton();
        lblHasil = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblIconCuaca = new javax.swing.JLabel();
        scrollTabel = new javax.swing.JScrollPane();
        tblDataCuaca = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblJudul.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblJudul.setText("APLIKASI CEK CUACA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(lblJudul)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblJudul)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblPilihKota.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblPilihKota.setText("Pilih Kota : ");

        cmbKota.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmbKota.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Jakarta", "Bandung", "Surabaya", "Yogyakarta", "Medan", "Bali", "Makasar ", "Banjarmasin", "Banjarbaru" }));

        txtKota.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnTambahFavorit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnTambahFavorit.setText("+ Tambah Favorit");
        btnTambahFavorit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahFavoritActionPerformed(evt);
            }
        });

        btnCek.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCek.setText("CEK");
        btnCek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekActionPerformed(evt);
            }
        });

        btnSimpanCSV.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSimpanCSV.setText("SIMPAN");
        btnSimpanCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanCSVActionPerformed(evt);
            }
        });

        btnMuatData.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnMuatData.setText("MUAT DATA");
        btnMuatData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMuatDataActionPerformed(evt);
            }
        });

        lblHasil.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblHasil.setText("Cuaca Saat ini : ");

        lblIconCuaca.setBackground(new java.awt.Color(204, 204, 255));
        lblIconCuaca.setText("`");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblIconCuaca, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(94, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 23, Short.MAX_VALUE)
                .addComponent(lblIconCuaca, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblPilihKota)
                                .addGap(29, 29, 29)
                                .addComponent(cmbKota, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(btnCek)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtKota, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnSimpanCSV)
                                .addGap(18, 18, 18)
                                .addComponent(btnMuatData)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTambahFavorit))
                    .addComponent(lblHasil)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPilihKota)
                    .addComponent(cmbKota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtKota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTambahFavorit))
                .addGap(18, 18, 18)
                .addComponent(lblHasil)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCek)
                    .addComponent(btnSimpanCSV)
                    .addComponent(btnMuatData))
                .addContainerGap())
        );

        scrollTabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tblDataCuaca.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Kota", "Suhu", "Kondisi", "Waktu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollTabel.setViewportView(tblDataCuaca);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollTabel)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollTabel, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekActionPerformed
        String kota = txtKota.getText().trim();
        if (kota.isEmpty() && cmbKota.getSelectedIndex() > 0)
            kota = cmbKota.getSelectedItem().toString();

        if (!kota.isEmpty()) cekCuaca(kota);
        else JOptionPane.showMessageDialog(this, "Masukkan atau pilih kota terlebih dahulu!");  
    }//GEN-LAST:event_btnCekActionPerformed

    private void btnTambahFavoritActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahFavoritActionPerformed
        String kota = txtKota.getText().trim();
        if (!kota.isEmpty() && ((DefaultComboBoxModel) cmbKota.getModel()).getIndexOf(kota) == -1) {
            cmbKota.addItem(kota);
            JOptionPane.showMessageDialog(this, kota + " ditambahkan ke favorit!");
        }
    }//GEN-LAST:event_btnTambahFavoritActionPerformed

    private void btnSimpanCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanCSVActionPerformed
       simpanKeCSV();
    }//GEN-LAST:event_btnSimpanCSVActionPerformed

    private void btnMuatDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMuatDataActionPerformed
       muatDataCSV();
    }//GEN-LAST:event_btnMuatDataActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormCekCuaca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormCekCuaca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormCekCuaca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormCekCuaca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormCekCuaca().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCek;
    private javax.swing.JButton btnMuatData;
    private javax.swing.JButton btnSimpanCSV;
    private javax.swing.JButton btnTambahFavorit;
    private javax.swing.JComboBox<String> cmbKota;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblHasil;
    private javax.swing.JLabel lblIconCuaca;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblPilihKota;
    private javax.swing.JScrollPane scrollTabel;
    private javax.swing.JTable tblDataCuaca;
    private javax.swing.JTextField txtKota;
    // End of variables declaration//GEN-END:variables
}
