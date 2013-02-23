/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JenovaSensorEditor.java
 *
 * Created on Nov 30, 2010, 11:10:48 AM
 */

package jenova.giu.editor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;

import jenova.sensors.ISensor;
/**
 * Creates an editor panel for modifying the values contained in an ISensor object
 * @author Adam Panzica
 *
 * @param <R> Raw value type for ISensor to be edited
 * @param <E> Engineering value type for ISensor to be edited
 */
@SuppressWarnings("serial")
public class JenovaSensorEditor<R,E> extends JenovaEditorGUI<ISensor<R, E>, JenovaSensorEditorController<R,E>> {

    /** Creates new form JenovaSensorEditor */
    public JenovaSensorEditor(ISensor<R, E> input, JenovaSensorEditorController<R, E> controller){
		super(input, controller);
		initComponents();
		initFields();
    }

    private void initFields() {
		this.nameTextField.setText(this.input.getName());
		this.portTextField.setText(Integer.toString(this.input.getPort()));
		this.typeTextField.setText(this.input.getSensorType());
		this.unitTextField.setText(this.input.getEngUnit());
	}

	/** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nameLabel = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        portLabel = new javax.swing.JLabel();
        portTextField = new javax.swing.JTextField();
        typeLabel = new javax.swing.JLabel();
        typeTextField = new javax.swing.JTextField();
        unitLabel = new javax.swing.JLabel();
        unitTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        valueTable = new javax.swing.JTable();
        saveButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setMaximumSize(getMinimumSize());
        setMinimumSize(new java.awt.Dimension(323, 225));
        setName("Jenova Sensor Editor"); // NOI18N
        setRequestFocusEnabled(false);

        nameLabel.setText("Sensor Name");

        nameTextField.setText("jTextField1");
        nameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nameTextFieldKeyTyped(evt);
            }
        });

        portLabel.setText("Sensor Port");

        portTextField.setText("jTextField1");
        portTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                portTextFieldKeyTyped(evt);
            }
        });

        typeLabel.setText("Sensor Type");

        typeTextField.setText("jTextField1");
        typeTextField.setEnabled(false);

        unitLabel.setText("Engineering Unit");

        unitTextField.setText("jTextField1");
        unitTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                unitTextFieldKeyTyped(evt);
            }
        });

        valueTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Raw Value", "Engineering Value"
            }
        ) {
            @SuppressWarnings("rawtypes")
			Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Double.class
            };

            @SuppressWarnings("rawtypes")
			public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        valueTable.setCellSelectionEnabled(true);
        jScrollPane1.setViewportView(valueTable);
        valueTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        valueTable.getColumnModel().getColumn(0).setResizable(false);
        valueTable.getColumnModel().getColumn(1).setResizable(false);

        saveButton.setText("Save");
        saveButton.setToolTipText("Save Changes to the Sensor");
        saveButton.setEnabled(false);
        saveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveButtonMouseClicked(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(nameTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(portTextField, 0, 0, Short.MAX_VALUE)
                            .addComponent(portLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(typeTextField)
                            .addComponent(typeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(unitTextField)
                            .addComponent(unitLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(185, Short.MAX_VALUE)
                .addComponent(saveButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(portLabel)
                    .addComponent(typeLabel)
                    .addComponent(unitLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(portTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(typeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(unitTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(saveButton))
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleName("Jenova Sensor Editor");
    }// </editor-fold>//GEN-END:initComponents

    private void saveButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveButtonMouseClicked
        this.controller.fireSaveButton(this.input);
    }//GEN-LAST:event_saveButtonMouseClicked

    private void cancelButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseClicked
        this.controller.fireCancelButton();
    }//GEN-LAST:event_cancelButtonMouseClicked

    private void nameTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameTextFieldKeyTyped
        this.controller.fireTextEntry(this.input);
    }//GEN-LAST:event_nameTextFieldKeyTyped

    private void portTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_portTextFieldKeyTyped
        this.controller.fireTextEntry(this.input);
    }//GEN-LAST:event_portTextFieldKeyTyped

    private void unitTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_unitTextFieldKeyTyped
        this.controller.fireTextEntry(this.input);
    }//GEN-LAST:event_unitTextFieldKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JLabel portLabel;
    private javax.swing.JTextField portTextField;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel typeLabel;
    private javax.swing.JTextField typeTextField;
    private javax.swing.JLabel unitLabel;
    private javax.swing.JTextField unitTextField;
    private javax.swing.JTable valueTable;
    // End of variables declaration//GEN-END:variables
    
    /**
     * @return The JTextField corresponding to the Name of the sensor
     */
    public JTextField getNameField(){
    	return this.nameTextField;
    }
    
    /**
     * @return The JTextField corresponding to the port of the sensor
     */
    public JTextField getPortField(){
    	return this.portTextField;
    }
    
    /**
     * @return The JTextField corresponding to the engineering unit of the sensor
     */
    public JTextField getUnitField(){
    	return this.unitTextField;
    }
    
    /**
     * @return The JTable corresponding to the Value Table of the sensor
     */
    public JTable getValueTable(){
    	return this.valueTable;
    }
    
    
    public String toString(){
    	return "Sensor Editor, Sensor: "+this.input.toString();
    }
    
    /**
     * @return JButton representing the Save button in the editor
     */
	public JButton getSaveButton() {
		return this.saveButton;
	}

}
