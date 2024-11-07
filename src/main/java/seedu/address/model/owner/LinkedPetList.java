package seedu.address.model.owner;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.link.Linkable;
import seedu.address.model.pet.Pet;
import seedu.address.model.pet.exceptions.DuplicatePetException;
import seedu.address.model.pet.exceptions.PetNotFoundException;

/**
 * A list of pets that enforces uniqueness between its elements and does not allow nulls.
 * A pet is considered unique by comparing using {@code Pet#isSamePet(Pet)}. As such, adding and updating of
 * pets uses Pet#isSamePet(Pet) for equality so as to ensure that the pet being added or updated is
 * unique in terms of identity in the UniquePetList. However, the removal of a pet uses Pet#equals(Object) so
 * as to ensure that the pet with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Pet#isSamePet(Pet)
 */
public class LinkedPetList implements Iterable<Pet> {

    private final ObservableList<Pet> internalList = FXCollections.observableArrayList();
    private final ObservableList<Pet> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent pet as the given argument.
     */
    public boolean contains(Pet toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePet);
    }

    /**
     * Returns the observable list
     */
    public ObservableList<Pet> getList() {
        return this.internalList;
    }

    /**
     * clears the list
     */
    public void resetList() {
        this.internalList.clear();
    }

    /**
     * Adds a pet to the list.
     * The pet must not already exist in the list.
     */
    public void add(Linkable target) {
        requireNonNull(target);
        Pet toAdd = (Pet) target;
        if (contains(toAdd)) {
            throw new DuplicatePetException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the pet {@code target} in the list with {@code editedPet}.
     * {@code target} must exist in the list.
     * The pet identity of {@code editedPet} must not be the same as another existing pet in the list.
     */
    public void setPet(Pet target, Pet editedPet) {
        requireAllNonNull(target, editedPet);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PetNotFoundException();
        }

        if (!target.isSamePet(editedPet) && contains(editedPet)) {
            throw new DuplicatePetException();
        }

        internalList.set(index, editedPet);
    }

    /**
     * Removes the equivalent pet from the list.
     * The pet must exist in the list.
     */
    public void remove(Linkable target) {
        requireNonNull(target);
        Pet toRemove = (Pet) target;
        if (!internalList.remove(toRemove)) {
            throw new PetNotFoundException();
        }
    }

    public void setPets(LinkedPetList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code pets}.
     * {@code pets} must not contain duplicate pets.
     */
    public void setPets(List<Pet> pets) {
        requireAllNonNull(pets);
        if (!petsAreUnique(pets)) {
            throw new DuplicatePetException();
        }

        internalList.setAll(pets);
    }

    /**
     * sorts the list of pets by name
     */
    public void sort() {
        internalList.sort(Comparator
                .comparing((Pet pet) -> pet.getName().name.toLowerCase())); //
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Pet> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Pet> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LinkedPetList)) {
            return false;
        }

        LinkedPetList otherUniquePetList = (LinkedPetList) other;
        return internalList.equals(otherUniquePetList.internalList);
    }

    /**
     * formats the list of pets linked to an owner for display as a field in the owner card
     * @returns a string to be displayed
     */
    public String getAsField() {
        StringBuilder formattedPets = new StringBuilder();

        formattedPets.append("Pets: ");

        if (internalList.isEmpty()) {
            formattedPets.append("Warning! This owner is not linked to any pets");
            return formattedPets.toString();
        }

        for (Pet pet : internalList) {
            if (formattedPets.length() > 6) { // Account for "Pets: "
                formattedPets.append(" | ");
            }
            formattedPets.append(pet.getName());
        }

        return formattedPets.toString();
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code pets} contains only unique pets.
     */
    private boolean petsAreUnique(List<Pet> pets) {
        for (int i = 0; i < pets.size() - 1; i++) {
            for (int j = i + 1; j < pets.size(); j++) {
                if (pets.get(i).isSamePet(pets.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}