import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.runtime.SwitchBootstraps;
import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;

public class Main {
    /**
     * Função para invocar o menu de entrada com opção ADMIN,CLIENTE e SAIR
     */
    public static void menuEntrada(String[][] matrizAnimais, String[][] matrizClientes, String[][] matrizInteracoes) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        int opcao;
        String username;
        String password;

        do {
            System.out.println("===== MENU ADMIN CODESAVANNA  =====");
            System.out.println("1 - ADMIN");
            System.out.println("2 - CLIENTE");
            System.out.println("3 - SAIR");

            System.out.print("\nOpção: ");
            opcao = input.nextInt();

            switch (opcao) {
                case 1: // Entrar em menu admin

                    System.out.print("\nUsername: ");
                    username = input.next();

                    System.out.print("Password: ");
                    password = input.next();

                    if (username.equals("admin") && password.equals("code")) {
                        menuAdmin(matrizAnimais, matrizClientes, matrizInteracoes);
                    } else {
                        System.out.println("Login incorreto");

                    }
                    break;

                case 2: // Entrar em menu cliente
                    menuCliente(matrizAnimais, matrizClientes, matrizInteracoes);
                    break;

                case 3: //Sair
                    System.out.println("Obrigada por ter visitado o nosso ZOO");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        } while (opcao != 3);
    }

    /**
     * Função para invocar o Menu Admin com opções para este tipo de usuario
     */
    public static void menuAdmin(String[][] matrizAnimais, String[][] matrizClientes, String[][] matrizInteracoes) throws FileNotFoundException {

        Scanner input = new Scanner(System.in);
        int opcao;
        String[] arrayPalavras = {"VISITA", "ESPETACULO", "ALIMENTACAO", "APADRINHAMENTO"};

        do {

            System.out.println("===== MENU ADMIN CODESAVANNA  =====");
            System.out.println("1 - Listar conteúdo dos ficheiros");
            System.out.println("2 - Estatísticas gerais de interações");
            System.out.println("3 - Receita total por tipo de interação");
            System.out.println("4 - Animal mais popular");
            System.out.println("5 - Top 3 espécies com mais apadrinhamentos");
            System.out.println("6 - Listar padrinhos de um animal");
            System.out.println("7 - Espetáculo mais rentável");
            System.out.println("8 - Ranking de animais em perigo de extinção");
            System.out.println("9 - Estatísticas por habitat");
            System.out.println("10- Voltar");
            System.out.println("0 - Sair");

            System.out.print("\nOpção: ");
            opcao = input.nextInt();


            switch (opcao) {
                case 1: // Listar conteúdo dos ficheiros
                    menuListarConteudoFicheiroAdmin(matrizAnimais, matrizClientes, matrizInteracoes);
                    break;

                case 2: // Estatísticas gerais de interações

                    int countTotal = 0;

                    for (int i = 0; i < arrayPalavras.length; i++) {
                        countTotal = countTotal + imprimirEstatisticas(matrizInteracoes, arrayPalavras[i]);
                    }
                    System.out.println("Total de interações" + " : " + countTotal);

                    for (int i = 0; i < arrayPalavras.length; i++) {
                        System.out.println(arrayPalavras[i] + " : " + imprimirEstatisticas(matrizInteracoes, arrayPalavras[i]));

                    }


                case 3: // Receita total por tipo de interação

                    double countReceitaTotal = 0;

                    for (int i = 0; i < arrayPalavras.length; i++) {
                        System.out.println(arrayPalavras[i] + " : " + imprimirReceita(matrizInteracoes, arrayPalavras[i]));
                    }

                    for (int i = 0; i < arrayPalavras.length; i++) {
                        countReceitaTotal = countReceitaTotal + imprimirReceita(matrizInteracoes, arrayPalavras[i]);
                    }
                    System.out.println("Receita total global" + " : " + countReceitaTotal);

                    break;

                case 4: // Animal mais popular
                    animalMaisPopular(matrizInteracoes, matrizAnimais);

                    break;
                case 5://Top 3 espécies com mais apadrinhamentos
                    break;


                case 6://Listar padrinhos de um animal
                    System.out.println("Digite o Id de um animal");
                    String idAnimal = input.next().toUpperCase();

                    if (animalExiste(matrizAnimais, idAnimal)) {
                        procurarApadrinhamentoPorIdAnimal(matrizInteracoes, matrizClientes, idAnimal);
                    } else {
                        System.out.println("Esse Id não existe, tente outro Id!");
                    }
                    ;
                    break;

                case 7://Espetáculo mais rentável
                    espetaculoMaisRentavel(matrizInteracoes);
                    break;

                case 8://Ranking de animais em perigo de extinção
                    verificaAnimaisEmExintincaoMaisPopulares(matrizInteracoes, matrizAnimais);
                    break;

                case 9://Estatísticas por habitat
                    estatisticasPorHabitat(matrizAnimais, matrizInteracoes);
                    break;

                case 10:
                    menuEntrada(matrizAnimais, matrizClientes, matrizInteracoes);
                    break;

                case 0://Sair
                    imprimirCopyright();

                    break;
            }
        } while (opcao != 0);
    }

    /**
     * Função para invocar o Menu Cliente e mostrar opções para este tipo de usuário
     */
    public static void menuCliente(String[][] matrizAnimais, String[][] matrizClientes, String[][] matrizInteracoes) throws FileNotFoundException {

        Scanner input = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("===== MENU CLIENTE CODE SAVANNA+ =====");
            System.out.println("1 - Ver catálogo de animais por habitat");
            System.out.println("2 - Ver atividades de um animal (espetáculos e alimentações)");
            System.out.println("3 - Simular apadrinhamento de um animal");
            System.out.println("4 - Jogo: adivinha a espécie");
            System.out.println("5 - Voltar");
            System.out.println("0 - Sair");

            System.out.print("\nOpção: ");
            opcao = input.nextInt();

            switch (opcao) {
                case 1: //  Ver catálogo de animais por habitat
                    imprimirAnimaisHabitat(matrizAnimais);
                    break;

                case 2: //Ver atividades de um animal (espetáculos e alimentações)"
                    System.out.println("Digite o Id de um animal");
                    String idAnimal = input.next().toUpperCase();

                    if (animalExiste(matrizAnimais, idAnimal)) {
                        verAtividadesDeUmAnimal(matrizInteracoes, matrizAnimais, idAnimal);
                    } else {
                        System.out.println("Esse Id não existe, tente outro Id!");
                    }
                    ;

                    break;

                case 3: // Simular apadrinhamento de um animal
                    cadastrarNovoApadrinhamento(matrizAnimais);
                    break;

                case 4: //Jogo: adivinha a espécie
                    jogarAdivinhaEspecie(matrizAnimais);

                    break;

                case 5:// voltar
                    menuEntrada(matrizAnimais, matrizClientes, matrizInteracoes);
                    break;


                case 0://Sair
                    imprimirCopyright();

                    break;
            }
        } while (opcao != 0);
    }

    /**
     * Função para contar linhas do ficheiro
     *
     * @param caminho
     * @return o numero de linhas do ficheiro
     * @throws FileNotFoundException
     */
    public static int contarLinhasFicheiro(String caminho) throws FileNotFoundException {

        File ficheiro = new File(caminho);
        Scanner sc = new Scanner(ficheiro);

        int contadorLinhas = 0;

        while (sc.hasNextLine()) {
            contadorLinhas++;
            sc.nextLine();
        }

        return contadorLinhas;

    }

    /**
     * Função para ler os ficheiros para uma matriz
     *
     * @param caminho
     * @return
     * @throws FileNotFoundException
     */
    public static String[][] ficheiroParaMatriz(String caminho) throws FileNotFoundException {

        // Declarar variáveis
        int linhaAtual = 0;

        // Contar as linhas do ficheiro (para saber quantas músicas temos)
        int resultadoContadorDeLinhas = contarLinhasFicheiro(caminho) - 1;

        //contar as colunas do ficheiro
        int resultadoContadorDeColunas = contadorDeColunas(caminho);

        // Criar a matriz à medida
        String[][] matrizCompleta = new String[resultadoContadorDeLinhas][resultadoContadorDeColunas];

        //abrir o ficheiro
        File ficheirosZoo = new File(caminho);
        Scanner sc = new Scanner(ficheirosZoo);

        // Avançar o cabeçalho
        String linha = sc.nextLine();

        while (sc.hasNextLine()) {
            linha = sc.nextLine();
            String[] linhaSeparada = linha.split(";");

            for (int coluna = 0; coluna < matrizCompleta[0].length; coluna++) {
                matrizCompleta[linhaAtual][coluna] = linhaSeparada[coluna];
            }

            linhaAtual++;
        }

        return matrizCompleta;

    }

    /**
     * Função para contar colunas
     *
     * @param caminho
     * @return
     */
    public static int contadorDeColunas(String caminho) throws FileNotFoundException {

        File ficheiroContaColuna = new File(caminho);
        Scanner sc = new Scanner(ficheiroContaColuna);
        int tamanhoLinha = 0;


        if (sc.hasNext()) {
            String linha = sc.nextLine();
            String[] linhaSeparada = linha.split(";");
            tamanhoLinha = linhaSeparada.length;
        }

        return tamanhoLinha;
    }

    /**
     * Função para Menu que lista conteúdos do ficheiro escolhido
     *
     * @param matrizAnimais
     * @param matrizClientes
     * @param matrizInteracoes
     */
    public static void menuListarConteudoFicheiroAdmin(String[][] matrizAnimais, String[][] matrizClientes, String[][] matrizInteracoes) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("===== MENU ADMIN CODESAVANNA  =====");
            System.out.println("1 - Animais");
            System.out.println("2 - Clientes");
            System.out.println("3 - Interações");
            System.out.println("4 - Voltar");

            System.out.print("\nOpção: ");
            opcao = input.nextInt();


            switch (opcao) {
                case 1: // animais
                    imprimirFicheiro(escolherCaminho("animais"));

                    break;

                case 2: // Clientes
                    imprimirFicheiro(escolherCaminho("clientes"));
                    break;

                case 3: // Interações
                    imprimirFicheiro(escolherCaminho("interacoes"));

                    break;

                case 4: // Voltar
                    menuAdmin(matrizAnimais, matrizClientes, matrizInteracoes);
                    break;


            }
        } while (opcao != 4);
    }

    /**
     * Função para imprimir o conteúdo do ficheiro
     *
     * @param caminho
     * @throws FileNotFoundException
     */
    public static void imprimirFicheiro(String caminho) throws FileNotFoundException {
        File ficheiro = new File(caminho);
        Scanner sc = new Scanner(ficheiro);

        while (sc.hasNextLine()) {
            String linha = sc.nextLine();
            System.out.println(linha);
        }
    }

    /**
     * Função para escolher o path de acordo com a opção escolhida
     *
     * @param escolhaDoCaminho
     * @return o path do arquivo csv escolhido
     */
    public static String escolherCaminho(String escolhaDoCaminho) {

        switch (escolhaDoCaminho) {
            case "animais":
                return "ficheiro/animais.csv";
            case "clientes":
                return "ficheiro/clientes.csv";
            case "interacoes":
                return "ficheiro/interacoes.csv";

        }

        return "";
    }

    /**
     * Função para calcularv estatisticas
     *
     * @param matrizEscolhida
     * @param estatisticaPesquisada
     * @return count
     */
    public static int imprimirEstatisticas(String[][] matrizEscolhida, String estatisticaPesquisada) {
        int count = 0;

        for (int linha = 0; linha < matrizEscolhida.length; linha++) {
            if (matrizEscolhida[linha][2].equals(estatisticaPesquisada)) {
                count++;
            }

        }
        return count;

    }

    /**
     * Função que imprime receita
     *
     * @param matrizEscolhida
     * @param receitaPorCategoria
     * @return o valor total de receita por interação
     */
    public static double imprimirReceita(String[][] matrizEscolhida, String receitaPorCategoria) {

        double countTotal = 0;

        for (int linha = 0; linha < matrizEscolhida.length; linha++) {
            if (matrizEscolhida[linha][2].equals(receitaPorCategoria)) {
                double valorPagoMatriz = Double.parseDouble(matrizEscolhida[linha][5]);
                countTotal = countTotal + valorPagoMatriz;
            }

        }
        return countTotal;

    }

    /**
     * Função para descobrir se o animal existe no arquivo utilizando o IdAnimal como comparador
     *
     * @param matrizEscolhida
     * @param idAnimal
     * @return true se o animal existir || false se não existir
     */
    public static boolean animalExiste(String[][] matrizEscolhida, String idAnimal) {
        boolean temIdAnimal = false;

        for (int linha = 0; linha < matrizEscolhida.length; linha++) {
            if (matrizEscolhida[linha][0].equals(idAnimal)) {
                temIdAnimal = true;
            }
        }

        return temIdAnimal;
    }

    /**
     * Fuinção que busca os padrinhos de um animal
     *
     * @param matrizEscolhidaInteracao
     * @param matrizCliente
     * @param idAnimal
     */
    public static void procurarApadrinhamentoPorIdAnimal(String[][] matrizEscolhidaInteracao, String[][] matrizCliente, String idAnimal) {

        for (int linha = 0; linha < matrizEscolhidaInteracao.length; linha++) {
            if (matrizEscolhidaInteracao[linha][3].equals(idAnimal) && matrizEscolhidaInteracao[linha][2].equals("APADRINHAMENTO")) {
                String idCliente = matrizEscolhidaInteracao[linha][1];
                for (int linhaCliente = 0; linhaCliente < matrizCliente.length; linhaCliente++) {
                    if (matrizCliente[linhaCliente][0].equals(idCliente)) {
                        System.out.println();
                        System.out.println("-------------------------------------------------------");
                        System.out.println("Nome do cliente : " + matrizCliente[linhaCliente][1] + "Email: " + matrizCliente[linhaCliente][3]);
                        System.out.println("valor mensal pago : " + Double.parseDouble(matrizEscolhidaInteracao[linha][5]));
                        System.out.println("Nome do plano de apadrinhamento : " + matrizEscolhidaInteracao[linha][4]);
                        System.out.println();
                        System.out.println("-----------------------------------------------------");
                    }

                }
            }
        }
    }

    /**
     * Função para descobrir o mais rentável
     *
     * @param matrizInteracoes
     */
    public static void espetaculoMaisRentavel(String[][] matrizInteracoes) {

        String nomeEventoAtual, idAnimalEspetaculoMaisRentavel = "", nomeEspetaculoMaisRentavel = "";
        double rendimentoEspetaculoMaisRentavel = 0;

        for (int linha = 0; linha < matrizInteracoes.length; linha++) {
            if (matrizInteracoes[linha][2].equals("ESPETACULO")) {

                // declarar as variaveis com valores atuais
                nomeEventoAtual = matrizInteracoes[linha][4];
                double rendimentoTotalEventoAtual = 0;

                // calcular o rendimento do evento (ESPETACULO) atual
                for (int linhaBusca = 0; linhaBusca < matrizInteracoes.length; linhaBusca++) {
                    if (matrizInteracoes[linhaBusca][4].equals(nomeEventoAtual) && matrizInteracoes[linhaBusca][2].equals("ESPETACULO")) {
                        rendimentoTotalEventoAtual += Double.parseDouble(matrizInteracoes[linhaBusca][5]);
                    }
                }
                if (rendimentoTotalEventoAtual > rendimentoEspetaculoMaisRentavel) {
                    nomeEspetaculoMaisRentavel = nomeEventoAtual;
                    rendimentoEspetaculoMaisRentavel = rendimentoTotalEventoAtual;
                    idAnimalEspetaculoMaisRentavel = matrizInteracoes[linha][3];
                }
            }
        }

        System.out.println("Nome Espetaculo mais rentavel: " + nomeEspetaculoMaisRentavel);
        System.out.println("ID Animal: " + idAnimalEspetaculoMaisRentavel);
        System.out.println("Valor arrecadado: " + rendimentoEspetaculoMaisRentavel);

    }

    /**
     * Função para obter os habitats sem repetição
     *
     * @param matrizAnimais
     * @return array com habitats unicos
     */
    public static String[] habitatsUnicos(String[][] matrizAnimais) {

        String[] arrayHabitatsTotal = new String[matrizAnimais.length];
        int indexDisponivel = 0;

        // Este ciclo itera para cada animal da matriz
        for (int linhaAnimais = 0; linhaAnimais < matrizAnimais.length; linhaAnimais++) {

            boolean encontrei = false;

            // matrizAnimais[linhaAnimais][3] é o habitat do animal atual

            // Este ciclo itera para cada habitat do meu array sem duplicados
            for (int habitatAtual = 0; habitatAtual < arrayHabitatsTotal.length; habitatAtual++) {
                if (matrizAnimais[linhaAnimais][3].equals(arrayHabitatsTotal[habitatAtual])) {
                    encontrei = true;
                }
            }

            if (!encontrei) { // encontrei == false
                arrayHabitatsTotal[indexDisponivel] = matrizAnimais[linhaAnimais][3];
                indexDisponivel++;
            }

        }

        int quantidadeDeHabitats = indexDisponivel;

        // Limpeza - colocar o array à medida
        String[] arrayHabitatsMedida = new String[quantidadeDeHabitats];

        for (int i = 0; i < arrayHabitatsMedida.length; i++) {
            arrayHabitatsMedida[i] = arrayHabitatsTotal[i];
        }

        return arrayHabitatsMedida;

    }

    /**
     * Função para contar a quantidade de animais por habitat
     *
     * @param matrizAnimais
     * @param habitat
     * @return contagem nde animais por habitat indicado
     */
    public static int numeroAnimaisHabitat(String[][] matrizAnimais, String habitat) {

        int contagemAnimais = 0;

        for (int linha = 0; linha < matrizAnimais.length; linha++) {
            if (matrizAnimais[linha][3].equals(habitat)) {
                contagemAnimais++;
            }
        }
        return contagemAnimais;
    }

    /**
     * Função para separar os animais pertencentes a cada habitat
     *
     * @param matrizAnimais
     * @param habitat
     * @return array de animais de um habitat
     */
    public static String[] animaisDeUmHabitat(String[][] matrizAnimais, String habitat) {
        String[] animaisDoHabitat = new String[matrizAnimais.length];
        int indexDisponivel = 0;

        for (int linhaAnimal = 0; linhaAnimal < matrizAnimais.length; linhaAnimal++) {
            if (matrizAnimais[linhaAnimal][3].equals(habitat)) {
                // Encontramos um animal deste Habitat
                animaisDoHabitat[indexDisponivel] = matrizAnimais[linhaAnimal][0];
                indexDisponivel++;
            }
        }
        int quantidadeAnimais = indexDisponivel;

        String[] animaisDoHabitatMedida = new String[quantidadeAnimais];

        for (int i = 0; i < animaisDoHabitatMedida.length; i++) {
            animaisDoHabitatMedida[i] = animaisDoHabitat[i];
        }

        return animaisDoHabitatMedida;
    }

    /**
     * Função para contar a quantidade de interações para um animal
     *
     * @param matrizInteracoes
     * @param idAnimal
     * @return contagem de interações
     */
    public static int interacoesAnimal(String[][] matrizInteracoes, String idAnimal) {
        int contagemInteracoes = 0;

        for (int linhaInteracao = 0; linhaInteracao < matrizInteracoes.length; linhaInteracao++) {

            if (matrizInteracoes[linhaInteracao][3].equals(idAnimal)) {
                // Contar a interação
                contagemInteracoes++;
            }
        }
        return contagemInteracoes;
    }

    /**
     * Função para calcular os rendimentos de um animal
     *
     * @param matrizInteracoes
     * @param idAnimal
     * @return rendimento total
     */
    public static double rendimentosAnimal(String[][] matrizInteracoes, String idAnimal) {
        double rendimentoTotal = 0;

        for (int linhaInteracao = 0; linhaInteracao < matrizInteracoes.length; linhaInteracao++) {

            if (matrizInteracoes[linhaInteracao][3].equals(idAnimal)) {
                // Incrementar ao rendimento
                rendimentoTotal += Double.parseDouble(matrizInteracoes[linhaInteracao][5]);
            }
        }
        return rendimentoTotal;
    }

    /**
     * Função para imprimir as estaisticas por habitat
     *
     * @param matrizAnimais
     * @param matrizInteracoes
     */
    public static void estatisticasPorHabitat(String[][] matrizAnimais, String[][] matrizInteracoes) {

        String[] habitatsSemDuplicados = habitatsUnicos(matrizAnimais);

        for (int i = 0; i < habitatsSemDuplicados.length; i++) {
            System.out.println("\nHabitat: " + habitatsSemDuplicados[i]);
            System.out.println("  Nº de Animais: " + numeroAnimaisHabitat(matrizAnimais, habitatsSemDuplicados[i]));

            int numInteracoesTotal = 0;
            double receitaTotal = 0;

            String[] idsAnimaisHabitatAtual = animaisDeUmHabitat(matrizAnimais, habitatsSemDuplicados[i]);

            for (int indexAnimal = 0; indexAnimal < idsAnimaisHabitatAtual.length; indexAnimal++) {
                numInteracoesTotal += interacoesAnimal(matrizInteracoes, idsAnimaisHabitatAtual[indexAnimal]);
                receitaTotal += rendimentosAnimal(matrizInteracoes, idsAnimaisHabitatAtual[indexAnimal]);

            }

            System.out.println("  Nº de Interações: " + numInteracoesTotal);
            System.out.println("  Receita Associada: " + receitaTotal + " €");


        }
    }

    /**
     * Função para retornar o nome do animal por um id
     *
     * @param matrizAnimais
     * @param idAnimal
     * @return nome do animal
     */
    public static String nomeAnimal(String[][] matrizAnimais, String idAnimal) {
        for (int linha = 0; linha < matrizAnimais.length; linha++) {
            if (matrizAnimais[linha][0].equals(idAnimal)) {
                return matrizAnimais[linha][1];
            }
        }

        return "N/A";
    }

    /**
     * Função para imprmir a especie do animal por um id animal
     *
     * @param matrizAnimais
     * @param idAnimal
     * @return especie do animal
     */
    public static String especieAnimal(String[][] matrizAnimais, String idAnimal) {
        for (int linha = 0; linha < matrizAnimais.length; linha++) {
            if (matrizAnimais[linha][0].equals(idAnimal)) {
                return matrizAnimais[linha][2];
            }
        }

        return "N/A";
    }

    /**
     * Função que imprime um habitat correspondente a um idAnimal
     *
     * @param matrizAnimais
     * @param idAnimal
     * @return
     */
    public static String imprimirAnimalPorHabitat(String[][] matrizAnimais, String idAnimal) {
        String nomeHabitat = "";
        for (int buscaHabitatLinha = 0; buscaHabitatLinha < matrizAnimais.length; buscaHabitatLinha++) {
            if (matrizAnimais[buscaHabitatLinha][0].equals(idAnimal)) {
                nomeHabitat = matrizAnimais[buscaHabitatLinha][3];
            }
        }
        return nomeHabitat;
    }

    /**
     * Função para imprimir os animais por habitat
     *
     * @param matrizAnimais
     */
    public static void imprimirAnimaisHabitat(String[][] matrizAnimais) {
        String[] habitatsSemDuplicados = habitatsUnicos(matrizAnimais);

        for (int i = 0; i < habitatsSemDuplicados.length; i++) {
            System.out.println("\n***** " + habitatsSemDuplicados[i] + " *****");

            String[] idsAnimaisHabitatAtual = animaisDeUmHabitat(matrizAnimais, habitatsSemDuplicados[i]);
            for (int indexAnimal = 0; indexAnimal < idsAnimaisHabitatAtual.length; indexAnimal++) {
                System.out.print(nomeAnimal(matrizAnimais, idsAnimaisHabitatAtual[indexAnimal]));
                System.out.print(" | ");
                System.out.println(especieAnimal(matrizAnimais, idsAnimaisHabitatAtual[indexAnimal]));
            }

            System.out.println();
        }
    }

    /**
     * Funções que indica o animal mais popular
     *
     * @param matrizInteracoes
     * @param matrizAnimal
     */
    public static void animalMaisPopular(String[][] matrizInteracoes, String[][] matrizAnimal) {

        String animalAtual, idAnimalMaisPouplar = "";
        int quantasVezesPossuiInteracaoAtual = 0, quantidadeDeInteracoesDoMaisPopular = 0;

        for (int linha = 0; linha < matrizInteracoes.length; linha++) {

            // declarar as variaveis com valores atuais
            animalAtual = matrizInteracoes[linha][3];
            quantasVezesPossuiInteracaoAtual = 0;

            //verificar a quantidade de vezes que o animal atual possui de interações
            for (int linhaBusca = 0; linhaBusca < matrizInteracoes.length; linhaBusca++) {
                if (matrizInteracoes[linhaBusca][3].equals(animalAtual)) {
                    quantasVezesPossuiInteracaoAtual++;
                }
            }
            // fazer um comparativo entre a quantidade de vezes do animal atual, com o atual animal mais popular
            if (quantasVezesPossuiInteracaoAtual > quantidadeDeInteracoesDoMaisPopular) {
                quantidadeDeInteracoesDoMaisPopular = quantasVezesPossuiInteracaoAtual;
                idAnimalMaisPouplar = matrizInteracoes[linha][3];
            }
        }
        System.out.println("Nome: " + nomeAnimal(matrizAnimal, idAnimalMaisPouplar));
        System.out.println("Espécie: " + especieAnimal(matrizAnimal, idAnimalMaisPouplar));
        System.out.println("Habitat: " + imprimirAnimalPorHabitat(matrizAnimal, idAnimalMaisPouplar));
        System.out.println("Numero de Interaçoes " + quantidadeDeInteracoesDoMaisPopular);
    }

    /**
     * Função que mostra a quantidade de valor arrecadado por animal em extinção
     *
     * @param matrizInteracoes
     * @param matrizAnimais
     */
    public static void verificaAnimaisEmExintincaoMaisPopulares(String[][] matrizInteracoes, String[][] matrizAnimais) {

        String idAnimalAtual = "";
        double rendimentoTotal = 0;

        for (int linha = 0; linha < matrizAnimais.length; linha++) {
            if (matrizAnimais[linha][5].equals("SIM")) {
                // declarar as variaveis com valores atuais
                idAnimalAtual = matrizAnimais[linha][0];
                rendimentoTotal = 0;

                // calcular o rendimento total
                for (int linhaBusca = 0; linhaBusca < matrizInteracoes.length; linhaBusca++) {
                    if (matrizInteracoes[linhaBusca][3].equals(idAnimalAtual) && matrizAnimais[linha][5].equals("SIM")) {
                        rendimentoTotal += Double.parseDouble(matrizInteracoes[linhaBusca][5]);

                    }

                }
                System.out.println("------------------------------------------------------");
                System.out.println();
                System.out.println("Nome do Animal em extinção: " + nomeAnimal(matrizAnimais, idAnimalAtual));
                System.out.println("Valor total arrecadado: " + rendimentoTotal);
                System.out.println();
                System.out.println();

            }
        }

    }

    /**
     * Função para ver as atividades de espetáculo e alimentação de um animal
     * @param matrizInteracoes
     * @param matrizAnimais
     * @param idAnimal
     */
    public static void verAtividadesDeUmAnimal(String[][] matrizInteracoes, String[][] matrizAnimais, String idAnimal) {
        int countInteracoesEspetaculos = 0;
        int countInteracoesAlimentacao = 0;

        String listaEspetaculos = "";
        String listaAlimentacoes = "";

        for (int linha = 0; linha < matrizInteracoes.length; linha++) {

            if (matrizInteracoes[linha][3].equals(idAnimal) && (matrizInteracoes[linha][2].equals("ESPETACULO") ||
                    matrizInteracoes[linha][2].equals("ALIMENTACAO"))) {

                if (matrizInteracoes[linha][2].equals("ESPETACULO")) {
                    listaEspetaculos = matrizInteracoes[linha][4];
                    countInteracoesEspetaculos++;
                } else if (matrizInteracoes[linha][2].equals("ALIMENTACAO")) {
                    listaAlimentacoes = matrizInteracoes[linha][4];
                    countInteracoesAlimentacao++;
                }

            }
        }
        System.out.println("-------------------Atividade do animal " + nomeAnimal(matrizAnimais, idAnimal) + " - " + especieAnimal(matrizAnimais, idAnimal) + "-----------------------------");
        System.out.println();
        System.out.println("Espetáculo:");
        if (countInteracoesEspetaculos > 0) {
            System.out.println("-" + listaEspetaculos + " : " + countInteracoesEspetaculos);
        } else {
            System.out.println("Não possui espetáculos");

        }
        System.out.println();
        System.out.println("Alimentações:");
        if (countInteracoesAlimentacao > 0) {
            System.out.println("-" + listaAlimentacoes + " : " + countInteracoesAlimentacao);
        }


    }

    /**
     * Função para cadastrar um novo padrinho
     * @param matrizAnimais
     */
    public static void cadastrarNovoApadrinhamento(String[][] matrizAnimais) {
        String nome;
        String email;
        String idAnimal;
        String plano = "";
        Double valorMensal;

        Scanner input = new Scanner(System.in);


        System.out.print("Nome do padrinho: ");
        nome = input.nextLine();

        System.out.print("Email: ");
        email = input.nextLine();

        System.out.print("ID do animal a apadrinhar: ");
        idAnimal = input.nextLine();
        System.out.print("Valor mensal € : ");
        valorMensal = input.nextDouble();

        if (animalExiste(matrizAnimais,idAnimal)){
            if (valorMensal <= 25) {
                plano = "Apadrinhamento Simples";
            } else if (valorMensal > 25 && valorMensal <= 50) {
                plano = "Apadrinhamento gold";
            } else if (valorMensal > 50) {
                plano = "Apadrinhamento diamond";

            }


            System.out.println("------------- Resumo do Apadrinhamento -----------------");
            System.out.println("Padrinho : " + nome + " (" + email + ")");
            System.out.println("Animal : " + idAnimal);
            System.out.println("Plano : " + plano);
            System.out.printf("Valor : " + valorMensal);
        }else {
            System.out.println("O id do animal indicado não existe, tente novamente!");
        }






    }

    public static void jogarAdivinhaEspecie(String[][] matrizAnimais){
        Scanner input = new Scanner(System.in);
        String especie,habitat,tipoDeDieta,perigoDeExtincao;

        // Escolhendo um animal aleatório
        Random rd = new Random();

        int indexRandom = rd.nextInt(matrizAnimais.length);
        String[] animalEscolhido = matrizAnimais[indexRandom];


        especie = animalEscolhido[2];
        habitat = animalEscolhido[3];
        tipoDeDieta = animalEscolhido[4];
        perigoDeExtincao = animalEscolhido[5];

        System.out.println();
        System.out.println("------ JOGO: ADIVINHA A ESPÉCIE -----");
        System.out.println("Pistas sobre o animal misterioso:");
        System.out.println("Habitat: " + habitat);
        System.out.println("Tipo de dieta: " + tipoDeDieta);
        System.out.println("Em perigo de extinção? " + perigoDeExtincao);


        //contar a quantidade de tentativas até acertar
        int tentativas = 0;
        String resposta = "";

        //começo do jogo
        do {

            System.out.println();
            System.out.print("Qual é a espécie? ");
            resposta = input.nextLine();
            tentativas++;

            if (!resposta.equalsIgnoreCase(especie)) {
                System.out.println("Não foi desta vez, tente novamente!");
            }

        } while (!resposta.equalsIgnoreCase(especie));

        // Resposta quando acertar
        System.out.println("Correto, muito bem! A espécie era: " + especie);
        System.out.println("Seu número de tentativas foram: " + tentativas);


    }
    public static void imprimirCopyright(){

            String texto =
                    "    _ _  __    _ _  __     _ _  __    _ _  __    _ _  __    _ _  __\n" +
                            "   ( | )/_/   ( | )/_/    ( | )/_/   ( | )/_/   ( | )/_/   ( | )/_/\n" +
                            "__( >O< )  __( >O< )   __( >O< )  __( >O< )  __( >O< )  __( >O< )\n" +
                            "\\_\\(_|_)   \\_\\(_|_)    \\_\\(_|_)   \\_\\(_|_)   \\_\\(_|_)   \\_\\(_|_)\n" +
                            "    _ _  __                                                 _ _  __\n" +
                            "   ( | )/_/                                                ( | )/_/\n" +
                            "__( >O< )        © 2025 | Mayara Sampaio                   __( >O< )\n" +
                            "\\_\\(_|_)           CodeSavanna ©                            \\_\\(_|_)\n" +
                            "    _ _  __                                                 _ _  __\n" +
                            "   ( | )/_/                                                ( | )/_/\n" +
                            "__( >O< )                                               __( >O< )\n" +
                            "\\_\\(_|_)                                                \\_\\(_|_)\n" +
                            "    _ _  __                                                 _ _  __\n" +
                            "   ( | )/_/                                                ( | )/_/\n" +
                            "__( >O< )                                               __( >O< )\n" +
                            "\\_\\(_|_)                                                \\_\\(_|_)\n" +
                            "    _ _  __                                                 _ _  __\n" +
                            "   ( | )/_/                                                ( | )/_/\n" +
                            "__( >O< )                                               __( >O< )\n" +
                            "\\_\\(_|_)                                                \\_\\(_|_)\n" +
                            "    _ _  __    _ _  __     _ _  __    _ _  __    _ _  __    _ _  __\n" +
                            "   ( | )/_/   ( | )/_/    ( | )/_/   ( | )/_/   ( | )/_/   ( | )/_/\n" +
                            "__( >O< )  __( >O< )   __( >O< )  __( >O< )  __( >O< )  __( >O< )\n" +
                            "\\_\\(_|_)   \\_\\(_|_)    \\_\\(_|_)   \\_\\(_|_)   \\_\\(_|_)   \\_\\(_|_)\n\n" +

                            "                            _\n" +
                            "                          .' `'.__\n" +
                            "                         /      \\ `\"\"-,\n" +
                            "        .-''''--...__..-/ .     |      \\\n" +
                            "      .'               ; :'     '.  a   |\n" +
                            "     /                 | :.       \\     =\\\n" +
                            "    ;                   \\\\':.      /  ,-.__;.-;`\n" +
                            "   /|     .              '--._   /-.7`._..-;`\n" +
                            "  ; |       '                |`-'      \\  =|\n" +
                            "  |/\\        .   -' /     /  ;         |  =/\n" +
                            "  (( ;.       ,_  .:|     | /     /\\   | =|\n" +
                            "   ) / `\\     | `\"\"`;     / |    | /   / =/\n" +
                            "     | ::|    |      \\    \\ \\    \\ `--' =/\n" +
                            "    /  '/\\    /       )    |/     `-...-`\n" +
                            "   /    | |  `\\    /-'    /;\n" +
                            "   \\  ,,/ |    \\   D    .'  \\\n" +
                            "jgs `\"\"`   \\  nnh  D_.-'L__nnh\n" +
                            "            `\"\"\"`\n";


        System.out.println(texto);
    }

    public static void main(String[] args) throws FileNotFoundException {

        String[][] matrizAnimais = ficheiroParaMatriz("ficheiro/animais.csv");
        String[][] matrizClientes = ficheiroParaMatriz("ficheiro/clientes.csv");
        String[][] matrizInteracoes = ficheiroParaMatriz("ficheiro/interacoes.csv");

        menuEntrada(matrizAnimais,matrizClientes,matrizInteracoes);

    }


}
