package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

public class Remark {
    public final String value;
    public static final String VALIDATION_REGEX = "[\\\\p{Alnum}][\\\\p{Alnum} ]*";
    public static final String MESSAGE_CONSTRAINTS = "Remarks should only contain alphanumeric characters and spaces, "
            + "and it should not be blank";

    /**
     * Constructs an {@code Remark}.
     *
     * @param remark A valid remark.
     */
    public Remark(String remark) {
        requireNonNull(remark);
        value = remark;
    }

    public static boolean isValidRemark(String test) {
        return test.matches("^$|[^\\s].*");
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Remark)) {
            return false;
        }

        Remark otherRemark = (Remark) other;
        return value.equals(otherRemark.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
