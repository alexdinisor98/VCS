package vcs;

import utils.ErrorCodeManager;
import utils.OperationType;

import java.util.ArrayList;

public class RollbackOperation extends VcsOperation {

    /**
     * Status operation constructor.
     *
     * @param type          type of the operation
     * @param operationArgs the arguments of the operation
     */
    public RollbackOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /**
     * Executes the operation.
     *
     * @param vcs the vcs
     * @return the return code
     */
    public int execute(Vcs vcs) {
        //golesc staging
        vcs.getStagedChanges().clear();

        int maxCommitId = 0;
        int indexBranch = 0;
        int indexInCommitList = 0;
        int currentId;
        //caut commit-ul cu cel mai mare id din toate branch-urile
        for (int i = 0; i < vcs.getCheckoutBranches().size(); i++) {
            for (int j = 0; j < vcs.getCheckoutBranches().get(i).getCommits().size(); j++) {
                currentId = vcs.getCheckoutBranches().get(i).getCommits().get(j).getId();
                if (maxCommitId < currentId) {
                    maxCommitId = currentId;
                    indexInCommitList = j;
                    indexBranch = i;
                }
            }
        }
        //reactualizez snapshot
        vcs.setActiveSnapshot(vcs.getCheckoutBranches().get(indexBranch)
                .getCommits().get(indexInCommitList).getSnapshot());

        return ErrorCodeManager.OK;
    }
}
