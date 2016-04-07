package org.netbeans.modules.nbtasks.nodefactories.root;

import java.beans.IntrospectionException;
import java.util.List;
import org.netbeans.api.project.Project;
import org.netbeans.modules.nbtasks.nodes.childnodes.GulpFileChildNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.netbeans.modules.nbtasks.nodes.childnodes.NbTasksChildNode;
import org.netbeans.modules.nbtasks.nodes.childnodes.NpmScriptsChildNode;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;

/**
 *
 * @author chrl
 */
public class NbTasksRootChildFactory extends ChildFactory<String> {
    private Project _project = null;

    public NbTasksRootChildFactory(Project project) {
        this._project = project;
    }

    @Override
    protected boolean createKeys(List<String> list) {
        GulpFileChildNode gulp;
        NpmScriptsChildNode npm;
        
        try {
            gulp = new GulpFileChildNode(DataObject.find(this._project.getProjectDirectory()));
            npm = new NpmScriptsChildNode(DataObject.find(this._project.getProjectDirectory()));
            
            list.add(gulp.getDisplayName());
            list.add(npm.getDisplayName());
        } catch (DataObjectNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        }

        return true;
    }

    @Override
    protected Node createNodeForKey(final String key) {
        NbTasksChildNode node = null;

        try {
            node = new NbTasksChildNode(DataObject.find(this._project.getProjectDirectory()));
        } catch (IntrospectionException | DataObjectNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        }

        return node;
    }
}
