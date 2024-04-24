/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mezun.ctr;

import java.sql.ResultSet;
import br.com.mezun.dao.CandidatoDAO;
import br.com.mezun.dao.ConexaoDAO;
import br.com.mezun.dto.CandidatoDTO;
import br.com.mezun.dto.EmpresaDTO;

public class CandidatoCTR {

    CandidatoDAO candidatoDAO = new CandidatoDAO();

   
    public ResultSet consultarCandidato(CandidatoDTO candidatoDTO, int opcao) {
        
        ResultSet rs = null;

    
        rs = candidatoDAO.consultarCandidato(candidatoDTO, opcao);

        return rs;
    }
    public String aceitarCandidato(CandidatoDTO candidatoDTO) {
        try {

            if (candidatoDAO.aceitarCandidato(candidatoDTO)) {
                return "Candidato Aceito com Sucesso!!!";
            } else {
                return "Algo deu errado, tente novamente!!!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Empresa NÃO Cadastrado";
        }
    }
    public String excluirVaga_Candidato(CandidatoDTO candidatoDTO) {
        try {
            if (candidatoDAO.excluirVaga_Candidato(candidatoDTO)) {
                return "Vaga requerida Excluída com Sucesso!!!";
            } else {
                return "Vaga requerida NÃO Excluída!!!";
            }
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
            return "Vaga requerida NÃO Excluída!!!";
        }
    }
    
    public void CloseDB() {
        ConexaoDAO.CloseDB();
    }
}
