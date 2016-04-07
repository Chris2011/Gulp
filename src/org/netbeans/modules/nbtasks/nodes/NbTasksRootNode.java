package org.netbeans.modules.nbtasks.nodes;

import org.netbeans.api.annotations.common.StaticResource;
import org.netbeans.api.project.Project;
import org.netbeans.modules.nbtasks.nodefactories.root.NbTasksRootChildFactory;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;

/**
 *
 * @author chrl
 */
public class NbTasksRootNode extends AbstractNode {

    @StaticResource
    private static final String IMAGE = "org/netbeans/modules/nbtasks/resources/nodejs.png";

    public NbTasksRootNode(Project project) {
        super(Children.create(new NbTasksRootChildFactory(project), true));
        setDisplayName("Tasks & Scripts");
        setIconBaseWithExtension(IMAGE);
    }
}