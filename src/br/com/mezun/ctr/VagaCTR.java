
package br.com.mezun.ctr;

import java.sql.ResultSet;
import br.com.mezun.dao.VagaDAO;
import br.com.mezun.dao.ConexaoDAO;
import br.com.mezun.dto.VagaDTO;
import br.com.mezun.dto.EmpresaDTO;
import br.com.mezun.dto.CargoDTO;
public class VagaCTR {
    VagaDAO vagaDAO = new VagaDAO();
    
    public String inserirVaga(VagaDTO vagaDTO, EmpresaDTO empresaDTO, CargoDTO cargoDTO) {
        try {

            if (vagaDAO.inserirVaga(vagaDTO, empresaDTO, cargoDTO)) {
                return "Vaga Cadastrado com Sucesso!!!";
            } else {
                return "Vaga NÃO Cadastrado!!!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Vaga NÃO Cadastrado";
        }
    }
    public String excluirVaga(VagaDTO vagaDTO) {
        try {
            if (vagaDAO.excluirVaga(vagaDTO)) {
                return "Vaga Excluído com Sucesso!!!";
            } else {
                return "Vaga NÃO Excluído!!!";
            }
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
            return "Vaga NÃO Excluído!!!";
        }
    }
    public String alterarVaga(VagaDTO vagaDTO, EmpresaDTO empresaDTO, CargoDTO cargoDTO) {
        try {
            //Chama o metodo que esta na classe DAO aguardando uma resposta (true ou false)
            if (vagaDAO.alterarVaga(vagaDTO, empresaDTO, cargoDTO)) {
                return "Vaga Alterada com Sucesso!!!";
            } else {
                return "Vaga NÃO Alterada!!!";
            }
        } //Caso tenha algum erro no codigo acima é enviado uma mensagem no console com o que esta acontecendo.
        catch (Exception e) {
            System.out.println(e.getMessage());
            return "Vaga NÃO Alterada!!!";
        }
    }
    public ResultSet consultarVaga(VagaDTO vagaDTO, int opcao) {
        
        ResultSet rs = null;

    
        rs = vagaDAO.consultarVaga(vagaDTO, opcao);

        return rs;
    }
    public void CloseDB() {
        ConexaoDAO.CloseDB();
    }
}
