package vcs;

import filesystem.FileSystemOperation;
import filesystem.FileSystemSnapshot;
import utils.OutputWriter;
import utils.Visitor;

import java.util.LinkedList;

public final class Vcs implements Visitor {
    private final OutputWriter outputWriter;

    public OutputWriter getOutputWriter() {
        return outputWriter;
    }

    public void setActiveSnapshot(FileSystemSnapshot activeSnapshot) {
        this.activeSnapshot = activeSnapshot;
    }

    private FileSystemSnapshot activeSnapshot;

    public FileSystemSnapshot getActiveSnapshot() {
        return activeSnapshot;
    }

    public LinkedList<String> getStagedChanges() {
        return stagedChanges;
    }

    private LinkedList<String> stagedChanges;

    public LinkedList<Branch> getBranches() {
        return branches;
    }

    private LinkedList<Branch> branches;

    public Staging getStaging() {
        return staging;
    }

    private Staging staging;

    public LinkedList<Branch> getCheckoutBranches() {
        return checkoutBranches;
    }

    private LinkedList<Branch> checkoutBranches;
    private String invalidBranchName = "";

    public void setInvalidBranchName(String invalidBranchName) {
        this.invalidBranchName = invalidBranchName;
    }

    /**
     * Vcs constructor.
     *
     * @param outputWriter the output writer
     */
    public Vcs(OutputWriter outputWriter) {
        this.outputWriter = outputWriter;
    }

    /**
     * Does initialisations.
     */
    public void init() {
        this.activeSnapshot = new FileSystemSnapshot(outputWriter);

        //TODO other initialisations
        this.stagedChanges = new LinkedList<String>();
        this.branches = new LinkedList<Branch>();
        this.checkoutBranches = new LinkedList<>();
        Branch branchMaster = new Branch();
        branchMaster.setName("master");
        Commit firstCommit = new Commit();
        firstCommit.setSnapshot(this.getActiveSnapshot().cloneFileSystem());
        branchMaster.getCommits().add(firstCommit);
        //adaug branch master la lista de branch-uri
        this.branches.add(branchMaster);
        //adaug branch master la lista branch-urile de checkout
        this.checkoutBranches.add(branchMaster);
        this.staging = new Staging();

    }

    /**
     * Visits a file system operation.
     *
     * @param fileSystemOperation the file system operation
     * @return the return code
     */
    public int visit(FileSystemOperation fileSystemOperation) {
        return fileSystemOperation.execute(this.activeSnapshot);
    }

    /**
     * Visits a vcs operation.
     *
     * @param vcsOperation the vcs operation
     * @return return code
     */
    @Override
    public int visit(VcsOperation vcsOperation) {
        //TODO
        return vcsOperation.execute(this);
    }

    //TODO methods through which vcs operations interact with this


}
