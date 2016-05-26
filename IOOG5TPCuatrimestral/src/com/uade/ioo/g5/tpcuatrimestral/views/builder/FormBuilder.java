package com.uade.ioo.g5.tpcuatrimestral.views.builder;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class FormBuilder {

	private int gridy = -1;
	
	private JPanel form;

	/**
	 * @param form
	 */
	public FormBuilder(JPanel form) {
		super();
		this.form = form;
	}

	public void addLabel(String label) {
		GridBagConstraints cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = ++gridy;
		cons.weighty = 1.0;
		cons.weightx = 0;
		cons.insets = new Insets(0, 10, 0, 10);
		cons.anchor = GridBagConstraints.WEST;
		form.add(new JLabel(label), cons);
	}

	public void addValue(Component comp) {
		GridBagConstraints cons = new GridBagConstraints();
		cons.gridx = 1;
		cons.gridy = gridy;
		cons.weighty = 0;
		cons.weightx = 0;
		cons.insets = new Insets(0, 10, 0, 10);
		cons.anchor = GridBagConstraints.WEST;
		form.add(comp, cons);
	}
}
