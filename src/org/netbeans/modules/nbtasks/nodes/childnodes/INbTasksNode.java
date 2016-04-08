package org.netbeans.modules.nbtasks.nodes.childnodes;

import java.awt.Image;
import org.openide.nodes.ChildFactory;

/**
 *
 * @author chrl
 */
public interface INbTasksNode<T> {
    public String getDisplayName();
    public Image getIcon(int type);
    public Image getOpenedIcon(int type);
    public ChildFactory<T> getChildNodeFactory();
}
