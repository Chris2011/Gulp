package org.netbeans.modules.nbtasks.nodefactories.typings;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.util.List;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.nodes.BeanNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author chrl
 */
public class TypingsChildNodeFactory extends ChildFactory<String> {
    private final DataObject dobj;

    public TypingsChildNodeFactory(DataObject dobj) {
        this.dobj = dobj;
    }

    @Override
    protected boolean createKeys(List<String> list) {
        FileObject fo = dobj.getPrimaryFile().getFileObject("typings.json");
        JSONParser jsonParser = new JSONParser();

        try {
            JSONObject jsonObject = (JSONObject)jsonParser.parse(fo.asText());
            JSONObject typings = (JSONObject)jsonObject.get("ambientDependencies");
            
            if (typings != null) {
                for (Object typing : typings.keySet()) {
                    list.add((String)typing);
                }
            }
        } catch (IOException | ParseException ex) {
            Exceptions.printStackTrace(ex);
        }

        return true;
    }

    @Override
    protected Node createNodeForKey(final String key) {
        BeanNode node = null;
        try {
            node = new BeanNode(key);

            node.setDisplayName(key);
        } catch (IntrospectionException ex) {
            Exceptions.printStackTrace(ex);
        }

        return node;
    }
}
