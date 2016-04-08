package org.netbeans.modules.nbtasks.nodefactories.root;

import java.util.List;
import org.netbeans.modules.nbtasks.nodes.childnodes.GulpFileChildNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.netbeans.modules.nbtasks.nodes.childnodes.NbTasksChildNode;
import org.netbeans.modules.nbtasks.nodes.childnodes.NpmScriptsChildNode;
import org.openide.loaders.DataObject;

/**
 *
 * @author chrl
 */
public class NbTasksChildFactory extends ChildFactory<String> {
    private DataObject _dobj;

    public NbTasksChildFactory(DataObject dobj) {
        this._dobj = dobj;
    }

    @Override
    protected boolean createKeys(List<String> list) {
        GulpFileChildNode gulp;
        NpmScriptsChildNode npm;
        
        gulp = new GulpFileChildNode(_dobj);
        npm = new NpmScriptsChildNode(_dobj);
        list.add(gulp.getDisplayName());
        list.add(npm.getDisplayName());

        return true;
    }

    @Override
    protected Node createNodeForKey(final String key) {
        NbTasksChildNode node = null;

//        try {
//            node = new NbTasksChildNode(_dobj);
//        } catch (IntrospectionException ex) {
//            Exceptions.printStackTrace(ex);
//        }

        return node;
    }
}
