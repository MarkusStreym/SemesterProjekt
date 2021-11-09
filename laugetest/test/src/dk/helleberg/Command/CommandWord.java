package dk.helleberg.Command;

public enum CommandWord {
    LEFT("LEFT"),UP("UP"),RIGHT("RIGHT"),DOWN("DOWN"),
    QUIT("QUIT"), HELP("HELP"), YES("YES"), NO("NO"),
    UNKNOWN("?");

    private String commandString;

    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }

    public String toString()
    {
        return commandString;
    }
}
