package university.console;

public interface Command {

    String getName();
    boolean isMatch(String text);
    String getResult(String text);
}
