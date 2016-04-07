package org.netbeans.modules.nbtasks.nodefactories.root;

import java.beans.IntrospectionException;
import java.util.List;
import org.netbeans.api.project.Project;
import org.netbeans.modules.nbtasks.nodes.childnodes.GulpFileChildNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.netbeans.modules.nbtasks.nodes.childnodes.NbTasksChildNode;
import org.openide.loaders.DataObjectNotFoundException;

/**
 *
 * @author chrl
 */
public class NbTasksChildFactory extends ChildFactory<String> {
    private Project _project = null;

    public NbTasksChildFactory(Project project) {
        this._project = project;
    }

    @Override
    protected boolean createKeys(List<String> list) {
        GulpFileChildNode gulp;
        try {
            gulp = new GulpFileChildNode(null);
        } catch (DataObjectNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        }
        
        list.add(gulp.getDisplayName());
        list.add("3");

        return true;
    }

    @Override
    protected Node createNodeForKey(final String key) {
        NbTasksChildNode node = null;

        try {
            node = new NbTasksChildNode(key);
        } catch (IntrospectionException ex) {
            Exceptions.printStackTrace(ex);
        }

        return node;
    }
}
