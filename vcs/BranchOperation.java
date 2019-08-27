package vcs;

import utils.ErrorCodeManager;
import utils.OperationType;

import java.util.ArrayList;

import static utils.ErrorCodeManager.VCS_BAD_CMD_CODE;

public class BranchOperation extends VcsOperation {
    private Branch newBranch;

    /**
     * Branch operation constructor.
     *
     * @param type          type of the operation
     * @param operationArgs the arguments of the operation
     */
    public BranchOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /**
     * Execute de CheckoutOperation.
     *
     * @param vcs the vcs
     */
    @Override
    public int execute(Vcs vcs) {
        newBranch = new Branch();
        newBranch.setName(operationArgs.get(1));
        int isBranchInList = 0;

        // if it already exists in the branch array
        for (int i = 0; i < vcs.getBranches().size(); i++) {
            if (vcs.getBranches().get(i).getName().equals(operationArgs.get(1))) {
                isBranchInList = 1;
                break;
            }
        }
        if (isBranchInList == 0) {
            vcs.getBranches().add(newBranch);
        } else {
            // if the branch exists, don't add it again
            vcs.getOutputWriter().write(VCS_BAD_CMD_CODE + " : "
                    + ErrorCodeManager.getVcsBadCmdStr() + "\n");
        }
        return ErrorCodeManager.OK;
    }
}
