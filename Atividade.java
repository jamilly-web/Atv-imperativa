import java.util.Scanner;

public class Exemplo {

    public static Scanner input = new Scanner(System.in);
    public static final int QTD = 3;

    public static void main(String[] args){
        Produto[] produtos = new Produto[QTD];
        int tam = 0;
        int escolha;

        do {
            System.out.println("\n==== MENU ====");
            System.out.println("1 - Cadastrar novo produto");
            System.out.println("2 - Listar produtos");
            System.out.println("3 - Filtrar por categoria");
            System.out.println("4 - Ordenar por categoria");
            System.out.println("5 - Remover produto");
            System.out.println("6 - Atualizar preço");
            System.out.println("7 - Subtotal por categoria");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            escolha = lerInt();

            switch (escolha) {
                case 1 -> tam = cadastrar(produtos, tam);
                case 2 -> listar(produtos, tam);
                case 3 -> filtrarPorCategoria(produtos, tam);
                case 4 -> ordenar(produtos, tam);
                case 5 -> tam = remover(produtos, tam);
                case 6 -> atualizarPreco(produtos, tam);
                case 7 -> subtotalPorCategoria(produtos, tam);
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }

        } while (escolha != 0);
    }

    // ===========================
    // MÉTODOS AUXILIARES
    // ===========================
    public static int lerInt() {
        while (!input.hasNextInt()) {
            System.out.print("Digite um número válido: ");
            input.next();
        }
        int valor = input.nextInt();
        input.nextLine(); // limpa buffer
        return valor;
    }

    public static double lerDouble() {
        while (!input.hasNextDouble()) {
            System.out.print("Digite um número válido: ");
            input.next();
        }
        double valor = input.nextDouble();
        input.nextLine();
        return valor;
    }

    // ===========================
    // CADASTRAR
    // ===========================
    public static int cadastrar(Produto[] produtos, int tam){
        if (tam >= produtos.length) {
            System.out.println("ERRO: Vetor cheio!");
            return tam;
        }

        Produto p = new Produto();

        System.out.print("Nome: ");
        p.nome = input.nextLine();

        System.out.print("Descrição: ");
        p.descricao = input.nextLine();

        System.out.print("Categoria: ");
        p.categoria = input.nextLine();

        System.out.print("Quantidade em estoque: ");
        p.estoque = lerInt();

        System.out.print("Quantidade mínima: ");
        p.qtdMinima = lerInt();

        System.out.print("Preço: ");
        p.preco = lerDouble();

        produtos[tam] = p;
        System.out.println("Produto cadastrado com sucesso!");

        return tam + 1;
    }

    // ===========================
    // LISTAR
    // ===========================
    public static void listar(Produto[] produtos, int tam){
        if (tam == 0) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        for (int i = 0; i < tam; i++) {
            imprimir(produtos[i]);
        }
    }

    public static void imprimir(Produto p){
        System.out.println("\n----------------------");
        System.out.println("Nome: " + p.nome);
        System.out.println("Descrição: " + p.descricao);
        System.out.println("Categoria: " + p.categoria);
        System.out.println("Estoque: " + p.estoque);
        System.out.println("Preço: R$ " + p.preco);
    }

    // ===========================
    // FILTRAR POR CATEGORIA
    // ===========================
    public static void filtrarPorCategoria(Produto[] produtos, int tam){
        System.out.print("Digite a categoria: ");
        String categoria = input.nextLine();

        boolean achou = false;

        for (int i = 0; i < tam; i++) {
            if (produtos[i].categoria.equalsIgnoreCase(categoria)) {
                imprimir(produtos[i]);
                achou = true;
            }
        }

        if (!achou) {
            System.out.println("Nenhum produto dessa categoria.");
        }
    }

    // ===========================
    // ORDENAR
    // ===========================
    public static void ordenar(Produto[] produtos, int tam){
        for (int i = 0; i < tam - 1; i++) {
            for (int j = 0; j < tam - 1 - i; j++) {
                if (produtos[j].categoria.compareToIgnoreCase(produtos[j+1].categoria) > 0) {
                    Produto aux = produtos[j];
                    produtos[j] = produtos[j+1];
                    produtos[j+1] = aux;
                }
            }
        }
        System.out.println("Produtos ordenados com sucesso!");
    }

    // ===========================
    // REMOVER
    // ===========================
    public static int remover(Produto[] produtos, int tam){
        System.out.print("Nome do produto a remover: ");
        String nome = input.nextLine();

        for (int i = 0; i < tam; i++) {
            if (produtos[i].nome.equalsIgnoreCase(nome)) {

                for (int j = i; j < tam - 1; j++) {
                    produtos[j] = produtos[j+1];
                }

                produtos[tam - 1] = null;

                System.out.println("Produto removido!");
                return tam - 1;
            }
        }

        System.out.println("Produto não encontrado.");
        return tam;
    }

    // ===========================
    // ATUALIZAR PREÇO
    // ===========================
    public static void atualizarPreco(Produto[] produtos, int tam){
        System.out.print("Nome do produto: ");
        String nome = input.nextLine();

        for (int i = 0; i < tam; i++) {
            if (produtos[i].nome.equalsIgnoreCase(nome)) {
                System.out.print("Novo preço: ");
                produtos[i].preco = lerDouble();
                System.out.println("Preço atualizado!");
                return;
            }
        }

        System.out.println("Produto não encontrado.");
    }

    // ===========================
    // SUBTOTAL POR CATEGORIA
    // ===========================
    public static void subtotalPorCategoria(Produto[] produtos, int tam){
        System.out.print("Categoria: ");
        String categoria = input.nextLine();

        double subtotal = 0;
        boolean achou = false;

        for (int i = 0; i < tam; i++) {
            if (produtos[i].categoria.equalsIgnoreCase(categoria)) {

                imprimir(produtos[i]);
                subtotal += produtos[i].preco * produtos[i].estoque;
                achou = true;
            }
        }

        if (!achou) {
            System.out.println("Nenhum produto dessa categoria.");
            return;
        }

        System.out.println("\nSubtotal em estoque dessa categoria: R$ " + subtotal);
    }
}
