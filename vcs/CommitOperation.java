package vcs;

import utils.ErrorCodeManager;
import utils.IDGenerator;
import utils.OperationType;

import java.util.ArrayList;

import static utils.ErrorCodeManager.VCS_BAD_CMD_CODE;

public class CommitOperation extends VcsOperation {
    private Commit newCommit;

    /**
     * Gets the new commit.
     *
     * @return the new commit
     */
    public Commit getNewCommit() {
        return newCommit;
    }

    /**
     * GSets the new commit.
     */
    public void setNewCommit(Commit currentCommit) {
        this.newCommit = currentCommit;
    }

    /**
     * Commit operation constructor.
     *
     * @param type          type of the operation
     * @param operationArgs the arguments of the operation
     */
    public CommitOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /**
     * Executes the operation.
     *
     * @param vcs the vcs
     * @return the return code
     */
    public int execute(Vcs vcs) {
        // no operation in staging
        if (vcs.getStagedChanges().size() == 0) {
            vcs.getOutputWriter().write(VCS_BAD_CMD_CODE + " : "
                    + ErrorCodeManager.getVcsBadCmdStr() + "\n");
        } else {
            // empty the staging
            vcs.getStagedChanges().clear();

            // create the new commit text
            String messagetoBeCommitted = "";
            for (int i = 2; i < operationArgs.size() - 1; i++) {
                messagetoBeCommitted += (operationArgs.get(i) + " ");
            }
            messagetoBeCommitted += (operationArgs.get(operationArgs.size() - 1));

            // create new commit
            newCommit = new Commit(IDGenerator.generateCommitID(),
                    vcs.getActiveSnapshot().cloneFileSystem(), messagetoBeCommitted);

            Branch currentBranch = vcs.getCheckoutBranches()
                    .get(vcs.getCheckoutBranches().size() - 1);
            currentBranch.getCommits().add(newCommit);
        }
        return ErrorCodeManager.OK;
    }
}
