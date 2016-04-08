package org.netbeans.modules.nbtasks.nodes.childnodes;

import java.awt.Image;
import java.beans.IntrospectionException;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;

/**
 *
 * @author chrl
 */
public class NbTasksChildNode extends BeanNode {
    private final INbTasksNode _nbTasksNode;
    
    public NbTasksChildNode(INbTasksNode nbTasksNode) throws IntrospectionException {
        super(nbTasksNode, Children.create(nbTasksNode.getChildNodeFactory(), true));
        
        this._nbTasksNode = nbTasksNode;
    }
    
    @Override
    public String getDisplayName() {
        return this._nbTasksNode.getDisplayName();
    }

    @Override
    public Image getOpenedIcon(int type) {
        return this._nbTasksNode.getOpenedIcon(type);
    }

    @Override
    public Image getIcon(int type) {
        return this._nbTasksNode.getIcon(type);
    }   
}