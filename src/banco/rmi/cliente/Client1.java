package banco.rmi.cliente;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JOptionPane;

import banco.rmi.remotebase.IRemoteBanco;

public class Client1 {
	public static void main(String[] args) {
		System.out.println("Registrando no servidor remoto...");

		try {
			Registry registry = LocateRegistry.getRegistry(9876);

			IRemoteBanco stub = (IRemoteBanco) registry.lookup("servidor_banco");

			int opcao = 0;
			while (opcao != 4) {
				opcao = Integer.parseInt(JOptionPane.showInputDialog(
						"Escolha uma das Opções:" + "\n1 - Saque" + "\n2 - Deposito" + "\n3 - Saldo" + "\n4 - Sair"+"\n5 - Ajuda"));
				System.out.println(opcao);
				
				switch (opcao) {
					case 1:
						double valorsaq = Double.parseDouble(JOptionPane.showInputDialog("Insira o valor do saque:"));
						if(stub.saque(1, valorsaq) == 0) {
							JOptionPane.showMessageDialog(null, "Saldo Insuficiente!");
						}else {
							stub.saque(1, valorsaq);
						}
						break;
						
					case 2:
						double valorde = Double.parseDouble(JOptionPane.showInputDialog("Insira o valor do Deposito:"));
						stub.deposito(1, valorde);
						break;
						
					case 3:
						JOptionPane.showMessageDialog(null, stub.saldo(1));
						break;
					case 5:
						
						JOptionPane.showMessageDialog(null, "Desculpe não posso te ajudar, sou um programa basico... se quiser me ajudar "
								+ "faça uma doação de bitcoin para minha carteira.. kkkkk rsrsrs :-)");
						break;
						
					default:
						JOptionPane.showMessageDialog(null, "Opção Invalida");
						break;
				}
			}

		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
}
