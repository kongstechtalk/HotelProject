import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Kong Lor & Christian Tejada
 * CSCI 1130
 * Hotel Final Project
 * Fall 2016
 */
public class Hotel_Project extends JApplet implements ActionListener, ItemListener{
    //DECLARE INSTANCE VARIABLES or Initialize the instance variable
    CustomerField customer;
    int bedsizePrice, totalPrice;

    String yourSelection, option1, option2, option3, option4, option5, option6;
    JPanel titlePanel, bottomPanel, leftPanel, customerPanel,
            centerPanel, imagePanel, buttonPanel, outputPanel, rightPanel, previewPanel;
    JLabel titleLabel,bottomLabel, kingkongLabel, imageLabel, outputLabel;
    JTextArea output;
    JScrollPane scroll;
    JButton submit, clear;
    //Room Details
    JPanel mainDetailPanel, roomPanel, radioPanel, checkPanel;
    JLabel title;
    ButtonGroup group;
    JRadioButton twinBed, queenBed, kingBed;
    JCheckBox ch1, ch2, ch3, ch4, ch5, ch6;
    //images
    String bedSizePic[] = {"twinbed.png", "queenbed.png", "kingbed.png","pool.png","pets.png","smoking.png","ac.png","directtv.png","wifi.png"};
    Image currentImage, img;
    ImageIcon imgIcon, imgKong, icon;
    JButton preview;
    int nextPic = 0;
    //draw hotel building
    Graphics2D g2d;
    final int WINDOW_WIDTH = 25, WINDOW_HEIGHT = 50, GAP = 5;
    int window1X = 770, window1Y = 419, building1Width = 216, building1Height = 60;
    final int numWindows1 = 7, numStories1 = 4;
    //2nd building
    int window2X = 800, window2Y = 299, building2Width = 156, building2Height = 60;
    final int numWindows2 = 5, numStories2 = 2;
    //3rd building
    int window3X = 830, window3Y = 179, building3Width = 96, building3Height = 60;
    final int numWindows3 = 3, numStories3 = 2;
    //4th building
    int window4X = 860, window4Y = 119, building4Width = 35, building4Height = 60;
    final int numWindows4 = 1, numStories4 = 1;

    public void init() {
        //
        setLayout(new BorderLayout());
        setupTitle();
        setupBottom();
        setupLeftSide();
        setupRightSide();
        setupCenter();

        registerButtons();
        registerBedSizes();
        registerOptions();
    }

    public void setupCenter() {
        centerPanel = new JPanel(new GridLayout(3, 1));
        //imagepanel
        imagePanel = new JPanel(new BorderLayout());
        imageLabel = new JLabel();
        currentImage = getImage(getCodeBase(), bedSizePic[0]);
        imgIcon = new ImageIcon(currentImage);
        imageLabel = new JLabel(imgIcon);
        icon = new ImageIcon();
        icon.setImage(currentImage);
        imageLabel.setIcon(icon);
        imagePanel.add(imageLabel);
        //preview button
        previewPanel = new JPanel(new BorderLayout());
        outputLabel = new JLabel("<html><br><br><br><br><h1 style=\"font-family:comic sans ms;\"><font size=+2 color=red> Your Checkout Receipt: </font></h1></hmtl>");
        preview = new JButton("Click Here to Preview Pictures");
        preview.addActionListener(this);
        previewPanel.add(preview, BorderLayout.NORTH);
        previewPanel.add(outputLabel, BorderLayout.CENTER);
        //set up output
        outputPanel = new JPanel(new BorderLayout());
        output = new JTextArea(15, 20);
        scroll = new JScrollPane(output);
        outputPanel.add(scroll);

        centerPanel.add(imagePanel);
        centerPanel.add(previewPanel);
        centerPanel.add(outputPanel);

        add(centerPanel, BorderLayout.CENTER);
    }

    public void setupLeftSide() {
        leftPanel = new JPanel(new GridLayout(3, 1));
        //add customer information
        customerPanel = new JPanel();
        customer = new CustomerField("<html><font size=+1 color=red><b><i>Customer Information</FONT></CENTER></html>");
        customerPanel.add(customer, LEFT_ALIGNMENT);

        //submit and clear button
        buttonPanel = new JPanel(new FlowLayout());
        submit = new JButton("Submit");
        clear = new JButton("Clear");
        buttonPanel.add(this.submit);
        buttonPanel.add(this.clear);
        add(this.buttonPanel);


        //Detail Panel
        mainDetailPanel = new JPanel(new BorderLayout());
        mainDetailPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        roomPanel = new JPanel(new GridLayout(2, 1));
        radioPanel = new JPanel(new GridLayout(3, 1));
        checkPanel = new JPanel(new GridLayout(3, 3));
        title = new JLabel("<html><b><font size=+2 color=red>Room Details</font></b></html>");

        group = new ButtonGroup();
        twinBed = new JRadioButton("Twin-$50 Per Night");
        queenBed = new JRadioButton("Queen-$60 Per Night");
        kingBed = new JRadioButton("King-$80 Per Night");
        twinBed.setFocusPainted(false);
        queenBed.setFocusPainted(false);
        kingBed.setFocusPainted(false);
        group.add(twinBed);
        group.add(queenBed);
        group.add(kingBed);
        radioPanel.add(twinBed);
        radioPanel.add(queenBed);
        radioPanel.add(kingBed);
        roomPanel.add(radioPanel);

        ch1 = new JCheckBox("A/C");
        ch2 = new JCheckBox("Smoking");
        ch3 = new JCheckBox("Pets");
        ch4 = new JCheckBox("Wifi");
        ch5 = new JCheckBox("Direct TV");
        ch6 = new JCheckBox("Pool Access");
        ch1.setFocusPainted(false);
        ch2.setFocusPainted(false);
        ch3.setFocusPainted(false);
        ch4.setFocusPainted(false);
        ch5.setFocusPainted(false);
        ch6.setFocusPainted(false);
        checkPanel.add(ch1);
        checkPanel.add(ch2);
        checkPanel.add(ch3);
        checkPanel.add(ch4);
        checkPanel.add(ch5);
        checkPanel.add(ch6);
        roomPanel.add(checkPanel);

        mainDetailPanel.add(title, BorderLayout.NORTH);

        mainDetailPanel.add(roomPanel);

        //End of Detail Panel

        leftPanel.add(customerPanel);
        leftPanel.add(mainDetailPanel);
        leftPanel.add(buttonPanel);


        //add to to left panel
        add(leftPanel, BorderLayout.WEST);

    }
    public void registerButtons(){
        submit.addActionListener(this);
        clear.addActionListener(this);
    }
    public void registerBedSizes(){
        twinBed.addItemListener(this);
        queenBed.addItemListener(this);
        kingBed.addItemListener(this);
    }
    public void registerOptions(){
        ch1.addItemListener(this);
        ch2.addItemListener(this);
        ch3.addItemListener(this);
        ch4.addItemListener(this);
        ch5.addItemListener(this);
        ch6.addItemListener(this);
    }

    public void setupTitle() {
        titlePanel = new JPanel(new FlowLayout());
        titleLabel = new JLabel("<html><h1 style=\"font-family:comic sans ms;\"><font size=+3 color=red><b><i>RESERVE YOUR STAY @ " +
                "CHRIS & KONG'S HOTEL</i></b></font></h1></html>");
        titlePanel.add(titleLabel);
        titlePanel.setBackground(Color.DARK_GRAY);
        add(titlePanel, BorderLayout.NORTH);
    }
    public void setupBottom() {
        bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBackground(Color.darkGray);
        bottomLabel = new JLabel("<html><font size=+2 color=red><b><i>Dinner is on us...If King Kong attacks you during your stay!</CENTER></html>");
        bottomPanel.add(bottomLabel);
        add(bottomPanel, BorderLayout.SOUTH);
    }
    public void setupRightSide() {
        rightPanel = new JPanel(new FlowLayout());
        img = getImage(getCodeBase(), "kingkong2.jpg");
        imgKong = new ImageIcon(img);
        kingkongLabel = new JLabel(imgKong);
        rightPanel.add(kingkongLabel);
        add(rightPanel, BorderLayout.EAST);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton eventButton = (JButton) e.getSource();

        if (eventButton == submit) {
            totalPrice=(Integer.parseInt(customer.nightsField.getText())*(bedsizePrice));
            output.setText("----Customer Information----" + "\n" +"\n"+
                    "Name:" + customer.nameField.getText() + "\n" +
                    "Street:" + customer.streetField.getText() + "\n" +
                    "City:" + customer.cityField.getText() + "\n" +
                    "State:" + customer.stateField.getText() + "\n" +
                    "Zip:" + customer.zipField.getText() + "\n" +
                    "# of Guest:" + customer.guestField.getText()+ "\n" +
                    "# of Nights:" + customer.nightsField.getText()+ "\n"+"\n"+
                    "----Room Details----"+"\n"+"\n"+
                    "Bed Selection:  " +yourSelection + "\n"+
                    "Options Include:" +"\n"+
                    option1 + "\n"+
                    option2 + "\n" +
                    option3 + "\n" +
                    option4 + "\n" +
                    option5+ "\n" +
                    option6 + "\n" +"\n"+
                    "----------------------------"+"\n"+
                    "Total Price: $ " + totalPrice +"\n");
        }
        if (eventButton == this.clear) {
            this.output.setText("");
        }
        if (eventButton == preview) {
            nextPic++;
            if (nextPic >= bedSizePic.length)
                nextPic = 0;
            currentImage = getImage(getCodeBase(), bedSizePic[nextPic]);
            icon.setImage(currentImage);
            repaint();
        }
        customer.clearFields();

    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getSource();

        if (source == twinBed) {
            bedsizePrice = 50;
            yourSelection = "Twin Size Bed $50.00 per Night";
        }
        if (source == queenBed) {
            bedsizePrice = 60;
            yourSelection = "Queen Size Bed $60.00 per Night";
        }
        if (source == kingBed) {
            bedsizePrice = 80;
            yourSelection = "King Size Bed $80.00 per Night";
        }
        //options for room
        if (ch1.isSelected()) {
            option1 = "AC - Yes ";
        } else {
            option1 = "AC - No";
        }
        if (ch2.isSelected()) {
            option2 = "Smoking - Yes";
        } else {
            option2 = "Smoking - No";
        }
        if (ch3.isSelected()) {
            option3 = "Pets - Yes";
        } else {
            option3 = "Pets - No";
        }
        if (ch4.isSelected()) {
            option4 = "Wifi - Yes";
        } else {
            option4 = "Wifi - No";
        }
        if (ch5.isSelected()) {
            option5 = "Direct TV - Yes";
        } else {
            option5 = "Direct TV - No";
        }
        if (ch6.isSelected()) {
            option6 = "Pool Access - Yes";
        } else {
            option6 = "Pool Access - No";
        }

        repaint();
    }

    public void paint(Graphics g){
        super.paint(g);
        g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        drawBuilding2();
        drawBuilding3();
        drawBuilding4();
        drawBuilding5();
        drawDoor();

    }
    public void drawDoor(){
        g2d.setColor(Color.red);
        g2d.fillRect(860,604,WINDOW_WIDTH,WINDOW_HEIGHT);
    }
    //second building
    public void drawBuilding2(){
        for (int count = 0; count< numStories1; count++){
            int y= (window1Y)+(count*(building1Height));
            drawStories2(y);
            for (int i = 0; i < numWindows1; i++){
                drawWindow2(y,i);
            }
        }//end of loop
    }
    public void drawStories2(int y){
        g2d.setColor(Color.BLACK);
        g2d.fillRect(window1X - GAP, y, building1Width, building1Height);
    }
    public void drawWindow2(int y, int i) {
        g2d.setColor(Color.white);
        g2d.fillRect((WINDOW_WIDTH + GAP) * i + window1X, y + GAP, WINDOW_WIDTH, WINDOW_HEIGHT);
    }
    public void drawBuilding3(){
        for (int count = 0; count< numStories2; count++){
            int y= (window2Y)+(count*(building2Height));
            drawStories3(y);
            for (int i = 0; i < numWindows2; i++){
                drawWindow3(y,i);
            }
        }//end of loop
    }
    public void drawStories3(int y){
        g2d.setColor(Color.BLACK);
        g2d.fillRect(window2X - GAP, y, building2Width, building2Height);
    }
    public void drawWindow3(int y, int i) {
        g2d.setColor(Color.WHITE);
        g2d.fillRect((WINDOW_WIDTH + GAP) * i + window2X, y + GAP, WINDOW_WIDTH, WINDOW_HEIGHT);
    }
    public void drawBuilding4(){
        for (int count = 0; count< numStories3; count++){
            int y= (window3Y)+(count*(building3Height));
            drawStories4(y);
            for (int i = 0; i < numWindows3; i++){
                drawWindow4(y,i);
            }
        }//end of loop
    }
    public void drawStories4(int y){
        g2d.setColor(Color.BLACK);
        g2d.fillRect(window3X - GAP, y, building3Width, building3Height);
    }
    public void drawWindow4(int y, int i) {
        g2d.setColor(Color.white);
        g2d.fillRect((WINDOW_WIDTH + GAP) * i + window3X, y + GAP, WINDOW_WIDTH, WINDOW_HEIGHT);
    }
    public void drawBuilding5(){
        for (int count = 0; count< numStories4; count++){
            int y= (window4Y)+(count*(building4Height));
            drawStories5(y);
            for (int i = 0; i < numWindows4; i++){
                drawWindow5(y,i);
            }
        }//end of loop
    }
    public void drawStories5(int y){
        g2d.setColor(Color.BLACK);
        g2d.fillRect(window4X - GAP, y, building4Width, building4Height);
    }
    public void drawWindow5(int y, int i) {
        g2d.setColor(Color.WHITE);
        g2d.fillRect((WINDOW_WIDTH + GAP) * i + window4X, y + GAP, WINDOW_WIDTH, WINDOW_HEIGHT);
    }

}
