package org.netbeans.modules.nbtasks.nodes.childnodes;

import java.awt.Image;
import org.netbeans.api.annotations.common.StaticResource;
import org.netbeans.modules.nbtasks.nodefactories.grunt.GruntTasksChildNodeFactory;
import org.openide.loaders.DataObject;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.FilterNode;
import org.openide.util.ImageUtilities;

/**
 *
 * @author chrl
 */
    public class GruntFileChildNode extends FilterNode implements INbTasksNode<String> {
        private final ChildFactory<String> _childNodeFactory;

        @StaticResource
        private static final String IMAGE = "org/netbeans/modules/nbtasks/resources/grunt.png";

        public GruntFileChildNode(DataObject dobj) {
            super(dobj.getNodeDelegate(), Children.create(new GruntTasksChildNodeFactory(dobj), true));
            
            this._childNodeFactory = new GruntTasksChildNodeFactory(dobj);
        }

        @Override
        public String getDisplayName() {
            return "Grunt Tasks";
        }

        @Override
        public Image getIcon(int type) {
            return ImageUtilities.loadImage(IMAGE);
        }

        @Override
        public Image getOpenedIcon(int type) {
            return ImageUtilities.loadImage(IMAGE);
        }

        @Override
        public ChildFactory<String> getChildNodeFactory() {
            return this._childNodeFactory;
        }
    }