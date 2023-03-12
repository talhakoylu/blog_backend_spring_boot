package backend.core.utils;

import backend.core.utils.exceptions.SlugHelperException;

import java.text.Normalizer;

public class SlugHelper {

    public static String toSlug(String value) {

        if (value == null || value.trim().isEmpty() || value.trim().isBlank()){
            System.out.println("value: " + value);
            throw new SlugHelperException("Slug value error.");
        }

        String normalized = Normalizer.normalize(value, Normalizer.Form.NFD);

        return normalized.replaceAll("[^\\p{ASCII}]", "") // remove non-ASCII chars
                .toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "") // remove non-alphanumeric chars except -
                .replaceAll("\\s+", "-") // replace whitespace with -
                .replaceAll("-{2,}", "-") // replace consecutive - with single -
                .replaceAll("^-|-$", "");

    }

}
