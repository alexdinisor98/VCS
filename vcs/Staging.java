package vcs;

import java.util.LinkedList;

public class Staging {
    private LinkedList<String> fileSystemTypes;

    Staging() {
        this.fileSystemTypes = new LinkedList<String>();
    }

    /**
     * Gets the filesystem types.
     *
     * @return the filesystem types
     */
    public LinkedList<String> getFileSystemTypes() {
        return fileSystemTypes;
    }
}
