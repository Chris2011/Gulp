package org.netbeans.modules.nbtasks;

import java.util.List;
import org.netbeans.api.project.Project;
import org.openide.nodes.ChildFactory;

/**
 *
 * @author chrl
 */
public class NbTasksChildFactory extends ChildFactory<String> {
    public NbTasksChildFactory(Project project) {
    }

    @Override
    protected boolean createKeys(List<String> list) {
        return true;
    }
}