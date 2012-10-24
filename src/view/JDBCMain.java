package view;
import java.util.Scanner;
import repository.RepositorioJDBC;
import exception.ExcecaoBancoDados;

public class JDBCMain {
	public static void main(String[] args) {   
		RepositorioJDBC repositorio = RepositorioJDBC.instanciar();
		Scanner s = new Scanner(System.in);
		int option = 1;
		
		while(option != 0){
			System.out.println("O que voc� deseja fazer?");
			System.out.println("1 - Inserir / 2 - Remover / 3 - Listar / 0 - Sair");
			option = s.nextInt();
			
			if(option == 1){
				System.out.println("Digite o nome que voc� deseja inserir:");
				String nome = s.next();
				try {
					repositorio.incluir(nome);
					System.out.println("Inserido com sucesso!");
				} catch (ExcecaoBancoDados e) {
					System.out.println(e.getMessage());
					if (e.getCause() != null) {
						e.getCause().printStackTrace();
					}
				}
			} else if(option == 2){
				try {
					System.out.println("Digite o id do nome que voc� deseja remover");
					int id = s.nextInt();
					repositorio.remover(id);
					System.out.println("Registro removido com sucesso!");
				} catch (ExcecaoBancoDados e){
					System.out.println(e.getMessage());
					if (e.getCause() != null) {
						e.getCause().printStackTrace();
					}
				}
			} else if(option == 3){
				try {
					repositorio.listar();
				} catch (ExcecaoBancoDados e){
					System.out.println(e.getMessage());
					if (e.getCause() != null) {
						e.getCause().printStackTrace();
					}
				}
			} else {
				System.out.println("Digite uma op��o valida");
			}
		}
	}
}
