package com.milada.gameoflife;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class UI extends JFrame{

    private static String[] languageCodes; // The list of Language Codes
    private static String USER_LANGUAGE; // The code the user picked. It's named in caps because it's important.

    private static Language language; // The language pack the UI will use

    //UI Variables
    private static final int DIVIDER_LOCATION = 320;
    private JButton ageMenu, educationMenu, jobMenu, familyMenu, propertyMenu, personalMenu, actionsMenu, statsMenu;
    private JMenuBar menuBar;
    private JSplitPane splitPane;
    private JScrollPane scrollPane;
    private JPanel logPanel, educationPanel, jobPanel, familyPanel, propertyPanel, personalPanel, actionsPanel, statsPanel, dummyPanel;
    private JTextArea logTextArea;
    //EDUCATION
    private JButton edu_studyButton, edu_dropoutButton, edu_upgradeButton, edu_applyButton;
    private JLabel edu_enrolledIn, edu_currentGrade, edu_requiredGrade, edu_certificatesEarned;
    //JOB
    JButton job_jobSearchButton, job_raiseButton, job_promoButton, job_quitButton, job_retireButton, job_detailsButton;
    JLabel job_currentJob, job_wage, job_hoursPerWeek, job_monthlyPay, job_yearlyPay;
    //FAMILY
    //PROPERTY
    private JButton pro_carsButton, pro_housesButton, pro_itemsButton;
    private JLabel pro_netWorth, pro_insurance;
    //PERSONAL
    //ACTIONS
    //STATS
    private JLabel sta_bankAccount;

    /**
     * Default and only constructor
     */
    UI() {
        super(Core.GAME_TITLE + " " + Version.getVersion());
        setLayout(new GridLayout());
        createMenus();
        createPanesAndPanels();
        addComponentsToUI();
    }

    /**
     * Build the UI and make it visible to the user
     */
    void build() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(true); // Flash the Window when something updates
        setLocationRelativeTo(null); // Put the Window in the middle of the screen
        setResizable(false); // Make sure the user can't resize the Window
        setSize(640,480);
        setVisible(true); // Display the Window (and hope nothing fails)
    }


    /**
     * Create the JMenu for the UI
     */
    private void createMenus() {
        menuBar = new JMenuBar();
        ageMenu = new JButton(language.AgeMenu);
        educationMenu = new JButton(language.EducationMenu);
        jobMenu = new JButton(language.JobMenu);
        familyMenu = new JButton(language.FamilyMenu);
        propertyMenu = new JButton(language.PropertyMenu);
        personalMenu = new JButton(language.PersonalMenu);
        actionsMenu = new JButton(language.ActionsMenu);
        statsMenu = new JButton(language.StatsMenu);

        ageMenu.addActionListener(e -> Core.life.age());
        educationMenu.addActionListener(e -> {
            splitPane.setRightComponent(educationPanel);
            splitPane.setDividerLocation(DIVIDER_LOCATION);
        });
        jobMenu.addActionListener(e -> {
            splitPane.setRightComponent(jobPanel);
            splitPane.setDividerLocation(DIVIDER_LOCATION);
        });
        familyMenu.addActionListener(e -> {
            splitPane.setRightComponent(familyPanel);
            splitPane.setDividerLocation(DIVIDER_LOCATION);
        });
        propertyMenu.addActionListener(e -> {
            splitPane.setRightComponent(propertyPanel);
            splitPane.setDividerLocation(DIVIDER_LOCATION);
        });
        personalMenu.addActionListener(e -> {
            splitPane.setRightComponent(personalPanel);
            splitPane.setDividerLocation(DIVIDER_LOCATION);
        });
        actionsMenu.addActionListener(e -> {
            splitPane.setRightComponent(actionsPanel);
            splitPane.setDividerLocation(DIVIDER_LOCATION);
        });
        statsMenu.addActionListener(e -> {
            splitPane.setRightComponent(statsPanel);
            splitPane.setDividerLocation(DIVIDER_LOCATION);
        });
    }

    private void createPanesAndPanels() {
        createPanes();
        createPanels();
    }

    private void createPanes() {
        splitPane = new JSplitPane() {
            private final int location = 320;
            {
                setDividerLocation(location);
            }
            @Override
            public int getDividerLocation() {
                return location;
            }
            @Override
            public int getLastDividerLocation() {
                return location;
            }
        }; // Main UI panel
        logPanel = new JPanel(); // Left hand side
        scrollPane = new JScrollPane();
        logTextArea = new JTextArea();

        logTextArea.setEditable(false);

        dummyPanel = new JPanel(new FlowLayout());
        dummyPanel.add(new JLabel(language.DummyPanelLabel));
        // Modify the splitPane
        splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(320);
        splitPane.setLeftComponent(logPanel);
        splitPane.setRightComponent(dummyPanel);
        add(splitPane);

        // Modify the Log window
        logPanel.setLayout(new BoxLayout(logPanel, BoxLayout.Y_AXIS));
        logPanel.add(scrollPane);
        scrollPane.setViewportView(logTextArea);
    }

    private void createPanels() {
        educationPanel = new JPanel(new GridLayout(0,1));
        jobPanel = new JPanel(new GridLayout(0,1));
        familyPanel = new JPanel(new GridLayout(0,1));
        propertyPanel = new JPanel(new GridLayout(0,1));
        personalPanel = new JPanel(new GridLayout(0,1));
        actionsPanel = new JPanel(new GridLayout(0,1));
        statsPanel = new JPanel(new GridLayout(0,1));

        createEducationPanel();
        createJobPanel();
        createFamilyPanel();
        createPropertyPanel();
        createPersonalPanel();
        createActionsPanel();
        createStatsPanel();
    }

    private void createEducationPanel() {
        educationPanel.add(edu_enrolledIn = new JLabel(language.Edu_CurrentlyEnrolled));
        educationPanel.add(edu_currentGrade = new JLabel(language.Edu_CurrentGrade));
        educationPanel.add(edu_requiredGrade = new JLabel(language.Edu_RequiredGrade));
        educationPanel.add(edu_certificatesEarned = new JLabel(language.Edu_CertificatesEarned));
        educationPanel.add(edu_studyButton = new JButton(language.Edu_StartStudyButton));
        educationPanel.add(edu_dropoutButton = new JButton(language.Edu_DropoutButton));
        educationPanel.add(edu_upgradeButton = new JButton(language.Edu_UpgradeButton));
        educationPanel.add(edu_applyButton = new JButton(language.Edu_ApplyButton));
        edu_studyButton.setEnabled(false);
        edu_dropoutButton.setEnabled(false);
        edu_upgradeButton.setEnabled(false);
        edu_applyButton.setEnabled(false);
    }

    private void createJobPanel() {
        jobPanel.add(job_currentJob = new JLabel(language.Job_CurrentJob));
        jobPanel.add(job_hoursPerWeek = new JLabel(language.Job_HoursPerWeek));
        jobPanel.add(job_wage = new JLabel(language.Job_Wage));
        jobPanel.add(job_monthlyPay = new JLabel(language.Job_MonthlyPay));
        jobPanel.add(job_yearlyPay = new JLabel(language.Job_YearlyPay));
        jobPanel.add(job_raiseButton = new JButton(language.Job_RaiseButton));
        jobPanel.add(job_promoButton = new JButton(language.Job_PromoButton));
        jobPanel.add(job_detailsButton = new JButton(language.Job_DetailsButton));
        jobPanel.add(job_quitButton = new JButton(language.Job_QuitButton));
        jobPanel.add(job_retireButton = new JButton(language.Job_RetireButton));
        jobPanel.add(job_jobSearchButton = new JButton(language.Job_JobSearchButton));
        job_raiseButton.setEnabled(false);
        job_promoButton.setEnabled(false);
        job_detailsButton.setEnabled(false);
        job_quitButton.setEnabled(false);
        job_retireButton.setEnabled(false);
        job_jobSearchButton.setEnabled(false);
    }

    private void createFamilyPanel() {

    }

    private void createPropertyPanel() {
        propertyPanel.add(pro_netWorth = new JLabel(language.Pro_NetWorth));
        propertyPanel.add(pro_insurance = new JLabel(language.Pro_Insurance));
        propertyPanel.add(pro_carsButton = new JButton(language.Pro_CarsButton));
        propertyPanel.add(pro_housesButton = new JButton(language.Pro_HousesButton));
        propertyPanel.add(pro_itemsButton = new JButton(language.Pro_ItemsButton));
    }

    private void createPersonalPanel() {

    }

    private void createActionsPanel() {

    }

    private void createStatsPanel() {
        statsPanel.add(sta_bankAccount = new JLabel(language.Sta_BankAccount));
    }

    public void updateAllLabels() {
        sta_bankAccount.setText(language.Sta_BankAccount + Core.life.BANK_ACCOUNT);
    }

    /**
     * Add the components to the UI. THIS WILL BE CHANGED! Panels will be used instead
     */
    private void addComponentsToUI() {
        menuBar.add(ageMenu);
        menuBar.add(educationMenu);
        menuBar.add(jobMenu);
        menuBar.add(familyMenu);
        menuBar.add(propertyMenu);
        menuBar.add(personalMenu);
        menuBar.add(actionsMenu);
        menuBar.add(statsMenu);
        setJMenuBar(menuBar);
    }

    void showStatsPanel() {
        splitPane.setRightComponent(statsPanel);
        splitPane.setDividerLocation(DIVIDER_LOCATION);
    }

    void logToPanel(String message) {
        logTextArea.append(message);
    }

    void reportToUIWithNotification(String title, String message) {
        JDialog d = new JDialog();
        d.setTitle(title);
        d.setType(Type.POPUP);
        d.add(new JLabel(message));
        d.pack();
        d.setLocationRelativeTo(null);
        d.setVisible(true);
        d.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }
    

    /**
     * Set the language of the UI
     * @return Whether or not the Language was set
     */
    static boolean setLanguage() {
        Core.log("Setting the UI Language", Core.LOG_TYPE_INFO, Core.getClassName());
        String languageCodeData = FileUtils.readFileAsString("data/languages.conf");
        if (languageCodeData.equals(Core.NULL_STRING)) {
            Core.log("Invalid Language file, exiting!", Core.LOG_TYPE_ERROR, Core.getClassName());
            Core.exit(1);
        }
        languageCodes = languageCodeData.split(Pattern.quote("\n"));
        for (String code : languageCodes) if (code.startsWith("USERLANGUAGE")) USER_LANGUAGE = code.split(Pattern.quote("="))[1];
        language = new Language(USER_LANGUAGE);
        //Core.log("Language has been set!", Core.LOG_TYPE_INFO, Core.getClassName());
        return true;
    }
}
