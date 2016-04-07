package org.netbeans.modules.nbtasks.nodes.childnodes;

import java.beans.IntrospectionException;
import org.netbeans.modules.nbtasks.nodefactories.root.NbTasksChildFactory;
import org.openide.loaders.DataObject;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;

/**
 *
 * @author chrl
 */
public class NbTasksChildNode extends BeanNode {
    public NbTasksChildNode(DataObject dobj) throws IntrospectionException {
        super(dobj.getNodeDelegate(), Children.create(new NbTasksChildFactory(dobj), true));
    }
    
    @Override
    public String getDisplayName() {
        return "Anything";
    }
}
