package br.com.mezun.view;

import javax.swing.JOptionPane;
import br.com.mezun.ctr.VagaCTR;
import br.com.mezun.dto.VagaDTO;
import br.com.mezun.dto.EmpresaDTO;
import br.com.mezun.ctr.EmpresaCTR;
import br.com.mezun.dto.CargoDTO;
import br.com.mezun.ctr.CargoCTR;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class VagaVIEW extends javax.swing.JInternalFrame {

    VagaDTO vagaDTO = new VagaDTO();
    EmpresaDTO empresaDTO = new EmpresaDTO();
    EmpresaCTR empresaCTR = new EmpresaCTR();
    CargoDTO cargoDTO = new CargoDTO();
    CargoCTR cargoCTR = new CargoCTR();
    VagaCTR vagaCTR = new VagaCTR();
    ResultSet rs;
    int gravar_alt;
    DefaultTableModel modelo_jtl_consultar_vaga;
    DefaultTableModel modelo_jtl_consultar_cargo;

    public VagaVIEW(EmpresaDTO empresaDTO) {
        initComponents();
        this.empresaDTO = empresaDTO;
        modelo_jtl_consultar_vaga = (DefaultTableModel) tabelaVaga.getModel();
        modelo_jtl_consultar_cargo = (DefaultTableModel) tabelaCargo.getModel();
        liberaCampos(false);
        liberaBotoes(true, false, false, false, true);
    }
        

    private void gravar() {
        vagaDTO.setNome(nome.getText());
        vagaDTO.setDataInicio(dataInicio.getText());
        vagaDTO.setDataFim(dataFim.getText());
        vagaDTO.setObservacoes(observacoes.getText());
        vagaDTO.setPropostaSalarial(propostaSalarial.getText());
        cargoDTO.setId(Integer.parseInt(String.valueOf(tabelaCargo.getValueAt(tabelaCargo.getSelectedRow(), 0))));

        JOptionPane.showMessageDialog(null,
                vagaCTR.inserirVaga(vagaDTO, empresaDTO, cargoDTO)
        );
    }

    private void excluir() {
        if (JOptionPane.showConfirmDialog(null, "Deseja Realmente excluir a Vaga?", "Aviso", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null,
                    vagaCTR.excluirVaga(vagaDTO)
            );
        }
    }

    private void alterar() {
        vagaDTO.setNome(nome.getText());
        vagaDTO.setDataInicio(dataInicio.getText());
        vagaDTO.setDataFim(dataFim.getText());
        vagaDTO.setObservacoes(observacoes.getText());
        vagaDTO.setPropostaSalarial(propostaSalarial.getText());
        cargoDTO.setId(Integer.parseInt(String.valueOf(tabelaCargo.getValueAt(tabelaCargo.getSelectedRow(), 0))));

                

        JOptionPane.showMessageDialog(null,
                vagaCTR.alterarVaga(vagaDTO, empresaDTO, cargoDTO)
        );
    }

    private void preencheTabela(String nome) {
        try {
            //Limpa todas as linhas
            modelo_jtl_consultar_vaga.setNumRows(0);

            //Enquanto tiver linhas - faça
            vagaDTO.setNome(nome);
            rs = vagaCTR.consultarVaga(vagaDTO, 1);
            while (rs.next()) {
                modelo_jtl_consultar_vaga.addRow(new Object[]{
                    rs.getString("idVaga"),
                    rs.getString("nome")});
            }
        } catch (SQLException erTab) {
            System.out.println("Erro SQL: " + erTab);
        }
    }

    private void liberaBotoes(boolean a, boolean b, boolean c, boolean d, boolean e) {
        btnNovo5.setEnabled(a);
        btnSalvar5.setEnabled(b);
        btnCancelar5.setEnabled(c);
        btnExcluir5.setEnabled(d);
        btnSair5.setEnabled(e);
    }

    private void limpaCampos() {
        nome.setText("");
        dataInicio.setText("");
        dataFim.setText("");
        observacoes.setText("");
        propostaSalarial.setText("");
        consultarCargo.setText("");
       // tabelaCargo.setText("");

    }

    private void liberaCampos(boolean a) {
        nome.setEnabled(a);
        dataInicio.setEnabled(a);
        dataFim.setEnabled(a);
        observacoes.setEnabled(a);
        propostaSalarial.setEnabled(a);
        consultarCargo.setEnabled(a);

    }
    private void preencheTabelaCargo(String nome) {
        try {
            //Limpa todas as linhas
            modelo_jtl_consultar_cargo.setNumRows(0);

            //Enquanto tiver linhas - faça
            cargoDTO.setNome(nome);
            rs = cargoCTR.consultarCargo(cargoDTO, 1);
            while (rs.next()) {
                modelo_jtl_consultar_cargo.addRow(new Object[]{
                    rs.getString("idCargo"),
                    rs.getString("nome"),
                    rs.getString("especificacoes")});
            }
        } catch (SQLException erTab) {
            System.out.println("Erro SQL: " + erTab);
        }
    }
     private void preencheTabelaCargo2(int id) {
        try {
            //Limpa todas as linhas
            modelo_jtl_consultar_cargo.setNumRows(0);

            //Enquanto tiver linhas - faça
            cargoDTO.setId(id);
            rs = cargoCTR.consultarCargo(cargoDTO, 2);
            while (rs.next()) {
                modelo_jtl_consultar_cargo.addRow(new Object[]{
                    rs.getString("idCargo"),
                    rs.getString("nome"),
                    rs.getString("especificacoes")});
            }
        } catch (SQLException erTab) {
            System.out.println("Erro SQL: " + erTab);
        }
    }

    private void preencheCampos(int id) {
        try {
            vagaDTO.setId(id);
           
            rs = vagaCTR.consultarVaga(vagaDTO, 2); //2 = é a pesquisa no id na classe DAO
            if (rs.next()) {
                limpaCampos();

                nome.setText(rs.getString("nome"));
                dataInicio.setText(rs.getString("dataDisponivelInicio"));
                dataFim.setText(rs.getString("dataDIsponivelFim"));
                observacoes.setText(rs.getString("observacoes"));
                propostaSalarial.setText(rs.getString("propostaSalarial"));
                cargoDTO.setId(rs.getInt("idCargo"));
                empresaDTO.setId(rs.getInt("idEmpresa"));
                preencheTabelaCargo2(rs.getInt("idCargo"));

                gravar_alt = 2;
                liberaCampos(true);
            }
        } catch (SQLException erTab) {
            System.out.println("Erro SQL: " + erTab);
        }
    }
  
    
    

        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        dataInicio = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        dataFim = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        observacoes = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        nome = new javax.swing.JTextField();
        propostaSalarial = new javax.swing.JTextField();
        consultarCargo = new javax.swing.JTextField();
        botaoCargo = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelaCargo = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaVaga = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        consultarVaga = new javax.swing.JTextField();
        botaoVaga = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        btnSalvar5 = new javax.swing.JButton();
        btnCancelar5 = new javax.swing.JButton();
        btnSair5 = new javax.swing.JButton();
        btnNovo5 = new javax.swing.JButton();
        btnExcluir5 = new javax.swing.JButton();

        setTitle("Cadastro de Vaga");

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setText("Vaga");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Vaga"));

        jLabel2.setText("DataInicio:");

        try {
            dataInicio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        dataInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataInicioActionPerformed(evt);
            }
        });

        jLabel3.setText("DataFim:");

        try {
            dataFim.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel4.setText("PropostaSalarial:");

        jLabel5.setText("Observações:");

        observacoes.setColumns(20);
        observacoes.setRows(5);
        jScrollPane1.setViewportView(observacoes);

        jLabel6.setText("Nome:");

        nome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomeActionPerformed(evt);
            }
        });

        propostaSalarial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                propostaSalarialActionPerformed(evt);
            }
        });

        botaoCargo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mezun/view/imagens/pesquisar.png"))); // NOI18N
        botaoCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCargoActionPerformed(evt);
            }
        });

        jLabel8.setText("Seleciona o Cargo:");

        tabelaCargo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID:", "Nome:", "Especificação:"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaCargo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaCargoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tabelaCargoMouseEntered(evt);
            }
        });
        jScrollPane3.setViewportView(tabelaCargo);
        if (tabelaCargo.getColumnModel().getColumnCount() > 0) {
            tabelaCargo.getColumnModel().getColumn(0).setResizable(false);
            tabelaCargo.getColumnModel().getColumn(0).setPreferredWidth(15);
            tabelaCargo.getColumnModel().getColumn(1).setResizable(false);
            tabelaCargo.getColumnModel().getColumn(1).setPreferredWidth(150);
            tabelaCargo.getColumnModel().getColumn(2).setResizable(false);
            tabelaCargo.getColumnModel().getColumn(2).setPreferredWidth(150);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(558, 558, 558))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel8)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(15, 15, 15)))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(propostaSalarial, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                                        .addComponent(consultarCargo))
                                    .addGap(18, 18, 18)
                                    .addComponent(botaoCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(18, 18, 18)
                                    .addComponent(dataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3)
                                    .addGap(18, 18, 18)
                                    .addComponent(dataFim, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dataFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(propostaSalarial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel8)
                        .addComponent(consultarCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(botaoCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Consulta"));

        tabelaVaga.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID:", "Nome:"
            }
        ));
        tabelaVaga.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaVagaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelaVaga);
        if (tabelaVaga.getColumnModel().getColumnCount() > 0) {
            tabelaVaga.getColumnModel().getColumn(0).setResizable(false);
            tabelaVaga.getColumnModel().getColumn(0).setPreferredWidth(15);
            tabelaVaga.getColumnModel().getColumn(1).setResizable(false);
            tabelaVaga.getColumnModel().getColumn(1).setPreferredWidth(150);
        }

        jLabel7.setText("Nome:");

        botaoVaga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mezun/view/imagens/pesquisar.png"))); // NOI18N
        botaoVaga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoVagaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(consultarVaga, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botaoVaga, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botaoVaga, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(consultarVaga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnSalvar5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mezun/view/imagens/salvar.png"))); // NOI18N
        btnSalvar5.setText("Salvar");
        btnSalvar5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvar5ActionPerformed(evt);
            }
        });

        btnCancelar5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mezun/view/imagens/cancelar.png"))); // NOI18N
        btnCancelar5.setText("Cancelar");
        btnCancelar5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar5ActionPerformed(evt);
            }
        });

        btnSair5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mezun/view/imagens/sair.png"))); // NOI18N
        btnSair5.setText("Sair");
        btnSair5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSair5ActionPerformed(evt);
            }
        });

        btnNovo5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mezun/view/imagens/novo.png"))); // NOI18N
        btnNovo5.setText("Novo");
        btnNovo5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovo5ActionPerformed(evt);
            }
        });

        btnExcluir5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mezun/view/imagens/excluir.png"))); // NOI18N
        btnExcluir5.setText("Excluir");
        btnExcluir5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluir5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(btnNovo5, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(btnCancelar5, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(btnSalvar5, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(btnExcluir5, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(btnSair5, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo5)
                    .addComponent(btnCancelar5, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(btnSalvar5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSair5))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)))
                        .addGap(13, 13, 13)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomeActionPerformed
        
    }//GEN-LAST:event_nomeActionPerformed

    private void botaoVagaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoVagaActionPerformed
        preencheTabela(consultarVaga.getText().toUpperCase());
    }//GEN-LAST:event_botaoVagaActionPerformed

    private void propostaSalarialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_propostaSalarialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_propostaSalarialActionPerformed

    private void btnSair5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSair5ActionPerformed
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_btnSair5ActionPerformed

    private void btnExcluir5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluir5ActionPerformed
        excluir();
        limpaCampos();
        liberaCampos(false);
        liberaBotoes(true, false, false, false, true);
        preencheTabela(consultarVaga.getText().toUpperCase());
    }//GEN-LAST:event_btnExcluir5ActionPerformed

    private void btnNovo5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovo5ActionPerformed
        liberaCampos(true);
        liberaBotoes(false, true, true, false, true);
        gravar_alt = 1;
    }//GEN-LAST:event_btnNovo5ActionPerformed

    private void btnSalvar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvar5ActionPerformed
       if (gravar_alt == 1) {
            gravar();
            gravar_alt = 0;
        } else {
            if (gravar_alt == 2) {
                alterar();
                preencheTabela(consultarVaga.getText().toUpperCase());
                gravar_alt = 0;
            } else {
                JOptionPane.showMessageDialog(null, "Erro no Sistema!!!");
            }
        }
        limpaCampos();
        liberaCampos(false);
        liberaBotoes(true, false, false, false, true);
    }//GEN-LAST:event_btnSalvar5ActionPerformed

    private void tabelaVagaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaVagaMouseClicked
       preencheCampos(Integer.parseInt(String.valueOf(tabelaVaga.getValueAt(tabelaVaga.getSelectedRow(), 0))));
       liberaBotoes(false, true, true, true, true);
    }//GEN-LAST:event_tabelaVagaMouseClicked

    private void dataInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataInicioActionPerformed

    }//GEN-LAST:event_dataInicioActionPerformed

    private void btnCancelar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar5ActionPerformed
        limpaCampos();
        liberaCampos(false);
        modelo_jtl_consultar_vaga.setNumRows(0);
        liberaBotoes(true, false, false, false, true);
        gravar_alt = 0;
    }//GEN-LAST:event_btnCancelar5ActionPerformed

    private void botaoCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCargoActionPerformed
        preencheTabelaCargo(consultarCargo.getText());
    }//GEN-LAST:event_botaoCargoActionPerformed

    private void tabelaCargoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaCargoMouseClicked
        
        liberaBotoes(false, true, true, true, true);
    }//GEN-LAST:event_tabelaCargoMouseClicked

    private void tabelaCargoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaCargoMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelaCargoMouseEntered


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCargo;
    private javax.swing.JButton botaoVaga;
    private javax.swing.JButton btnCancelar5;
    private javax.swing.JButton btnExcluir5;
    private javax.swing.JButton btnNovo5;
    private javax.swing.JButton btnSair5;
    private javax.swing.JButton btnSalvar5;
    private javax.swing.JTextField consultarCargo;
    private javax.swing.JTextField consultarVaga;
    private javax.swing.JFormattedTextField dataFim;
    private javax.swing.JFormattedTextField dataInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField nome;
    private javax.swing.JTextArea observacoes;
    private javax.swing.JTextField propostaSalarial;
    private javax.swing.JTable tabelaCargo;
    private javax.swing.JTable tabelaVaga;
    // End of variables declaration//GEN-END:variables

   
}
