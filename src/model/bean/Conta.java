/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import model.dao.ContaDAO;

/**
 *
 * @author anton
 */
public class Conta {

    private int idContas;
    private String loginUsuario;
    private String senhaUsuario;
    private double saldoUsuario;
    private String nomeUsuario;

    public int getIdContas() {
        return idContas;
    }

    public void setIdContas(int idContas) {
        this.idContas = idContas;
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public double getSaldoUsuario() {
        return saldoUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String sacar(Double valorSaque) {
        //true se der para sacar
        //false se não der para sacar
        ContaDAO cDao = new ContaDAO();
        if (saldoUsuario > 0) {
            saldoUsuario -= valorSaque;
            cDao.update(this);
            return "Sucesso";
        } else {
            return "não deu";
        }
    }

    public boolean depositar(Double valorDeposito) {
        //true se der para depositar
        //false se não der para depositar
        ContaDAO cDao = new ContaDAO();
        if (valorDeposito > 0) {
            this.saldoUsuario += valorDeposito;
            cDao.update(this);
            return true;
        } else {

            return false;
        }

    }

    public String transferencia(Conta destino, Double valorTransferencia) {
        ContaDAO cDao = new ContaDAO();
        if (this.sacar(valorTransferencia).equals("Sucesso")) {

            destino.depositar(valorTransferencia);
            return "Transferencia realizada";
        } else {
            return "Transferencia nao deu certo";
        }
    }

    public Double saldo() {
        return saldoUsuario;
    }

}
