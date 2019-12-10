package cesar.gui;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Montador mont = new Montador("/home/eduardo/Documentos/Ciencia_da_computacao/PS/comandos.txt", "teste2.txt");
        mont.Monta();
    }
    
}
