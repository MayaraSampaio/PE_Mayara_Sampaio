import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.runtime.SwitchBootstraps;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    //TODO
    //todas as funções receberão as matrizes como parametro

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
                        menuAdmin(matrizAnimais,matrizClientes,matrizInteracoes);
                    } else {
                        System.out.println("Login incorreto");

                    }
                    break;

                case 2: // Entrar em menu cliente
                    menuCliente(matrizAnimais,matrizClientes,matrizInteracoes);
                    break;

                case 3: //Sair
                    System.out.println("Obrigada por ter visitado o nosso ZOO");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }while (opcao !=3);
    }

    /**
     * Função para invocar o Menu Admin com opções para este tipo de usuario
     */
    public static void menuAdmin(String[][] matrizAnimais, String[][] matrizClientes, String[][] matrizInteracoes) throws FileNotFoundException {

        Scanner input = new Scanner(System.in);
        int opcao;
        String[] arrayPalavras = {"VISITA","ESPETACULO","ALIMENTACAO","APADRINHAMENTO"};

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
                    menuListarConteudoFicheiroAdmin(matrizAnimais,matrizClientes, matrizInteracoes);
                    break;

                case 2: // Estatísticas gerais de interações

                    int countTotal=0;

                    for (int i= 0; i < arrayPalavras.length;i++) {
                       countTotal= countTotal + imprimirEstatisticas(matrizInteracoes, arrayPalavras[i]);
                    }
                    System.out.println("Total de interações" + " : " + countTotal);

                    for (int i= 0; i < arrayPalavras.length;i++) {
                       System.out.println(arrayPalavras[i] + " : " + imprimirEstatisticas(matrizInteracoes, arrayPalavras[i]));

                    }


                case 3: // Receita total por tipo de interação

                    double countReceitaTotal=0;

                    for (int i= 0; i < arrayPalavras.length;i++) {
                        System.out.println(arrayPalavras[i] + " : " + imprimirReceita(matrizInteracoes, arrayPalavras[i]));
                    }

                    for (int i= 0; i < arrayPalavras.length;i++) {
                        countReceitaTotal= countReceitaTotal + imprimirReceita(matrizInteracoes, arrayPalavras[i]);
                    }
                    System.out.println("Receita total global" + " : " + countReceitaTotal);

                    break;

                case 4: // Animal mais popular

                    break;
                case 5://Top 3 espécies com mais apadrinhamentos
                    break;


                case 6://Listar padrinhos de um animal
                    System.out.println("Digite o Id de um animal");
                    String idAnimal = input.next().toUpperCase();

                    if (animalExiste(matrizAnimais, idAnimal)){
                        procurarApadrinhamentoPorIdAnimal(matrizInteracoes,matrizClientes,idAnimal);
                    } else {
                        System.out.println("Esse Id não existe, tente outro Id!");
                    };


                    break;

                case 7://Espetáculo mais rentável
                    break;

                case 8://Ranking de animais em perigo de extinção
                    break;

                case 9://Estatísticas por habitat
                    break;

                case 10:
                    menuEntrada(matrizAnimais, matrizClientes,  matrizInteracoes);
                    break;

                case 0://Sair

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
                case 1: // Listar conteúdo dos ficheiros
                    break;

                case 2: // Estatísticas gerais de interações

                    break;

                case 3: // Receita total por tipo de interação

                    break;

                case 4: //Top 3 espécies com mais apadrinhamentos
                    break;

                case 5:// voltar
                    menuEntrada(matrizAnimais, matrizClientes, matrizInteracoes);
                    break;


                case 0://Sair

                    break;
            }
        }while (opcao != 0);
    }



    /**
     * Função para contar linhas do ficheiro
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
     * @param caminho
     * @return
     */
    public static int contadorDeColunas(String caminho) throws FileNotFoundException {

        File ficheiroContaColuna = new File(caminho);
        Scanner sc = new Scanner(ficheiroContaColuna);
        int tamanhoLinha = 0;


        if (sc.hasNext()){
            String linha = sc.nextLine();
            String[] linhaSeparada = linha.split(";");
            tamanhoLinha = linhaSeparada.length;
        }

        return tamanhoLinha;
    }
    /**
     * Função para Menu que lista conteúdos do ficheiro escolhido
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
    //FUNÇÃO PARA INSERIR O PATH DE ACORDO COM O PATH ESCOLHIDO
    public static String escolherCaminho(String escolhaDoCaminho){

        switch (escolhaDoCaminho){
            case "animais" :
                return "ficheiro/animais.csv";
            case "clientes" :
                return "ficheiro/clientes.csv";
            case "interacoes":
                return "ficheiro/interacoes.csv";

        }

        return "";
    }

    /**
     * Função para calcularv estatisticas
     * @param matrizEscolhida
     * @param estatisticaPesquisada
     * @return count
     */
    public static int imprimirEstatisticas(String[][] matrizEscolhida,String estatisticaPesquisada){
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
     * @param matrizEscolhida
     * @param receitaPorCategoria
     * @return o valor total de receita por interação
     */
    public static double imprimirReceita(String[][] matrizEscolhida,String receitaPorCategoria){

        double countTotal = 0;

        for (int linha = 0; linha < matrizEscolhida.length; linha++) {
            if (matrizEscolhida[linha][2].equals(receitaPorCategoria)) {
                double valorPagoMatriz = Double.parseDouble(matrizEscolhida[linha][5]);
                countTotal = countTotal + valorPagoMatriz;
            }

        }
        return countTotal;

    }

    public static boolean animalExiste(String[][] matrizEscolhida,String idAnimal){
        boolean temIdAnimal = false;

        for (int linha = 0; linha < matrizEscolhida.length; linha++) {
            if (matrizEscolhida[linha][0].equals(idAnimal)) {
                temIdAnimal= true;
            }
        }

        return temIdAnimal;
    }

    public static void procurarApadrinhamentoPorIdAnimal(String[][] matrizEscolhidaInteracao, String[][] matrizCliente, String idAnimal){

        for (int linha = 0; linha < matrizEscolhidaInteracao.length; linha++) {
            if (matrizEscolhidaInteracao[linha][3].equals(idAnimal) && matrizEscolhidaInteracao[linha][2].equals("APADRINHAMENTO")) {
                String idCliente = matrizEscolhidaInteracao[linha][1];
                for (int linhaCliente = 0; linhaCliente < matrizCliente.length; linhaCliente++){
                    if (matrizCliente[linhaCliente][0].equals(idCliente)){
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






    public static void main(String[] args) throws FileNotFoundException {

        String[][] matrizAnimais = ficheiroParaMatriz("ficheiro/animais.csv");
        String[][] matrizClientes = ficheiroParaMatriz("ficheiro/clientes.csv");
        String[][] matrizInteracoes = ficheiroParaMatriz("ficheiro/interacoes.csv");


        menuEntrada(matrizAnimais,matrizClientes,matrizInteracoes);

    }
}
