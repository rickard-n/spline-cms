package se.spline.api.folder.parameter;

import java.util.Date;

public class DateFolderParameter implements FolderParameter<Date> {
    private final String name;
    private final Date value;

    public DateFolderParameter(String name, Date value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Date getValue() {
        return value;
    }
}
