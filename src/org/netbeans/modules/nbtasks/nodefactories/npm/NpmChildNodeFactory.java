package org.netbeans.modules.nbtasks.nodefactories.npm;

import org.netbeans.api.project.Project;
import org.netbeans.modules.nbtasks.nodes.childnodes.NpmScriptsChildNode;
import org.netbeans.spi.project.ui.support.NodeFactory;
import org.netbeans.spi.project.ui.support.NodeFactorySupport;
import org.netbeans.spi.project.ui.support.NodeList;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.util.Exceptions;

//@NodeFactory.Registration(position = 5000, projectType = "org-netbeans-modules-web-clientproject")
public class NpmChildNodeFactory implements NodeFactory {
    private Project project;
    private final DataObject dobj;
    
    public NpmChildNodeFactory(DataObject dobj) {
        this.dobj = dobj;
    }

    @Override
    public NodeList<?> createNodes(Project prjct) {
        this.project = prjct;

        try {
            final FileObject fileObject = prjct.getProjectDirectory().getFileObject("gulpfile.js");

            if (fileObject != null) {
                 DataObject dobj = DataObject.find(fileObject);
            
                if (dobj != null) {
                    NpmScriptsChildNode nd = new NpmScriptsChildNode(dobj);

                    return NodeFactorySupport.fixedNodeList(nd);
                }
            }
        } catch (DataObjectNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        }

        return NodeFactorySupport.fixedNodeList();
    }
}