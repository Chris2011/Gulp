package org.netbeans.modules.nbtasks.nodes.childnodes;

import java.beans.IntrospectionException;
import org.netbeans.modules.nbtasks.nodefactories.root.NbTasksChildFactory;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;

/**
 *
 * @author chrl
 */
public class NbTasksChildNode extends BeanNode {
    public NbTasksChildNode(String bean) throws IntrospectionException {
        super(bean, Children.create(new NbTasksChildFactory(), true));
        setDisplayName(bean);
    }   
}
