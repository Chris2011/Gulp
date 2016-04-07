package org.netbeans.modules.nbtasks.nodefactories.gulp;

import java.awt.event.ActionEvent;
import java.beans.IntrospectionException;
import java.io.IOException;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.netbeans.api.extexecution.ExecutionDescriptor;
import org.netbeans.api.extexecution.ExecutionService;
import org.netbeans.api.extexecution.ExternalProcessBuilder;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.nodes.BeanNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;

/**
 *
 * @author chrl
 */
public class GulpTaskChildNodeFactory extends ChildFactory<String> {
    private final DataObject dobj;

    public GulpTaskChildNodeFactory(DataObject dobj) {
        this.dobj = dobj;
    }

    @Override
    protected boolean createKeys(List<String> list) {
        FileObject fo = dobj.getPrimaryFile();
        try {
            List<String> lines = fo.asLines();
            for (String line : lines) {
                if (line.startsWith("gulp.task")) {
                    int index = line.indexOf(",");
                    list.add(line.substring(0, index - 1).replace("gulp.task('", ""));
                }
            }
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }

        return true;
    }

    @Override
    protected Node createNodeForKey(final String key) {
        BeanNode node = null;
        try {
            node = new BeanNode(key) {
                @Override
                public Action[] getActions(boolean context) {
                    return new Action[]{new AbstractAction("Run") {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            ExternalProcessBuilder processBuilder = new ExternalProcessBuilder("gulp").
                                    //                                        addArgument("run").
                                    //                                        addArgument("-m").
                                    //                                        addArgument(namespaceName + "/" + methodName).
                                    workingDirectory(FileUtil.toFile(dobj.getPrimaryFile()));
                            ExecutionDescriptor descriptor = new ExecutionDescriptor().
                                    frontWindow(true).
                                    showProgress(true).
                                    controllable(true);
                            ExecutionService service = ExecutionService.newService(
                                    processBuilder,
                                    descriptor,
                                    key);
                            service.run();

                        }
                    }};
                }
            };

            node.setDisplayName(key);
        } catch (IntrospectionException ex) {
            Exceptions.printStackTrace(ex);
        }

        return node;
    }
}
