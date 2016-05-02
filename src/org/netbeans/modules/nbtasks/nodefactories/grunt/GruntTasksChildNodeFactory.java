package org.netbeans.modules.nbtasks.nodefactories.grunt;

import java.beans.IntrospectionException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.prefs.BackingStoreException;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.nodes.BeanNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.NbPreferences;
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

        // Get netbeans specific preferences.        
        System.out.println(NbPreferences.root().get("grunt.path", null));

        Runtime rt = Runtime.getRuntime();
        String[] commands = {gruntCmd, "--help"};
        Process proc;
        String s;

        try {
            proc = rt.exec(commands);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

            BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
            
            list.add(stdInput.readLine());
            
            while ((s = stdInput.readLine()) != null) {
//                if (s.contains("Available tasks")) {
                    list.add(s);
//                }
            }
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
