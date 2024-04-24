
package br.com.mezun.ctr;

import java.sql.ResultSet;
import br.com.mezun.dao.CargoDAO;
import br.com.mezun.dto.CargoDTO;
import br.com.mezun.dao.ConexaoDAO;
public class CargoCTR {
    CargoDAO cargoDAO = new CargoDAO();
    public String inserirCargo(CargoDTO cargoDTO) {
        try {

            if (cargoDAO.inserirCargo(cargoDTO)) {
                return "Cargo Cadastrado com Sucesso!!!";
            } else {
                return "Cargo NÃO Cadastrado!!!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Cargo NÃO Cadastrado";
        }
    }
    public String excluirCargo(CargoDTO cargoDTO) {
        try {
            if (cargoDAO.excluirCargo(cargoDTO)) {
                return "Cargo Excluído com Sucesso!!!";
            } else {
                return "Cargo NÃO Excluído!!!";
            }
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
            return "Cargo NÃO Excluído!!!";
        }
    }
    public String alterarCargo(CargoDTO cargoDTO) {
        try {
            //Chama o metodo que esta na classe DAO aguardando uma resposta (true ou false)
            if (cargoDAO.alterarCargo(cargoDTO)) {
                return "Cargo Alterado com Sucesso!!!";
            } else {
                return "Cargo NÃO Alterado!!!";
            }
        } //Caso tenha algum erro no codigo acima é enviado uma mensagem no console com o que esta acontecendo.
        catch (Exception e) {
            System.out.println(e.getMessage());
            return "Cargo NÃO Alterado!!!";
        }
    }
    public ResultSet consultarCargo(CargoDTO cargoDTO, int opcao) {
        
        ResultSet rs = null;

    
        rs = cargoDAO.consultarCargo(cargoDTO, opcao);

        return rs;
    }
    public void CloseDB() {
        ConexaoDAO.CloseDB();
    }
}
