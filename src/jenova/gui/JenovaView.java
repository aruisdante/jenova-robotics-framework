/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JenovaView.java
 *
 * Created on Jul 3, 2010, 12:12:59 PM
 */

package jenova.gui;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 * Default interface view for a JenovaBot
 * @author Adam Panzica
 */
public class JenovaView<R,E> extends javax.swing.JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JenovaViewController<R, E> controller;
    
    /** Creates new form JenovaView */
    public JenovaView() {
        initComponents();
        initJenovaConsole();
    }

    public void setController(JenovaViewController<R, E> controller){
        this.controller = controller;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings({ "unchecked", "serial" })
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        viewTabs = new javax.swing.JTabbedPane();
        jenovaBotInfoPanel = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        nameField = new javax.swing.JLabel();
        jenovaConsoleTextAreaScrollPane = new javax.swing.JScrollPane();
        jenovaConsoleTextArea = new javax.swing.JTextPane();
        printErrorLogButton = new javax.swing.JButton();
        printStatusLogButton = new javax.swing.JButton();
        sensorStatusScrollPane = new javax.swing.JScrollPane();
        sensorStatusTable = new javax.swing.JTable();
        hawkEyeLocalMapPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JENOVA");
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        viewTabs.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        viewTabs.setName("JenovaTabs"); // NOI18N
        viewTabs.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                viewTabsFocusGained(evt);
            }
        });

        jenovaBotInfoPanel.setName("JENOVA BOT STATUS"); // NOI18N

        nameLabel.setText("Name:");

        nameField.setText("jLabel1");

        jenovaConsoleTextAreaScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Console", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jenovaConsoleTextArea.setBorder(null);
        jenovaConsoleTextArea.setEditable(false);
        jenovaConsoleTextAreaScrollPane.setViewportView(jenovaConsoleTextArea);

        printErrorLogButton.setText("Print Error Log");
        printErrorLogButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                printErrorLogButtonMouseClicked(evt);
            }
        });

        printStatusLogButton.setText("Print Status Log");
        printStatusLogButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                printStatusLogButtonMouseClicked(evt);
            }
        });

        sensorStatusScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sensor Status", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        sensorStatusTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Name", "Type", "Port", "Raw Value", "Engining Unit Value", "Unit"
            }
        ) {
            @SuppressWarnings("rawtypes")
			Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            @SuppressWarnings("rawtypes")
			public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        sensorStatusTable.getTableHeader().setReorderingAllowed(false);
        sensorStatusTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sensorStatusTableMouseClicked(evt);
            }
        });
        sensorStatusTable.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                sensorStatusTableComponentMoved(evt);
            }
            public void componentResized(java.awt.event.ComponentEvent evt) {
                sensorStatusTableComponentResized(evt);
            }
        });
        sensorStatusScrollPane.setViewportView(sensorStatusTable);

        hawkEyeLocalMapPanel.setBackground(new java.awt.Color(255, 255, 255));
        hawkEyeLocalMapPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "HawkEye Local Map", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        hawkEyeLocalMapPanel.setMaximumSize(new java.awt.Dimension(400, 400));
        hawkEyeLocalMapPanel.setMinimumSize(new java.awt.Dimension(400, 400));
        hawkEyeLocalMapPanel.setName("hawkEyeLocalMap"); // NOI18N
        hawkEyeLocalMapPanel.setPreferredSize(new java.awt.Dimension(400, 400));

        javax.swing.GroupLayout hawkEyeLocalMapPanelLayout = new javax.swing.GroupLayout(hawkEyeLocalMapPanel);
        hawkEyeLocalMapPanel.setLayout(hawkEyeLocalMapPanelLayout);
        hawkEyeLocalMapPanelLayout.setHorizontalGroup(
            hawkEyeLocalMapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 388, Short.MAX_VALUE)
        );
        hawkEyeLocalMapPanelLayout.setVerticalGroup(
            hawkEyeLocalMapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 370, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jenovaBotInfoPanelLayout = new javax.swing.GroupLayout(jenovaBotInfoPanel);
        jenovaBotInfoPanel.setLayout(jenovaBotInfoPanelLayout);
        jenovaBotInfoPanelLayout.setHorizontalGroup(
            jenovaBotInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jenovaBotInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jenovaBotInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jenovaBotInfoPanelLayout.createSequentialGroup()
                        .addGroup(jenovaBotInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jenovaBotInfoPanelLayout.createSequentialGroup()
                                .addComponent(nameLabel)
                                .addGap(18, 18, 18)
                                .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jenovaBotInfoPanelLayout.createSequentialGroup()
                                .addComponent(printErrorLogButton)
                                .addGap(18, 18, 18)
                                .addComponent(printStatusLogButton)))
                        .addGap(923, 923, 923))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jenovaBotInfoPanelLayout.createSequentialGroup()
                        .addGroup(jenovaBotInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(sensorStatusScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)
                            .addComponent(jenovaConsoleTextAreaScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE))
                        .addGap(98, 98, 98)
                        .addComponent(hawkEyeLocalMapPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88))))
        );
        jenovaBotInfoPanelLayout.setVerticalGroup(
            jenovaBotInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jenovaBotInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jenovaBotInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(nameField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addGroup(jenovaBotInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hawkEyeLocalMapPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jenovaBotInfoPanelLayout.createSequentialGroup()
                        .addComponent(sensorStatusScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jenovaConsoleTextAreaScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jenovaBotInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(printErrorLogButton)
                            .addComponent(printStatusLogButton))))
                .addContainerGap())
        );

        viewTabs.addTab("MAIN", null, jenovaBotInfoPanel, "Contains general information about the JENOVA bot");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(viewTabs, javax.swing.GroupLayout.DEFAULT_SIZE, 1270, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(viewTabs, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sensorStatusTableComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_sensorStatusTableComponentResized
        // TODO add your handling code here:
}//GEN-LAST:event_sensorStatusTableComponentResized

    private void sensorStatusTableComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_sensorStatusTableComponentMoved

}//GEN-LAST:event_sensorStatusTableComponentMoved

    private void printStatusLogButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printStatusLogButtonMouseClicked
        controller.firePrintStatusLogButton();
}//GEN-LAST:event_printStatusLogButtonMouseClicked

    private void printErrorLogButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printErrorLogButtonMouseClicked
        controller.firePrintErrorLogButton();
}//GEN-LAST:event_printErrorLogButtonMouseClicked

    private void sensorStatusTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sensorStatusTableMouseClicked
        int clickCount = evt.getClickCount();
        if(clickCount == 2){
            System.out.println("Double Click");
            this.controller.fireSensorTableDoubleClick(this.sensorStatusTable.getSelectedRow());
        }
        
    }//GEN-LAST:event_sensorStatusTableMouseClicked

    private void viewTabsFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_viewTabsFocusGained
        this.controller.updateView();
    }//GEN-LAST:event_viewTabsFocusGained

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        this.controller.updateView();
    }//GEN-LAST:event_formWindowGainedFocus

   
    
    /**
     * Sets the text in the name field
     * @param name Name of the JENOVA bot
     */
    public void setNameField(String name){
    	this.nameField.setText(name);
    }
    
    /************************* CODE FOR HANDELING THE HAWKEYE LOCAL MAP **************************/
    public JPanel getHawkEyeLocalMapPanel(){
    	return this.hawkEyeLocalMapPanel;
    }
    
    /************************* CODE FOR HANDELING THE SENSOR STATUS TABLE ************************/
    public JTable getSensorTable(){
    	return this.sensorStatusTable;
    }
    
    
    /************************* CODE FOR HANDELING THE JENOVA CONSOLE *****************************/
    private StyledDocument jenovaConsoleText;
    Style errorText;
    Style statusText;
    Style consoleText;
    static public enum consoleMessageType {CONSOLE,ERROR,STATUS}; //Enum for console message types
    
    private void initJenovaConsole(){
    	this.jenovaConsoleText = this.jenovaConsoleTextArea.getStyledDocument();
    	//Sets up the formatting for generic console text
    	consoleText = this.jenovaConsoleText.addStyle("CONSOLE", null);
    	StyleConstants.setForeground(consoleText, Color.black);
    	StyleConstants.setFontSize(consoleText, 12);
    	StyleConstants.setFontFamily(consoleText, "Times New Roman");
    	//Sets up the formatting for error message text
    	errorText = this.jenovaConsoleText.addStyle("ERROR", consoleText);
    	StyleConstants.setForeground(errorText, Color.red);
    	StyleConstants.setBold(errorText, true);
    	//Sets up the formatting for status message text
        statusText = this.jenovaConsoleText.addStyle("STATUS", consoleText);
        StyleConstants.setForeground(statusText, Color.green);
        StyleConstants.setBold(statusText, true);
    }
    
    public void setConsoleText(String text, consoleMessageType type){
    	switch(type){
    	case CONSOLE:
    		break;
    	case ERROR:
    		break;
    	case STATUS:
    		break;
    	}
    }
    
    public void addConsoleText(String text, consoleMessageType type){
    	switch(type){
    	case CONSOLE:
    		try {
				this.jenovaConsoleText.insertString(this.jenovaConsoleText.getLength(), text, this.jenovaConsoleText.getStyle("CONSOLE"));
			} catch (BadLocationException e) {
				System.err.println("Error, invalid style type");
			}
    		break;
    	case ERROR:
    		try {
				this.jenovaConsoleText.insertString(this.jenovaConsoleText.getLength(), text, this.jenovaConsoleText.getStyle("ERROR"));
			} catch (BadLocationException e) {
				System.err.println("Error, invalid style type");
			}
    		break;
    	case STATUS:
    		try {
				this.jenovaConsoleText.insertString(this.jenovaConsoleText.getLength(), text, this.jenovaConsoleText.getStyle("STATUS"));
			} catch (BadLocationException e) {
				System.err.println("Error, invalid style type");
			}
    		break;
    	}
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel hawkEyeLocalMapPanel;
    private javax.swing.JPanel jenovaBotInfoPanel;
    private javax.swing.JTextPane jenovaConsoleTextArea;
    private javax.swing.JScrollPane jenovaConsoleTextAreaScrollPane;
    private javax.swing.JLabel nameField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JButton printErrorLogButton;
    private javax.swing.JButton printStatusLogButton;
    private javax.swing.JScrollPane sensorStatusScrollPane;
    private javax.swing.JTable sensorStatusTable;
    private javax.swing.JTabbedPane viewTabs;
    // End of variables declaration//GEN-END:variables
}
