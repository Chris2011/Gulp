package org.netbeans.modules.nbtasks;

import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ChangeListener;
import org.netbeans.api.project.Project;
import org.netbeans.spi.project.ui.support.NodeFactory;
import org.netbeans.spi.project.ui.support.NodeList;
import org.openide.nodes.FilterNode;
import org.openide.nodes.Node;

/**
 *
 * @author chrl
 */
@NodeFactory.Registration(projectType = {
    "org-netbeans-modules-maven",
    "org-netbeans-modules-php-phpproject",
    "org-netbeans-modules-php-project",
    "org-netbeans-modules-web-clientproject",
    "org-netbeans-modules-web-project",
    "org.netbeans.modules.web.clientproject"
}, position = 10000)
public class NbTasksNodeFactory implements NodeFactory {
    @Override
    public NodeList<?> createNodes(final Project project) {
        return new TaskNodeList(project);
    }
    
    private class TaskNodeList implements NodeList<Node> {

        Project project;

        public TaskNodeList(Project project) {
            this.project = project;
        }

        @Override
        public List<Node> keys() {
            List<Node> result = new ArrayList<>();
            
            result.add(new NbTasksNode(project));

            return result;
        }

        @Override
        public void addChangeListener(ChangeListener cl) {
        }

        @Override
        public void removeChangeListener(ChangeListener cl) {
        }

        @Override
        public Node node(Node node) {
            return new FilterNode(node);
        }

        @Override
        public void addNotify() {
        }

        @Override
        public void removeNotify() {
        }
    }
}