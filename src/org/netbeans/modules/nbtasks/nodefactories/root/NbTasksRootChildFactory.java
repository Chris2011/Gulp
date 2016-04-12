package org.netbeans.modules.nbtasks.nodefactories.root;

import java.beans.IntrospectionException;
import java.util.List;
import org.netbeans.api.project.Project;
import org.netbeans.modules.nbtasks.nodes.childnodes.GruntFileChildNode;
import org.netbeans.modules.nbtasks.nodes.childnodes.GulpFileChildNode;
import org.netbeans.modules.nbtasks.nodes.childnodes.INbTasksNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.netbeans.modules.nbtasks.nodes.childnodes.NbTasksChildNode;
import org.netbeans.modules.nbtasks.nodes.childnodes.NpmScriptsChildNode;
import org.netbeans.modules.nbtasks.nodes.childnodes.TypingsChildNode;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;

/**
 *
 * @author chrl
 */
public class NbTasksRootChildFactory extends ChildFactory<INbTasksNode> {
    private Project _project = null;

    public NbTasksRootChildFactory(Project project) {
        this._project = project;
    }

    @Override
    protected boolean createKeys(List<INbTasksNode> list) {
        GulpFileChildNode gulp;
        GruntFileChildNode grunt;
        NpmScriptsChildNode npm;
        TypingsChildNode typings;
        
        try {
            if (this._project.getProjectDirectory().getFileObject("gulpfile.js") != null) {
                gulp = new GulpFileChildNode(DataObject.find(this._project.getProjectDirectory()));

                list.add(gulp);
            }
            
            if (this._project.getProjectDirectory().getFileObject("Gruntfile.js") != null) {
                grunt = new GruntFileChildNode(DataObject.find(this._project.getProjectDirectory()));

                list.add(grunt);
            }

            if (this._project.getProjectDirectory().getFileObject("typings.json") != null) {
                typings = new TypingsChildNode(DataObject.find(this._project.getProjectDirectory()));

                list.add(typings);
            }
            
            if (this._project.getProjectDirectory().getFileObject("package.json") != null) {
                npm = new NpmScriptsChildNode(DataObject.find(this._project.getProjectDirectory()));

                list.add(npm);
            }
        } catch (DataObjectNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        }

        return true;
    }

    @Override
    protected Node createNodeForKey(final INbTasksNode key) {
        NbTasksChildNode node = null;
        
        try {
            node = new NbTasksChildNode(key);
        } catch (IntrospectionException ex) {
            Exceptions.printStackTrace(ex);
        }

        return node;
    }
}
