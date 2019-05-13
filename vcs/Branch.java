package vcs;

import java.util.LinkedList;

public class Branch {
    private LinkedList<Commit> commits;
    private String name = "";

    public Branch() {
        this.commits = new LinkedList<>();
    }

    /**
     * Sets the commits.
     */
    public void setCommits(LinkedList<Commit> commits) {
        this.commits = commits;
    }

    /**
     * Sets the name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the commits.
     *
     * @return the commits
     */
    public LinkedList<Commit> getCommits() {
        return commits;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }


}
