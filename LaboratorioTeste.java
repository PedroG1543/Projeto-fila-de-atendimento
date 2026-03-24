import java.util.Scanner;

public class LaboratorioTeste {
    private static Fila fila = new Fila();
    private static Scanner scanner = new Scanner(System.in);
    private static int contadorSenhas = 1;
    
    public static void main(String[] args) {
        int opcao;
        
        do {
            exibirMenu();
            System.out.print("Digite sua opção: ");
            opcao = lerInteiro();
            
            switch (opcao) {
                case 1:
                    solicitarNovaSenha();
                    break;
                case 2:
                    excluirSenha();
                    break;
                case 3:
                    listarTodasSenhas();
                    break;
                case 4:
                    visualizarProximo();
                    break;
                case 5:
                    chamarProximo();
                    break;
                case 6:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
            
            if (opcao != 6) {
                System.out.println("\nPressione Enter para continuar...");
                scanner.nextLine();
            }
            
        } while (opcao != 6);
        
        scanner.close();
    }
    
    private static void exibirMenu() {
        System.out.println("\n=== LABORATÓRIO DE COLETA DE SANGUE ===");
        System.out.println("1. Solicitar nova senha");
        System.out.println("2. Excluir uma senha");
        System.out.println("3. Listar todas as senhas");
        System.out.println("4. Visualizar próximo a ser atendido");
        System.out.println("5. Chamar próximo paciente");
        System.out.println("6. Sair");
        System.out.println("=====================================");
    }
    
    private static void solicitarNovaSenha() {
        System.out.println("\n--- SOLICITAR NOVA SENHA ---");
        System.out.println("1. Senha Comum");
        System.out.println("2. Senha Prioritária");
        System.out.print("Digite o tipo de senha: ");
        
        int tipo = lerInteiro();
        String senha;
        
        switch (tipo) {
            case 1:
                senha = "C" + String.format("%03d", contadorSenhas++);
                fila.enfileirar(senha, false);
                break;
            case 2:
                senha = "P" + String.format("%03d", contadorSenhas++);
                fila.enfileirar(senha, true);
                break;
            default:
                System.out.println("Tipo de senha inválido!");
        }
    }
    
    private static void excluirSenha() {
        System.out.println("\n--- EXCLUIR SENHA ---");
        System.out.print("Digite a senha a ser excluída (ex: C001, P002): ");
        String senha = scanner.nextLine().trim().toUpperCase();
        
        if (senha.isEmpty()) {
            System.out.println("Senha não pode ser vazia!");
            return;
        }
        
        fila.removerSenha(senha);
    }
    
    private static void listarTodasSenhas() {
        System.out.println("\n--- LISTAR TODAS AS SENHAS ---");
        fila.listarSenhas();
    }
    
    private static void visualizarProximo() {
        System.out.println("\n--- PRÓXIMO A SER ATENDIDO ---");
        String proximo = fila.proximoAtendimento();
        
        if (proximo != null) {
            System.out.println("Próximo paciente a ser chamado: " + proximo);
            
            if (proximo.startsWith("P")) {
                int restantes = 3 - fila.getContadorPrioritariosAtendidos();
                System.out.println("Atendimentos prioritários restantes antes de chamar um comum: " + restantes);
            }
        } else {
            System.out.println("Não há pacientes na fila!");
        }
    }
    
    private static void chamarProximo() {
        System.out.println("\n--- CHAMAR PRÓXIMO PACIENTE ---");
        String chamado = fila.desenfileirar();
        
        if (chamado != null) {
            System.out.println("📢 CHAMANDO PACIENTE: " + chamado);
            System.out.println("Por favor, dirija-se ao guichê de coleta.");
            
            if (chamado.startsWith("P")) {
                System.out.println("(Paciente Prioritário)");
            } else {
                System.out.println("(Paciente Comum)");
            }
        } else {
            System.out.println("Não há pacientes na fila para chamar!");
        }
    }
    
    private static int lerInteiro() {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Valor inválido! Digite um número inteiro: ");
            }
        }
    }
}
