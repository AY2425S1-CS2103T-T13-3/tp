package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.owner.Owner;
import seedu.address.model.person.Person;

/**
 * Panel containing two lists: one for owners and one for pets.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Owner> ownerListView;

    @FXML
    private ListView<Person> petListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Person> personList, ObservableList<Owner> ownerList) {
        super(FXML);
        ownerListView.setItems(ownerList);
        ownerListView.setCellFactory(listView -> new OwnerListViewCell());

        // Temporarily using ownerList for both, but you can modify this once the petList is implemented
        petListView.setItems(personList); // Replace with petList later
        petListView.setCellFactory(listView -> new PersonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(person, getIndex() + 1).getRoot());
            }
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Owner} using a {@code OwnerCard}.
     */
    class OwnerListViewCell extends ListCell<Owner> {
        @Override
        protected void updateItem(Owner owner, boolean empty) {
            super.updateItem(owner, empty);

            if (empty || owner == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new OwnerCard(owner, getIndex() + 1).getRoot());
            }
        }
    }


}
