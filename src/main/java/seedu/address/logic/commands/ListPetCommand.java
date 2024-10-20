package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PETS;

import seedu.address.model.Model;

/**
 * Lists all pets in the application to the user.
 */
public class ListPetCommand extends ListCommand {

    /** The command word used to trigger the list pet action. */
    public static final String COMMAND_WORD = "list";

    /** The message displayed when the list of pets is successfully shown. */
    public static final String MESSAGE_SUCCESS = "Listed all pets";

    /**
     * Executes the list pet command, updating the filtered list in the model
     * to show all pets.
     *
     * @param model The {@code Model} which contains the application data.
     * @return The result of the command execution, which contains a success message.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPetList(PREDICATE_SHOW_ALL_PETS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
