public class Main {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();

        editor.insert("Hello, ");
        editor.insert("World!");
        editor.insert(" MEME");

        editor.undo();
        editor.undo();

        editor.redo();
    }
}