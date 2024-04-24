
package br.com.mezun.view;

import javax.swing.JOptionPane;
import br.com.mezun.dto.EmpresaDTO;


public class TelaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form TelaPrincipal
     */
     EmpresaDTO empresaDTO = new EmpresaDTO();
    public TelaPrincipal(int id) {
        empresaDTO.setId(id);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Cadastro Curriculos");
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        menuCadastro = new javax.swing.JMenu();
        menuItemEmpresa = new javax.swing.JMenuItem();
        menuItemVaga = new javax.swing.JMenuItem();
        menuItemCargo = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        verVagas = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        saiSistema = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        desktopPane.setBackground(new java.awt.Color(0, 102, 102));

        menuCadastro.setMnemonic('f');
        menuCadastro.setText("Cadastro");

        menuItemEmpresa.setMnemonic('s');
        menuItemEmpresa.setText("Empresa");
        menuItemEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemEmpresaActionPerformed(evt);
            }
        });
        menuCadastro.add(menuItemEmpresa);

        menuItemVaga.setText("Vaga");
        menuItemVaga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemVagaActionPerformed(evt);
            }
        });
        menuCadastro.add(menuItemVaga);

        menuItemCargo.setText("Cargo");
        menuItemCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCargoActionPerformed(evt);
            }
        });
        menuCadastro.add(menuItemCargo);

        menuBar.add(menuCadastro);

        jMenu1.setText("Solitações de Vagas");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        verVagas.setText("Vagas Requeridas");
        verVagas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verVagasActionPerformed(evt);
            }
        });
        jMenu1.add(verVagas);

        menuBar.add(jMenu1);

        editMenu.setMnemonic('e');
        editMenu.setText("Sair");
        editMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editMenuActionPerformed(evt);
            }
        });

        saiSistema.setText("Sair do Sistema");
        saiSistema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saiSistemaActionPerformed(evt);
            }
        });
        editMenu.add(saiSistema);

        menuBar.add(editMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(desktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1082, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(desktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, 771, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuItemVagaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemVagaActionPerformed
        VagaVIEW vagaVIEW = new VagaVIEW(empresaDTO);
        this.desktopPane.add(vagaVIEW);
        vagaVIEW.setVisible(true);
    }//GEN-LAST:event_menuItemVagaActionPerformed

    private void menuItemEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemEmpresaActionPerformed
        EmpresaVIEW empresaVIEW = new EmpresaVIEW();
        this.desktopPane.add(empresaVIEW);
        empresaVIEW.setVisible(true);
    }//GEN-LAST:event_menuItemEmpresaActionPerformed

    private void menuItemCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCargoActionPerformed
        CargoVIEW cargoVIEW = new CargoVIEW();
        this.desktopPane.add(cargoVIEW);
        cargoVIEW.setVisible(true);
    }//GEN-LAST:event_menuItemCargoActionPerformed

    private void editMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editMenuActionPerformed
       
    }//GEN-LAST:event_editMenuActionPerformed

    private void saiSistemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saiSistemaActionPerformed
        sair();
    }//GEN-LAST:event_saiSistemaActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void verVagasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verVagasActionPerformed
        CandidatoVIEW candidatoVIEW = new CandidatoVIEW();
        this.desktopPane.add(candidatoVIEW);
        candidatoVIEW.setVisible(true);
    }//GEN-LAST:event_verVagasActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new TelaPrincipal(empresaDTO).setVisible(true);
//            }
//        });
//    }
    private void sair(){
        Object[] options = { "Sair", "Cancelar" };
        if(JOptionPane.showOptionDialog(null, "Deseja Sair do Sistema", "Informação", 
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]) == 0){
            System.exit(0);
        } 
    } 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuCadastro;
    private javax.swing.JMenuItem menuItemCargo;
    private javax.swing.JMenuItem menuItemEmpresa;
    private javax.swing.JMenuItem menuItemVaga;
    private javax.swing.JMenuItem saiSistema;
    private javax.swing.JMenuItem verVagas;
    // End of variables declaration//GEN-END:variables

}