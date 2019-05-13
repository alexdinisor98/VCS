package vcs;

import filesystem.FileSystemSnapshot;
import utils.IDGenerator;

public class Commit {
    private int id;
    private FileSystemSnapshot snapshot;
    private String message = "";

    /**
     * Gets the snapshot.
     *
     * @return the snapshot
     */
    public FileSystemSnapshot getSnapshot() {
        return snapshot;
    }

    /**
     * Sets the snapshot.
     */
    public void setSnapshot(FileSystemSnapshot snapshot) {
        this.snapshot = snapshot;
    }

    /**
     * Gets the message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    public Commit() {
        this.id = IDGenerator.generateCommitID();
        this.message = "First commit";
        this.snapshot = null;

    }

    public Commit(int id, FileSystemSnapshot snapshot, String message) {
        this.id = id;
        this.snapshot = snapshot;
        this.message = message;
    }

    /**
     * Sets the id.
     */
    public void setId(int id) {
        this.id = IDGenerator.generateCommitID();
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

}
