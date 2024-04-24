package br.com.mezun.view;

import javax.swing.JOptionPane;
import br.com.mezun.ctr.CargoCTR;
import br.com.mezun.dto.CargoDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class CargoVIEW extends javax.swing.JInternalFrame {

    CargoDTO cargoDTO = new CargoDTO();
    CargoCTR cargoCTR = new CargoCTR();
    ResultSet rs;
    int gravar_alt; //Variavel usada para saber se esta alterando o incluindo
    DefaultTableModel modelo_jtl_consultar_cargo;

    public CargoVIEW() {
        initComponents();
        modelo_jtl_consultar_cargo = (DefaultTableModel) tabelaCargo.getModel();
        liberaCampos(false);
        liberaBotoes(true, false, false, false, true);
    }

    private void gravar() {
        cargoDTO.setNome(nomeCargo.getText());
        cargoDTO.setObservacoes(observacoesCargo.getText());

        JOptionPane.showMessageDialog(null,
                cargoCTR.inserirCargo(cargoDTO)
        );
    }

    private void excluir() {
        if (JOptionPane.showConfirmDialog(null, "Deseja Realmente excluir o Cargo?", "Aviso", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null,
                    cargoCTR.excluirCargo(cargoDTO)
            );
        }
    }

    private void alterar() {
        cargoDTO.setNome(nomeCargo.getText());
        cargoDTO.setObservacoes(observacoesCargo.getText());

        JOptionPane.showMessageDialog(null,
                cargoCTR.alterarCargo(cargoDTO)
        );
    }

    private void preencheTabela(String nome) {
        try {
            //Limpa todas as linhas
            modelo_jtl_consultar_cargo.setNumRows(0);

            //Enquanto tiver linhas - faça
            cargoDTO.setNome(nome);
            rs = cargoCTR.consultarCargo(cargoDTO, 1);
            while (rs.next()) {
                modelo_jtl_consultar_cargo.addRow(new Object[]{
                    rs.getString("idCargo"),
                    rs.getString("nome"),});
            }
        } catch (SQLException erTab) {
            System.out.println("Erro SQL: " + erTab);
        }
    }

    private void liberaBotoes(boolean a, boolean b, boolean c, boolean d, boolean e) {
        btnNovo3.setEnabled(a);
        btnSalvar3.setEnabled(b);
        btnCancelar3.setEnabled(c);
        btnExcluir3.setEnabled(d);
        btnSair3.setEnabled(e);
    }

    private void limpaCampos() {
        nomeCargo.setText("");
        observacoesCargo.setText("");

    }

    private void liberaCampos(boolean a) {
        nomeCargo.setEnabled(a);
        observacoesCargo.setEnabled(a);

    }

    private void preencheCampos(int id) {
        try {
            cargoDTO.setId(id);
            rs = cargoCTR.consultarCargo(cargoDTO, 2); //2 = é a pesquisa no id na classe DAO
            if (rs.next()) {
                limpaCampos();

                nomeCargo.setText(rs.getString("nome"));
                observacoesCargo.setText(rs.getString("especificacoes"));

                gravar_alt = 2;
                liberaCampos(true);
            }
        } catch (Exception erTab) {
            System.out.println("Erro SQL: " + erTab);
        }
    }

    private boolean verificaPreenchimentoGeral() {
        if (nomeCargo.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(null, "O campo Nome deve ser preenchido");
            nomeCargo.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        nomeCargo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        observacoesCargo = new javax.swing.JTextPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        consultarCargo = new javax.swing.JTextField();
        botaoCargo = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelaCargo = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        btnCancelar3 = new javax.swing.JButton();
        btnSair3 = new javax.swing.JButton();
        btnNovo3 = new javax.swing.JButton();
        btnExcluir3 = new javax.swing.JButton();
        btnSalvar3 = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        setTitle("Cadastro de Cargo");
        setToolTipText("");

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setText("Cargo:");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cargo"));

        jLabel2.setText("Nome:");

        jLabel3.setText("Especificações:");

        jScrollPane1.setViewportView(observacoesCargo);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nomeCargo))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nomeCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Consulta"));

        jLabel4.setText("Nome:");

        botaoCargo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mezun/view/imagens/pesquisar.png"))); // NOI18N
        botaoCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCargoActionPerformed(evt);
            }
        });

        tabelaCargo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID:", "Nome:"
            }
        ));
        tabelaCargo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaCargoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabelaCargo);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(consultarCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botaoCargo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botaoCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(consultarCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnCancelar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mezun/view/imagens/cancelar.png"))); // NOI18N
        btnCancelar3.setText("Cancelar");
        btnCancelar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar3ActionPerformed(evt);
            }
        });

        btnSair3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mezun/view/imagens/sair.png"))); // NOI18N
        btnSair3.setText("Sair");
        btnSair3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSair3ActionPerformed(evt);
            }
        });

        btnNovo3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mezun/view/imagens/novo.png"))); // NOI18N
        btnNovo3.setText("Novo");
        btnNovo3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovo3ActionPerformed(evt);
            }
        });

        btnExcluir3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mezun/view/imagens/excluir.png"))); // NOI18N
        btnExcluir3.setText("Excluir");
        btnExcluir3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluir3ActionPerformed(evt);
            }
        });

        btnSalvar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mezun/view/imagens/salvar.png"))); // NOI18N
        btnSalvar3.setText("Salvar");
        btnSalvar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvar3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(btnNovo3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnCancelar3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnSalvar3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnExcluir3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(btnSair3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancelar3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSair3)
                    .addComponent(btnExcluir3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSalvar3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(423, 423, 423)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(65, 65, 65)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar3ActionPerformed
        limpaCampos();
        liberaCampos(false);
        modelo_jtl_consultar_cargo.setNumRows(0);
        liberaBotoes(true, false, false, false, true);
        gravar_alt = 0;
    }//GEN-LAST:event_btnCancelar3ActionPerformed

    private void btnSair3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSair3ActionPerformed
        this.dispose();
        this.setVisible(false);
    }//GEN-LAST:event_btnSair3ActionPerformed

    private void btnExcluir3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluir3ActionPerformed
        excluir();
        limpaCampos();
        liberaCampos(false);
        liberaBotoes(true, false, false, false, true);
        preencheTabela(consultarCargo.getText().toUpperCase());
    }//GEN-LAST:event_btnExcluir3ActionPerformed

    private void btnNovo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovo3ActionPerformed
        liberaCampos(true);
        liberaBotoes(false, true, true, false, true);
        gravar_alt = 1;
    }//GEN-LAST:event_btnNovo3ActionPerformed

    private void btnSalvar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvar3ActionPerformed
        if (gravar_alt == 1) {
            gravar();
            gravar_alt = 0;
        } else {
            if (gravar_alt == 2) {
                alterar();
                preencheTabela(consultarCargo.getText().toUpperCase());
                gravar_alt = 0;
            } else {
                JOptionPane.showMessageDialog(null, "Erro no Sistema!!!");
            }
        }
        limpaCampos();
        liberaCampos(false);
        liberaBotoes(true, false, false, false, true);
    }//GEN-LAST:event_btnSalvar3ActionPerformed

    private void tabelaCargoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaCargoMouseClicked
        preencheCampos(Integer.parseInt(String.valueOf(tabelaCargo.getValueAt(tabelaCargo.getSelectedRow(), 0))));
        liberaBotoes(false, true, true, true, true);
    }//GEN-LAST:event_tabelaCargoMouseClicked

    private void botaoCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCargoActionPerformed
        preencheTabela(consultarCargo.getText().toUpperCase());

    }//GEN-LAST:event_botaoCargoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCargo;
    private javax.swing.JButton btnCancelar3;
    private javax.swing.JButton btnExcluir3;
    private javax.swing.JButton btnNovo3;
    private javax.swing.JButton btnSair3;
    private javax.swing.JButton btnSalvar3;
    private javax.swing.JTextField consultarCargo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField nomeCargo;
    private javax.swing.JTextPane observacoesCargo;
    private javax.swing.JTable tabelaCargo;
    // End of variables declaration//GEN-END:variables
}
