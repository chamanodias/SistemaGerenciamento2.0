
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SistemaIntegrado {
    private Restaurante restaurante;
    private List<Evento> eventos;
    private Clinica clinica;

    public SistemaIntegrado() {
        this.restaurante = new Restaurante();
        this.eventos = new ArrayList<>();
        this.clinica = new Clinica();
    }
    public void cadastrarEvento(String nome, String data, Local local, int capacidadeMaxima) {
        Evento evento = new Evento(nome, data, local, capacidadeMaxima);
        eventos.add(evento);
        System.out.println("Evento cadastrado: " + nome);
    }

    
    public void exibirMesasOcupadas() {
        int mesasOcupadas = 0;
        for (Mesa mesa : restaurante.getMesas()) {
            if (mesa.isOcupada()) {
                mesasOcupadas++;
            }
        }
        System.out.println("Número de mesas ocupadas: " + mesasOcupadas);
    }

    public void contarPedidos() {
        int totalPedidos = 0;
        for (Mesa mesa : restaurante.getMesas()) {
            totalPedidos += mesa.getPedidos().size();
        }
        System.out.println("Número total de pedidos: " + totalPedidos);
    }

    public void calcularReceitaTotal() {
        double receitaTotal = 0;
        for (Mesa mesa : restaurante.getMesas()) {
            receitaTotal += mesa.getTotalPedido();
        }
        System.out.println("Receita total gerada pelo restaurante: R$ " + receitaTotal);
    }

    public void exibirEventos() {
        if (eventos.isEmpty()) {
            System.out.println("Nenhum evento cadastrado.");
            return;
        }
    
        System.out.println("Eventos cadastrados:");
        for (Evento evento : eventos) {
            System.out.println("Nome: " + evento.getNome() + ", Data: " + evento.getData() + ", Local: " + evento.getLocal().getNomeLocal());
        }
    }
    
    public int contarEventos() {
        return eventos.size();
    }

    public int totalParticipantes() {
        int totalParticipantes = 0;
    
        for (Evento evento : eventos) {
            totalParticipantes += evento.getParticipante().size();
        }
    
        return totalParticipantes;
    }

    public int contarConsultas() {
        return clinica.getConsultas().size();
    }


    public void exibirMenuPrincipal(Scanner scanner) {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n========== MENU INTEGRADO ==========");
            System.out.println("1. Gerenciar Restaurante ");
            System.out.println("2. Administrar Eventos ");
            System.out.println("3. Acessar Clínica Médica ");
            System.out.println("4. Análise de Dados ");
            System.out.println("5. Encerrar Sistema ");
            System.out.print("Selecione a opção desejada: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    gerenciarRestaurante(scanner);
                    break;
                case 2:
                    gerenciarEventos(scanner);
                    break;
                case 3:
                    gerenciarClinica(scanner);
                    break;
                case 4:
                    responderPerguntas(scanner);
                    break;
                case 5:
                    System.out.println("\nObrigado por utilizar o sistema!!!");
                    System.out.println("Encerrando...");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void gerenciarRestaurante(Scanner scanner) {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\nMENU DO RESTAURANTE:");
            System.out.println("1. Exibir Menu");
            System.out.println("2. Fazer Pedido");
            System.out.println("3. Fechar Conta");
            System.out.println("4. Buscar Pedido");
            System.out.println("5. Liberar Mesa");
            System.out.println("6. Cancelar Pedido");
            System.out.println("7. Gerar Relatório");
            System.out.println("8. Voltar");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    restaurante.exibirMenu();
                    break;
                case 2:
                    System.out.print("\nNúmero da Mesa: ");
                    int numeroMesa = scanner.nextInt();
                    restaurante.fazerPedido(numeroMesa, scanner);
                    break;
                case 3:
                    System.out.print("\nNúmero da Mesa: ");
                    int mesa = scanner.nextInt();
                    restaurante.fecharConta(mesa, scanner);
                    break;
                case 4:
                    System.out.print("\nNúmero da Mesa: ");
                    int mesaVerificar = scanner.nextInt();
                    restaurante.verificarPedido(mesaVerificar);
                    break;
                case 5:
                    System.out.print("\nNúmero da Mesa a liberar: ");
                    int mesaLiberar = scanner.nextInt();
                    restaurante.liberarMesa(mesaLiberar);
                    break;
                case 6:
                    System.out.print("\nNúmero da Mesa para cancelar o pedido: ");
                    int mesaCancelar = scanner.nextInt();
                    restaurante.cancelarPedido(mesaCancelar, scanner);
                    break;
                case 7:
                    restaurante.gerarRelatorio();
                    break;
                case 8:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void gerenciarEventos(Scanner scanner) {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\nMENU DE EVENTOS:");
            System.out.println("1. Criar Evento");
            System.out.println("2. Registrar Participante");
            System.out.println("3. Gerar Relatório do Evento");
            System.out.println("4. Voltar");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    criarEvento(scanner);
                    break;
                case 2:
                    registrarParticipante(scanner);
                    break;
                case 3:
                    gerarRelatorioEventos(scanner);
                    break;
                case 4:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void criarEvento(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Nome do Evento: ");
        String nome = scanner.nextLine();
        System.out.print("Data do Evento: ");
        String data = scanner.nextLine();
        System.out.print("Local do Evento: ");
        String localNome = scanner.nextLine();
        System.out.print("Endereço do Evento: ");
        String endereco = scanner.nextLine();
        System.out.print("Capacidade Máxima: ");
        int capacidadeMaxima = scanner.nextInt();

        Local local = new Local(localNome, endereco);
        Evento evento = new Evento(nome, data, local, capacidadeMaxima);
        eventos.add(evento);
        System.out.println("Evento criado com sucesso!");
    }

    private void registrarParticipante(Scanner scanner) {
        System.out.print("Digite o nome do evento para registrar o participante: ");
        scanner.nextLine();
        String nomeEvento = scanner.nextLine();
        Evento evento = eventos.stream()
                .filter(e -> e.getNome().equalsIgnoreCase(nomeEvento))
                .findFirst()
                .orElse(null);

        if (evento != null) {
            System.out.print("Nome do Participante: ");
            String nomeParticipante = scanner.nextLine();
            System.out.print("Email do Participante: ");
            String email = scanner.nextLine();
            Participante participante = new Participante(nomeParticipante, email);
            evento.registrarParticipante(participante);
        } else {
            System.out.println("Evento não encontrado.");
        }
    }

    private void gerarRelatorioEventos(Scanner scanner) {
        System.out.print("Digite o nome do evento para gerar o relatório: ");
        scanner.nextLine();
        String nomeEvento = scanner.nextLine();
        Evento evento = eventos.stream()
                .filter(e -> e.getNome().equalsIgnoreCase(nomeEvento))
                .findFirst()
                .orElse(null);

        if (evento != null) {
            evento.gerarRelatorio();
        } else {
            System.out.println("Evento não encontrado.");
        }
    }

    private void gerenciarClinica(Scanner scanner) {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\nMENU DA CLÍNICA:");
            System.out.println("1. Cadastrar Médico");
            System.out.println("2. Cadastrar Paciente");
            System.out.println("3. Agendar Consulta");
            System.out.println("4. Exibir Consultas");
            System.out.println("5. Exibir Pacientes");
            System.out.println("6. Exibir Médicos");
            System.out.println("7. Voltar");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    clinica.criarMedico(scanner);
                    break;
                case 2:
                    clinica.criarPaciente(scanner);
                    break;
                case 3:
                    clinica.criarConsulta(scanner);
                    break;
                case 4:
                    exibirConsultas();
                    break;
                case 5:
                    exibirPacientes();
                    break;
                case 6:
                    exibirMedicos();
                    break;
                case 7:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
    
    private void exibirConsultas() {
        if (clinica.getConsultas().isEmpty()) {
            System.out.println("Nenhuma consulta agendada.");
            return;
        }
        for (Consulta consulta : clinica.getConsultas()) {
            clinica.exibirDadosConsulta(consulta);
        }
    }
    
    private void exibirPacientes() {
        if (clinica.getPacientes().isEmpty()) {
            System.out.println("Nenhum paciente cadastrado.");
        } else {
            for (Paciente p : clinica.getPacientes()) {
                clinica.exibirDadosPaciente(p);
                System.out.println();
            }
        }
    }
    
    private void exibirMedicos() {
        if (clinica.getMedicos().isEmpty()) {
            System.out.println("Nenhum médico cadastrado.");
        } else {
            for (Medico m : clinica.getMedicos()) {
                clinica.exibirDadosMedico(m);
                System.out.println();
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaIntegrado sistema = new SistemaIntegrado();
        sistema.exibirMenuPrincipal(scanner);
        scanner.close();
    }     

    private void eventoComMaisParticipantes() {
        Evento eventoComMaisParticipantes = null;

        for (Evento evento : eventos) {
            if (eventoComMaisParticipantes == null || evento.getParticipante().size() > eventoComMaisParticipantes.getParticipante().size()) {
                eventoComMaisParticipantes = evento;
            }
        }

        if (eventoComMaisParticipantes != null) {
            System.out.println("O evento com mais participantes é: " + eventoComMaisParticipantes.getNome() +
                               " com " + eventoComMaisParticipantes.getParticipante().size() + " participantes.");
        } else {
            System.out.println("Nenhum evento encontrado.");
        }
    }

    private void eventoMaisRentavel() {
        Evento eventoMaisRentavel = null;
        double maiorReceita = 0;
        double precoPorParticipante = 100;

        for (Evento evento : eventos) {
            double receitaAtual = evento.getParticipante().size() * precoPorParticipante;
            if (eventoMaisRentavel == null || receitaAtual > maiorReceita) {
                maiorReceita = receitaAtual;
                eventoMaisRentavel = evento;
            }
        }

        if (eventoMaisRentavel != null) {
            System.out.println("O evento mais rentável é: " + eventoMaisRentavel.getNome() +
                               " com uma receita de R$ " + maiorReceita);
        } else {
            System.out.println("Nenhum evento encontrado.");
        }
    }

    public void medicoMaisSolicitado() {
        if (clinica.getMedicos().isEmpty()) {
            System.out.println("Nenhum médico registrado.");
            return;
        }

        Medico medicoMaisSolicitado = null;
        int maxConsultas = 0;

        for (Medico medico : clinica.getMedicos()) {
            if (medico.getContadorDeConsultas() > maxConsultas) {
                maxConsultas = medico.getContadorDeConsultas();
                medicoMaisSolicitado = medico;
            }
        }

        if (medicoMaisSolicitado != null) {
            System.out.println("\nO médico mais solicitado é: " + medicoMaisSolicitado.getNomeMedico() + " com " + maxConsultas + " consultas.\n");
        }
    }

    public void datasHorariosDeUmMedico(Scanner scanner) {
        if (clinica.getConsultas().isEmpty()) {
            System.out.println("Nenhuma consulta cadastrada.");
            return;
        }
    
        scanner.nextLine();
        System.out.print("\nDigite o nome do médico: ");
        String entrada = scanner.nextLine();
    
        Medico medicoEncontrado = null;
    
        for (Medico medico : clinica.getMedicos()) {
            if (medico.getNomeMedico().equalsIgnoreCase(entrada)) {
                medicoEncontrado = medico;
                break;
            }
        }
    
        if (medicoEncontrado == null) {
            System.out.println("Médico não encontrado.\n");
            return;
        }
    
        System.out.println("\nConsultas agendadas para o médico " + medicoEncontrado.getNomeMedico() + ":");
    
        boolean encontrouConsulta = false;
    
        for (Consulta consulta : clinica.getConsultas()) {
            if (consulta.getMedico().equals(medicoEncontrado)) {
                System.out.println("Data: " + consulta.getData() + " | Horário: " + consulta.getHorario());
                encontrouConsulta = true;
            }
        }
    
        System.out.println("\n");
    
        if (!encontrouConsulta) {
            System.out.println("Este médico não tem consultas agendadas.\n");
        }
    }
    

    public void pratoMaisPedido() {
        Map<String, Integer> contadorPratos = new HashMap<>();
        List<Mesa> mesas = restaurante.getMesas();
        for (Mesa mesa : mesas) {
            if (mesa.isOcupada()) {
                for (ItemDoPedido item : mesa.getPedidos()) {
                    String nomePrato = item.getNome();
                    contadorPratos.put(nomePrato, contadorPratos.getOrDefault(nomePrato, 0) + 1);
                }
            }
        }

        String pratoMaisPedido = null;
        int maxPedidos = 0;

        for (Map.Entry<String, Integer> entry : contadorPratos.entrySet()) {
            if (entry.getValue() > maxPedidos) {
                maxPedidos = entry.getValue();
                pratoMaisPedido = entry.getKey();
            }
        }

        if (pratoMaisPedido != null) {
            System.out.println("O prato mais pedido é: " + pratoMaisPedido + " (Total: " + maxPedidos + ")");
        } else {
            System.out.println("Nenhum prato foi pedido.");
        }
    }

    public void mesaMaisRentavel() {
        double maiorTotal = 0;
        Mesa mesaMaisRentavel = null;
    
        List<Mesa> mesas = restaurante.getMesas();
    
        for (Mesa mesa : mesas) {
            if (mesa.isOcupada()) {
                double totalMesa = mesa.getTotalPedido();
                if (totalMesa > maiorTotal) {
                    maiorTotal = totalMesa;
                    mesaMaisRentavel = mesa;
                }
            }
        }
    
        if (mesaMaisRentavel != null) {
            System.out.println("A mesa mais rentável é a mesa " + mesaMaisRentavel.getNumero() + " com total de R$ " + maiorTotal);
        } else {
            System.out.println("Nenhuma mesa gerou receita.");
        }
    }    

    private void responderPerguntas(Scanner scanner) {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n========== ANÁLISE DE DADOS ==========");
            System.out.println("1. Perguntas sobre o Restaurante");
            System.out.println("2. Perguntas sobre Eventos");
            System.out.println("3. Perguntas sobre a Clínica");
            System.out.println("4. Voltar ao menu principal");
            System.out.print("Selecione a opção desejada: ");
            int opcaoSistema = scanner.nextInt();
    
            switch (opcaoSistema) {
                case 1:
                    perguntasRestaurante(scanner);
                    break;
                case 2:
                    perguntasEventos(scanner);
                    break;
                case 3:
                    perguntasClinica(scanner);
                    break;
                case 4:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
    
    private void perguntasRestaurante(Scanner scanner) {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n========== ANÁLISE DE DADOS ==========");
            System.out.println("1. Quantas mesas estão ocupadas no restaurante?");
            System.out.println("2. Qual é o número total de pedidos feitos?");
            System.out.println("3. Voltar ao menu principal");
            System.out.print("Selecione a opção desejada: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    exibirMesasOcupadas();
                    break;
                case 2:
                    contarPedidos();
                    break;
                case 3:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
    
    private void perguntasEventos(Scanner scanner) {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n========== PERGUNTAS SOBRE EVENTOS ==========");
            System.out.println("1. Qual evento possui mais participantes?");
            System.out.println("2. Qual evento gerou a maior receita?");
            System.out.println("3. Exibir lista de eventos cadastrados");
            System.out.println("4. Voltar ao menu de análise de dados");
            System.out.print("Selecione a opção desejada: ");
            int opcao = scanner.nextInt();
    
            switch (opcao) {
                case 1:
                    eventoComMaisParticipantes();
                    break;
                case 2:
                    eventoMaisRentavel();
                    break;
                case 3:
                    exibirEventos();
                    break;
                case 4:
                    continuar = false;  
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
    
    
    private void perguntasClinica(Scanner scanner) {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n========== PERGUNTAS SOBRE A CLÍNICA ==========");
            System.out.println("1. Qual médico foi mais solicitado?");
            System.out.println("2. Consultas agendadas para um médico específico");
            System.out.println("3. Exibir total de pacientes cadastrados");
            System.out.println("4. Médicos disponíveis na clínica");
            System.out.println("5. Voltar ao menu de análise de dados");
            System.out.print("Selecione a opção desejada: ");
            int opcao = scanner.nextInt();
    
            switch (opcao) {
                case 1:
                    medicoMaisSolicitado();
                    break;
                case 2:
                    datasHorariosDeUmMedico(scanner);
                    break;
                case 3:
                    exibirPacientes();
                    break;
                case 4:
                    exibirMedicos();
                    break;
                case 5:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
    
    
}