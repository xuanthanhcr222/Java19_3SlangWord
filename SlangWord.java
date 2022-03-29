public class SlangWord {
    private String ID;
    private String Definition;

    public SlangWord(String id, String definition) {
        ID = id;
        Definition = definition;
    }

    public String getDefinition() {
        return Definition;
    }

    public void setDefinition(String definition) {
        Definition = definition;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return ID + '`' + Definition;
    }
}
