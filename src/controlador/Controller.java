/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import model.bean.Conta;
import view.telaCadastro;
import view.telaInicial;
import view.telaTransferencia;
import view.telaUsuario;

/**
 *
 * @author anton
 */
public class Controller {

    Conta contaUsuario;

    public void executarTelaCadastro() {
        telaCadastro telaCadastro = new telaCadastro();
        telaCadastro.setVisible(true);
    }

    public void executarTelaInicial() {
        telaInicial telaInicial = new telaInicial();
        telaInicial.setVisible(true);
    }

    public void executarTelaTransferencia() {
        telaTransferencia telaTransferencia = new telaTransferencia();
        telaTransferencia.setVisible(true);
    }

    public void executarTelaUsuario() {
        telaUsuario telaUsuario = new telaUsuario();
        telaUsuario.setVisible(true);
    }

    public Conta getConta() {

        return contaUsuario;
    }

    public void setConta(Conta c) {

        this.contaUsuario = c;
    }

    public static void main(String[] args) {
        Controller c = new Controller();
        c.executarTelaInicial();
    }

}
