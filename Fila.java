public class Fila {
    private static final int MAX_SIZE = 100;
    private String[] senhaPrioritaria;
    private String[] senhaComum;
    private int frentePrioritaria;
    private int trasPrioritaria;
    private int frenteComum;
    private int trasComum;
    private int contadorPrioritariosAtendidos;
    
    public Fila() {
        senhaPrioritaria = new String[MAX_SIZE];
        senhaComum = new String[MAX_SIZE];
        frentePrioritaria = 0;
        trasPrioritaria = -1;
        frenteComum = 0;
        trasComum = -1;
        contadorPrioritariosAtendidos = 0;
    }
    
    public boolean estaCheia(String[] fila) {
        return trasComum == MAX_SIZE - 1 || trasPrioritaria == MAX_SIZE - 1;
    }
    
    public boolean estaVazia(String[] fila, int frente, int tras) {
        return frente > tras;
    }
    
    public void enfileirar(String senha, boolean prioritaria) {
        if (prioritaria) {
            if (trasPrioritaria < MAX_SIZE - 1) {
                trasPrioritaria++;
                senhaPrioritaria[trasPrioritaria] = senha;
                System.out.println("Senha prioritária " + senha + " adicionada à fila.");
            } else {
                System.out.println("Fila prioritária está cheia!");
            }
        } else {
            if (trasComum < MAX_SIZE - 1) {
                trasComum++;
                senhaComum[trasComum] = senha;
                System.out.println("Senha comum " + senha + " adicionada à fila.");
            } else {
                System.out.println("Fila comum está cheia!");
            }
        }
    }
    
    public String desenfileirar() {
        if (contadorPrioritariosAtendidos < 3 && !estaVazia(senhaPrioritaria, frentePrioritaria, trasPrioritaria)) {
            String senha = senhaPrioritaria[frentePrioritaria];
            frentePrioritaria++;
            contadorPrioritariosAtendidos++;
            return senha;
        } else {
            if (!estaVazia(senhaComum, frenteComum, trasComum)) {
                String senha = senhaComum[frenteComum];
                frenteComum++;
                contadorPrioritariosAtendidos = 0;
                return senha;
            } else if (!estaVazia(senhaPrioritaria, frentePrioritaria, trasPrioritaria)) {
                String senha = senhaPrioritaria[frentePrioritaria];
                frentePrioritaria++;
                contadorPrioritariosAtendidos++;
                return senha;
            }
        }
        return null;
    }
    
    public String proximoAtendimento() {
        if (contadorPrioritariosAtendidos < 3 && !estaVazia(senhaPrioritaria, frentePrioritaria, trasPrioritaria)) {
            return senhaPrioritaria[frentePrioritaria];
        } else {
            if (!estaVazia(senhaComum, frenteComum, trasComum)) {
                return senhaComum[frenteComum];
            } else if (!estaVazia(senhaPrioritaria, frentePrioritaria, trasPrioritaria)) {
                return senhaPrioritaria[frentePrioritaria];
            }
        }
        return null;
    }
    
    public void listarSenhas() {
        System.out.println("=== FILAS DE ATENDIMENTO ===");
        
        System.out.println("\nFILA PRIORITÁRIA:");
        if (estaVazia(senhaPrioritaria, frentePrioritaria, trasPrioritaria)) {
            System.out.println("Fila prioritária vazia.");
        } else {
            for (int i = frentePrioritaria; i <= trasPrioritaria; i++) {
                System.out.println((i - frentePrioritaria + 1) + ". " + senhaPrioritaria[i]);
            }
        }
        
        System.out.println("\nFILA COMUM:");
        if (estaVazia(senhaComum, frenteComum, trasComum)) {
            System.out.println("Fila comum vazia.");
        } else {
            for (int i = frenteComum; i <= trasComum; i++) {
                System.out.println((i - frenteComum + 1) + ". " + senhaComum[i]);
            }
        }
        
        System.out.println("\nContador de prioritários atendidos: " + contadorPrioritariosAtendidos + "/3");
    }
    
    public boolean removerSenha(String senha) {
        boolean encontrou = false;
        
        for (int i = frentePrioritaria; i <= trasPrioritaria; i++) {
            if (senhaPrioritaria[i].equals(senha)) {
                encontrou = true;
                for (int j = i; j < trasPrioritaria; j++) {
                    senhaPrioritaria[j] = senhaPrioritaria[j + 1];
                }
                trasPrioritaria--;
                System.out.println("Senha " + senha + " removida da fila prioritária.");
                return true;
            }
        }
        
        for (int i = frenteComum; i <= trasComum; i++) {
            if (senhaComum[i].equals(senha)) {
                encontrou = true;
                for (int j = i; j < trasComum; j++) {
                    senhaComum[j] = senhaComum[j + 1];
                }
                trasComum--;
                System.out.println("Senha " + senha + " removida da fila comum.");
                return true;
            }
        }
        
        if (!encontrou) {
            System.out.println("Senha " + senha + " não encontrada em nenhuma fila.");
        }
        return false;
    }
    
    public int getContadorPrioritariosAtendidos() {
        return contadorPrioritariosAtendidos;
    }
    
    public void resetarContador() {
        contadorPrioritariosAtendidos = 0;
    }
}
