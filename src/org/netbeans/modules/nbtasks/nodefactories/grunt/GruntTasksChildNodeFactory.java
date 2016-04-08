package org.netbeans.modules.nbtasks.nodefactories.grunt;

import java.beans.IntrospectionException;
import java.util.List;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.nodes.BeanNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;

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
