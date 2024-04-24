package br.com.mezun.dao;
import br.com.mezun.dto.EmpresaDTO;
import br.com.mezun.dto.VagaDTO;
import br.com.mezun.dto.CargoDTO;
import br.com.mezun.dto.EmpresaDTO;
import java.sql.*;

public class VagaDAO {

    private ResultSet rs = null;
    //Manipular o banco de dados
    private Statement stmt = null;

    public boolean inserirVaga(VagaDTO vagaDTO, EmpresaDTO empresaDTO, CargoDTO cargoDTO) throws SQLException {
        try {
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "Insert into Vaga ("
                    + "nome, dataDisponivelInicio, dataDisponivelFim, propostaSalarial, observacoes, idEmpresa, idCargo) values ('"
                    + vagaDTO.getNome() + "','"
                    + vagaDTO.getDataInicio() + "','"
                    + vagaDTO.getDataFim() + "','"
                    + vagaDTO.getPropostaSalarial() + "','"
                    + vagaDTO.getObservacoes() + "',"
                    + empresaDTO.getId() + "," 
                    + cargoDTO.getId() + ");";

            System.out.println(comando);
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

    public boolean excluirVaga(VagaDTO vagaDTO) {
        try {
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "Delete from Vaga where idVaga = " + vagaDTO.getId();
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

    public boolean alterarVaga(VagaDTO vagaDTO, EmpresaDTO empresaDTO, CargoDTO cargoDTO) {
        try {
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "Update Vaga set "
                    + "nome = '" + vagaDTO.getNome() + "', "
                    + "dataDisponivelInicio = '" + vagaDTO.getDataInicio() + "', "
                    + "dataDisponivelFim = '" + vagaDTO.getDataFim() + "', "
                    + "propostaSalarial = '" + vagaDTO.getPropostaSalarial() + "', "
                    + "observacoes = '" + vagaDTO.getObservacoes() + "', "
                    + "idEmpresa = " + empresaDTO.getId() + ", "
                    + "idCargo = " + cargoDTO.getId() + " "
                    + "where idVaga = " + vagaDTO.getId();

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

    public ResultSet consultarVaga(VagaDTO vagaDTO, int opcao) {
        try {
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "";
            switch (opcao) {
                case 1:
                    comando = "Select v.* "
                            + "from Vaga v "
                            + "where v.nome like '" + vagaDTO.getNome() + "%' "
                            + "order by v.nome ";

                    break;
                case 2:
                    comando = "Select v.* "
                            + "from Vaga v "
                            + "where v.idVaga = " + vagaDTO.getId();
                    break;
                case 3:
                    comando = "Select v.idVaga, v.dataDisponivelInicio, v.dataDisponivelFim "
                            + "from Vaga v ";
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
