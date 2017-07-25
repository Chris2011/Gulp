package org.netbeans.modules.nbtasks.nodes.childnodes;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import org.netbeans.api.extexecution.ExecutionDescriptor;
import org.netbeans.api.extexecution.ExecutionService;
import org.netbeans.api.extexecution.ExternalProcessBuilder;
import org.openide.awt.NotificationDisplayer;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.Exceptions;
import org.openide.util.NbPreferences;

/**
 *
 * @author Chrl
 */
public class BaseRunableChildNode extends AbstractNode {
    private final FileObject primaryFile;
    private final String key;
    private final String preferenceNode;
    private final String preferenceKey;
    private final String command;

    public BaseRunableChildNode(FileObject primaryFile, String nodeName, String preferenceNode, String preferenceKey, String command) {
        super(Children.LEAF);

        this.primaryFile = primaryFile;
        this.key = nodeName;

        this.preferenceNode = preferenceNode;
        this.preferenceKey = preferenceKey;
        this.command = command;
    }

    @Override
    public Image getIcon(int type) {
        return null; // TODO: Remove icon from node. Null gives warning.
    }

    @Override
    public Action[] getActions(boolean popup) {
        return new Action[]{
            new AbstractAction("Run") {
                @Override
                public void actionPerformed(ActionEvent e) {
//                    try {
                        if (showMissingCommandNotification()) {
                            return;
                        }

//                        ProcessBuilder processBuilder = new ProcessBuilder(getPreferencesNode(), "run", key);
//                        List<String> command1 = processBuilder.command();
//
//                        processBuilder.directory(FileUtil.toFile(primaryFile));
//
//                        Process startedProcess = processBuilder.start();
//
//                        InputStream is = startedProcess.getInputStream();
//                        InputStreamReader isr = new InputStreamReader(is);
//                        BufferedReader br = new BufferedReader(isr);
//                        String line;
//
//                        while ((line = br.readLine()) != null) {
//                            System.out.println(line);
//                        }
//
//                        System.out.println("Done.");

                        ExternalProcessBuilder processBuilder = new ExternalProcessBuilder(getPreferencesNode())
                                .addArgument("run")
                                .addArgument(key)
                                .workingDirectory(FileUtil.toFile(primaryFile));
                        ExecutionDescriptor descriptor = new ExecutionDescriptor().
                                frontWindow(true).
                                showProgress(true).
                                controllable(true);
                        ExecutionService service = ExecutionService.newService(
                                processBuilder,
                                descriptor,
                                key);
                        service.run();
//                    } catch (IOException ex) {
//                        Exceptions.printStackTrace(ex);
//                    }
                }
            }
        };
    }

    private String getPreferencesNode() {
        return NbPreferences.root().node(preferenceNode).get(preferenceKey, null);
    }

    private boolean showMissingCommandNotification() {
        if (getPreferencesNode().isEmpty()) {
            NotificationDisplayer.getDefault().notify("Command missing", new ImageIcon(""), String.format("Please install missing command: %s", command), null);

            return true;
        }

        return false;
    }

    @Override
    public Action getPreferredAction() {
        if (showMissingCommandNotification()) {
            return null;
        }

        return getActions(false)[0];
    }
}
