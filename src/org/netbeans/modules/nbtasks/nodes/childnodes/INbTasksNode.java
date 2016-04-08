package org.netbeans.modules.nbtasks.nodes.childnodes;

import java.awt.Image;

/**
 *
 * @author chrl
 */
public interface INbTasksNode {
    public String getDisplayName();
    public Image getIcon(int type);
    public Image getOpenedIcon(int type);
}
