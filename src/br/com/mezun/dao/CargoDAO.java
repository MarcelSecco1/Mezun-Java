package br.com.mezun.dao;

import br.com.mezun.dto.CargoDTO;
import java.sql.*;

public class CargoDAO {

    //Atributo do tipo ResultSet utilizado para realizar consultas
    private ResultSet rs = null;
    //Manipular o banco de dados
    private Statement stmt = null;

    public boolean inserirCargo(CargoDTO cargoDTO) throws SQLException {
        try {
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "Insert into Cargo ("
                    + "nome, especificacoes) values ('" + cargoDTO.getNome() + "','" + cargoDTO.getObservacoes() + "') ";

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

    public boolean excluirCargo(CargoDTO cargoDTO) {
        try {
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "Delete from Cargo where idCargo = " + cargoDTO.getId();
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

    public boolean alterarCargo(CargoDTO cargoDTO) {
        try {
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "Update Cargo set "
                    + "nome = '" + cargoDTO.getNome() + "', "
                    + "especificacoes = '" + cargoDTO.getObservacoes() + "'"
                    + "where idCargo = " + cargoDTO.getId();

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

    public ResultSet consultarCargo(CargoDTO cargoDTO, int opcao) {
        try {
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "";
            switch (opcao) {
                case 1:
                    comando = "Select c.* "
                            + "from Cargo c "
                            + "where c.nome ilike '" + cargoDTO.getNome() + "%' "
                            + "order by c.nome";

                    break;
                case 2:
                    comando = "Select c.* "
                            + "from Cargo c "
                            + "where c.idCargo= " + cargoDTO.getId();
                    break;
                case 3:
                    comando = "Select c.idCargo, c.nome "
                            + "from Cargo c ";
                    break;

            }
            rs = stmt.executeQuery(comando.toUpperCase());
            return rs;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return rs;
        }
    }
}
