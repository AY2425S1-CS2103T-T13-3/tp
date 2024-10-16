package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.owner.Owner;
import seedu.address.model.owner.UniqueOwnerList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.pet.*;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameOwner and .isSamePet comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueOwnerList owners;
    private final UniquePetList pets;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */

    {
        persons = new UniquePersonList();
        owners = new UniqueOwnerList();
        pets = new UniquePetList();
    }

    public AddressBook() {
    }

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the owner list with {@code owners}.
     * {@code owners} must not contain duplicate owners.
     */
    public void setOwners(List<Owner> owners) {
        this.owners.setOwners(owners);
    }


    /**
     * Replaces the contents of the pet list with {@code pets}.
     * {@code pets} must not contain duplicate pets.
     */
    public void setPets(List<Pet> pets) {
        this.pets.setPets(pets);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setOwners(newData.getOwnerList());
        setPets(newData.getPetList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// owner-level operations

    /**
     * Returns true if an owner with the same identity as {@code owner} exists in the address book.
     */
    public boolean hasOwner(Owner owner) {
        requireNonNull(owner);
        return owners.contains(owner);
    }

    /**
     * Adds an owner to the address book.
     * The owner must not already exist in the address book.
     */
    public void addOwner(Owner o) {
        owners.add(o);
    }

    /**
     * Replaces the given owner {@code target} in the list with {@code editedOwner}.
     * {@code target} must exist in the address book.
     * The owner identity of {@code editedOwner} must not be the same as another existing owner in the address book.
     */
    public void setOwner(Owner target, Owner editedOwner) {
        requireNonNull(editedOwner);

        owners.setOwner(target, editedOwner);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeOwner(Owner key) {
        owners.remove(key);
    }

    //// pet-level operations

    /**
     * Returns true if a pet with the same identity as {@code pet} exits in the address book.
     */
    public boolean hasPet(Pet pet) {
        requireNonNull(pet);
        return pets.contains(pet);
    }

    /**
     * Adds a pet to the address book.
     * The pet must not already exist in the address book.
     */
    public void addPet(Pet p) {
        pets.add(p);
    }

    /**
     * Replaces the given pet {@code target} in the list with {@code editedPet}.
     * {@code target} must exist in the address book.
     * The pet identity of {@code editedPet} must not be the same as another existing pet in the address book.
     */
    public void setPet(Pet target, Pet editedPet) {
        requireNonNull(editedPet);

        pets.setPet(target, editedPet);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePet(Pet key) {
        pets.remove(key);
    }
    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("persons", persons)
            .add("owners", owners).add("pets", pets)
            .add("owners", owners)
            .toString();
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Owner> getOwnerList() {
        return owners.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Pet> getPetList() {
        return pets.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddressBook)) {
            return false;
        }

        AddressBook otherAddressBook = (AddressBook) other;

        return persons.equals(otherAddressBook.persons) && owners.equals(otherAddressBook.owners)
                && pets.equals(otherAddressBook.pets);
        return persons.equals(otherAddressBook.persons) && owners.equals(otherAddressBook.owners);
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
