package br.com.mezun.dao;

import java.sql.*;
import br.com.mezun.dto.EmpresaDTO;

public class EmpresaDAO {

    //Atributo do tipo ResultSet utilizado para realizar consultas
    private ResultSet rs = null;
    //Manipular o banco de dados
    private Statement stmt = null;

    public boolean inserirEmpresa(EmpresaDTO empresaDTO) throws SQLException {
        try {
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "insert into Empresa (telefoneContato, nomeFantasia, CNPJ, rua, numero, bairro, CEP, cidade,senha)"
                    + "values('"+empresaDTO.getTelefone()+"','"
                    + empresaDTO.getNomeFantasia() + "','"
                    + empresaDTO.getCnpj() + "','"
                    + empresaDTO.getRua() + "',"
                    + empresaDTO.getNumero() + ",'"
                    + empresaDTO.getBairro() + "','"
                    + empresaDTO.getCep() + "','"
                    + empresaDTO.getCidade() + "','"
                     + empresaDTO.getSenha_log() + "');";
            

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

    public boolean excluirEmpresa(EmpresaDTO empresaDTO) {
        try {
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "Delete from Empresa where idEmpresa = " + empresaDTO.getId();
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

    public boolean alterarEmpresa(EmpresaDTO empresaDTO) {
        try {
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "Update Empresa set "
                    + "telefoneContato = '" + empresaDTO.getTelefone() + "', "
                    + "nomeFantasia = '" + empresaDTO.getNomeFantasia() + "', "
                    + "cnpj = '" + empresaDTO.getCnpj() + "', "
                    + "rua = '" + empresaDTO.getRua() + "', "
                    + "numero = " + empresaDTO.getNumero() + ", "
                    + "bairro = '" + empresaDTO.getBairro() + "', "
                    + "cep = '" + empresaDTO.getCep() + "', "
                    + "cidade = '" + empresaDTO.getCidade() + "', "
                    + "email = '" + empresaDTO.getNome() + "', "
                    + "senha = '" + empresaDTO.getSenha_log() + "' "
                    + "where idEmpresa = " + empresaDTO.getId();

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

    public ResultSet consultarEmpresa(EmpresaDTO empresaDTO, int opcao) {
        try {
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "";
            switch (opcao) {
                case 1:
                    comando = "Select e.* "
                            + "from Empresa e "
                            + "where e.CNPJ ilike '" + empresaDTO.getCnpj() + "%' "
                            + "order by e.nomeFantasia";

                    break;
                case 2:
                    comando = "Select e.* "
                            + "from Empresa e "
                            + "where e.idEmpresa= " + empresaDTO.getId();
                    break;
                case 3:
                    comando = "Select e.idEmpresa, e.nomeFantasia "
                            + "from Empresa e e.CNPJ like '" + empresaDTO.getCnpj() + "%'";
                    break;

            }
            rs = stmt.executeQuery(comando.toUpperCase());
            return rs;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return rs;
        }
    }
    public int logarEmpresa(EmpresaDTO empresaDTO) {
        try {
            //Chama o metodo que esta na classe ConexaoDAO para abrir o banco de dados
            ConexaoDAO.ConectDB();
            //Cria o Statement que responsavel por executar alguma coisa no banco de dados
            stmt = ConexaoDAO.con.createStatement();
            //Comando SQL que sera executado no banco de dados
            String comando = "Select e.idEmpresa " +
                             "from Empresa e " + 
                             "where e.CNPJ = '" + empresaDTO.getCnpj_log()+ "'" +
                             " and e.senha = '" + empresaDTO.getSenha_log() + "'";

            //Executa o comando SQL no banco de Dados
            rs = null;
            rs = stmt.executeQuery(comando);
            if(rs.next()){
                return rs.getInt("idEmpresa");
            }
            else{
                return 0;
            }
                
        } //Caso tenha algum erro no codigo acima é enviado uma mensagem no console com o que esta acontecendo.
        catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
        finally{
            ConexaoDAO.CloseDB();
        }
    }
}
