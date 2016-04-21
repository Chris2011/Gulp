package org.netbeans.modules.nbtasks.nodefactories.grunt;

import java.beans.IntrospectionException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import javax.swing.JOptionPane;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.nodes.BeanNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.Utilities;

/**
 *
 * @author chrl
 */
public class GruntTasksChildNodeFactory extends ChildFactory<String> {
    private final DataObject dobj;

    public GruntTasksChildNodeFactory(DataObject dobj) {
        this.dobj = dobj;
    }

    @Override
    protected boolean createKeys(List<String> list) {
        FileObject fo = dobj.getPrimaryFile().getFileObject("Gruntfile.js");
        String gruntCmd = Utilities.isWindows() ? "C:\\Users\\chrl\\AppData\\Roaming\\npm\\grunt.cmd" : "grunt";

//        ExternalProcessBuilder processBuilder = new ExternalProcessBuilder(gruntCmd)
//                                        .addArgument("-h")
//                                        .workingDirectory(FileUtil.toFile(dobj.getPrimaryFile()));
//                                ExecutionDescriptor descriptor = new ExecutionDescriptor().
//                                        frontWindow(false).
//                                        showProgress(true).                                        
//                                        controllable(true);
//                                ExecutionService service = ExecutionService.newService(
//                                        processBuilder,
//                                        descriptor,
//                                        "Grunt help");
//
//        Future<Integer> run = service.run();
        Runtime rt = Runtime.getRuntime();
        String[] commands = {gruntCmd, "-help"};
        Process proc;
        try {
            proc = rt.exec(commands);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

            BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
            
            JOptionPane.showMessageDialog(null, stdInput.lines());

        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }

// read the output from the command
//System.out.println("Here is the standard output of the command:\n");
//String s = null;
//while ((s = stdInput.readLine()) != null) {
//    System.out.println(s);
//}
//
//// read any errors from the attempted command
//System.out.println("Here is the standard error of the command (if any):\n");
//while ((s = stdError.readLine()) != null) {
//    System.out.println(s);
//}
        return true;
    }

    @Override
    protected Node createNodeForKey(final String key) {
        BeanNode node = null;

        try {
            node = new BeanNode(key);

            node.setDisplayName(key);
        } catch (IntrospectionException ex) {
            Exceptions.printStackTrace(ex);
        }

        return node;
    }
}
