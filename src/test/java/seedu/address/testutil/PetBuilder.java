package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.pet.Age;
import seedu.address.model.pet.Breed;
import seedu.address.model.pet.Name;
import seedu.address.model.pet.Pet;
import seedu.address.model.pet.Sex;
import seedu.address.model.pet.Species;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Pet objects.
 */
public class PetBuilder {

    public static final String DEFAULT_NAME = "Sophie";
    public static final String DEFAULT_SPECIES = "Dog";
    public static final String DEFAULT_BREED = "German Shepard";
    public static final String DEFAULT_AGE = "5";
    public static final String DEFAULT_SEX = "F";

    private Name name;
    private Species species;
    private Breed breed;
    private Age age;
    private Sex sex;
    private Set<Tag> tags;

    /**
     * Creates a {@code PetBuilder} with the default details.
     */
    public PetBuilder() {
        name = new Name(DEFAULT_NAME);
        species = new Species(DEFAULT_SPECIES);
        breed = new Breed(DEFAULT_BREED);
        age = new Age(DEFAULT_AGE);
        sex = new Sex(DEFAULT_SEX);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PetBuilder with the data of {@code petToCopy}.
     */
    public PetBuilder(Pet petToCopy) {
        name = petToCopy.getName();
        species = petToCopy.getSpecies();
        breed = petToCopy.getBreed();
        age = petToCopy.getAge();
        sex = petToCopy.getSex();
        tags = new HashSet<>(petToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Pet} that we are building.
     */
    public PetBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Species} of the {@code Pet} that we are building.
     */
    public PetBuilder withSpecies(String species) {
        this.species = new Species(species);
        return this;
    }

    /**
     * Sets the {@code Breed} of the {@code Pet} that we are building.
     */
    public PetBuilder withBreed(String breed) {
        this.breed = new Breed(breed);
        return this;
    }

    /**
     * Sets the {@code Age} of the {@code Pet} that we are building.
     */
    public PetBuilder withAge(String age) {
        this.age = new Age(age);
        return this;
    }

    /**
     * Sets the {@code Sex} of the {@code Pet} that we are building.
     */
    public PetBuilder withSex(String sex) {
        this.sex = new Sex(sex);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Pet} that we are building.
     */
    public PetBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    public Pet build() {
        return new Pet(name, species, breed, age, sex, tags);
    }
}
