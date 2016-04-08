package org.netbeans.modules.nbtasks.nodefactories.npm;

import java.awt.event.ActionEvent;
import java.beans.IntrospectionException;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.netbeans.api.extexecution.ExecutionDescriptor;
import org.netbeans.api.extexecution.ExecutionService;
import org.netbeans.api.extexecution.ExternalProcessBuilder;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
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
public class NpmScriptsChildNodeFactory extends ChildFactory<String> {
    private final DataObject dobj;

    public NpmScriptsChildNodeFactory(DataObject dobj) {
        this.dobj = dobj;
    }

    @Override
    protected boolean createKeys(List<String> list) {
        FileObject fo = dobj.getPrimaryFile().getFileObject("package.json");
        JSONParser jsonParser = new JSONParser();

        try {
            JSONObject jsonObject = (JSONObject)jsonParser.parse(fo.asText());
            JSONObject npmScripts = (JSONObject)jsonObject.get("scripts");
            
            if (npmScripts != null) {
                for (Object npmScript : npmScripts.keySet()) {
                    list.add((String)npmScript);
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
