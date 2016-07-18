package se.spline.api.folder.parameter;

public class BooleanFolderParameter implements FolderParameter<Boolean> {

    private final String name;
    private final Boolean value;

    public BooleanFolderParameter(String name, Boolean value) {
        this.name = name;
        this.value = value;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public Boolean getValue() {
        return value;
    }
}
