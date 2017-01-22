import java.util.Scanner;

public class FolhaDePagamento {
	static Scanner scan = new Scanner(System.in);
	static Employee funcionario[] = new Employee[256];

	public static void add_employee(Employee funcionario[], int indiceFuncionarios) {
		funcionario[indiceFuncionarios] = new Employee();

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
		funcionario[indiceFuncionarios].comissao = scan.nextDouble() / 100;

		System.out.println("---------------------------------");
		System.out.println("Empregado adicionado com sucesso!");
		System.out.printf("O ID do empregado é [%d]\n", indiceFuncionarios);
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
	}

	public static void add_card(Employee funcionario[]) {
		System.out.println("Digitar cartão de entrada ou saida?");
		int op, id, dia, hora, min;
		System.out.println("1 - Entrada");
		System.out.println("2 - Saida");
		op = scan.nextInt();

		if (op == 1) {
			System.out.println("Digite o ID do funfionario");
			id = scan.nextInt();
			System.out.println("Digit o dia");
			dia = scan.nextInt();
			System.out.println("Digite a hora");
			hora = scan.nextInt();
			System.out.println("Digite os minutos");
			min = scan.nextInt();
			funcionario[id].cartaoDePonto[0][dia - 1] = (hora * 60) + min;
		} else if (op == 2) {
			System.out.println("Digite o ID do funfionario");
			id = scan.nextInt();
			System.out.println("Digit o dia");
			dia = scan.nextInt();
			System.out.println("Digite a hora");
			hora = scan.nextInt();
			System.out.println("Digite os minutos");
			min = scan.nextInt();
			funcionario[id].cartaoDePonto[2][dia - 1] = (hora * 60) + min;
		}
	}

	public static void add_sale(Employee funcionario[]) {
		int dia, id;
		double valor;
		System.out.print("Digite o ID do empregado: ");
		id = scan.nextInt();
		System.out.print("Digite o dia");
		dia = scan.nextInt();
		System.out.print("Digite o valor das vendas");
		valor = scan.nextInt();
		funcionario[id].vendas[dia - 1] = valor * funcionario[id].comissao;
	}

	public static void test(Employee funcionario[], int id) {

	}

	public static void main(String[] args) {

		int op, total_funcionarios = 0;

		do {
			System.out.println("Sistema de Folha de Pagamento");
			System.out.println("-----------------------------");
			System.out.println("Selecione uma opção\n\n");
			System.out.println("1 -> Adicionar um empregado");
			System.out.println("2 -> Remover um empregado");
			System.out.println("3 -> Adicionar cartão de ponto");
			System.out.println("4 -> Adicionar vendas");
			System.out.println("0 -> Sair");

			op = scan.nextInt();

			if (op == 1) {
				add_employee(funcionario, total_funcionarios);
				total_funcionarios++;
			} else if (op == 2)
				remove_employee(funcionario, total_funcionarios);
			else if (op == 3)
				add_card(funcionario);
			else if (op == 4)
				add_sale(funcionario);
			else if (op == 0) {
				System.exit(0);
			}
		} while (op != 0);

	}

}
