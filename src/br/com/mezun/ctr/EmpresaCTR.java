
package br.com.mezun.ctr;

import java.sql.ResultSet;
import br.com.mezun.dao.EmpresaDAO;
import br.com.mezun.dto.EmpresaDTO;
import br.com.mezun.dao.ConexaoDAO;
public class EmpresaCTR {
    EmpresaDAO empresaDAO = new EmpresaDAO();
    public String inserirEmpresa(EmpresaDTO empresaDTO) {
        try {

            if (empresaDAO.inserirEmpresa(empresaDTO)) {
                return "Empresa Cadastrado com Sucesso!!!";
            } else {
                return "Empresa NÃO Cadastrado!!!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Empresa NÃO Cadastrado";
        }
    }
    public String excluirEmpresa(EmpresaDTO empresaDTO) {
        try {
            if (empresaDAO.excluirEmpresa(empresaDTO)) {
                return "Empresa Excluído com Sucesso!!!";
            } else {
                return "Empresa NÃO Excluído!!!";
            }
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
            return "Empresa NÃO Excluído!!!";
        }
    }
    public int logarEmpresa(EmpresaDTO empresaDTO) {
        return empresaDAO.logarEmpresa(empresaDTO);

    }
    public String alterarEmpresa(EmpresaDTO empresaDTO) {
        try {
            //Chama o metodo que esta na classe DAO aguardando uma resposta (true ou false)
            if (empresaDAO.alterarEmpresa(empresaDTO)) {
                return "Empresa Alterado com Sucesso!!!";
            } else {
                return "Empresa NÃO Alterado!!!";
            }
        } //Caso tenha algum erro no codigo acima é enviado uma mensagem no console com o que esta acontecendo.
        catch (Exception e) {
            System.out.println(e.getMessage());
            return "Empresa NÃO Alterado!!!";
        }
    }
    public ResultSet consultarEmpresa(EmpresaDTO empresaDTO, int opcao) {
        
        ResultSet rs = null;

    
        rs = empresaDAO.consultarEmpresa(empresaDTO, opcao);

        return rs;
    }
    
    public void CloseDB() {
        ConexaoDAO.CloseDB();
    }
}
