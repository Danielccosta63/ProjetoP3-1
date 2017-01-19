import java.util.Scanner;

public class FolhaDePagamento {
	static Scanner scan = new Scanner(System.in);
	static Employee funcionario[] = new Employee[256];

	public static void add_employee(Employee funcionario[], int indiceFuncionarios) {
		funcionario[indiceFuncionarios] = new Employee();
		int op;

		System.out.print("Digite o nome do empregado: ");
		scan.nextLine();
		funcionario[indiceFuncionarios].nome = scan.nextLine();

		System.out.print("\nDigite o endereço do empregado: ");
		funcionario[indiceFuncionarios].endereco = scan.nextLine();

		System.out.print("\nDigite o tipo (1-Hourly, 2-Salaried ou 3-Commissioned: ");
		funcionario[indiceFuncionarios].tipo = scan.nextInt();

		System.out.print("\nSalario por hora: ");
		funcionario[indiceFuncionarios].salarioHora = scan.nextDouble();

		System.out.print("\nSalario mensal: ");
		funcionario[indiceFuncionarios].salarioMensal = scan.nextDouble();

		System.out.println("\nComissão: ");
		funcionario[indiceFuncionarios].comissao = scan.nextDouble();

		System.out.println("---------------------------------");
		System.out.println("Empregado adicionado com sucesso!");
		System.out.printf("O códido do empregado é [%d]\n", indiceFuncionarios);
		System.out.println("1-Retornar ao Menu");
		System.out.println("2-Encerrar");
		op = scan.nextInt();
		if (op == 1) {
			menu();
		} else if (op == 2) {
			System.exit(0);
		}
	}

	public static void remove_employee(Employee funcionario[], int total_funcionarios) {
		System.out.println("Deseja remover pelo código de empregado ou pelo nome do empregado?");
		System.out.println("1-Codigo de empregado");
		System.out.println("2-Nome do empregado");

		int op, cod;
		String nome;
		op = scan.nextInt();
		if (op == 1) {
			System.out.print("Por favor digite o código do empregado: ");
			cod = scan.nextInt();
			funcionario[cod].ativo = false;
		} else if (op == 2) {
			System.out.print("Por favor digite o nome do empregado: ");
			nome = scan.next();
			for (int i = 0; i < total_funcionarios + 1; i++) {
				if (nome.equals(funcionario[i].nome)) {
					funcionario[i].ativo = false;
					break;
				}
			}
		}
		System.out.println("Empregado removido com sucesso!");
		System.out.println("1-Retornar ao Menu");
		System.out.println("2-Encerrar");
		op = scan.nextInt();
		if (op == 1) {
			menu();
		} else if (op == 2) {
			System.exit(0);
		}
	}

	public static void menu() {
		System.out.println("Sistema de Folha de Pagamento");
		System.out.println("-----------------------------");
		System.out.println("Selecione uma opção\n\n");
		System.out.println("1 -> Adicionar um empregado");
		System.out.println("2 -> Remover um empregado");

		int op, total_funcionarios = 0;
		op = scan.nextInt();

		if (op == 1) {
			add_employee(funcionario, total_funcionarios);
			total_funcionarios++;
		} else if (op == 2) {
			remove_employee(funcionario, total_funcionarios);
		}
	}

	public static void main(String[] args) {
		menu();

	}

}
