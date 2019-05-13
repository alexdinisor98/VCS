package vcs;

import utils.ErrorCodeManager;
import utils.OperationType;

import java.util.ArrayList;

public class StatusOperation extends VcsOperation {
    /**
     * Status operation constructor.
     *
     * @param type          type of the operation
     * @param operationArgs the arguments of the operation
     */
    public StatusOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /**
     * Execute de CheckoutOperation.
     *
     * @param vcs the vcs
     */
    @Override
    public int execute(Vcs vcs) {
        //pe vcs.checkoutBranches.size() - 1 este branch-ul curent
        //pe care s-a facut checkout
        int idCurrentBranch = vcs.getCheckoutBranches().size() - 1;
        String nameCurrentBranch = vcs.getCheckoutBranches().get(idCurrentBranch).getName();

        vcs.getOutputWriter().write("On branch: " + nameCurrentBranch + "\n");
        vcs.getOutputWriter().write("Staged changes:" + "\n");

        //modificarile din staging pt fiecare operatie de filesystem
        for (int i = 0; i < vcs.getStagedChanges().size(); i++) {

            if (vcs.getStaging().getFileSystemTypes().get(i).equals("TOUCH")) {
                vcs.getOutputWriter().write("\t" + "Created file "
                        + vcs.getStagedChanges().get(i) + "\n");
            }

            if (vcs.getStaging().getFileSystemTypes().get(i).equals("MAKEDIR")) {
                vcs.getOutputWriter().write("\t" + "Created directory "
                        + vcs.getStagedChanges().get(i) + "\n");
            }

            if (vcs.getStaging().getFileSystemTypes().get(i).equals("WRITETOFILE")) {
                vcs.getOutputWriter().write("\t" + "Added \""
                        + vcs.getStagedChanges().get(i) + "\" to file "
                        + vcs.getStagedChanges().get(i) + "\n");
            }

            if (vcs.getStaging().getFileSystemTypes().get(i).equals("REMOVE")) {
                vcs.getOutputWriter().write("\t" + "Removed "
                        + vcs.getStagedChanges().get(i) + "\n");
            }

            if (vcs.getStaging().getFileSystemTypes().get(i).equals("CHANGEDIR")) {
                vcs.getOutputWriter().write("\t" + "Changed directory to "
                        + vcs.getStagedChanges().get(i) + "\n");
            }

        }

        return ErrorCodeManager.OK;
    }

}
