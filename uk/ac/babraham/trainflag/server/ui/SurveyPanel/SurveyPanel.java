package uk.ac.babraham.trainflag.server.ui.SurveyPanel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import uk.ac.babraham.trainflag.survey.SurveyQuestion;
import uk.ac.babraham.trainflag.survey.SurveySet;

public class SurveyPanel extends JPanel implements MouseListener, ActionListener {

	private JTable surveyTable;
	private SurveySet surveys;
	
	public SurveyPanel (SurveySet surveys) {
		this.surveys = surveys;
		setLayout(new BorderLayout());

		surveyTable = new JTable(new SurveySetTableModel(surveys));
		surveyTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		surveyTable.addMouseListener(this);
		surveyTable.setFont(new Font("Arial", Font.PLAIN, 20));
		surveyTable.setRowHeight(30);
		add(new JScrollPane(surveyTable),BorderLayout.CENTER);

		JPanel surveyButtonPanel = new JPanel();
		
		JButton loadSurveysButton = new JButton("Load Surveys");
		loadSurveysButton.setActionCommand("load_surveys");
		loadSurveysButton.addActionListener(this);
		surveyButtonPanel.add(loadSurveysButton);
		
		JButton saveSurveysButton = new JButton("Save Surveys");
		saveSurveysButton.setActionCommand("save_surveys");
		saveSurveysButton.addActionListener(this);
		surveyButtonPanel.add(saveSurveysButton);
		
		JButton newSurveyButton = new JButton("Add Survey");
		newSurveyButton.setActionCommand("new_survey");
		newSurveyButton.addActionListener(this);
		surveyButtonPanel.add(newSurveyButton);
		
		JButton askSurveyButton = new JButton("Ask Survey");
		askSurveyButton.setActionCommand("ask_survey");
		askSurveyButton.addActionListener(this);
		surveyButtonPanel.add(askSurveyButton);	
		
		add(surveyButtonPanel,BorderLayout.SOUTH);

	}

	@Override
	public void mouseClicked(MouseEvent me) {
		if (me.getSource() == surveyTable) {
			// This comes from the survey table
			if (me.getClickCount() == 2) {
				new SurveyQuestionEditor(surveys.questions()[surveyTable.getSelectedRow()]);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equals("new_survey")) {
			SurveyQuestion newQuestion = new SurveyQuestion("Why is a raven like a writing desk?");
			new SurveyQuestionEditor(newQuestion);
			SurveyPanel.this.surveys.addQuestion(newQuestion);
		}
		else if (ae.getActionCommand().equals("load_surveys")) {
			// TODO: Load surveys
		}
		else if (ae.getActionCommand().equals("save_surveys")) {
			// TODO: Load surveys
		}
		else if (ae.getActionCommand().equals("ask_survey")) {
			// TODO: Load surveys
		}
	}
	
	
}
