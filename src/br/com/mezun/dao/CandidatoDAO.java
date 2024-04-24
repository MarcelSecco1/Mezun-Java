package br.com.mezun.dao;

import java.sql.*;
import br.com.mezun.dto.CandidatoDTO;

public class CandidatoDAO {

    //Atributo do tipo ResultSet utilizado para realizar consultas
    private ResultSet rs = null;
    //Manipular o banco de dados
    private Statement stmt = null;

    public ResultSet consultarCandidato(CandidatoDTO candidatoDTO, int opcao) {
        try {
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "";
            switch (opcao) {
                case 1:
                    comando = "Select c.* "
                            + "from Candidato c "
                            + "where c.nome like '" + candidatoDTO.getNomeCandidato() + "%' "
                            + "order by c.nome";

                    break;
                case 2:
                    comando = "Select c.* "
                            + "from Candidato c "
                            + "where c.idCandidato= " + candidatoDTO.getId();
                    break;
                case 3:
                    comando = "Select c.idCandidato, c.nome "
                            + "from Candidato c ";

                    break;
                case 4:
                    comando = "select c.nome, vc.*, v.nome as nomeVaga"
                            + "	from Candidato c, Vaga_Candidato vc, Vaga v"
                            + "	where v.idVaga = vc.idVaga and"
                            + "	vc.idCandidato = c.idCandidato and"
                            + "	c.nome ilike '" + candidatoDTO.getNomeCandidato() + "%' order by c.nome";

                    break;

            }
            rs = stmt.executeQuery(comando.toUpperCase());
            return rs;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return rs;
        }
    }
    
    public boolean excluirVaga_Candidato(CandidatoDTO candidatoDTO) {
        try {
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "Delete from Vaga_Candidato where idCandidato_Vaga = " + candidatoDTO.getId();
            
            stmt.execute(comando);
            ConexaoDAO.con.commit();
            stmt.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            ConexaoDAO.CloseDB();
        }
    }

    public boolean aceitarCandidato(CandidatoDTO candidatoDTO) {
        try {
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "Update Vaga_Candidato set "
                    + "status = 'Aceito!!' "
                    + "where idCandidato_Vaga = " + candidatoDTO.getId();

            stmt.execute(comando.toUpperCase());
            ConexaoDAO.con.commit();
            stmt.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {

            ConexaoDAO.CloseDB();
        }
    }
    
}
