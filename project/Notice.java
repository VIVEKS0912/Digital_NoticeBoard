package project;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Notice {
    private String text;
    private String owner;
    private String timestamp;

    public Notice(String text, String owner) {
        this.text = text;
        this.owner = owner;
        this.timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public String getText() {
        return text;
    }

    public String getOwner() {
        return owner;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return text + "\n(Owner: " + owner + ", Added on: " + timestamp + ")";
    }
}

