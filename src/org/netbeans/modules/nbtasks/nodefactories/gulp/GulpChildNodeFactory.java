package org.netbeans.modules.nbtasks.nodefactories.gulp;

import org.netbeans.api.project.Project;
import org.netbeans.modules.nbtasks.nodes.childnodes.GulpFileChildNode;
import org.netbeans.spi.project.ui.support.NodeFactory;
import org.netbeans.spi.project.ui.support.NodeFactorySupport;
import org.netbeans.spi.project.ui.support.NodeList;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.util.Exceptions;

public class GulpChildNodeFactory implements NodeFactory {
    private Project project;

    @Override
    public NodeList<?> createNodes(Project prjct) {
        this.project = prjct;

        try {
            final FileObject fileObject = prjct.getProjectDirectory().getFileObject("gulpfile.js");

            if (fileObject != null) {
                 DataObject dobj = DataObject.find(fileObject);
            
                if (dobj != null) {
                    GulpFileChildNode nd = new GulpFileChildNode(dobj);

                    return NodeFactorySupport.fixedNodeList(nd);
                }
            }
        } catch (DataObjectNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        }

        return NodeFactorySupport.fixedNodeList();
    }
}