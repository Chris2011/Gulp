package org.netbeans.modules.nbtasks.nodes.childnodes;

import java.awt.Image;
import org.netbeans.api.annotations.common.StaticResource;
import org.netbeans.modules.nbtasks.nodefactories.npm.NpmScriptsChildNodeFactory;
import org.openide.loaders.DataObject;
import org.openide.nodes.FilterNode;
import org.openide.util.ImageUtilities;

/**
 *
 * @author chrl
 */
    public class NpmScriptsChildNode extends FilterNode implements INbTasksNode {

        @StaticResource
        private static final String IMAGE = "org/netbeans/modules/nbtasks/resources/npm.png";

        public NpmScriptsChildNode(DataObject dobj) {
            super(dobj.getNodeDelegate(), Children.create(new NpmScriptsChildNodeFactory(dobj), true));
        }

        @Override
        public String getDisplayName() {
            return "NPM Scripts";
        }

        @Override
        public Image getIcon(int type) {
            return ImageUtilities.loadImage(IMAGE);
        }

        @Override
        public Image getOpenedIcon(int type) {
            return ImageUtilities.loadImage(IMAGE);
        }
    }