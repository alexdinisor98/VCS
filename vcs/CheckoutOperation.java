package vcs;

import utils.ErrorCodeManager;
import utils.OperationType;

import java.util.ArrayList;
import java.util.LinkedList;

import static utils.ErrorCodeManager.VCS_BAD_CMD_CODE;
import static utils.ErrorCodeManager.VCS_BAD_PATH_CODE;
import static utils.ErrorCodeManager.VCS_STAGED_OP_CODE;

public class CheckoutOperation extends VcsOperation {
    /**
     * Sets the branch.
     */
    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    /**
     * Gets the branch.
     *
     * @return the branch
     */
    public Branch getBranch() {
        return branch;
    }

    private Branch branch;

    /**
     * Status operation constructor.
     *
     * @param type          type of the operation
     * @param operationArgs the arguments of the operation
     */
    public CheckoutOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /**
     * Execute de CheckoutOperation.
     *
     * @param vcs the vcs
     */
    @Override
    public int execute(Vcs vcs) {
        branch = new Branch();
        int isAlreadyABranch = 0;
        //command "checkout -c commitId"
        if (getOperationArgs().get(1).equals("-c")) {

            Branch currentBranch = vcs.getCheckoutBranches()
                    .get(vcs.getCheckoutBranches().size() - 1);

            int numberOfCommits = currentBranch.getCommits().size();
            int idForCheckout = Integer.parseInt(operationArgs.get(2));
            int indexInList = getIndexOfCommit(currentBranch.getCommits(), idForCheckout);

            // if staging is empty
            if (vcs.getStagedChanges().size() == 0) {
                // id doesn't exist
                if (indexInList == -1) {
                    vcs.getOutputWriter().write(VCS_BAD_PATH_CODE + " : "
                            + ErrorCodeManager.getVcsBadPathStr() + "\n");
                } else {
                    // remove all elements after the commit with index = checkout command
                    for (int i = indexInList + 1; i < numberOfCommits; i++) {
                        currentBranch.getCommits().remove(i);
                    }

                    // re-update snapshot
                    vcs.setActiveSnapshot(currentBranch.getCommits()
                            .get(indexInList).getSnapshot().cloneFileSystem());
                }
            } else {
                // staging is not empty
                vcs.getOutputWriter().write(VCS_STAGED_OP_CODE + " : "
                        + ErrorCodeManager.getVcsStagedOpStr() + "\n");
                return 0;

            }
        } else {
            //command "vcs checkout branchName"
            for (int i = 0; i < vcs.getBranches().size(); i++) {
                //daca branch-ul a fost creat deja
                if (vcs.getBranches().get(i).getName().equals(operationArgs.get(1))) {
                    isAlreadyABranch = 1;
                    break;
                }
            }
            // current branch already exists in branch list
            if (isAlreadyABranch == 1) {
                // if staging is empty, it can move to the new branch
                if (vcs.getStagedChanges().size() == 0) {
                    branch.setName(operationArgs.get(1));
                    vcs.getCheckoutBranches().add(branch);
                } else {
                    vcs.getOutputWriter().write(VCS_STAGED_OP_CODE + " : "
                            + ErrorCodeManager.getVcsStagedOpStr() + "\n");
                }

            } else {
                // if name is wrong set
                branch.setName(operationArgs.get(1));
                vcs.setInvalidBranchName(operationArgs.get(1));
                vcs.getOutputWriter().write(VCS_BAD_CMD_CODE + " : "
                        + ErrorCodeManager.getVcsBadCmdStr() + "\n");

            }
        }
        return ErrorCodeManager.OK;
    }
    // commit index searched in the commit list
    static int getIndexOfCommit(LinkedList<Commit> commits, int id) {
        for (int index = 0; index < commits.size(); index++) {
            if (commits.get(index).getId() == id) {
                return index;
            }
        }
        return -1;
    }
}
