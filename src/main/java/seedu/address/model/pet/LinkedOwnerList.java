package seedu.address.model.pet;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.owner.Owner;
import seedu.address.model.owner.exceptions.DuplicateOwnerException;
import seedu.address.model.owner.exceptions.OwnerNotFoundException;
import seedu.address.model.pet.exceptions.InvalidOwnerNumberException;

/**
 * A list of Owners that enforces uniqueness between its elements and does not allow nulls.
 * A Owner is considered unique by comparing using {@code Owner#isSameOwner(Owner)}. As such, adding and updating of
 * Owners uses Owner#isSameOwner(Owner) for equality so as to ensure that the Owner being added or updated is
 * unique in terms of identity in the UniqueOwnerList. However, the removal of a Owner uses Owner#equals(Object) so
 * as to ensure that the Owner with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Owner#isSameOwner(Owner)
 */
public class LinkedOwnerList implements Iterable<Owner> {

    private final ObservableList<Owner> internalList = FXCollections.observableArrayList();
    private final ObservableList<Owner> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent Owner as the given argument.
     */
    public boolean contains(Owner toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameOwner);
    }

    /**
     * Returns the observable list
     */
    public ObservableList<Owner> getList() {
        return this.internalList;
    }

    /**
     * Adds a Owner to the list.
     * The Owner must not already exist in the list.
     */
    public void add(Owner toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateOwnerException();
        }
        if (internalList.size() > 0) {
            throw new InvalidOwnerNumberException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the Owner {@code target} in the list with {@code editedOwner}.
     * {@code target} must exist in the list.
     * The Owner identity of {@code editedOwner} must not be the same as another existing Owner in the list.
     */
    public void setOwner(Owner target, Owner editedOwner) {
        requireAllNonNull(target, editedOwner);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new OwnerNotFoundException();
        }

        if (!target.isSameOwner(editedOwner) && contains(editedOwner)) {
            throw new DuplicateOwnerException();
        }

        internalList.set(index, editedOwner);
    }

    /**
     * Removes the equivalent Owner from the list.
     * The Owner must exist in the list.
     */
    public void remove(Owner toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new OwnerNotFoundException();
        }
        System.out.println("hi");
    }

    public void setOwners(LinkedOwnerList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code Owners}.
     * {@code Owners} must not contain duplicate Owners.
     */
    public void setOwners(List<Owner> Owners) {
        requireAllNonNull(Owners);
        if (!OwnersAreUnique(Owners)) {
            throw new DuplicateOwnerException();
        }

        internalList.setAll(Owners);
    }

    /**
     * sorts the list of Owners by name
     */
    public void sort() {
        internalList.sort(Comparator
                .comparing((Owner Owner) -> Owner.getName().fullName.toLowerCase())); //
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Owner> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Owner> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LinkedOwnerList)) {
            return false;
        }

        LinkedOwnerList otherUniqueOwnerList = (LinkedOwnerList) other;
        return internalList.equals(otherUniqueOwnerList.internalList);
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
     * Returns true if {@code Owners} contains only unique Owners.
     */
    private boolean OwnersAreUnique(List<Owner> Owners) {
        for (int i = 0; i < Owners.size() - 1; i++) {
            for (int j = i + 1; j < Owners.size(); j++) {
                if (Owners.get(i).isSameOwner(Owners.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}
