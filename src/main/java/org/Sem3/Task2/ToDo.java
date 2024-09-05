package org.Sem3.Task2;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class ToDo implements Externalizable {
    private String title;
    private boolean isDone;


    public ToDo() {
    }

    /**
     * @param title create new task, default isDone = false
     */
    public ToDo(String title) {
        isDone = false;
        this.title = title;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(title);
        out.writeBoolean(isDone);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        title = (String) in.readObject();
        isDone = in.readBoolean();
    }


    /**
     * @return isDone status
     */
    public boolean isDone() {
        return isDone;
    }


    /**
     * @param done - status isDone [ ][x]
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title installs a new title for the task
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
