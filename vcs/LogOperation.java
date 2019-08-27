package vcs;

import utils.ErrorCodeManager;
import utils.OperationType;

import java.util.ArrayList;

public class LogOperation extends VcsOperation {

    /**
     * Status operation constructor.
     *
     * @param type          type of the operation
     * @param operationArgs the arguments of the operation
     */
    public LogOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /**
     * Executes the operation.
     *
     * @param vcs the vcs
     * @return the return code
     */
    public int execute(Vcs vcs) {
        Branch currentBranch = vcs.getCheckoutBranches()
                .get(vcs.getCheckoutBranches().size() - 1);

        int currentId;
        String currentMessage;
        // display all commits on the current branch
        for (int i = 0; i < currentBranch.getCommits().size() - 1; i++) {
            currentId = currentBranch.getCommits().get(i).getId();
            currentMessage = currentBranch.getCommits().get(i).getMessage();

            vcs.getOutputWriter().write("Commit id: " + currentId + "\n");
            vcs.getOutputWriter().write("Message: " + currentMessage + "\n");
            vcs.getOutputWriter().write("\n");
        }

        // log for last commit
        int lastIndex = currentBranch.getCommits().size() - 1;
        vcs.getOutputWriter().write("Commit id: "
                + currentBranch.getCommits().get(lastIndex).getId() + "\n");

        vcs.getOutputWriter().write("Message: "
                + currentBranch.getCommits().get(lastIndex).getMessage() + "\n");

        return ErrorCodeManager.OK;
    }
}
