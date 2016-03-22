package org.netbeans.modules.nbtasks;

import org.netbeans.api.project.Project;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;

/**
 *
 * @author chrl
 */
public class NbTasksNode extends AbstractNode {

//    @StaticResource
//    private static final String IMAGE = "org/chrisle/netbeans/plugins/csharp4netbeans/resources/references.png";

    public NbTasksNode(Project project) {
        super(Children.create(new NbTasksChildFactory(project), true));
        setDisplayName("Tasks & Scripts");
//        setIconBaseWithExtension(IMAGE);
    }
}