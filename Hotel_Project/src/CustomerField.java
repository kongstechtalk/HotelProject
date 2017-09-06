import javax.swing.*;
import java.awt.*;

/**
 * Created by kongm on 11/27/2016.
 */
public class CustomerField extends JPanel {
    JLabel name, street, city, state, zip, nights, guest, typeLabel;
    JTextField nameField, streetField, cityField, stateField, zipField, nightsField, guestField;
    JPanel container;
    GridLayout grid;
    String customerInfo;

    public CustomerField(String type) {

        customerInfo=type;
        container=new JPanel();
        grid = new GridLayout(7, 2);
        setLayout(new BorderLayout());
        container.setLayout(grid);

        typeLabel=new JLabel(customerInfo, JLabel.CENTER);
        add(typeLabel, BorderLayout.NORTH);

        createLables();
        createFields();
        addComponents();
    }

    public void clearFields(){
        nameField.setText("");
        streetField.setText("");
        cityField.setText("");
        stateField.setText("");
        zipField.setText("");
        nightsField.setText("");
        guestField.setText("");
    }

    public void createLables() {
        name = new JLabel("Name: ", JLabel.CENTER);
        street = new JLabel("Street:", JLabel.CENTER);
        city = new JLabel("City:", JLabel.CENTER);
        state = new JLabel("State: ", JLabel.CENTER);
        zip = new JLabel("Zip: ", JLabel.CENTER);
        nights = new JLabel("# of Nights: ", JLabel.CENTER);
        guest = new JLabel("How many guest: ", JLabel.CENTER);
    }

    public void createFields() {
        nameField = new JTextField(10);
        streetField = new JTextField(10);
        cityField = new JTextField(10);
        stateField = new JTextField(10);
        zipField = new JTextField(10);
        nightsField = new JTextField(10);
        guestField = new JTextField(10);
    }

    public void addComponents() {
        container.add(name);
        container.add(nameField);
        container.add(street);
        container.add(streetField);
        container.add(city);
        container.add(cityField);
        container.add(state);
        container.add(stateField);
        container.add(zip);
        container.add(zipField);
        container.add(nights);
        container.add(nightsField);
        container.add(guest);
        container.add(guestField);
        add(container, BorderLayout.CENTER);

    }

}


