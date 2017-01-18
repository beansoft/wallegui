package preferences;

import beansoft.swing.OptionPane;
import beansoft.util.FileUtil;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.Theme;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.InputStream;

// TODO 等待实现.
public class PreferencesDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel editorPane;

    private RTextScrollPane scrollPane;
    private RSyntaxTextArea textArea;

    public PreferencesDialog() {
        setContentPane(contentPane);
        setModal(true);
        setTitle("偏好设置");
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        textArea = createTextArea();
        scrollPane = new RTextScrollPane(textArea, true);
        editorPane.add(scrollPane);

        try {
            textArea.setText(FileUtil.readFileAsString("conf/sign.properties"));
        } catch (Exception e) {
            e.printStackTrace();
            OptionPane.showErrorMessageDialog(this, "无法载入 conf/sign.properties", "读取文件失败");
        }
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    /**
     * Creates the text area for this application.
     *
     * @return The text area.
     */
    private RSyntaxTextArea createTextArea() {
        RSyntaxTextArea textArea = new RSyntaxTextArea(25, 70);
        textArea.setTabSize(3);
        textArea.setCaretPosition(0);
        textArea.setHighlightCurrentLine(true);
        textArea.requestFocusInWindow();
        textArea.setMarkOccurrences(true);
        textArea.setCodeFoldingEnabled(true);
        textArea.setClearWhitespaceLinesEnabled(false);
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PROPERTIES_FILE);
        try {
            InputStream in = getClass().getResourceAsStream("/dark.xml");
            Theme theme = Theme.load(in);
            theme.apply(textArea);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return textArea;
    }

    public static void main(String[] args) {
        PreferencesDialog dialog = new PreferencesDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
