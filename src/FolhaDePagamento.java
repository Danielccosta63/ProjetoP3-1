import java.util.Scanner;


public class FolhaDePagamento {
	static Scanner scan = new Scanner(System.in);
	static Employee funcionario[] = new Employee[256];
	public static int alteracao=0;
	public static int idAux=0;
	
	public static void add_employee(Employee funcionario[], int indiceFuncionarios) {
		funcionario[indiceFuncionarios] = new Employee();
		int tipo;
		
		System.out.print("Digite o nome do empregado: ");
		scan.nextLine();
		funcionario[indiceFuncionarios].nome = scan.nextLine();

		System.out.print("\nDigite o endereço do empregado: ");
		funcionario[indiceFuncionarios].endereco = scan.nextLine();

		System.out.print("\nDigite o tipo (1-Horista, 2-Assalariado ou 3-Commissionado: ");
		tipo= scan.nextInt();
		funcionario[indiceFuncionarios].tipo = tipo;
		
		if(tipo==1){
			System.out.print("\nSalario por hora: ");
			funcionario[indiceFuncionarios].salarioHora = scan.nextDouble();
		}
		else if(tipo==2){
			System.out.print("\nSalario mensal: ");
			funcionario[indiceFuncionarios].salarioMensal = scan.nextDouble();
			System.out.print("\nVencimento: ");
			funcionario[indiceFuncionarios].vencimento = scan.nextInt();
		}
		else if(tipo==3){
			System.out.print("\nSalario mensal: ");
			funcionario[indiceFuncionarios].salarioMensal = scan.nextDouble();
			System.out.print("\nVencimento: ");
			funcionario[indiceFuncionarios].vencimento = scan.nextInt();
			System.out.println("\nComissão: ");
			funcionario[indiceFuncionarios].comissao = scan.nextDouble() / 100;
		}
		System.out.print("Pertence ao sindicato? 1-Sim 2-Não ");
		tipo=scan.nextInt();
		if(tipo==1){
			funcionario[indiceFuncionarios].sindicato = 1;
		}		
		alteracao=1;
		idAux=indiceFuncionarios;
		System.out.println("---------------------------------");
		System.out.println("Empregado adicionado com sucesso!");
		System.out.printf("O ID do empregado é [%d]\n", indiceFuncionarios);
	}

	public static void remove_employee(Employee funcionario[], int total_funcionarios) {
			int id;
			System.out.print("Por favor digite o código do empregado: ");
			id = scan.nextInt();
			funcionario[id].ativo = false;
			alteracao=2;
			idAux=id;
			System.out.println("Empregado removido com sucesso!");
	}

	public static void add_card(Employee funcionario[]) {
		System.out.println("Digitar cartão de entrada ou saida?");
		int op, id, dia, hora, min;
		System.out.println("1 - Entrada");
		System.out.println("2 - Saida");
		op = scan.nextInt();

		if (op == 1) {
			System.out.print("Digite o ID do funfionario: ");
			id = scan.nextInt();
			System.out.println("\nDigit o dia: ");
			dia = scan.nextInt();
			System.out.print("\nDigite a hora: ");
			hora = scan.nextInt();
			System.out.println("\nDigite os minutos: ");
			min = scan.nextInt();
			funcionario[id].cartaoDePonto[0][dia - 1] = (hora * 60) + min;
			alteracao=31;
		} else if (op == 2) {
			System.out.print("Digite o ID do funfionario: ");
			id = scan.nextInt();
			System.out.print("\nDigit o dia: ");
			dia = scan.nextInt();
			System.out.print("\nDigite a hora: ");
			hora = scan.nextInt();
			System.out.print("\nDigite os minutos: ");
			min = scan.nextInt();
			funcionario[id].cartaoDePonto[2][dia - 1] = (hora * 60) + min;
			funcionario[id].salarioAcumulado += (funcionario[id].cartaoDePonto[2][dia - 1]
					- funcionario[id].cartaoDePonto[0][dia - 1]) * funcionario[id].salarioHora;
			alteracao=32;
		}
	}

	public static void add_sale(Employee funcionario[]) {
		int dia, id;
		double valor;
		System.out.print("Digite o ID do empregado: ");
		id = scan.nextInt();
		System.out.print("\nDigite o dia: ");
		dia = scan.nextInt();
		System.out.print("\nDigite o valor das vendas: ");
		valor = scan.nextInt();
		funcionario[id].vendas[dia - 1] = valor * funcionario[id].comissao;
		alteracao=4;
	}

	public static void add_rate(Employee funcionario[]) {
		int id;
		double valor;
		System.out.print("Digite o ID do empregado: ");
		id = scan.nextInt();
		System.out.println("\nDigite o valor da taxa :");
		valor = scan.nextDouble();
		funcionario[id].taxas += valor / 100;
		alteracao=5;
	}

	public static void change_data(Employee funcionario[]) {
		int id;
		System.out.println("Digite ID do empregado: ");
		id = scan.nextInt();

		System.out.print("Digite o novo nome do empregado: ");
		scan.nextLine();
		funcionario[id].nome = scan.nextLine();

		System.out.print("\nDigite o novo endereço do empregado: ");
		funcionario[id].endereco = scan.nextLine();

		System.out.print("\nDigite o novo tipo (1-Hourly, 2-Salaried ou 3-Commissioned: ");
		funcionario[id].tipo = scan.nextInt();

		System.out.print("\nNovo salario por hora: ");
		funcionario[id].salarioHora = scan.nextDouble();

		System.out.print("\nNovo salario mensal: ");
		funcionario[id].salarioMensal = scan.nextDouble();

		System.out.println("\nNova Comissão: ");
		funcionario[id].comissao = scan.nextDouble() / 100;
		alteracao=6;
	}
	
	public static void uno(Employee funcionario[]){
		if(alteracao==1){
			funcionario[idAux].ativo = false;
		}
		if(alteracao==2){
			funcionario[idAux].ativo = true;
		}
	}
	
	public static void test(Employee funcionario[]) {

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
			System.out.println("5 -> Adicionar taxas");
			System.out.println("6 -> Alterar dados");
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
			else if (op == 5)
				add_rate(funcionario);
			else if (op == 6)
				change_data(funcionario);
			else if (op == 0) {
				System.exit(0);
			}
		} while (op != 0);

	}

}
