package org.netbeans.modules.nbtasks.nodefactories.npm;

import java.io.IOException;
import java.util.List;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.netbeans.modules.nbtasks.nodes.childnodes.BaseRunableChildNode;
import org.openide.filesystems.FileUtil;
import org.openide.util.NbPreferences;

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
    protected Node createNodeForKey(final String nodeName) {
        BaseRunableChildNode node = new BaseRunableChildNode(dobj.getPrimaryFile(), nodeName, "org/netbeans/modules/javascript/nodejs/nodejs", "npm.path", "npm");

        node.setDisplayName(nodeName);

        return node;
    }
}
